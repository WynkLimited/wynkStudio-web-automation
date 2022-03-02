@ContinueWatching
Feature: Continue Watching


  @sanity
  Scenario Outline: Add Movie Into Continue Watching
    When Open playable content with contentType<contentType> ,contentName<contentName>,contentID<contentID>,flag<flag>
    Then click on play pause button
    When Login with number<mobileNumber> and OTP<OTP>
    And set seekBar Thumb in start
    And click on play pause player controller button after <timeInSec>
    When Navigate To Home Page
    Then Verify that the content <contentName> is added to continue watching list
    And delete a content from continue watching rail



    Examples:
      | contentType | contentName | contentID                            | flag  | mobileNumber                    | OTP                    | timeInSec |
      | MOVIES      | roja        | RAJTV_MOVIE_61a49bcc1507c370d5879c22 | false | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | 34000     |

  @smoke
  Scenario Outline: Verify deleted content from continue watching rail is not appearing
    When Open playable content with contentType<contentType> ,contentName<contentName>,contentID<contentID>,flag<flag>
    Then click on play pause button
    When Login with number<mobileNumber> and OTP<OTP>
    And set seekBar Thumb in start
    And click on play pause player controller button after <timeInSec>
    When Navigate To Home Page
    Then Verify that the content <contentName> is added to continue watching list
    And delete a content from continue watching rail
    Then Verify that the content <contentName> is removed from continue watching list

    Examples:
      | contentType | contentName | contentID                            | flag  | mobileNumber                    | OTP                    | timeInSec |
      | MOVIES      | roja        | RAJTV_MOVIE_61a49bcc1507c370d5879c22 | false | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | 35000     |


  @sanity
  Scenario Outline: Verify continue watching list are same on homepage and watchlist page
    When Open playable content with contentType<contentType> ,contentName<contentName>,contentID<contentID>,flag<flag>
    Then click on play pause button
    When Login with number<mobileNumber> and OTP<OTP>
    And set seekBar Thumb in start
    And click on play pause player controller button after <timeInSec>
    When Navigate To Home Page
    Then Verify continue watching list are same on homepage and watchlist page
    And delete a content from continue watching rail


    Examples:
      | contentType | contentName | contentID                            | flag  | mobileNumber                    | OTP                    | timeInSec |
      | MOVIES      | roja        | RAJTV_MOVIE_61a49bcc1507c370d5879c22 | false | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | 40000     |


  @sanity
  Scenario Outline: Play content for less than30sec and content not presesnt in CW rail
    When Navigate To Home Page
    When Open playable content with contentType<contentType> ,contentName<contentName>,contentID<contentID>,flag<flag>
    Then click on play pause button
    When Login with number<mobileNumber> and OTP<OTP>
    And set seekBar Thumb in start
    And click on play pause player controller button after <timeInSec>
    When Navigate To Home Page
    Then Verify that the content <contentName> is not added to continue watching list


    Examples:
      | contentType | contentName | contentID                            | flag  | mobileNumber                    | OTP                    | timeInSec |
      | MOVIES      | roja        | RAJTV_MOVIE_61a49bcc1507c370d5879c22 | false | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | 2000      |

  @sanity
  Scenario:verify there is no continue watching rail on Un-registered mode
    When Navigate To Home Page
    Then verify there is no continue watching rail on Unregistered mode


