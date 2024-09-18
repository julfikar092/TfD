package com.example.tfd.ui.kVA_37;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tfd.databinding.FragmentKva37Binding;
import com.example.tfd.ui.kVA_37.kVA_37ViewModel;

public class kVA_37Fragment extends Fragment {

    private FragmentKva37Binding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        kVA_37ViewModel kva37ViewModel =
                new ViewModelProvider(this).get(kVA_37ViewModel.class);

        binding = FragmentKva37Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textKva15;
        kva37ViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}