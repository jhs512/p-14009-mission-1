package com.back.utils;

import com.back.domain.WiseSaying;
import java.util.List;

public class Validator {

    public static boolean isValidCommand(String[] parseInput) {
        if (!isValidCommandName(parseInput[0])) {
            return false;
        }

        try {
            Long validId = Long.parseLong(parseInput[1]);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    public static boolean isValidCommandName(String input) {
        String[] command = input.split("=?");
        if (command[0].matches("^(등록|수정|삭제|목록|종료)$")) {
            return true;
        }
        return false;
    }

    public static boolean isValidIdLong(String[] parseInput) {
        if (parseInput == null || parseInput.length < 2) {
            return false;
        }
        try {
            Long.parseLong(parseInput[1]);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
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
