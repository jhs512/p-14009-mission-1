package com.back;


import java.util.*;
/*== 명언 앱 ==
명령) 등록
명언 : 현재를 사랑하라.
작가 : 작자미상
1번 명언이 등록되었습니다.
명령) 등록
명언 : 현재를 사랑하라.
작가 : 작자미상
2번 명언이 등록되었습니다.
명령) 종료  */
public class four {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("==명언 앱==");
        int count = 1;
        while (true){
            System.out.print("명령)");
            String cmd = sc.nextLine().trim();
            if (cmd.equals("종료")){
                break;
            }
            else if(cmd.equals("등록")){
                System.out.print("명언 :");
                String wisesaying=sc.nextLine().trim();
                System.out.print("작가 :");
                String author=sc.nextLine().trim();
                System.out.printf("%d번 명언이 등록되었습니다.", count++); //println을 사용하면 String.format을 해야한다는걸 배움
            }

        }

    }
}


