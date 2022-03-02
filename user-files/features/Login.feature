@login
Feature: Login

  @regression
  Scenario Outline: Login With content playing
    Given User search for content <contentName>
    Then click on play pause button
    And User enters mobile number <mobileNumber> for login
    And User enters otp <otp> for login
    Then Verify content is playing

    Examples:
      | contentName        | mobileNumber                    | otp                    |
      | roja               | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} |

  @sanity
  Scenario Outline:  Verify on menu/navigation page for RajTV User
    When Navigate To Home Page
    When Login WithOut GUI Number <mobileNumber> OTP <otp>
    And user click on profile icon
    Then Verify on menu-navigation page having options <menu>

    Examples:
      | otp  | mobileNumber                     | menu                                                              |
      | 1234 | %{XSTREAM_PREMIUM_PHONE_NUMBER} | Watchlist , Plans & Offers , Logout , Feedback ,Help and Support |

  @sanity
  Scenario Outline: Verify All Top Pages when login with valid number
    When Navigate To Home Page
    Then click on signin button
    And User only enters mobile number <mobileNumber> for loginleNumber> for login
    Then Click on login Continue button
    And User enters otp <otp> for login
    Then verify All Top Pages <pages>

    Examples:
      | mobileNumber                    | otp                    | pages                      |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | Home ,Movies,TV Shows, News |

  @sanity
  Scenario Outline: verify all top bar pages for withoutLogin
    When Navigate To Home Page
    Then verify All Top Pages <pages>

    Examples:
      | mobileNumber                    | otp                    | pages                      |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | Home ,Movies,TV Shows, News |

  @regression
  Scenario Outline: Play any content and verify register popup then close popup and then play the content and again verify register popup for withoutLogin
    When Open playable content with contentType<contentType> ,contentName<contentName>,contentID<contentID>,flag<flag>
    Then click on play pause button
    And close the login popup
    Then click on play pause button
    Then verify login popup

    Examples:
      | contentType | contentName        | contentID                                 | flag  | mobilenumber                    | OTP                    |
      | MOVIES      | roja               | RAJTV_MOVIE_61a49bcc1507c370d5879c22      | false | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} |

  @regression
  Scenario Outline: Verify without login and play any content then register popup will come then login and validate the same searched content is playing
    When Open playable content with contentType<contentType> ,contentName<contentName>,contentID<contentID>,flag<flag>
    Then click on play pause button
    When Login with number<mobilenumber> and OTP<OTP>
    Then Verify content is playing


    Examples:
      | contentType | contentName        | contentID                                 | flag  | mobilenumber                    | OTP                    |
      | MOVIES      | roja               | RAJTV_MOVIE_61a49bcc1507c370d5879c22      | false | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} |

  @sanity
  Scenario Outline: Login using watchlist and Verify content added into watchlist
    When Open playable content with contentType<contentType> ,contentName<contentName>,contentID<contentID>,flag<flag>
    Then Add content to watchlist
    When Login with number<mobilenumber> and OTP<OTP>
    Then  verify content is added in watchlist

    Examples:
      | contentType | contentName        | contentID                                 | flag  | mobilenumber                    | OTP                    |
      | MOVIES      | roja               | RAJTV_MOVIE_61a49bcc1507c370d5879c22      | false | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} |

  @regression
  Scenario Outline: verify Login Continue button is enable or disable for <mobileNumber>
    When Navigate To Home Page
    Then click on signin button
    And User only enters mobile number <mobileNumber> for login
    Then Verify continue button is enable or not when user enter 9 digit number - flag <flag>

    Examples:
      | mobileNumber | flag  |
      | 12345678     | false |
      | 1234567891   | true  |

  @regression
  Scenario Outline: verify OPT button is enable or disable with  partial or complete OTP for <mobileNumber>
    When Navigate To Home Page
    Then click on signin button
    And User only enters mobile number <mobileNumber> for login
    Then Click on login Continue button
    When User only enters otp <otp> for login
    Then Verify verify button is enable or not when user enter less than 4 digit number - flag <flag>

    Examples:
      | mobileNumber | otp  | flag  |
      | 0134567890   | 1224 | true  |
      | 0123456780   | 123  | false |

  @sanity
  Scenario Outline: verify resend OTP Button functionality
    When Navigate To Home Page
    Then click on signin button
    And User only enters mobile number <mobileNumber> for login
    Then Click on login Continue button
    When User only enters otp <otp> for login
    When click on Resent OTP button
    Then verify OTP Text box On Resend click <otpsize>

    Examples:
      | mobileNumber | otp  | otpsize |
      | 0134567890   | 1224 | 0       |

  @regression
  Scenario Outline: verify invalid OTP Error Message
    When Navigate To Home Page
    Then click on signin button
    And  User enters mobile number <mobileNumber> for login
    And  User enters otp <otp> for login
    Then verify OTP Error message visible <ErrorMsg>

    Examples:
      | mobileNumber | otp  | ErrorMsg         |
      | 0123143124   | 0000 | OTP is incorrect |

  @regression
  Scenario Outline: Test the functionality of back button on OTP page.
    When Navigate To Home Page
    Then click on signin button
    And  User enters mobile number <mobileNumber> for login
    Then click on back button
    Then verify user is on login popup


    Examples:
      | mobileNumber | otp  | ErrorMsg         |
      | 0123143124   | 0000 | OTP is incorrect |



#  Login_TC_8
#  Login pop up should be contained AirtelXstream Logo and  these Strings(Enter your Airtel number
#  You can also use your Broadband/DTH registered numbers to login,I agree to the Terms of Uses and Privacy Policy)
#  Click on user terms and privacy policy so page should redirect to these policies page.
  @sanity
  Scenario: Verify login PopUP elements visible or not
    When Navigate To Home Page
    Then click on signin button
    Then Verify Login PopUP elements is visible or not

   @smoke
  Scenario Outline: Verify user is able to login with valid credentials
    When Navigate To Home Page
    Then click on signin button
    And User only enters mobile number <mobileNumber> for login
    Then Click on login Continue button
    When User only enters otp <otp> for login
    Then User clicks on verify button
    Then verify successfully login
    Examples:
      | mobileNumber                    | otp                    |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} |



#  @P0 @smoke @mobile
#  Scenario Outline: Login with more Page red button login
#    When Navigate To More Page
#    Then click on Red Login Button
#    And User enters mobile number <mobileNumber> for login
#    And User enters otp <otp> for login
 #   Then click on Logout button
#    When Navigate To More Page
#    Then click on Red Login Button
#
#    Examples:
#      | mobileNumber                    | otp                    |
#