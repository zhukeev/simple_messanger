package com.example.oil.ui.manual.car_brand.view;

import com.example.oil.ui.manual.car_brand.model.Car;

import java.util.List;

public interface ICarBrandView {

    void updateCarList(List<Car> carList);
    void setCarList(List<Car> carList);
}
