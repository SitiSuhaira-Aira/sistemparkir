package com.mycompany.sistemparkir;

import java.util.ArrayList;
import java.util.HashMap;

public class AreaParkir {
    private String namaArea;
    
    // 1. KOMPOSISI: Objek diciptakan secara internal (hidup-mati bergantung pada AreaParkir)
    private LokasiGate gateMasuk;
    
    // 2. AGREGASI: Koleksi data dikelola di sini, objek luar dimasukkan ke dalam list/map
    private ArrayList<KendaraanParkir> daftarKendaraan;
    private HashMap<String, PemilikKendaraan> dataPemilik;

    public AreaParkir(String namaArea, String namaGate, String zona) {
        this.namaArea = namaArea;
        this.gateMasuk = new LokasiGate(namaGate, zona); // Komposisi
        this.daftarKendaraan = new ArrayList<>();
        this.dataPemilik = new HashMap<>();
    }

    public void bukaAreaParkir() {
        System.out.println("\n*** Menginisialisasi Sistem Parkir: " + this.namaArea + " ***");
        gateMasuk.infoGate();
    }

    // Aggregation: Menambah data kendaraan
    public void tambahKendaraan(KendaraanParkir k) {
        daftarKendaraan.add(k);
        System.out.println("Sukses! Kendaraan terdaftar di sistem. (Total: " + daftarKendaraan.size() + ")");
    }

    // Aggregation: Menambah data pemilik/member parkir
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
}