package com.bartoszwalter.students.taxes;

import com.bartoszwalter.students.contracts.Contract;
import com.bartoszwalter.students.factories.ContractFactory;
import com.bartoszwalter.students.model.DescriptionAmount;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Map;

public class TaxCalculator
{

    public static double salary = 0; //pensja
    public static char employmentContract = ' '; //rodzaj umowy


    public static void main(String[] args)
    {
        try
        {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);

            System.out.print("Podaj kwotę dochodu: ");
            salary = Double.parseDouble(br.readLine());

            System.out.print("Typ umowy: (P)raca, (Z)lecenie: ");
            employmentContract = br.readLine().charAt(0);

        } catch (Exception ex)
        { // TODO obsluga roznych exception lub zamiana BufferedReader  na Scanner
            System.out.println("Błędna kwota");
            System.err.println(ex);
            return;
        }


        if (employmentContract == 'P')
        {


            /******************************************************************************
             *
             * ROZWIAZANIE PO REFAKTORYZACJI
             *
             */

            System.out.println();
            System.out.println("*********************************************************************");
            System.out.println();

            try
            {
                Contract contract = ContractFactory.createContract(employmentContract);

                Map<String, DescriptionAmount> calculatedValuesAndDescriptions = contract.calculate(salary);

                System.out.println(contract.getHeader());
                for (Map.Entry<String, DescriptionAmount> valueAndDescription : calculatedValuesAndDescriptions.entrySet())
                {
                    System.out.println(valueAndDescription.getValue().getDescription() + valueAndDescription.getValue().getFormattedAmount());
                }


            } catch (IllegalArgumentException er)
            {
                System.out.println("Nieznany typ umowy!");
            }


            /***************************************
             *
             * KONIEC
             *
             */
        } else if (employmentContract == 'Z')
        {


            /******************************************************************************
             *
             * ROZWIAZANIE PO REFAKTORYZACJI
             *
             */

            System.out.println();
            System.out.println("*********************************************************************");
            System.out.println();

            try
            {
                Contract contract = ContractFactory.createContract(employmentContract); //zwraca nowy ContractOfMandate

                Map<String, DescriptionAmount> calculatedValuesAndDescriptions = contract.calculate(salary);
                //mapa ktora ma w sobie obliczone wartosci

                System.out.println(contract.getHeader());
                //dla kazdej wartosci w mapie
                for (Map.Entry<String, DescriptionAmount> valueAndDescription : calculatedValuesAndDescriptions.entrySet())
                {
                    //wyswietl opis i kwote
                    System.out.println(valueAndDescription.getValue().getDescription() + valueAndDescription.getValue().getFormattedAmount());
                }


            } catch (IllegalArgumentException er)
            {
                System.out.println("Nieznany typ umowy!");
            }


        } else
        {
            System.out.println("Nieznany typ umowy!");
        }
    }


    /**
     * NEW SEGMENT
     */
    public static double calculateTax(double taxedSalary)
    {
        return (taxedSalary * 18) / 100;
    }

    public static double calculateRetirementPayment(double amountBrutto)
    {
        return (amountBrutto * 9.76) / 100;
    }

    public static double calculateRentPayment(double amountBrutto)
    {
        return (amountBrutto * 1.5) / 100;
    }

    public static double calculateHealthInsurance(double amountBrutto)
    {
        return (amountBrutto * 2.45) / 100;
    }

    public static double calculateWIthoutInsurances(double amountBrutto, double retirementPayment, double rentPayment, double healthInsurance)
    {
        return amountBrutto - retirementPayment - rentPayment - healthInsurance;
    }

    public static double calculateInsuranceNine(double amountNetto)
    {
        return (amountNetto * 9) / 100;
    }

    public static double calculateInsuranceSeven(double amountNetto)
    {
        return (amountNetto * 7.75) / 100;
    }

    public static double calculateTaxedSalary(double healthInsuranceNetto, double costsOfObtaining)
    {
        return healthInsuranceNetto - costsOfObtaining;
    }

    public static double roundToWholeNumber(double number)
    {
        DecimalFormat decimalFormat = new DecimalFormat("#");
        return Double.parseDouble(decimalFormat.format(number));
    }

    public static double roundToTwoDigitalsAfterComa(double number)
    {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        return Double.parseDouble(decimalFormat.format(number));
    }

    public static double calculateFullTax(double taxPrepayment, double taxReducingAmount)
    {
        return taxPrepayment - taxReducingAmount;
    }

    public static double calculateAdvancePayment(double taxPrepayment, double healthContributionSeven, double taxReducingAmount)
    {
        return taxPrepayment - healthContributionSeven - taxReducingAmount;
    }

    public static double calculateSalaryNetto(double salary, double retirementPayment, double rentPayment, double healthInsurance, double healthContributionNine, double advancedPaymentTaxOfficeRounded)
    {
        return salary - ((retirementPayment + rentPayment + healthInsurance) + healthContributionNine + advancedPaymentTaxOfficeRounded);
    }

    public static double calculateCostOfObstaining(double healthInsuranceNetto)
    {
        return (healthInsuranceNetto * 20) / 100;
    }
    /**
     * END SEGMENT
     */

}
