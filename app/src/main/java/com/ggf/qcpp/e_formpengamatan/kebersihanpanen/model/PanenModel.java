package com.ggf.qcpp.e_formpengamatan.kebersihanpanen.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PanenModel implements Serializable {

    @SerializedName("GUID")
    public String GUID;
    @SerializedName("CREATED_AT")
    public String CREATED_AT;

    @SerializedName("no_spk")
    public String NO_SPK;

    @SerializedName("username")
    public String USERNAME;

    @SerializedName("no_line")
    public String NO_LINE;

    @SerializedName("kategori")
    public String KATEGORI;
    @SerializedName("update_peta")
    public String update_peta;

    @SerializedName("tanggal_panen")
    public String tanggal_panen;

    @SerializedName("lokasi")
    public String LOKASI;

    @SerializedName("pg")
    public String PG;
    @SerializedName("luas_netto")
    public String LUAS_NETTO;
    @SerializedName("NO_UNIT_IMPLEMENT")
    public String NO_UNIT_IMPLEMENT;
    @SerializedName("wil")
    public String WILAYAH;

    @SerializedName("VERIFIED")
    public verified VERIFIED;
    @SerializedName("status_pengamatan")
    public String STATUS_PENGAMATAN;
    @SerializedName("reworking")
    public String reworking;





    @SerializedName("DATA")
    public List<PlotModel> DATA;

    public String getUpdate_peta() {
        return update_peta;
    }

    public void setUpdate_peta(String update_peta) {
        this.update_peta = update_peta;
    }

    public String getTanggal_panen() {
        return tanggal_panen;
    }

    public void setTanggal_panen(String tanggal_panen) {
        this.tanggal_panen = tanggal_panen;
    }

    public String getReworking() {
        return reworking;
    }

    public void setReworking(String reworking) {
        this.reworking = reworking;
    }

    public String getSTATUS_PENGAMATAN() {
        return STATUS_PENGAMATAN;
    }

    public void setSTATUS_PENGAMATAN(String STATUS_PENGAMATAN) {
        this.STATUS_PENGAMATAN = STATUS_PENGAMATAN;
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

    public verified getVERIFIED() {
        return VERIFIED;
    }

    public void setVERIFIED(verified VERIFIED) {
        this.VERIFIED = VERIFIED;
    }

    public List<PlotModel> getDATA() {
        return DATA;
    }

    public void setDATA(List<PlotModel> DATA) {
        this.DATA = DATA;
    }

    public  class verified implements Serializable{
        @SerializedName("VERIFIED_MANDOR")
        public verified_by VERIFIED_MANDOR;

        @SerializedName("VERIFIED_KASI")
        public verified_by VERIFIED_KASI;

        @SerializedName("VERIFIED_KABAG")
        public verified_by VERIFIED_KABAG;

        public verified_by getVERIFIED_MANDOR() {
            return VERIFIED_MANDOR;
        }

        public void setVERIFIED_MANDOR(verified_by VERIFIED_MANDOR) {
            this.VERIFIED_MANDOR = VERIFIED_MANDOR;
        }

        public verified_by getVERIFIED_KASI() {
            return VERIFIED_KASI;
        }

        public void setVERIFIED_KASI(verified_by VERIFIED_KASI) {
            this.VERIFIED_KASI = VERIFIED_KASI;
        }

        public verified_by getVERIFIED_KABAG() {
            return VERIFIED_KABAG;
        }

        public void setVERIFIED_KABAG(verified_by VERIFIED_KABAG) {
            this.VERIFIED_KABAG = VERIFIED_KABAG;
        }



        public class verified_by implements Serializable{
            @SerializedName("nama")
            public String nama;

            @SerializedName("status")
            public boolean status;


            public String getNama() {
                return nama;
            }

            public void setNama(String nama) {
                this.nama = nama;
            }

            public boolean isStatus() {
                return status;
            }

            public void setStatus(boolean status) {
                this.status = status;
            }
        }

    }


}
