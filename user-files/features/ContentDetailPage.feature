@ContentDetail
Feature: Content detail page


  @sanity
  Scenario Outline: Verify the content details from API AND UI
    When Open playable content with contentType<contentType> ,contentName<contentName>,contentID<contentID>,flag<flag>
    Then Verify the content details from API AND UI for content <contentID> , mobileNumber <mobileNumber>

    Examples:
      | contentType | contentName | contentID                            | flag  | mobileNumber                    | OTP                    | timeInSec |
      | MOVIES      | roja        | RAJTV_MOVIE_61a49bcc1507c370d5879c22 | false | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | 30000     |

  @sanity
  Scenario Outline: Verify non-registered user can naviagte to content detail page when click on any content
    When Open playable content with contentType<contentType> ,contentName<contentName>,contentID<contentID>,flag<flag>
    Then Verify the content details page

    Examples:
      | contentType | contentName | contentID                            | flag  | mobileNumber                    | OTP                    | timeInSec |
      | MOVIES      | roja        | RAJTV_MOVIE_61a49bcc1507c370d5879c22 | false | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | 30000     |

  @sanity
  Scenario Outline: Verify registered user can naviagte to content detail page when click on any content
    When Navigate To Home Page
    When Login WithOut GUI Number <mobileNumber> OTP <otp>
    When Open playable content with contentType<contentType> ,contentName<contentName>,contentID<contentID>,flag<flag>
    Then Verify the content details page

    Examples:
      | contentType | contentName | contentID                            | flag  | mobileNumber                    | otp                    | timeInSec |
      | MOVIES      | roja        | RAJTV_MOVIE_61a49bcc1507c370d5879c22 | false | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | 30000     |

  @regression
  Scenario Outline: Verify that the user can see tagged genere to the content
    When Open playable content with contentType<contentType> ,contentName<contentName>,contentID<contentID>,flag<flag>
    Then Verify that the user can see tagged genere to the content for <mobileNumber> and <contentID>
    Examples:
      | contentType | contentName | contentID                            | flag  | mobileNumber                    | otp                    | waitingTime | apiCounter | hitTimeGap |
      | MOVIES      | roja        | RAJTV_MOVIE_61a49bcc1507c370d5879c22 | false | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | 10000       | 10         | 300        |

  @regression
  Scenario Outline: Verify that the user can see tagged Language to the content
    When Open playable content with contentType<contentType> ,contentName<contentName>,contentID<contentID>,flag<flag>
    Then Verify that the user can see tagged Language to the content for <mobileNumber> and <contentID>
    Examples:
      | contentType | contentName | contentID                            | flag  | mobilenumber                    | OTP                    | waitingTime | apiCounter | hitTimeGap |
      | MOVIES      | roja        | RAJTV_MOVIE_61a49bcc1507c370d5879c22 | false | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | 10000       | 10         | 300        |

  @regression
  Scenario Outline: Verify that the user can see credits of the content
    When Open playable content with contentType<contentType> ,contentName<contentName>,contentID<contentID>,flag<flag>
    Then Verify that the user can see credits of the content for <mobileNumber> and <contentID>
    Examples:
      | contentType | contentName | contentID                            | flag  | mobilenumber                    | OTP                    | waitingTime | apiCounter | hitTimeGap |
      | MOVIES      | roja        | RAJTV_MOVIE_61a49bcc1507c370d5879c22 | false | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | 10000       | 10         | 300        |


  @regression
  Scenario Outline: Verify that description on content detail page
    When Open playable content with contentType<contentType> ,contentName<contentName>,contentID<contentID>,flag<flag>
    Then Verify that description on content detail page  for <mobileNumber> and <contentID>
    Examples:
      | contentType | contentName | contentID                            | flag  | mobilenumber                    | OTP                    | waitingTime | apiCounter | hitTimeGap |
      | MOVIES      | roja        | RAJTV_MOVIE_61a49bcc1507c370d5879c22 | false | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | 10000       | 10         | 300        |

  @regression
  Scenario Outline: Verify share icon on the content detail page for Registered user
    When Navigate To Home Page
    When Login WithOut GUI Number <mobilenumber> OTP <OTP>
    When Open playable content with contentType<contentType> ,contentName<contentName>,contentID<contentID>,flag<flag>
    Then Verify share icon on every content detail page
    Examples:
      | contentType | contentName | contentID                            | flag  | mobilenumber                    | OTP                    | waitingTime | apiCounter | hitTimeGap |
      | MOVIES      | roja        | RAJTV_MOVIE_61a49bcc1507c370d5879c22 | false | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | 10000       | 10         | 300        |

  @regression
  Scenario Outline: Verify watchlist icon on the player
    When Navigate To Home Page
    When Login WithOut GUI Number <mobilenumber> OTP <OTP>
    When Open playable content with contentType<contentType> ,contentName<contentName>,contentID<contentID>,flag<flag>
    Then Verify  Watchlist icon is present for registered user
    Examples:
      | contentType | contentName | contentID                            | flag  | mobilenumber                    | OTP                    | waitingTime | apiCounter | hitTimeGap |
      | MOVIES      | roja        | RAJTV_MOVIE_61a49bcc1507c370d5879c22 | false | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | 10000       | 10         | 300        |

 @regression
  Scenario Outline: Verify user can navigate to content detail page for locked content
    When Navigate To Home Page
    When Login WithOut GUI Number <mobilenumber> OTP <OTP>
    When Open playable content with contentType<contentType> ,contentName<contentName>,contentID<contentID>,flag<flag>
    Then Verify the content details page
    Examples:
      | contentType | contentName | contentID                            | flag  | mobilenumber         | OTP                    | waitingTime | apiCounter | hitTimeGap |
      | MOVIES      | roja        | RAJTV_MOVIE_61a49bcc1507c370d5879c22 | false | %{UNSUBSCRIBED_USER} | %{XSTREAM_PREMIUM_OTP} | 10000       | 10         | 300        |
