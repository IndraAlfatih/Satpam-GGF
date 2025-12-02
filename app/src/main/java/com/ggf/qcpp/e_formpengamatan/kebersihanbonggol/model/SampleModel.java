package com.ggf.qcpp.e_formpengamatan.kebersihanbonggol.model;

import com.google.gson.annotations.SerializedName;

public class SampleModel {

    @SerializedName("no_spk")
    public String no_spk;

    @SerializedName("lokasi")
    public String lokasi;

    @SerializedName("std_musim")
    public String std_musim;

    @SerializedName("plot")
    public int plot;

    @SerializedName("wil")
    public String wil;

    @SerializedName("luas_plot")
    public float luas_plot;


    @SerializedName("no_sample")
    public int no_sample;
    @SerializedName("estimasi")
    public float ESTIMASI;

    @SerializedName("bonggol_segar_kurang_dari")
    public float BONGGOL_SEGAR_KURANG_DARI;

    @SerializedName("bonggol_segar_lebih_dari")
    public float BONGGOL_SEGAR_LEBIH_DARI;

    @SerializedName("keterangan")
    public String keterangan;


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

    public String getStd_musim() {
        return std_musim;
    }

    public void setStd_musim(String std_musim) {
        this.std_musim = std_musim;
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

    public int getNo_sample() {
        return no_sample;
    }

    public void setNo_sample(int no_sample) {
        this.no_sample = no_sample;
    }

    public float getESTIMASI() {
        return ESTIMASI;
    }

    public void setESTIMASI(float ESTIMASI) {
        this.ESTIMASI = ESTIMASI;
    }

    public float getBONGGOL_SEGAR_KURANG_DARI() {
        return BONGGOL_SEGAR_KURANG_DARI;
    }

    public void setBONGGOL_SEGAR_KURANG_DARI(float BONGGOL_SEGAR_KURANG_DARI) {
        this.BONGGOL_SEGAR_KURANG_DARI = BONGGOL_SEGAR_KURANG_DARI;
    }

    public float getBONGGOL_SEGAR_LEBIH_DARI() {
        return BONGGOL_SEGAR_LEBIH_DARI;
    }

    public void setBONGGOL_SEGAR_LEBIH_DARI(float BONGGOL_SEGAR_LEBIH_DARI) {
        this.BONGGOL_SEGAR_LEBIH_DARI = BONGGOL_SEGAR_LEBIH_DARI;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getWil() {
        return wil;
    }

    public void setWil(String wil) {
        this.wil = wil;
    }
}
