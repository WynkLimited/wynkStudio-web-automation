package in.wynk.steps;

import com.amazonaws.services.dynamodbv2.xspec.S;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import in.wynk.framework.SoftAssert;
import in.wynk.framework.Utils;
import in.wynk.pages.*;
import junit.framework.Assert;

import java.util.Iterator;
import java.util.Set;


public class SongDashBoardStep {

    SongDashboardPage songDashboardPage ;
    AuthorizationPage authpage ;
    CommonStudioPage commonStudioPage;
    SoftAssert anAssert;
    ArtistHomePage studioPage;
    Utils utility;
    ReleaseSummaryPage releaseSummaryPage;
    String nameOfSong = null, key =null;
    Double totalStream , uniqueListens, followerCount;
    boolean flag = false ;
    String uniqueListenerReleasePage, totalStreamReleasePage, followerCountReleasePage, likeReleasePage, htActivatedReleasePage;


    public SongDashBoardStep(AuthorizationPage authpage, CommonStudioPage commonStudioPage, SoftAssert anAssert,
                             ArtistHomePage studioPage
                             , Utils utility, SongDashboardPage songDashboardPage,
                             ReleaseSummaryPage releaseSummaryPage)
    {
        this.authpage = authpage;
        this.anAssert =  anAssert;
        this.commonStudioPage = commonStudioPage;
        this.studioPage = studioPage;
        this.utility = utility;
        this.songDashboardPage = songDashboardPage;
        this.releaseSummaryPage = releaseSummaryPage;


    }



    @Then("Assert important UI elements on Dashboard")
    public void assertImportantUIElementsOnDashboard()
    {
        Assert.assertTrue("Heading of the page are missing",songDashboardPage.ifDasboardHeadingsPresent());
        Assert.assertTrue("Filter is not working", songDashboardPage.isLast7DaysFilterPresent());
        Assert.assertTrue("Like and HT activated Tabs are present",
                songDashboardPage.isLikeCountAndHTActivatedCountTabPresent());
        Assert.assertTrue("Search Text box not present",
                songDashboardPage.isSearchTextBoxPresent());
        Assert.assertTrue("Profile Button",
                commonStudioPage.isProfileButtonPresent());
        Assert.assertTrue("Few songs may have broken artwork",songDashboardPage.isArtCoverForAllsongsPresent());
    }


    @Then("Assert details of song are appearing on UI")
    public void assertDetailsOfSongAreAppearingOnUI() {
        anAssert.assertTrue(( songDashboardPage.totalSinglesAsPerTableHeading()==songDashboardPage.songCountOnSinglePage()),
                "Correct song count is not there");
        Assert.assertTrue("Data not coming for songs",songDashboardPage.checkIfDataIsComingForAllTheSongs());

    }

    @And("Assert that in All song tab contains all types of songs")
    public void assertThatInAllSongTabContainsAllTypesOfSongs() {


        commonStudioPage.verifySongCountIsCorrect(CommonStudioPage.tabOption.ALL);
        commonStudioPage.verifySongCountIsCorrect(CommonStudioPage.tabOption.DRAFT);
        commonStudioPage.verifySongCountIsCorrect(CommonStudioPage.tabOption.LIVE);
        commonStudioPage.verifySongCountIsCorrect(CommonStudioPage.tabOption.INREVIEW);
        commonStudioPage.verifySongCountIsCorrect(CommonStudioPage.tabOption.REJECTED);
    }



    @And("Click on Live Tab if it has more than zero songs")
    public void clickOnLiveTabIfItHasMoreThanZeroSongs() {
       flag = commonStudioPage.clickLiveSongTab();
    }

    @Then("click on song Title")
    public void clickOnSongTitle()
    {
        if(flag) {
            nameOfSong = commonStudioPage.clickFirstSongReleaseSummary();
        }

    }

    @Then("Assert Release summary Page of Live song is opened")
    public void assertReleaseSummaryPageOfLiveSongIsOpened()
    {
        if ( flag) {
            Assert.assertTrue(nameOfSong.equalsIgnoreCase(releaseSummaryPage.getNameOfSong()));
            Assert.assertTrue(releaseSummaryPage.isLiveTextPresent());
            Assert.assertNotNull(releaseSummaryPage.getTotalStream());
            Assert.assertNotNull(releaseSummaryPage.getLikes());
            Assert.assertNotNull(releaseSummaryPage.getUniqueListens());
            Assert.assertNotNull(releaseSummaryPage.getHelloTunesActivated());
            Assert.assertTrue(releaseSummaryPage.isLastUpdatedTextPresentLiveState());
            Assert.assertTrue(releaseSummaryPage.isListenOnWynkButtonPresent());
        }
    }

    @And("Click on back Arrow")
    public void clickOnBackArrow()
    {
        if (flag)
       releaseSummaryPage.clickBackArrow();
    }

    @And("Click on Draft Tab if it has more than zero songs")
    public void clickOnDraftTabIfItHasMoreThanZeroSongs()
    {
        flag = commonStudioPage.clickDraftSongTab();
    }

    @Then("Assert Release summary Page of Draft song is opened")
    public void assertReleaseSummaryPageOfDraftSongIsOpened()
    {
        if (flag) {
            Assert.assertTrue(nameOfSong.equalsIgnoreCase(releaseSummaryPage.getNameOfSong()));
            Assert.assertTrue(releaseSummaryPage.checkIfAllElementsOnDraftReleaseSummaryPresent());
        }
    }

    @And("Click on In-Review Tab if it has more than zero songs")
    public void clickOnInReviewTabIfItHasMoreThanZeroSongs() {
      flag=   commonStudioPage.clickinReviewSongTab();
    }

    @Then("Assert Release summary Page of in Review song is opened")
    public void assertReleaseSummaryPageOfInReviewSongIsOpened() {

        if (flag) {
            Assert.assertTrue(nameOfSong.equalsIgnoreCase(releaseSummaryPage.getNameOfSong()));
            Assert.assertTrue(releaseSummaryPage.checkIfAllElementsOnInReviewReleaseSummaryPresent());
        }
    }

    @And("Click on Rejected Tab if it has more than zero songs")
    public void clickOnRejectedTabIfItHasMoreThanZeroSongs() {
       flag =  commonStudioPage.clickRejectedSongTab();
    }

    @Then("Assert Release summary Page of Rejected song is opened")
    public void assertReleaseSummaryPageOfRejectedSongIsOpened() {
        if(flag) {
            Assert.assertTrue(nameOfSong.equalsIgnoreCase(releaseSummaryPage.getNameOfSong()));
            Assert.assertTrue(releaseSummaryPage.checkIfAllElementsOnRejectedReleaseSummaryPresent());
        }
    }

    @Then("Click first live song is clickable")
    public void clickFirstLiveSongIsClickable() {

        flag = songDashboardPage.clickFirstLiveSongInTable();
    }

    @And("Assert the name of song click on Release Summary page")
    public void assertTheNameOfSongClickOnReleaseSummaryPage() {

        if(flag)
            Assert.assertTrue(songDashboardPage.songAnalyticsForValidation.containsKey(releaseSummaryPage.getNameOfSong()));


    }

    @Then("click on Filter")
    public void clickOnFilter() {

        songDashboardPage.clickLastXDaysDropDown();
    }

    @And("select Last {int} days Filter from drop down")
    public void selectLastDaysFilterFromDropDown(int arg0) {

        if(arg0 == 30)
            songDashboardPage.clickLast30DaysFilterInsideDropDown();
        else if(arg0 == 90)
            songDashboardPage.clickLast90DaysFilterInsideDropDown();
    }

    @Then("Assert filter name is changed to Last {int} days")
    public void assertFilterNameIsChangedToLastDays(int arg0)
    {
        Assert.assertTrue(songDashboardPage.getLastXDaysDropDownText().contains(String.valueOf(arg0)));
    }

    @And("Assert content is coming for all the songs in the table")
    public void assertContentIsComingForAllTheSongsInTheTable() {
        songDashboardPage.checkIfDataIsComingForAllTheSongs();
    }

    @And("Assert unique Stream and Total Stream count is greater than previous filter")
    public void assertUniqueStreamAndTotalStreamCountIsGreaterThanPreviousFilter()
    {
        Assert.assertTrue(totalStream<=utility.convertCountToDouble(songDashboardPage.getTotalStreamsCount())
       && uniqueListens <= utility.convertCountToDouble(songDashboardPage.getUniqueListenersCount())
               && followerCount <= utility.convertCountToDouble(songDashboardPage.getFollowersCount()
               ));
    }

    @Then("click on download Button")
    public void clickOnDownloadButton() {
        songDashboardPage.clickDownloadButton();


    }

    @Then("Pick random dates in current month")
    public void pickRandomDatesInCurrentMonth() {
        songDashboardPage.pickDatesRandomlyInCurrentMonth();
    }

    @And("click on done button in Download section")
    public void clickOnDoneButtonInDownloadSection() {
        songDashboardPage.clickDoneButtonInDownloadSection();
        Assert.assertTrue(songDashboardPage.isNotificationMsgForSuccessfulDownloadPresent());
        int x = 0;
    }

    @And("Select All the time")
    public void selectAllTheTime() {
        songDashboardPage.clickAllTheTimeRadioButton();
    }

    @Then("Click on cross button present in download section")
    public void clickOnCrossButtonPresentInDownloadSection() {
        songDashboardPage.clickCrossInDownloadSection();
    }

    @Then("click on any song present in dashboard")
    public void clickOnAnySongPresentInDashboard()
    {
      songDashboardPage.clickFirstLiveSongInTable();
    }



    @Then("Check by default last {int} days Filter should be selected")
    public void checkByDefaultLastDaysFilterShouldBeSelected(int arg0) {
        songDashboardPage.isLastXDaysFilterPresent(arg0);
    }

    @And("Click on Unique Listenes")
    public void clickOnUniqueListenes() {
        uniqueListenerReleasePage = releaseSummaryPage.getUniqueListenReleasePage();
        releaseSummaryPage.clickUniqueListensBox();
    }

    @Then("Assert that a Trends pop up is coming up with correct count {string}")
    public void assertThatATrendsPopUpIsComingUpWithCorrectCount(String arg0) {
        if(arg0.equalsIgnoreCase("uniqueListens")) {
            Assert.assertTrue("Unique listen count on relase page and graph is not same",
                    releaseSummaryPage.getUniqueListenTrendGraph().equalsIgnoreCase(uniqueListenerReleasePage));

        }
        else if(arg0.equalsIgnoreCase("totalStreams"))
        {
            Assert.assertTrue("Total streams count on relase page and graph is not same",
                    releaseSummaryPage.getTotalStreamsTrendGraph().equalsIgnoreCase(totalStreamReleasePage));
        }
        else if(arg0.equalsIgnoreCase("likes"))
        {
            Assert.assertTrue("Total streams count on relase page and graph is not same",
                    releaseSummaryPage.getHTActivatedTrendGraph().equalsIgnoreCase(likeReleasePage));
        }
        else if(arg0.equalsIgnoreCase("ht"))
        {
            Assert.assertTrue("Total streams count on relase page and graph is not same",
                    releaseSummaryPage.getHTActivatedTrendGraph().equalsIgnoreCase(htActivatedReleasePage));
        }
        Assert.assertTrue("Graph on Trend pop up is not coming", releaseSummaryPage.isGraphPresentOnTrendsPage());
    }

    @Then("click on cross button on Trends pop up")
    public void clickOnCrossButtonOnTrendsPopUp() {
        releaseSummaryPage.clickCrossIcon();
    }

    @And("Click on Total Streams")
    public void clickOnTotalStreams() {
        totalStreamReleasePage = releaseSummaryPage.getTotalStreamsReleasePage();
        releaseSummaryPage.clickTotalStreamsBox();
    }

    @And("Assert Total Streams and Unique Listenes Graphs are displaying")
    public void assertTotalStreamsAndUniqueListenesGraphsAreDisplaying()
    {
         releaseSummaryPage.ifGraphPresentRowWise();
    }

    @And("Assert that graph should be disappeared")
    public void assertThatGraphShouldBeDisappeared() {
    }

    @And("Read values of unique listens, total stream and follower from dashboard")
    public void readValuesOfUniqueListensTotalStreamAndFollowerFromDashboard() {

        totalStream = utility.convertCountToDouble(songDashboardPage.getTotalStreamsCount());
        uniqueListens= utility.convertCountToDouble(songDashboardPage.getUniqueListenersCount());
        followerCount = utility.convertCountToDouble(songDashboardPage.getFollowersCount());
    }

    @Then("Click first row in Release summary page")
    public void clickFirstRowInReleaseSummaryPage() {

        releaseSummaryPage.clickFirstLiveSongInTable();
    }

    @Then("click on play button on Release Summary page")
    public void clickOnPlayButtonOnReleaseSummaryPage() {
      Assert.assertTrue(releaseSummaryPage.clickPlayButton().equalsIgnoreCase(releaseSummaryPage.getNameOfSong()));
    }

    @And("Assert that song name is same on Wynk and Wynk studio")
    public void assertThatSongNameIsSameOnWynkAndWynkStudio() {

    }


    @Then("Assert that Live song is appearing in the results of search")
    public void assertThatLiveSongIsAppearingInTheResultsOfSearch()
    {
        Assert.assertTrue(commonStudioPage.getInnerHtmlSearchContainer().contains(nameOfSong));
    }

    @And("Click on the song in result")
    public void clickOnTheSongInResult() {

        flag = commonStudioPage.getInnerHtmlSearchContainer().contains(nameOfSong);

         if (flag)
            commonStudioPage.clickFirstSongSearchResult();
    }


    @Then("Click on cross icon")
    public void clickOnCrossIcon() {
        commonStudioPage.clickCrossSignInSearchBar();
    }

    @Then("Assert that No Search Results should appear")
    public void assertThatNoSearchResultsShouldAppear() throws InterruptedException {

        Assert.assertTrue(commonStudioPage.isNoSearchResultFoundPresent());
    }

    @And("Search song for that artist {string}")
    public void searchSongForThatArtist(String arg0) throws Exception {

        if(arg0 == null | arg0 == " " | arg0.isEmpty() ) {
            Iterator<String> itr = songDashboardPage.songAnalyticsForValidation.keySet().iterator();
            while (itr.hasNext()) {
                nameOfSong = itr.next();
                commonStudioPage.enterSongNameInSearchBox(nameOfSong);
                break;
            }
        }
        else
        {
            commonStudioPage.enterSongNameInSearchBox(arg0);
        }
    }




}
