@Search
Feature: Search

  @sanity
  Scenario Outline: Verify search behaviour when user search a content that is not present
    When Navigate To Home Page
    Then click on signin button
    And  User enters mobile number <mobileNumber> for login
    And  User enters otp <otp> for login
    Then click on search button
    When search contentName <contentName>
    Then Verfity no result found text for <contentName>
    Examples:
      | mobileNumber                    | otp                    | contentName |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | ggg         |

  @smoke
  Scenario Outline: Search a movie and play
    When Navigate To Home Page
    Then click on signin button
    And  User enters mobile number <mobileNumber> for login
    And  User enters otp <otp> for login
    Then click on search button
    When search contentName <contentName>
    Then select content<contentType>
    Then click on play pause button
    Then Verify content is playing
    Examples:
      | mobileNumber                    | otp                    | contentName | contentType |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | roja        |   movies    |

  @regression
  Scenario Outline: Search Web Series and play
    When Navigate To Home Page
    Then click on signin button
    And  User enters mobile number <mobileNumber> for login
    And  User enters otp <otp> for login
    Then click on search button
    When search contentName <contentName>
    Then select content<contentType>
    Then click on play pause button
    Then Verify content is playing

    Examples:
      | mobileNumber                    | otp                    | contentName               |contentType |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | Navarathri special        | tv shows   |

  @regression
  Scenario Outline: Verify Search behaviour when search text contains special character
    When Navigate To Home Page
    Then click on signin button
    And  User enters mobile number <mobileNumber> for login
    And  User enters otp <otp> for login
    Then click on search button
    When search contentName <contentName>
    Then Verfity no result found text for <contentName>
    Examples:
      | mobileNumber                    | otp                    | contentName |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | !@$@#$      |

  @regression
  Scenario Outline: Verify search bar when user type a text in search and click on cross icon on search bar
    When Navigate To Home Page
    Then click on signin button
    And  User enters mobile number <mobileNumber> for login
    And  User enters otp <otp> for login
    Then click on search button
    When search contentName <contentName>
    Then click on searchbar
    Then click on search cross button
    Then verify the search bar empty
    Examples:
      | mobileNumber                    | otp                    | contentName |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | roja        |

  @regression
  Scenario Outline: verify the placeholder text of searchbar
    When Navigate To Home Page
    Then click on signin button
    And  User enters mobile number <mobileNumber> for login
    And  User enters otp <otp> for login
    Then click on search button
    Then verify the placeholder text
    Examples:
      | mobileNumber                    | otp                    |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} |

  @regression
  Scenario Outline: Verify Search behaviour when video is in PIP Mode
    When Open playable content with contentType<contentType> ,contentName<contentName>,contentID<contentID>,flag<flag>
    Then click on play pause button
    When Login with number<mobilenumber> and OTP<OTP>
    And click on PIP button
    Then click on search button
    When search contentName <contentName>
    Then Verfity the search behaviour for <contentName>


    Examples:
      | contentType | contentName | contentID                            | flag  | mobilenumber                    | OTP                    | waitingTime | apiCounter | hitTimeGap |
      | MOVIES      | roja        | RAJTV_MOVIE_61a49bcc1507c370d5879c22 | false | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | 10000       | 10         | 300        |

  @sanity
  Scenario Outline: Verify Search Page Verification after First Search
    When Navigate To Home Page
    Then click on signin button
    And  User enters mobile number <mobileNumber> for login
    And  User enters otp <otp> for login
    Then click on search button
    And type contentName <contentName> on searchbar
    Then select the content from the dropdown
    Then click on searchbar
    Then verify the searched <contentName> under the recent search


    Examples:
      | mobileNumber                    | otp                    | contentName |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | roja        |

  @regression
  Scenario Outline:  Auto suggestion contents available must be as per api resonse (api)
    When Navigate To Home Page
    Then click on signin button
    And  User enters mobile number <mobileNumber> for login
    And  User enters otp <otp> for login
    Then click on search button
    And type contentName <contentName> on searchbar
    Then Verify Auto suggestion contents list should match with API for mobile number <mobileNumber> and contentName <contentName>

    Examples:
      | mobileNumber                    | otp                    | contentName |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | puth        |

  @sanity
  Scenario Outline:  Observe no suggestion automatically until Search Text length is greater than or equal to three
    When Navigate To Home Page
    Then click on signin button
    And  User enters mobile number <mobileNumber> for login
    And  User enters otp <otp> for login
    Then click on search button
    And type contentName <contentName> on searchbar
    Then Verify no suggestion for <contentName> until Search Text length is greater than or equal to three

    Examples:
      | mobileNumber                    | otp                    | contentName |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | pu          |

  @sanity
  Scenario Outline:  Observe Autosuggestion automatically when Search Text length is greater than or equal to three
    When Navigate To Home Page
    Then click on signin button
    And  User enters mobile number <mobileNumber> for login
    And  User enters otp <otp> for login
    Then click on search button
    And type contentName <contentName> on searchbar
    Then Verify Autosuggestion for <contentName> when Search Text length is greater than or equal to three

    Examples:
      | mobileNumber                    | otp                    | contentName |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | put         |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | puth        |

  @regression
  Scenario Outline:  Verify total content count in serach page from API and content count in rail title (api)
    When Navigate To Home Page
    Then click on search button
    When search contentName <contentName>
    Then Verify total content count in serach page from API and content count in rail title for contentName <contentName> number <mobileNumber>

    Examples:
      | mobileNumber                    | contentName |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | roja        |

  @regression
  Scenario Outline:  Verify search added in recent search
    When Navigate To Home Page
    Then click on search button
    When search multiple content <contentsName>
    Then Verify searched content <contentsName> under recent searches

    Examples:
      | mobileNumber                    | otp                    | contentsName                                |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | Thaai Naadu , Pandian ,Dharmathin Thalaivan |

  @sanity
  Scenario Outline:  Trending and Recent verification on Search page
    When Navigate To Home Page
    Then click on search button
    When search multiple content <contentsName>
    Then Verify Trending and Recent verification on Search pages

    Examples:
      | mobileNumber                    | otp                    | contentsName                                |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | Thaai Naadu , Pandian ,Dharmathin Thalaivan |

  @smoke
  Scenario: Search Page verification
    When Navigate To Home Page
    Then Verify Search Page

  @regression
  Scenario Outline:  Verify content search by <Searchlanguage> is related to <Searchlanguage>
    When Navigate To Home Page
    Then click on search button
    When search by <Searchlanguage>
    Then Verify search is related to <Searchlanguage> language

    Examples:
      | mobileNumber                    | otp                    | Searchlanguage |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | tamil          |

  @regression
  Scenario Outline:  Search a movie by star name and play then verify staring content
    When Navigate To Home Page
    Then click on search button
    When search by <artistName>
    Then Verify search is related to <artistName> artist

    Examples:
      | mobileNumber                    | otp                    | artistName    |
      | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | Jayalalithaa J  |

  @regression
  Scenario Outline:  Verify that contents in Trending should be consistent for all the users
    When Navigate To Home Page
    Then Verify Trending content list should be consistent for UserA <userAmobileNumber> and UserB <userBmobileNumber> <otp>


    Examples:
      | userAmobileNumber | userBmobileNumber  | otp                    |
      | %{USER_A_NUMBER}  | %{USER_B_NUMBER}   | %{XSTREAM_PREMIUM_OTP} |




