@ContentListing
Feature: ContentListing

  @P0
  Scenario Outline: verify <PageName> Banner from API for this user
    When Navigate To Home Page
    Then click on signin button
    And User enters mobile number <mobileNumber> for login
    And User enters otp <otp> for login
    Then Navigate To Language Page
    Then wait for 1000 milli second
    Then select LanguageEnglish,Hindi
    Then wait for 1000 milli second
    Then reload current page
    When Navigate To Page<PageName>
    And wait for 1000 milli second
    When verify <PageName> banner Content From API content<mobileNumber> JiraID <JiraID>

    Examples: 
      | mobileNumber                    | otp                    | PageName     | JiraID |
     | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | HOME PAGE    | NO     |
  #    | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | TVSHOWS PAGE | NO     |
  #    | %{XSTREAM_PREMIUM_PHONE_NUMBER}| %{XSTREAM_PREMIUM_OTP} | MOVIES PAGE  | NO     |

  @P0 @smoke @wip
  Scenario Outline: verify <PageName> Rails from Layout API
    When Navigate To Home Page
    Then click on signin button
    And User enters mobile number <mobileNumber> for login
    And User enters otp <otp> for login
    Then Navigate To Language Page
    Then wait for 500 milli second
    Then select LanguageEnglish,Hindi
    Then wait for 1000 milli second
    When Navigate To Page<PageName>
    When verify <PageName> Rails Layout From API content<mobileNumber> JiraID <JiraID>

    Examples: 
      | mobileNumber                    | otp                    | PageName     | JiraID |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | HOME PAGE    | NO     |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | TVSHOWS PAGE | NO     |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | MOVIES PAGE  | NO     |

  # comment due to sync api Takes 10 15 minute data Syncing
  #  @P0
  #  Scenario Outline: verify continue watch list and my watchlist from Sync API
  #    When  Navigate To More Page
  #    Then  click on Login button
  #    And   User enters mobile number <mobileNumber> for login
  #    And   User enters otp <otp> for login
  #    Then  wait for 1000 milli second
  #    When  Navigate To More Page
  #    Then  remove all content from watch list
  #    Then  wait for 1000 milli second
  #    When  add content in my watchlist
  #    When  Navigate To More Page
  #    Then  wait for 2000 milli second
  #    When  add content in continue watch list from my watchlist
  #    Then  verify My WatchList and Continue Watchlist content
  #    Then  wait for 1000 milli second
  #    Then  verify watch list content from API<mobileNumber>
  #
  #    Examples:
  #      | mobileNumber                    | otp                    |
  #      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} |
  @P0 @wip
  Scenario Outline: verify <languages> player Page You may also like content from Related API for <ContentType>
    Then verify You may also like content for <languages> language contentType <ContentType> apiLang <langForAPI> and user <mobileNumber> JiraID <JiraID>

    Examples: 
      | mobileNumber                    | otp                    | languages     | langForAPI | ContentType | JiraID |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | English,Hindi | en,hi      | Movies      | NO     |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | Bengali       | bn         | Movies      | NO     |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | Gujarati      | gu         | Movies      | NO     |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | Kannada       | kn         | Movies      | NO     |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | Malyalam      | ml         | Movies      | NO     |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | Marathi       | mr         | Movies      | NO     |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | Tamil         | ta         | Movies      | NO     |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | Telugu        | te         | Movies      | NO     |

  @smoke @wip
  Scenario Outline: verify <languages> player Page You may also like content from Related API for <ContentType>
    Then verify You may also like content for <languages> language contentType <ContentType> apiLang <langForAPI> and user <mobileNumber> JiraID <JiraID>

    Examples: 
      | mobileNumber                    | otp                    | languages     | langForAPI | ContentType | JiraID |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | English,Hindi | en,hi      | Movies      | NO     |
