Feature: Dashboard feature validation
  As a dashboard user, I want to see a list of Marvel characters, so that I can explore their
  details, filter and select specific characters for more information or actions.

  Background:
    Given User navigates to dashboard
    When dashboard loads successfully

  @homepage
  Scenario: Successful loading of dashboard homepage
    Then initial list of Marvel characters is displayed

  @scroll
  Scenario: validating scroll functionality
    When user scrolls down the list
    Then Additional characters are loaded dynamically


  @search
  Scenario Outline: validating search functionality
    When user enters "<search term>" in search bar
    Then list updates to display matching characters
    Examples:
    |search term|
    |hello|
    |Zaladane|
    |Spider|

  @character
  Scenario Outline: validating character page
    When user clicks "<number>" character
    Then user is navigated to character page
    And detailed information of the character is shown
    Examples:
      |number|
      |10|
      |51|

