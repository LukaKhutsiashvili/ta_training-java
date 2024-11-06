package com.epam.training.luka_khutsiashvili.final_task.tests;

import com.epam.training.luka_khutsiashvili.final_task.config.ConfigLoader;
import com.epam.training.luka_khutsiashvili.final_task.driver.DriverSingleton;
import com.epam.training.luka_khutsiashvili.final_task.pages.LoginPage;
import com.epam.training.luka_khutsiashvili.final_task.utils.CustomAssertions;
import com.epam.training.luka_khutsiashvili.final_task.utils.LoggerUtil;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;

@Execution(ExecutionMode.CONCURRENT)
public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private String baseUrl;

    @BeforeEach
    public void setUp() {
        ConfigLoader config = ConfigLoader.getInstance();
        baseUrl = config.getBaseUrl();
        driver = DriverSingleton.getDriver();
        loginPage = new LoginPage(driver);
        driver.get(baseUrl);
        LoggerUtil.logInfo("Navigated to login page: " + baseUrl);
    }

    @ParameterizedTest
    @MethodSource("com.epam.training.luka_khutsiashvili.final_task.utils.TestDataProvider#loginDataProvider")
    public void testLoginForm(String username, String password, String expectedMessageOrTitle) {
        if (username.isEmpty() && password.isEmpty()) {
            testEmptyCredentials(expectedMessageOrTitle);
        } else if (!username.isEmpty() && password.isEmpty()) {
            testUsernameOnly(username, expectedMessageOrTitle);
        } else {
            testValidCredentials(username, password, expectedMessageOrTitle);
        }
    }

    private void testEmptyCredentials(String expectedErrorMessage) {
        LoggerUtil.logInfo("Testing UC-1: Empty credentials");
        loginPage.enterUsername("temp_username");
        loginPage.enterPassword("temp_password");
        loginPage.clearFields();
        loginPage.clickLogin();

        String errorMessage = loginPage.getErrorMessage();
        CustomAssertions.assertErrorMessage(errorMessage, expectedErrorMessage);
        LoggerUtil.logInfo("UC-1: Empty credentials test completed");
    }

    private void testUsernameOnly(String username, String expectedErrorMessage) {
        LoggerUtil.logInfo("Testing UC-2: Username only");
        loginPage.enterUsername(username);
        loginPage.enterPassword("temp_password");
        loginPage.clearPasswordField();
        loginPage.clickLogin();

        String errorMessage = loginPage.getErrorMessage();
        CustomAssertions.assertErrorMessage(errorMessage, expectedErrorMessage);
        LoggerUtil.logInfo("UC-2: Username only test completed");
    }

    private void testValidCredentials(String username, String password, String expectedTitle) {
        LoggerUtil.logInfo("Testing UC-3: Valid credentials");
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        CustomAssertions.assertTextEquals(driver.getTitle(), expectedTitle);
        LoggerUtil.logInfo("UC-3: Valid credentials test completed");
    }

    @AfterEach
    public void tearDown() {
        try {
            DriverSingleton.quitDriver();
        } catch (Exception e) {
            LoggerUtil.logError("Error quitting driver", e);
        }
    }
}