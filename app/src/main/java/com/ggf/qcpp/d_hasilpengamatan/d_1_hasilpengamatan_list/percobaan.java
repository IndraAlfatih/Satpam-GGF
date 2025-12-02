//package com.ggf.qcpp.e_formpengamatan.tanam;
//
//import static com.ggf.qcpp.utils.Utils.generateTglSekarang;
//import static com.ggf.qcpp.utils.Utils.goToListPengamatan;
//import static com.ggf.qcpp.utils.Utils.parStringDefault;
//import static com.ggf.qcpp.utils.Utils.parseFloatDefault;
//import static com.ggf.qcpp.utils.Utils.parseIntDefault;
//
//import android.app.DatePickerDialog;
//import android.content.Context;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.AutoCompleteTextView;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//import com.ggf.qcpp.R;
//import com.ggf.qcpp.e_formpengamatan.petikbibit.FormPengamatanPetikBibitPresenter;
//import com.ggf.qcpp.e_formpengamatan.petikbibit.IFormPengamatanPetikBibitView;
//import com.ggf.qcpp.e_formpengamatan.petikbibit.model.PetikBibitModel;
//import com.ggf.qcpp.e_formpengamatan.tanam.model.PlotModel;
//import com.ggf.qcpp.e_formpengamatan.tanam.model.SampleModel;
//import com.ggf.qcpp.e_formpengamatan.tanam.model.TanamModel;
//import com.ggf.qcpp.ui.SweetDialogs;
//import com.ggf.qcpp.utils.Utils;
//import com.google.gson.Gson;
//import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
//
//import java.text.DecimalFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Collections;
//import java.util.Date;
//import java.util.List;
//import java.util.Locale;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
//public class FormPengamatanTanam extends AppCompatActivity implements  View.OnClickListener , IFormPengamatanTanamView {
//
//    @BindView(R.id.mSubmit)
//    Button mSubmit;
//    @BindView(R.id.mAddSample)
//    ImageView mAddSample;
//    @BindView(R.id.containerPlotData)
//    LinearLayout containerPlotData;
//    @BindView(R.id.mLokasi)
//    EditText mLokasi;
//    @BindView(R.id.mUpdatePeta)
//    EditText mUpdatePeta;
//    @BindView(R.id.automusim)
//    AutoCompleteTextView automusim;
//    @BindView(R.id.autostatuspengamatan)
//    AutoCompleteTextView autostatuspengamatan;
//    @BindView(R.id.autoWilayah)
//    AutoCompleteTextView autoWilayah;
//    @BindView(R.id.mPlot)
//    EditText mPlot;
//    @BindView(R.id.mLuasPlot)
//    EditText mLuasPlot;
//    @BindView(R.id.autokelasbibit)
//    AutoCompleteTextView autokelasbibit;
//    @BindView(R.id.autojenisbibit)
//    AutoCompleteTextView autojenisbibit;
//    @BindView(R.id.mMandor)
//    EditText mMandor;
//    @BindView(R.id.mPanjangJTDB)
//    EditText mPanjangJTDB;
//    @BindView(R.id.mTanamanJTDB)
//    AutoCompleteTextView mTanamanJTDB;
//    @BindView(R.id.autostatusjtdb)
//    AutoCompleteTextView autostatusjtdb;
//    @BindView(R.id.mPanjangJTAB)
//    EditText mPanjangJTAB;
//    @BindView(R.id.mTanamanJTAB)
//    AutoCompleteTextView mTanamanJTAB;
//    @BindView(R.id.autostatusjtab)
//    AutoCompleteTextView autostatusjtab;
//    @BindView(R.id.m1)
//    EditText m1;
//    @BindView(R.id.m2)
//    EditText m2;
//    @BindView(R.id.m3)
//    EditText m3;
//    @BindView(R.id.m4)
//    EditText m4;
//    @BindView(R.id.mTegak)
//    EditText mTegak;
//    @BindView(R.id.mTidakTegak)
//    EditText mTidakTegak;
//
//    @BindView(R.id.mKeterangan)
//    EditText mKeterangan;
//
//    View rowView;
//    View rowViewPlot;
//    View viewnya = null;
//    String plot = "0";
//    TanamModel model;
//
//    List<PlotModel> dataPlot = new ArrayList<>();
//    List<SampleModel> dataSample = new ArrayList<>();
//
//    SweetAlertDialog sweetAlertDialog;
//    SampleModel sampleModel = null;
//    PlotModel plotModel = null;
//    int index = 1;
//    FormPengamatanTanamPresenter presenter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_form_pengamatan_tanam);
//        ButterKnife.bind(this);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//        Calendar calendar = Calendar.getInstance();
//        int year = calendar.get(Calendar.YEAR);
//        int month = calendar.get(Calendar.MONTH);
//        int day = calendar.get(Calendar.DAY_OF_MONTH);
//
//        // Saat kolom di-klik, tampilkan DatePickerDialog
//        mUpdatePeta.setOnClickListener(v -> {
//            DatePickerDialog datePickerDialog = new DatePickerDialog(
//                    this,
//                    (view, selectedYear, selectedMonth, selectedDay) -> {
//                        // Tambahkan 1 ke bulan (karena bulan dimulai dari 0)
//                        String selectedDate = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay;
//                        mUpdatePeta.setText(selectedDate); // Set tanggal di EditText
//                    },
//                    year, month, day);
//
//            datePickerDialog.show(); // Tampilkan dialog
//        });
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, autojenisbibit2);
//        autojenisbibit.setAdapter(adapter);
//
//        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, automusim2);
//        automusim.setAdapter(adapter2);
//
//        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, autostatuspengamatan2);
//        autostatuspengamatan.setAdapter(adapter3);
//
//        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, autokelasbibit2);
//        autokelasbibit.setAdapter(adapter4);
//
//        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, autotanaman2);
//        mTanamanJTDB.setAdapter(adapter5);
//
//        ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, autotanaman3);
//        mTanamanJTAB.setAdapter(adapter6);
//
//        ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, autostatusjtab2);
//        autostatusjtab.setAdapter(adapter7);
//
//        ArrayAdapter<String> adapter8 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, autostatusjtdb2);
//        autostatusjtdb.setAdapter(adapter8);
//
//        ArrayAdapter<String> adapter9 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, autoWilayah2);
//        autoWilayah.setAdapter(adapter9);
//
//        // Menonaktifkan input teks, tetapi dropdown masih muncul
//        automusim.setKeyListener(null);
//        autojenisbibit.setKeyListener(null);
//        autostatuspengamatan.setKeyListener(null);
//        autokelasbibit.setKeyListener(null);
//        mTanamanJTDB.setKeyListener(null);
//        mTanamanJTAB.setKeyListener(null);
//        autostatusjtab.setKeyListener(null);
//        autostatusjtdb.setKeyListener(null);
//
//        // Memastikan dropdown muncul meskipun tidak ada teks yang dimasukkan
//        automusim.setThreshold(1);
//        autojenisbibit.setThreshold(1);
//        autostatuspengamatan.setThreshold(1);
//        autokelasbibit.setThreshold(1);
//        mTanamanJTDB.setThreshold(1);
//        mTanamanJTAB.setThreshold(1);
//        autostatusjtab.setThreshold(1);
//        autostatusjtdb.setThreshold(1);
//        // Atur threshold sesuai kebutuhan (misalnya 1 untuk memulai pencarian setelah 1 karakter)
//
//        model = (TanamModel) getIntent().getSerializableExtra("model");
//        Log.d("TanamModel", new Gson().toJson(model));
//
//        presenter = new FormPengamatanTanamPresenter(this);
//        mSubmit.setOnClickListener(this);
//        mAddSample.setOnClickListener(this);
//    }
//
//    void addPlotForm() {
//
//        //Tidak boleh kosong
//        if (mLokasi.getText().toString().equals("")) {
//            Toast.makeText(this, "Lokasi Tidak boleh kosong", Toast.LENGTH_SHORT).show();
//        } else if (mUpdatePeta.getText().toString().equals("")) {
//            Toast.makeText(this, "Update Peta tidak boleh kosong", Toast.LENGTH_SHORT).show();
//        } else if (automusim.getText().toString().equals("")) {
//            Toast.makeText(this, "Musim tidak boleh kosong", Toast.LENGTH_SHORT).show();
//        } else if (autostatuspengamatan.getText().toString().equals("")) {
//            Toast.makeText(this, "Status Pengamatan tidak boleh kosong", Toast.LENGTH_SHORT).show();
//        } else if (autoWilayah.getText().toString().equals("")) {
//            Toast.makeText(this, "Wilayah tidak boleh kosong", Toast.LENGTH_SHORT).show();
//        } else if (mPlot.getText().toString().equals("")) {
//            Toast.makeText(this, "No Plot tidak boleh kosong", Toast.LENGTH_SHORT).show();
//        } else if (mLuasPlot.getText().toString().equals("")) {
//            Toast.makeText(this, "Luas Plot tidak boleh kosong", Toast.LENGTH_SHORT).show();
//        } else if (autokelasbibit.getText().toString().equals("")) {
//            Toast.makeText(this, "Kelas Bibit tidak boleh kosong", Toast.LENGTH_SHORT).show();
//        } else if (autojenisbibit.getText().toString().equals("")) {
//            Toast.makeText(this, "Jenis Bibit tidak boleh kosong", Toast.LENGTH_SHORT).show();
//        } else if (mPanjangJTAB.getText().toString().equals("")) {
//            Toast.makeText(this, "Panjang JTAB tidak boleh kosong", Toast.LENGTH_SHORT).show();
//        } else if (mTanamanJTAB.getText().toString().equals("")) {
//            Toast.makeText(this, "Total Tanaman JTDB tidak boleh kosong", Toast.LENGTH_SHORT).show();
//        } else if (mPanjangJTDB.getText().toString().equals("")) {
//            Toast.makeText(this, "Panjang JTDB tidak boleh kosong", Toast.LENGTH_SHORT).show();
//        } else if (mTanamanJTDB.getText().toString().equals("")) {
//            Toast.makeText(this, "Total Tanaman JTAB tidak boleh kosong", Toast.LENGTH_SHORT).show();
//        } else if (mTegak.getText().toString().equals("")) {
//            Toast.makeText(this, "Total Tegak Terinjak tidak boleh kosong", Toast.LENGTH_SHORT).show();
//        } else if (m1.getText().toString().equals("")) {
//            Toast.makeText(this, "Kedalaman tidak boleh kosong", Toast.LENGTH_SHORT).show();
//        } else if (m2.getText().toString().equals("")) {
//            Toast.makeText(this, "Kedalaman tidak boleh kosong", Toast.LENGTH_SHORT).show();
//        } else if (m3.getText().toString().equals("")) {
//            Toast.makeText(this, "Kedalaman tidak boleh kosong", Toast.LENGTH_SHORT).show();
//        } else if (mTegak.getText().toString().equals("")) {
//            Toast.makeText(this, "Data tidak boleh kosong", Toast.LENGTH_SHORT).show();
//        } else {
//            sampleModel = new SampleModel();
//            boolean plotExists = false;
//
//            // Cek apakah plot sudah ada
//            for (PlotModel existingPlot : dataPlot) {
//                if (existingPlot.getPLOT().equals(mPlot.getText().toString())) {
//                    // Jika plot sudah ada, tambahkan sampel baru ke plot ini
//                    plotExists = true;
//                    dataSample = existingPlot.getSAMPLE(); // Ambil daftar sampel dari plot
//                    this.addSampleForm(); // Tambahkan data sampel
//                    break;
//                }
//            }
//
//            if (!plotExists) {
//                // Jika plot baru, buat plot baru dan tambahkan ke dataPlot
//                index = 1;
//                dataSample = new ArrayList<>();
//                plotModel = new PlotModel();
//                this.addSampleForm(); // Tambahkan data sampel pertama
//                plotModel.setPLOT(mPlot.getText().toString());
//                plotModel.setSAMPLE(dataSample);
//
//                dataPlot.add(plotModel);
//            }
//
//            plot = mPlot.getText().toString();
//
//        }
//
//
//    }
//
//    void addSampleForm() {
//        float avgJTDB = 0;
//        float avgJTAB = 0;
//        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        rowView = inflater.inflate(R.layout.data_tanam_field, null);
//        DecimalFormat df = new DecimalFormat();
//        df.setMaximumFractionDigits(2);
//
//        final TextView plot = rowView.findViewById(R.id.mPlot);
//        final TextView mNoSample = rowView.findViewById(R.id.mNoSample);
//        final TextView luasplot = rowView.findViewById(R.id.mLuasPlot);
//        final TextView kelasbibit = rowView.findViewById(R.id.autokelasbibit);
//        final TextView jenisbibit = rowView.findViewById(R.id.autojenisbibit);
//        final TextView namamandor = rowView.findViewById(R.id.mMandor);
//        final TextView panjangjtdb = rowView.findViewById(R.id.mPanjangJTDB);
//        final TextView tanamanjtdb = rowView.findViewById(R.id.mTanamanJTDB);
//        final TextView statusjtdb = rowView.findViewById(R.id.autostatusjtdb);
//        final TextView panjangjtab = rowView.findViewById(R.id.mPanjangJTAB);
//        final TextView tanamanjtab = rowView.findViewById(R.id.mTanamanJTAB);
//        final TextView statusjtab = rowView.findViewById(R.id.autostatusjtab);
//        final TextView mm1 = rowView.findViewById(R.id.m1);
//        final TextView mm2 = rowView.findViewById(R.id.m2);
//        final TextView mm3 = rowView.findViewById(R.id.m3);
//        final TextView mm4 = rowView.findViewById(R.id.m4);
//        final TextView tegak = rowView.findViewById(R.id.mTegak);
//        final TextView tidaktegak = rowView.findViewById(R.id.mTidakTegak);
//        namamandor.setText(mMandor.getText().toString().isEmpty() ? "-" : mMandor.getText().toString());
//        luasplot.setText(mLuasPlot.getText().toString());
//        kelasbibit.setText(autokelasbibit.getText().toString());
//        jenisbibit.setText(autojenisbibit.getText().toString());
//        panjangjtdb.setText(mPanjangJTDB.getText().toString());
//        tanamanjtdb.setText(mTanamanJTDB.getText().toString());
//        statusjtdb.setText(autostatusjtdb.getText().toString());
//        panjangjtab.setText(mPanjangJTAB.getText().toString());
//        tanamanjtab.setText(mTanamanJTAB.getText().toString());
//        statusjtab.setText(autostatusjtab.getText().toString());
//        plot.setText(mPlot.getText().toString());
//        mm1.setText(m1.getText().toString());
//        mm2.setText(m2.getText().toString());
//        mm3.setText(m3.getText().toString());
//        mm4.setText(m4.getText().toString());
//        tegak.setText(mTegak.getText().toString());
//        tidaktegak.setText(mTidakTegak.getText().toString());
//
//        List<Integer> existingSamples = new ArrayList<>();
//        for (SampleModel sample : dataSample) {
//            if (sample.getPlot() == Integer.parseInt(mPlot.getText().toString())) {
//                existingSamples.add(sample.getNo_sample());
//            }
//        }
//
//        // Cari nomor urutan yang hilang
//        Collections.sort(existingSamples);
//        int nextSampleNumber = 1; // Mulai dari 1
//        for (int i = 0; i < existingSamples.size(); i++) {
//            if (existingSamples.get(i) != nextSampleNumber) {
//                break; // Temukan nomor yang hilang
//            }
//            nextSampleNumber++;
//        }
//
//        mNoSample.setText(String.valueOf(nextSampleNumber));
//
//        sampleModel.setPlot(Integer.parseInt(mPlot.getText().toString()));
//        sampleModel.setLokasi(mLokasi.getText().toString());
////        sampleModel.setLokasi(Integer.parseInt(mPlot.getText().toString()));
//        sampleModel.setStd_musim(automusim.getText().toString());
//        sampleModel.setStatus_pengamatan(autostatuspengamatan.getText().toString());
//        sampleModel.setWil(autoWilayah.getText().toString());
//
//        sampleModel.setLuas_plot(Float.parseFloat(mLuasPlot.getText().toString()));
//        sampleModel.setKelas_bibit(autokelasbibit.getText().toString());
//        sampleModel.setJenis_bibit(autojenisbibit.getText().toString());
//        sampleModel.setMandor_bibit(mMandor.getText().toString());
//
//        sampleModel.setPanjang_jtdb(Float.parseFloat(mPanjangJTDB.getText().toString()));
//        sampleModel.setTot_tanamjtdb(Integer.parseInt(mTanamanJTDB.getText().toString()));
//        sampleModel.setStatus_jtdb(autostatusjtdb.getText().toString());
//
//        sampleModel.setPanjang_jtab(Float.parseFloat(mPanjangJTAB.getText().toString()));
//        sampleModel.setTot_tanamjtab(Integer.parseInt(mTanamanJTAB.getText().toString()));
//        sampleModel.setStatus_jtab(autostatusjtab.getText().toString());
//
//        sampleModel.setKedalaman_1(Float.parseFloat(m1.getText().toString()));
//        sampleModel.setKedalaman_2(Float.parseFloat(m2.getText().toString()));
//        sampleModel.setKedalaman_3(Float.parseFloat(m3.getText().toString()));
//        sampleModel.setKedalaman_4(Float.parseFloat(m4.getText().toString()));
//
//        sampleModel.setTot_tegakterinjak(Integer.parseInt(mTegak.getText().toString()));
//        sampleModel.setTot_tidaktegakterinjak(Integer.parseInt(mTidakTegak.getText().toString()));
//
//        String selectedDateString = mUpdatePeta.getText().toString();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
//        try {
//            Date selectedDate = dateFormat.parse(selectedDateString); // Konversi String ke Date
////            sampleModel.setUpdate_peta(selectedDate); // Set ke model
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//            Toast.makeText(this, "Format tanggal tidak valid", Toast.LENGTH_SHORT).show();
//        }
//        sampleModel.setUpdate_peta(selectedDateString);
//
//
//        sampleModel.setKeterangan(parStringDefault(mKeterangan.getText().toString() , "-"));
//
////        dataSample.add(sampleModel);
//        dataSample.add(sampleModel);
//        Log.d("datanyanih", new Gson().toJson(model));
//        containerPlotData.addView(rowView, 0);
//
//        //Hapus Saat Apply
//        Toast.makeText(this, "Data Sudah Ditambahkan", Toast.LENGTH_SHORT).show();
//        mPanjangJTAB.getText().clear();
//        mTanamanJTAB.getText().clear();
//        mPanjangJTDB.getText().clear();
//        mTanamanJTDB.getText().clear();
//        mTegak.getText().clear();
//        mTidakTegak.getText().clear();
//        autostatusjtdb.getText().clear();
//        autostatusjtab.getText().clear();
//
//        m1.getText().clear();
//        m2.getText().clear();
//        m3.getText().clear();
//        m4.getText().clear();
//
//
//    }
//
//    public void onDeleteSample(View v) {
//
//        TextView txtPlot = ((View) v.getParent()).findViewById(R.id.mPlot);
//        TextView txtNoSample = ((View) v.getParent()).findViewById(R.id.mNoSample);
//        int plotToDelete = Integer.parseInt(txtPlot.getText().toString());
//        int noSampleToDelete = Integer.parseInt(txtNoSample.getText().toString());
//
//        Log.d("Hapus", "Menghapus data dengan PLOT: " + plotToDelete + " dan No_Sample: " + noSampleToDelete);
//
//        // Hapus dari dataPlot
//        boolean dataRemoved = false;
//        for (int i = 0; i < dataPlot.size(); i++) {
//            if (dataPlot.get(i).getPLOT().equals(String.valueOf(plotToDelete))) {
//                List<SampleModel> samples = dataPlot.get(i).getSAMPLE();
//                for (int j = 0; j < samples.size(); j++) {
//                    if (samples.get(j).getNo_sample() == noSampleToDelete) {
//                        samples.remove(j);
//                        dataRemoved = true;
//                        Log.d("Hapus", "Data di dataPlot dihapus. PLOT: " + plotToDelete + ", No_Sample: " + noSampleToDelete);
//                        break;
//                    }
//                }
//
//                // Jika semua sampel sudah dihapus dari plot, hapus plot dari dataPlot
//                if (samples.isEmpty()) {
//                    dataPlot.remove(i);
//                    Log.d("Hapus", "Seluruh data untuk plot " + plotToDelete + " telah dihapus.");
//                }
//                break;
//            }
//        }
//
//        // Hapus dari dataSample
//        for (int i = 0; i < dataSample.size(); i++) {
//            if (dataSample.get(i).getPlot() == plotToDelete &&
//                    dataSample.get(i).getNo_sample() == noSampleToDelete) {
//                dataSample.remove(i);
//                Log.d("Hapus", "Data di dataSample dihapus. PLOT: " + plotToDelete + ", No_Sample: " + noSampleToDelete);
//                break;
//            }
//        }
//
//        // Hapus view dari container
//        if (dataRemoved) {
//            ((ViewGroup) v.getParent().getParent()).removeView((ViewGroup) v.getParent());
//            Log.d("Hapus", "View dihapus dari containerPlotData.");
//        } else {
//            Log.d("Hapus", "Data tidak ditemukan untuk dihapus.");
//        }
//
//        // Debugging final state
//        Log.d("Hapus", "State dataPlot setelah penghapusan: " + new Gson().toJson(dataPlot));
//        Log.d("Hapus", "State dataSample setelah penghapusan: " + new Gson().toJson(dataSample));
//    }
//
//    @Override
//    public void onSubmit() {
//        plotModel.setSAMPLE(dataSample);
//        model.setLOKASI(mLokasi.getText().toString());
//        model.setWILAYAH(autoWilayah.getText().toString());
//        model.setDATA(dataPlot);
//        Log.d("dataBody", new Gson().toJson(model));
//        presenter.createPengamatan(model);
//    }
//
//    @Override
//    public void onCreateSuccess(String rm) {
//        String message = "Tanam Berhasil di Kirim" + "\n\"" + generateTglSekarang() + "\"\nLokasi: " + mLokasi.getText().toString();
//        SweetDialogs.commonSuccessWithIntent(this,message, string -> {
//            goToListPengamatan(this);
//        });
//    }
//
//
//    @Override
//    public void showLoadingIndicator() {
////        Toast.makeText(this, "woi", Toast.LENGTH_SHORT).show();
//        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
//        sweetAlertDialog.setTitleText("Loading ...");
//        sweetAlertDialog.show();
//    }
//
//    @Override
//    public void hideLoadingIndicator() {
//        sweetAlertDialog.dismiss();
//    }
//
//    @Override
//    public void onNetworkError(String cause) {
//        Log.e("errornya", cause);
////        SweetDialogs.endpointError(this);
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.mSubmit:
//                this.onSubmit();
//                break;
//            case R.id.mAddSample:
//                this.addPlotForm();
//                break;
//        }
//    }
//
//    private static final String[] automusim2 = new String[]{
//            "Kering", "Basah"
//    };
//
//    private static final String[] autojenisbibit2 = new String[]{
//            "Sucker", "Crown", "Crown Storing", "Sucker Plus"
//    };
//
//    private static final String[] autostatuspengamatan2 = new String[]{
//            "Crosscheck", "Inprocess", "Reworking"
//    };
//
//    private static final String[] autokelasbibit2 = new String[]{
//            "Super Kecil", "Kecil", "Sedang", "Besar", "Over", "Over Plus"
//    };
//
//    private static final String[] autotanaman2 = new String[]{
//            "9", "10", "11"
//    };
//
//    private static final String[] autotanaman3 = new String[]{
//            "4", "5", "6"
//    };
//
//    private static final String[] autostatusjtab2 = new String[]{
//            "< Standar", "On Stndar", "> Standar"
//    };
//
//    private static final String[] autostatusjtdb2 = new String[]{
//            "< Standar", "On Stndar", "> Standar"
//    };
//    private static final String[] autoWilayah2 = new String[]{
//            "AW01", "AW02", "AW03", "AW04", "AW05", "AW06", "AW07", "AW08", "AW09", "AW10", "AW11", "AW12", "AW13", "AW14", "AW15", "AW16", "AW17", "AW18", "AW19", "AW20", "AW21", "AW22", "AW23"
//    };
//
//}
