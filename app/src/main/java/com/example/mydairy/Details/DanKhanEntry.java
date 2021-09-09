package com.example.mydairy.Details;

public class DanKhanEntry {

    String Pashudan_Dairy, Pashudan_External, Makai_Own, Makai_External, Papdi_External, Pashuahar_Details, Pashushar_Amount;
    public DanKhanEntry(String Pashudan_Dairy, String Pashudan_External,String Makai_Own,String Makai_External,String Papdi_External,
                        String Pashuahar_Details,String Pashushar_Amount)
    {
        this.Pashudan_Dairy = Pashudan_Dairy;
        this.Pashudan_External = Pashudan_External;
        this.Makai_Own = Makai_Own;
        this.Makai_External = Makai_External;
        this.Papdi_External = Papdi_External;
        this.Pashuahar_Details = Pashuahar_Details;
        this.Pashushar_Amount = Pashushar_Amount;
    }

    public String getPashudan_Dairy() {
        return Pashudan_Dairy;
    }

    public String getPashudan_External() {
        return Pashudan_External;
    }

    public String getMakai_Own() {
        return Makai_Own;
    }

    public String getMakai_External() {
        return Makai_External;
    }

    public String getPapdi_External() {
        return Papdi_External;
    }

    public String getPashuahar_Details() {
        return Pashuahar_Details;
    }

    public String getPashushar_Amount() {
        return Pashushar_Amount;
    }
}
