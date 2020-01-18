package com.example.oil.ui.manual.car_brand.create_car.pick_car_body.presenter;

import com.example.oil.db.migration.CarBodyMigration;
import com.example.oil.ui.manual.car_brand.create_car.pick_car_body.model.CarBody;
import com.example.oil.ui.manual.car_brand.create_car.pick_car_body.view.IPickModelView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class CarBodyPresenter implements ICarBodyPresenter {


    private IPickModelView pickModelView;
    private Realm realm;

    public CarBodyPresenter(IPickModelView pickModelView) {
        this.pickModelView = pickModelView;
    }

    @Override
    public void getCarBodies() {

        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("car_body.realm")
                .deleteRealmIfMigrationNeeded()
                .migration(new CarBodyMigration())
                .build();


        realm = Realm.getInstance(configuration);

        realm.beginTransaction();

        List<CarBody> carBodies = new ArrayList<>();
        carBodies.add(new CarBody("Седан"));
        carBodies.add(new CarBody("Универсал"));
        carBodies.add(new CarBody("Хэтчбэк"));
        carBodies.add(new CarBody("Купе"));
        carBodies.add(new CarBody("Микроавтобус"));
        carBodies.add(new CarBody("Минивэн"));

        realm.where(CarBody.class).findAll().deleteAllFromRealm();

        realm.insertOrUpdate(carBodies);


        realm.commitTransaction();


        pickModelView.getCarBodies(new ArrayList<>(realm.where(CarBody.class).findAll()));

    }

    private void removeRealm(RealmConfiguration configuration) {

        if (!realm.isClosed()) {

            realm.close();
            Realm.deleteRealm(configuration);

        }


    }
}
