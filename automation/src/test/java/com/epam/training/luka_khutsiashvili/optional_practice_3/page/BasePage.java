package com.epam.training.luka_khutsiashvili.optional_practice_3.page;

import com.epam.training.luka_khutsiashvili.optional_practice_3.config.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Config.DEFAULT_TIMEOUT));
        PageFactory.initElements(driver, this);
    }

    protected void openPage(String url) {
        driver.get(url);
    }
}
