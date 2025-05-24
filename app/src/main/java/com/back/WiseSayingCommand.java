package com.back;

import java.util.Scanner;

public class WiseSayingCommand {

    void run() {
        Scanner sc = App.scanner;
        // "종료"를 제외한 커맨드가 아니라면 계속해서 실행.
        while (true) {
            System.out.print("명령) ");

            if (sc.hasNext() == false) {
                break;
            }

            String command = sc.nextLine();

            // while문의 제어를 위해 switch를 의도적으로 사용하지 않음.
            if (command.equals("등록")) {
                WiseSayingRunner.register();
            } else if (command.equals("목록")) {
                WiseSayingRunner.showList();
            } else if (command.matches("^삭제\\?id=[1-9]\\d*$")) {
                WiseSayingRunner.delete(command);
            } else if (command.matches("^수정\\?id=[1-9]\\d*$")) {
                WiseSayingRunner.modify(command);
            } else if (command.equals("종료")) {
                break;
            } else {
                // 포맷에 맞지 않을 시 패스함.
            }

        }
    }
}
