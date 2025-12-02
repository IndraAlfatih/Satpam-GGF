package com.ggf.qcpp.e_formpengamatan.jalansaluran.model;

import com.google.gson.annotations.SerializedName;

public class SampleModel {

    @SerializedName("no_spk")
    public String no_spk;


    @SerializedName("no_sample")
    public int no_sample;

    @SerializedName("lokasi")
    public String lokasi;

    @SerializedName("no_line")
    public int no_line;

    @SerializedName("luas_plot")
    public float luas_plot;

    @SerializedName("plot")
    public int PLOT;

    @SerializedName("jalan_block")
    public float jalan_block;
    @SerializedName("jalan_plot")
    public float jalan_plot;
    @SerializedName("jalan_seksi")
    public float jalan_seksi;
    @SerializedName("jalan_perimeter")
    public float jalan_perimeter;

    @SerializedName("saluran_sekunder")
    public float saluran_sekunder;
    @SerializedName("saluran_tersier")
    public float saluran_tersier;
    @SerializedName("jalan_saluran_tidak_ada_ripper")
    public float jalan_saluran_tidak_ada_ripper;
    @SerializedName("saluran_tersier_examini")
    public float saluran_tersier_examini;
    @SerializedName("saluran_tersier_traktor")
    public float saluran_tersier_traktor;
    @SerializedName("jumlah_saluran_tersier")
    public int jumlah_saluran_tersier;

    @SerializedName("lebar_tersier_dinding_atas")
    public float lebar_tersier_dinding_atas;
    @SerializedName("lebar_tersier_erosi")
    public float lebar_tersier_erosi;
    @SerializedName("lebar_sekunder_dinding_atas")
    public float lebar_sekunder_dinding_atas;
    @SerializedName("lebar_sekunder_erosi")
    public float lebar_sekunder_erosi;

    @SerializedName("keterangan")
    public String keterangan;

    public int getNo_sample() {
        return no_sample;
    }

    public void setNo_sample(int no_sample) {
        this.no_sample = no_sample;
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

    public int getNo_line() {
        return no_line;
    }

    public void setNo_line(int no_line) {
        this.no_line = no_line;
    }

    public float getLuas_plot() {
        return luas_plot;
    }

    public void setLuas_plot(float luas_plot) {
        this.luas_plot = luas_plot;
    }

    public int getPLOT() {
        return PLOT;
    }

    public void setPLOT(int PLOT) {
        this.PLOT = PLOT;
    }

    public float getJalan_block() {
        return jalan_block;
    }

    public void setJalan_block(float jalan_block) {
        this.jalan_block = jalan_block;
    }

    public float getJalan_plot() {
        return jalan_plot;
    }

    public void setJalan_plot(float jalan_plot) {
        this.jalan_plot = jalan_plot;
    }

    public float getJalan_seksi() {
        return jalan_seksi;
    }

    public void setJalan_seksi(float jalan_seksi) {
        this.jalan_seksi = jalan_seksi;
    }

    public float getJalan_perimeter() {
        return jalan_perimeter;
    }

    public void setJalan_perimeter(float jalan_perimeter) {
        this.jalan_perimeter = jalan_perimeter;
    }

    public float getSaluran_sekunder() {
        return saluran_sekunder;
    }

    public void setSaluran_sekunder(float saluran_sekunder) {
        this.saluran_sekunder = saluran_sekunder;
    }

    public float getSaluran_tersier() {
        return saluran_tersier;
    }

    public void setSaluran_tersier(float saluran_tersier) {
        this.saluran_tersier = saluran_tersier;
    }

    public float getJalan_saluran_tidak_ada_ripper() {
        return jalan_saluran_tidak_ada_ripper;
    }

    public void setJalan_saluran_tidak_ada_ripper(float jalan_saluran_tidak_ada_ripper) {
        this.jalan_saluran_tidak_ada_ripper = jalan_saluran_tidak_ada_ripper;
    }

    public float getSaluran_tersier_examini() {
        return saluran_tersier_examini;
    }

    public void setSaluran_tersier_examini(float saluran_tersier_examini) {
        this.saluran_tersier_examini = saluran_tersier_examini;
    }

    public float getSaluran_tersier_traktor() {
        return saluran_tersier_traktor;
    }

    public void setSaluran_tersier_traktor(float saluran_tersier_traktor) {
        this.saluran_tersier_traktor = saluran_tersier_traktor;
    }

    public int getJumlah_saluran_tersier() {
        return jumlah_saluran_tersier;
    }

    public void setJumlah_saluran_tersier(int jumlah_saluran_tersier) {
        this.jumlah_saluran_tersier = jumlah_saluran_tersier;
    }

    public float getLebar_tersier_dinding_atas() {
        return lebar_tersier_dinding_atas;
    }

    public void setLebar_tersier_dinding_atas(float lebar_tersier_dinding_atas) {
        this.lebar_tersier_dinding_atas = lebar_tersier_dinding_atas;
    }

    public float getLebar_tersier_erosi() {
        return lebar_tersier_erosi;
    }

    public void setLebar_tersier_erosi(float lebar_tersier_erosi) {
        this.lebar_tersier_erosi = lebar_tersier_erosi;
    }

    public float getLebar_sekunder_dinding_atas() {
        return lebar_sekunder_dinding_atas;
    }

    public void setLebar_sekunder_dinding_atas(float lebar_sekunder_dinding_atas) {
        this.lebar_sekunder_dinding_atas = lebar_sekunder_dinding_atas;
    }

    public float getLebar_sekunder_erosi() {
        return lebar_sekunder_erosi;
    }

    public void setLebar_sekunder_erosi(float lebar_sekunder_erosi) {
        this.lebar_sekunder_erosi = lebar_sekunder_erosi;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
