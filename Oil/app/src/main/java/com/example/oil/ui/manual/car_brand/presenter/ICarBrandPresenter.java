package com.example.oil.ui.manual.car_brand.presenter;

import com.example.oil.ui.manual.car_brand.model.Car;

import java.util.List;

public interface ICarBrandPresenter {

    void getCars();

    void setManufacturer(String manufacturer);
    void setModel(String model);
    void setGeneration(String generation);
    void setCarBody(String carBody);
    void setCarBody(String carBody, List<Car> carList);
    void setYearOfManufacture(String year);

}
