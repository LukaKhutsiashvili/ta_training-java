
# TA Training Java Project

This repository implements automation testing for the saucedemo Login Form, Google Cloud Platform Pricing Calculator, and Pastebin. Utilizing TestNG, Maven, and Cucumber BDD, this project ensures reliable, adaptable, and efficient test execution across various environments and browsers.

## Final Task - Saucedemo Login Form Automation

The final task automates the testing of the Login Form on the saucedemo website, using Cucumber BDD to structure the test scenarios. The task covers three main use cases:

### Use Cases

1. **UC-1**: Test Login form with empty credentials.
   - **Steps**:
     - Type any credentials into both the "Username" and "Password" fields.
     - Clear the contents of both fields.
     - Click the "Login" button.
     - **Expected Result**: An error message should be displayed stating "Username is required".

2. **UC-2**: Test Login form with only the username provided.
   - **Steps**:
     - Type any credentials into the "Username" field.
     - Enter any value in the "Password" field.
     - Clear the contents of the "Password" field.
     - Click the "Login" button.
     - **Expected Result**: An error message should be displayed stating "Password is required".

3. **UC-3**: Test Login form with both username and password provided.
   - **Steps**:
     - Type valid credentials in the "Username" field that are part of the accepted username list (e.g., "standard_user").
     - Enter "secret_sauce" in the "Password" field.
     - Click the "Login" button.
     - **Expected Result**: Successful login should occur, and the dashboard should display with the title "Swag Labs".

### Features

- **BDD with Cucumber**: Implements Given-When-Then scenarios in `src/test/resources/features/login.feature` files for clear test structure.
- **Data-Driven Testing**: Utilizes JUnit's `ParameterizedTest` and custom `TestDataProvider` to cover multiple test cases.
- **Parallel Execution**: Configured parallel execution for faster test runs, supporting up to three concurrent threads.
- **Non-Parallel Execution**: For non-parallel test execution, use the `CucumberTestRunner` located in `final_task/cucumber/runners/CucumberTestRunner` to run tests sequentially.
- **Browser Options**: Custom configurations for Firefox and Edge browsers, including headless mode, window maximization, height and width control.

### Running the Final Task

The final task runs the `LoginTest` in different environments, each configured with a specific browser:

- **Development Environment** (`dev`): Runs in **Firefox** (default browser).
- **QA Environment** (`qa`): Runs in **Microsoft Edge**.

Use the following commands to execute the final task in each environment:

**Development Environment**
```shell
mvn clean test -Dtest=LoginTest -Denvironment=dev
```

**QA Environment**
```shell
mvn clean test -Dtest=LoginTest -Denvironment=qa
```

## Google Cloud Platform Pricing Calculator Project Overview  

- **Multi-Environment Adaptability**: Configured to run in Development, QA, Staging, and Production environments for seamless integration across all stages of development.
- **Cross-Browser Support**: Compatible with Chrome, Firefox, and Edge to ensure consistent performance across popular browsers.
- **Modular Test Management**: Organized test suites and configurations for focused and comprehensive test runs.
- **Error-Driven Insights**: Automated screenshot capture on critical test failures for GoogleCloudTest, enabling faster diagnostics.
- **Structured Logging**: Log4j2 provides clear and organized logs, simplifying monitoring and error tracking.

## Prerequisites

Ensure the following are set up to guarantee smooth test execution:

- **Java**: Version 11 or higher
- **Maven**: Version 3.6 or higher
- **WebDrivers**: Latest drivers for Chrome, Firefox, and Edge must be available.

## Running GoogleCloudTest in Specific Environments

Execute the commands below to run `GoogleCloudTest` in targeted environments with specified browsers.

**Development Environment**
```shell
mvn clean test -Dtest=GoogleCloudTest -Denvironment=dev
```

**QA Environment**
```shell
mvn clean test -Dtest=GoogleCloudTest -Denvironment=qa
```

**Staging Environment**
```shell
mvn clean test -Dtest=GoogleCloudTest -Denvironment=staging
```

**Production Environment**
```shell
mvn clean test -Dtest=GoogleCloudTest -Denvironment=prod
```

## Running the Complete Test Suite

For a full suite execution covering GoogleCloudTest and saucedemo Login Test, use the following command:

```shell
mvn clean test "-Dsurefire.suiteXmlFiles=src/test/resources/suites/testng-all.xml" -Denvironment=dev
```

## Logging and Screenshot Management

- **Logging**: Configured via Log4j2 for structured tracking and debugging.
- **Screenshots**: Screenshots are automatically captured on test failures for GoogleCloudTest only and saved in `/screenshots` for easy access.
