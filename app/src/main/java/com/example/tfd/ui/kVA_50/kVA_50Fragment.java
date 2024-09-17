package com.example.tfd.ui.kVA_50;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tfd.databinding.FragmentKva50Binding;
import com.example.tfd.ui.kVA_50.kVA_50ViewModel;

public class kVA_50Fragment extends Fragment {

    private FragmentKva50Binding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        kVA_50ViewModel kva50ViewModel =
                new ViewModelProvider(this).get(kVA_50ViewModel.class);

        binding = FragmentKva50Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textKva15;
        kva50ViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}