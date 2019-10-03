package com.example.bookinglane.model;

public class TripHistory {

    private String date_time;
   private String from;
    private String to;
    private String price;
    private String image_url;

    public TripHistory(String date_time, String from, String to, String price, String image_url) {
        this.date_time = date_time;
        this.from = from;
        this.to = to;
        this.price = price;
        this.image_url = image_url;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }


}
