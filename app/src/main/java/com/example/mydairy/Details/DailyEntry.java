package com.example.mydairy.Details;

public class DailyEntry {

    private String fat, quantity, amount;

    public DailyEntry(String fat, String quantity, String amount)
    {
           this.fat = fat;
           this.quantity = quantity;
           this.amount = amount;
    }

    public String getFat() {
        return fat;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getAmount() {
        return amount;
    }
}
