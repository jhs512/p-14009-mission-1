package com.back.utils;

import com.back.domain.WiseSaying;
import java.util.List;

public class Validator {

    public static boolean isValidIdLong(String[] parseInput) {
        if (parseInput == null || parseInput.length < 2) {
            return false;
        }
        try {
            Long.parseLong(parseInput[1]);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidIdInWiseSayings(String[] parseInput, List<WiseSaying> wiseSayings) {
        if (isValidIdLong(parseInput)) {
            Long id = Long.parseLong(parseInput[1]);
            return wiseSayings.stream()
                    .anyMatch(wiseSaying -> wiseSaying.getId().equals(id));
        }
        return false;
    }

}
