@Watchlist
Feature: watchlist

  @sanity
  Scenario Outline: Check Watchlist rails for un-registered user
    When Navigate To Home Page
    Then Verify no Watchlist rails for un-registered user
    Examples:
      | mobileNumber                    | otp                    |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} |


  @smoke
  Scenario Outline: Add Content into watchlist and verify
    When Navigate To Home Page
    When Login WithOut GUI Number <mobilenumber> OTP <OTP>
    When Open playable content with contentType<contentType> ,contentName<contentName>,contentID<contentID>,flag<flag>
    And add content to watchlist
    Then Verify content <contentName> added into watchlist
    Then delete content from watch List

    Examples:
      | contentType | contentName | contentID                            | flag  | mobilenumber                    | OTP                    |
      | MOVIES      | style        | RAJTV_MOVIE_61af0b351507c370d5879c61 | false | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} |

  @smoke
  Scenario Outline: verify cross icon is visible when user hover mouse on wishlisted content
    When Navigate To Home Page
    When Login WithOut GUI Number <mobilenumber> OTP <OTP>
    When Open playable content with contentType<contentType> ,contentName<contentName>,contentID<contentID>,flag<flag>
    And add content to watchlist
    When Navigate To Home Page
    Then Verify cross icon is visible on hover on watchlist content
    Then delete content from watch List


    Examples:
      | contentType | contentName | contentID                            | flag  | mobilenumber                    | OTP                    |
      | MOVIES      | style        | RAJTV_MOVIE_61af0b351507c370d5879c61 | false | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} |

  @sanity
  Scenario Outline: Verify on clicking on wishlist option, user can see the contents already added in wishlist
    When Navigate To Home Page
    When Login WithOut GUI Number <mobilenumber> OTP <OTP>
    When Open playable content with contentType<contentType> ,contentName<contentName>,contentID<contentID>,flag<flag>
    And add content to watchlist
    Then Verify remove from watchlist icon visible on hover on watchlist content

    Examples:
      | contentType | contentName | contentID                            | flag  | mobilenumber                    | OTP                    |
      | MOVIES      | style       | RAJTV_MOVIE_61af0b351507c370d5879c61 | false | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} |


  @smoke
  Scenario Outline: verify watchlist icon is visible when user hover mouse on wishlisted content
    When Navigate To Home Page
    When Login WithOut GUI Number <mobilenumber> OTP <OTP>
    Then Verify watchlist icon is visible on hover on  content card

    Examples:
      | contentType | contentName | contentID                            | flag  | mobilenumber                    | OTP                    |
      | MOVIES      | style        | RAJTV_MOVIE_61af0b351507c370d5879c61 | false | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} |


  @sanity
  Scenario Outline: Watchlist option will be visible when user open any content in unregistered mode
   When Open playable content with contentType<contentType> ,contentName<contentName>,contentID<contentID>,flag<flag>
   Then Verify  Watchlist icon is present for un-registered user

   Examples:
     | contentType | contentName | contentID                            | flag  |
     | MOVIES      | style        | RAJTV_MOVIE_61af0b351507c370d5879c61 | false |
