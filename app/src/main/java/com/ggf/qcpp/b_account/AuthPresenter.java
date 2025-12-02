package com.ggf.qcpp.b_account;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.ggf.qcpp.App;
import com.ggf.qcpp.Prefs;
import com.ggf.qcpp.b_account.model.LoginResponse;
import com.ggf.qcpp.e_formpengamatan.chopper.IFormPengamatanChopperView;
import com.ggf.qcpp.e_formpengamatan.chopper.model.ChopperModel;
import com.ggf.qcpp.network.NetworkService;
import com.ggf.qcpp.network.RestService;
import com.ggf.qcpp.utils.CommonResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AuthPresenter {

    final IAuthView view;
    private final Retrofit restService;
    private final String TAG = "Auth Presenter";

    AuthPresenter(IAuthView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }


    boolean isLoggedIn(){
        return App.getPref().getBoolean(Prefs.PREF_IS_LOGEDIN, false);
    }

    void storeAccessToken(String token){
        App.getPref().put(Prefs.PREF_ACCESS_TOKEN, token);
    }

    void storeProfile(String data){
        App.getPref().put(Prefs.PREF_STORE_PROFILE, data);
        App.getPref().put(Prefs.PREF_IS_LOGEDIN, true);
    }

    void login(String username , String pasword) {
        JsonObject paramObject = new JsonObject();
        paramObject.addProperty("email", username);
        paramObject.addProperty("password", pasword);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
//                    .header("x-access-token", token)
//                    .header("username", nik)
                    .header("Content-Type", "application/json")
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        }).build();
//        view.showLoadingIndicator();
        restService.create(NetworkService.class).login(paramObject)
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(retrofit2.Call<LoginResponse> call, Response<LoginResponse> CommonRespon) {
                        Log.d("responenya", new Gson().toJson(CommonRespon.body()));
//                        view.hideLoadingIndicator();
                        if (CommonRespon.body().getmStatus())
                            view.onLoginSuccess(CommonRespon.body());

                        else
                            view.onLoginFailed(CommonRespon.body().getmRm());

                    }

                    @Override
                    public void onFailure(retrofit2.Call<LoginResponse> call, Throwable t) {
//                        view.hideLoadingIndicator();
                        Log.d("erornyani" , t.getLocalizedMessage());
                        Log.d("erornyani2" , t.getCause().toString());
//                        view.onCreateSuccess("Tidak ada koneksi internet, anda dapat mengirim ulang lgi nanti");
                    }
                });
    }
}
