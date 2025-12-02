package com.ggf.qcpp.d_hasilpengamatan.d_1_hasilpengamatan_list;

import android.util.Log;

import com.ggf.qcpp.App;
import com.ggf.qcpp.Prefs;
import com.ggf.qcpp.d_hasilpengamatan.d_2_hasilpengamatan_lahan.mandor.model.ChopperResponse;
import com.ggf.qcpp.i_notify.model.NotifyResponse;
import com.ggf.qcpp.network.NetworkService;
import com.ggf.qcpp.network.RestService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HasilPengamatanPresenter {

    final IHasilPengamatanView view;
    private final Retrofit restService;
    private final String TAG = "Auth Presenter";

    HasilPengamatanPresenter(IHasilPengamatanView view) {
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

    void getPengamatan() {
        Log.d("getpengamatan" , "getpengamatan");
        view.showLoadingIndicator();
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
        restService.create(NetworkService.class).getPengamatan()
                .enqueue(new Callback<NotifyResponse>() {
                    @Override
                    public void onResponse(retrofit2.Call<NotifyResponse> call, Response<NotifyResponse> response) {
                        view.hideLoadingIndicator();
//                        if (CommonRespon.body().getSuccess())
                        view.onDataReady(response.body().getData());
//                        else
//                            view.onCreateFailed(CommonRespon.body().getmRm(), rut, CommonRespon.body().getValue());

                    }

                    @Override
                    public void onFailure(retrofit2.Call<NotifyResponse> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }

    void getPengamatanFilter(String lokasi , String kategori, String Email) {
        JsonObject paramObject = new JsonObject();
        paramObject.addProperty("kategori", kategori);
        paramObject.addProperty("lokasi", lokasi);
        paramObject.addProperty("username", Email);

        Log.d("datafilter" , new Gson().toJson(paramObject));
        view.showLoadingIndicator();
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
        restService.create(NetworkService.class).getPengamatanFilter(paramObject)
                .enqueue(new Callback<NotifyResponse>() {
                    @Override
                    public void onResponse(retrofit2.Call<NotifyResponse> call, Response<NotifyResponse> response) {
                        view.hideLoadingIndicator();
//                        if (CommonRespon.body().getSuccess())
                        view.onDataReady(response.body().getData());
//                        else
//                            view.onCreateFailed(CommonRespon.body().getmRm(), rut, CommonRespon.body().getValue());

                    }

                    @Override
                    public void onFailure(retrofit2.Call<NotifyResponse> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }
}
