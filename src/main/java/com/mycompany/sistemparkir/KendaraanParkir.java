package com.mycompany.sistemparkir;

public abstract class KendaraanParkir {
    private String platNomor;
    private String pemilik;
    private int jamMasuk;

    public static int totalKendaraanParkir = 0;

    // Menggunakan 'throws Exception' pada constructor
    public KendaraanParkir(String platNomor, String pemilik, int jamMasuk) throws Exception {
        if (jamMasuk < 0 || jamMasuk > 24) {
            throw new Exception("Jam masuk tidak valid! Harus berada di antara rentang 0 sampai 24.");
        }
        this.platNomor = platNomor;
        this.pemilik = pemilik;
        this.jamMasuk = jamMasuk;
        totalKendaraanParkir++;
    }

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
        this.jamMasuk = jamMasuk;
    }

    public void tampilkanInfo() {
        System.out.printf("Plat: %-10s | Pemilik: %-15s | Masuk: %02d:00%n", this.platNomor, this.pemilik, this.jamMasuk);
    }

    public void hitungTarif(int jamKeluar) {
        int durasi = jamKeluar - this.jamMasuk;
        if (durasi < 0) durasi += 24;
        System.out.println("Durasi parkir: " + durasi + " jam.");
    }

    public abstract void hitungBiayaTambahan();
}