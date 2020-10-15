package webtest;

import java.util.List;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;


@DefaultUrl("https://market.yandex.ru")
public class SearchPage extends PageObject {

    private String foundByCriteriaTitle;
    private String foundByCriteriaPrice;
    private String foundByKeywordsTitle;
    private String foundByKeywordsPrice;

    public void selectTopCategory() {
        WebElementFacade link = find(By.linkText("Компьютеры"));
        try {
            link.click();
        } catch (ElementClickInterceptedException e) {
            openUrl(link.getAttribute("href"));
        }
    }

    public void selectSubCategory() {
        find(By.linkText("Планшеты")).click();
    }

    public void selectManufacturer() {
        find(By.xpath("//*[text()=\"Samsung\"]/parent::div")).click();
        pause();
    }

    public void selectSorting() {
        find(By.xpath("//*[text()=\"по цене\"]")).click();
        pause();
    }

    public void saveFoundByCriteria() {
        System.out.println("Logging search results!");
        int count = 0;
        for (WebElement item : getSearchResults()) {
            String title = getTitle(item);
            String price = getPrice(item);
            System.out.println("Title: " + title + "\tPrice: " + price);
            count++;
            if (count == 2) {
                foundByCriteriaTitle = title;
                foundByCriteriaPrice = price;
            }
            if (count >= 5) break;
        }
    }

    public void typeKeywords() {
        typeInto(find(By.id("header-search")), foundByCriteriaTitle);
        find(By.xpath("//*[text()=\"Найти\"]/parent::button")).click();
    }

    public void saveFoundByKeywords() {
        foundByKeywordsTitle = getTitle(getSearchResults().get(0));
        foundByKeywordsPrice = getPrice(getSearchResults().get(0));
    }

    public void compareFound() {
        assertEquals(foundByCriteriaTitle, foundByKeywordsTitle);
        assertEquals(foundByCriteriaPrice, foundByKeywordsPrice);
    }

    private void pause() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private List<WebElement> getSearchResults() {
        return find(By.xpath("//*[@data-zone-name=\"SearchResults\"]"))
                .findElements(By.xpath(".//*[@data-zone-name=\"snippet-card\"]"));
    }

    private String getTitle(WebElement item) {
        return item.findElement(By.xpath(".//*[@data-zone-name=\"title\"]")).getText();
    }

    private String getPrice(WebElement item) {
        return item.findElement(By.xpath(".//*[@data-zone-name=\"price\"]//a")).getText();
    }

}
