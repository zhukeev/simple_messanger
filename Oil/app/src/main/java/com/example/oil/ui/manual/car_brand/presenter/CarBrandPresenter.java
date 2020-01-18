package com.example.oil.ui.manual.car_brand.presenter;

import android.util.Log;

import com.example.oil.db.migration.CarMigration;
import com.example.oil.ui.manual.car_brand.model.Car;
import com.example.oil.ui.manual.car_brand.view.ICarBrandView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class CarBrandPresenter implements ICarBrandPresenter {


    private String manufacturer, model, generation, carBody, yearOfManufacture;

    private ICarBrandView carBrandView;
    private List<Car> cars = new ArrayList<>();
    private List<Car> filteredCars = new ArrayList<>();
    private Realm realm;

    public CarBrandPresenter(ICarBrandView carBrandView) {
        this.carBrandView = carBrandView;
    }


    @Override
    public void getCars() {

        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("cars.realm")
                .deleteRealmIfMigrationNeeded()
                .migration(new CarMigration())
                .build();

        realm = Realm.getInstance(configuration);

        realm.beginTransaction();


        cars.add(new Car("Audi", "A1", "I", "(C7) Рестайлинг 1", "(2014-2019)"));
        cars.add(new Car("Audi", "A2", "II", "(C7) Рестайлинг 2", "(2015-2020)"));
        cars.add(new Car("Audi", "A3", "III", "(C7) Рестайлинг 3", "(2014-2019)"));
        cars.add(new Car("Audi", "A5", "IV", "(C7) Рестайлинг 4", "(2015-2020)"));
        cars.add(new Car("Audi", "A6", "VI", "(C7) Рестайлинг 5", "(2014-2019)"));

        cars.add(new Car("BMW", "A1", "I", "(C7) Рестайлинг 1", "(2014-2019)"));
        cars.add(new Car("BMW", "A2", "II", "(C7) Рестайлинг 2", "(2015-2020)"));
        cars.add(new Car("BMW", "A3", "III", "(C7) Рестайлинг 3", "(2014-2019)"));
        cars.add(new Car("BMW", "A5", "IV", "(C7) Рестайлинг 4", "(2015-2020)"));
        cars.add(new Car("BMW", "A6", "VI", "(C7) Рестайлинг 5", "(2014-2019)"));

        cars.add(new Car("Mercedes Benz", "A1", "I", "(C7) Рестайлинг 1", "(2014-2019)"));
        cars.add(new Car("Mercedes Benz", "A2", "II", "(C7) Рестайлинг 2", "(2015-2020)"));
        cars.add(new Car("Mercedes Benz", "A3", "III", "(C7) Рестайлинг 3", "(2014-2019)"));
        cars.add(new Car("Mercedes Benz", "A5", "IV", "(C7) Рестайлинг 4", "(2015-2020)"));
        cars.add(new Car("Mercedes Benz", "A6", "VI", "(C7) Рестайлинг 5", "(2014-2019)"));

        cars.add(new Car("Volvo", "A1", "I", "(C7) Рестайлинг 1", "(2014-2019)"));
        cars.add(new Car("Volvo", "A2", "II", "(C7) Рестайлинг 2", "(2015-2020)"));
        cars.add(new Car("Volvo", "A3", "III", "(C7) Рестайлинг 3", "(2014-2019)"));
        cars.add(new Car("Volvo", "A5", "IV", "(C7) Рестайлинг 4", "(2015-2020)"));
        cars.add(new Car("Volvo", "A6", "VI", "(C7) Рестайлинг 5", "(2014-2019)"));

        realm.where(Car.class).findAll().deleteAllFromRealm();

        realm.copyToRealm(cars);
        realm.commitTransaction();
        carBrandView.setCarList(cars);
    }

    @Override
    public void setManufacturer(String manufacturer) {

        model = "";
        generation = "";
        carBody = "";
        yearOfManufacture = "";
        this.manufacturer = manufacturer;


        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                filteredCars = realm.copyFromRealm(realm.where(Car.class).equalTo("manufacturer", manufacturer).findAll());
                carBrandView.updateCarList(filteredCars);

            }
        });


    }

    @Override
    public void setModel(String model) {
        this.model = model;
        filteredCars = realm.copyFromRealm(realm.where(Car.class)
                .equalTo("model", model)
                .and()
                .equalTo("manufacturer", manufacturer)
                .findAll());
        carBrandView.updateCarList(filteredCars);

    }

    @Override
    public void setGeneration(String generation) {

        this.generation = generation;

        filteredCars = realm.copyFromRealm(realm.where(Car.class)
                .equalTo("generation", generation)
                .and()
                .equalTo("model", model)
                .and()
                .equalTo("manufacturer", manufacturer)
                .findAll());
        carBrandView.updateCarList(filteredCars);
    }

    @Override
    public void setCarBody(String carBody) {
        this.carBody = carBody;

        filteredCars = realm.copyFromRealm(realm.where(Car.class)

                .equalTo("carBody", carBody).findAll());


        carBrandView.updateCarList(filteredCars);
    }

    @Override
    public void setCarBody(String carBody, List<Car> carList) {


        List<Car> temp = new ArrayList<>();


        for (Car car : carList) {
            if (car.getCarBody().equals(carBody)) {
                temp.add(car);
            }
        }

        carBrandView.updateCarList(temp);

    }


    @Override
    public void setYearOfManufacture(String year) {
        this.yearOfManufacture = year;
    }

}
