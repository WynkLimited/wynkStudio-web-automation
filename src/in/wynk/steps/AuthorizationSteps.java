package in.wynk.steps;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import in.wynk.framework.SoftAssert;
import in.wynk.pages.AuthorizationPage;
import in.wynk.pages.CommonPage;


 public class AuthorizationSteps {

    AuthorizationPage auth ;
    CommonPage commonPage;
    SoftAssert anAssert;


    public AuthorizationSteps(AuthorizationPage auth, CommonPage commonPage, SoftAssert anAssert)
    {
        this.auth = auth;
        this.anAssert =  anAssert;
        this.commonPage = commonPage;
    }




    @Then("Click On Login Button")
    public void clickOnLoginButton() {


    }

    @Then("Click on Create Account")
    public void clickOnCreateAccount() {

    }

    @Then("Enter Email")
    public void enterEmail() {
    }

    @And("Enter Password")
    public void enterPassword() {
    }

    @And("Enter Full Name")
    public void enterFullName() {
    }

    @Then("Verify Account using API")
    public void verifyAccountUsingAPI() {
    }

    @Then("Refresh the Studio Wynk Page")
    public void refreshTheStudioWynkPage() {
    }

    @Then("Assert User should be in Logged in State")
    public void assertUserShouldBeInLoggedInState() {
    }

    @Given("User open Wynk studio home page")
    public void userOpenWynkStudioHomePage() {

       commonPage.navigateToPage(CommonPage.navigationOption.LOGIN);
    }
 }
