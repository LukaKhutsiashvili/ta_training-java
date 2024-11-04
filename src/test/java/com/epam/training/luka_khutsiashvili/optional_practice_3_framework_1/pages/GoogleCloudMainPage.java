package com.epam.training.luka_khutsiashvili.optional_practice_3_framework_1.pages;

import com.epam.training.luka_khutsiashvili.optional_practice_3_framework_1.config.ConfigLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.epam.training.luka_khutsiashvili.helper_functions.HelperFunctions.clickElement;
import static com.epam.training.luka_khutsiashvili.helper_functions.HelperFunctions.setInputText;

public class GoogleCloudMainPage extends AbstractPage {
    private static final Logger logger = LoggerFactory.getLogger(GoogleCloudMainPage.class);
    private static final String CALCULATOR_SEARCH_TERM = "Google Cloud Platform Pricing Calculator";

    @FindBy(xpath = "//div[@class='ND91id LLv0lb' and @jsname='MVsrn']")
    private WebElement searchIcon;

    @FindBy(xpath = "//*[@id='i4']")
    private WebElement searchInputField;

    @FindBy(xpath = "//i[@aria-label='Search' and text()='send_spark']")
    private WebElement inputFieldSearchBtn;

    @FindBy(xpath = "//a[@class='K5hUy' and @track-name='google cloud pricing calculator']")
    private WebElement searchResult;

    @FindBy(xpath = "//span[@jsname='V67aGc' and @class='UywwFc-vQzf8d']")
    private WebElement addEstimateBtn;

    @FindBy(xpath = "//div[@role='button' and @data-service-form='8']")
    private WebElement computeEngineBtn;

    public GoogleCloudMainPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        super.openPage(ConfigLoader.getBaseUrl());
        logger.info("Opened Google Cloud Main Page: {}", ConfigLoader.getBaseUrl());
    }

    public void openComputeEngineForm() {
        searchForCalculator();
        clickElement(wait, searchResult);
        logger.info("Navigated to the Compute Engine form");
        clickElement(addEstimateBtn);
        clickElement(wait, computeEngineBtn);
    }

    private void searchForCalculator() {
        clickElement(searchIcon);
        setInputText(searchInputField, CALCULATOR_SEARCH_TERM);
        clickElement(inputFieldSearchBtn);
        logger.info("Searched for: {}", CALCULATOR_SEARCH_TERM);
    }
}