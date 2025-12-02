package com.ggf.qcpp.e_formpengamatan.sesetbonggol;

import static com.ggf.qcpp.utils.Utils.generateTglSekarang;
import static com.ggf.qcpp.utils.Utils.goToListPengamatan;
import static com.ggf.qcpp.utils.Utils.parStringDefault;

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
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ggf.qcpp.R;
import com.ggf.qcpp.e_formpengamatan.sesetbonggol.model.SesetBonggolModel;
import com.ggf.qcpp.e_formpengamatan.sesetbonggol.model.PlotModel;
import com.ggf.qcpp.e_formpengamatan.sesetbonggol.model.SampleModel;
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

public class FormPengamatanSesetBonggol extends AppCompatActivity implements IFormPengamatanSesetBonggolView, View.OnClickListener {

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
    @BindView(R.id.mKeterangan)
    EditText mKeterangan;
    @BindView(R.id.autoWilayah)
    AutoCompleteTextView autoWilayah;

    @BindView(R.id.mLuasNetto)
    EditText mLuasNetto;

    @BindView(R.id.mUpdatePeta)
    EditText mUpdatePeta;

    @BindView(R.id.mTanggalPanenRampet)
    EditText mTanggalPanenRampet;

    @BindView(R.id.autoStatusRC)
    AutoCompleteTextView autoStatusRC;



    @BindView(R.id.mKetinggianSampah)
    EditText mKetinggianSampah;

    @BindView(R.id.mSampleBonggol)
    EditText mSampleBonggol;

    @BindView(R.id.mKupasanSTD)
    EditText mKupasanSTD;

    @BindView(R.id.mKupasanRata)
    EditText mKupasanRata;

    @BindView(R.id.mPotonganSTD)
    EditText mPotonganSTD;

    @BindView(R.id.mKondisiSTD)
    EditText mKondisiSTD;

    @BindView(R.id.mMuatan)
    AutoCompleteTextView mMuatan;


    View rowView;
    View rowViewPlot;
    View viewnya = null;
    String plot = "0";
    SesetBonggolModel model;

    List<PlotModel> dataPlot = new ArrayList<>();
    List<SampleModel> dataSample = new ArrayList<>();

    SweetAlertDialog sweetAlertDialog;
    SampleModel sampleModel = null;
    PlotModel plotModel = null;
    FormPengamatanSesetBonggolPresenter presenter;
    int index = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form_pengamatan_bonggol_terseset);

        ButterKnife.bind(this);
        presenter = new FormPengamatanSesetBonggolPresenter(this);
        model = (SesetBonggolModel) getIntent().getSerializableExtra("model");

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

        Calendar calendar2 = Calendar.getInstance();
        int year2 = calendar2.get(Calendar.YEAR);
        int month2 = calendar2.get(Calendar.MONTH);
        int day2 = calendar2.get(Calendar.DAY_OF_MONTH);
        mTanggalPanenRampet.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog2 = new DatePickerDialog(
                    this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        // Tambahkan 1 ke bulan (karena bulan dimulai dari 0)
                        String selectedDate = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay;
                        mTanggalPanenRampet.setText(selectedDate); // Set tanggal di EditText
                    },
                    year2, month2, day2);

            datePickerDialog2.show(); // Tampilkan dialog
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, autoStatusRC2);
        autoStatusRC.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, autoWilayah2);
        autoWilayah.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mMuatan2);
        mMuatan.setAdapter(adapter3);

        mMuatan.setOnClickListener(this);
        autoStatusRC.setOnClickListener(this);
        autoWilayah.setOnClickListener(this);
        mSubmit.setOnClickListener(this);
        mAddSample.setOnClickListener(this);
    }

    void addPlotForm() {
//        Toast.makeText(this, "cek", Toast.LENGTH_SHORT).show();
        //Tidak boleh kosong
        if (mLokasi.getText().toString().equals("")) {
            Toast.makeText(this, "Lokasi tidak boleh kosong", Toast.LENGTH_SHORT).show();
//        } else if (mLuasAktif.getText().toString().equals("")) {
//            Toast.makeText(this, "Luas Aktif tidak boleh kosong", Toast.LENGTH_SHORT).show();
//        } else if (automusim.getText().toString().equals("")) {
//            Toast.makeText(this, "Musim tidak boleh kosong", Toast.LENGTH_SHORT).show();
//        } else if (autostatuslokasi.getText().toString().equals("")) {
//            Toast.makeText(this, "Status Lokasi tidak boleh kosong", Toast.LENGTH_SHORT).show();
//        } else if (autoexcomodity.getText().toString().equals("")) {
//            Toast.makeText(this, "Ex Comodity tidak boleh kosong", Toast.LENGTH_SHORT).show();
//        } else if (autoWilayah.getText().toString().equals("")) {
//            Toast.makeText(this, "Wilayah tidak boleh kosong", Toast.LENGTH_SHORT).show();
//        }else if (mPlot.getText().toString().equals("")) {
//            Toast.makeText(this, "No Plot tidak boleh kosong", Toast.LENGTH_SHORT).show();
//        } else if (mLuasPlot.getText().toString().equals("")) {
//            Toast.makeText(this, "Luas Plot tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }else {
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
        rowView = inflater.inflate(R.layout.data_bonggolterseset_sample_field, null);

        TextView no_sample = rowView.findViewById(R.id.mNoSample);
        TextView plot = rowView.findViewById(R.id.mPlot);
        TextView luasPlot = rowView.findViewById(R.id.mLuasPlot);
        TextView KetinggianSampah = rowView.findViewById(R.id.mKetinggianSampah);
        TextView TotalSample = rowView.findViewById(R.id.mSampleBonggol);
        TextView KupasanSTD = rowView.findViewById(R.id.mKupasanSTD);
        TextView PotonganSTD = rowView.findViewById(R.id.mPotonganSTD);
        TextView KondisiSTD = rowView.findViewById(R.id.mKondisiSTD);
        TextView kondisimuatan = rowView.findViewById(R.id.mMuatan);

        plot.setText(mPlot.getText().toString());
        luasPlot.setText(mLuasPlot.getText().toString());
        KetinggianSampah.setText(mKetinggianSampah.getText().toString());
        TotalSample.setText(mSampleBonggol.getText().toString());
        KupasanSTD.setText(mKupasanSTD.getText().toString());
        PotonganSTD.setText(mPotonganSTD.getText().toString());
        KondisiSTD.setText(mKondisiSTD.getText().toString());
        kondisimuatan.setText(mMuatan.getText().toString());


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
        String selectedDateString2 = mTanggalPanenRampet.getText().toString();
        no_sample.setText(String.valueOf(nextSampleNumber));

        sampleModel.setNo_spk(model.getNO_SPK());
        sampleModel.setLokasi(mLokasi.getText().toString());
        sampleModel.setUpdate_peta(selectedDateString);
        sampleModel.setTanggal_panen_rampet(selectedDateString2);
        sampleModel.setNo_sample(Integer.parseInt(no_sample.getText().toString()));
        sampleModel.setWil(autoWilayah.getText().toString());
        sampleModel.setStatus_rc(autoStatusRC.getText().toString());
        sampleModel.setPlot(Integer.parseInt(mPlot.getText().toString()));
        sampleModel.setLuas_plot(Float.parseFloat(mLuasPlot.getText().toString()));
        sampleModel.setNetto(mLuasNetto.getText().toString());
        sampleModel.setKetinggian_sampah(Float.parseFloat(mKetinggianSampah.getText().toString()));
        sampleModel.setJumlah_sample_masuk_standar_kebersihan_kupasan(Float.parseFloat(mKupasanSTD.getText().toString()));
        sampleModel.setRerata_panjang_bonggol(Float.parseFloat(mKupasanRata.getText().toString()));
        sampleModel.setJumlah_sample_masuk_standar_kondisi_bonggol(Float.parseFloat(mKondisiSTD.getText().toString()));

        sampleModel.setKondisimuatan(mMuatan.getText().toString());
        sampleModel.setKeterangan(mKeterangan.getText().toString());
//        sampleModel.setKeterangan(parStringDefault(mKeterangan.getText().toString(),"-"));
//        sampleModel.setKomposit_a4();


        dataSample.add(sampleModel);
        Log.d("datanyanih", new Gson().toJson(model));
        containerPlotData.addView(rowView, 0);

        //Hapus Saat Apply
        Toast.makeText(this, "Data Sudah Ditambahkan", Toast.LENGTH_SHORT).show();

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
        sampleModel.setKeterangan(mKeterangan.getText().toString());
        sampleModel.setKondisimuatan(mMuatan.getText().toString());
        plotModel.setSAMPLE(dataSample);
        model.setLOKASI(mLokasi.getText().toString());
        model.setWILAYAH(autoWilayah.getText().toString());
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
        String message = "Seset Bonggol"
                + ", Lokasi: " + mLokasi.getText().toString()
                + "\nTanggal: " + generateTglSekarang()
                + "\nWaktu: " + currentTime;

        SweetDialogs.commonSuccessWithIntent(this, message, string -> {
            goToListPengamatan(this);
        });
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
        SweetDialogs.endpointError(this);
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

    private static final String[] autoStatusRC2 = new String[]{
            "NSSC", "NSBB"
    };

    private static final String[] mMuatan2 = new String[]{
            "0", "1"
    };

    private static final String[] autoWilayah2 = new String[]{
            "AW01", "AW02", "AW03", "AW04", "AW05", "AW06", "AW07", "AW08", "AW09", "AW10","AW11", "AW12", "AW13", "AW14", "AW15", "AW16", "AW17", "AW18", "AW19", "AW20", "AW21", "AW22", "AW23"
    };


}