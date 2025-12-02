package com.ggf.qcpp.rencana_kerja;

import com.ggf.qcpp.b_account.model.UserModel;
import com.ggf.qcpp.rencana_kerja.model.RencanaKerjaModel;
import com.ggf.qcpp.rencana_kerja.model.Users;
import com.ggf.qcpp.utils.CommonResponse;

import java.util.List;

public interface IRencanaKerjaView {


    void onCreateSuccess(CommonResponse model);

    void onDataReady(List<RencanaKerjaModel> model);

    void onListMandor(List<Users> model);

    void onListKasie(List<Users> model);

    void onListKabag(List<Users> model);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onNetworkError(String cause);
}
