package com.example.bookinglaneadmin.ui.earnings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EarningsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public EarningsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}