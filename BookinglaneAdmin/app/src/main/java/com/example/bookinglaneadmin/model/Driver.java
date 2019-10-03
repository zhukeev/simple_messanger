package com.example.bookinglaneadmin.model;

public class Driver {
    String fullname;
    String phone;
    String email;
    String address;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Driver(String fullname, String phone, String email, String address) {
        this.fullname = fullname;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }
}
