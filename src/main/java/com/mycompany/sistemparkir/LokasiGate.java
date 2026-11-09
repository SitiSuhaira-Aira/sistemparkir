package com.mycompany.sistemparkir;

public class LokasiGate {
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