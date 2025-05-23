package com.back;

import java.util.ArrayList;
import java.util.Scanner;

class Quote {
    int id;
    String phrase;
    String author;

    Quote(int id, String phrase, String author) {
        this.id = id;
        this.phrase = phrase;
        this.author = author;
    }

    @Override
    public String toString() {
        return id + " / " + author + " / " + phrase;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Quote> quotes = new ArrayList<>();
        int nextId = 1;

        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령 : ");
            String input = sc.nextLine().trim();

            switch (input) {
                case "등록":
                    System.out.print("명언 : ");
                    String phrase = sc.nextLine().trim();
                    System.out.print("작가 : ");
                    String author = sc.nextLine().trim();
                    quotes.add(new Quote(nextId, phrase, author));
                    System.out.println(nextId + "번 명언이 등록되었습니다.");
                    nextId++;
                    break;

                case "목록":
                    System.out.println("번호 / 작가 / 명언");
                    System.out.println("------------------------");
                    for (int i = quotes.size() - 1; i >= 0; i--) {
                        System.out.println(quotes.get(i));
                    }
                    break;

                case "종료":
                    System.exit(0);
                    break;

                default:
                    if (input.startsWith("삭제?id=")) {
                        // startsWith : 특정문구로 시작하는지?
                        try {
                            int idToDelete = Integer.parseInt(input.replaceAll("[^0-9]", ""));
                            boolean removed = quotes.removeIf(q -> q.id == idToDelete);
                            // removeif : 조건에 맞는 요소 제거, 제거 되었는지 여부를 boolean 값으로 반환
                            if (removed) {
                                System.out.println(idToDelete + "번 명언이 삭제되었습니다.");
                            } else {
                                System.out.println(idToDelete + "번 명언이 존재하지 않습니다.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("삭제할 ID를 정확히 입력해주세요.");
                        }
                        break;
                    }else if (input.startsWith("수정?id=")) {
                       try{
                           int idToChange = Integer.parseInt(input.replaceAll("[^0-9]",""));
                           boolean found = false;
                           for(Quote q : quotes) {
                               // 향상된 for문 -> quotes라는 arraylist<Quote> 안에 잇는 모든 quote 객체를 하나씩 순서대로 꺼내서 그 객체를 q라는 임시변수에 담아서 쓰겠다는 의미
                               if(q.id == idToChange){
                                   System.out.print("명언(기존) : " + q.phrase + " 새 명언: ");
                                   String newPhrase = sc.nextLine().trim();
                                   System.out.print("작가(기존) : " + q.author + " 새 작가: ");
                                   String newAuthor = sc.nextLine().trim();

                                   q.phrase = newPhrase;
                                   q.author = newAuthor;

                                   System.out.println(idToChange + "번 명언이 수정되었습니다.");
                                   found = true;
                                   break;
                               }
                           }

                       }catch(NumberFormatException e){
                           System.out.println("수정할 ID를 정확히 입력해주세요");
                       }
                        break;
                    }

                    System.out.println("입력한 명령을 다시 확인해주세요");
                    break;
            }
        }
    }
}




/* 코드 문제점
*  동적배열 사용하면 index가 삭제될떄마다 번호 앞으로 댕겨짐 댕... ㅠ ㅠ -> map 사용
*
* */