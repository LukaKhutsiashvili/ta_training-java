package com.epam.training.luka_khutsiashvili.optional_practice_3_framework_1.model;

import org.openqa.selenium.By;

public enum FieldType {
    NUMBER_OF_INSTANCES(By.xpath("//span[text()='Number of Instances']/following-sibling::span[@class='Kfvdz']"), "Number of Instances"),
    OPERATING_SYSTEM(By.xpath("//span[text()='Operating System / Software']/following-sibling::span"), "Operating System"),
    PROVISIONING_MODEL(By.xpath("//span[text()='Provisioning Model']/following-sibling::span[@class='Kfvdz']"), "Provisioning Model"),
    MACHINE_TYPE(By.xpath("//span[text()='Machine type']/following-sibling::span"), "Machine Type"),
    GPU_MODEL(By.xpath("//span[text()='GPU Model']/following-sibling::span[@class='Kfvdz']"), "GPU Model"),
    NUMBER_OF_GPUS(By.xpath("//span[text()='Number of GPUs']/following-sibling::span[@class='Kfvdz']"), "Number of GPUs"),
    LOCAL_SSD(By.xpath("//span[text()='Local SSD']/following-sibling::span"), "Local SSD"),
    REGION(By.xpath("//span[text()='Region']/following-sibling::span"), "Region");

    private final By locator;
    private final String fieldName;

    FieldType(By locator, String fieldName) {
        this.locator = locator;
        this.fieldName = fieldName;
    }

    public By getLocator() {
        return locator;
    }

    public String getFieldName() {
        return fieldName;
    }
}
