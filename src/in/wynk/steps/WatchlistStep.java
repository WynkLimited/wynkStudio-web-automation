package in.wynk.steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import in.wynk.pages.CommonPage;
import in.wynk.pages.HomePage;
import in.wynk.pages.WatchlistPage;
import junit.framework.Assert;

public class WatchlistStep {
    WatchlistPage watchlistPage;
    CommonPage commonPage;
    HomePage homePage;

    public WatchlistStep(WatchlistPage watchlistPage, CommonPage commonPage , HomePage homePage) {
        this.watchlistPage= watchlistPage;
        this.commonPage =commonPage;
        this.homePage = homePage;
    }

    @Then("Verify no Watchlist rails for un-registered user")
    public void verify_no_Watchlist_rails_for_un_registered_user() {
        Assert.assertTrue("Watchlist is present", !watchlistPage.VerifyIfWatchListIsPresent()  );
    }

    @When("add content to watchlist")
    public void add_content_to_watchlist() {
       watchlistPage.clickOnAddToWatchlistButton();
    }

    @Then("Verify content (.+) added into watchlist")
    public void verify_content_contentName_added_into_watchlist(String contentName) {
     //   commonPage.navigateToPage(CommonPage.navigationOption.HOME);
       Assert.assertTrue( "content is not added in watchlist " ,homePage.verifyIfContentIsAddedToWatchlist(contentName));
    }

    @Then("Verify  Watchlist icon is present for un-registered user")
    public void verify_Watchlist_icon_is_present_for_un_registered_user() {
        Assert.assertTrue("Watchlist is not present", watchlistPage.VerifyIfWatchListIsPresent()  );
    }

    @Then("delete content from watch List")
    public void delete_content_from_watch_List() {
        homePage.deleteContentFromWatchlist();
    }
    @Then("Verify cross icon is visible on hover on watchlist content")
    public void verify_cross_icon_is_visible_on_hover_on_watchlist_content() {
     homePage.VerifyCrossIconOnWatchlist();
    }

    @Then("Verify watchlist icon is visible on hover on  content card")
    public void verify_watchlist_icon_is_visible_on_hover_on_content_card() {
        homePage.VerifyWatchlistIconOnRails();
    }

    @Then("Verify remove from watchlist icon visible on hover on watchlist content")
    public void verify_remove_from_watchlist_icon_visible_on_hover_on_watchlist_content() {
        watchlistPage.verifyRemoveFromWatchlistIcon();
    }

}
