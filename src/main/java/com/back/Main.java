package com.back;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("== 명언 앱 ==");

        boolean isExit = false;
        int count = 0;

        String[] contents = new String[10];
        String[] authors = new String[10];

        while (!isExit) {
            System.out.print("명령) ");
            String input = scanner.nextLine();
            String command = input.split("\\?")[0];
            String params = input.contains("?") ? input.split("\\?", 2)[1] : "";
            int id = 0;

            switch (command) {
                case "등록":
                    System.out.print("명언: ");
                    String content = scanner.nextLine();
                    System.out.print("작가: ");
                    String author = scanner.nextLine();
                    contents[count] = content;
                    authors[count] = author;
                    System.out.println((++count) + "번 명언이 등록되었습니다.");
                    break;

                case "목록":
                    System.out.println("번호  / 작가 / 명언");
                    System.out.println("----------------------");
                    printList(contents, authors, count);
                    break;

                case "삭제":
                    id = Integer.parseInt(params.substring(3));

                    if(count < id || contents[id-1].equals("삭제됨")){
                        System.out.println(id + "번 명언은 존재하지 않습니다.");
                    }
                    else {
                        contents[id-1] = "삭제됨";
                        System.out.println(id + "번 명언이 삭제되었습니다.");
                    }
                    break;

                case "수정":
                    id = Integer.parseInt(params.substring(3));

                    if(count < id || contents[id-1].equals("삭제됨")){
                        System.out.println(id + "번 명언은 존재하지 않습니다.");
                    }
                    else {
                        System.out.println("명언(기존): " + contents[id-1]);
                        System.out.print("명언 :");
                        String newContent = scanner.nextLine();
                        contents[id-1] = newContent;
                        System.out.println("작가(기존): " + authors[id-1]);
                        System.out.print("작가 :");
                        String newAuthor = scanner.nextLine();
                        authors[id-1] = newAuthor;
                    }
                    break;

                case "종료":
                    isExit = true;
                    break;

                default:
                    System.out.println("잘못된 명령입니다.");
                    break;
            }
        }
    }

    public static void printList(String[] c, String[] a, int cnt){
        for(int i = cnt-1; i >= 0; i--){
            if(!c[i].equals("삭제됨")) {
                System.out.println((i+1) + " / " + a[i] + " / " + c[i]);
            }
        }
    }
}