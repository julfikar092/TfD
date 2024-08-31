package com.example.tfd.ui.kVA_50;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class kVA_50ViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public kVA_50ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
}