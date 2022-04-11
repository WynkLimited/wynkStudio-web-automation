@SongDashboard
Feature: Sing Dashboard


  @SongDashboard
    @Regression
  Scenario Outline: Verify important UI elements on Song Dashboard when artist has more than 1 song to him
    Given User open Wynk studio Login page
    Then  Enter Email <type_of_user>
    And   Enter Password
    Then  Click on Login
    And   Assert that in All song tab contains all types of songs
    And   Click on dashboard button
    Then  Assert details of song are appearing on UI
    Then  Assert important UI elements on Dashboard
    Then  Click first live song is clickable
    And   Assert the name of song click on Release Summary page
    Examples:
    |type_of_user|
    |Artist       |


  @SongDashboard
    @Regression
  Scenario Outline: Verify important UI elements on Song Dashboard when artist has more than 1 song to him
    Given User open Wynk studio Login page
    Then  Enter Email <type_of_user>
    And   Enter Password
    Then  Click on Login
    And   Click on Live Tab if it has more than zero songs
    Then  click on song Title
    Then  Assert Release summary Page of Live song is opened
    And   Click on back Arrow
    And   Click on Draft Tab if it has more than zero songs
    Then  click on song Title
    Then  Assert Release summary Page of Draft song is opened
    And   Click on back Arrow
    And   Click on In-Review Tab if it has more than zero songs
    Then  click on song Title
    Then  Assert Release summary Page of in Review song is opened
    And   Click on back Arrow
    And   Click on Rejected Tab if it has more than zero songs
    Then  click on song Title
    Then  Assert Release summary Page of Rejected song is opened
    Examples:
      |type_of_user|
      |Artist       |


