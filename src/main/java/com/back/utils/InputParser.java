package com.back.utils;

public class InputParser {

    public static String[] parseInput(String input) {
        if (input == null || input.trim().isEmpty()) {
            return null;
        }
        return input.split("=?");
    }
}
