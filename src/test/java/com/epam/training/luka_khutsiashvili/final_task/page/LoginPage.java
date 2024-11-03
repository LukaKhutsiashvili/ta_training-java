package com.epam.training.luka_khutsiashvili.final_task.page;

import com.epam.training.luka_khutsiashvili.final_task.util.LoggerUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//input[@id='user-name']")
    private WebElement usernameField;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@id='login-button']")
    private WebElement loginButton;

    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(usernameField)).sendKeys(username);
        LoggerUtil.logInfo("Entered username: " + username);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);
        LoggerUtil.logInfo("Entered password.");
    }

    public void clearFields() {
        wait.until(ExpectedConditions.visibilityOf(usernameField)).clear();
        wait.until(ExpectedConditions.visibilityOf(passwordField)).clear();
        LoggerUtil.logInfo("Cleared username and password fields.");
    }

    public void clearUsernameField() {
        wait.until(ExpectedConditions.visibilityOf(usernameField)).clear();
        LoggerUtil.logInfo("Cleared username field.");
    }

    public void clearPasswordField() {
        wait.until(ExpectedConditions.visibilityOf(passwordField)).clear();
        LoggerUtil.logInfo("Cleared password field.");
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        LoggerUtil.logInfo("Clicked login button.");
    }

    public String getErrorMessage() {
        String message = wait.until(ExpectedConditions.visibilityOf(errorMessage)).getText();
        LoggerUtil.logInfo("Captured error message: " + message);
        return message;
    }
}