@authorization
Feature: Auth


  @Smoke
    @Auth
    Scenario: Verify new user signup
    Given User open Wynk studio Register page
    Then Enter EmailId on Register Page
    And  Enter Password
    And  Enter Full Name
    Then Click on Create Account
    And Read verification Link using Mailinator API
    Then Hit verification link sent over mail
    Then Assert User should be on profession page

  @Sanity
    @Auth
  Scenario: Verify new user signup with used verification code
    Given User open Wynk studio Register page
    Then Enter EmailId on Register Page
    And  Enter Password
    And  Enter Full Name
    Then Click on Create Account
    And  Read verification Link using Mailinator API
    Then Hit verification link sent over mail
    And  Logout from Profession Page
    Then Hit verification link sent over mail
    Then Assert User should be verification-error expiry page


  @Smoke
    @Auth
  Scenario Outline: Login with a RUX user
    Given User open Wynk studio Login page
    Then Enter Email <type_of_user>
    And  Enter Password
    Then Click on Login
    Then Assert User should be in Logged in State
    Examples:
      |type_of_user|
      |RUX         |

  @Regression
    @Auth
  Scenario Outline: Login with a invalid RUX user
    Given User open Wynk studio Login page
    Then Enter Email <type_of_user>
    And  Enter Password
    Then Click on Login
    Then Assert User should not be in Logged in State
    Examples:
      |type_of_user|
      |Invalid         |


  @Regression
    @Auth
    Scenario Outline: Verify that user whose email verification is pending is not able to signin
    Given User open Wynk studio Register page
    Then Enter EmailId on Register Page
    And Enter Password
    And Enter Full Name on Register Page
    Then Click on Create Account
    Then Assert send me link again button is present or not
    Given User open Wynk studio Login page
    Then Enter Email <type_of_user>
    And  Enter Password
    Then Click on Login
    Then Assert User should on Forget Password Page
    Examples:
    |type_of_user|
    |NUX         |


  @Sanity
    @Auth
  Scenario Outline: Verify that Forgot Password with RUX and invalid users
    Given User open Wynk studio Login page
    And Click on Forgot Password
    And Enter email ID on Forgot Password Page <type_of_user>
    And click on Send Link button
    Then Assert the notification <type_of_user>
    Examples:
      |type_of_user|
      |RUX         |
      |INVALID|


    @Smoke
    @Auth
  Scenario Outline: Verify that Correct Home Page opens for Artist and Podcaster
    Given User open Wynk studio Login page
    And Enter Email <type_of_user>
    And  Enter Password
    And Click on Login
    Then Assert that correct home page is shown to the user <type_of_user>
    Examples:
      |type_of_user|
      |Artist      |
      |Podcast|




















