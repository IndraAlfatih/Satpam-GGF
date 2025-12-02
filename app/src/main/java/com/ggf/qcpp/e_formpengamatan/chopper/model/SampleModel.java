package com.ggf.qcpp.e_formpengamatan.chopper.model;

import com.google.gson.annotations.SerializedName;

public class SampleModel {

    // String Fields
    @SerializedName("wil")
    public String wilayah;

    @SerializedName("no_spk")
    public String no_spk;
    @SerializedName("no_spk2")
    public String no_spk2;
    @SerializedName("no_line")
    public String no_line;
    @SerializedName("lokasi")
    public String lokasi;

    @SerializedName("ex_comodity")
    public String ex_comodity;

    @SerializedName("luas_plot")
    public Float luas_plot;

    @SerializedName("luas_aktif")
    public Float luas_aktif;

    @SerializedName("status_pengamatan")
    public String status_pengamatan;

    @SerializedName("reworking")
    public String reworking;

    @SerializedName("no_unit_implement")
    public String no_unit_implement;

    @SerializedName("jenis_implement")
    public String jenis_implement;

    @SerializedName("eks")
    public String eks;

    @SerializedName("keterangan")
    public String KETERANGAN;

    @SerializedName("IMAGE")
    public String IMAGE;

    // int Fields
    @SerializedName("no_sample")
    public int no_sample;

    @SerializedName("plot")
    public int PLOT;

    // float Fields

    @SerializedName("tanaman_hancur")
    public float TANAMAN_HANCUR;

    @SerializedName("bonggol_terpecah")
    public float BONGGOL_TERPECAH;

    @SerializedName("aplikasi_rapat")
    public float APLIKASI_RAPAT;

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

    public Float getLuas_aktif() {
        return luas_aktif;
    }

    public void setLuas_aktif(Float luas_aktif) {
        this.luas_aktif = luas_aktif;
    }

    public String getEx_comodity() {
        return ex_comodity;
    }

    public void setEx_comodity(String ex_comodity) {
        this.ex_comodity = ex_comodity;
    }

    public void setLuas_plot(Float luas_plot) {
        this.luas_plot = luas_plot;
    }

    public String getReworking() {
        return reworking;
    }

    public void setReworking(String reworking) {
        this.reworking = reworking;
    }

    // Getters and Setters
    public String getWilayah() {
        return wilayah;
    }

    public void setWilayah(String wilayah) {
        this.wilayah = wilayah;
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

    public String getNo_unit_implement() {
        return no_unit_implement;
    }

    public void setNo_unit_implement(String no_unit_implement) {
        this.no_unit_implement = no_unit_implement;
    }

    public String getJenis_implement() {
        return jenis_implement;
    }

    public void setJenis_implement(String jenis_implement) {
        this.jenis_implement = jenis_implement;
    }

    public String getEks() {
        return eks;
    }

    public void setEks(String eks) {
        this.eks = eks;
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

    public float getTANAMAN_HANCUR() {
        return TANAMAN_HANCUR;
    }

    public void setTANAMAN_HANCUR(float TANAMAN_HANCUR) {
        this.TANAMAN_HANCUR = TANAMAN_HANCUR;
    }

    public float getBONGGOL_TERPECAH() {
        return BONGGOL_TERPECAH;
    }

    public void setBONGGOL_TERPECAH(float BONGGOL_TERPECAH) {
        this.BONGGOL_TERPECAH = BONGGOL_TERPECAH;
    }

    public float getAPLIKASI_RAPAT() {
        return APLIKASI_RAPAT;
    }

    public void setAPLIKASI_RAPAT(float APLIKASI_RAPAT) {
        this.APLIKASI_RAPAT = APLIKASI_RAPAT;
    }

    public String getKETERANGAN() {
        return KETERANGAN;
    }

    public void setKETERANGAN(String KETERANGAN) {
        this.KETERANGAN = KETERANGAN;
    }

    public String getIMAGE() {
        return IMAGE;
    }

    public void setIMAGE(String IMAGE) {
        this.IMAGE = IMAGE;
    }
}
