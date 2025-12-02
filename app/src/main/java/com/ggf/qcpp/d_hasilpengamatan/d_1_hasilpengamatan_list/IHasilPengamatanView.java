package com.ggf.qcpp.d_hasilpengamatan.d_1_hasilpengamatan_list;

import com.ggf.qcpp.e_formpengamatan.chopper.model.ChopperModel;
import com.ggf.qcpp.i_notify.model.PengamatanModel;

import java.util.List;

public interface IHasilPengamatanView {
    void onSubmit();

    void onVerified(String guid, ChopperModel model);

    void onVerifiedSuccess();

    void onDataReady(List<PengamatanModel> data );

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onNetworkError(String cause);
}
