@LiveTV
Feature: LiveTV

  @P0 @sanity @wip
  Scenario Outline: verify <languages> All Live Tv Rails From The LiveTv layout API
    When Navigate To More Page
    Then click on Login button
    And User enters mobile number <mobileNumber> for login
    And User enters otp <otp> for login
    Then Navigate To Language Page
    Then wait for 500 milli second
    Then select Language<languages>
    When Navigate To LiveTV Page
    Then reload current page
    And wait for 2000 milli second
    Then verify <languages> Live Tv rails layout from layout API for <mobileNumber> and API lng<langForAPI>  jiraID <jiraID>

    Examples: 
      | mobileNumber                    | otp                    | languages     | langForAPI | jiraID |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | English,Hindi | en,hi      | NO     |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | Bengali       | bn         | NO     |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | Gujarati      | gu         | NO     |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | Kannada       | kn         | NO     |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | Malyalam      | ml         | NO     |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | Marathi       | mr         | NO     |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | Tamil         | ta         | NO     |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | Telugu        | te         | NO     |

  @P0 @sanity @wip
  Scenario Outline: verify <languages> live tv Rails Contents from Content API
    When verify <languages> All Rails Channels from API when API lang is <langForAPI> for MobileNumber<mobileNumber> jiraID <jiraID>

    Examples: 
      | mobileNumber                    | otp                    | languages     | langForAPI | jiraID |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | English,Hindi | en,hi      | NO     |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | Bengali       | bn         | NO     |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | Gujarati      | gu         | NO     |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | Kannada       | kn         | NO     |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | Malyalam      | ml         | NO     |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | Marathi       | mr         | NO     |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | Tamil         | ta         | NO     |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | Telugu        | te         | NO     |

  @P0 @sanity @wip
  Scenario Outline: verify content sorting options for <languages> Live TV Rails
    Then verify Sorting options<SortingType> for <mobileNumber> and <langForAPI>  jiraID <jiraID>

    Examples: 
      | mobileNumber                    | otp                    | languages     | langForAPI | SortingType                                 | jiraID |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | English,Hindi | en,hi      | Sort By Z-A,Sort By A-Z,Sort By Newly Added | NO     |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | Bengali       | bn         | Sort By Z-A,Sort By A-Z,Sort By Newly Added | NO     |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | Gujarati      | gu         | Sort By Z-A,Sort By A-Z,Sort By Newly Added | NO     |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | Kannada       | kn         | Sort By Z-A,Sort By A-Z,Sort By Newly Added | NO     |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | Malyalam      | ml         | Sort By Z-A,Sort By A-Z,Sort By Newly Added | NO     |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | Marathi       | mr         | Sort By Z-A,Sort By A-Z,Sort By Newly Added | NO     |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | Tamil         | ta         | Sort By Z-A,Sort By A-Z,Sort By Newly Added | NO     |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | Telugu        | te         | Sort By Z-A,Sort By A-Z,Sort By Newly Added | NO     |

  @P0 @wip
  Scenario Outline: verify rails More and previous and next content button
    When Navigate To More Page
    Then click on Login button
    And User enters mobile number <mobileNumber> for login
    And User enters otp <otp> for login
    And wait for 3000 milli second
    When Navigate To LiveTV Page
    When scrolling To rail and verify more and arrow button jiraID <jiraID>

    Examples: 
      | mobileNumber                    | otp                    | jiraID |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | NO     |

  @P0 @wip
  Scenario Outline: verify previous and next content button Functionality
    When Navigate To More Page
    Then click on Login button
    And User enters mobile number <mobileNumber> for login
    And User enters otp <otp> for login
    And wait for 3000 milli second
    When Navigate To LiveTV Page
    When scrolling To rail and verify previous and next arrow button Functionality jiraID <jiraID>

    Examples: 
      | mobileNumber                    | otp                    | jiraID |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | NO     |

  @P0 @sanity @wip
  Scenario Outline: verify <languages> live Channel Schedule Rails Contents
    When Verify channel schedule rail MobileNumber <mobileNumber> - API Lang <langForAPI>

    Examples: 
      | mobileNumber                    | otp                    | languages     | langForAPI | jiraID |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | English,Hindi | en,hi      | NO     |

  #      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | Bengali       | bn         | NO     |
  #      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | Gujarati      | gu         | NO     |
  #      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | Kannada       | kn         | NO     |
  #      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | Malyalam      | ml         | NO     |
  #      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | Marathi       | mr         | NO     |
  #      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | Tamil         | ta         | NO     |
  #      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | Telugu        | te         | NO     |
  @P0 @smoke @wip
  Scenario Outline: verify <languages> Current live content contain red live Logo and red border line
    When Verify schedule rail live content card red indicator  MobileNumber <mobileNumber> - API Lang <langForAPI>

    Examples: 
      | mobileNumber                    | otp                    | languages     | langForAPI | jiraID |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | English,Hindi | en,hi      | NO     |

  @smoke @wip
  Scenario Outline: verify content sorting options for <languages> Live TV Rails
    Then verify Sorting options<SortingType> for <mobileNumber> and <langForAPI>  jiraID <jiraID>

    Examples: 
      | mobileNumber                    | otp                    | languages     | langForAPI | SortingType                                 | jiraID |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | English,Hindi | en,hi      | Sort By Z-A,Sort By A-Z,Sort By Newly Added | NO     |
