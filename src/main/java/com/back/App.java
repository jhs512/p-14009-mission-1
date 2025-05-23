package com.back;

import com.back.entity.WiseSaying;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {
    Scanner scanner = new Scanner(System.in);
    int idCount = 0;

    Map<Integer, WiseSaying> wiseSayingMap = new HashMap<>();

    void run() {
        reBoot();

        System.out.println("== 명언 앱 ==");

        label:
        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine().trim();

            switch (cmd) {
                case "종료":
                    break label;
                case "등록":
                    actionWrite();
                    break;
                case "목록":
                    actionList();
                    break;
                default:
                    if (cmd.startsWith("삭제?id=")) {
                        int num = Integer.parseInt(cmd.substring(6));
                        actionDelete(num);
                    } else if (cmd.startsWith("수정?id=")) {
                        int num = Integer.parseInt(cmd.substring(6));
                        actionModify(num);
                    }
                    break;
            }
        }

        scanner.close();
    }

    void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        readList();
    }

    private void readList() {
        for (int i = idCount; i >= 1; i--) {
            WiseSaying ws = wiseSayingMap.get(i);
            if (ws != null) {
                System.out.printf("%d / %s / %s\n", ws.id, ws.author, ws.content);
            }
        }
    }

    void reBoot(){

    }

    void actionWrite() {
        System.out.print("명언 : ");
        String wiseSayingContent = scanner.nextLine().trim();
        System.out.print("작가 : ");
        String wiseSayingAuthor = scanner.nextLine().trim();

        System.out.println(write(wiseSayingContent, wiseSayingAuthor).getId() + "번 명언이 등록되었습니다.");
    }

    WiseSaying write(String content, String author) {
        WiseSaying wiseSaying = new WiseSaying(++idCount, content, author);
        wiseSayingMap.put(idCount, wiseSaying);
        return wiseSaying;
    }

    void actionDelete(int index) {
        if (wiseSayingMap.containsKey(index)) {
            delete(index);
            System.out.printf("%d번 명언이 삭제되었습니다.%n", index);
        } else {
            System.out.printf("%d번 명언은 존재하지 않습니다.%n", index);
        }
    }

    private void delete(int index) {
        wiseSayingMap.remove(index);
    }

    void actionModify(int index) {
        if (wiseSayingMap.containsKey(index)) {
            System.out.printf("명언(기존) : %s%n", wiseSayingMap.get(index).getcontent());
            System.out.print("명언 : ");
            String content = scanner.nextLine().trim();
            System.out.printf("작가(기존) : %s%n", wiseSayingMap.get(index).getauthor());
            System.out.print("작가 : ");
            String author = scanner.nextLine().trim();

            modify(index, content, author);
        } else {
            System.out.printf("%d번 명언은 존재하지 않습니다.%n", index);
        }
    }

    private void modify(int index, String content, String author) {
        wiseSayingMap.get(index).setcontent(content);
        wiseSayingMap.get(index).setauthor(author);
    }

}