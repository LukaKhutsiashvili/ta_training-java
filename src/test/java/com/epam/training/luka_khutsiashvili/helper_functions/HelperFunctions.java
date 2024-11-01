package com.epam.training.luka_khutsiashvili.helper_functions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelperFunctions {

    // Method with WebDriverWait for setting input text
    public static void setInputText(WebDriverWait wait, WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
    }

    // Method without WebDriverWait for setting input text
    public static void setInputText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    // Method with WebDriverWait for clicking element
    public static void clickElement(WebDriverWait wait, WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    // Method without WebDriverWait for clicking element
    public static void clickElement(WebElement element) {
        element.click();
    }
}
