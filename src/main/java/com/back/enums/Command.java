package com.back.enums;

public enum Command {

    종료("종료"),
    등록("등록"),
    목록("목록"),
    수정("수정"),
    삭제("삭제");

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
