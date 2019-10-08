package com.example.quicklist.model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Event {

    String title;
    String datetime;
    int image_url;
    String color;
    String icon;
    String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Event() {
    }

    public Event(String title, String datetime, String icon, String color) {
        this.title = title;
        this.datetime = datetime;
        this.icon = icon;
        this.color = color;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Event(String title, String datetime, int image_url, String color) {
        this.title = title;
        this.datetime = datetime;
        this.image_url = image_url;
        this.color = color;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("color", color);
        result.put("icon", icon);
        result.put("datetime", datetime);
        result.put("title", title);
        return result;
    }

    @Override
    public String toString() {
        return "Event{" +
                "title='" + title + '\'' +
                ", datetime='" + datetime + '\'' +
                ", image_url=" + image_url +
                ", color='" + color + '\'' +
                ", icon='" + icon + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}
