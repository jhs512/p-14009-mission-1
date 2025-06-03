package com.back.view;

import com.back.domain.WiseSaying;
import java.util.List;

public class OutputView {

    public void printWiseSayingById(Long id) {
        System.out.println(id + "번 명언이 등록되었습니다.");
    }

    public void printDeleteWiseSayingById(Long id) {
        System.out.println(id + "번 명언이 삭제되었습니다.");
    }

    public void printWiseSayingDoesNotExist(Long id) {
        System.out.println(id + "번 명언은 존재하지 않습니다.");
    }

    public void printAllWiseSayings(List<WiseSaying> wiseSayings) {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        for (int i = wiseSayings.size() - 1; i >= 0; i--) {
            System.out.printf("%d / %s / %s%n",
                    wiseSayings.get(i).getId(), wiseSayings.get(i).getAuthor(), wiseSayings.get(i).getContent());
        }
    }

    public void printOldContent(WiseSaying wiseSaying) {
        System.out.print("명언(기존) : " + wiseSaying.getContent());
    }

    public void printOldAuthor(WiseSaying wiseSaying) {
        System.out.print("작가(기존) : " + wiseSaying.getAuthor());
    }
}
