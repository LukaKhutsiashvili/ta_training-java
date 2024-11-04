package com.epam.training.luka_khutsiashvili.final_task.utils;

import com.epam.training.luka_khutsiashvili.final_task.config.ConfigLoader;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserOptions {

    /**
     * Configures Firefox options based on environment settings.
     * - Headless mode: Enables headless mode if specified, with dynamic width and height.
     * - Start Maximized: Maximizes the window if not in headless mode.
     */
    public static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();

        if (ConfigLoader.getInstance().isHeadless()) {
            options.addArguments("--headless");
            options.addArguments("--width=" + ConfigLoader.getInstance().getBrowserWidth());
            options.addArguments("--height=" + ConfigLoader.getInstance().getBrowserHeight());
        } else if (ConfigLoader.getInstance().isStartMaximized()) {
            options.addArguments("--start-maximized");
        }

        return options;
    }

    /**
     * Configures Edge options based on environment settings.
     * - Headless mode: Enables headless mode if specified, with dynamic window size.
     * - Start Maximized: Maximizes the window if not in headless mode.
     */
    public static EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();

        if (ConfigLoader.getInstance().isHeadless()) {
            options.addArguments("--headless");
            options.addArguments("--window-size=" + ConfigLoader.getInstance().getBrowserWidth() + "," + ConfigLoader.getInstance().getBrowserHeight());
        } else if (ConfigLoader.getInstance().isStartMaximized()) {
            options.addArguments("--start-maximized");
        }

        return options;
    }
}