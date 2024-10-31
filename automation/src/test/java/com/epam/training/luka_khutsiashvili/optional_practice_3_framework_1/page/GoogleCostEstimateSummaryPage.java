package com.epam.training.luka_khutsiashvili.optional_practice_3_framework_1.page;

import com.epam.training.luka_khutsiashvili.optional_practice_3_framework_1.model.CostEstimate;
import com.epam.training.luka_khutsiashvili.optional_practice_3_framework_1.model.FieldType;
import com.epam.training.luka_khutsiashvili.optional_practice_3_framework_1.util.BrowserUtils;
import com.epam.training.luka_khutsiashvili.optional_practice_3_framework_1.util.ElementVerifier;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.EnumMap;
import java.util.Map;

import static com.epam.training.luka_khutsiashvili.helper_functions.HelperFunctions.clickElement;

public class GoogleCostEstimateSummaryPage extends AbstractPage {

    @FindBy(xpath = "//button[@aria-label='Open Share Estimate dialog']")
    private WebElement shareBtn;

    @FindBy(xpath = "//a[@track-name='open estimate summary']")
    private WebElement openEstimateSummaryLink;

    public GoogleCostEstimateSummaryPage(WebDriver driver) {
        super(driver);
    }

    public void openEstimateSummary() {
        BrowserUtils.scrollToElement(driver, shareBtn);
        clickElement(wait, shareBtn);
        clickElement(wait, openEstimateSummaryLink);
    }

    public void verifyEstimateSummary(CostEstimate expected) {
        ElementVerifier.verifyElements(driver, createExpectedValueMap(expected));
    }

    private Map<FieldType, String> createExpectedValueMap(CostEstimate expected) {
        Map<FieldType, String> expectedValues = new EnumMap<>(FieldType.class);
        expectedValues.put(FieldType.NUMBER_OF_INSTANCES, expected.getNumOfInstance());
        expectedValues.put(FieldType.OPERATING_SYSTEM, expected.getOperatingSystem());
        expectedValues.put(FieldType.PROVISIONING_MODEL, expected.getProvisioningModel());
        expectedValues.put(FieldType.MACHINE_TYPE, expected.getMachineType());
        expectedValues.put(FieldType.GPU_MODEL, expected.getGpuModel());
        expectedValues.put(FieldType.NUMBER_OF_GPUS, expected.getGpuCount());
        expectedValues.put(FieldType.LOCAL_SSD, expected.getLocalSSD());
        expectedValues.put(FieldType.REGION, expected.getRegion());
        return expectedValues;
    }
}