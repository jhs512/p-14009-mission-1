package com.back;

import java.util.Collections;
import java.util.Scanner;
import java.util.TreeMap;

public class WiseSayingStore {

    private static final TreeMap<Integer, WiseSaying> store = new TreeMap<>(Collections.reverseOrder());
    private static final Scanner sc = App.scanner;
    private static int sequence = 1;

    static void create(String wiseSaying, String writer) {
        store.put(sequence, new WiseSaying(sequence, wiseSaying, writer));
        System.out.println(sequence + "번 명언이 등록되었습니다");

        sequence++;
    }

    static void read() {
        for (WiseSaying entry : store.values()) {
            System.out.println(entry.sequence + " / " + entry.writer + " / " + entry.wiseSaying);
        }
    }

    static void update(int sequence) {
        // 키가 존재하지 않을 때.
        if (store.containsKey(sequence) == false) {
            System.out.println(sequence + "번 명언은 존재하지 않습니다.");
            return;
        }

        WiseSaying entry = store.get(sequence);

        System.out.println("명언(기존) : " + entry.wiseSaying);
        System.out.print("명언 : ");
        String wiseSaying = sc.nextLine();

        System.out.println("작가(기존) : " + entry.writer);
        System.out.print("작가 : ");
        String writer = sc.nextLine();

        entry.setWiseSaying(wiseSaying);
        entry.setWriter(writer);
    }

    static void delete(int sequence) {
        if (store.containsKey(sequence) == true) {
            store.remove(sequence);
            System.out.println(sequence + "번 명언이 삭제되었습니다.");
        } else {
            System.out.println(sequence + "번 명언은 존재하지 않습니다.");
        }

    }
}
