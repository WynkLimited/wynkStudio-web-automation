package in.wynk.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import junit.framework.Assert;
import in.wynk.pages.*;
import in.wynk.common.Utils;

import java.io.IOException;

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
    String  href1, href2;
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
        releaseCreationPage.clickNoButtonOnPopUp();
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
        Assert.assertTrue("Error Msg not coming",
                releaseCreationPage.isErrorMsgsForMissingFieldUploadReleasePage());
        Assert.assertTrue("Artwork Error Msg not coming",releaseCreationPage.isArtworkMissingAlertPresent());
    }

    @Then("click on upload audio button")
    public void clickOnUploadAudioButton() {
        releaseCreationPage.clickAddAudioButtonPopUp();
    }

    @And("upload audio file")
    public void uploadAudioFile() throws IOException, InterruptedException {
        releaseCreationPage.uploadAudio();
        Thread.sleep(20000);
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
            Thread.sleep(20000);
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
}
