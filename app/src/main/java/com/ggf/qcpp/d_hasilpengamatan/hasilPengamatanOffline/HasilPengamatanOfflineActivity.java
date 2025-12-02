package com.ggf.qcpp.d_hasilpengamatan.hasilPengamatanOffline;

import static com.ggf.qcpp.utils.Utils.generateTglSekarang;
import static com.ggf.qcpp.utils.Utils.goToListPengamatan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ggf.qcpp.App;
import com.ggf.qcpp.Prefs;
import com.ggf.qcpp.R;
import com.ggf.qcpp.b_account.model.LoginResponse;
import com.ggf.qcpp.c_home.c_1_home;
import com.ggf.qcpp.c_home.home_leader;
import com.ggf.qcpp.d_hasilpengamatan.hasilPengamatanOffline.model.OfflineModel;
import com.ggf.qcpp.e_formpengamatan.adukanbahan.model.AdukanBahanDilokasiModel;
import com.ggf.qcpp.e_formpengamatan.bajak.model.BajakModel;
import com.ggf.qcpp.e_formpengamatan.chopper.model.ChopperModel;
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
import com.ggf.qcpp.i_notify.NotifyAdapter;
import com.ggf.qcpp.i_notify.i_notify_1;
import com.ggf.qcpp.network.SQLiteHelper;
import com.ggf.qcpp.ui.SweetDialogs;
import com.ggf.qcpp.utils.GsonHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HasilPengamatanOfflineActivity extends AppCompatActivity implements PengamatanOfflineAdapter.OnItemSelected , IPengamatanOfflineView{
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    public PengamatanOfflineAdapter adapter;
    public PengamatanOfflinePresenter presenter;
    LoginResponse mProfile;
    SweetAlertDialog sweetAlertDialog;
    private OfflineModel selectedOfflineModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hasil_pengamatan_offline);
        mProfile = (LoginResponse) GsonHelper.parseGson(
                App.getPref().getString(Prefs.PREF_STORE_PROFILE, ""),
                new LoginResponse()
        );
        ButterKnife.bind(this);

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(HasilPengamatanOfflineActivity.this, c_1_home.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish(); // biar activity ini langsung ditutup
            }
        });

        presenter = new PengamatanOfflinePresenter(this);
        SQLiteHelper dbHelper = new SQLiteHelper(this);
        String dataList = String.valueOf(dbHelper.getAllData());
//        String cleanedDataList = dataList.replace("\"", " ");
        Log.d("datanya1", dataList);
        if (!dataList.isEmpty()) {
            Gson gson = new Gson();
            // Parse the JSON string into a list of OfflineModel objects
            Type listType = new TypeToken<List<OfflineModel>>(){}.getType();
//            List<OfflineModel> offlineDataList = (List<OfflineModel>) new Gson().fromJson(dataList, OfflineModel.class);
            List<OfflineModel> dataModelList = gson.fromJson(dataList,listType);
            if (dataModelList != null && !dataModelList.isEmpty()) {
                // Set up RecyclerView with the parsed data
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                mRecyclerView.setHasFixedSize(true);
                mRecyclerView.setLayoutManager(linearLayoutManager);
                mRecyclerView.clearFocus();

                // Pass the parsed list to the adapter
                adapter = new PengamatanOfflineAdapter(dataModelList, this, this, mProfile.getData().getUser().getEmail());
                mRecyclerView.setAdapter(adapter);
            }
        }
    }


    @Override
    public void onSelect(OfflineModel model) {
        this.selectedOfflineModel = model;
        String kategori = model.getKategori(); // Assuming this returns a String
        String data = new Gson().toJson(model);

        Map<String, Consumer<String>> kategoriActions = new HashMap<>();

        // Add kategori actions
        kategoriActions.put("chopper", (datas) -> {
            ChopperModel dataModel = new Gson().fromJson(data, new TypeToken<ChopperModel>() {}.getType());
//            dataModel.setKeterangan("-");
            Log.d("dataOffline", new Gson().toJson(dataModel));
            presenter.Chopper(dataModel);
        });

        kategoriActions.put("bajak", (datas) -> {
            BajakModel dataModel = new Gson().fromJson(data, new TypeToken<BajakModel>() {}.getType());
            Log.d("dataOffline", new Gson().toJson(dataModel));
            presenter.Bajak(dataModel);
        });

        kategoriActions.put("finishing", (datas) -> {
            FinishingModel dataModel = new Gson().fromJson(data, new TypeToken<FinishingModel>() {}.getType());
            Log.d("dataOffline", new Gson().toJson(dataModel));
            presenter.Finishing(dataModel);
        });

        kategoriActions.put("subsoiler", (datas) -> {
            SubsoilerModel dataModel = new Gson().fromJson(data, new TypeToken<SubsoilerModel>() {}.getType());
            Log.d("dataOffline", new Gson().toJson(dataModel));
            presenter.Subsoiler(dataModel);
        });

//        kategoriActions.put("ridger", (datas) -> {
//            RidgerModel dataModel = new Gson().fromJson(data, new TypeToken<RidgerModel>() {}.getType());
//            Log.d("dataOffline", new Gson().toJson(dataModel));
//            presenter.Ridger(dataModel);
//        });

//        kategoriActions.put("kebersihan_bonggol", (datas) -> {
//            KebersihanBonggolModel dataModel = new Gson().fromJson(data, new TypeToken<KebersihanBonggolModel>() {}.getType());
//            Log.d("dataOffline", new Gson().toJson(dataModel));
//            presenter.KebersihanBonggol(dataModel);
//        });

//        kategoriActions.put("ph_tanah", (datas) -> {
//            PhtanahModel dataModel = new Gson().fromJson(data, new TypeToken<PhtanahModel>() {}.getType());
//            Log.d("dataOffline", new Gson().toJson(dataModel));
//            presenter.PhTanah(dataModel);
//        });

        kategoriActions.put("petik_bibit", (datas) -> {
            PetikBibitModel dataModel = new Gson().fromJson(data, new TypeToken<PetikBibitModel>() {}.getType());
            Log.d("dataOffline", new Gson().toJson(dataModel));
            presenter.PetikBibit(dataModel);
        });

//        kategoriActions.put("kebersihan_transport", (datas) -> {
//            TransportModel dataModel = new Gson().fromJson(data, new TypeToken<TransportModel>() {}.getType());
//            Log.d("dataOffline", new Gson().toJson(dataModel));
//            presenter.KebersihanTransport(dataModel);
//        });

        kategoriActions.put("pool_dipping", (datas) -> {
            PoolDippingModel dataModel = new Gson().fromJson(data, new TypeToken<PoolDippingModel>() {}.getType());
            Log.d("dataOffline", new Gson().toJson(dataModel));
            presenter.PoolDipping(dataModel);
        });

        kategoriActions.put("drop_bibit", (datas) -> {
            DropBibitModel dataModel = new Gson().fromJson(data, new TypeToken<DropBibitModel>() {}.getType());
            Log.d("dataOffline", new Gson().toJson(dataModel));
            presenter.DropBibit(dataModel);
        });

        kategoriActions.put("kualitas_tanam", (datas) -> {
            TanamModel dataModel = new Gson().fromJson(data, new TypeToken<TanamModel>() {}.getType());
            Log.d("dataOffline", new Gson().toJson(dataModel));
            presenter.KualitasTanam(dataModel);
        });

        kategoriActions.put("jumlah_baris", (datas) -> {
            JumlahBarisModel dataModel = new Gson().fromJson(data, new TypeToken<JumlahBarisModel>() {}.getType());
            Log.d("dataOffline", new Gson().toJson(dataModel));
            presenter.JumlahBaris(dataModel);
        });

        kategoriActions.put("potensi_bibit_crown", (datas) -> {
            PotensiCrownModel dataModel = new Gson().fromJson(data, new TypeToken<PotensiCrownModel>() {}.getType());
            Log.d("dataOffline", new Gson().toJson(dataModel));
            presenter.PotensiCrown(dataModel);
        });

        kategoriActions.put("singkong_stek_panjang", (datas) -> {
            SingkongStekPanjangModel dataModel = new Gson().fromJson(data, new TypeToken<SingkongStekPanjangModel>() {}.getType());
            Log.d("dataOffline", new Gson().toJson(dataModel));
            presenter.StekPanjang(dataModel);
        });

        kategoriActions.put("mixer", (datas) -> {
            GudangMixerModel dataModel = new Gson().fromJson(data, new TypeToken<GudangMixerModel>() {}.getType());
            Log.d("dataOffline", new Gson().toJson(dataModel));
            presenter.mixer(dataModel);
        });

        kategoriActions.put("adukan", (datas) -> {
            Log.d("dataAdukan", datas);
            AdukanBahanDilokasiModel dataModel = new Gson().fromJson(data, new TypeToken<AdukanBahanDilokasiModel>() {}.getType());
            Log.d("dataOffline", new Gson().toJson(dataModel));
            presenter.adukan(dataModel);
        });

        kategoriActions.put("panen", (datas) -> {
            PanenModel dataModel = new Gson().fromJson(data, new TypeToken<PanenModel>() {}.getType());
            Log.d("dataOffline", new Gson().toJson(dataModel));
            presenter.panen(dataModel);
        });

        kategoriActions.put("bonggol_tidak_terseset", (datas) -> {
            BonggolTidakTersesetModel dataModel = new Gson().fromJson(data, new TypeToken<BonggolTidakTersesetModel>() {}.getType());
            Log.d("dataOffline", new Gson().toJson(dataModel));
            presenter.bonggol_tidak_terseset(dataModel);
        });



        // Example of a generic "tractor" handler
        Consumer<String> tractorHandler = (datas) -> {
            // Handle the "tractor" related category here
            Log.d("dataOffline", "Handling tractor-related category");
            // Optionally call presenter.handleTractor(data); or any other specific action
        };

        // Add "tractor" related categories
        List<String> tractorCategories = Collections.emptyList();

        // Add the "tractor" handler to all relevant categories
        for (String tractorCategory : tractorCategories) {
            kategoriActions.put(tractorCategory, tractorHandler);
        }

        // Default case handler (Optional)
        kategoriActions.put("default", (datas) -> {
            Log.d("dataOffline", "Unknown category, handling default case");
            // Handle default scenario
        });

        // Execute the corresponding action for the category
        kategoriActions.getOrDefault(kategori, kategoriActions.get("default")).accept(data);

//        switch (kategori) {
//            case "chopper":
//                Type listChopper = new TypeToken<ChopperModel>(){}.getType();
//                ChopperModel dataModel = new Gson().fromJson(data , listChopper);
//                Log.d("dataOffline" , new Gson().toJson(dataModel));
//                presenter.Chopper(dataModel);
//                break;
//            case "bajak":
//                Type listBajak = new TypeToken<ChopperModel>(){}.getType();
//                BajakModel bajak = new Gson().fromJson(data , listBajak);
//                Log.d("dataOffline" , new Gson().toJson(bajak));
//                presenter.Bajak(bajak);
//                break;
//            case "finishing":
//                // Handle the "tractor" case
////                handleTractor(model);
//                break;
//            case "ridger":
//                // Handle the "tractor" case
////                handleTractor(model);
//                break;
//            case "kebersihan_bonggol":
//                // Handle the "tractor" case
////                handleTractor(model);
//                break;
//            case "ph_tanah":
//                // Handle the "tractor" case
////                handleTractor(model);
//                break;
//            case "petik_bibit":
//                // Handle the "tractor" case
////                handleTractor(model);
//                break;
//            case "kebersihan_transport":
//                // Handle the "tractor" case
////                handleTractor(model);
//                break;
//            case "pool_dipping":
//                // Handle the "tractor" case
////                handleTractor(model);
//                break;
//            case "drop_bibit":
//                // Handle the "tractor" case
////                handleTractor(model);
//                break;
//            case "kualitas_tanam":
//                // Handle the "tractor" case
////                handleTractor(model);
//                break;
//            case "jumlah_baris":
//                // Handle the "tractor" case
////                handleTractor(model);
//                break;
//            default:
//                // Handle the default case when no match is found
////                handleDefault(model);
//                break;
//        }

    }


    @Override
    public void onCreateSuccess(String rm) {
//        String message = "Chopper Berhasil di Kirim" + "\n\"" + generateTglSekarang() + "\"\nLokasi: " + mLokasi.getText().toString();
//        SweetDialogs.commonSuccessWithIntent(this,rm, string -> {
//            goToListPengamatan(this);
//        });
        Log.d("selectedOfflineModel", new Gson().toJson(selectedOfflineModel));
        if (selectedOfflineModel != null) {
            SQLiteHelper dbHelper = new SQLiteHelper(this);
            boolean deleted = dbHelper.deleteDataById(selectedOfflineModel.getNoSpk());
            if (deleted) {
                Log.d("SQLite", "Data berhasil dihapus: " + selectedOfflineModel.getNoSpk());
//                startActivity(new Intent(this, HasilPengamatanOfflineActivity.class));
//                finish();

                SweetDialogs.commonSuccessWithIntent(this,rm, string -> {
                    startActivity(new Intent(this, HasilPengamatanOfflineActivity.class));
                    finish();
                });
            } else {
                Log.e("SQLite", "Gagal menghapus data: " + selectedOfflineModel.getNoSpk());
            }
        }
//        SweetDialogs.commonSuccess(this,rm,true);

    }

    @Override
    public void showLoadingIndicator() {
        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText("Loading ...");
        sweetAlertDialog.show();
    }

    @Override
    public void hideLoadingIndicator() {
        sweetAlertDialog.dismiss();
    }

    @Override
    public void onNetworkError(String cause, String data) {
        SweetDialogs.commonWarning(this,"Gagal Memuat Permintaan" , "Anda Tidak memiliki koneksi internet", false);
//        SweetDialogs.commonWarningWithIntent(this,"Gagal Memuat Permintaan",App.getApplication().getString(R.string.notif_offline_mode),string -> startActivity(new Intent(this, c_1_home.class)));

    }
}