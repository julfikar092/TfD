package com.example.tfd.ui.kVA_15;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tfd.databinding.FragmentKva15Binding;
import com.example.tfd.ui.kVA_15.kVA_15ViewModel;

public class kVA_15Fragment extends Fragment {

    private FragmentKva15Binding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        kVA_15ViewModel kva15ViewModel =
                new ViewModelProvider(this).get(kVA_15ViewModel.class);

        binding = FragmentKva15Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textKva15;
        kva15ViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}