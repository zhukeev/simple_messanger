package com.example.bookinglaneadmin.ui.company_profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CompanyProfileViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CompanyProfileViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is send fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}