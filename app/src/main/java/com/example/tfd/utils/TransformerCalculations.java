package com.example.tfd.utils;

public class TransformerCalculations {

    /**
     * Full load current (A)
     * Formula: Ifl = (kVA * 1000) / V
     */
    public static double fullLoadCurrent(double kva, double voltage) {
        if (voltage == 0) return 0;
        return (kva * 1000.0) / voltage;
    }

    /**
     * Copper loss at full load (W)
     * Formula: Pcufull = I² * R
     */
    public static double copperLossFull(double current, double resistance) {
        return (current * current) * resistance;
    }

    /**
     * Copper loss at fractional load
     * Formula: Pcu = (fraction²) * Pcu_full
     */
    public static double copperLossAtLoad(double pCuFull, double fraction) {
        return (fraction * fraction) * pCuFull;
    }

    /**
     * Core loss (W)
     * Formula: Pcore = loss_per_kg * core_weight * handling_factor
     */
    public static double coreLoss(double lossPerKg, double coreWeightKg, double handlingFactor) {
        return lossPerKg * coreWeightKg * handlingFactor;
    }

    /**
     * Resistance percentage of voltage
     * Formula: R% = (R / V) * 100
     */
    public static double resistancePercentage(double resistance, double voltage) {
        if (voltage == 0) return 0;
        return (resistance / voltage) * 100.0;
    }

    /**
     * Efficiency at unity power factor
     * Formula: η = (Output) / (Output + Total Losses)
     * Output = kVA * 1000 W at unity PF
     */
    public static double efficiencyUnityPF(double kva, double voltage, double pCu, double pCore) {
        double output = kva * 1000.0;   // unity PF output (W)
        double losses = pCu + pCore;
        if ((output + losses) == 0) return 0;
        return (output / (output + losses)) * 100.0;
    }
}
