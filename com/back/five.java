package com.back;
import java.util.*;


/*== 명언 앱 ==
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
명령) 종료
 */

public class five {
    public static void main(String [] args){
        Scanner sc=new Scanner(System.in);
        WiseSaying[] wiseSayings = new WiseSaying[100];
        System.out.println("==명언 앱==");
        int count = 0; // 명언의 개수
        while(true){
            System.out.print("명령)");
            String cmd = sc.nextLine().trim();
            if(cmd.equals("종료")){
                break;
            }
            else if(cmd.equals("등록")){
                System.out.print("명언 :");
                String ws = sc.nextLine().trim();
                System.out.print("작가 :");
                String author = sc.nextLine().trim();
                int id = ++count; // 명언의 ID는 1부터 시작

                WiseSaying wisesaying= new WiseSaying(id, ws, author);
                wiseSayings[count - 1] = wisesaying; // 배열에 저장
                System.out.println("%d번째 명언이 등록되었습니다.".formatted(count));
            }
            else if(cmd.equals("목록")){
                System.out.println("번호/작가/명언");
                System.out.println("----------------------");
                for(int i = count - 1; i>=0; i--){
                    WiseSaying wiseSaying = wiseSayings[i];
                    if (wiseSaying != null) {
                        System.out.printf("%d / %s / %s\n", wiseSaying.id, wiseSaying.author, wiseSaying.content);
                    }
                    if(wiseSaying == null){
                        System.out.println("등록된 명언이 없습니다.");
                        break;
                    }
                }
            }
        }
    }
}