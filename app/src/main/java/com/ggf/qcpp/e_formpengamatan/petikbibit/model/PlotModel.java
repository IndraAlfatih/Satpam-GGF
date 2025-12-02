package com.ggf.qcpp.e_formpengamatan.petikbibit.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlotModel {
    @SerializedName("PLOT")
    public String PLOT;

    @SerializedName("LUAS_PLOT")
    public String LUAS_PLOT;

    @SerializedName("NO_JALUR")
    public String NO_JALUR;

    @SerializedName("LABEL")
    public String LABEL;

    @SerializedName("REAL")
    public String REAL;

    @SerializedName("KELAS_BIBIT")
    public String KELAS_BIBIT;

    @SerializedName("JENIS_BIBIT")
    public String JENIS_BIBIT;


    @SerializedName("SAMPLE")
    public List<SampleModel> SAMPLE;

    public String getPLOT() {
        return PLOT;
    }

    public void setPLOT(String PLOT) {
        this.PLOT = PLOT;
    }

    public List<SampleModel> getSAMPLE() {
        return SAMPLE;
    }

    public void setSAMPLE(List<SampleModel> SAMPLE) {
        this.SAMPLE = SAMPLE;
    }

    public String getLUAS_PLOT() {
        return LUAS_PLOT;
    }

    public void setLUAS_PLOT(String LUAS_PLOT) {
        this.LUAS_PLOT = LUAS_PLOT;
    }

    public String getNO_JALUR() {
        return NO_JALUR;
    }

    public void setNO_JALUR(String NO_JALUR) {
        this.NO_JALUR = NO_JALUR;
    }

    public String getLABEL() {
        return LABEL;
    }

    public void setLABEL(String LABEL) {
        this.LABEL = LABEL;
    }

    public String getREAL() {
        return REAL;
    }

    public void setREAL(String REAL) {
        this.REAL = REAL;
    }

    public String getKELAS_BIBIT() {
        return KELAS_BIBIT;
    }

    public void setKELAS_BIBIT(String KELAS_BIBIT) {
        this.KELAS_BIBIT = KELAS_BIBIT;
    }

    public String getJENIS_BIBIT() {
        return JENIS_BIBIT;
    }

    public void setJENIS_BIBIT(String JENIS_BIBIT) {
        this.JENIS_BIBIT = JENIS_BIBIT;
    }
}
