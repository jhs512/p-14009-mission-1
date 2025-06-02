import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    List<WiseSaying> wiseSayingList = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    private static int nextId = 0;

    /**
     * 명언 앱 실행
     */
    public void run() {
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine();
            if (cmd.equals("종료")) {
                break;
            } else if (cmd.equals("등록")) {
                createWiseSaying();
            } else if (cmd.equals("목록")) {
                printWiseSayingList();
            } else if (cmd.startsWith("삭제?id=")) {
                deleteWiseSaying(getCmdId(cmd));
            } else if (cmd.startsWith("수정?id=")) {
                modifyWiseSaying(getCmdId(cmd));
            }
        }
        sc.close();
    }

    /**
     * 명언 등록
     */
    private void createWiseSaying() {
        System.out.print("명언 : ");
        String content = sc.nextLine();

        System.out.print("작가 : ");
        String author = sc.nextLine();

        WiseSaying wiseSaying = new WiseSaying(++nextId, content, author);
        wiseSayingList.add(wiseSaying);
        System.out.printf("%d번 명언이 등록되었습니다.%n", wiseSaying.getId());
    }

    /**
     * 명언 목록 조회
     */
    private void printWiseSayingList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        for (WiseSaying wiseSaying : wiseSayingList.reversed()) {
            System.out.printf("%d / %s / %s%n", wiseSaying.getId(), wiseSaying.getAuthor(), wiseSaying.getContent());
        }
    }

    /**
     * 명언 수정
     * @param cmdId
     */
    private void modifyWiseSaying(long cmdId) {
        if (cmdId == -1) {
            return;
        }

        boolean hasId = false;
        for (WiseSaying wiseSaying : wiseSayingList) {
            if (wiseSaying.getId() == cmdId) {
                hasId = true;

                System.out.printf("명언(기존) : %s%n", wiseSaying.getContent());
                System.out.print("명언 : ");
                wiseSaying.setContent(sc.nextLine());

                System.out.printf("작가(기존) : %s%n", wiseSaying.getAuthor());
                System.out.print("작가 : ");
                wiseSaying.setAuthor(sc.nextLine());

                System.out.printf("%d번 명언이 수정되었습니다.%n", cmdId);
                break;
            }
        }

        if (!hasId) {
            System.out.printf("%d번 명언은 존재하지 않습니다.%n", cmdId);
        }
    }

    /**
     * 명언 삭제
     * @param cmdId
     */
    private void deleteWiseSaying(long cmdId) {
        if (cmdId == -1) {
            return;
        }

        boolean hasId = false;
        for (int i = 0; i < wiseSayingList.size(); i++) {
            if (wiseSayingList.get(i).getId() == cmdId) {
                hasId = true;
                wiseSayingList.remove(i);

                System.out.printf("%d번 명언이 삭제되었습니다.%n", cmdId);
                break;
            }
        }

        if (!hasId) {
            System.out.printf("%d번 명언은 존재하지 않습니다.%n", cmdId);
        }
    }

    /**
     * 삭제할 명언 id 추출 (삭제?id=)
     * @param cmd
     * @return cmdId (오입력 시 -1)
     */
    private long getCmdId(String cmd) {
        String[] parts = cmd.split("\\?id=");
        if (parts.length != 2) {
            return -1;
        }
        try {
            return Long.parseLong(parts[1]);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
