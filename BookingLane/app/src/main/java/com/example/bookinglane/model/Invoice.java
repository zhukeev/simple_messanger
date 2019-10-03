package com.example.bookinglane.model;

public class Invoice {

    private String company;
    private String client;
    private String date;
    private String time;
    private String from;
    private String to;
    private String car;
    private int miles;
    private String coast;
    private String company_adress;
    private String phone_number;
    private String email;
    private long id_number;

    public Invoice(String company, String client, String date, String time, String from, String to, String car, int miles, String coast, String company_adress, String phone_number, String email, int id_number) {
        this.company = company;
        this.client = client;
        this.date = date;
        this.time = time;
        this.from = from;
        this.to = to;
        this.car = car;
        this.miles = miles;
        this.coast = coast;
        this.company_adress = company_adress;
        this.phone_number = phone_number;
        this.email = email;
        this.id_number = id_number;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public int getMiles() {
        return miles;
    }

    public void setMiles(int miles) {
        this.miles = miles;
    }

    public String getCoast() {
        return coast;
    }

    public void setCoast(String coast) {
        this.coast = coast;
    }

    public String getCompany_adress() {
        return company_adress;
    }

    public void setCompany_adress(String company_adress) {
        this.company_adress = company_adress;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
