package com.example.bookinglaneadmin.ui.order_details;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OrderDetailsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public OrderDetailsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is send fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}