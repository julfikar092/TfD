package com.example.tfd.ui.kVA_5;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class kVA_5ViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public kVA_5ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
}