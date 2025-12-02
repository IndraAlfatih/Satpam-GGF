package com.ggf.qcpp.e_formpengamatan.gudangmixer;

import static com.ggf.qcpp.utils.Utils.generateTglSekarang;
import static com.ggf.qcpp.utils.Utils.goToListPengamatan;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ggf.qcpp.App;
import com.ggf.qcpp.R;
import com.ggf.qcpp.c_home.c_1_home;
import com.ggf.qcpp.e_formpengamatan.gudangmixer.model.GudangMixerModel;
import com.ggf.qcpp.e_formpengamatan.gudangmixer.model.PlotModel;
import com.ggf.qcpp.e_formpengamatan.gudangmixer.model.SampleModel;
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

public class FormPengamatanGudangMixer extends AppCompatActivity implements View.OnClickListener, IFormPengamatanGudangMixerView {

    @BindView(R.id.mSubmit) Button mSubmit;
    @BindView(R.id.mAddSample) ImageView mAddSample;
    @BindView(R.id.containerPlotData) LinearLayout containerPlotData;
    @BindView(R.id.mKeterangan) EditText mKeterangan;
    @BindView(R.id.mLokasi) EditText mLokasi;
    @BindView(R.id.mLokasiCor) EditText mLokasiCor;
    @BindView(R.id.mPlot) EditText mPlot;
    @BindView(R.id.mReworking) AutoCompleteTextView mReworking;
    @BindView(R.id.mTanggalPengamatan) EditText mTanggalPengamatan;
    @BindView(R.id.mMandorBibit) EditText mMandorBibit;
    @BindView(R.id.mDiv) AutoCompleteTextView mDiv;
    @BindView(R.id.mKodeUnitBsc) AutoCompleteTextView mKodeUnitBsc;
    @BindView(R.id.mShift) AutoCompleteTextView mShift;
    @BindView(R.id.mJenisBahan) AutoCompleteTextView mJenisBahan;
    @BindView(R.id.mRencana) EditText mRencana;
    @BindView(R.id.mReal) EditText mReal;
    @BindView(R.id.mJenisBahan2) AutoCompleteTextView mJenisBahan2;
    @BindView(R.id.mRencana2) EditText mRencana2;
    @BindView(R.id.mSPK) EditText mSPK;
    @BindView(R.id.mLine) EditText mLine;
    @BindView(R.id.mReal2) EditText mReal2;
    @BindView(R.id.mJenisBahan3) AutoCompleteTextView mJenisBahan3;
    @BindView(R.id.mRencana3) EditText mRencana3;
    @BindView(R.id.mReal3) EditText mReal3;
    @BindView(R.id.mJenisBahan4) AutoCompleteTextView mJenisBahan4;
    @BindView(R.id.mRencana4) EditText mRencana4;
    @BindView(R.id.mReal4) EditText mReal4;
    @BindView(R.id.mJenisBahan5) AutoCompleteTextView mJenisBahan5;
    @BindView(R.id.mRencana5) EditText mRencana5;
    @BindView(R.id.mReal5) EditText mReal5;
    @BindView(R.id.mJenisBahan6) AutoCompleteTextView mJenisBahan6;
    @BindView(R.id.mRencana6) EditText mRencana6;
    @BindView(R.id.mReal6) EditText mReal6;
    @BindView(R.id.mJenisBahan7) AutoCompleteTextView mJenisBahan7;
    @BindView(R.id.mRencana7) EditText mRencana7;
    @BindView(R.id.mReal7) EditText mReal7;
    @BindView(R.id.mJenisBahan8) AutoCompleteTextView mJenisBahan8;
    @BindView(R.id.mRencana8) EditText mRencana8;
    @BindView(R.id.mReal8) EditText mReal8;
    @BindView(R.id.mJenisBahan9) AutoCompleteTextView mJenisBahan9;
    @BindView(R.id.mRencana9) EditText mRencana9;
    @BindView(R.id.mReal9) EditText mReal9;
    @BindView(R.id.mJenisBahan10) AutoCompleteTextView mJenisBahan10;
    @BindView(R.id.mRencana10) EditText mRencana10;
    @BindView(R.id.mReal10) EditText mReal10;
    @BindView(R.id.mMulaiAdukanMixerKecil) EditText mMulaiAdukanMixerKecil;
    @BindView(R.id.mSelesaiAdukanMixerKecil) EditText mSelesaiAdukanMixerKecil;
    @BindView(R.id.mMulaiAdukanMixerBesar) EditText mMulaiAdukanMixerBesar;
    @BindView(R.id.mSelesaiAdukanMixerBesar) EditText mSelesaiAdukanMixerBesar;
    @BindView(R.id.mKodeUnitTangkiSuplay) AutoCompleteTextView mKodeUnitTangkiSuplay;
    @BindView(R.id.mVolumeAirTigaPerempat) AutoCompleteTextView mVolumeAirTigaPerempat;
    @BindView(R.id.mRencanaVolumeAir) EditText mRencanaVolumeAir;
    @BindView(R.id.mRealVolumeAir) EditText mRealVolumeAir;
    @BindView(R.id.mJenisAplikasi) AutoCompleteTextView mJenisAplikasi;
    @BindView(R.id.mKeteranganPengisian) EditText mKeteranganPengisian;
    @BindView(R.id.mKet) EditText mKet;
    @BindView(R.id.mTangkiMixer) AutoCompleteTextView mTangkiMixer;
    @BindView(R.id.mCuciBilas) AutoCompleteTextView mCuciBilas;


    View rowView;
    View rowViewPlot;
    View viewnya = null;
    String plot = "0";
    GudangMixerModel model;
    int index = 1;
    List<PlotModel> dataPlot = new ArrayList<>();
    List<SampleModel> dataSample = new ArrayList<>();
    SweetAlertDialog sweetAlertDialog;
    SampleModel sampleModel = null;
    PlotModel plotModel = null;
    FormPengamatanGudangMixerPresenter presenter;
    private ScrollView scrollView;
    private View contentView;
    private View focusedView;
    private static final String DRAFT_KEY = "draft_gudang_mixer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form_pengamatan_gudang_mixer);

        ButterKnife.bind(this);
        presenter = new FormPengamatanGudangMixerPresenter(this);
        model = (GudangMixerModel) getIntent().getSerializableExtra("model");

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
        mTanggalPengamatan.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        // Tambahkan 1 ke bulan (karena bulan dimulai dari 0)
                        String selectedDate = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay;
                        mTanggalPengamatan.setText(selectedDate); // Set tanggal di EditText
                    },
                    year, month, day);

            datePickerDialog.show(); // Tampilkan dialog
        });

        EditText etTime = findViewById(R.id.mMulaiAdukanMixerKecil);
        EditText etTime2 = findViewById(R.id.mSelesaiAdukanMixerKecil);
        EditText etTime3 = findViewById(R.id.mMulaiAdukanMixerBesar);
        EditText etTime4 = findViewById(R.id.mSelesaiAdukanMixerBesar);

        etTime.setOnClickListener(v -> {
            // Mendapatkan waktu saat ini
            int hour = 12; // Default jam
            int minute = 0; // Default menit

            // Membuat dialog TimePicker
            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    FormPengamatanGudangMixer.this,
                    (TimePicker view, int selectedHour, int selectedMinute) -> {
                        // Format waktu yang dipilih
                        String time = String.format(Locale.getDefault(), "%02d:%02d", selectedHour, selectedMinute);
                        etTime.setText(time); // Menampilkan di EditText
                    },
                    hour, minute, true // true untuk format 24 jam
            );
            // Menampilkan dialog
            timePickerDialog.show();
        });

        etTime2.setOnClickListener(v -> {
            // Mendapatkan waktu saat ini
            int hour = 12; // Default jam
            int minute = 0; // Default menit

            // Membuat dialog TimePicker
            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    FormPengamatanGudangMixer.this,
                    (TimePicker view, int selectedHour, int selectedMinute) -> {
                        // Format waktu yang dipilih
                        String time = String.format(Locale.getDefault(), "%02d:%02d", selectedHour, selectedMinute);
                        etTime2.setText(time); // Menampilkan di EditText
                    },
                    hour, minute, true // true untuk format 24 jam
            );

            // Menampilkan dialog
            timePickerDialog.show();
        });

        etTime3.setOnClickListener(v -> {
            // Mendapatkan waktu saat ini
            int hour = 12; // Default jam
            int minute = 0; // Default menit

            // Membuat dialog TimePicker
            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    FormPengamatanGudangMixer.this,
                    (TimePicker view, int selectedHour, int selectedMinute) -> {
                        // Format waktu yang dipilih
                        String time = String.format(Locale.getDefault(), "%02d:%02d", selectedHour, selectedMinute);
                        etTime3.setText(time); // Menampilkan di EditText
                    },
                    hour, minute, true // true untuk format 24 jam
            );

            // Menampilkan dialog
            timePickerDialog.show();
        });

        etTime4.setOnClickListener(v -> {
            // Mendapatkan waktu saat ini
            int hour = 12; // Default jam
            int minute = 0; // Default menit

            // Membuat dialog TimePicker
            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    FormPengamatanGudangMixer.this,
                    (TimePicker view, int selectedHour, int selectedMinute) -> {
                        // Format waktu yang dipilih
                        String time = String.format(Locale.getDefault(), "%02d:%02d", selectedHour, selectedMinute);
                        etTime4.setText(time); // Menampilkan di EditText
                    },
                    hour, minute, true // true untuk format 24 jam
            );

            // Menampilkan dialog
            timePickerDialog.show();
        });


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mDiv2); mDiv.setAdapter(adapter);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mShift2); mShift.setAdapter(adapter2);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mVolumeAirTigaPerempat2); mVolumeAirTigaPerempat.setAdapter(adapter4);
        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mJenisAplikasi2); mJenisAplikasi.setAdapter(adapter5);
        ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mTangkiMixer2); mTangkiMixer.setAdapter(adapter6); mTangkiMixer.setDropDownWidth(800); // contoh fix dp
        ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mCuciBilas2); mCuciBilas.setAdapter(adapter7);
        ArrayAdapter<String> adapter8 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mKodeUnitBsc2); mKodeUnitBsc.setAdapter(adapter8);
        ArrayAdapter<String> adapter9 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mKodeUnitTangkiSuplay2); mKodeUnitTangkiSuplay.setAdapter(adapter9);
        ArrayAdapter<String> adapter10 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mReworking2); mReworking.setAdapter(adapter10);

        AutoCompleteTextView[] jenisBahanViews = {
                mJenisBahan, mJenisBahan2, mJenisBahan3, mJenisBahan4, mJenisBahan5, mJenisBahan6, mJenisBahan7, mJenisBahan8, mJenisBahan9, mJenisBahan10
        };

        for (int i = 0; i < jenisBahanViews.length; i++) {
            ArrayAdapter<String> adapter11 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, mJenisBahan_1);
            jenisBahanViews[i].setAdapter(adapter11);
        }

        EditText lokasiUppercase = findViewById(R.id.mLokasi);
        lokasiUppercase.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        EditText lokasicorUppercase = findViewById(R.id.mLokasiCor);
        lokasicorUppercase.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        // Menonaktifkan input teks, tetapi dropdown masih muncul
        mDiv.setKeyListener(null); mKodeUnitBsc.setKeyListener(null); mShift.setKeyListener(null); mVolumeAirTigaPerempat.setKeyListener(null); mJenisAplikasi.setKeyListener(null); mTangkiMixer.setKeyListener(null); mReworking.setKeyListener(null); mJenisBahan.setKeyListener(null); mJenisBahan2.setKeyListener(null); mJenisBahan3.setKeyListener(null); mJenisBahan4.setKeyListener(null); mJenisBahan5.setKeyListener(null); mJenisBahan6.setKeyListener(null); mJenisBahan7.setKeyListener(null); mJenisBahan8.setKeyListener(null); mJenisBahan9.setKeyListener(null); mJenisBahan10.setKeyListener(null); mKodeUnitTangkiSuplay.setKeyListener(null); mCuciBilas.setKeyListener(null);

        // Memastikan dropdown muncul meskipun tidak ada teks yang dimasukkan
        mDiv.setThreshold(1); mKodeUnitBsc.setThreshold(1); mShift.setThreshold(1); mVolumeAirTigaPerempat.setThreshold(1); mJenisAplikasi.setThreshold(1); mTangkiMixer.setThreshold(1); mReworking.setThreshold(1); mJenisBahan.setThreshold(1); mJenisBahan2.setThreshold(1); mJenisBahan3.setThreshold(1); mJenisBahan4.setThreshold(1); mJenisBahan5.setThreshold(1); mJenisBahan6.setThreshold(1); mJenisBahan7.setThreshold(1); mJenisBahan8.setThreshold(1); mJenisBahan9.setThreshold(1); mJenisBahan10.setThreshold(1); mKodeUnitTangkiSuplay.setThreshold(1); mCuciBilas.setThreshold(1);

        model = (GudangMixerModel) getIntent().getSerializableExtra("model");
        Log.d("bajakmodel", new Gson().toJson(model));

        presenter = new FormPengamatanGudangMixerPresenter(this);
        mSubmit.setOnClickListener(this);
        mAddSample.setOnClickListener(this);
    }
    private void saveTemporaryData() {
        GudangMixerModel draft = new GudangMixerModel();
        draft.setNO_SPK(mSPK.getText().toString());
        draft.setNO_LINE(mLine.getText().toString());
        draft.setLOKASI(mLokasi.getText().toString());
        draft.setTanggalPengamatan(mTanggalPengamatan.getText().toString());
        draft.setMandorBibit(mMandorBibit.getText().toString());
        draft.setDiv(mDiv.getText().toString());
        draft.setKodeUnitBsc(mKodeUnitBsc.getText().toString());
        draft.setShift(mShift.getText().toString());
        draft.setLokasi_adukan(mLokasiCor.getText().toString());
        draft.setVolumeAirTigaPerempat(mVolumeAirTigaPerempat.getText().toString());

//        draft.setRencanaVolumeAir(
//                mRencanaVolumeAir.getText().toString().trim().isEmpty()
//                        ? 0f
//                        : Float.parseFloat(mRencanaVolumeAir.getText().toString().trim())
//        );
//
//        draft.setRealVolumeAir(
//                mRealVolumeAir.getText().toString().trim().isEmpty()
//                        ? 0f
//                        : Float.parseFloat(mRealVolumeAir.getText().toString().trim())
//        );

        draft.setRencanaVolumeAir(mRencanaVolumeAir.getText().toString());
        draft.setRealVolumeAir(mRealVolumeAir.getText().toString());
//        draft.setRencanaVolumeAir(Float.parseFloat(mRencanaVolumeAir.getText().toString()));
//        draft.setRealVolumeAir(Float.parseFloat(mRealVolumeAir.getText().toString()));
        draft.setJenisAplikasi(mJenisAplikasi.getText().toString());
        draft.setTangkiMixer(mTangkiMixer.getText().toString());
        draft.setReworking(mReworking.getText().toString());
        draft.setDATA(dataPlot); // list plot berisi sample
        TemporaryFormStorage.saveDraft(this, DRAFT_KEY, draft);
        Log.d("DraftSave", "Draft GudangMixer saved: " + new Gson().toJson(draft));
    }

    private void loadTemporaryData() {
        GudangMixerModel draft = TemporaryFormStorage.loadDraft(this, DRAFT_KEY, GudangMixerModel.class);
        if (draft != null) {
            Log.d("DraftLoad", "Draft GudangMixer loaded: " + new Gson().toJson(draft));

            mSPK.setText(draft.getNO_SPK());
            mLine.setText(draft.getNO_LINE());
            mLokasi.setText(draft.getLOKASI());
            mTanggalPengamatan.setText(draft.getTanggalPengamatan());
            mMandorBibit.setText(draft.getMandorBibit());
            mDiv.setText(draft.getDiv());
            mKodeUnitBsc.setText(draft.getKodeUnitBsc());
            mShift.setText(draft.getShift());
            mLokasiCor.setText(draft.getLokasi_adukan());
            mVolumeAirTigaPerempat.setText(draft.getVolumeAirTigaPerempat());
            mRencanaVolumeAir.setText(String.valueOf(draft.getRencanaVolumeAir()));
            mRealVolumeAir.setText(String.valueOf(draft.getRealVolumeAir()));
            mJenisAplikasi.setText(draft.getJenisAplikasi());
            mTangkiMixer.setText(draft.getTangkiMixer());
            mReworking.setText(draft.getReworking());

            // restore dataPlot dan sample
            if (draft.getDATA() != null) {
                dataPlot.clear();
                dataPlot.addAll(draft.getDATA());
                for (PlotModel plot : draft.getDATA()) {
                    if (plot.getSAMPLE() != null) {
                        for (SampleModel sample : plot.getSAMPLE()) {
                            restoreSampleView(sample);
                        }
                    }
                }
            }
            mDiv.post(() -> { mDiv.setKeyListener(null); mDiv.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, mDiv2)); });
            mKodeUnitBsc.post(() -> { mKodeUnitBsc.setKeyListener(null); mKodeUnitBsc.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, mKodeUnitBsc2)); });
            mShift.post(() -> { mShift.setKeyListener(null); mShift.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, mShift2)); });
            mVolumeAirTigaPerempat.post(() -> { mVolumeAirTigaPerempat.setKeyListener(null); mVolumeAirTigaPerempat.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, mVolumeAirTigaPerempat2)); });
            mJenisAplikasi.post(() -> { mJenisAplikasi.setKeyListener(null); mJenisAplikasi.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, mJenisAplikasi2)); });
            mTangkiMixer.post(() -> { mTangkiMixer.setKeyListener(null); mTangkiMixer.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, mTangkiMixer2)); });
            mJenisBahan.post(() -> { mJenisBahan.setKeyListener(null); mJenisBahan.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, mJenisBahan_1)); });
            mJenisBahan2.post(() -> { mJenisBahan2.setKeyListener(null); mJenisBahan2.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, mJenisBahan_1)); });
            mJenisBahan3.post(() -> { mJenisBahan3.setKeyListener(null); mJenisBahan3.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, mJenisBahan_1)); });
            mJenisBahan4.post(() -> { mJenisBahan4.setKeyListener(null); mJenisBahan4.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, mJenisBahan_1)); });
            mJenisBahan5.post(() -> { mJenisBahan5.setKeyListener(null); mJenisBahan5.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, mJenisBahan_1)); });
            mJenisBahan6.post(() -> { mJenisBahan6.setKeyListener(null); mJenisBahan6.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, mJenisBahan_1)); });
            mJenisBahan7.post(() -> { mJenisBahan7.setKeyListener(null); mJenisBahan7.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, mJenisBahan_1)); });
            mJenisBahan8.post(() -> { mJenisBahan8.setKeyListener(null); mJenisBahan8.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, mJenisBahan_1)); });
            mJenisBahan9.post(() -> { mJenisBahan9.setKeyListener(null); mJenisBahan9.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, mJenisBahan_1)); });
            mJenisBahan10.post(() -> { mJenisBahan10.setKeyListener(null); mJenisBahan10.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, mJenisBahan_1)); });
            mKodeUnitTangkiSuplay.post(() -> { mKodeUnitTangkiSuplay.setKeyListener(null); mKodeUnitTangkiSuplay.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, mKodeUnitTangkiSuplay2)); });
            mCuciBilas.post(() -> { mCuciBilas.setKeyListener(null); mCuciBilas.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, mCuciBilas2)); });
        }
    }

    @Override
    protected void onPause() {
        super.onPause(); saveTemporaryData();
    }

    @Override
    protected void onResume() {
        super.onResume(); loadTemporaryData();
    }

    private void restoreSampleView(SampleModel sampleModel) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.data_gudangmixer_sample_field, null);

        final TextView noSample = rowView.findViewById(R.id.mNoSample);
        final TextView plot = rowView.findViewById(R.id.mPlot);
        final TextView jenisbahan = rowView.findViewById(R.id.mJenisBahan);
        final TextView renc = rowView.findViewById(R.id.mRencana);
        final TextView real = rowView.findViewById(R.id.mReal);
        final TextView lokasi_cor = rowView.findViewById(R.id.mLokasiCor);
        final TextView jenisbahan2 = rowView.findViewById(R.id.mJenisBahan2);
        final TextView renc2 = rowView.findViewById(R.id.mRencana2);
        final TextView real2 = rowView.findViewById(R.id.mReal2);
        final TextView jenisbahan3 = rowView.findViewById(R.id.mJenisBahan3);
        final TextView renc3 = rowView.findViewById(R.id.mRencana3);
        final TextView real3 = rowView.findViewById(R.id.mReal3);
        final TextView jenisbahan4 = rowView.findViewById(R.id.mJenisBahan4);
        final TextView renc4 = rowView.findViewById(R.id.mRencana4);
        final TextView real4 = rowView.findViewById(R.id.mReal4);
        final TextView jenisbahan5 = rowView.findViewById(R.id.mJenisBahan5);
        final TextView renc5 = rowView.findViewById(R.id.mRencana5);
        final TextView real5 = rowView.findViewById(R.id.mReal5);
        final TextView jenisbahan6 = rowView.findViewById(R.id.mJenisBahan6);
        final TextView renc6 = rowView.findViewById(R.id.mRencana6);
        final TextView real6 = rowView.findViewById(R.id.mReal6);
        final TextView jenisbahan7 = rowView.findViewById(R.id.mJenisBahan7);
        final TextView renc7 = rowView.findViewById(R.id.mRencana7);
        final TextView real7 = rowView.findViewById(R.id.mReal7);
        final TextView jenisbahan8 = rowView.findViewById(R.id.mJenisBahan8);
        final TextView renc8 = rowView.findViewById(R.id.mRencana8);
        final TextView real8 = rowView.findViewById(R.id.mReal8);
        final TextView jenisbahan9 = rowView.findViewById(R.id.mJenisBahan9);
        final TextView renc9 = rowView.findViewById(R.id.mRencana9);
        final TextView real9 = rowView.findViewById(R.id.mReal9);
        final TextView jenisbahan10 = rowView.findViewById(R.id.mJenisBahan10);
        final TextView renc10 = rowView.findViewById(R.id.mRencana10);
        final TextView real10 = rowView.findViewById(R.id.mReal10);
        final TextView kecilmulai = rowView.findViewById(R.id.mMulaiAdukanMixerKecil);
        final TextView kecilselesai = rowView.findViewById(R.id.mSelesaiAdukanMixerKecil);
        final TextView besarmulai = rowView.findViewById(R.id.mMulaiAdukanMixerBesar);
        final TextView besarselesai = rowView.findViewById(R.id.mSelesaiAdukanMixerBesar);
        final TextView kodesuplay = rowView.findViewById(R.id.mKodeUnitTangkiSuplay);
        final TextView ketpengisian = rowView.findViewById(R.id.mKeteranganPengisian);

        // Set semua field sesuai sampleModel
        noSample.setText(String.valueOf(sampleModel.getNo_sample()));
        plot.setText(String.valueOf(sampleModel.getPLOT()));
        jenisbahan.setText(sampleModel.getJenisBahan());
        renc.setText(String.valueOf(sampleModel.getRencana()));
        real.setText(String.valueOf(sampleModel.getReal()));
        jenisbahan2.setText(sampleModel.getJenis_bahan_2());
        renc2.setText(String.valueOf(sampleModel.getRencana_2()));
        real2.setText(String.valueOf(sampleModel.getReal_2()));
        jenisbahan3.setText(sampleModel.getJenis_bahan_3());
        renc3.setText(String.valueOf(sampleModel.getRencana_3()));
        real3.setText(String.valueOf(sampleModel.getReal_3()));
        jenisbahan4.setText(sampleModel.getJenis_bahan_4());
        renc4.setText(String.valueOf(sampleModel.getRencana_4()));
        real4.setText(String.valueOf(sampleModel.getReal_4()));
        jenisbahan5.setText(sampleModel.getJenis_bahan_5());
        renc5.setText(String.valueOf(sampleModel.getRencana_5()));
        real5.setText(String.valueOf(sampleModel.getReal_5()));
        jenisbahan6.setText(sampleModel.getJenis_bahan_6());
        renc6.setText(String.valueOf(sampleModel.getRencana_6()));
        real6.setText(String.valueOf(sampleModel.getReal_6()));
        jenisbahan7.setText(sampleModel.getJenis_bahan_7());
        renc7.setText(String.valueOf(sampleModel.getRencana_7()));
        real7.setText(String.valueOf(sampleModel.getReal_7()));
        jenisbahan8.setText(sampleModel.getJenis_bahan_8());
        renc8.setText(String.valueOf(sampleModel.getRencana_8()));
        real8.setText(String.valueOf(sampleModel.getReal_8()));
        jenisbahan9.setText(sampleModel.getJenis_bahan_9());
        renc9.setText(String.valueOf(sampleModel.getRencana_9()));
        real9.setText(String.valueOf(sampleModel.getReal_9()));
        jenisbahan10.setText(sampleModel.getJenis_bahan_10());
        renc10.setText(String.valueOf(sampleModel.getRencana_10()));
        real10.setText(String.valueOf(sampleModel.getReal_10()));
        lokasi_cor.setText(sampleModel.getLokasi_adukan());
        kecilmulai.setText(sampleModel.getMulaiAdukanMixerKecil());
        kecilselesai.setText(sampleModel.getSelesaiAdukanMixerKecil());
        besarmulai.setText(sampleModel.getMulaiAdukanMixerBesar());
        besarselesai.setText(sampleModel.getSelesaiAdukanMixerBesar());
        kodesuplay.setText(sampleModel.getKodeUnitTangkiSuplay());
        ketpengisian.setText(sampleModel.getKeteranganPengisian());

        containerPlotData.addView(rowView, 0);
    }


    void addPlotForm() {
//        Toast.makeText(this, "cek", Toast.LENGTH_SHORT).show();

        //Tidak boleh kosong
        if (mSPK.getText().toString().equals("")) {
            Toast.makeText(this, "No SPK tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mLine.getText().toString().equals("")) {
            Toast.makeText(this, "No Line tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mTanggalPengamatan.getText().toString().equals("")) {
            Toast.makeText(this, "Tanggal Pengamatan tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mMandorBibit.getText().toString().equals("")) {
            Toast.makeText(this, "Mandor Mixer tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mDiv.getText().toString().equals("")) {
            Toast.makeText(this, "FM/Div tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mKodeUnitBsc.getText().toString().equals("")) {
            Toast.makeText(this, "Kode BSC tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mShift.getText().toString().equals("")) {
            Toast.makeText(this, "Shift tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mLokasi.getText().toString().equals("")) {
            Toast.makeText(this, "Semua Lokasi tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mVolumeAirTigaPerempat.getText().toString().equals("")) {
            Toast.makeText(this, "Vol Air 3/4 tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mRencanaVolumeAir.getText().toString().equals("")) {
            Toast.makeText(this, "Rencana Vol Air tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mRealVolumeAir.getText().toString().equals("")) {
            Toast.makeText(this, "Real Vol Air tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mJenisAplikasi.getText().toString().equals("")) {
            Toast.makeText(this, "Jenis Aplikasi tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mTangkiMixer.getText().toString().equals("")) {
            Toast.makeText(this, "No Tangki Mixer tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mReworking.getText().toString().equals("")) {
            Toast.makeText(this, "Status Reworking tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mPlot.getText().toString().equals("")) {
            Toast.makeText(this, "Urutan Adukan tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mLokasiCor.getText().toString().equals("")) {
            Toast.makeText(this, "Lokasi per Cor tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mJenisBahan.getText().toString().equals("")) {
            Toast.makeText(this, "Jenis Bahan ke 1 tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mRencana.getText().toString().equals("")) {
            Toast.makeText(this, "Rencana  ke 1 tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mReal.getText().toString().equals("")) {
            Toast.makeText(this, "Real ke 1 tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mMulaiAdukanMixerKecil.getText().toString().equals("")) {
            Toast.makeText(this, "Waktu Mulai Mixer Kecil tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mSelesaiAdukanMixerKecil.getText().toString().equals("")) {
            Toast.makeText(this, "Waktu Selesai Mixer Kecil tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mMulaiAdukanMixerBesar.getText().toString().equals("")) {
            Toast.makeText(this, "Waktu Mulai Mixer Besar tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mSelesaiAdukanMixerBesar.getText().toString().equals("")) {
            Toast.makeText(this, "Waktu Selesai Mixer Besar tidak boleh kosong", Toast.LENGTH_SHORT).show();
//        } else if (mCuciBilas.getText().toString().equals("")) {
//            Toast.makeText(this, "Cuci Bilas tidak boleh kosong", Toast.LENGTH_SHORT).show();
//        } else if (mReworking.getText().toString().equals("")) {
//            Toast.makeText(this, "Reworking tidak boleh kosong", Toast.LENGTH_SHORT).show();
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
        rowView = inflater.inflate(R.layout.data_gudangmixer_sample_field, null);
//        final LinearLayout containerSampleData = view.findViewById(R.id.containerSampleData);
        final TextView noSample = rowView.findViewById(R.id.mNoSample);
        final TextView plot = rowView.findViewById(R.id.mPlot);
        final TextView jenisbahan = rowView.findViewById(R.id.mJenisBahan);
        final TextView renc = rowView.findViewById(R.id.mRencana);
        final TextView real = rowView.findViewById(R.id.mReal);
        final TextView lokasi_cor = rowView.findViewById(R.id.mLokasiCor);
        final TextView jenisbahan2 = rowView.findViewById(R.id.mJenisBahan2);
        final TextView renc2 = rowView.findViewById(R.id.mRencana2);
        final TextView real2 = rowView.findViewById(R.id.mReal2);
        final TextView jenisbahan3 = rowView.findViewById(R.id.mJenisBahan3);
        final TextView renc3 = rowView.findViewById(R.id.mRencana3);
        final TextView real3 = rowView.findViewById(R.id.mReal3);
        final TextView jenisbahan4 = rowView.findViewById(R.id.mJenisBahan4);
        final TextView renc4 = rowView.findViewById(R.id.mRencana4);
        final TextView real4 = rowView.findViewById(R.id.mReal4);
        final TextView jenisbahan5 = rowView.findViewById(R.id.mJenisBahan5);
        final TextView renc5 = rowView.findViewById(R.id.mRencana5);
        final TextView real5 = rowView.findViewById(R.id.mReal5);
        final TextView jenisbahan6 = rowView.findViewById(R.id.mJenisBahan6);
        final TextView renc6 = rowView.findViewById(R.id.mRencana6);
        final TextView real6 = rowView.findViewById(R.id.mReal6);
        final TextView jenisbahan7 = rowView.findViewById(R.id.mJenisBahan7);
        final TextView renc7 = rowView.findViewById(R.id.mRencana7);
        final TextView real7 = rowView.findViewById(R.id.mReal7);
        final TextView jenisbahan8 = rowView.findViewById(R.id.mJenisBahan8);
        final TextView renc8 = rowView.findViewById(R.id.mRencana8);
        final TextView real8 = rowView.findViewById(R.id.mReal8);
        final TextView jenisbahan9 = rowView.findViewById(R.id.mJenisBahan9);
        final TextView renc9 = rowView.findViewById(R.id.mRencana9);
        final TextView real9 = rowView.findViewById(R.id.mReal9);
        final TextView jenisbahan10 = rowView.findViewById(R.id.mJenisBahan10);
        final TextView renc10 = rowView.findViewById(R.id.mRencana10);
        final TextView real10 = rowView.findViewById(R.id.mReal10);
        final TextView kecilmulai = rowView.findViewById(R.id.mMulaiAdukanMixerKecil);
        final TextView kecilselesai = rowView.findViewById(R.id.mSelesaiAdukanMixerKecil);
        final TextView besarmulai = rowView.findViewById(R.id.mMulaiAdukanMixerBesar);
        final TextView besarselesai = rowView.findViewById(R.id.mSelesaiAdukanMixerBesar);
        final TextView kodesuplay = rowView.findViewById(R.id.mKodeUnitTangkiSuplay);
        final TextView ketpengisian = rowView.findViewById(R.id.mKeteranganPengisian);

        jenisbahan.setText(mJenisBahan.getText().toString());
        renc.setText(mRencana.getText().toString());
        real.setText(mReal.getText().toString());
        jenisbahan2.setText(mJenisBahan2.getText().toString());
        renc2.setText(mRencana2.getText().toString());
        real2.setText(mReal2.getText().toString());
        jenisbahan3.setText(mJenisBahan3.getText().toString());
        renc3.setText(mRencana3.getText().toString());
        real3.setText(mReal3.getText().toString());
        jenisbahan4.setText(mJenisBahan4.getText().toString());
        renc4.setText(mRencana4.getText().toString());
        real4.setText(mReal4.getText().toString());
        jenisbahan5.setText(mJenisBahan5.getText().toString());
        renc5.setText(mRencana5.getText().toString());
        real5.setText(mReal5.getText().toString());
        jenisbahan6.setText(mJenisBahan6.getText().toString());
        renc6.setText(mRencana6.getText().toString());
        real6.setText(mReal6.getText().toString());
        jenisbahan7.setText(mJenisBahan7.getText().toString());
        renc7.setText(mRencana7.getText().toString());
        real7.setText(mReal7.getText().toString());
        jenisbahan8.setText(mJenisBahan8.getText().toString());
        renc8.setText(mRencana8.getText().toString());
        real8.setText(mReal8.getText().toString());
        jenisbahan9.setText(mJenisBahan9.getText().toString());
        renc9.setText(mRencana9.getText().toString());
        real9.setText(mReal9.getText().toString());
        jenisbahan10.setText(mJenisBahan10.getText().toString());
        renc10.setText(mRencana10.getText().toString());
        real10.setText(mReal10.getText().toString());
        lokasi_cor.setText(mLokasiCor.getText().toString());
        kecilmulai.setText(mMulaiAdukanMixerKecil.getText().toString());
        kecilselesai.setText(mSelesaiAdukanMixerKecil.getText().toString());
        besarmulai.setText(mMulaiAdukanMixerBesar.getText().toString());
        besarselesai.setText(mSelesaiAdukanMixerBesar.getText().toString());
        kodesuplay.setText(mKodeUnitTangkiSuplay.getText().toString());
        ketpengisian.setText(mKeteranganPengisian.getText().toString());
        plot.setText(mPlot.getText().toString());

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

        noSample.setText(String.valueOf(nextSampleNumber));
        sampleModel.setNo_sample(noSample.getText().toString());
//        sampleModel.setNo_sample(noSample.getText().toString());
//        sampleModel = new SampleModel();
        String selectedDateString = mTanggalPengamatan.getText().toString();
        sampleModel.setTanggalPengamatan(selectedDateString);
        sampleModel.setNo_spk(model.getNO_SPK());
        sampleModel.setKodeUnitBsc(mKodeUnitBsc.getText().toString());
        sampleModel.setMandorBibit(mMandorBibit.getText().toString());
        sampleModel.setDiv(mDiv.getText().toString());
        sampleModel.setShift(mShift.getText().toString());
        sampleModel.setPLOT(Integer.parseInt(mPlot.getText().toString()));
        sampleModel.setReworking(mReworking.getText().toString());
        sampleModel.setJenisBahan(mJenisBahan.getText().toString());
        sampleModel.setRencana(Float.parseFloat(mRencana.getText().toString()));
        sampleModel.setReal(Float.parseFloat(mReal.getText().toString()));
        sampleModel.setNo_spk2(mSPK.getText().toString());
        sampleModel.setNo_line(mLine.getText().toString());
        sampleModel.setJenis_bahan_2(mJenisBahan2.getText().toString().trim().isEmpty() ? "-" : mJenisBahan2.getText().toString().trim()); sampleModel.setRencana_2(mRencana2.getText().toString().trim().isEmpty() ? 0 : Float.parseFloat(mRencana2.getText().toString())); sampleModel.setReal_2(mReal2.getText().toString().trim().isEmpty() ? 0 : Float.parseFloat(mReal2.getText().toString()));
        sampleModel.setJenis_bahan_3(mJenisBahan3.getText().toString().trim().isEmpty() ? "-" : mJenisBahan3.getText().toString().trim()); sampleModel.setRencana_3(mRencana3.getText().toString().trim().isEmpty() ? 0 : Float.parseFloat(mRencana3.getText().toString())); sampleModel.setReal_3(mReal3.getText().toString().trim().isEmpty() ? 0 : Float.parseFloat(mReal3.getText().toString()));
        sampleModel.setJenis_bahan_4(mJenisBahan4.getText().toString().trim().isEmpty() ? "-" : mJenisBahan4.getText().toString().trim()); sampleModel.setRencana_4(mRencana4.getText().toString().trim().isEmpty() ? 0 : Float.parseFloat(mRencana4.getText().toString())); sampleModel.setReal_4(mReal4.getText().toString().trim().isEmpty() ? 0 : Float.parseFloat(mReal4.getText().toString()));
        sampleModel.setJenis_bahan_5(mJenisBahan5.getText().toString().trim().isEmpty() ? "-" : mJenisBahan5.getText().toString().trim()); sampleModel.setRencana_5(mRencana5.getText().toString().trim().isEmpty() ? 0 : Float.parseFloat(mRencana5.getText().toString())); sampleModel.setReal_5(mReal5.getText().toString().trim().isEmpty() ? 0 : Float.parseFloat(mReal5.getText().toString()));
        sampleModel.setJenis_bahan_6(mJenisBahan6.getText().toString().trim().isEmpty() ? "-" : mJenisBahan6.getText().toString().trim()); sampleModel.setRencana_6(mRencana6.getText().toString().trim().isEmpty() ? 0 : Float.parseFloat(mRencana6.getText().toString())); sampleModel.setReal_6(mReal6.getText().toString().trim().isEmpty() ? 0 : Float.parseFloat(mReal6.getText().toString()));
        sampleModel.setJenis_bahan_7(mJenisBahan7.getText().toString().trim().isEmpty() ? "-" : mJenisBahan7.getText().toString().trim()); sampleModel.setRencana_7(mRencana7.getText().toString().trim().isEmpty() ? 0 : Float.parseFloat(mRencana7.getText().toString())); sampleModel.setReal_7(mReal7.getText().toString().trim().isEmpty() ? 0 : Float.parseFloat(mReal7.getText().toString()));
        sampleModel.setJenis_bahan_8(mJenisBahan8.getText().toString().trim().isEmpty() ? "-" : mJenisBahan8.getText().toString().trim()); sampleModel.setRencana_8(mRencana8.getText().toString().trim().isEmpty() ? 0 : Float.parseFloat(mRencana8.getText().toString())); sampleModel.setReal_8(mReal8.getText().toString().trim().isEmpty() ? 0 : Float.parseFloat(mReal8.getText().toString()));
        sampleModel.setJenis_bahan_9(mJenisBahan9.getText().toString().trim().isEmpty() ? "-" : mJenisBahan9.getText().toString().trim()); sampleModel.setRencana_9(mRencana9.getText().toString().trim().isEmpty() ? 0 : Float.parseFloat(mRencana9.getText().toString())); sampleModel.setReal_9(mReal9.getText().toString().trim().isEmpty() ? 0 : Float.parseFloat(mReal9.getText().toString()));
        sampleModel.setJenis_bahan_10(mJenisBahan10.getText().toString().trim().isEmpty() ? "-" : mJenisBahan10.getText().toString().trim()); sampleModel.setRencana_10(mRencana10.getText().toString().trim().isEmpty() ? 0 : Float.parseFloat(mRencana10.getText().toString())); sampleModel.setReal_10(mReal10.getText().toString().trim().isEmpty() ? 0 : Float.parseFloat(mReal10.getText().toString()));
        sampleModel.setLokasi(mLokasi.getText().toString());
        sampleModel.setLokasi_adukan(mLokasiCor.getText().toString());
        sampleModel.setVolumeAirTigaPerempat(mVolumeAirTigaPerempat.getText().toString());
        sampleModel.setRencanaVolumeAir(Float.parseFloat(mRencanaVolumeAir.getText().toString()));
        sampleModel.setRealVolumeAir(Float.parseFloat(mRealVolumeAir.getText().toString()));
        sampleModel.setJenisAplikasi(mJenisAplikasi.getText().toString());
        sampleModel.setTangkiMixer(mTangkiMixer.getText().toString());
        sampleModel.setCuciBilas(mCuciBilas.getText().toString().trim().isEmpty() ? "-" : mCuciBilas.getText().toString().trim());
        sampleModel.setKeterangan(mKet.getText().toString().trim().isEmpty() ? "-" : mKet.getText().toString().trim());
        sampleModel.setKodeUnitTangkiSuplay(mKodeUnitTangkiSuplay.getText().toString().trim().isEmpty() ? "-" : mKodeUnitTangkiSuplay.getText().toString().trim());
        sampleModel.setKeteranganPengisian(mKeteranganPengisian.getText().toString().trim().isEmpty() ? "-" : mKeteranganPengisian.getText().toString().trim());

        // Selesai Adukan Mixer Besar
        String jam1 = mSelesaiAdukanMixerBesar.getText().toString().trim();
        if (jam1.isEmpty()) mSelesaiAdukanMixerBesar.setText("00:00");
        jam1 = mSelesaiAdukanMixerBesar.getText().toString().trim();
        String jamFinal1 = jam1.length() == 5 ? jam1 + ":00" : jam1;
        sampleModel.setSelesaiAdukanMixerBesar(jamFinal1);

        // Mulai Adukan Mixer Besar
        String jam2 = mMulaiAdukanMixerBesar.getText().toString().trim();
        if (jam2.isEmpty()) mMulaiAdukanMixerBesar.setText("00:00");
        jam2 = mMulaiAdukanMixerBesar.getText().toString().trim();
        String jamFinal2 = jam2.length() == 5 ? jam2 + ":00" : jam2;
        sampleModel.setMulaiAdukanMixerBesar(jamFinal2);

        // Selesai Adukan Mixer Kecil
        String jam3 = mSelesaiAdukanMixerKecil.getText().toString().trim();
        if (jam3.isEmpty()) mSelesaiAdukanMixerKecil.setText("00:00");
        jam3 = mSelesaiAdukanMixerKecil.getText().toString().trim();
        String jamFinal3 = jam3.length() == 5 ? jam3 + ":00" : jam3;
        sampleModel.setSelesaiAdukanMixerKecil(jamFinal3);

        // Mulai Adukan Mixer Kecil
        String jam4 = mMulaiAdukanMixerKecil.getText().toString().trim();
        if (jam4.isEmpty()) mMulaiAdukanMixerKecil.setText("00:00");
        jam4 = mMulaiAdukanMixerKecil.getText().toString().trim();
        String jamFinal4 = jam4.length() == 5 ? jam4 + ":00" : jam4;
        sampleModel.setMulaiAdukanMixerKecil(jamFinal4);


//        dataSample.add(sampleModel);
        dataSample.add(sampleModel);
//        index +=1 ;
        containerPlotData.addView(rowView, 0);

        //Hapus Saat Apply
        Toast.makeText(this, "Data Sudah Ditambahkan", Toast.LENGTH_SHORT).show();
        mMulaiAdukanMixerKecil.getText().clear();
        mSelesaiAdukanMixerKecil.getText().clear();
        mMulaiAdukanMixerBesar.getText().clear();
        mSelesaiAdukanMixerBesar.getText().clear();
        mKeteranganPengisian.getText().clear();
        mJenisBahan.getText().clear();
        mRencana.getText().clear();
        mReal.getText().clear();
        mJenisBahan2.getText().clear();
        mRencana2.getText().clear();
        mReal2.getText().clear();
        mJenisBahan3.getText().clear();
        mRencana3.getText().clear();
        mReal3.getText().clear();
        mJenisBahan4.getText().clear();
        mRencana4.getText().clear();
        mReal4.getText().clear();
        mJenisBahan5.getText().clear();
        mRencana5.getText().clear();
        mReal5.getText().clear();
        mJenisBahan6.getText().clear();
        mRencana6.getText().clear();
        mReal6.getText().clear();
        mJenisBahan7.getText().clear();
        mRencana7.getText().clear();
        mReal7.getText().clear();
        mJenisBahan8.getText().clear();
        mRencana8.getText().clear();
        mReal8.getText().clear();
        mJenisBahan9.getText().clear();
        mRencana9.getText().clear();
        mReal9.getText().clear();
        mJenisBahan10.getText().clear();
        mRencana10.getText().clear();
        mReal10.getText().clear();
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
        if (dataPlot.size() > 0) {
//            plotModel.setSAMPLE(dataSample);
            model.setLOKASI(mLokasi.getText().toString());
            model.setDATA(dataPlot);
//            sampleModel.setCuciBilas(mCuciBilas.getText().toString());
            Log.d("dataBody", new Gson().toJson(model));
            presenter.createPengamatan(model);
        } else
            SweetDialogs.commonError(this, "Harap apply data terlebih dahulu", false);
    }

    // di FormPengamatanGudangMixer.java
    private void clearForm() {
        // Kosongkan semua input header
        mSPK.setText("");
        mLine.setText("");
        mLokasi.setText("");
        mTanggalPengamatan.setText("");
        mMandorBibit.setText("");
        mDiv.setText("");
        mKodeUnitBsc.setText("");
        mShift.setText("");
        mVolumeAirTigaPerempat.setText("");
        mRencanaVolumeAir.setText("");
        mRealVolumeAir.setText("");
        mJenisAplikasi.setText("");
        mTangkiMixer.setText("");
        mReworking.setText("");
        mLokasiCor.setText("");

        // misalnya dropdown commodity mixer

        // Kosongkan data di memory
        dataPlot.clear();
        dataSample.clear();

        // Kosongkan container sample di UI
        containerPlotData.removeAllViews();

        // Reset model juga
        model = new GudangMixerModel();

        // Hapus draft tersimpan biar nggak balik lagi
        TemporaryFormStorage.clearDraft(this, DRAFT_KEY);

        Log.d("FormReset", "Form Gudang Mixer cleared");
    }


    @Override
    public void onCreateSuccess(String rm) {

        // Tambahkan format timestamp
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        String currentTime = sdf.format(new Date()); // Mendapatkan waktu saat ini

        // Tambahkan waktu ke dalam pesan
        String message = "Gudang Mixer"
                + ", Lokasi: " + mLokasi.getText().toString()
                + "\nTanggal: " + generateTglSekarang()
                + "\nWaktu: " + currentTime
                + "\n" + getString(R.string.versi_apps);

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
    public void onNetworkError(String cause, String data) {

        Log.e("errornya", cause);
        SQLiteHelper dbHelper = new SQLiteHelper(this);
        dbHelper.saveChopperData(data, model.getNO_SPK());  // Assuming 'data' is a JSON string

        Log.d("Saved data", "Data saved to SQLite: " + data);
        Log.d("Saved data", "Data saved to SQLite: " + model.getNO_SPK());
        // Show a dialog indicating that the data has been saved offline
//        SweetDialogs.commonError(this, App.getApplication().getString(R.string.notif_offline_mode), false);
        SweetDialogs.commonWarningWithIntent(this, "Anda Tidak ada Koneksi Internet", App.getApplication().getString(R.string.notif_offline_mode), string -> startActivity(new Intent(this, c_1_home.class)));

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

    private static final String[] mDiv2 = new String[]{
            "FM01A", "FM01B", "FM02", "FM03A", "FM03B", "FM04", "FM05", "FM06"
    };

    private static final String[] mShift2 = new String[]{
            "Pagi", "Malam"
    };

    private static final String[] mJenisBahan_1 = new String[]{
            "Air Bersih", "Borax", "K2SO4", "KCL", "MAP", "MKP", "FESO4", "MGSO4", "ZNSO4", "Urea", "ZA", "Kaolin", "S14", "LOB", "Urease", "Biopestisida", "Urea Pospat", "NBPT", "LOB 2.0"
    };

    private static final String[] mTangkiMixer2 = new String[]{
            "1", "2", "Air Bersih Dari Penampungan Utama"
    };

    private static final String[] mCuciBilas2 = new String[]{
            "1", "0"
    };

    private static final String[] mVolumeAirTigaPerempat2 = new String[]{
            "1", "0"
    };

    private static final String[] mReworking2 = new String[]{
            "Sebelum Reworking", "Sesudah Reworking"
    };

    private static final String[] mJenisAplikasi2 = new String[]{
            "Booster", "Cuci Bilas", "Foliar Spray", "Forcing", "Insectisida", "Pestisida", "Post Planting", "Pre Planting", "Repening"
    };

    private static final String[] mKodeUnitBsc2 = new String[]{
            "BDF-001", "BDF-002", "BDF-003", "BDF-004", "BDF-005", "BDF-006", "BDF-007", "BDF-008", "BDF-009", "BDF-010",
            "BSC-001", "BSC-002", "BSC-003", "BSC-004", "BSC-005", "BSC-006", "BSC-007", "BSC-008", "BSC-009", "BSC-010",
            "BSC-011", "BSC-012", "BSC-013", "BSC-014", "BSC-015", "BSC-016", "BSC-017", "BSC-018", "BSC-019", "BSC-020"
    };
    private static final String[] mKodeUnitTangkiSuplay2 = new String[]{
            "TSM-001", "TSM-002", "TSM-003", "TSM-004", "TSM-005", "TSM-006", "TSM-007", "TSM-008", "TSM-009", "TSM-010",
            "TSM-011", "TSM-012", "TSM-013", "TSM-014", "TSM-015", "TSM-016", "TSM-017", "TSM-018", "TSM-019", "TSM-020",
            "TSM-021", "TSM-022", "TSM-023", "TSM-024", "TSM-025", "TSM-026", "TSM-027", "TSM-028", "TSM-029", "TSM-030",
            "TSM-031", "TSM-032", "TSM-033", "TSM-034", "TSM-035", "TSM-036", "TSM-037", "TSM-038", "TSM-039", "TSM-040", "TSM-041"
    };


}