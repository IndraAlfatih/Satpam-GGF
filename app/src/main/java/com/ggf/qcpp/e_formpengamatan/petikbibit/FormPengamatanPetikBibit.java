package com.ggf.qcpp.e_formpengamatan.petikbibit;

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
import com.ggf.qcpp.e_formpengamatan.petikbibit.model.PetikBibitModel;
import com.ggf.qcpp.e_formpengamatan.petikbibit.model.PlotModel;
import com.ggf.qcpp.e_formpengamatan.petikbibit.model.SampleModel;
import com.ggf.qcpp.network.SQLiteHelper;
import com.ggf.qcpp.ui.SweetDialogs;
import com.ggf.qcpp.utils.TemporaryFormStorage;
import com.ggf.qcpp.utils.Utils;
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

public class FormPengamatanPetikBibit extends AppCompatActivity implements  View.OnClickListener , IFormPengamatanPetikBibitView  {
    @BindView(R.id.mSubmit)
    Button mSubmit;

    @BindView(R.id.mAddSample)
    ImageView mAddSample;

    @BindView(R.id.containerPlotData)
    LinearLayout containerPlotData;
    @BindView(R.id.mPlot)
    EditText mPlot;

    @BindView(R.id.mSPK)
    EditText mSPK;

    @BindView(R.id.mLine)
    EditText mLine;

    @BindView(R.id.autoNoBibit)
    AutoCompleteTextView autonobibit;
    @BindView(R.id.mReworking)
    AutoCompleteTextView mReworking;

    @BindView(R.id.mLokasi)
    EditText mLokasi;

    @BindView(R.id.mMandorBibit)
    EditText mMandorBibit;

    @BindView(R.id.mLabel)
    EditText mLabel;

    @BindView(R.id.mReal)
    EditText mReal;


    @BindView(R.id.mJenisBibit)
    AutoCompleteTextView mJenisBibit;

    @BindView(R.id.mKelasBibit)
    AutoCompleteTextView mKelasBibit;

    @BindView(R.id.mNormal)
    EditText mNormal;

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

    @BindView(R.id.mPetik3)
    EditText mPetik3;

    @BindView(R.id.autoWilayah)
    AutoCompleteTextView autoWilayah;

    @BindView(R.id.mPetik4)
    EditText mPetik4;

    @BindView(R.id.mPetik5)
    EditText mPetik5;

    @BindView(R.id.mPetik6)
    EditText mPetik6;

    @BindView(R.id.mPetik7)
    EditText mPetik7;

    @BindView(R.id.mKeterangan)
    EditText mKeterangan;

    @BindView(R.id.mKet)
    EditText mKet;

    @BindView(R.id.mUpdatePeta)
    EditText mUpdatePeta;

    View rowView;
    View rowViewPlot;
    View viewnya = null ;
    String plot = "0" ;
    PetikBibitModel model;

    List<PlotModel> dataPlot = new ArrayList<>() ;
    List<SampleModel> dataSample = new ArrayList<>() ;

    SweetAlertDialog sweetAlertDialog;
    SampleModel sampleModel = null;
    PlotModel plotModel = null ;
    int index = 1 ;

    private static final String DRAFT_KEY = "draft_petik_bibit";
    FormPengamatanPetikBibitPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form_pengamatan_petik_bibit);
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

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line , autojenisbibit2);
        mJenisBibit.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line , autokelasbibit2);
        mKelasBibit.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, autoWilayah2);
        autoWilayah.setAdapter(adapter3);

        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mReworking2);
        mReworking.setAdapter(adapter4);

        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, autoNObibit2);
        autonobibit.setAdapter(adapter5);

        EditText lokasiUppercase = findViewById(R.id.mLokasi);
        lokasiUppercase.setFilters(new InputFilter[] {new InputFilter.AllCaps()});

        // Menonaktifkan input teks, tetapi dropdown masih muncul
        mJenisBibit.setKeyListener(null);
        mKelasBibit.setKeyListener(null);
        autonobibit.setKeyListener(null);
        mReworking.setKeyListener(null);
        autoWilayah.setKeyListener(null);

        // Memastikan dropdown muncul meskipun tidak ada teks yang dimasukkan
        autoWilayah.setThreshold(1);
        mJenisBibit.setThreshold(1);
        mKelasBibit.setThreshold(1);
        mReworking.setThreshold(1);
        autonobibit.setThreshold(1);

        // Atur threshold sesuai kebutuhan (misalnya 1 untuk memulai pencarian setelah 1 karakter)

        model = (PetikBibitModel) getIntent().getSerializableExtra("model");
        Log.d("bajakmodel" , new Gson().toJson(model));

        presenter = new FormPengamatanPetikBibitPresenter(this);
        mSubmit.setOnClickListener(this);
        mAddSample.setOnClickListener(this);
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

//    private void saveDraft() {
//        model.setNO_SPK(mSPK.getText().toString());
//        model.setNO_LINE(mLine.getText().toString());
//        model.setLOKASI(mLokasi.getText().toString());
//        model.setWILAYAH(autoWilayah.getText().toString());
//        model.setDATA(dataPlot);
//
//
//        TemporaryFormStorage.saveDraft(this, DRAFT_KEY, model);
//        Log.d("DraftPetikBibit", "Draft tersimpan otomatis");
//    }

    private void saveTemporaryData() {
        // Simpan sementara
        PetikBibitModel draft = new PetikBibitModel();

        draft.setNO_SPK(mSPK.getText().toString());
        draft.setNO_LINE(mLine.getText().toString());
        draft.setLOKASI(mLokasi.getText().toString());
        draft.setWILAYAH(autoWilayah.getText().toString());

        draft.setMandor_bibit(mMandorBibit.getText().toString());
        draft.setJenis_bibit(mJenisBibit.getText().toString());
        draft.setReworking(mReworking.getText().toString());
        draft.setUpdate_peta(mUpdatePeta.getText().toString());

        draft.setDATA(dataPlot); // simpan semua plot + sample

        TemporaryFormStorage.saveDraft(this, DRAFT_KEY, draft);
        Log.d("DraftSaved", new Gson().toJson(draft));
    }

    private void loadTemporaryData() {
        // Simpan sementara
        PetikBibitModel draft = TemporaryFormStorage.loadDraft(this, DRAFT_KEY, PetikBibitModel.class);
        if (draft != null) {
            Log.d("DraftLoaded", new Gson().toJson(draft));
            mSPK.setText(draft.getNO_SPK());
            mLine.setText(draft.getNO_LINE());
            mLokasi.setText(draft.getLOKASI());
            autoWilayah.setText(draft.getWILAYAH());

            mMandorBibit.setText(draft.getMandor_bibit());
            mJenisBibit.setText(draft.getJenis_bibit());
            mReworking.setText(draft.getReworking());
            mUpdatePeta.setText(draft.getUpdate_peta());


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
            mJenisBibit.post(() -> {
                ArrayAdapter<String> adapterJenisBibit = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, autojenisbibit2);
                mJenisBibit.setAdapter(adapterJenisBibit);
            });

            mKelasBibit.post(() -> {
                ArrayAdapter<String> adapterKelasBibit = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, autokelasbibit2);
                mKelasBibit.setAdapter(adapterKelasBibit);
            });

            autonobibit.post(() -> {
                ArrayAdapter<String> adapterNoBibit = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, autoNObibit2);
                autonobibit.setAdapter(adapterNoBibit);
            });

            mReworking.post(() -> {
                ArrayAdapter<String> adapterReworking = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, mReworking2);
                mReworking.setAdapter(adapterReworking);
            });

            autoWilayah.post(() -> {
                ArrayAdapter<String> adapterWilayah = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, autoWilayah2);
                autoWilayah.setAdapter(adapterWilayah);
            });


        }
    }

    private void restoreSampleView(SampleModel s) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.data_petikbibit_sample_field, null);

        TextView noSample = rowView.findViewById(R.id.mNoSample);
        TextView plot = rowView.findViewById(R.id.mPlot);
        TextView real = rowView.findViewById(R.id.mReal);
        TextView label = rowView.findViewById(R.id.mLabel);
        TextView normal = rowView.findViewById(R.id.mNormal);
        TextView afkir = rowView.findViewById(R.id.mAfkir);
        TextView over = rowView.findViewById(R.id.mOver);
        TextView overPlus = rowView.findViewById(R.id.mOverPlus);
        TextView petik1 = rowView.findViewById(R.id.mPetik1);
        TextView petik2 = rowView.findViewById(R.id.mPetik2);
        TextView petik3 = rowView.findViewById(R.id.mPetik3);
        TextView petik4 = rowView.findViewById(R.id.mPetik4);
        TextView petik5 = rowView.findViewById(R.id.mPetik5);
        TextView petik6 = rowView.findViewById(R.id.mPetik6);
        TextView petik7 = rowView.findViewById(R.id.mPetik7);

        noSample.setText(String.valueOf(s.getNo_sample()));
        plot.setText(String.valueOf(s.getPlot()));
        real.setText(String.valueOf(s.getReal()));
        label.setText(String.valueOf(s.getLabel()));
        normal.setText(String.valueOf(s.getBibit_normal()));
        afkir.setText(String.valueOf(s.getBibit_afkir()));
        over.setText(String.valueOf(s.getBibit_over()));
        overPlus.setText(String.valueOf(s.getBibit_over_plus()));
        petik1.setText(String.valueOf(s.getBibit_1()));
        petik2.setText(String.valueOf(s.getBibit_2()));
        petik3.setText(String.valueOf(s.getBibit_3()));
        petik4.setText(String.valueOf(s.getBibit_4()));
        petik5.setText(String.valueOf(s.getBibit_5()));
        petik6.setText(String.valueOf(s.getBibit_6()));
        petik7.setText(String.valueOf(s.getBibit_7()));

        containerPlotData.addView(rowView, 0);
    }

    private void clearForm() {
        // Kosongkan semua input
        mSPK.setText("");
        mLine.setText("");
        mLokasi.setText("");
        autoWilayah.setText("");
        mMandorBibit.setText("");
        mJenisBibit.setText("");
        mKelasBibit.setText("");
        mReworking.setText("");
        mLabel.setText("");
        mReal.setText("");
        mNormal.setText("");
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
        mKeterangan.setText("");
        mKet.setText("");
        mUpdatePeta.setText("");

        // Kosongkan data memory
        dataPlot.clear();
        dataSample.clear();
        containerPlotData.removeAllViews();

        // Reset model
        model = new PetikBibitModel();

        // Hapus draft
        TemporaryFormStorage.clearDraft(this, DRAFT_KEY);

        Log.d("FormReset", "Form PetikBibit cleared");
    }

    void addPlotForm() {
        //Tidak boleh kosong
        if (mSPK.getText().toString().equals("")) {
            Toast.makeText(this, "No SPK tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mLine.getText().toString().equals("")) {
            Toast.makeText(this, "No Line tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mLokasi.getText().toString().equals("")) {
            Toast.makeText(this, "Lokasi tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mMandorBibit.getText().toString().equals("")) {
            Toast.makeText(this, "Nama Mandor tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mJenisBibit.getText().toString().equals("")) {
            Toast.makeText(this, "Jenis Bibit tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (autoWilayah.getText().toString().equals("")) {
            Toast.makeText(this, "Wilayah tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mReworking.getText().toString().equals("")) {
            Toast.makeText(this, "Reworking tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mUpdatePeta.getText().toString().equals("")) {
            Toast.makeText(this, "Update Peta tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mPlot.getText().toString().equals("")) {
            Toast.makeText(this, "No Plot tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mKelasBibit.getText().toString().equals("")) {
            Toast.makeText(this, "Kelas Bibit tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (autonobibit.getText().toString().equals("")) {
            Toast.makeText(this, "Nomor Bibit tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mNormal.getText().toString().equals("")) {
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
        rowView = inflater.inflate(R.layout.data_petikbibit_sample_field, null);

        final TextView real = rowView.findViewById(R.id.mReal);
        final TextView label = rowView.findViewById(R.id.mLabel);
        final TextView plot = rowView.findViewById(R.id.mPlot);
        final TextView normal = rowView.findViewById(R.id.mNormal);
        final TextView afkir = rowView.findViewById(R.id.mAfkir);
        final TextView overPlus = rowView.findViewById(R.id.mOverPlus);
        final TextView over = rowView.findViewById(R.id.mOver);
        final TextView petik1 = rowView.findViewById(R.id.mPetik1);
        final TextView petik2 = rowView.findViewById(R.id.mPetik2);
        final TextView petik3 = rowView.findViewById(R.id.mPetik3);
        final TextView petik4 = rowView.findViewById(R.id.mPetik4);
//        final TextView nobibit = rowView.findViewById(R.id.autoNoBibit);
        final TextView petik5 = rowView.findViewById(R.id.mPetik5);
        final TextView petik6 = rowView.findViewById(R.id.mPetik6);
        final TextView petik7 = rowView.findViewById(R.id.mPetik7);
        final TextView noSample = rowView.findViewById(R.id.mNoSample);
        label.setText(mLabel.getText().toString());
        real.setText(mReal.getText().toString());
        normal.setText(mNormal.getText().toString());
        afkir.setText(mAfkir.getText().toString());
        overPlus.setText(mOverPlus.getText().toString());
        over.setText(mOver.getText().toString());
        plot.setText(mPlot.getText().toString());
//        nobibit.setText(autonobibit.getText().toString());
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

        sampleModel.setNo_spk2(mSPK.getText().toString());
        sampleModel.setNo_line(mLine.getText().toString());
        sampleModel.setNo_spk(model.getNO_SPK());
        sampleModel.setNomor_bibit(autonobibit.getText().toString());
        sampleModel.setLokasi(mLokasi.getText().toString());
        sampleModel.setJenis_bibit(mJenisBibit.getText().toString());
        sampleModel.setKelas_bibit(mKelasBibit.getText().toString());
        sampleModel.setNo_sample(noSample.getText().toString());
        sampleModel.setPlot(Integer.parseInt(mPlot.getText().toString()));
        sampleModel.setBibit_normal(Float.parseFloat(mNormal.getText().toString()));
        sampleModel.setReworking(mReworking.getText().toString());
        sampleModel.setWil(autoWilayah.getText().toString());

        sampleModel.setReal(parseFloatDefault(mReal.getText().toString(), 0.0f));
        sampleModel.setLabel(parseFloatDefault(mLabel.getText().toString(), 0.0f));
        sampleModel.setBibit_afkir(parseFloatDefault(mAfkir.getText().toString(), 0.0f));
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
        sampleModel.setMandor_bibit(mMandorBibit.getText().toString().isEmpty() ? "-" : mMandorBibit.getText().toString());


//        dataSample.add(sampleModel);
        dataSample.add(sampleModel);
        Log.d("datanyanih" , new Gson().toJson(model));
        containerPlotData.addView(rowView, 0);

        //Hapus Saat Apply
        Toast.makeText(this, "Data Sudah Ditambahkan", Toast.LENGTH_SHORT).show();
        mLabel.getText().clear();
        mReal.getText().clear();
        mNormal.getText().clear();
        mAfkir.getText().clear();

        mOverPlus.getText().clear();
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
        String message = "Petik Bibit"
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

    private static final String [] autojenisbibit2 = new String[]{
            "Sucker", "Crown", "Crown Storing", "Nursery Sucker Plus"
    };

    private static final String[] mReworking2 = new String[]{
            "Sebelum Reworking", "Sesudah Reworking"
    };
    private static final String[] autoNObibit2 = new String[]{
            "0+", "0", "1", "2", "3", "4", "5", "6", "7"
    };

    private static final String [] autokelasbibit2 = new String[]{
            "Super Kecil", "Kecil", "Sedang", "Besar", " Extra Besar"
    };

    private static final String[] autoWilayah2 = new String[]{
            "AW01", "AW02", "AW03", "AW04", "AW05", "AW06", "AW07", "AW08", "AW09", "AW10","AW11", "AW12", "AW13", "AW14", "AW15", "AW16", "AW17", "AW18", "AW19", "AW20", "AW21", "AW22", "AW23"
    };
}