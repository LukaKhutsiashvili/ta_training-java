package com.epam.training.luka_khutsiashvili.optional_practice_3_framework_1.util;

import com.epam.training.luka_khutsiashvili.optional_practice_3_framework_1.driver.DriverSingleton;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.openqa.selenium.WebDriver;

public class ScreenshotExtension implements TestExecutionExceptionHandler {

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        WebDriver driver = DriverSingleton.getDriver(); // Get the existing driver instance
        ScreenshotUtil.captureScreenshot(driver, context.getDisplayName()); // Capture screenshot on failure
        throw throwable; // Re-throw the exception to mark the test as failed
    }
}