package com.example.chatterbox.objects;

public class DateTimeModel {

    private String Date;
    private String Time;

    public DateTimeModel(){}

    public DateTimeModel(String date, String time) {
        Date = date;
        Time = time;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
