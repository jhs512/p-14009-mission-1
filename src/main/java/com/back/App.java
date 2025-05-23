package com.back;
import java.util.*;

public class App {

    Scanner sc = new Scanner(System.in);
    int lastId = 0;
    WiseSaying[] wiseSayings = new WiseSaying[100];
    int wiseSayingsLastIndex = -1;

    void run() {
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine().trim();

            if (cmd.equals("종료")) {
                break;
            } else if (cmd.equals("등록")) {
                actionWrite();
            } else if (cmd.equals("목록")) {
                actionList();
            } else if (cmd.startsWith("삭제")) {
                actionDel(cmd);
            } else if (cmd.startsWith("수정")) {
                actionModify(cmd);
            }
        }
        sc.close();
    }

    void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        WiseSaying[] forListWiseSayings = findForList();

        for (WiseSaying wiseSaying : forListWiseSayings) {
            System.out.printf("%d / %s / %s\n", wiseSaying.id, wiseSaying.content, wiseSaying.author);
        }
    }

    void actionWrite() {
        System.out.print("명언 : ");
        String content = sc.nextLine().trim();
        System.out.print("작가 : ");
        String author = sc.nextLine().trim();

        WiseSaying wiseSaying = write(content, author);

        System.out.println("%d번 명언이 등록되었습니다".formatted(wiseSaying.id));

    }

    void actionDel(String cmd) {
        String[] cmdBits = cmd.split("=", 2);

        if (cmdBits.length < 2 || cmdBits[1].isEmpty()) {
            System.out.println("id를 입력해주세요.");
            return;
        }
        int id = Integer.parseInt(cmdBits[1]);

        int deletedIndex = delete(id);

        if(deletedIndex == -1){
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
            return;
        }

        System.out.println("%d번 명언이 삭제되었습니다.".formatted(id));
    }

    void actionModify(String cmd) {
        String[] cmdBits = cmd.split("=", 2);

        if(cmdBits.length < 2 || cmdBits[1].isEmpty()){
            System.out.println("id를 입력해주세요.");
            return;
        }

        int id = Integer.parseInt(cmdBits[1]);
        WiseSaying wiseSaying = findById(id);

        if(wiseSaying == null){
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
            return;
        }

        System.out.printf("명언(기존) : %s\n", wiseSaying.content);
        System.out.print("명언 : ");
        String contemt = sc.nextLine().trim();

        System.out.printf("작가(기존) : %s\n", wiseSaying.author);
        System.out.print("작가 : ");
        String author = sc.nextLine().trim();

        modify(wiseSaying, contemt, author);
    }

    int delete(int id){
        int deleteIndex = findIndexById(id);

        if(deleteIndex == -1) return deleteIndex;

        for(int i = deleteIndex + 1; i <= wiseSayingsLastIndex; i++){
            wiseSayings[i-1] = wiseSayings[i];
        }

        wiseSayings[wiseSayingsLastIndex] = null;
        wiseSayingsLastIndex--;

        return deleteIndex;
    }

    int getSize() {
        return wiseSayingsLastIndex + 1;
    }

    WiseSaying findById(int id){
        int index = findIndexById(id);

        if(index == -1) return null;

        return wiseSayings[index];
    }

    int findIndexById(int id){
        for(int i = 0; i <= wiseSayingsLastIndex; i++){
            if(wiseSayings[i].id == id){
                return i;
            }
        }
        return -1;
    }

    void modify(WiseSaying wiseSaying, String content, String author){
        wiseSaying.content = content;
        wiseSaying.author = author;
    }

    WiseSaying[] findForList() {

        WiseSaying[] forListWiseSayings = new WiseSaying[getSize()];

        int forListWiseSayingsIndex = -1;

        for (int i = wiseSayingsLastIndex; i >= 0; i--) {
            forListWiseSayings[++forListWiseSayingsIndex] = wiseSayings[i];
        }

        return forListWiseSayings;
    }

    WiseSaying write(String content, String author) {
        WiseSaying wiseSaying = new WiseSaying();
        wiseSaying.id = ++lastId;
        wiseSaying.content = content;
        wiseSaying.author = author;

        wiseSayings[++wiseSayingsLastIndex] = wiseSaying;

        return wiseSaying;
    }
}
