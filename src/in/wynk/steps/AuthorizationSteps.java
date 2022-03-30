package in.wynk.steps;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import in.wynk.common.Utils;
import in.wynk.framework.MailinatorAPI;
import in.wynk.framework.SoftAssert;
import in.wynk.pages.*;
import junit.framework.Assert;
import static java.lang.Thread.sleep;


public class AuthorizationSteps {

    AuthorizationPage authpage ;
    CommonStudioPage commonStudioPage;
    SoftAssert anAssert;
    ArtistHomePage studioPage;
    String randomEmail = null;
    Utils utility;
    CommonPage commonPage;
    MailinatorAPI mailinatorApi;
    PodcastHomePage  podcastHomePage;
    ProfessionPage professionPage;
    String verificationLink =null;
    static String emailId = null;


    public AuthorizationSteps(AuthorizationPage authpage, CommonStudioPage commonStudioPage, SoftAssert anAssert,
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
    }

    @Then("Click on Create Account")
    public void clickOnCreateAccount() throws Exception {
     authpage.clickCreateAccountRegisterPage();
    }

    @And("Enter Password")
    public void enterPassword() throws Exception {
        authpage.enterPasswordOnLoginPage();
        Assert.assertTrue("Password not entered", authpage.ifPasswordTextBoxEmpty());
    }

    @And("Enter Full Name")
    public void enterFullName() throws Exception {
        authpage.enterFullNameOnRegisterPage();
        Assert.assertTrue("Full Name not entered", authpage.ifFullNameTextBoxIsEmpty());

    }



    @Then("Assert User should be in Logged in State")
    public void assertUserShouldBeInLoggedInState() {
        Assert.assertTrue( "profile button is not present on Studio Home page",commonStudioPage.isProfileButtonPresent());
        Assert.assertTrue("New Release is not present on Studio Home page", studioPage.isNewReleaseButtonPresent());
        Assert.assertTrue ( "AllSongs..Rejected Tan is not present on Studio home page",
                commonStudioPage.isAllSong_RejectedTabPresent());
    }



    @Given("User open Wynk studio Login page")
    public void userOpenWynkStudioLoginPage() {
        commonPage.navigateToPage(CommonPage.navigationOption.LOGIN);
       Assert.assertTrue(authpage.isEmailTextBoxPresent());

    }

    @Then("Click on Login")
    public void clickOnLogin() throws Exception
    {
         authpage.clickLoginButtonOnLoginPage();
    }

    @Then("Assert User should not be in Logged in State")
    public void assertUserShouldNotBeInLoggedInState()
    {
        authpage.clickCrossButtonNotification();
        Assert.assertTrue(authpage.isPasswordTextBoxPresent());
        Assert.assertTrue(authpage.isLoginButtonPresent());
    }

    @Given("User open Wynk studio Register page")
    public void userOpenWynkStudioRegisterPage()
    {
        commonPage.navigateToPage(CommonPage.navigationOption.REGISTER);
        Assert.assertTrue(authpage.isPasswordTextBoxPresent());
    }



    @And("Enter Full Name on Register Page")
    public void enterFullNameOnRegisterPage() throws Exception {
        authpage.enterFullNameOnRegisterPage();
    }

    @Then("Assert send me link again button is present or not")
    public void assertSendMeLinkAgainButtonIsPresentOrNot() {

        Assert.assertTrue("Send link again button not found", authpage.isSendLinAgainButtonPresent());
    }


    @Then("Assert User should on Forget Password Page")
    public void assertUserShouldOnForgetPasswordPage() {

        Assert.assertTrue("Send me link again button", authpage.isSendLinAgainButtonPresent());
    }

    @Then("Enter Email (.+)")
    public void enterEmailType_of_user(String typeOfUser) throws Exception {

        if(typeOfUser.equalsIgnoreCase("NUX"))
        {
            authpage.enterEmailOnLoginPage(randomEmail);

        }
        else if (typeOfUser.equalsIgnoreCase("Invalid"))
        {
            authpage.enterEmailOnLoginPage("randon@random.com");

        }
        else if (typeOfUser.equalsIgnoreCase("RUX") )
        {
            authpage.enterEmailOnLoginPage(emailId);
        }
        else if(typeOfUser.equalsIgnoreCase("Podcast"))
        {
            authpage.enterEmailOnLoginPage("podcast10@yopmail.com");
        }
        else if (typeOfUser.equalsIgnoreCase("Artist"))
        {
            authpage.enterEmailOnLoginPage();
        }

        Assert.assertTrue("Email not entered", authpage.ifEmailTextBoxIsEmpty());
    }

    @Then("Enter EmailId on Register Page")
    public void enterEmailIdOnRegisterPage() throws Exception {

        randomEmail = mailinatorApi.generateRandomUserId();
        emailId = randomEmail+"@team807464.testinator.com";
        authpage.enterEmailOnRegisterPage(emailId);
    }

    @And("Click on Forgot Password")
    public void clickOnForgotPassword() {

        authpage.clickForgotPasswordLink();
    }


    @And("click on Send Link button")
    public void clickOnSendLinkButton()
    {

        authpage.clickSendLink();
    }


    @And("Enter email ID on Forgot Password Page (.+)")
    public void enterEmailIDOnForgotPasswordPageType_of_user(String typeOfUser) throws Exception {

        if(typeOfUser.equalsIgnoreCase("NUX"))
        {
            authpage.enterEmailOnForgotPasswordPage(randomEmail);

        }
        else if (typeOfUser.equalsIgnoreCase("Invalid"))
        {
            authpage.enterEmailOnForgotPasswordPage("randon@random.com");

        }
        else if (typeOfUser.equalsIgnoreCase("RUX"))
        {
            authpage.enterEmailOnForgotPasswordPage();
        }

    }

    @Then("Assert the notification (.+)")
    public void assertTheNotificationType_of_user(String typeOfUser) {

        if(typeOfUser.equalsIgnoreCase("NUX") || typeOfUser.equalsIgnoreCase("RUX") )
        {
            authpage.isSuccessNotificationPresent();

        }
        else if (typeOfUser.equalsIgnoreCase("Invalid"))
        {
            authpage.isFailureNotificationPresent();

        }
    }



    @Then("Assert that correct home page is shown to the user (.+)")
    public void assertThatCorrectHomePageIsShownToTheUserType_of_user(String typeOfUser) {

        if(typeOfUser.equalsIgnoreCase("Podcast"))
        Assert.assertTrue("AddPodcast button is not present", podcastHomePage.isAddPodcastButtonArtistHomePagePresent());

        else if (typeOfUser.equalsIgnoreCase("Artist"))
        Assert.assertTrue("Add New release button is not present",studioPage.isNewReleaseButtonPresent() );

    }

    @Then("Hit verification link sent over mail")
    public void hitVerificationLinkSentOverMail() {

        commonPage.navigateToPage(verificationLink);

    }


    @Then("Assert User should be on profession page")
    public void assertUserShouldBeOnProfessionPage() {

        Assert.assertTrue("I am a Music Artist Button not present", professionPage.isAmAMusicArtistButtonPresent());
        Assert.assertTrue("I am a Podcast Creator Button not present", professionPage.isAmAPodcastCreatorButtonPresent());
        Assert.assertTrue("I am a Agency and Label not present", professionPage.isAgencyAndLabelButtonPresent());
        Assert.assertTrue("Wynk Studio Banner present", professionPage.isWynkStudioBannerPresent());


    }

    @Then("Assert User should be verification-error expiry page")
    public void assertUserShouldBeVerificationErrorExpiryPage() {

        Assert.assertTrue("Text Msg of verification Link expiry not present", authpage.isVerificationLinkExpiryMsgPresent());
        Assert.assertTrue("Send link again button on verification page not present",authpage.isSendLinkAgainVerificationErrorPageButtonPresent());

    }

    @And("Read verification Link using Mailinator API")
    public void readVerificationLinkUsingMailinatorAPI() throws InterruptedException {

        sleep(10000);
        verificationLink =  mailinatorApi.readEmail(randomEmail);
    }

    @And("Logout from Profession Page")
    public void logoutFromProfessionPage() throws InterruptedException {
        authpage.clickCrossButtonNotification();
        professionPage.clickProfileButton();
        sleep(2000);
        professionPage.clickLogoutButton();
        sleep(3000);
    }



}
