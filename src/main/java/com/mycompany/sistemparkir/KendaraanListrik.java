package com.mycompany.sistemparkir;

public class KendaraanListrik extends KendaraanParkir implements DapatAsuransi {
    private int kapasitasBateraiKWH;

    public KendaraanListrik(String platNomor, String pemilik, int jamMasuk, int kapasitasBateraiKWH) throws Exception {
        super(platNomor, pemilik, jamMasuk);
        if (kapasitasBateraiKWH <= 0) {
            throw new Exception("Kapasitas baterai EV harus bernilai lebih dari 0 kWh!");
        }
        this.kapasitasBateraiKWH = kapasitasBateraiKWH;
    }

    public int getKapasitasBateraiKWH() {
        return kapasitasBateraiKWH;
    }

    @Override
    public void tampilkanInfo() {
        System.out.printf("[EV/Listrik] Plat: %-10s | Pemilik: %-15s | Masuk: %02d:00 | Baterai: %d kWh%n", 
                getPlatNomor(), getPemilik(), getJamMasuk(), this.kapasitasBateraiKWH);
    }

    @Override
    public void hitungTarif(int jamKeluar) {
        int durasi = jamKeluar - getJamMasuk();
        if (durasi < 0) durasi += 24;
        int tarif = durasi * 3000;
        System.out.println("-> Total Tarif Parkir EV Listrik (" + durasi + " jam): Rp " + tarif + " (Free Charging Spot)");
    }

    @Override
    public void hitungBiayaTambahan() {
        System.out.println("-> Biaya Pemeliharaan Stasiun Pengisian Daya (SPKLU): Rp 2.000");
    }

    @Override
    public void klaimAsuransi() {
        System.out.println("-> [ASURANSI] Proteksi khusus komponen kelistrikan dan baterai EV aktif.");
    }
}