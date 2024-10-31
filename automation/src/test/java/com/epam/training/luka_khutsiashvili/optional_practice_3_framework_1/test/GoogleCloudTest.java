package com.epam.training.luka_khutsiashvili.optional_practice_3_framework_1.test;

import com.epam.training.luka_khutsiashvili.optional_practice_3_framework_1.config.ConfigLoader;
import com.epam.training.luka_khutsiashvili.optional_practice_3_framework_1.driver.DriverSingleton;
import com.epam.training.luka_khutsiashvili.optional_practice_3_framework_1.model.CostEstimate;
import com.epam.training.luka_khutsiashvili.optional_practice_3_framework_1.page.GoogleCloudMainPage;
import com.epam.training.luka_khutsiashvili.optional_practice_3_framework_1.page.GoogleComputeEnginePage;
import com.epam.training.luka_khutsiashvili.optional_practice_3_framework_1.page.GoogleCostEstimateSummaryPage;
import com.epam.training.luka_khutsiashvili.optional_practice_3_framework_1.util.BrowserUtils;
import com.epam.training.luka_khutsiashvili.optional_practice_3_framework_1.util.ScreenshotExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExtendWith(ScreenshotExtension.class)
public class GoogleCloudTest {
    private static final Logger logger = LoggerFactory.getLogger(GoogleCloudTest.class);

    private WebDriver driver;
    private GoogleCloudMainPage googleCloudMainPage;
    private GoogleComputeEnginePage googleComputeEnginePage;
    private GoogleCostEstimateSummaryPage googleCostEstimateSummaryPage;

    @BeforeEach
    public void setUp() {
        // Load the environment-specific properties before setting up WebDriver
        String environment = System.getProperty("environment", "dev"); // Defaults to "dev" if not specified
        ConfigLoader.loadEnvironmentProperties(environment);
        logger.info("Loaded environment properties for: {}", environment);

        // Initialize WebDriver using the browser specified in the environment properties
        String browser = ConfigLoader.getProperty("BROWSER");
        driver = DriverSingleton.getDriver(browser);
        logger.info("Initialized WebDriver for browser: {}", browser);

        // Initialize page objects
        googleCloudMainPage = new GoogleCloudMainPage(driver);
        googleCloudMainPage.openPage();
        logger.info("Opened Google Cloud Main Page");
    }

    @Test
    public void testCreateNewCostEstimate() {
        googleCloudMainPage.openComputeEngineForm();
        googleComputeEnginePage = new GoogleComputeEnginePage(driver);
        googleComputeEnginePage.fillOutForm("4");
        logger.info("Filled out the form with instance count: 4");

        googleCostEstimateSummaryPage = new GoogleCostEstimateSummaryPage(driver);
        googleCostEstimateSummaryPage.openEstimateSummary();
        logger.info("Opened estimate summary");

        String originalWindow = driver.getWindowHandle();
        BrowserUtils.switchToNewWindow(driver, originalWindow);
        logger.info("Switched to new window");

        CostEstimate expectedEstimate = new CostEstimate("4",
                "Free: Debian, CentOS, CoreOS, Ubuntu or BYOL (Bring Your Own License)",
                "Regular",
                "n1-standard-8, vCPUs: 8, RAM: 30 GB",
                "NVIDIA V100",
                "1",
                "2x375 GB",
                "Netherlands (europe-west4)");

        googleCostEstimateSummaryPage.verifyEstimateSummary(expectedEstimate);
        logger.info("Verified the cost estimate summary successfully");
    }

    @AfterEach
    public void tearDown() {
        DriverSingleton.closeDriver();
        logger.info("Closed the WebDriver session");
    }
}