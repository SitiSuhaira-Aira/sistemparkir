package com.mycompany.sistemparkir;

import java.io.Serializable;

public class PemilikKendaraan implements Serializable {
    private static final long serialVersionUID = 1L; // Pengaman identitas versi objek
    
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