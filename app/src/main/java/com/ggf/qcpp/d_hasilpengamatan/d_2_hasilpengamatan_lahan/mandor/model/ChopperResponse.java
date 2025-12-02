package com.ggf.qcpp.d_hasilpengamatan.d_2_hasilpengamatan_lahan.mandor.model;

import android.service.chooser.ChooserAction;

import com.ggf.qcpp.e_formpengamatan.chopper.model.ChopperModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChopperResponse {
    @SerializedName("code")
    private String mRc;

    @SerializedName("message")
    private String mRm;

    @SerializedName("status")
    private Boolean mStatus;

    @SerializedName("data")
    private List<ChopperModel> data;

    public String getmRc() {
        return mRc;
    }

    public void setmRc(String mRc) {
        this.mRc = mRc;
    }

    public String getmRm() {
        return mRm;
    }

    public void setmRm(String mRm) {
        this.mRm = mRm;
    }

    public Boolean getmStatus() {
        return mStatus;
    }

    public void setmStatus(Boolean mStatus) {
        this.mStatus = mStatus;
    }

    public List<ChopperModel> getData() {
        return data;
    }

    public void setData(List<ChopperModel> data) {
        this.data = data;
    }
}
