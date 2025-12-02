package com.ggf.qcpp.d_hasilpengamatan.d_2_hasilpengamatan_lahan.mandor;

import android.util.Log;

import com.ggf.qcpp.App;
import com.ggf.qcpp.Prefs;
import com.ggf.qcpp.d_hasilpengamatan.d_2_hasilpengamatan_lahan.mandor.model.HasilPengamatanResponse;
import com.ggf.qcpp.e_formpengamatan.chopper.model.ChopperModel;
import com.ggf.qcpp.i_notify.model.PengamatanModel;
import com.ggf.qcpp.network.NetworkService;
import com.ggf.qcpp.network.RestService;
import com.ggf.qcpp.utils.CommonResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HasilPengamatanMandorPresenter {

    final IHasilPengamatanMandorView view;
    private final Retrofit restService;
    private final String TAG = "Auth Presenter";

    HasilPengamatanMandorPresenter(IHasilPengamatanMandorView view) {
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

    void getPengamatan(String no_spk,String kategori, String lokasi) {

//        Map<String, String> postModel = new HashMap<>();
//        postModel.put("no_spk", no_spk);
//        postModel.put("kategori", kategori);
        JsonObject paramObject = new JsonObject();
        paramObject.addProperty("no_spk", no_spk);
        paramObject.addProperty("kategori", kategori);
        paramObject.addProperty("lokasi", lokasi);

        Log.d("postModel" , new Gson().toJson(paramObject));
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
        restService.create(NetworkService.class).getPengamatanByGuid(paramObject)
                .enqueue(new Callback<HasilPengamatanResponse>() {
                    @Override
                    public void onResponse(retrofit2.Call<HasilPengamatanResponse> call, Response<HasilPengamatanResponse> response) {
                        view.hideLoadingIndicator();
                        Log.d("responena" , response.toString());
                        Log.d("responseData" , new Gson().toJson(response.body()));
                        if (response.body().getmStatus())
                            view.onDataReady(response.body().getData());


                    }

                    @Override
                    public void onFailure(retrofit2.Call<HasilPengamatanResponse> call, Throwable t) {
                        Log.d("erornya" , new Gson().toJson(t));
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }

    void onVerified(PengamatanModel model) {
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
        restService.create(NetworkService.class).onVerified(model.getGUID() , model)
                .enqueue(new Callback<CommonResponse>() {
                    @Override
                    public void onResponse(retrofit2.Call<CommonResponse> call, Response<CommonResponse> response) {
                        Log.d("kttnya" , response.toString());
                        view.hideLoadingIndicator();
                        view.onVerifiedSuccess(model);
//                        if (CommonRespon.body().getSuccess())
//                        view.onDataReady(response.body().getData().get(0));
//                        else
//                            view.onCreateFailed(CommonRespon.body().getmRm(), rut, CommonRespon.body().getValue());

                    }

                    @Override
                    public void onFailure(retrofit2.Call<CommonResponse> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }
}
