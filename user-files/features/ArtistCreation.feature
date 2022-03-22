@artistCreation
Feature: Artist Creation

  Background:
    Given User open Wynk studio Register page
    Then Enter EmailId on Register Page
    And  Enter Password
    And  Enter Full Name
    Then Click on Create Account
    And Read verification Link using Mailinator API
    Then Hit verification link sent over mail
    Then Assert User should be on profession page
    Then click on i am a Music Artist
    Given Enter name on Create or claim an artist profile page

 @karishma1
    Scenario: Verify Create Or claim artist profile page
      Then Assert List size of Artists in Drop down is greater then zero
      And Assert Studio Home Banner
      And Assert profile Button
      And Select a non-claimed Artist from drop down
      And Assert Claim Artist, View Profile on wynk and create new profile button

  @karishma1
    Scenario: Verify click on back button in 'Create Or claim artist profile' page
        Then Click on Create New Profile Button
        And  Click on Back button on Add Artist Details page
        Then Assert user is on Create Or claim artist profile page

    @karishma1
    Scenario: Verify if Artist wants to proceed without filling mandatory fields on Add Artist Details Page
      Then Click on Create New Profile Button
      Then Click on continue on Artist Details Page
      Then Assert Language alert
      And  Assert Artist Bio alert
      And  Assert Role Alert
      And  Assert IPRS Alert

    @karishma
    Scenario: Verify photo upload functionality by Artist
      Then Click on Create New Profile Button
      And Click on Upload Photo
      And Click on + sign in Upload Photo Pop up
      And Upload File
      And Click on Done Button in Upload Photo Pop up
      Then Assert same image is uploaded on Add Artist Details Page











