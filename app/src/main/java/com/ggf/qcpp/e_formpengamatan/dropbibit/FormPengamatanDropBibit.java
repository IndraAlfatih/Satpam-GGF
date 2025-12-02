package com.ggf.qcpp.e_formpengamatan.dropbibit;

import static com.ggf.qcpp.utils.Utils.generateSpk;
import static com.ggf.qcpp.utils.Utils.generateTglSekarang;
import static com.ggf.qcpp.utils.Utils.goToListPengamatan;
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
import com.ggf.qcpp.e_formpengamatan.bajak.model.BajakModel;
import com.ggf.qcpp.e_formpengamatan.dropbibit.model.DropBibitModel;
import com.ggf.qcpp.e_formpengamatan.petikbibit.FormPengamatanPetikBibitPresenter;
import com.ggf.qcpp.e_formpengamatan.petikbibit.IFormPengamatanPetikBibitView;
import com.ggf.qcpp.e_formpengamatan.petikbibit.model.PetikBibitModel;
import com.ggf.qcpp.e_formpengamatan.dropbibit.model.PlotModel;
import com.ggf.qcpp.e_formpengamatan.dropbibit.model.SampleModel;
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

public class FormPengamatanDropBibit extends AppCompatActivity implements View.OnClickListener, IFormPengamatanDropBibitView {
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
    @BindView(R.id.mPlot)
    EditText mPlot;

    @BindView(R.id.mNoKendaraan)
    EditText mNoKendaraan;

    @BindView(R.id.mReworking)
    AutoCompleteTextView mReworking;

    @BindView(R.id.autoWilayah)
    AutoCompleteTextView autoWilayah;

    @BindView(R.id.mLokasi)
    EditText mLokasi;

    @BindView(R.id.autojenisbibit)
    AutoCompleteTextView autojenisbibit;

    @BindView(R.id.autokelasbibit)
    AutoCompleteTextView autokelasbibit;

    @BindView(R.id.autoNoBibit)
    AutoCompleteTextView autonobibit;

    @BindView(R.id.mNormal)
    EditText mNormal;
    @BindView(R.id.mMandorBibit)
    EditText mMandorBibit;

    @BindView(R.id.mLuasPlot)
    EditText mLuasPlot;

    @BindView(R.id.mAfkir)
    EditText mAfkir;

    @BindView(R.id.mOver)
    EditText mOver;

    @BindView(R.id.mOverPlus)
    EditText mOverPlus;

    @BindView(R.id.mPetik1)
    EditText mPetik1;

    @BindView(R.id.mPetik2)
    EditText mPetik2;

    @BindView(R.id.mKeterangan)
    EditText mKeterangan;

    @BindView(R.id.mPetik3)
    EditText mPetik3;

    @BindView(R.id.mPetik4)
    EditText mPetik4;

    @BindView(R.id.mPetik5)
    EditText mPetik5;

    @BindView(R.id.mPetik6)
    EditText mPetik6;

    @BindView(R.id.mPetik7)
    EditText mPetik7;

    @BindView(R.id.mKet)
    EditText mKet;

    @BindView(R.id.mUpdatePeta)
    EditText mUpdatePeta;

    View rowView;
    View rowViewPlot;
    View viewnya = null;
    String plot = "0";
    DropBibitModel model;

    List<PlotModel> dataPlot = new ArrayList<>();
    List<SampleModel> dataSample = new ArrayList<>();

    SweetAlertDialog sweetAlertDialog;
    SampleModel sampleModel = null;
    PlotModel plotModel = null;
    FormPengamatanDropBibitPresenter presenter;
    String generateSpk = "";
    int index = 1;
    private static final String DRAFT_KEY = "draft_dropbibit";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form_pengamatan_drop_bibit);
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


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, autojenisbibit2);
        autojenisbibit.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, autokelasbibit2);
        autokelasbibit.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, autoNObibit2);
        autonobibit.setAdapter(adapter3);

        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, autoWilayah2);
        autoWilayah.setAdapter(adapter4);

        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mReworking2);
        mReworking.setAdapter(adapter5);

        EditText lokasiUppercase = findViewById(R.id.mLokasi);
        lokasiUppercase.setFilters(new InputFilter[] {new InputFilter.AllCaps()});

        // Menonaktifkan input teks, tetapi dropdown masih muncul
        autojenisbibit.setKeyListener(null);
        autokelasbibit.setKeyListener(null);
        autonobibit.setKeyListener(null);
        mReworking.setKeyListener(null);
        autoWilayah.setKeyListener(null);

        // Memastikan dropdown muncul meskipun tidak ada teks yang dimasukkan
        autoWilayah.setThreshold(1);
        autojenisbibit.setThreshold(1);
        autokelasbibit.setThreshold(1);
        autonobibit.setThreshold(1);
        mReworking.setThreshold(1);

        // Atur threshold sesuai kebutuhan (misalnya 1 untuk memulai pencarian setelah 1 karakter)
        model = (DropBibitModel) getIntent().getSerializableExtra("model");
        Log.d("DropBibitModel", new Gson().toJson(model));
        generateSpk = generateSpk(model.getKATEGORI());
        presenter = new FormPengamatanDropBibitPresenter(this);
        mSubmit.setOnClickListener(this);
        mAddSample.setOnClickListener(this);
    }


//    private void saveTemporaryData() {
//        if (model != null) {
//            model.setLOKASI(mLokasi.getText().toString());
//            model.setWILAYAH(autoWilayah.getText().toString());
//            model.setDATA(dataPlot);
//
//            TemporaryFormStorage.saveDraft(this, DRAFT_KEY, model);
//            Log.d("Draft", "Draft DropBibit saved: " + new Gson().toJson(model));
//        }
//    }

    private void saveTemporaryData() {
        // Simpan sementara
        DropBibitModel existingDraft = TemporaryFormStorage.loadDraft(this, DRAFT_KEY,DropBibitModel.class);
        DropBibitModel draft = new DropBibitModel();
        // Gunakan NO_SPK dari draft sebelumnya jika sudah ada
        if (existingDraft != null && existingDraft.getNO_SPK() != null && !existingDraft.getNO_SPK().isEmpty()) {
            draft.setNO_SPK(existingDraft.getNO_SPK());
        } else {
            // Generate baru hanya jika belum ada
            draft.setNO_SPK(generateSpk);
        }
//        draft.setNO_LINE(mLine.getText().toString());
        draft.setNO_SPK2(mSPK.getText().toString());
        draft.setNO_LINE(mLine.getText().toString());
        draft.setLOKASI(mLokasi.getText().toString());
        draft.setWILAYAH(autoWilayah.getText().toString());
        draft.setMandor_bibit(mMandorBibit.getText().toString());
        draft.setKelas_bibit(autokelasbibit.getText().toString());
        draft.setJenis_bibit(autojenisbibit.getText().toString());
        draft.setReworking(mReworking.getText().toString());
        draft.setUpdate_peta(mUpdatePeta.getText().toString());

        draft.setDATA(dataPlot); // simpan semua plot + sample

        TemporaryFormStorage.saveDraft(this, DRAFT_KEY, draft);
        Log.d("DraftSaved", new Gson().toJson(draft));
    }


    private void loadTemporaryData() {
        // Simpan sementara
//        mSPK.setText(generateSpk);
        DropBibitModel draft = TemporaryFormStorage.loadDraft(this, DRAFT_KEY, DropBibitModel.class);
        if (draft != null) {

            Log.d("DraftLoaded", new Gson().toJson(draft));
//            model.setNO_SPK(draft.getNO_SPK());

            mSPK.setText(draft.getNO_SPK2());
            mLine.setText(draft.getNO_LINE());
            mLokasi.setText(draft.getLOKASI());
            autoWilayah.setText(draft.getWILAYAH());
            mMandorBibit.setText(draft.getMandor_bibit());
            autokelasbibit.setText(draft.getKelas_bibit());
            autojenisbibit.setText(draft.getJenis_bibit());
            mReworking.setText(draft.getReworking());
            mUpdatePeta.setText(draft.getUpdate_peta());
//            model.setNO_SPK(generateSpk(model.getKATEGORI()));
            model.setNO_SPK(draft.getNO_SPK());


            if (draft.getDATA() != null) {
                dataPlot = draft.getDATA();
                for (PlotModel p : dataPlot) {
                    if (p.getSAMPLE() != null) {
                        for (SampleModel s : p.getSAMPLE()) {
                            restoreSampleView(s);
                        }
                    }
                }
            }

            // ✅ Tambahkan ini di akhir: rebind adapter biar dropdown aktif lagi
            autokelasbibit.post(() -> {
                ArrayAdapter<String> adapterautokelasbibit = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, autokelasbibit2);
                autokelasbibit.setAdapter(adapterautokelasbibit);
            });

            autojenisbibit.post(() -> {
                ArrayAdapter<String> adapterautojenisbibit = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, autojenisbibit2);
                autojenisbibit.setAdapter(adapterautojenisbibit);
            });

            mReworking.post(() -> {
                ArrayAdapter<String> adapterReworking = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, mReworking2);
                mReworking.setAdapter(adapterReworking);
            });

            autoWilayah.post(() -> {
                ArrayAdapter<String> adapterWilayah = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, autoWilayah2);
                autoWilayah.setAdapter(adapterWilayah);
            });

            autonobibit.post(() -> {
                ArrayAdapter<String> adapterautonobibit = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, autoNObibit2);
                autonobibit.setAdapter(adapterautonobibit);
            });

        }
    }

    // --- CLEAR FORM ---
    private void clearForm() {
        // Kosongkan semua input
        mSPK.setText("");
        mLine.setText("");
        mLokasi.setText("");
        autoWilayah.setText("");
        mKeterangan.setText("");
        mPlot.setText("");
        mNoKendaraan.setText("");
        mReworking.setText("");
        autojenisbibit.setText("");
        autokelasbibit.setText("");
        autonobibit.setText("");
        mNormal.setText("");
        mMandorBibit.setText("");
        mLuasPlot.setText("");
        mAfkir.setText("");
        mOver.setText("");
        mOverPlus.setText("");
        mPetik1.setText("");
        mPetik2.setText("");
        mPetik3.setText("");
        mPetik4.setText("");
        mPetik5.setText("");
        mPetik6.setText("");
        mPetik7.setText("");
        mKet.setText("");
        mUpdatePeta.setText("");

        // Kosongkan data memory
        dataPlot.clear();
        dataSample.clear();

        // Hapus tampilan sample dari UI
        containerPlotData.removeAllViews();

        // Reset model
        model = new DropBibitModel();

        // Hapus draft tersimpan biar nggak balik lagi
        TemporaryFormStorage.clearDraft(this, "draft_dropbibit");

        Log.d("FormReset", "Form Drop Bibit cleared");
    }

    // --- Restore sample ke UI dari draft ---
    private void restoreSampleView(SampleModel sample) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.data_dropbibit_sample_field, null);

        final TextView plot = rowView.findViewById(R.id.mPlot);
        final TextView normal = rowView.findViewById(R.id.mNormal);
        final TextView afkir = rowView.findViewById(R.id.mAfkir);
        final TextView noBibit = rowView.findViewById(R.id.autoNoBibit);
        final TextView overPlus = rowView.findViewById(R.id.mOverPlus);
        final TextView over = rowView.findViewById(R.id.mOver);
        final TextView petik1 = rowView.findViewById(R.id.mPetik1);
        final TextView petik2 = rowView.findViewById(R.id.mPetik2);
        final TextView petik3 = rowView.findViewById(R.id.mPetik3);
        final TextView petik4 = rowView.findViewById(R.id.mPetik4);
        final TextView petik5 = rowView.findViewById(R.id.mPetik5);
        final TextView petik6 = rowView.findViewById(R.id.mPetik6);
        final TextView petik7 = rowView.findViewById(R.id.mPetik7);
        final TextView noSample = rowView.findViewById(R.id.mNoSample);

        plot.setText(String.valueOf(sample.getPlot()));
        normal.setText(String.valueOf(sample.getBibit_normal()));
        afkir.setText(String.valueOf(sample.getBibit_afkir()));
        noBibit.setText(sample.getNomor_bibit());
        overPlus.setText(String.valueOf(sample.getBibit_over_plus()));
        over.setText(String.valueOf(sample.getBibit_over()));
        petik1.setText(String.valueOf(sample.getBibit_1()));
        petik2.setText(String.valueOf(sample.getBibit_2()));
        petik3.setText(String.valueOf(sample.getBibit_3()));
        petik4.setText(String.valueOf(sample.getBibit_4()));
        petik5.setText(String.valueOf(sample.getBibit_5()));
        petik6.setText(String.valueOf(sample.getBibit_6()));
        petik7.setText(String.valueOf(sample.getBibit_7()));
        noSample.setText(String.valueOf(sample.getNo_sample()));

        containerPlotData.addView(rowView, 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        generateSpk = generateSpk(model.getKATEGORI());
//        generateSpk = generateSpk ;
        loadTemporaryData();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        generateSpk = generateSpk ;
        saveTemporaryData();
    }
    void addPlotForm() {

        //Tidak boleh kosong
        if (mSPK.getText().toString().equals("")) {
            Toast.makeText(this, "No SPK tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mLine.getText().toString().equals("")) {
            Toast.makeText(this, "No Line tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mLokasi.getText().toString().equals("")) {
            Toast.makeText(this, "Lokasi tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mMandorBibit.getText().toString().equals("")) {
            Toast.makeText(this, "Mandor Bibit tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }else if (autokelasbibit.getText().toString().equals("")) {
            Toast.makeText(this, "Kelas Bibit tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (autojenisbibit.getText().toString().equals("")) {
            Toast.makeText(this, "Jenis Bibit tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (autoWilayah.getText().toString().equals("")) {
            Toast.makeText(this, "Wilayah tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mReworking.getText().toString().equals("")) {
            Toast.makeText(this, "Reworking tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mUpdatePeta.getText().toString().equals("")) {
            Toast.makeText(this, "Update Peta tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mLuasPlot.getText().toString().equals("")) {
            Toast.makeText(this, "Luas Plot tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mNoKendaraan.getText().toString().equals("")) {
            Toast.makeText(this, "No Kendaraan tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mPlot.getText().toString().equals("")) {
            Toast.makeText(this, "No Plot tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (autonobibit.getText().toString().equals("")) {
            Toast.makeText(this, "No Bibit tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mNormal.getText().toString().equals("")) {
            Toast.makeText(this, "Bibit Normal tidak boleh kosong", Toast.LENGTH_SHORT).show();
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
        rowView = inflater.inflate(R.layout.data_dropbibit_sample_field, null);


        final TextView plot = rowView.findViewById(R.id.mPlot);
        final TextView normal = rowView.findViewById(R.id.mNormal);
        final TextView afkir = rowView.findViewById(R.id.mAfkir);
        final TextView nobibit = rowView.findViewById(R.id.autoNoBibit);
        final TextView overPlus = rowView.findViewById(R.id.mOverPlus);
        final TextView over = rowView.findViewById(R.id.mOver);
//        final TextView nokendaraan = rowView.findViewById(R.id.mNoKendaraan);
        final TextView petik1 = rowView.findViewById(R.id.mPetik1);
        final TextView petik2 = rowView.findViewById(R.id.mPetik2);
        final TextView petik3 = rowView.findViewById(R.id.mPetik3);
        final TextView petik4 = rowView.findViewById(R.id.mPetik4);
        final TextView petik5 = rowView.findViewById(R.id.mPetik5);
        final TextView petik6 = rowView.findViewById(R.id.mPetik6);
        final TextView petik7 = rowView.findViewById(R.id.mPetik7);
        final TextView noSample = rowView.findViewById(R.id.mNoSample);

        plot.setText(mPlot.getText().toString());
        normal.setText(mNormal.getText().toString());
        afkir.setText(mAfkir.getText().toString());
//        nokendaraan.setText(mNoKendaraan.getText().toString());
        nobibit.setText(autonobibit.getText().toString());
        overPlus.setText(mOverPlus.getText().toString());
        over.setText(mOver.getText().toString());
        petik1.setText(mPetik1.getText().toString());
        petik2.setText(mPetik2.getText().toString());
        petik3.setText(mPetik3.getText().toString());
        petik4.setText(mPetik4.getText().toString());
        petik5.setText(mPetik5.getText().toString());
        petik6.setText(mPetik6.getText().toString());
        petik7.setText(mPetik7.getText().toString());


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

        noSample.setText(String.valueOf(nextSampleNumber));
        DropBibitModel existingDraft = TemporaryFormStorage.loadDraft(this, DRAFT_KEY, DropBibitModel.class);
//        if(draft != null){
//            sampleModel.setNo_spk(draft.getNO_SPK());
//        }else {
//            sampleModel.setNo_spk(generateSpk);
//        }
//        sampleModel.setNo_spk2(mSPK.getText().toString());
        sampleModel.setNo_line(mLine.getText().toString());
        if (existingDraft != null && existingDraft.getNO_SPK() != null && !existingDraft.getNO_SPK().isEmpty()) {
            sampleModel.setNo_spk(existingDraft.getNO_SPK());
//            sampleModel.setNo_spk2(existingDraft.getNO_SPK());
        }else{
            sampleModel.setNo_spk(generateSpk);
//            sampleModel.setNo_spk2(generateSpk);
        }
        sampleModel.setNo_spk2(mSPK.getText().toString());
        sampleModel.setLokasi(mLokasi.getText().toString());
        sampleModel.setJenis_bibit(autojenisbibit.getText().toString());
        sampleModel.setKelas_bibit(autokelasbibit.getText().toString());
        sampleModel.setNo_sample(noSample.getText().toString());
        sampleModel.setPlot(Integer.parseInt(mPlot.getText().toString()));
        sampleModel.setWil(autoWilayah.getText().toString());
        sampleModel.setReworking(mReworking.getText().toString());
        sampleModel.setNomor_bibit(autonobibit.getText().toString());
        sampleModel.setBibit_normal(Float.parseFloat(mNormal.getText().toString()));
        sampleModel.setLuas_plot(Float.parseFloat(mLuasPlot.getText().toString()));
//        sampleModel.setKeseragaman_bibit(Float.parseFloat(mJumlahBibitPertumpuk.getText().toString()));

//        sampleModel.setMandor_bibit((mMandorBibit.getText().toString().contains(""))
//                ? "-" : mMandorBibit.getText().toString());

        sampleModel.setMandor_bibit(
                mMandorBibit.getText().toString().trim().isEmpty() ? "-" : mMandorBibit.getText().toString().trim()
        );
        sampleModel.setNo_kendaraan(mNoKendaraan.getText().toString());

        sampleModel.setBibit_afkir(parseFloatDefault(mAfkir.getText().toString(), 0.0f));
//        sampleModel.setLuas_plot(parseFloatDefault(mLuasPlot.getText().toString(), 0.0f));
        sampleModel.setBibit_over_plus(parseIntDefault(mOverPlus.getText().toString(), 0));
        sampleModel.setBibit_over(parseIntDefault(mOver.getText().toString(), 0));
        sampleModel.setBibit_1(parseIntDefault(mPetik1.getText().toString(), 0));
        sampleModel.setBibit_2(parseIntDefault(mPetik2.getText().toString(), 0));
        sampleModel.setBibit_3(parseIntDefault(mPetik3.getText().toString(), 0));
        sampleModel.setBibit_4(parseIntDefault(mPetik4.getText().toString(), 0));
        sampleModel.setBibit_5(parseIntDefault(mPetik5.getText().toString(), 0));
        sampleModel.setBibit_6(parseIntDefault(mPetik6.getText().toString(), 0));
        sampleModel.setBibit_7(parseIntDefault(mPetik7.getText().toString(), 0));
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
        mNormal.getText().clear();
        mAfkir.getText().clear();
        mOverPlus.getText().clear();
        mNoKendaraan.getText().clear();
        mOver.getText().clear();
        mPetik1.getText().clear();
        mPetik2.getText().clear();
        mPetik3.getText().clear();
        mPetik4.getText().clear();
        mPetik5.getText().clear();
        mPetik6.getText().clear();
        mPetik7.getText().clear();

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
            DropBibitModel existingDraft = TemporaryFormStorage.loadDraft(this, DRAFT_KEY, DropBibitModel.class);
            if (existingDraft != null && existingDraft.getNO_SPK() != null && !existingDraft.getNO_SPK().isEmpty()) {
             model.setNO_SPK(existingDraft.getNO_SPK());
            }else{
                model.setNO_SPK(generateSpk);
            }
            model.setLOKASI(mLokasi.getText().toString());
            model.setWILAYAH(autoWilayah.getText().toString());
            model.setDATA(dataPlot);
            Log.d("datanya", new Gson().toJson(model));
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
        String message = "Drop Bibit"
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
        dbHelper.saveChopperData(data, generateSpk);  // Assuming 'data' is a JSON string


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

    private static final String[] autojenisbibit2 = new String[]{
            "Sucker", "Crown", "Crown Storing", "Nursery Sucker Plus"
    };

    private static final String[] autokelasbibit2 = new String[]{
            "Super Kecil", "Kecil", "Sedang", "Besar", " Extra Besar"
    };

    private static final String[] autoNObibit2 = new String[]{
            "0+", "0", "1", "2", "3", "4", "5", "6", "7"
    };
    private static final String[] autoWilayah2 = new String[]{
            "AW01", "AW02", "AW03", "AW04", "AW05", "AW06", "AW07", "AW08", "AW09", "AW10", "AW11", "AW12", "AW13", "AW14", "AW15", "AW16", "AW17", "AW18", "AW19", "AW20", "AW21", "AW22", "AW23"
    };

    private static final String[] mReworking2 = new String[]{
            "Sebelum Reworking", "Sesudah Reworking"
    };
}