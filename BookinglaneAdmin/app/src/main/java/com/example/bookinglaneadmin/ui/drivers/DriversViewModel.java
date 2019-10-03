package com.example.bookinglaneadmin.ui.drivers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DriversViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DriversViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is tools fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}