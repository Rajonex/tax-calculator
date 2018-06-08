package com.bartoszwalter.students.taxes;

import com.bartoszwalter.students.contracts.Contract;
import com.bartoszwalter.students.factories.ContractFactory;
import com.bartoszwalter.students.model.DescriptionAmount;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Map;

public class TaxCalculator {

    public static double salary = 0; //pensja
    public static char employmentContract = ' '; //rodzaj umowy
    // składki na ubezpieczenia społeczne
    public static double retirementPayment = 0; //składka emerytalna (9,76% pensji)
    public static double rentPayment = 0; // składka rentowa (1,5% pensji)
    public static double healthInsurance = 0; // ubezpieczenie zdrowotne (2,45% pensji)
    // składki na ubezpieczenia zdrowotne
    public static double costsOfObtaining = 111.25; //koszty uzyskania
    public static double healthContribution9per = 0; // składka zdrowotna od pensji wymiaru 9%
    public static double healthContribution7per = 0; // składka zdrowotna od pensji wymiaru 7,75%
    public static double taxPrepayment = 0; // zaliczka na podatek dochodowy 18%
    public static double taxReducingAmount = 46.33; // kwota zmienjszająca podatek 46,33 PLN
    public static double advancedPaymentTaxOffice = 0; //zaliczka do urzędu skarbowego
    public static double advancedPaymentTaxOfficeRounded = 0; //zaliczka do urzędu skarbowego po zaokrągleniu

    public static void main(String[] args) {
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);

            System.out.print("Podaj kwotę dochodu: ");
            salary = Double.parseDouble(br.readLine());

            System.out.print("Typ umowy: (P)raca, (Z)lecenie: ");
            employmentContract = br.readLine().charAt(0);

        } catch (Exception ex) { // TODO obsluga roznych exception lub zamiana BufferedReader  na Scanner
            System.out.println("Błędna kwota");
            System.err.println(ex);
            return;
        }

        DecimalFormat df00 = new DecimalFormat("#.00");
        DecimalFormat df = new DecimalFormat("#");

        if (employmentContract == 'P') {
            System.out.println("UMOWA O PRACĘ");
            System.out.println("Podstawa wymiaru składek " + salary);


            retirementPayment = calculateRetirementPayment(salary);
            rentPayment = calculateRentPayment(salary);
            healthInsurance = calculateHealthInsurance(salary);

            double healthInsuranceNetto = calculateWIthoutInsurances(salary, retirementPayment, rentPayment, healthInsurance);

//			double healthInsuranceNetto = obliczonaPodstawa(salary);

            System.out.println("Składka na ubezpieczenie emerytalne " + df00.format(retirementPayment));
            System.out.println("Składka na ubezpieczenie rentowe    " + df00.format(rentPayment));
            System.out.println("Składka na ubezpieczenie chorobowe  " + df00.format(healthInsurance));
            System.out.println("Podstawa wymiaru składki na ubezpieczenie zdrowotne: " + healthInsuranceNetto);

            healthContribution7per = calculateInsuranceSeven(healthInsuranceNetto);
            healthContribution9per = calculateInsuranceNine(healthInsuranceNetto);
//			calculateInsurance(healthInsuranceNetto);

            System.out.println("Składka na ubezpieczenie zdrowotne: 9% = " + df00.format(healthContribution9per) + " 7,75% = " + df00.format(healthContribution7per));
            System.out.println("Koszty uzyskania przychodu w stałej wysokości " + costsOfObtaining);

//			double taxedSalary = healthInsuranceNetto - costsOfObtaining; //pensja opodatkowana
            double taxedSalary = calculateTaxedSalary(healthInsuranceNetto, costsOfObtaining);
//			double taxedSalaryRounded = Double.parseDouble(df.format(taxedSalary)); //pensja opodatkowana zaokrąglona
            double taxedSalaryRounded = roundToWholeNumber(taxedSalary);

            System.out.println("Podstawa opodatkowania " + taxedSalary + " zaokrąglona " + df.format(taxedSalaryRounded));


            taxPrepayment = calculateTax(taxedSalaryRounded);


            System.out.println("Zaliczka na podatek dochodowy 18 % = " + taxPrepayment);
            System.out.println("Kwota wolna od podatku = " + taxReducingAmount);

//			double fullTax = taxPrepayment - taxReducingAmount;
            double fullTax = calculateFullTax(taxPrepayment, taxReducingAmount);

            System.out.println("Podatek potrącony = " + df00.format(fullTax));


            advancedPaymentTaxOffice = calculateAdvancePayment(taxPrepayment, healthContribution7per, taxReducingAmount);


//			advancedPaymentTaxOfficeRounded = Double.parseDouble(df.format(advancedPaymentTaxOffice));
            advancedPaymentTaxOfficeRounded = roundToWholeNumber(advancedPaymentTaxOffice);

            System.out.println("Zaliczka do urzędu skarbowego = "
                    + df00.format(advancedPaymentTaxOffice) + " po zaokrągleniu = "
                    + df.format(advancedPaymentTaxOfficeRounded));

//			double wageNetto = salary - ((retirementPayment + rentPayment + healthInsurance) + healthContribution9per + advancedPaymentTaxOfficeRounded);
            double wageNetto = calculateSalaryNetto(salary, retirementPayment, rentPayment, healthInsurance, healthContribution9per, advancedPaymentTaxOfficeRounded);

            System.out.println();
            System.out.println("Pracownik otrzyma wynagrodzenie netto w wysokości = " + df00.format(wageNetto));


            /******************************************************************************
             *
             * ROZWIAZANIE PO REFAKTORYZACJI
             *
             */

            System.out.println();
            System.out.println("*********************************************************************");
            System.out.println();

            try {
                Contract contract = ContractFactory.createContract(employmentContract);

                Map<String, DescriptionAmount> calculatedValuesAndDescriptions = contract.calculate(salary);

                System.out.println(contract.getHeader());
                for (Map.Entry<String, DescriptionAmount> valueAndDescription: calculatedValuesAndDescriptions.entrySet())
                {
                    System.out.println(valueAndDescription.getValue().getDescription() + valueAndDescription.getValue().getFormattedAmount());
                }


            } catch (IllegalArgumentException er) {
                System.out.println("Nieznany typ umowy!");
            }


            /***************************************
             *
             * KONIEC
             *
             */
        } else if (employmentContract == 'Z') {
            System.out.println("UMOWA-ZLECENIE");
            System.out.println("Podstawa wymiaru składek " + salary);

//			double healthInsuranceNetto = obliczonaPodstawa(salary);

            retirementPayment = calculateRetirementPayment(salary);
            rentPayment = calculateRentPayment(salary);
            healthInsurance = calculateHealthInsurance(salary);

            double healthInsuranceNetto = calculateWIthoutInsurances(salary, retirementPayment, rentPayment, healthInsurance);

            System.out.println("Składka na ubezpieczenie emerytalne " + df00.format(retirementPayment));
            System.out.println("Składka na ubezpieczenie rentowe    " + df00.format(rentPayment));
            System.out.println("Składka na ubezpieczenie chorobowe  " + df00.format(healthInsurance));
            System.out.println("Podstawa wymiaru składki na ubezpieczenie zdrowotne: " + healthInsuranceNetto);

            healthContribution9per = calculateInsuranceNine(healthInsuranceNetto);
            healthContribution7per = calculateInsuranceSeven(healthInsuranceNetto);

//			calculateInsurance(healthInsuranceNetto);
            System.out.println("Składka na ubezpieczenie zdrowotne: 9% = " + df00.format(healthContribution9per) + " 7,75% = " + df00.format(healthContribution7per));
            taxReducingAmount = 0;
            costsOfObtaining = calculateCostOfObstaining(healthInsuranceNetto);
            System.out.println("Koszty uzyskania przychodu (stałe) " + costsOfObtaining);

            double baseOfTax = healthInsuranceNetto - costsOfObtaining;
            double baseOfTaxRounded = Double.parseDouble(df.format(baseOfTax));

            System.out.println("Podstawa opodatkowania " + baseOfTax + " zaokrąglona " + df.format(baseOfTaxRounded));

            taxPrepayment = calculateTax(baseOfTaxRounded);


            System.out.println("Zaliczka na podatek dochodowy 18 % = "
                    + taxPrepayment);

            double taxDeducted = taxPrepayment;

            System.out.println("Podatek potrącony = "
                    + df00.format(taxDeducted));


            advancedPaymentTaxOffice = calculateAdvancePayment(taxPrepayment, healthContribution7per, taxReducingAmount);


//			advancedPaymentTaxOfficeRounded = Double.parseDouble(df.format(advancedPaymentTaxOffice));
            advancedPaymentTaxOfficeRounded = roundToWholeNumber(advancedPaymentTaxOffice);
            System.out.println("Zaliczka do urzędu skarbowego = "
                    + df00.format(advancedPaymentTaxOffice) + " po zaokrągleniu = "
                    + df.format(advancedPaymentTaxOfficeRounded));

//			double salary = salary - ((retirementPayment + rentPayment + healthInsurance) + healthContribution9per + advancedPaymentTaxOfficeRounded);
            double salary = calculateSalaryNetto(TaxCalculator.salary, retirementPayment, rentPayment, healthInsurance, healthContribution9per, advancedPaymentTaxOfficeRounded);

            System.out.println();
            System.out.println("Pracownik otrzyma salary netto w wysokości = "
                    + df00.format(salary));
        } else {
            System.out.println("Nieznany typ umowy!");
        }
    }

//	public static void calculateAdvancePayment() {
//		advancedPaymentTaxOffice = taxPrepayment - healthContribution7per - taxReducingAmount;
//	}

//	public static void calculateTax(double podstawa) {
//		taxPrepayment = (podstawa * 18) / 100;
//	}

    /**
     * NEW SEGMENT
     */
    public static double calculateTax(double taxedSalary) {
        return (taxedSalary * 18) / 100;
    }

    public static double calculateRetirementPayment(double amountBrutto) {
        return (amountBrutto * 9.76) / 100;
    }

    public static double calculateRentPayment(double amountBrutto) {
        return (amountBrutto * 1.5) / 100;
    }

    public static double calculateHealthInsurance(double amountBrutto) {
        return (amountBrutto * 2.45) / 100;
    }

    public static double calculateWIthoutInsurances(double amountBrutto, double retirementPayment, double rentPayment, double healthInsurance) {
        return amountBrutto - retirementPayment - rentPayment - healthInsurance;
    }

    public static double calculateInsuranceNine(double amountNetto) {
        return (amountNetto * 9) / 100;
    }

    public static double calculateInsuranceSeven(double amountNetto) {
        return (amountNetto * 7.75) / 100;
    }

    public static double calculateTaxedSalary(double healthInsuranceNetto, double costsOfObtaining) {
        return healthInsuranceNetto - costsOfObtaining;
    }

    public static double roundToWholeNumber(double number) {
        DecimalFormat decimalFormat = new DecimalFormat("#");
        return Double.parseDouble(decimalFormat.format(number));
    }

    public static double roundToTwoDigitalsAfterComa(double number) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        return Double.parseDouble(decimalFormat.format(number));
    }

    public static double calculateFullTax(double taxPrepayment, double taxReducingAmount) {
        return taxPrepayment - taxReducingAmount;
    }

    public static double calculateAdvancePayment(double taxPrepayment, double healthContributionSeven, double taxReducingAmount) {
        return taxPrepayment - healthContributionSeven - taxReducingAmount;
    }

    public static double calculateSalaryNetto(double salary, double retirementPayment, double rentPayment, double healthInsurance, double healthContributionNine, double advancedPaymentTaxOfficeRounded) {
        return salary - ((retirementPayment + rentPayment + healthInsurance) + healthContributionNine + advancedPaymentTaxOfficeRounded);
    }

    public static double calculateCostOfObstaining(double healthInsuranceNetto) {
        return (healthInsuranceNetto * 20) / 100;
    }
    /**
     * END SEGMENT
     */

//	public static double obliczonaPodstawa(double podstawa) { // TODO zamiast nadpisywac wartosci globalne zwracac obiekt z tymi wartosciami
//		retirementPayment = (podstawa * 9.76) / 100;
//		rentPayment = (podstawa * 1.5) / 100;
//		healthInsurance = (podstawa * 2.45) / 100;
//		return (podstawa - retirementPayment - rentPayment - healthInsurance);
//	}

//	public static void calculateInsurance(double podstawa) {
//		healthContribution9per = (podstawa * 9) / 100;
//		healthContribution7per = (podstawa * 7.75) / 100;
//	}
}
