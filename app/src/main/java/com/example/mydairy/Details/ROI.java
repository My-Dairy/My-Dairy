package com.example.mydairy.Details;

public class ROI {
    String roi_monthly,roi_withdraw,roi_balance;

    public ROI(String roi_monthly, String roi_withdraw, String roi_balance)
    {
        this.roi_monthly=roi_monthly;
        this.roi_withdraw=roi_withdraw;
        this.roi_balance=roi_balance;
    }


    public String getRoi_monthly() {
        return roi_monthly;
    }
    public String getRoi_withdraw(){
        return roi_withdraw;
    }
    public String getRoi_balance(){
        return roi_balance;
    }

}
