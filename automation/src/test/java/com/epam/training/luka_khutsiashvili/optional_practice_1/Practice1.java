package com.epam.training.luka_khutsiashvili.optional_practice_1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static com.epam.training.luka_khutsiashvili.utility.HelperFunctions.clickElement;
import static com.epam.training.luka_khutsiashvili.utility.HelperFunctions.setInputText;


public class Practice1 {

    private static final String URL = "https://pastebin.com/";
    private static final String PASTE_TEXT = "Hello from WebDriver";
    private static final String PASTE_NAME = "helloweb";
    private static final String EXPIRATION_TIME = "10 Minutes";

    public static void main(String[] args) {
        // Use WebDriverManager to set up ChromeDriver
        WebDriverManager.chromedriver().setup();

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Navigate to the webpage
            driver.get(URL);

            // Input text into the main text area
            setInputText(wait, By.id("postform-text"), PASTE_TEXT);

            // Select expiration time
            clickElement(wait, By.id("select2-postform-expiration-container"));
            clickElement(wait, By.xpath("//li[contains(@class, 'select2-results__option') and text()='" + EXPIRATION_TIME + "']"));

            // Input paste name
            setInputText(wait, By.id("postform-name"), PASTE_NAME);

            // Click the create button
            clickElement(wait, By.xpath("//button[text()='Create New Paste']"));

        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
        } finally {
            // Quit the WebDriver instance
            driver.quit();
        }
    }
}
