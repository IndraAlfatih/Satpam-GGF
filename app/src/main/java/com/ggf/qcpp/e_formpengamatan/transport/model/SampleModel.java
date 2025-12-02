package com.ggf.qcpp.e_formpengamatan.transport.model;

import com.google.gson.annotations.SerializedName;

public class SampleModel {

    @SerializedName("no_spk")
    public String no_spk;

    @SerializedName("jenis_bibit")
    public String jenis_bibit;

    @SerializedName("wil")
    public String wil;

    @SerializedName("lokasi")
    public String lokasi;

    @SerializedName("plot")
    public int plot;

    @SerializedName("no_sample")
    public int no_sample;

    @SerializedName("luas_plot")
    public float luas_plot;

    @SerializedName("jumlah_tumpuk")
    public int jumlah_tumpuk;

    @SerializedName("rerata_tumpuk")
    public float rerata_tumpuk;

    @SerializedName("gulud")
    public float gulud;

    @SerializedName("dijalan")
    public float dijalan;
    @SerializedName("terlindas")
    public float terlindas;


    @SerializedName("keterangan")
    public String keterangan;

    public int getNo_sample() {
        return no_sample;
    }

    public void setNo_sample(int no_sample) {
        this.no_sample = no_sample;
    }

    public String getWil() {
        return wil;
    }

    public void setWil(String wil) {
        this.wil = wil;
    }

    public String getNo_spk() {
        return no_spk;
    }

    public void setNo_spk(String no_spk) {
        this.no_spk = no_spk;
    }

    public String getJenis_bibit() {
        return jenis_bibit;
    }

    public void setJenis_bibit(String jenis_bibit) {
        this.jenis_bibit = jenis_bibit;
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

    public int getJumlah_tumpuk() {
        return jumlah_tumpuk;
    }

    public void setJumlah_tumpuk(int jumlah_tumpuk) {
        this.jumlah_tumpuk = jumlah_tumpuk;
    }

    public float getRerata_tumpuk() {
        return rerata_tumpuk;
    }

    public void setRerata_tumpuk(float rerata_tumpuk) {
        this.rerata_tumpuk = rerata_tumpuk;
    }

    public float getGulud() {
        return gulud;
    }

    public void setGulud(float gulud) {
        this.gulud = gulud;
    }

    public float getDijalan() {
        return dijalan;
    }

    public void setDijalan(float dijalan) {
        this.dijalan = dijalan;
    }

    public float getTerlindas() {
        return terlindas;
    }

    public void setTerlindas(float terlindas) {
        this.terlindas = terlindas;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
