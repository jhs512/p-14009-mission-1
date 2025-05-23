import java.util.Map;
import java.util.Scanner;

public class Step8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<Integer, WiseSaying> wiseSayingList = new java.util.HashMap<>();
        int index = 1;
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine().trim();

            if (cmd.equals("종료")) {
                System.out.println("== 종료 ==");
                break;
            }else if (cmd.equals("등록")) {
                System.out.print("명언 : ");
                String famous = inputString(scanner);
                System.out.print("작가 : ");
                String writer = inputString(scanner);
                wiseSayingList.put(index,new WiseSaying(index, famous, writer));
                System.out.println(index + "번 명언이 등록 되었습니다.");
                index++;
            } else if (cmd.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("---------------------");
                for (WiseSaying ws : wiseSayingList.values()) {
                    System.out.println(ws.getId() + " / " + ws.getWriter() + " / " + ws.getContent());
                }
            } else if (cmd.equals("삭제")) {
                System.out.print("삭제?id=");
                int id = Integer.parseInt(scanner.nextLine());
                if (!wiseSayingList.containsKey(id)) {
                    System.out.println("해당 id의 명언이 존재하지 않습니다.");
                    continue;
                }
                wiseSayingList.remove(id);
            } else if (cmd.equals("수정")) {
                System.out.print("수정?id=");
                int id = Integer.parseInt(scanner.nextLine());
                if (!wiseSayingList.containsKey(id)) {
                    System.out.println("해당 id의 명언이 존재하지 않습니다.");
                    continue;
                }
                WiseSaying ws = wiseSayingList.get(id);
                System.out.println("기존 명언 : " + ws.getContent());
                System.out.print("새로운 명언 : ");
                String newFamous = inputString(scanner);
                System.out.println("기존 작가 : " + ws.getWriter());
                System.out.print("새로운 작가 : ");
                String newWriter = inputString(scanner);
                ws.setContent(newFamous);
                ws.setWriter(newWriter);
            }
        }
    }

    private static String inputString(Scanner scanner) {
        String SPECIAL_CHAR_PATTERN = ".*[^a-zA-Z0-9가-힣ㄱ-ㅎㅏ-ㅣ ].*";
        while (true) {
            String str = scanner.nextLine();
            if (str.matches(SPECIAL_CHAR_PATTERN)) {
                System.out.println("특수문자는 입력할 수 없습니다.");
                System.out.print("다시 입력해주세요: ");
            } else {
                return str;
            }
        }
    }
}
