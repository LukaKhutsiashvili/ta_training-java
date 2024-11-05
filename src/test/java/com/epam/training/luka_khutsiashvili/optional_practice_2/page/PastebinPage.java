package com.epam.training.luka_khutsiashvili.optional_practice_2.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static com.epam.training.luka_khutsiashvili.helper_functions.HelperFunctions.clickElement;
import static com.epam.training.luka_khutsiashvili.helper_functions.HelperFunctions.setInputText;

public class PastebinPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Constants for default options
    private static final String SYNTAX_HIGHLIGHTING = "Bash";
    private static final String EXPIRATION_TIME = "10 Minutes";

    // Initialize locators using @FindBy annotation
    @FindBy(id = "postform-text")
    private WebElement codeInput;

    @FindBy(xpath = "//span[@id='select2-postform-format-container']")
    private WebElement syntaxHighlightingDropdown;

    @FindBy(xpath = "//span[@id='select2-postform-expiration-container']")
    private WebElement expirationDropdown;

    @FindBy(id = "postform-name")
    private WebElement pasteNameInput;

    @FindBy(xpath = "//button[text()='Create New Paste']")
    private WebElement submitButton;

    @FindBy(xpath = "//div[@class='source bash']/ol")
    private WebElement pastedCode;

    @FindBy(xpath = "//div[@class='left']/a[text()='Bash']")
    private WebElement syntaxVerification;

    // Constructor
    public PastebinPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Open page
    public void openPage(String url) {
        driver.get(url);
    }

    // Create new paste with flexibility for syntax and expiration
    public void createNewPaste(String code, String pasteName, String syntaxHighlighting, String expirationTime) {
        setInputText(wait, codeInput, code);
        selectOption(syntaxHighlightingDropdown, syntaxHighlighting);
        selectOption(expirationDropdown, expirationTime);
        setInputText(wait, pasteNameInput, pasteName);
        clickElement(wait, submitButton);
    }

    // Overloaded method for default values
    public void createNewPaste(String code, String pasteName) {
        createNewPaste(code, pasteName, SYNTAX_HIGHLIGHTING, EXPIRATION_TIME);
    }

    // Hide ads if present on the page
    private void hideAdIfPresent() {
        List<WebElement> ads = driver.findElements(By.className("display_ad_place"));
        if (!ads.isEmpty()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.visibility='hidden'", ads.get(0));
        }
    }

    // Helper for dropdown selection
    private void selectOption(WebElement dropdown, String optionText) {
        hideAdIfPresent(); // Ensure ads are hidden before interacting
        clickElement(wait, dropdown);
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//li[contains(@class, 'select2-results__option') and text()='" + optionText + "']")
        ));
        clickElement(wait, option);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public boolean isSyntaxBash() {
        return wait.until(ExpectedConditions.visibilityOf(syntaxVerification)).getText().equals(SYNTAX_HIGHLIGHTING);
    }

    public String getPastedCode() {
        wait.until(ExpectedConditions.visibilityOf(pastedCode));
        List<WebElement> codeLines = pastedCode.findElements(By.tagName("li"));

        StringBuilder pastedCodeText = new StringBuilder();
        for (WebElement line : codeLines) {
            pastedCodeText.append(line.getText()).append("\n");
        }
        return pastedCodeText.toString().trim();
    }

    public void waitForTitleToContain(String expectedTitle) {
        wait.until(ExpectedConditions.titleContains(expectedTitle));
    }
}