package com.epam.training.luka_khutsiashvili.final_task.pages;

import com.epam.training.luka_khutsiashvili.final_task.utils.LoggerUtil;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage {

    @FindBy(xpath = "//input[@id='user-name']")
    private WebElement usernameField;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@id='login-button']")
    private WebElement loginButton;

    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        super(driver); // Call the constructor of AbstractPage
    }

    public void enterUsername(String username) {
        usernameField.sendKeys(username);
        LoggerUtil.logInfo("Entered username: " + username);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
        LoggerUtil.logInfo("Entered password.");
    }

    public void clearFields() {
        clearField(usernameField, "username");
        clearField(passwordField, "password");
    }

    public void clearPasswordField() {
        clearField(passwordField, "password");
    }

    public void clickLogin() {
        loginButton.click();
        LoggerUtil.logInfo("Clicked login button.");
    }

    public String getErrorMessage() {
        String message = errorMessage.getText();
        LoggerUtil.logInfo("Captured error message: " + message);
        return message;
    }

    private void clearField(WebElement element, String fieldName) {
        new Actions(driver)
                .click(element)
                .keyDown(Keys.CONTROL)
                .sendKeys("a")
                .keyUp(Keys.CONTROL)
                .sendKeys(Keys.BACK_SPACE)
                .perform();
        LoggerUtil.logInfo("Cleared " + fieldName + " field.");
    }
}