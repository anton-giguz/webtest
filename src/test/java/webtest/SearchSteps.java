package webtest;

import net.thucydides.core.annotations.Step;


public class SearchSteps {

    SearchPage page;

    @Step
    public void isOnTheHomePage() {
        page.open();
    }

    @Step
    public void searchesByCriteria() {
        page.selectTopCategory();
        page.selectSubCategory();
        page.selectManufacturer();
        page.selectSorting();
        page.saveFoundByCriteria();
    }

    @Step
    public void searchesByKeywords() {
        page.typeKeywords();
        page.saveFoundByKeywords();
    }

    @Step
    public void seesMatchingResults() {
        page.compareFound();
    }

}
