package com.example.oil.ui.manual.car_brand.create_car.pick_model.presenter;

import com.example.oil.db.migration.CarModelMigration;
import com.example.oil.ui.manual.car_brand.create_car.pick_model.model.CarModel;
import com.example.oil.ui.manual.car_brand.create_car.pick_model.view.IPickModelView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class PickModelPresenter implements IPickModelPresenter {


    private IPickModelView pickModelView;

    public PickModelPresenter(IPickModelView pickModelView) {
        this.pickModelView = pickModelView;
    }

    @Override
    public void getModels() {

        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("models.realm")
                .deleteRealmIfMigrationNeeded()
                .migration(new CarModelMigration())
                .build();

        Realm realm = Realm.getInstance(configuration);

        realm.beginTransaction();


        List<CarModel> models = new ArrayList<>();
        models.add(new CarModel("BMW"));
        models.add(new CarModel("BMW"));
        models.add(new CarModel("BMW"));
        models.add(new CarModel("Audi"));
        models.add(new CarModel("Audi"));
        models.add(new CarModel("Audi"));
        models.add(new CarModel("Mercedes Benz"));
        models.add(new CarModel("Mercedes Benz"));
        models.add(new CarModel("Mercedes Benz"));
        models.add(new CarModel("Mercedes Benz"));

        realm.insertOrUpdate(models);


        realm.commitTransaction();


        pickModelView.getModels(new ArrayList<>(realm.where(CarModel.class).findAll()));

    }
}
