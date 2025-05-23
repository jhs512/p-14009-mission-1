package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("== 명언 앱 ==");

        Scanner scanner = new Scanner(System.in);
        int cnt = 0; // 명언 번호를 위한 counting
        List<String[]> index = new ArrayList<>();

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

                String[] entry = new String[2];
                entry[0] = wiseSayingContent;  // 명언
                entry[1] = wiseSayingAuthor;   // 작가
                // 리스트에 추가
                index.add(entry);

                System.out.println( (cnt + 1) + "번 명언이 등록되었습니다.");
                cnt++;
            } else if (cmd.equals("목록")) {
                if(cnt > 0) {
                    for (int i = cnt;i > 0;i--) {
                        System.out.println(i + " / " + index.get(i-1)[0] + " / " + index.get(i-1)[1]);
                    }
                }
            } else if (cmd.startsWith("삭제?id=")) {

                // id= 이후의 숫자를 추출
                int id = Integer.parseInt(cmd.substring("삭제?id=".length()));

                if (id >= 1 && id <= index.size()) {
                    index.remove(id - 1);
                    cnt--;
                    System.out.println(id + "번 명언이 삭제되었습니다.");
                } else {
                    System.out.println(id + "번 명언은 존재하지 않습니다.");
                }
            }else if (cmd.startsWith("수정?id=")) {

                // id= 이후의 숫자를 추출
                int id = Integer.parseInt(cmd.substring("수정?id=".length()));
                System.out.println("수정id길이" + cmd.substring("수정?id=".length()));
                System.out.println("index.size = " + index.size());
                if (id >= 1 && id <= index.size()) {

                    System.out.println("명언(기존) : " + index.get(id - 1)[0]);
                    System.out.print("명언 : ");
                    String wiseSayingContent = scanner.nextLine().trim();
                    index.get(id - 1)[0] = wiseSayingContent;

                    System.out.println("작가(기존) : " + index.get(id - 1)[1]);
                    System.out.print("작가 : ");
                    String wiseSayingAuthor = scanner.nextLine().trim();
                    index.get(id - 1)[1] = wiseSayingAuthor;


                } else {
                    System.out.println(id + "번 명언은 존재하지 않습니다.");
                }
            }
        }




        scanner.close();
    }
}
