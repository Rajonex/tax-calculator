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



    }



}
