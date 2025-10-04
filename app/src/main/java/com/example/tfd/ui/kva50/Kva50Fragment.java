package com.example.tfd.ui.kva50;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.Toast;
import android.widget.TextView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tfd.databinding.FragmentKva50Binding;
import com.google.android.material.textfield.TextInputEditText;
import com.example.tfd.ui.LossCalcViewModel;

public class Kva50Fragment extends Fragment {

    private FragmentKva50Binding binding;
    private LossCalcViewModel vm;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentKva50Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm = new ViewModelProvider(this).get(LossCalcViewModel.class);

        double ratedKva = parseDouble(getText(binding.specRatedKva), 50.0);   // ✅ default 50 KVA
        double ltVolt   = parseDouble(getText(binding.specLtVolt),   240.0);

        Double lossPerKgAt1p5T = tryParse(getText(binding.specLossPerKgAt1p5T));
        Double coreWeightKg    = tryParse(getText(binding.specCoreWeightKg));
        double handlingFactor  = parseDouble(getText(binding.specHandlingFactor), 1.10);

        final TextInputEditText teslaInput      = binding.NoLoadLoss;
        final TextInputEditText resistanceInput = binding.resistance;
        final Button submitButton               = binding.submitButton;
        final TableLayout tableLayout           = binding.tableLayout;

        tableLayout.setVisibility(View.GONE);

        submitButton.setOnClickListener(v -> {
            String teslaStr = safeText(teslaInput);
            String rStr     = safeText(resistanceInput);

            if (teslaStr.isEmpty() || rStr.isEmpty()) {
                Toast.makeText(getActivity(), "Please enter both Tesla and Resistance", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                double teslaOrCoreW  = Double.parseDouble(teslaStr);
                double resistanceOhm = Double.parseDouble(rStr);

                LossCalcViewModel.Results r = vm.compute(
                        ratedKva, ltVolt,
                        resistanceOhm,
                        lossPerKgAt1p5T, coreWeightKg, handlingFactor,
                        (coreWeightKg == null ? teslaOrCoreW : null)
                );

                // --- Fill table ---
                setText(binding.cellFullLoadCurrent,   fmtA(r.ifl));
                setText(binding.cellCuLossHalf,        fmtW(r.pCuHalf));
                setText(binding.cellCuLossThreeQuarter,fmtW(r.pCu3q));
                setText(binding.cellCuLossFull,        fmtW(r.pCuFull));
                setText(binding.cellCoreLossW,         fmtW(r.pCoreW));

                setText(binding.cellNoLoadLossKw,      fmtkW(r.pCoreW));
                setText(binding.cellFullLoadLossKw,    fmtkW(r.pCoreW + r.pCuFull));

                setText(binding.cellResistancePercent, String.format("%.3f%%", r.rPercent));

                setText(binding.cellEffHalf,           fmtPct(r.effHalf));
                setText(binding.cellEffThreeQuarter,   fmtPct(r.eff3q));
                setText(binding.cellEffFull,           fmtPct(r.effFull));

                // --- Hide inputs, show table ---
                teslaInput.setText("");
                resistanceInput.setText("");
                binding.teslaHeader.setVisibility(View.GONE);
                binding.inputOfNoLoadLoss.setVisibility(View.GONE);
                binding.resistanceHeader.setVisibility(View.GONE);
                binding.inputOfResistance.setVisibility(View.GONE);
                submitButton.setVisibility(View.GONE);
                tableLayout.setVisibility(View.VISIBLE);

            } catch (NumberFormatException e) {
                Toast.makeText(getActivity(), "Enter valid numbers (e.g. 1.5 and 0.75)", Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }

    // --- Helpers ---
    private static String getText(TextView tv) {
        return tv != null && tv.getText() != null ? tv.getText().toString().trim() : "";
    }
    private static String safeText(TextInputEditText et) {
        if (et == null || et.getText() == null) return "";
        return et.getText().toString().trim().replace(",", ".");
    }
    private static double parseDouble(String s, double fb) {
        try { return Double.parseDouble(s); } catch (Exception e) { return fb; }
    }
    private static Double tryParse(String s) {
        try { if (s == null || s.isEmpty()) return null; return Double.parseDouble(s); }
        catch (Exception e) { return null; }
    }
    private static void setText(TextView tv, String val) { if (tv != null) tv.setText(val); }
    private static String fmtW(double w)   { return String.format("%.2f W", w); }
    private static String fmtkW(double w)  { return String.format("%.3f kW", w / 1000.0); }
    private static String fmtA(double a)   { return String.format("%.3f A", a); }
    private static String fmtPct(double p) { return String.format("%.2f%%", p); }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
