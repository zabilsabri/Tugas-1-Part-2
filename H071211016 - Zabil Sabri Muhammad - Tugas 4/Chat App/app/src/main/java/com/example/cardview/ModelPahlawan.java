package com.example.cardview;

public class ModelPahlawan {
    private String nama;
    private String tentang;
    private String foto;
    private String waktu;
    private String nomor;
    private String status;
    private String tgl_status;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTentang() {
        return tentang;
    }

    public void setTentang(String tentang) {
        this.tentang = tentang;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTgl_status(String tgl_status) {
        this.tgl_status = tgl_status;
    }

    public String getTgl_status() {
        return tgl_status;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getWaktu() {
        return waktu;
    }
}
