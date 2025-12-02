package com.ggf.qcpp.e_formpengamatan.tanamsingkong;

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

import com.ggf.qcpp.R;
import com.ggf.qcpp.e_formpengamatan.tanamsingkong.model.PlotModel;
import com.ggf.qcpp.e_formpengamatan.tanamsingkong.model.SampleModel;
import com.ggf.qcpp.e_formpengamatan.tanamsingkong.model.TanamSingkongModel;
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

public class FormPengamatanTanamSingkong extends AppCompatActivity implements View.OnClickListener, IFormPengamatanTanamSingkongView {

    @BindView(R.id.mSubmit)
    Button mSubmit;
    @BindView(R.id.mAddSample)
    ImageView mAddSample;
    @BindView(R.id.containerPlotData)
    LinearLayout containerPlotData;
    @BindView(R.id.mLokasi)
    EditText mLokasi;

    @BindView(R.id.mUpdatePeta)
    EditText mUpdatePeta;

    @BindView(R.id.mJenisBibit)
    AutoCompleteTextView mJenisBibit;

    @BindView(R.id.mPlot)
    EditText mPlot;

    @BindView(R.id.mLuasPlot)
    EditText mLuasPlot;

    @BindView(R.id.mKeterangan)
    EditText mKeterangan;

    //batas

    @BindView(R.id.mKualitasJtdb1)
    EditText mKualitasJtdb1;

    @BindView(R.id.mKualitasJtdb2)
    EditText mKualitasJtdb2;

    @BindView(R.id.mKualitasJtdb3)
    EditText mKualitasJtdb3;

    @BindView(R.id.mKualitasJtdb4)
    EditText mKualitasJtdb4;

    @BindView(R.id.mKualitasJtdb5)
    EditText mKualitasJtdb5;

    @BindView(R.id.mKualitasJtdb6)
    EditText mKualitasJtdb6;

    @BindView(R.id.mKualitasJtdb7)
    EditText mKualitasJtdb7;

    @BindView(R.id.mKualitasJtdb8)
    EditText mKualitasJtdb8;

    @BindView(R.id.mKualitasJtdb9)
    EditText mKualitasJtdb9;

    @BindView(R.id.mKualitasJtdb10)
    EditText mKualitasJtdb10;

    @BindView(R.id.mTotalSampleJtdb)
    EditText mTotalSampleJtdb;

    @BindView(R.id.mJumlahMasukStandarJtdb)
    EditText mJumlahMasukStandarJtdb;

    @BindView(R.id.mKualitasJtab1)
    EditText mKualitasJtab1;

    @BindView(R.id.mKualitasJtab2)
    EditText mKualitasJtab2;

    @BindView(R.id.mKualitasJtab3)
    EditText mKualitasJtab3;

    @BindView(R.id.mKualitasJtab4)
    EditText mKualitasJtab4;

    @BindView(R.id.mKualitasJtab5)
    EditText mKualitasJtab5;

    @BindView(R.id.mKualitasJtab6)
    EditText mKualitasJtab6;

    @BindView(R.id.mKualitasJtab7)
    EditText mKualitasJtab7;

    @BindView(R.id.mKualitasJtab8)
    EditText mKualitasJtab8;

    @BindView(R.id.mKualitasJtab9)
    EditText mKualitasJtab9;

    @BindView(R.id.mKualitasJtab10)
    EditText mKualitasJtab10;

    @BindView(R.id.mTotalSampleJtab)
    EditText mTotalSampleJtab;

    @BindView(R.id.mJumlahMasukStandarJtab)
    EditText mJumlahMasukStandarJtab;

    @BindView(R.id.mKedalamanTanam4)
    EditText mKedalamanTanam4;

    @BindView(R.id.mKedalamanTanam5)
    EditText mKedalamanTanam5;

    @BindView(R.id.mKedalamanTanam6)
    EditText mKedalamanTanam6;

    @BindView(R.id.mKedalamanTanam7)
    EditText mKedalamanTanam7;

    @BindView(R.id.mKedalamanTanam8)
    EditText mKedalamanTanam8;

    @BindView(R.id.mKedalamanTanam9)
    EditText mKedalamanTanam9;

    @BindView(R.id.mKedalamanTanam10)
    EditText mKedalamanTanam10;

    @BindView(R.id.mKedalamanTanam11)
    EditText mKedalamanTanam11;

    @BindView(R.id.mKedalamanTanam12)
    EditText mKedalamanTanam12;

    @BindView(R.id.mKedalamanTanam13)
    EditText mKedalamanTanam13;

    @BindView(R.id.mKedalamanTanam14)
    EditText mKedalamanTanam14;

    @BindView(R.id.mTotalSampleKedalaman)
    EditText mTotalSampleKedalaman;

    @BindView(R.id.mJumlahMasukStandarKedalaman)
    EditText mJumlahMasukStandarKedalaman;

    @BindView(R.id.mYa)
    EditText mYa;

    @BindView(R.id.mTidak)
    EditText mTidak;

    @BindView(R.id.mPopulasi)
    EditText mPopulasi;

    View rowView;
    View rowViewPlot;
    View viewnya = null;
    String plot = "0";
    TanamSingkongModel model;

    int index = 1;

    List<PlotModel> dataPlot = new ArrayList<>();
    List<SampleModel> dataSample = new ArrayList<>();

    SweetAlertDialog sweetAlertDialog;
    SampleModel sampleModel = null;
    PlotModel plotModel = null;
    FormPengamatanTanamSingkongPresenter presenter;

    private ScrollView scrollView;
    private View contentView;
    private View focusedView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form_pengamatan_singkong_tanam);

        ButterKnife.bind(this);
        presenter = new FormPengamatanTanamSingkongPresenter(this);
        model = (TanamSingkongModel) getIntent().getSerializableExtra("model");

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

        model = (TanamSingkongModel) getIntent().getSerializableExtra("model");
        Log.d("bajakmodel", new Gson().toJson(model));

        presenter = new FormPengamatanTanamSingkongPresenter(this);
        mSubmit.setOnClickListener(this);
        mAddSample.setOnClickListener(this);
    }

    void addPlotForm() {
//        Toast.makeText(this, "cek", Toast.LENGTH_SHORT).show();

        //Tidak boleh kosong
        if (mLokasi.getText().toString().equals("")) {
            Toast.makeText(this, "Lokasi tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
//        else if (automusim.getText().toString().equals("")) {
//            Toast.makeText(this, "Musim tidak boleh kosong", Toast.LENGTH_SHORT).show();
//        }
//        else if (statuspengamatan.getText().toString().equals("")) {
//            Toast.makeText(this, "Status Pengamatan tidak boleh kosong", Toast.LENGTH_SHORT).show();
//        }
//        else if (autoComodityBajak.getText().toString().equals("")) {
//            Toast.makeText(this, "Comodity Bajak tidak boleh kosong", Toast.LENGTH_SHORT).show();
//        }
//        else if (autoJenisBajak.getText().toString().equals("")) {
//            Toast.makeText(this, "Jenis Bajak tidak boleh kosong", Toast.LENGTH_SHORT).show();
//        }
//        else if (autoWilayah.getText().toString().equals("")) {
//            Toast.makeText(this, "Wilayah tidak boleh kosong", Toast.LENGTH_SHORT).show();
//        }
//        else if (mPlot.getText().toString().equals("")) {
//            Toast.makeText(this, "No Plot tidak boleh kosong", Toast.LENGTH_SHORT).show();
//        }
//        else if (mLuasPlot.getText().toString().equals("")) {
//            Toast.makeText(this, "Luas Plot tidak boleh kosong", Toast.LENGTH_SHORT).show();
//        }
//        else if (mKedalaman.getText().toString().equals("")) {
//            Toast.makeText(this, "Kedalaman tidak boleh kosong", Toast.LENGTH_SHORT).show();
//        }
//        else if (mDeadFurrow.getText().toString().equals("")) {
//            Toast.makeText(this, "Dead Furrow tidak boleh kosong", Toast.LENGTH_SHORT).show();
//        }
//        else if (autoAplikasiPinggiran.getText().toString().equals("")) {
//            Toast.makeText(this, "Aplikasi Pinggiran tidak boleh kosong", Toast.LENGTH_SHORT).show();
//        }
//        else if (autoKerataanAplikasi.getText().toString().equals("")) {
//            Toast.makeText(this, "Aplikasi Kerataan tidak boleh kosong", Toast.LENGTH_SHORT).show();
//        }
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
        rowView = inflater.inflate(R.layout.data_singkongtanam_sample_field, null);
//        final LinearLayout containerSampleData = view.findViewById(R.id.containerSampleData);

        final TextView noSample = rowView.findViewById(R.id.mNoSample);
        final TextView plot = rowView.findViewById(R.id.mPlot);
        final TextView luasplot = rowView.findViewById(R.id.mLuasPlot);

        final TextView kualitasjtdb1 = rowView.findViewById(R.id.mKualitasJtdb1);
        final TextView kualitasjtdb2 = rowView.findViewById(R.id.mKualitasJtdb2);
        final TextView kualitasjtdb3 = rowView.findViewById(R.id.mKualitasJtdb3);
        final TextView kualitasjtdb4 = rowView.findViewById(R.id.mKualitasJtdb4);
        final TextView kualitasjtdb5 = rowView.findViewById(R.id.mKualitasJtdb5);
        final TextView kualitasjtdb6 = rowView.findViewById(R.id.mKualitasJtdb6);
        final TextView kualitasjtdb7 = rowView.findViewById(R.id.mKualitasJtdb7);
        final TextView kualitasjtdb8 = rowView.findViewById(R.id.mKualitasJtdb8);
        final TextView kualitasjtdb9 = rowView.findViewById(R.id.mKualitasJtdb9);
        final TextView kualitasjtdb10 = rowView.findViewById(R.id.mKualitasJtdb10);

        final TextView totsamplejtdb = rowView.findViewById(R.id.mTotalSampleJtdb);
        final TextView jumstdjtdb = rowView.findViewById(R.id.mJumlahMasukStandarJtdb);

        final TextView kualitasjtab1 = rowView.findViewById(R.id.mKualitasJtab1);
        final TextView kualitasjtab2 = rowView.findViewById(R.id.mKualitasJtab2);
        final TextView kualitasjtab3 = rowView.findViewById(R.id.mKualitasJtab3);
        final TextView kualitasjtab4 = rowView.findViewById(R.id.mKualitasJtab4);
        final TextView kualitasjtab5 = rowView.findViewById(R.id.mKualitasJtab5);
        final TextView kualitasjtab6 = rowView.findViewById(R.id.mKualitasJtab6);
        final TextView kualitasjtab7 = rowView.findViewById(R.id.mKualitasJtab7);
        final TextView kualitasjtab8 = rowView.findViewById(R.id.mKualitasJtab8);
        final TextView kualitasjtab9 = rowView.findViewById(R.id.mKualitasJtab9);
        final TextView kualitasjtab10 = rowView.findViewById(R.id.mKualitasJtab10);

        final TextView totsamplejtab = rowView.findViewById(R.id.mTotalSampleJtab);
        final TextView jumstdjtab = rowView.findViewById(R.id.mJumlahMasukStandarJtab);

        final TextView kedalamantanam4 = rowView.findViewById(R.id.mKedalamanTanam4);
        final TextView kedalamantanam5 = rowView.findViewById(R.id.mKedalamanTanam5);
        final TextView kedalamantanam6 = rowView.findViewById(R.id.mKedalamanTanam6);
        final TextView kedalamantanam7 = rowView.findViewById(R.id.mKedalamanTanam7);
        final TextView kedalamantanam8 = rowView.findViewById(R.id.mKedalamanTanam8);
        final TextView kedalamantanam9 = rowView.findViewById(R.id.mKedalamanTanam9);
        final TextView kedalamantanam10 = rowView.findViewById(R.id.mKedalamanTanam10);
        final TextView kedalamantanam11 = rowView.findViewById(R.id.mKedalamanTanam11);
        final TextView kedalamantanam12 = rowView.findViewById(R.id.mKedalamanTanam12);
        final TextView kedalamantanam13 = rowView.findViewById(R.id.mKedalamanTanam13);
        final TextView kedalamantanam14 = rowView.findViewById(R.id.mKedalamanTanam14);

        final TextView totalsamplekedalaman = rowView.findViewById(R.id.mTotalSampleKedalaman);
        final TextView jumlahmasukstandarkedalaman = rowView.findViewById(R.id.mJumlahMasukStandarKedalaman);

        final TextView ya = rowView.findViewById(R.id.mYa);
        final TextView tidak = rowView.findViewById(R.id.mTidak);

        final TextView populasi = rowView.findViewById(R.id.mPopulasi);

        luasplot.setText(mLuasPlot.getText().toString());
        kualitasjtdb1.setText(mKualitasJtdb1.getText().toString());
        kualitasjtdb2.setText(mKualitasJtdb2.getText().toString());
        kualitasjtdb3.setText(mKualitasJtdb3.getText().toString());
        kualitasjtdb4.setText(mKualitasJtdb4.getText().toString());
        kualitasjtdb5.setText(mKualitasJtdb5.getText().toString());
        kualitasjtdb6.setText(mKualitasJtdb6.getText().toString());
        kualitasjtdb7.setText(mKualitasJtdb7.getText().toString());
        kualitasjtdb8.setText(mKualitasJtdb8.getText().toString());
        kualitasjtdb9.setText(mKualitasJtdb9.getText().toString());
        kualitasjtdb10.setText(mKualitasJtdb10.getText().toString());

        totsamplejtdb.setText(mTotalSampleJtdb.getText().toString());
        jumstdjtdb.setText(mJumlahMasukStandarJtdb.getText().toString());

        kualitasjtab1.setText(mKualitasJtab1.getText().toString());
        kualitasjtab2.setText(mKualitasJtab2.getText().toString());
        kualitasjtab3.setText(mKualitasJtab3.getText().toString());
        kualitasjtab4.setText(mKualitasJtab4.getText().toString());
        kualitasjtab5.setText(mKualitasJtab5.getText().toString());
        kualitasjtab6.setText(mKualitasJtab6.getText().toString());
        kualitasjtab7.setText(mKualitasJtab7.getText().toString());
        kualitasjtab8.setText(mKualitasJtab8.getText().toString());
        kualitasjtab9.setText(mKualitasJtab9.getText().toString());
        kualitasjtab10.setText(mKualitasJtab10.getText().toString());

        totsamplejtab.setText(mTotalSampleJtab.getText().toString());
        jumstdjtab.setText(mJumlahMasukStandarJtab.getText().toString());

        kedalamantanam4.setText(mKedalamanTanam4.getText().toString());
        kedalamantanam5.setText(mKedalamanTanam5.getText().toString());
        kedalamantanam6.setText(mKedalamanTanam6.getText().toString());
        kedalamantanam7.setText(mKedalamanTanam7.getText().toString());
        kedalamantanam8.setText(mKedalamanTanam8.getText().toString());
        kedalamantanam9.setText(mKedalamanTanam9.getText().toString());
        kedalamantanam10.setText(mKedalamanTanam10.getText().toString());
        kedalamantanam11.setText(mKedalamanTanam11.getText().toString());
        kedalamantanam12.setText(mKedalamanTanam12.getText().toString());
        kedalamantanam13.setText(mKedalamanTanam13.getText().toString());
        kedalamantanam14.setText(mKedalamanTanam14.getText().toString());

        totalsamplekedalaman.setText(mTotalSampleJtab.getText().toString());
        jumlahmasukstandarkedalaman.setText(mJumlahMasukStandarKedalaman.getText().toString());

        ya.setText(mYa.getText().toString());
        tidak.setText(mTidak.getText().toString());
        populasi.setText(mPopulasi.getText().toString());

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
        sampleModel.setJenisBibit(mJenisBibit.getText().toString());


        sampleModel.setPLOT(Integer.parseInt(mPlot.getText().toString()));
        sampleModel.setLuasPlot((float) Integer.parseInt(mLuasPlot.getText().toString()));

        sampleModel.setKeterangan(mKeterangan.getText().toString());

        sampleModel.setKualitas_jtdb_1((float) Integer.parseInt(mKualitasJtdb1.getText().toString()));
        sampleModel.setKualitas_jtdb_2((float) Integer.parseInt(mKualitasJtdb2.getText().toString()));
        sampleModel.setKualitas_jtdb_3((float) Integer.parseInt(mKualitasJtdb3.getText().toString()));
        sampleModel.setKualitas_jtdb_4((float) Integer.parseInt(mKualitasJtdb4.getText().toString()));
        sampleModel.setKualitas_jtdb_5((float) Integer.parseInt(mKualitasJtdb5.getText().toString()));
        sampleModel.setKualitas_jtdb_6((float) Integer.parseInt(mKualitasJtdb6.getText().toString()));
        sampleModel.setKualitas_jtdb_7((float) Integer.parseInt(mKualitasJtdb7.getText().toString()));
        sampleModel.setKualitas_jtdb_8((float) Integer.parseInt(mKualitasJtdb8.getText().toString()));
        sampleModel.setKualitas_jtdb_9((float) Integer.parseInt(mKualitasJtdb9.getText().toString()));
        sampleModel.setKualitas_jtdb_10((float) Integer.parseInt(mKualitasJtdb10.getText().toString()));

        sampleModel.setTotal_sample_jtdb((float) Integer.parseInt(mTotalSampleJtdb.getText().toString()));
        sampleModel.setJumlah_masuk_standar_jtdb((float) Integer.parseInt(mJumlahMasukStandarJtdb.getText().toString()));

        sampleModel.setKualitas_jtab_1((float) Integer.parseInt(mKualitasJtab1.getText().toString()));
        sampleModel.setKualitas_jtab_2((float) Integer.parseInt(mKualitasJtab2.getText().toString()));
        sampleModel.setKualitas_jtab_3((float) Integer.parseInt(mKualitasJtab3.getText().toString()));
        sampleModel.setKualitas_jtab_4((float) Integer.parseInt(mKualitasJtab4.getText().toString()));
        sampleModel.setKualitas_jtab_5((float) Integer.parseInt(mKualitasJtab5.getText().toString()));
        sampleModel.setKualitas_jtab_6((float) Integer.parseInt(mKualitasJtab6.getText().toString()));
        sampleModel.setKualitas_jtab_7((float) Integer.parseInt(mKualitasJtab7.getText().toString()));
        sampleModel.setKualitas_jtab_8((float) Integer.parseInt(mKualitasJtab8.getText().toString()));
        sampleModel.setKualitas_jtab_9((float) Integer.parseInt(mKualitasJtab9.getText().toString()));
        sampleModel.setKualitas_jtab_10((float) Integer.parseInt(mKualitasJtab10.getText().toString()));

        sampleModel.setTotal_sample_jtab((float) Integer.parseInt(mTotalSampleJtab.getText().toString()));
        sampleModel.setJumlah_masuk_standar_jtab((float) Integer.parseInt(mJumlahMasukStandarJtab.getText().toString()));

        sampleModel.setKedalaman_tanam_4((float) Integer.parseInt(mKedalamanTanam4.getText().toString()));
        sampleModel.setKedalaman_tanam_5((float) Integer.parseInt(mKedalamanTanam5.getText().toString()));
        sampleModel.setKedalaman_tanam_6((float) Integer.parseInt(mKedalamanTanam6.getText().toString()));
        sampleModel.setKedalaman_tanam_7((float) Integer.parseInt(mKedalamanTanam7.getText().toString()));
        sampleModel.setKedalaman_tanam_8((float) Integer.parseInt(mKedalamanTanam8.getText().toString()));
        sampleModel.setKedalaman_tanam_9((float) Integer.parseInt(mKedalamanTanam9.getText().toString()));
        sampleModel.setKedalaman_tanam_10((float) Integer.parseInt(mKedalamanTanam10.getText().toString()));
        sampleModel.setKedalaman_tanam_11((float) Integer.parseInt(mKedalamanTanam11.getText().toString()));
        sampleModel.setKedalaman_tanam_12((float) Integer.parseInt(mKedalamanTanam12.getText().toString()));
        sampleModel.setKedalaman_tanam_13((float) Integer.parseInt(mKedalamanTanam13.getText().toString()));
        sampleModel.setKedalaman_tanam_14((float) Integer.parseInt(mKedalamanTanam14.getText().toString()));

        sampleModel.setTotal_sample_kedalaman((float) Integer.parseInt(mTotalSampleKedalaman.getText().toString()));
        sampleModel.setJumlah_masuk_standar_kedalaman((float) Integer.parseInt(mJumlahMasukStandarKedalaman.getText().toString()));

        sampleModel.setYa(Integer.parseInt(mYa.getText().toString()));
        sampleModel.setTidak(Integer.parseInt(mTidak.getText().toString()));

        sampleModel.setPopulasi(Integer.parseInt(mPopulasi.getText().toString()));


//        dataSample.add(sampleModel);
        dataSample.add(sampleModel);
//        index +=1 ;
        containerPlotData.addView(rowView, 0);

        //Hapus Saat Apply
        Toast.makeText(this, "Data Sudah Ditambahkan", Toast.LENGTH_SHORT).show();
//        mKedalaman.getText().clear();
//        mDeadFurrow.getText().clear();

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
        String message = "Tanam Singkong"
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

    private static final String [] mJenisBibit2 = new String[]{
            "DN9" , "GRD" , "KSS" , "SCI" , "UJ5"
    };


}