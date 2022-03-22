package in.wynk.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import junit.framework.Assert;
import in.wynk.framework.MailinatorAPI;
import in.wynk.framework.SoftAssert;
import in.wynk.pages.*;
import in.wynk.common.Utils;


public class ArtistCreationSteps {

    CreateArtistPage createArtistPage;
    AuthorizationPage authpage ;
    CommonStudioPage commonStudioPage;
    SoftAssert anAssert;
    ArtistHomePage studioPage;
    String randomEmail = null;
    Utils utility;
    CommonPage commonPage;
    MailinatorAPI mailinatorApi;
    PodcastHomePage podcastHomePage;
    ProfessionPage professionPage;
    ClaimArtistPage claimArtistPage;
    String  href1, href2;


    public ArtistCreationSteps(ClaimArtistPage claimArtistPage, CreateArtistPage createArtistPage,AuthorizationPage authpage, CommonStudioPage commonStudioPage, SoftAssert anAssert,
                              ArtistHomePage studioPage, Utils utility, CommonPage  commonPage, MailinatorAPI mailinatorApi,
                              PodcastHomePage podcastHomePage , ProfessionPage professionPage )
    {
        this.authpage = authpage;
        this.anAssert =  anAssert;
        this.commonStudioPage = commonStudioPage;
        this.studioPage = studioPage;
        this.utility = utility;
        this.commonPage = commonPage;
        this.mailinatorApi = mailinatorApi;
        this.podcastHomePage =podcastHomePage;
        this.professionPage =professionPage;
        this.createArtistPage= createArtistPage;
        this.claimArtistPage =claimArtistPage;
    }

    @Given("click on i am a Music Artist")
    public void clickOnIAmAMusicArtist() {

        professionPage.clickIAmAMusicArtistButton();

    }


    @And("Enter name on Create or claim an artist profile page")
    public void enterNameOnCreateOrClaimAnArtistProfilePage() throws Exception {
        createArtistPage.typeArtistNameInTextBox("Rahul");
    }


    @Then("Assert List size of Artists in Drop down is greater then zero")
    public void assertListSizeOfArtistsInDropDownIsGreaterThenZero() {
        Assert.assertTrue("Nothing coming as the name of artist is searched ",
                (createArtistPage.getSizeOfListOfArtistInDropDown()>=1));
    }

    @And("Select a non-claimed Artist from drop down")
    public void selectANonClaimedArtistFromDropDown() {
        createArtistPage.clickFirstNonClaimedArtistFound();
    }

    @And("Assert Studio Home Banner")
    public void assertStudioHomeBanner() {
     Assert.assertTrue("Home banner is not present",professionPage.isWynkStudioBannerPresent());

    }

    @And("Assert profile Button")
    public void assertProfileButton() {

        Assert.assertTrue("Profile button is not present",professionPage.isProfilelButtonPresent());
    }

    @And("Assert Claim Artist, View Profile on wynk and create new profile button")
    public void assertClaimArtistViewProfileOnWynkAndCreateNewProfileButton() {

        Assert.assertTrue("Claim this profile button not present",claimArtistPage.isClaimArtistButtonPresent());
        Assert.assertTrue("Create new profile button not present",claimArtistPage.isViewProfileOnWynkPresent());
        Assert.assertTrue("View this profile on Wynk button not present",claimArtistPage.isCreateNewProfileButtonPresent());

    }

    @Then("Click on Create New Profile Button")
    public void clickOnCreateNewProfileButton() {

        createArtistPage.clickCreateNewProfile();
    }

    @And("Click on Back button on Add Artist Details page")
    public void clickOnBackButtonOnAddArtistDetailsPage() {

        createArtistPage.clickBackArrowButton();
    }


    @Then("Assert user is on Create Or claim artist profile page")
    public void assertUserIsOnCreateOrClaimArtistProfilePage() {
        Assert.assertTrue("Create or claim artist page not visible",createArtistPage.isHeadingClaimCreateArtistPresent());
    }

    @Then("Click on continue on Artist Details Page")
    public void clickOnContinueOnArtistDetailsPage()
    {
        createArtistPage.clickContinueButton();
    }

    @Then("Assert Language alert")
    public void assertLanguageAlert() {
      Assert.assertTrue("Lang alert is not coming", createArtistPage.isLangSelectedAlertPresent());
    }

    @And("Assert Artist Bio alert")
    public void assertArtistBioAlert()
    {
        Assert.assertTrue("Bio alert is not coming", createArtistPage.isNoBioMentionedAlertPresent());
    }


    @And("Assert Role Alert")
    public void assertRoleAlert() {
        Assert.assertTrue("Role alert is not coming", createArtistPage.isNoRoleAlertPresent());
    }

    @And("Assert IPRS Alert")
    public void assertIPRSAlert() {
        Assert.assertTrue("IPRS alert is not coming", createArtistPage.isIprsSelectedAlertPresent());
    }

    @And("Click on Upload Photo")
    public void clickOnUploadPhoto()
    {
        createArtistPage.clickPhotoUploadAddArtistDetailsPage();
    }

    @And("Click on + sign in Upload Photo Pop up")
    public void clickOnSignInUploadPhotoPopUp() {
     createArtistPage.clickAddPhotoPlusSignPopUp();
    }

    @And("Upload File")
    public void uploadFile() throws Exception {
        createArtistPage.uploadImage();
    }

    @And("Click on Done Button in Upload Photo Pop up")
    public void clickOnDoneButtonInUploadPhotoPopUp()
    {
        href1 = createArtistPage.getLinkOfImageUploadedOnPopUp();
        createArtistPage.clickDoneButtonOnPopUp();
        href2 = createArtistPage.getLinkOfImageUploadedOnAddArtistDetails();

    }

    @Then("Assert same image is uploaded on Add Artist Details Page")
    public void assertSameImageIsUploadedOnAddArtistDetailsPage()
    {
     Assert.assertTrue("image uploaed is not correct",   href1.equalsIgnoreCase(href2));
    }
}
