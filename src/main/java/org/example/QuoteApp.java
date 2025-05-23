package org.example;

import java.io.*;
import java.util.*;
import com.google.gson.Gson;

public class QuoteApp {
    private static QuoteApp instance = new QuoteApp();
    private ArrayList<Quote> quote_list = new ArrayList<>();
    int id = 1;
    private String dbFilePath = "db/wiseSaying/";

    private QuoteApp(){}
    public static QuoteApp getInstance(){
        return instance;
    }
    public void resisterQuote(BufferedReader br) throws IOException{
        System.out.print("명언 : ");
        String content = br.readLine();
        System.out.print("작가 : ");
        String author = br.readLine();
        Quote quote = new Quote(id, author, content);
        quote_list.add(quote);
        writeFile(String.valueOf(id));
        System.out.println(quote.getId() + "번 명언이 등록되었습니다.");
        id++;
    }
    public void editQuoteById(BufferedReader br ,String editId) throws IOException{
        boolean isExist = false;
        for (Quote quote : quote_list) {
            if (quote.getId() == Integer.parseInt(editId)) {
                System.out.println("명언(기존) : " + quote.getContent());
                System.out.print("명언 :");
                String editContent = br.readLine();
                quote.setContent(editContent);
                System.out.println("작가(기존) : " + quote.getAuthor());
                System.out.print("작가 : ");
                String editAuthor = br.readLine();
                quote.setAuthor(editAuthor);
                isExist = true;
            }
        }
        if(!isExist){
            System.out.println( editId + "번 명언은 존재하지 않습니다.");

        }
    }
    public void deleteQuoteById(String removeId){
        boolean isExist = false;

        for(int i = 0; i<quote_list.size();i++){
            if(quote_list.get(i).getId() == Integer.parseInt(removeId)){
                quote_list.remove(i);
                System.out.println( removeId + "번 명언이 삭제 되었습니다.");
                isExist = true;
            }
        }
        if(!isExist){
            System.out.println( removeId + "번 명언은 존재하지 않습니다.");

        }
    }
    public void  printQuoteList(){
        System.out.println("번호 / 작가 / 명언 ");
        System.out.println("----------------------");
        for(int i = quote_list.size() - 1 ; i >= 0; i--){
            System.out.println( quote_list.get(i).getId() + " / " + quote_list.get(i).getAuthor() + " / " + quote_list.get(i).getContent());
        }
    }

    public void writeFile(String fileId) throws IOException{
        File dir = new File(dbFilePath);
        if(!dir.exists()){
            dir.mkdirs();
        }
        File file = new File(dbFilePath + fileId + ".json");
        if(!file.createNewFile()){
            System.out.println(fileId + ".json 생성예정");
        }else {
            System.out.println(fileId + ".json 등록 및 저장 예정");
        }

    }
    public void editFile(String fileName){

    }
    public void deleteFile(String fileName){

    }
}
