package com.example.tfd.ui.kVA_37;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class kVA_37ViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public kVA_37ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
}