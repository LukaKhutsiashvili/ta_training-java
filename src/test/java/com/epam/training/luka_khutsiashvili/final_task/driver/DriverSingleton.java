package com.epam.training.luka_khutsiashvili.final_task.driver;

import com.epam.training.luka_khutsiashvili.final_task.config.ConfigLoader;
import com.epam.training.luka_khutsiashvili.final_task.util.BrowserOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class DriverSingleton {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverSingleton() {}

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            initializeDriver();
        }
        return driver.get();
    }

    private static void initializeDriver() {
        String browser = ConfigLoader.getInstance().getBrowser();

        // Initialize WebDriver based on the specified browser
        switch (browser.toLowerCase()) {
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver.set(new EdgeDriver(BrowserOptions.getEdgeOptions()));
                break;
            case "firefox":
            default:
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver(BrowserOptions.getFirefoxOptions()));
                break;
        }

        // Set timeouts to prevent indefinite waiting on page loads and scripts
        driver.get().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));  // Page load timeout

        // Maximize window if not in headless mode
        if (!ConfigLoader.getInstance().isHeadless()) {
            driver.get().manage().window().maximize();
        }

        // Add a shutdown hook to ensure WebDriver closes if the JVM exits unexpectedly
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                quitDriver();
            } catch (Exception e) {
                System.err.println("Failed to close WebDriver during JVM shutdown: " + e.getMessage());
            }
        }));
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}