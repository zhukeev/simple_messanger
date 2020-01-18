package com.example.oil.ui.manual.car_brand.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Car extends RealmObject {

   /* @PrimaryKey
    private int id;*/
    private String manufacturer;
    private String model;
    private String generation;
    private String carBody;
    private String yearOfManufacture;

/*    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }*/

    public Car() {
    }

    public Car(String manufacturer, String model, String generation, String carBody, String yearOfManufacture) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.generation = generation;
        this.carBody = carBody;
        this.yearOfManufacture = yearOfManufacture;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getGeneration() {
        return generation;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
    }

    public String getCarBody() {
        return carBody;
    }

    public void setCarBody(String carBody) {
        this.carBody = carBody;
    }

    public String getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(String yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    @Override
    public String toString() {
        return "Car{" +
                "manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", generation='" + generation + '\'' +
                ", carBody='" + carBody + '\'' +
                ", yearOfManufacture='" + yearOfManufacture + '\'' +
                '}';
    }
}
