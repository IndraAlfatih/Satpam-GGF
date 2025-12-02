package com.ggf.qcpp.e_formpengamatan.boom;

import static com.ggf.qcpp.utils.Utils.generateTglSekarang;
import static com.ggf.qcpp.utils.Utils.goToListPengamatan;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ggf.qcpp.R;
import com.ggf.qcpp.e_formpengamatan.boom.model.SampleModel;
import com.ggf.qcpp.e_formpengamatan.boom.model.BoomMixerModel;
import com.ggf.qcpp.e_formpengamatan.boom.model.PlotModel;
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

public class FormPengamatanBoomMixer extends AppCompatActivity implements View.OnClickListener, IFormPengamatanBoomMixerView {

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

    @BindView(R.id.mLokasi)
    EditText mLokasi;

    @BindView(R.id.mLuasBruto)
    EditText mLuasBruto;

    @BindView(R.id.mTanggalPengamatan)
    EditText mTanggalPengamatan;

    @BindView(R.id.mTanggalAplikasi)
    EditText mTanggalAplikasi;

    @BindView(R.id.mShift)
    AutoCompleteTextView mShift;

    @BindView(R.id.mMandorBibit)
    EditText mMandorBibit;

    @BindView(R.id.mKodeCameco)
    AutoCompleteTextView mKodeCameco;

    @BindView(R.id.mKodeTangki)
    AutoCompleteTextView mKodeTangki;

    @BindView(R.id.mJenisAplikasi)
    AutoCompleteTextView mJenisAplikasi;

    @BindView(R.id.mUpdatePeta)
    EditText mUpdatePeta;

    @BindView(R.id.mMulaiPengisian)
    EditText mMulaiPengisian;

    @BindView(R.id.mSelesaiPengisian)
    EditText mSelesaiPengisian;

//    @BindView(R.id.mTotalPengisian)
//    EditText mTotalPengisian;

    @BindView(R.id.mKodePlotTeraplikasi)
    EditText mKodePlotTeraplikasi;

    @BindView(R.id.mLuasAktif)
    EditText mLuasAktif;

    @BindView(R.id.mLuasAktifTeraplikasi)
    AutoCompleteTextView mLuasAktifTeraplikasi;

    @BindView(R.id.mMulaiAplikasi)
    EditText mMulaiAplikasi;

    @BindView(R.id.mSelesaiAplikasi)
    EditText mSelesaiAplikasi;

//    @BindView(R.id.mTotalAplikasi)
//    EditText mTotalAplikasi;

    @BindView(R.id.mVolumeAir)
    EditText mVolumeAir;

    @BindView(R.id.mRencana)
    EditText mRencana;

    @BindView(R.id.mReal)
    EditText mReal;

    @BindView(R.id.mNoNozzle)
    EditText mNoNozzle;

    @BindView(R.id.mTemuanNozzle)
    AutoCompleteTextView mTemuanNozzle;

    @BindView(R.id.mBerhentiLuarPlot)
    EditText mBerhentiLuarPlot;

    @BindView(R.id.mBerhentiDalamPlot)
    EditText mBerhentiDalamPlot;

    @BindView(R.id.mNoPlotSayapKananCameco)
    EditText mNoPlotSayapKananCameco;

    @BindView(R.id.mNoPlotSayapKiriCameco)
    EditText mNoPlotSayapKiriCameco;

    @BindView(R.id.mKebocoranGoldPumpBsc)
    AutoCompleteTextView mKebocoranGoldPumpBsc;

    @BindView(R.id.mKebocoranGoldPumpTsm)
    AutoCompleteTextView mKebocoranGoldPumpTsm;

    @BindView(R.id.mKebocoranTangkiTsm)
    AutoCompleteTextView mKebocoranTangkiTsm;

    @BindView(R.id.mPressure)
    EditText mPressure;

    @BindView(R.id.mSpeed)
    EditText mSpeed;

    @BindView(R.id.mSuhuSaatForcing)
    EditText mSuhuSaatForcing;

//    @BindView(R.id.mCeklistKeaktifanAgitatorCameco)
//    AutoCompleteTextView mCeklistKeaktifanAgitatorCameco;

    @BindView(R.id.mCeklistKeaktifanAgitatorTangkiSuplay)
    AutoCompleteTextView mCeklistKeaktifanAgitatorTangkiSuplay;

    View rowView;
    View rowViewPlot;
    View viewnya = null;
    String plot = "0";
    BoomMixerModel model;

    int index = 1;

    List<PlotModel> dataPlot = new ArrayList<>();
    List<SampleModel> dataSample = new ArrayList<>();

    SweetAlertDialog sweetAlertDialog;
    SampleModel sampleModel = null;
    PlotModel plotModel = null;
    FormPengamatanBoomMixerPresenter presenter;

    private ScrollView scrollView;
    private View contentView;
    private View focusedView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form_pengamatan_boom_mixer);

        ButterKnife.bind(this);
        presenter = new FormPengamatanBoomMixerPresenter(this);
        model = (BoomMixerModel) getIntent().getSerializableExtra("model");

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText etTime = findViewById(R.id.mMulaiAplikasi);
        EditText etTime2 = findViewById(R.id.mSelesaiAplikasi);

        EditText etTime3 = findViewById(R.id.mMulaiPengisian);
        EditText etTime4 = findViewById(R.id.mSelesaiPengisian);

        etTime.setOnClickListener(v -> {
            // Mendapatkan waktu saat ini
            int hour = 12; // Default jam
            int minute = 0; // Default menit

            // Membuat dialog TimePicker
            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    FormPengamatanBoomMixer.this,
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
                    FormPengamatanBoomMixer.this,
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
                    FormPengamatanBoomMixer.this,
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
                    FormPengamatanBoomMixer.this,
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

        Calendar calendar2 = Calendar.getInstance();
        int year2 = calendar2.get(Calendar.YEAR);
        int month2 = calendar2.get(Calendar.MONTH);
        int day2 = calendar2.get(Calendar.DAY_OF_MONTH);
        mTanggalAplikasi.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        // Tambahkan 1 ke bulan (karena bulan dimulai dari 0)
                        String selectedDate = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay;
                        mTanggalAplikasi.setText(selectedDate); // Set tanggal di EditText
                    },
                    year2, month2, day2);

            datePickerDialog.show(); // Tampilkan dialog
        });

        Calendar calendar3 = Calendar.getInstance();
        int year3 = calendar3.get(Calendar.YEAR);
        int month3 = calendar3.get(Calendar.MONTH);
        int day3 = calendar3.get(Calendar.DAY_OF_MONTH);
        mUpdatePeta.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        // Tambahkan 1 ke bulan (karena bulan dimulai dari 0)
                        String selectedDate = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay;
                        mUpdatePeta.setText(selectedDate); // Set tanggal di EditText
                    },
                    year3, month3, day3);

            datePickerDialog.show(); // Tampilkan dialog
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mKodeTangki2);
        mKodeTangki.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mShift2);
        mShift.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mTemuanNozzle2);
        mTemuanNozzle.setAdapter(adapter3);

        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mKebocoranGoldPumpBsc2);
        mKebocoranGoldPumpBsc.setAdapter(adapter4);

        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mKebocoranGoldPumpTsm2);
        mKebocoranGoldPumpTsm.setAdapter(adapter5);

        ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mKebocoranTangkiTsm2);
        mKebocoranTangkiTsm.setAdapter(adapter6);

        ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mLuasAktifTeraplikasi2);
        mLuasAktifTeraplikasi.setAdapter(adapter7);

        ArrayAdapter<String> adapter8 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mCeklistKeaktifanAgitatorTangkiSuplay2);
        mCeklistKeaktifanAgitatorTangkiSuplay.setAdapter(adapter8);

        ArrayAdapter<String> adapter9 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mKodeCameco2);
        mKodeCameco.setAdapter(adapter9);

        ArrayAdapter<String> adapter10 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mJenisAplikasi2);
        mJenisAplikasi.setAdapter(adapter10);

        // Menonaktifkan input teks, tetapi dropdown masih muncul
        mShift.setKeyListener(null);
        mKodeTangki.setKeyListener(null);
        mJenisAplikasi.setKeyListener(null);
        mLuasAktifTeraplikasi.setKeyListener(null);
        mTemuanNozzle.setKeyListener(null);
        mKebocoranGoldPumpBsc.setKeyListener(null);
        mKebocoranGoldPumpTsm.setKeyListener(null);
        mKebocoranTangkiTsm.setKeyListener(null);
        mCeklistKeaktifanAgitatorTangkiSuplay.setKeyListener(null);

        // Memastikan dropdown muncul meskipun tidak ada teks yang dimasukkan
        mShift.setThreshold(1);
        mKodeTangki.setThreshold(1);
        mJenisAplikasi.setThreshold(1);
        mLuasAktifTeraplikasi.setThreshold(1);
        mTemuanNozzle.setThreshold(1);
        mKebocoranGoldPumpBsc.setThreshold(1);
        mKebocoranGoldPumpTsm.setThreshold(1);
        mKebocoranTangkiTsm.setThreshold(1);
        mCeklistKeaktifanAgitatorTangkiSuplay.setThreshold(1);

        model = (BoomMixerModel) getIntent().getSerializableExtra("model");
        Log.d("bajakmodel", new Gson().toJson(model));

        presenter = new FormPengamatanBoomMixerPresenter(this);
        mSubmit.setOnClickListener(this);
        mAddSample.setOnClickListener(this);
    }

    void addPlotForm() {
//        Toast.makeText(this, "cek", Toast.LENGTH_SHORT).show();

        //Tidak boleh kosong
        if (mTanggalPengamatan.getText().toString().equals("")) {
            Toast.makeText(this, "Tanggal Pengamatan tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mMandorBibit.getText().toString().equals("")) {
            Toast.makeText(this, "Mandor BSC tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mTanggalAplikasi.getText().toString().equals("")) {
            Toast.makeText(this, "Tanggal Aplikasi tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mKodeCameco.getText().toString().equals("")) {
            Toast.makeText(this, "Kode Cameco tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mShift.getText().toString().equals("")) {
            Toast.makeText(this, "Shift tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mLokasi.getText().toString().equals("")) {
            Toast.makeText(this, "Lokasi tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mRencana.getText().toString().equals("")) {
            Toast.makeText(this, "Renc Vol Air tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mReal.getText().toString().equals("")) {
            Toast.makeText(this, "Real Vol Air tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mKodeTangki.getText().toString().equals("")) {
            Toast.makeText(this, "Kode Tangki Suplay tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mPlot.getText().toString().equals("")) {
            Toast.makeText(this, "No Data tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mJenisAplikasi.getText().toString().equals("")) {
            Toast.makeText(this, "Jenis Aplikasi tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mUpdatePeta.getText().toString().equals("")) {
            Toast.makeText(this, "Update Peta tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mMulaiPengisian.getText().toString().equals("")) {
            Toast.makeText(this, "Mulai Pengisian tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mSelesaiPengisian.getText().toString().equals("")) {
            Toast.makeText(this, "Selesai Pengisian tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mKodePlotTeraplikasi.getText().toString().equals("")) {
            Toast.makeText(this, "Kode Plot Teraplikasi tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mLuasAktifTeraplikasi.getText().toString().equals("")) {
            Toast.makeText(this, "Luasan Lokasi Teraplikasi tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mLuasAktif.getText().toString().equals("")) {
            Toast.makeText(this, "Luasan Aktif tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mLuasBruto.getText().toString().equals("")) {
            Toast.makeText(this, "Luas Bruto tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mMulaiAplikasi.getText().toString().equals("")) {
            Toast.makeText(this, "Mulai Aplikasi tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mSelesaiAplikasi.getText().toString().equals("")) {
            Toast.makeText(this, "Selesai Aplikasi tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mVolumeAir.getText().toString().equals("")) {
            Toast.makeText(this, "Volume Air tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mPressure.getText().toString().equals("")) {
            Toast.makeText(this, "Pressure tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mSpeed.getText().toString().equals("")) {
            Toast.makeText(this, "Speed tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mCeklistKeaktifanAgitatorTangkiSuplay.getText().toString().equals("")) {
            Toast.makeText(this, "Agritator Tanglo Suplay tidak boleh kosong", Toast.LENGTH_SHORT).show();
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
        rowView = inflater.inflate(R.layout.data_boommixer_sample_field, null);
//        final LinearLayout containerSampleData = view.findViewById(R.id.containerSampleData);

        final TextView noSample = rowView.findViewById(R.id.mNoSample);
        final TextView plot = rowView.findViewById(R.id.mPlot);

        final TextView jenisaplikasi = rowView.findViewById(R.id.mJenisAplikasi);
        final TextView luasbruto = rowView.findViewById(R.id.mLuasBruto);
        final TextView kodeunits = rowView.findViewById(R.id.mKodeCameco);
        final TextView updatepeta = rowView.findViewById(R.id.mUpdatePeta);
        final TextView lokasi = rowView.findViewById(R.id.mLokasi);
        final TextView mulaipengisian = rowView.findViewById(R.id.mMulaiPengisian);
        final TextView selesaipengisian = rowView.findViewById(R.id.mSelesaiPengisian);
        final TextView plotteraplikasi = rowView.findViewById(R.id.mKodePlotTeraplikasi);
        final TextView luasanaktif = rowView.findViewById(R.id.mLuasAktif);
        final TextView luasanteraplikasi = rowView.findViewById(R.id.mLuasAktifTeraplikasi);
        final TextView mulaiaplikasi = rowView.findViewById(R.id.mMulaiAplikasi);
        final TextView selesaiaplikasi = rowView.findViewById(R.id.mSelesaiAplikasi);
        final TextView volumeair = rowView.findViewById(R.id.mVolumeAir);
//        final TextView rencana = rowView.findViewById(R.id.mRencana);
//        final TextView realisasi = rowView.findViewById(R.id.mReal);
        final TextView nonozel = rowView.findViewById(R.id.mNoNozzle);
        final TextView temuannozel = rowView.findViewById(R.id.mTemuanNozzle);
        final TextView luarplot = rowView.findViewById(R.id.mBerhentiLuarPlot);
        final TextView dalamplot = rowView.findViewById(R.id.mBerhentiDalamPlot);
        final TextView kirisayap = rowView.findViewById(R.id.mNoPlotSayapKiriCameco);
        final TextView kanansayap = rowView.findViewById(R.id.mNoPlotSayapKananCameco);
        final TextView bscgold = rowView.findViewById(R.id.mKebocoranGoldPumpBsc);
        final TextView tsmgold = rowView.findViewById(R.id.mKebocoranGoldPumpTsm);
        final TextView tsmtangki = rowView.findViewById(R.id.mKebocoranTangkiTsm);
        final TextView pressure = rowView.findViewById(R.id.mPressure);
        final TextView speed = rowView.findViewById(R.id.mSpeed);
//        final TextView suhu = rowView.findViewById(R.id.mSuhuSaatForcing);
//        final TextView agritatorcameco = rowView.findViewById(R.id.mCeklistKeaktifanAgitatorCameco);
        final TextView agritatortangki = rowView.findViewById(R.id.mCeklistKeaktifanAgitatorTangkiSuplay);

        // Tetapkan nilai default 1 untuk plot
        plot.setText("1");

        jenisaplikasi.setText(mJenisAplikasi.getText().toString());
        lokasi.setText(mLokasi.getText().toString());
        plot.setText(mPlot.getText().toString());
        kodeunits.setText(mKodeCameco.getText().toString());
        updatepeta.setText(mUpdatePeta.getText().toString());
        luasbruto.setText(mLuasBruto.getText().toString());
        mulaipengisian.setText(mMulaiPengisian.getText().toString());
        selesaipengisian.setText(mSelesaiPengisian.getText().toString());
        plotteraplikasi.setText(mKodePlotTeraplikasi.getText().toString());
        luasanaktif.setText(mLuasAktif.getText().toString());
        luasanteraplikasi.setText(mLuasAktifTeraplikasi.getText().toString());
        mulaiaplikasi.setText(mMulaiAplikasi.getText().toString());
        selesaiaplikasi.setText(mSelesaiAplikasi.getText().toString());
        volumeair.setText(mVolumeAir.getText().toString());
//        rencana.setText(mRencana.getText().toString());
//        realisasi.setText(mReal.getText().toString());
        nonozel.setText(mNoNozzle.getText().toString());
        temuannozel.setText(mTemuanNozzle.getText().toString());
        luarplot.setText(mBerhentiLuarPlot.getText().toString());
        dalamplot.setText(mBerhentiDalamPlot.getText().toString());
        kirisayap.setText(mNoPlotSayapKiriCameco.getText().toString());
        kanansayap.setText(mNoPlotSayapKananCameco.getText().toString());
        bscgold.setText(mKebocoranGoldPumpBsc.getText().toString());
        tsmgold.setText(mKebocoranGoldPumpTsm.getText().toString());
        tsmtangki.setText(mKebocoranTangkiTsm.getText().toString());
        pressure.setText(mPressure.getText().toString());
        speed.setText(mSpeed.getText().toString());
//        suhu.setText(mSuhuSaatForcing.getText().toString());
//        agritatorcameco.setText(mCeklistKeaktifanAgitatorCameco.getText().toString());
        agritatortangki.setText(mCeklistKeaktifanAgitatorTangkiSuplay.getText().toString());




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

        String selectedDateString = mTanggalPengamatan.getText().toString();
        sampleModel.setTanggalPengamatan(selectedDateString);
        String selectedDateString2 = mTanggalAplikasi.getText().toString();
        sampleModel.setTanggalAplikasi(selectedDateString2);
        String selectedDateString3 = mUpdatePeta.getText().toString();
        sampleModel.setUpdatePeta(selectedDateString3);
        noSample.setText(String.valueOf(nextSampleNumber));
        sampleModel.setNo_sample(noSample.getText().toString());

        sampleModel.setMandorBibit(mMandorBibit.getText().toString());
        sampleModel.setKodeCameco(mKodeCameco.getText().toString());
        sampleModel.setShift(mShift.getText().toString());
        sampleModel.setPLOT(1);
        sampleModel.setKodeTangki(mKodeTangki.getText().toString());
        sampleModel.setLokasi(mLokasi.getText().toString());
        sampleModel.setJenisAplikasi(mJenisAplikasi.getText().toString());
        sampleModel.setUpdatePeta(mUpdatePeta.getText().toString());
        sampleModel.setMulaiPengisian(mMulaiPengisian.getText().toString());
        sampleModel.setLuas_bruto(Double.parseDouble(mLuasBruto.getText().toString()));
        sampleModel.setSelesaiPengisian(mSelesaiPengisian.getText().toString());
        sampleModel.setKodePlotTeraplikasi(mKodePlotTeraplikasi.getText().toString());
        sampleModel.setLuasAktif(Float.parseFloat(mLuasAktif.getText().toString()));
        sampleModel.setLuasAktifTeraplikasi(Float.parseFloat(mLuasAktifTeraplikasi.getText().toString()));
        sampleModel.setMulaiAplikasi(mMulaiAplikasi.getText().toString());
        sampleModel.setSelesaiAplikasi(mSelesaiAplikasi.getText().toString());
        sampleModel.setVolumeAir(Float.parseFloat(mVolumeAir.getText().toString()));
        sampleModel.setRencana(Float.parseFloat(mRencana.getText().toString()));
        sampleModel.setReal(Float.parseFloat(mReal.getText().toString()));
        sampleModel.setNoNozzle(mNoNozzle.getText().toString());
        sampleModel.setTemuanNozzle(mTemuanNozzle.getText().toString());
        sampleModel.setBerhentiLuarPlot(mBerhentiLuarPlot.getText().toString());
        sampleModel.setBerhentiDalamPlot(mBerhentiDalamPlot.getText().toString());
        sampleModel.setNoPlotSayapKiriCameco(Integer.parseInt(mNoPlotSayapKiriCameco.getText().toString()));
        sampleModel.setNoPlotSayapKananCameco(Integer.parseInt(mNoPlotSayapKananCameco.getText().toString()));
        sampleModel.setKebocoranGoldPumpBsc(mKebocoranGoldPumpBsc.getText().toString());
        sampleModel.setKebocoranGoldPumpTsm(mKebocoranGoldPumpTsm.getText().toString());
        sampleModel.setKebocoranTangkiTsm(mKebocoranTangkiTsm.getText().toString());
        sampleModel.setSpeed(Float.parseFloat(mSpeed.getText().toString()));
        sampleModel.setSuhuSaatForcing(Float.parseFloat(mSuhuSaatForcing.getText().toString()));
        sampleModel.setCeklistKeaktifanAgitatorTangkiSuplay(mCeklistKeaktifanAgitatorTangkiSuplay.getText().toString());
        sampleModel.setKeterangan(mKeterangan.getText().toString());

//        dataSample.add(sampleModel);
        dataSample.add(sampleModel);
//        index +=1 ;
        containerPlotData.addView(rowView, 0);

        //Hapus Saat Apply
        Toast.makeText(this, "Data Sudah Ditambahkan", Toast.LENGTH_SHORT).show();
//        mKodeTangki.getText().clear();
//        mJenisAplikasi.getText().clear();
//        mUpdatePeta.getText().clear();
//        mMulaiPengisian.getText().clear();
//        mSelesaiPengisian.getText().clear();
//        mKodePlotTeraplikasi.getText().clear();
//        mLuasAktifTeraplikasi.getText().clear();
//        mLuasAktif.getText().clear();
//        mLuasBruto.getText().clear();
//        mMulaiAplikasi.getText().clear();
//        mSelesaiAplikasi.getText().clear();
//        mVolumeAir.getText().clear();
//        mNoNozzle.getText().clear();
//        mTemuanNozzle.getText().clear();
//        mBerhentiLuarPlot.getText().clear();
//        mBerhentiDalamPlot.getText().clear();
//        mNoPlotSayapKiriCameco.getText().clear();
//        mNoPlotSayapKananCameco.getText().clear();
//        mKebocoranGoldPumpBsc.getText().clear();
//        mKebocoranGoldPumpTsm.getText().clear();
//        mKebocoranTangkiTsm.getText().clear();
//        mPressure.getText().clear();
//        mSpeed.getText().clear();
//        mCeklistKeaktifanAgitatorTangkiSuplay.getText().clear();

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
        String message = "Boom Spray"
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

    private static final String [] mKodeTangki2 = new String[]{
            "TSM-001" , "TSM-002" , "TSM-003" , "TSM-004" , "TSM-005" , "TSM-006" , "TSM-007" , "TSM-008" , "TSM-009" , "TSM-010",
            "TSM-011" , "TSM-012" , "TSM-013" , "TSM-014" , "TSM-015" , "TSM-016" , "TSM-017" , "TSM-018" , "TSM-019" , "TSM-020",
            "TSM-021" , "TSM-022" , "TSM-023" , "TSM-024" , "TSM-025" , "TSM-026" , "TSM-027" , "TSM-028" , "TSM-029" , "TSM-030",
            "TSM-031" , "TSM-032" , "TSM-033" , "TSM-034" , "TSM-035" , "TSM-036" , "TSM-037" , "TSM-038" , "TSM-039" , "TSM-040", "TSM-041"
    };

    private static final String [] mShift2 = new String[]{
            "Pagi" , "Malam"
    };

    private static final String [] mTemuanNozzle2 = new String[]{
            "0" , "1"
    };

    private static final String [] mKebocoranGoldPumpBsc2 = new String[]{
            "0" , "1"
    };

    private static final String [] mKebocoranGoldPumpTsm2 = new String[]{
            "0" , "1"
    };

    private static final String [] mKebocoranTangkiTsm2 = new String[]{
            "0" , "1"
    };

    private static final String [] mLuasAktifTeraplikasi2 = new String[]{
            "0" , "1"
    };

    private static final String [] mCeklistKeaktifanAgitatorTangkiSuplay2 = new String[]{
            "0" , "1"
    };

    private static final String [] mKodeCameco2 = new String[]{
            "BSC-001" , "BSC-002" , "BSC-003" , "BSC-004" , "BSC-005" , "BSC-006" , "BSC-007" , "BSC-008" , "BSC-009" , "BSC-010",
            "BSC-011" , "BSC-012" , "BSC-013" , "BSC-014" , "BSC-015" , "BSC-016" , "BSC-017" , "BSC-018" , "BSC-019" , "BSC-020"
    };

    private static final String [] mJenisAplikasi2 = new String[]{
            "Booster" , "Cuci Bilas" , "Foliar Spray" , "Forcing" , "Insectisida" , "Pestisida" , "Post Planting" , "Pre Planting" , "Repening"
    };

}