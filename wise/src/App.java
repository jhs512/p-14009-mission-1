import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int val = 1000;
        String[] author = new String[val];
        String[] wise = new String[val];

        System.out.println("== 명언 앱 ==");

        int num = 0;
        while (true) {
            System.out.print("명령) ");
            String s = sc.next();

            if (s.equals("종료")) {
                break;
            } else if (s.equals("등록")) {
                num++;

                System.out.print("명언 : ");
                wise[num] = sc.next();
                System.out.print("작가 : ");
                author[num] = sc.next();
                System.out.println(num + "번 명언이 등록되었습니다.");
            } else if (s.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");

                for (int i = num; i >= 1; i--) {
                    if (author[i] == null) {
                        continue;
                    }

                    System.out.println(i + " / " + author[i] + " / " + wise[i]);
                }
            } else if (s.substring(0, 6).equals("삭제?id=")) {
                int n = Integer.parseInt(s.substring(6));

                if (author[n] == null) {
                    System.out.println(n + "번 명언이 존재하지 않습니다.");
                } else {
                    author[n] = null;
                    System.out.println(n + "번 명언이 삭제되었습니다.");
                }
            } else if (s.substring(0, 6).equals("수정?id=")) {
                int n = Integer.parseInt(s.substring(6));

                if (author[n] == null) {
                    System.out.println(n + "번 명언이 존재하지 않습니다.");
                } else {
                    System.out.println("명언(기존) : " + wise[n]);
                    System.out.print("명언 : ");
                    wise[n] = sc.next();

                    System.out.println("작가(기존) : " + author[n]);
                    System.out.print("작가 : ");
                    author[n] = sc.next();
                }
            }
        }
    }
}
