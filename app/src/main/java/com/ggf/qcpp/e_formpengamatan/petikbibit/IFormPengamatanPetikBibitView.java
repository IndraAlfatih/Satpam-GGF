package com.ggf.qcpp.e_formpengamatan.petikbibit;

public interface IFormPengamatanPetikBibitView {
    void onSubmit();


    void onCreateSuccess(String rm);

    void onCreateFailed(String eror);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onNetworkError(String cause,String data);
}
