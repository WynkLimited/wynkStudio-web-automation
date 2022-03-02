@Share
Feature: Share

  @sanity
  Scenario Outline: Verify share icon on every content detail page with registered user
    When Navigate To Home Page
    When Login WithOut GUI Number <mobileNumber> OTP <otp>
    When Open playable content with contentType<contentType> ,contentName<contentName>,contentID<contentID>,flag<flag>
    Then Verify share icon on every content detail page
    Examples:
      | contentType | contentName | contentID                            | flag  | mobileNumber                    | otp                    | waitingTime | apiCounter | hitTimeGap |
      | MOVIES      | roja        | RAJTV_MOVIE_61a49bcc1507c370d5879c22 | false | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | 10000       | 10         | 300        |

  @smoke
  Scenario Outline: Verify share popup on the click of share icon
    When Open playable content with contentType<contentType> ,contentName<contentName>,contentID<contentID>,flag<flag>
    And click on share icon
    Then Verify share popup contains <shareOptions>
    Examples:
      | contentType | contentName | contentID                            | flag  | shareOptions                           |
      | MOVIES      | roja        | RAJTV_MOVIE_61a49bcc1507c370d5879c22 | false | Email , Twitter , facebook , Copy link |

  @sanity
  Scenario Outline: Verify share icon on every content detail page with unregistered user
    When Open playable content with contentType<contentType> ,contentName<contentName>,contentID<contentID>,flag<flag>
    Then Verify share icon on every content detail page
    Examples:
      | contentType | contentName | contentID                            | flag  | mobilenumber                    | OTP                    | waitingTime | apiCounter | hitTimeGap |
      | MOVIES      | roja        | RAJTV_MOVIE_61a49bcc1507c370d5879c22 | false | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | 10000       | 10         | 300        |

  @regression
  Scenario Outline: Verify share icon on the content detail page with registered user
    When Navigate To Home Page
    When Login WithOut GUI Number <mobilenumber> OTP <OTP>
    When Open playable content with contentType<contentType> ,contentName<contentName>,contentID<contentID>,flag<flag>
    Then Verify share icon on every content detail page
    Examples:
      | contentType | contentName | contentID                            | flag  | mobilenumber                    | OTP                    | waitingTime | apiCounter | hitTimeGap |
      | MOVIES      | roja        | RAJTV_MOVIE_61a49bcc1507c370d5879c22 | false | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | 10000       | 10         | 300        |

