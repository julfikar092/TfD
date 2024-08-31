package com.example.tfd.ui.kVA_25;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class kVA_25ViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public kVA_25ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
}