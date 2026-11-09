package com.mycompany.sistemparkir;

public class PemilikKendaraan {
    private String idMember;
    private String nama;

    public PemilikKendaraan(String idMember, String nama) {
        this.idMember = idMember;
        this.nama = nama;
    }

    public String getIdMember() {
        return idMember;
    }

    public String getNama() {
        return nama;
    }
}