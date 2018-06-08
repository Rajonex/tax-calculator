package com.bartoszwalter.students.contracts;

import com.bartoszwalter.students.model.DescriptionAmount;

import java.text.DecimalFormat;
import java.util.Map;

public abstract class Contract {
    public abstract Map<String, DescriptionAmount> calculate(double salary);

    public abstract String getHeader();

    protected double calculateTax(double taxedSalary)
    {
        return (taxedSalary * 18) / 100;
    }

    protected double calculateRetirementPayment(double amountBrutto)
    {
        return (amountBrutto * 9.76) / 100;
    }

    protected double calculateRentPayment(double amountBrutto)
    {
        return (amountBrutto * 1.5) / 100;
    }

    protected double calculateHealthInsurance(double amountBrutto)
    {
        return (amountBrutto * 2.45) / 100;
    }

    protected double calculateWithoutInsurances(double amountBrutto, double retirementPayment, double rentPayment, double healthInsurance)
    {
        return amountBrutto - retirementPayment - rentPayment - healthInsurance;
    }

    protected double calculateInsuranceNine(double amountNetto)
    {
        return (amountNetto * 9) / 100;
    }

    protected double calculateInsuranceSeven(double amountNetto)
    {
        return (amountNetto * 7.75) / 100;
    }

    protected double calculateTaxedSalary(double healthInsuranceNetto, double costsOfObtaining)
    {
        return healthInsuranceNetto - costsOfObtaining;
    }

    protected double roundToWholeNumber(double number)
    {
        DecimalFormat decimalFormat = new DecimalFormat("#");
        return Double.parseDouble(decimalFormat.format(number));
    }

    protected double roundToTwoDigitalsAfterComa(double number)
    {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        return Double.parseDouble(decimalFormat.format(number));
    }

    protected double calculateFullTax(double taxPrepayment, double taxReducingAmount)
    {
        return taxPrepayment - taxReducingAmount;
    }

    protected double calculateAdvancePayment(double taxPrepayment, double healthContributionSeven, double taxReducingAmount) {
        return taxPrepayment - healthContributionSeven - taxReducingAmount;
    }

    protected double calculateSalaryNetto(double salary, double retirementPayment, double rentPayment, double healthInsurance, double healthContributionNine, double advancedPaymentTaxOfficeRounded)
    {
        return salary - ((retirementPayment + rentPayment + healthInsurance) + healthContributionNine + advancedPaymentTaxOfficeRounded);
    }

    protected double calculateCostOfObstaining(double healthInsuranceNetto)
    {
        return (healthInsuranceNetto * 20) / 100;
    }
}
