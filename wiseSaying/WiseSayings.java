package com.back.wiseSaying;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WiseSayings {
    long id = 1L;
    private Map<Long, String[]> wiseSayingMap;

    public WiseSayings() {
        wiseSayingMap = new HashMap<>();
    }

    public long addWiseSaying( String author, String content) {
        if (author.matches(".*[^a-zA-Z0-9가-힣\\s].*") || content.matches(".*[^a-zA-Z0-9가-힣\\s].*")) {
            return -1;
        }

        String[] wiseSaying = {author, content};
        wiseSayingMap.put(id, wiseSaying);

        return id++;
    }

    public void addWiseSaying(long id, String author, String content) {
        if (author.matches(".*[^a-zA-Z0-9가-힣\\s].*") || content.matches(".*[^a-zA-Z0-9가-힣\\s].*")) {
            return;
        }

        String[] wiseSaying = {author, content};
        wiseSayingMap.put(id, wiseSaying);
    }

    public long deleteWiseSaying(long id) {
        if (wiseSayingMap.containsKey(id)) {
            wiseSayingMap.remove(id);
            return id;
        } else {
            return -1;
        }
    }

    public void modifyWiseSaying(long id, Scanner scanner) {
        if (wiseSayingMap.containsKey(id)) {
            String[] value = wiseSayingMap.get(id);

            System.out.println("명언(기존) : " + value[1]);
            System.out.print("명언(수정) : ");
            String content = scanner.nextLine().trim();

            System.out.println("작가(기존) : " + value[0]);
            System.out.print("작가(수정) : ");
            String author = scanner.nextLine().trim();

            addWiseSaying(id, author, content);
        } else {
            System.out.println(id + "번 명언이 존재하지 않습니다.");
        }
    }

    public void showWiseSaying() {
        System.out.println("번호 / 작가 / 명언 ");
        System.out.println("----------------------");

        for (Map.Entry<Long, String[]> entry :
                wiseSayingMap.entrySet().stream()
                        .sorted((e1, e2) -> Long.compare(e2.getKey(), e1.getKey()))  // key 기준 역순 정렬
                        .toList()) {
            Long key = entry.getKey();
            String[] value = entry.getValue();
            System.out.printf("%d / %s / %s\n", key, value[0], value[1]);
        }
    }
}
