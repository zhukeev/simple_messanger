package com.example.bookinglaneadmin.ui.reservations_pro_subs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ReservationsProSubsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ReservationsProSubsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is share fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}