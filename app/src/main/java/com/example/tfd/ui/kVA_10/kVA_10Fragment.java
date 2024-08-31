package com.example.tfd.ui.kVA_10;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tfd.databinding.FragmentKva10Binding;
import com.example.tfd.databinding.FragmentKva5Binding;
import com.example.tfd.ui.kVA_10.kVA_10ViewModel;

public class kVA_10Fragment extends Fragment {

    private FragmentKva10Binding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        kVA_10ViewModel kva5ViewModel =
                new ViewModelProvider(this).get(kVA_10ViewModel.class);

        binding = FragmentKva10Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textKva10;
        kva5ViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}