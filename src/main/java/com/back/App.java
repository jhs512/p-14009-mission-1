package com.back;

import java.util.Scanner;

public class App {
    Controller controller = new Controller();
    Scanner scanner = new java.util.Scanner(System.in);
    public void run() {
        System.out.println("============= 명언 앱 =============");
        boolean running = true; // 명령 실행 상태를 명시적으로 설정
        while (running) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine().trim();
            running = controller.runCommand(cmd); // 명령어 처리
        }
    }
}
