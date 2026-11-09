package com.mycompany.sistemparkir;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Sistemparkir {

    public static void cariKendaraan(String plat, AreaParkir area) throws KendaraanTidakDitemukanException {
        System.out.println("\n[Pencarian Berdasarkan Plat Nomor: " + plat + "]");
        boolean ditemukan = false;
        for (int i = 0; i < area.getJumlahKendaraan(); i++) {
            if (area.getKendaraanInt(i).getPlatNomor().equalsIgnoreCase(plat)) {
                System.out.print("- Ditemukan: ");
                area.getKendaraanInt(i).tampilkanInfo();
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            throw new KendaraanTidakDitemukanException("Gagal: Kendaraan dengan plat nomor '" + plat + "' tidak terdaftar.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Instansiasi Kelas Induk Pengelola yang Menerapkan Composition & Aggregation
        AreaParkir areaUnila = new AreaParkir("Area Parkir Utama Unila", "Gate 1 Rektorat", "Zona A");
        areaUnila.bukaAreaParkir();

        boolean isRunning = true;

        System.out.println("==========================================================");
        System.out.println("   SISTEM PARKIR KAMPUS - MODUL 10 (RELASI ANTAR-OBJEK)   ");
        System.out.println("==========================================================");

        while (isRunning) {
            System.out.println("\nMenu Utama:");
            System.out.println("1. Tambah Kendaraan Masuk");
            System.out.println("2. Lihat Daftar Kendaraan & Simulasi Tarif");
            System.out.println("3. Cari Kendaraan (Custom Exception)");
            System.out.println("4. Manajemen Data Pemilik (HashMap)");
            System.out.println("5. Simulasi Transaksi Parkir Masuk (Demo Association)");
            System.out.println("6. Keluar");
            System.out.print("Pilih Menu (1-6): ");

            try {
                int pilihan = scanner.nextInt();
                scanner.nextLine();

                switch (pilihan) {
                    case 1:
                        System.out.println("\n--- Pilih Jenis Kendaraan ---");
                        System.out.println("1. Kendaraan Roda Dua (Motor)");
                        System.out.println("2. Kendaraan Roda Empat (Mobil)");
                        System.out.println("3. Kendaraan Listrik / EV");
                        System.out.print("Pilihan (1/2/3): ");
                        int jenis = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Masukkan Plat Nomor   : ");
                        String plat = scanner.nextLine();
                        System.out.print("Masukkan Nama Pemilik : ");
                        String pemilik = scanner.nextLine();
                        System.out.print("Masukkan Jam Masuk (0-24): ");
                        int jam = scanner.nextInt();
                        scanner.nextLine();

                        if (jenis == 1) {
                            System.out.print("Apakah helm berstandar SNI? (true/false): ");
                            boolean sni = scanner.nextBoolean();
                            scanner.nextLine();
                            areaUnila.tambahKendaraan(new KendaraanRodaDua(plat, pemilik, jam, sni));
                        } else if (jenis == 2) {
                            System.out.print("Masukkan Jenis Mobil (Sedan/SUV/Minibus): ");
                            String jenisMobil = scanner.nextLine();
                            areaUnila.tambahKendaraan(new KendaraanRodaEmpat(plat, pemilik, jam, jenisMobil));
                        } else if (jenis == 3) {
                            System.out.print("Masukkan Kapasitas Baterai (kWh): ");
                            int kwh = scanner.nextInt();
                            scanner.nextLine();
                            areaUnila.tambahKendaraan(new KendaraanListrik(plat, pemilik, jam, kwh));
                        } else {
                            System.out.println("Pilihan jenis kendaraan tidak valid!");
                        }
                        break;

                    case 2:
                        System.out.println("\n--- Daftar Kendaraan di Parkiran ---");
                        if (areaUnila.getJumlahKendaraan() == 0) {
                            System.out.println("Belum ada data kendaraan yang tersimpan.");
                        } else {
                            for (int i = 0; i < areaUnila.getJumlahKendaraan(); i++) {
                                System.out.print((i + 1) + ". ");
                                System.out.print("Simulasi Jam Keluar nomor " + (i+1) + " (0-24): ");
                                int jamKeluar = scanner.nextInt();
                                scanner.nextLine();
                                
                                KendaraanParkir k = areaUnila.getKendaraanInt(i);
                                k.tampilkanInfo();
                                k.hitungTarif(jamKeluar);
                                k.hitungBiayaTambahan();

                                if (k instanceof DapatAsuransi) {
                                    ((DapatAsuransi) k).klaimAsuransi();
                                }
                                if (k instanceof DapatVoucherCuci) {
                                    ((DapatVoucherCuci) k).cetakVoucherCuci();
                                }
                                System.out.println("------------------------------------------------------------------");
                            }
                            System.out.println("* Total Objek Kendaraan (Static Counter): " + KendaraanParkir.totalKendaraanParkir);
                        }
                        System.out.print("Tekan Enter untuk melanjutkan...");
                        scanner.nextLine();
                        break;

                    case 3:
                        System.out.println("\n--- Fitur Cari Kendaraan ---");
                        System.out.print("Masukkan Plat Nomor yang dicari: ");
                        String cariPlat = scanner.nextLine();
                        try {
                            cariKendaraan(cariPlat, areaUnila);
                        } catch (KendaraanTidakDitemukanException e) {
                            System.out.println("Peringatan: " + e.getMessage());
                        } finally {
                            System.out.println("-> Sesi pencarian selesai.");
                        }
                        System.out.print("Tekan Enter untuk melanjutkan...");
                        scanner.nextLine();
                        break;

                    case 4:
                        System.out.println("\n--- Manajemen Data Pemilik (Aggregation / HashMap) ---");
                        System.out.println("1. Tambah Pemilik Baru");
                        System.out.println("2. Cari Pemilik via ID");
                        System.out.println("3. Tampilkan Semua Pemilik");
                        System.out.print("Pilih Menu (1-3): ");
                        int menuP = scanner.nextInt();
                        scanner.nextLine();

                        if (menuP == 1) {
                            System.out.print("Masukkan ID Member: ");
                            String id = scanner.nextLine();
                            System.out.print("Masukkan Nama Lengkap: ");
                            String nama = scanner.nextLine();
                            areaUnila.tambahPemilik(new PemilikKendaraan(id, nama));
                        } else if (menuP == 2) {
                            System.out.print("Masukkan ID yang dicari: ");
                            areaUnila.cariPemilik(scanner.nextLine());
                        } else if (menuP == 3) {
                            areaUnila.lihatSemuaPemilik();
                        } else {
                            System.out.println("Pilihan tidak valid.");
                        }
                        System.out.print("Tekan Enter untuk melanjutkan...");
                        scanner.nextLine();
                        break;

                    case 5:
                        System.out.println("\n--- Sistem Peminjaman / Transaksi Parkir (Demo Association) ---");
                        System.out.print("Masukkan ID Member Pemilik: ");
                        String inputId = scanner.nextLine();
                        PemilikKendaraan p = areaUnila.getPemilik(inputId);

                        if (p == null) {
                            System.out.println("Gagal: Pemilik dengan ID tersebut belum terdaftar di sistem member!");
                        } else if (areaUnila.getJumlahKendaraan() == 0) {
                            System.out.println("Gagal: Belum ada kendaraan terdaftar di sistem.");
                        } else {
                            areaUnila.lihatSemuaKendaraan();
                            System.out.print("Pilih Nomor Kendaraan yang akan diproses: ");
                            int idx = scanner.nextInt() - 1;
                            scanner.nextLine();

                            if (idx >= 0 && idx < areaUnila.getJumlahKendaraan()) {
                                KendaraanParkir k = areaUnila.getKendaraanInt(idx);
                                // Menjalankan Relasi Asosiasi antara PemilikKendaraan dan KendaraanParkir
                                TransaksiParkir.prosesParkirMasuk(p, k);
                            } else {
                                System.out.println("Nomor kendaraan tidak valid!");
                            }
                        }
                        System.out.print("Tekan Enter untuk melanjutkan...");
                        scanner.nextLine();
                        break;

                    case 6:
                        System.out.println("Terima kasih telah menggunakan Sistem Parkir Kampus!");
                        isRunning = false;
                        break;

                    default:
                        System.out.println("Pilihan menu utama tidak valid (1-6).");
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("\n[FATAL ERROR] Input tidak valid! Harap masukkan angka.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("\n[ERROR] Terjadi kesalahan: " + e.getMessage());
            }
        }
        scanner.close();
    }
}