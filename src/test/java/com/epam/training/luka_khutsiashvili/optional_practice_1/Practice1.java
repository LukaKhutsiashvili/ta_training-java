package com.epam.training.luka_khutsiashvili.optional_practice_1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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
            WebElement textArea = wait.until(ExpectedConditions.elementToBeClickable(By.id("postform-text")));
            textArea.clear();
            textArea.sendKeys(PASTE_TEXT);

            // Select expiration time
            WebElement expirationDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("select2-postform-expiration-container")));
            expirationDropdown.click();

            WebElement expirationOption = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//li[contains(@class, 'select2-results__option') and text()='" + EXPIRATION_TIME + "']")));
            expirationOption.click();

            // Input paste name
            WebElement pasteNameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("postform-name")));
            pasteNameField.clear();
            pasteNameField.sendKeys(PASTE_NAME);

            // Click the create button
            WebElement createButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Create New Paste']")));
            createButton.click();

        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
        } finally {
              driver.quit();
        }
    }
}
