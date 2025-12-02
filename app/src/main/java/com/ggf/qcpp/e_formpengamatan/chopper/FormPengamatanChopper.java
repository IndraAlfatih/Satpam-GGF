package com.ggf.qcpp.e_formpengamatan.chopper;

import static com.ggf.qcpp.utils.Utils.convertStirngToInt;
import static com.ggf.qcpp.utils.Utils.generateTglSekarang;
import static com.ggf.qcpp.utils.Utils.getSavedDataFromLocalStorage;
import static com.ggf.qcpp.utils.Utils.goToListPengamatan;
import static com.ggf.qcpp.utils.Utils.now;
import static com.ggf.qcpp.utils.Utils.saveDataToLocalStorage;
import static com.ggf.qcpp.utils.Utils.storePengamatan;
import static com.ggf.qcpp.utils.Utils.totalnilaiChopper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ggf.qcpp.App;
import com.ggf.qcpp.Prefs;
import com.ggf.qcpp.R;
import com.ggf.qcpp.b_account.model.LoginResponse;
//import com.ggf.qcpp.d_hasilpengamatan.hasilPengamatanOffline.model.OfflineModel;
import com.ggf.qcpp.c_home.c_1_home;
import com.ggf.qcpp.e_formpengamatan.bajak.model.BajakModel;
import com.ggf.qcpp.e_formpengamatan.chopper.model.ChopperModel;
import com.ggf.qcpp.e_formpengamatan.chopper.model.PlotModel;
import com.ggf.qcpp.e_formpengamatan.chopper.model.SampleModel;
import com.ggf.qcpp.network.SQLiteHelper;
import com.ggf.qcpp.ui.SweetDialogs;
import com.ggf.qcpp.utils.GsonHelper;
import com.ggf.qcpp.utils.TemporaryFormStorage;
import com.google.gson.Gson;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
//import cn.pedant.SweetAlert.SweetAlertDialog;

public class FormPengamatanChopper extends AppCompatActivity implements IFormPengamatanChopperView, View.OnClickListener {

    LoginResponse mProfile;
    ChopperModel mDataOffline;
    @BindView(R.id.mSubmit)
    Button mSubmit;

//    @BindView(R.id.mLanjut)
//    Button mLanjut;

    @BindView(R.id.mSPK)
    EditText mSPK;

    @BindView(R.id.mLine)
    EditText mLine;

    @BindView(R.id.mReworking)
    AutoCompleteTextView mReworking;

    @BindView(R.id.mAddSample)
    ImageView mAddSample;

    @BindView(R.id.mLuasPlot)
    EditText mLuasPlot;
    @BindView(R.id.mLuasAktif)
    EditText mLuasAktif;
    @BindView(R.id.layoutHeader)
    LinearLayout layoutHeader;
    @BindView(R.id.layoutPlot)
    LinearLayout layoutPlot;

//    @BindView(R.id.containerSampleData)
//    LinearLayout containerSampleData;

    @BindView(R.id.containerPlotData)
    LinearLayout containerPlotData;

    @BindView(R.id.mCreated)
    TextView mCreated;
    @BindView(R.id.mPG)
    TextView mPG;
    @BindView(R.id.mUsername)
    TextView mUsername;

    @BindView(R.id.mLokasi)
    EditText mLokasi;


    @BindView(R.id.mNoUnit)
    EditText mNoUnit;

    @BindView(R.id.mJenisImplement)
    EditText mJenisImplement;
    @BindView(R.id.autoWilayah)
    AutoCompleteTextView autoWilayah;

    @BindView(R.id.autoComodityBajak)
    AutoCompleteTextView autoComodityBajak;
    @BindView(R.id.mStatusPengamatan)
    AutoCompleteTextView mStatusPengamatan;


    @BindView(R.id.mPlot)
    EditText mPlot;


    @BindView(R.id.mKeterangan)
    EditText mKeterangan;


    @BindView(R.id.mTanamanHancur)
    EditText mTanamanHancur;

    @BindView(R.id.mBonggol)
    EditText mBonggol;

    @BindView(R.id.mAplikasi)
    EditText mAplikasi;


    @BindView(R.id.mNow)
    TextView mNow;

    @BindView(R.id.mKet)
    EditText mKet;
    SweetAlertDialog sweetAlertDialog;
    FormPengamatanChopperPresenter presenter;

    ChopperModel model;
    //    OfflineModel modelOffline;
    View rowView;
    View rowViewPlot;
    List<PlotModel> dataPlot = new ArrayList<>();
    List<SampleModel> dataSample = new ArrayList<>();

    String plot = "0";
    View viewnya = null;

    SampleModel sampleModel = null;
    PlotModel plotModel = null;
    int index = 1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form_pengamatan_chopper);
        ButterKnife.bind(this);
        mProfile = (LoginResponse) GsonHelper.parseGson(
                App.getPref().getString(Prefs.PREF_STORE_PROFILE, ""),
                new LoginResponse()
        );

        //StatusBar Transparant
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


        Date date = new Date();
        mUsername.setText(mProfile.getData().getUser().getName());
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, autoWilayah2);
        autoWilayah.setAdapter(adapter4);
        mNow.setText(now());
        StrictMode.ThreadPolicy policy = new StrictMode.
                ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        model = (ChopperModel) getIntent().getSerializableExtra("model");
        Log.d("serialnya", new Gson().toJson(model));
        presenter = new FormPengamatanChopperPresenter(this);
//        mLanjut.setOnClickListener(this);
        mSubmit.setOnClickListener(this);
        mAddSample.setOnClickListener(this);
        this.showPlotForm();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.constrain1), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mReworking2);
        mReworking.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, statuspengamatan2);
        mStatusPengamatan.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, autoComodityBajak2);
        autoComodityBajak.setAdapter(adapter3);

        EditText lokasiUppercase = findViewById(R.id.mLokasi);
        lokasiUppercase.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

//        // 🔧 Pastikan dropdown bisa diklik ulang dan selalu menampilkan semua item
//        autoWilayah.setInputType(0); // biar gak bisa diketik tapi tetap bisa diklik
//
//        autoWilayah.setOnClickListener(v -> {
//            String currentText = autoWilayah.getText().toString(); // simpan teks yang dipilih
//            autoWilayah.setText(""); // kosongkan biar semua item muncul
//            autoWilayah.showDropDown(); // tampilkan semua item
//            autoWilayah.setText(currentText, false); // balikin teks lama tanpa trigger filter
//        });
//
//        mStatusPengamatan.setInputType(0); // biar gak bisa diketik tapi tetap bisa diklik
//
//        mStatusPengamatan.setOnClickListener(v -> {
//            String currentText = mStatusPengamatan.getText().toString(); // simpan teks yang dipilih
//            mStatusPengamatan.setText(""); // kosongkan biar semua item muncul
//            mStatusPengamatan.showDropDown(); // tampilkan semua item
//            mStatusPengamatan.setText(currentText, false); // balikin teks lama tanpa trigger filter
//        });
//
//        mReworking.setInputType(0); // biar gak bisa diketik tapi tetap bisa diklik
//
//        mReworking.setOnClickListener(v -> {
//            String currentText = mReworking.getText().toString(); // simpan teks yang dipilih
//            mReworking.setText(""); // kosongkan biar semua item muncul
//            mReworking.showDropDown(); // tampilkan semua item
//            mReworking.setText(currentText, false); // balikin teks lama tanpa trigger filter
//        });
//
//        autoComodityBajak.setInputType(0); // biar gak bisa diketik tapi tetap bisa diklik
//
//        autoComodityBajak.setOnClickListener(v -> {
//            String currentText = autoComodityBajak.getText().toString(); // simpan teks yang dipilih
//            autoComodityBajak.setText(""); // kosongkan biar semua item muncul
//            autoComodityBajak.showDropDown(); // tampilkan semua item
//            autoComodityBajak.setText(currentText, false); // balikin teks lama tanpa trigger filter
//        });

        // Menonaktifkan input teks, tetapi dropdown masih muncul
        autoWilayah.setKeyListener(null);
        mStatusPengamatan.setKeyListener(null);
        mReworking.setKeyListener(null);
        autoComodityBajak.setKeyListener(null);


        // Memastikan dropdown muncul meskipun tidak ada teks yang dimasukkan
        autoWilayah.setThreshold(1);
        mStatusPengamatan.setThreshold(1);
        mReworking.setThreshold(1);
        autoComodityBajak.setThreshold(1);

    }

    void showHeaderForm() {
        layoutHeader.setVisibility(View.VISIBLE);
        layoutPlot.setVisibility(View.GONE);
//        mLanjut.setVisibility(View.VISIBLE);
        mSubmit.setVisibility(View.GONE);
    }

    void showPlotForm() {
        layoutHeader.setVisibility(View.GONE);
        layoutPlot.setVisibility(View.VISIBLE);
//        mLanjut.setVisibility(View.GONE);
        mSubmit.setVisibility(View.VISIBLE);
    }

    //    void addPlotForm() {
////        String plotSekarang = "0" ;
//
//        //Tidak boleh kosong
//        if (mLokasi.getText().toString().equals("")) {
//            Toast.makeText(this, "Lokasi tidak boleh kosong", Toast.LENGTH_SHORT).show();
//        } else if (mStatusPengamatan.getText().toString().equals("")) {
//            Toast.makeText(this, "Status Pengamatan tidak boleh kosong", Toast.LENGTH_SHORT).show();
//        } else if (mPlot.getText().toString().equals("")) {
//            Toast.makeText(this, "No Plot tidak boleh kosong", Toast.LENGTH_SHORT).show();
//        } else if (mLuasPlot.getText().toString().equals("")) {
//            Toast.makeText(this, "Luas Plot tidak boleh kosong", Toast.LENGTH_SHORT).show();
//        } else if (mTanamanHancur.getText().toString().equals("")) {
//            Toast.makeText(this, "Tanaman Hancur tidak boleh kosong", Toast.LENGTH_SHORT).show();
//        } else if (mBonggol.getText().toString().equals("")) {
//            Toast.makeText(this, "Bonggol Tercacah tidak boleh kosong", Toast.LENGTH_SHORT).show();
//        } else if (mAplikasi.getText().toString().equals("")) {
//            Toast.makeText(this, "Aplikasi Rapat tidak boleh kosong", Toast.LENGTH_SHORT).show();
//        } else {
//
//            sampleModel = new SampleModel();
//            if (mPlot.getText().toString().equals(plot)) {
//                this.addSampleForm();
//            } else {
//                index = 1;
//                dataSample = new ArrayList<>();
//                plotModel = new PlotModel();
//                this.addSampleForm();
//                plotModel.setPLOT(mPlot.getText().toString());
//                plotModel.setSAMPLE(dataSample);
//
//                dataPlot.add(plotModel);
//
//
//            }
//            plot = mPlot.getText().toString();
//
//
//        }
//
//
//    }
    void addPlotForm() {
        // Validasi input tidak boleh kosong

        if (mSPK.getText().toString().equals("")) {
            Toast.makeText(this, "No SPK tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mLine.getText().toString().equals("")) {
            Toast.makeText(this, "No Line tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mLokasi.getText().toString().equals("")) {
            Toast.makeText(this, "Lokasi tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mStatusPengamatan.getText().toString().equals("")) {
            Toast.makeText(this, "Status Pengamatan tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mNoUnit.getText().toString().equals("")) {
            Toast.makeText(this, "No Unit Implement tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mJenisImplement.getText().toString().equals("")) {
            Toast.makeText(this, "Jenis Implement tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mReworking.getText().toString().equals("")) {
            Toast.makeText(this, "Reworking tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (autoWilayah.getText().toString().equals("")) {
            Toast.makeText(this, "Wilayah tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mLuasAktif.getText().toString().equals("")) {
            Toast.makeText(this, "Luas Aktif tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (autoComodityBajak.getText().toString().equals("")) {
            Toast.makeText(this, "Ex Comodity tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mPlot.getText().toString().equals("")) {
            Toast.makeText(this, "No Plot tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mLuasPlot.getText().toString().equals("")) {
            Toast.makeText(this, "Luas Plot tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mTanamanHancur.getText().toString().equals("")) {
            Toast.makeText(this, "Tanaman Hancur tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mBonggol.getText().toString().equals("")) {
            Toast.makeText(this, "Bonggol Tercacah tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mAplikasi.getText().toString().equals("")) {
            Toast.makeText(this, "Aplikasi Rapat tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else {
            sampleModel = new SampleModel();
            boolean plotExists = false;

            // Cek apakah plot sudah ada
            for (PlotModel existingPlot : dataPlot) {
                if (existingPlot.getPLOT().equals(mPlot.getText().toString())) {
                    // Jika plot sudah ada, tambahkan sampel baru ke plot ini
                    plotExists = true;
                    dataSample = existingPlot.getSAMPLE(); // Ambil daftar sampel dari plot
                    this.addSampleForm(); // Tambahkan data sampel
                    break;
                }
            }

            if (!plotExists) {
                // Jika plot baru, buat plot baru dan tambahkan ke dataPlot
                index = 1;
                dataSample = new ArrayList<>();
                plotModel = new PlotModel();
                this.addSampleForm(); // Tambahkan data sampel pertama
                plotModel.setPLOT(mPlot.getText().toString());
                plotModel.setSAMPLE(dataSample);

                dataPlot.add(plotModel);
            }

            plot = mPlot.getText().toString();
        }
    }

    void addSampleForm() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rowView = inflater.inflate(R.layout.data_chopper_sample_field, null);
        final LinearLayout containerSampleData = rowView.findViewById(R.id.containerPlotData);

        final TextView no_plot = rowView.findViewById(R.id.mPlot);
        final TextView tanamanHancur = rowView.findViewById(R.id.mTanamanHancur);
        final TextView bonggolTerpecah = rowView.findViewById(R.id.mBonggol);
        final TextView aplikasiRapat = rowView.findViewById(R.id.mAplikasi);
        final TextView nosample = rowView.findViewById(R.id.mNoSample);
        final TextView mTotalNilai = rowView.findViewById(R.id.mTotalNilai);

        no_plot.setText(mPlot.getText().toString());
        tanamanHancur.setText(mTanamanHancur.getText().toString());
        bonggolTerpecah.setText(mBonggol.getText().toString());
        aplikasiRapat.setText(mAplikasi.getText().toString());

        // Tentukan nomor sampel yang kosong
        List<Integer> existingSamples = new ArrayList<>();
        for (SampleModel sample : dataSample) {
            if (sample.getPLOT() == Integer.parseInt(mPlot.getText().toString())) {
                existingSamples.add(sample.getNo_sample());
            }
        }

        // Cari nomor urutan yang hilang
        Collections.sort(existingSamples);
        int nextSampleNumber = 1; // Mulai dari 1
        for (int i = 0; i < existingSamples.size(); i++) {
            if (existingSamples.get(i) != nextSampleNumber) {
                break; // Temukan nomor yang hilang
            }
            nextSampleNumber++;
        }

        nosample.setText(String.valueOf(nextSampleNumber));
        mTotalNilai.setText(totalnilaiChopper(
                tanamanHancur.getText().toString(),
                bonggolTerpecah.getText().toString(),
                aplikasiRapat.getText().toString()) + " %");

        sampleModel.setNo_spk(model.getNO_SPK());
        sampleModel.setPLOT(Integer.valueOf(no_plot.getText().toString()));
        sampleModel.setTANAMAN_HANCUR(Float.parseFloat(tanamanHancur.getText().toString()));
        sampleModel.setBONGGOL_TERPECAH(Float.parseFloat(bonggolTerpecah.getText().toString()));
        sampleModel.setAPLIKASI_RAPAT(Float.parseFloat(aplikasiRapat.getText().toString()));
        sampleModel.setLuas_plot(Float.parseFloat(mLuasPlot.getText().toString()));
        sampleModel.setJenis_implement(mJenisImplement.getText().toString());
        sampleModel.setNo_unit_implement(mNoUnit.getText().toString());
        sampleModel.setNo_spk2(mSPK.getText().toString());
        sampleModel.setNo_line(mLine.getText().toString());
        sampleModel.setStatus_pengamatan(mStatusPengamatan.getText().toString());
        sampleModel.setLokasi(mLokasi.getText().toString());
        sampleModel.setReworking(mReworking.getText().toString());
        sampleModel.setNo_sample(nextSampleNumber);
        sampleModel.setEx_comodity(autoComodityBajak.getText().toString());
        sampleModel.setLuas_aktif(Float.parseFloat(mLuasAktif.getText().toString()));
        sampleModel.setWilayah(autoWilayah.getText().toString());
        sampleModel.setKETERANGAN(
                mKet.getText().toString().trim().isEmpty() ? "-" : mKet.getText().toString().trim()
        );

        dataSample.add(sampleModel);
        Log.d("dataApply", new Gson().toJson(dataSample));
        containerPlotData.addView(rowView, 0);


        Toast.makeText(this, "Data Sudah Ditambahkan", Toast.LENGTH_SHORT).show();
        mTanamanHancur.getText().clear();
        mBonggol.getText().clear();
        mAplikasi.getText().clear();

//        mPlot.getText().clear();

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadTemporaryData(); // auto load ketika activity kembali aktif
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveTemporaryData(); // auto save ketika activity pause
    }


    private void saveTemporaryData() {
//        plotModel.setSAMPLE(dataSample);
//        model.setLOKASI(mLokasi.getText().toString());
//        model.setWILAYAH(autoWilayah.getText().toString());
//        model.setDATA(dataPlot);
//        model.setKeterangan(mKeterangan.getText().toString());
//
//        TemporaryFormStorage.saveDraft(this, "draft_chopper", model);

        ChopperModel draft = new ChopperModel();
        draft.setNO_SPK(mSPK.getText().toString());
        draft.setNO_LINE(mLine.getText().toString());
        draft.setLOKASI(mLokasi.getText().toString());
        draft.setSTATUS_PENGAMATAN(mStatusPengamatan.getText().toString());
        draft.setNO_UNIT_IMPLEMENT(mNoUnit.getText().toString());
        draft.setJenis_implement(mJenisImplement.getText().toString());
        draft.setWILAYAH(autoWilayah.getText().toString());
        draft.setReworking(mReworking.getText().toString());
        draft.setLUAS_NETTO(mLuasAktif.getText().toString());
        draft.setEx_comodity(autoComodityBajak.getText().toString());

        draft.setDATA(dataPlot); // simpan semua plot + sample

        TemporaryFormStorage.saveDraft(this, "draft_chopper", draft);
        Log.d("DraftSaved", new Gson().toJson(draft));

    }

    private void loadTemporaryData() {
        ChopperModel draft = TemporaryFormStorage.loadDraft(this, "draft_chopper", ChopperModel.class);
        Log.d("dataTemp", new Gson().toJson(draft));

        if (draft != null) {
            mSPK.setText(draft.NO_SPK);
            mLine.setText(draft.NO_LINE);
            mLokasi.setText(draft.LOKASI);
            mStatusPengamatan.setText(draft.STATUS_PENGAMATAN);
            autoWilayah.setText(draft.WILAYAH);
            mKeterangan.setText(draft.keterangan);
            mLuasAktif.setText(draft.LUAS_NETTO);
            mNoUnit.setText(draft.NO_UNIT_IMPLEMENT);
            mJenisImplement.setText(draft.jenis_implement);
            mReworking.setText(draft.reworking);
            autoComodityBajak.setText(draft.ex_comodity);

            containerPlotData.removeAllViews();
            dataPlot.clear();

            // restore data sample & plot
            if (draft.getDATA() != null) {
                dataPlot = draft.getDATA();
                for (PlotModel p : dataPlot) {
                    for (SampleModel s : p.getSAMPLE()) {
                        restoreSampleView(s);
                    }
                }
            }

            // ✅ Tambahkan ini di akhir: rebind adapter biar dropdown aktif lagi
            autoWilayah.post(() -> {
                ArrayAdapter<String> adapterWilayah =
                        new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, autoWilayah2);
                autoWilayah.setAdapter(adapterWilayah);
            });

            mStatusPengamatan.post(() -> {
                ArrayAdapter<String> adapterStatus =
                        new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, statuspengamatan2);
                mStatusPengamatan.setAdapter(adapterStatus);
            });

            mReworking.post(() -> {
                ArrayAdapter<String> adapterRework =
                        new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, mReworking2);
                mReworking.setAdapter(adapterRework);
            });

            autoComodityBajak.post(() -> {
                ArrayAdapter<String> adapterComodity =
                        new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, autoComodityBajak2);
                autoComodityBajak.setAdapter(adapterComodity);
            });
        }
    }



    private void restoreSampleView(SampleModel sample) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.data_chopper_sample_field, null);

        final TextView no_plot = rowView.findViewById(R.id.mPlot);
        final TextView tanamanHancur = rowView.findViewById(R.id.mTanamanHancur);
        final TextView bonggolTerpecah = rowView.findViewById(R.id.mBonggol);
        final TextView aplikasiRapat = rowView.findViewById(R.id.mAplikasi);
        final TextView nosample = rowView.findViewById(R.id.mNoSample);
        final TextView mTotalNilai = rowView.findViewById(R.id.mTotalNilai);

        no_plot.setText(String.valueOf(sample.getPLOT()));
        tanamanHancur.setText(String.valueOf(sample.getTANAMAN_HANCUR()));
        bonggolTerpecah.setText(String.valueOf(sample.getBONGGOL_TERPECAH()));
        aplikasiRapat.setText(String.valueOf(sample.getAPLIKASI_RAPAT()));
        nosample.setText(String.valueOf(sample.getNo_sample()));
        mTotalNilai.setText(totalnilaiChopper(
                String.valueOf(sample.getTANAMAN_HANCUR()),
                String.valueOf(sample.getBONGGOL_TERPECAH()),
                String.valueOf(sample.getAPLIKASI_RAPAT())) + " %");

        containerPlotData.addView(rowView, 0);
    }


    public void onDeleteSample(View v) {

        TextView txtPlot = ((View) v.getParent()).findViewById(R.id.mPlot);
        TextView txtNoSample = ((View) v.getParent()).findViewById(R.id.mNoSample);
        int plotToDelete = Integer.parseInt(txtPlot.getText().toString());
        int noSampleToDelete = Integer.parseInt(txtNoSample.getText().toString());

        Log.d("Hapus", "Menghapus data dengan PLOT: " + plotToDelete + " dan No_Sample: " + noSampleToDelete);

        // Hapus dari dataPlot
        boolean dataRemoved = false;
        for (int i = 0; i < dataPlot.size(); i++) {
            if (dataPlot.get(i).getPLOT().equals(String.valueOf(plotToDelete))) {
                List<SampleModel> samples = dataPlot.get(i).getSAMPLE();
                for (int j = 0; j < samples.size(); j++) {
                    if (samples.get(j).getNo_sample() == noSampleToDelete) {
                        samples.remove(j);
                        dataRemoved = true;
                        Log.d("Hapus", "Data di dataPlot dihapus. PLOT: " + plotToDelete + ", No_Sample: " + noSampleToDelete);
                        break;
                    }
                }

                // Jika semua sampel sudah dihapus dari plot, hapus plot dari dataPlot
                if (samples.isEmpty()) {
                    dataPlot.remove(i);
                    Log.d("Hapus", "Seluruh data untuk plot " + plotToDelete + " telah dihapus.");
                }
                break;
            }
        }

        // Hapus dari dataSample
        for (int i = 0; i < dataSample.size(); i++) {
            if (dataSample.get(i).getPLOT() == plotToDelete &&
                    dataSample.get(i).getNo_sample() == noSampleToDelete) {
                dataSample.remove(i);
                Log.d("Hapus", "Data di dataSample dihapus. PLOT: " + plotToDelete + ", No_Sample: " + noSampleToDelete);
                break;
            }
        }

        // Hapus view dari container
        if (dataRemoved) {
            ((ViewGroup) v.getParent().getParent()).removeView((ViewGroup) v.getParent());
            Log.d("Hapus", "View dihapus dari containerPlotData.");
        } else {
            Log.d("Hapus", "Data tidak ditemukan untuk dihapus.");
        }

        // Debugging final state
        Log.d("Hapus", "State dataPlot setelah penghapusan: " + new Gson().toJson(dataPlot));
        Log.d("Hapus", "State dataSample setelah penghapusan: " + new Gson().toJson(dataSample));
    }


    @Override
    public void onSubmit() {

        Log.d("datasubmit", new Gson().toJson(dataPlot));
        Log.d("datanya", new Gson().toJson(model));

        if (dataPlot.size() > 0) {
//            plotModel.setSAMPLE(dataSample);
            model.setLOKASI(mLokasi.getText().toString());
            model.setWILAYAH(autoWilayah.getText().toString());
            model.setDATA(dataPlot);

            model.setKeterangan(
                    mKeterangan.getText().toString().trim().isEmpty() ? "-" : mKeterangan.getText().toString().trim()
            );

//            modelOffline = new OfflineModel() ;
//            modelOffline.setLOKASI(mLokasi.getText().toString());
//            modelOffline.setWILAYAH(autoWilayah.getText().toString());
//            modelOffline.setDATA(model.getDATA());
//            Log.d("datanya", new Gson().toJson(model));
//            Log.d("datanyanih", new Gson().toJson(dataSample));
            presenter.createPengamatan(model);
        } else
            SweetDialogs.commonError(this, "Harap apply data terlebih dahulu", false);
    }


    @Override
    public void onCreateSuccess(String rm) {
        // Tambahkan format timestamp
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        String currentTime = sdf.format(new Date()); // Mendapatkan waktu saat ini

        // Tambahkan waktu ke dalam pesan
        String message = "Chopper"
                + ", Lokasi: " + mLokasi.getText().toString()
                + "\nTanggal: " + generateTglSekarang()
                + "\nWaktu: " + currentTime
                + "\n" + getString(R.string.versi_apps);

        SweetDialogs.commonSuccessWithIntent(this, message, string -> {
            goToListPengamatan(this);
        });

        TemporaryFormStorage.clearDraft(this, "draft_chopper");
        clearForm();
    }

    private void clearForm() {
        // Kosongkan semua input header
        mSPK.setText("");
        mLine.setText("");
        mReworking.setText("");
        mLuasPlot.setText("");
        mLuasAktif.setText("");
        mLokasi.setText("");
        mNoUnit.setText("");
        mJenisImplement.setText("");
        autoWilayah.setText("");
        autoComodityBajak.setText("");
        mStatusPengamatan.setText("");
        mPlot.setText("");
        mKeterangan.setText("");
        mTanamanHancur.setText("");
        mBonggol.setText("");
        mAplikasi.setText("");
        mKet.setText("");

        // Kosongkan data di memory
        dataPlot.clear();
        dataSample.clear();

        // Kosongkan container sample di UI
        containerPlotData.removeAllViews();

        // Reset model juga
        model = new ChopperModel();
        TemporaryFormStorage.clearDraft(this, "draft_chopper");

    }


    @Override
    public void showLoadingIndicator() {
//        Toast.makeText(this, "woi", Toast.LENGTH_SHORT).show();
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

        Log.e("errornya", cause);
        SQLiteHelper dbHelper = new SQLiteHelper(this);
        dbHelper.saveChopperData(data, model.getNO_SPK());  // Assuming 'data' is a JSON string


        Log.d("Saved data", "Data saved to SQLite: " + data);
        Log.d("Saved data", "Data saved to SQLite: " + model.getNO_SPK());
        // Show a dialog indicating that the data has been saved offline
//        SweetDialogs.commonError(this, App.getApplication().getString(R.string.notif_offline_mode), false);
        SweetDialogs.commonWarningWithIntent(this, "Anda Tidak ada Koneksi Internet", App.getApplication().getString(R.string.notif_offline_mode), string -> startActivity(new Intent(this, c_1_home.class)));

        TemporaryFormStorage.clearDraft(this, "draft_chopper");
        clearForm();

    }

    public void onDelete(View v) {
        int indexOfMyView = ((ViewGroup) v.getParent()).indexOfChild(v);
//        TextView namaAnak = ((View) v.getParent()).findViewById(R.id.mNoSample);
//        System.out.println(namaAnak.getText().toString());
        ((ViewGroup) v.getParent().getParent()).removeView(viewnya);
//        containerPlotData.removeViewAt(indexOfMyView);
//        for (int i = 0; i < dataAnaks.length(); i++) {
//            try {
//                if (namaAnak.getText().toString().equals(dataAnaks.getJSONObject(i).getString("namaAnak"))) {
//                    dataAnaks.remove(i);
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//        containerPlotData.removeView((View) v.getParent());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mSubmit:
                this.onSubmit();
                break;
            case R.id.mAddSample:
                this.addPlotForm();
                break;
        }
    }

    private static final String[] statuspengamatan2 = new String[]{
            "Inprocess", "Crosscheck"
    };

    private static final String[] mReworking2 = new String[]{
            "Sebelum Reworking", "Sesudah Reworking"
    };

    private static final String[] autoComodityBajak2 = new String[]{
            "Nanas", "Singkong", "Pisang"
    };
    private static final String[] autoWilayah2 = new String[]{
            "AW01", "AW02", "AW03", "AW04", "AW05", "AW06", "AW07", "AW08", "AW09", "AW10", "AW11", "AW12", "AW13", "AW14", "AW15", "AW16", "AW17", "AW18", "AW19", "AW20", "AW21", "AW22", "AW23"
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed(); // Sudah cukup
    }
}