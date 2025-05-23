package com.back.wiseSaying;

import java.util.Scanner;

public class WiseSayingApp {
    private final Scanner scanner = new Scanner(System.in);
    private final WiseSayingController controller = new WiseSayingController(scanner);

    public void start() {
        while (true) {
            System.out.print("명령) ");
            String cmd;
            cmd = scanner.nextLine().trim();
            controller.executeCommand(cmd);
        }
    }
}
