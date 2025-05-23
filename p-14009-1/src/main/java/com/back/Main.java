package com.back;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Saying{
    private int id;
    public String content;
    public String author;

    public Saying(int id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }
    public int getId() {
        return id;
    }
    public String getContent() {
        return content;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setContent(String content) {
        this.content = content;
    }
}

public class Main {
    public static void main(String[] args) {

        System.out.println("==명언 앱==");
        int count = 0;
        Scanner scanner = new Scanner(System.in);

        List<Saying> sayings = new ArrayList<>();

        while (true) {
            System.out.print("명령)");

            String cmd = scanner.nextLine().trim();


            if (cmd.equals("종료")) {
                break;
            } else if (cmd.equals("등록")) {
                count++;
                System.out.print("명언 : ");
                String content = scanner.nextLine().trim();
                System.out.print("작가 : ");
                String author = scanner.nextLine().trim();
                System.out.println(count + "번 명언이 등록되었습니다.");
                sayings.add(new Saying(count, content, author));
            } else if (cmd.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("------------------------------");
                for (int i = sayings.size()-1; i>= 0; i--) {
                    Saying saying = sayings.get(i);
                    System.out.println(saying.getId() + " / " + saying.getAuthor() + " / " + saying.getContent());
                }
            } else if (cmd.startsWith("삭제?id=")) {
                String target = cmd.replace("삭제?id=","");
                int targetId = Integer.parseInt(target);
                boolean found = false;

                for(int i =0;i<sayings.size();i++){
                    if(sayings.get(i).getId() == targetId){
                        sayings.remove(i);
                        System.out.println(targetId + "번 명언이 삭제되었습니다.");
                        found = true;
                        break;
                    }
                }
                if(!found){
                    System.out.println(targetId + "번 명언은 존재하지 않습니다.");
                }
            } else if (cmd.startsWith("수정?id=")) {
                String target = cmd.replace("수정?id=","");
                int targetId = Integer.parseInt(target);
                boolean found = false;

                for(int i =0;i<sayings.size();i++){
                    Saying saying = sayings.get(i);
                    if(targetId== saying.getId()){
                        System.out.println("명언(기존) : " + saying.getContent());
                        System.out.print("명언 : ");
                        String newContent = scanner.nextLine().trim();

                        System.out.println("작가(기존) : " + saying.getAuthor());
                        System.out.print("작가 : ");
                        String newAuthor = scanner.nextLine().trim();

                        saying.setContent(newContent);
                        saying.setAuthor(newAuthor);

                        found = true;
                        break;
                    }
                }
                if(!found){
                    System.out.println(targetId + "번 명언은 존재하지 않습니다.");
                }
            }
        }

        scanner.close();
    }
}