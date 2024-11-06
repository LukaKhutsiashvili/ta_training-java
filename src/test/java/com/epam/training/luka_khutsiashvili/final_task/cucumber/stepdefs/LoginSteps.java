package com.epam.training.luka_khutsiashvili.final_task.cucumber.stepdefs;

import com.epam.training.luka_khutsiashvili.final_task.config.ConfigLoader;
import com.epam.training.luka_khutsiashvili.final_task.driver.DriverSingleton;
import com.epam.training.luka_khutsiashvili.final_task.pages.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginSteps {
    private WebDriver driver;
    private LoginPage loginPage;
    private WebDriverWait wait;

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        driver = DriverSingleton.getDriver();
        loginPage = new LoginPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(ConfigLoader.getInstance().getBaseUrl());
    }

    @When("I type any credentials in the {string} field")
    public void i_type_any_credentials_in_the_field(String field) {
        if ("Username".equalsIgnoreCase(field)) {
            loginPage.enterUsername("temp_username");
        } else if ("Password".equalsIgnoreCase(field)) {
            loginPage.enterPassword("temp_password");
        }
    }

    @When("I type {string} in the {string} field")
    public void i_type_in_the_field(String value, String field) {
        if ("Username".equalsIgnoreCase(field)) {
            loginPage.enterUsername(value);
        } else if ("Password".equalsIgnoreCase(field)) {
            loginPage.enterPassword(value);
        }
    }

    @When("I enter a password")
    public void i_enter_a_password() {
        loginPage.enterPassword("temp_password");
    }

    @When("I enter {string} as the password")
    public void i_enter_as_the_password(String password) {
        loginPage.enterPassword(password);
    }

    @When("I clear the {string} and {string} fields")
    public void i_clear_fields(String field1, String field2) {
        if ("Username".equalsIgnoreCase(field1) && "Password".equalsIgnoreCase(field2)) {
            loginPage.clearFields();
        }
    }

    @When("I clear the {string} input")
    public void i_clear_input(String field) {
        if ("Password".equalsIgnoreCase(field)) {
            loginPage.clearPasswordField();
        }
    }

    @When("I click the {string} button")
    public void i_click_the_button(String button) {
        if ("Login".equalsIgnoreCase(button)) {
            loginPage.clickLogin();
        }
    }

    @Then("I should see an error message {string}")
    public void i_should_see_an_error_message(String expectedMessage) {
        String actualMessage = loginPage.getErrorMessage();
        String cleanedMessage = actualMessage.replace("Epic sadface: ", "").trim();
        Assertions.assertEquals(expectedMessage, cleanedMessage, "Error message mismatch!");
    }

    @Then("I should see the page title {string} in the dashboard")
    public void i_should_see_the_page_title_in_the_dashboard(String expectedTitle) {
        // Wait for the page title to load after login
        wait.until(ExpectedConditions.titleIs(expectedTitle));
        Assertions.assertEquals(expectedTitle, driver.getTitle(), "Page title mismatch!");
    }

    @After
    public void tearDown() {
        DriverSingleton.quitDriver(); // Ensure browser closes after each scenario
    }
}