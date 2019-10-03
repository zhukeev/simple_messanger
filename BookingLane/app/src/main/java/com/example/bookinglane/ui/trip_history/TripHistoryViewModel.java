package com.example.bookinglane.ui.trip_history;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TripHistoryViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TripHistoryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}