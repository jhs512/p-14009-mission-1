package com.back.view;

import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public String readCommand() {
        System.out.print("명령) ");
        return scanner.nextLine().trim();
    }

    public String readNewContent() {
        System.out.print("명언 : ");
        return scanner.nextLine().trim();
    }

    public String readNewAuthor() {
        System.out.print("작가 : ");
        return scanner.nextLine().trim();
    }
}
