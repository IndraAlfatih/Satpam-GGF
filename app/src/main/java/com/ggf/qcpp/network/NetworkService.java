package com.ggf.qcpp.network;




import com.ggf.qcpp.b_account.model.LoginResponse;
import com.ggf.qcpp.b_account.model.UserModel;
import com.ggf.qcpp.d_hasilpengamatan.d_2_hasilpengamatan_lahan.mandor.model.HasilPengamatanResponse;
import com.ggf.qcpp.e_formpengamatan.adukanbahan.model.AdukanBahanDilokasiModel;
import com.ggf.qcpp.e_formpengamatan.bajak.model.BajakModel;
import com.ggf.qcpp.e_formpengamatan.z_satpam.model.SatpamModel;
import com.ggf.qcpp.e_formpengamatan.boom.model.BoomMixerModel;
import com.ggf.qcpp.e_formpengamatan.chopper.model.ChopperModel;
import com.ggf.qcpp.e_formpengamatan.dropbibit.model.DropBibitModel;
import com.ggf.qcpp.e_formpengamatan.finishing.model.FinishingModel;
import com.ggf.qcpp.e_formpengamatan.gudangmixer.model.GudangMixerModel;
import com.ggf.qcpp.e_formpengamatan.jalansaluran.model.JalanSaluranModel;
import com.ggf.qcpp.e_formpengamatan.jumlahbaris.model.JumlahBarisModel;
import com.ggf.qcpp.e_formpengamatan.kebersihanbonggol.model.KebersihanBonggolModel;
import com.ggf.qcpp.e_formpengamatan.kebersihanpanen.model.PanenModel;
import com.ggf.qcpp.e_formpengamatan.petikbibit.model.PetikBibitModel;
import com.ggf.qcpp.e_formpengamatan.phtanah.model.PhtanahModel;
import com.ggf.qcpp.e_formpengamatan.pooldipping.model.PoolDippingModel;
import com.ggf.qcpp.e_formpengamatan.potensicrown.model.PotensiCrownModel;
import com.ggf.qcpp.e_formpengamatan.ridger.model.RidgerModel;
import com.ggf.qcpp.e_formpengamatan.sesetbonggol.model.SesetBonggolModel;
import com.ggf.qcpp.e_formpengamatan.stekpanjang.model.SingkongStekPanjangModel;
import com.ggf.qcpp.e_formpengamatan.stekpendek.model.SingkongStekPendekModel;
import com.ggf.qcpp.e_formpengamatan.subsoiler.model.SubsoilerModel;
import com.ggf.qcpp.e_formpengamatan.tanam.model.TanamModel;
import com.ggf.qcpp.e_formpengamatan.tanamsingkong.model.TanamSingkongModel;
import com.ggf.qcpp.e_formpengamatan.tidakterseset.model.BonggolTidakTersesetModel;
import com.ggf.qcpp.e_formpengamatan.transport.model.TransportModel;
import com.ggf.qcpp.i_notify.model.NotifyResponse;
import com.ggf.qcpp.i_notify.model.PengamatanModel;
import com.ggf.qcpp.rencana_kerja.model.ListLeaderResponse;
import com.ggf.qcpp.rencana_kerja.model.RencanaKerjaModel;
import com.ggf.qcpp.rencana_kerja.model.SpkModel;
import com.ggf.qcpp.utils.CommonResponse;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface NetworkService {
    @POST("login")
    Call<LoginResponse> login(@Body JsonObject model);

    @POST("postPengamatan")
    Call<CommonResponse> pengamatan(@Body ChopperModel model);

    @POST("postPengamatan")
    Call<CommonResponse> pengamatanBajak(@Body BajakModel model);

    @POST("postLembarMutasi")
    Call<CommonResponse> pengamatanSatpam(@Body SatpamModel model);

    @POST("postPengamatan")
    Call<CommonResponse> pengamatanSubsoil(@Body SubsoilerModel model);

    @POST("postPengamatan")
    Call<CommonResponse> pengamatanBoomMixer(@Body BoomMixerModel model);

    @POST("postPengamatan")
    Call<CommonResponse> pengamatanJumlahBaris(@Body JumlahBarisModel model);

    @POST("postPengamatan")
    Call<CommonResponse> pengamatanPotensiCrown(@Body PotensiCrownModel model);

    @POST("postPengamatan")
    Call<CommonResponse> pengamatanGudangMixer(@Body GudangMixerModel model);

    @POST("postPengamatan")
    Call<CommonResponse> pengamatanTransport(@Body TransportModel model);

    @POST("postPengamatan")
    Call<CommonResponse> pengamatanPanen(@Body PanenModel model);

    @POST("postPengamatan")
    Call<CommonResponse> pengamatanTanam(@Body TanamModel model);

    @POST("postPengamatan")
    Call<CommonResponse> pengamatanAdukanBahanDilokasi(@Body AdukanBahanDilokasiModel model);

    @POST("postPengamatan")
    Call<CommonResponse> pengamatanTanamSingkong(@Body TanamSingkongModel model);

    @POST("postPengamatan")
    Call<CommonResponse> pengamatanTidakTerseset(@Body BonggolTidakTersesetModel model);

    @POST("postPengamatan")
    Call<CommonResponse> pengamatanSesetBonggol(@Body SesetBonggolModel model);

    @POST("postPengamatan")
    Call<CommonResponse> pengamatanSingkongStekPendek(@Body SingkongStekPendekModel model);

    @POST("postPengamatan")
    Call<CommonResponse> pengamatanSingkongStekPanjang(@Body SingkongStekPanjangModel model);

    @POST("postPengamatan")
    Call<CommonResponse> pengamatanFinishing(@Body FinishingModel model);


    @POST("postPengamatan")
    Call<CommonResponse> pengamatanPetikBibit(@Body PetikBibitModel model);

    @POST("postPengamatan")
    Call<CommonResponse> pengamatanPoolDipping(@Body PoolDippingModel model);

    @POST("postPengamatan")
    Call<CommonResponse> pengamatanRidger(@Body RidgerModel model);


    @POST("postPengamatan")
    Call<CommonResponse> pengamatanPhTanah(@Body PhtanahModel model);

    @POST("postPengamatan")
    Call<CommonResponse> pengamatanJalanSaluran(@Body JalanSaluranModel model);

    @POST("postPengamatan")
    Call<CommonResponse> pengamatanKebersihanBonggol(@Body KebersihanBonggolModel model);

    @POST("postPengamatan")
    Call<CommonResponse> pengamatanDropBibit(@Body DropBibitModel model);

    //
    @GET("getPengamatan")
    Call<NotifyResponse> getPengamatan();


    @POST("getPengamatanByLeader")
    Call<NotifyResponse> getPengamatanByLeader(@Body JsonObject paramObject);

    @POST("getPengamatanByKategori")
    Call<HasilPengamatanResponse> getPengamatanByGuid(@Body JsonObject paramObject);

    @POST("filterPengamatan")
    Call<NotifyResponse> getPengamatanFilter(@Body JsonObject paramObject);

    @POST("updatePengamatan/{id}")
    Call<CommonResponse> onVerified(@Path("id") String id, @Body PengamatanModel model);


    @DELETE("pengamatan/{id}")
    Call<CommonResponse> onDeletePengamatan(@Path("id") String id);

    @GET("getPengamatan")
    Call<List<RencanaKerjaModel>> getRencanaKerja();

    @GET("getListLeader/{id_role}")
    Call<ListLeaderResponse> getListLeader(@Path("id_role") int id_role);

    @POST("spk/create")
    Call<CommonResponse> createSpk(@Body SpkModel model);
}
