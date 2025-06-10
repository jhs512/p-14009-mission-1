package com.back;

import com.back.WiseSaying;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class App {
    private final Scanner scanner = new Scanner(System.in);
    private int lastId = 0;
    private final List<WiseSaying> wiseSayings = new ArrayList<>();
    private int wiseSayingsLastIndex = -1;


    // 진입점 시작
    public void run() {
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine().trim();
            Rq rq = new Rq(cmd);

            // 모던 switch문
            switch (rq.getActionName()) {
                case "종료" -> {
                    System.out.println("프로그램이 종료합니다.");
                    return;
                }
                case "목록" -> actionList();
                case "등록" -> actionWrite();
                case "삭제" -> actionDelete(rq);
                case "수정" -> actionModify(rq);
            }
        }
    }
    // 진입점 끝


    // 내부 로직 처리 후 '출력'을 담당하는 action- 메서드들
    // 등록 시 출력 담당
    private void actionWrite() {
        System.out.print("명언 : ");
        String content = scanner.nextLine().trim();
        System.out.print("작가 : ");
        String author = scanner.nextLine().trim();

        WiseSaying wiseSaying2 = write(content, author);

        System.out.println("%d번 명언이 등록되었습니다.".formatted(wiseSaying2.getId()));
    }

    // 목록 시 출력 담당
    private void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        List<WiseSaying> forListWiseSayings = findForList();

        for (WiseSaying wiseSaying : forListWiseSayings) {
            System.out.printf("%d / %s / %s\n", wiseSaying.getId(), wiseSaying.getAuthor(), wiseSaying.getContent());
        }
    }

    // 삭제 시 출력 담당
    private void actionDelete(Rq rq) {
        int id = rq.getParamAsInt("id",-1);

        if (id == -1) {
            System.out.println("id를 숫자로 입력해주세요.");
            return;
        }

        WiseSaying deletedIndex = delete(id);

        if (deletedIndex == null) {
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
            return;
        }

        System.out.println("%d번 명언이 삭제되었습니다.".formatted(id));
    }

    // 수정 시 출력 담당
    private void actionModify(Rq rq) {
        int id = rq.getParamAsInt("id", -1);

        if (id == -1) {
            System.out.println("id를 숫자로 입력해주세요.");
            return;
        }

        WiseSaying wiseSaying = findById(id);

        // 수정할 번허의 명언이 없을 때 예외처리
        if (wiseSaying == null) {
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
            return;
        }

        // 수정된 명언,작가 입력받기
        System.out.printf("명언(기존) : %s\n", wiseSaying.getContent());
        System.out.print("명언 : ");
        String content = scanner.nextLine().trim();

        System.out.printf("작가(기존) : %s\n", wiseSaying.getAuthor());
        System.out.print("작가 : ");
        String author = scanner.nextLine().trim();

        // 수정된 내용으로 값 대입
        modify(wiseSaying, content, author);
    }


    // 내부 로직 메서드들
    // 등록 로직
    private WiseSaying write(String content, String author) {
        WiseSaying wiseSaying = new WiseSaying(++lastId, author, content);

        wiseSayings.add(wiseSaying);

        return wiseSaying;
    }

    // 목록 로직
    private int getSize() {
        return wiseSayingsLastIndex + 1;
    }

    private List<WiseSaying> findForList() {
        return wiseSayings.reversed();
    }

    // 삭제 로직
    private WiseSaying delete(int id) {
        WiseSaying deleteIndex = findById(id);

        // 명언 없을 때
        if (deleteIndex == null) return deleteIndex;

        wiseSayings.remove(deleteIndex);

        return deleteIndex;
    }

    // 사용자가 삭제&수정하고자 하는 번호가 리스트의 몇 번째 인덱스인지 찾는 메서드
    private WiseSaying findById(int id) {
        return wiseSayings
                .stream()
                .filter(wiseSaying -> wiseSaying.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // 수정 로직
    private void modify(WiseSaying wiseSaying, String content, String author) {
        wiseSaying.setContent(content);
        wiseSaying.setAuthor(author);
    }
}
