package com.example.oil.ui.manual.kinda_service.pick_class.presenter;

import com.example.oil.db.migration.KindaServiceMigration;
import com.example.oil.db.migration.OilClassMigration;
import com.example.oil.ui.manual.kinda_service.pick_class.model.CarService;
import com.example.oil.ui.manual.kinda_service.pick_class.view.IKindaServiceView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class KindaServicePresenter implements IKindaServicePresenter {


    private IKindaServiceView pickModelView;

    public KindaServicePresenter(IKindaServiceView pickModelView) {
        this.pickModelView = pickModelView;
    }

    @Override
    public void getOilClasses() {

        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("kinda_service.realm")
                .deleteRealmIfMigrationNeeded()
                .migration(new KindaServiceMigration())
                .build();

        Realm realm = Realm.getInstance(configuration);

        realm.beginTransaction();

        realm.where(CarService.class).findAll().deleteAllFromRealm();

        List<CarService> models = new ArrayList<>();
        models.add(new CarService("Замена масла ", " 500с"));
        models.add(new CarService("Замена фильтра ", " 200с"));

        realm.insertOrUpdate(models);

        realm.commitTransaction();


        pickModelView.getKindaServices(new ArrayList<>(realm.where(CarService.class).findAll()));

    }
}
