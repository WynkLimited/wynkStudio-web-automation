package in.wynk.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import in.wynk.framework.SoftAssert;
import in.wynk.framework.Utils;
import in.wynk.pages.*;
import junit.framework.Assert;



public class SongDashBoardStep {

    SongDashboardPage songDashboardPage ;
    AuthorizationPage authpage ;
    CommonStudioPage commonStudioPage;
    SoftAssert anAssert;
    ArtistHomePage studioPage;
    Utils utility;
    ReleaseSummaryPage releaseSummaryPage;
    String nameOfSong = null;
    boolean flag = false ;


    public SongDashBoardStep(AuthorizationPage authpage, CommonStudioPage commonStudioPage, SoftAssert anAssert,
                              ArtistHomePage studioPage, Utils utility, SongDashboardPage songDashboardPage, ReleaseSummaryPage releaseSummaryPage)
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
        commonStudioPage.verifySongCountIsCorrect(CommonStudioPage.tabOption.LIVE);
        commonStudioPage.verifySongCountIsCorrect(CommonStudioPage.tabOption.INREVIEW);
        commonStudioPage.verifySongCountIsCorrect(CommonStudioPage.tabOption.LIVE);
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
        if(flag)
      songDashboardPage.clickFirstLiveSongInTable();
    }

    @And("Assert the name of song click on Release Summary page")
    public void assertTheNameOfSongClickOnReleaseSummaryPage() {

        if(flag) {
            Assert.assertTrue(songDashboardPage.songAnalyticsForValidation.get(
                            (songDashboardPage.getHeaderName(SongDashboardPage.HeaderName.TITLE))).
                    equalsIgnoreCase(releaseSummaryPage.getNameOfSong()));
        }

    }

}
