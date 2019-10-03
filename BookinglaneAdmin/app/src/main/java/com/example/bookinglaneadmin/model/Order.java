package com.example.bookinglaneadmin.model;

import java.io.Serializable;

public class Order implements Serializable {
    String from;
    String to;
    int people;
    String time;
    String carType;
    String timeOfReceipt;
    int status;

    public static final int COME_LIST = 1;
    public static final int PERFORMED_LIST = 2;
    public static final int CANCELED_LIST = 3;
    public static final int HISTORY_LIST = 4;

    public Order(String from, String to, int people, String time, String carType, String timeOfReceipt, int status) {
        this.from = from;
        this.to = to;
        this.people = people;
        this.time = time;
        this.carType = carType;
        this.timeOfReceipt = timeOfReceipt;
        this.status = status;
    }

    public String getTimeOfReceipt() {
        return timeOfReceipt;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getPeople() {
        return String.valueOf( people);
    }

    public String getTime() {
        return time;
    }

    public String getCarType() {
        return carType;
    }

    public int getStatus() {
        return status;
    }

    public Order() {
    }

    @Override
    public String toString() {
        return "Order{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", people=" + people +
                ", time='" + time + '\'' +
                ", carType='" + carType + '\'' +
                ", timeOfReceipt='" + timeOfReceipt + '\'' +
                ", status=" + status +
                '}';
    }
}
