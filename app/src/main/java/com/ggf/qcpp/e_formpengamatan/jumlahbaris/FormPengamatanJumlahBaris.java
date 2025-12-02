package com.ggf.qcpp.e_formpengamatan.jumlahbaris;

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
import com.ggf.qcpp.e_formpengamatan.jumlahbaris.model.JumlahBarisModel;
import com.ggf.qcpp.e_formpengamatan.jumlahbaris.model.PlotModel;
import com.ggf.qcpp.e_formpengamatan.jumlahbaris.model.SampleModel;
import com.ggf.qcpp.e_formpengamatan.kebersihanbonggol.IFormPengamatanKebersihanBonggolView;
import com.ggf.qcpp.e_formpengamatan.tanam.model.TanamModel;
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

public class FormPengamatanJumlahBaris extends AppCompatActivity implements View.OnClickListener, IFormPengamatanJumlahBarisView {
    @BindView(R.id.mSubmit)
    Button mSubmit;

    @BindView(R.id.mAddSample)
    ImageView mAddSample;

    @BindView(R.id.containerPlotData)
    LinearLayout containerPlotData;
    @BindView(R.id.mLokasi)
    EditText mLokasi;
    @BindView(R.id.mReworking)
    AutoCompleteTextView mReworking;
    @BindView(R.id.mSPK)
    EditText mSPK;

    @BindView(R.id.mLine)
    EditText mLine;
    @BindView(R.id.autoWilayah)
    AutoCompleteTextView autoWilayah;

    @BindView(R.id.autoStatusPengamatan)
    AutoCompleteTextView autoStatusPengamatan;
    @BindView(R.id.mPlot)
    EditText mPlot;

    @BindView(R.id.mLuasPlot)
    EditText mLuasPlot;

    @BindView(R.id.mCe)
    EditText mCe;

    @BindView(R.id.mManual)
    EditText mManual;

    @BindView(R.id.mLebarJalan)
    EditText mLebarJalan;

    @BindView(R.id.mExamini)
    EditText mExamini;

    @BindView(R.id.mTraktor)
    EditText mTraktor;

    @BindView(R.id.mDijer)
    EditText mDijer;

    @BindView(R.id.mJumlahBaris)
    EditText mJumlahBaris;

    @BindView(R.id.mJumlahBarisStd)
    EditText mJumlahBarisStd;


    @BindView(R.id.mPenambahanBaris)
    EditText mPenambahanBaris;

    @BindView(R.id.mJumlahPb)
    EditText mJumlahPb;

    @BindView(R.id.mTersier)
    EditText mTersier;

    @BindView(R.id.mPenambahanBarisTersier)
    EditText mPenambahanBarisTersier;

    @BindView(R.id.mKancingan)
    EditText mKancingan;

    @BindView(R.id.mKeterangan)
    EditText mKeterangan;
    @BindView(R.id.mKet)
    EditText mKet;

    @BindView(R.id.mUpdatePeta)
    EditText mUpdatePeta;

    @BindView(R.id.mMandor)
    EditText mMandor;
    private static final String DRAFT_KEY = "draft_jumlah_baris";


    int index = 1;
    View rowView;
    View rowViewPlot;
    View viewnya = null;
    String plot = "0";
    JumlahBarisModel model;

    List<PlotModel> dataPlot = new ArrayList<>();
    List<SampleModel> dataSample = new ArrayList<>();

    SweetAlertDialog sweetAlertDialog;
    SampleModel sampleModel = null;
    PlotModel plotModel = null;
    FormPengamatanJumlahBarisPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form_pengamatan_jumlah_baris);
        ButterKnife.bind(this);
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

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, autoWilayah2);
        autoWilayah.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mReworking2);
        mReworking.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mStatusPengamatan2);
        autoStatusPengamatan.setAdapter(adapter3);

        EditText lokasiUppercase = findViewById(R.id.mLokasi);
        lokasiUppercase.setFilters(new InputFilter[] {new InputFilter.AllCaps()});

        // Menonaktifkan input teks, tetapi dropdown masih muncul
        mReworking.setKeyListener(null);
        autoWilayah.setKeyListener(null);
        autoStatusPengamatan.setKeyListener(null);


        // Memastikan dropdown muncul meskipun tidak ada teks yang dimasukkan
        autoWilayah.setThreshold(1);
        mReworking.setThreshold(1);
        autoStatusPengamatan.setThreshold(1);


        model = (JumlahBarisModel) getIntent().getSerializableExtra("model");
        Log.d("TanamModel", new Gson().toJson(model));
        presenter = new FormPengamatanJumlahBarisPresenter(this);

        mSubmit.setOnClickListener(this);
        mAddSample.setOnClickListener(this);
    }
    private void saveTemporaryData() {
        JumlahBarisModel draft = new JumlahBarisModel();
        draft.setNO_SPK(mSPK.getText().toString());
        draft.setNO_LINE(mLine.getText().toString());
        draft.setLOKASI(mLokasi.getText().toString());
        draft.setWILAYAH(autoWilayah.getText().toString());
        draft.setSTATUS_PENGAMATAN(autoStatusPengamatan.getText().toString());

        draft.setUpdate_peta(mUpdatePeta.getText().toString());
        draft.setMandor(mMandor.getText().toString());
        draft.setReworking(mReworking.getText().toString());

        draft.setDATA(dataPlot);

        TemporaryFormStorage.saveDraft(this, DRAFT_KEY, draft);
        Log.d("DraftSave", "Draft jumlah baris saved: " + new Gson().toJson(draft));
    }
    private void loadTemporaryData() {
        JumlahBarisModel draft = TemporaryFormStorage.loadDraft(this, DRAFT_KEY, JumlahBarisModel.class);
        if (draft != null) {
            Log.d("DraftLoad", "Draft jumlah baris loaded: " + new Gson().toJson(draft));

            mSPK.setText(draft.getNO_SPK());
            mLine.setText(draft.getNO_LINE());
            mLokasi.setText(draft.getLOKASI());
            autoWilayah.setText(draft.getWILAYAH());
            autoStatusPengamatan.setText(draft.getSTATUS_PENGAMATAN());

            mUpdatePeta.setText(draft.getUpdate_peta());
            mMandor.setText(draft.getMandor());
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
            mReworking.post(() -> {
                mReworking.setKeyListener(null);
                ArrayAdapter<String> adapterReworking = new ArrayAdapter<>(this,
                        android.R.layout.simple_dropdown_item_1line,
                        mReworking2);
                mReworking.setAdapter(adapterReworking);
            });

            autoWilayah.post(() -> {
                autoWilayah.setKeyListener(null);
                ArrayAdapter<String> adapterWilayah = new ArrayAdapter<>(this,
                        android.R.layout.simple_dropdown_item_1line,
                        autoWilayah2);
                autoWilayah.setAdapter(adapterWilayah);
            });

            autoStatusPengamatan.post(() -> {
                autoStatusPengamatan.setKeyListener(null);
                ArrayAdapter<String> adapterStatusPengamatan = new ArrayAdapter<>(this,
                        android.R.layout.simple_dropdown_item_1line,
                        mStatusPengamatan2);
                autoStatusPengamatan.setAdapter(adapterStatusPengamatan);
            });

        }
    }


    private void restoreSampleView(SampleModel sample) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.data_jumlahbaris_sample_field, null);

//        ((TextView) rowView.findViewById(R.id.mNoSample)).setText(String.valueOf(sample.getNo_sample()));
//        ((TextView) rowView.findViewById(R.id.mPlot)).setText(String.valueOf(sample.getPlot()));
//        ((TextView) rowView.findViewById(R.id.mCe)).setText(String.valueOf(sample.getCe()));
//        ((TextView) rowView.findViewById(R.id.mLebarJalan)).setText(String.valueOf(sample.getLebar_jalan()));
//        ((TextView) rowView.findViewById(R.id.mManual)).setText(String.valueOf(sample.getManual()));
//        ((TextView) rowView.findViewById(R.id.mExamini)).setText(String.valueOf(sample.getExamini()));
//        ((TextView) rowView.findViewById(R.id.mDijer)).setText(String.valueOf(sample.getDitcher()));
//        ((TextView) rowView.findViewById(R.id.mJumlahBarisStd)).setText(String.valueOf(sample.getJumlah_baris_std()));
//        ((TextView) rowView.findViewById(R.id.mTraktor)).setText(String.valueOf(sample.getTraktor()));
//        ((TextView) rowView.findViewById(R.id.mJumlahBaris)).setText(String.valueOf(sample.getJumlah_baris()));
//        ((TextView) rowView.findViewById(R.id.mPenambahanBaris)).setText(String.valueOf(sample.getPenambahan_baris()));
//        ((TextView) rowView.findViewById(R.id.mJumlahPb)).setText(String.valueOf(sample.getJumlah_pb()));
//        ((TextView) rowView.findViewById(R.id.mTersier)).setText(String.valueOf(sample.getTersier()));
//        ((TextView) rowView.findViewById(R.id.mKancingan)).setText(String.valueOf(sample.getKancingan()));
//        ((TextView) rowView.findViewById(R.id.mPenambahanBarisTersier)).setText(String.valueOf(sample.getPenambahan_baris_sal_tersier()));

        final TextView noSample = rowView.findViewById(R.id.mNoSample);
        final TextView plot = rowView.findViewById(R.id.mPlot);
        final TextView ce = rowView.findViewById(R.id.mCe);
        final TextView lebarJalan = rowView.findViewById(R.id.mLebarJalan);
        final TextView manual = rowView.findViewById(R.id.mManual);
        final TextView examini = rowView.findViewById(R.id.mExamini);
        final TextView dijer = rowView.findViewById(R.id.mDijer);
        final TextView jumlahBarisStd = rowView.findViewById(R.id.mJumlahBarisStd);
        final TextView traktor = rowView.findViewById(R.id.mTraktor);
        final TextView jumlahBaris = rowView.findViewById(R.id.mJumlahBaris);
        final TextView penambahanBaris = rowView.findViewById(R.id.mPenambahanBaris);
        final TextView jumlahPb = rowView.findViewById(R.id.mJumlahPb);
        final TextView tersier = rowView.findViewById(R.id.mTersier);
        final TextView kancingan = rowView.findViewById(R.id.mKancingan);
        final TextView penambahanBarisTersier = rowView.findViewById(R.id.mPenambahanBarisTersier);

        noSample.setText(String.valueOf(sample.getNo_sample()));
        plot.setText(String.valueOf(sample.getPlot()));
        ce.setText(String.valueOf(sample.getCe()));
        lebarJalan.setText(String.valueOf(sample.getLebar_jalan()));
        manual.setText(String.valueOf(sample.getManual()));
        examini.setText(String.valueOf(sample.getExamini()));
        dijer.setText(String.valueOf(sample.getDitcher()));
        jumlahBarisStd.setText(String.valueOf(sample.getJumlah_baris_std()));
        traktor.setText(String.valueOf(sample.getTraktor()));
        jumlahBaris.setText(String.valueOf(sample.getJumlah_baris()));
        penambahanBaris.setText(String.valueOf(sample.getPenambahan_baris()));
        jumlahPb.setText(String.valueOf(sample.getJumlah_pb()));
        tersier.setText(String.valueOf(sample.getTersier()));
        kancingan.setText(String.valueOf(sample.getKancingan()));
        penambahanBarisTersier.setText(String.valueOf(sample.getPenambahan_baris_sal_tersier()));


        containerPlotData.addView(rowView, 0);
    }
    private void clearForm() {
        mSPK.setText("");
        mLine.setText("");
        mLokasi.setText("");
        autoWilayah.setText("");
        autoStatusPengamatan.setText("");
        mReworking.setText("");
        mUpdatePeta.setText("");
        mMandor.setText("");
        mKeterangan.setText("");
        mKet.setText("");

        dataPlot.clear();
        dataSample.clear();
        containerPlotData.removeAllViews();

        model = new JumlahBarisModel();

        TemporaryFormStorage.clearDraft(this, DRAFT_KEY);
        Log.d("FormReset", "Form jumlah baris cleared");
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
        } else if (mUpdatePeta.getText().toString().equals("")) {
            Toast.makeText(this, "Update Peta tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mMandor.getText().toString().equals("")) {
            Toast.makeText(this, "Mandor tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mReworking.getText().toString().equals("")) {
            Toast.makeText(this, "Reworking tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (autoStatusPengamatan.getText().toString().equals("")) {
            Toast.makeText(this, "Status Pengamatan tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mPlot.getText().toString().equals("")) {
            Toast.makeText(this, "No Plot tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mLuasPlot.getText().toString().equals("")) {
            Toast.makeText(this, "Luas Plot tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mCe.getText().toString().equals("")) {
            Toast.makeText(this, "Ce tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mManual.getText().toString().equals("")) {
            Toast.makeText(this, "Manual tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mLebarJalan.getText().toString().equals("")) {
            Toast.makeText(this, "Lebar Jalan tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mJumlahBaris.getText().toString().equals("")) {
            Toast.makeText(this, "Jumlah Baris tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mJumlahBarisStd.getText().toString().equals("")) {
            Toast.makeText(this, "Jumlah Baris Masuk Standar tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mTersier.getText().toString().equals("")) {
            Toast.makeText(this, "Tersier tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mKancingan.getText().toString().equals("")) {
            Toast.makeText(this, "Kancingan tidak boleh kosong", Toast.LENGTH_SHORT).show();
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
        rowView = inflater.inflate(R.layout.data_jumlahbaris_sample_field, null);


//
        final TextView no_sample = rowView.findViewById(R.id.mNoSample);
        final TextView ce = rowView.findViewById(R.id.mCe);
        final TextView lebarJalan = rowView.findViewById(R.id.mLebarJalan);
        final TextView manual = rowView.findViewById(R.id.mManual);
        final TextView examini = rowView.findViewById(R.id.mExamini);
        final TextView dijer = rowView.findViewById(R.id.mDijer);
        final TextView barisStd = rowView.findViewById(R.id.mJumlahBarisStd);
        final TextView traktor = rowView.findViewById(R.id.mTraktor);
        final TextView jumlahBaris = rowView.findViewById(R.id.mJumlahBaris);
        final TextView penambahanBaris = rowView.findViewById(R.id.mPenambahanBaris);
        final TextView jumlahPb = rowView.findViewById(R.id.mJumlahPb);
        final TextView tersier = rowView.findViewById(R.id.mTersier);
        final TextView kancingan = rowView.findViewById(R.id.mKancingan);
        final TextView plot = rowView.findViewById(R.id.mPlot);
        final TextView penambahanBarisTersier = rowView.findViewById(R.id.mPenambahanBarisTersier);

        plot.setText(mPlot.getText().toString());
        ce.setText(mCe.getText().toString());
        lebarJalan.setText(mLebarJalan.getText().toString());
        manual.setText(mManual.getText().toString());
        examini.setText(mExamini.getText().toString());
        dijer.setText(mDijer.getText().toString());
        barisStd.setText(mJumlahBarisStd.getText().toString());
        traktor.setText(mTraktor.getText().toString());
        jumlahBaris.setText(mJumlahBaris.getText().toString());
        penambahanBaris.setText(mPenambahanBaris.getText().toString());
        jumlahPb.setText(mJumlahPb.getText().toString());
        tersier.setText(mTersier.getText().toString());
        kancingan.setText(mKancingan.getText().toString());
        penambahanBarisTersier.setText(mPenambahanBarisTersier.getText().toString());

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
        sampleModel.setStatus_pengamatan(autoStatusPengamatan.getText().toString());
        sampleModel.setPlot(Integer.parseInt(mPlot.getText().toString()));
        sampleModel.setLuas_plot(Float.parseFloat(mLuasPlot.getText().toString()));
        sampleModel.setCe(Float.parseFloat(ce.getText().toString()));
        sampleModel.setLebar_jalan(Float.parseFloat(lebarJalan.getText().toString()));
        sampleModel.setManual(Float.parseFloat(manual.getText().toString()));
        sampleModel.setMandor(mMandor.getText().toString());
        sampleModel.setJumlah_baris_std(Integer.parseInt(barisStd.getText().toString()));
        sampleModel.setNo_spk2(mSPK.getText().toString());
        sampleModel.setNo_line(mLine.getText().toString());
        sampleModel.setWil(autoWilayah.getText().toString());
        sampleModel.setReworking(mReworking.getText().toString());
        sampleModel.setJumlah_baris(Integer.parseInt(jumlahBaris.getText().toString()));
        sampleModel.setTersier(Integer.parseInt(tersier.getText().toString()));
        sampleModel.setKancingan(Integer.parseInt(kancingan.getText().toString()));

        sampleModel.setExamini(parseIntDefault(examini.getText().toString(), 0));
        sampleModel.setDitcher(parseIntDefault(dijer.getText().toString(), 0));
        sampleModel.setTraktor(parseIntDefault(traktor.getText().toString(), 0));

        sampleModel.setPenambahan_baris(parseIntDefault(penambahanBaris.getText().toString(), 0));
        sampleModel.setJumlah_pb(parseIntDefault(jumlahPb.getText().toString(), 0));

        sampleModel.setPenambahan_baris_sal_tersier(parseIntDefault(penambahanBarisTersier.getText().toString(), 0));

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
        sampleModel.setKeterangan(
                mKet.getText().toString().trim().isEmpty() ? "-" : mKet.getText().toString().trim()
        );

        dataSample.add(sampleModel);
        Log.d("datanyanih", new Gson().toJson(model));
        containerPlotData.addView(rowView, 0);

        //Hapus Saat Apply
        Toast.makeText(this, "Data Sudah Ditambahkan", Toast.LENGTH_SHORT).show();
        mCe.getText().clear();
        mManual.getText().clear();
        mLebarJalan.getText().clear();
        mExamini.getText().clear();

        mTraktor.getText().clear();
        mDijer.getText().clear();
        mJumlahBaris.getText().clear();
        mJumlahBarisStd.getText().clear();
        mPenambahanBaris.getText().clear();
        mJumlahPb.getText().clear();
        mTersier.getText().clear();
        mKancingan.getText().clear();
        mPenambahanBarisTersier.getText().clear();

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
        String message = "Jumlah Baris"
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

    private static final String[] autoWilayah2 = new String[]{
            "AW01", "AW02", "AW03", "AW04", "AW05", "AW06", "AW07", "AW08", "AW09", "AW10", "AW11", "AW12", "AW13", "AW14", "AW15", "AW16", "AW17", "AW18", "AW19", "AW20", "AW21", "AW22", "AW23"
    };

    private static final String[] mStatusPengamatan2 = new String[]{
            "Selesai Tanam", "Validasi","Inprocess"
    };
    private static final String[] mReworking2 = new String[]{
            "Sebelum Reworking", "Sesudah Reworking"
    };
}
