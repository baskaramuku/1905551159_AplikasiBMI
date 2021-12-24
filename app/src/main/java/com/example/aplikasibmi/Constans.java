package com.example.aplikasibmi;

public class Constans {
    //COLLUMNS
    static final String ROW_ID = "id";
    static final String NAMA = "nama";
    static final String JK = "jk";
    static final String HOBI = "hobi";
    static final String TINGGI = "tinggi";
    static final String BERAT = "berat";
    static final String HASIL = "hasil";

    //DB PROPERTIES
    static final String DB_NAME = "db_aplikasibmi";
    static final String TB_NAME = "tb_pengguna";
    static final int DB_VERSION = '1';

    //CREATE TABLE
        static final String CREATE_TB = "CREATE TABLE tb_pengguna(id INTEGER PRIMARY KEY AUTOINCREMENT, nama TEXT NOT NULL, jk TEXT NOT NULL, hobi TEXT NOT NULL, tinggi INTEGER, berat INTEGER, hasil TEXT NOT NULL)";
}
