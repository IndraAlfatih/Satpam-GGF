package com.ggf.qcpp.b_account;

import com.ggf.qcpp.b_account.model.LoginResponse;
import com.ggf.qcpp.b_account.model.UserModel;

public interface IAuthView {
    void onSubmit(String username , String password);

    void onLoginSuccess(LoginResponse model);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onLoginFailed(String message);

    void onNetworkError(String cause);
}
