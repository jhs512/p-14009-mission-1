import java.util.ArrayList;
import java.util.Scanner;

public class ex1_wise {
    public static void main(String[] args) {

        System.out.println("== 명언 앱 ==");
        ArrayList<WiseSaying> wiseList = new ArrayList<>();
        int cnt = 0;
        while (true) {
            System.out.print("명령) ");
            Scanner sn = new Scanner(System.in);
            String ans = sn.nextLine();
            System.out.println(ans);

            if (ans.equals("등록")) {
                System.out.print("명언: ");
                String wiseSayingContent = sn.nextLine();
                System.out.print("작가: ");
                String wiseSayingAuth = sn.nextLine();
                System.out.println("%d번 명언이 등록되었습니다.".formatted(cnt + 1));
                cnt++;
                WiseSaying wiseSaying = new WiseSaying();
                wiseSaying.id = cnt;
                wiseSaying.content = wiseSayingContent;
                wiseSaying.author = wiseSayingAuth;
                wiseList.add(wiseSaying);

            } else if (ans.equals("종료")) {
                System.out.println("프로그램을 종료합니다");
                break;
            }
            else if (ans.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("-------------------------------");
                for (int i = wiseList.size() - 1; i >= 0; i--) {
                    if(wiseList.get(i).content == null){
                        continue;
                    }
                    else {
                        System.out.println("%d / %s / %s".formatted(
                                wiseList.get(i).id, wiseList.get(i).author, wiseList.get(i).content));
                    }
                }
            }
            else if(ans.substring(0,6).equals("삭제?id=")){
                int id = Integer.parseInt(ans.substring(6));
                if(wiseList.get(id).content == null){
                    System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
                }
                else {
                    wiseList.get(id-1).content = null;
                    wiseList.get(id-1).author = null;
                    System.out.println("%d번 명언을 삭제했습니다.".formatted(id));
                }
            }
            else if(ans.substring(0,6).equals("수정?id=")){
                int id = Integer.parseInt(ans.substring(6));
                if(wiseList.get(id-1).content == null){
                    System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
                }
                else {
                    System.out.println("명언(기존) : %s".formatted(wiseList.get(id-1).content));
                    System.out.print("명언: ");
                    wiseList.get(id-1).content = sn.nextLine();
                    System.out.println("%d번 명언을 수정했습니다.".formatted(id));
                }
            }

        }
    }
}