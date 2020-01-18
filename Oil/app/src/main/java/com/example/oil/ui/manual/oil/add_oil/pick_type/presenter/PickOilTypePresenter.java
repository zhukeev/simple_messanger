package com.example.oil.ui.manual.oil.add_oil.pick_type.presenter;

import com.example.oil.db.migration.CarModelMigration;
import com.example.oil.db.migration.OilTypeMigration;
import com.example.oil.ui.manual.oil.add_oil.pick_type.model.OilType;
import com.example.oil.ui.manual.oil.add_oil.pick_type.view.IPickOilTypeView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class PickOilTypePresenter implements IPickOilTypePresenter {


    private IPickOilTypeView pickModelView;

    public PickOilTypePresenter(IPickOilTypeView pickModelView) {
        this.pickModelView = pickModelView;
    }

    @Override
    public void getOilTypes() {

        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("oil_type.realm")
                .deleteRealmIfMigrationNeeded()
                .migration(new OilTypeMigration())
                .build();

        Realm realm = Realm.getInstance(configuration);

        realm.beginTransaction();

        realm.where(OilType.class).findAll().deleteAllFromRealm();
        List<OilType> models = new ArrayList<>();
        models.add(new OilType("Полусинтетическое моторное масло"));

        realm.insertOrUpdate(models);


        realm.commitTransaction();


        pickModelView.getOilTypes(new ArrayList<>(realm.where(OilType.class).findAll()));

    }
}
