Feature: Login Form Validation

    Scenario: UC-1 Test Login form with empty credentials
        Given I am on the login page
        When I type any credentials in the "Username" field
        And I type any credentials in the "Password" field
        And I clear the "Username" and "Password" fields
        And I click the "Login" button
        Then I should see an error message "Username is required"

    Scenario: UC-2 Test Login form with credentials by passing Username
        Given I am on the login page
        When I type any credentials in the "Username" field
        And I enter a password
        And I clear the "Password" input
        And I click the "Login" button
        Then I should see an error message "Password is required"

    Scenario: UC-3 Test Login form with credentials by passing Username & Password
        Given I am on the login page
        When I type "standard_user" in the "Username" field
        And I enter "secret_sauce" as the password
        And I click the "Login" button
        Then I should see the page title "Swag Labs" in the dashboard