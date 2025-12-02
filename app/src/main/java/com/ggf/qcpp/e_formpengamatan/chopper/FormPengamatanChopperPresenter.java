package com.ggf.qcpp.e_formpengamatan.chopper;

import static com.ggf.qcpp.utils.Utils.storePengamatan;

import android.util.Log;

import com.ggf.qcpp.App;
import com.ggf.qcpp.Prefs;
import com.ggf.qcpp.e_formpengamatan.chopper.model.ChopperModel;
import com.ggf.qcpp.network.NetworkService;
import com.ggf.qcpp.network.RestService;
import com.ggf.qcpp.utils.CommonResponse;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FormPengamatanChopperPresenter {

    final IFormPengamatanChopperView view;
    private final Retrofit restService;
    private final String TAG = "Auth Presenter";

    FormPengamatanChopperPresenter(IFormPengamatanChopperView view) {
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

    void createPengamatan(ChopperModel model) {
        System.out.println(new Gson().toJson(model));
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
        view.showLoadingIndicator();
        restService.create(NetworkService.class).pengamatan(model)
                .enqueue(new Callback<CommonResponse>() {
                    @Override
                    public void onResponse(retrofit2.Call<CommonResponse> call, Response<CommonResponse> CommonRespon) {
                        Log.d("responenya", CommonRespon.toString());
                        view.hideLoadingIndicator();
//                        if (CommonRespon.body().getSuccess())
                            view.onCreateSuccess(CommonRespon.body().getRm());
//                        else
//                            view.onCreateFailed(CommonRespon.body().getmRm(), rut, CommonRespon.body().getValue());

                    }

                    @Override
                    public void onFailure(retrofit2.Call<CommonResponse> call, Throwable t) {
                        view.onNetworkError(t.getLocalizedMessage() , new Gson().toJson(model));
//                        storePengamatan(new Gson().toJson(model));
                        view.hideLoadingIndicator();
//                        Log.d("erornyani" , t.getLocalizedMessage());
//                        Log.d("erornyani2" , t.getCause().toString());
//                        view.onCreateSuccess("Tidak ada koneksi internet, anda dapat mengirim ulang lgi nanti");
                    }
                });
    }
}
