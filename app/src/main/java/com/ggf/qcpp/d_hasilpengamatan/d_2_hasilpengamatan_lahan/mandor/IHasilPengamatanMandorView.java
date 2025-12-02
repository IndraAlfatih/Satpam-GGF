package com.ggf.qcpp.d_hasilpengamatan.d_2_hasilpengamatan_lahan.mandor;

import com.ggf.qcpp.d_hasilpengamatan.d_2_hasilpengamatan_lahan.mandor.model.HasilPengamatanModel;
import com.ggf.qcpp.e_formpengamatan.chopper.model.ChopperModel;
import com.ggf.qcpp.i_notify.model.PengamatanModel;

import java.util.List;

public interface IHasilPengamatanMandorView {
    void onSubmit();


    void onVerified(String guid, ChopperModel model);

    void onVerifiedSuccess(PengamatanModel model);

    void onDataReady(List<HasilPengamatanModel> data);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onNetworkError(String cause);
}
