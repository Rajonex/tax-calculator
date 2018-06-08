package com.bartoszwalter.students.taxes;

import org.junit.Assert;
import org.junit.Test;

public class TaxCalculatorTest {


    @Test
    public void calculateTax() {
        double calculatedTax = TaxCalculator.calculateTax(1000);
        Assert.assertEquals(180, calculatedTax, 0.0001);
    }

    @Test
    public void calculateRetirementPayment() {
        double calculatedRetirementPayment = TaxCalculator.calculateRetirementPayment(1000);
        Assert.assertEquals(97.6, calculatedRetirementPayment, 0.01);
    }

    @Test
    public void calculateRentPayment() {
        double calculatedRentPayment = TaxCalculator.calculateRentPayment(1000);
        Assert.assertEquals(15, calculatedRentPayment, 0.01);
    }

    @Test
    public void calculateHealthInsurance() {
        double calculatedHealthInsurance = TaxCalculator.calculateHealthInsurance(1000);
        Assert.assertEquals(24.5, calculatedHealthInsurance, 0.01);
    }

    @Test
    public void calculateSalaryWithoutInsurances() {

    }

    @Test
    public void calculateInsuranceNine() {

    }

    @Test
    public void calculateInsuranceSeven() {

    }

    @Test
    public void calculateTaxedSalary() {

    }

    @Test
    public void roundToWholeNumber() {

    }

    @Test
    public void roundToTwoDigitalsAfterComa() {

    }

    @Test
    public void calculateFullTax() {

    }

    @Test
    public void calculateAdevancePayment() {

    }

    @Test
    public void calculateSalaryNetto() {

    }

    @Test
    public void calculateCostsOfObtaining() {

    }
}
