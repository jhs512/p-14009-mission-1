package com.back.controller;

import com.back.domain.WiseSaying;
import com.back.enums.Command;
import com.back.repository.WiseSayingRepository;
import com.back.service.WiseSayingService;
import com.back.utils.InputParser;
import com.back.utils.Validator;
import com.back.view.InputView;
import com.back.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class WiseSayingController {

    private final InputView inputView;
    private final OutputView outputView;
    private final WiseSayingService wiseSayingService;
    private final WiseSayingRepository wiseSayingRepository;

    private List<WiseSaying> wiseSayings = new ArrayList<>();
    private Long indexID = 0L;

    public WiseSayingController(InputView inputView, OutputView outputView, WiseSayingService wiseSayingService, WiseSayingRepository wiseSayingRepository) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.wiseSayingService = wiseSayingService;
        this.wiseSayingRepository = wiseSayingRepository;
    }

    public void run() {
        while (true) {
            outputView.printStartWiseSayingApp();
            String[] command = InputParser.parseInput(inputView.readCommand());// 이거 utils - parser에서 가공
            // 명령어 예외처리
            // 1. Command에 없는 명령어인 경우
            if (!Validator.isValidCommand(command)) {
                outputView.printInvalidCommand();
                continue;
            }
            // 2. 삭제 및 수정에서, 존재하지 않는 ID를 입력한 경우
            if (command[0].matches("^(삭제|수정)$") && !Validator.isValidIdInWiseSayings(command, wiseSayings)) {
                outputView.printWiseSayingDoesNotExist(Long.parseLong(command[1]));
                continue;
            }
            if (command[0].equals(Command.종료.getCommand())) {
                break;
            }
            if (command[0].equals(Command.등록.getCommand())) {
                add();
                continue;
            }
            if (command[0].equals(Command.목록.getCommand())) {
                outputView.printAllWiseSayings(wiseSayings);
                continue;
            }
            if (command[0].equals(Command.수정.getCommand())) {
                change(command);
                continue;
            }
            if (command[0].equals(Command.삭제.getCommand())) {
                delete(command);
                continue;
            }
        }
    }

    public void add() {
        String content = inputView.readNewContent();
        String author = inputView.readNewAuthor();
        wiseSayingService.addWiseSaying(wiseSayings, new WiseSaying(++indexID, content, author));
        outputView.printWiseSayingById(indexID);
    }

    public void change(String[] command) {
        WiseSaying oldWiseSaying = wiseSayingRepository.getWiseSayingById(wiseSayings, Long.parseLong(command[1]));
        outputView.printOldContent(oldWiseSaying);
        String newContent = inputView.readNewContent();
        outputView.printOldAuthor(oldWiseSaying);
        String newAuthor = inputView.readNewAuthor();
        wiseSayingService.changeWiseSaying(oldWiseSaying, newContent, newAuthor);
    }

    public void delete(String[] command) {
        wiseSayingService.deleteWiseSaying(wiseSayings, wiseSayingRepository.getWiseSayingById(wiseSayings, Long.parseLong(command[1])));
        outputView.printDeleteWiseSayingById(Long.parseLong(command[1]));
    }
}
