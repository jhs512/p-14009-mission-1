package mission.mission1;

import java.util.Scanner;

class WiseSaying {
    int id;
    String say;
    String author;
}

public class Step{
    Scanner sc = new Scanner(System.in);

    void step8(){
        System.out.println("== 명언 앱 =="); // 명언 앱 시작
        int sayI = 0; // 명언 목록 시작 번호
        WiseSaying[] wsl = new WiseSaying[100]; // 명언 목록을 저장할 배열
        while (true){
            System.out.print("명령) ");
            String cmd = sc.nextLine().trim();
            if (cmd.equals("등록")){
                WiseSaying ws = new WiseSaying(); // 명언 객체 생성
                ws.id = sayI + 1; // 명언 ID 설정
                System.out.print("명언: ");
                String say = sc.nextLine().trim();
                ws.say = say;

                System.out.print("작가: ");
                String author = sc.nextLine().trim();
                ws.author = author;
                wsl[sayI] = ws; // 명언 객체를 배열에 저장
                sayI++;
                System.out.printf("%d번 명언이 등록되었습니다.\n", sayI);

            } else if(cmd.equals("종료")){
                break;
            } else if(cmd.equals("목록")){
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");
                for (int i=wsl.length-1; i>=0; i--){
                    if (wsl[i] != null){
                        System.out.printf("%d / %s / %s\n", wsl[i].id, wsl[i].author, wsl[i].say);
                    }
                }
            } else if(cmd.startsWith("삭제")) {
                String[] delete = cmd.split("=");
                if (delete.length<2 || delete[1].isEmpty()){
                    System.out.println("id를 입력해주세요.");
                    continue;
                }
                int id = Integer.parseInt(delete[1]);
                int deleteIndex = -1;
                for(int i=0; i<=sayI; i++){
                    if(wsl[i] != null && wsl[i].id == id){
                        deleteIndex = i;
                        break;
                    }
                }
                if (deleteIndex == -1) {
                    System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
                } else {
                    System.out.printf("%d번 명언이 삭제되었습니다.\n", wsl[deleteIndex].id);
                    wsl[deleteIndex] = null; // 해당 명언을 삭제
                }


            } else if(cmd.startsWith("수정")) {
                String[] modify = cmd.split("=");
                if (modify.length<2 || modify[1].isEmpty()) {
                    System.out.println("id를 입력해주세요.");
                    continue;
                }
                int id = Integer.parseInt(modify[1]);
                int modifyIndex = -1;

                for(int i=0; i<=sayI; i++){
                    if(wsl[i] != null && wsl[i].id == id){
                        modifyIndex = i;
                        break;
                    }
                }

                if (modifyIndex == -1) {
                    System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
                } else {
                    System.out.printf("명언(기존) : %s\n", wsl[modifyIndex].say);
                    System.out.print("명언: ");
                    String newSay = sc.nextLine().trim();
                    System.out.printf("작가(기존) : %s\n", wsl[modifyIndex].author);
                    System.out.print("작가: ");
                    String newAuthor = sc.nextLine().trim();
                    wsl[modifyIndex].say = newSay;
                    wsl[modifyIndex].author = newAuthor;
                }
            }
        }
    }
}


