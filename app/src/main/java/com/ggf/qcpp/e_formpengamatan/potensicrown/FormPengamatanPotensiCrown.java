package com.ggf.qcpp.e_formpengamatan.potensicrown;

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
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ggf.qcpp.App;
import com.ggf.qcpp.R;
import com.ggf.qcpp.c_home.c_1_home;
import com.ggf.qcpp.e_formpengamatan.kebersihanpanen.FormPengamatanKebersihanPanenPresenter;
import com.ggf.qcpp.e_formpengamatan.kebersihanpanen.model.PanenModel;
import com.ggf.qcpp.e_formpengamatan.potensicrown.FormPengamatanPotensiCrown;
import com.ggf.qcpp.e_formpengamatan.potensicrown.model.PotensiCrownModel;
import com.ggf.qcpp.e_formpengamatan.potensicrown.model.PlotModel;
import com.ggf.qcpp.e_formpengamatan.potensicrown.model.SampleModel;
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

public class FormPengamatanPotensiCrown extends AppCompatActivity implements View.OnClickListener, IFormPengamatanPotensiCrownView  {

    @BindView(R.id.mSubmit)
    Button mSubmit;

    @BindView(R.id.mAddSample)
    ImageView mAddSample;

    @BindView(R.id.mSPK)
    EditText mSPK;

    @BindView(R.id.mLine)
    EditText mLine;

    @BindView(R.id.containerPlotData)
    LinearLayout containerPlotData;

    @BindView(R.id.mLokasi)
    EditText mLokasi;
    @BindView(R.id.autoWilayah)
    AutoCompleteTextView autoWilayah;
    @BindView(R.id.mPlot)
    EditText mPlot;

    @BindView(R.id.mLuasPlot)
    EditText mLuasPlot;
    @BindView(R.id.mUpdatePeta)
    EditText mUpdatePeta;
    @BindView(R.id.mRencanaPanen)
    EditText mRencanaPanen;


    @BindView(R.id.mDaunLemas)
    EditText mDaunLemas;
    @BindView(R.id.mDaunBerduri)
    EditText mDaunBerduri;
    @BindView(R.id.mTitikTumbuhLebih)
    EditText mTitikTumbuhLebih;
    @BindView(R.id.mTumbuhTidakAda)
    EditText mTumbuhTidakAda;
    @BindView(R.id.mUnderSize)
    EditText mUnderSize;
    @BindView(R.id.mRusakMekanis)
    EditText mRusakMekanis;
    @BindView(R.id.mBusuk)
    EditText mBusuk;
    @BindView(R.id.mPenyakit)
    EditText mPenyakit;
    @BindView(R.id.mLayuPermanen)
    EditText mLayuPermanen;
    @BindView(R.id.mCabangTiga)
    EditText mCabangTiga;
    @BindView(R.id.mTotal)
    EditText mTotal;


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
    @BindView(R.id.mTot)
    EditText mTot;

    @BindView(R.id.mNormal)
    EditText mNormal;
    @BindView(R.id.mCabang)
    EditText mCabang;
    @BindView(R.id.mLiar)
    EditText mLiar;

    @BindView(R.id.mCrownCabangDua)
    EditText mCrownCabangDua;
    @BindView(R.id.mCrownCabangTiga)
    EditText mCrownCabangTiga;

    @BindView(R.id.mTanamanMandul)
    EditText mTanamanMandul;

    @BindView(R.id.mKet)
    EditText mKet;

    private static final String DRAFT_KEY = "draft_potensi_crown";
    int index = 1;
    View rowView;
    View rowViewPlot;
    View viewnya = null;
    String plot = "0";
    PotensiCrownModel model;

    List<PlotModel> dataPlot = new ArrayList<>();
    List<SampleModel> dataSample = new ArrayList<>();

    SweetAlertDialog sweetAlertDialog;
    SampleModel sampleModel = null;
    PlotModel plotModel = null;
    FormPengamatanPotensiCrownPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form_pengamatan_potensi_crown);

        ButterKnife.bind(this);
        presenter = new FormPengamatanPotensiCrownPresenter(this);
        model = (PotensiCrownModel) getIntent().getSerializableExtra("model");

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
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

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        mRencanaPanen.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        // Tambahkan 1 ke bulan (karena bulan dimulai dari 0)
                        String selectedDate = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay;
                        mRencanaPanen.setText(selectedDate); // Set tanggal di EditText
                    },
                    year, month, day);

            datePickerDialog.show(); // Tampilkan dialog
        });

        Calendar calendar2 = Calendar.getInstance();
        int year2 = calendar2.get(Calendar.YEAR);
        int month2 = calendar2.get(Calendar.MONTH);
        int day2 = calendar2.get(Calendar.DAY_OF_MONTH);
        mUpdatePeta.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        // Tambahkan 1 ke bulan (karena bulan dimulai dari 0)
                        String selectedDate = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay;
                        mUpdatePeta.setText(selectedDate); // Set tanggal di EditText
                    },
                    year2, month2, day2);

            datePickerDialog.show(); // Tampilkan dialog
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, autoWilayah2);
        autoWilayah.setAdapter(adapter);

        model = (PotensiCrownModel) getIntent().getSerializableExtra("model");
        Log.d("TanamModel", new Gson().toJson(model));
        presenter = new FormPengamatanPotensiCrownPresenter(this);

        EditText lokasiUppercase = findViewById(R.id.mLokasi);
        lokasiUppercase.setFilters(new InputFilter[] {new InputFilter.AllCaps()});

        // Menonaktifkan input teks, tetapi dropdown masih muncul
        autoWilayah.setKeyListener(null);

        // Memastikan dropdown muncul meskipun tidak ada teks yang dimasukkan
        autoWilayah.setThreshold(1);

        mSubmit.setOnClickListener(this);
        mAddSample.setOnClickListener(this);
    }
    private void saveTemporaryData() {
        PotensiCrownModel draft = new PotensiCrownModel();
        draft.setNO_SPK(mSPK.getText().toString());
        draft.setNO_LINE(mLine.getText().toString());
        draft.setLOKASI(mLokasi.getText().toString());
        draft.setWILAYAH(autoWilayah.getText().toString());

        draft.setUpdate_peta(mUpdatePeta.getText().toString());
        draft.setRencana_panen(mRencanaPanen.getText().toString());


        draft.setDATA(dataPlot);

        TemporaryFormStorage.saveDraft(this, DRAFT_KEY, draft);
        Log.d("DraftSave", "Draft potensi crown saved: " + new Gson().toJson(draft));
    }
    private void loadTemporaryData() {
        PotensiCrownModel draft = TemporaryFormStorage.loadDraft(this, DRAFT_KEY, PotensiCrownModel.class);
        if (draft != null) {
            Log.d("DraftLoad", "Draft potensi crown loaded: " + new Gson().toJson(draft));

            mSPK.setText(draft.getNO_SPK());
            mLine.setText(draft.getNO_LINE());
            mLokasi.setText(draft.getLOKASI());
            autoWilayah.setText(draft.getWILAYAH());

            mUpdatePeta.setText(draft.getUpdate_peta());
            mRencanaPanen.setText(draft.getRencana_panen());

            containerPlotData.removeAllViews();
            dataPlot.clear();

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
                ArrayAdapter<String> adapterautoWilayah = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, autoWilayah2);
                autoWilayah.setAdapter(adapterautoWilayah);
            });


        }
    }
    private void clearForm() {
        mSPK.setText("");
        mLine.setText("");
        mLokasi.setText("");
        autoWilayah.setText("");
        mUpdatePeta.setText("");
        mRencanaPanen.setText("");
        mDaunLemas.setText("");
        mDaunBerduri.setText("");
        mTitikTumbuhLebih.setText("");
        mTumbuhTidakAda.setText("");
        mUnderSize.setText("");
        mRusakMekanis.setText("");
        mBusuk.setText("");
        mPenyakit.setText("");
        mLayuPermanen.setText("");
        mCabangTiga.setText("");
        mTotal.setText("");
        m1.setText("");
        m2.setText("");
        m3.setText("");
        m4.setText("");
        m5.setText("");
        m6.setText("");
        m7.setText("");
        mTot.setText("");
        mNormal.setText("");
        mCabang.setText("");
        mLiar.setText("");
        mCrownCabangDua.setText("");
        mCrownCabangTiga.setText("");
        mTanamanMandul.setText("");
        mKet.setText("");

        dataPlot.clear();
        dataSample.clear();
        containerPlotData.removeAllViews();

        model = new PotensiCrownModel();


        Log.d("FormReset", "Form potensi crown cleared");
    }
    private void restoreSampleView(SampleModel s) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.data_potensicrown_sample_field, null);

//        ((TextView) rowView.findViewById(R.id.mPlot)).setText(String.valueOf(s.getPlot()));
//        ((TextView) rowView.findViewById(R.id.mNoSample)).setText(String.valueOf(s.getNo_sample()));
//        ((TextView) rowView.findViewById(R.id.mDaunLemas)).setText(String.valueOf(s.getDaun_lemas()));
//        ((TextView) rowView.findViewById(R.id.mDaunBerduri)).setText(String.valueOf(s.getDaun_berduri()));
//        ((TextView) rowView.findViewById(R.id.mTitikTumbuhLebih)).setText(String.valueOf(s.getTumbuh_lebih_dari_1()));
//        ((TextView) rowView.findViewById(R.id.mTumbuhTidakAda)).setText(String.valueOf(s.getTumbuh_tidak_ada()));
//        ((TextView) rowView.findViewById(R.id.mUnderSize)).setText(String.valueOf(s.getUnder_size()));
//        ((TextView) rowView.findViewById(R.id.mRusakMekanis)).setText(String.valueOf(s.getRusak_mekanis()));
//        ((TextView) rowView.findViewById(R.id.mBusuk)).setText(String.valueOf(s.getBusuk()));
//        ((TextView) rowView.findViewById(R.id.mPenyakit)).setText(String.valueOf(s.getBergejala()));
//        ((TextView) rowView.findViewById(R.id.mLayuPermanen)).setText(String.valueOf(s.getLayu()));
//        ((TextView) rowView.findViewById(R.id.mCabangTiga)).setText(String.valueOf(s.getCabang_lebih_dari_3()));
//        ((TextView) rowView.findViewById(R.id.mTotal)).setText(String.valueOf(s.getTotal_afkir()));
//
//        ((TextView) rowView.findViewById(R.id.m1)).setText(String.valueOf(s.getBibit_10_sampai_11()));
//        ((TextView) rowView.findViewById(R.id.m2)).setText(String.valueOf(s.getBibit_12_sampai_14()));
//        ((TextView) rowView.findViewById(R.id.m3)).setText(String.valueOf(s.getBibit_15_sampai_17()));
//        ((TextView) rowView.findViewById(R.id.m4)).setText(String.valueOf(s.getBibit_18_sampai_24()));
//        ((TextView) rowView.findViewById(R.id.m5)).setText(String.valueOf(s.getBibit_25_sampai_33()));
//        ((TextView) rowView.findViewById(R.id.m6)).setText(String.valueOf(s.getBibit_34_sampai_38()));
//        ((TextView) rowView.findViewById(R.id.m7)).setText(String.valueOf(s.getBibit_lebih_dari_38()));
//        ((TextView) rowView.findViewById(R.id.mTot)).setText(String.valueOf(s.getTotal_bibit()));
//
//        ((TextView) rowView.findViewById(R.id.mNormal)).setText(String.valueOf(s.getNormal()));
//        ((TextView) rowView.findViewById(R.id.mCabang)).setText(String.valueOf(s.getCabang()));
//        ((TextView) rowView.findViewById(R.id.mLiar)).setText(String.valueOf(s.getLiar()));
//        ((TextView) rowView.findViewById(R.id.mCrownCabangDua)).setText(String.valueOf(s.getCrown_cabang_2()));
//        ((TextView) rowView.findViewById(R.id.mCrownCabangTiga)).setText(String.valueOf(s.getCrown_cabang_3()));
//        ((TextView) rowView.findViewById(R.id.mTanamanMandul)).setText(String.valueOf(s.getTanaman_mandul()));

        final TextView plot = rowView.findViewById(R.id.mPlot);
        final TextView noSample = rowView.findViewById(R.id.mNoSample);
        final TextView daunLemas = rowView.findViewById(R.id.mDaunLemas);
        final TextView daunBerduri = rowView.findViewById(R.id.mDaunBerduri);
        final TextView titikTumbuhLebih = rowView.findViewById(R.id.mTitikTumbuhLebih);
        final TextView tumbuhTidakAda = rowView.findViewById(R.id.mTumbuhTidakAda);
        final TextView underSize = rowView.findViewById(R.id.mUnderSize);
        final TextView rusakMekanis = rowView.findViewById(R.id.mRusakMekanis);
        final TextView busuk = rowView.findViewById(R.id.mBusuk);
        final TextView penyakit = rowView.findViewById(R.id.mPenyakit);
        final TextView layuPermanen = rowView.findViewById(R.id.mLayuPermanen);
        final TextView cabangTiga = rowView.findViewById(R.id.mCabangTiga);
        final TextView total = rowView.findViewById(R.id.mTotal);

        final TextView m1 = rowView.findViewById(R.id.m1);
        final TextView m2 = rowView.findViewById(R.id.m2);
        final TextView m3 = rowView.findViewById(R.id.m3);
        final TextView m4 = rowView.findViewById(R.id.m4);
        final TextView m5 = rowView.findViewById(R.id.m5);
        final TextView m6 = rowView.findViewById(R.id.m6);
        final TextView m7 = rowView.findViewById(R.id.m7);
        final TextView mTot = rowView.findViewById(R.id.mTot);

        final TextView normal = rowView.findViewById(R.id.mNormal);
        final TextView cabang = rowView.findViewById(R.id.mCabang);
        final TextView liar = rowView.findViewById(R.id.mLiar);
        final TextView crownCabangDua = rowView.findViewById(R.id.mCrownCabangDua);
        final TextView crownCabangTiga = rowView.findViewById(R.id.mCrownCabangTiga);
        final TextView tanamanMandul = rowView.findViewById(R.id.mTanamanMandul);

        plot.setText(String.valueOf(s.getPlot()));
        noSample.setText(String.valueOf(s.getNo_sample()));
        daunLemas.setText(String.valueOf(s.getDaun_lemas()));
        daunBerduri.setText(String.valueOf(s.getDaun_berduri()));
        titikTumbuhLebih.setText(String.valueOf(s.getTumbuh_lebih_dari_1()));
        tumbuhTidakAda.setText(String.valueOf(s.getTumbuh_tidak_ada()));
        underSize.setText(String.valueOf(s.getUnder_size()));
        rusakMekanis.setText(String.valueOf(s.getRusak_mekanis()));
        busuk.setText(String.valueOf(s.getBusuk()));
        penyakit.setText(String.valueOf(s.getBergejala()));
        layuPermanen.setText(String.valueOf(s.getLayu()));
        cabangTiga.setText(String.valueOf(s.getCabang_lebih_dari_3()));
        total.setText(String.valueOf(s.getTotal_afkir()));

        m1.setText(String.valueOf(s.getBibit_10_sampai_11()));
        m2.setText(String.valueOf(s.getBibit_12_sampai_14()));
        m3.setText(String.valueOf(s.getBibit_15_sampai_17()));
        m4.setText(String.valueOf(s.getBibit_18_sampai_24()));
        m5.setText(String.valueOf(s.getBibit_25_sampai_33()));
        m6.setText(String.valueOf(s.getBibit_34_sampai_38()));
        m7.setText(String.valueOf(s.getBibit_lebih_dari_38()));
        mTot.setText(String.valueOf(s.getTotal_bibit()));

        normal.setText(String.valueOf(s.getNormal()));
        cabang.setText(String.valueOf(s.getCabang()));
        liar.setText(String.valueOf(s.getLiar()));
        crownCabangDua.setText(String.valueOf(s.getCrown_cabang_2()));
        crownCabangTiga.setText(String.valueOf(s.getCrown_cabang_3()));
        tanamanMandul.setText(String.valueOf(s.getTanaman_mandul()));


        containerPlotData.addView(rowView, 0);
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveTemporaryData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadTemporaryData();
    }

    void addPlotForm() {

        //Tidak boleh kosong
        if (mSPK.getText().toString().equals("")) {
            Toast.makeText(this, "No SPK tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mLine.getText().toString().equals("")) {
            Toast.makeText(this, "No Line tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mLokasi.getText().toString().equals("")) {
            Toast.makeText(this, "Lokasi tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (autoWilayah.getText().toString().equals("")) {
            Toast.makeText(this, "Wilayah tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mPlot.getText().toString().equals("")) {
            Toast.makeText(this, "No Plot tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mLuasPlot.getText().toString().equals("")) {
            Toast.makeText(this, "Luas Plot tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mUpdatePeta.getText().toString().equals("")) {
            Toast.makeText(this, "Update Peta tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mRencanaPanen.getText().toString().equals("")) {
            Toast.makeText(this, "Rencana Panen tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }  else {
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
        rowView = inflater.inflate(R.layout.data_potensicrown_sample_field, null);

        final TextView plot = rowView.findViewById(R.id.mPlot);
        final TextView no_sample = rowView.findViewById(R.id.mNoSample);

        final TextView daunlemas = rowView.findViewById(R.id.mDaunLemas);
        final TextView daunberduri = rowView.findViewById(R.id.mDaunBerduri);
        final TextView titiktumbuhkurang = rowView.findViewById(R.id.mTitikTumbuhLebih);
        final TextView tumbuhtidakada = rowView.findViewById(R.id.mTumbuhTidakAda);
        final TextView undersize = rowView.findViewById(R.id.mUnderSize);
        final TextView rusakmekanis = rowView.findViewById(R.id.mRusakMekanis);
        final TextView busuk = rowView.findViewById(R.id.mBusuk);
        final TextView gejala = rowView.findViewById(R.id.mPenyakit);
        final TextView layupermanen = rowView.findViewById(R.id.mLayuPermanen);
        final TextView cabangtiga = rowView.findViewById(R.id.mCabangTiga);
        final TextView totalafkir = rowView.findViewById(R.id.mTotal);

        final TextView satu = rowView.findViewById(R.id.m1);
        final TextView dua = rowView.findViewById(R.id.m2);
        final TextView tiga = rowView.findViewById(R.id.m3);
        final TextView empat = rowView.findViewById(R.id.m4);
        final TextView lima = rowView.findViewById(R.id.m5);
        final TextView enam = rowView.findViewById(R.id.m6);
        final TextView tujuh = rowView.findViewById(R.id.m7);
        final TextView totalbibit = rowView.findViewById(R.id.mTot);

        final TextView normal = rowView.findViewById(R.id.mNormal);
        final TextView cabang = rowView.findViewById(R.id.mCabang);
        final TextView liar = rowView.findViewById(R.id.mLiar);

        final TextView crowndua = rowView.findViewById(R.id.mCrownCabangDua);
        final TextView crowntiga = rowView.findViewById(R.id.mCrownCabangTiga);

        final TextView mandul = rowView.findViewById(R.id.mTanamanMandul);


        plot.setText(mPlot.getText().toString());
        daunlemas.setText(mDaunLemas.getText().toString());
        daunberduri.setText(mDaunBerduri.getText().toString());
        titiktumbuhkurang.setText(mTitikTumbuhLebih.getText().toString());
        tumbuhtidakada.setText(mTumbuhTidakAda.getText().toString());
        undersize.setText(mUnderSize.getText().toString());
        rusakmekanis.setText(mRusakMekanis.getText().toString());
        busuk.setText(mBusuk.getText().toString());
        gejala.setText(mPenyakit.getText().toString());
        layupermanen.setText(mLayuPermanen.getText().toString());
        cabangtiga.setText(mCabangTiga.getText().toString());
        totalafkir.setText(mTotal.getText().toString());
        satu.setText(m1.getText().toString());
        dua.setText(m2.getText().toString());
        tiga.setText(m3.getText().toString());
        empat.setText(m4.getText().toString());
        lima.setText(m5.getText().toString());
        enam.setText(m6.getText().toString());
        tujuh.setText(m7.getText().toString());
        totalbibit.setText(mTot.getText().toString());
        normal.setText(mNormal.getText().toString());
        cabang.setText(mCabang.getText().toString());
        liar.setText(mLiar.getText().toString());
        crowndua.setText(mCrownCabangDua.getText().toString());
        crowntiga.setText(mCrownCabangTiga.getText().toString());
        mandul.setText(mTanamanMandul.getText().toString());

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

        no_sample.setText(String.valueOf(nextSampleNumber));

        sampleModel.setNo_sample(Integer.parseInt(no_sample.getText().toString()));
        sampleModel.setNo_spk(model.getNO_SPK());
        sampleModel.setLokasi(mLokasi.getText().toString());
        sampleModel.setPlot(Integer.parseInt(mPlot.getText().toString()));
        sampleModel.setLuas_plot(Float.parseFloat(mLuasPlot.getText().toString()));
        sampleModel.setWil(autoWilayah.getText().toString());

        sampleModel.setNo_spk2(mSPK.getText().toString());
        sampleModel.setNo_line(mLine.getText().toString());
        sampleModel.setDaun_lemas(parseIntDefault(mDaunLemas.getText().toString(), 0));
        sampleModel.setDaun_berduri(parseIntDefault(mDaunBerduri.getText().toString(), 0));
        sampleModel.setTumbuh_tidak_ada(parseIntDefault(mTumbuhTidakAda.getText().toString(), 0));
        sampleModel.setTumbuh_lebih_dari_1(parseIntDefault(mTitikTumbuhLebih.getText().toString(), 0));
        sampleModel.setUnder_size(parseIntDefault(mUnderSize.getText().toString(), 0));
        sampleModel.setRusak_mekanis(parseIntDefault(mRusakMekanis.getText().toString(), 0));
        sampleModel.setBusuk(parseIntDefault(mBusuk.getText().toString(), 0));
        sampleModel.setBergejala(parseIntDefault(mPenyakit.getText().toString(), 0));
        sampleModel.setLayu(parseIntDefault(mLayuPermanen.getText().toString(), 0));
        sampleModel.setCabang_lebih_dari_3(parseIntDefault(mCabangTiga.getText().toString(), 0));
        sampleModel.setBibit_10_sampai_11(parseIntDefault(m1.getText().toString(), 0));
        sampleModel.setBibit_12_sampai_14(parseIntDefault(m2.getText().toString(), 0));
        sampleModel.setBibit_15_sampai_17(parseIntDefault(m3.getText().toString(), 0));
        sampleModel.setBibit_18_sampai_24(parseIntDefault(m4.getText().toString(), 0));
        sampleModel.setBibit_25_sampai_33(parseIntDefault(m5.getText().toString(), 0));
        sampleModel.setBibit_34_sampai_38(parseIntDefault(m6.getText().toString(), 0));
        sampleModel.setBibit_lebih_dari_38(parseIntDefault(m7.getText().toString(), 0));
        sampleModel.setTotal_afkir(parseIntDefault(mTotal.getText().toString(), 0));
        sampleModel.setTotal_bibit(parseIntDefault(mTot.getText().toString(), 0));
        sampleModel.setNormal(parseIntDefault(mNormal.getText().toString(), 0));
        sampleModel.setCabang(parseIntDefault(mCabang.getText().toString(), 0));
        sampleModel.setLiar(parseIntDefault(mLiar.getText().toString(), 0));
        sampleModel.setCrown_cabang_2(parseIntDefault(mCrownCabangDua.getText().toString(), 0));
        sampleModel.setCrown_cabang_3(parseIntDefault(mCrownCabangTiga.getText().toString(), 0));
        sampleModel.setTanaman_mandul(parseIntDefault(mTanamanMandul.getText().toString(), 0));

        String selectedDateString2 = mRencanaPanen.getText().toString();
        sampleModel.setRencana_panen(selectedDateString2);

        String selectedDateString3 = mUpdatePeta.getText().toString();
        sampleModel.setUpdate_peta(selectedDateString3);


        sampleModel.setKeterangan(
                mKet.getText().toString().trim().isEmpty() ? "-" : mKet.getText().toString().trim()
        );



        dataSample.add(sampleModel);
        Log.d("datanyanih", new Gson().toJson(model));
        containerPlotData.addView(rowView, 0);

        Toast.makeText(this, "Data Sudah Ditambahkan", Toast.LENGTH_SHORT).show();

        //Hapus Saat Apply
        mDaunLemas.getText().clear();
        mDaunBerduri.getText().clear();
        mTitikTumbuhLebih.getText().clear();
        mTumbuhTidakAda.getText().clear();
        mUnderSize.getText().clear();
        mRusakMekanis.getText().clear();
        mBusuk.getText().clear();
        mPenyakit.getText().clear();
        mLayuPermanen.getText().clear();
        mCabangTiga.getText().clear();
        mTot.getText().clear();
        mTotal.getText().clear();
        m1.getText().clear();
        m2.getText().clear();
        m3.getText().clear();
        m4.getText().clear();
        m5.getText().clear();
        m6.getText().clear();
        m7.getText().clear();
        mNormal.getText().clear();
        mCabang.getText().clear();
        mLiar.getText().clear();
        mCrownCabangDua.getText().clear();
        mCabangTiga.getText().clear();
        mTanamanMandul.getText().clear();

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
        if (dataPlot.size() > 0) {
//            sampleModel.setKeterangan(mKeterangan.getText().toString());
//            plotModel.setSAMPLE(dataSample);
            model.setLOKASI(mLokasi.getText().toString());
            model.setWILAYAH(autoWilayah.getText().toString());
            model.setDATA(dataPlot);

            Log.d("dataBody", new Gson().toJson(model));
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
        String message = "Potensi Crown"
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
//        Toast.makeText(this, "woi", Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, rm, Toast.LENGTH_SHORT).show();
        SweetDialogs.commonError(this, eror, true);
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
    public void onNetworkError(String cause , String data) {

        Log.e("errornya", cause);
        SQLiteHelper dbHelper = new SQLiteHelper(this);
        dbHelper.saveChopperData(data, model.getNO_SPK());  // Assuming 'data' is a JSON string


        Log.d("Saved data", "Data saved to SQLite: " + data);
        Log.d("Saved data", "Data saved to SQLite: " + model.getNO_SPK());
        // Show a dialog indicating that the data has been saved offline
//        SweetDialogs.commonError(this, App.getApplication().getString(R.string.notif_offline_mode), false);
        SweetDialogs.commonWarningWithIntent(this,"Anda Tidak ada Koneksi Internet", App.getApplication().getString(R.string.notif_offline_mode), string -> startActivity(new Intent(this, c_1_home.class)));

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

    private static final String[] autoWilayah2 = new String[]{
            "AW01", "AW02", "AW03", "AW04", "AW05", "AW06", "AW07", "AW08", "AW09", "AW10", "AW11", "AW12", "AW13", "AW14", "AW15", "AW16", "AW17", "AW18", "AW19", "AW20", "AW21", "AW22", "AW23"
    };

}