Feature: Saucedemo User Login

  Background:
    Given the home page is opened

  Scenario: Successful login
    Given the 'Username' field is filled with 'standard_user'
    And the 'Password' field is filled with 'secret_sauce'
    When the 'Login' button is clicked
    Then the user is redirected to the Products page

  Scenario Outline: Incorrect login attempts
    Given the 'Username' field is filled with '<username>'
    And the 'Password' field is filled with '<password>'
    When the 'Login' button is clicked
    Then the '<errorMessage>' message is shown
    Examples:
      | username        | password       | errorMessage                                                              |
      |                 |                | Epic sadface: Username is required                                        |
      | standard_user   |                | Epic sadface: Password is required                                        |
      | standard_user   | wrong_password | Epic sadface: Username and password do not match any user in this service |
      | locked_out_user | secret_sauce   | Epic sadface: Sorry, this user has been locked out.                       |
      |                 | secret_sauce   | Epic sadface: Username is required                                        |
      | wrong_username  | secret sauce   | Epic sadface: Username and password do not match any user in this service |


