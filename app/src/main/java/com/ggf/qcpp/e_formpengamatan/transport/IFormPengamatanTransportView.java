package com.ggf.qcpp.e_formpengamatan.transport;

public interface IFormPengamatanTransportView {
    void onSubmit();


    void onCreateSuccess(String rm);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onNetworkError(String cause,String data);
}
