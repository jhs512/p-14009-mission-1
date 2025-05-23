import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("== 명언 앱 ==");

        int no = 0;
        String[][] list = new String[100][3];

        Scanner scanner = new Scanner(System.in);

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

                list[no][0] = String.valueOf(no + 1);
                list[no][1] = wiseSayingContent;
                list[no][2] = wiseSayingAuthor;

                System.out.println("%d번 명언이 등록되었습니다.".formatted(no + 1));
                no++;



            } else if (cmd.equals("목록")) {
                System.out.println("번호 / 작가 / 명언\n--------------------");
                for (int i = no - 1; i >= 0; i--) {
                    if (list[i][0] != null) {
                        System.out.println(list[i][0] + " / " + list[i][2] + " / " + list[i][1]);
                    }
                }



            } else if (cmd.startsWith("삭제?id=")) {
                try {
                    int delNo = Integer.parseInt(cmd.substring("삭제?id=".length()));

                    if (list[delNo - 1][0] != null) {
                        for (int i = 0; i <= 2; i++) {
                            list[delNo - 1][i] = null;
                        }
                        System.out.println(delNo + "번 명언이 삭제되었습니다.");
                    } else {
                        System.out.println(delNo + "번 명언은 존재하지 않습니다.");
                    }

                } catch (NumberFormatException e) {
                    System.out.println("잘못된 형식입니다. 예: 삭제?id=1");
                }



            } else if (cmd.startsWith("수정?id=")) {
                try {
                    int upNo = Integer.parseInt(cmd.substring("수정?id=".length()));

                    if (list[upNo - 1][0] != null) {
                        System.out.println("명언(기존) : " + list[upNo - 1][1]);
                        System.out.print("명언 : ");
                        String wiseSayingContent = scanner.nextLine().trim();
                        list[upNo - 1][1] = wiseSayingContent;

                        System.out.println("작가(기존) : " + list[upNo - 1][2]);
                        System.out.print("작가 : ");
                        String wiseSayingAuthor = scanner.nextLine().trim();
                        list[upNo - 1][2] = wiseSayingAuthor;

                        System.out.println(upNo + "번 명언이 수정되었습니다.");
                    } else {
                        System.out.println(upNo + "번 명언은 존재하지 않습니다.");
                    }

                } catch (NumberFormatException e) {
                    System.out.println("잘못된 형식입니다. 예: 수정?id=1");
                }
            }
        }

        scanner.close();

    }
}
