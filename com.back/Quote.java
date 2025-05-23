package com.back;

public class Quote {
    private int num;
    private String author;
    private String quote;

    public Quote(int num, String author, String quote) {
        this.num = num;
        this.author = author;
        this.quote = quote;
    }
    public void setNum(int num) {
        this.num = num;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public int getNum() {
        return num;
    }

    public String getAuthor() {
        return author;
    }

    public String getQuote() {
        return quote;
    }

    public String toString() {
        return num + " / " + author + " / " + quote;
    }
}
