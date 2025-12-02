package com.ggf.qcpp.e_formpengamatan.kebersihanbonggol;

public interface IFormPengamatanKebersihanBonggolView {
    void onSubmit();


    void onCreateSuccess(String rm);

    void onCreateFailed(String eror);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onNetworkError(String cause,String Data);
}
