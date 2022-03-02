@LocalCookie
Feature: Local Storage & Cookies

  @P0 @smoke @wip
  Scenario Outline: verify Local Storage data from user/login API
    When Navigate To Home Page
    Then click on signin button
    And  User enters mobile number <mobileNumber> for login
    And  User enters otp <otp> for login
    Then wait for 1000 milli second
    When Verify Local Storage data from user/login API MobileNumber <mobileNumber> - OTP <otp> - localStorageItem <localStorageItem> - LoginAPIKeys <LoginAPIKeys>

    Examples:
      | mobileNumber                    | otp                    | localStorageItem                   | LoginAPIKeys                                      |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | msisdnDetected,token,user_lang,uid | msisdnDetected,token,userConfig.userInfo.lang,uid |

  @P0 @sanity
  Scenario Outline: verify selected language should be reflect in local Storage for <languages>
    Then Navigate To Language Page
    Then wait for 500 milli second
    Then select Language<languages>
    Then Verify Selected language should be reflect in LocalStorage - Language <languages> - JiraID <jiraID>
    Then Navigate To Language Page
    Then Verify default language <defaultLanguage> should be selected

    Examples:
      | mobileNumber                    | otp                    | languages    | defaultLanguage |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | %{TAMIL}     | %{TAMIL}        |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | %{TELUGU}    | %{TAMIL}        |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | %{MALAYALAM} | %{TAMIL}        |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | %{KANNADA}   | %{TAMIL}        |


  @P0 @sanity
  Scenario Outline: verify Recent Search Query should be save in Local Storage
    When Navigate To Home Page
    Then click on signin button
    And  User enters mobile number <mobileNumber> for login
    And  User enters otp <otp> for login
    Then Navigate To Search Page
    Then wait for 500 milli second
    Then click on Recent search clear button
    When verify resent search Query from local storage - Query <searchQuery>
    Examples:
      | mobileNumber                    | otp                    | searchQuery |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | roja,style  |


  @P0 @smoke @wip
  Scenario Outline: verify continue watch list and my watchlist rail content should be visible in local Storage
    When Navigate To Home Page
    Then click on signin button
    And   User enters mobile number <mobileNumber> for login
    And   User enters otp <otp> for login
    Then  wait for 1000 milli second
    When Navigate To Home Page
    Then  remove all content from watch list
    Then  wait for 1000 milli second
    Then  reload current page
    Then  Navigate To Home Page
    Then  wait for 1000 milli second
    When  add content in my watchlist NoOfContent 2
    When Navigate To Home Page
    Then  wait for 2000 milli second
    When  add content in continue watch list from my watchlist
    When Navigate To Home Page
    Then  verify continue watch list and my watchlist rail content should be visible in local Storage
    Examples:
      | mobileNumber                    | otp                    |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} |

