package com.ggf.qcpp.d_hasilpengamatan.d_1_hasilpengamatan_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;

import com.ggf.qcpp.App;
import com.ggf.qcpp.Prefs;
import com.ggf.qcpp.R;
import com.ggf.qcpp.b_account.model.LoginResponse;
import com.ggf.qcpp.d_hasilpengamatan.d_2_hasilpengamatan_lahan.mandor.HasilPengamatanActivity;
import com.ggf.qcpp.e_formpengamatan.chopper.model.ChopperModel;
import com.ggf.qcpp.i_notify.NotifyAdapter;
import com.ggf.qcpp.i_notify.model.PengamatanModel;
import com.ggf.qcpp.utils.GsonHelper;
import com.google.gson.Gson;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class d_1_hasilpengamatan_1 extends AppCompatActivity implements IHasilPengamatanView, NotifyAdapter.OnItemSelected {

    String[] itemTahun = {"2021", "2022", "2023", "2024"};
    String[] itemBulan = {"Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"};
    String[] itemAktifitas = {"Chopper", "Pembajakan", "Agregat", "Ridger SR", "Kebersihan Bonggol", "Analisa pH Tanah", "Bibit di Lokasi Petik", "Kebersihan Transport", "Pool Dipping", "Bibit di Lokasi Tanam", "Kualitas Tanam", "Penambahan Baris", "Gudang Mixer", "Adukan Bahan di Lokasi", "Aplikasi Boom Spray", "Kebersihan Panen", "Seset Bonggol", "Bonggol Tidak Terseset", "Bibit Singkong Stek Panjang", "Bibit Singkong Stek Pendek", "Tanam Singkong"};
    String[] itemWilayah = {"PG 1", "PG 2", "PG 3"};
    LinearLayout buttonBack;
    Button buttonLanjut;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.mFilter)
    Button mFilter;

    @BindView(R.id.mLokasi)
    AutoCompleteTextView mLokasi;

    @BindView(R.id.mKategori)
    AutoCompleteTextView mKategori;
    AutoCompleteTextView autoCompleteTextView;

    ArrayAdapter<String> adapterItem;
    HasilPengamatanPresenter presenter;
    SweetAlertDialog sweetAlertDialog;
    public NotifyAdapter adapter;
    String username;

    ArrayList<String>listLokasi  = null;
    ArrayList<String>listKategori  = null;
    LoginResponse mProfile;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_1_hasilpengamatan);
        ButterKnife.bind(this);

        mProfile = (LoginResponse) GsonHelper.parseGson(
                App.getPref().getString(Prefs.PREF_STORE_PROFILE, ""),
                new LoginResponse()
        );

        Log.d("datauser" ,new Gson().toJson(mProfile));


        presenter = new HasilPengamatanPresenter(this);
        presenter.getPengamatanFilter(null , null , mProfile.getData().getUser().getEmail());

        mFilter.setOnClickListener(view -> presenter.getPengamatanFilter(mLokasi.getText().toString(), mKategori.getText().toString(),mProfile.getData().getUser().getEmail()));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            getWindow().setDecorFitsSystemWindows(false);
        } else {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            );
        }

    }


    @Override
    public void onSubmit() {

    }

    @Override
    public void onVerified(String guid, ChopperModel model) {

    }

    @Override
    public void onVerifiedSuccess() {

    }

    @Override
    public void onDataReady(List<PengamatanModel> data) {

        Collections.sort(data, (o1, o2) -> o2.getCREATED_AT().compareTo(o1.getCREATED_AT()));
        if(listLokasi == null){
            listLokasi = new ArrayList<>();
            listKategori = new ArrayList<>();
            for (int i = 0; i < data.size(); i++) {
                if(!listLokasi.contains(data.get(i).getLOKASI())) {
                    listLokasi.add(data.get(i).getLOKASI());
                }
                if(!listKategori.contains(data.get(i).getKATEGORI())) {
                    listKategori.add(data.get(i).getKATEGORI());
                }

            }
            ArrayAdapter<String> adapterLokasi = new ArrayAdapter<String>(this,
                    android.R.layout.simple_dropdown_item_1line, listLokasi);

            mLokasi.setAdapter(adapterLokasi);

            ArrayAdapter<String> adapterKategori = new ArrayAdapter<String>(this,
                    android.R.layout.simple_dropdown_item_1line, listKategori);

            mKategori.setAdapter(adapterKategori);
        }


        Log.d("datalistlokasi", new Gson().toJson(data));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.clearFocus();
        adapter = new NotifyAdapter(data, this, this, username,true);
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }

    @Override
    public void showLoadingIndicator() {

    }

    @Override
    public void hideLoadingIndicator() {

    }

    @Override
    public void onNetworkError(String cause) {

    }

    public void onBackPressed() {
        // Tambahkan kode lain yang Anda inginkan sebelum menutup aktivitas (jika perlu).
        super.onBackPressed();
        finish(); // Menutup aktivitas saat tombol "Back" ditekan.
    }

    @Override
    public void onSelect(PengamatanModel model) {
        Intent intent = new Intent(this, HasilPengamatanActivity.class);
        intent.putExtra("model", model);
        intent.putExtra("fromClassname", getClass().getSimpleName());
        startActivity(intent);
    }

    @Override
    public void onHapus(PengamatanModel model) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}

