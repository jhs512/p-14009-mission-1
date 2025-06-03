package com.back.repository;

import com.back.domain.WiseSaying;
import java.util.List;

public class WiseSayingRepository {

    public WiseSaying getWiseSayingById(List<WiseSaying> wiseSayings, Long id) {
        return wiseSayings.stream()
                .filter(wiseSaying -> wiseSaying.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
