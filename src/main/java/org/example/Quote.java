package org.example;

public class Quote {
    private int id;
    private String author;
    private String content;
    public Quote(int id, String author, String content){
        this.id = id;
        this.author = author;
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }
    public String getContent(){
        return content;
    }
    public int getId(){
        return id;
    }
    public void setAuthor(String author){
        this.author = author;
    }
    public void setContent(String content){
        this.content = content;
    }
}
