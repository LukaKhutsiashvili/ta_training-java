package com.epam.training.luka_khutsiashvili.optional_practice_2.test;

import com.epam.training.luka_khutsiashvili.optional_practice_2.page.PastebinPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PastebinTest {
    private WebDriver driver;
    private PastebinPage pastebinPage;
    private static final String URL = "https://pastebin.com/";
    private static final String PASTE_TEXT =
            "git config --global user.name  \"New Sheriff in Town\"\n" +
                    "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
                    "git push origin master --force";
    private static final String PASTE_NAME = "how to gain dominance among developers";

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        pastebinPage = new PastebinPage(driver);
        pastebinPage.openPage(URL);
    }

    @Test
    public void testCreateNewPaste() {
        pastebinPage.createNewPaste(PASTE_TEXT, PASTE_NAME);

        // Wait for the title to contain the expected paste name
        pastebinPage.waitForTitleToContain(PASTE_NAME);

        // Verify page title contains the paste name
        String actualTitle = pastebinPage.getPageTitle();
        assertTrue(actualTitle.contains(PASTE_NAME),
                "Expected the page title to contain '" + PASTE_NAME + "', but it was '" + actualTitle + "'");

        // Verify syntax highlighting is Bash
        assertTrue(pastebinPage.isSyntaxBash(), "Syntax highlighting is not set to Bash.");

        String actualPastedCode = pastebinPage.getPastedCode();
        assertTrue(actualPastedCode.contains(PASTE_TEXT),
                "The pasted code does not contain the expected text. Actual pasted code: " + actualPastedCode);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
