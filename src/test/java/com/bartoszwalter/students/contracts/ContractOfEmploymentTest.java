package com.bartoszwalter.students.contracts;

import com.bartoszwalter.students.factories.ContractFactory;
import com.bartoszwalter.students.model.DescriptionAmount;
import com.bartoszwalter.students.taxes.TaxCalculator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class ContractOfEmploymentTest {


    private Contract contract;

    @Before
    public void init()
    {
        contract = ContractFactory.createContract('P');
    }

    @Test
    public void calculateTaxPrepayment() {
        Map<String, DescriptionAmount> calculatedValuesAndDescriptions = contract.calculate(1000);
        double calculatedValue = calculatedValuesAndDescriptions.get("taxPrepayment").getAmount();

        Assert.assertEquals(135.36, calculatedValue, 0.01);
    }

    @Test
    public void calculateRetirementPayment() {
        Map<String, DescriptionAmount> calculatedValuesAndDescriptions = contract.calculate(1000);
        double calculatedValue = calculatedValuesAndDescriptions.get("retirement").getAmount();

        Assert.assertEquals(97.6, calculatedValue, 0.01);
    }

    @Test
    public void calculateRentPayment() {
        Map<String, DescriptionAmount> calculatedValuesAndDescriptions = contract.calculate(1000);
        double calculatedValue = calculatedValuesAndDescriptions.get("rent").getAmount();

        Assert.assertEquals(15, calculatedValue, 0.01);
    }

    @Test
    public void calculateHealthInsurance() {
        Map<String, DescriptionAmount> calculatedValuesAndDescriptions = contract.calculate(1000);
        double calculatedValue = calculatedValuesAndDescriptions.get("healthInsurance").getAmount();

        Assert.assertEquals(24.5, calculatedValue, 0.01);
    }

    @Test
    public void calculateSalaryWithoutInsurances() {
        Map<String, DescriptionAmount> calculatedValuesAndDescriptions = contract.calculate(1000);
        double calculatedValue = calculatedValuesAndDescriptions.get("salaryWithoutInsurences").getAmount();

        Assert.assertEquals(862.9, calculatedValue, 0.01);
    }

    @Test
    public void calculateInsuranceNine() {
        Map<String, DescriptionAmount> calculatedValuesAndDescriptions = contract.calculate(1000);
        double calculatedValue = calculatedValuesAndDescriptions.get("healthContributionNine").getAmount();

        Assert.assertEquals(77.66, calculatedValue, 0.01);
    }

    @Test
    public void calculateInsuranceSeven() {
        Map<String, DescriptionAmount> calculatedValuesAndDescriptions = contract.calculate(1000);
        double calculatedValue = calculatedValuesAndDescriptions.get("healthContributionSeven").getAmount();

        Assert.assertEquals(66.87, calculatedValue, 0.01);
    }

    @Test
    public void calculateTaxedSalary() {
        Map<String, DescriptionAmount> calculatedValuesAndDescriptions = contract.calculate(1000);
        double calculatedValue = calculatedValuesAndDescriptions.get("taxedSalary").getAmount();

        Assert.assertEquals(751.65, calculatedValue, 0.01);
    }

    @Test
    public void calculateFullTax() {
        Map<String, DescriptionAmount> calculatedValuesAndDescriptions = contract.calculate(1000);
        double calculatedValue = calculatedValuesAndDescriptions.get("fullTax").getAmount();

        Assert.assertEquals(89.03, calculatedValue, 0.01);
    }

    @Test
    public void calculateAdvancePayment() {
        Map<String, DescriptionAmount> calculatedValuesAndDescriptions = contract.calculate(1000);
        double calculatedValue = calculatedValuesAndDescriptions.get("taxOffice").getAmount();

        Assert.assertEquals(22.16, calculatedValue, 0.01);
    }

    @Test
    public void calculateSalaryNetto() {
        Map<String, DescriptionAmount> calculatedValuesAndDescriptions = contract.calculate(1000);
        double calculatedValue = calculatedValuesAndDescriptions.get("salaryNetto").getAmount();

        Assert.assertEquals(763.24, calculatedValue, 0.01);
    }

    @Test
    public void calculateCostsOfObtaining() {
        Map<String, DescriptionAmount> calculatedValuesAndDescriptions = contract.calculate(1000);
        double calculatedValue = calculatedValuesAndDescriptions.get("obtainingCosts").getAmount();

        Assert.assertEquals(111.25, calculatedValue, 0.01);
    }

    @After
    public void finish()
    {
        contract = null;
    }
}
