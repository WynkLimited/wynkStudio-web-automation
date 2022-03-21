package in.wynk.PageElements;

import lombok.Getter;
import org.openqa.selenium.By;


@Getter
public class AuthPageElements {

    private By LoginButtonHomePage = By.xpath("//button[@class='style__SecondaryButton-sc-1o736mh-11 kVuEJD']");
    private By emailAddressTextBox = By.cssSelector(".style__Container-sc-1pzdtrh-0 > .ant-input");
    private By passwordTextBox = By.cssSelector(".ant-input-affix-wrapper > .ant-input");
    private By loginButton = By.xpath("//span[@class='svgicon__IconWrapper-sc-gxfrqa-0 hzltSc icon ctaIcon']");
    private By fullNameTextBox = By.cssSelector("#registerForm_name .ant-input");
    private By createAccountButton = By.xpath("//span[contains(.,'Create Account')]");
    private By termsAndConditions = By.cssSelector("strong > strong");
    private By notificationCrossButton =By.cssSelector(".anticon-close > svg");
    private By fullNameRegisterPage = By.cssSelector("#registerForm_name .ant-input");
    private By emailRegisterPage = By.cssSelector("#registerForm_email .ant-input");
    private By sendMeVerificationLinkAgainButton = By.xpath("//button[@class='ant-btn style__StyledButton-sc-4pmgzs-0 cgIfa']");
    private By forgotPasswordLink = By.xpath("//a[@class='style__StyledLink-sc-1hahjst-11 biNXrM passwordText']");
    private By emaiIdTextBoxForgotPasswordPage = By.xpath("//div[@id='forgotPasswordForm_email']//input");
    private By sendEmailButtonForgotPasswordPage= By.cssSelector(".ant-btn > span");
    private By notificationNoRegisteredEmailFound
            = By.xpath("//div[@class='ant-notification ant-notification-topRight']//div[contains(text(),'No registered user found with given email')]");
    private By emailSentNotification =
            By.xpath("//div[@class='ant-notification ant-notification-topRight']//div[contains(text(),'Email sent successfully')]");

    private By verificationErrorMsg = By.xpath("//h5[@class='s3styled__S3-sc-85vhak-0 style__StyledS3-sc-1hahjst-20 kGsEfG cIUfYj'][1]");
    private By sendMeLinkAgainOnverificationErrorPage =
            By.xpath("//button[@class='ant-btn ant-btn-default ant-btn-round ant-btn-sm ant-btn-block']");


}

