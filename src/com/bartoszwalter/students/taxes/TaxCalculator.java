package com.bartoszwalter.students.taxes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class TaxCalculator {

	public static double wage = 0; //pensja
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
			wage = Double.parseDouble(br.readLine());

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
			System.out.println("Podstawa wymiaru składek " + wage);

			double healthInsuranceNetto = obliczonaPodstawa(wage);

			System.out.println("Składka na ubezpieczenie emerytalne " + df00.format(retirementPayment));
			System.out.println("Składka na ubezpieczenie rentowe    " + df00.format(rentPayment));
			System.out.println("Składka na ubezpieczenie chorobowe  " + df00.format(healthInsurance));
			System.out.println("Podstawa wymiaru składki na ubezpieczenie zdrowotne: " + healthInsuranceNetto);

			calculateInsurance(healthInsuranceNetto);

			System.out.println("Składka na ubezpieczenie zdrowotne: 9% = " + df00.format(healthContribution9per) + " 7,75% = " + df00.format(healthContribution7per));
			System.out.println("Koszty uzyskania przychodu w stałej wysokości " + costsOfObtaining);

			double taxedWage = healthInsuranceNetto - costsOfObtaining; //pensja opodatkowana
			double taxedWageRounded = Double.parseDouble(df.format(taxedWage)); //pensja opodatkowana zaokrąglona

			System.out.println("Podstawa opodatkowania " + taxedWage + " zaokrąglona " + df.format(taxedWageRounded));


			calculateTax(taxedWageRounded);


			System.out.println("Zaliczka na podatek dochodowy 18 % = " + taxPrepayment);
			System.out.println("Kwota wolna od podatku = " + taxReducingAmount);

			double fullTax = taxPrepayment - taxReducingAmount;

			System.out.println("Podatek potrącony = " + df00.format(fullTax));


			calculateAdvancePayment();


			advancedPaymentTaxOfficeRounded = Double.parseDouble(df.format(advancedPaymentTaxOffice));
			System.out.println("Zaliczka do urzędu skarbowego = "
					+ df00.format(advancedPaymentTaxOffice) + " po zaokrągleniu = "
					+ df.format(advancedPaymentTaxOfficeRounded));

			double wageNetto = wage - ((retirementPayment + rentPayment + healthInsurance) + healthContribution9per + advancedPaymentTaxOfficeRounded);

			System.out.println();
			System.out
					.println("Pracownik otrzyma wynagrodzenie netto w wysokości = " + df00.format(wageNetto));

		} else if (employmentContract == 'Z') {
			System.out.println("UMOWA-ZLECENIE");
			System.out.println("Podstawa wymiaru składek " + wage);

			double healthInsuranceNetto = obliczonaPodstawa(wage);

			System.out.println("Składka na ubezpieczenie emerytalne " + df00.format(retirementPayment));
			System.out.println("Składka na ubezpieczenie rentowe    " + df00.format(rentPayment));
			System.out.println("Składka na ubezpieczenie chorobowe  " + df00.format(healthInsurance));
			System.out.println("Podstawa wymiaru składki na ubezpieczenie zdrowotne: " + healthInsuranceNetto);
			calculateInsurance(healthInsuranceNetto);
			System.out.println("Składka na ubezpieczenie zdrowotne: 9% = " + df00.format(healthContribution9per) + " 7,75% = " + df00.format(healthContribution7per));
			taxReducingAmount = 0;
			costsOfObtaining = (healthInsuranceNetto * 20) / 100;
			System.out.println("Koszty uzyskania przychodu (stałe) " + costsOfObtaining);

			double podstawaOpodat = healthInsuranceNetto - costsOfObtaining;
			double podstawaOpodat0 = Double.parseDouble(df.format(podstawaOpodat));

			System.out.println("Podstawa opodatkowania " + podstawaOpodat + " zaokrąglona " + df.format(podstawaOpodat0));

			calculateTax(podstawaOpodat0);


			System.out.println("Zaliczka na podatek dochodowy 18 % = "
					+ taxPrepayment);

			double podatekPotracony = taxPrepayment;

			System.out.println("Podatek potrącony = "
					+ df00.format(podatekPotracony));


			calculateAdvancePayment();


			advancedPaymentTaxOfficeRounded = Double.parseDouble(df.format(advancedPaymentTaxOffice));
			System.out.println("Zaliczka do urzędu skarbowego = "
					+ df00.format(advancedPaymentTaxOffice) + " po zaokrągleniu = "
					+ df.format(advancedPaymentTaxOfficeRounded));

			double wynagrodzenie = wage
					- ((retirementPayment + rentPayment + healthInsurance) + healthContribution9per + advancedPaymentTaxOfficeRounded);

			System.out.println();
			System.out
					.println("Pracownik otrzyma wynagrodzenie netto w wysokości = "
							+ df00.format(wynagrodzenie));
		} else {
			System.out.println("Nieznany typ umowy!");
		}
	}

	public static void calculateAdvancePayment() {
		advancedPaymentTaxOffice = taxPrepayment - healthContribution7per - taxReducingAmount;
	}

	public static void calculateTax(double podstawa) {
		taxPrepayment = (podstawa * 18) / 100;
	}

	public static double obliczonaPodstawa(double podstawa) { // TODO zamiast nadpisywac wartosci globalne zwracac obiekt z tymi wartosciami
		retirementPayment = (podstawa * 9.76) / 100;
		rentPayment = (podstawa * 1.5) / 100;
		healthInsurance = (podstawa * 2.45) / 100;
		return (podstawa - retirementPayment - rentPayment - healthInsurance);
	}

	public static void calculateInsurance(double podstawa) {
		healthContribution9per = (podstawa * 9) / 100;
		healthContribution7per = (podstawa * 7.75) / 100;
	}
}
