package webtest;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.openqa.selenium.WebDriver;


@RunWith(SerenityRunner.class)
public class SearchStory {

    @Managed                                                                
    WebDriver driver;

    @Steps
    public SearchSteps user;

    @Test
    public void variousWaysOfSearchShouldDisplayMatchingResults() {
        // GIVEN
        user.isOnTheHomePage();

        // WHEN
        user.searchesByCriteria();
        user.searchesByKeywords();

        // THEN
        user.seesMatchingResults();
    }

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

}
