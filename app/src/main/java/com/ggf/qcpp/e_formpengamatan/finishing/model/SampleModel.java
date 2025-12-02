package com.ggf.qcpp.e_formpengamatan.finishing.model;

import com.google.gson.annotations.SerializedName;

public class SampleModel {
    @SerializedName("no_spk")
    public String no_spk;
    @SerializedName("lokasi")
    public String lokasi;

    @SerializedName("no_spk2")
    public String no_spk2;
    @SerializedName("no_line")
    public String no_line;

    @SerializedName("status_pengamatan")
    public String status_pengamatan;


    @SerializedName("std_musim")
    public String std_musim;

    @SerializedName("reworking")
    public String reworking;

    @SerializedName("no_sample")
    public int no_sample;
    @SerializedName("wil")
    public String wil;

    @SerializedName("no_unit_implement")
    public String no_unit_implement;

    @SerializedName("keterangan")
    public String keterangan;

    @SerializedName("ex_comodity")
    public String ex_comodity;

    @SerializedName("plot")
    public int PLOT;

    @SerializedName("luas_plot")
    public float luas_plot;
    @SerializedName("luas_aktif")
    public float luas_aktif;
    @SerializedName("lolos_ayakan")
    public float LOLOS_AYAKAN;

    @SerializedName("tidak_lolos_ayakan")
    public float TIDAK_LOLOS_AYAKAN;

    @SerializedName("kerataan_aplikasi")
    public float APLIKASI_KERATAAN;
    @SerializedName("jenis_implement")
    public String jenis_implement;

    public String getJenis_implement() {
        return jenis_implement;
    }

    public void setJenis_implement(String jenis_implement) {
        this.jenis_implement = jenis_implement;
    }

    public String getNo_spk2() {
        return no_spk2;
    }

    public void setNo_spk2(String no_spk2) {
        this.no_spk2 = no_spk2;
    }

    public String getNo_line() {
        return no_line;
    }

    public void setNo_line(String no_line) {
        this.no_line = no_line;
    }

    public String getEx_comodity() {
        return ex_comodity;
    }

    public void setEx_comodity(String ex_comodity) {
        this.ex_comodity = ex_comodity;
    }

    public float getLuas_aktif() {
        return luas_aktif;
    }

    public void setLuas_aktif(float luas_aktif) {
        this.luas_aktif = luas_aktif;
    }

    public String getReworking() {
        return reworking;
    }

    public void setReworking(String reworking) {
        this.reworking = reworking;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getNo_spk() {
        return no_spk;
    }

    public void setNo_spk(String no_spk) {
        this.no_spk = no_spk;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getStatus_pengamatan() {
        return status_pengamatan;
    }

    public void setStatus_pengamatan(String status_pengamatan) {
        this.status_pengamatan = status_pengamatan;
    }

    public String getStd_musim() {
        return std_musim;
    }

    public void setStd_musim(String std_musim) {
        this.std_musim = std_musim;
    }

    public int getNo_sample() {
        return no_sample;
    }

    public void setNo_sample(int no_sample) {
        this.no_sample = no_sample;
    }

    public String getNo_unit_implement() {
        return no_unit_implement;
    }

    public void setNo_unit_implement(String no_unit_implement) {
        this.no_unit_implement = no_unit_implement;
    }

    public int getPLOT() {
        return PLOT;
    }

    public void setPLOT(int PLOT) {
        this.PLOT = PLOT;
    }

    public float getLuas_plot() {
        return luas_plot;
    }

    public void setLuas_plot(float luas_plot) {
        this.luas_plot = luas_plot;
    }

    public String getWil() {
        return wil;
    }

    public void setWil(String wil) {
        this.wil = wil;
    }

    public float getLOLOS_AYAKAN() {
        return LOLOS_AYAKAN;
    }

    public void setLOLOS_AYAKAN(float LOLOS_AYAKAN) {
        this.LOLOS_AYAKAN = LOLOS_AYAKAN;
    }

    public float getTIDAK_LOLOS_AYAKAN() {
        return TIDAK_LOLOS_AYAKAN;
    }

    public void setTIDAK_LOLOS_AYAKAN(float TIDAK_LOLOS_AYAKAN) {
        this.TIDAK_LOLOS_AYAKAN = TIDAK_LOLOS_AYAKAN;
    }

    public float getAPLIKASI_KERATAAN() {
        return APLIKASI_KERATAAN;
    }

    public void setAPLIKASI_KERATAAN(float APLIKASI_KERATAAN) {
        this.APLIKASI_KERATAAN = APLIKASI_KERATAAN;
    }
}
