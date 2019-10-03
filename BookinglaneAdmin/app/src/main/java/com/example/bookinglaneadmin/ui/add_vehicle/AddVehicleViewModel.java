package com.example.bookinglaneadmin.ui.add_vehicle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddVehicleViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AddVehicleViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is send fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}