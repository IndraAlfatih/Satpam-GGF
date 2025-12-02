package com.ggf.qcpp.e_formpengamatan.jumlahbaris.model;

import com.google.gson.annotations.SerializedName;

public class SampleModel {

    @SerializedName("no_sample")
    public int no_sample;
    @SerializedName("no_spk")
    public String no_spk;
    @SerializedName("lokasi")
    public String lokasi;
    @SerializedName("plot")
    public int plot;
    @SerializedName("no_spk2")
    public String no_spk2;
    @SerializedName("no_line")
    public String no_line;
    @SerializedName("wil")
    public String wil;
    @SerializedName("status_pengamatan")
    public String status_pengamatan;
    @SerializedName("luas_plot")
    public float luas_plot;


    @SerializedName("ce")
    public float ce;
    @SerializedName("lebar_jalan")
    public float lebar_jalan;
    @SerializedName("manual")
    public float manual;
    @SerializedName("reworking")
    public String reworking;
    @SerializedName("examini")
    public int examini;
    @SerializedName("traktor")
    public int traktor;
    @SerializedName("ditcher")
    public int ditcher;

    @SerializedName("jumlah_baris")
    public int jumlah_baris;
    @SerializedName("jumlah_baris_std")
    public int jumlah_baris_std;
    @SerializedName("penambahan_baris")
    public int penambahan_baris;

    @SerializedName("jumlah_pb")
    public int jumlah_pb;
    @SerializedName("tersier")
    public int tersier;
    @SerializedName("penambahan_baris_sal_tersier")
    public int penambahan_baris_sal_tersier;
    @SerializedName("kancingan")
    public int kancingan;

    @SerializedName("hasil")
    public float hasil;

    @SerializedName("keterangan")
    public String keterangan;

    @SerializedName("update_peta")
    public String update_peta;

    @SerializedName("mandor_bibit")
    public String mandor;

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

    public String getStatus_pengamatan() {
        return status_pengamatan;
    }

    public void setStatus_pengamatan(String status_pengamatan) {
        this.status_pengamatan = status_pengamatan;
    }

    public String getUpdate_peta() {
        return update_peta;
    }

    public void setUpdate_peta(String update_peta) {
        this.update_peta = update_peta;
    }

    public String getMandor() {
        return mandor;
    }

    public void setMandor(String mandor) {
        this.mandor = mandor;
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

    public String getReworking() {
        return reworking;
    }

    public void setReworking(String reworking) {
        this.reworking = reworking;
    }



    public float getLebar_jalan() {
        return lebar_jalan;
    }

    public void setLebar_jalan(float lebar_jalan) {
        this.lebar_jalan = lebar_jalan;
    }

    public float getCe() {
        return ce;
    }

    public void setCe(float ce) {
        this.ce = ce;
    }

    public float getManual() {
        return manual;
    }

    public void setManual(float manual) {
        this.manual = manual;
    }

    public int getExamini() {
        return examini;
    }

    public void setExamini(int examini) {
        this.examini = examini;
    }

    public int getTraktor() {
        return traktor;
    }

    public void setTraktor(int traktor) {
        this.traktor = traktor;
    }

    public int getDitcher() {
        return ditcher;
    }

    public void setDitcher(int ditcher) {
        this.ditcher = ditcher;
    }

    public int getJumlah_baris() {
        return jumlah_baris;
    }

    public void setJumlah_baris(int jumlah_baris) {
        this.jumlah_baris = jumlah_baris;
    }

    public int getJumlah_baris_std() {
        return jumlah_baris_std;
    }

    public void setJumlah_baris_std(int jumlah_baris_std) {
        this.jumlah_baris_std = jumlah_baris_std;
    }

    public int getNo_sample() {
        return no_sample;
    }

    public String getWil() {
        return wil;
    }

    public void setWil(String wil) {
        this.wil = wil;
    }

    public void setNo_sample(int no_sample) {
        this.no_sample = no_sample;
    }

    public int getPenambahan_baris() {
        return penambahan_baris;
    }

    public void setPenambahan_baris(int penambahan_baris) {
        this.penambahan_baris = penambahan_baris;
    }

    public int getJumlah_pb() {
        return jumlah_pb;
    }

    public void setJumlah_pb(int jumlah_pb) {
        this.jumlah_pb = jumlah_pb;
    }

    public int getTersier() {
        return tersier;
    }

    public void setTersier(int tersier) {
        this.tersier = tersier;
    }

    public int getPenambahan_baris_sal_tersier() {
        return penambahan_baris_sal_tersier;
    }

    public void setPenambahan_baris_sal_tersier(int penambahan_baris_sal_tersier) {
        this.penambahan_baris_sal_tersier = penambahan_baris_sal_tersier;
    }

    public int getKancingan() {
        return kancingan;
    }

    public void setKancingan(int kancingan) {
        this.kancingan = kancingan;
    }

    public float getHasil() {
        return hasil;
    }

    public void setHasil(float hasil) {
        this.hasil = hasil;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
