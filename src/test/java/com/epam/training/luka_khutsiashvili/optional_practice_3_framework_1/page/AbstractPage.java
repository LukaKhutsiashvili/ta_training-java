package com.epam.training.luka_khutsiashvili.optional_practice_3_framework_1.page;

import com.epam.training.luka_khutsiashvili.optional_practice_3_framework_1.config.ConfigLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbstractPage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigLoader.getTimeout()));
        PageFactory.initElements(driver, this);
    }

    protected void openPage(String url) {
        driver.get(url);
    }
}