package com.mycompany.sistemparkir;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Sistemparkir {

    
    public static void cariKendaraan(String plat, ArrayList<KendaraanParkir> daftar) throws KendaraanTidakDitemukanException {
        System.out.println("\n[Pencarian Berdasarkan Plat Nomor: " + plat + "]");
        boolean ditemukan = false;
        for (int i = 0; i < daftar.size(); i++) {
            if (daftar.get(i).getPlatNomor().equalsIgnoreCase(plat)) {
                System.out.print("- Ditemukan: ");
                daftar.get(i).tampilkanInfo();
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            throw new KendaraanTidakDitemukanException("Gagal: Kendaraan dengan plat nomor '" + plat + "' tidak terdaftar di sistem.");
        }
    }

    public static void cariKendaraan(int jamMasuk, ArrayList<KendaraanParkir> daftar) throws KendaraanTidakDitemukanException {
        System.out.println("\n[Pencarian Berdasarkan Jam Masuk: " + jamMasuk + ":00]");
        boolean ditemukan = false;
        for (int i = 0; i < daftar.size(); i++) {
            if (daftar.get(i).getJamMasuk() == jamMasuk) {
                System.out.print("- Ditemukan: ");
                daftar.get(i).tampilkanInfo();
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            throw new KendaraanTidakDitemukanException("Gagal: Tidak ada kendaraan yang masuk pada jam " + jamMasuk + ":00.");
        }
    }

    public static void simulasiProsesParkir(KendaraanParkir kendaraan, int jamKeluar) {
        System.out.println("--- Menjalankan Sistem Otomatis Parkir ---");
        kendaraan.tampilkanInfo();
        kendaraan.hitungTarif(jamKeluar);
        kendaraan.hitungBiayaTambahan();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        
        ArrayList<KendaraanParkir> daftarParkir = new ArrayList<>();
        HashMap<String, String> dataAnggota = new HashMap<>();
        
        boolean isRunning = true;

        System.out.println("==========================================================");
        System.out.println("   SISTEM PARKIR KAMPUS - EXCEPTION & COLLECTION MODUL 9  ");
        System.out.println("==========================================================");

        while (isRunning) {
            System.out.println("\nMenu Utama:");
            System.out.println("1. Tambah Kendaraan Masuk");
            System.out.println("2. Lihat Daftar & Simulasi Tarif");
            System.out.println("3. Cari Kendaraan (Demo Custom Exception & throws)");
            System.out.println("4. Manajemen Anggota Parkir (Demo HashMap)");
            System.out.println("5. Keluar");
            System.out.print("Pilih Menu (1-5): ");

            
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
                            daftarParkir.add(new KendaraanRodaDua(plat, pemilik, jam, sni));
                        } else if (jenis == 2) {
                            System.out.print("Masukkan Jenis Mobil (Sedan/SUV/Minibus): ");
                            String jenisMobil = scanner.nextLine();
                            daftarParkir.add(new KendaraanRodaEmpat(plat, pemilik, jam, jenisMobil));
                        } else if (jenis == 3) {
                            System.out.print("Masukkan Kapasitas Baterai (kWh): ");
                            int kwh = scanner.nextInt();
                            scanner.nextLine();
                            daftarParkir.add(new KendaraanListrik(plat, pemilik, jam, kwh));
                        } else {
                            System.out.println("Pilihan jenis kendaraan tidak valid!");
                            break;
                        }

                        System.out.println("Sukses! Kendaraan berhasil ditambahkan. (Total Koleksi ArrayList: " + daftarParkir.size() + ")");
                        break;

                    case 2:
                        System.out.println("\n--- Daftar Kendaraan di Parkiran ---");
                        if (daftarParkir.isEmpty()) {
                            System.out.println("Belum ada data kendaraan yang tersimpan.");
                        } else {
                            for (int i = 0; i < daftarParkir.size(); i++) {
                                System.out.print((i + 1) + ". ");
                                System.out.print("Simulasi Jam Keluar nomor " + (i+1) + " (0-24): ");
                                int jamKeluar = scanner.nextInt();
                                scanner.nextLine();
                                
                                simulasiProsesParkir(daftarParkir.get(i), jamKeluar);

                                if (daftarParkir.get(i) instanceof DapatAsuransi) {
                                    ((DapatAsuransi) daftarParkir.get(i)).klaimAsuransi();
                                }
                                if (daftarParkir.get(i) instanceof DapatVoucherCuci) {
                                    ((DapatVoucherCuci) daftarParkir.get(i)).cetakVoucherCuci();
                                }
                                System.out.println("------------------------------------------------------------------");
                            }
                            System.out.println("* Total Objek Kendaraan (Static Counter): " + KendaraanParkir.totalKendaraanParkir);
                        }
                        System.out.print("Tekan Enter untuk melanjutkan...");
                        scanner.nextLine();
                        break;

                    case 3:
                        System.out.println("\n--- Fitur Cari Kendaraan (Demo Try-Catch-Finally) ---");
                        System.out.println("1. Cari Berdasarkan Plat Nomor (String)");
                        System.out.println("2. Cari Berdasarkan Jam Masuk (Integer)");
                        System.out.print("Pilih Mode (1/2): ");
                        int modeCari = scanner.nextInt();
                        scanner.nextLine();

                        // Exception Handling dengan try-catch-finally[cite: 8]
                        try {
                            if (modeCari == 1) {
                                System.out.print("Masukkan Plat Nomor: ");
                                String cariPlat = scanner.nextLine();
                                cariKendaraan(cariPlat, daftarParkir);
                            } else if (modeCari == 2) {
                                System.out.print("Masukkan Jam Masuk: ");
                                int cariJam = scanner.nextInt();
                                scanner.nextLine();
                                cariKendaraan(cariJam, daftarParkir);
                            } else {
                                System.out.println("Pilihan mode pencarian tidak valid.");
                            }
                        } catch (KendaraanTidakDitemukanException e) {
                            System.out.println("Peringatan Custom Exception: " + e.getMessage());
                        } finally {
                            System.out.println("-> Sesi pencarian selesai dieksekusi (Blok Finally).");
                        }

                        System.out.print("Tekan Enter untuk melanjutkan...");
                        scanner.nextLine();
                        break;

                    case 4:
                        System.out.println("\n--- Manajemen Anggota Parkir (HashMap) ---");
                        System.out.println("1. Tambah Anggota Baru (.put)");
                        System.out.println("2. Cari Anggota via ID/NPM (.containsKey & .get)");
                        System.out.println("3. Tampilkan Semua Anggota (.size & .keySet)");
                        System.out.print("Pilih Menu Anggota (1-3): ");
                        int menuAnggota = scanner.nextInt();
                        scanner.nextLine();

                        if (menuAnggota == 1) {
                            System.out.print("Masukkan ID/NPM Anggota (Key): ");
                            String idKey = scanner.nextLine();
                            System.out.print("Masukkan Nama Lengkap (Value): ");
                            String namaVal = scanner.nextLine();

                            dataAnggota.put(idKey, namaVal);
                            System.out.println("Berhasil menambahkan anggota baru ke HashMap!");
                        } else if (menuAnggota == 2) {
                            System.out.print("Masukkan ID/NPM yang dicari: ");
                            String searchId = scanner.nextLine();

                            if (dataAnggota.containsKey(searchId)) {
                                System.out.println("Ditemukan! Nama Anggota: " + dataAnggota.get(searchId));
                            } else {
                                System.out.println("Anggota dengan ID tersebut tidak terdaftar.");
                            }
                        } else if (menuAnggota == 3) {
                            System.out.println("Total Anggota Terdaftar: " + dataAnggota.size());
                            for (String kunciId : dataAnggota.keySet()) {
                                System.out.println("- ID: " + kunciId + " | Nama: " + dataAnggota.get(kunciId));
                            }
                        } else {
                            System.out.println("Pilihan menu anggota tidak valid.");
                        }

                        System.out.print("Tekan Enter untuk melanjutkan...");
                        scanner.nextLine();
                        break;

                    case 5:
                        System.out.println("Terima kasih telah menggunakan Sistem Parkir Kampus!");
                        isRunning = false;
                        break;

                    default:
                        System.out.println("Pilihan menu utama tidak valid (1-5).");
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("\n[FATAL ERROR] Input tidak valid! Anda harus memasukkan format angka.");
                scanner.nextLine(); // Membersihkan scanner buffer
            } catch (Exception e) {
                System.out.println("\n[ERROR SISTEM] Terjadi kegagalan validasi: " + e.getMessage());
            } finally {
               
            }
        }
        scanner.close();
    }
}