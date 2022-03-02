package in.wynk.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import in.wynk.framework.Assert;
import in.wynk.pages.CommonPage;
import in.wynk.pages.HomePage;
import in.wynk.pages.LoginPage;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LoginStep {

    LoginPage loginPage;
    Assert Assert;
    CommonPage commonPage;
    HomePage homePage;

    public LoginStep(HomePage homePage, CommonPage commonPage, LoginPage loginPage, Assert Assert) {
        this.homePage = homePage;
        this.loginPage = loginPage;
        this.Assert = Assert;
        this.commonPage = commonPage;

    }

    @When("^User enters mobile number (.+) for login$")
    public void user_enters_mobile_number_for_login(String mobileNumber) throws Exception {
        mobileNumber = (String) loginPage.getGDValue(mobileNumber);
        loginPage.enterMobileNumber(mobileNumber);
        loginPage.clickContinueButton();
    }

    @Then("^Login with number(.+) and OTP(.+)$")
    public void login(String mobileNumber, String Otp) throws Exception {
        user_enters_mobile_number_for_login(mobileNumber);
        user_enters_otp_for_login(Otp);
    }

    @Then("^User only enters mobile number (.+) for login$")
    public void userEnterOnlyMobileNumber(String mobileNumber) throws Exception {
        mobileNumber = (String) loginPage.getGDValue(mobileNumber);
        loginPage.enterMobileNumber(mobileNumber);
    }

    @Then("^Click on login Continue button")
    public void clickOnLoginContinueBtn() {
        loginPage.clickContinueButton();
    }

    @When("^User enters otp (.+) for login$")
    public void user_enters_otp_for_login(String otp) throws Exception {
        otp = (String) loginPage.getGDValue(otp);
        loginPage.enterOtp(otp);
        loginPage.clickVerifyButton();
    }

    @When("^User only enters otp (.+) for login$")
    public void user_Only_enters_otp_for_login(String otp) throws Exception {
        otp = (String) loginPage.getGDValue(otp);
        loginPage.enterOtp(otp);
    }

    @Then("^Verify continue button is enable or not when user enter 9 digit number - flag (.+)$")
    public void verifyContinueButtonIsEnable(String flag) {
        Assert.assertEquals(Boolean.toString(loginPage.isContinueButtonEnable()), (String) loginPage.getGDValue(flag));
    }

    @Then("^Verify verify button is enable or not when user enter less than 4 digit number - flag (.+)$")
    public void verifyVerifyButtonIsEnable(String flag) {
        Assert.assertEquals(Boolean.toString(loginPage.isVerifyButtonEnable()), (String) loginPage.getGDValue(flag));
    }

    @When("^click on Resent OTP button$")
    public void clickOnResendOTPButton() {
        loginPage.clickOnResendButton();
    }

    @Then("^verify OTP Text box On Resend click (.+)$")
    public void verifyOPTTextBoxOnResendClick(String otpsize) {
        Assert.assertEquals(Integer.toString(loginPage.getOTP().toString().length()), otpsize);
    }

    @And("^verify More Page is visible$")
    public void verifyMorePageIsVisible() {
    }

    @Then("^verify OTP Error message visible (.+)$")
    public void verifyOTPErrorMsg(String errorMsg) throws InterruptedException {
        Assert.assertEquals(loginPage.getOTPErrorMgs().toString(), errorMsg);
    }

    @When("Login WithOut GUI Number (.+) OTP (.+)")
    public void loginWithoutGUI(String number, String OTP) throws Exception {
        loginPage.loginWithoutGUI((String) loginPage.getGDValue(number), (String) loginPage.getGDValue(OTP));
    }

    @When("Verify Login PopUP elements is visible or not")
    public void verifyLoginPopUPElements() throws Exception {
        loginPage.verifyLoginPopUPElements();
    }

    @When("^Navigate To Home Page$")
    public void NavigateToHomePage() {
        loginPage.navigateToHomePage();
    }

    @Then("^User clicks on verify button$")
    public void clickOnVerifyButton() {
        loginPage.clickOnVerifyPage();
    }

    @Then("verify all top bar pages (.+)")
    public void verifyAllTopBarPagesPages(String pages) {
        loginPage.verifyAllTopBarPages(pages);
    }

    @Then("verify successfully login")
    public void verifySuccessfullyLogin() {
        loginPage.verifySuccessfullyLogin();
    }

    @Then("verify All Top Pages (.+)")
    public void verify_All_Top_Page(String pages) {
        loginPage.verifyAllTopPages(pages);
    }

    @Then("close the login popup")
    public void close_the_login_popup() {
        loginPage.closeLoginPopup();
    }

    @Then("verify login popup")
    public void verify_login_popup() {
        loginPage.verifyLoginPopUPElements();
    }


    @When("user click on profile icon")
    public void user_click_on_profile_icon() {
        homePage.clickonProfileButton();
    }


    @Then("Verify on menu-navigation page having options (.+)")
    public void verify_on_menu_navigation_page_having_options(String items) {
        List<String> menuItems = homePage.getMenuOption();
        Assert.assertTrue(menuItems.containsAll(Arrays.asList(items.toLowerCase().split("\\s*,\\s*")).stream().collect(Collectors.toSet())), "all menu items not present");

    }

    @Then("click on back button")
    public void click_on_back_button() {
        loginPage.clickOnBackButton();
    }

    @Then("verify user is on login popup")
    public void verify_user_is_on_login_popup() {
        verify_login_popup();
    }

    @Then("click on Logout button")
    public void click_on_Logout_button() {
        homePage.clickonProfileButton();
        homePage.clickOnLogOutButton();
        //Assert.assertTrue(homePage.isLoginButtonPresent());
    }
}
