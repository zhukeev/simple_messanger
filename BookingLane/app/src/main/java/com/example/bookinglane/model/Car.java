package com.example.bookinglane.model;

public class Car {

    String title;
    String description;
    float rating;
    String price;
    boolean recommend;

    public boolean isRecommend() {
        return recommend;
    }

    public Car(String title, String description, float rating, String price, boolean recommend) {
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.price = price;
        this.recommend = recommend;
    }

    public Car(String title, String description, float rating, String price) {
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", price='" + price + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
