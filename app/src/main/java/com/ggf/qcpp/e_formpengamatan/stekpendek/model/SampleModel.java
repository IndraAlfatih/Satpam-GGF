package com.ggf.qcpp.e_formpengamatan.stekpendek.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class SampleModel {

    @SerializedName("no_spk")
    public String no_spk;
    @SerializedName("lokasi")
    public String lokasi;
    @SerializedName("no_sample")
    public int no_sample;
    @SerializedName("plot")
    public int PLOT;
    @SerializedName("wil")
    public String wil;
    @SerializedName("luas_plot")
    public Float luas_plot;
    @SerializedName("mandor_bibit")
    public String mandor_bibit;

    @SerializedName("status_pengamatan")
    public String STATUS_PENGAMATAN;
    @SerializedName("update_peta")
    public String update_peta;
    @SerializedName("jenis_bibit")
    public String jenis_bibit;
    @SerializedName("jumlah_bibit_di_spk")
    public int jumlah_bibit_di_spk;
    @SerializedName("jumlah_bibit_normal")
    public int jumlah_bibit_normal;
    @SerializedName("jumlah_bibit_afkir")
    public int jumlah_bibit_afkir;
    @SerializedName("jumlah_bibit_potonga_masuk_standar_15")
    public float jumlah_bibit_potonga_masuk_standar_15;

    @SerializedName("jumlah_bibit_potonga_masuk_standar_16")
    public float jumlah_bibit_potonga_masuk_standar_16;

    @SerializedName("jumlah_bibit_potonga_masuk_standar_17")
    public float jumlah_bibit_potonga_masuk_standar_17;

    @SerializedName("jumlah_bibit_potonga_masuk_standar_18")
    public float jumlah_bibit_potonga_masuk_standar_18;

    @SerializedName("jumlah_bibit_potonga_masuk_standar_19")
    public float jumlah_bibit_potonga_masuk_standar_19;

    @SerializedName("jumlah_bibit_potonga_masuk_standar_20")
    public float jumlah_bibit_potonga_masuk_standar_20;

    @SerializedName("jumlah_bibit_potonga_masuk_standar_21")
    public float jumlah_bibit_potonga_masuk_standar_21;

    @SerializedName("jumlah_bibit_potonga_masuk_standar_22")
    public float jumlah_bibit_potonga_masuk_standar_22;

    @SerializedName("jumlah_bibit_potonga_masuk_standar_23")
    public float jumlah_bibit_potonga_masuk_standar_23;

    @SerializedName("jumlah_bibit_potonga_masuk_standar_24")
    public float jumlah_bibit_potonga_masuk_standar_24;

    @SerializedName("jumlah_bibit_potonga_masuk_standar_25")
    public float jumlah_bibit_potonga_masuk_standar_25;

    @SerializedName("jumlah_bibit_potonga_masuk_standar_26")
    public float jumlah_bibit_potonga_masuk_standar_26;

    @SerializedName("jumlah_bibit_potonga_masuk_standar_27")
    public float jumlah_bibit_potonga_masuk_standar_27;

    @SerializedName("jumlah_bibit_potonga_masuk_standar_28")
    public float jumlah_bibit_potonga_masuk_standar_28;


    @SerializedName("jumlahsample")
    public int jumlahsample;

    @SerializedName("alat_potong_bibit")
    public String alat_potong_bibit;

    @SerializedName("keterangan")
    public String keterangan;

    @SerializedName("username")
    public String USERNAME;

    public int getJumlahsample() {
        return jumlahsample;
    }

    public void setJumlahsample(int jumlahsample) {
        this.jumlahsample = jumlahsample;
    }

    public String getAlat_potong_bibit() {
        return alat_potong_bibit;
    }

    public void setAlat_potong_bibit(String alat_potong_bibit) {
        this.alat_potong_bibit = alat_potong_bibit;
    }

    public void setNo_sample(int no_sample) {
        this.no_sample = no_sample;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getSTATUS_PENGAMATAN() {
        return STATUS_PENGAMATAN;
    }

    public void setSTATUS_PENGAMATAN(String STATUS_PENGAMATAN) {
        this.STATUS_PENGAMATAN = STATUS_PENGAMATAN;
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

    public int getNo_sample() {
        return no_sample;
    }

    public void setNo_sample(String no_sample) {
        this.no_sample = Integer.parseInt(no_sample);
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

    public String getWil() {
        return wil;
    }

    public void setWil(String wil) {
        this.wil = wil;
    }

    public String getMandor_bibit() {
        return mandor_bibit;
    }

    public void setMandor_bibit(String mandor_bibit) {
        this.mandor_bibit = mandor_bibit;
    }

    public String getUpdate_peta() {
        return update_peta;
    }

    public void setUpdate_peta(String update_peta) {
        this.update_peta = update_peta;
    }

    public String getJenis_bibit() {
        return jenis_bibit;
    }

    public void setJenis_bibit(String jenis_bibit) {
        this.jenis_bibit = jenis_bibit;
    }

    public int getJumlah_bibit_di_spk() {
        return jumlah_bibit_di_spk;
    }

    public void setJumlah_bibit_di_spk(int jumlah_bibit_di_spk) {
        this.jumlah_bibit_di_spk = jumlah_bibit_di_spk;
    }

    public int getJumlah_bibit_normal() {
        return jumlah_bibit_normal;
    }

    public void setJumlah_bibit_normal(int jumlah_bibit_normal) {
        this.jumlah_bibit_normal = jumlah_bibit_normal;
    }

    public int getJumlah_bibit_afkir() {
        return jumlah_bibit_afkir;
    }

    public void setJumlah_bibit_afkir(int jumlah_bibit_afkir) {
        this.jumlah_bibit_afkir = jumlah_bibit_afkir;
    }

    public float getJumlah_bibit_potonga_masuk_standar_15() {
        return jumlah_bibit_potonga_masuk_standar_15;
    }

    public void setJumlah_bibit_potonga_masuk_standar_15(float jumlah_bibit_potonga_masuk_standar_15) {
        this.jumlah_bibit_potonga_masuk_standar_15 = jumlah_bibit_potonga_masuk_standar_15;
    }

    public float getJumlah_bibit_potonga_masuk_standar_16() {
        return jumlah_bibit_potonga_masuk_standar_16;
    }

    public void setJumlah_bibit_potonga_masuk_standar_16(float jumlah_bibit_potonga_masuk_standar_16) {
        this.jumlah_bibit_potonga_masuk_standar_16 = jumlah_bibit_potonga_masuk_standar_16;
    }

    public float getJumlah_bibit_potonga_masuk_standar_17() {
        return jumlah_bibit_potonga_masuk_standar_17;
    }

    public void setJumlah_bibit_potonga_masuk_standar_17(float jumlah_bibit_potonga_masuk_standar_17) {
        this.jumlah_bibit_potonga_masuk_standar_17 = jumlah_bibit_potonga_masuk_standar_17;
    }

    public float getJumlah_bibit_potonga_masuk_standar_18() {
        return jumlah_bibit_potonga_masuk_standar_18;
    }

    public void setJumlah_bibit_potonga_masuk_standar_18(float jumlah_bibit_potonga_masuk_standar_18) {
        this.jumlah_bibit_potonga_masuk_standar_18 = jumlah_bibit_potonga_masuk_standar_18;
    }

    public float getJumlah_bibit_potonga_masuk_standar_19() {
        return jumlah_bibit_potonga_masuk_standar_19;
    }

    public void setJumlah_bibit_potonga_masuk_standar_19(float jumlah_bibit_potonga_masuk_standar_19) {
        this.jumlah_bibit_potonga_masuk_standar_19 = jumlah_bibit_potonga_masuk_standar_19;
    }

    public float getJumlah_bibit_potonga_masuk_standar_20() {
        return jumlah_bibit_potonga_masuk_standar_20;
    }

    public void setJumlah_bibit_potonga_masuk_standar_20(float jumlah_bibit_potonga_masuk_standar_20) {
        this.jumlah_bibit_potonga_masuk_standar_20 = jumlah_bibit_potonga_masuk_standar_20;
    }

    public float getJumlah_bibit_potonga_masuk_standar_21() {
        return jumlah_bibit_potonga_masuk_standar_21;
    }

    public void setJumlah_bibit_potonga_masuk_standar_21(float jumlah_bibit_potonga_masuk_standar_21) {
        this.jumlah_bibit_potonga_masuk_standar_21 = jumlah_bibit_potonga_masuk_standar_21;
    }

    public float getJumlah_bibit_potonga_masuk_standar_22() {
        return jumlah_bibit_potonga_masuk_standar_22;
    }

    public void setJumlah_bibit_potonga_masuk_standar_22(float jumlah_bibit_potonga_masuk_standar_22) {
        this.jumlah_bibit_potonga_masuk_standar_22 = jumlah_bibit_potonga_masuk_standar_22;
    }

    public float getJumlah_bibit_potonga_masuk_standar_23() {
        return jumlah_bibit_potonga_masuk_standar_23;
    }

    public void setJumlah_bibit_potonga_masuk_standar_23(float jumlah_bibit_potonga_masuk_standar_23) {
        this.jumlah_bibit_potonga_masuk_standar_23 = jumlah_bibit_potonga_masuk_standar_23;
    }

    public float getJumlah_bibit_potonga_masuk_standar_24() {
        return jumlah_bibit_potonga_masuk_standar_24;
    }

    public void setJumlah_bibit_potonga_masuk_standar_24(float jumlah_bibit_potonga_masuk_standar_24) {
        this.jumlah_bibit_potonga_masuk_standar_24 = jumlah_bibit_potonga_masuk_standar_24;
    }

    public float getJumlah_bibit_potonga_masuk_standar_25() {
        return jumlah_bibit_potonga_masuk_standar_25;
    }

    public void setJumlah_bibit_potonga_masuk_standar_25(float jumlah_bibit_potonga_masuk_standar_25) {
        this.jumlah_bibit_potonga_masuk_standar_25 = jumlah_bibit_potonga_masuk_standar_25;
    }

    public float getJumlah_bibit_potonga_masuk_standar_26() {
        return jumlah_bibit_potonga_masuk_standar_26;
    }

    public void setJumlah_bibit_potonga_masuk_standar_26(float jumlah_bibit_potonga_masuk_standar_26) {
        this.jumlah_bibit_potonga_masuk_standar_26 = jumlah_bibit_potonga_masuk_standar_26;
    }

    public float getJumlah_bibit_potonga_masuk_standar_27() {
        return jumlah_bibit_potonga_masuk_standar_27;
    }

    public void setJumlah_bibit_potonga_masuk_standar_27(float jumlah_bibit_potonga_masuk_standar_27) {
        this.jumlah_bibit_potonga_masuk_standar_27 = jumlah_bibit_potonga_masuk_standar_27;
    }

    public float getJumlah_bibit_potonga_masuk_standar_28() {
        return jumlah_bibit_potonga_masuk_standar_28;
    }

    public void setJumlah_bibit_potonga_masuk_standar_28(float jumlah_bibit_potonga_masuk_standar_28) {
        this.jumlah_bibit_potonga_masuk_standar_28 = jumlah_bibit_potonga_masuk_standar_28;
    }

    public void setJumlah_bibit_potonga_masuk_standar_28(int jumlah_bibit_potonga_masuk_standar_28) {
        this.jumlah_bibit_potonga_masuk_standar_28 = jumlah_bibit_potonga_masuk_standar_28;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }


}
