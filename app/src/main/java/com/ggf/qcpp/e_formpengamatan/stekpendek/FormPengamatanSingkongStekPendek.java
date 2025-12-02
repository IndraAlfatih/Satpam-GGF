package com.ggf.qcpp.e_formpengamatan.stekpendek;

import static com.ggf.qcpp.utils.Utils.generateTglSekarang;
import static com.ggf.qcpp.utils.Utils.goToListPengamatan;
import static com.ggf.qcpp.utils.Utils.now;

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
import com.ggf.qcpp.Prefs;
import com.ggf.qcpp.R;
import com.ggf.qcpp.b_account.model.LoginResponse;
import com.ggf.qcpp.e_formpengamatan.stekpendek.model.PlotModel;
import com.ggf.qcpp.e_formpengamatan.stekpendek.model.SampleModel;
import com.ggf.qcpp.e_formpengamatan.stekpendek.model.SingkongStekPendekModel;
import com.ggf.qcpp.e_formpengamatan.tidakterseset.FormPengamatanBonggolTidakTersesetPresenter;
import com.ggf.qcpp.e_formpengamatan.tidakterseset.model.BonggolTidakTersesetModel;
import com.ggf.qcpp.ui.SweetDialogs;
import com.ggf.qcpp.utils.GsonHelper;
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

public class FormPengamatanSingkongStekPendek extends AppCompatActivity implements View.OnClickListener, IFormPengamatanSingkongStekPendekView {

    LoginResponse mProfile ;

    @BindView(R.id.mSubmit)
    Button mSubmit;

    @BindView(R.id.mAddSample)
    ImageView mAddSample;
    @BindView(R.id.mKeterangan)
    EditText mKeterangan;

    @BindView(R.id.autoJenisBibit)
    AutoCompleteTextView autoJenisBibit;

    @BindView(R.id.containerPlotData)
    LinearLayout containerPlotData;
    @BindView(R.id.mLokasi)
    EditText mLokasi;
    @BindView(R.id.mPlot)
    EditText mPlot;
    @BindView(R.id.mLuasPlot)
    EditText mLuasPlot;
    @BindView(R.id.mUpdatePeta)
    EditText mUpdatePeta;

    @BindView(R.id.mJumlahSample)
    EditText mJumlahSample;

    @BindView(R.id.mBibitSPK)
    EditText mBibitSPK;

    @BindView(R.id.mMandorBibit)
    EditText mMandorBibit;

    @BindView(R.id.mBibitNormal)
    EditText mBibitNormal;

    @BindView(R.id.mBibitAfkir)
    EditText mBibitAfkir;

    @BindView(R.id.mPendek1)
    EditText mPendek1;
    @BindView(R.id.mPendek2)
    EditText mPendek2;
    @BindView(R.id.mPendek3)
    EditText mPendek3;
    @BindView(R.id.mPendek4)
    EditText mPendek4;
    @BindView(R.id.mPendek5)
    EditText mPendek5;
    @BindView(R.id.mPendek6)
    EditText mPendek6;
    @BindView(R.id.mPendek7)
    EditText mPendek7;
    @BindView(R.id.mPendek8)
    EditText mPendek8;
    @BindView(R.id.mPendek9)
    EditText mPendek9;
    @BindView(R.id.mPendek10)
    EditText mPendek10;
    @BindView(R.id.mPendek11)
    EditText mPendek11;
    @BindView(R.id.mPendek12)
    EditText mPendek12;
    @BindView(R.id.mPendek13)
    EditText mPendek13;
    @BindView(R.id.mPendek14)
    EditText mPendek14;

    @BindView(R.id.autoAlatPotong)
    AutoCompleteTextView autoAlatPotong;

    @BindView(R.id.mUsername)
    TextView mUsername;

    @BindView(R.id.mNow)
    TextView mNow;

    View rowView;
    View rowViewPlot;
    View viewnya = null ;
    String plot = "0" ;
    SingkongStekPendekModel model;

    int index = 1 ;

    List<PlotModel> dataPlot = new ArrayList<>() ;
    List<SampleModel> dataSample = new ArrayList<>() ;

    SweetAlertDialog sweetAlertDialog;
    SampleModel sampleModel = null;
    PlotModel plotModel = null ;
    FormPengamatanSingkongStekPendekPresenter presenter;

    private ScrollView scrollView;
    private View contentView;
    private View focusedView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form_pengamatan_singkong_stek_pendek);

        ButterKnife.bind(this);
        presenter = new FormPengamatanSingkongStekPendekPresenter(this);
        model = (SingkongStekPendekModel) getIntent().getSerializableExtra("model");

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

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line , autoAlatPotong2);
        autoAlatPotong.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line , autoJenisBibit2);
        autoJenisBibit.setAdapter(adapter2);

        // Menonaktifkan input teks, tetapi dropdown masih muncul
        autoAlatPotong.setKeyListener(null);
        autoJenisBibit.setKeyListener(null);

        // Memastikan dropdown muncul meskipun tidak ada teks yang dimasukkan
        autoAlatPotong.setThreshold(1);
        autoJenisBibit.setThreshold(1);

        // Atur threshold sesuai kebutuhan (misalnya 1 untuk memulai pencarian setelah 1 karakter)
        model = (SingkongStekPendekModel) getIntent().getSerializableExtra("model");
        Log.d("bajakmodel" , new Gson().toJson(model));

        presenter = new FormPengamatanSingkongStekPendekPresenter(this);
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
        else if (autoJenisBibit.getText().toString().equals("")) {
            Toast.makeText(this, "Jenis Bibit tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mBibitSPK.getText().toString().equals("")) {
            Toast.makeText(this, "Bibit SPK tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mPlot.getText().toString().equals("")) {
            Toast.makeText(this, "No Plot tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mLuasPlot.getText().toString().equals("")) {
            Toast.makeText(this, "Luas Plot tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mBibitNormal.getText().toString().equals("")) {
            Toast.makeText(this, "Bibit Normal tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mBibitAfkir.getText().toString().equals("")) {
            Toast.makeText(this, "Bibit Afkir tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mJumlahSample.getText().toString().equals("")) {
            Toast.makeText(this, "Jumlah Sample tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (autoAlatPotong.getText().toString().equals("")) {
            Toast.makeText(this, "Alat Potong tidak boleh kosong", Toast.LENGTH_SHORT).show();
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
        rowView = inflater.inflate(R.layout.data_singkongbibitstekpendek_sample_field, null);
//        final LinearLayout containerSampleData = view.findViewById(R.id.containerSampleData);

        final TextView noSample = rowView.findViewById(R.id.mNoSample);
        final TextView plot = rowView.findViewById(R.id.mPlot);

        final TextView luas_plot = rowView.findViewById(R.id.mLuasPlot);
        final TextView jumlah_bibit_normal = rowView.findViewById(R.id.mBibitNormal);
        final TextView jumlah_bibit_afkir = rowView.findViewById(R.id.mBibitAfkir);

        final TextView jumlah_bibit_potonga_masuk_standar_15 = rowView.findViewById(R.id.mPendek1);
        final TextView jumlah_bibit_potonga_masuk_standar_16 = rowView.findViewById(R.id.mPendek2);
        final TextView jumlah_bibit_potonga_masuk_standar_17 = rowView.findViewById(R.id.mPendek3);
        final TextView jumlah_bibit_potonga_masuk_standar_18 = rowView.findViewById(R.id.mPendek4);
        final TextView jumlah_bibit_potonga_masuk_standar_19 = rowView.findViewById(R.id.mPendek5);
        final TextView jumlah_bibit_potonga_masuk_standar_20 = rowView.findViewById(R.id.mPendek6);
        final TextView jumlah_bibit_potonga_masuk_standar_21 = rowView.findViewById(R.id.mPendek7);
        final TextView jumlah_bibit_potonga_masuk_standar_22 = rowView.findViewById(R.id.mPendek8);
        final TextView jumlah_bibit_potonga_masuk_standar_23 = rowView.findViewById(R.id.mPendek9);
        final TextView jumlah_bibit_potonga_masuk_standar_24 = rowView.findViewById(R.id.mPendek10);
        final TextView jumlah_bibit_potonga_masuk_standar_25 = rowView.findViewById(R.id.mPendek11);
        final TextView jumlah_bibit_potonga_masuk_standar_26 = rowView.findViewById(R.id.mPendek12);
        final TextView jumlah_bibit_potonga_masuk_standar_27 = rowView.findViewById(R.id.mPendek13);
        final TextView jumlah_bibit_potonga_masuk_standar_28 = rowView.findViewById(R.id.mPendek14);
        final TextView jumlahsample = rowView.findViewById(R.id.mJumlahSample);


        luas_plot.setText(mLuasPlot.getText().toString());
        jumlah_bibit_normal.setText(mBibitNormal.getText().toString());
        jumlah_bibit_afkir.setText(mBibitAfkir.getText().toString());

        jumlah_bibit_potonga_masuk_standar_15.setText(mPendek1.getText().toString());
        jumlah_bibit_potonga_masuk_standar_16.setText(mPendek2.getText().toString());
        jumlah_bibit_potonga_masuk_standar_17.setText(mPendek3.getText().toString());
        jumlah_bibit_potonga_masuk_standar_18.setText(mPendek4.getText().toString());
        jumlah_bibit_potonga_masuk_standar_19.setText(mPendek5.getText().toString());
        jumlah_bibit_potonga_masuk_standar_20.setText(mPendek6.getText().toString());
        jumlah_bibit_potonga_masuk_standar_21.setText(mPendek7.getText().toString());
        jumlah_bibit_potonga_masuk_standar_22.setText(mPendek8.getText().toString());
        jumlah_bibit_potonga_masuk_standar_23.setText(mPendek9.getText().toString());
        jumlah_bibit_potonga_masuk_standar_24.setText(mPendek10.getText().toString());
        jumlah_bibit_potonga_masuk_standar_25.setText(mPendek11.getText().toString());
        jumlah_bibit_potonga_masuk_standar_26.setText(mPendek12.getText().toString());
        jumlah_bibit_potonga_masuk_standar_27.setText(mPendek13.getText().toString());
        jumlah_bibit_potonga_masuk_standar_28.setText(mJumlahSample.getText().toString());
        jumlahsample.setText(mPendek14.getText().toString());

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
        sampleModel.setUpdate_peta(selectedDateString);
        noSample.setText(String.valueOf(nextSampleNumber));
        sampleModel.setNo_sample(noSample.getText().toString());
        sampleModel.setNo_spk(model.getNO_SPK());
        sampleModel.setLokasi(mLokasi.getText().toString());
        sampleModel.setMandor_bibit(mMandorBibit.getText().toString());
        sampleModel.setJenis_bibit(autoJenisBibit.getText().toString());
        sampleModel.setJumlah_bibit_di_spk(Integer.parseInt(mBibitSPK.getText().toString()));

        sampleModel.setPLOT(Integer.parseInt(mPlot.getText().toString()));
        sampleModel.setLuas_plot((float) Integer.parseInt(mLuasPlot.getText().toString()));
        sampleModel.setJumlah_bibit_normal(Integer.parseInt(mBibitNormal.getText().toString()));
        sampleModel.setJumlah_bibit_afkir(Integer.parseInt(mBibitAfkir.getText().toString()));

        sampleModel.setJumlah_bibit_potonga_masuk_standar_15(Float.parseFloat(mPendek1.getText().toString()));
        sampleModel.setJumlah_bibit_potonga_masuk_standar_16(Float.parseFloat(mPendek2.getText().toString()));
        sampleModel.setJumlah_bibit_potonga_masuk_standar_17(Float.parseFloat(mPendek3.getText().toString()));
        sampleModel.setJumlah_bibit_potonga_masuk_standar_18(Float.parseFloat(mPendek4.getText().toString()));
        sampleModel.setJumlah_bibit_potonga_masuk_standar_19(Float.parseFloat(mPendek5.getText().toString()));
        sampleModel.setJumlah_bibit_potonga_masuk_standar_20(Float.parseFloat(mPendek6.getText().toString()));
        sampleModel.setJumlah_bibit_potonga_masuk_standar_21(Float.parseFloat(mPendek7.getText().toString()));
        sampleModel.setJumlah_bibit_potonga_masuk_standar_22(Float.parseFloat(mPendek8.getText().toString()));
        sampleModel.setJumlah_bibit_potonga_masuk_standar_23(Float.parseFloat(mPendek9.getText().toString()));
        sampleModel.setJumlah_bibit_potonga_masuk_standar_24(Float.parseFloat(mPendek10.getText().toString()));
        sampleModel.setJumlah_bibit_potonga_masuk_standar_25(Float.parseFloat(mPendek11.getText().toString()));
        sampleModel.setJumlah_bibit_potonga_masuk_standar_26(Float.parseFloat(mPendek12.getText().toString()));
        sampleModel.setJumlah_bibit_potonga_masuk_standar_27(Float.parseFloat(mPendek13.getText().toString()));
        sampleModel.setJumlah_bibit_potonga_masuk_standar_28(Float.parseFloat(mPendek14.getText().toString()));

        sampleModel.setJumlahsample(Integer.parseInt(mJumlahSample.getText().toString()));

        sampleModel.setAlat_potong_bibit(autoAlatPotong.getText().toString());
        sampleModel.setKeterangan(mKeterangan.getText().toString());

//        dataSample.add(sampleModel);
        dataSample.add(sampleModel);
//        index +=1 ;
        containerPlotData.addView(rowView, 0);

        //Hapus Saat Apply
        Toast.makeText(this, "Data Sudah Ditambahkan", Toast.LENGTH_SHORT).show();

        mPendek1.getText().clear();
        mPendek2.getText().clear();
        mPendek3.getText().clear();
        mPendek4.getText().clear();
        mPendek5.getText().clear();
        mPendek6.getText().clear();
        mPendek7.getText().clear();
        mPendek8.getText().clear();
        mPendek9.getText().clear();
        mPendek10.getText().clear();
        mPendek11.getText().clear();
        mPendek12.getText().clear();
        mPendek13.getText().clear();
        mPendek14.getText().clear();

        autoAlatPotong.getText().clear();

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
//        sampleModel.setAlat_potong_bibit(autoAlatPotong.getText().toString());
        sampleModel.setKeterangan(mKeterangan.getText().toString());
        plotModel.setSAMPLE(dataSample);
        model.setLOKASI(mLokasi.getText().toString());
        model.setDATA(dataPlot);
        Toast.makeText(this, autoAlatPotong.getText().toString(), Toast.LENGTH_SHORT).show();
        Log.d("dataBody", new Gson().toJson(model));
        presenter.createPengamatan(model);
    }

    @Override
    public void onCreateSuccess(String rm) {
        // Tambahkan format timestamp
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        String currentTime = sdf.format(new Date()); // Mendapatkan waktu saat ini

        // Tambahkan waktu ke dalam pesan
        String message = "Bibit Singkong Stek Pendek"
                + "\nTanggal: " + generateTglSekarang()
                + "\nWaktu: " + currentTime
                + "\nLokasi: " + mLokasi.getText().toString();

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
    public void onNetworkError(String cause) {
        Log.e("errornya", cause);
//        SweetDialogs.endpointError(this);
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

    private static final String [] autoAlatPotong2 = new String[]{
            "Golok" , "Gergaji"
    };

    private static final String [] autoJenisBibit2 = new String[]{
            "DN9" , "GRD" , "KSS" , "SCI" , "UJ5"
    };

}