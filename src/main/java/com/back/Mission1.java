package com.back;

import java.util.Scanner;

public class Mission1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("== 명언 앱 ==");

        int lastId = 0;
        WiseSaying[] wiseSayings = new WiseSaying[100];
        int wiseSayingLastIdx = -1;

        while (true) {
            System.out.print("명령) ");
            String command = scanner.nextLine().trim();

            if (command.equals("종료")) {
                break;

            } else if (command.equals("등록")) {
                System.out.print("명언 : ");
                String content = scanner.nextLine().trim();

                System.out.print("작가 : ");
                String author = scanner.nextLine().trim();

                int id = ++lastId;
                WiseSaying wiseSaying = new WiseSaying();
                wiseSaying.id = id;
                wiseSaying.content = content;
                wiseSaying.author = author;

                wiseSayings[++wiseSayingLastIdx] = wiseSaying;

                System.out.println(id + "번 명언이 등록되었습니다.");

            } else if (command.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");
                for (int i = wiseSayingLastIdx; i >= 0; i--) {
                    WiseSaying ws = wiseSayings[i];
                    if (ws != null) {
                        System.out.println(ws.id + " / " + ws.author + " / " + ws.content);
                    }
                }

            } else if (command.startsWith("삭제?id=")) {
                int id = Integer.parseInt(command.split("=")[1]);
                boolean found = false;

                for (int i = 0; i <= wiseSayingLastIdx; i++) {
                    if (wiseSayings[i] != null && wiseSayings[i].id == id) {
                        wiseSayings[i] = null;
                        System.out.println(id + "번 명언이 삭제되었습니다.");
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println(id + "번 명언은 존재하지 않습니다.");
                }

            } else if (command.startsWith("수정?id=")) {
                int id = Integer.parseInt(command.split("=")[1]);
                WiseSaying target = null;

                for (int i = 0; i <= wiseSayingLastIdx; i++) {
                    if (wiseSayings[i] != null && wiseSayings[i].id == id) {
                        target = wiseSayings[i];
                        break;
                    }
                }

                if (target == null) {
                    System.out.println(id + "번 명언은 존재하지 않습니다.");
                } else {
                    System.out.println("명언(기존) : " + target.content);
                    System.out.print("명언 : ");
                    String newContent = scanner.nextLine().trim();

                    System.out.println("작가(기존) : " + target.author);
                    System.out.print("작가 : ");
                    String newAuthor = scanner.nextLine().trim();

                    target.content = newContent;
                    target.author = newAuthor;
                }

            } else {
                System.out.println("지원하지 않는 명령입니다.");
            }
        }

        scanner.close();
    }
}
