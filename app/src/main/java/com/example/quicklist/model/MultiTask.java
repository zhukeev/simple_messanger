package com.example.quicklist.model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class MultiTask {

    String title;
    String image_url;
    String color;
    int res_url_image;

    public MultiTask(String title, int res_url_image) {
        this.title = title;
        this.res_url_image = res_url_image;
    }

    public MultiTask(String title, String image_url, String color) {
        this.title = title;
        this.color = color;
        this.image_url = image_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
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
        result.put("icon", image_url);
        result.put("name", title);
        return result;
    }

}
