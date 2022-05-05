@SongDashboard
Feature: Sing Dashboard


  @SongDashboard
    @Regression
    @fail1
  Scenario Outline: Verify important UI elements on Song Dashboard
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


  @SongDashboard
    @Regression
  Scenario Outline: Verify all use-cases of Filter and download on Dashboard
    Given User open Wynk studio Login page
    Then  Enter Email <type_of_user>
    And   Enter Password
    Then  Click on Login
    And   Click on dashboard button
    And   Read values of unique listens, total stream and follower from dashboard
    Then  click on Filter
    And   select Last 30 days Filter from drop down
    Then  Assert filter name is changed to Last 30 days
    And   Assert content is coming for all the songs in the table
    And   Assert unique Stream and Total Stream count is greater than previous filter
    And   Read values of unique listens, total stream and follower from dashboard
    Then  click on Filter
    And   select Last 90 days Filter from drop down
    Then  Assert filter name is changed to Last 90 days
    And   Assert content is coming for all the songs in the table
    And   Assert unique Stream and Total Stream count is greater than previous filter
    Then  click on download Button
    Then  Pick random dates in current month
    And   click on done button in Download section
    Then  click on download Button
    And   Select All the time
    Then  click on done button in Download section
    And  click on download Button
    Then Click on cross button present in download section
    Examples:
      |type_of_user|
      |Artist       |


  @SongDashboard
    @Regression
  Scenario Outline: Verify all use-cases Analytics on Release summary page
    Given User open Wynk studio Login page
    Then  Enter Email <type_of_user>
    And   Enter Password
    Then  Click on Login
    And   Click on dashboard button
    Then  Click first live song is clickable
    Then  Assert the name of song click on Release Summary page
    Then  Check by default last 7 days Filter should be selected
    And   Click on Unique Listenes
    Then  Assert that a Trends pop up is coming up with correct count "uniqueListens"
    Then  click on cross button on Trends pop up
    And   Click on Total Streams
    Then  Assert that a Trends pop up is coming up with correct count "totalStreams"
    Then  click on cross button on Trends pop up
    Then  click on Filter
    And   select Last 30 days Filter from drop down
    Then  Click first row in Release summary page
    And   Assert Total Streams and Unique Listenes Graphs are displaying
    Then  click on play button on Release Summary page
    And   Assert that song name is same on Wynk and Wynk studio
    Examples:
      |type_of_user|
      |Artist       |

  @SongDashboard
    @Regression
  Scenario Outline: Verify all use-cases Search Bar on Dashboard
    Given User open Wynk studio Login page
    Then  Enter Email <type_of_user>
    And   Enter Password
    Then  Click on Login
    And   Click on dashboard button
    Then  Click first live song is clickable
    Then  Search song for that artist ""
    Then  Assert that Live song is appearing in the results of search
    And   Click on the song in result
    Then  Assert Release summary Page of Live song is opened
    Then  Click on cross icon
    And   Search song for that artist "sdsd"
    Then  Assert that No Search Results should appear
    Examples:
      |type_of_user|
      |Artist       |

