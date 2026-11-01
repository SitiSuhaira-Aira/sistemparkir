package com.mycompany.sistemparkir;

public class KendaraanRodaEmpat extends KendaraanParkir implements DapatAsuransi, DapatVoucherCuci {
    private String jenisMobil;

    public KendaraanRodaEmpat(String platNomor, String pemilik, int jamMasuk, String jenisMobil) throws Exception {
        super(platNomor, pemilik, jamMasuk);
        if (jenisMobil == null || jenisMobil.trim().isEmpty()) {
            throw new Exception("Jenis mobil tidak boleh kosong!");
        }
        this.jenisMobil = jenisMobil;
    }

    public String getJenisMobil() {
        return jenisMobil;
    }

    @Override
    public void tampilkanInfo() {
        System.out.printf("[Mobil]      Plat: %-10s | Pemilik: %-15s | Masuk: %02d:00 | Jenis: %s%n", 
                getPlatNomor(), getPemilik(), getJamMasuk(), this.jenisMobil);
    }

    @Override
    public void hitungTarif(int jamKeluar) {
        int durasi = jamKeluar - getJamMasuk();
        if (durasi < 0) durasi += 24;
        int tarif = durasi * 5000;
        System.out.println("-> Total Tarif Parkir Mobil (" + durasi + " jam): Rp " + tarif);
    }

    @Override
    public void hitungBiayaTambahan() {
        System.out.println("-> Biaya Kebersihan Area Parkir Mobil: Rp 5.000");
    }

    @Override
    public void klaimAsuransi() {
        System.out.println("-> [ASURANSI] Mobil terproteksi asuransi kerusakan dan kehilangan di area kampus.");
    }

    @Override
    public void cetakVoucherCuci() {
        System.out.println("-> [VOUCHER] Mobil Anda mendapatkan gratis 1x cuci steam hidrolik.");
    }
}