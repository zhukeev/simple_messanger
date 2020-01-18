package com.example.oil.ui.manual.car_brand.create_car.pick_gen.model;

import io.realm.RealmObject;

public class CarGeneration extends RealmObject {

    public CarGeneration(String generation) {
        this.generation = generation;
    }

    String generation;

    public CarGeneration() {
    }

    public String getGeneration() {
        return generation;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
    }
}
