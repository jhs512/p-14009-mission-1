import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(" == 명언 앱 == ");

        String[] WiseSaying = new String[100];
        String[] author = new String[100];
        int lastId = 0;

        while(true) {
            System.out.print("명령) ");
            String str = scanner.nextLine().trim();

            String order;
            String Rq;

            if(str.contains("?")) {
                String[] parts = str.split("\\?");
                order = parts[0].trim();
                Rq = parts[1].trim();
            } else {
                order = str.trim();
                Rq = "";
            }

            switch(order) {
                case "등록":
                    System.out.print("명언 : ");
                    WiseSaying[lastId] = scanner.nextLine();
                    System.out.print("작가 : ");
                    author[lastId] = scanner.nextLine();
                    System.out.printf("%d번 명언이 등록되었습니다.\n", ++lastId);
                    break;

                case "목록":
                    System.out.println("--------------------");
                    for(int i = lastId-1; i >= 0; i--) {
                        if(WiseSaying[i] != null) {
                            System.out.printf("%d / %s / %s\n", i+1, WiseSaying[i], author[i]);
                        }
                    }
                    break;

                case "삭제":
                case "수정":
                    int id = -1;
                    if (Rq.startsWith("id=")) {
                        try {
                            id = Integer.parseInt(Rq.substring(3)) - 1;
                        } catch (NumberFormatException e) {
                            System.out.println("잘못된 ID 형식입니다.");
                            break;
                        }
                    }

                    if (id < 0 || id >= lastId || WiseSaying[id] == null) {
                        System.out.printf("%d번 명언은 존재하지 않습니다.\n", id + 1);
                        break;
                    }

                    if (order.equals("삭제")) {
                        WiseSaying[id] = null;
                        author[id] = null;
                        System.out.printf("%d번 명언이 삭제되었습니다.\n", id + 1);
                    } else {
                        System.out.print("명언 : ");
                        WiseSaying[id] = scanner.nextLine();
                        System.out.print("작가 : ");
                        author[id] = scanner.nextLine();
                        System.out.printf("%d번 명언이 수정되었습니다.\n", id + 1);
                    }
                    break;

                case "종료":
                    scanner.close();
                    return;
            }
        }
    } // main
} // Main
