package com.bartoszwalter.students.factories;

import com.bartoszwalter.students.contracts.Contract;
import com.bartoszwalter.students.contracts.ContractOfEmployment;
import com.bartoszwalter.students.contracts.ContractOfMandate;

public class ContractFactory {

    public static Contract createContract(char contractCode)
    {
        if(contractCode == 'P')
        {
            return new ContractOfEmployment(111.25, 46.33);
        } else if(contractCode == 'Z'){
            return new ContractOfMandate();
        } else
        {
            throw new IllegalArgumentException("Podano nieprawidłowy identyfikator umowy.");
        }
    }
}
