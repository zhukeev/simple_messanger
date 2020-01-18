package com.example.oil.ui.manual.oil.add_oil.pick_viscosity.model;

import io.realm.RealmObject;

public class OilViscosity extends RealmObject {

    private String oil_viscosity;

    public OilViscosity(String oil_viscosity) {
        this.oil_viscosity = oil_viscosity;
    }

    public OilViscosity() { }

    public String getOil_viscosity() {
        return oil_viscosity;
    }

    public void setOil_viscosity(String oil_viscosity) {
        this.oil_viscosity = oil_viscosity;
    }
}
