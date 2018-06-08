package com.bartoszwalter.students.model;

public class DescriptionAmount {

    private String description;
    private double amount;
    private String formattedAmount;

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public String getFormattedAmount() {
        return formattedAmount;
    }

    public DescriptionAmount(String description, double amount, String formattedAmount) {
        this.description = description;
        this.amount = amount;
        this.formattedAmount = formattedAmount;
    }
}
