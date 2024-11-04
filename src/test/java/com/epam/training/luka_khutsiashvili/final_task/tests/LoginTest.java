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
        // Initialize ConfigLoader and retrieve the base URL for the current environment
        ConfigLoader config = ConfigLoader.getInstance();
        baseUrl = config.getBaseUrl();

        // Set up WebDriver and navigate to the login page
        driver = DriverSingleton.getDriver();
        loginPage = new LoginPage(driver);
        driver.get(baseUrl);  // Use the environment-specific base URL
        LoggerUtil.logInfo("Navigated to the login page at: " + baseUrl);
    }

    @AfterEach
    public void tearDown() {
        DriverSingleton.quitDriver();
    }

    @ParameterizedTest
    @MethodSource("com.epam.training.luka_khutsiashvili.final_task.util.TestDataProvider#loginDataProvider")
    public void testLoginForm(String username, String password, String expectedMessageOrTitle) throws InterruptedException {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        if (username.isEmpty() || password.isEmpty()) {
            // UC-1 and UC-2: Expect error message
            String errorMessage = loginPage.getErrorMessage();
            LoggerUtil.logInfo("Checking error message for missing credentials.");
            CustomAssertions.assertErrorMessage(errorMessage, expectedMessageOrTitle);
        } else {
            // UC-3: Validate page title
            LoggerUtil.logInfo("Validating title for successful login.");
            CustomAssertions.assertTextEquals(driver.getTitle(), expectedMessageOrTitle);
        }
    }
}