package com.epam.training.luka_khutsiashvili.final_task.driver;

import com.epam.training.luka_khutsiashvili.final_task.config.ConfigLoader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.Dimension;

public class DriverSingleton {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final ConfigLoader config = ConfigLoader.getInstance();

    // Get the WebDriver instance for the current thread
    public static WebDriver getDriver() {
        if (driver.get() == null) {
            initializeDriver();
        }
        return driver.get();
    }

    // Initialize WebDriver based on the specified browser in the properties file
    private static void initializeDriver() {
        String browser = config.getBrowser().toLowerCase();

        switch (browser) {
            case "edge":
                setupEdgeDriver();
                break;
            case "firefox":
            default:
                setupFirefoxDriver();
                break;
        }

        // Apply window settings (maximize or set dimensions)
        configureWindow();
    }

    // Quit the WebDriver instance and clean up
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

    // Setup Firefox WebDriver
    private static void setupFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        if (config.isHeadless()) {
            options.addArguments("--headless");
            options.addArguments("--width=" + config.getBrowserWidth());
            options.addArguments("--height=" + config.getBrowserHeight());
        }
        driver.set(new FirefoxDriver(options));
    }

    // Setup Edge WebDriver
    private static void setupEdgeDriver() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        if (config.isHeadless()) {
            options.addArguments("--headless");
            options.addArguments("--window-size=" + config.getBrowserWidth() + "x" + config.getBrowserHeight());
        }
        options.addArguments("--disable-autofill"); // Disable autofill in Edge
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--inprivate"); // Run in private mode to avoid cached data
        driver.set(new EdgeDriver(options));
    }

    // Apply window settings based on the configuration
    private static void configureWindow() {
        if (config.isStartMaximized()) {
            driver.get().manage().window().maximize();
        } else {
            driver.get().manage().window().setSize(new Dimension(
                    config.getBrowserWidth(),
                    config.getBrowserHeight()
            ));
        }
    }
}