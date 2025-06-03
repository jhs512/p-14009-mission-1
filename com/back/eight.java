package com.back;
import java.util.*;



/*
== 명언 앱 ==
명령) 등록
명언 : 현재를 사랑하라.
작가 : 작자미상
1번 명언이 등록되었습니다.
명령) 등록
명언 : 과거에 집착하지 마라.
작가 : 작자미상
2번 명언이 등록되었습니다.
명령) 목록
번호 / 작가 / 명언
----------------------
2 / 작자미상 / 과거에 집착하지 마라.
1 / 작자미상 / 현재를 사랑하라.
명령) 삭제?id=1
1번 명언이 삭제되었습니다.
명령) 삭제?id=1
1번 명언은 존재하지 않습니다.
명령) 수정?id=3
3번 명언은 존재하지 않습니다.
명령) 수정?id=2
명언(기존) : 과거에 집착하지 마라.
명언 : 현재와 자신을 사랑하라.
작가(기존) : 작자미상
작가 : 홍길동
명령) 목록
번호 / 작가 / 명언
----------------------
2 / 홍길동 / 현재와 자신을 사랑하라.
명령) 종료
 */
public class Main{
    public static void main(String []args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("== 명언 앱 ==");
        List<WiseSaying> wiseSayings = new ArrayList<>();
        int id = 0;
        while (true) {
            System.out.print("명령)");

            String cmd = sc.nextLine().trim();
            if (cmd.equals("종료")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            } else if (cmd.equals("등록")) {
                System.out.print("명언:");
                String name = sc.nextLine().trim();
                System.out.print("작가:");
                String author = sc.nextLine().trim();
                id++;
                WiseSaying wiseSaying = new WiseSaying(id, name, author);
                wiseSayings.add(wiseSaying);
                System.out.println("%d번째 등록되었습니다.".formatted(id));
            } else if (cmd.equals("목록")) {
                if (wiseSayings.isEmpty()) {
                    System.out.println("등록된 명언이 없습니다.");
                    continue;
                }
                System.out.println("번호 / 작가 / 명언");
                for (int i = wiseSayings.size() - 1; i >= 0; i--) {
                    WiseSaying ws = wiseSayings.get(i);
                    System.out.println("%d / %s / %s".formatted(ws.id, ws.author, ws.name));
                }
            } else if (cmd.startsWith("삭제?id=")) {
                {
                    cmd = cmd.split("=", 2)[1];
                    int deleteId = Integer.parseInt(cmd);
                    boolean found = false;
                    for (int i = 0; i < wiseSayings.size(); i++) {
                        if (wiseSayings.get(i).id == deleteId) {
                            wiseSayings.remove(i);
                            System.out.println("%d번 명언이 삭제되었습니다.".formatted(deleteId));
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("%d번 명언은 존재하지 않습니다.".formatted(deleteId));
                    }
                }
            } else if (cmd.startsWith("수정?id=")) {
                cmd = cmd.split("=", 2)[1];
                int updateId = Integer.parseInt(cmd);
                for (int i = 0; i < wiseSayings.size(); i++) {
                    if (updateId == wiseSayings.get(i).id) {
                        WiseSaying ws = wiseSayings.get(i);
                        System.out.println("%d번 명언(기존) : %s".formatted(ws.id, ws.name));
                        System.out.print("명언 : ");
                        String newName = sc.nextLine().trim();
                        System.out.println("작가(기존) : %s".formatted(ws.author));
                        System.out.print("작가 : ");
                        String newAuthor = sc.nextLine().trim();
                        ws.name = newName;
                        ws.author = newAuthor;
                        System.out.println("%d번 명언이 수정되었습니다.".formatted(ws.id));
                    }
                }
            }
        }
    }
}

