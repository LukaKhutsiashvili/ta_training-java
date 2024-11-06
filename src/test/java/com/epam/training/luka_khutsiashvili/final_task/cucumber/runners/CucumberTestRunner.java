package com.epam.training.luka_khutsiashvili.final_task.cucumber.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.epam.training.luka_khutsiashvili.final_task.cucumber.stepdefs",
        plugin = {"pretty", "json:target/cucumber.json"},
        publish = true
)
public class CucumberTestRunner {
}