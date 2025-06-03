package com.back;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("== 명언 앱 ==");
        Scanner scanner = new Scanner(System.in);
        int WiseSayingCount = 0;                                // 명언의 개수
        WiseSaying wiseSayings[] = new WiseSaying[100];         // 명언을 저장할 배열

        // 명언 앱의 메인 루프
        while(true){
            System.out.print("명령) ");
            String cmd = scanner.nextLine().trim();
            if(cmd.equals("종료")) {
                break;
            }


            else if(cmd.equals("등록")){
                System.out.print("명언 : ");
                String content = scanner.nextLine().trim();
                System.out.print("작가 : ");
                String author = scanner.nextLine().trim();
                int id = WiseSayingCount + 1;
                wiseSayings[WiseSayingCount] = new WiseSaying(id, content, author);
                WiseSayingCount++;
                System.out.println("%d번 명언이 등록되었습니다.".formatted(id));
            }


            else if(cmd.equals("목록")){
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");
                for(int i = WiseSayingCount-1; i >= 0; i--){
                    WiseSaying wiseSaying = wiseSayings[i];
                    System.out.printf("%d / %s / %s\n", wiseSaying.id, wiseSaying.author, wiseSaying.content);
                }
            }


            else if(cmd.startsWith("삭제")) {
                String[] cmdBits = cmd.split("=", 2);
                if (cmdBits.length < 2 || cmdBits[1].isBlank()) {
                    System.out.println("삭제할 명언의 id를 입력해주세요.");
                    continue;
                }
                int id = Integer.parseInt(cmdBits[1]);

                // 명언 찾기
                boolean found = false;
                for (int i = 0; i < WiseSayingCount; i++) {
                    if (wiseSayings[i].id == id) {
                        // 삭제
                        for (int j = i; j < WiseSayingCount - 1; j++) {
                            wiseSayings[j] = wiseSayings[j + 1];
                        }
                        wiseSayings[WiseSayingCount - 1] = null;
                        WiseSayingCount--;
                        System.out.println("%d번 명언이 삭제되었습니다.".formatted(id));
                        found = true;
                        break;
                    }
                }
                // 명언이 존재하지 않는 경우
                if (!found) {
                    System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
                }
            }

            else if(cmd.startsWith("수정")){
                String[] cmdBits = cmd.split("=", 2);
                if (cmdBits.length < 2 || cmdBits[1].isBlank()) {
                    System.out.println("수정할 명언의 id를 입력해주세요.");
                    continue;
                }
                int id = Integer.parseInt(cmdBits[1]);
                WiseSaying wiseSaying = null;
                // 명언 찾기
                for (int i = 0; i < WiseSayingCount; i++) {
                    if (wiseSayings[i].id == id) {
                        wiseSaying = wiseSayings[i];
                        break;
                    }
                }
                if (wiseSaying == null) {
                    System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
                    continue;
                }
                System.out.printf("명언(기존) : %s\n", wiseSaying.content);
                System.out.print("명언 : ");
                String content = scanner.nextLine().trim();

                System.out.printf("작가(기존) : %s\n", wiseSaying.author);
                System.out.print("작가 : ");
                String author = scanner.nextLine().trim();

                wiseSaying.content = content;
                wiseSaying.author = author;

                System.out.println("%d번 명언이 수정되었습니다.".formatted(id));
            }
            else{
                System.out.println("알 수 없는 명령어입니다.");
            }
        }
    }
}