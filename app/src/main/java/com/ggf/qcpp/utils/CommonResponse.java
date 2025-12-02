package com.ggf.qcpp.utils;

import com.google.gson.annotations.SerializedName;

public class CommonResponse {
    @SerializedName("code")
    private String mRc;
    @SerializedName("message")
    private String mRm;

    @SerializedName("status")
    private Boolean mStatus;

    public String getRc() {return mRc; }

    public void setRc(String rc) { mRc = rc; }


    public String getRm() { return mRm; }

    public void setRm(String rm) { mRm = rm; }

    public Boolean getSuccess() { return mStatus; }

    public void  setSuccess(Boolean success) { mStatus = success; }
}
