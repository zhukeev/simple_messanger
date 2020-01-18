package com.example.oil.ui.manual.car_brand.create_car.pick_engine_mark.model;

import io.realm.RealmObject;

public class EngineMarks extends RealmObject {

    public EngineMarks(String engine_marks) {
        this.engine_marks = engine_marks;
    }

    private String engine_marks;

    public EngineMarks() {
    }

    public String getEngine_marks() {
        return engine_marks;
    }

    public void setEngine_marks(String engine_marks) {
        this.engine_marks = engine_marks;
    }
}
