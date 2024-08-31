package com.example.tfd.ui.kVA_25;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tfd.databinding.FragmentKva25Binding;
import com.example.tfd.ui.kVA_25.kVA_25ViewModel;


public class kVA_25Fragment extends Fragment {

    private FragmentKva25Binding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        kVA_25ViewModel kva25ViewModel =
                new ViewModelProvider(this).get(kVA_25ViewModel.class);

        binding = FragmentKva25Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textKva25;
        kva25ViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}