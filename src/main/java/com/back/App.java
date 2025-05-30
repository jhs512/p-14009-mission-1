package com.back;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private Scanner scanner = new Scanner(System.in);
    private List<WiseSayings> wiseSayings = new ArrayList<>();
    private int lastId = 0;

    void run(){

        System.out.println("== 명언 앱 ==");
        while (true){
            System.out.print("명언) ");
            String cmd = scanner.nextLine().trim();

            if(cmd.equals("종료")) {
                break;
            } else if(cmd.equals("등록")){
                actionWrite();
            } else if(cmd.startsWith("삭제")){
                actionDelete(cmd);
            } else  if(cmd.startsWith("수정")){
                actionModify(cmd);
            } else if(cmd.equals("목록")){
                actionList();
            }
        }
        scanner.close();
    }

    //

    private void actionWrite(){
        System.out.print("명언: ");
        String content = scanner.nextLine().trim();
        System.out.print("작가: ");
        String author = scanner.nextLine().trim();
        WiseSayings wiseSaying = write(content, author);
        System.out.printf("%d번 명언이 등록되었습니다.\n", wiseSaying.getId());
    }

    private void actionDelete(String cmd){
        String cmdBits[] = cmd.split("=", 2);


        if(cmdBits.length < 2 || cmdBits[1].isEmpty()){
            System.out.println("id가 입력되지 않았습니다.");
        }

        int id = Integer.parseInt(cmdBits[1]);

        WiseSayings wiseSaying = findById(id);

        if (wiseSaying==null){
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
            return;
        }else {
            delete(id);
            System.out.printf("%d번 명언이 삭제되었습니다.\n", id);
        }

    }

    private void actionList(){
        System.out.println("번호 / 작가 / 명언\n" +
                "----------------------");

        List<WiseSayings> listSayings = findForList();
        for(WiseSayings wiseSaying :findForList()){
            System.out.printf("%d / %s / %s \n", wiseSaying.getId(), wiseSaying.getAuthor(), wiseSaying.getContent());
        }

    }

    private void actionModify(String cmd){
        String cmdBits[] = cmd.split("=", 2);

        int id = Integer.parseInt(cmdBits[1]);

        if(cmdBits.length < 2 || cmdBits[1].isEmpty()){
            System.out.println("id가 입력되지 않았습니다.");
        }

        WiseSayings wiseSaying = findById(id);

        if (wiseSaying==null){
            System.out.printf("%d번 명언은 존재하지 않습니다.", id);
            return;
        }

        System.out.printf("명언(기존) : %s\n", wiseSaying.getContent());
        System.out.print("명언 : ");
        String content = scanner.nextLine().trim();

        System.out.printf("작가(기존) : %s\n", wiseSaying.getAuthor());
        System.out.print("작가 : ");
        String author = scanner.nextLine().trim();

        modify(wiseSaying, content, author);
    }

    //

    private WiseSayings write(String content, String author){
        WiseSayings wiseSaying = new WiseSayings(++lastId, author, content);
        wiseSayings.add(wiseSaying);
        return wiseSaying;
    }

    private void delete(int id){
        WiseSayings wiseSaying = findById(id);
        wiseSayings.remove(wiseSaying);
    }

    private void modify(WiseSayings wiseSaying, String content, String author){
        wiseSaying.setContent(content);
        wiseSaying.setAuthor(author);
    }

    private List<WiseSayings> findForList(){
        return wiseSayings.reversed();
    }

    private WiseSayings findById(int id){
        return wiseSayings
                .stream()
                .filter(wiseSayings -> wiseSayings.getId() == id)
                .findFirst()
                .orElse(null);
    }

}
