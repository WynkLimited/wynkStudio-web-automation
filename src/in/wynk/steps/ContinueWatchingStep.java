package in.wynk.steps;

import cucumber.api.java.en.Then;
import in.wynk.pages.CommonPage;
import in.wynk.pages.ContinueWatchingPage;
import in.wynk.pages.HomePage;
import junit.framework.Assert;

import java.util.List;

public class ContinueWatchingStep {
    ContinueWatchingPage continueWatchingPage;
    HomePage homePage;
    CommonPage commonPage ;

    public ContinueWatchingStep(CommonPage commonPage,HomePage homePage, ContinueWatchingPage continueWatchingPage) {
        this.continueWatchingPage = continueWatchingPage;
        this.commonPage = commonPage;
        this.homePage = homePage;
    }

    @Then("Verify that the content (.+) is added to continue watching list")
    public void verify_that_the_content_roja_is_added_to_continue_watching_list(String contentName) {
        List<String> continueWatchingList;
        continueWatchingList = homePage.getAllContinueWatchingList();
        continueWatchingPage.verifyContentIsAddedToList(continueWatchingList , contentName);
    }

    @Then("Verify that the content (.+) is not added to continue watching list")
    public void verify_that_the_content_roja_is_not_added_to_continue_watching_list(String contentName) {
        List<String> continueWatchingList;
        continueWatchingList = homePage.getAllContinueWatchingList();
        continueWatchingPage.verifyContentIsNotAddedToList(continueWatchingList , contentName);
    }

    @Then("verify there is no continue watching rail on Unregistered mode")
    public void verify_there_is_no_continue_watching_rail_on_Unregistered_mode() {
        List<String> railsNameList = homePage.getRailsName();
        Assert.assertTrue("content  added to continue watching list", !railsNameList.contains("continue watching"));
    }

    @Then("Verify continue watching list are same on homepage and watchlist page")
    public void verify_continue_watching_list_are_same_on_homepage_and_watchlist_page() {
        List<String> homePageRailsNameList = homePage.getAllContinueWatchingList();
        List<String> WatchlistPageRailsNameList = homePage.getContinueWatchingListfromWatchlistPahge() ;
       Assert.assertTrue("continue watching list are not same on homepage and watchlist page", commonPage.AssertionBetweenTwoList(homePageRailsNameList, WatchlistPageRailsNameList, ""));

    }


    @Then("delete a content from continue watching rail")
    public void delete_a_content_from_continue_watching_rail() {
      int   listSize =  homePage.getAllContinueWatchingList().size();
       homePage.deleteFirstContent("continue watching");
//       int listSizeAfterDeletion =  homePage.getAllContinueWatchingList().size();
//        Assert.assertTrue("content  not deleted from continue watching list", listSize>listSizeAfterDeletion);

    }

    @Then("Verify that the content (.+) is removed from continue watching list")
    public void verify_that_the_content_roja_is_removed_from_continue_watching_list(String contentName) {
//        if(homePage.ContinueWatchlistPresent()){
            List<String>   listSize =  homePage.getAllContinueWatchingList();
            Assert.assertTrue("content is not deleted",  !homePage.getAllContinueWatchingList().contains(contentName));
//        }else{
//            Assert.assertTrue(true);
//        }

    }

}
