package com.example.oil.model;

public class Filter {

    String title;
    String models;
    String engines;
    String image;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getModels() {
        return models;
    }

    public void setModels(String models) {
        this.models = models;
    }

    public String getEngines() {
        return engines;
    }

    public void setEngines(String engines) {
        this.engines = engines;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Filter(String title, String models, String engines, String image) {
        this.title = title;
        this.models = models;
        this.engines = engines;
        this.image = image;
    }
}
