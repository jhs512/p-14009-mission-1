package com.back;
import java.util.*;
//== 명언 앱 ==
//명령) 종료
public class one {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("==명언 앱==");
        System.out.println("명령)");

        while (true){
            String cmd = sc.nextLine();
            if (cmd.equals("종료")){
                break;
            }
        }

    }
}
