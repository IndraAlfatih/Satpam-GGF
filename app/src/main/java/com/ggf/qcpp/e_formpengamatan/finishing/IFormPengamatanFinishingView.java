package com.ggf.qcpp.e_formpengamatan.finishing;

public interface IFormPengamatanFinishingView {
    void onSubmit();


    void onCreateSuccess(String rm);

    void onCreateFailed(String eror);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onNetworkError(String cause ,String data);
}
