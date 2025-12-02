package com.ggf.qcpp.e_formpengamatan.stekpanjang.model;

import com.google.gson.annotations.SerializedName;

public class SampleModel {

    @SerializedName("no_spk")
    public String no_spk;

    @SerializedName("lokasi")
    public String lokasi;

    @SerializedName("mandor_bibit")
    public String mandorBibit;

    @SerializedName("update_peta")
    public String updatePeta;

    @SerializedName("jenis_bibit")
    public String jenisBibit;

    @SerializedName("jumlah_bibit_di_spk")
    public int jumlahBibitDiSpk;

    @SerializedName("plot")
    public int PLOT;

    @SerializedName("luas_plot")
    public double luasPlot;

    @SerializedName("no_sample")
    public int no_sample;

    @SerializedName("kode_unit")
    public String kodeUnit;

    @SerializedName("jumlah_bibit_ikat_di_spk")
    public int jumlahBibitIkatDiSpk;

    @SerializedName("real_bibit_ikat")
    public int realBibitIkat;

    @SerializedName("bibit_normal")
    public int bibitNormal;

    @SerializedName("bibit_afkir")
    public int bibitAfkir; // Nullable: Use `int` but be cautious with null handling

    @SerializedName("jumlah_keliling_batang_masuk_standar")
    public int jumlahKelilingBatangMasukStandar;

    @SerializedName("keliling_bibit_atas_1")
    public int kelilingBibitAtas1; // Nullable

    @SerializedName("keliling_bibit_atas_2")
    public int kelilingBibitAtas2; // Nullable

    @SerializedName("keliling_bibit_atas_3")
    public int kelilingBibitAtas3; // Nullable

    @SerializedName("keliling_bibit_atas_4")
    public int kelilingBibitAtas4; // Nullable

    @SerializedName("keliling_bibit_atas_5")
    public int kelilingBibitAtas5; // Nullable

    @SerializedName("keliling_bibit_atas_6")
    public int kelilingBibitAtas6; // Nullable

    @SerializedName("keliling_bibit_atas_7")
    public int kelilingBibitAtas7; // Nullable

    @SerializedName("keliling_bibit_atas_8")
    public int kelilingBibitAtas8; // Nullable

    @SerializedName("keliling_bibit_atas_9")
    public int kelilingBibitAtas9; // Nullable

    @SerializedName("keliling_bibit_atas_10")
    public int kelilingBibitAtas10; // Nullable

    @SerializedName("keliling_bibit_bawah_1")
    public int kelilingBibitBawah1; // Nullable

    @SerializedName("keliling_bibit_bawah_2")
    public int kelilingBibitBawah2; // Nullable

    @SerializedName("keliling_bibit_bawah_3")
    public int kelilingBibitBawah3; // Nullable

    @SerializedName("keliling_bibit_bawah_4")
    public int kelilingBibitBawah4; // Nullable

    @SerializedName("keliling_bibit_bawah_5")
    public int kelilingBibitBawah5; // Nullable

    @SerializedName("keliling_bibit_bawah_6")
    public int kelilingBibitBawah6; // Nullable

    @SerializedName("keliling_bibit_bawah_7")
    public int kelilingBibitBawah7; // Nullable

    @SerializedName("keliling_bibit_bawah_8")
    public int kelilingBibitBawah8; // Nullable

    @SerializedName("keliling_bibit_bawah_9")
    public int kelilingBibitBawah9; // Nullable

    @SerializedName("keliling_bibit_bawah_10")
    public int kelilingBibitBawah10; // Nullable

    @SerializedName("username")
    public String USERNAME;

    @SerializedName("keterangan")
    public String keterangan;

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public int getRealBibitIkat() {
        return realBibitIkat;
    }

    public void setRealBibitIkat(int realBibitIkat) {
        this.realBibitIkat = realBibitIkat;
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

    public String getMandorBibit() {
        return mandorBibit;
    }

    public void setMandorBibit(String mandorBibit) {
        this.mandorBibit = mandorBibit;
    }

    public String getUpdatePeta() {
        return updatePeta;
    }

    public void setUpdatePeta(String updatePeta) {
        this.updatePeta = updatePeta;
    }

    public String getJenisBibit() {
        return jenisBibit;
    }

    public void setJenisBibit(String jenisBibit) {
        this.jenisBibit = jenisBibit;
    }

    public int getJumlahBibitDiSpk() {
        return jumlahBibitDiSpk;
    }

    public void setJumlahBibitDiSpk(int jumlahBibitDiSpk) {
        this.jumlahBibitDiSpk = jumlahBibitDiSpk;
    }



    public double getLuasPlot() {
        return luasPlot;
    }

    public void setLuasPlot(double luasPlot) {
        this.luasPlot = luasPlot;
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


    public String getKodeUnit() {
        return kodeUnit;
    }

    public void setKodeUnit(String kodeUnit) {
        this.kodeUnit = kodeUnit;
    }

    public int getJumlahBibitIkatDiSpk() {
        return jumlahBibitIkatDiSpk;
    }

    public void setJumlahBibitIkatDiSpk(int jumlahBibitIkatDiSpk) {
        this.jumlahBibitIkatDiSpk = jumlahBibitIkatDiSpk;
    }

    public int getBibitNormal() {
        return bibitNormal;
    }

    public void setBibitNormal(int bibitNormal) {
        this.bibitNormal = bibitNormal;
    }

    public int getBibitAfkir() {
        return bibitAfkir;
    }

    public void setBibitAfkir(int bibitAfkir) {
        this.bibitAfkir = bibitAfkir;
    }

    public int getJumlahKelilingBatangMasukStandar() {
        return jumlahKelilingBatangMasukStandar;
    }

    public void setJumlahKelilingBatangMasukStandar(int jumlahKelilingBatangMasukStandar) {
        this.jumlahKelilingBatangMasukStandar = jumlahKelilingBatangMasukStandar;
    }

    public int getKelilingBibitAtas1() {
        return kelilingBibitAtas1;
    }

    public void setKelilingBibitAtas1(int kelilingBibitAtas1) {
        this.kelilingBibitAtas1 = kelilingBibitAtas1;
    }

    public int getKelilingBibitAtas2() {
        return kelilingBibitAtas2;
    }

    public void setKelilingBibitAtas2(int kelilingBibitAtas2) {
        this.kelilingBibitAtas2 = kelilingBibitAtas2;
    }

    public int getKelilingBibitAtas3() {
        return kelilingBibitAtas3;
    }

    public void setKelilingBibitAtas3(int kelilingBibitAtas3) {
        this.kelilingBibitAtas3 = kelilingBibitAtas3;
    }

    public int getKelilingBibitAtas4() {
        return kelilingBibitAtas4;
    }

    public void setKelilingBibitAtas4(int kelilingBibitAtas4) {
        this.kelilingBibitAtas4 = kelilingBibitAtas4;
    }

    public int getKelilingBibitAtas5() {
        return kelilingBibitAtas5;
    }

    public void setKelilingBibitAtas5(int kelilingBibitAtas5) {
        this.kelilingBibitAtas5 = kelilingBibitAtas5;
    }

    public int getKelilingBibitAtas6() {
        return kelilingBibitAtas6;
    }

    public void setKelilingBibitAtas6(int kelilingBibitAtas6) {
        this.kelilingBibitAtas6 = kelilingBibitAtas6;
    }

    public int getKelilingBibitAtas7() {
        return kelilingBibitAtas7;
    }

    public void setKelilingBibitAtas7(int kelilingBibitAtas7) {
        this.kelilingBibitAtas7 = kelilingBibitAtas7;
    }

    public int getKelilingBibitAtas8() {
        return kelilingBibitAtas8;
    }

    public void setKelilingBibitAtas8(int kelilingBibitAtas8) {
        this.kelilingBibitAtas8 = kelilingBibitAtas8;
    }

    public int getKelilingBibitAtas9() {
        return kelilingBibitAtas9;
    }

    public void setKelilingBibitAtas9(int kelilingBibitAtas9) {
        this.kelilingBibitAtas9 = kelilingBibitAtas9;
    }

    public int getKelilingBibitAtas10() {
        return kelilingBibitAtas10;
    }

    public void setKelilingBibitAtas10(int kelilingBibitAtas10) {
        this.kelilingBibitAtas10 = kelilingBibitAtas10;
    }


    public int getKelilingBibitBawah1() {
        return kelilingBibitBawah1;
    }

    public void setKelilingBibitBawah1(int kelilingBibitBawah1) {
        this.kelilingBibitBawah1 = kelilingBibitBawah1;
    }

    public int getKelilingBibitBawah2() {
        return kelilingBibitBawah2;
    }

    public void setKelilingBibitBawah2(int kelilingBibitBawah2) {
        this.kelilingBibitBawah2 = kelilingBibitBawah2;
    }

    public int getKelilingBibitBawah3() {
        return kelilingBibitBawah3;
    }

    public void setKelilingBibitBawah3(int kelilingBibitBawah3) {
        this.kelilingBibitBawah3 = kelilingBibitBawah3;
    }

    public int getKelilingBibitBawah4() {
        return kelilingBibitBawah4;
    }

    public void setKelilingBibitBawah4(int kelilingBibitBawah4) {
        this.kelilingBibitBawah4 = kelilingBibitBawah4;
    }

    public int getKelilingBibitBawah5() {
        return kelilingBibitBawah5;
    }

    public void setKelilingBibitBawah5(int kelilingBibitBawah5) {
        this.kelilingBibitBawah5 = kelilingBibitBawah5;
    }

    public int getKelilingBibitBawah6() {
        return kelilingBibitBawah6;
    }

    public void setKelilingBibitBawah6(int kelilingBibitBawah6) {
        this.kelilingBibitBawah6 = kelilingBibitBawah6;
    }

    public int getKelilingBibitBawah7() {
        return kelilingBibitBawah7;
    }

    public void setKelilingBibitBawah7(int kelilingBibitBawah7) {
        this.kelilingBibitBawah7 = kelilingBibitBawah7;
    }

    public int getKelilingBibitBawah8() {
        return kelilingBibitBawah8;
    }

    public void setKelilingBibitBawah8(int kelilingBibitBawah8) {
        this.kelilingBibitBawah8 = kelilingBibitBawah8;
    }

    public int getKelilingBibitBawah9() {
        return kelilingBibitBawah9;
    }

    public void setKelilingBibitBawah9(int kelilingBibitBawah9) {
        this.kelilingBibitBawah9 = kelilingBibitBawah9;
    }

    public int getKelilingBibitBawah10() {
        return kelilingBibitBawah10;
    }

    public void setKelilingBibitBawah10(int kelilingBibitBawah10) {
        this.kelilingBibitBawah10 = kelilingBibitBawah10;
    }



    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }


}
