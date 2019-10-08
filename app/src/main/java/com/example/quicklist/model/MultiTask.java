package com.example.quicklist.model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class MultiTask {

    String name;
    String icon;
    String color;
    int res_url_image;

    public MultiTask() {
    }

    public MultiTask(String title, int res_url_image) {
        this.name = title;
        this.res_url_image = res_url_image;
    }

    public MultiTask(String title, String image_url, String color) {
        this.name = title;
        this.color = color;
        this.icon = image_url;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getRes_url_image() {
        return res_url_image;
    }

    public void setRes_url_image(int res_url_image) {
        this.res_url_image = res_url_image;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("color", color);
        result.put("icon", icon);
        result.put("name", name);
        return result;
    }

}
