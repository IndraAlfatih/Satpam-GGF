package com.ggf.qcpp.rencana_kerja;

import android.util.Log;

import com.ggf.qcpp.App;
import com.ggf.qcpp.Prefs;
import com.ggf.qcpp.R;
import com.ggf.qcpp.b_account.model.LoginResponse;
import com.ggf.qcpp.b_account.model.UserModel;
import com.ggf.qcpp.e_formpengamatan.bajak.IFormPengamatanBajakView;
import com.ggf.qcpp.e_formpengamatan.bajak.model.BajakModel;
import com.ggf.qcpp.network.NetworkService;
import com.ggf.qcpp.network.RestService;
import com.ggf.qcpp.rencana_kerja.model.ListLeaderResponse;
import com.ggf.qcpp.rencana_kerja.model.RencanaKerjaModel;
import com.ggf.qcpp.rencana_kerja.model.SpkModel;
import com.ggf.qcpp.utils.CommonResponse;
import com.google.gson.Gson;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RencanaKerjaPresenter {

    final IRencanaKerjaView view;
    private final Retrofit restService;
    private final String TAG = "Auth Presenter";

    RencanaKerjaPresenter(IRencanaKerjaView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }


    boolean isLoggedIn() {
        return App.getPref().getBoolean(Prefs.PREF_IS_LOGEDIN, false);
    }

    void storeAccessToken(String token) {
        App.getPref().put(Prefs.PREF_ACCESS_TOKEN, token);
    }

    void storeProfile(String data) {
        App.getPref().put(Prefs.PREF_STORE_PROFILE, data);
        App.getPref().put(Prefs.PREF_IS_LOGEDIN, true);
    }

    void getRencanaKerja() {
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
        restService.create(NetworkService.class).getRencanaKerja()
                .enqueue(new Callback<List<RencanaKerjaModel>>() {
                    @Override
                    public void onResponse(retrofit2.Call<List<RencanaKerjaModel>> call, Response<List<RencanaKerjaModel>> CommonRespon) {
//                        view.hideLoadingIndicator();
//                        if (CommonRespon.body().getSuccess())
                        Log.d("dataPresenter", CommonRespon.toString());
                        view.onDataReady(CommonRespon.body());
//                        else
//                            view.onCreateFailed(CommonRespon.body().getmRm(), rut, CommonRespon.body().getValue());

                    }

                    @Override
                    public void onFailure(retrofit2.Call<List<RencanaKerjaModel>> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }

    void getListLeader(final String id_role) {
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
        restService.create(NetworkService.class).getListLeader(Integer.parseInt(id_role))
                .enqueue(new Callback<ListLeaderResponse>() {
                    @Override
                    public void onResponse(retrofit2.Call<ListLeaderResponse> call, Response<ListLeaderResponse> CommonRespon) {
                        view.hideLoadingIndicator();
//                        if (CommonRespon.body().getSuccess())
                        if (id_role .equals(App.getApplication().getString(R.string.role_kabag)) )
                            view.onListKabag(CommonRespon.body().getData());
                        else  if (id_role .equals(App.getApplication().getString(R.string.role_kasie)) )
                            view.onListKasie(CommonRespon.body().getData());
                        else  if (id_role .equals(App.getApplication().getString(R.string.role_mandor)) )
                            view.onListMandor(CommonRespon.body().getData());
//
                    }

                    @Override
                    public void onFailure(retrofit2.Call<ListLeaderResponse> call, Throwable t) {
                        Log.d("error dari "+id_role ,t.getLocalizedMessage());
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }

    void createSPK(SpkModel model) {
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
        restService.create(NetworkService.class).createSpk(model)
                .enqueue(new Callback<CommonResponse>() {
                    @Override
                    public void onResponse(retrofit2.Call<CommonResponse> call, Response<CommonResponse> CommonRespon) {

                        view.hideLoadingIndicator();
                        view.onCreateSuccess(CommonRespon.body());
                    }

                    @Override
                    public void onFailure(retrofit2.Call<CommonResponse>call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }
}
