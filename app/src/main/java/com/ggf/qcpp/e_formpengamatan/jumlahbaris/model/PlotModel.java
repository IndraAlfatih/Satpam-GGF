package com.ggf.qcpp.e_formpengamatan.jumlahbaris.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlotModel {
    @SerializedName("PLOT")
    public String PLOT;



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


}
