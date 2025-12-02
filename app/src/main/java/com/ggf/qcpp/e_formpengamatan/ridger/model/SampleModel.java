package com.ggf.qcpp.e_formpengamatan.ridger.model;

import com.google.gson.annotations.SerializedName;

public class SampleModel {
    @SerializedName("no_spk")
    public String no_spk;

    @SerializedName("no_sample")
    public int no_sample;
    @SerializedName("plot")
    public int PLOT;
    @SerializedName("luas_plot")
    public float luas_plot;
    @SerializedName("lokasi")
    public String lokasi;
    @SerializedName("std_musim")
    public String std_musim;
    @SerializedName("no_unit_implement")
    public String no_unit_implement;
    @SerializedName("jarak_antar_poros_gulud")
    public float jarak_antar_poros_gulud;
    @SerializedName("kedalaman_kuku_ridge")
    public float kedalaman_kuku_ridger;
    @SerializedName("wil")
    public String wil;

    @SerializedName("keterangan")
    public String keterangan;

    public String getWil() {
        return wil;
    }

    public void setWil(String wil) {
        this.wil = wil;
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

    public int getNo_sample() {
        return no_sample;
    }

    public void setNo_sample(int no_sample) {
        this.no_sample = no_sample;
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

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getStd_musim() {
        return std_musim;
    }

    public void setStd_musim(String std_musim) {
        this.std_musim = std_musim;
    }

    public String getNo_unit_implement() {
        return no_unit_implement;
    }

    public void setNo_unit_implement(String no_unit_implement) {
        this.no_unit_implement = no_unit_implement;
    }

    public float getJarak_antar_poros_gulud() {
        return jarak_antar_poros_gulud;
    }

    public void setJarak_antar_poros_gulud(float jarak_antar_poros_gulud) {
        this.jarak_antar_poros_gulud = jarak_antar_poros_gulud;
    }

    public float getKedalaman_kuku_ridger() {
        return kedalaman_kuku_ridger;
    }

    public void setKedalaman_kuku_ridger(float kedalaman_kuku_ridger) {
        this.kedalaman_kuku_ridger = kedalaman_kuku_ridger;
    }
}
