import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static Scanner sc;

    public static void main(String[] args) {

        sc = new Scanner(System.in);

        System.out.println("== 명언 앱 ==");
        int idx = 1;

        Map<Integer, Quote> quoteMap = new HashMap<>();


        while (true) {
            System.out.print("명령) ");
            String oper = sc.nextLine().trim();

            if (oper.equals("종료")) {
                break;

            } else if (oper.equals("등록")) {

                idx = create(sc, quoteMap, idx);

            } else if (oper.equals("목록")) {

                showList(quoteMap);

            } else if (oper.contains("삭제")) {
                String number = oper.substring(6);
                int i = Integer.parseInt(number);
                delete(quoteMap, i);

            } else if (oper.contains("수정")) {
                String number = oper.substring(6);
                int i = Integer.parseInt(number);
                getChange(quoteMap, i);

            }

        }

    }

    private static void getChange(Map<Integer, Quote> quoteMap, int i) {

        boolean b = quoteMap.containsKey(i);
        if (b) {
            Quote quote = quoteMap.get(i);
            System.out.println("명언(기존) : " + quote.getText());
            System.out.println("명언 : ");
            String newText = sc.nextLine().trim();
            System.out.println("작가(기존) : " + quote.getText());
            System.out.println("작가 :");
            String newAuthor = sc.nextLine().trim();

            quoteMap.put(i, new Quote(newText, newAuthor));
        } else {
            System.out.println(i + "번 명언은 존재하지 않습니다.");
        }
    }

    private static void delete(Map<Integer, Quote> quoteMap, int idx) {

        boolean b = quoteMap.containsKey(idx);

        if (b) {
            quoteMap.remove(idx);
            System.out.println(idx + "번 명언이 삭제되었습니다.");

        } else {
            System.out.println(idx + "번 명언은 존재하지 않는다.");
        }

    }

    private static int create(Scanner sc, Map<Integer, Quote> quoteMap, int idx) {

        System.out.print("명언 :");
        String title = sc.nextLine().trim();

        System.out.print("작가 :");
        String author = sc.nextLine().trim();

        quoteMap.put(idx, new Quote(title, author));

        System.out.println(idx + "번 명언이 등록되었습니다.");

        idx++;// 증가

        return idx;
    }

    private static void showList(Map<Integer, Quote> quoteMap) {

        System.out.println("번호 / 작가 / 명언");
        System.out.println("--------------------");

        for (Integer integer : quoteMap.keySet()) {
            Quote quote = quoteMap.get(integer);
            String author = quote.getAuthor();
            String text = quote.getText();
            System.out.println(integer + " / " + author + " / " + text);
        }
    }


}