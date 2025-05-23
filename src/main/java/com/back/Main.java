package com.back;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class wiseSaying {
    int id;
    String content;
    String author;

    wiseSaying(int id, String content, String author){
        this.id = id;
        this.content = content;
        this.author = author;
    }
}


public class Main {
    public static void main(String[] args) {
        System.out.println("== 명언 앱 ==");

        Scanner scanner = new Scanner(System.in);

        List<wiseSaying> wiseSayings = new ArrayList<>();

        int no =0;
        System.out.println();

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine().trim();

            if (cmd.equals("종료")) {
                break;
            } else if (cmd.equals("등록")) {

                System.out.print("명언 : ");
                String wiseSayingContent = scanner.nextLine().trim();
                System.out.print("작가 : ");
                String wiseSayingAuthor = scanner.nextLine().trim();

                wiseSaying ws= new wiseSaying(++no, wiseSayingContent, wiseSayingAuthor);
                wiseSayings.add(ws);

                System.out.println("%d번 명언이 등록되었습니다.".formatted(no));
            } else if (cmd.equals("목록")){
                System.out.println("번호 / 작가 / 명언");
                System.out.println("-------------------");

                for (wiseSaying ws : wiseSayings) {
                    System.out.println("%d / %s / %s".formatted(ws.id, ws.author, ws.content));
                }
         }  else if (cmd.startsWith("삭제?id=")){
                try{
                    int deletenum=Integer.parseInt(cmd.substring(6));
                    boolean isDeleted=false;
                    isDeleted=wiseSayings.removeIf(ws->ws.id==deletenum);

                    if (isDeleted==true){
                        System.out.println(deletenum+"번 명언이 삭제 되었습니다.");
                    }
                    else{
                        System.out.println(deletenum+"번 명언은 존재하지 않습니다.");
                    }
                }
                catch ( NumberFormatException e){
                    System.out.println("id를 확인해주세요.");
                }
            }else if(cmd.startsWith("수정?id=")){
                try{
                    int editnum=Integer.parseInt(cmd.substring(6));
                    boolean isFound=false;
                    for (wiseSaying ws : wiseSayings){
                        if (ws.id==editnum){
                            System.out.println("명언(기존) : "+ws.content);
                            System.out.print("명언 : ");
                            String newContent=scanner.nextLine().trim();
                            ws.content=newContent;
                            System.out.println("작가(기존) : "+ws.author);
                            System.out.print("작가 : ");
                            String newAuthor=scanner.nextLine().trim();
                            ws.author=newAuthor;
                            isFound=true;
                            break;
                        }
                    }
                    if (isFound==false){
                        System.out.println(editnum+"번 명언은 존재하지 않습니다");
                    }
                }
                catch ( NumberFormatException e){
                    System.out.println("id를 확인해주세요.");
                }
            }
            else{
                System.out.println("명령을 다시 확인해주세요.");
            }
        }

        scanner.close();
    }
}
