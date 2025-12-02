package com.ggf.qcpp.e_formpengamatan.tanam;

public interface IFormPengamatanTanamView {
    void onSubmit();


    void onCreateSuccess(String rm);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onNetworkError(String cause,String data);
}
