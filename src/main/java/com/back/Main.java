package com.back;

import java.util.Scanner;

public class Main {

    Scanner scanner;

    public void run() {
        System.out.println("== 명언 앱 ==");

        int indexCounts = 3;
        String[] wiseSayingContent = new String[indexCounts];
        String[] wiseSayingAuthor = new String[indexCounts];
        int i = 0;

        while (true) {
            System.out.print("명령) ");
            String cmdInit = scanner.nextLine();
            String[] cmd = cmdInit.split("\\?id=");

            switch (cmd[0]) {
                case "등록" -> {
                    System.out.print("명언 : ");
                    wiseSayingContent[i] = scanner.nextLine();

                    System.out.print("작가 : ");
                    wiseSayingAuthor[i] = scanner.nextLine();

                    System.out.println("%d번 명언이 등록되었습니다.".formatted(i + 1));
                    if (i < indexCounts - 1) {
                        i++;
                    }
                }
                case "목록" -> {
                    System.out.println("----------------------");
                    for (int j = i; j >= 0; j--) {
                        if (wiseSayingContent[j] != null) {
                            System.out.println("%d / %s / %s".formatted(j + 1, wiseSayingAuthor[j], wiseSayingContent[j]));
                        }
                    }
                }
                case "삭제" -> {
                    if (wiseSayingContent[Integer.parseInt(cmd[1]) - 1] == null) {
                        System.out.println("%d번 명언은 존재하지 않습니다.".formatted(Integer.parseInt(cmd[1])));
                    } else {
                        wiseSayingContent[Integer.parseInt(cmd[1]) - 1] = null;
                        wiseSayingAuthor[Integer.parseInt(cmd[1]) - 1] = null;
                        System.out.println("%d번 명언이 삭제되었습니다.".formatted(Integer.parseInt(cmd[1])));
                    }
                }
                case "수정" -> {
                    if (wiseSayingContent[Integer.parseInt(cmd[1]) - 1] == null) {
                        System.out.println("%d번 명언은 존재하지 않습니다.".formatted(Integer.parseInt(cmd[1])));
                    } else {
                        System.out.println("명언(기존) : %s".formatted(wiseSayingContent[Integer.parseInt(cmd[1]) - 1]));
                        System.out.print("명언 : ");
                        wiseSayingContent[Integer.parseInt(cmd[1]) - 1] = scanner.nextLine();
                        System.out.println("작가(기존) : %s".formatted(wiseSayingAuthor[Integer.parseInt(cmd[1]) - 1]));
                        System.out.print("작가 : ");
                        wiseSayingAuthor[Integer.parseInt(cmd[1]) - 1] = scanner.nextLine();
                    }
                }
                case "종료" -> {
                    return;
                }
            }
        }
    }

    public Main(Scanner scanner) {
        this.scanner = scanner;
    }
}
