package com.example.tfd.ui.kVA_5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tfd.databinding.FragmentKva5Binding;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textfield.TextInputEditText;

public class kVA_5Fragment extends Fragment {

    private FragmentKva5Binding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        kVA_5ViewModel kva5ViewModel =
                new ViewModelProvider(this).get(kVA_5ViewModel.class);

        binding = FragmentKva5Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Access the input fields, button, and table using View Binding
        final TextView initialText = binding.textKva15;

        final TextView teslaHeader = binding.teslaHeader;
        final TextView resistanceHeader = binding.resistanceHeader;
        final TextInputLayout inputNoLoadLoss = binding.inputOfNoLoadLoss;
        final TextInputLayout inputResistance = binding.inputOfResistance;
        final TextInputEditText noLoadLossEditText = binding.NoLoadLoss;
        final TextInputEditText resistanceEditText = binding.resistance;
        final Button submitButton = binding.submitButton;
        final TableLayout tableLayout = binding.tableLayout;

        // Reset the visibility of the input fields and button
        inputNoLoadLoss.setVisibility(View.VISIBLE);
        inputResistance.setVisibility(View.VISIBLE);
        submitButton.setVisibility(View.VISIBLE);
        binding.textKva15.setVisibility(View.VISIBLE);

        // Hide the table initially
        tableLayout.setVisibility(View.GONE);

        // Set click listener for the submit button
        submitButton.setOnClickListener(v -> {
            String noLoadLoss = noLoadLossEditText.getText().toString();
            String resistance = resistanceEditText.getText().toString();

            if (noLoadLoss.isEmpty() || resistance.isEmpty()) {
                Toast.makeText(getActivity(), "Please fill out all fields", Toast.LENGTH_SHORT).show();
            } else {
                noLoadLossEditText.setText("");
                resistanceEditText.setText("");
                // Hide input fields and button
                initialText.setVisibility(View.GONE);
                teslaHeader.setVisibility(View.GONE);
                inputNoLoadLoss.setVisibility(View.GONE);
                resistanceHeader.setVisibility(View.GONE);
                inputResistance.setVisibility(View.GONE);
                submitButton.setVisibility(View.GONE);

                // Show the table layout
                tableLayout.setVisibility(View.VISIBLE);

            }
        });

        kva5ViewModel.getText().observe(getViewLifecycleOwner(), binding.textKva15::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
