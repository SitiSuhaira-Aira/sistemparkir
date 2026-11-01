package com.mycompany.sistemparkir;

import java.util.Scanner;

public class Sistemparkir {

    // 1. Method Overloading: Pencarian berdasarkan Plat Nomor (String)
    public static void cariKendaraan(String plat, KendaraanParkir[] daftar, int jumlah) {
        System.out.println("\n[Hasil Pencarian Berdasarkan Plat: " + plat + "]");
        boolean ditemukan = false;
        for (int i = 0; i < jumlah; i++) {
            if (daftar[i].getPlatNomor().equalsIgnoreCase(plat)) {
                System.out.print("- Ditemukan: ");
                daftar[i].tampilkanInfo();
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            System.out.println("Kendaraan dengan plat nomor tersebut tidak ditemukan.");
        }
    }

    // 2. Method Overloading: Pencarian berdasarkan Jam Masuk (int)
    public static void cariKendaraan(int jamMasuk, KendaraanParkir[] daftar, int jumlah) {
        System.out.println("\n[Hasil Pencarian Berdasarkan Jam Masuk: " + jamMasuk + ":00]");
        boolean ditemukan = false;
        for (int i = 0; i < jumlah; i++) {
            if (daftar[i].getJamMasuk() == jamMasuk) {
                System.out.print("- Ditemukan: ");
                daftar[i].tampilkanInfo();
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            System.out.println("Tidak ada kendaraan yang masuk pada jam tersebut.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Array satu dimensi untuk menyimpan objek kendaraan parkir
        KendaraanParkir[] daftarParkir = new KendaraanParkir[10];
        int jumlahParkir = 0;
        boolean isRunning = true;

        System.out.println("==============================================");
        System.out.println("    SELAMAT DATANG DI SISTEM PARKIR KAMPUS    ");
        System.out.println("==============================================");

        while (isRunning) {
            System.out.println("\nMenu Utama:");
            System.out.println("1. Tambah Kendaraan Masuk");
            System.out.println("2. Lihat Daftar Parkir & Hitung Tarif");
            System.out.println("3. Cari Kendaraan (Method Overloading)");
            System.out.println("4. Keluar");
            System.out.print("Pilih Menu (1-4): ");

            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Membersihkan buffer newline

            switch (pilihan) {
                case 1:
                    if (jumlahParkir < daftarParkir.length) {
                        System.out.println("\n--- Pilih Jenis Kendaraan ---");
                        System.out.println("1. Kendaraan Roda Dua (Motor)");
                        System.out.println("2. Kendaraan Roda Empat (Mobil)");
                        System.out.print("Pilihan (1/2): ");
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
                            // Polimorfisme & Instansiasi Subclass Motor
                            daftarParkir[jumlahParkir] = new KendaraanRodaDua(plat, pemilik, jam, sni);
                        } else if (jenis == 2) {
                            System.out.print("Masukkan Jenis Mobil (Sedan/SUV/Minibus): ");
                            String jenisMobil = scanner.nextLine();
                            // Polimorfisme & Instansiasi Subclass Mobil
                            daftarParkir[jumlahParkir] = new KendaraanRodaEmpat(plat, pemilik, jam, jenisMobil);
                        } else {
                            System.out.println("Pilihan tidak valid!");
                            break;
                        }

                        jumlahParkir++;
                        System.out.println("Sukses! Kendaraan berhasil didaftarkan ke area parkir.");
                    } else {
                        System.out.println("Maaf, kapasitas area parkir sudah penuh!");
                    }
                    break;

                case 2:
                    System.out.println("\n--- Daftar Kendaraan di Parkiran Kampus ---");
                    if (jumlahParkir == 0) {
                        System.out.println("Belum ada kendaraan yang terdaftar.");
                    } else {
                        for (int i = 0; i < jumlahParkir; i++) {
                            System.out.print((i + 1) + ". ");
                            daftarParkir[i].tampilkanInfo(); // Memanggil method overriding
                            
                            // Simulasi pengecekan tarif keluar
                            System.out.print("   -> Simulasi Jam Keluar kendaraan ini (0-24): ");
                            int jamKeluar = scanner.nextInt();
                            scanner.nextLine();
                            daftarParkir[i].hitungTarif(jamKeluar); // Memanggil method overriding tarif
                            System.out.println("--------------------------------------------------------------------------");
                        }
                        System.out.println("* Total Seluruh Kendaraan Masuk (Static Counter): " + KendaraanParkir.totalKendaraanParkir);
                    }
                    System.out.print("Tekan Enter untuk melanjutkan...");
                    scanner.nextLine();
                    break;

                case 3:
                    System.out.println("\n--- Fitur Cari Kendaraan (Method Overloading) ---");
                    System.out.println("1. Cari Berdasarkan Plat Nomor (String)");
                    System.out.println("2. Cari Berdasarkan Jam Masuk (Integer)");
                    System.out.print("Pilih Mode Pencarian (1/2): ");
                    int modeCari = scanner.nextInt();
                    scanner.nextLine();

                    if (modeCari == 1) {
                        System.out.print("Masukkan Plat Nomor: ");
                        String cariPlat = scanner.nextLine();
                        cariKendaraan(cariPlat, daftarParkir, jumlahParkir); // Memanggil overload method 1
                    } else if (modeCari == 2) {
                        System.out.print("Masukkan Jam Masuk: ");
                        int cariJam = scanner.nextInt();
                        scanner.nextLine();
                        cariKendaraan(cariJam, daftarParkir, jumlahParkir); // Memanggil overload method 2
                    } else {
                        System.out.println("Pilihan mode tidak valid.");
                    }
                    System.out.print("Tekan Enter untuk melanjutkan...");
                    scanner.nextLine();
                    break;

                case 4:
                    System.out.println("Terima kasih telah menggunakan Sistem Parkir Kampus!");
                    isRunning = false;
                    break;

                default:
                    System.out.println("Pilihan tidak valid. Silakan masukkan angka 1-4.");
                    break;
            }
        }
        scanner.close();
    }
}