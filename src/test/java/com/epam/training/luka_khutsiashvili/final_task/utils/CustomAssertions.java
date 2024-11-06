package com.epam.training.luka_khutsiashvili.final_task.utils;

import org.hamcrest.MatcherAssert;
import static org.hamcrest.Matchers.*;

public class CustomAssertions {

    // Asserts that the actual text exactly matches the expected text
    public static void assertTextEquals(String actual, String expected) {
        MatcherAssert.assertThat("Text does not match!", actual, is(equalTo(expected)));
    }

    // Asserts that the actual error message contains the expected part
    public static void assertErrorMessage(String actualMessage, String expectedMessagePart) {
        MatcherAssert.assertThat("Error message mismatch!", actualMessage, containsString(expectedMessagePart));
    }
}