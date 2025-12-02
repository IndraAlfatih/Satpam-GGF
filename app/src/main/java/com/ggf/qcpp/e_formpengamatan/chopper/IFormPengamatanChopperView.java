package com.ggf.qcpp.e_formpengamatan.chopper;

public interface IFormPengamatanChopperView {
    void onSubmit();


    void onCreateSuccess(String rm);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onNetworkError(String cause , String data);
}
