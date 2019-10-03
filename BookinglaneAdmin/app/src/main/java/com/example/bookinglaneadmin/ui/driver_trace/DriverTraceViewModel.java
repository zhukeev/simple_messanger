package com.example.bookinglaneadmin.ui.driver_trace;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DriverTraceViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DriverTraceViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is send fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}