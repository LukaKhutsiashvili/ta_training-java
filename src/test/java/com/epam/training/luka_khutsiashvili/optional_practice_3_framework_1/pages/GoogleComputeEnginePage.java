package com.epam.training.luka_khutsiashvili.optional_practice_3_framework_1.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.epam.training.luka_khutsiashvili.helper_functions.HelperFunctions.clickElement;
import static com.epam.training.luka_khutsiashvili.helper_functions.HelperFunctions.setInputText;

public class GoogleComputeEnginePage extends AbstractPage {

    @FindBy(xpath = "//*[@id='c22']")
    private WebElement instanceCountInput;

    @FindBy(xpath = "//div[@class='VfPpkd-TkwUic' and @aria-controls='c31']")
    private WebElement operatingSystemDropdown;

    @FindBy(xpath = "//li[@data-value='free-debian-centos-coreos-ubuntu-or-byol-bring-your-own-license']")
    private WebElement operatingSystemOptionFreeDebian;

    @FindBy(xpath = "//label[@class='zT2df' and @for='107regular']")
    private WebElement provisioningModelRegularOption;

    @FindBy(xpath = "//span[text()='Machine Family']/ancestor::div[@role='combobox']")
    private WebElement machineFamilyDropdown;

    @FindBy(xpath = "//li[@role='option' and @data-value='general-purpose']")
    private WebElement machineFamilyOptionGeneralPurpose;

    @FindBy(xpath = "//*[@class='O1htCb-H9tDt PPUDSe t8xIwc' and @jsname='vGGDlb']")
    private WebElement seriesDropdown;

    @FindBy(xpath = "//li[@role='option' and @data-value='n1']")
    private WebElement seriesOptionN1;

    @FindBy(xpath = "//div[@class='O1htCb-H9tDt PPUDSe t8xIwc' and @jsname='kgDJk']")
    private WebElement machineTypeDropdown;

    @FindBy(xpath = "//li[@role='option' and @data-value='n1-standard-8']")
    private WebElement machineTypeOptionN1Standard8;

    @FindBy(xpath = "//button[@aria-label='Add GPUs']")
    private WebElement addGPUButton;

    @FindBy(xpath = "//div[@class='MSEcuf' and @data-field-type='158']")
    private WebElement gpuTypeDropdown;

    @FindBy(xpath = "//li[@data-value='nvidia-tesla-v100']")
    private WebElement gpuTypeOptionNVIDIA_V100;

    @FindBy(xpath = "//div[@class='MSEcuf' and @data-field-type='174']")
    private WebElement gpuCountDropdown;

    @FindBy(xpath = "//li[@role='option' and @aria-selected='true' and @data-value='1']")
    private WebElement gpuCountOption1;

    @FindBy(xpath = "//div[@class='MSEcuf' and @data-field-type='180']")
    private WebElement localSSDDropdown;

    @FindBy(xpath = "//li[@role='option' and @data-value='2' and .//span[contains(text(), '2x375 GB')]]")
    private WebElement localSSDOption2x375Gb;

    @FindBy(xpath = "//div[@class='MSEcuf' and @data-field-type='115']")
    private WebElement regionDropdown;

    @FindBy(xpath = "//li[@role='option' and @data-value='europe-west4']")
    private WebElement regionOptionNetherlands;

    @FindBy(xpath = "//*[contains(text(),'Service cost updated')]")
    WebElement serviceCostUpdated;

    public GoogleComputeEnginePage(WebDriver driver) {
        super(driver);
    }

    public void fillOutForm(String instanceCount) {
        setInputText(wait, instanceCountInput, instanceCount);
        selectOperatingSystem();
        selectProvisioningModel();
        selectMachineFamily();
        selectSeries();
        selectMachineType();
        addGPU();
        selectLocalSSD();
        selectRegion();
    }

    private void selectOperatingSystem() {
        clickElement(wait, operatingSystemDropdown);
        clickElement(wait,operatingSystemOptionFreeDebian);
    }

    private void selectProvisioningModel() {
        clickElement(wait, provisioningModelRegularOption);
    }

    private void selectMachineFamily() {
        clickElement(wait,machineFamilyDropdown);
        clickElement(wait,machineFamilyOptionGeneralPurpose);
    }

    private void selectSeries() {
        clickElement(wait,seriesDropdown);
        clickElement(wait,seriesOptionN1);
    }

    private void selectMachineType() {
        clickElement(wait,machineTypeDropdown);
        clickElement(wait,machineTypeOptionN1Standard8);
    }

    private void addGPU() {
        clickElement(wait,addGPUButton);
        clickElement(wait,gpuTypeDropdown);
        clickElement(wait,gpuTypeOptionNVIDIA_V100);
        clickElement(wait,gpuCountDropdown);
        clickElement(wait,gpuCountOption1);
    }

    private void selectLocalSSD() {
        clickElement(wait,localSSDDropdown);
        clickElement(wait,localSSDOption2x375Gb);
    }

    private void selectRegion() {
        clickElement(wait,regionDropdown);
        clickElement(wait,regionOptionNetherlands);
        // Wait for the "Service cost updated" message to appear
        wait.until(ExpectedConditions.visibilityOf(serviceCostUpdated));
    }
}