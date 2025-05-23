package com.back;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Quote8 {
    public void run() {
        System.out.println("== 명언 앱8 ==");
        Scanner scanner = new Scanner(System.in);
        int cnt = 0;
        Map<Integer, Quote> map = new HashMap<>();
        while (true){
            System.out.print("명령) ");
            String cmd = removeSpecialChars(scanner.nextLine().trim());

            if(cmd.equals("종료")){
                break;
            }
            if(cmd.equals("등록")){
                System.out.print("명언 : ");
                String strQuote = removeSpecialChars(scanner.nextLine().trim());
                System.out.print("작가 : ");
                String strAuthor = removeSpecialChars(scanner.nextLine().trim());
                cnt++;
                Quote quote = new Quote(cnt, strAuthor, strQuote);
                map.put(cnt, quote);
                System.out.println(cnt+"번 명언이 등록되었습니다.");
            }
            if(cmd.equals("목록")){
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");
                for(int i=cnt; i>0; i--){
                    if(map.containsKey(i)) {
                        System.out.println(map.get(i).toString());
                    }
                }
            }
            if(cmd.substring(0,2).equals("삭제")){
                int deleteNum = Integer.parseInt(cmd.substring(cmd.lastIndexOf("=")+1));
                if(map.containsKey(deleteNum)){
                    map.remove(deleteNum);
                    System.out.println(deleteNum+"번 명언이 삭제되었습니다.");
                }else{
                    System.out.println(deleteNum+"번 명언은 존재하지 않습니다.");
                }

            }
            if(cmd.substring(0,2).equals("수정")){
                int modifyNum = Integer.parseInt(cmd.substring(cmd.lastIndexOf("=")+1));
                if(!map.containsKey(modifyNum)){
                    System.out.println(modifyNum+"번 명언은 존재하지 않습니다.");
                }else{
                    Quote origin = map.get(modifyNum);
                    System.out.print("명언(기존) : "+origin.getQuote()+"\n명언 : ");
                    String strQuote = scanner.nextLine().trim();
                    origin.setQuote(strQuote);
                    System.out.print("작가(기존) : "+origin.getAuthor()+"\n작가 : ");
                    String strAuthor = scanner.nextLine().trim();
                    origin.setAuthor(strAuthor);
                    map.put(modifyNum, origin);
                }
            }
        }
        scanner.close();
    }

    //~`!@#$%^&*()_-+{}[]|\:;"'<>/ 제거
    private String removeSpecialChars(String str){
        return str.replaceAll("[^가-힣a-zA-Z0-9 ,.=?]", "");
    }
}
