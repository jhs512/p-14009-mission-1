import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Proverb {

  private static final ProverbManager proverbManager = new ProverbManager();

  public static void main(String[] args) {
    System.out.println("== 명언 앱 ==");
    Scanner scanner = new Scanner(System.in);

    List<WiseSaying> wiseSayingList;
    try {
      wiseSayingList = proverbManager.fetchProverbs();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    TreeSet<Integer> listCheckSet = new TreeSet<>();
    for (int i = 1; i <= wiseSayingList.size() - 1; i++) {
      listCheckSet.add(wiseSayingList.get(i).id);
    }

    label:
    while (true) {
      System.out.print("명령) ");
      String cmd = scanner.nextLine().trim();
      String cmdType = cmd.substring(0, 2);

      switch (cmdType) {

        // 1단계
        case "종료":
          break label;

        // 2~4단계
        case "등록":
          try {
            wiseSayingList.add(makeProv(proverbManager.getNextId(), listCheckSet, scanner));
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
          break;

        // 5단계
        case "목록":
          getAllProv(wiseSayingList);
          break;

        // 6,7단계
        case "삭제":
          int removeId = Integer.parseInt(cmd.split("=")[1]);
          try {
            deleteProv(removeId, wiseSayingList, listCheckSet);
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
          break;

        // 8단계
        case "수정":
          int modifyId = Integer.parseInt(cmd.split("=")[1]);
          try {
            modifyProv(modifyId, wiseSayingList, listCheckSet, scanner);
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
          break;

        // 10단계
        case "빌드":
          try {
            proverbManager.makeWiseSayingFile();
            System.out.println("data.json 파일의 내용이 갱신되었습니다.");
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
          break;
      }
    }

    scanner.close();
  }

  private static WiseSaying makeProv(int registerId, Set<Integer> listCheckSet, Scanner scanner)
      throws IOException {
    while (true) {
      System.out.print("명언 : ");
      String proverb = scanner.nextLine().trim();

      if (containsSpecificChar(proverb)) {
        while (true) {
          System.out.print("작가 : ");
          String author = scanner.nextLine().trim();

          if (containsSpecificChar(author)) {
            listCheckSet.add(registerId);
            proverbManager.saveProverb(registerId, proverb, author);
            System.out.println(registerId + "번 명언이 등록되었습니다.");
            return new WiseSaying(registerId, proverb, author);
          }

          System.out.println("작가에 특수문자를 제외하고 입력해주세요.");
        }
      }

      System.out.println("명언에 특수문자를 제외하고 입력해주세요.");
    }
  }

  private static void getAllProv(List<WiseSaying> wiseSayingList) {
    System.out.println("번호 / 작가 / 명언");
    System.out.println("----------------");

    for (int idx = wiseSayingList.size() - 1; idx >= 1; idx--) {
      System.out.println(wiseSayingList.get(idx).id + " / "
          + wiseSayingList.get(idx).author + " / " + wiseSayingList.get(idx).content);
    }
  }

  private static void deleteProv(
      int removeId,
      List<WiseSaying> wiseSayingList,
      TreeSet<Integer> listCheckSet
  ) throws IOException {
    if (listCheckSet.contains(removeId)) {
      for (int i = 1; i <= wiseSayingList.size() - 1; i++) {
        if (wiseSayingList.get(i).id == removeId) {
          wiseSayingList.remove(wiseSayingList.get(i));
        }
      }

      listCheckSet.remove(removeId);
      proverbManager.deleteProverb(removeId);
      System.out.println(removeId + "번 명언이 삭제되었습니다.");
    } else {
      System.out.println(removeId + "번 명언은 존재하지 않습니다.");
    }
  }

  private static void modifyProv(
      int modifyId,
      List<WiseSaying> wiseSayingList,
      TreeSet<Integer> listCheckSet,
      Scanner scanner
  ) throws IOException {
    if (listCheckSet.contains(modifyId)) {
      int wiseSayingListIdx = 0;
      for (int i = 1; i <= wiseSayingList.size() - 1; i++) {
        if (wiseSayingList.get(i).id == modifyId) {
          wiseSayingListIdx = i;
          break;
        }
      }

      System.out.println("명언(기존) : " + wiseSayingList.get(wiseSayingListIdx).content);
      WiseSaying newWiseSaying = new WiseSaying();
      newWiseSaying.id = modifyId;

      while (true) {
        System.out.print("명언 : ");
        String newProverb = scanner.nextLine().trim();

        if (containsSpecificChar(newProverb)) {
          newWiseSaying.content = newProverb;
          System.out.println("작가(기존) : " + wiseSayingList.get(wiseSayingListIdx).author);

          while (true) {
            System.out.print("작가 : ");
            String newAuthor = scanner.nextLine().trim();

            if (containsSpecificChar(newAuthor)) {
              newWiseSaying.author = newAuthor;
              proverbManager.saveProverb(modifyId, newProverb, newAuthor);
              wiseSayingList.set(wiseSayingListIdx, newWiseSaying);
              return;
            }

            System.out.println("명언에 특수문자를 제외하고 입력해주세요.");
          }
        }

        System.out.println("명언에 특수문자를 제외하고 입력해주세요.");
      }
    } else {
      System.out.println(modifyId + "번 명언은 존재하지 않습니다.");
    }
  }

  private static boolean containsSpecificChar(String text) {
    Pattern pattern = Pattern.compile("[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?`~]");
    Matcher matcher = pattern.matcher(text);
    return !matcher.find();
  }
}
