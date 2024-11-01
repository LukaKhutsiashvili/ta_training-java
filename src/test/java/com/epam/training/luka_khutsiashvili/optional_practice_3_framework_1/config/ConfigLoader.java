package com.epam.training.luka_khutsiashvili.optional_practice_3_framework_1.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static final Properties properties = new Properties();

    // Static initializer to load properties based on environment at startup
    static {
        String environment = System.getProperty("environment", "dev"); // Default to "dev" if not specified
        loadEnvironmentProperties(environment);
    }

    public static void loadEnvironmentProperties(String environment) {
        String fileName = "config/" + environment + ".properties";
        try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new IllegalArgumentException("Properties file not found for environment: " + fileName);
            }
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    // Method to retrieve timeout, with default fallback if not set
    public static int getTimeout() {
        return Integer.parseInt(properties.getProperty("TIMEOUT", "10")); // Default to 10 seconds if not specified
    }

    public static String getBaseUrl() {
        return properties.getProperty("BASE_URL");
    }
}
