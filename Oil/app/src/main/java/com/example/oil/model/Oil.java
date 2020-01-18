package com.example.oil.model;

import com.example.oil.R;

public class Oil {

    private String title;
    private String composition;
    private String viscosity;
    private String oilClass;
    private String engine;
    private boolean okForTurboEngine;
    private String inWarehouse;
    private String cameToWarehouse;
    private String sold;
    private String image;

    public Oil(String title, String composition, String viscosity, String oilClass, String engine, boolean okForTurboEngine, String inWarehouse, String cameToWarehouse, String sold, String image) {
        this.title = title;
        this.composition = composition;
        this.viscosity = viscosity;
        this.oilClass = oilClass;
        this.engine = engine;
        this.okForTurboEngine = okForTurboEngine;
        this.inWarehouse = inWarehouse;
        this.cameToWarehouse = cameToWarehouse;
        this.sold = sold;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    public String getViscosity() {
        return viscosity;
    }

    public void setViscosity(String viscosity) {
        this.viscosity = viscosity;
    }

    public String getOilClass() {
        return oilClass;
    }

    public void setOilClass(String oilClass) {
        this.oilClass = oilClass;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public boolean isOkForTurboEngine() {
        return okForTurboEngine;
    }

    public void setOkForTurboEngine(boolean okForTurboEngine) {
        this.okForTurboEngine = okForTurboEngine;
    }

    public String getInWarehouse() {
        return inWarehouse;
    }

    public void setInWarehouse(String inWarehouse) {
        this.inWarehouse = inWarehouse;
    }

    public String getSold() {
        return sold;
    }

    public void setSold(String sold) {
        this.sold = sold;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public String getCameToWarehouse() {
        return cameToWarehouse;
    }

    public void setCameToWarehouse(String cameToWarehouse) {
        this.cameToWarehouse = cameToWarehouse;
    }
}
