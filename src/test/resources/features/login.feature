Feature: Login Functionality

Scenario: Empty credentials
    Given I open the login page
    When I clear the username and password fields
    And I click the login button
    Then I should see the error message "Username is required"

Scenario: Only username provided
    Given I open the login page
    When I enter username "standard_user"
    And I clear the password field
    And I click the login button
    Then I should see the error message "Password is required"

Scenario: Valid credentials
    Given I open the login page
    When I enter username "standard_user"
    And I enter password "secret_sauce"
    And I click the login button
    Then I should see the title "Swag Labs"
