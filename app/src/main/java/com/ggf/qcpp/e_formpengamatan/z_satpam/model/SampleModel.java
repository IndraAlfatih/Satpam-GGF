package com.ggf.qcpp.e_formpengamatan.z_satpam.model;

import com.google.gson.annotations.SerializedName;

public class SampleModel {

    @SerializedName("no_spk")
    public String no_spk;
    @SerializedName("lokasi")
    public String lokasi;

    @SerializedName("status_pengamatan")
    public String status_pengamatan;
    @SerializedName("no_spk2")
    public String no_spk2;
    @SerializedName("no_line")
    public String no_line;

    @SerializedName("jenis_implement")
    public String jenis_implement;
    @SerializedName("comodity")
    public String comodity_bajak;

    @SerializedName("aplikasi_pinggiran")
    public String aplikasi_pinggiran;

    @SerializedName("kerataan_aplikasi")
    public String kerataan_aplikasi;

    @SerializedName("keterangan")
    public String keterangan;


    @SerializedName("std_musim")
    public String std_musim;

    @SerializedName("reworking")
    public String reworking;
    @SerializedName("luas_aktif")
    public Float luas_aktif;


    @SerializedName("jenis_bajak")
    public String std_jenisbajak;


    @SerializedName("no_sample")
    public int no_sample;

    @SerializedName("plot")
    public int PLOT;

    @SerializedName("wil")
    public String wil;

    @SerializedName("luas_plot")
    public Float luas_plot;

    @SerializedName("kedalaman")
    public Float KEDALAMAN;

    @SerializedName("dead_furrow")
    public String DEAD_FURROW;

    @SerializedName("lolos_ayakan")
    public String lolos_ayakan;

    @SerializedName("tidak_lolos_ayakan")
    public String tidak_lolos_ayakan;

    public String getLolos_ayakan() {
        return lolos_ayakan;
    }

    public void setLolos_ayakan(String lolos_ayakan) {
        this.lolos_ayakan = lolos_ayakan;
    }

    public String getTidak_lolos_ayakan() {
        return tidak_lolos_ayakan;
    }

    public void setTidak_lolos_ayakan(String tidak_lolos_ayakan) {
        this.tidak_lolos_ayakan = tidak_lolos_ayakan;
    }

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

    public Float getLuas_aktif() {
        return luas_aktif;
    }

    public void setLuas_aktif(Float luas_aktif) {
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

    public String getAplikasi_pinggiran() {
        return aplikasi_pinggiran;
    }

    public void setAplikasi_pinggiran(String aplikasi_pinggiran) {
        this.aplikasi_pinggiran = aplikasi_pinggiran;
    }

    public String getWil() {
        return wil;
    }

    public void setWil(String wil) {
        this.wil = wil;
    }

    public String getComodity_bajak() {
        return comodity_bajak;
    }

    public void setComodity_bajak(String comodity_bajak) {
        this.comodity_bajak = comodity_bajak;
    }

    public String getStd_musim() {
        return std_musim;
    }

    public void setStd_musim(String std_musim) {
        this.std_musim = std_musim;
    }

    public String getStd_jenisbajak() {
        return std_jenisbajak;
    }

    public void setStd_jenisbajak(String std_jenisbajak) {
        this.std_jenisbajak = std_jenisbajak;
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

    public Float getLuas_plot() {
        return luas_plot;
    }

    public void setLuas_plot(Float luas_plot) {
        this.luas_plot = luas_plot;
    }

    public Float getKEDALAMAN() {
        return KEDALAMAN;
    }

    public void setKEDALAMAN(Float KEDALAMAN) {
        this.KEDALAMAN = KEDALAMAN;
    }

    public String getDEAD_FURROW() {
        return DEAD_FURROW;
    }

    public void setDEAD_FURROW(String DEAD_FURROW) {
        this.DEAD_FURROW = DEAD_FURROW;
    }

    //    public String getAplikasi_rapat() {
//        return aplikasi_pinggiran;
//    }
//
//    public void setAplikasi_rapat(String aplikasi_pinggiran) {
//        this.aplikasi_pinggiran = aplikasi_pinggiran;
//    }

    public String getKerataan_aplikasi() {
        return kerataan_aplikasi;
    }

    public void setKerataan_aplikasi(String kerataan_aplikasi) {
        this.kerataan_aplikasi = kerataan_aplikasi;
    }

}
