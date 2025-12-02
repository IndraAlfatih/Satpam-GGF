package com.ggf.qcpp.e_formpengamatan.pooldipping.model;

import android.widget.AutoCompleteTextView;

import com.ggf.qcpp.R;
import com.google.gson.annotations.SerializedName;

import butterknife.BindView;

public class SampleModel {

    @SerializedName("no_spk")
    public String no_spk;

    @SerializedName("lokasi")
    public String lokasi;

    @SerializedName("mandor_bibit")
    public String mandor_bibit;

    @SerializedName("reworking")
    public String reworking;
    @SerializedName("plot")
    public int plot;
    @SerializedName("no_spk2")
    public String no_spk2;
    @SerializedName("no_line")
    public String no_line;

    @SerializedName("nomor_bibit")
    public String nomor_bibit;

    @SerializedName("wil")
    public String wil;

    @SerializedName("kelas_bibit")
    public String kelas_bibit;

    @SerializedName("jenis_bibit")
    public String jenis_bibit;

    @SerializedName("tanggal_ditemukan_bibit_campur")
    public String tanggal_ditemukan_bibit_campur;
    @SerializedName("no_kendaraan")
    public String no_kendaraan;

    @SerializedName("jenis_unit")
    public String jenis_unit;


    @SerializedName("asal_do")
    public String asal_do;

    @SerializedName("tujuan_do")
    public String tujuan_do;

    @SerializedName("jumlah_sampel")
    public float jumlah_sampel;

    @SerializedName("bibit_normal")
    public float bibit_normal;

    @SerializedName("bibit_afkir")
    public float bibit_afkir;

    @SerializedName("bibit_over_plus")
    public float bibit_over_plus;

    @SerializedName("bibit_over")
    public float bibit_over;

    @SerializedName("bibit_1")
    public float bibit_1;

    @SerializedName("bibit_2")
    public float bibit_2;

    @SerializedName("bibit_3")
    public float bibit_3;

    @SerializedName("bibit_4")
    public float bibit_4;

    @SerializedName("bibit_5")
    public float bibit_5;

    @SerializedName("bibit_6")
    public float bibit_6;

    @SerializedName("bibit_7")
    public float bibit_7;


    @SerializedName("no_sample")
    public int no_sample;


    @SerializedName("hasil")
    public float hasil;
    @SerializedName("informasi_bibit_terdipping")
    public int informasi_bibit_terdipping;

    @SerializedName("keterangan")
    public String keterangan;

    public String getTanggal_ditemukan_bibit_campur() {
        return tanggal_ditemukan_bibit_campur;
    }

    public void setTanggal_ditemukan_bibit_campur(String tanggal_ditemukan_bibit_campur) {
        this.tanggal_ditemukan_bibit_campur = tanggal_ditemukan_bibit_campur;
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

    public String getNomor_bibit() {
        return nomor_bibit;
    }

    public void setNomor_bibit(String nomor_bibit) {
        this.nomor_bibit = nomor_bibit;
    }

    public String getReworking() {
        return reworking;
    }

    public void setReworking(String reworking) {
        this.reworking = reworking;
    }

    public int getNo_sample() {
        return no_sample;
    }

    public void setNo_sample(int no_sample) {
        this.no_sample = no_sample;
    }

    public int getPlot() {
        return plot;
    }

    public void setPlot(int plot) {
        this.plot = plot;
    }

    public String getWil() {
        return wil;
    }

    public void setWil(String wil) {
        this.wil = wil;
    }

    public float getJumlah_sampel() {
        return jumlah_sampel;
    }

    public void setJumlah_sampel(float jumlah_sampel) {
        this.jumlah_sampel = jumlah_sampel;
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


    public String getMandor_bibit() {
        return mandor_bibit;
    }

    public void setMandor_bibit(String mandor_bibit) {
        this.mandor_bibit = mandor_bibit;
    }

    public String getNo_kendaraan() {
        return no_kendaraan;
    }

    public void setNo_kendaraan(String no_kendaraan) {
        this.no_kendaraan = no_kendaraan;
    }

    public String getJenis_unit() {
        return jenis_unit;
    }

    public void setJenis_unit(String jenis_unit) {
        this.jenis_unit = jenis_unit;
    }

    public String getAsal_do() {
        return asal_do;
    }

    public void setAsal_do(String asal_do) {
        this.asal_do = asal_do;
    }

    public String getTujuan_do() {
        return tujuan_do;
    }

    public void setTujuan_do(String tujuan_do) {
        this.tujuan_do = tujuan_do;
    }

    public String getKelas_bibit() {
        return kelas_bibit;
    }

    public void setKelas_bibit(String kelas_bibit) {
        this.kelas_bibit = kelas_bibit;
    }

    public String getJenis_bibit() {
        return jenis_bibit;
    }

    public void setJenis_bibit(String jenis_bibit) {
        this.jenis_bibit = jenis_bibit;
    }

    public float getBibit_normal() {
        return bibit_normal;
    }

    public void setBibit_normal(float bibit_normal) {
        this.bibit_normal = bibit_normal;
    }

    public float getBibit_afkir() {
        return bibit_afkir;
    }

    public void setBibit_afkir(float bibit_afkir) {
        this.bibit_afkir = bibit_afkir;
    }

    public float getBibit_over_plus() {
        return bibit_over_plus;
    }

    public void setBibit_over_plus(float bibit_over_plus) {
        this.bibit_over_plus = bibit_over_plus;
    }

    public float getBibit_over() {
        return bibit_over;
    }

    public void setBibit_over(float bibit_over) {
        this.bibit_over = bibit_over;
    }

    public float getBibit_1() {
        return bibit_1;
    }

    public void setBibit_1(float bibit_1) {
        this.bibit_1 = bibit_1;
    }

    public float getBibit_2() {
        return bibit_2;
    }

    public void setBibit_2(float bibit_2) {
        this.bibit_2 = bibit_2;
    }

    public float getBibit_3() {
        return bibit_3;
    }

    public void setBibit_3(float bibit_3) {
        this.bibit_3 = bibit_3;
    }

    public float getBibit_4() {
        return bibit_4;
    }

    public void setBibit_4(float bibit_4) {
        this.bibit_4 = bibit_4;
    }

    public float getBibit_5() {
        return bibit_5;
    }

    public void setBibit_5(float bibit_5) {
        this.bibit_5 = bibit_5;
    }

    public float getBibit_6() {
        return bibit_6;
    }

    public void setBibit_6(float bibit_6) {
        this.bibit_6 = bibit_6;
    }

    public float getBibit_7() {
        return bibit_7;
    }

    public void setBibit_7(float bibit_7) {
        this.bibit_7 = bibit_7;
    }

    public float getHasil() {
        return hasil;
    }

    public void setHasil(float hasil) {
        this.hasil = hasil;
    }

    public int getInformasi_bibit_terdipping() {
        return informasi_bibit_terdipping;
    }

    public void setInformasi_bibit_terdipping(int informasi_bibit_terdipping) {
        this.informasi_bibit_terdipping = informasi_bibit_terdipping;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
