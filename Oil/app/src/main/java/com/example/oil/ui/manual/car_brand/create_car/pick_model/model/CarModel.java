package com.example.oil.ui.manual.car_brand.create_car.pick_model.model;

import io.realm.RealmObject;

public class CarModel extends RealmObject {

    String model;

    public CarModel(String model) {
        this.model = model;
    }

    public CarModel() {
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
