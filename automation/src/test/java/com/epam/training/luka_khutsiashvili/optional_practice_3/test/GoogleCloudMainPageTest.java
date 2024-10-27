package com.epam.training.luka_khutsiashvili.optional_practice_3.test;

import com.epam.training.luka_khutsiashvili.optional_practice_3.model.CostEstimate;
import com.epam.training.luka_khutsiashvili.optional_practice_3.page.GoogleCloudMainPage;
import com.epam.training.luka_khutsiashvili.optional_practice_3.page.GoogleComputeEnginePage;
import com.epam.training.luka_khutsiashvili.optional_practice_3.page.GoogleCostEstimateSummaryPage;
import com.epam.training.luka_khutsiashvili.optional_practice_3.util.TestSetup;
import com.epam.training.luka_khutsiashvili.optional_practice_3.util.BrowserUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class GoogleCloudMainPageTest {
    private WebDriver driver;
    private GoogleCloudMainPage googleCloudMainPage;
    private GoogleComputeEnginePage googleComputeEnginePage;
    private GoogleCostEstimateSummaryPage googleCostEstimateSummaryPage;
    private TestSetup testSetup;

    @BeforeEach
    public void setUp() {
        testSetup = new TestSetup();
        driver = testSetup.initializeDriver();
        googleCloudMainPage = new GoogleCloudMainPage(driver);
        googleCloudMainPage.openPage();
    }

    @Test
    public void testCreateNewCostEstimate() {
        googleCloudMainPage.openComputeEngineForm();
        googleComputeEnginePage = new GoogleComputeEnginePage(driver);
        googleComputeEnginePage.fillOutForm("4");

        googleCostEstimateSummaryPage = new GoogleCostEstimateSummaryPage(driver);
        googleCostEstimateSummaryPage.openEstimateSummary();

        String originalWindow = driver.getWindowHandle();
        BrowserUtils.switchToNewWindow(driver, originalWindow);

        CostEstimate expectedEstimate = new CostEstimate("4",
                "Free: Debian, CentOS, CoreOS, Ubuntu or BYOL (Bring Your Own License)",
                "Regular",
                "n1-standard-8, vCPUs: 8, RAM: 30 GB",
                "NVIDIA V100",
                "1",
                "2x375 GB",
                "Netherlands (europe-west4)");

        googleCostEstimateSummaryPage.verifyEstimateSummary(expectedEstimate);
    }

    @AfterEach
    public void tearDown() {
        testSetup.tearDownDriver(driver);
    }
}