package com.ggf.qcpp.e_formpengamatan.tanamsingkong;

public interface IFormPengamatanTanamSingkongView {
    void onSubmit();


    void onCreateSuccess(String rm);

    void onCreateFailed(String eror);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onNetworkError(String cause);
}
