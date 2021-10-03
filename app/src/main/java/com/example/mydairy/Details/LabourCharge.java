package com.example.mydairy.Details;

public class LabourCharge {

    String monthly , withdraw,balance,type,amount,date;

    public LabourCharge(String monthly, String withdraw, String balance, String type, String amount) {
        this.monthly = monthly;
        this.withdraw = withdraw;
        this.balance = balance;
        this.type = type;
        this.amount = amount;
    }

    public LabourCharge(String monthly, String withdraw, String balance, String type, String amount, String date) {
        this.monthly = monthly;
        this.withdraw = withdraw;
        this.balance = balance;
        this.type = type;
        this.amount = amount;
        this.date=date;
    }

    public String getDate() {
        return date;
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
