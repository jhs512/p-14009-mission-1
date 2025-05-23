package com.back;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static void del(List<Writer> arr, int idx) {
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).idx == idx) {
                System.out.println(arr.size());
                arr.remove(i);
                System.out.printf("%d번 명언이 삭제되었습니다.\n", idx);
                return;
            }
        }
        System.out.printf("%d번 명언은 존재하지 않습니다.\n", idx);
        return;
    }

    static void modify(List<Writer> arr, int idx) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).idx == idx) {
                System.out.println("명언(기존) : " +arr.get(i).word);
                System.out.print("명언 : ");
                arr.get(i).word = sc.nextLine();

                System.out.println("작가(기존) : " +arr.get(i).name);
                System.out.print("작가 : ");
                arr.get(i).name = sc.nextLine();
                return;
            }
        }
        System.out.printf("%d번 명언은 존재하지 않습니다.\n",idx);
        return;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String command = "";
        String name = "";
        String word = "";
        int idx = -1;
        int ct = 1;

        List<Writer> arr = new ArrayList<>();

        System.out.println("== 명언 앱 ==");
        while (!command.equals("종료")) {
            System.out.print("명령) ");
            command = sc.nextLine();

            //등록
            if (command.equals("등록")) {
                System.out.print("명언 : ");
                word = sc.nextLine();
                System.out.print("작가 : ");
                name = sc.nextLine();

                arr.add(new Writer(ct, name, word));
                System.out.printf("%d번 명언이 등록되었습니다.\n", ct);
                ct++;
            }

            //목록
            if (command.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");
                for (Writer w : arr) {
                    System.out.printf("%d / %s / %s\n", w.idx, w.name, w.word);
                }
            }

            //삭제
            if (command.startsWith("삭제")) {
                idx = Integer.parseInt(command.substring(6));
                del(arr, idx);
            }

            //수정
            if (command.startsWith("수정")){
                idx = Integer.parseInt(command.substring(6));
                modify(arr, idx);
            }

        }
    }
}