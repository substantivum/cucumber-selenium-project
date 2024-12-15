Feature: Saucedemo Shopping

  Background:
    Given the home page is opened
    And the 'Username' field is filled with 'standard_user'
    And the 'Password' field is filled with 'secret_sauce'
    And the 'Login' button is clicked

  Scenario: Buying a backpack and t-shirt
    Given the 'Sauce Labs Backpack' is added to the cart
    And the 'Sauce Labs Bolt T-Shirt' is added to the cart
    And the 'Cart' button is clicked
    And the 'Checkout' button is clicked
    And the 'First Name' field is filled with 'testname_first'
    And the 'Last Name' field is filled with 'testname_last'
    And the 'Zip Code' field is filled with '1111'
    When the 'Continue' button is clicked
    Then the price should read 'Total: $49.66'
    Then the cart is emptied

  Scenario Outline: Buying an item
    Given the '<item>' is added to the cart
    And the 'Cart' button is clicked
    And the 'Checkout' button is clicked
    And the 'First Name' field is filled with '<firstName>'
    And the 'Last Name' field is filled with '<lastName>'
    And the 'Zip Code' field is filled with '<zipCode>'
    When the 'Continue' button is clicked
    Then the price should read 'Total: $<totalPrice>'
    Then the cart is emptied
    Examples:
      | item                              | firstName       | lastName       | zipCode | totalPrice |
      | Sauce Labs Backpack               | testname_first  | testname_last  | 1111    | 32.39      |
      | Sauce Labs Bike Light             | john_doe        | doe_john       | 2222    | 10.79      |
      | Sauce Labs Bolt T-Shirt           | jane_doe        | doe_jane       | 3333    | 17.27      |
      | Sauce Labs Onesie                 | alice_smith     | smith_alice    | 4444    | 8.63       |
      | Sauce Labs Fleece Jacket          | bob_jones       | jones_bob      | 5555    | 53.99      |
      | Test.allTheThings() T-Shirt (Red) | mike_brown      | brown_mike     | 6666    | 17.27      |


  Scenario Outline: Buying two items
    Given the '<item1>' is added to the cart
    And the '<item2>' is added to the cart
    And the 'Cart' button is clicked
    And the 'Checkout' button is clicked
    And the 'First Name' field is filled with '<firstName>'
    And the 'Last Name' field is filled with '<lastName>'
    And the 'Zip Code' field is filled with '<zipCode>'
    When the 'Continue' button is clicked
    Then the price should read 'Total: $<totalPrice>'
    Then the cart is emptied
    Examples:
      | item1                   | item2                                   | firstName       | lastName       | zipCode | totalPrice |
      | Sauce Labs Backpack     | Sauce Labs Bolt T-Shirt                 | testname_first  | testname_last  | 1111    | 49.66      |
      | Sauce Labs Backpack     | Sauce Labs Bike Light                   | john_doe        | doe_john       | 2222    | 43.18      |
      | Sauce Labs Bolt T-Shirt | Sauce Labs Fleece Jacket                | jane_doe        | doe_jane       | 3333    | 71.26      |
      | Sauce Labs Backpack     | Sauce Labs Onesie                       | alice_smith     | smith_alice    | 4444    | 41.02      |
      | Sauce Labs Bolt T-Shirt | Test.allTheThings() T-Shirt (Red)       | bob_jones       | jones_bob      | 5555    | 34.54      |
      | Sauce Labs Fleece Jacket| Sauce Labs Onesie                       | mike_brown      | brown_mike     | 6666    | 62.62      |

  Scenario: Remove an item from the cart
    Given the 'Sauce Labs Backpack' is added to the cart
    And the 'Cart' button is clicked
    And remove 'Sauce Labs Backpack' from the cart
    Then the cart should be empty

  Scenario: Remove all items from the cart
    Given the 'Sauce Labs Backpack' is added to the cart
    And the 'Sauce Labs Bolt T-Shirt' is added to the cart
    And the 'Cart' button is clicked
    And remove 'Sauce Labs Backpack' from the cart
    And remove "Sauce Labs Bolt T-Shirt" from the cart
    Then the cart should be empty

  Scenario Outline: Total price change after removing an item
    Given the "<item1>" is added to the cart
    And the "<item2>" is added to the cart
    And the "Cart" button is clicked
    And remove "<item1>" from the cart
    And the 'Checkout' button is clicked
    And the 'First Name' field is filled with '<firstName>'
    And the 'Last Name' field is filled with '<lastName>'
    And the 'Zip Code' field is filled with '<zipCode>'
    When the 'Continue' button is clicked
    Then the price should read "Total: $<totalPrice>"
    Then the cart is emptied
    Examples:
      | item1                   | item2                                   | firstName       | lastName       | zipCode | totalPrice |
      | Sauce Labs Backpack     | Sauce Labs Bolt T-Shirt                 | testname_first  | testname_last  | 1111    | 17.27      |
      | Sauce Labs Backpack     | Sauce Labs Bike Light                   | john_doe        | doe_john       | 2222    | 10.79      |
      | Sauce Labs Bolt T-Shirt | Sauce Labs Fleece Jacket                | jane_doe        | doe_jane       | 3333    | 53.99      |
      | Sauce Labs Backpack     | Sauce Labs Onesie                       | alice_smith     | smith_alice    | 4444    | 8.63       |
      | Sauce Labs Bolt T-Shirt | Test.allTheThings() T-Shirt (Red)       | bob_jones       | jones_bob      | 5555    | 17.27      |
      | Sauce Labs Fleece Jacket| Sauce Labs Onesie                       | mike_brown      | brown_mike     | 6666    | 8.63       |