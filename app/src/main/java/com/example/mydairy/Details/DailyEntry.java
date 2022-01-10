package com.example.mydairy.Details;

public class DailyEntry {

    private String fat, quantity, amount, date, time;

    public DailyEntry(String fat, String quantity, String amount)
    {
           this.fat = fat;
           this.quantity = quantity;
           this.amount = amount;
           //this.time = time;
    }

    public DailyEntry(String fat, String quantity, String amount, String date, String time) {
        this.fat = fat;
        this.quantity = quantity;
        this.amount = amount;
        this.date = date;
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
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
