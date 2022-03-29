package in.wynk.pages;
import in.wynk.PageElements.AuthPageElements;
import in.wynk.common.DriverActionUtils;
import in.wynk.framework.Reporting;
import org.openqa.selenium.By;
import in.wynk.common.Utils;



public class AuthorizationPage extends DriverActionUtils{

    CommonPage commonPage;
    in.wynk.framework.Assert anAssert;
    AuthPageElements elements;
    //String password = "Abcd@12345";
    String password = "Abcd@12345", prodPassword = "abcdef";
    Utils utility;
    String emailArtist = "karishmakoul2@gmail.com", prodEmail = "chassi.koul@gmail.com";



    public AuthorizationPage(CommonPage commonPage, Reporting Reporter, in.wynk.framework.Assert Assert,
                             in.wynk.framework.SoftAssert SoftAssert, AuthPageElements elements, Utils utility) {

        super(Reporter, Assert, SoftAssert);
        this.commonPage = commonPage;
        this.anAssert = Assert;
        this.elements = elements;
        this.utility = utility;

    }

    public boolean isEmailTextBoxPresent()
    {
       return isElementDisplayed( elements.getEmailAddressTextBox(), "EmailTextBox",true);
    }

    public boolean isPasswordTextBoxPresent()
    {
        return isElementDisplayed( elements.getPasswordTextBox(), "PasswordTextBox",true);
    }

    public boolean isLoginButtonPresent()
    {
        return isElementDisplayed( elements.getLoginButton(), "Loginbutton",true);
    }

    public boolean isHomeLoginButtonPresent()
    {
        return isElementDisplayed((By) elements.getLoginButtonHomePage(), "Home Login button",true);
    }

    public void enterEmailOnLoginPage(String email) throws Exception {

        click(elements.getEmailAddressTextBox(), "EmailTextBox" );
        type( elements.getEmailAddressTextBox(), "EmailTextBox", email, 5);
    }

    public void enterEmailOnLoginPage() throws Exception {

        click(elements.getEmailAddressTextBox(), "EmailTextBox" );
        type( elements.getEmailAddressTextBox(), "EmailTextBox", prodEmail, 5);
    }
    public void enterEmailOnRegisterPage(String randomEmail) throws Exception
    {

        click(elements.getEmailRegisterPage(), "EmailTextBoxRegisterPage" );
        type( elements.getEmailRegisterPage(), "EmailTextBoxRegisterPage", randomEmail , 5);
    }

    public void enterFullNameOnRegisterPage() throws Exception {

        click(elements.getEmailAddressTextBox(), "Full Name" );
        type( elements.getEmailAddressTextBox(), "Full Name", "karishmaAutomation", 5);
    }

    public boolean ifEmailTextBoxIsEmpty()
    {
        if( !getElementWhenVisible(elements.getEmailAddressTextBox()).getAttribute("value").isEmpty() )
         {
             return true;
         }
        return false;
    }

    public boolean ifFullNameTextBoxIsEmpty()
    {
        if( !getElementWhenVisible(elements.getFullNameTextBox()).getAttribute("value").isEmpty() )
        {
            return true;
        }
        return false;
    }
    public boolean ifPasswordTextBoxEmpty()
    {
        if(!getElementWhenVisible(elements.getPasswordTextBox()).getAttribute("value").isEmpty())
        {
            return true;
        }
        return false;
    }

    public void enterPasswordOnLoginPage() throws Exception {
        click(elements.getPasswordTextBox(), "PasswordTextBox" );
        type((By) elements.getPasswordTextBox(), "PasswordTextBox", prodPassword, 5);

    }

    public void clickLoginButtonOnLoginPage() throws Exception {
        click(elements.getLoginButton(), "Login button" );
    }

    public void clickCreateAccountRegisterPage() throws Exception {
        click(elements.getCreateAccountButton(), "Create Account" );
    }

    public void clickCrossButtonNotification()
    {
        click(elements.getNotificationCrossButton(), "NotificationCrossButton" );
    }

    public void clickForgotPasswordLink()
    {
        click(elements.getForgotPasswordLink(),"Forgot password link");
    }

    public void enterEmailOnForgotPasswordPage(String email) throws Exception
    {
        click(elements.getEmaiIdTextBoxForgotPasswordPage(), "Email Text Box on Forgot Password Page" );
        type( elements.getEmaiIdTextBoxForgotPasswordPage(), "Email Text Box on Forgot Password Page", email, 5);
    }

    public void enterEmailOnForgotPasswordPage() throws Exception
    {
        click(elements.getEmaiIdTextBoxForgotPasswordPage(), "Email Text Box on Forgot Password Page" );
        type( elements.getEmaiIdTextBoxForgotPasswordPage(), "Email Text Box on Forgot Password Page", emailArtist, 5);
    }


    public void clickSendLink()
    {
        click(elements.getSendEmailButtonForgotPasswordPage(),"Send link");
    }

   public boolean isFailureNotificationPresent()
   {
       return isElementDisplayed(elements.getNotificationNoRegisteredEmailFound(), "No Registerd User Found notification",true);
   }

    public boolean isSuccessNotificationPresent()
    {
        return isElementDisplayed(elements.getEmailSentNotification(), "No Registerd User Found notification",true);
    }

    public boolean isSendLinAgainButtonPresent()
    {
        return isElementDisplayed( elements.getSendMeVerificationLinkAgainButton(), "Send link again button",true);
    }

    public boolean isSendLinkAgainVerificationErrorPageButtonPresent()
    {
        return isElementDisplayed( elements.getSendMeLinkAgainOnverificationErrorPage(), "Send link again button",true);
    }

    public void clickSendLinkAgainVerificationErrorPageButton()
    {
        click(elements.getSendMeLinkAgainOnverificationErrorPage(), "Send link again button");
    }

    public boolean isVerificationLinkExpiryMsgPresent()
    {
        return isElementDisplayed( elements.getVerificationErrorMsg(), "Text Msg of verification Link expiry",true);
    }




}
