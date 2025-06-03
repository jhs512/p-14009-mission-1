package com.back.service;

import com.back.domain.WiseSaying;
import java.util.List;

public class WiseSayingService {

    public void addWiseSaying(List<WiseSaying> wiseSayings, WiseSaying wiseSaying) {
        wiseSayings.add(wiseSaying);
    }

    public void changeWiseSaying(WiseSaying oldWiseSaying, String newContent, String newAuthor) {
        oldWiseSaying.setContent(newContent);
        oldWiseSaying.setAuthor(newAuthor);
    }

    public void deleteWiseSaying(List<WiseSaying> wiseSayings, WiseSaying wiseSaying) {
        if (wiseSayings.contains(wiseSaying)) {
            wiseSayings.remove(wiseSaying);
        }
    }
}
