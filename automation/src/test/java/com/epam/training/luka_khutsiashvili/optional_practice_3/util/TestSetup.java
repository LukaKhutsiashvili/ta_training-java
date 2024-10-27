package com.epam.training.luka_khutsiashvili.optional_practice_3.util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestSetup {
    public WebDriver initializeDriver() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    public void tearDownDriver(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }
}
