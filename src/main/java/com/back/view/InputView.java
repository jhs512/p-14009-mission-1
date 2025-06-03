package com.back.view;

import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public String readCommand() {
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

    public String readChangeContent() {
        System.out.print("명언(기존) : ");
        return scanner.nextLine().trim();
    }

    public String readChangeAuthor() {
        System.out.print("작가(기존) : ");
        return scanner.nextLine().trim();
    }
}
