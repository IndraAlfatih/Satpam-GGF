package com.ggf.qcpp.rencana_kerja.model;

import com.google.gson.annotations.SerializedName;

public class SpkModel {

    @SerializedName("id")
    private int id;

    @SerializedName("no_spk")
    private String no_spk;

    @SerializedName("kit")
    private String kit;

    @SerializedName("tgl_spk")
    private String tglSpk;

    @SerializedName("tgl_real")
    private String tglReal;

    @SerializedName("shift")
    private int shift;

    @SerializedName("plksn_id")
    private String pelaksanaId;

    @SerializedName("plksn_nama")
    private String pelaksanaNama;

    @SerializedName("plksn_role")
    private String pelaksanaRole;

    @SerializedName("mandor_id")
    private String mandorId;

    @SerializedName("mandor_nama")
    private String mandorNama;

    @SerializedName("pic_nama")
    private String picNama;

    @SerializedName("pic_role")
    private String picRole;

    @SerializedName("kabag_id")
    private String kabagId;

    @SerializedName("kabag_nama")
    private String kabagNama;

    @SerializedName("kasie_id")
    private String kasiId;

    @SerializedName("kasie_nama")
    private String kasieNama;

    @SerializedName("bagian_id")
    private String bagianId;

    @SerializedName("bagian_nama")
    private String bagianNama;

    @SerializedName("resv_no")
    private String reservasiNo;

    @SerializedName("catatan")
    private String catatan;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;

    // ✅ Constructor kosong (dibutuhkan untuk deserialisasi)
    public SpkModel() {
    }

    // ✅ Getter dan Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNo_spk() {
        return no_spk;
    }

    public void setNo_spk(String no_spk) {
        this.no_spk = no_spk;
    }

    public String getKit() {
        return kit;
    }

    public void setKit(String kit) {
        this.kit = kit;
    }

    public String getTglSpk() {
        return tglSpk;
    }

    public void setTglSpk(String tglSpk) {
        this.tglSpk = tglSpk;
    }

    public String getTglReal() {
        return tglReal;
    }

    public void setTglReal(String tglReal) {
        this.tglReal = tglReal;
    }

    public int getShift() {
        return shift;
    }

    public void setShift(int shift) {
        this.shift = shift;
    }

    public String getPelaksanaId() {
        return pelaksanaId;
    }

    public void setPelaksanaId(String pelaksanaId) {
        this.pelaksanaId = pelaksanaId;
    }

    public String getPelaksanaNama() {
        return pelaksanaNama;
    }

    public void setPelaksanaNama(String pelaksanaNama) {
        this.pelaksanaNama = pelaksanaNama;
    }

    public String getPelaksanaRole() {
        return pelaksanaRole;
    }

    public void setPelaksanaRole(String pelaksanaRole) {
        this.pelaksanaRole = pelaksanaRole;
    }

    public String getMandorId() {
        return mandorId;
    }

    public void setMandorId(String mandorId) {
        this.mandorId = mandorId;
    }

    public String getMandorNama() {
        return mandorNama;
    }

    public void setMandorNama(String mandorNama) {
        this.mandorNama = mandorNama;
    }

    public String getPicNama() {
        return picNama;
    }

    public void setPicNama(String picNama) {
        this.picNama = picNama;
    }

    public String getPicRole() {
        return picRole;
    }

    public void setPicRole(String picRole) {
        this.picRole = picRole;
    }

    public String getKabagId() {
        return kabagId;
    }

    public void setKabagId(String kabagId) {
        this.kabagId = kabagId;
    }

    public String getKabagNama() {
        return kabagNama;
    }

    public void setKabagNama(String kabagNama) {
        this.kabagNama = kabagNama;
    }

    public String getBagianId() {
        return bagianId;
    }

    public void setBagianId(String bagianId) {
        this.bagianId = bagianId;
    }

    public String getBagianNama() {
        return bagianNama;
    }

    public void setBagianNama(String bagianNama) {
        this.bagianNama = bagianNama;
    }

    public String getReservasiNo() {
        return reservasiNo;
    }

    public void setReservasiNo(String reservasiNo) {
        this.reservasiNo = reservasiNo;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getKasiId() {
        return kasiId;
    }

    public void setKasiId(String kasiId) {
        this.kasiId = kasiId;
    }

    public String getKasieNama() {
        return kasieNama;
    }

    public void setKasieNama(String kasieNama) {
        this.kasieNama = kasieNama;
    }
}

