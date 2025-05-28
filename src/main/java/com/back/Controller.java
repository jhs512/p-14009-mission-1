package com.back;

import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    Scanner scanner = new Scanner(System.in);
    logic logic = new logic();

    public boolean runCommand(String cmd) {
        String command = cmd.split("\\?")[0];
        switch (command) {
            case "등록":
                register();
                break;
            case "목록":
                list();
                break;
            case "수정":
                modify(cmd);
                break;
            case "삭제":
                delete(cmd);
                break;
            case "종료":
                exit();
                break;
            default:
                System.out.println("잘못된 명령입니다.");
                break;
        }
        return true;
    }
    private void register() {
        System.out.print("내용) ");
        String content = scanner.nextLine();
        System.out.print("작성자) ");
        String author = scanner.nextLine();
        System.out.println(logic.write(content, author));
    }
    private void list() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        ArrayList<WiseSaying> list = logic.list();
        list.sort((o2, o1) -> o1.getId() - o2.getId());
        for (WiseSaying saying : list) {
            System.out.println(saying.getId() + " / " + saying.getAuthor() + " / " + saying.getContent());
        }
    }
    private void modify(String command) {
        String[] split = command.split("\\?");
        if (split.length != 2 || split[1].isEmpty() || !split[1].startsWith("id=")) {
            System.out.println("명령어를 바르게 작성해 주세요. ex) 수정?id=1");
            return;
        }

        int id = Integer.parseInt(split[1].split("=")[1]);
        WiseSaying modifying = logic.findById(id);

        if (modifying == null) {
            System.out.println("해당하는 명언이 없습니다.");
            return;
        }

        System.out.println("변경 전 내용) " + modifying.getContent());
        System.out.print("내용) ");
        String content = scanner.nextLine();
        System.out.println("변경 전 작성자) " + modifying.getAuthor());
        System.out.print("작성자) ");
        String author = scanner.nextLine();
        System.out.println(logic.modify(id, content, author));
    }
    private void exit() {
        System.out.println("종료합니다.");
        System.exit(0);
    }
    private void delete(String command) {
        String[] split = command.split("\\?");
        if (split.length != 2 || split[1].isEmpty() || !split[1].startsWith("id=")) {
            System.out.println("명령어를 바르게 작성해 주세요. ex) 삭제?id=1");
            return;
        }
        int id = Integer.parseInt(split[1].split("=")[1]);
        String msg = logic.delete(id);
        System.out.println(msg);
    }
}
