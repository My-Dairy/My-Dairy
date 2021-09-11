package com.example.mydairy.Details;

public class LabourCharge {

    String monthly , withdraw,balance,type,amount;

    public LabourCharge(String monthly, String withdraw, String balance, String type, String amount) {
        this.monthly = monthly;
        this.withdraw = withdraw;
        this.balance = balance;
        this.type = type;
        this.amount = amount;
    }

    public String getMonthly() {
        return monthly;
    }

    public String getWithdraw() {
        return withdraw;
    }

    public String getBalance() {
        return balance;
    }

    public String getType() {
        return type;
    }

    public String getAmount() {
        return amount;
    }
}
