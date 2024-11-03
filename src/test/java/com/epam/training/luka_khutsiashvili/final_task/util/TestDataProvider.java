package com.epam.training.luka_khutsiashvili.final_task.util;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class TestDataProvider {
    public static Stream<Arguments> loginDataProvider() {
        return Stream.of(
                Arguments.of("", "", "Username is required"),  // UC-1
                Arguments.of("user", "", "Password is required"),  // UC-2
                Arguments.of("standard_user", "secret_sauce", "Swag Labs")  // UC-3
        );
    }
}