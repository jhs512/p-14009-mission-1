package com.back;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("== 명언 앱 ==");

        Scanner scanner = new Scanner(System.in);

        // 명언 갯수 카운트
        int count = 0;

        // 등록된 명언 리스트에 저장
        HashMap<Integer, String[]> list = new HashMap<>();
        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine().trim();

            if (cmd.equals("종료")) {
                break;
            } else if (cmd.equals("등록")) {
                System.out.print("명언 : ");
                String wiseSayingContent = scanner.nextLine().trim();
                System.out.print("작가 : ");
                String wiseSayingAuthor = scanner.nextLine().trim();

                count++;
                list.put(count, new String[]{wiseSayingAuthor, wiseSayingContent});
                System.out.println(count +"번 명언이 등록되었습니다.");
            } else if (cmd.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                list.forEach((key, value) -> {
                    System.out.println(key + " / " + value[0] + " / " + value[1]);
                });
            } else if (cmd.contains("삭제?id=")) {
                String id = cmd.split("id=")[1];
                String[] value = list.get(Integer.parseInt(id));
                if (value == null) {
                    System.out.println(id + "번 명언은 존재하지 않습니다.");
                    continue;
                }
                list.remove(Integer.parseInt(id));
                System.out.println(id + "번 명언이 삭제되었습니다.");
            } else if (cmd.contains("수정?id=")) {
                String id = cmd.split("id=")[1];
                String[] value = list.get(Integer.parseInt(id));
                if (value == null) {
                    System.out.println(id + "번 명언은 존재하지 않습니다.");
                    continue;
                }
                System.out.println("명언(기존) : " + value[1]);
                System.out.print("명언 : ");
                String wiseSayingContent = scanner.nextLine().trim();
                System.out.println("작가(기존) : " + value[0]);
                System.out.print("작가 : ");
                String wiseSayingAuthor = scanner.nextLine().trim();
                list.put(count, new String[]{wiseSayingAuthor, wiseSayingContent});
            }
        }

        scanner.close();
    }
}
