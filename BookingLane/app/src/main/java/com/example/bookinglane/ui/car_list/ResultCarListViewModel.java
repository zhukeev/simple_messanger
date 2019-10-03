package com.example.bookinglane.ui.car_list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ResultCarListViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ResultCarListViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is share fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}