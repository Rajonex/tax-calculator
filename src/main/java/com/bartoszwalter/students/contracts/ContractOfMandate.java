package com.bartoszwalter.students.contracts;

import com.bartoszwalter.students.model.DescriptionAmount;

import java.util.Map;

public class ContractOfMandate extends Contract{
    public Map<String, DescriptionAmount> calculate(double salary) {
        return null;
    }

    public String getHeader() {
        return "UMOWA-ZLECENIE";
    }
}
