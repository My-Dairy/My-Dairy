package com.example.mydairy.Details;

public class GassCharo {

    String checkbox,sukhocharoQuantity,sukhocharoAmount,tractorCharge,seeds,fertilizers,labourCharge,lilocharoSeeds,lilocharo_fertilizers, date;

    public GassCharo(String checkbox, String sukhocharoQuantity, String sukhocharoAmount, String tractorCharge, String seeds, String fertilizers, String labourCharge, String lilocharoSeeds, String lilocharo_fertilizers) {
        this.checkbox = checkbox;
        this.sukhocharoQuantity = sukhocharoQuantity;
        this.sukhocharoAmount = sukhocharoAmount;
        this.tractorCharge = tractorCharge;
        this.seeds = seeds;
        this.fertilizers = fertilizers;
        this.labourCharge = labourCharge;
        this.lilocharoSeeds = lilocharoSeeds;
        this.lilocharo_fertilizers = lilocharo_fertilizers;
    }

    public GassCharo(String checkbox, String sukhocharoQuantity, String sukhocharoAmount, String tractorCharge, String seeds, String fertilizers, String labourCharge,String date) {
        this.checkbox = checkbox;
        this.sukhocharoQuantity = sukhocharoQuantity;
        this.sukhocharoAmount = sukhocharoAmount;
        this.tractorCharge = tractorCharge;
        this.seeds = seeds;
        this.fertilizers = fertilizers;
        this.labourCharge = labourCharge;
//        this.lilocharoSeeds = lilocharoSeeds;
//        this.lilocharo_fertilizers = lilocharo_fertilizers;
        this.date=date;
        //this.time=time;
    }

    public String getDate() {
        return date;
    }

    public String getCheckbox() {
        return checkbox;
    }

    public String getSukhocharoQuantity() {
        return sukhocharoQuantity;
    }

    public String getSukhocharoAmount() {
        return sukhocharoAmount;
    }

    public String getTractorCharge() {
        return tractorCharge;
    }

    public String getSeeds() {
        return seeds;
    }

    public String getFertilizers() {
        return fertilizers;
    }

    public String getLabourCharge() {
        return labourCharge;
    }

    public String getLilocharoSeeds() {
        return lilocharoSeeds;
    }

    public String getLilocharo_fertilizers() {
        return lilocharo_fertilizers;
    }
}
