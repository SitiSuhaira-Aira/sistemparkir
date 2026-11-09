package com.mycompany.sistemparkir;

public class TransaksiParkir {
    public static void prosesParkirMasuk(PemilikKendaraan pemilik, KendaraanParkir kendaraan) {
        System.out.println("\n===== STRUK TIKET PARKIR KAMPUS =====");
        System.out.println("Pemilik / Member : " + pemilik.getNama() + " (ID: " + pemilik.getIdMember() + ")");
        System.out.print("Kendaraan        : ");
        kendaraan.tampilkanInfo();
        System.out.println("Status           : Berhasil dicatat di Gate Masuk.");
        System.out.println("=====================================");
    }
}