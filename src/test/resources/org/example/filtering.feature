Feature: Saucedemo filtering

  Background:
    Given the home page is opened
    And the 'Username' field is filled with 'standard_user'
    And the 'Password' field is filled with 'secret_sauce'
    And the 'Login' button is clicked

  Scenario: Filtering items by price (Low to High)
    Given the 'Filter' dropdown is displayed
    When the 'Filter' dropdown is selected
    And the low to high option is chosen
    Then the items should be displayed in ascending order of price

  Scenario: Filtering items by price (High to Low)
    Given the 'Filter' dropdown is displayed
    When the 'Filter' dropdown is selected
    And the high to low option is chosen
    Then the items should be displayed in descending order of price

  Scenario: Filtering items by name (A to Z)
    Given the 'Filter' dropdown is displayed
    When the 'Filter' dropdown is selected
    And the A to Z option is chosen
    Then the items should be displayed in alphabetical order

  Scenario: Filtering items by name (Z to A)
    Given the 'Filter' dropdown is displayed
    When the 'Filter' dropdown is selected
    And the Z to A option is chosen
    Then the items should be displayed in reverse alphabetical order
