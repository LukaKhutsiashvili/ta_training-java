package com.epam.training.luka_khutsiashvili.optional_practice_3.util;

import com.epam.training.luka_khutsiashvili.optional_practice_3.model.FieldType;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

public class ElementVerifier {

    private static final Duration TIMEOUT_DURATION = Duration.ofSeconds(10);


    public static void verifyElements(WebDriver driver, Map<FieldType, String> expectedValues) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_DURATION);
        SoftAssertions softAssertions = new SoftAssertions();

        expectedValues.forEach((fieldType, expectedValue) -> {
            try {
                WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(fieldType.getLocator()));
                String actualText = element.getText().trim();
                softAssertions.assertThat(actualText)
                        .as("Mismatch in field: " + fieldType.getFieldName())
                        .isEqualTo(expectedValue);
            } catch (Exception e) {
                System.out.println("Error finding field: " + fieldType.getFieldName());
                e.printStackTrace();
                softAssertions.fail("Field not found within timeout: " + fieldType.getFieldName());
            }
        });

        softAssertions.assertAll();
    }
}
