package com.example.oil.ui.manual.oil.add_oil.pick_class.model;

import io.realm.RealmObject;

public class OilClass extends RealmObject {

    String oil_class;

    public OilClass(String oil_class) {
        this.oil_class = oil_class;
    }

    public OilClass() {
    }

    public String getOil_class() {
        return oil_class;
    }

    public void setOil_class(String oil_class) {
        this.oil_class = oil_class;
    }
}
