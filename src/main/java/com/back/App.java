package com.back;

import java.util.Scanner;

class WiseSaying {
    int id;
    String content;
    String author;

    WiseSaying(int id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }
}

public class App {
    Scanner sc = new Scanner(System.in);
    WiseSaying[] sayings = new WiseSaying[100];
    int lastId = 0, lastIndex = -1;

    void run() {
        System.out.println("== 명언 앱 ==");
        while (true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine().trim();

            if (cmd.equals("종료")) break;
            else if (cmd.equals("등록")) write();
            else if (cmd.equals("목록")) list();
            else if (cmd.startsWith("삭제")) delete(getId(cmd));
            else if (cmd.startsWith("수정")) modify(getId(cmd));
        }
    }

    void write() {
        System.out.print("명언 : ");
        String content = sc.nextLine().trim();
        System.out.print("작가 : ");
        String author = sc.nextLine().trim();
        sayings[++lastIndex] = new WiseSaying(++lastId, content, author);
        System.out.printf("%d번 명언이 등록되었습니다.\n", lastId);
    }

    void list() {
        System.out.println("번호 / 작가 / 명언\n----------------------");
        for (int i = lastIndex; i >= 0; i--) {
            WiseSaying s = sayings[i];
            System.out.printf("%d / %s / %s\n", s.id, s.author, s.content);
        }
    }

    void delete(int id) {
        int index = findIndexById(id);
        if (index == -1) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
            return;
        }
        for (int i = index; i < lastIndex; i++) sayings[i] = sayings[i + 1];
        sayings[lastIndex--] = null;
        System.out.printf("%d번 명언이 삭제되었습니다.\n", id);
    }

    void modify(int id) {
        int index = findIndexById(id);
        if (index == -1) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
            return;
        }
        WiseSaying s = sayings[index];
        System.out.printf("명언(기존) : %s\n명언 : ", s.content);
        s.content = sc.nextLine().trim();
        System.out.printf("작가(기존) : %s\n작가 : ", s.author);
        s.author = sc.nextLine().trim();
    }

    int findIndexById(int id) {
        for (int i = 0; i <= lastIndex; i++) if (sayings[i].id == id) return i;
        return -1;
    }

    int getId(String cmd) {
        String[] bits = cmd.split("=");
        return bits.length < 2 ? -1 : Integer.parseInt(bits[1]);
    }

    public static void main(String[] args) {
        new App().run();
    }
}
