package in.wynk.steps;


import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import in.wynk.pages.CommonPage;
import in.wynk.pages.HomePage;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


public class HomeStep {


    in.wynk.framework.Assert Assert;
    HomePage homePage;
    CommonPage commonPage;

    public HomeStep( HomePage homePage, CommonPage commonPage, in.wynk.framework.Assert Assert) {
        this.Assert = Assert;
        this.homePage = homePage;
        this.commonPage = commonPage;

    }

    @When("^verify (.+) banner Content From API content(.+) JiraID (.+)$")
    public void verify_Banner_For_HomePage(String PageName, String mobileNumber, String jiraID) throws Exception {
        String PhoneNumber =  (String) commonPage.getGDValue(mobileNumber);
        Set<String> contentFromAPI = new HashSet<>();
        Set<String> contentFromUI = new HashSet<>();

        switch (PageName) {
            case "HOME PAGE":
                contentFromAPI.addAll(homePage.getBannerContentFromAPIForHome(PhoneNumber, commonPage.getPageID(CommonPage.PageID.HOME), "title", "en,hi").stream().collect(Collectors.toSet()));
                break;
            case "TVSHOWS PAGE":
                contentFromAPI.addAll(homePage.getBannerContentFromAPIForHome(PhoneNumber, commonPage.getPageID(CommonPage.PageID.TVSHOWS), "title", "en,hi").stream().collect(Collectors.toSet()));
                break;
            case "MOVIES PAGE":
                contentFromAPI.addAll(homePage.getBannerContentFromAPIForHome(PhoneNumber, commonPage.getPageID(CommonPage.PageID.MOVIES), "title", "en,hi").stream().collect(Collectors.toSet()));
                break;
        }
        contentFromUI.addAll(commonPage.getBannerContentName());
        commonPage.aPIuIAssertionBetweenTwoSet(contentFromAPI, contentFromUI, "BANNER", PageName, "Hindi English", jiraID);
    }

    @When("^add content in my watchlist NoOfContent (.+)$")
    public void add_content_In_My_Watchlist(String  NoOfContent) {
        homePage.addContentInWatchList(Integer.parseInt(NoOfContent));
    }

    @Then("^verify My WatchList and Continue Watchlist content$")
    public void verify_Watchlist_Content() throws InterruptedException {
        commonPage.scrollToRail("My Watchlist", 10000);
        commonPage.clickOnRail("My Watchlist");
    }

    @Then("^click on signin button$")
    public void clickOnSignButton() throws InterruptedException {
        homePage.clickOnSignInButton();

    }
}
