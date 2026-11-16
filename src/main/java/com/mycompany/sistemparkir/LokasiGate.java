package com.mycompany.sistemparkir;

import java.io.Serializable;

public class LokasiGate implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String namaGate;
    private String zonaKampus;

    public LokasiGate(String namaGate, String zonaKampus) {
        this.namaGate = namaGate;
        this.zonaKampus = zonaKampus;
    }

    public void infoGate() {
        System.out.printf("-> [GATE UTAMA] Terhubung ke Gate: %s (Zona: %s)%n", this.namaGate, this.zonaKampus);
    }
}