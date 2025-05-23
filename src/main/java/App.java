import java.util.ArrayList;
import java.util.Scanner;

public class App {
    ArrayList<MyClass> list = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    int num = 1;

    public void run() {
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String command = sc.nextLine().trim();

            if (command.equals("종료")) break;
            else if (command.equals("등록")) register();
            else if (command.equals("목록")) showList();
            else if (command.contains("삭제?id=")) delete(command);
            else if (command.contains("수정?id=")) update(command);
            else System.out.println("다시 입력해주세요");
        }
    }

    public void register() {
        System.out.print("명언 : ");
        String content = sc.nextLine().trim();
        System.out.print("작가 : ");
        String author = sc.nextLine().trim();
        list.add(new MyClass(num, content, author));
        System.out.println(num + "번 명언이 등록되었습니다.");
        num++;
    }

    public void showList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("-------------------");
        for (int i = list.size() - 1; i >= 0; i--) {
            MyClass m = list.get(i);
            System.out.println(m.id + " / " + m.author + " / " + m.content);
        }
    }

    public void delete(String command) {
        int id = Integer.parseInt(command.substring(6));
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).id == id) {
                list.remove(i);
                System.out.println(id + "번 명언이 삭제되었습니다.");
                return;
            }
        }
        System.out.println(id + "번 명언은 존재하지 않습니다.");
    }

    public void update(String command) {
        int id = Integer.parseInt(command.substring(6));

        for (MyClass m : list) {
            if (m.id == id) {
                System.out.println("명언(기존) : " + m.content);
                System.out.print("명언 : ");
                m.content = sc.nextLine().trim();

                System.out.println("작가(기존) : " + m.author);
                System.out.print("작가 : ");
                m.author = sc.nextLine().trim();

                System.out.println(id + "번 명언이 수정되었습니다.");
                return;
            }
        }
        System.out.println(id + "번 명언은 존재하지 않습니다.");
    }

    void showlist() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("-------------------");
        for (int i = list.size() - 1; i >= 0; i--)
            System.out.println(list.get(i).id + " / " + list.get(i).author + " / " + list.get(i).content);
    }

}
