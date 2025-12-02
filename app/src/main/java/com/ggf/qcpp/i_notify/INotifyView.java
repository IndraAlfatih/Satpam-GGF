package com.ggf.qcpp.i_notify;

import android.app.Notification;

import com.ggf.qcpp.e_formpengamatan.chopper.model.ChopperModel;
import com.ggf.qcpp.i_notify.model.NotifyResponse;
import com.ggf.qcpp.i_notify.model.PengamatanModel;
import com.ggf.qcpp.utils.CommonResponse;

import java.util.List;

public interface INotifyView {
    void onSubmit();

    void onVerified(String guid, ChopperModel model);

    void onVerifiedSuccess();

    void onDataReady(List<PengamatanModel> data);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onNetworkError(String cause);

    void onDeleteSuccess(CommonResponse response);
}
