package com.ggf.qcpp.e_formpengamatan.phtanah.model;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class SampleModel {
    @SerializedName("no_spk")
    public String no_spk;

    @SerializedName("lokasi")
    public String lokasi;

    @SerializedName("std_musim")
    public String std_musim;

    @SerializedName("plot")
    public int plot;

    @SerializedName("no_sample")
    public int no_sample;

    @SerializedName("wil")
    public String wil;

    @SerializedName("luas_plot")
    public float luas_plot;

    @SerializedName("luas_aktif")
    public String luas_aktif;

    @SerializedName("status_lokasi")
    public String status_lokasi;

    @SerializedName("ex_comodity")
    public String ex_comodity;
    @SerializedName("analisa_a1")
    public float analisa_a1;

    @SerializedName("analisa_a2")
    public float analisa_a2;

    @SerializedName("analisa_a3")
    public float analisa_a3;

    @SerializedName("analisa_a4")
    public float analisa_a4;
    @SerializedName("komposit_a1")
    public float komposit_a1;

    @SerializedName("komposit_a2")
    public float komposit_a2;
    @SerializedName("komposit_a3")
    public float komposit_a3;

    @SerializedName("komposit_a4")
    public float komposit_a4;

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

    public String getLuas_aktif() {
        return luas_aktif;
    }

    public void setLuas_aktif(String luas_aktif) {
        this.luas_aktif = luas_aktif;
    }

    public String getStatus_lokasi() {
        return status_lokasi;
    }

    public void setStatus_lokasi(String status_lokasi) {
        this.status_lokasi = status_lokasi;
    }

    public String getEx_comodity() {
        return ex_comodity;
    }

    public void setEx_comodity(String ex_comodity) {
        this.ex_comodity = ex_comodity;
    }

    public float getAnalisa_a1() {
        return analisa_a1;
    }

    public void setAnalisa_a1(float analisa_a1) {
        this.analisa_a1 = analisa_a1;
    }

    public float getAnalisa_a2() {
        return analisa_a2;
    }

    public void setAnalisa_a2(float analisa_a2) {
        this.analisa_a2 = analisa_a2;
    }

    public float getAnalisa_a3() {
        return analisa_a3;
    }

    public void setAnalisa_a3(float analisa_a3) {
        this.analisa_a3 = analisa_a3;
    }

    public float getAnalisa_a4() {
        return analisa_a4;
    }

    public void setAnalisa_a4(float analisa_a4) {
        this.analisa_a4 = analisa_a4;
    }

    public float getKomposit_a1() {
        return komposit_a1;
    }

    public void setKomposit_a1(float komposit_a1) {
        this.komposit_a1 = komposit_a1;
    }

    public float getKomposit_a3() {
        return komposit_a3;
    }

    public void setKomposit_a3(float komposit_a3) {
        this.komposit_a3 = komposit_a3;
    }

    public float getKomposit_a4() {
        return komposit_a4;
    }

    public void setKomposit_a4(float komposit_a4) {
        this.komposit_a4 = komposit_a4;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public float getKomposit_a2() {
        return komposit_a2;
    }

    public void setKomposit_a2(float komposit_a2) {
        this.komposit_a2 = komposit_a2;
    }
}
