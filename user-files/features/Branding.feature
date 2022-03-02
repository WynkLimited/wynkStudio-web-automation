@Branding
Feature: Branding

  @sanity
  Scenario Outline: Verify the version number for Raj TV app
    When Navigate To Home Page
    Then Verify version number <buildVersionNumber>

    Examples:
      |buildVersionNumber       |
      | %{BUILD_VERSION_NUMBER} |