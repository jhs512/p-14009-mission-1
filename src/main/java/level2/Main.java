package level2;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("== 명언 앱 ==");

        while(true){
            System.out.print("명령) ");
            String str = scanner.nextLine();
            if(str.equals("등록")){
                System.out.print("명언 : ");
                String wiseSaying = scanner.nextLine();
                System.out.print("작가 : ");
                String wiseSayingAuthor = scanner.nextLine();
            }
            else if(str.equals("종료")){
                break;
            }
        }


    }
}
