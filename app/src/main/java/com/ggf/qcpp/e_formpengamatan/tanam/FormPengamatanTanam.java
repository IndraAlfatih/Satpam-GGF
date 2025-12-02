package com.ggf.qcpp.e_formpengamatan.tanam;

import static com.ggf.qcpp.utils.Utils.generateTglSekarang;
import static com.ggf.qcpp.utils.Utils.goToListPengamatan;
import static com.ggf.qcpp.utils.Utils.parStringDefault;
import static com.ggf.qcpp.utils.Utils.parseFloatDefault;
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
import com.ggf.qcpp.e_formpengamatan.petikbibit.FormPengamatanPetikBibitPresenter;
import com.ggf.qcpp.e_formpengamatan.petikbibit.IFormPengamatanPetikBibitView;
import com.ggf.qcpp.e_formpengamatan.petikbibit.model.PetikBibitModel;
import com.ggf.qcpp.e_formpengamatan.tanam.model.PlotModel;
import com.ggf.qcpp.e_formpengamatan.tanam.model.SampleModel;
import com.ggf.qcpp.e_formpengamatan.tanam.model.TanamModel;
import com.ggf.qcpp.network.SQLiteHelper;
import com.ggf.qcpp.ui.SweetDialogs;
import com.ggf.qcpp.utils.TemporaryFormStorage;
import com.ggf.qcpp.utils.Utils;
import com.google.gson.Gson;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.text.DecimalFormat;
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

public class FormPengamatanTanam extends AppCompatActivity implements View.OnClickListener, IFormPengamatanTanamView {

    @BindView(R.id.autojenisbibit)
    AutoCompleteTextView autojenisbibit;

    @BindView(R.id.automusim)
    AutoCompleteTextView automusim;
    @BindView(R.id.mReworking)
    AutoCompleteTextView mReworking;
    @BindView(R.id.autostatuspengamatan)
    AutoCompleteTextView autostatuspengamatan;

    @BindView(R.id.mSPK)
    EditText mSPK;

    @BindView(R.id.mLine)
    EditText mLine;

    @BindView(R.id.autokelasbibit)
    AutoCompleteTextView autokelasbibit;

    @BindView(R.id.autostatusjtab)
    AutoCompleteTextView autostatusjtab;

    @BindView(R.id.autoWilayah)
    AutoCompleteTextView autoWilayah;

    @BindView(R.id.autostatusjtdb)
    AutoCompleteTextView autostatusjtdb;

    @BindView(R.id.mTanamanJTAB)
    AutoCompleteTextView mTanamanJTAB;

    @BindView(R.id.mTanamanJTDB)
    AutoCompleteTextView mTanamanJTDB;

    @BindView(R.id.mSubmit)
    Button mSubmit;

    @BindView(R.id.mAddSample)
    ImageView mAddSample;

    @BindView(R.id.containerPlotData)
    LinearLayout containerPlotData;

    @BindView(R.id.mLokasi)
    EditText mLokasi;
    @BindView(R.id.mPlot)
    EditText mPlot;

    @BindView(R.id.mTotalPlot)
    EditText mTotalPlot;
    @BindView(R.id.mLuasPlot)
    EditText mLuasPlot;
    @BindView(R.id.mUpdatePeta)
    EditText mUpdatePeta;
    @BindView(R.id.mMandor)
    EditText mMandor;
    @BindView(R.id.mPanjangJTDB)
    EditText mPanjangJtdb;
    @BindView(R.id.mPanjangJTAB)
    EditText mPanjangJtab;

    @BindView(R.id.m1)
    EditText m1;
    @BindView(R.id.m2)
    EditText m2;
    @BindView(R.id.m3)
    EditText m3;
    @BindView(R.id.m4)
    EditText m4;
    @BindView(R.id.mTegak)
    EditText mTegak;
    @BindView(R.id.mTidakTegak)
    EditText mTidakTegak;


    @BindView(R.id.mKeterangan)
    EditText mKeterangan;

    @BindView(R.id.mKet)
    EditText mKet;

    View rowView;
    View rowViewPlot;
    View viewnya = null;
    String plot = "0";
    TanamModel model;

    List<PlotModel> dataPlot = new ArrayList<>();
    List<SampleModel> dataSample = new ArrayList<>();

    SweetAlertDialog sweetAlertDialog;
    SampleModel sampleModel = null;
    PlotModel plotModel = null;
    int index = 1;
    FormPengamatanTanamPresenter presenter;
    private static final String DRAFT_KEY = "draft_tanam";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form_pengamatan_tanam);
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

        // Saat kolom di-klik, tampilkan DatePickerDialog
        mUpdatePeta.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        // Tambahkan 1 ke bulan (karena bulan dimulai dari 0)
                        String selectedDate = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay;
                        mUpdatePeta.setText(selectedDate); // Set tanggal di EditText
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

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, autojenisbibit2);
        autojenisbibit.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, automusim2);
        automusim.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, autostatuspengamatan2);
        autostatuspengamatan.setAdapter(adapter3);

        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, autokelasbibit2);
        autokelasbibit.setAdapter(adapter4);

        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, autotanaman2);
        mTanamanJTDB.setAdapter(adapter5);

        ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, autotanaman3);
        mTanamanJTAB.setAdapter(adapter6);

        ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, autostatusjtab2);
        autostatusjtab.setAdapter(adapter7);

        ArrayAdapter<String> adapter8 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, autostatusjtdb2);
        autostatusjtdb.setAdapter(adapter8);

        ArrayAdapter<String> adapter9 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, autoWilayah2);
        autoWilayah.setAdapter(adapter9);

        ArrayAdapter<String> adapter10 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mReworking2);
        mReworking.setAdapter(adapter10);

        EditText lokasiUppercase = findViewById(R.id.mLokasi);
        lokasiUppercase.setFilters(new InputFilter[] {new InputFilter.AllCaps()});

        // Menonaktifkan input teks, tetapi dropdown masih muncul
        automusim.setKeyListener(null);
        autojenisbibit.setKeyListener(null);
        autostatuspengamatan.setKeyListener(null);
        autokelasbibit.setKeyListener(null);
        mTanamanJTDB.setKeyListener(null);
        mReworking.setKeyListener(null);
        mTanamanJTAB.setKeyListener(null);
        autostatusjtab.setKeyListener(null);
        autostatusjtdb.setKeyListener(null);
        autoWilayah.setKeyListener(null);

        // Memastikan dropdown muncul meskipun tidak ada teks yang dimasukkan
        autoWilayah.setThreshold(1);
        automusim.setThreshold(1);
        autojenisbibit.setThreshold(1);
        autostatuspengamatan.setThreshold(1);
        autokelasbibit.setThreshold(1);
        mTanamanJTDB.setThreshold(1);
        mTanamanJTAB.setThreshold(1);
        mReworking.setThreshold(1);
        autostatusjtab.setThreshold(1);
        autostatusjtdb.setThreshold(1);
        // Atur threshold sesuai kebutuhan (misalnya 1 untuk memulai pencarian setelah 1 karakter)

        model = (TanamModel) getIntent().getSerializableExtra("model");
        Log.d("TanamModel", new Gson().toJson(model));

        presenter = new FormPengamatanTanamPresenter(this);
        mSubmit.setOnClickListener(this);
        mAddSample.setOnClickListener(this);
    }
    private void clearForm() {
        mSPK.setText("");
        mLine.setText("");
        mLokasi.setText("");
        autoWilayah.setText("");
        mKeterangan.setText("");
        mUpdatePeta.setText("");
        automusim.setText("");
        autostatuspengamatan.setText("");
        mReworking.setText("");

        dataPlot.clear();
        dataSample.clear();
        containerPlotData.removeAllViews();

        model = new TanamModel();
        TemporaryFormStorage.clearDraft(this, DRAFT_KEY);

        Log.d("FormReset", "Form tanam cleared");
    }

    /** Simpan data sementara ke draft */
    private void saveTemporaryData() {
        TanamModel draft = new TanamModel();
        draft.setNO_SPK(mSPK.getText().toString());
        draft.setNO_LINE(mLine.getText().toString());
        draft.setLOKASI(mLokasi.getText().toString());
        draft.setWILAYAH(autoWilayah.getText().toString());

        draft.setUpdate_peta(mUpdatePeta.getText().toString());
        draft.setStd_musim(automusim.getText().toString());
        draft.setSTATUS_PENGAMATAN(autostatuspengamatan.getText().toString());
        draft.setReworking(mReworking.getText().toString());

        draft.setDATA(dataPlot);

        TemporaryFormStorage.saveDraft(this, DRAFT_KEY, draft);
        Log.d("DraftSave", "Draft tanam saved: " + new Gson().toJson(draft));
    }

    /** Load data sementara dari draft */
    private void loadTemporaryData() {
        TanamModel draft = TemporaryFormStorage.loadDraft(this, DRAFT_KEY, TanamModel.class);
        if (draft != null) {

            mSPK.setText(draft.getNO_SPK());
            mLine.setText(draft.getNO_LINE());
            mLokasi.setText(draft.getLOKASI());
            autoWilayah.setText(draft.getWILAYAH());

            mUpdatePeta.setText(draft.getUpdate_peta());
            automusim.setText(draft.getStd_musim());
            autostatuspengamatan.setText(draft.getSTATUS_PENGAMATAN());
            mReworking.setText(draft.getReworking());


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
            automusim.post(() -> {
                ArrayAdapter<String> adapterAutomusim = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, automusim2);
                automusim.setAdapter(adapterAutomusim);
            });

            autojenisbibit.post(() -> {
                ArrayAdapter<String> adapterJenisBibit = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, autojenisbibit2);
                autojenisbibit.setAdapter(adapterJenisBibit);
            });

            autostatuspengamatan.post(() -> {
                ArrayAdapter<String> adapterStatusPengamatan = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, autostatuspengamatan2);
                autostatuspengamatan.setAdapter(adapterStatusPengamatan);
            });

            autokelasbibit.post(() -> {
                ArrayAdapter<String> adapterKelasBibit = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, autokelasbibit2);
                autokelasbibit.setAdapter(adapterKelasBibit);
            });

            mTanamanJTDB.post(() -> {
                ArrayAdapter<String> adapterTanamanJTDB = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, autotanaman2);
                mTanamanJTDB.setAdapter(adapterTanamanJTDB);
            });

            mReworking.post(() -> {
                ArrayAdapter<String> adapterReworking = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, mReworking2);
                mReworking.setAdapter(adapterReworking);
            });

            mTanamanJTAB.post(() -> {
                ArrayAdapter<String> adapterTanamanJTAB = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, autotanaman3);
                mTanamanJTAB.setAdapter(adapterTanamanJTAB);
            });

            autostatusjtab.post(() -> {
                ArrayAdapter<String> adapterStatusJTAB = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, autostatusjtab2);
                autostatusjtab.setAdapter(adapterStatusJTAB);
            });

            autostatusjtdb.post(() -> {
                ArrayAdapter<String> adapterStatusJTDB = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, autostatusjtdb2);
                autostatusjtdb.setAdapter(adapterStatusJTDB);
            });

            autoWilayah.post(() -> {
                ArrayAdapter<String> adapterWilayah = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, autoWilayah2);
                autoWilayah.setAdapter(adapterWilayah);
            });

        }
    }

    /** Tampilkan ulang sample yang sudah ada */
    private void restoreSampleView(SampleModel sample) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.data_tanam_field, null);

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        // Binding ke view di layout data_tanam_field
        final TextView plot = rowView.findViewById(R.id.mPlot);
        final TextView luasplot = rowView.findViewById(R.id.mLuasPlot);
        final TextView kelasbibit = rowView.findViewById(R.id.autokelasbibit);
        final TextView jenisbibit = rowView.findViewById(R.id.autojenisbibit);
        final TextView mNoSample = rowView.findViewById(R.id.mNoSample);
        final TextView mandor = rowView.findViewById(R.id.mMandor);
        final TextView tanamanJTDB = rowView.findViewById(R.id.mTanamanJTDB);
        final TextView tanamanJTAB = rowView.findViewById(R.id.mTanamanJTAB);
        final TextView rataJTDB = rowView.findViewById(R.id.mRataJtdb);
        final TextView rataJTAB = rowView.findViewById(R.id.mRataJtab);
        final TextView panjangJTDB = rowView.findViewById(R.id.mPanjangJTDB);
        final TextView panjangJTAB = rowView.findViewById(R.id.mPanjangJTAB);
        final TextView statusjtdb = rowView.findViewById(R.id.autostatusjtdb);
        final TextView statusjtab = rowView.findViewById(R.id.autostatusjtab);
        final TextView mm1 = rowView.findViewById(R.id.m1);
        final TextView mm2 = rowView.findViewById(R.id.m2);
        final TextView mm3 = rowView.findViewById(R.id.m3);
        final TextView mm4 = rowView.findViewById(R.id.m4);
        final TextView tegak = rowView.findViewById(R.id.mTegak);
        final TextView tidakTegak = rowView.findViewById(R.id.mTidakTegak);

        // Hitung ulang rata-rata
        float avgJTDB = sample.getTot_tanamjtdb() > 1
                ? sample.getPanjang_jtdb() / (sample.getTot_tanamjtdb() - 1)
                : 0f;
        float avgJTAB = sample.getTot_tanamjtab() > 1
                ? sample.getPanjang_jtab() / (sample.getTot_tanamjtab() - 1)
                : 0f;

        // Set isi ke tampilan
        plot.setText(String.valueOf(sample.getPlot()));
        luasplot.setText(String.valueOf(sample.getLuas_plot()));
        kelasbibit.setText(sample.getKelas_bibit());
        jenisbibit.setText(sample.getJenis_bibit());
        mNoSample.setText(String.valueOf(sample.getNo_sample()));
        mandor.setText(sample.getMandor_bibit());
        tanamanJTDB.setText(String.valueOf(sample.getTot_tanamjtdb()));
        tanamanJTAB.setText(String.valueOf(sample.getTot_tanamjtab()));
        rataJTDB.setText(df.format(avgJTDB));
        rataJTAB.setText(df.format(avgJTAB));
        panjangJTDB.setText(String.valueOf(sample.getPanjang_jtdb()));
        panjangJTAB.setText(String.valueOf(sample.getPanjang_jtab()));
        statusjtdb.setText(sample.getStatus_jtdb());
        statusjtab.setText(sample.getStatus_jtab());
        mm1.setText(String.valueOf(sample.getKedalaman_1()));
        mm2.setText(String.valueOf(sample.getKedalaman_2()));
        mm3.setText(String.valueOf(sample.getKedalaman_3()));
        mm4.setText(String.valueOf(sample.getKedalaman_4()));
        tegak.setText(String.valueOf(sample.getTot_tegakterinjak()));
        tidakTegak.setText(String.valueOf(sample.getTot_tidaktegakterinjak()));

        // Tambahkan ke container
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
            Toast.makeText(this, "Lokasi Tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mUpdatePeta.getText().toString().equals("")) {
            Toast.makeText(this, "Update Peta tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (automusim.getText().toString().equals("")) {
            Toast.makeText(this, "Musim tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (autostatuspengamatan.getText().toString().equals("")) {
            Toast.makeText(this, "Status Pengamatan tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (autoWilayah.getText().toString().equals("")) {
            Toast.makeText(this, "Wilayah tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mReworking.getText().toString().equals("")) {
            Toast.makeText(this, "Reworking tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mPlot.getText().toString().equals("")) {
            Toast.makeText(this, "No Plot tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mLuasPlot.getText().toString().equals("")) {
            Toast.makeText(this, "Luas Plot tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (autokelasbibit.getText().toString().equals("")) {
            Toast.makeText(this, "Kelas Bibit tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (autojenisbibit.getText().toString().equals("")) {
            Toast.makeText(this, "Jenis Bibit tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mMandor.getText().toString().equals("")) {
            Toast.makeText(this, "Mandor Bibit tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mTotalPlot.getText().toString().equals("")) {
            Toast.makeText(this, "Total Plot tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mPanjangJtdb.getText().toString().equals("")) {
            Toast.makeText(this, "Panjang JTDB tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mTanamanJTDB.getText().toString().equals("")) {
            Toast.makeText(this, "Total Tanaman JTDB tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (autostatusjtdb.getText().toString().equals("")) {
            Toast.makeText(this, "Status JTDB tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mPanjangJtab.getText().toString().equals("")) {
            Toast.makeText(this, "Panjang JTAB tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mTanamanJTAB.getText().toString().equals("")) {
            Toast.makeText(this, "Total Tanaman JTAB tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (autostatusjtab.getText().toString().equals("")) {
            Toast.makeText(this, "Status JTAB tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (m1.getText().toString().equals("")) {
            Toast.makeText(this, "Kedalaman tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (m2.getText().toString().equals("")) {
            Toast.makeText(this, "Kedalaman tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (m3.getText().toString().equals("")) {
            Toast.makeText(this, "Kedalaman tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mTegak.getText().toString().equals("")) {
            Toast.makeText(this, "Total Tegak Terinjak tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mTidakTegak.getText().toString().equals("")) {
            Toast.makeText(this, "Tidak Tegak Terinjak tidak boleh kosong", Toast.LENGTH_SHORT).show();
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
        float avgJTDB = 0;
        float avgJTAB = 0;
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rowView = inflater.inflate(R.layout.data_tanam_field, null);
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        final TextView plot = rowView.findViewById(R.id.mPlot);
        final TextView luasplot = rowView.findViewById(R.id.mLuasPlot);
        final TextView kelasbibit = rowView.findViewById(R.id.autokelasbibit);
        final TextView jenisbibit = rowView.findViewById(R.id.autojenisbibit);
        final TextView mNoSample = rowView.findViewById(R.id.mNoSample);
        final TextView mandor = rowView.findViewById(R.id.mMandor);
        final TextView tanamanJTDB = rowView.findViewById(R.id.mTanamanJTDB);
        final TextView tanamanJTAB = rowView.findViewById(R.id.mTanamanJTAB);
        final TextView rataJTDB = rowView.findViewById(R.id.mRataJtdb);
        final TextView rataJTAB = rowView.findViewById(R.id.mRataJtab);
        final TextView panjangJTDB = rowView.findViewById(R.id.mPanjangJTDB);
        final TextView panjangJTAB = rowView.findViewById(R.id.mPanjangJTAB);
        final TextView statusjtdb = rowView.findViewById(R.id.autostatusjtdb);
        final TextView statusjtab = rowView.findViewById(R.id.autostatusjtab);
        final TextView mm1 = rowView.findViewById(R.id.m1);
        final TextView mm2 = rowView.findViewById(R.id.m2);
        final TextView mm3 = rowView.findViewById(R.id.m3);
        final TextView mm4 = rowView.findViewById(R.id.m4);
        final TextView tegak = rowView.findViewById(R.id.mTegak);
        final TextView tidakTegak = rowView.findViewById(R.id.mTidakTegak);
        avgJTDB = (float) Float.parseFloat(mPanjangJtdb.getText().toString()) / (Float.parseFloat(mTanamanJTDB.getText().toString()) - 1);
        avgJTAB = (float) Float.parseFloat(mPanjangJtab.getText().toString()) / (Float.parseFloat(mTanamanJTAB.getText().toString()) - 1);
//        mMandor.getText().toString().isEmpty() ? "-" : mKeterangan.getText().toString()
        luasplot.setText(mLuasPlot.getText().toString());
        kelasbibit.setText(autokelasbibit.getText().toString());
        jenisbibit.setText(autojenisbibit.getText().toString());
        panjangJTAB.setText(mTanamanJTAB.getText().toString());
        panjangJTDB.setText(mTanamanJTDB.getText().toString());
        statusjtab.setText(autostatusjtab.getText().toString());
        statusjtdb.setText(autostatusjtdb.getText().toString());
        mandor.setText(mMandor.getText().toString().isEmpty() ? "-" : mMandor.getText().toString());
        tanamanJTDB.setText(mTanamanJTDB.getText().toString());
        tanamanJTAB.setText(mTanamanJTAB.getText().toString());
        rataJTDB.setText(df.format(avgJTDB));
        rataJTAB.setText(df.format(avgJTAB));
        plot.setText(mPlot.getText().toString());
        mm1.setText(m1.getText().toString());
        mm2.setText(m2.getText().toString());
        mm3.setText(m3.getText().toString());
        mm4.setText(m4.getText().toString());
        panjangJTDB.setText(mPanjangJtdb.getText().toString());
        tegak.setText(mTegak.getText().toString());
        tidakTegak.setText(mTidakTegak.getText().toString());

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

        sampleModel.setNo_spk2(mSPK.getText().toString());
        sampleModel.setNo_line(mLine.getText().toString());
        sampleModel.setNo_spk(model.getNO_SPK());
        sampleModel.setWil(autoWilayah.getText().toString());
        sampleModel.setPlot(Integer.parseInt(mPlot.getText().toString()));
        sampleModel.setTotal_plot(Integer.parseInt(mTotalPlot.getText().toString()));
        sampleModel.setLuas_plot(Float.parseFloat(mLuasPlot.getText().toString()));
        sampleModel.setLokasi(mLokasi.getText().toString());
        sampleModel.setStd_musim(automusim.getText().toString());
        sampleModel.setReworking(mReworking.getText().toString());
        sampleModel.setStatus_pengamatan(autostatuspengamatan.getText().toString());
        sampleModel.setNo_sample(Integer.parseInt(mNoSample.getText().toString()));
        sampleModel.setKelas_bibit(autokelasbibit.getText().toString());
        sampleModel.setJenis_bibit(autojenisbibit.getText().toString());
        sampleModel.setStatus_jtab(autostatusjtab.getText().toString());
        sampleModel.setStatus_jtdb(autostatusjtdb.getText().toString());
        String selectedDateString = mUpdatePeta.getText().toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try {
            Date selectedDate = dateFormat.parse(selectedDateString); // Konversi String ke Date
//            sampleModel.setUpdate_peta(selectedDate); // Set ke model

        } catch (ParseException e) {
            e.printStackTrace();
            Toast.makeText(this, "Format tanggal tidak valid", Toast.LENGTH_SHORT).show();
        }
        sampleModel.setUpdate_peta(selectedDateString);

        sampleModel.setTot_tanamjtdb(Integer.parseInt(mTanamanJTDB.getText().toString()));
        sampleModel.setTot_tanamjtab(Integer.parseInt(mTanamanJTAB.getText().toString()));
        sampleModel.setPanjang_jtdb(Float.parseFloat(mPanjangJtdb.getText().toString()));
        sampleModel.setPanjang_jtab(Float.parseFloat(mPanjangJtab.getText().toString()));
        sampleModel.setKedalaman_1(Float.parseFloat(m1.getText().toString()));
        sampleModel.setKedalaman_2(Float.parseFloat(m2.getText().toString()));
        sampleModel.setKedalaman_3(Float.parseFloat(m3.getText().toString()));
        sampleModel.setMandor_bibit(mMandor.getText().toString());
        sampleModel.setTot_tegakterinjak(Integer.parseInt(mTegak.getText().toString()));

        sampleModel.setKedalaman_4(parseFloatDefault(m4.getText().toString(), 0.0f));
        sampleModel.setTot_tidaktegakterinjak(parseIntDefault(mTidakTegak.getText().toString(), 0));
//        sampleModel.setUpdate_peta(mUpdatePeta.getText().toString());

//        sampleModel.setKurang_dari_standar(Float.parseFloat("1"));
//        sampleModel.setLebih_dari_standar(Float.parseFloat("1"));
//        sampleModel.setOn_standar(Float.parseFloat("1"));
//
//        sampleModel.setKedalaman_4(Float.parseFloat(m4.getText().toString()));
//        sampleModel.setKedalaman_5(Float.parseFloat(m5.getText().toString()));
//        sampleModel.setKedalaman_6(Float.parseFloat(m6.getText().toString()));
//        sampleModel.setKedalaman_7(Float.parseFloat(m7.getText().toString()));

        sampleModel.setMandor_bibit(mMandor.getText().toString());
        sampleModel.setKeterangan(
                mKet.getText().toString().trim().isEmpty() ? "-" : mKet.getText().toString().trim()
        );


//        dataSample.add(sampleModel);
        dataSample.add(sampleModel);
        Log.d("datanyanih", new Gson().toJson(model));
        containerPlotData.addView(rowView, 0);

        //Hapus Saat Apply
        Toast.makeText(this, "Data Sudah Ditambahkan", Toast.LENGTH_SHORT).show();
        mPanjangJtab.getText().clear();
        mTanamanJTDB.getText().clear();
        mPanjangJtdb.getText().clear();
        mTanamanJTAB.getText().clear();
        mTegak.getText().clear();
        mTidakTegak.getText().clear();
        autostatusjtdb.getText().clear();
        autostatusjtab.getText().clear();

        m1.getText().clear();
        m2.getText().clear();
        m3.getText().clear();
        m4.getText().clear();

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
        String message = "Tanam"
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

    private static final String[] automusim2 = new String[]{
            "Kering", "Basah"
    };

    private static final String[] autojenisbibit2 = new String[]{
            "Sucker", "Crown", "Crown Storing", "Nursery Sucker Plus"
    };

    private static final String[] autostatuspengamatan2 = new String[]{
            "Crosscheck", "Inprocess"
    };

    private static final String[] autokelasbibit2 = new String[]{
            "Super Kecil", "Kecil", "Sedang", "Besar", "Extra Besar", "Crown No 6", "Crown No 7"
    };

    private static final String[] autotanaman2 = new String[]{
            "9", "10", "11"
    };

    private static final String[] autotanaman3 = new String[]{
            "4", "5", "6"
    };

    private static final String[] autostatusjtab2 = new String[]{
            "< Standar", "On Stndar", "> Standar"
    };

    private static final String[] mReworking2 = new String[]{
            "Sebelum Reworking", "Sesudah Reworking"
    };

    private static final String[] autostatusjtdb2 = new String[]{
            "< Standar", "On Stndar", "> Standar"
    };
    private static final String[] autoWilayah2 = new String[]{
            "AW01", "AW02", "AW03", "AW04", "AW05", "AW06", "AW07", "AW08", "AW09", "AW10", "AW11", "AW12", "AW13", "AW14", "AW15", "AW16", "AW17", "AW18", "AW19", "AW20", "AW21", "AW22", "AW23"
    };

}
