package com.example.bookinglaneadmin.ui.fleet;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class YourFleetViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public YourFleetViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}