package com.back;

import java.util.Scanner;

public class App {
    Scanner scanner = new Scanner(System.in); //사용자입력을 받는 기능을 하는코드
    int lastId = 0;
    WiseSaying[] wiseSayings = new WiseSaying[100];
    int wiseSayingsLastIndex = -1;

    //진입점 시작
    public void run(){
        System.out.println("== 명언 앱 ==");
        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine().trim();//nextLine이라는 함수
            if (cmd.equals("종료")) {//사용자가 입력한 값이 종료와 동일하면
                break;
            } else if (cmd.equals("등록")) {
                actionWrite();

            } else if (cmd.equals("목록")) {
                actionList();
            } else if (cmd.startsWith("삭제")) {
                actionDelete(cmd);
            }else if (cmd.startsWith("수정")){
                actionModify(cmd);
            }
        }
        scanner.close();
    }


    //진입점 끝

    //사용자 등록 응대? 담당
    void actionWrite(){
        System.out.print("명언 : ");
        String content = scanner.nextLine().trim();//변수의 의미
        System.out.print("작가 : ");
        String author = scanner.nextLine().trim();

        WiseSaying wiseSaying = write(content, author);
        System.out.println("%d번 명언이 등록되었습니다.".formatted(wiseSaying.id));
    }

    //목록 출력 담당
    void actionList(){
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        WiseSaying[] forListWiseSayings = findForList();

        for(WiseSaying wiseSaying : forListWiseSayings){
            System.out.println("%d / %s / %s".formatted(wiseSaying.id, wiseSaying.author, wiseSaying.content));
        }
    }

    void actionDelete(String cmd){
        String[] cmdBits = cmd.split(  "=",   2);

        if (cmdBits.length < 2 || cmdBits[1].isEmpty()) {
            System.out.println("id를 입력해주세요");
            return;
        }
        int id = Integer.parseInt(cmdBits[1]);
        int deleteIndex = delete(id);

        if(deleteIndex == -1) {
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
            return;
        }

        System.out.println("%d번 명언이 삭제되었습니다.".formatted(id));
    }

    private void actionModify(String cmd) {
        String[] cmdBits = cmd.split("=",2);

        if (cmdBits.length < 2 || cmdBits[1].isEmpty()){
            System.out.println("id를 입력해주세요.");
            return;
        }

        int id = Integer.parseInt(cmdBits[1]);

        WiseSaying wiseSaying = findbyId(id);

        if (wiseSaying == null) {
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
            return;
        }

        System.out.printf("명언(기존) : %s\n", wiseSaying.content);
        System.out.print("명언 : ");
        String content = scanner.nextLine().trim();

        System.out.printf("작가(기존) : %s\n", wiseSaying.author);
        System.out.print("작가 : ");
        String author = scanner.nextLine().trim();

        modify(wiseSaying, content, author);

    }



    int getSize(){
        return wiseSayingsLastIndex + 1 ;
    }

    WiseSaying[] findForList(){
        WiseSaying[] forListWiseSayings = new WiseSaying[getSize()];
        int forListWiseSayingsIndex = -1;

        for(int i = wiseSayingsLastIndex; i >=0; i--) {
            forListWiseSayings[++forListWiseSayingsIndex] = wiseSayings[i];
        }

        return forListWiseSayings;

    }

    WiseSaying write(String content, String author){
        WiseSaying wiseSaying = new WiseSaying();//객체 생성한거야 붕어빵만든거야
        wiseSaying.id = ++lastId;
        wiseSaying.author = author;
        wiseSaying.content = content;

        wiseSayings[++wiseSayingsLastIndex]=wiseSaying;

        return wiseSaying;
    }

    int delete(int id) {
        int deleteIndex = -1;

        for (int i =0; i<=wiseSayingsLastIndex; i++){
            if (wiseSayings[i].id == id) {
                deleteIndex = i;
                break;
            }
        }

        if (deleteIndex == -1) return deleteIndex;

        for(int i =deleteIndex + 1; i <= wiseSayingsLastIndex; i++){
            wiseSayings[i -1] = wiseSayings[i];
        }

        wiseSayings[wiseSayingsLastIndex] = null;
        wiseSayingsLastIndex--;

        return deleteIndex;
    }

    void modify(WiseSaying wiseSaying, String content, String author) {
        wiseSaying.content = content;
        wiseSaying.author = author;
    }


    WiseSaying findbyId(int id) {
        int index = findIndexById(id);

        if (index == -1) return null;

        return wiseSayings[index];
    }

    int findIndexById(int id) {
        for (int i = 0; i <= wiseSayingsLastIndex; i++){
            if (wiseSayings[i].id == id) {
                return i;
            }
        }

        return -1;
    }

}
