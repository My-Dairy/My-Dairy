package com.example.mydairy.Details;

public class devInv {
    String cowPurchase,cowSelling,expansionDetail,expansionAmount;

    public devInv(String cowPurchase, String cowSelling, String expansionDetail, String expansionAmount) {
        this.cowPurchase = cowPurchase;
        this.cowSelling = cowSelling;
        this.expansionDetail = expansionDetail;
        this.expansionAmount = expansionAmount;
    }

    public String getCowPurchase() {
        return cowPurchase;
    }

    public String getCowSelling() {
        return cowSelling;
    }

    public String getExpansionDetail() {
        return expansionDetail;
    }

    public String getExpansionAmount() {
        return expansionAmount;
    }
}
