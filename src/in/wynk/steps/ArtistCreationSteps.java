package in.wynk.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import junit.framework.Assert;
import in.wynk.framework.MailinatorAPI;
import in.wynk.framework.SoftAssert;
import in.wynk.pages.*;
import in.wynk.common.Utils;
import org.aspectj.weaver.patterns.HasMemberTypePattern;

import java.util.HashMap;

import static java.lang.Thread.sleep;


public class ArtistCreationSteps {

    static HashMap<String, String> mapToValidate;
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
        this.mapToValidate = new HashMap<String, String>();
    }

    @Given("click on i am a Music Artist")
    public void clickOnIAmAMusicArtist() {

        professionPage.clickIAmAMusicArtistButton();

    }


    @And("Enter name on Create or claim an artist profile page")
    public void enterNameOnCreateOrClaimAnArtistProfilePage() throws Exception {

        mapToValidate.put("ArtistName","Rahul");
        createArtistPage.typeArtistNameInTextBox("Rahul");

    }


    @Then("Assert List size of Artists in Drop down is greater then zero")
    public void assertListSizeOfArtistsInDropDownIsGreaterThenZero() throws InterruptedException {
        Assert.assertTrue("Nothing coming as the name of artist is searched ",
                (createArtistPage.getSizeOfListOfArtistInDropDown()>=1));
    }

    @And("Select a non-claimed Artist from drop down")
    public void selectANonClaimedArtistFromDropDown() throws InterruptedException {
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



    @And("Click on Done Button in Upload Photo Pop up")
    public void clickOnDoneButtonInUploadPhotoPopUp() throws InterruptedException {
        href1 = createArtistPage.getLinkOfFirstImageUploadedOnPopUp();
        createArtistPage.clickDoneButtonOnPopUp();
        href2 = createArtistPage.getLinkOfImageUploadedOnAddArtistDetails();

    }

    @Then("Assert same image is uploaded on Add Artist Details Page")
    public void assertSameImageIsUploadedOnAddArtistDetailsPage()
    {
        Assert.assertTrue("image uploaed is not correct",   href1.equalsIgnoreCase(href2));
    }

    @Then("Assert First image is uploaded successfully")
    public void assertFirstImageIsUploadedSuccessfully() throws InterruptedException {
        Assert.assertNotNull(createArtistPage.getLinkOfFirstImageUploadedOnPopUp());
    }

    @Then("Assert Second image is uploaded successfully")
    public void assertSecondImageIsUploadedSuccessfully() throws InterruptedException {
        Assert.assertNotNull(createArtistPage.getLinkOfSecondImageUploadedOnPopUp());
    }

    @And("Upload First File")
    public void uploadFirstFile() throws Exception {
        createArtistPage.uploadFirstImage();
    }

    @And("Upload Second File")
    public void uploadSecondFile() throws Exception {
        createArtistPage.uploadSecondImage();
    }

    @Then("Select Language")
    public void selectLanguage() throws Exception {
        createArtistPage.clickDropDownAndSelectValue(CreateArtistPage.dropDownToSelect.LANGUAGES, "Hindi");
    }

    @And("Select Role")
    public void selectRole() throws Exception {
        createArtistPage.clickDropDownAndSelectValue(CreateArtistPage.dropDownToSelect.ROLE, "Singer");
        mapToValidate.put("role","Instrumentalist");
    }

    @And("Enter Artist Bio")
    public void enterArtistBio() throws Exception {

        createArtistPage.typeArtistBio();
    }

    @And("Select IPRS radio button")
    public void selectIPRSRadioButton() throws InterruptedException {
        createArtistPage.clickYesIprsRadioButton();
    }

    @Then("click on continue button")
    public void clickOnContinueButton() {

        createArtistPage.clickContinueButton();
    }

    @Then("Assert User profile creation pop up")
    public void assertUserProfileCreationPopUp() {
        Assert.assertNotNull(studioPage.getUrlProfilePicOnWelcomeBanner());
        Assert.assertTrue(studioPage.isGoToStudioHomeButtonOnWelcomePresent());
        studioPage.clickGoToStudioHomeButtonOnWelcomeBanner();
        studioPage.clickDashboardButtonArtistHomePage();
        Assert.assertTrue(mapToValidate.get("role").equalsIgnoreCase(studioPage.getRoleOfArtistDashBoard()));
        Assert.assertTrue(mapToValidate.get("ArtistName").equalsIgnoreCase(studioPage.getNameOfArtistDashBoard()));

    }




    @And("Click on cross sign on update photo tab")
    public void clickOnCrossSignOnUpdatePhotoTab() {

        createArtistPage.clickCrossButtonOnProfilePic();
    }

    @And("Upload First File with wrong extension")
    public void uploadFirstFileWithWrongExtension() throws Exception {
        createArtistPage.uploadInvalidImage();
    }

    @Then("Assert that error message for wrong image extension")
    public void assertThatErrorMessageForWrongImageExtension() {
        Assert.assertTrue("Photo is uploaded",createArtistPage.isAddPhotoPlusSignPopUp());
    }

    @Then("Verify the Artist name is same which user entered on Create or claim an artist profile page")
    public void verifyTheArtistNameIsSameWhichUserEnteredOnCreateOrClaimAnArtistProfilePage()
    {
       Assert.assertTrue(createArtistPage.readArtistNameFromArtistNameTextBox().equalsIgnoreCase("rahul"));
    }
}
