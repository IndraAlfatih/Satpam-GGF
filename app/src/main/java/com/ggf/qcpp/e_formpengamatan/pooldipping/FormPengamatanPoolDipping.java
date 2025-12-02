package com.ggf.qcpp.e_formpengamatan.pooldipping;

import static com.ggf.qcpp.utils.Utils.generateTglSekarang;
import static com.ggf.qcpp.utils.Utils.goToListPengamatan;
import static com.ggf.qcpp.utils.Utils.parseIntDefault;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.util.Pools;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ggf.qcpp.App;
import com.ggf.qcpp.R;
import com.ggf.qcpp.c_home.c_1_home;
import com.ggf.qcpp.e_formpengamatan.bajak.model.BajakModel;
import com.ggf.qcpp.e_formpengamatan.finishing.IFormPengamatanFinishingView;
import com.ggf.qcpp.e_formpengamatan.pooldipping.model.PlotModel;
import com.ggf.qcpp.e_formpengamatan.pooldipping.model.PoolDippingModel;
import com.ggf.qcpp.e_formpengamatan.pooldipping.model.SampleModel;
import com.ggf.qcpp.network.SQLiteHelper;
import com.ggf.qcpp.ui.SweetDialogs;
import com.ggf.qcpp.utils.TemporaryFormStorage;
import com.google.gson.Gson;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FormPengamatanPoolDipping extends AppCompatActivity implements View.OnClickListener, IFormPengamatanFinishingView {
    @BindView(R.id.mSubmit)
    Button mSubmit;

    @BindView(R.id.mAddSample)
    ImageView mAddSample;

    @BindView(R.id.containerPlotData)
    LinearLayout containerPlotData;

    @BindView(R.id.autoNoBibit)
    AutoCompleteTextView autonobibit;
    @BindView(R.id.mLokasi)
    AutoCompleteTextView mLokasi;
    @BindView(R.id.mReworking)
    AutoCompleteTextView mReworking;

    @BindView(R.id.mTglBibitCampur)
    EditText mTglBibitCampur;

    @BindView(R.id.mMandorBibit)
    EditText mMandorBibit;
    @BindView(R.id.mKendaraan)
    EditText mKendaraan;
    @BindView(R.id.mJenisUnit)
    AutoCompleteTextView mJenisUnit;

    @BindView(R.id.mJenisBibit)
    AutoCompleteTextView mJenisBibit;

    @BindView(R.id.mKelasBibit)
    AutoCompleteTextView mKelasBibit;

    @BindView(R.id.mAsalDO)
    EditText mAsalDO;

    @BindView(R.id.mTujuanDO)
    EditText mTujuanDO;

    @BindView(R.id.autoWilayah)
    AutoCompleteTextView autoWilayah;


//    @BindView(R.id.mKeteranganDO)
//    TextView mKeteranganDO;

    @BindView(R.id.mJumlahSample)
    EditText mJumlahSample;

    @BindView(R.id.mBibitNormal)
    EditText mBibitNormal;

    @BindView(R.id.mSPK)
    EditText mSPK;

    @BindView(R.id.mLine)
    EditText mLine;

    @BindView(R.id.mAfkir)
    EditText mAfkir;

    @BindView(R.id.m1)
    EditText m1;

    @BindView(R.id.m2)
    EditText m2;

    @BindView(R.id.m3)
    EditText m3;

    @BindView(R.id.m4)
    EditText m4;

    @BindView(R.id.m5)
    EditText m5;

    @BindView(R.id.m6)
    EditText m6;

    @BindView(R.id.m7)
    EditText m7;

    @BindView(R.id.mOver)
    EditText mOver;

    @BindView(R.id.mOverPlus)
    EditText mOverPlus;

    @BindView(R.id.mKet)
    EditText mKet;

    @BindView(R.id.mPlot)
    EditText mPlot;


    @BindView(R.id.mHasil)
    TextView mHasil;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.mYa)
    RadioButton mYa;
    @BindView(R.id.mTidak)
    RadioButton mTidak;

//    @BindView(R.id.mInfoBibit)
//    EditText mInfoBibit;

    private static final String DRAFT_KEY = "draft_pool_dipping";

    int index = 1 ;
    View rowView;
    View rowViewPlot;
    View viewnya = null;
    String plot = "1";
    PoolDippingModel model;

    List<PlotModel> dataPlot = new ArrayList<>();
    List<SampleModel> dataSample = new ArrayList<>();

    SweetAlertDialog sweetAlertDialog;
    SampleModel sampleModel = null;
    PlotModel plotModel = null;
    FormPengamatanPoolDippingPresenter presenter;
    int valueTerdipping ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_form_pengamatan_pool_dipping);
        ButterKnife.bind(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        mTglBibitCampur.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        // Tambahkan 1 ke bulan (karena bulan dimulai dari 0)
                        String selectedDate = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay;
                        mTglBibitCampur.setText(selectedDate); // Set tanggal di EditText
                    },
                    year, month, day);

            datePickerDialog.show(); // Tampilkan dialog
        });

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

        mPlot.setText("1");
        mPlot.setKeyListener(null);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, autojenisbibit2);
        mJenisBibit.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, autokelasbibit2);
        mKelasBibit.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, listLokPool);
        mLokasi.setAdapter(adapter3);

        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, autojenisunit2);
        mJenisUnit.setAdapter(adapter4);

        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, autoWilayah2);
        autoWilayah.setAdapter(adapter5);

        ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mReworking2);
        mReworking.setAdapter(adapter6);

        ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, autoNObibit2);
        autonobibit.setAdapter(adapter7);

        EditText lokasiUppercase = findViewById(R.id.mLokasi);
        lokasiUppercase.setFilters(new InputFilter[] {new InputFilter.AllCaps()});


        // Menonaktifkan input teks, tetapi dropdown masih muncul
        mJenisBibit.setKeyListener(null);
        mKelasBibit.setKeyListener(null);
        mLokasi.setKeyListener(null);
        mReworking.setKeyListener(null);
        autonobibit.setKeyListener(null);
        mJenisUnit.setKeyListener(null);
        autoWilayah.setKeyListener(null);

        // Memastikan dropdown muncul meskipun tidak ada teks yang dimasukkan
        autoWilayah.setThreshold(1);
        mJenisBibit.setThreshold(1);
        mKelasBibit.setThreshold(1);
        mLokasi.setThreshold(1);
        autonobibit.setThreshold(1);
        mJenisUnit.setThreshold(1);
        mReworking.setThreshold(1);
        // Atur threshold sesuai kebutuhan (misalnya 1 untuk memulai pencarian setelah 1 karakter)

        model = (PoolDippingModel) getIntent().getSerializableExtra("model");
        Log.d("bajakmodel", new Gson().toJson(model));
        presenter = new FormPengamatanPoolDippingPresenter(this);
        mSubmit.setOnClickListener(this);
        mAddSample.setOnClickListener(this);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()

        {
            @Override
            public void onCheckedChanged (RadioGroup group,int checkedId){
                if (checkedId == R.id.mYa) {
                    valueTerdipping = 1;
//                    showToast("Selected: Ya (Value: 1)");
                } else if (checkedId == R.id.mTidak) {
                    valueTerdipping = 0;
//                    showToast("Selected: Tidak (Value: 0)");
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadTemporaryData();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveTemporaryData();
    }

    private void saveTemporaryData() {
        PoolDippingModel draft = new PoolDippingModel();

        // isi header
        draft.setLOKASI(mLokasi.getText().toString());
        draft.setWILAYAH(autoWilayah.getText().toString());
        draft.setNO_SPK(mSPK.getText().toString());
        draft.setNO_LINE(mLine.getText().toString());
        draft.setMandor_bibit(mMandorBibit.getText().toString());
        draft.setReworking(mReworking.getText().toString());

        draft.setDATA(dataPlot); // simpan semua plot + sample

        TemporaryFormStorage.saveDraft(this, DRAFT_KEY, draft);
        Log.d("DraftSaved", new Gson().toJson(draft));
    }
    private void loadTemporaryData() {
        PoolDippingModel draft = TemporaryFormStorage.loadDraft(this, DRAFT_KEY, PoolDippingModel.class);
        if (draft != null) {
            Log.d("DraftLoaded", new Gson().toJson(draft));

            // restore header
            mLokasi.setText(draft.getLOKASI());
            autoWilayah.setText(draft.getWILAYAH());
            mSPK.setText(draft.getNO_SPK());
            mLine.setText(draft.getNO_LINE());
            mMandorBibit.setText(draft.getMandor_bibit());
            mReworking.setText(draft.getReworking());


            // restore plot + sample ke UI
            if (draft.getDATA() != null) {
                dataPlot = draft.getDATA();
                containerPlotData.removeAllViews();
                for (PlotModel p : dataPlot) {
                    for (SampleModel s : p.getSAMPLE()) {
                        restoreSampleView(s);
                    }
                }
            }

            // ✅ Tambahkan ini di akhir: rebind adapter biar dropdown aktif lagi
            mJenisBibit.post(() -> {
                ArrayAdapter<String> adapterJenisBibit = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, autojenisbibit2);
                mJenisBibit.setAdapter(adapterJenisBibit);
            });

            mKelasBibit.post(() -> {
                ArrayAdapter<String> adapterKelasBibit = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, autokelasbibit2);
                mKelasBibit.setAdapter(adapterKelasBibit);
            });

            mLokasi.post(() -> {
                ArrayAdapter<String> adapterLokasi = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, listLokPool);
                mLokasi.setAdapter(adapterLokasi);
            });

            mReworking.post(() -> {
                ArrayAdapter<String> adapterReworking = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, mReworking2);
                mReworking.setAdapter(adapterReworking);
            });

            autonobibit.post(() -> {
                ArrayAdapter<String> adapterAutonobibit = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, autoNObibit2);
                autonobibit.setAdapter(adapterAutonobibit);
            });

            mJenisUnit.post(() -> {
                ArrayAdapter<String> adapterJenisUnit = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, autojenisunit2);
                mJenisUnit.setAdapter(adapterJenisUnit);
            });

            autoWilayah.post(() -> {
                ArrayAdapter<String> adapterAutoWilayah = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, autoWilayah2);
                autoWilayah.setAdapter(adapterAutoWilayah);
            });

        }
    }

    private void restoreSampleView(SampleModel s) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.data_pooldipping_sample_field, null);

//        ((TextView) rowView.findViewById(R.id.mNoSample)).setText(String.valueOf(s.getNo_sample()));
//        ((TextView) rowView.findViewById(R.id.mKendaraan)).setText(s.getNo_kendaraan());
//        ((TextView) rowView.findViewById(R.id.mJenisUnit)).setText(s.getJenis_unit());
//        ((TextView) rowView.findViewById(R.id.mJenisBibit)).setText(s.getJenis_bibit());
//        ((TextView) rowView.findViewById(R.id.mKelasBibit)).setText(s.getKelas_bibit());
//        ((TextView) rowView.findViewById(R.id.autoNoBibit)).setText(s.getNomor_bibit());
//        ((TextView) rowView.findViewById(R.id.mAsalDO)).setText(s.getAsal_do());
//        ((TextView) rowView.findViewById(R.id.mKeteranganDO)).setText(s.getTujuan_do());
//        ((TextView) rowView.findViewById(R.id.mJumlahSample)).setText(String.valueOf(s.getJumlah_sampel()));
//        ((TextView) rowView.findViewById(R.id.mBibitNormal)).setText(String.valueOf(s.getBibit_normal()));
//        ((TextView) rowView.findViewById(R.id.mAfkir)).setText(String.valueOf(s.getBibit_afkir()));
//        ((TextView) rowView.findViewById(R.id.mPlot)).setText(String.valueOf(s.getPlot()));
//        ((TextView) rowView.findViewById(R.id.m1)).setText(String.valueOf(s.getBibit_1()));
//        ((TextView) rowView.findViewById(R.id.m2)).setText(String.valueOf(s.getBibit_2()));
//        ((TextView) rowView.findViewById(R.id.m3)).setText(String.valueOf(s.getBibit_3()));
//        ((TextView) rowView.findViewById(R.id.m4)).setText(String.valueOf(s.getBibit_4()));
//        ((TextView) rowView.findViewById(R.id.m5)).setText(String.valueOf(s.getBibit_5()));
//        ((TextView) rowView.findViewById(R.id.m6)).setText(String.valueOf(s.getBibit_6()));
//        ((TextView) rowView.findViewById(R.id.m7)).setText(String.valueOf(s.getBibit_7()));
//        ((TextView) rowView.findViewById(R.id.mOver)).setText(String.valueOf(s.getBibit_over()));
//        ((TextView) rowView.findViewById(R.id.mOverPlus)).setText(String.valueOf(s.getBibit_over_plus()));
//        ((TextView) rowView.findViewById(R.id.mHasil)).setText(String.valueOf(s.getHasil()));
//        ((TextView) rowView.findViewById(R.id.mInfoBibit)).setText(s.getInformasi_bibit_terdipping() == 1 ? "Ya" : "Tidak");

        final TextView no_sample = rowView.findViewById(R.id.mNoSample);
        final TextView kendaraan = rowView.findViewById(R.id.mKendaraan);
        final TextView jenis_unit = rowView.findViewById(R.id.mJenisUnit);
        final TextView jenis_bibit = rowView.findViewById(R.id.mJenisBibit);
        final TextView kelas_bibit = rowView.findViewById(R.id.mKelasBibit);
        final TextView no_bibit = rowView.findViewById(R.id.autoNoBibit);
        final TextView asal_do = rowView.findViewById(R.id.mAsalDO);
        final TextView tujuan_do = rowView.findViewById(R.id.mKeteranganDO);
        final TextView jumlah_sample = rowView.findViewById(R.id.mJumlahSample);
        final TextView bibit_normal = rowView.findViewById(R.id.mBibitNormal);
        final TextView afkir = rowView.findViewById(R.id.mAfkir);
        final TextView no_plot = rowView.findViewById(R.id.mPlot);
        final TextView bibit_1 = rowView.findViewById(R.id.m1);
        final TextView bibit_2 = rowView.findViewById(R.id.m2);
        final TextView bibit_3 = rowView.findViewById(R.id.m3);
        final TextView bibit_4 = rowView.findViewById(R.id.m4);
        final TextView bibit_5 = rowView.findViewById(R.id.m5);
        final TextView bibit_6 = rowView.findViewById(R.id.m6);
        final TextView bibit_7 = rowView.findViewById(R.id.m7);
        final TextView bibit_over = rowView.findViewById(R.id.mOver);
        final TextView bibit_over_plus = rowView.findViewById(R.id.mOverPlus);
        final TextView hasil = rowView.findViewById(R.id.mHasil);
        final TextView info_bibit = rowView.findViewById(R.id.mInfoBibit);

        no_sample.setText(String.valueOf(s.getNo_sample()));
        kendaraan.setText(s.getNo_kendaraan());
        jenis_unit.setText(s.getJenis_unit());
        jenis_bibit.setText(s.getJenis_bibit());
        kelas_bibit.setText(s.getKelas_bibit());
        no_bibit.setText(s.getNomor_bibit());
        asal_do.setText(s.getAsal_do());
        tujuan_do.setText(s.getTujuan_do());
        jumlah_sample.setText(String.valueOf(s.getJumlah_sampel()));
        bibit_normal.setText(String.valueOf(s.getBibit_normal()));
        afkir.setText(String.valueOf(s.getBibit_afkir()));
        no_plot.setText(String.valueOf(s.getPlot()));
        bibit_1.setText(String.valueOf(s.getBibit_1()));
        bibit_2.setText(String.valueOf(s.getBibit_2()));
        bibit_3.setText(String.valueOf(s.getBibit_3()));
        bibit_4.setText(String.valueOf(s.getBibit_4()));
        bibit_5.setText(String.valueOf(s.getBibit_5()));
        bibit_6.setText(String.valueOf(s.getBibit_6()));
        bibit_7.setText(String.valueOf(s.getBibit_7()));
        bibit_over.setText(String.valueOf(s.getBibit_over()));
        bibit_over_plus.setText(String.valueOf(s.getBibit_over_plus()));
        hasil.setText(String.valueOf(s.getHasil()));
        info_bibit.setText(s.getInformasi_bibit_terdipping() == 1 ? "Ya" : "Tidak");


        containerPlotData.addView(rowView, 0);
    }

    private void clearForm() {
        // Kosongkan semua input header
        mSPK.setText("");
        mPlot.setText("1");
        mLine.setText("");
        mLokasi.setText("");
        autoWilayah.setText("");
        mMandorBibit.setText("");
        mKendaraan.setText("");
        mJenisUnit.setText("");
        mJenisBibit.setText("");
        mKelasBibit.setText("");
        autonobibit.setText("");
        mAsalDO.setText("");
        mTujuanDO.setText("");
        mReworking.setText("");
        mJumlahSample.setText("");
        mBibitNormal.setText("");
        mAfkir.setText("");
        m1.setText("");
        m2.setText("");
        m3.setText("");
        m4.setText("");
        m5.setText("");
        m6.setText("");
        m7.setText("");
        mOver.setText("");
        mOverPlus.setText("");
        mKet.setText("");
        mHasil.setText("");
        mTglBibitCampur.setText("");

        // Reset radio button
        radioGroup.clearCheck();
        valueTerdipping = -1;

        // Kosongkan data di memory
        dataPlot.clear();
        dataSample.clear();

        // Kosongkan container sample di UI
        containerPlotData.removeAllViews();

        // Reset model juga
        model = new PoolDippingModel();

        // Hapus draft tersimpan biar gak muncul lagi
        TemporaryFormStorage.clearDraft(this, "draft_pool_dipping");

        Log.d("FormReset", "Form PoolDipping cleared");
    }


    void addPlotForm() {

        //Tidak boleh kosong
        if (mSPK.getText().toString().equals("")) {
            Toast.makeText(this, "No SPK tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mLine.getText().toString().equals("")) {
            Toast.makeText(this, "No Line tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mLokasi.getText().toString().equals("")) {
            Toast.makeText(this, "Lokasi Dipping tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mMandorBibit.getText().toString().equals("")){
            Toast.makeText(this, "Mandor Bibit tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (autoWilayah.getText().toString().equals("")){
            Toast.makeText(this, "Wilayah tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mReworking.getText().toString().equals("")) {
            Toast.makeText(this, "Status Reworking tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
//        else if (mPlot.getText().toString().equals("")){
//            Toast.makeText(this, "No Data tidak boleh kosong", Toast.LENGTH_SHORT).show();
//        }
//        else if (mTglBibitCampur.getText().toString().equals("")) {
//            Toast.makeText(this, "Tgl Bibit Campur tidak boleh kosong", Toast.LENGTH_SHORT).show();
//        }
        else if (mKendaraan.getText().toString().equals("")) {
            Toast.makeText(this, "No Kendaraan tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mJenisUnit.getText().toString().equals("")) {
            Toast.makeText(this, "Jenis Unit tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mAsalDO.getText().toString().equals("")) {
            Toast.makeText(this, "Asal DO tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mTujuanDO.getText().toString().equals("")) {
            Toast.makeText(this, "Tujuan DO tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mJenisBibit.getText().toString().equals("")) {
            Toast.makeText(this, "Jenis Bibit tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mKelasBibit.getText().toString().equals("")) {
            Toast.makeText(this, "Kelas Bibit tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (autonobibit.getText().toString().equals("")) {
            Toast.makeText(this, "No Bibit tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mJumlahSample.getText().toString().equals("")) {
            Toast.makeText(this, "Total Sample tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mBibitNormal.getText().toString().equals("")) {
            Toast.makeText(this, "Bibit Normal tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else {
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
        rowView = inflater.inflate(R.layout.data_pooldipping_sample_field, null);

        final TextView mNoSample = rowView.findViewById(R.id.mNoSample);
        final TextView kendaraan = rowView.findViewById(R.id.mKendaraan);
        final TextView jenisunit = rowView.findViewById(R.id.mJenisUnit);
        final TextView jenisBibit = rowView.findViewById(R.id.mJenisBibit);
        final TextView kelasbibit = rowView.findViewById(R.id.mKelasBibit);
        final TextView nobibit = rowView.findViewById(R.id.autoNoBibit);
        final TextView asalDO = rowView.findViewById(R.id.mAsalDO);
        final TextView ketDO = rowView.findViewById(R.id.mKeteranganDO);
        final TextView jumlahSample = rowView.findViewById(R.id.mJumlahSample);
        final TextView bibitNormal = rowView.findViewById(R.id.mBibitNormal);
        final TextView afkir = rowView.findViewById(R.id.mAfkir);
        final TextView no_plot = rowView.findViewById(R.id.mPlot);
        final TextView bibit1 = rowView.findViewById(R.id.m1);
        final TextView bibit2 = rowView.findViewById(R.id.m2);
        final TextView bibit3 = rowView.findViewById(R.id.m3);
        final TextView bibit4 = rowView.findViewById(R.id.m4);
        final TextView bibit5 = rowView.findViewById(R.id.m5);
        final TextView bibit6 = rowView.findViewById(R.id.m6);
        final TextView bibit7 = rowView.findViewById(R.id.m7);
        final TextView over = rowView.findViewById(R.id.mOver);
        final TextView overplus = rowView.findViewById(R.id.mOverPlus);
        final TextView hasil = rowView.findViewById(R.id.mHasil);
        final TextView infoBibit = rowView.findViewById(R.id.mInfoBibit);

//        mNoSample.setText(mNoSample.getText().toString());
        kendaraan.setText(mKendaraan.getText().toString());
        jenisunit.setText(mJenisUnit.getText().toString());
        jenisBibit.setText(mJenisBibit.getText().toString());
        kelasbibit.setText(mKelasBibit.getText().toString());
        nobibit.setText(autonobibit.getText().toString());
        asalDO.setText(mAsalDO.getText().toString());
        ketDO.setText(mTujuanDO.getText().toString());
        jumlahSample.setText(mJumlahSample.getText().toString());
        bibitNormal.setText(mBibitNormal.getText().toString());
        afkir.setText(mAfkir.getText().toString());
        no_plot.setText(mPlot.getText().toString());

        over.setText(mOver.getText().toString());
        overplus.setText(mOverPlus.getText().toString());
        bibit1.setText(m1.getText().toString());
        bibit2.setText(m2.getText().toString());
        bibit3.setText(m3.getText().toString());
        bibit4.setText(m4.getText().toString());
        bibit5.setText(m5.getText().toString());
        bibit6.setText(m6.getText().toString());
        bibit7.setText(m7.getText().toString());


        hasil.setText(mHasil.getText().toString());
        infoBibit.setText(valueTerdipping == 1 ? "Ya" : valueTerdipping == 0 ? "Tidak" : "-");

        List<Integer> existingSamples = new ArrayList<>();
        for (SampleModel sample : dataSample) {
            if (sample.getPlot() == Integer.parseInt(mPlot.getText().toString())) {
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

        mNoSample.setText(String.valueOf(nextSampleNumber));

        sampleModel.setNo_sample(Integer.parseInt(mNoSample.getText().toString()));
        sampleModel.setNo_spk(model.getNO_SPK());
        sampleModel.setLokasi(mLokasi.getText().toString());
        sampleModel.setNo_spk2(mSPK.getText().toString());
        sampleModel.setNo_line(mLine.getText().toString());
        sampleModel.setMandor_bibit(mMandorBibit.getText().toString());
        sampleModel.setNo_kendaraan(mKendaraan.getText().toString());
        sampleModel.setJenis_unit(mJenisUnit.getText().toString());
        sampleModel.setAsal_do(mAsalDO.getText().toString());
        sampleModel.setTujuan_do(mTujuanDO.getText().toString());
        sampleModel.setKelas_bibit(mKelasBibit.getText().toString());
        sampleModel.setNomor_bibit(autonobibit.getText().toString());
        sampleModel.setJenis_bibit(mJenisBibit.getText().toString());
        sampleModel.setJumlah_sampel(Float.parseFloat(mJumlahSample.getText().toString()));
        sampleModel.setBibit_normal(Integer.parseInt(mBibitNormal.getText().toString()));
        sampleModel.setInformasi_bibit_terdipping(valueTerdipping);
        sampleModel.setWil(autoWilayah.getText().toString());
        sampleModel.setReworking(mReworking.getText().toString());

//        String selectedDateString = mTglBibitCampur.getText().toString();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
//        try {
//            Date selectedDate = dateFormat.parse(selectedDateString); // Konversi String ke Date
////            sampleModel.setUpdate_peta(selectedDate); // Set ke model
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//            Toast.makeText(this, "Format tanggal tidak valid", Toast.LENGTH_SHORT).show();
//        }
//        sampleModel.setTanggal_ditemukan_bibit_campur(selectedDateString);

//        String selectedDateString = mTglBibitCampur.getText().toString().trim();
//
//        if (selectedDateString.isEmpty()) {
//            sampleModel.setTanggal_ditemukan_bibit_campur(null); // ✅ kalau kosong jadi null
//        } else {
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
//            try {
//                Date selectedDate = dateFormat.parse(selectedDateString);
//                sampleModel.setTanggal_ditemukan_bibit_campur(selectedDateString);
//            } catch (ParseException e) {
//                e.printStackTrace();
//                sampleModel.setTanggal_ditemukan_bibit_campur(null); // ✅ kalau format salah juga null
//                Toast.makeText(this, "Format tanggal tidak valid", Toast.LENGTH_SHORT).show();
//            }
//        }

        String selectedDateString = mTglBibitCampur.getText().toString().trim();

        if(selectedDateString.isEmpty()){
            selectedDateString = "";
        }

        sampleModel.setTanggal_ditemukan_bibit_campur(selectedDateString);



        sampleModel.setBibit_afkir(parseIntDefault(mAfkir.getText().toString(), 0));
        sampleModel.setBibit_1(parseIntDefault(m1.getText().toString(), 0));
        sampleModel.setBibit_2(parseIntDefault(m2.getText().toString(), 0));
        sampleModel.setBibit_3(parseIntDefault(m3.getText().toString(), 0));
        sampleModel.setBibit_4(parseIntDefault(m4.getText().toString(), 0));
        sampleModel.setBibit_5(parseIntDefault(m5.getText().toString(), 0));
        sampleModel.setBibit_6(parseIntDefault(m6.getText().toString(), 0));
        sampleModel.setBibit_7(parseIntDefault(m7.getText().toString(), 0));
        sampleModel.setBibit_over(parseIntDefault(mOver.getText().toString(), 0));
        sampleModel.setBibit_over_plus(parseIntDefault(mOverPlus.getText().toString(), 0));
        sampleModel.setPlot(Integer.parseInt(mPlot.getText().toString()));
        sampleModel.setHasil(Float.parseFloat(mHasil.getText().toString()));

        sampleModel.setKeterangan(
                mKet.getText().toString().trim().isEmpty() ? "-" : mKet.getText().toString().trim()
        );

//        sampleModel.setPlot(
//                mPlot.getText().toString().trim().isEmpty() ? 0 : Integer.parseInt(mPlot.getText().toString().trim())
//        );

//        sampleModel.setPlot(Integer.parseInt(mPlot.getText().toString().trim()));



//        sampleModel.setKeterangan(mKeterangan.getText().toString().contains("")
//                ? "-" : mKeterangan.getText().toString());

        dataSample.add(sampleModel);
        Log.d("datanyanih", new Gson().toJson(model));
        containerPlotData.addView(rowView, 0);

        //Hapus Saat Apply
        Toast.makeText(this, "Data Sudah Ditambahkan", Toast.LENGTH_SHORT).show();
        mAsalDO.getText().clear();
        mTujuanDO.getText().clear();
        mJumlahSample.getText().clear();
        mBibitNormal.getText().clear();
        mAfkir.getText().clear();
        mKendaraan.getText().clear();
        mOver.getText().clear();
        mOverPlus.getText().clear();
        m1.getText().clear();
        m2.getText().clear();
        m3.getText().clear();
        m4.getText().clear();
        m5.getText().clear();
        m6.getText().clear();
        m7.getText().clear();

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
            if (dataSample.get(i).getPlot() == plotToDelete &&
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
        if(dataPlot.size()>0){
//        sampleModel.setKeterangan(mKeterangan.getText().toString());
//        plotModel.setSAMPLE(dataSample);
            model.setLOKASI(mLokasi.getText().toString());
            model.setWILAYAH(autoWilayah.getText().toString());
            model.setDATA(dataPlot);
            Log.d("dataBody" ,new Gson().toJson(model));
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
        String message = "Pool Dipping"
                + ", Lokasi: " + mLokasi.getText().toString()
                + "\nTanggal: " + generateTglSekarang()
                + "\nWaktu: " + currentTime
                + "\n"+getString(R.string.versi_apps);

        SweetDialogs.commonSuccessWithIntent(this, message, string -> {
            goToListPengamatan(this);
        });
        TemporaryFormStorage.clearDraft(this, DRAFT_KEY);
        clearForm();
    }

    @Override
    public void onCreateFailed(String eror) {

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
    public void onNetworkError(String cause,String data) {
        Log.e("errornya", cause);
        Log.e("errornya", cause);
        SQLiteHelper dbHelper = new SQLiteHelper(this);
        dbHelper.saveChopperData(data,model.getNO_SPK());  // Assuming 'data' is a JSON string

        Log.d("Saved data", "Data saved to SQLite: " + data);

        // Show a dialog indicating that the data has been saved offline
        SweetDialogs.commonWarningWithIntent(this,"Anda Tidak ada Koneksi Internet",App.getApplication().getString(R.string.notif_offline_mode),string -> startActivity(new Intent(this, c_1_home.class)));

        TemporaryFormStorage.clearDraft(this, DRAFT_KEY);
        clearForm();
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

    private static final String[] autokelasbibit2 = new String[]{
            "Super Kecil", "Kecil", "Sedang", "Besar", " Extra Besar"
    };

    private static final String[] autojenisbibit2 = new String[]{
            "Sucker", "Crown", "Crown Storing", "Nursery Sucker Plus"
    };

    private static final String[] listLokPool = new String[]{
            "PD01", "PD02", "Lakop", "PD03A", "PD04", "PD05", "PD06"
    };
    private static final String[] mReworking2 = new String[]{
            "Sebelum Reworking", "Sesudah Reworking"
    };
    private static final String[] autojenisunit2 = new String[]{
            "Fuso", "Colt Diesel"
    };
    private static final String[] autoNObibit2 = new String[]{
            "0+", "0", "1", "2", "3", "4", "5", "6", "7"
    };
    private static final String[] autoWilayah2 = new String[]{
            "AW01", "AW02", "AW03", "AW04", "AW05", "AW06", "AW07", "AW08", "AW09", "AW10","AW11", "AW12", "AW13", "AW14", "AW15", "AW16", "AW17", "AW18", "AW19", "AW20", "AW21", "AW22", "AW23"
    };

}