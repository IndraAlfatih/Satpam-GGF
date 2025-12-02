package com.ggf.qcpp.e_formpengamatan.boom;

public interface IFormPengamatanBoomMixerView {
    void onSubmit();


    void onCreateSuccess(String rm);

    void onCreateFailed(String eror);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onNetworkError(String cause);
}
