import java.io.IOException;
import java.nio.file.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class ProverbManager {

  private final String WISE_SAYING_DIR = "db/wiseSaying/";
  private final String LAST_ID_FILE_PATH = WISE_SAYING_DIR + "/lastId.txt";

  public void saveProverb(int registerId, String content, String author) throws IOException {
    Files.createDirectories(Paths.get(WISE_SAYING_DIR));

    String jsonProverb = createJson(registerId, content, author);
    Path newFilePath = Paths.get(WISE_SAYING_DIR + registerId + ".json");
    Files.writeString(newFilePath, jsonProverb, StandardOpenOption.CREATE);

    updateLastId(Math.max(registerId, getNextId() - 1));
  }

  public List<WiseSaying> fetchProverbs() throws IOException {
    List<WiseSaying> wiseSayingList = new ArrayList<>();
    wiseSayingList.add(new WiseSaying(0, "", ""));

    int lastId;
    try {
      lastId = Integer.parseInt(Files.readString(Paths.get(LAST_ID_FILE_PATH)));
    } catch (NoSuchFileException e) {
      return wiseSayingList;
    }

    for (int i = 1; i <= lastId; i++) {
      Path wiseSayingFilePath = Paths.get(WISE_SAYING_DIR + i + ".json");
      if (!Files.exists(wiseSayingFilePath)) {
        continue;
      }

      String jsonWiseSaying = Files.readString(wiseSayingFilePath);
      String[] parts = jsonWiseSaying.split("\n");

      int wiseSayingElementSize = 3;
      String[] wiseSayingElements = new String[wiseSayingElementSize];

      for (int j = 1; j <= wiseSayingElementSize; j++) {
        String part = parts[j].split(": ")[1];
        if (part.charAt(0) == '\"') {
          int lastQuoteIndex = 0;
          for (int k = part.length() - 1; k >= 0; k--) {
            if (part.charAt(k) == '\"') {
              lastQuoteIndex = k;
              break;
            }
          }

          wiseSayingElements[j - 1] = part.substring(1, lastQuoteIndex);
        } else {
          wiseSayingElements[j - 1] = part.substring(0, part.length() - 1);
        }
      }

      wiseSayingList.add(new WiseSaying(
          Integer.parseInt(wiseSayingElements[0]), wiseSayingElements[1], wiseSayingElements[2]));
    }

    return wiseSayingList;
  }

  public void deleteProverb(int removeId) throws IOException {
    Path wiseSayingFilePath = Paths.get(WISE_SAYING_DIR + removeId + ".json");
    Files.deleteIfExists(wiseSayingFilePath);
  }

  public int getNextId() throws IOException {
    try {
      String lastID = Files.readString(Paths.get(LAST_ID_FILE_PATH));
      return Integer.parseInt(lastID) + 1;
    } catch (NoSuchFileException e) {
      return 1;
    }
  }

  public void makeWiseSayingFile() throws IOException {
    Files.createDirectories(Paths.get(WISE_SAYING_DIR));

    StringBuilder wiseSayingListString = new StringBuilder();
    boolean isFirstFile = true;
    wiseSayingListString.append("[\n  ");
    for (int i = 1; i < getNextId(); i++) {
      Path wiseSayingJsonFilePath = Paths.get(WISE_SAYING_DIR + i + ".json");
      if (Files.exists(wiseSayingJsonFilePath)) {
        String wiseSayingJsonFile = Files.readString(wiseSayingJsonFilePath);
        String indentedWiseSayingJsonFile = wiseSayingJsonFile.replaceAll("\n", "\n  ");

        if (isFirstFile) {
          isFirstFile = false;
        } else {
          wiseSayingListString.append(",\n  ");
        }

        wiseSayingListString.append(indentedWiseSayingJsonFile);
      }
    }

    wiseSayingListString.append("\n]");

    if (isFirstFile) {
      wiseSayingListString.setLength(0);
      wiseSayingListString.append("[]");
    }

    Files.writeString(Paths.get(WISE_SAYING_DIR + "data.json"), wiseSayingListString,
        StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
  }

  private String createJson(int id, String content, String author) {
    String template = """
        '{'
          "id": {0},
          "content": "{1}",
          "author": "{2}"
        '}'""";

    return MessageFormat.format(template, id, content, author);
  }

  private void updateLastId(int id) throws IOException {
    Files.writeString(Paths.get(LAST_ID_FILE_PATH), String.valueOf(id), StandardOpenOption.CREATE,
        StandardOpenOption.TRUNCATE_EXISTING);
  }
}
