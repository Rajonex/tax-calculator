package com.bartoszwalter.students.factories;

import com.bartoszwalter.students.contracts.Contract;
import com.bartoszwalter.students.contracts.ContractOfEmployment;
import com.bartoszwalter.students.contracts.ContractOfMandate;
import org.junit.Assert;
import org.junit.Test;

public class ContractFactoryTest {

    @Test
    public void createContractOfEmployment()
    {
        Contract contract = ContractFactory.createContract('P');
        Assert.assertTrue(contract instanceof ContractOfEmployment);
    }

    @Test
    public void createContractOfMandate()
    {
        Contract contract = ContractFactory.createContract('Z');
        Assert.assertTrue(contract instanceof ContractOfMandate);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createAnotherContract() throws Exception
    {
        Contract contract = ContractFactory.createContract('a');
    }
}
