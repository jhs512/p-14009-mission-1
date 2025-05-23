package com.back;

import java.util.Scanner;

public class App {
    public static final int MAX_NUM = 1000;

    public Scanner scanner;
    public WiseSaying[] db;
    public int num;

    App() {
        db = new WiseSaying[MAX_NUM+1];
        num = 0;
    }

    public void run() {
        boolean exit = false;
        scanner = new Scanner(System.in);

        System.out.println("== 명언 앱 ==");
        while(!exit) {
            System.out.print("명령) ");
            String input = scanner.nextLine().trim();
            String cmd = input.split("\\?")[0];
            String[] param = input.contains("?") ? input.split("\\?")[1].split("&") : new String[]{};

            switch(cmd) {
                case "등록" -> add();
                case "삭제" -> remove(param);
                case "수정" -> update(param);
                case "목록" -> list(param);
                case "종료" -> exit = true;
                default -> System.out.println("잘못된 명령어입니다.");
            }
        }

        scanner.close();
    }

    public void add() {
        System.out.print("명언 : ");
        String content = scanner.nextLine().trim();
        System.out.print("작가 : ");
        String author = scanner.nextLine().trim();
        db[++num] = new WiseSaying(num, content, author);
        System.out.println(num + "번 명언이 등록되었습니다.");
    }

    public void update(String[] param) {
        int id;
        try {
            id = Integer.parseInt(param[0].split("=")[1]);
        } catch (Exception e) {
            System.out.println("ID를 올바르게 입력해주세요.");
            return;
        }
        if (id > num || db[id].id == -1) {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
            return;
        }

        System.out.println("명언(기존) : " + db[id].content);
        System.out.print("명언 : ");
        String input = scanner.nextLine().trim();
        db[id].content = input;

        System.out.println("작가(기존) : " + db[id].author);
        System.out.print("작가 : ");
        input = scanner.nextLine().trim();
        db[id].author = input;
    }

    public void remove(String[] param) {
        int id;
        try {
            id = Integer.parseInt(param[0].split("=")[1]);
        } catch (Exception e) {
            System.out.println("ID를 올바르게 입력해주세요.");
            return;
        }
        if (id > num || db[id].id == -1) {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
            return;
        }

        db[id].id = -1;
        System.out.println(id + "번 명언이 삭제되었습니다.");
    }

    public void list(String[] param) {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        for(int i=num; i>0; i--) {
            if(db[i].id == -1)
                continue;
            System.out.printf("%d / %s / %s\n", db[i].id, db[i].author, db[i].content);
        }
    }
}
