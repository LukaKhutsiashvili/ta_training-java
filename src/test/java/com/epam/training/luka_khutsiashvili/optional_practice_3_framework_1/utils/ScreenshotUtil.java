package com.epam.training.luka_khutsiashvili.optional_practice_3_framework_1.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;

public class ScreenshotUtil {

    private static final String SCREENSHOT_PATH = "screenshots/";

    public static void captureScreenshot(WebDriver driver, String testName) {
        try {
            // Ensure the screenshots directory exists
            Files.createDirectories(Paths.get(SCREENSHOT_PATH));

            // Capture the screenshot and save it with a timestamp
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File destFile = new File(SCREENSHOT_PATH + testName + "_" + timestamp + ".png");

            FileUtils.copyFile(srcFile, destFile);
            System.out.println("Screenshot saved at: " + destFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}