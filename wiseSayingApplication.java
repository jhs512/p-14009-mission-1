

import java.util.Scanner;

class wiseSaying
{
    String wiseSaying;
    String author;
    int list_num;
}
public class wiseSayingApplication {

    public static void main(String[] args) {
        System.out.println("== 명언 앱 ==");

        int sayingNum =1;
        int escapeCode  = 0;

        wiseSaying[]  wiseSayingArray = new wiseSaying[0];

        while(true){
            System.out.print("명령) ");
            Scanner  scan= new Scanner(System.in);
            String message = scan.nextLine().trim();

            switch (message)
            {
                case "종료":  {

                    escapeCode =1;
                    break;
                }


                case "등록":  {
                    wiseSaying tmp = new wiseSaying();

                    System.out.print("명언 :");
                    tmp.wiseSaying = scan.nextLine().trim();

                    System.out.print("작가: ");
                    tmp.author = scan.nextLine().trim();

                    tmp.list_num = sayingNum;
                    wiseSayingArray = arrayAdd(wiseSayingArray,tmp);
                    sayingNum++;
                    break;
                }


                case "목록":  {
                    System.out.println("번호\t / 작가\t / 명언");
                    System.out.println("==============================");

                    for(int i = 0;i<wiseSayingArray.length;i++)
                    {
                        System.out.printf("%d\t/ %s\t/ %s\n",   wiseSayingArray[i].list_num,
                                wiseSayingArray[i].author,
                                wiseSayingArray[i].wiseSaying);
                    }

                    break;
                }


                default:    {

                    if (message.length()>=2){

                        if (message.substring(0, 2).equals("삭제")) {

                            int deleteNum = Integer.parseInt( message.substring(6));

                            System.out.printf("delete num = %d\n",deleteNum);

                            int is_found = -1;

                            for(int i  = 0 ;i<wiseSayingArray.length;i++)
                            {
                                if(wiseSayingArray[i].list_num == deleteNum){
                                    is_found = i;
                                }
                            }
                            if(is_found == -1) System.out.printf("%d번 명언은 존재하지않습니다\n",deleteNum);
                            else wiseSayingArray = arrayRemove(wiseSayingArray, is_found);

                            break;
                        }

                        else if (message.substring(0, 2).equals("수정"))
                        {

                            int retouchNum = Integer.parseInt( message.substring(6));

                            System.out.printf("retouch num = %d\n",retouchNum);

                            int is_found = 0;

                            for(int i  = 0 ;i<wiseSayingArray.length;i++)
                            {
                                if(wiseSayingArray[i].list_num == retouchNum){
                                    System.out.printf("명언(기존)\t: %s\n",wiseSayingArray[i].wiseSaying);
                                    System.out.print("명언 : ");
                                    wiseSayingArray[i].wiseSaying = scan.nextLine().trim();

                                    System.out.printf("작가(기존)\t: %s\n",wiseSayingArray[i].author);
                                    System.out.print("작가 : ");
                                    wiseSayingArray[i].author = scan.nextLine().trim();

                                    System.out.println("수정되었습니다");
                                    is_found = 1;
                                }
                            }

                            if(is_found == 0) System.out.printf("%d번 명언은 존재하지않습니다\n",retouchNum);

                            break;
                        }

                        else break;
                    }
                }
            }

            if (escapeCode ==1 )break;
        }

    }
    static wiseSaying[] arrayAdd(wiseSaying[] arr, wiseSaying saying)
    {
        wiseSaying[] tmp = new wiseSaying[arr.length+1];
        for(int i = 0 ;i<arr.length; i++){
            tmp[i] = arr[i];
        }
        tmp[arr.length] = saying;
        return tmp;
    }

    static wiseSaying[] arrayRemove(wiseSaying[] arr, int num)
    {
        wiseSaying[] tmp = new wiseSaying[arr.length-1];
        for(int i = 0,j = 0; i<arr.length;i++,j++)
        {
            if (i != num)tmp[j] = arr[i];
            else j--;
        }
        return tmp;
    }
    static void backupData(wiseSaying[] arr,int num)
    {

    }

}