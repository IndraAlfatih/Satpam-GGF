package com.ggf.qcpp.e_formpengamatan.tidakterseset;

import static com.ggf.qcpp.utils.Utils.generateTglSekarang;
import static com.ggf.qcpp.utils.Utils.goToListPengamatan;
import static com.ggf.qcpp.utils.Utils.parStringDefault;

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
import com.ggf.qcpp.e_formpengamatan.petikbibit.model.PetikBibitModel;
import com.ggf.qcpp.e_formpengamatan.tidakterseset.model.BonggolTidakTersesetModel;
import com.ggf.qcpp.e_formpengamatan.tidakterseset.model.PlotModel;
import com.ggf.qcpp.e_formpengamatan.tidakterseset.model.SampleModel;
import com.ggf.qcpp.network.SQLiteHelper;
import com.ggf.qcpp.ui.SweetDialogs;
import com.ggf.qcpp.utils.TemporaryFormStorage;
import com.google.gson.Gson;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FormPengamatanBonggolTidakTerseset extends AppCompatActivity implements IFormPengamatanBonggolTidakTersesetView, View.OnClickListener {
    @BindView(R.id.mLokasi)
    EditText mLokasi;
    @BindView(R.id.mSubmit)
    Button mSubmit;
    @BindView(R.id.mAddSample)
    ImageView mAddSample;
    @BindView(R.id.containerPlotData)
    LinearLayout containerPlotData;
    @BindView(R.id.mPlot)
    EditText mPlot;
    @BindView(R.id.mLuasPlot)
    EditText mLuasPlot;

    @BindView(R.id.mSPK)
    EditText mSPK;

    @BindView(R.id.mLine)
    EditText mLine;
    @BindView(R.id.mKeterangan)
    EditText mKeterangan;
    @BindView(R.id.mReworking)
    AutoCompleteTextView mReworking;

    @BindView(R.id.autoGrade)
    AutoCompleteTextView autoGrade;
    @BindView(R.id.autoWilayah)
    AutoCompleteTextView autoWilayah;

    @BindView(R.id.mStatusLokasi)
    AutoCompleteTextView mStatusLokasi;

    @BindView(R.id.mUpdatePeta)
    EditText mUpdatePeta;

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
    @BindView(R.id.mKeteranganJmlSucker)
    EditText mKeteranganJmlSucker;

    @BindView(R.id.mLuasLokasiAktif)
    EditText mLuasLokasiAktif;

    @BindView(R.id.mKet)
    EditText mKet;


//    @BindView(R.id.mKeteranganJmlIndukan)
//    EditText mKeteranganJmlIndukan;

    private static final String DRAFT_KEY = "draft_bonggol_tidak_terseset";
    View rowView;
    View rowViewPlot;
    View viewnya = null;
    String plot = "0";
    BonggolTidakTersesetModel model;

    List<PlotModel> dataPlot = new ArrayList<>();
    List<SampleModel> dataSample = new ArrayList<>();

    SweetAlertDialog sweetAlertDialog;
    SampleModel sampleModel = null;
    PlotModel plotModel = null;
    FormPengamatanBonggolTidakTersesetPresenter presenter;
    int index = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form_pengamatan_bonggol_tidak_terseset);

        ButterKnife.bind(this);
        presenter = new FormPengamatanBonggolTidakTersesetPresenter(this);
        model = (BonggolTidakTersesetModel) getIntent().getSerializableExtra("model");

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

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, ListGrade);
        autoGrade.setAdapter(adapter3);

        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, autoWilayah2);
        autoWilayah.setAdapter(adapter4);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mStatusLokasi2);
        mStatusLokasi.setAdapter(adapter);

        ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mReworking2);
        mReworking.setAdapter(adapter7);

        EditText lokasiUppercase = findViewById(R.id.mLokasi);
        lokasiUppercase.setFilters(new InputFilter[] {new InputFilter.AllCaps()});

        // Menonaktifkan input teks, tetapi dropdown masih muncul
        autoGrade.setKeyListener(null);
        mStatusLokasi.setKeyListener(null);

        mReworking.setKeyListener(null);
        autoWilayah.setKeyListener(null);

        // Memastikan dropdown muncul meskipun tidak ada teks yang dimasukkan
        autoWilayah.setThreshold(1);
        mReworking.setThreshold(1);
        mStatusLokasi.setThreshold(1);
        autoGrade.setThreshold(1);


        mSubmit.setOnClickListener(this);
        mAddSample.setOnClickListener(this);
    }
    private void saveTemporaryData() {
        BonggolTidakTersesetModel draft = new BonggolTidakTersesetModel();

        draft.setNO_SPK(mSPK.getText().toString());
        draft.setNO_LINE(mLine.getText().toString());
        draft.setLOKASI(mLokasi.getText().toString());
        draft.setWILAYAH(autoWilayah.getText().toString());

        draft.setGrade(autoGrade.getText().toString());
        draft.setUpdate_peta(mUpdatePeta.getText().toString());
        draft.setStatus_lokasi(mStatusLokasi.getText().toString());
        draft.setLuas_aktif(mLuasLokasiAktif.getText().toString());
        draft.setReworking(mReworking.getText().toString());


        // simpan data sample & plot
        draft.setDATA(dataPlot);

        TemporaryFormStorage.saveDraft(this, DRAFT_KEY, draft);
        Log.d("DraftSave", "Draft Bonggol Tidak Terseset saved: " + new Gson().toJson(draft));
    }

    private void loadTemporaryData() {
        BonggolTidakTersesetModel draft = (BonggolTidakTersesetModel)
                TemporaryFormStorage.loadDraft(this, DRAFT_KEY, BonggolTidakTersesetModel.class);

        if (draft == null) return;

        mSPK.setText(draft.getNO_SPK());
        mLine.setText(draft.getNO_LINE());
        mLokasi.setText(draft.getLOKASI());
        autoWilayah.setText(draft.getWILAYAH());

        autoGrade.setText(draft.getGrade());
        mUpdatePeta.setText(draft.getUpdate_peta());
        mStatusLokasi.setText(draft.getStatus_lokasi());
        mLuasLokasiAktif.setText(draft.getLuas_aktif());
        mReworking.setText(draft.getReworking());


        // reload data sample kalau ada
        if (draft.getDATA() != null) {
            dataPlot = draft.getDATA();
            for (PlotModel p: dataPlot) {
                if (p.getSAMPLE() != null) {
                    for (SampleModel s : p.getSAMPLE()) {
                        restoreSampleView(s);
                    }
                }
            }
        }

        // ✅ Tambahkan ini di akhir: rebind adapter biar dropdown aktif lagi
        autoGrade.post(() -> {
            ArrayAdapter<String> adapterGrade = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, ListGrade);
            autoGrade.setAdapter(adapterGrade);
        });

        mStatusLokasi.post(() -> {
            ArrayAdapter<String> adapterStatusLokasi = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, mStatusLokasi2);
            mStatusLokasi.setAdapter(adapterStatusLokasi);
        });

        mReworking.post(() -> {
            ArrayAdapter<String> adapterReworking = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, mReworking2);
            mReworking.setAdapter(adapterReworking);
        });

        autoWilayah.post(() -> {
            ArrayAdapter<String> adapterautoWilayah = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, autoWilayah2);
            autoWilayah.setAdapter(adapterautoWilayah);
        });

    }


    void addPlotForm() {
//        Toast.makeText(this, "cek", Toast.LENGTH_SHORT).show();
        //Tidak boleh kosong
        if (mSPK.getText().toString().equals("")) {
            Toast.makeText(this, "No SPK tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mLine.getText().toString().equals("")) {
            Toast.makeText(this, "No Line tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if(mLokasi.getText().toString().equals("")) {
            Toast.makeText(this, "Lokasi tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (autoGrade.getText().toString().equals("")) {
            Toast.makeText(this, "Grade tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mUpdatePeta.getText().toString().equals("")) {
            Toast.makeText(this, "Update Peta tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (autoWilayah.getText().toString().equals("")) {
            Toast.makeText(this, "Wilayah tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mStatusLokasi.getText().toString().equals("")) {
            Toast.makeText(this, "Status Lokasi tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mLuasLokasiAktif.getText().toString().equals("")) {
            Toast.makeText(this, "Luas Aktif tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mReworking.getText().toString().equals("")) {
            Toast.makeText(this, "Reworking tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }else if (mPlot.getText().toString().equals("")) {
            Toast.makeText(this, "No Plot tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mLuasPlot.getText().toString().equals("")) {
            Toast.makeText(this, "Luas Plot tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (m1.getText().toString().equals("")) {
            Toast.makeText(this, "<15 tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (m2.getText().toString().equals("")) {
            Toast.makeText(this, "15-19 tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (m3.getText().toString().equals("")) {
            Toast.makeText(this, "20-22 tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (m4.getText().toString().equals("")) {
            Toast.makeText(this, "23-25 tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (m5.getText().toString().equals("")) {
            Toast.makeText(this, "26-28 tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (m6.getText().toString().equals("")) {
            Toast.makeText(this, "29-31 tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (m7.getText().toString().equals("")) {
            Toast.makeText(this, ">31 tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mKeteranganJmlSucker.getText().toString().equals("")) {
            Toast.makeText(this, "Jumlah Sucker tidak boleh kosong", Toast.LENGTH_SHORT).show();
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
        rowView = inflater.inflate(R.layout.data_bonggoltidakterseset_sample_field, null);
//
//        TextView lokasi = rowView.findViewById(R.id.mLokasi);
//        TextView aautoGrade = rowView.findViewById(R.id.autoGrade);
//        TextView updatePeta = rowView.findViewById(R.id.mUpdatePeta);
        TextView no_sample = rowView.findViewById(R.id.mNoSample);
        TextView plot = rowView.findViewById(R.id.mPlot);
        TextView luasPlot = rowView.findViewById(R.id.mLuasPlot);
        TextView jumlahKurangDari15 = rowView.findViewById(R.id.m1);
        TextView jumlah1519 = rowView.findViewById(R.id.m2);
        TextView jumlah2022 = rowView.findViewById(R.id.m3);
        TextView jumlah2325 = rowView.findViewById(R.id.m4);
        TextView jumlah2628 = rowView.findViewById(R.id.m5);
        TextView jumlah2931 = rowView.findViewById(R.id.m6);
        TextView jumlahLebihDari31 = rowView.findViewById(R.id.m7);
        TextView keteranganJmlSucker = rowView.findViewById(R.id.mKeteranganJmlSucker);
//        lokasi.setText(mLokasi.getText().toString());
//        aautoGrade.setText(autoGrade.getText().toString());
//        updatePeta.setText(mUpdatePeta.getText().toString());
        luasPlot.setText(mLuasPlot.getText().toString());

        plot.setText(mPlot.getText().toString());
        jumlahKurangDari15.setText(m1.getText().toString());
        jumlah1519.setText(m2.getText().toString());
        jumlah2022.setText(m3.getText().toString());
        jumlah2325.setText(m4.getText().toString());
        jumlah2628.setText(m5.getText().toString());
        jumlah2931.setText(m6.getText().toString());
        jumlahLebihDari31.setText(m7.getText().toString());
        keteranganJmlSucker.setText(mKeteranganJmlSucker.getText().toString());


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
        String selectedDateString = mUpdatePeta.getText().toString();
        no_sample.setText(String.valueOf(nextSampleNumber));

        sampleModel.setNo_spk2(mSPK.getText().toString());
        sampleModel.setNo_line(mLine.getText().toString());
        sampleModel.setNo_spk(model.getNO_SPK());
        sampleModel.setLokasi(mLokasi.getText().toString());
        sampleModel.setUpdate_peta(selectedDateString);
        sampleModel.setNo_sample(Integer.parseInt(no_sample.getText().toString()));
        sampleModel.setPg(model.getPG());
        sampleModel.setWil(autoWilayah.getText().toString());
        sampleModel.setGrade(autoGrade.getText().toString());
        sampleModel.setReworking(mReworking.getText().toString());
        sampleModel.setStatus_lokasi(mStatusLokasi.getText().toString());
        sampleModel.setPlot(Integer.parseInt(plot.getText().toString()));
        sampleModel.setLuas_plot(Float.parseFloat(luasPlot.getText().toString()));

        sampleModel.setJumlah_panjang_bonggol_kurang_dari_15(Integer.parseInt(jumlahKurangDari15.getText().toString()));
        sampleModel.setJumlah_panjang_bonggol_15_sampai_19(Integer.parseInt(jumlah1519.getText().toString()));
        sampleModel.setJumlah_panjang_bonggol_20_sampai_22(Integer.parseInt(jumlah2022.getText().toString()));
        sampleModel.setJumlah_panjang_bonggol_23_sampai_25(Integer.parseInt(jumlah2325.getText().toString()));
        sampleModel.setJumlah_panjang_bonggol_26_sampai_28(Integer.parseInt(jumlah2628.getText().toString()));
        sampleModel.setJumlah_panjang_bonggol_29_sampai_31(Integer.parseInt(jumlah2931.getText().toString()));
        sampleModel.setJumlah_panjang_bonggol_lebih_dari_31(Integer.parseInt(jumlahLebihDari31.getText().toString()));

        sampleModel.setKeterangan_jumlah_sucker(parStringDefault(keteranganJmlSucker.getText().toString(), "-"));
        sampleModel.setKeterangan_jumlah_indukan(parStringDefault(keteranganJmlSucker.getText().toString(), "-"));
        sampleModel.setLuas_lokasi_aktif(Float.parseFloat(mLuasLokasiAktif.getText().toString()));
//        sampleModel.setKeterangan(parStringDefault(mKeterangan.getText().toString(),"-"));
//        sampleModel.setKomposit_a4();

        sampleModel.setKeterangan(
                mKet.getText().toString().trim().isEmpty() ? "-" : mKet.getText().toString().trim()
        );

        dataSample.add(sampleModel);
        Log.d("datanyanih", new Gson().toJson(model));
        containerPlotData.addView(rowView, 0);

        //Hapus Saat Apply
        Toast.makeText(this, "Data Sudah Ditambahkan", Toast.LENGTH_SHORT).show();

        m1.getText().clear();
        m2.getText().clear();
        m3.getText().clear();
        m4.getText().clear();
        m5.getText().clear();
        m6.getText().clear();
        m7.getText().clear();
        mKeteranganJmlSucker.getText().clear();

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

    private void restoreSampleView(SampleModel sampleModel) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.data_bonggoltidakterseset_sample_field, null);

        TextView no_sample = rowView.findViewById(R.id.mNoSample);
        TextView plot = rowView.findViewById(R.id.mPlot);
        TextView luasPlot = rowView.findViewById(R.id.mLuasPlot);
        TextView jumlahKurangDari15 = rowView.findViewById(R.id.m1);
        TextView jumlah1519 = rowView.findViewById(R.id.m2);
        TextView jumlah2022 = rowView.findViewById(R.id.m3);
        TextView jumlah2325 = rowView.findViewById(R.id.m4);
        TextView jumlah2628 = rowView.findViewById(R.id.m5);
        TextView jumlah2931 = rowView.findViewById(R.id.m6);
        TextView jumlahLebihDari31 = rowView.findViewById(R.id.m7);
        TextView keteranganJmlSucker = rowView.findViewById(R.id.mKeteranganJmlSucker);

        // isi view dari model (gunakan getter sesuai model kamu)
        no_sample.setText(String.valueOf(sampleModel.getNo_sample()));
        plot.setText(String.valueOf(sampleModel.getPlot())); // atau getPLOT() sesuai model
        luasPlot.setText(String.valueOf(sampleModel.getLuas_plot()));
        jumlahKurangDari15.setText(String.valueOf(sampleModel.getJumlah_panjang_bonggol_kurang_dari_15()));
        jumlah1519.setText(String.valueOf(sampleModel.getJumlah_panjang_bonggol_15_sampai_19()));
        jumlah2022.setText(String.valueOf(sampleModel.getJumlah_panjang_bonggol_20_sampai_22()));
        jumlah2325.setText(String.valueOf(sampleModel.getJumlah_panjang_bonggol_23_sampai_25()));
        jumlah2628.setText(String.valueOf(sampleModel.getJumlah_panjang_bonggol_26_sampai_28()));
        jumlah2931.setText(String.valueOf(sampleModel.getJumlah_panjang_bonggol_29_sampai_31()));
        jumlahLebihDari31.setText(String.valueOf(sampleModel.getJumlah_panjang_bonggol_lebih_dari_31()));
        keteranganJmlSucker.setText(parStringDefault(sampleModel.getKeterangan_jumlah_sucker(), "-"));

        // tambahkan view di container
        containerPlotData.addView(rowView, 0);
    }

    // --- 3) clearForm() : reset semua field, data list dan UI, serta clear draft dari TemporaryFormStorage ---
    private void clearForm() {
        // reset header fields
        mSPK.setText("");
        mLine.setText("");
        mLokasi.setText("");
        autoWilayah.setText("");
        autoGrade.setText("");
        mUpdatePeta.setText("");
        mLuasPlot.setText("");
        mLuasLokasiAktif.setText("");
        mKeterangan.setText("");
        mKeteranganJmlSucker.setText("");
        mKet.setText("");

        // reset dropdowns if any:
        mReworking.setText("");
        mStatusLokasi.setText("");

        // clear data lists
        dataPlot.clear();
        dataSample.clear();

        // clear UI container
        containerPlotData.removeAllViews();

        // reset model
        model = new BonggolTidakTersesetModel();

        // remove draft storage
        TemporaryFormStorage.clearDraft(this, DRAFT_KEY);

        Log.d("FormReset", "Form BonggolTidakTerseset cleared");
    }

    // --- 4) override onPause / onResume supaya autosave & reload ---
    @Override
    protected void onPause() {
        super.onPause();
        saveTemporaryData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // reload draft (keamanan: hanya reload jika belum ada data di memori)
        loadTemporaryData();
    }

    // --- 5) pada onCreateSuccess clear draft dan form (supaya draft tidak muncul lagi setelah submit sukses) ---


    @Override
    public void onCreateSuccess(String rm) {
        // Tambahkan format timestamp
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        String currentTime = sdf.format(new Date()); // Mendapatkan waktu saat ini

        // Tambahkan waktu ke dalam pesan
        String message = "Bonggol Tidak Terseset"
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

    private static final String[] ListGrade = new String[]{
            "A", "B", "C"
    };
    private static final String[] mReworking2 = new String[]{
            "Sebelum Reworking", "Sesudah Reworking"
    };

    private static final String[] mStatusLokasi2 = new String[]{
            "NSSC", "NSBB"
    };
    private static final String[] autoWilayah2 = new String[]{
            "AW01", "AW02", "AW03", "AW04", "AW05", "AW06", "AW07", "AW08", "AW09", "AW10", "AW11", "AW12", "AW13", "AW14", "AW15", "AW16", "AW17", "AW18", "AW19", "AW20", "AW21", "AW22", "AW23"
    };
}