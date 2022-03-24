@ReleaseCreation
Feature: Release Creation

  @karishma1
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
    |RUX         |


    @karishma1
    Scenario Outline: Verify click on cross Button on  pop up
      Given User open Wynk studio Login page
      Then  Enter Email <type_of_user>
      And   Enter Password
      Then  Click on Login
      Given User clicks on New Release button
      Then click on cross button on pop up
      And Assert back drop modal should not be present
      Examples:
        |type_of_user|
        |RUX         |

  @karishma1
  Scenario Outline: Verify click on cross Button on  pop up
    Given User open Wynk studio Login page
    Then  Enter Email <type_of_user>
    And   Enter Password
    Then  Click on Login
    Given User clicks on New Release button
    Then click on No button on pop up
    And Assert error message should be present on pop up
    Examples:
      |type_of_user|
      |RUX         |

  @karishma1
  Scenario Outline: Verify click on cross Button on  pop up
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
      |RUX         |

  @karishma1
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
      |RUX         |

  @karishma1
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
      |RUX         |

  @karishma
  Scenario Outline: Verify Upload Release Page
    Given User open Wynk studio Login page
    Then  Enter Email <type_of_user>
    And   Enter Password
    Then  Click on Login
    Given User clicks on New Release button
    Then  click on Yes button on pop up
    Then  Click on continue Button on Pop up
    Then click on upload audio button
    And upload audio file
    Then Enter Release title
    Then select ISRC
    And Select UPC
    Then click on upload artwork button
    And Upload Artwork File
    And Click on Continue button Add Release
    Then Assert Add your release Details is present
    Examples:
      |type_of_user|
      |RUX         |


