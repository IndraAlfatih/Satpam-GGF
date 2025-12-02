package com.ggf.qcpp.e_formpengamatan.dropbibit;

public interface IFormPengamatanDropBibitView {
    void onSubmit();


    void onCreateSuccess(String rm);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onNetworkError(String cause,String data);
}
