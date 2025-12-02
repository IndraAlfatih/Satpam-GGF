package com.ggf.qcpp.b_account.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginResponse {
    @SerializedName("code")
    private String mRc;
    @SerializedName("message")
    private String mRm;

    @SerializedName("success")
    private Boolean mStatus;

    @SerializedName("data")
    private UserModel data;

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

    public UserModel getData() {
        return data;
    }

    public void setData(UserModel data) {
        this.data = data;
    }
}
