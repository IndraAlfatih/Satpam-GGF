package com.ggf.qcpp.i_notify.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PengamatanModel implements Serializable {

    @SerializedName("id")
    public String GUID;

    @SerializedName("created_at")
    public String CREATED_AT;

    @SerializedName("no_spk")
    public String NO_SPK;

    @SerializedName("username")
    public String USERNAME;


    @SerializedName("name")
    public String name;

    @SerializedName("no_line")
    public String NO_LINE;

    @SerializedName("kategori")
    public String KATEGORI;
    @SerializedName("status_pengamatan")
    public String STATUS_PENGAMATAN;
    @SerializedName("lokasi")
    public String LOKASI;

    @SerializedName("pg")
    public String PG;
    @SerializedName("luas_netto")
    public String LUAS_NETTO;

    @SerializedName("no_unit_implement")
    public String NO_UNIT_IMPLEMENT;

    @SerializedName("verify_mandor")
    public int verify_mandor;

    @SerializedName("verify_kasi")
    public int verify_kasi;

    @SerializedName("verify_kabag")
    public int verify_kabag;
    @SerializedName("wil")
    public String WILAYAH;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVerify_mandor() {
        return verify_mandor;
    }

    public void setVerify_mandor(int verify_mandor) {
        this.verify_mandor = verify_mandor;
    }

    public int getVerify_kasi() {
        return verify_kasi;
    }

    public void setVerify_kasi(int verify_kasi) {
        this.verify_kasi = verify_kasi;
    }

    public int getVerify_kabag() {
        return verify_kabag;
    }

    public void setVerify_kabag(int verify_kabag) {
        this.verify_kabag = verify_kabag;
    }

    public String getGUID() {
        return GUID;
    }

    public void setGUID(String GUID) {
        this.GUID = GUID;
    }

    public String getCREATED_AT() {
        return CREATED_AT;
    }

    public void setCREATED_AT(String CREATED_AT) {
        this.CREATED_AT = CREATED_AT;
    }

    public String getNO_SPK() {
        return NO_SPK;
    }

    public void setNO_SPK(String NO_SPK) {
        this.NO_SPK = NO_SPK;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getNO_LINE() {
        return NO_LINE;
    }

    public void setNO_LINE(String NO_LINE) {
        this.NO_LINE = NO_LINE;
    }

    public String getKATEGORI() {
        return KATEGORI;
    }

    public void setKATEGORI(String KATEGORI) {
        this.KATEGORI = KATEGORI;
    }

    public String getSTATUS_PENGAMATAN() {
        return STATUS_PENGAMATAN;
    }

    public void setSTATUS_PENGAMATAN(String STATUS_PENGAMATAN) {
        this.STATUS_PENGAMATAN = STATUS_PENGAMATAN;
    }

    public String getLOKASI() {
        return LOKASI;
    }

    public void setLOKASI(String LOKASI) {
        this.LOKASI = LOKASI;
    }

    public String getPG() {
        return PG;
    }

    public void setPG(String PG) {
        this.PG = PG;
    }

    public String getLUAS_NETTO() {
        return LUAS_NETTO;
    }

    public void setLUAS_NETTO(String LUAS_NETTO) {
        this.LUAS_NETTO = LUAS_NETTO;
    }

    public String getNO_UNIT_IMPLEMENT() {
        return NO_UNIT_IMPLEMENT;
    }

    public void setNO_UNIT_IMPLEMENT(String NO_UNIT_IMPLEMENT) {
        this.NO_UNIT_IMPLEMENT = NO_UNIT_IMPLEMENT;
    }

    public String getWILAYAH() {
        return WILAYAH;
    }

    public void setWILAYAH(String WILAYAH) {
        this.WILAYAH = WILAYAH;
    }
}
