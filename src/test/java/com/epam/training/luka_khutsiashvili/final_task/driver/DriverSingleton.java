package com.epam.training.luka_khutsiashvili.final_task.driver;

import com.epam.training.luka_khutsiashvili.final_task.config.ConfigLoader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * Singleton class for managing WebDriver instances, ensuring only one driver instance per thread.
 */
public class DriverSingleton {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final ConfigLoader config = ConfigLoader.getInstance();

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            initializeDriver();
        }
        return driver.get();
    }

    /**
     * Initialize WebDriver based on the browser specified in the properties file.
     */
    private static void initializeDriver() {
        String browser = config.getBrowser().toLowerCase();

        switch (browser) {
            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = getEdgeOptions();
                driver.set(new EdgeDriver(edgeOptions));
                break;

            case "firefox":
            default:
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = getFirefoxOptions();
                driver.set(new FirefoxDriver(firefoxOptions));
                break;
        }

        // Apply window settings
        if (config.isStartMaximized()) {
            driver.get().manage().window().maximize();
        } else {
            driver.get().manage().window().setSize(new org.openqa.selenium.Dimension(
                    config.getBrowserWidth(), config.getBrowserHeight()));
        }
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

    /**
     * Configure Firefox browser options.
     */
    private static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        if (config.isHeadless()) {
            options.addArguments("--headless");
            options.addArguments("--width=" + config.getBrowserWidth());
            options.addArguments("--height=" + config.getBrowserHeight());
        }
        return options;
    }

    /**
     * Configure Edge browser options.
     */
    private static EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        if (config.isHeadless()) {
            options.addArguments("--headless");
            options.addArguments("--window-size=" + config.getBrowserWidth() + "x" + config.getBrowserHeight());
        }
        options.addArguments("--disable-autofill");
        return options;
    }
}