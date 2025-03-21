package com.shimotsuji.android.taxcalculator;

import static org.junit.Assert.*;

import org.junit.Test;

public class TaxCalculatorTest {

    private final TaxCalculator taxCalculator = new TaxCalculator();

    @Test
    public void calculateTaxIncludedPriceTest() {
        assertEquals(110.0, taxCalculator.calculateTaxIncludedPrice(100, 0.1), 0.001);
        assertEquals(220.0, taxCalculator.calculateTaxIncludedPrice(200, 0.1), 0.001);
    }
}