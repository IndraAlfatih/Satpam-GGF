//package com.ggf.qcpp.d_hasilpengamatan.hasilPengamatanOffline.model;
//
//import com.google.gson.annotations.SerializedName;
//import java.util.List;
//
//public class DataModel {
//
//    @SerializedName("DATA")
//    private List<DataItem> data;
//    private String kategori;
//    private String lokasi;
//    private String luas_netto;
//    private String no_line;
//    private String no_spk;
//    @SerializedName("NO_UNIT_IMPLEMENT")
//    private String noUnitImplement;
//    private String pg;
//    private String status_pengamatan;
//    private String username;
//    private String wil;
//
//    // Getters and Setters
//    public List<DataItem> getData() {
//        return data;
//    }
//
//    public void setData(List<DataItem> data) {
//        this.data = data;
//    }
//
//    public String getKategori() {
//        return kategori;
//    }
//
//    public void setKategori(String kategori) {
//        this.kategori = kategori;
//    }
//
//    public String getLokasi() {
//        return lokasi;
//    }
//
//    public void setLokasi(String lokasi) {
//        this.lokasi = lokasi;
//    }
//
//    public String getLuas_netto() {
//        return luas_netto;
//    }
//
//    public void setLuas_netto(String luas_netto) {
//        this.luas_netto = luas_netto;
//    }
//
//    public String getNo_line() {
//        return no_line;
//    }
//
//    public void setNo_line(String no_line) {
//        this.no_line = no_line;
//    }
//
//    public String getNo_spk() {
//        return no_spk;
//    }
//
//    public void setNo_spk(String no_spk) {
//        this.no_spk = no_spk;
//    }
//
//    public String getNoUnitImplement() {
//        return noUnitImplement;
//    }
//
//    public void setNoUnitImplement(String noUnitImplement) {
//        this.noUnitImplement = noUnitImplement;
//    }
//
//    public String getPg() {
//        return pg;
//    }
//
//    public void setPg(String pg) {
//        this.pg = pg;
//    }
//
//    public String getStatus_pengamatan() {
//        return status_pengamatan;
//    }
//
//    public void setStatus_pengamatan(String status_pengamatan) {
//        this.status_pengamatan = status_pengamatan;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getWil() {
//        return wil;
//    }
//
//    public void setWil(String wil) {
//        this.wil = wil;
//    }
//}
//
//class DataItem {
//    private String PLOT;
//    private List<Sample> SAMPLE;
//
//    public String getPLOT() {
//        return PLOT;
//    }
//
//    public void setPLOT(String PLOT) {
//        this.PLOT = PLOT;
//    }
//
//    public List<Sample> getSAMPLE() {
//        return SAMPLE;
//    }
//
//    public void setSAMPLE(List<Sample> SAMPLE) {
//        this.SAMPLE = SAMPLE;
//    }
//}
//
//class Sample {
//    private double aplikasi_rapat;
//    private double bonggol_terpecah;
//    private int plot;
//    private double tanaman_hancur;
//    private String jenis_implement;
//    private String lokasi;
//    private double luas_plot;
//    private int no_sample;
//    private String no_spk;
//    private String no_unit_implement;
//    private String status_pengamatan;
//    private String wil;
//
//    // Getters and Setters
//    public double getAplikasi_rapat() {
//        return aplikasi_rapat;
//    }
//
//    public void setAplikasi_rapat(double aplikasi_rapat) {
//        this.aplikasi_rapat = aplikasi_rapat;
//    }
//
//    public double getBonggol_terpecah() {
//        return bonggol_terpecah;
//    }
//
//    public void setBonggol_terpecah(double bonggol_terpecah) {
//        this.bonggol_terpecah = bonggol_terpecah;
//    }
//
//    public int getPlot() {
//        return plot;
//    }
//
//    public void setPlot(int plot) {
//        this.plot = plot;
//    }
//
//    public double getTanaman_hancur() {
//        return tanaman_hancur;
//    }
//
//    public void setTanaman_hancur(double tanaman_hancur) {
//        this.tanaman_hancur = tanaman_hancur;
//    }
//
//    public String getJenis_implement() {
//        return jenis_implement;
//    }
//
//    public void setJenis_implement(String jenis_implement) {
//        this.jenis_implement = jenis_implement;
//    }
//
//    public String getLokasi() {
//        return lokasi;
//    }
//
//    public void setLokasi(String lokasi) {
//        this.lokasi = lokasi;
//    }
//
//    public double getLuas_plot() {
//        return luas_plot;
//    }
//
//    public void setLuas_plot(double luas_plot) {
//        this.luas_plot = luas_plot;
//    }
//
//    public int getNo_sample() {
//        return no_sample;
//    }
//
//    public void setNo_sample(int no_sample) {
//        this.no_sample = no_sample;
//    }
//
//    public String getNo_spk() {
//        return no_spk;
//    }
//
//    public void setNo_spk(String no_spk) {
//        this.no_spk = no_spk;
//    }
//
//    public String getNo_unit_implement() {
//        return no_unit_implement;
//    }
//
//    public void setNo_unit_implement(String no_unit_implement) {
//        this.no_unit_implement = no_unit_implement;
//    }
//
//    public String getStatus_pengamatan() {
//        return status_pengamatan;
//    }
//
//    public void setStatus_pengamatan(String status_pengamatan) {
//        this.status_pengamatan = status_pengamatan;
//    }
//
//    public String getWil() {
//        return wil;
//    }
//
//    public void setWil(String wil) {
//        this.wil = wil;
//    }
//}
