package com.back;

import java.util.Scanner;

public class WiseSayingRunner {

    private static final Scanner sc = App.scanner;

    static void register() {
        System.out.print("명언 : ");
        String wiseSaying = sc.nextLine();

        System.out.print("작가 : ");
        String writer = sc.nextLine();

        // 등록되었습니다의 출력은 여기서 하지 않음.
        WiseSayingStore.create(wiseSaying, writer);
    }

    static void showList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        WiseSayingStore.read();
    }

    static void delete(String command) {
        int sequence = Integer.parseInt(command.split("=")[1]);
        WiseSayingStore.delete(sequence);
    }

    static void modify(String command) {
        int sequence = Integer.parseInt(command.split("=")[1]);
        WiseSayingStore.update(sequence);
    }

}
