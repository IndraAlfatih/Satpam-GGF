package com.ggf.qcpp.e_formpengamatan.kebersihanpanen.model;

import com.google.gson.annotations.SerializedName;

public class SampleModel {

    @SerializedName("no_spk")
    public String no_spk;
    @SerializedName("lokasi")
    public String lokasi;
    @SerializedName("plot")
    public int PLOT;
    @SerializedName("no_sample")
    public int no_sample;
    @SerializedName("keterangan")
    public String keterangan;

    //batas------------------------------------------
    @SerializedName("tanggal_panen")
    public String tanggal_panen;
    @SerializedName("no_spk2")
    public String no_spk2;
    @SerializedName("no_line")
    public String no_line;

    @SerializedName("tanggal_plot")
    public String tanggal_plot;
    @SerializedName("reworking")
    public String reworking;
    @SerializedName("shift")
    public String shift;

    @SerializedName("update_peta")
    public String update_peta;


    @SerializedName("luas_plot")
    public double luas_plot;

    @SerializedName("regu_panen")
    public String regu_panen;

    @SerializedName("wil")
    public String wil;

    @SerializedName("panjang_pengamatan")
    public int panjang_pengamatan;

    @SerializedName("normal_buah_tertinggal_besar")
    public int normal_buah_tertinggal_besar;

    @SerializedName("normal_buah_tertinggal_sedang")
    public int normal_buah_tertinggal_sedang;

    @SerializedName("normal_buah_tertinggal_kecil")
    public int normal_buah_tertinggal_kecil;

    @SerializedName("tidak_crown")
    public int tidak_crown;

    @SerializedName("jumlah_sal_sekunder")
    public int jumlah_sal_sekunder;

    @SerializedName("jumlah_titik_pengamatan")
    public int jumlah_titik_pengamatan;

    @SerializedName("sekunder_buah_tertinggal_besar")
    public int sekunder_buah_tertinggal_besar;

    @SerializedName("sekunder_buah_tertinggal_sedang")
    public int sekunder_buah_tertinggal_sedang;

    @SerializedName("sekunder_buah_tertinggal_kecil")
    public int sekunder_buah_tertinggal_kecil;

    @SerializedName("jumlah_sal_tersier")
    public int jumlah_sal_tersier;

    @SerializedName("jumlah_titik_diamati")
    public int jumlah_titik_diamati;

    @SerializedName("tersier_buah_tertinggal_besar")
    public int tersier_buah_tertinggal_besar;

    @SerializedName("tersier_buah_tertinggal_sedang")
    public int tersier_buah_tertinggal_sedang;

    @SerializedName("tersier_buah_tertinggal_kecil")
    public int tersier_buah_tertinggal_kecil;

    @SerializedName("pengamatan_crown_tertinggal_baris_1_sampai_8")
    public int pengamatan_crown_tertinggal_baris_1_sampai_8;

    @SerializedName("pengamatan_crown_tertinggal_baris_9_sampai_35")
    public int pengamatan_crown_tertinggal_baris_9_sampai_35;

    @SerializedName("jumlah_baris")
    public int jumlah_baris;

    @SerializedName("total_crown")
    public int total_crown;

    @SerializedName("jalur")
    public String jalur;

    @SerializedName("statuslokasi")
    public String statuslokasi;

    @SerializedName("statuspengamatan")
    public String statuspengamatan;

    @SerializedName("crownnormal")
    public String crownnormal;

    @SerializedName("crownkipas")
    public String crownkipas;

    @SerializedName("crownbusuknormal")
    public String crownbusuknormal;

    @SerializedName("crownbusuktidaknormal")
    public String crownbusuktidaknormal;


    @SerializedName("username")
    public String USERNAME;

    @SerializedName("lebar_plot")
    public Float lebar_plot;

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

    public String getTanggal_plot() {
        return tanggal_plot;
    }

    public void setTanggal_plot(String tanggal_plot) {
        this.tanggal_plot = tanggal_plot;
    }

    public int getTidak_crown() {
        return tidak_crown;
    }

    public void setTidak_crown(int tidak_crown) {
        this.tidak_crown = tidak_crown;
    }

    public Float getLebar_plot() {
        return lebar_plot;
    }

    public void setLebar_plot(Float lebar_plot) {
        this.lebar_plot = lebar_plot;
    }

    public String getCrownnormal() {
        return crownnormal;
    }

    public void setCrownnormal(String crownnormal) {
        this.crownnormal = crownnormal;
    }

    public String getCrownkipas() {
        return crownkipas;
    }

    public String getReworking() {
        return reworking;
    }

    public void setReworking(String reworking) {
        this.reworking = reworking;
    }

    public void setCrownkipas(String crownkipas) {
        this.crownkipas = crownkipas;
    }

    public String getCrownbusuknormal() {
        return crownbusuknormal;
    }

    public void setCrownbusuknormal(String crownbusuknormal) {
        this.crownbusuknormal = crownbusuknormal;
    }

    public String getCrownbusuktidaknormal() {
        return crownbusuktidaknormal;
    }

    public void setCrownbusuktidaknormal(String crownbusuktidaknormal) {
        this.crownbusuktidaknormal = crownbusuktidaknormal;
    }

    public String getStatuspengamatan() {
        return statuspengamatan;
    }

    public void setStatuspengamatan(String statuspengamatan) {
        this.statuspengamatan = statuspengamatan;
    }

    public String getStatuslokasi() {
        return statuslokasi;
    }

    public void setStatuslokasi(String statuslokasi) {
        this.statuslokasi = statuslokasi;
    }

    public String getJalur() {
        return jalur;
    }

    public void setJalur(String jalur) {
        this.jalur = jalur;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
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


    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public int getNormal_buah_tertinggal_besar() {
        return normal_buah_tertinggal_besar;
    }

    public void setNormal_buah_tertinggal_besar(int normal_buah_tertinggal_besar) {
        this.normal_buah_tertinggal_besar = normal_buah_tertinggal_besar;
    }

    public void setNo_sample(int no_sample) {
        this.no_sample = no_sample;
    }

    public String getTanggal_panen() {
        return tanggal_panen;
    }

    public void setTanggal_panen(String tanggal_panen) {
        this.tanggal_panen = tanggal_panen;
    }

    public String getUpdate_peta() {
        return update_peta;
    }

    public void setUpdate_peta(String update_peta) {
        this.update_peta = update_peta;
    }


    public double getLuas_plot() {
        return luas_plot;
    }

    public void setLuas_plot(double luas_plot) {
        this.luas_plot = luas_plot;
    }

    public String getRegu_panen() {
        return regu_panen;
    }

    public void setRegu_panen(String regu_panen) {
        this.regu_panen = regu_panen;
    }

    public String getWil() {
        return wil;
    }

    public void setWil(String wil) {
        this.wil = wil;
    }

    public int getPanjang_pengamatan() {
        return panjang_pengamatan;
    }

    public void setPanjang_pengamatan(int panjang_pengamatan) {
        this.panjang_pengamatan = panjang_pengamatan;
    }

    public int getNormal_buah_tertinggal_sedang() {
        return normal_buah_tertinggal_sedang;
    }

    public void setNormal_buah_tertinggal_sedang(int normal_buah_tertinggal_sedang) {
        this.normal_buah_tertinggal_sedang = normal_buah_tertinggal_sedang;
    }

    public int getNormal_buah_tertinggal_kecil() {
        return normal_buah_tertinggal_kecil;
    }

    public void setNormal_buah_tertinggal_kecil(int normal_buah_tertinggal_kecil) {
        this.normal_buah_tertinggal_kecil = normal_buah_tertinggal_kecil;
    }

    public int getJumlah_sal_sekunder() {
        return jumlah_sal_sekunder;
    }

    public void setJumlah_sal_sekunder(int jumlah_sal_sekunder) {
        this.jumlah_sal_sekunder = jumlah_sal_sekunder;
    }

    public int getJumlah_titik_pengamatan() {
        return jumlah_titik_pengamatan;
    }

    public void setJumlah_titik_pengamatan(int jumlah_titik_pengamatan) {
        this.jumlah_titik_pengamatan = jumlah_titik_pengamatan;
    }

    public int getSekunder_buah_tertinggal_besar() {
        return sekunder_buah_tertinggal_besar;
    }

    public void setSekunder_buah_tertinggal_besar(int sekunder_buah_tertinggal_besar) {
        this.sekunder_buah_tertinggal_besar = sekunder_buah_tertinggal_besar;
    }

    public int getSekunder_buah_tertinggal_sedang() {
        return sekunder_buah_tertinggal_sedang;
    }

    public void setSekunder_buah_tertinggal_sedang(int sekunder_buah_tertinggal_sedang) {
        this.sekunder_buah_tertinggal_sedang = sekunder_buah_tertinggal_sedang;
    }

    public int getSekunder_buah_tertinggal_kecil() {
        return sekunder_buah_tertinggal_kecil;
    }

    public void setSekunder_buah_tertinggal_kecil(int sekunder_buah_tertinggal_kecil) {
        this.sekunder_buah_tertinggal_kecil = sekunder_buah_tertinggal_kecil;
    }

    public int getJumlah_sal_tersier() {
        return jumlah_sal_tersier;
    }

    public void setJumlah_sal_tersier(int jumlah_sal_tersier) {
        this.jumlah_sal_tersier = jumlah_sal_tersier;
    }

    public int getJumlah_titik_diamati() {
        return jumlah_titik_diamati;
    }

    public void setJumlah_titik_diamati(int jumlah_titik_diamati) {
        this.jumlah_titik_diamati = jumlah_titik_diamati;
    }

    public int getTersier_buah_tertinggal_besar() {
        return tersier_buah_tertinggal_besar;
    }

    public void setTersier_buah_tertinggal_besar(int tersier_buah_tertinggal_besar) {
        this.tersier_buah_tertinggal_besar = tersier_buah_tertinggal_besar;
    }

    public int getTersier_buah_tertinggal_sedang() {
        return tersier_buah_tertinggal_sedang;
    }

    public void setTersier_buah_tertinggal_sedang(int tersier_buah_tertinggal_sedang) {
        this.tersier_buah_tertinggal_sedang = tersier_buah_tertinggal_sedang;
    }

    public int getTersier_buah_tertinggal_kecil() {
        return tersier_buah_tertinggal_kecil;
    }

    public void setTersier_buah_tertinggal_kecil(int tersier_buah_tertinggal_kecil) {
        this.tersier_buah_tertinggal_kecil = tersier_buah_tertinggal_kecil;
    }

    public int getPengamatan_crown_tertinggal_baris_1_sampai_8() {
        return pengamatan_crown_tertinggal_baris_1_sampai_8;
    }

    public void setPengamatan_crown_tertinggal_baris_1_sampai_8(int pengamatan_crown_tertinggal_baris_1_sampai_8) {
        this.pengamatan_crown_tertinggal_baris_1_sampai_8 = pengamatan_crown_tertinggal_baris_1_sampai_8;
    }

    public int getPengamatan_crown_tertinggal_baris_9_sampai_35() {
        return pengamatan_crown_tertinggal_baris_9_sampai_35;
    }

    public void setPengamatan_crown_tertinggal_baris_9_sampai_35(int pengamatan_crown_tertinggal_baris_9_sampai_35) {
        this.pengamatan_crown_tertinggal_baris_9_sampai_35 = pengamatan_crown_tertinggal_baris_9_sampai_35;
    }

    public int getJumlah_baris() {
        return jumlah_baris;
    }

    public void setJumlah_baris(int jumlah_baris) {
        this.jumlah_baris = jumlah_baris;
    }

    public int getTotal_crown() {
        return total_crown;
    }

    public void setTotal_crown(int total_crown) {
        this.total_crown = total_crown;
    }

//    public String getCeklist_plot() {
//        return ceklist_plot;
//    }
//
//    public void setCeklist_plot(String ceklist_plot) {
//        this.ceklist_plot = ceklist_plot;
//    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }



}
