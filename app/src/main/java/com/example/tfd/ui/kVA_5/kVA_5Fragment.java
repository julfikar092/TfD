package com.example.tfd.ui.kVA_5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tfd.databinding.FragmentKva5Binding;

public class kVA_5Fragment extends Fragment {

    private FragmentKva5Binding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        kVA_5ViewModel kva5ViewModel =
                new ViewModelProvider(this).get(kVA_5ViewModel.class);

        binding = FragmentKva5Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button submitButton = binding.submitButton;
        submitButton.setOnClickListener(v -> {
            EditText noLoadLossInput = binding.NoLoadLoss;
            EditText resistanceInput = binding.resistance;

            String noLoadLossStr = noLoadLossInput.getText().toString();
            String resistanceStr = resistanceInput.getText().toString();

            if (noLoadLossStr.isEmpty() || resistanceStr.isEmpty()) {
                Toast.makeText(getActivity(), "Please enter both values.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Hide input fields, headers, and button
            binding.teslaHeader.setVisibility(View.GONE);
            binding.inputOfNoLoadLoss.setVisibility(View.GONE);
            binding.resistanceHeader.setVisibility(View.GONE);
            binding.inputOfResistance.setVisibility(View.GONE);
            binding.submitButton.setVisibility(View.GONE);

            // Calculate dynamic data based on input
            double noLoadLoss = Double.parseDouble(noLoadLossStr);
            double resistance = Double.parseDouble(resistanceStr);
            double dynamicValue1 = noLoadLoss * 2; // Example calculation
            double dynamicValue2 = resistance * 3; // Example calculation

            /*// Update the table with dynamic data
            TextView dynamicData1 = binding.dynamicData1;
            TextView dynamicData2 = binding.dynamicData2;
            TextView dynamicData3 = binding.dynamicData3;
            TextView dynamicData4 = binding.dynamicData4;

            dynamicData1.setText(String.valueOf(dynamicValue1));
            dynamicData2.setText(String.valueOf(dynamicValue2));
            dynamicData3.setText("Fixed Data"); // Example fixed data
            dynamicData4.setText("Fixed Data"); // Example fixed data*/

            // Make the table visible
            binding.resultTable.setVisibility(View.VISIBLE);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
