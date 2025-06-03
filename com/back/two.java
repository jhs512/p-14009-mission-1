package com.back;
import java.util.*;
//== 명언 앱 ==
//명령) 등록
//명언 : 현재를 사랑하라.
//작가 : 작자미상
//명령) 종료
public class two {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("==명언 앱==");

        while (true){
            System.out.print("명령)");
            String cmd = sc.nextLine();
            if (cmd.equals("종료")){
                break;
            }
            else if(cmd.equals("등록")){
                System.out.print("명언 :");
                String wisesaying=sc.nextLine();
                System.out.print("작가 :");
                String author=sc.nextLine();
            }

        }

    }
}
