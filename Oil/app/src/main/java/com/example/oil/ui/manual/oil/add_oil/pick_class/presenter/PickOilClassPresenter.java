package com.example.oil.ui.manual.oil.add_oil.pick_class.presenter;

import com.example.oil.db.migration.OilClassMigration;
import com.example.oil.db.migration.OilTypeMigration;
import com.example.oil.ui.manual.oil.add_oil.pick_class.model.OilClass;
import com.example.oil.ui.manual.oil.add_oil.pick_class.view.IPickOilClassView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class PickOilClassPresenter implements IPickOilClassPresenter {


    private IPickOilClassView pickModelView;

    public PickOilClassPresenter(IPickOilClassView pickModelView) {
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

        realm.where(OilClass.class).findAll().deleteAllFromRealm();

        List<OilClass> models = new ArrayList<>();
        models.add(new OilClass("API SL"));

        realm.insertOrUpdate(models);

        realm.commitTransaction();


        pickModelView.getOilTypes(new ArrayList<>(realm.where(OilClass.class).findAll()));

    }
}
