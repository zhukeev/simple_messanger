package com.example.quicklist.model;

public class MultiList {

    String title;
    String image_url;
    int res_url_image;

    public MultiList(String title, int res_url_image) {
        this.title = title;
        this.res_url_image = res_url_image;
    }

    public MultiList(String title, String image_url) {
        this.title = title;
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
}
