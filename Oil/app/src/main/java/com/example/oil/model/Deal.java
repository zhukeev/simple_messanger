package com.example.oil.model;

public class Deal {

    int orderNum;
    String address;
    String date;
    String time;
    String fullname;
    String dateOfBirth;
    String email;
    String phoneNumber;
    boolean whatsApp;
    String car_brand;
    String model_year;
    String mileage;
    String KINDA_SERVICE;
    String oil;
    String filter;
    String oil_volume;


    public Deal() {
    }

    public Deal(int orderNum, String address, String date, String time, String fullname, String dateOfBirth,
                String email, String phoneNumber, boolean whatsApp, String car_brand, String model_year,
                String mileage, String KINDA_SERVICE, String oil, String filter, String oil_volume) {

        this.orderNum = orderNum;
        this.address = address;
        this.date = date;
        this.time = time;
        this.fullname = fullname;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.whatsApp = whatsApp;
        this.car_brand = car_brand;
        this.model_year = model_year;
        this.mileage = mileage;
        this.KINDA_SERVICE = KINDA_SERVICE;
        this.oil = oil;
        this.filter = filter;
        this.oil_volume = oil_volume;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isWhatsApp() {
        return whatsApp;
    }

    public void setWhatsApp(boolean whatsApp) {
        this.whatsApp = whatsApp;
    }

    public String getCar_brand() {
        return car_brand;
    }

    public void setCar_brand(String car_brand) {
        this.car_brand = car_brand;
    }

    public String getModel_year() {
        return model_year;
    }

    public void setModel_year(String model_year) {
        this.model_year = model_year;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getKINDA_SERVICE() {
        return KINDA_SERVICE;
    }

    public void setKINDA_SERVICE(String KINDA_SERVICE) {
        this.KINDA_SERVICE = KINDA_SERVICE;
    }

    public String getOil() {
        return oil;
    }

    public void setOil(String oil) {
        this.oil = oil;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getOil_volume() {
        return oil_volume;
    }

    public void setOil_volume(String oil_volume) {
        this.oil_volume = oil_volume;
    }
}

