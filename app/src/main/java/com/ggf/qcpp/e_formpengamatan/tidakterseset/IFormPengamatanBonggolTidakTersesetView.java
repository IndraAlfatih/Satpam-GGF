package com.ggf.qcpp.e_formpengamatan.tidakterseset;

public interface IFormPengamatanBonggolTidakTersesetView {
    void onSubmit();


    void onCreateSuccess(String rm);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onNetworkError(String cause,String data);
}
