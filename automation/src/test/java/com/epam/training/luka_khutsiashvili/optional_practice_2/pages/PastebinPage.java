package com.epam.training.luka_khutsiashvili.optional_practice_2.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static com.epam.training.luka_khutsiashvili.utility.HelperFunctions.clickElement;
import static com.epam.training.luka_khutsiashvili.utility.HelperFunctions.setInputText;

import java.time.Duration;
import java.util.List;

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
    private By pastedCodeLocator = By.xpath("//div[@class='source bash']/ol"); // Update based on actual element
    private By syntaxVerificationLocator = By.xpath("//div[@class='left']/a[text()='Bash']");

    public PastebinPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openPage(String url) {
        driver.get(url);
    }

    public void createNewPaste(String code, String pasteName) {
        setInputText(wait,codeInputLocator, code);
        setSyntaxHighlighting();
        setExpiration();
        setInputText(wait,pasteNameLocator, pasteName);
        clickElement(wait,submitButtonLocator);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public boolean isSyntaxBash() {
        WebElement selectedSyntax = driver.findElement(syntaxVerificationLocator);
        return selectedSyntax.getText().equals("Bash");
    }

    public String getPastedCode() {
        // Wait until the <ol class="bash"> is visible
        List<WebElement> codeLines = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(pastedCodeLocator));

        // Extract text from each <li> element and concatenate it to form the full code
        StringBuilder pastedCode = new StringBuilder();
        for (WebElement line : codeLines) {
            pastedCode.append(line.getText()).append("\n"); // Add newline after each line
        }

        return pastedCode.toString().trim(); // Return the concatenated text
    }


    public void waitForTitleToContain(String expectedTitle) {
        wait.until(ExpectedConditions.titleContains(expectedTitle));
    }

    private void setSyntaxHighlighting() {
        clickElement(wait,syntaxHighlightingDropdown);
        clickElement(wait,bashOption);
    }

    private void setExpiration() {
        clickElement(wait,expirationDropdown);
        clickElement(wait,expirationOption);
    }
}
