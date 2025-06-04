package com.back;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("== 명언 앱 ==");

        Scanner scanner=new Scanner(System.in); //사용자입력을 받는 기능을 하는코드


        while(true){
            System.out.print("명령) ");
            String str=scanner.nextLine().trim();//nextLine이라는 함수
            if(str.equals("종료")){//사용자가 입력한 값이 종료와 동일하면
                break;
            }
            else if(str.equals("등록")){
                System.out.print("명언 : ");
                String wiseSayingContent = scanner.nextLine().trim();//변수의 의미
                System.out.print("작가 : ");
                String wiseSayingAuthor = scanner.nextLine().trim();
                System.out.println("1번 명언이 등록되었습니다.");
            }


        }
        scanner.close();
    }
}



