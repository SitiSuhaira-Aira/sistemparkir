package com.mycompany.sistemparkir;

public class KendaraanParkir {
    // Enkapsulasi: private field
    private String platNomor;
    private String pemilik;
    private int jamMasuk;

    // Variabel Static (milik bersama class)
    public static int totalKendaraanParkir = 0;

    // Constructor menggunakan this
    public KendaraanParkir(String platNomor, String pemilik, int jamMasuk) {
        this.platNomor = platNomor;
        this.pemilik = pemilik;
        this.jamMasuk = jamMasuk;
        totalKendaraanParkir++; // Increment counter static
    }

    // Getter dan Setter
    public String getPlatNomor() {
        return this.platNomor;
    }

    public void setPlatNomor(String platNomor) {
        this.platNomor = platNomor;
    }

    public String getPemilik() {
        return this.pemilik;
    }

    public void setPemilik(String pemilik) {
        this.pemilik = pemilik;
    }

    public int getJamMasuk() {
        return this.jamMasuk;
    }

    public void setJamMasuk(int jamMasuk) {
        if (jamMasuk >= 0 && jamMasuk <= 24) {
            this.jamMasuk = jamMasuk;
        } else {
            System.out.println("Jam masuk tidak valid!");
        }
    }

    // Method dasar untuk info
    public void tampilkanInfo() {
        System.out.printf("Plat: %-10s | Pemilik: %-15s | Masuk: %02d:00%n", this.platNomor, this.pemilik, this.jamMasuk);
    }

    // Method dasar hitung tarif (akan di-override)
    public void hitungTarif(int jamKeluar) {
        int durasi = jamKeluar - this.jamMasuk;
        if (durasi < 0) durasi += 24;
        System.out.println("Durasi parkir: " + durasi + " jam.");
    }
}