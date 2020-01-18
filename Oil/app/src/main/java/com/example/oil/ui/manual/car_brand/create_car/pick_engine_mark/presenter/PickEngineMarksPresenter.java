package com.example.oil.ui.manual.car_brand.create_car.pick_engine_mark.presenter;

import com.example.oil.db.migration.CarEngineMarkMigration;
import com.example.oil.db.migration.CarGenerationMigration;
import com.example.oil.ui.manual.car_brand.create_car.pick_engine_mark.model.EngineMarks;
import com.example.oil.ui.manual.car_brand.create_car.pick_engine_mark.view.IPickEngineMarksView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class PickEngineMarksPresenter implements IPickEngineMarksPresenter {


    private IPickEngineMarksView pickGenerationView;

    public PickEngineMarksPresenter(IPickEngineMarksView pickGenerationView) {
        this.pickGenerationView = pickGenerationView;
    }

    @Override
    public void getEngineMarks() {

        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("engine_marks.realm")
                .deleteRealmIfMigrationNeeded()
                .migration(new CarEngineMarkMigration())
                .build();

        Realm realm = Realm.getInstance(configuration);

        realm.beginTransaction();


        List<EngineMarks> models = new ArrayList<>();

        models.add(new EngineMarks("FUJA"));
        models.add(new EngineMarks("FXJA"));
        models.add(new EngineMarks("ASDA"));
        models.add(new EngineMarks("ASDB"));
        models.add(new EngineMarks("ECOBOOST"));
        models.add(new EngineMarks("HWDA"));
        models.add(new EngineMarks("HXDA"));

        realm.where(EngineMarks.class).findAll().deleteAllFromRealm();

        realm.insertOrUpdate(models);

        realm.commitTransaction();


        pickGenerationView.getGeneration(new ArrayList<>(realm.where(EngineMarks.class).findAll()));

    }
}
