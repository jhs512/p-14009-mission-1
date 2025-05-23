package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
        static Scanner sc = new Scanner(System.in);
        static List<String[]> list = new ArrayList<>();
        static int count = 1;

        public static void main(String[] args) {
            System.out.println("== 명언 앱 ==");

            while (true) {
                System.out.print("명령) ");
                String cmd = sc.nextLine().trim();

                if (cmd.equals("종료")) {
                    break;
                } else if (cmd.equals("등록")) {
                    actionWrite();
                } else if (cmd.equals("목록")) {
                    actionList();
                } else if (cmd.startsWith("삭제?id=")) {
                    delete(cmd);
                } else if (cmd.startsWith("수정?id=")) {
                    update(cmd);
                }
            }

            sc.close();
        }

        static void actionWrite() {
            System.out.print("명언: ");
            String wiseSaying = sc.nextLine().trim();
            System.out.print("작가: ");
            String author = sc.nextLine().trim();
            String[] arr = {String.valueOf(count), wiseSaying, author};
            list.add(arr);
            System.out.println("%d번 명언이 등록되었습니다.".formatted(count));
            count++;
        }

        static void actionList() {
            System.out.println("번호 / 작가 / 명언");
            System.out.println("----------------------");

            for (int i = list.size() - 1; i >= 0; i--) {
                System.out.println("%s / %s / %s".formatted(list.get(i)[0], list.get(i)[2], list.get(i)[1]));
            }
        }

        static void delete(String cmd) {
        try {
            String[] arr = cmd.split("=");
            int id = Integer.parseInt(arr[1]);

            boolean found = false;
            for (int i = 0; i < list.size(); i++) {
                if (Integer.parseInt(list.get(i)[0]) == id) {
                    list.remove(i);
                    System.out.println("%d번 명언이 삭제되었습니다.".formatted(id));
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
            }
        } catch (NumberFormatException e) {
            System.out.println("잘못된 id");
            }
        }

        static void update(String cmd) {
        try {
            String[] arr = cmd.split("=");
            int id = Integer.parseInt(arr[1]);

            boolean found = false;
            for (int i = 0; i < list.size(); i++) {
                if (Integer.parseInt(list.get(i)[0]) == id) {
                    System.out.println("명언(기존) : "+ list.get(i)[1]);
                    System.out.print("명언 : ");
                    String wiseSaying = sc.nextLine();
                    System.out.println("작가(기존) : "+ list.get(i)[2]);
                    System.out.print("작가 : ");
                    String author = sc.nextLine();
                    String[] newArr = {String.valueOf(id), wiseSaying, author};
                    list.set(i, newArr);
                    System.out.println("%d번 명언이 수정되었습니다.".formatted(id));
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
            }
        } catch (NumberFormatException e) {
            System.out.println("잘못된 id");
        }
    }
        }