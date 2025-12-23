package com.ggf.qcpp.d_hasilpengamatan.hasilPengamatanOffline;

import android.util.Log;

import com.ggf.qcpp.App;
import com.ggf.qcpp.Prefs;
import com.ggf.qcpp.d_hasilpengamatan.hasilPengamatanOffline.model.OfflineModel;
import com.ggf.qcpp.e_formpengamatan.adukanbahan.model.AdukanBahanDilokasiModel;
import com.ggf.qcpp.e_formpengamatan.bajak.model.BajakModel;
import com.ggf.qcpp.e_formpengamatan.z_satpam.model.SatpamModel;
import com.ggf.qcpp.e_formpengamatan.chopper.model.ChopperModel;
import com.ggf.qcpp.e_formpengamatan.dropbibit.IFormPengamatanDropBibitView;
import com.ggf.qcpp.e_formpengamatan.dropbibit.model.DropBibitModel;
import com.ggf.qcpp.e_formpengamatan.finishing.model.FinishingModel;
import com.ggf.qcpp.e_formpengamatan.gudangmixer.model.GudangMixerModel;
import com.ggf.qcpp.e_formpengamatan.jumlahbaris.model.JumlahBarisModel;
import com.ggf.qcpp.e_formpengamatan.kebersihanbonggol.model.KebersihanBonggolModel;
import com.ggf.qcpp.e_formpengamatan.kebersihanpanen.model.PanenModel;
import com.ggf.qcpp.e_formpengamatan.petikbibit.model.PetikBibitModel;
import com.ggf.qcpp.e_formpengamatan.phtanah.model.PhtanahModel;
import com.ggf.qcpp.e_formpengamatan.pooldipping.model.PoolDippingModel;
import com.ggf.qcpp.e_formpengamatan.potensicrown.model.PotensiCrownModel;
import com.ggf.qcpp.e_formpengamatan.ridger.model.RidgerModel;
import com.ggf.qcpp.e_formpengamatan.stekpanjang.model.SingkongStekPanjangModel;
import com.ggf.qcpp.e_formpengamatan.subsoiler.model.SubsoilerModel;
import com.ggf.qcpp.e_formpengamatan.tanam.model.TanamModel;
import com.ggf.qcpp.e_formpengamatan.tidakterseset.model.BonggolTidakTersesetModel;
import com.ggf.qcpp.e_formpengamatan.transport.model.TransportModel;
import com.ggf.qcpp.network.NetworkService;
import com.ggf.qcpp.network.RestService;
import com.ggf.qcpp.utils.CommonResponse;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PengamatanOfflinePresenter {

    final IPengamatanOfflineView view;
    private final Retrofit restService;
    private final String TAG = "Auth Presenter";

    PengamatanOfflinePresenter(IPengamatanOfflineView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }


    void Chopper(ChopperModel model) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
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
                        view.hideLoadingIndicator();
//                        Log.d("responserver", new Gson().toJson(CommonRespon.body()));
                        view.onCreateSuccess(CommonRespon.body().getRm());
//
                    }

                    @Override
                    public void onFailure(retrofit2.Call<CommonResponse> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError("Anda tidak mempunyai akses internet", new Gson().toJson(model));
                    }
                });
    }

    void Bajak(BajakModel model) {
        System.out.println(model);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .header("Content-Type", "application/json")
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        }).build();
        view.showLoadingIndicator();
        restService.create(NetworkService.class).pengamatanBajak(model)
                .enqueue(new Callback<CommonResponse>() {
                    @Override
                    public void onResponse(retrofit2.Call<CommonResponse> call, Response<CommonResponse> CommonRespon) {
                        view.hideLoadingIndicator();
                        view.onCreateSuccess(CommonRespon.body().getRm());
//
                    }

                    @Override
                    public void onFailure(retrofit2.Call<CommonResponse> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError("Anda tidak mempunyai akses internet", new Gson().toJson(model));
                    }
                });
    }

    void Satpam(SatpamModel model) {
        System.out.println(model);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .header("Content-Type", "application/json")
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        }).build();
        view.showLoadingIndicator();
        restService.create(NetworkService.class).pengamatanSatpam(model)
                .enqueue(new Callback<CommonResponse>() {
                    @Override
                    public void onResponse(retrofit2.Call<CommonResponse> call, Response<CommonResponse> CommonRespon) {
                        view.hideLoadingIndicator();
                        view.onCreateSuccess(CommonRespon.body().getRm());
//
                    }

                    @Override
                    public void onFailure(retrofit2.Call<CommonResponse> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError("Anda tidak mempunyai akses internet", new Gson().toJson(model));
                    }
                });
    }

    void Finishing(FinishingModel model) {
        System.out.println(model);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .header("Content-Type", "application/json")
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        }).build();
        view.showLoadingIndicator();
        restService.create(NetworkService.class).pengamatanFinishing(model)
                .enqueue(new Callback<CommonResponse>() {
                    @Override
                    public void onResponse(retrofit2.Call<CommonResponse> call, Response<CommonResponse> CommonRespon) {
                        view.hideLoadingIndicator();
                        view.onCreateSuccess(CommonRespon.body().getRm());
//
                    }

                    @Override
                    public void onFailure(retrofit2.Call<CommonResponse> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError("Anda tidak mempunyai akses internet", new Gson().toJson(model));
                    }
                });
    }

    void Subsoiler(SubsoilerModel model) {
        System.out.println(model);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .header("Content-Type", "application/json")
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        }).build();
        view.showLoadingIndicator();
        restService.create(NetworkService.class).pengamatanSubsoil(model)
                .enqueue(new Callback<CommonResponse>() {
                    @Override
                    public void onResponse(retrofit2.Call<CommonResponse> call, Response<CommonResponse> CommonRespon) {
                        view.hideLoadingIndicator();
                        view.onCreateSuccess(CommonRespon.body().getRm());
//
                    }

                    @Override
                    public void onFailure(retrofit2.Call<CommonResponse> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError("Anda tidak mempunyai akses internet", new Gson().toJson(model));
                    }
                });
    }

    void PotensiCrown(PotensiCrownModel model) {
        System.out.println(model);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .header("Content-Type", "application/json")
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        }).build();
        view.showLoadingIndicator();
        restService.create(NetworkService.class).pengamatanPotensiCrown(model)
                .enqueue(new Callback<CommonResponse>() {
                    @Override
                    public void onResponse(retrofit2.Call<CommonResponse> call, Response<CommonResponse> CommonRespon) {
                        view.hideLoadingIndicator();
                        view.onCreateSuccess(CommonRespon.body().getRm());
//
                    }

                    @Override
                    public void onFailure(retrofit2.Call<CommonResponse> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError("Anda tidak mempunyai akses internet", new Gson().toJson(model));
                    }
                });
    }

    void Ridger(RidgerModel model) {
        System.out.println(model);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .header("Content-Type", "application/json")
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        }).build();
        view.showLoadingIndicator();
        restService.create(NetworkService.class).pengamatanRidger(model)
                .enqueue(new Callback<CommonResponse>() {
                    @Override
                    public void onResponse(retrofit2.Call<CommonResponse> call, Response<CommonResponse> CommonRespon) {
                        view.hideLoadingIndicator();
                        view.onCreateSuccess(CommonRespon.body().getRm());
//
                    }

                    @Override
                    public void onFailure(retrofit2.Call<CommonResponse> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError("Anda tidak mempunyai akses internet", new Gson().toJson(model));
                    }
                });
    }

    void KebersihanBonggol(KebersihanBonggolModel model) {
        System.out.println(model);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .header("Content-Type", "application/json")
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        }).build();
        view.showLoadingIndicator();
        restService.create(NetworkService.class).pengamatanKebersihanBonggol(model)
                .enqueue(new Callback<CommonResponse>() {
                    @Override
                    public void onResponse(retrofit2.Call<CommonResponse> call, Response<CommonResponse> CommonRespon) {
                        view.hideLoadingIndicator();
                        view.onCreateSuccess(CommonRespon.body().getRm());
//
                    }

                    @Override
                    public void onFailure(retrofit2.Call<CommonResponse> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError("Anda tidak mempunyai akses internet", new Gson().toJson(model));
                    }
                });
    }

    void PhTanah(PhtanahModel model) {
        System.out.println(model);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .header("Content-Type", "application/json")
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        }).build();
        view.showLoadingIndicator();
        restService.create(NetworkService.class).pengamatanPhTanah(model)
                .enqueue(new Callback<CommonResponse>() {
                    @Override
                    public void onResponse(retrofit2.Call<CommonResponse> call, Response<CommonResponse> CommonRespon) {
                        view.hideLoadingIndicator();
                        view.onCreateSuccess(CommonRespon.body().getRm());
//
                    }

                    @Override
                    public void onFailure(retrofit2.Call<CommonResponse> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError("Anda tidak mempunyai akses internet", new Gson().toJson(model));
                    }
                });
    }

    void PetikBibit(PetikBibitModel model) {
        System.out.println(model);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .header("Content-Type", "application/json")
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        }).build();
        view.showLoadingIndicator();
        restService.create(NetworkService.class).pengamatanPetikBibit(model)
                .enqueue(new Callback<CommonResponse>() {
                    @Override
                    public void onResponse(retrofit2.Call<CommonResponse> call, Response<CommonResponse> CommonRespon) {
                        view.hideLoadingIndicator();
                        view.onCreateSuccess(CommonRespon.body().getRm());
//
                    }

                    @Override
                    public void onFailure(retrofit2.Call<CommonResponse> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError("Anda tidak mempunyai akses internet", new Gson().toJson(model));
                    }
                });
    }

    void KebersihanTransport(TransportModel model) {
        System.out.println(model);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .header("Content-Type", "application/json")
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        }).build();
        view.showLoadingIndicator();
        restService.create(NetworkService.class).pengamatanTransport(model)
                .enqueue(new Callback<CommonResponse>() {
                    @Override
                    public void onResponse(retrofit2.Call<CommonResponse> call, Response<CommonResponse> CommonRespon) {
                        view.hideLoadingIndicator();
                        view.onCreateSuccess(CommonRespon.body().getRm());
//
                    }

                    @Override
                    public void onFailure(retrofit2.Call<CommonResponse> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError("Anda tidak mempunyai akses internet", new Gson().toJson(model));
                    }
                });
    }

    void mixer(GudangMixerModel model) {
//        System.out.println(model);
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
        restService.create(NetworkService.class).pengamatanGudangMixer(model)
                .enqueue(new Callback<CommonResponse>() {
                    @Override
                    public void onResponse(retrofit2.Call<CommonResponse> call, Response<CommonResponse> CommonRespon) {
                        view.hideLoadingIndicator();

//                        if (CommonRespon.body().getSuccess())
                        view.onCreateSuccess(CommonRespon.body().getRm());
//                        else
//                            view.onCreateFailed(CommonRespon.body().getmRm(), rut, CommonRespon.body().getValue());

                    }

                    @Override
                    public void onFailure(retrofit2.Call<CommonResponse> call, Throwable t) {

                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage(), new Gson().toJson(model));
                    }
                });
    }

    void adukan(AdukanBahanDilokasiModel model) {
//        System.out.println(model);
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
        restService.create(NetworkService.class).pengamatanAdukanBahanDilokasi(model)
                .enqueue(new Callback<CommonResponse>() {
                    @Override
                    public void onResponse(retrofit2.Call<CommonResponse> call, Response<CommonResponse> CommonRespon) {
                        view.hideLoadingIndicator();

//                        if (CommonRespon.body().getSuccess())
                        view.onCreateSuccess(CommonRespon.body().getRm());
//                        else
//                            view.onCreateFailed(CommonRespon.body().getmRm(), rut, CommonRespon.body().getValue());

                    }

                    @Override
                    public void onFailure(retrofit2.Call<CommonResponse> call, Throwable t) {

                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage(),new Gson().toJson(model));
                    }
                });
    }

    void panen(PanenModel model) {
//        System.out.println(model);
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
        restService.create(NetworkService.class).pengamatanPanen(model)
                .enqueue(new Callback<CommonResponse>() {
                    @Override
                    public void onResponse(retrofit2.Call<CommonResponse> call, Response<CommonResponse> CommonRespon) {
                        view.hideLoadingIndicator();

//                        if (CommonRespon.body().getSuccess())
                        view.onCreateSuccess(CommonRespon.body().getRm());
//                        else
//                            view.onCreateFailed(CommonRespon.body().getmRm(), rut, CommonRespon.body().getValue());

                    }

                    @Override
                    public void onFailure(retrofit2.Call<CommonResponse> call, Throwable t) {

                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage(),new Gson().toJson(model));
                    }
                });
    }

    void bonggol_tidak_terseset(BonggolTidakTersesetModel model) {
//        System.out.println(model);
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
        restService.create(NetworkService.class).pengamatanTidakTerseset(model)
                .enqueue(new Callback<CommonResponse>() {
                    @Override
                    public void onResponse(retrofit2.Call<CommonResponse> call, Response<CommonResponse> CommonRespon) {
                        view.hideLoadingIndicator();

//                        if (CommonRespon.body().getSuccess())
                        view.onCreateSuccess(CommonRespon.body().getRm());
//                        else
//                            view.onCreateFailed(CommonRespon.body().getmRm(), rut, CommonRespon.body().getValue());

                    }

                    @Override
                    public void onFailure(retrofit2.Call<CommonResponse> call, Throwable t) {

                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage(),new Gson().toJson(model));
                    }
                });
    }

    void PoolDipping(PoolDippingModel model) {
        System.out.println(model);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .header("Content-Type", "application/json")
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        }).build();
        view.showLoadingIndicator();
        restService.create(NetworkService.class).pengamatanPoolDipping(model)
                .enqueue(new Callback<CommonResponse>() {
                    @Override
                    public void onResponse(retrofit2.Call<CommonResponse> call, Response<CommonResponse> CommonRespon) {
                        view.hideLoadingIndicator();
                        view.onCreateSuccess(CommonRespon.body().getRm());
//
                    }

                    @Override
                    public void onFailure(retrofit2.Call<CommonResponse> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError("Anda tidak mempunyai akses internet", new Gson().toJson(model));
                    }
                });
    }

    void DropBibit(DropBibitModel model) {
        System.out.println(model);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .header("Content-Type", "application/json")
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        }).build();
        view.showLoadingIndicator();
        restService.create(NetworkService.class).pengamatanDropBibit(model)
                .enqueue(new Callback<CommonResponse>() {
                    @Override
                    public void onResponse(retrofit2.Call<CommonResponse> call, Response<CommonResponse> CommonRespon) {
                        view.hideLoadingIndicator();
                        view.onCreateSuccess(CommonRespon.body().getRm());
//
                    }

                    @Override
                    public void onFailure(retrofit2.Call<CommonResponse> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError("Anda tidak mempunyai akses internet", new Gson().toJson(model));
                    }
                });
    }

    void KualitasTanam(TanamModel model) {
        System.out.println(model);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .header("Content-Type", "application/json")
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        }).build();
        view.showLoadingIndicator();
        restService.create(NetworkService.class).pengamatanTanam(model)
                .enqueue(new Callback<CommonResponse>() {
                    @Override
                    public void onResponse(retrofit2.Call<CommonResponse> call, Response<CommonResponse> CommonRespon) {
                        view.hideLoadingIndicator();
                        view.onCreateSuccess(CommonRespon.body().getRm());
//
                    }

                    @Override
                    public void onFailure(retrofit2.Call<CommonResponse> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError("Anda tidak mempunyai akses internet", new Gson().toJson(model));
                    }
                });
    }

    void JumlahBaris(JumlahBarisModel model) {
        System.out.println(model);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .header("Content-Type", "application/json")
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        }).build();
        view.showLoadingIndicator();
        restService.create(NetworkService.class).pengamatanJumlahBaris(model)
                .enqueue(new Callback<CommonResponse>() {
                    @Override
                    public void onResponse(retrofit2.Call<CommonResponse> call, Response<CommonResponse> CommonRespon) {
                        view.hideLoadingIndicator();
                        view.onCreateSuccess(CommonRespon.body().getRm());
//
                    }

                    @Override
                    public void onFailure(retrofit2.Call<CommonResponse> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError("Anda tidak mempunyai akses internet", new Gson().toJson(model));
                    }
                });
    }

    void StekPanjang(SingkongStekPanjangModel model) {
        System.out.println(model);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .header("Content-Type", "application/json")
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        }).build();
        view.showLoadingIndicator();
        restService.create(NetworkService.class).pengamatanSingkongStekPanjang(model)
                .enqueue(new Callback<CommonResponse>() {
                    @Override
                    public void onResponse(retrofit2.Call<CommonResponse> call, Response<CommonResponse> CommonRespon) {
                        view.hideLoadingIndicator();
                        view.onCreateSuccess(CommonRespon.body().getRm());
//
                    }

                    @Override
                    public void onFailure(retrofit2.Call<CommonResponse> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError("Anda tidak mempunyai akses internet", new Gson().toJson(model));
                    }
                });
    }
}
