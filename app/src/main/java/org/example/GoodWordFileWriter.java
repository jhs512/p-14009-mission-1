package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileWriter {
    private Path resourcePath = Path.of("./app/src/main/resources/db/wiseSaying/");

    void writeJson(GoodWordEntry entry) {
        try {
            // TODO 상대 경로로 바꿀 것. 더불어서 경로 생성도
            Path path = Files.createDirectories(resourcePath);

            Files.writeString(path.resolve(entry.getId() + ".json"), entry.toJson());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void writeListId(int id) {
        try {
            // TODO 상대 경로로 바꿀 것. 더불어서 경로 생성도
            Path path = Files.createDirectories(resourcePath);

            Files.writeString(path.resolve("lastId.txt"), String.valueOf(id));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void writeData(List<GoodWordEntry> goodWordEntryList) {
        try {
            Path path = Files.createDirectories(resourcePath);
            String data = JsonBuilder.getGoodWordJsonData(goodWordEntryList);

            Files.writeString(path.resolve("data.json"), data);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
