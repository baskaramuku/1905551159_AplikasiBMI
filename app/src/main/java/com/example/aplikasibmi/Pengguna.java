package com.example.aplikasibmi;

public class Pengguna {
    int id, tinggi, berat;
    String nama, jk, hobi, hasil;

    public Pengguna(int id, int tinggi, int berat, String nama, String jk, String hobi, String hasil) {
        this.id = id;
        this.tinggi = tinggi;
        this.berat = berat;
        this.nama = nama;
        this.jk = jk;
        this.hobi = hobi;
        this.hasil = hasil;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTinggi() {
        return tinggi;
    }

    public void setTinggi(int tinggi) {
        this.tinggi = tinggi;
    }

    public int getBerat() {
        return berat;
    }

    public void setBerat(int berat) {
        this.berat = berat;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJk() {
        return jk;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }

    public String getHobi() {
        return hobi;
    }

    public void setHobi(String hobi) {
        this.hobi = hobi;
    }

    public String getHasil() {
        return hasil;
    }

    public void setHasil(String hasil) {
        this.hasil = hasil;
    }
}
