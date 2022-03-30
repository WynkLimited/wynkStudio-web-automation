@ReleaseCreation
Feature: Release Creation

  @Regression
  Scenario Outline: Verify a pop up open when user clicks on '+ Add new release' button.
    Given User open Wynk studio Login page
    Then  Enter Email <type_of_user>
    And   Enter Password
    Then  Click on Login
    Given User clicks on New Release button
    Then Assert Yes button on pop up
    Then Assert No button on pop up
    Then Assert Are you add heading
    Then Assert cross button on Pop UP
    Examples:
    |type_of_user|
    |Artist      |

  @Regression

  Scenario Outline: Verify adding multiple Genre
    Given User open Wynk studio Login page
    Then  Enter Email <type_of_user>
    And   Enter Password
    Then  Click on Login
    Then  Click on dashboard button
    And   Click on studio home button
    Given User clicks on New Release button
    Then  click on Yes button on pop up
    Then  Click on continue Button on Pop up
    Then  click on upload audio button
    And   upload audio file
    Then  Enter Release title
    Then  select ISRC
    And   Select UPC
    Then  click on upload artwork button
    And   Upload Artwork File
    And   Click on Continue button Add Release
    Then  select multiple Genre
    And   Assert that multiple Genre got selected
    Examples:
      |type_of_user|
      |Artist         |

    @Regression
    Scenario Outline: Verify click on cross Button on pop up
      Given User open Wynk studio Login page
      Then  Enter Email <type_of_user>
      And   Enter Password
      Then  Click on Login
      Given User clicks on New Release button
      Then click on cross button on pop up
      And Assert back drop modal should not be present
      Examples:
        |type_of_user|
        |Artist         |

    @Regression
    Scenario Outline: Verify click on No Radio Button on pop up
      Given User open Wynk studio Login page
      Then  Enter Email <type_of_user>
      And   Enter Password
      Then  Click on Login
      Given User clicks on New Release button
      Then click on No button on pop up
      And Assert error message should be present on pop up
      Examples:
      |type_of_user|
      |Artist         |

  @Sanity
  Scenario Outline: Verify click on Yes Radio Button on  pop up
    Given User open Wynk studio Login page
    Then  Enter Email <type_of_user>
    And   Enter Password
    Then  Click on Login
    Given User clicks on New Release button
    Then click on Yes button on pop up
    Then Click on continue Button on Pop up
    And Assert Add New Release page should be present
    Examples:
      |type_of_user|
      |Artist         |

  @Regression
  Scenario Outline: Verify click on Back Arrow Button on  pop up
    Given User open Wynk studio Login page
    Then  Enter Email <type_of_user>
    And   Enter Password
    Then  Click on Login
    Given User clicks on New Release button
    Then  click on Yes button on pop up
    Then  Click on continue Button on Pop up
    And   Click on Back Arrow On Add Release Detail Page
    Then  Assert Add New Release Button should be present
    Examples:
      |type_of_user|
      |Artist         |

  @Sanity
  Scenario Outline: Verify Error Alerts are coming if user clicks on continue without filling the details on Upload Release Pgae
    Given User open Wynk studio Login page
    Then  Enter Email <type_of_user>
    And   Enter Password
    Then  Click on Login
    Given User clicks on New Release button
    Then  click on Yes button on pop up
    Then  Click on continue Button on Pop up
    And   Click on Continue button Add Release
    Then  Assert Error Msgs on Required Fields Upload Release Page
    Examples:
      |type_of_user|
      |Artist         |

  @Sanity
  Scenario Outline: Verify if audio file is getting played till 100% completion
    Given User open Wynk studio Login page
    Then  Enter Email <type_of_user>
    And   Enter Password
    Then  Click on Login
    Given User clicks on New Release button
    Then  click on Yes button on pop up
    Then  Click on continue Button on Pop up
    Then click on upload audio button
    And   upload audio file
    Then click on play button
    And Assert if the song is playing
    Examples:
      |type_of_user|
      |Artist         |

  @Regression
  Scenario Outline: Verify clicking on cross button is removing the uploaded audio file
    Given User open Wynk studio Login page
    Then  Enter Email <type_of_user>
    And   Enter Password
    Then  Click on Login
    Given User clicks on New Release button
    Then  click on Yes button on pop up
    Then  Click on continue Button on Pop up
    Then  click on upload audio button
    And   upload audio file
    Then  click on cross button
    And   verify that audio file is removed
    Examples:
      |type_of_user|
      |Artist         |

  @Sanity
  Scenario Outline: Verify Uploading a valid audio file with .WAV/.WMA file
    Given User open Wynk studio Login page
    Then  Enter Email <type_of_user>
    And   Enter Password
    Then  Click on Login
    Given User clicks on New Release button
    Then  click on Yes button on pop up
    Then  Click on continue Button on Pop up
    Then  click on upload audio button
    And   upload .wav audio file
    Then  click on play button
    And  Assert if the song is playing
    Examples:
      |type_of_user|
      |Artist      |

  @Sanity
  Scenario Outline: Verify Uploading a less than 800 * 800 resolution artwork
    Given User open Wynk studio Login page
    Then  Enter Email <type_of_user>
    And   Enter Password
    Then  Click on Login
    Given User clicks on New Release button
    Then  click on Yes button on pop up
    Then  Click on continue Button on Pop up
    Then  click on upload artwork button
    And   Upload small resolution Artwork File
    And  Assert the error msg for smaller resolution artwork
    Examples:
      |type_of_user|
      |Artist      |


  @Regression
  Scenario Outline: Verify when user clicks on continue without entering UPC and ISRC Code
    Given User open Wynk studio Login page
    Then  Enter Email <type_of_user>
    And   Enter Password
    Then  Click on Login
    Given User clicks on New Release button
    Then  click on Yes button on pop up
    Then  Click on continue Button on Pop up
    Then Click on Yes I have UPC
    And Click on Yes I have ISRC
    And Click on Continue button Add Release
    And Assert Error Msgs on Required Fields Upload Release Page
    Examples:
      |type_of_user|
      |Artist      |


  @Regression
  Scenario Outline: Verify Upload Release Page
    Given User open Wynk studio Login page
    Then  Enter Email <type_of_user>
    And   Enter Password
    Then  Click on Login
    Given User clicks on New Release button
    Then  click on Yes button on pop up
    Then  Click on continue Button on Pop up
    Then  click on upload audio button
    And   upload audio file
    Then Enter Release title
    Then select ISRC
    And Select UPC
    Then click on upload artwork button
    And Upload Artwork File
    And Click on Continue button Add Release
    Then Assert Add your release Details is present
    Examples:
      |type_of_user|
      |Artist         |

    @Regression
    Scenario Outline: Verify upload lyrics via upload lrc file on banner
      Given User open Wynk studio Login page
      Then  Enter Email <type_of_user>
      And   Enter Password
      Then  Click on Login
      Then  Click on dashboard button
      And   Click on studio home button
      Given User clicks on New Release button
      Then  click on Yes button on pop up
      Then  Click on continue Button on Pop up
      Then  click on upload audio button
      And   upload audio file
      Then  Enter Release title
      Then  select ISRC
      And   Select UPC
      Then  click on upload artwork button
      And   Upload Artwork File
      And   Click on Continue button Add Release
      Then  click on Here link
      And   Click on upload lyric button on modal
      Then  upload lyrics file
      Then  Assert if the lyric file is successfully uploaded
      Examples:
        |type_of_user|
        |Artist         |


    @Smoke
    Scenario Outline: Verify end to end flow
      Given User open Wynk studio Login page
      Then  Enter Email <type_of_user>
      And   Enter Password
      Then  Click on Login
      Then  Click on dashboard button
      And   Click on studio home button
      Given User clicks on New Release button
      Then  click on Yes button on pop up
      Then  Click on continue Button on Pop up
      Then  click on upload audio button
      And   upload audio file
      Then  Enter Release title
      Then  select ISRC
      And   Select UPC
      Then  click on upload artwork button
      And   Upload Artwork File
      And   Click on Continue button Add Release
      Then  Select Primary artist Role
      And   assert that the name of Primary artist is correct
      Then  Select Supporting Artist role and enter name
      And   click on cross button for secondary artist
      And   Assert that secondary artist is removed
      Then  Select Primary Language
      And   select Genre
      Then  click on upload lyrics along
      Then  Click on upload lyrics in lrc format button
      And   upload lyrics file
      Then  Assert if the lyric file is successfully uploaded
      And   Remove lyrics file
      And   assert file is removed
      Then  Enter lyrics manually
      And   Select No in explicit content
      Then  Select Yes in Previously release
      And   Enter invalid links
      And   Click on Continue button Add Release
      Then Assert for the error message for invalid URL
      And   Select No in previously release
      Then  Click on Continue button Add Release
      And   click on Here link
      And   Assert if the modal opens
      Then  click on cross icon
      Then  Enter first clip name "Hello Tune 1"
      And   Click Add more HT clip button
      And   Enter second clip name "Hello Tune 1"
      Then  Click on Continue button Add Release
      And   Assert that duplicate clipnames not allowed msg is showing
      Then  Enter second clip name "Hello Tune 2"
      And   Click on Continue button Add Release
      Then  Select as soon as possile
      And   Click on Continue button Add Release
      Then  Click on edit button for Release uploaded
      Then  Enter Release title
      And   Click on Continue button Add Release
      Then  Match the data is correct
      Then  Click on confirm and Submit
      And   Assert that the Release is in review state
      Examples:
        |type_of_user|
        |Artist         |



