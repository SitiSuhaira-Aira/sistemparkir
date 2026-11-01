package com.mycompany.sistemparkir;

import java.util.Scanner;

public class Sistemparkir {

    // 1. Compile-Time Polymorphism (Static Binding): Method Overloading berdasarkan Plat Nomor (String)
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

    // 2. Compile-Time Polymorphism (Static Binding): Method Overloading berdasarkan Jam Masuk (int)
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

    // 3. Runtime Polymorphism & Dynamic Binding via Parameter Superclass (Upcasting)
    public static void simulasiProsesParkir(KendaraanParkir kendaraan, int jamKeluar) {
        System.out.println("--- Menjalankan Sistem Otomatis Kendaraan ---");
        kendaraan.tampilkanInfo(); // Dynamic binding untuk info
        kendaraan.hitungTarif(jamKeluar); // Dynamic binding untuk perhitungan tarif spesifik subclass
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Array Superclass: Wadah bertipe induk yang menampung berbagai bentuk subclass (Polimorfisme)
        KendaraanParkir[] daftarParkir = new KendaraanParkir[10];
        int jumlahParkir = 0;
        boolean isRunning = true;

        System.out.println("==================================================");
        System.out.println("   SISTEM PARKIR KAMPUS - POLIMORFISME & BINDING  ");
        System.out.println("==================================================");

        while (isRunning) {
            System.out.println("\nMenu Utama:");
            System.out.println("1. Tambah Kendaraan Masuk (Pilih Subclass)");
            System.out.println("2. Lihat Daftar & Simulasi Tarif (Runtime Polymorphism)");
            System.out.println("3. Cari Kendaraan (Compile-Time Polymorphism / Overloading)");
            System.out.println("4. Keluar");
            System.out.print("Pilih Menu (1-4): ");

            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    if (jumlahParkir < daftarParkir.length) {
                        System.out.println("\n--- Pilih Jenis Kendaraan ---");
                        System.out.println("1. Kendaraan Roda Dua (Motor)");
                        System.out.println("2. Kendaraan Roda Empat (Mobil)");
                        System.out.println("3. Kendaraan Listrik / EV (Baru)");
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

                        // Upcasting: Objek subclass disimpan ke dalam referensi/array superclass
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
                        System.out.println("Sukses! Objek berhasil didaftarkan secara polimorfis.");
                    } else {
                        System.out.println("Kapasitas area parkir penuh!");
                    }
                    break;

                case 2:
                    System.out.println("\n--- Daftar & Simulasi Dynamic Binding ---");
                    if (jumlahParkir == 0) {
                        System.out.println("Belum ada data kendaraan.");
                    } else {
                        for (int i = 0; i < jumlahParkir; i++) {
                            System.out.print((i + 1) + ". ");
                            
                            // RUNTIME POLYMORPHISM & DYNAMIC BINDING TERJADI DI SINI:
                            // Kompiler hanya melihat array sebagai 'KendaraanParkir', 
                            // namun saat runtime Java otomatis mengeksekusi method versi Subclass aslinya.
                            System.out.print("Simulasi Jam Keluar untuk nomor " + (i+1) + " (0-24): ");
                            int jamKeluar = scanner.nextInt();
                            scanner.nextLine();
                            
                            // Memanggil method dengan parameter Superclass (Upcasting parameter)
                            simulasiProsesParkir(daftarParkir[i], jamKeluar);
                            System.out.println("------------------------------------------------------------");
                        }
                        System.out.println("* Total Objek Kendaraan (Static Counter): " + KendaraanParkir.totalKendaraanParkir);
                    }
                    System.out.print("Tekan Enter untuk melanjutkan...");
                    scanner.nextLine();
                    break;

                case 3:
                    System.out.println("\n--- Fitur Cari Kendaraan (Compile-Time Polymorphism / Overloading) ---");
                    System.out.println("1. Cari Berdasarkan Plat Nomor (String)");
                    System.out.println("2. Cari Berdasarkan Jam Masuk (Integer)");
                    System.out.print("Pilih Mode (1/2): ");
                    int modeCari = scanner.nextInt();
                    scanner.nextLine();

                    if (modeCari == 1) {
                        System.out.print("Masukkan Plat Nomor: ");
                        String cariPlat = scanner.nextLine();
                        cariKendaraan(cariPlat, daftarParkir, jumlahParkir); // Keputusan oleh kompiler (Static Binding)
                    } else if (modeCari == 2) {
                        System.out.print("Masukkan Jam Masuk: ");
                        int cariJam = scanner.nextInt();
                        scanner.nextLine();
                        cariKendaraan(cariJam, daftarParkir, jumlahParkir); // Keputusan oleh kompiler (Static Binding)
                    } else {
                        System.out.println("Pilihan tidak valid.");
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
