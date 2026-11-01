package com.mycompany.sistemparkir;

public class KendaraanRodaEmpat extends KendaraanParkir {
    private String jenisMobil; 

    public KendaraanRodaEmpat(String platNomor, String pemilik, int jamMasuk, String jenisMobil) {
        super(platNomor, pemilik, jamMasuk);
        this.jenisMobil = jenisMobil;
    }

    public String getJenisMobil() {
        return jenisMobil;
    }

    public void setJenisMobil(String jenisMobil) {
        this.jenisMobil = jenisMobil;
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
}