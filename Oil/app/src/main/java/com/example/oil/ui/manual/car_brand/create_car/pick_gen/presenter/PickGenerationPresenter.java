package com.example.oil.ui.manual.car_brand.create_car.pick_gen.presenter;

import com.example.oil.db.migration.CarGenerationMigration;
import com.example.oil.ui.manual.car_brand.create_car.pick_gen.model.CarGeneration;
import com.example.oil.ui.manual.car_brand.create_car.pick_gen.view.IPickGenerationView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class PickGenerationPresenter implements IPickGenerationPresenter {


    private IPickGenerationView pickGenerationView;

    public PickGenerationPresenter(IPickGenerationView pickGenerationView) {
        this.pickGenerationView = pickGenerationView;
    }

    @Override
    public void getGenerations() {

        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("generations.realm")
                .deleteRealmIfMigrationNeeded()
                .migration(new CarGenerationMigration())
                .build();

        Realm realm = Realm.getInstance(configuration);

        realm.beginTransaction();


        List<CarGeneration> models = new ArrayList<>();
        models.add(new CarGeneration("I"));
        models.add(new CarGeneration("I"));
        models.add(new CarGeneration("I"));
        models.add(new CarGeneration("II"));
        models.add(new CarGeneration("II"));
        models.add(new CarGeneration("III"));
        models.add(new CarGeneration("III"));
        models.add(new CarGeneration("III"));
        models.add(new CarGeneration("IV"));
        models.add(new CarGeneration("IV"));
        models.add(new CarGeneration("V"));
        models.add(new CarGeneration("V"));
        models.add(new CarGeneration("VI"));

        realm.insertOrUpdate(models);


        realm.commitTransaction();


        pickGenerationView.getGeneration(new ArrayList<>(realm.where(CarGeneration.class).findAll()));

    }
}
