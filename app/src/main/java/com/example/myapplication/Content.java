package com.example.myapplication;

public class Content {

    private String _title;
    private String _date;
    public Content(String title, String date) {
        _title = title;
        _date = date;
    }

    public String get_date() {
        return _date;
    }

    public String get_title() {
        return _title;
    }
}
