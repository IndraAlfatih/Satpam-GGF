package com.ggf.qcpp.i_notify.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NotifyResponse {
    @SerializedName("code")
    private String mRc;

    @SerializedName("message")
    private String mRm;

    @SerializedName("status")
    private Boolean mStatus;
    @SerializedName("data")
    private List<PengamatanModel> data;

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

    public List<PengamatanModel> getData() {
        return data;
    }

    public void setData(List<PengamatanModel> data) {
        this.data = data;
    }
}
