package com.mycompany.sistemparkir;

import java.io.FileWriter;
import java.io.IOException;

public class TransaksiParkir {
    public static void prosesParkirMasuk(PemilikKendaraan pemilik, KendaraanParkir kendaraan) {
        System.out.println("\n===== STRUK TIKET PARKIR KAMPUS =====");
        System.out.println("Pemilik / Member : " + pemilik.getNama() + " (ID: " + pemilik.getIdMember() + ")");
        System.out.print("Kendaraan        : ");
        kendaraan.tampilkanInfo();
        System.out.println("Status           : Berhasil dicatat di Gate Masuk.");
        System.out.println("=====================================");

        // FILE I/O (WRITE): Menulis riwayat transaksi ke file teks eksternal (.txt)
        try (FileWriter pipaTulis = new FileWriter("log_transaksi_parkir.txt", true)) {
            String log = "Member: " + pemilik.getNama() + " | Plat: " + kendaraan.getPlatNomor() + " | Jam Masuk: " + kendaraan.getJamMasuk() + "\n";
            pipaTulis.write(log);
            System.out.println("-> [FILE I/O] Log transaksi berhasil dicatat ke file teks.");
        } catch (IOException e) {
            System.out.println("Peringatan: Gagal menulis log transaksi ke file. " + e.getMessage());
        }
    }
}