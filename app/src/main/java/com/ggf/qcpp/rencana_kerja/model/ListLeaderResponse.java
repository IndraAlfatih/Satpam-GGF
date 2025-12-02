package com.ggf.qcpp.rencana_kerja.model;

import com.ggf.qcpp.b_account.model.UserModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListLeaderResponse {
    @SerializedName("code")
    private String mRc;
    @SerializedName("message")
    private String mRm;

    @SerializedName("success")
    private Boolean mStatus;

    @SerializedName("data")
    private List<Users> data;

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

    public List<Users> getData() {
        return data;
    }

    public void setData(List<Users> data) {
        this.data = data;
    }


}
