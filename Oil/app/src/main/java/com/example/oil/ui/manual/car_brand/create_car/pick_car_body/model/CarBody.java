package com.example.oil.ui.manual.car_brand.create_car.pick_car_body.model;

import io.realm.RealmObject;

public class CarBody extends RealmObject {

    String carBody;

    public CarBody(String carBody) {
        this.carBody = carBody;
    }

    public CarBody() {
    }

    public String getCarBody() {
        return carBody;
    }

    public void setCarBody(String carBody) {
        this.carBody = carBody;
    }
}
