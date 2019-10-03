package com.example.quicklist.model;

public class Event {

    String title;
    String datetime;
    int image_url;
    String bg_color;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public int getImage_url() {
        return image_url;
    }

    public void setImage_url(int image_url) {
        this.image_url = image_url;
    }

    public String  getBg_color() {
        return bg_color;
    }

    public void setBg_color(String  bg_color) {
        this.bg_color = bg_color;
    }

    public Event(String title, String datetime, int image_url, String  bg_color) {
        this.title = title;
        this.datetime = datetime;
        this.image_url = image_url;
        this.bg_color = bg_color;
    }
}
