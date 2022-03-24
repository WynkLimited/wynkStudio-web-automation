@artistCreation
Feature: Artist Creation

  @karishma1
    Scenario: Verify Create Or claim artist profile page
    Given User open Wynk studio Register page
    Then Enter EmailId on Register Page
    And  Enter Password
    And  Enter Full Name
    Then Click on Create Account
    And  Read verification Link using Mailinator API
    Then Hit verification link sent over mail
    Then Assert User should be on profession page
    Then click on i am a Music Artist
    Given Enter name on Create or claim an artist profile page
    Then  Assert List size of Artists in Drop down is greater then zero
    And  Assert Studio Home Banner
    And  Assert profile Button
    And  Select a non-claimed Artist from drop down
    And  Assert Claim Artist, View Profile on wynk and create new profile button


  @karishma1
    Scenario Outline: Verify click on back button in 'Create Or claim artist profile' page
        Given User open Wynk studio Login page
        Then  Enter Email <type_of_user>
        And   Enter Password
        Then  Click on Login
        Given Enter name on Create or claim an artist profile page
        Then Click on Create New Profile Button
        And  Click on Back button on Add Artist Details page
        Then Assert user is on Create Or claim artist profile page
    Examples:
      |type_of_user|
      |RUX         |

    @karishma1
    Scenario Outline: Verify if Artist wants to proceed without filling mandatory fields on Add Artist Details Page
      Given User open Wynk studio Login page
      Then  Enter Email <type_of_user>
      And   Enter Password
      Then  Click on Login
      Given Enter name on Create or claim an artist profile page
      Then Click on Create New Profile Button
      Then Click on continue on Artist Details Page
      Then Assert Language alert
      And  Assert Artist Bio alert
      And  Assert Role Alert
      And  Assert IPRS Alert
      Examples:
        |type_of_user|
        |RUX         |

    @karishma1
    Scenario Outline: Verify photo upload functionality by Artist
      Given User open Wynk studio Login page
      Then  Enter Email <type_of_user>
      And   Enter Password
      Then  Click on Login
      Given Enter name on Create or claim an artist profile page
      Then  Click on Create New Profile Button
      And   Click on Upload Photo
      And   Click on + sign in Upload Photo Pop up
      And   Upload First File
      And   Click on Done Button in Upload Photo Pop up
      Then  Assert same image is uploaded on Add Artist Details Page
      Examples:
        |type_of_user|
        |RUX         |


    @karishma1
    Scenario Outline: Verify if Artist wants to upload more photos
      Given User open Wynk studio Login page
      Then  Enter Email <type_of_user>
      And   Enter Password
      Then  Click on Login
      Given Enter name on Create or claim an artist profile page
      Then  Click on Create New Profile Button
      And   Click on Upload Photo
      And   Click on + sign in Upload Photo Pop up
      And   Upload First File
      Then  Assert First image is uploaded successfully
      And   Click on + sign in Upload Photo Pop up
      And   Upload Second File
      Then  Assert Second image is uploaded successfully
      Examples:
        |type_of_user|
        |RUX         |


      @karishma1
      Scenario Outline: Verify that user's state is persisted when user clicks on back arrow
      Given User open Wynk studio Login page
      Then  Enter Email <type_of_user>
      And   Enter Password
      Then  Click on Login
      Given Enter name on Create or claim an artist profile page
      Then  Click on Create New Profile Button
      #  And   Click on Upload Photo
      #  And   Click on + sign in Upload Photo Pop up
       # And   Upload First File
       # And   Click on Done Button in Upload Photo Pop up
        Then Select Language
        And Select Role
        And Enter Artist Bio
        And Select IPRS radio button
        Then click on continue button
        Then Assert User profile creation pop up
        Examples:
          |type_of_user|
          |RUX         |












