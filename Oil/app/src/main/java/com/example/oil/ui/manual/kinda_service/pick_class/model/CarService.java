package com.example.oil.ui.manual.kinda_service.pick_class.model;

import android.os.Parcelable;

import org.parceler.Parcel;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.RealmClass;


@Parcel(value = Parcel.Serialization.BEAN, analyze = {CarService.class})
public class CarService extends RealmObject implements Parcelable {

    private String title;
    private String price;

    public CarService() {
    }

    public CarService(String title, String price) {
        this.title = title;
        this.price = price;
    }

    protected CarService(android.os.Parcel in) {
        title = in.readString();
        price = in.readString();
    }

    public static final Creator<CarService> CREATOR = new Creator<CarService>() {
        @Override
        public CarService createFromParcel(android.os.Parcel in) {
            return new CarService(in);
        }

        @Override
        public CarService[] newArray(int size) {
            return new CarService[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel parcel, int i) {

        parcel.writeString(title);
        parcel.writeString(price);
    }
}
