package in.wynk.PageElements;

import lombok.Getter;
import org.openqa.selenium.By;


@Getter
public class AuthPageElements {

    private By LoginButtonHomePage = By.xpath("//button[@class='style__SecondaryButton-sc-1o736mh-11 kVuEJD']");
    private By emailAddressTextBox = By.cssSelector(".style__Container-sc-1pzdtrh-0 > .ant-input");
    private By passwordTextBox = By.cssSelector(".ant-input-affix-wrapper > .ant-input");
    private By loginButton = By.xpath("//form[@id='loginForm']//span[contains(text(),'Login')]");
    private By fullNameTextBox = By.cssSelector("#registerForm_name .ant-input");
    private By createAccountButton = By.xpath("//span[contains(.,'Create Account')]");
    private By termsAndConditions = By.cssSelector("strong > strong");
    private By notificationCrossButton =By.cssSelector(".anticon-close > svg");
    private By fullNameRegisterPage = By.cssSelector("#registerForm_name .ant-input");
    private By emailRegisterPage = By.cssSelector("#registerForm_email .ant-input");
    private By sendMeVerificationLinkAgainButton = By.xpath("//button/span[contains(text(),'Send me link again')]");
    private By forgotPasswordLink = By.xpath("//a[contains(text(),'Forgot Password')]");
    private By emaiIdTextBoxForgotPasswordPage = By.xpath("//div[@id='forgotPasswordForm_email']//input");
    private By sendEmailButtonForgotPasswordPage= By.cssSelector(".ant-btn > span");
    private By notificationNoRegisteredEmailFound
            = By.xpath("//div[contains(text(),'No registered user found with given email')]");
    private By emailSentNotification =
            By.xpath("//div[contains(text(),'Email sent successfully')]");

    private By incorrectUsernamePasswordNotification = By.xpath("//div[contains(text(),'Incorrect username or password.')]");

    private By verificationErrorMsg = By.xpath("//h5[contains(text(),'Uh oh! Your verification link has been expired.')]");
    private By sendMeLinkAgainOnverificationErrorPage =
            By.xpath("//button/span[contains(text(),'Send me link again')]");
    private By errorMsg = By.xpath("//div[contains(@class,'ant-notification-notice-error')]");


}

