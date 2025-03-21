package com.shimotsuji.android.taxcalculator;

public class TaxCalculator {
    public double calculateTaxIncludedPrice(double price, double taxRate) {
        return price * (1 + taxRate);
    }
}
