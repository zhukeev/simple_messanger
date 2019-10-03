package com.example.bookinglaneadmin.model;

public class SendNotificationModel {
    String title,body,sound;

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public SendNotificationModel(String title, String body, String sound) {
        this.title = title;
        this.body = body;
        this.sound = sound;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
