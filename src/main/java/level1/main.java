package level1;

import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("== 명언 앱 ==");
        System.out.print("명언) ");

        while(true){
            String str = scanner.nextLine();
            if(str.equals("종료")){
                break;
            }
        }

    }
}
