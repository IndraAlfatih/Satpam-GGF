package com.ggf.qcpp.e_formpengamatan.kebersihanpanen;

import static com.ggf.qcpp.utils.Utils.generateTglSekarang;
import static com.ggf.qcpp.utils.Utils.goToListPengamatan;

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
import android.widget.ScrollView;
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
import com.ggf.qcpp.e_formpengamatan.kebersihanpanen.model.PanenModel;
import com.ggf.qcpp.e_formpengamatan.kebersihanpanen.model.PlotModel;
import com.ggf.qcpp.e_formpengamatan.kebersihanpanen.model.SampleModel;
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

public class FormPengamatanKebersihanPanen extends AppCompatActivity implements View.OnClickListener, IFormPengamatanKebersihanPanenView {

    @BindView(R.id.mSubmit)
    Button mSubmit;
    @BindView(R.id.mAddSample)
    ImageView mAddSample;
    @BindView(R.id.containerPlotData)
    LinearLayout containerPlotData;
    @BindView(R.id.mPlot)
    EditText mPlot;
    @BindView(R.id.mKeterangan)
    EditText mKeterangan;

    //batas
    @BindView(R.id.mReworking)
    AutoCompleteTextView mReworking;
    @BindView(R.id.mTanggalPanen)
    EditText mTanggalPanen;
    @BindView(R.id.mSPK)
    EditText mSPK;

    @BindView(R.id.mLine)
    EditText mLine;

    @BindView(R.id.mTanggalPlot)
    EditText mTanggalPlot;

    @BindView(R.id.mLokasi)
    EditText mLokasi;

    @BindView(R.id.mUpdatePeta)
    EditText mUpdatePeta;

    @BindView(R.id.mLuasPlot)
    EditText mLuasPlot;

    @BindView(R.id.mReguPanen)
    EditText mReguPanen;

    @BindView(R.id.mWil)
    AutoCompleteTextView mWil;

    @BindView(R.id.mJalur)
    AutoCompleteTextView mJalur;

    @BindView(R.id.mStatusLokasi)
    AutoCompleteTextView mStatusLokasi;

    @BindView(R.id.mShift)
    AutoCompleteTextView mShift;
    @BindView(R.id.mStatusPengamatan)
    AutoCompleteTextView mStatusPengamatan;

    @BindView(R.id.mPanjangPengamatan)
    EditText mPanjangPengamatan;

    @BindView(R.id.mTidakCrown)
    EditText mTidakCrown;
    @BindView(R.id.mNormalBuahTertinggalBesar)
    EditText mNormalBuahTertinggalBesar;

    @BindView(R.id.mNormalBuahTertinggalSedang)
    EditText mNormalBuahTertinggalSedang;

    @BindView(R.id.mNormalBuahTertinggalKecil)
    EditText mNormalBuahTertinggalKecil;

    @BindView(R.id.mJumlahSalSekunder)
    EditText mJumlahSalSekunder;

    @BindView(R.id.mJumlahTitikPengamatan)
    EditText mJumlahTitikPengamatan;

    @BindView(R.id.mSekunderBuahTertinggalBesar)
    EditText mSekunderBuahTertinggalBesar;

    @BindView(R.id.mSekunderBuahTertinggalSedang)
    EditText mSekunderBuahTertinggalSedang;

    @BindView(R.id.mSekunderBuahTertinggalKecil)
    EditText mSekunderBuahTertinggalKecil;

    @BindView(R.id.mJumlahSalTersier)
    EditText mJumlahSalTersier;

    @BindView(R.id.mJumlahTitikDiamati)
    EditText mJumlahTitikDiamati;

    @BindView(R.id.mTersierBuahTertinggalBesar)
    EditText mTersierBuahTertinggalBesar;

    @BindView(R.id.mTersierBuahTertinggalSedang)
    EditText mTersierBuahTertinggalSedang;

    @BindView(R.id.mTersierBuahTertinggalKecil)
    EditText mTersierBuahTertinggalKecil;


    @BindView(R.id.mJumlahBaris)
    EditText mJumlahBaris;

    @BindView(R.id.mCrownNormal)
    EditText mCrownNormal;

    @BindView(R.id.mCrownKipas)
    EditText mCrownKipas;

    @BindView(R.id.mCrownBusukNormal)
    EditText mCrownBusukNormal;

    @BindView(R.id.mCrownBusukTidakNormal)
    EditText mCrownBusukTidakNormal;

    @BindView(R.id.mTotalCrown)
    EditText mTotalCrown;

    @BindView(R.id.mLebarPlot)
    EditText mLebarPlot;

    @BindView(R.id.mKet)
    EditText mKet;

    View rowView;
    View rowViewPlot;
    View viewnya = null;
    String plot = "0";
    PanenModel model;

    int index = 1;

    List<PlotModel> dataPlot = new ArrayList<>();
    List<SampleModel> dataSample = new ArrayList<>();

    SweetAlertDialog sweetAlertDialog;
    SampleModel sampleModel = null;
    PlotModel plotModel = null;
    FormPengamatanKebersihanPanenPresenter presenter;

    private ScrollView scrollView;
    private View contentView;
    private View focusedView;

    private static final String DRAFT_KEY = "draft_panen_kebersihan";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form_pengamatan_kebersihan_panen);

        ButterKnife.bind(this);
        presenter = new FormPengamatanKebersihanPanenPresenter(this);
        model = (PanenModel) getIntent().getSerializableExtra("model");

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
        mTanggalPanen.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        String selectedDate = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay;
                        mTanggalPanen.setText(selectedDate);
                    },
                    year, month, day);

            datePickerDialog.show();
        });

        Calendar calendar2 = Calendar.getInstance();
        int year2 = calendar2.get(Calendar.YEAR);
        int month2 = calendar2.get(Calendar.MONTH);
        int day2 = calendar2.get(Calendar.DAY_OF_MONTH);
        mUpdatePeta.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        String selectedDate = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay;
                        mUpdatePeta.setText(selectedDate);
                    },
                    year2, month2, day2);

            datePickerDialog.show();
        });

        Calendar calendar3 = Calendar.getInstance();
        int year3 = calendar3.get(Calendar.YEAR);
        int month3 = calendar3.get(Calendar.MONTH);
        int day3 = calendar3.get(Calendar.DAY_OF_MONTH);
        mTanggalPlot.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        String selectedDate = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay;
                        mTanggalPlot.setText(selectedDate);
                    },
                    year3, month3, day3);

            datePickerDialog.show();
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mShift2);
        mShift.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mWil2);
        mWil.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mJalur2);
        mJalur.setAdapter(adapter3);

        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mStatusLokasi2);
        mStatusLokasi.setAdapter(adapter4);

        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mStatusPengamatan2);
        mStatusPengamatan.setAdapter(adapter5);

        ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mReworking2);
        mReworking.setAdapter(adapter6);

        EditText lokasiUppercase = findViewById(R.id.mLokasi);
        lokasiUppercase.setFilters(new InputFilter[] {new InputFilter.AllCaps()});

        // Menonaktifkan input teks, tetapi dropdown masih muncul
        mShift.setKeyListener(null);
        mReworking.setKeyListener(null);
        mWil.setKeyListener(null);
        mJalur.setKeyListener(null);
        mStatusPengamatan.setKeyListener(null);
        mStatusLokasi.setKeyListener(null);

        // Memastikan dropdown muncul meskipun tidak ada teks yang dimasukkan
        mShift.setThreshold(1);
        mReworking.setThreshold(1);
        mWil.setThreshold(1);
        mJalur.setThreshold(1);
        mStatusPengamatan.setThreshold(1);
        mStatusLokasi.setThreshold(1);

        model = (PanenModel) getIntent().getSerializableExtra("model");
        Log.d("bajakmodel", new Gson().toJson(model));

        presenter = new FormPengamatanKebersihanPanenPresenter(this);
        mSubmit.setOnClickListener(this);
        mAddSample.setOnClickListener(this);
    }

    private boolean isEmpty(EditText editText) {
        return editText.getText().toString().trim().isEmpty();
    }

    void addPlotForm() {
        if (isEmpty(mSPK)) {
            Toast.makeText(this, "No SPK tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEmpty(mLine)) {
            Toast.makeText(this, "No Line tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEmpty(mTanggalPanen)) {
            Toast.makeText(this, "Tanggal Awal Panen tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEmpty(mUpdatePeta)) {
            Toast.makeText(this, "Update Peta tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEmpty(mReworking)) {
            Toast.makeText(this, "Status Reworking tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEmpty(mShift)) {
            Toast.makeText(this, "Shift tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEmpty(mLuasPlot)) {
            Toast.makeText(this, "Luas plot tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEmpty(mReguPanen)) {
            Toast.makeText(this, "Regu panen tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEmpty(mWil)) {
            Toast.makeText(this, "Wilayah tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEmpty(mPanjangPengamatan)) {
            Toast.makeText(this, "Panjang Pengamatan tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEmpty(mLokasi)) {
            Toast.makeText(this, "Lokasi tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEmpty(mPlot)) {
            Toast.makeText(this, "No Plot tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEmpty(mJalur)) {
            Toast.makeText(this, "Jalur Plot tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEmpty(mLebarPlot)) {
            Toast.makeText(this, "Lebar Plot tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEmpty(mStatusPengamatan)) {
            Toast.makeText(this, "Status Pengamatan tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEmpty(mStatusLokasi)) {
            Toast.makeText(this, "Status Lokasi tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEmpty(mTanggalPlot)) {
            Toast.makeText(this, "Tanggal Plot tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEmpty(mNormalBuahTertinggalBesar)) {
            Toast.makeText(this, "Jalur Normal Besar tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEmpty(mNormalBuahTertinggalSedang)) {
            Toast.makeText(this, "Jalur Normal Sedang tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEmpty(mNormalBuahTertinggalKecil)) {
            Toast.makeText(this, "Jalur Normal Kecil tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEmpty(mSekunderBuahTertinggalBesar)) {
            Toast.makeText(this, "Saluran Sekunder Besar tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEmpty(mSekunderBuahTertinggalSedang)) {
            Toast.makeText(this, "Saluran Sekunder Sedang tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEmpty(mSekunderBuahTertinggalKecil)) {
            Toast.makeText(this, "Saluran Sekunder Kecil tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEmpty(mJumlahSalSekunder)) {
            Toast.makeText(this, "Jumlah Saluran Sekunder tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEmpty(mJumlahTitikPengamatan)) {
            Toast.makeText(this, "Jumlah Saluran Sekunder Diamati tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEmpty(mTersierBuahTertinggalBesar)) {
            Toast.makeText(this, "Saluran Tersier Besar tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEmpty(mTersierBuahTertinggalSedang)) {
            Toast.makeText(this, "Saluran Tersier Sedang tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEmpty(mTersierBuahTertinggalKecil)) {
            Toast.makeText(this, "Saluran Tersier Kecil tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEmpty(mJumlahSalTersier)) {
            Toast.makeText(this, "Jumlah Saluran Tersier tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEmpty(mJumlahTitikDiamati)) {
            Toast.makeText(this, "Jumlah Saluran Tersier Diamati tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEmpty(mJumlahBaris)) {
            Toast.makeText(this, "Jumlah Baris Diamati tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEmpty(mTotalCrown)) {
            Toast.makeText(this, "Tanaman Mandul tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEmpty(mTidakCrown)) {
            Toast.makeText(this, "Buah Tidak Tumbuh Crown tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEmpty(mCrownNormal)) {
            Toast.makeText(this, "Crown Normal tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEmpty(mCrownKipas)) {
            Toast.makeText(this, "Crown Kipas tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEmpty(mCrownBusukNormal)) {
            Toast.makeText(this, "Crown Busuk Normal tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEmpty(mCrownBusukTidakNormal)) {
            Toast.makeText(this, "Crown Busuk Tidak Normal tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        // Default value untuk keterangan
        if (mKeterangan.getText().toString().trim().isEmpty()) {
            mKeterangan.setText("-");
        }

        sampleModel = new SampleModel();
        boolean plotExists = false;

        // Cek apakah plot sudah ada
        for (PlotModel existingPlot : dataPlot) {
            if (existingPlot.getPLOT().equals(mPlot.getText().toString())) {
                plotExists = true;
                dataSample = existingPlot.getSAMPLE(); // Ambil daftar sampel dari plot
                this.addSampleForm(); // Tambahkan data sampel
                break;
            }
        }

        if (!plotExists) {
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

    void addSampleForm() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rowView = inflater.inflate(R.layout.data_panen_sample_field, null);

        final TextView noSample = rowView.findViewById(R.id.mNoSample);
        final TextView plot = rowView.findViewById(R.id.mPlot);
        final TextView shift = rowView.findViewById(R.id.mShift);
        final TextView luasplot = rowView.findViewById(R.id.mLuasPlot);
        final TextView regu = rowView.findViewById(R.id.mReguPanen);
        final TextView wilayah = rowView.findViewById(R.id.mWil);
        final TextView lokasi = rowView.findViewById(R.id.mLokasi);
        final TextView panjangpengamatan = rowView.findViewById(R.id.mPanjangPengamatan);
        final TextView normalbesar = rowView.findViewById(R.id.mNormalBuahTertinggalBesar);
        final TextView normalsedang = rowView.findViewById(R.id.mNormalBuahTertinggalSedang);
        final TextView normalkecil = rowView.findViewById(R.id.mNormalBuahTertinggalKecil);
        final TextView sekunderbesar = rowView.findViewById(R.id.mSekunderBuahTertinggalBesar);
        final TextView sekundersedang = rowView.findViewById(R.id.mSekunderBuahTertinggalSedang);
        final TextView sekunderkecil = rowView.findViewById(R.id.mSekunderBuahTertinggalKecil);
        final TextView sekundertot = rowView.findViewById(R.id.mJumlahSalSekunder);
        final TextView sekundertitik = rowView.findViewById(R.id.mJumlahTitikPengamatan);
        final TextView tersierbesar = rowView.findViewById(R.id.mTersierBuahTertinggalBesar);
        final TextView tersiersedang = rowView.findViewById(R.id.mTersierBuahTertinggalSedang);
        final TextView tersierkecil = rowView.findViewById(R.id.mTersierBuahTertinggalKecil);
        final TextView tersiertot = rowView.findViewById(R.id.mJumlahSalTersier);
        final TextView tersiertitik = rowView.findViewById(R.id.mJumlahTitikDiamati);
        final TextView totcrown = rowView.findViewById(R.id.mTotalCrown);
        final TextView jumlahbaris = rowView.findViewById(R.id.mJumlahBaris);
        final TextView jalur = rowView.findViewById(R.id.mJalur);
        final TextView statuspengamatan = rowView.findViewById(R.id.mStatusPengamatan);
        final TextView crownnormal = rowView.findViewById(R.id.mCrownNormal);
        final TextView crownkipas = rowView.findViewById(R.id.mCrownKipas);
        final TextView busuknormal = rowView.findViewById(R.id.mCrownBusukNormal);
        final TextView busuktidaknormal = rowView.findViewById(R.id.mCrownBusukTidakNormal);
        final TextView statuslokasi = rowView.findViewById(R.id.mStatusLokasi);

        luasplot.setText(mLuasPlot.getText().toString());
        regu.setText(mReguPanen.getText().toString());
        lokasi.setText(mLokasi.getText().toString());
        wilayah.setText(mWil.getText().toString());
        shift.setText(mShift.getText().toString());
        panjangpengamatan.setText(mPanjangPengamatan.getText().toString());
        normalbesar.setText(mNormalBuahTertinggalBesar.getText().toString());
        normalsedang.setText(mNormalBuahTertinggalSedang.getText().toString());
        normalkecil.setText(mNormalBuahTertinggalKecil.getText().toString());
        sekunderbesar.setText(mSekunderBuahTertinggalBesar.getText().toString());
        sekundersedang.setText(mSekunderBuahTertinggalSedang.getText().toString());
        sekunderkecil.setText(mSekunderBuahTertinggalKecil.getText().toString());
        sekundertot.setText(mJumlahSalSekunder.getText().toString());
        sekundertitik.setText(mJumlahTitikPengamatan.getText().toString());
        tersierbesar.setText(mTersierBuahTertinggalBesar.getText().toString());
        tersiersedang.setText(mTersierBuahTertinggalSedang.getText().toString());
        tersierkecil.setText(mTersierBuahTertinggalKecil.getText().toString());
        tersiertot.setText(mJumlahSalTersier.getText().toString());
        tersiertitik.setText(mJumlahTitikDiamati.getText().toString());
        totcrown.setText(mTotalCrown.getText().toString());
        jumlahbaris.setText(mJumlahBaris.getText().toString());
        jalur.setText(mJalur.getText().toString());
        statuspengamatan.setText(mStatusPengamatan.getText().toString());
        crownnormal.setText(mCrownNormal.getText().toString());
        crownkipas.setText(mCrownKipas.getText().toString());
        busuknormal.setText(mCrownBusukNormal.getText().toString());
        busuktidaknormal.setText(mCrownBusukTidakNormal.getText().toString());
        statuslokasi.setText(mStatusLokasi.getText().toString());

        plot.setText(mPlot.getText().toString());

        List<Integer> existingSamples = new ArrayList<>();
        for (SampleModel sample : dataSample) {
            if (sample.getPLOT() == Integer.parseInt(mPlot.getText().toString())) {
                existingSamples.add(sample.getNo_sample());
            }
        }

        Collections.sort(existingSamples);
        int nextSampleNumber = 1;
        for (int i = 0; i < existingSamples.size(); i++) {
            if (existingSamples.get(i) != nextSampleNumber) {
                break;
            }
            nextSampleNumber++;
        }

        sampleModel.setNo_spk(model.getNO_SPK());

        String selectedDateString2 = mTanggalPanen.getText().toString();
        sampleModel.setTanggal_panen(selectedDateString2);

        String selectedDateString4 = mTanggalPlot.getText().toString();
        sampleModel.setTanggal_plot(selectedDateString4);

        String selectedDateString3 = mUpdatePeta.getText().toString();
        sampleModel.setUpdate_peta(selectedDateString3);

        noSample.setText(String.valueOf(nextSampleNumber));
        sampleModel.setNo_sample(noSample.getText().toString());
        sampleModel.setShift(mShift.getText().toString());
        sampleModel.setPLOT(Integer.parseInt(mPlot.getText().toString()));
        sampleModel.setLuas_plot(Float.parseFloat(mLuasPlot.getText().toString()));
        sampleModel.setRegu_panen(mReguPanen.getText().toString());
        sampleModel.setWil(mWil.getText().toString());
        sampleModel.setReworking(mReworking.getText().toString());
        sampleModel.setLokasi(mLokasi.getText().toString());
        sampleModel.setNo_spk2(mSPK.getText().toString());
        sampleModel.setNo_line(mLine.getText().toString());
        sampleModel.setTidak_crown(Integer.parseInt(mTidakCrown.getText().toString()));
        sampleModel.setPanjang_pengamatan(Integer.parseInt(mPanjangPengamatan.getText().toString()));
        sampleModel.setNormal_buah_tertinggal_besar(Integer.parseInt(mNormalBuahTertinggalBesar.getText().toString()));
        sampleModel.setNormal_buah_tertinggal_sedang(Integer.parseInt(mNormalBuahTertinggalSedang.getText().toString()));
        sampleModel.setNormal_buah_tertinggal_kecil(Integer.parseInt(mNormalBuahTertinggalKecil.getText().toString()));
        sampleModel.setSekunder_buah_tertinggal_besar(Integer.parseInt(mSekunderBuahTertinggalBesar.getText().toString()));
        sampleModel.setSekunder_buah_tertinggal_sedang(Integer.parseInt(mSekunderBuahTertinggalSedang.getText().toString()));
        sampleModel.setSekunder_buah_tertinggal_kecil(Integer.parseInt(mSekunderBuahTertinggalKecil.getText().toString()));
        sampleModel.setJumlah_sal_sekunder(Integer.parseInt(mJumlahSalSekunder.getText().toString()));
        sampleModel.setJumlah_titik_pengamatan(Integer.parseInt(mJumlahTitikPengamatan.getText().toString()));
        sampleModel.setTersier_buah_tertinggal_besar(Integer.parseInt(mTersierBuahTertinggalBesar.getText().toString()));
        sampleModel.setTersier_buah_tertinggal_sedang(Integer.parseInt(mTersierBuahTertinggalSedang.getText().toString()));
        sampleModel.setTersier_buah_tertinggal_kecil(Integer.parseInt(mTersierBuahTertinggalKecil.getText().toString()));
        sampleModel.setJumlah_sal_tersier(Integer.parseInt(mJumlahSalTersier.getText().toString()));
        sampleModel.setJumlah_titik_diamati(Integer.parseInt(mJumlahTitikDiamati.getText().toString()));
        sampleModel.setTotal_crown(
                Integer.parseInt(mKet.getText().toString().trim().isEmpty() ? "0" : mTotalCrown.getText().toString().trim())
        );
        sampleModel.setJumlah_baris(Integer.parseInt(mJumlahBaris.getText().toString()));
        sampleModel.setJalur(mJalur.getText().toString());
        sampleModel.setStatuslokasi(mStatusLokasi.getText().toString());
        sampleModel.setCrownnormal(mCrownNormal.getText().toString());
        sampleModel.setCrownkipas(mCrownKipas.getText().toString());
        sampleModel.setCrownbusuknormal(mCrownBusukNormal.getText().toString());
        sampleModel.setCrownbusuktidaknormal(mCrownBusukTidakNormal.getText().toString());
        sampleModel.setStatuspengamatan(mStatusPengamatan.getText().toString());
        sampleModel.setLebar_plot(Float.parseFloat(mLebarPlot.getText().toString()));
        sampleModel.setKeterangan(
                mKet.getText().toString().trim().isEmpty() ? "-" : mKet.getText().toString().trim()
        );

        dataSample.add(sampleModel);
        containerPlotData.addView(rowView, 0);

        //Hapus Saat Apply (clear only sample inputs)
        Toast.makeText(this, "Data Sudah Ditambahkan", Toast.LENGTH_SHORT).show();
        mNormalBuahTertinggalBesar.getText().clear();
        mNormalBuahTertinggalSedang.getText().clear();
        mNormalBuahTertinggalKecil.getText().clear();
        mSekunderBuahTertinggalBesar.getText().clear();
        mSekunderBuahTertinggalSedang.getText().clear();
        mSekunderBuahTertinggalKecil.getText().clear();
        mTidakCrown.getText().clear();
        mTersierBuahTertinggalBesar.getText().clear();
        mTersierBuahTertinggalSedang.getText().clear();
        mTersierBuahTertinggalKecil.getText().clear();
        mJumlahSalSekunder.getText().clear();
        mJumlahSalTersier.getText().clear();
        mJumlahTitikDiamati.getText().clear();
        mJumlahTitikPengamatan.getText().clear();
        mTotalCrown.getText().clear();
        mJumlahBaris.getText().clear();
        mCrownNormal.getText().clear();
        mCrownKipas.getText().clear();
        mCrownBusukTidakNormal.getText().clear();
        mCrownBusukNormal.getText().clear();

        // After modifying dataPlot/dataSample, save draft
        saveTemporaryData();
    }

    public void onDeleteSample(View v) {
        TextView txtPlot = ((View) v.getParent()).findViewById(R.id.mPlot);
        TextView txtNoSample = ((View) v.getParent()).findViewById(R.id.mNoSample);
        int plotToDelete = Integer.parseInt(txtPlot.getText().toString());
        int noSampleToDelete = Integer.parseInt(txtNoSample.getText().toString());

        Log.d("Hapus", "Menghapus data dengan PLOT: " + plotToDelete + " dan No_Sample: " + noSampleToDelete);

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

                if (samples.isEmpty()) {
                    dataPlot.remove(i);
                    Log.d("Hapus", "Seluruh data untuk plot " + plotToDelete + " telah dihapus.");
                }
                break;
            }
        }

        for (int i = 0; i < dataSample.size(); i++) {
            if (dataSample.get(i).getPLOT() == plotToDelete &&
                    dataSample.get(i).getNo_sample() == noSampleToDelete) {
                dataSample.remove(i);
                Log.d("Hapus", "Data di dataSample dihapus. PLOT: " + plotToDelete + ", No_Sample: " + noSampleToDelete);
                break;
            }
        }

        if (dataRemoved) {
            ((ViewGroup) v.getParent().getParent()).removeView((ViewGroup) v.getParent());
            Log.d("Hapus", "View dihapus dari containerPlotData.");
            // update draft because data changed
            saveTemporaryData();
        } else {
            Log.d("Hapus", "Data tidak ditemukan untuk dihapus.");
        }

        Log.d("Hapus", "State dataPlot setelah penghapusan: " + new Gson().toJson(dataPlot));
        Log.d("Hapus", "State dataSample setelah penghapusan: " + new Gson().toJson(dataSample));
    }

    @Override
    public void onSubmit() {
        if (dataPlot.size() > 0) {
//            sampleModel.setKeterangan(mKeterangan.getText().toString());
//            plotModel.setSAMPLE(dataSample);
            model.setLOKASI(mLokasi.getText().toString());
            model.setDATA(dataPlot);
//            sampleModel.setKeterangan(
//                    mKet.getText().toString().trim().isEmpty() ? "-" : mKet.getText().toString().trim()
//            );
            Log.d("dataBody", new Gson().toJson(model));
            presenter.createPengamatan(model);
        } else
            SweetDialogs.commonError(this, "Harap apply data terlebih dahulu", false);
    }

    @Override
    public void onCreateSuccess(String rm) {
        // Tambahkan format timestamp
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        String currentTime = sdf.format(new Date());
        String message = "Kebersihan Panen"
                + ", Lokasi: " + mLokasi.getText().toString()
                + "\nTanggal: " + generateTglSekarang()
                + "\nWaktu: " + currentTime
                + "\n"+getString(R.string.versi_apps);

        SweetDialogs.commonSuccessWithIntent(this, message, string -> {
            goToListPengamatan(this);
        });
        // Clear draft dan reset form
        TemporaryFormStorage.clearDraft(this, DRAFT_KEY);
        clearForm();
    }

    @Override
    public void onCreateFailed(String eror) {
        SweetDialogs.commonError(this, eror, true);
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
    public void onNetworkError(String cause , String data) {
        Log.e("errornya", cause);
        SQLiteHelper dbHelper = new SQLiteHelper(this);
        dbHelper.saveChopperData(data, model.getNO_SPK());

        Log.d("Saved data", "Data saved to SQLite: " + data);
        Log.d("Saved data", "Data saved to SQLite: " + model.getNO_SPK());
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

    private static final String[] mShift2 = new String[]{
            "Pagi", "Malam"
    };

    private static final String[] mJalur2 = new String[]{
            "A", "B", "Parimeter"
    };

    private static final String[] mStatusLokasi2 = new String[]{
            "NSSC", "NSFC"
    };
    private static final String[] mReworking2 = new String[]{
            "Sebelum Reworking", "Sesudah Reworking"
    };
    private static final String[] mStatusPengamatan2 = new String[]{
            "Crosscheck", "Inprocess"
    };

    private static final String[] mWil2 = new String[]{
            "AW01", "AW02", "AW03", "AW04", "AW05", "AW06", "AW07", "AW08", "AW09", "AW10", "AW11", "AW12", "AW13", "AW14", "AW15", "AW16", "AW17", "AW18", "AW19", "AW20", "AW21", "AW22", "AW23"
    };

    // ---------- DRAFT / AUTOSAVE METHODS ----------
    private void saveTemporaryData() {
        PanenModel draft = new PanenModel();
        draft.setNO_SPK(mSPK.getText().toString());
        draft.setNO_LINE(mLine.getText().toString());
        draft.setLOKASI(mLokasi.getText().toString());
        draft.setTanggal_panen(mTanggalPanen.getText().toString());
        draft.setUpdate_peta(mUpdatePeta.getText().toString());
        draft.setReworking(mReworking.getText().toString());

        draft.setDATA(dataPlot);

        TemporaryFormStorage.saveDraft(this, DRAFT_KEY, draft);
        Log.d("DraftSave", "Draft panen saved: " + new Gson().toJson(draft));
    }

    private void loadTemporaryData() {
        PanenModel draft = TemporaryFormStorage.loadDraft(this, DRAFT_KEY, PanenModel.class);
        if (draft != null) {
            Log.d("DraftLoad", "Draft panen loaded: " + new Gson().toJson(draft));

            mSPK.setText(draft.getNO_SPK());
            mLine.setText(draft.getNO_LINE());
            mLokasi.setText(draft.getLOKASI());

            mTanggalPanen.setText(draft.getTanggal_panen());
            mUpdatePeta.setText(draft.getUpdate_peta());
            mReworking.setText(draft.getReworking());

            containerPlotData.removeAllViews();
            dataPlot.clear();
            dataSample.clear();

            if (draft.getDATA() != null) {
                dataPlot = draft.getDATA();
                for (PlotModel p : dataPlot) {
                    List<SampleModel> samples = p.getSAMPLE();
                    if (samples != null) {
                        for (SampleModel s : samples) {
                            // restore sample view and also add to dataSample so deletion logic consistent
                            restoreSampleView(s);
                            dataSample.add(s);
                        }
                    }
                }
            }

            // ✅ Tambahkan ini di akhir: rebind adapter biar dropdown aktif lagi
            mShift.post(() -> {
                ArrayAdapter<String> adapterShift = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, mShift2);
                mShift.setAdapter(adapterShift);
            });

            mReworking.post(() -> {
                ArrayAdapter<String> adapterReworking = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, mReworking2);
                mReworking.setAdapter(adapterReworking);
            });

            mWil.post(() -> {
                ArrayAdapter<String> adapterWil = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, mWil2);
                mWil.setAdapter(adapterWil);
            });

            mJalur.post(() -> {
                ArrayAdapter<String> adapterJalur = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, mJalur2);
                mJalur.setAdapter(adapterJalur);
            });

            mStatusPengamatan.post(() -> {
                ArrayAdapter<String> adapterStatusPengamatan = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, mStatusPengamatan2);
                mStatusPengamatan.setAdapter(adapterStatusPengamatan);
            });

            mStatusLokasi.post(() -> {
                ArrayAdapter<String> adapterStatusLokasi = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, mStatusLokasi2);
                mStatusLokasi.setAdapter(adapterStatusLokasi);
            });

        }
    }

    private void restoreSampleView(SampleModel sample) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.data_panen_sample_field, null);

        TextView noSample = rowView.findViewById(R.id.mNoSample);
        TextView shift = rowView.findViewById(R.id.mShift);
        TextView luasplot = rowView.findViewById(R.id.mLuasPlot);
        TextView regu = rowView.findViewById(R.id.mReguPanen);
        TextView wilayah = rowView.findViewById(R.id.mWil);
        TextView lokasi = rowView.findViewById(R.id.mLokasi);
        TextView panjangpengamatan = rowView.findViewById(R.id.mPanjangPengamatan);
        TextView normalbesar = rowView.findViewById(R.id.mNormalBuahTertinggalBesar);
        TextView normalsedang = rowView.findViewById(R.id.mNormalBuahTertinggalSedang);
        TextView normalkecil = rowView.findViewById(R.id.mNormalBuahTertinggalKecil);
        TextView sekunderbesar = rowView.findViewById(R.id.mSekunderBuahTertinggalBesar);
        TextView sekundersedang = rowView.findViewById(R.id.mSekunderBuahTertinggalSedang);
        TextView sekunderkecil = rowView.findViewById(R.id.mSekunderBuahTertinggalKecil);
        TextView sekundertot = rowView.findViewById(R.id.mJumlahSalSekunder);
        TextView sekundertitik = rowView.findViewById(R.id.mJumlahTitikPengamatan);
        TextView tersierbesar = rowView.findViewById(R.id.mTersierBuahTertinggalBesar);
        TextView tersiersedang = rowView.findViewById(R.id.mTersierBuahTertinggalSedang);
        TextView tersierkecil = rowView.findViewById(R.id.mTersierBuahTertinggalKecil);
        TextView tersiertot = rowView.findViewById(R.id.mJumlahSalTersier);
        TextView tersiertitik = rowView.findViewById(R.id.mJumlahTitikDiamati);
        TextView totcrown = rowView.findViewById(R.id.mTotalCrown);
        TextView jumlahbaris = rowView.findViewById(R.id.mJumlahBaris);
        TextView jalur = rowView.findViewById(R.id.mJalur);
        TextView statuspengamatan = rowView.findViewById(R.id.mStatusPengamatan);
        TextView crownnormal = rowView.findViewById(R.id.mCrownNormal);
        TextView crownkipas = rowView.findViewById(R.id.mCrownKipas);
        TextView busuknormal = rowView.findViewById(R.id.mCrownBusukNormal);
        TextView busuktidaknormal = rowView.findViewById(R.id.mCrownBusukTidakNormal);
        TextView statuslokasi = rowView.findViewById(R.id.mStatusLokasi);
        TextView plotNo = rowView.findViewById(R.id.mPlot);

        noSample.setText(String.valueOf(sample.getNo_sample()));
        shift.setText(sample.getShift());
        luasplot.setText(String.valueOf(sample.getLuas_plot()));
        regu.setText(sample.getRegu_panen());
        wilayah.setText(sample.getWil());
        lokasi.setText(sample.getLokasi());
        panjangpengamatan.setText(String.valueOf(sample.getPanjang_pengamatan()));
        normalbesar.setText(String.valueOf(sample.getNormal_buah_tertinggal_besar()));
        normalsedang.setText(String.valueOf(sample.getNormal_buah_tertinggal_sedang()));
        normalkecil.setText(String.valueOf(sample.getNormal_buah_tertinggal_kecil()));
        sekunderbesar.setText(String.valueOf(sample.getSekunder_buah_tertinggal_besar()));
        sekundersedang.setText(String.valueOf(sample.getSekunder_buah_tertinggal_sedang()));
        sekunderkecil.setText(String.valueOf(sample.getSekunder_buah_tertinggal_kecil()));
        sekundertot.setText(String.valueOf(sample.getJumlah_sal_sekunder()));
        sekundertitik.setText(String.valueOf(sample.getJumlah_titik_pengamatan()));
        tersierbesar.setText(String.valueOf(sample.getTersier_buah_tertinggal_besar()));
        tersiersedang.setText(String.valueOf(sample.getTersier_buah_tertinggal_sedang()));
        tersierkecil.setText(String.valueOf(sample.getTersier_buah_tertinggal_kecil()));
        tersiertot.setText(String.valueOf(sample.getJumlah_sal_tersier()));
        tersiertitik.setText(String.valueOf(sample.getJumlah_titik_diamati()));
        totcrown.setText(String.valueOf(sample.getTotal_crown()));
        jumlahbaris.setText(String.valueOf(sample.getJumlah_baris()));
        jalur.setText(sample.getJalur());
        statuspengamatan.setText(sample.getStatuspengamatan());
        crownnormal.setText(sample.getCrownnormal());
        crownkipas.setText(sample.getCrownkipas());
        busuknormal.setText(sample.getCrownbusuknormal());
        busuktidaknormal.setText(sample.getCrownbusuktidaknormal());
        statuslokasi.setText(sample.getStatuslokasi());
        plotNo.setText(String.valueOf(sample.getPLOT()));

        containerPlotData.addView(rowView, 0);
    }

    private void clearForm() {
        // Kosongkan header fields
        mSPK.setText("");
        mLine.setText("");
        mLokasi.setText("");
        mReguPanen.setText("");
        mWil.setText("");
        mUpdatePeta.setText("");
        mTanggalPanen.setText("");
        mTanggalPlot.setText("");
        mLuasPlot.setText("");
        mLebarPlot.setText("");
        mShift.setText("");
        mReworking.setText("");
        mStatusPengamatan.setText("");
        mStatusLokasi.setText("");
        mPlot.setText("");
        mKeterangan.setText("");
        mKet.setText("");

        // Kosongkan sample inputs
        mPanjangPengamatan.getText().clear();
        mTidakCrown.getText().clear();
        mNormalBuahTertinggalBesar.getText().clear();
        mNormalBuahTertinggalSedang.getText().clear();
        mNormalBuahTertinggalKecil.getText().clear();
        mSekunderBuahTertinggalBesar.getText().clear();
        mSekunderBuahTertinggalSedang.getText().clear();
        mSekunderBuahTertinggalKecil.getText().clear();
        mJumlahSalSekunder.getText().clear();
        mJumlahTitikPengamatan.getText().clear();
        mTersierBuahTertinggalBesar.getText().clear();
        mTersierBuahTertinggalSedang.getText().clear();
        mTersierBuahTertinggalKecil.getText().clear();
        mJumlahSalTersier.getText().clear();
        mJumlahTitikDiamati.getText().clear();
        mJumlahBaris.getText().clear();
        mCrownNormal.getText().clear();
        mCrownKipas.getText().clear();
        mCrownBusukNormal.getText().clear();
        mCrownBusukTidakNormal.getText().clear();
        mTotalCrown.getText().clear();

        // Clear in-memory lists and UI
        dataPlot.clear();
        dataSample.clear();
        containerPlotData.removeAllViews();

        // Reset model
        model = new PanenModel();

        // Hapus draft tersimpan
        TemporaryFormStorage.clearDraft(this, DRAFT_KEY);

        Log.d("FormReset", "Form panen cleared");
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
}
