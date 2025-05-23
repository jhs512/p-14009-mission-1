package level6;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<Integer, Wisesaying> map = new HashMap<>();

        System.out.println("== 명언 앱 ==");
        int cnt = 1;

        while (true) {
            System.out.print("명령) ");
            String command = scanner.nextLine();
            switch (command) {
                case "등록":
                    System.out.print("명언 : ");
                    String wiseSaying = scanner.nextLine();
                    System.out.print("작가 : ");
                    String wiseSayingAuthor = scanner.nextLine();
                    System.out.println(cnt + "번 명언이 등록되었습니다.");
                    Wisesaying temp = new Wisesaying(wiseSaying, wiseSayingAuthor);
                    map.put(cnt, temp);
                    cnt++;
                    break;
                case "종료":
                    return;
                case "목록":
                    System.out.println("번호 / 작가 / 명언");
                    System.out.println("-------------------------");
                    for (int i = cnt - 1; i > 0; i--) {
                        Wisesaying wisesaying = map.get(i);
                        if (map.containsKey(i)) {
                            System.out.println(i + " / " + wisesaying.wisesayingAuthor + " / " + wisesaying.wisesaying);
                        }
                    }
                    break;
                default:
                    if (command.startsWith("삭제")) {
                        String[] parts = command.split("=");
                        if (parts.length == 2) {
                            int id = Integer.parseInt(parts[1]);
                            if (map.containsKey(id)) {
                                map.remove(id);
                                System.out.println(id + "번 명언이 삭제되었습니다.");
                            }
                        }
                    }

            }
        }
    }

}
