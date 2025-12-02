package com.ggf.qcpp.e_formpengamatan.potensicrown;

public interface IFormPengamatanPotensiCrownView {
    void onSubmit();


    void onCreateSuccess(String rm);

    void onCreateFailed(String eror);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onNetworkError(String cause,String data);
}
