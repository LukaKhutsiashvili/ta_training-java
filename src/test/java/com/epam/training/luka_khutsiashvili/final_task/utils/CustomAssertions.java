package com.epam.training.luka_khutsiashvili.final_task.utils;

import org.hamcrest.MatcherAssert;
import static org.hamcrest.Matchers.*;

public class CustomAssertions {

    public static void assertTextEquals(String actual, String expected) {
        MatcherAssert.assertThat("Text does not match!", actual, is(equalTo(expected)));
    }

    public static void assertElementDisplayed(boolean isDisplayed) {
        MatcherAssert.assertThat("Element should be displayed", isDisplayed, is(true));
    }

    public static void assertErrorMessage(String actualMessage, String expectedMessage) {
        MatcherAssert.assertThat("Error message mismatch!", actualMessage, containsString(expectedMessage));
    }

    public static void assertCollectionSize(int actualSize, int expectedSize) {
        MatcherAssert.assertThat("Collection size does not match!", actualSize, is(equalTo(expectedSize)));
    }

    public static void assertContainsSubstring(String actual, String expectedSubstring) {
        MatcherAssert.assertThat("String does not contain expected substring!", actual, containsString(expectedSubstring));
    }
}