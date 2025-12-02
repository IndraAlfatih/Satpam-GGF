package com.ggf.qcpp.e_formpengamatan.sesetbonggol.model;

import com.google.gson.annotations.SerializedName;

public class SampleModel {

    @SerializedName("no_spk")
    public String no_spk;
    @SerializedName("lokasi")
    public String lokasi;
    @SerializedName("netto")
    public String netto;
    @SerializedName("update_peta")
    public String update_peta;
    @SerializedName("tanggal_panen_rampet")
    public String tanggal_panen_rampet;
    @SerializedName("status_rc")
    public String status_rc;
    @SerializedName("wil")
    public String wil;
    @SerializedName("plot")
    public int plot;
    @SerializedName("luas_plot")
    public float luas_plot;
    @SerializedName("no_sample")
    public int no_sample;


    @SerializedName("ketinggian_sampah")
    public float ketinggian_sampah;
    @SerializedName("jumlah_sample")
    public float jumlah_sample;
    @SerializedName("jumlah_sample_masuk_standar_kebersihan_kupasan")
    public float jumlah_sample_masuk_standar_kebersihan_kupasan;
    @SerializedName("jumlah_sample_masuk_standar_potongan_bonggol")
    public float jumlah_sample_masuk_standar_potongan_bonggol;
    @SerializedName("rerata_panjang_bonggol")
    public float rerata_panjang_bonggol;
    @SerializedName("jumlah_sample_masuk_standar_kondisi_bonggol")
    public float jumlah_sample_masuk_standar_kondisi_bonggol;
    @SerializedName("jumlah_sample_masuk_standar_kondisi_bin")
    public float jumlah_sample_masuk_standar_kondisi_bin;

    @SerializedName("kondisimuatan")
    public String kondisimuatan;

    @SerializedName("keterangan")
    public String keterangan;

    //


    public String getKondisimuatan() {
        return kondisimuatan;
    }

    public void setKondisimuatan(String kondisimuatan) {
        this.kondisimuatan = kondisimuatan;
    }

    public String getNo_spk() {
        return no_spk;
    }

    public void setNo_spk(String no_spk) {
        this.no_spk = no_spk;
    }


    public int getNo_sample() {
        return no_sample;
    }

    public void setNo_sample(int no_sample) {
        this.no_sample = no_sample;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getNetto() {
        return netto;
    }

    public void setNetto(String netto) {
        this.netto = netto;
    }

    public String getUpdate_peta() {
        return update_peta;
    }

    public void setUpdate_peta(String update_peta) {
        this.update_peta = update_peta;
    }

    public String getTanggal_panen_rampet() {
        return tanggal_panen_rampet;
    }

    public void setTanggal_panen_rampet(String tanggal_panen_rampet) {
        this.tanggal_panen_rampet = tanggal_panen_rampet;
    }

    public String getStatus_rc() {
        return status_rc;
    }

    public void setStatus_rc(String status_rc) {
        this.status_rc = status_rc;
    }

    public String getWil() {
        return wil;
    }

    public void setWil(String wil) {
        this.wil = wil;
    }

    public int getPlot() {
        return plot;
    }

    public void setPlot(int plot) {
        this.plot = plot;
    }

    public float getLuas_plot() {
        return luas_plot;
    }

    public void setLuas_plot(float luas_plot) {
        this.luas_plot = luas_plot;
    }


    public float getKetinggian_sampah() {
        return ketinggian_sampah;
    }

    public void setKetinggian_sampah(float ketinggian_sampah) {
        this.ketinggian_sampah = ketinggian_sampah;
    }

    public float getJumlah_sample() {
        return jumlah_sample;
    }

    public void setJumlah_sample(float jumlah_sample) {
        this.jumlah_sample = jumlah_sample;
    }

    public float getJumlah_sample_masuk_standar_kebersihan_kupasan() {
        return jumlah_sample_masuk_standar_kebersihan_kupasan;
    }

    public void setJumlah_sample_masuk_standar_kebersihan_kupasan(float jumlah_sample_masuk_standar_kebersihan_kupasan) {
        this.jumlah_sample_masuk_standar_kebersihan_kupasan = jumlah_sample_masuk_standar_kebersihan_kupasan;
    }

    public float getJumlah_sample_masuk_standar_potongan_bonggol() {
        return jumlah_sample_masuk_standar_potongan_bonggol;
    }

    public void setJumlah_sample_masuk_standar_potongan_bonggol(float jumlah_sample_masuk_standar_potongan_bonggol) {
        this.jumlah_sample_masuk_standar_potongan_bonggol = jumlah_sample_masuk_standar_potongan_bonggol;
    }

    public float getRerata_panjang_bonggol() {
        return rerata_panjang_bonggol;
    }

    public void setRerata_panjang_bonggol(float rerata_panjang_bonggol) {
        this.rerata_panjang_bonggol = rerata_panjang_bonggol;
    }

    public float getJumlah_sample_masuk_standar_kondisi_bonggol() {
        return jumlah_sample_masuk_standar_kondisi_bonggol;
    }

    public void setJumlah_sample_masuk_standar_kondisi_bonggol(float jumlah_sample_masuk_standar_kondisi_bonggol) {
        this.jumlah_sample_masuk_standar_kondisi_bonggol = jumlah_sample_masuk_standar_kondisi_bonggol;
    }

    public float getJumlah_sample_masuk_standar_kondisi_bin() {
        return jumlah_sample_masuk_standar_kondisi_bin;
    }

    public void setJumlah_sample_masuk_standar_kondisi_bin(float jumlah_sample_masuk_standar_kondisi_bin) {
        this.jumlah_sample_masuk_standar_kondisi_bin = jumlah_sample_masuk_standar_kondisi_bin;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
