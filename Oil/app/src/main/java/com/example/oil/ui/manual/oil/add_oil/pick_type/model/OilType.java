package com.example.oil.ui.manual.oil.add_oil.pick_type.model;

import io.realm.RealmObject;

public class OilType extends RealmObject {

    String oil_type;

    public OilType(String oil_type) {
        this.oil_type = oil_type;
    }

    public OilType() {
    }

    public String getOil_type() {
        return oil_type;
    }

    public void setOil_type(String oil_type) {
        this.oil_type = oil_type;
    }
}
