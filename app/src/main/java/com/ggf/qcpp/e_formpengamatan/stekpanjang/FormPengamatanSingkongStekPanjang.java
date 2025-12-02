package com.ggf.qcpp.e_formpengamatan.stekpanjang;

import static com.ggf.qcpp.utils.Utils.generateTglSekarang;
import static com.ggf.qcpp.utils.Utils.goToListPengamatan;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.ggf.qcpp.e_formpengamatan.stekpanjang.model.PlotModel;
import com.ggf.qcpp.e_formpengamatan.stekpanjang.model.SampleModel;
import com.ggf.qcpp.e_formpengamatan.stekpanjang.model.SingkongStekPanjangModel;
import com.ggf.qcpp.network.SQLiteHelper;
import com.ggf.qcpp.ui.SweetDialogs;
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

public class FormPengamatanSingkongStekPanjang extends AppCompatActivity implements View.OnClickListener, IFormPengamatanSingkongStekPanjangView {

    @BindView(R.id.mSubmit)
    Button mSubmit;
    @BindView(R.id.mAddSample)
    ImageView mAddSample;
    @BindView(R.id.containerPlotData)
    LinearLayout containerPlotData;
    @BindView(R.id.mPlot)
    EditText mPlot;

    //batas

    @BindView(R.id.mLokasi)
    public EditText mLokasi;

    @BindView(R.id.mMandorBibit)
    public EditText mMandorBibit;

    @BindView(R.id.mUpdatePeta)
    public EditText mUpdatePeta;

    @BindView(R.id.mJenisBibit)
    public AutoCompleteTextView mJenisBibit;
//
//    @BindView(R.id.mJumlahBibitDiSpk)
//    public EditText mJumlahBibitDiSpk;


    @BindView(R.id.mLuasPlot)
    public EditText mLuasPlot;

    @BindView(R.id.mKodeUnit)
    public EditText mKodeUnit;

    @BindView(R.id.mJumlahBibitIkatDiSpk)
    public EditText mJumlahBibitIkatDiSpk;

    @BindView(R.id.mRealBibitIkat)
    public EditText mRealBibitIkat;

    @BindView(R.id.mBibitNormal)
    public EditText mBibitNormal;

    @BindView(R.id.mBibitAfkir)
    public EditText mBibitAfkir;

    @BindView(R.id.mJumlahKelilingBatangMasukStandar)
    public EditText mJumlahKelilingBatangMasukStandar;

    @BindView(R.id.mKelilingBibitAtas1)
    public EditText mKelilingBibitAtas1;

    @BindView(R.id.mKelilingBibitAtas2)
    public EditText mKelilingBibitAtas2;

    @BindView(R.id.mKelilingBibitAtas3)
    public EditText mKelilingBibitAtas3;

    @BindView(R.id.mKelilingBibitAtas4)
    public EditText mKelilingBibitAtas4;

    @BindView(R.id.mKelilingBibitAtas5)
    public EditText mKelilingBibitAtas5;

    @BindView(R.id.mKelilingBibitAtas6)
    public EditText mKelilingBibitAtas6;

    @BindView(R.id.mKelilingBibitAtas7)
    public EditText mKelilingBibitAtas7;

    @BindView(R.id.mKelilingBibitAtas8)
    public EditText mKelilingBibitAtas8;

    @BindView(R.id.mKelilingBibitAtas9)
    public EditText mKelilingBibitAtas9;

    @BindView(R.id.mKelilingBibitAtas10)
    public EditText mKelilingBibitAtas10;



    @BindView(R.id.mKelilingBibitBawah1)
    public EditText mKelilingBibitBawah1;

    @BindView(R.id.mKelilingBibitBawah2)
    public EditText mKelilingBibitBawah2;

    @BindView(R.id.mKelilingBibitBawah3)
    public EditText mKelilingBibitBawah3;

    @BindView(R.id.mKelilingBibitBawah4)
    public EditText mKelilingBibitBawah4;

    @BindView(R.id.mKelilingBibitBawah5)
    public EditText mKelilingBibitBawah5;

    @BindView(R.id.mKelilingBibitBawah6)
    public EditText mKelilingBibitBawah6;

    @BindView(R.id.mKelilingBibitBawah7)
    public EditText mKelilingBibitBawah7;

    @BindView(R.id.mKelilingBibitBawah8)
    public EditText mKelilingBibitBawah8;

    @BindView(R.id.mKelilingBibitBawah9)
    public EditText mKelilingBibitBawah9;

    @BindView(R.id.mKelilingBibitBawah10)
    public EditText mKelilingBibitBawah10;

    @BindView(R.id.mKeterangan)
    public EditText mKeterangan;

    View rowView;
    View rowViewPlot;
    View viewnya = null;
    String plot = "0";
    SingkongStekPanjangModel model;

    int index = 1;

    List<PlotModel> dataPlot = new ArrayList<>();
    List<SampleModel> dataSample = new ArrayList<>();

    SweetAlertDialog sweetAlertDialog;
    SampleModel sampleModel = null;
    PlotModel plotModel = null;
    FormPengamatanSingkongStekPanjangPresenter presenter;

    private ScrollView scrollView;
    private View contentView;
    private View focusedView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form_pengamatan_singkong_stek_panjang);

        ButterKnife.bind(this);
        presenter = new FormPengamatanSingkongStekPanjangPresenter(this);
        model = (SingkongStekPanjangModel) getIntent().getSerializableExtra("model");

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

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mJenisBibit2);
        mJenisBibit.setAdapter(adapter);

        // Menonaktifkan input teks, tetapi dropdown masih muncul
        mJenisBibit.setKeyListener(null);

        // Memastikan dropdown muncul meskipun tidak ada teks yang dimasukkan
        mJenisBibit.setThreshold(1);

        model = (SingkongStekPanjangModel) getIntent().getSerializableExtra("model");
        Log.d("bajakmodel", new Gson().toJson(model));

        presenter = new FormPengamatanSingkongStekPanjangPresenter(this);
        mSubmit.setOnClickListener(this);
        mAddSample.setOnClickListener(this);
    }

    void addPlotForm() {
//        Toast.makeText(this, "cek", Toast.LENGTH_SHORT).show();

        //Tidak boleh kosong
        if (mLokasi.getText().toString().equals("")) {
            Toast.makeText(this, "Lokasi tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mMandorBibit.getText().toString().equals("")) {
            Toast.makeText(this, "Mandor Bibit tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mUpdatePeta.getText().toString().equals("")) {
            Toast.makeText(this, "Update Peta tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mJenisBibit.getText().toString().equals("")) {
            Toast.makeText(this, "Jenis Bibit tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mPlot.getText().toString().equals("")) {
            Toast.makeText(this, "Nomor Plot tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mLuasPlot.getText().toString().equals("")) {
            Toast.makeText(this, "Luar Plot tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mKodeUnit.getText().toString().equals("")) {
            Toast.makeText(this, "Kode Unit tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mJumlahBibitIkatDiSpk.getText().toString().equals("")) {
            Toast.makeText(this, "Jumlah Bibit di SPK tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mRealBibitIkat.getText().toString().equals("")) {
            Toast.makeText(this, "Jumlah Bibit Real tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mBibitNormal.getText().toString().equals("")) {
            Toast.makeText(this, "Batang Normal tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mBibitAfkir.getText().toString().equals("")) {
            Toast.makeText(this, "Batang Afkir tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mJumlahKelilingBatangMasukStandar.getText().toString().equals("")) {
            Toast.makeText(this, "Keliling Batang Masuk Standar tidak boleh kosong", Toast.LENGTH_SHORT).show();
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
        rowView = inflater.inflate(R.layout.data_singkongbibitstekpanjang_sample_field, null);
//        final LinearLayout containerSampleData = view.findViewById(R.id.containerSampleData);

        final TextView noSample = rowView.findViewById(R.id.mNoSample);
        final TextView plot = rowView.findViewById(R.id.mPlot);
        final TextView luasplot = rowView.findViewById(R.id.mLuasPlot);

        final TextView kode_unit = rowView.findViewById(R.id.mKodeUnit);
        final TextView spkikat = rowView.findViewById(R.id.mJumlahBibitIkatDiSpk);
        final TextView realikat = rowView.findViewById(R.id.mRealBibitIkat);

        final TextView bibitnormal = rowView.findViewById(R.id.mBibitNormal);
        final TextView bibitafkir = rowView.findViewById(R.id.mBibitAfkir);
        final TextView kelilingbatang = rowView.findViewById(R.id.mJumlahKelilingBatangMasukStandar);
        final TextView bawah1 = rowView.findViewById(R.id.mKelilingBibitBawah1);
        final TextView bawah2 = rowView.findViewById(R.id.mKelilingBibitBawah2);
        final TextView bawah3 = rowView.findViewById(R.id.mKelilingBibitBawah3);
        final TextView bawah4 = rowView.findViewById(R.id.mKelilingBibitBawah4);
        final TextView bawah5 = rowView.findViewById(R.id.mKelilingBibitBawah5);
        final TextView bawah6 = rowView.findViewById(R.id.mKelilingBibitBawah6);
        final TextView bawah7 = rowView.findViewById(R.id.mKelilingBibitBawah7);
        final TextView bawah8 = rowView.findViewById(R.id.mKelilingBibitBawah8);
        final TextView bawah9 = rowView.findViewById(R.id.mKelilingBibitBawah9);
        final TextView bawah10 = rowView.findViewById(R.id.mKelilingBibitBawah10);

        final TextView atas1 = rowView.findViewById(R.id.mKelilingBibitAtas1);
        final TextView atas2 = rowView.findViewById(R.id.mKelilingBibitAtas2);
        final TextView atas3 = rowView.findViewById(R.id.mKelilingBibitAtas3);
        final TextView atas4 = rowView.findViewById(R.id.mKelilingBibitAtas4);
        final TextView atas5 = rowView.findViewById(R.id.mKelilingBibitAtas5);
        final TextView atas6 = rowView.findViewById(R.id.mKelilingBibitAtas6);
        final TextView atas7 = rowView.findViewById(R.id.mKelilingBibitAtas7);
        final TextView atas8 = rowView.findViewById(R.id.mKelilingBibitAtas8);
        final TextView atas9 = rowView.findViewById(R.id.mKelilingBibitAtas9);
        final TextView atas10 = rowView.findViewById(R.id.mKelilingBibitAtas10);

        luasplot.setText(mLuasPlot.getText().toString());
        kode_unit.setText(mKodeUnit.getText().toString());
        spkikat.setText(mJumlahBibitIkatDiSpk.getText().toString());
        realikat.setText(mRealBibitIkat.getText().toString());
        bibitnormal.setText(mBibitNormal.getText().toString());
        bibitafkir.setText(mBibitAfkir.getText().toString());
        kelilingbatang.setText(mJumlahKelilingBatangMasukStandar.getText().toString());
        bawah1.setText(mKelilingBibitBawah1.getText().toString());
        bawah2.setText(mKelilingBibitBawah2.getText().toString());
        bawah3.setText(mKelilingBibitBawah3.getText().toString());
        bawah4.setText(mKelilingBibitBawah4.getText().toString());
        bawah5.setText(mKelilingBibitBawah5.getText().toString());
        bawah6.setText(mKelilingBibitBawah6.getText().toString());
        bawah7.setText(mKelilingBibitBawah7.getText().toString());
        bawah8.setText(mKelilingBibitBawah8.getText().toString());
        bawah9.setText(mKelilingBibitBawah9.getText().toString());
        bawah10.setText(mKelilingBibitBawah10.getText().toString());

        atas1.setText(mKelilingBibitAtas1.getText().toString());
        atas2.setText(mKelilingBibitAtas2.getText().toString());
        atas3.setText(mKelilingBibitAtas3.getText().toString());
        atas4.setText(mKelilingBibitAtas4.getText().toString());
        atas5.setText(mKelilingBibitAtas5.getText().toString());
        atas6.setText(mKelilingBibitAtas6.getText().toString());
        atas7.setText(mKelilingBibitAtas7.getText().toString());
        atas8.setText(mKelilingBibitAtas8.getText().toString());
        atas9.setText(mKelilingBibitAtas9.getText().toString());
        atas10.setText(mKelilingBibitAtas10.getText().toString());


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

        String selectedDateString = mUpdatePeta.getText().toString();
        sampleModel.setUpdatePeta(selectedDateString);
        noSample.setText(String.valueOf(nextSampleNumber));
        sampleModel.setNo_sample(noSample.getText().toString());
        sampleModel.setNo_spk(model.getNO_SPK());

        sampleModel.setLokasi(mLokasi.getText().toString());
        sampleModel.setMandorBibit(mMandorBibit.getText().toString());
        sampleModel.setJenisBibit(mJenisBibit.getText().toString());
//        sampleModel.setJumlahBibitDiSpk(Integer.parseInt(mJumlahBibitDiSpk.getText().toString()));

        sampleModel.setPLOT(Integer.parseInt(mPlot.getText().toString()));
        sampleModel.setLuasPlot((float) Integer.parseInt(mLuasPlot.getText().toString()));
        sampleModel.setKodeUnit(mKodeUnit.getText().toString());

        sampleModel.setJumlahBibitIkatDiSpk(Integer.parseInt(mJumlahBibitIkatDiSpk.getText().toString()));
        sampleModel.setRealBibitIkat(Integer.parseInt(mRealBibitIkat.getText().toString()));

        sampleModel.setBibitNormal(Integer.parseInt(mBibitNormal.getText().toString()));
        sampleModel.setBibitAfkir(Integer.parseInt(mBibitAfkir.getText().toString()));

        sampleModel.setJumlahKelilingBatangMasukStandar(Integer.parseInt(mJumlahKelilingBatangMasukStandar.getText().toString()));

        sampleModel.setKelilingBibitBawah1(Integer.parseInt(mKelilingBibitBawah1.getText().toString()));
        sampleModel.setKelilingBibitBawah2(Integer.parseInt(mKelilingBibitBawah2.getText().toString()));
        sampleModel.setKelilingBibitBawah3(Integer.parseInt(mKelilingBibitBawah3.getText().toString()));
        sampleModel.setKelilingBibitBawah4(Integer.parseInt(mKelilingBibitBawah4.getText().toString()));
        sampleModel.setKelilingBibitBawah5(Integer.parseInt(mKelilingBibitBawah5.getText().toString()));
        sampleModel.setKelilingBibitBawah6(Integer.parseInt(mKelilingBibitBawah6.getText().toString()));
        sampleModel.setKelilingBibitBawah7(Integer.parseInt(mKelilingBibitBawah7.getText().toString()));
        sampleModel.setKelilingBibitBawah8(Integer.parseInt(mKelilingBibitBawah8.getText().toString()));
        sampleModel.setKelilingBibitBawah9(Integer.parseInt(mKelilingBibitBawah9.getText().toString()));
        sampleModel.setKelilingBibitBawah10(Integer.parseInt(mKelilingBibitBawah10.getText().toString()));

        sampleModel.setKelilingBibitAtas1(Integer.parseInt(mKelilingBibitAtas1.getText().toString()));
        sampleModel.setKelilingBibitAtas2(Integer.parseInt(mKelilingBibitAtas2.getText().toString()));
        sampleModel.setKelilingBibitAtas3(Integer.parseInt(mKelilingBibitAtas3.getText().toString()));
        sampleModel.setKelilingBibitAtas4(Integer.parseInt(mKelilingBibitAtas4.getText().toString()));
        sampleModel.setKelilingBibitAtas5(Integer.parseInt(mKelilingBibitAtas5.getText().toString()));
        sampleModel.setKelilingBibitAtas6(Integer.parseInt(mKelilingBibitAtas6.getText().toString()));
        sampleModel.setKelilingBibitAtas7(Integer.parseInt(mKelilingBibitAtas7.getText().toString()));
        sampleModel.setKelilingBibitAtas8(Integer.parseInt(mKelilingBibitAtas8.getText().toString()));
        sampleModel.setKelilingBibitAtas9(Integer.parseInt(mKelilingBibitAtas9.getText().toString()));
        sampleModel.setKelilingBibitAtas10(Integer.parseInt(mKelilingBibitAtas10.getText().toString()));

        sampleModel.setKeterangan(mKeterangan.getText().toString());

//        dataSample.add(sampleModel);
        dataSample.add(sampleModel);
//        index +=1 ;
        containerPlotData.addView(rowView, 0);

        //Hapus Saat Apply
        Toast.makeText(this, "Data Sudah Ditambahkan", Toast.LENGTH_SHORT).show();
        mKelilingBibitBawah1.getText().clear();
        mKelilingBibitBawah2.getText().clear();
        mKelilingBibitBawah3.getText().clear();
        mKelilingBibitBawah4.getText().clear();
        mKelilingBibitBawah5.getText().clear();
        mKelilingBibitBawah6.getText().clear();
        mKelilingBibitBawah7.getText().clear();
        mKelilingBibitBawah8.getText().clear();
        mKelilingBibitBawah9.getText().clear();
        mKelilingBibitBawah10.getText().clear();

        mKelilingBibitAtas1.getText().clear();
        mKelilingBibitAtas2.getText().clear();
        mKelilingBibitAtas3.getText().clear();
        mKelilingBibitAtas4.getText().clear();
        mKelilingBibitAtas5.getText().clear();
        mKelilingBibitAtas6.getText().clear();
        mKelilingBibitAtas7.getText().clear();
        mKelilingBibitAtas8.getText().clear();
        mKelilingBibitAtas9.getText().clear();
        mKelilingBibitAtas10.getText().clear();
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
        sampleModel.setKeterangan(mKeterangan.getText().toString());
        plotModel.setSAMPLE(dataSample);
        model.setLOKASI(mLokasi.getText().toString());
        model.setDATA(dataPlot);

        Log.d("dataBody", new Gson().toJson(model));
        presenter.createPengamatan(model);
    }

    @Override
    public void onCreateSuccess(String rm) {
        // Tambahkan format timestamp
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        String currentTime = sdf.format(new Date()); // Mendapatkan waktu saat ini

        // Tambahkan waktu ke dalam pesan
        String message = "Bibit Singkong Stek Panjang"
                + ", Lokasi: " + mLokasi.getText().toString()
                + "\nTanggal: " + generateTglSekarang()
                + "\nWaktu: " + currentTime;

        SweetDialogs.commonSuccessWithIntent(this, message, string -> {
            goToListPengamatan(this);
        });
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
    public void onNetworkError(String cause ,String data) {
        SQLiteHelper dbHelper = new SQLiteHelper(this);
        dbHelper.saveChopperData(data,model.getNO_SPK());  // Assuming 'data' is a JSON string

        Log.d("Saved data", "Data saved to SQLite: " + data);

        // Show a dialog indicating that the data has been saved offline
        SweetDialogs.commonError(this, App.getApplication().getString(R.string.notif_offline_mode), false);
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

    private static final String [] mJenisBibit2 = new String[]{
            "DN9" , "GRD" , "KSS" , "SCI" , "UJ5"
    };

}