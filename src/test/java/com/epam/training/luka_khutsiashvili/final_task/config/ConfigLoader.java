package com.epam.training.luka_khutsiashvili.final_task.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static final Properties properties = new Properties();
    private static ConfigLoader instance;

    private ConfigLoader() {
        String environment = System.getProperty("env", "dev");  // Default to "dev" environment
        String propertiesFile = String.format("config/application-%s.properties", environment);

        try (InputStream input = getClass().getClassLoader().getResourceAsStream(propertiesFile)) {
            if (input == null) {
                throw new IOException("Unable to find " + propertiesFile);
            }
            properties.load(input);
            System.out.println("Loaded properties for environment: " + environment);
        } catch (IOException e) {
            System.err.println("Error loading configuration: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static ConfigLoader getInstance() {
        if (instance == null) {
            instance = new ConfigLoader();
        }
        return instance;
    }

    public String getBrowser() {
        return System.getProperty("browser", properties.getProperty("browser", "firefox"));
    }

    public String getBaseUrl() {
        return properties.getProperty("base.url", "https://default.example.com");
    }

    public boolean isHeadless() {
        return Boolean.parseBoolean(properties.getProperty("browser.headless", "false"));
    }

    public boolean isStartMaximized() {
        return Boolean.parseBoolean(properties.getProperty("browser.startMaximized", "true"));
    }

    public int getBrowserWidth() {
        try {
            return Integer.parseInt(properties.getProperty("browser.width", "1920"));
        } catch (NumberFormatException e) {
            System.err.println("Invalid browser width; defaulting to 1920.");
            return 1920;
        }
    }

    public int getBrowserHeight() {
        try {
            return Integer.parseInt(properties.getProperty("browser.height", "1080"));
        } catch (NumberFormatException e) {
            System.err.println("Invalid browser height; defaulting to 1080.");
            return 1080;
        }
    }
}