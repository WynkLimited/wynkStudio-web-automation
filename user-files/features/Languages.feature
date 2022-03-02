@Languages
Feature: Languages

  @sanity
  Scenario Outline:  Verify user can update the language
    When Navigate To Home Page
    When Login WithOut GUI Number <mobileNumber> OTP <otp>
    Then Navigate To Language Page
    Then select <languages> and click on submit button
    Then Navigate To Language Page
    Then Verify language <languages> is updated
    Then select default language<defaultLanguage>

    Examples:
      | mobileNumber                    | otp                    | languages    | defaultLanguage |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | %{TAMIL}     | %{TAMIL}        |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | %{TELUGU}    | %{TAMIL}        |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | %{MALAYALAM} | %{TAMIL}        |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | %{KANNADA}   | %{TAMIL}        |

  @sanity
  Scenario Outline: Verify User should be able to select min 1 language
    When Navigate To Home Page
    When Login WithOut GUI Number <mobileNumber> OTP <otp>
    Then Navigate To Language Page
    Then Verify User should be able to select min <minLanguageCount> languages
    Then select default language<defaultLanguage>

    Examples:
      | mobileNumber                    | otp                    | minLanguageCount      | defaultLanguage |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | %{MIN_LANGUAGE_COUNT} | %{TAMIL}        |


  @sanity
  Scenario Outline: Verify User should be able to select max 4 language
    When Navigate To Home Page
    When Login WithOut GUI Number <mobileNumber> OTP <otp>
    Then Navigate To Language Page
    Then Verify User should be able to select max <maxLanguageCount> languages
    Then select default language<defaultLanguage>

    Examples:
      | mobileNumber                    | otp                    | maxLanguageCount      | defaultLanguage |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | %{MAX_LANGUAGE_COUNT} | %{TAMIL}        |

  @P0 @sanity
  Scenario Outline:  Verify Languages without login Account
    When Navigate To Language Page
    Then verify Languages from Language Page<languages> jiraID <jiraID>

    Examples:
      | languages    | jiraID |
      | %{LANGUAGES} | NO     |


  @sanity  @wip
  Scenario Outline: Verify selected Languages from userConfig Api with Ui
    When Navigate To Home Page
    When Login WithOut GUI Number <mobileNumber> OTP <otp>
    Then Navigate To Language Page
    Then verify selected Languages <language> from  userConfig API for User<mobileNumber>
    Examples:
      | mobileNumber                    | otp                    | language     |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | %{TAMIL}     |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | %{TELUGU}    |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | %{MALAYALAM} |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | %{KANNADA}   |

  @P0 @sanity
  Scenario Outline: Verify that user  languague screen UI and text with login account
    When Navigate To Home Page
    When Login WithOut GUI Number <mobileNumber> OTP <otp>
    Then Navigate To Language Page
    Then Verify that user languague screen UI and text <languages>
    Examples:
      | mobileNumber                    | otp                    | languages    |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | %{LANGUAGES} |

  @P0 @sanity
  Scenario Outline: Verify that user  languague screen UI and text without login account
    When Navigate To Home Page
    Then Navigate To Language Page
    Then Verify that user languague screen UI and text <languages>
    Examples:
      | languages    |
      | %{LANGUAGES} |

  @regression
  Scenario Outline: Default language should be selected for new user
    When Navigate To Home Page
    When Login WithOut GUI Number <mobileNumber> OTP <otp>
    Then Navigate To Language Page
    Then Verify default language <defaultLanguage> should be selected
    Examples:
      | mobileNumber         | otp                    | defaultLanguage      |
      | %{UNSUBSCRIBED_USER} | %{XSTREAM_PREMIUM_OTP} | %{DEFAULT_LANGUAGES} |

  @regression
  Scenario Outline: Login with A user and select <selectLanguage1> language then login with B number and select language <selectLanguage2>
  then login with A number again and verify selected language
    When Navigate To Home Page
    When Login WithOut GUI Number <UserAMobileNumber> OTP <otp>
    Then Navigate To Language Page
    Then select <selectLanguage1> and click on submit button
    When Login WithOut GUI Number <UserBMobileNumber> OTP <otp>
    Then Navigate To Language Page
    Then select <selectLanguage2> and click on submit button
    When Login WithOut GUI Number <UserAMobileNumber> OTP <otp>
    Then Navigate To Language Page
    And Verify that selected language for user A should be <selectLanguage1>
    Then select default language<defaultLanguage>


    Examples:
      | UserAMobileNumber  | UserBMobileNumber | otp                    | selectLanguage1 | selectLanguage2 |defaultLanguage      |
      | %{USER_A_NUMBER}   | %{USER_B_NUMBER}  | %{XSTREAM_PREMIUM_OTP} | %{TELUGU}       | %{MALAYALAM}    |%{DEFAULT_LANGUAGES} |

  @regression
  Scenario Outline: Already Subscribed User should be able to re-select the language again
    When Navigate To Home Page
    When Login WithOut GUI Number <mobileNumber> OTP <otp>
    Then Navigate To Language Page
    And Verify User should be able to re-select the language <selectLanguage> again
    Then select default language<defaultLanguage>


    Examples:
      | mobileNumber      |otp                     | selectLanguage  | defaultLanguage      |
      | %{USER_A_NUMBER}  | %{XSTREAM_PREMIUM_OTP} | %{TELUGU}       | %{DEFAULT_LANGUAGES} |


  @regression
  Scenario Outline: Already Subscribed user relogin in the app already selected lang should appear to user again
    When Navigate To Home Page
    When Login WithOut GUI Number <mobileNumber> OTP <otp>
    Then Navigate To Language Page
    Then select <selectLanguage> and click on submit button
    Then click on Logout button
    When Login WithOut GUI Number <mobileNumber> OTP <otp>
    Then Navigate To Language Page
    Then Verify already selected lang <selectLanguage> should appear to user again
    Then select default language<defaultLanguage>


    Examples:
      | mobileNumber      |otp                     | selectLanguage  | defaultLanguage      |
      | %{USER_A_NUMBER}  | %{XSTREAM_PREMIUM_OTP} | %{TELUGU}       | %{DEFAULT_LANGUAGES} |

  @regression @wip
  Scenario Outline: Change languages in offline mode and verify language should not change
    When Navigate To Home Page
    When Login WithOut GUI Number <mobileNumber> OTP <otp>
    Then Navigate To Language Page
    And Disable wifi connection
    Then select <languages> and click on submit button
    Then verify language should not change

    Examples:
      | mobileNumber                    | otp                    | languages  |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | %{KANNADA} |

  @regression
  Scenario Outline: Verify that user can see is only language related to rajtv
    When Navigate To Home Page
    Then Navigate To Language Page
    Then Verify that user can see is only language <languages>
    Examples:
      | projectName     | languages    |
      | %{PROJECT_NAME} | %{LANGUAGES} |


