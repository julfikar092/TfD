package com.example.tfd.ui.kVA_15;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class kVA_15ViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public kVA_15ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
}