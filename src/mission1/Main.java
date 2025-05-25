package mission1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("== 명언 앱 ==");

        Scanner scanner = new Scanner(System.in);

        WiseSaying[] saying = new WiseSaying[100];
        int id = 0;

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

                WiseSaying wiseSaying = new WiseSaying();
                wiseSaying.id = id + 1;
                wiseSaying.content = wiseSayingContent;
                wiseSaying.author = wiseSayingAuthor;

                saying[id] = wiseSaying;
                id++;

                System.out.println(wiseSaying.id + "번 명언이 등록되었습니다.");
            } else if (cmd.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");

                for (int i = id - 1; i >= 0; i--) {
                    WiseSaying wiseSaying = saying[i];
                    if(wiseSaying == null) continue;
                    System.out.println(wiseSaying.id + "/" + wiseSaying.author + "/" + wiseSaying.content);
                }
            } else if (cmd.startsWith("삭제?id=")) {
                String[] parts = cmd.split("=");
                int deleteid = Integer.parseInt(parts[1]);
                boolean found = false;

                for (int i = 0; i < id; i++) {
                    if (saying[i] != null && saying[i].id == deleteid) {
                        saying[i] = null;
                        found = true;
                        System.out.println(deleteid + "번 명언이 삭제되었습니다.");
                        break;
                    }
                }
                if (!found) {
                    System.out.println(deleteid + "번 명언은 존재하지 않습니다.");
                }
            } else if (cmd.startsWith("수정?id=")) {
                String[] parts = cmd.split("=");
                int updateid = Integer.parseInt(parts[1]);
                boolean found = false;
                for (int i = 0; i < id; i++) {
                    if (saying[i] != null && saying[i].id == updateid) {
                        found = true;
                        System.out.println("명언(기존) : " + saying[i].content);
                        System.out.print("명언 : " );
                        String newcontent = scanner.nextLine().trim();

                        System.out.println("작가(가존) : " + saying[i].author);
                        System.out.print("작가 : ");
                        String newauthor = scanner.nextLine().trim();

                        saying[i].content = newcontent;
                        saying[i].author = newauthor;

                        System.out.println(updateid + "번 명언이 수정되었습니다.");
                        break;
                    }
                }
                if (!found) {
                    System.out.println(updateid + "번 명언은 존재하지 않습니다.");
                }

            }
        }
        scanner.close();
    }
}
