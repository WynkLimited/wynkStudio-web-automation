package in.wynk.pages;

import in.wynk.API.API;

import io.restassured.response.Response;
import org.openqa.selenium.By;
import in.wynk.common.DriverActionUtils;
import in.wynk.framework.Reporting;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.remote.RemoteExecuteMethod;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.html5.RemoteWebStorage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class LoginPage extends DriverActionUtils {

    API api;
    CommonPage commonPage;
    HomePage homePage;
    in.wynk.framework.Assert anAssert;

    public LoginPage(HomePage homePage , CommonPage commonPage, API api, Reporting Reporter, in.wynk.framework.Assert Assert, in.wynk.framework.SoftAssert SoftAssert) {

        super(Reporter, Assert, SoftAssert);
        this.homePage=homePage;
        this.api = api;
        this.commonPage = commonPage;
        this.anAssert = Assert;
    }

    private By mobileNumber = By.id("mobile-number");
    private By firstDigit = By.id("otp-first-letter");
    private By secondDigit = By.id("otp-second-letter");
    private By thirdDigit = By.id("otp-third-letter");
    private By fourthDigit = By.id("otp-fourth-letter");
    private By continueButton = By.id("mobile-number-verify-btn");
    private By verifyButton = By.id("otp-verify-btn");
    private By resendOtpButton = By.id("resend-otp");
    private By otpErrorMsg = By.xpath("//div[@id = 'otp-upper']//span");
    private By closeButton = By.id("close-login-popup");
    private By smallTitle1 = By.id("login-heading");
    private By smallTitle2 = By.id("login-sub-heading");
    private By termsOfUser = By.id("login-terms-of-uses");
    private By privacyPolicy = By.id("login-privacy-policy");
    private By navPages = By.xpath("//a[@class='nav-link']");
    private By closeLoginPopupBtn = By.id("close-login-popup");
    private By backbtn = By.xpath("//button[@class='close']");




    public void enterOtp(String otp) throws Exception {
        if (otp.length() == 4) {
            sleep(3000);
            sendKeys(firstDigit, String.valueOf(otp.charAt(0)));
            sendKeys(secondDigit, String.valueOf(otp.charAt(1)));
            sendKeys(thirdDigit, String.valueOf(otp.charAt(2)));
            sendKeys(fourthDigit, String.valueOf(otp.charAt(3)));
        } else {
            sleep(2000);
            sendKeys(firstDigit, String.valueOf(otp.charAt(0)));
            sendKeys(secondDigit, String.valueOf(otp.charAt(1)));
            sendKeys(thirdDigit, String.valueOf(otp.charAt(2)));
        }
    }

    public String getOTP() {
        return "" + getText(firstDigit) + getText(secondDigit) + getText(thirdDigit) + getText(fourthDigit);
    }

    public void clickVerifyButton() {
        click(verifyButton, "Verify");
    }

    public void clickContinueButton() {
        click(continueButton, "Continue");
    }

    public boolean isContinueButtonEnable() {
        return getDriver().findElement(continueButton).isEnabled();
    }

    public boolean isVerifyButtonEnable() {
        return getDriver().findElement(verifyButton).isEnabled();
    }

    public void clickOnResendButton() {
        click(resendOtpButton, "Resend OTP");
    }

    public String getOTPErrorMgs() {
        sleep(2000);
        return getText(otpErrorMsg);
    }

    public void loginWithoutGUI(String mobileNumber, String Otp) throws Exception {

        Response response = api.hitLoginAPI(mobileNumber, Otp);
        LocalStorage local;
        if(sTestDetails.get().get("grid").equalsIgnoreCase("true")) {
          RemoteExecuteMethod executeMethod = new RemoteExecuteMethod((RemoteWebDriver) getDriver());
          RemoteWebStorage webStorage = new RemoteWebStorage(executeMethod);
          local = webStorage.getLocalStorage();
        } else {
          local = ((WebStorage) getDriver()).getLocalStorage();
        }
        System.out.println(System.currentTimeMillis());
        local.setItem("authToken", response.jsonPath().get("authToken").toString());
        local.setItem("msisdnDetected", response.jsonPath().get("msisdnDetected").toString());
        local.setItem("token", response.jsonPath().get("token").toString());
        local.setItem("user_lang", response.jsonPath().get("userConfig.userInfo.lang").toString());
        local.setItem("uid", response.jsonPath().get("uid").toString());
        System.out.println(System.currentTimeMillis());
        commonPage.reload_page();
        if (!homePage.verifyLoginSuccessfully()) {
            Assert.fail("not able to login from API please check application local storage functionality  ");
        }
    }


    public void verifyLoginPopUPElements() {
          Assert.assertTrue(isElementDisplayed(closeButton,"Login PopUP close Button ",false));
          Assert.assertTrue(getText(smallTitle1).toLowerCase().trim().contains("Enter your Mobile number".toLowerCase()));
       //   Assert.assertTrue( getText(smallTitle2).toLowerCase().trim().contains("You can also use your Broadband/DTH registered numbers to login".toLowerCase()));
          click(termsOfUser,"terms of Uses" ,5);
          Assert.assertTrue(homePage.isTermsOfUsesSelected());
          homePage.selectOption(HomePage.HomePageOption.LOGIN);
          click(privacyPolicy,"privacy Policy" ,5);
          Assert.assertTrue(homePage.isPrivacyPolicySelected());

    }

    public void navigateToHomePage() {
        launchUrl(sTestDetails.get().get("APP_URL")  , true);
    }

    public void enterMobileNumber(String mobileNumber) throws Exception {
        type(this.mobileNumber, "Mobile Number", mobileNumber, true);
    }

    public void clickOnVerifyPage() {
        click(verifyButton, "verify");
    }

    public void verifyAllTopBarPages(String pages) {
        pages = (String)homePage .getGDValue(pages);
        homePage.validateAllHomePages(pages, false);
    }

    public void verifySuccessfullyLogin() {
        homePage.verifyLoginSuccessfully();
    }

    public void verifyAllTopPages(String pages) {
        List<String> listOfPages = new ArrayList<>();
        List<WebElement> webElements = getDriver().findElements(navPages);
        for(WebElement elements : webElements){
            listOfPages.add(elements.getText());
        }
        String[] pageList = pages.split("\\s*,\\s*");
        Assert.assertTrue(listOfPages.containsAll(Arrays.asList(pageList)));
    }

    public void closeLoginPopup() {
        click(closeLoginPopupBtn, "close login popup btn");
    }

    public void clickOnBackButton() {
        click(backbtn, "back to login popup");
    }
}
