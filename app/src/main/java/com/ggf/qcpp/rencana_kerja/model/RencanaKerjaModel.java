package com.ggf.qcpp.rencana_kerja.model;

import com.google.gson.annotations.SerializedName;

public class RencanaKerjaModel {

    @SerializedName("id")
    public String id;

    @SerializedName("no_spk")
    public String no_spk;

    @SerializedName("username")
    public String username;

    @SerializedName("no_line")
    public String no_line;

    @SerializedName("kategori")
    public String kategori;

    @SerializedName("status_pengamatan")
    public String status_pengamatan;

    @SerializedName("lokasi")
    public String lokasi;

    @SerializedName("pg")
    public String pg;

    @SerializedName("luas_netto")
    public String luas_netto;

    @SerializedName("no_unit_implement")
    public String no_unit_implement;

    @SerializedName("wil")
    public String wil;

    @SerializedName("created_at")
    public String created_at;

    @SerializedName("updated_at")
    public String updated_at;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNo_spk() {
        return no_spk;
    }

    public void setNo_spk(String no_spk) {
        this.no_spk = no_spk;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNo_line() {
        return no_line;
    }

    public void setNo_line(String no_line) {
        this.no_line = no_line;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getStatus_pengamatan() {
        return status_pengamatan;
    }

    public void setStatus_pengamatan(String status_pengamatan) {
        this.status_pengamatan = status_pengamatan;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getPg() {
        return pg;
    }

    public void setPg(String pg) {
        this.pg = pg;
    }

    public String getLuas_netto() {
        return luas_netto;
    }

    public void setLuas_netto(String luas_netto) {
        this.luas_netto = luas_netto;
    }

    public String getNo_unit_implement() {
        return no_unit_implement;
    }

    public void setNo_unit_implement(String no_unit_implement) {
        this.no_unit_implement = no_unit_implement;
    }

    public String getWil() {
        return wil;
    }

    public void setWil(String wil) {
        this.wil = wil;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
