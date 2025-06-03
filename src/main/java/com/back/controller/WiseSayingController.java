package com.back.controller;

import com.back.domain.WiseSaying;
import com.back.enums.Command;
import com.back.service.WiseSayingService;
import com.back.view.InputView;
import com.back.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class WiseSayingController {

    private final InputView inputView;
    private final OutputView outputView;
    private final WiseSayingService wiseSayingService;

    private List<WiseSaying> wiseSayings = new ArrayList<>();
    private Long indexID = 0L;

    public WiseSayingController(InputView inputView, OutputView outputView, WiseSayingService wiseSayingService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.wiseSayingService = wiseSayingService;
    }

    public void run() {
        while (true) {
            String[] command = {"1", "2"};// 이거 utils - parser에서 가공
            // 명령어 예외처리
                // 1. Command에 없는 명령어인 경우
                // 2. 삭제 및 수정에서, 존재하지 않는 ID를 입력한 경우
            if (command.equals(Command.종료.name())) {
                break;
            }
            if (command.equals(Command.등록.name())) {
                String content = inputView.readNewContent();
                String author = inputView.readNewAuthor();
                wiseSayingService.addWiseSaying(wiseSayings, new WiseSaying(++indexID, content, author));
                outputView.printWiseSayingById(indexID);
            }
            if (command.equals(Command.목록.name())) {
                outputView.printAllWiseSayings(wiseSayings);
            }
            if (command.equals(Command.수정.name())) {
                WiseSaying oldWiseSaying = getWiseSayingById(Long.parseLong(command[1]));
                outputView.printOldContent(oldWiseSaying);
                String newContent = inputView.readNewContent();
                outputView.printOldAuthor(oldWiseSaying);
                String newAuthor = inputView.readNewAuthor();
                wiseSayingService.changeWiseSaying(oldWiseSaying, newContent, newAuthor);

            }
            if (command.equals(Command.삭제.name())) {
                outputView.printDeleteWiseSayingById(Long.parseLong(command[1]));
            }
        }
    }

    public WiseSaying getWiseSayingById(Long id) {
        return wiseSayings.stream()
                .filter(wiseSaying -> wiseSaying.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
