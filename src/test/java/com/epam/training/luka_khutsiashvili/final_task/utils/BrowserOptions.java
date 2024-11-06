package com.epam.training.luka_khutsiashvili.final_task.utils;

import com.epam.training.luka_khutsiashvili.final_task.config.ConfigLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserOptions {

    private static final ConfigLoader config = ConfigLoader.getInstance();

    public static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();

        if (config.isHeadless()) {
            options.addArguments("--headless");
            options.addArguments("--width=" + config.getBrowserWidth());
            options.addArguments("--height=" + config.getBrowserHeight());
        } else if (config.isStartMaximized()) {
            options.addArguments("--start-maximized");
        }

        return options;
    }

    public static EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();

        if (config.isHeadless()) {
            options.addArguments("--headless");
            options.addArguments("window-size=" + config.getBrowserWidth() + "x" + config.getBrowserHeight());
        } else if (config.isStartMaximized()) {
            options.addArguments("--start-maximized");
        }
        return options;
    }

    public static WebDriver getEdgeDriver() {
        return new EdgeDriver(getEdgeOptions());
    }
}