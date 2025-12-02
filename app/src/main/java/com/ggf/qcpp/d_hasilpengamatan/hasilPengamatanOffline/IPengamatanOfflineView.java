package com.ggf.qcpp.d_hasilpengamatan.hasilPengamatanOffline;

public interface IPengamatanOfflineView {

    void onCreateSuccess(String rm);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onNetworkError(String cause , String data);
}
