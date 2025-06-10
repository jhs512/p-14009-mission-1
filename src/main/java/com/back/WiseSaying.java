package com.back;

public class WiseSaying {
    private final int id;
    private String content;
    private String author;

    // id, content, author에 접근하기 위한 getter/setter 메서드들
    public int getId(){
        return id;
    }

    public String getContent(){
        return content;
    }

    public String getAuthor(){
        return author;
    }

    public void setContent(String content){
        this.content = content;
    }


    public void setAuthor(String author){
        this.author = author;
    }

    // 생성자 (WiseSaying 객체 생성과 동시에 모든 필드를 초기화하도록)
    public WiseSaying(int id, String content, String author){
        this.id = id;
        this.content = content;
        this.author = author;
    }
}