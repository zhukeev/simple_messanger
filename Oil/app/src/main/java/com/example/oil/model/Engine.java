package com.example.oil.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Engine implements Parcelable {

    String type;
    String fuel;

    protected Engine(Parcel in) {
        type = in.readString();
        fuel = in.readString();
    }

    public static final Creator<Engine> CREATOR = new Creator<Engine>() {
        @Override
        public Engine createFromParcel(Parcel in) {
            return new Engine(in);
        }

        @Override
        public Engine[] newArray(int size) {
            return new Engine[size];
        }
    };

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public Engine(String type, String fuel) {
        this.type = type;
        this.fuel = fuel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(type);
        parcel.writeString(fuel);
    }

    @Override
    public String toString() {
        return "Engine{" +
                "type='" + type + '\'' +
                ", fuel='" + fuel + '\'' +
                '}';
    }
}
