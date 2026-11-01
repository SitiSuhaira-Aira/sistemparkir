package com.mycompany.sistemparkir;

public class KendaraanRodaDua extends KendaraanParkir {
    private boolean helmStandar; 

    public KendaraanRodaDua(String platNomor, String pemilik, int jamMasuk, boolean helmStandar) {
        super(platNomor, pemilik, jamMasuk); 
        this.helmStandar = helmStandar;
    }

    public boolean isHelmStandar() {
        return helmStandar;
    }

    public void setHelmStandar(boolean helmStandar) {
        this.helmStandar = helmStandar;
    }

    // Method Overriding untuk tampilkanInfo
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
}