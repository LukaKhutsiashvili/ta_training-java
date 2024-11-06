package com.epam.training.luka_khutsiashvili.final_task.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static final Properties properties = new Properties();
    private static ConfigLoader instance;

    private ConfigLoader() {
        String environment = System.getProperty("environment", "dev");  // Default to "dev" if not specified
        String propertiesFile = String.format("config/application-%s.properties", environment);

        try (InputStream input = getClass().getClassLoader().getResourceAsStream(propertiesFile)) {
            if (input == null) {
                throw new IOException("Configuration file not found: " + propertiesFile);
            }
            properties.load(input);
            System.out.println("Loaded environment: " + environment);
        } catch (IOException e) {
            System.err.println("Error loading configuration: " + e.getMessage());
        }
    }

    public static ConfigLoader getInstance() {
        if (instance == null) {
            instance = new ConfigLoader();
        }
        return instance;
    }

    public String getProperty(String key, String defaultValue) {
        return System.getProperty(key, properties.getProperty(key, defaultValue));
    }

    public String getBrowser() {
        // Load the browser type from properties, defaulting to "firefox" if not specified
        return properties.getProperty("browser", "firefox");
    }

    public String getBaseUrl() {
        return getProperty("base.url", "https://default.example.com");
    }

    public boolean isHeadless() {
        return Boolean.parseBoolean(getProperty("browser.headless", "false"));
    }

    public boolean isStartMaximized() {
        return Boolean.parseBoolean(getProperty("browser.startMaximized", "true"));
    }

    public int getBrowserWidth() {
        return Integer.parseInt(getProperty("browser.width", "1920"));
    }

    public int getBrowserHeight() {
        return Integer.parseInt(getProperty("browser.height", "1080"));
    }
}