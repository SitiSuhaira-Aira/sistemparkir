package com.mycompany.sistemparkir;

public class KendaraanRodaDua extends KendaraanParkir implements DapatVoucherCuci {
    private boolean helmStandar;

    public KendaraanRodaDua(String platNomor, String pemilik, int jamMasuk, boolean helmStandar) throws Exception {
        super(platNomor, pemilik, jamMasuk);
        this.helmStandar = helmStandar;
    }

    public boolean isHelmStandar() {
        return helmStandar;
    }

    @Override
    public void tampilkanInfo() {
        String statusHelm = this.helmStandar ? "SNI" : "Tidak SNI";
        System.out.printf("[Motor]      Plat: %-10s | Pemilik: %-15s | Masuk: %02d:00 | Helm: %s%n", 
                getPlatNomor(), getPemilik(), getJamMasuk(), statusHelm);
    }

    @Override
    public void hitungTarif(int jamKeluar) {
        int durasi = jamKeluar - getJamMasuk();
        if (durasi < 0) durasi += 24;
        int tarif = durasi * 2000;
        System.out.println("-> Total Tarif Parkir Motor (" + durasi + " jam): Rp " + tarif);
    }

    @Override
    public void hitungBiayaTambahan() {
        System.out.println("-> Biaya Penitipan Helm: Rp 1.000");
    }

    @Override
    public void cetakVoucherCuci() {
        System.out.println("-> [VOUCHER] Motor Anda berhak mendapatkan diskon cuci helm gratis!");
    }
}