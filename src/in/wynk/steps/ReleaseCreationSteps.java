package in.wynk.steps;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import junit.framework.Assert;
import in.wynk.pages.*;
import in.wynk.common.Utils;

import java.io.IOException;
import java.util.HashMap;

import static org.apache.kafka.common.utils.Utils.sleep;

public class ReleaseCreationSteps {

    CreateArtistPage createArtistPage;
    AuthorizationPage authpage ;
    CommonStudioPage commonStudioPage;
    ArtistHomePage studioPage;
    Utils utility;
    CommonPage commonPage;
    PodcastHomePage podcastHomePage;
    ProfessionPage professionPage;
    ClaimArtistPage claimArtistPage;
    String  nameOfPrimaryArtist;
    ReleaseCreationPage releaseCreationPage;



    public ReleaseCreationSteps(ClaimArtistPage claimArtistPage, CreateArtistPage createArtistPage,AuthorizationPage authpage, CommonStudioPage commonStudioPage,
                               ArtistHomePage studioPage, Utils utility, CommonPage  commonPage,
                               PodcastHomePage podcastHomePage , ProfessionPage professionPage, ReleaseCreationPage releaseCreationPage )
    {
        this.authpage = authpage;

        this.commonStudioPage = commonStudioPage;
        this.studioPage = studioPage;
        this.utility = utility;
        this.commonPage = commonPage;
        this.podcastHomePage =podcastHomePage;
        this.professionPage =professionPage;
        this.createArtistPage= createArtistPage;
        this.claimArtistPage =claimArtistPage;
        this.releaseCreationPage=releaseCreationPage;
    }

    @Given("User clicks on New Release button")
    public void userClicksOnNewReleaseButton() {

        studioPage.clickNewReleaseButton();
    }

    @Then("Assert Yes button on pop up")
    public void assertYesButtonOnPopUp() {
     Assert.assertTrue("Yes Radio Button Not present",  releaseCreationPage.isYesRadioButtonPresent());
    }

    @Then("Assert No button on pop up")
    public void assertNoButtonOnPopUp() {

        Assert.assertTrue("No Radio Button Not present",  releaseCreationPage.isNoRadioButtonPresent());
    }

    @Then("Assert Are you add heading")
    public void assertAreYouAddHeading() {
        Assert.assertTrue("Are You Adding new release label Not present",  releaseCreationPage.isAreYouAddingFreshRelLabelPresent());
    }

    @Then("Assert cross button on Pop UP")
    public void assertCrossButtonOnPopUP() {
        Assert.assertTrue("Cross button on pop up Not present",  releaseCreationPage.isCrossButtonOnPopUpPresent());
    }

    @Then("click on cross button on pop up")
    public void clickOnCrossButtonOnPopUp() {
        releaseCreationPage.clickCrossButtonOnPopUp();
    }

    @And("Assert back drop modal should not be present")
    public void assertBackDropModalShouldNotBePresent() {
        Assert.assertFalse("Back Drop modal Not present",releaseCreationPage.isBackDropModalPresent());
    }

    @Then("click on No button on pop up")
    public void clickOnNoButtonOnPopUp() {
        releaseCreationPage.clickNoRadioButtonOnPopUp();
    }

    @And("Assert error message should be present on pop up")
    public void assertErrorMessageShouldBePresentOnPopUp() {
      Assert.assertTrue("Contact us button present",releaseCreationPage.isContactUsButtonPresent());
    }

    @Then("click on Yes button on pop up")
    public void clickOnYesButtonOnPopUp() {
        releaseCreationPage.clickYesButtonOnPopUp();
    }

    @And("Assert Add New Release page should be present")
    public void assertAddNewReleasePageShouldBePresent() {
        Assert.assertTrue("Add Release Detail Header present",releaseCreationPage.isAddReleaseDetailHeaderPresent());
    }

    @Then("Click on continue Button on Pop up")
    public void clickOnContinueButtonOnPopUp() {
        releaseCreationPage.clickContinueButtonPopUp();
    }

    @And("Click on Back Arrow On Add Release Detail Page")
    public void clickOnBackArrowOnAddReleaseDetailPage() {
        releaseCreationPage.clickBackArrowAddReleaseDetailPage();
    }

    @Then("Assert Add New Release Button should be present")
    public void assertAddNewReleaseButtonShouldBePresent()
    {
        Assert.assertTrue("New Release Button not present",studioPage.isNewReleaseButtonPresent());
    }


    @And("Click on Continue button Add Release")
    public void clickOnContinueButtonAddRelease() {
      releaseCreationPage.clickContinueUploadReleasePage();
    }

    @Then("Assert Error Msgs on Required Fields Upload Release Page")
    public void assertErrorMsgsOnRequiredFieldsUploadReleasePage() {
        Assert.assertTrue("Error Msg Regressionming",
                releaseCreationPage.isErrorMsgsForMissingFieldUploadReleasePage());
        Assert.assertTrue("Artwork Error Msg Regressionming",releaseCreationPage.isArtworkMissingAlertPresent());
    }

    @Then("click on upload audio button")
    public void clickOnUploadAudioButton() throws InterruptedException {
        Thread.sleep(10000);
        releaseCreationPage.clickAddAudioButton();
    }

    @And("upload audio file")
    public void uploadAudioFile() throws IOException, InterruptedException {
        releaseCreationPage.uploadAudio();


    }

    @Then("Enter Release title")
    public void enterReleaseTitle() throws Exception {
        releaseCreationPage.typeReleaseTitleTextBox();
    }

    @Then("select ISRC")
    public void selectISRC() throws Exception {
        releaseCreationPage.clickGenerateISRCForMe();
    }

    @And("Select UPC")
    public void selectUPC() throws Exception {
        releaseCreationPage.clickGenerateUPCForMe();
    }

    @Then("click on upload artwork button")
    public void clickOnUploadArtworkButton() throws Exception {

        releaseCreationPage.clickUploadArtwork();
    }

    @And("Upload Artwork File")
    public void uploadArtworkFile()  {

        try {
            releaseCreationPage.uploadArtwork();

        }catch (IOException ex) {
           ex.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    @Then("Assert Add your release Details is present")
    public void assertAddYourReleaseDetailsIsPresent() {
       Assert.assertTrue("AddYourReleaseDetailsHeading", releaseCreationPage.isAddYourReleaseDetailsHeading());
    }


    @Then("click on play button")
    public void clickOnPlayButton() {
        releaseCreationPage.clickPlayButton();

    }

    @And("Assert if the song is playing")
    public void assertIfTheSongIsPlaying() throws InterruptedException {
        Assert.assertTrue("Song not played", releaseCreationPage.checkIfSongIsPlaying());
    }

    @Then("click on cross button")
    public void clickOnCrossButton() {
        releaseCreationPage.clickCrossButton();
    }

    @And("verify that audio file is removed")
    public void verifyThatAudioFileIsRemoved() {
        Assert.assertFalse("Autio file not deleted after clicking cross button", releaseCreationPage.isProgressBarPresent());
    }

    @And("upload .wav audio file")
    public void uploadWavAudioFile() throws IOException, InterruptedException {

        releaseCreationPage.uploadWAVAudio();
        Thread.sleep(20000);

    }

    @And("Upload small resolution Artwork File")
    public void uploadSmallResolutionArtworkFile() throws IOException, InterruptedException {
        releaseCreationPage.uploadSmallerResolutionArtwwork();
        Thread.sleep(20000);
    }

    @And("Assert the error msg for smaller resolution artwork")
    public void assertTheErrorMsgForSmallerResolutionArtwork() {

     Assert.assertTrue("Smaller resolution err msg not present",
             releaseCreationPage.isSmallerResolutionErrorMsgPresent());
    }

    @Then("Click on Yes I have UPC")
    public void clickOnYesIHaveUPC() {

        releaseCreationPage.clickYesUPCButton();
    }

    @And("Click on Yes I have ISRC")
    public void clickOnYesIHaveISRC() {
        releaseCreationPage.clickYesISRCButton();
    }

    @Then("Select Primary artist Role")
    public void selectPrimaryArtistRole() throws InterruptedException {
        releaseCreationPage.selectPrimaryArtistRole();
    }
    @And("assert that the name of Primary artist is correct")
    public void assertThatTheNameOfPrimaryArtistIsCorrect() {

        Assert.assertTrue("Primary name is Regressionrrect", nameOfPrimaryArtist.equalsIgnoreCase(releaseCreationPage.readPrimaryArtistName()));
    }

    @Then("Select Supporting Artist role and enter name")
    public void selectSupportingArtistRoleAndEnterName() throws Exception {
        releaseCreationPage.selectSupportingArtistRoleAndEnterName();
    }

    @And("click on cross button for secondary artist")
    public void clickOnCrossButtonForSecondaryArtist() throws Exception {
        releaseCreationPage.clickCrossButtonSecondaryArtist();
    }

    @And("Assert that secondary artist is removed")
    public void assertThatSecondaryArtistIsRemoved() {
        Assert.assertTrue("Secondary artist is not removed", releaseCreationPage.isAddSupportingArtistButtonPresent());
    }

    @Then("Select Primary Language")
    public void selectPrimaryLanguage() throws InterruptedException {
        releaseCreationPage.selectPrimaryLanguage();
    }

    @And("select Genre")
    public void selectGenre() throws Exception {
        releaseCreationPage.selectPrimaryGenre();
    }

    @Then("upload lyrics file")
    public void uploadLyricsFile() throws IOException, InterruptedException {
        releaseCreationPage.uploadLyricFile();
    }

    @And("Remove lyrics file")
    public void removeLyricsFile() {

        releaseCreationPage.clickRemoveLRCfile();
    }

    @And("assert file is removed")
    public void assertFileIsRemoved() {
      Assert.assertFalse("LRC file is not removed",releaseCreationPage.isLrcFileSuccessfullyUploaded());
    }

    @Then("Enter lyrics manually")
    public void enterLyricsManually() throws Exception
    {
        releaseCreationPage.clickAddLyricRadioButton();
        releaseCreationPage.typeLyricsManually();
    }

    @And("Select No in explicit content")
    public void selectNoInExplicitContent() {
        releaseCreationPage.clickNoExplicitContentRadioButton();

    }

    @And("Select No in previously release")
    public void selectNoInPreviouslyRelease() {
        releaseCreationPage.clickNoPreviouslyReleaseRadioButton();
    }


    @And("Assert that duplicate clipnames not allowed msg is showing")
    public void assertThatDuplicateClipnamesNotAllowedMsgIsShowing() {

       Assert.assertTrue( "Duplicate Error msg is not showing",releaseCreationPage.isHTDuplicateErrorMsgExist());
    }

    @Then("Select as soon as possile")
    public void selectAsSoonAsPossile() {
        releaseCreationPage.clickAsSoonAsPosibleRadioButton();
    }

    @Then("Match the data is correct")
    public void matchTheDataIsCorrect() {
        releaseCreationPage.matchTheDetails();
    }

    @Then("Click on confirm and Submit")
    public void clickOnConfirmAndSubmit() {
        releaseCreationPage.clickConfirmAndSubmitButton();
    }


    @Then("Click on dashboard button")
    public void clickOnDashboardButton() {

        studioPage.clickDashboardButtonArtistHomePage();
        nameOfPrimaryArtist = studioPage.getNameOfArtistDashBoard();
    }

    @And("Click on studio home button")
    public void clickOnStudioHomeButton() {
        studioPage.clickStudioButtonArtistHomePage();
    }


    @Then("click on upload lyrics along")
    public void clickOnUploadLyricsAlong() throws IOException, InterruptedException {

        releaseCreationPage.clickUploadLyricsAlongSongRadioButton();
        Thread.sleep(1000);
    }

    @Then("Assert if the lyric file is successfully uploaded")
    public void assertIfTheLyricFileIsSuccessfullyUploaded() {
        Assert.assertTrue("LRC file not uploaded successfully",
              releaseCreationPage.isLrcFileSuccessfullyUploaded());
    }

    @Then("Enter first clip name (.*?)")
    public void enterFirstClipName(String clipName) throws Exception {
        releaseCreationPage.clickAndEnterFirstHT(clipName);
    }

    @And("Enter second clip name (.*?)")
    public void enterSecondClipName(String clipName) throws Exception {
        releaseCreationPage.clickAndEnterSecondHT(clipName);
    }

    @Then("Click on upload lyrics in lrc format button")
    public void clickOnUploadLyricsInLrcFormatButton() throws InterruptedException {
        releaseCreationPage.clickUploadFileLRCFormatButton();

    }

    @And("Click Add more HT clip button")
    public void clickAddMoreHTClipButton() {

        releaseCreationPage.clickAddMoreHTCLips();
    }

    @And("Assert that the Release is in review state")
    public void assertThatTheReleaseIsInReviewState() throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertTrue("Release name in correct",studioPage.getReleaseNameOnReleaseSummaryPage().equalsIgnoreCase("Karishma Automation"));
        Assert.assertTrue(" Not in in-review state",studioPage.getInReviewState().contains("Review"));
    }

    @Then("select multiple Genre")
    public void selectMultipleGenre() throws Exception {
        releaseCreationPage.selectMultipleGenre();

    }

    @And("Assert that multiple Genre got selected")
    public void assertThatMultipleGenreGotSelected() {
       Assert.assertTrue( "Multiple genre are not selected", releaseCreationPage.countOfGenreSlected()>1);
    }

    @And("click on Here link")
    public void clickOnHereLink() {
        releaseCreationPage.clickHereLinkOnAddHTPage();
    }

    @And("Assert if the modal opens")
    public void assertIfTheModalOpens() {
      Assert.assertTrue("HT help banner is broken",  releaseCreationPage.isHTHelpBannerIntact());
    }

    @Then("click on cross icon")
    public void clickOnCrossIcon() throws InterruptedException {
        releaseCreationPage.clickCrossIconOnHTHelpBanner();
        Thread.sleep(5000);

    }

    @And("Click on upload lyric button on modal")
    public void clickOnUploadLyricButtonOnModal() throws InterruptedException {
        releaseCreationPage.clickUploadLrcButtonOnBanner();
    }

    @Then("Select Yes in Previously release")
    public void selectYesInPreviouslyRelease() {
        releaseCreationPage.clickYesPreviouslyUploadedRadioButton();
    }

    @And("Enter invalid links")
    public void enterInvalidLinks() {

        releaseCreationPage.enterTextInReleaseLinkTextBox("karishma");
    }

    @Then("Assert for the error message for invalid URL")
    public void assertForTheErrorMessageForInvalidURL() {

        Assert.assertTrue("Invalid URL Error msg is not coming",releaseCreationPage.isWrongURLAlertPresentPreviouslyReleased());
    }

    @Then("Click on edit button for Release uploaded")
    public void clickOnEditButtonForReleaseUploaded() {
        releaseCreationPage.clickEditButtonForReleaseUploadedButton();
    }

    @Then("Select Check box for Album Title")
    public void selectCheckBoxForAlbumTitle() {

        releaseCreationPage.selectAlbumTitleCheckbox();
    }

    @Then("Enter Publisher Name")
    public void enterPublisherName() {
        if(!releaseCreationPage.getPublisherName().isEmpty())
            releaseCreationPage.enterPublisherName(releaseCreationPage.readPrimaryArtistName());
    }
}
