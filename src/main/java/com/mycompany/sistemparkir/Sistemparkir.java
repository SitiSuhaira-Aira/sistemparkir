package com.mycompany.sistemparkir;

import java.util.Scanner;

public class Sistemparkir{

    // Compile-Time Polymorphism (Method Overloading) Berdasarkan Plat Nomor (String)
    public static void cariKendaraan(String plat, KendaraanParkir[] daftar, int jumlah) {
        System.out.println("\n[Pencarian Berdasarkan Plat Nomor: " + plat + "]");
        boolean ditemukan = false;
        for (int i = 0; i < jumlah; i++) {
            if (daftar[i].getPlatNomor().equalsIgnoreCase(plat)) {
                System.out.print("- Ditemukan: ");
                daftar[i].tampilkanInfo();
                ditemukan = true;
            }
        }
        if (!ditemukan) System.out.println("Kendaraan tidak ditemukan.");
    }

    // Compile-Time Polymorphism (Method Overloading) Berdasarkan Jam Masuk (int)
    public static void cariKendaraan(int jamMasuk, KendaraanParkir[] daftar, int jumlah) {
        System.out.println("\n[Pencarian Berdasarkan Jam Masuk: " + jamMasuk + ":00]");
        boolean ditemukan = false;
        for (int i = 0; i < jumlah; i++) {
            if (daftar[i].getJamMasuk() == jamMasuk) {
                System.out.print("- Ditemukan: ");
                daftar[i].tampilkanInfo();
                ditemukan = true;
            }
        }
        if (!ditemukan) System.out.println("Tidak ada kendaraan pada jam tersebut.");
    }

    // Runtime Polymorphism & Dynamic Binding via Parameter Superclass
    public static void simulasiProsesParkir(KendaraanParkir kendaraan, int jamKeluar) {
        System.out.println("--- Menjalankan Sistem Otomatis Parkir ---");
        kendaraan.tampilkanInfo();
        kendaraan.hitungTarif(jamKeluar);
        kendaraan.hitungBiayaTambahan(); // Memanggil abstract method yang di-override
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Array Superclass Abstract
        KendaraanParkir[] daftarParkir = new KendaraanParkir[10];
        int jumlahParkir = 0;
        boolean isRunning = true;

        System.out.println("======================================================");
        System.out.println("   SISTEM PARKIR KAMPUS - ABSTRACT & INTERFACE MODUL 7");
        System.out.println("======================================================");

        while (isRunning) {
            System.out.println("\nMenu Utama:");
            System.out.println("1. Tambah Kendaraan Masuk");
            System.out.println("2. Lihat Daftar, Tarif, & Kontrak Interface (instanceof)");
            System.out.println("3. Cari Kendaraan (Method Overloading)");
            System.out.println("4. Keluar");
            System.out.print("Pilih Menu (1-4): ");

            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    if (jumlahParkir < daftarParkir.length) {
                        System.out.println("\n--- Pilih Jenis Kendaraan ---");
                        System.out.println("1. Kendaraan Roda Dua (Motor)");
                        System.out.println("2. Kendaraan Roda Empat (Mobil - Multiple Implements)");
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
                            daftarParkir[jumlahParkir] = new KendaraanRodaDua(plat, pemilik, jam, sni);
                        } else if (jenis == 2) {
                            System.out.print("Masukkan Jenis Mobil (Sedan/SUV/Minibus): ");
                            String jenisMobil = scanner.nextLine();
                            daftarParkir[jumlahParkir] = new KendaraanRodaEmpat(plat, pemilik, jam, jenisMobil);
                        } else if (jenis == 3) {
                            System.out.print("Masukkan Kapasitas Baterai (kWh): ");
                            int kwh = scanner.nextInt();
                            scanner.nextLine();
                            daftarParkir[jumlahParkir] = new KendaraanListrik(plat, pemilik, jam, kwh);
                        } else {
                            System.out.println("Pilihan tidak valid!");
                            break;
                        }

                        jumlahParkir++;
                        System.out.println("Sukses! Kendaraan berhasil terdaftar.");
                    } else {
                        System.out.println("Kapasitas area parkir penuh!");
                    }
                    break;

                case 2:
                    System.out.println("\n--- Daftar & Pemanggilan Fitur Interface (instanceof) ---");
                    if (jumlahParkir == 0) {
                        System.out.println("Belum ada data kendaraan.");
                    } else {
                        for (int i = 0; i < jumlahParkir; i++) {
                            System.out.print((i + 1) + ". ");
                            System.out.print("Simulasi Jam Keluar untuk nomor " + (i+1) + " (0-24): ");
                            int jamKeluar = scanner.nextInt();
                            scanner.nextLine();
                            
                            // Pemanggilan Dynamic Binding & Abstract Method
                            simulasiProsesParkir(daftarParkir[i], jamKeluar);

                            // MODUL 7: Pengecekan menggunakan instanceof untuk mengakses kontrak Interface secara aman
                            if (daftarParkir[i] instanceof DapatAsuransi) {
                                ((DapatAsuransi) daftarParkir[i]).klaimAsuransi();
                            }
                            if (daftarParkir[i] instanceof DapatVoucherCuci) {
                                ((DapatVoucherCuci) daftarParkir[i]).cetakVoucherCuci();
                            }

                            System.out.println("------------------------------------------------------------------");
                        }
                        System.out.println("* Total Objek Kendaraan (Static Counter): " + KendaraanParkir.totalKendaraanParkir);
                    }
                    System.out.print("Tekan Enter untuk melanjutkan...");
                    scanner.nextLine();
                    break;

                case 3:
                    System.out.println("\n--- Fitur Cari Kendaraan (Method Overloading) ---");
                    System.out.println("1. Cari Berdasarkan Plat Nomor (String)");
                    System.out.println("2. Cari Berdasarkan Jam Masuk (Integer)");
                    System.out.print("Pilih Mode (1/2): ");
                    int modeCari = scanner.nextInt();
                    scanner.nextLine();

                    if (modeCari == 1) {
                        System.out.print("Masukkan Plat Nomor: ");
                        String cariPlat = scanner.nextLine();
                        cariKendaraan(cariPlat, daftarParkir, jumlahParkir);
                    } else if (modeCari == 2) {
                        System.out.print("Masukkan Jam Masuk: ");
                        int cariJam = scanner.nextInt();
                        scanner.nextLine();
                        cariKendaraan(cariJam, daftarParkir, jumlahParkir);
                    } else {
                        System.out.println("Pilihan mode tidak valid.");
                    }
                    System.out.print("Tekan Enter untuk melanjutkan...");
                    scanner.nextLine();
                    break;

                case 4:
                    System.out.println("Terima kasih!");
                    isRunning = false;
                    break;

                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }
        }
        scanner.close();
    }
}