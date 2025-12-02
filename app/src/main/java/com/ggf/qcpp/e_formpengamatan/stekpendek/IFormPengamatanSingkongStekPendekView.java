package com.ggf.qcpp.e_formpengamatan.stekpendek;

public interface IFormPengamatanSingkongStekPendekView {
    void onSubmit();


    void onCreateSuccess(String rm);

    void onCreateFailed(String eror);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onNetworkError(String cause);
}
