package com.mycompany.sistemparkir;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class AreaParkir implements Serializable {
    private static final long serialVersionUID = 1L;

    private String namaArea;
    private LokasiGate gateMasuk;
    
    private ArrayList<KendaraanParkir> daftarKendaraan;
    private HashMap<String, PemilikKendaraan> dataPemilik;

    public AreaParkir(String namaArea, String namaGate, String zona) {
        this.namaArea = namaArea;
        this.gateMasuk = new LokasiGate(namaGate, zona);
        this.daftarKendaraan = new ArrayList<>();
        this.dataPemilik = new HashMap<>();
    }

    public void bukaAreaParkir() {
        System.out.println("\n*** Menginisialisasi Sistem Parkir: " + this.namaArea + " ***");
        gateMasuk.infoGate();
    }

    public void tambahKendaraan(KendaraanParkir k) {
        daftarKendaraan.add(k);
        System.out.println("Sukses! Kendaraan terdaftar di sistem. (Total: " + daftarKendaraan.size() + ")");
    }

    public void tambahPemilik(PemilikKendaraan p) {
        dataPemilik.put(p.getIdMember(), p);
        System.out.println("Berhasil mendaftarkan data pemilik/member baru!");
    }

    public void lihatSemuaKendaraan() {
        if (daftarKendaraan.isEmpty()) {
            System.out.println("Belum ada kendaraan yang terdaftar di area parkir.");
            return;
        }
        for (int i = 0; i < daftarKendaraan.size(); i++) {
            System.out.print((i + 1) + ". ");
            daftarKendaraan.get(i).tampilkanInfo();
        }
    }

    public void cariPemilik(String id) {
        if (dataPemilik.containsKey(id)) {
            System.out.println("Ditemukan Pemilik -> ID: " + id + " | Nama: " + dataPemilik.get(id).getNama());
        } else {
            System.out.println("Pemilik dengan ID tersebut tidak terdaftar.");
        }
    }

    public void lihatSemuaPemilik() {
        System.out.println("Total Pemilik Terdaftar: " + dataPemilik.size());
        for (String key : dataPemilik.keySet()) {
            System.out.println("- ID: " + key + " | Nama: " + dataPemilik.get(key).getNama());
        }
    }

    public PemilikKendaraan getPemilik(String id) {
        return dataPemilik.get(id);
    }

    public KendaraanParkir getKendaraanInt(int index) {
        return daftarKendaraan.get(index);
    }

    public int getJumlahKendaraan() {
        return daftarKendaraan.size();
    }

    // --- FITUR SERIALIZATION (Membekukan Objek ke File Biner .dat) ---
    public void simpanDataKeFile() {
        try {
            FileOutputStream fileKeluar = new FileOutputStream("data_parkir.dat");
            ObjectOutputStream mesinPembeku = new ObjectOutputStream(fileKeluar);

            // Menyimpan ArrayList kendaraan dan HashMap pemilik sekaligus
            mesinPembeku.writeObject(this.daftarKendaraan);
            mesinPembeku.writeObject(this.dataPemilik);

            mesinPembeku.close();
            System.out.println("-> [SYSTEM LOG] Seluruh data parkir berhasil disimpan (Serialization) ke file 'data_parkir.dat'!");
        } catch (Exception e) {
            System.out.println("-> [SYSTEM ERROR] Gagal menyimpan data objek: " + e.getMessage());
        }
    }

    // --- FITUR DESERIALIZATION (Menghidupkan Objek Kembali dari File .dat) ---
    @SuppressWarnings("unchecked")
    public void muatDataDariFile() {
        try {
            File cekFile = new File("data_parkir.dat");
            if (!cekFile.exists()) {
                return; // Batalkan jika file belum ada
            }

            FileInputStream fileMasuk = new FileInputStream(cekFile);
            ObjectInputStream mesinPembangkit = new ObjectInputStream(fileMasuk);

            // Merakit kembali byte menjadi struktur ArrayList dan HashMap
            this.daftarKendaraan = (ArrayList<KendaraanParkir>) mesinPembangkit.readObject();
            this.dataPemilik = (HashMap<String, PemilikKendaraan>) mesinPembangkit.readObject();

            // Mengembalikan nilai counter static
            KendaraanParkir.totalKendaraanParkir = this.daftarKendaraan.size();

            mesinPembangkit.close();
            System.out.println("-> [SYSTEM LOG] Data sistem berhasil dimuat ulang (Deserialization) dari penyimpanan!");
        } catch (Exception e) {
            System.out.println("-> [SYSTEM ERROR] Gagal memuat data objek: " + e.getMessage());
        }
    }

    // --- FITUR MEMBACA FILE TEKS (File I/O Read Menggunakan Scanner) ---
    public void bacaLogTransaksi() {
        System.out.println("\n--- Riwayat Log Transaksi Parkir (.txt) ---");
        try {
            File fileLog = new File("log_transaksi_parkir.txt");
            if (!fileLog.exists()) {
                System.out.println("Belum ada file log transaksi yang tercatat.");
                return;
            }

            Scanner mesinBaca = new Scanner(fileLog);
            while (mesinBaca.hasNextLine()) {
                System.out.println(mesinBaca.nextLine());
            }
            mesinBaca.close();
        } catch (FileNotFoundException e) {
            System.out.println("Gagal membuka file log transaksi: " + e.getMessage());
        }
    }
}