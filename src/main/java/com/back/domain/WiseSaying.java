package com.back.domain;

public class WiseSaying {

    private Long id;
    private String content;
    private String author;

    protected WiseSaying() {
    }

    public WiseSaying(Long id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
