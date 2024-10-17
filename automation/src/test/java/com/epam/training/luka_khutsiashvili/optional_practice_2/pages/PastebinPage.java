package com.epam.training.luka_khutsiashvili.optional_practice_2.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PastebinPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By codeInputLocator = By.id("postform-text");
    private By syntaxHighlightingDropdown = By.id("select2-postform-format-container");
    private By bashOption = By.xpath("//li[contains(@class, 'select2-results__option') and text()='Bash']");
    private By expirationDropdown = By.id("select2-postform-expiration-container");
    private By expirationOption = By.xpath("//li[contains(@class, 'select2-results__option') and text()='10 Minutes']");
    private By pasteNameLocator = By.id("postform-name");
    private By submitButtonLocator = By.xpath("//button[text()='Create New Paste']");
    private By syntaxVerificationLocator = By.xpath("//div[@class='left']/a[text()='Bash']");
    private By codeVerificationLocator = By.xpath("//textarea[@class='textarea']");

    public PastebinPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openPage(String url) {
        driver.get(url);
    }

    public void createNewPaste(String code, String pasteName) {
        setInputText(codeInputLocator, code);
        setSyntaxHighlighting();
        setExpiration();
        setInputText(pasteNameLocator, pasteName);
        clickElement(submitButtonLocator);
    }



    public String getPageTitle() {
        return driver.getTitle();
    }

    public boolean isSyntaxBash() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(syntaxVerificationLocator)) != null;
    }

    public String getPastedCode() {
        return driver.findElement(codeVerificationLocator).getText();
    }

    private void setInputText(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    private void clickElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    private void setSyntaxHighlighting() {
        clickElement(syntaxHighlightingDropdown);
        clickElement(bashOption);
    }

    private void setExpiration() {
        clickElement(expirationDropdown);
        clickElement(expirationOption);
    }
}
