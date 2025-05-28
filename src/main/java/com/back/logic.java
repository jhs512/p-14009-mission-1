package com.back;

import java.util.ArrayList;

public class logic {
    ArrayList<WiseSaying> list = new ArrayList<>();
    int id = 0;
    public String write(String content, String author) {
        WiseSaying write = new WiseSaying(++id, content, author);
        list.add(write);
        return id+"번 명언이 등록되었습니다.";
    }
    public ArrayList<WiseSaying> list() {
        return list;
    }
    public WiseSaying findById(int id) {
        for (WiseSaying saying : list) {
            if (saying.getId() == id) {
                return saying;
            }
        }
        return null;
    }
    public String modify(int id, String content, String author) {
        String msg = "";
        for (WiseSaying saying : list) {
            if (id == saying.getId()) {
                saying.setContent(content);
                saying.setAuthor(author);
                msg = id+"번 명언이 수정되었습니다.";
            }
            else {
                msg = id+"번 명언이 없습니다.";
            }
        }
        return msg;
    }
    public String delete(int id) {
        for (WiseSaying saying : list) {
            if (saying.getId() == id) {
                list.remove(saying);
                return id+"번 명언이 삭제되었습니다.";
            }
        }
        return id+"번 명언은 존재하지 않습니다.";
    }
}
