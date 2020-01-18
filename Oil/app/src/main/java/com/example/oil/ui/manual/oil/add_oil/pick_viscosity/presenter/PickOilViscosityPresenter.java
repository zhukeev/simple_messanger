package com.example.oil.ui.manual.oil.add_oil.pick_viscosity.presenter;

import com.example.oil.db.migration.OilClassMigration;
import com.example.oil.ui.manual.oil.add_oil.pick_viscosity.model.OilViscosity;
import com.example.oil.ui.manual.oil.add_oil.pick_viscosity.view.IPickOilViscosityView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class PickOilViscosityPresenter implements IPickOilViscosityPresenter {


    private IPickOilViscosityView pickModelView;

    public PickOilViscosityPresenter(IPickOilViscosityView pickModelView) {
        this.pickModelView = pickModelView;
    }

    @Override
    public void getOilClasses() {

        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("oil_class.realm")
                .deleteRealmIfMigrationNeeded()
                .migration(new OilClassMigration())
                .build();

        Realm realm = Realm.getInstance(configuration);

        realm.beginTransaction();

        realm.where(OilViscosity.class).findAll().deleteAllFromRealm();

        List<OilViscosity> models = new ArrayList<>();
        models.add(new OilViscosity("API SL"));

        realm.insertOrUpdate(models);

        realm.commitTransaction();


        pickModelView.getOilViscosity(new ArrayList<>(realm.where(OilViscosity.class).findAll()));

    }
}
