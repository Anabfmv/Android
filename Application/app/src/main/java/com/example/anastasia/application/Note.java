package com.example.anastasia.application;

public class Note {
    public int id;
    public String header;
    public String text;
    public String date;

    public Note(String header, String text, String date,int id) {
        this.id = id;
        this.header = header;
        this.text = text;
        this.date = date;
    }
}