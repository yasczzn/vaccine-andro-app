package com.example.vaksinapplication.model;

import com.google.firebase.database.IgnoreExtraProperties;
import java.io.Serializable;
@IgnoreExtraProperties
public class Pasien implements Serializable{
    private String Nama;
    private String Nik;
    private String JenisKelamin;
    private String No;
    private String Email;
    private String Alamat;
    private String key;

    public Pasien(){
    }

    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getNama() {
        return Nama;
    }
    public void setNama(String Nama) {
        this.Nama = Nama;
    }
    public String getNik() {
        return Nik;
    }
    public void setNik(String Nik) {
        this.Nik = Nik;
    }
    public String getJenisKelamin() {
        return JenisKelamin;
    }
    public void setJenisKelamin(String JenisKelamin) {
        this.JenisKelamin = JenisKelamin;
    }
    public String getNo() {
        return No;
    }
    public void setNo(String No) {
        this.No = No;
    }
    public String getEmail() {
        return Email;
    }
    public void setEmail(String Email) {
        this.Email = Email;
    }
    public String getAlamat() {
        return Alamat;
    }
    public void setAlamat(String Alamat) {
        this.Alamat = Alamat;
    }

    @Override
    public String toString() {
        return " "+Nama+"\n" +
                " "+Nik+"\n" +
                " "+JenisKelamin+"\n" +
                " "+No+"\n" +
                " "+Email+"\n" +
                " "+Alamat;
    }
    public Pasien(String nm, String nk, String gend, String nh, String em, String alm ) {
        Nama = nm;
        Nik = nk;
        JenisKelamin = gend;
        No = nh;
        Email = em;
        Alamat = alm;
    }
}

