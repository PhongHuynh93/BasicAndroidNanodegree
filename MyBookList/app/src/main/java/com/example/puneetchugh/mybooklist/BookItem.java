package com.example.puneetchugh.mybooklist;

/**
 * Created by puneetchugh on 6/30/16.
 */
public class BookItem {
    private String author;
    private String title;

    public BookItem(String author, String title){

        this.author = author;
        this.title = title;
    }

    public String getAuthor(){
        return author;
    }

    public String getTitle(){
        return title;
    }
}
