package com.example.mydairy.Details;

public class DavaDaru {

    String medicine_external , medical_visit_dairy , medical_visit_external , dose_dairy , dose_external,date;

    public DavaDaru(String medicine_external, String medical_visit_dairy, String medical_visit_external, String dose_dairy, String dose_external) {
        this.medicine_external = medicine_external;
        this.medical_visit_dairy = medical_visit_dairy;
        this.medical_visit_external = medical_visit_external;
        this.dose_dairy = dose_dairy;
        this.dose_external = dose_external;
    }

    public DavaDaru(String medicine_external, String medical_visit_external, String dose_external, String date) {
        this.medicine_external = medicine_external;
        this.medical_visit_dairy = medical_visit_dairy;
        this.medical_visit_external = medical_visit_external;
        this.dose_dairy = dose_dairy;
        this.dose_external = dose_external;
        this.date=date;
    }

    public String getDate() {
        return date;
    }

    public String getMedicine_external() {
        return medicine_external;
    }

    public String getMedical_visit_dairy() {
        return medical_visit_dairy;
    }

    public String getMedical_visit_external() {
        return medical_visit_external;
    }

    public String getDose_dairy() {
        return dose_dairy;
    }

    public String getDose_external() {
        return dose_external;
    }
}
