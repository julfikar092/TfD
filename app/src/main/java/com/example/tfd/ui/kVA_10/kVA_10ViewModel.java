package com.example.tfd.ui.kVA_10;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class kVA_10ViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public kVA_10ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
}