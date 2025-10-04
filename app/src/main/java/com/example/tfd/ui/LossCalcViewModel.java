package com.example.tfd.ui;

import androidx.lifecycle.ViewModel;

import com.example.tfd.utils.TransformerCalculations;

public class LossCalcViewModel extends ViewModel {

    // Structure to return results back to Fragment
    public static class Results {
        public double ifl;      // Full load current (A)
        public double pCuFull;  // Copper loss (full load, W)
        public double pCuHalf;  // Copper loss (half load, W)
        public double pCu3q;    // Copper loss (3/4 load, W)
        public double pCoreW;   // Core loss (W)
        public double rPercent; // Resistance percentage of V
        public double effHalf;  // Efficiency at half load
        public double eff3q;    // Efficiency at 3/4 load
        public double effFull;  // Efficiency at full load
    }

    /**
     * Compute transformer losses and efficiencies.
     *
     * @param ratedKva    Transformer rating (kVA)
     * @param ltVolt      LT Voltage (V)
     * @param resistance  Winding resistance (Ohm)
     * @param lossPerKgAt1p5T  Loss per Kg at 1.5 Tesla (optional, can be null)
     * @param coreWeightKg     Core weight (Kg, optional, can be null)
     * @param handlingFactor   Handling factor (default 1.10 if not provided)
     * @param teslaInput       Tesla value if no coreWeight provided
     */
    public Results compute(double ratedKva,
                           double ltVolt,
                           double resistance,
                           Double lossPerKgAt1p5T,
                           Double coreWeightKg,
                           double handlingFactor,
                           Double teslaInput) {

        Results out = new Results();

        // --- Full load current ---
        out.ifl = TransformerCalculations.fullLoadCurrent(ratedKva, ltVolt);

        // --- Copper losses ---
        out.pCuFull = TransformerCalculations.copperLossFull(out.ifl, resistance);
        out.pCuHalf = TransformerCalculations.copperLossAtLoad(out.pCuFull, 0.5);
        out.pCu3q   = TransformerCalculations.copperLossAtLoad(out.pCuFull, 0.75);

        // --- Core loss ---
        if (coreWeightKg != null && lossPerKgAt1p5T != null) {
            // If both values provided, use them
            out.pCoreW = TransformerCalculations.coreLoss(lossPerKgAt1p5T, coreWeightKg, handlingFactor);
        } else if (teslaInput != null) {
            // If no core weight, assume Tesla input was provided
            out.pCoreW = teslaInput;
        } else {
            // Default to 0 if nothing provided
            out.pCoreW = 0;
        }

        // --- Resistance % of voltage ---
        out.rPercent = TransformerCalculations.resistancePercentage(resistance, ltVolt);

        // --- Efficiencies ---
        out.effHalf = TransformerCalculations.efficiencyUnityPF(ratedKva, ltVolt, out.pCuHalf, out.pCoreW);
        out.eff3q   = TransformerCalculations.efficiencyUnityPF(ratedKva, ltVolt, out.pCu3q, out.pCoreW);
        out.effFull = TransformerCalculations.efficiencyUnityPF(ratedKva, ltVolt, out.pCuFull, out.pCoreW);

        return out;
    }
}
