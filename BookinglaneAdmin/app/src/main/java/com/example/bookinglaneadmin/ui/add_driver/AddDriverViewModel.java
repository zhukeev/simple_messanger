package com.example.bookinglaneadmin.ui.add_driver;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddDriverViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AddDriverViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is send fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}