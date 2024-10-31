# TA Training Java Project

This repository implements automation testing for the Google Cloud Platform Pricing Calculator and Pastebin, utilizing TestNG and Maven to ensure reliable, adaptable, and efficient test execution.

## Project Overview

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

Execute the commands below to run GoogleCloudTest in targeted environments with specified browsers.

### Development Environment

```bash
mvn test -Dtest=GoogleCloudTest -Denvironment=dev -DBROWSER=chrome
```

### QA Environment

```bash
mvn test -Dtest=GoogleCloudTest -Denvironment=qa -DBROWSER=firefox
```

### Staging Environment

```bash
mvn test -Dtest=GoogleCloudTest -Denvironment=staging -DBROWSER=chrome
```

### Production Environment

```bash
mvn test -Dtest=GoogleCloudTest -Denvironment=prod -DBROWSER=edge
```

### Running the Complete Test Suite

For a full suite execution covering both GoogleCloudTest and PastebinTest, use the following command:

```bash
mvn test "-Dsurefire.suiteXmlFiles=src/test/resources/suites/testng-all.xml" -Denvironment=dev
```

## Logging and Screenshot Management

- **Logging**: Configured via Log4j2; logs are saved in logs/app.log for structured tracking and debugging.
- **Screenshots**: Screenshots are automatically captured on test failures for GoogleCloudTest only and saved in /screenshots for easy access.
