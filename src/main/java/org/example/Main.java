package org.example;
import java.lang.*;
import java.io.*;
import java.util.ArrayList;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class Main {
    public static void main(String[] args) throws IOException{
        //TIP 캐럿을 강조 표시된 텍스트에 놓고 <shortcut actionId="ShowIntentionActions"/>을(를) 누르면
        // IntelliJ IDEA이(가) 수정을 제안하는 것을 확인할 수 있습니다.
        System.out.println("==명언 앱==");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Quote> quote_list = new ArrayList<>();
        QuoteApp App = QuoteApp.getInstance();

        while(true) {
            System.out.print("명령) ");
            String input = br.readLine();

            if(input.equals("종료")){
                break;
            }else if(input.equals("등록")) {
                App.resisterQuote(br);

            }else if(input.equals("목록")){
                App.printQuoteList();

            }else if(input.startsWith("삭제?id=")){
                String removeId = input.substring("삭제?id=".length());
                App.deleteQuoteById(removeId);

            }else if(input.startsWith("수정?id=")) {
                String editId = input.substring("수정?id=".length());
                App.editQuoteById(br, editId);

            }else{
                System.out.println("잘못된 입력");
            }
        }
    }
}