package com.ggf.qcpp.e_formpengamatan.adukanbahan.model;

import com.google.gson.annotations.SerializedName;

public class SampleModel {

    @SerializedName("no_spk")
    public String no_spk;
    @SerializedName("no_spk2")
    public String no_spk2;
    @SerializedName("no_line")
    public String no_line;
    @SerializedName("lokasi")
    public String lokasi;
    @SerializedName("plot")
    public int PLOT;
    @SerializedName("no_sample")
    public int no_sample;
    @SerializedName("keterangan")
    public String keterangan;

    //batas------------------------------------------

    @SerializedName("tanggal_pengamatan")
    public String tanggal_pengamatan;

    @SerializedName("mandor_bibit")
    public String mandor_bibit;

    @SerializedName("div")
    public String div;

    @SerializedName("kode_bsc")
    public String kode_bsc;

    @SerializedName("shift")
    public String shift;

    @SerializedName("reworking")
    public String reworking;

    @SerializedName("aktivitas")
    public String aktivitas;

    @SerializedName("jenis_bahan")
    public String jenis_bahan;

    @SerializedName("rencana")
    public float rencana;

    @SerializedName("real")
    public float real_value;
    @SerializedName("pengisian_ke")
    public float pengisian_ke;

    @SerializedName("volume_air")
    public float volume_air;

//    @SerializedName("ceklist_keaktifan_agitator_tangki")
//    public String ceklist_keaktifan_agitator_tangki;

    @SerializedName("ceklist_keaktifan_agitator_cameco")
    public String ceklist_keaktifan_agitator_cameco;


    @SerializedName("username")
    public String USERNAME;

    //batas------------------------------------------


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

    public String getReworking() {
        return reworking;
    }

    public void setReworking(String reworking) {
        this.reworking = reworking;
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

    public int getPLOT() {
        return PLOT;
    }

    public void setPLOT(int PLOT) {
        this.PLOT = PLOT;
    }

    public int getNo_sample() {
        return no_sample;
    }

    public void setNo_sample(String no_sample) {
        this.no_sample = Integer.parseInt(no_sample);
    }

    public void setNo_sample(int no_sample) {
        this.no_sample = no_sample;
    }


    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getTanggal_pengamatan() {
        return tanggal_pengamatan;
    }

    public void setTanggal_pengamatan(String tanggal_pengamatan) {
        this.tanggal_pengamatan = tanggal_pengamatan;
    }

    public String getMandor_bibit() {
        return mandor_bibit;
    }

    public void setMandor_bibit(String mandor_bibit) {
        this.mandor_bibit = mandor_bibit;
    }

    public String getDiv() {
        return div;
    }

    public void setDiv(String div) {
        this.div = div;
    }

    public String getKode_bsc() {
        return kode_bsc;
    }

    public void setKode_bsc(String kode_bsc) {
        this.kode_bsc = kode_bsc;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getAktivitas() {
        return aktivitas;
    }

    public void setAktivitas(String aktivitas) {
        this.aktivitas = aktivitas;
    }

    public String getJenis_bahan() {
        return jenis_bahan;
    }

    public void setJenis_bahan(String jenis_bahan) {
        this.jenis_bahan = jenis_bahan;
    }

    public float getRencana() {
        return rencana;
    }

    public void setRencana(float rencana) {
        this.rencana = rencana;
    }

    public float getReal_value() {
        return real_value;
    }

    public void setReal_value(float real_value) {
        this.real_value = real_value;
    }

    public float getPengisian_ke() {
        return pengisian_ke;
    }

    public void setPengisian_ke(float pengisian_ke) {
        this.pengisian_ke = pengisian_ke;
    }

    public float getVolume_air() {
        return volume_air;
    }

    public void setVolume_air(float volume_air) {
        this.volume_air = volume_air;
    }



//    public String getCeklist_keaktifan_agitator_tangki() {
//        return ceklist_keaktifan_agitator_tangki;
//    }
//
//    public void setCeklist_keaktifan_agitator_tangki(String ceklist_keaktifan_agitator_tangki) {
//        this.ceklist_keaktifan_agitator_tangki = ceklist_keaktifan_agitator_tangki;
//    }

    public String getCeklist_keaktifan_agitator_cameco() {
        return ceklist_keaktifan_agitator_cameco;
    }

    public void setCeklist_keaktifan_agitator_cameco(String ceklist_keaktifan_agitator_cameco) {
        this.ceklist_keaktifan_agitator_cameco = ceklist_keaktifan_agitator_cameco;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }



}
