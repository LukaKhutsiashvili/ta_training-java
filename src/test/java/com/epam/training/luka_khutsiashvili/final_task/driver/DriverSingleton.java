package com.epam.training.luka_khutsiashvili.final_task.driver;

import com.epam.training.luka_khutsiashvili.final_task.config.ConfigLoader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import org.openqa.selenium.edge.EdgeOptions;

public class DriverSingleton {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

        public static WebDriver getDriver() {
            if (driver.get() == null) {
                initializeDriver();
            }
            return driver.get();
        }

        private static void initializeDriver() {
            String browser = ConfigLoader.getInstance().getBrowser().toLowerCase();
            ConfigLoader config = ConfigLoader.getInstance();

            switch (browser) {
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    EdgeOptions edgeOptions = new EdgeOptions();
                    driver.set(new EdgeDriver(edgeOptions));
                    break;

                case "firefox":
                default:
                    WebDriverManager.firefoxdriver().setup();
                    driver.set(new FirefoxDriver());
                    break;
            }

            driver.get().manage().window().maximize();
        }

        public static void quitDriver() {
            if (driver.get() != null) {
                driver.get().quit();
                driver.remove();
            }
        }
    }