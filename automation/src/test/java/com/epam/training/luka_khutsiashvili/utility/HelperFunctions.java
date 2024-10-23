package com.epam.training.luka_khutsiashvili.utility;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelperFunctions {

    // Set input text using WebElement
    public static void setInputText(WebDriverWait wait, WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();  // Clear any existing text
        element.sendKeys(text); // Enter the new text
    }

    // Click an element using WebElement
    public static void clickElement(WebDriverWait wait, WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }
}
