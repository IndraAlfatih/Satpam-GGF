package com.ggf.qcpp.e_formpengamatan.adukanbahan;

import static com.ggf.qcpp.utils.Utils.generateTglSekarang;
import static com.ggf.qcpp.utils.Utils.goToListPengamatan;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
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
import com.ggf.qcpp.e_formpengamatan.adukanbahan.model.AdukanBahanDilokasiModel;
import com.ggf.qcpp.e_formpengamatan.adukanbahan.model.PlotModel;
import com.ggf.qcpp.e_formpengamatan.adukanbahan.model.SampleModel;
import com.ggf.qcpp.e_formpengamatan.gudangmixer.model.GudangMixerModel;
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

public class FormPengamatanAdukanBahanDilokasi extends AppCompatActivity implements View.OnClickListener, IFormPengamatanAdukanBahanDilokasiView {

    @BindView(R.id.mSubmit)
    Button mSubmit;
    @BindView(R.id.mAddSample)
    ImageView mAddSample;
    @BindView(R.id.containerPlotData)
    LinearLayout containerPlotData;
    @BindView(R.id.mLokasi)
    EditText mLokasi;

    @BindView(R.id.mPlot)
    EditText mPlot;

    @BindView(R.id.mKeterangan)
    EditText mKeterangan;

    //batas

    @BindView(R.id.mSPK)
    EditText mSPK;

    @BindView(R.id.mLine)
    EditText mLine;


    @BindView(R.id.mTanggalPengamatan)
    EditText mTanggalPengamatan;

    @BindView(R.id.mMandorBibit)
    EditText mMandorBibit;

    @BindView(R.id.mKet)
    EditText mKet;

    @BindView(R.id.mDiv)
    AutoCompleteTextView mDiv;

    @BindView(R.id.mKodeBsc)
    EditText mKodeBsc;

    @BindView(R.id.mShift)
    AutoCompleteTextView mShift;

    @BindView(R.id.mReworking)
    AutoCompleteTextView mReworking;

    @BindView(R.id.mAktivitas)
    AutoCompleteTextView mAktivitas;

    @BindView(R.id.mJenisBahan)
    AutoCompleteTextView mJenisBahan;

    @BindView(R.id.mRencana)
    EditText mRencana;

    @BindView(R.id.mReal)
    EditText mReal;

    @BindView(R.id.mPengisianKe)
    EditText mPengisianKe;

    @BindView(R.id.mVolumeAir)
    EditText mVolumeAir;

//    @BindView(R.id.mCeklistKeaktifanAgitatorTangki)
//    AutoCompleteTextView mCeklistKeaktifanAgitatorTangki;

    @BindView(R.id.mCeklistKeaktifanAgitatorCameco)
    AutoCompleteTextView mCeklistKeaktifanAgitatorCameco;

    View rowView;
    View rowViewPlot;
    View viewnya = null;
    String plot = "0";
    AdukanBahanDilokasiModel model;

    int index = 1;

    List<PlotModel> dataPlot = new ArrayList<>();
    List<SampleModel> dataSample = new ArrayList<>();

    SweetAlertDialog sweetAlertDialog;
    SampleModel sampleModel = null;
    PlotModel plotModel = null;
    FormPengamatanAdukanBahanDilokasiPresenter presenter;

    private ScrollView scrollView;
    private View contentView;
    private View focusedView;

    private static final String DRAFT_KEY = "draft_adukan";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form_pengamatan_adukan_bahan_dilokasi);

        ButterKnife.bind(this);
        presenter = new FormPengamatanAdukanBahanDilokasiPresenter(this);
        model = (AdukanBahanDilokasiModel) getIntent().getSerializableExtra("model");

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

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mDiv2);
        mDiv.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mShift2);
        mShift.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mJenisBahan2);
        mJenisBahan.setAdapter(adapter3);

//        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mCeklistKeaktifanAgitatorTangki2);
//        mCeklistKeaktifanAgitatorTangki.setAdapter(adapter4);

        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mCeklistKeaktifanAgitatorCameco2);
        mCeklistKeaktifanAgitatorCameco.setAdapter(adapter5);

        ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mAktivitas2);
        mAktivitas.setAdapter(adapter6);

        ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mReworking2);
        mReworking.setAdapter(adapter7);

        // Menonaktifkan input teks, tetapi dropdown masih muncul
        mDiv.setKeyListener(null);
        mShift.setKeyListener(null);
        mReworking.setKeyListener(null);
        mCeklistKeaktifanAgitatorCameco.setKeyListener(null);


        // Memastikan dropdown muncul meskipun tidak ada teks yang dimasukkan
        mDiv.setThreshold(1);
        mShift.setThreshold(1);
        mReworking.setThreshold(1);
        mJenisBahan.setThreshold(1);
        mCeklistKeaktifanAgitatorCameco.setThreshold(1);
        mAktivitas.setThreshold(1);

        model = (AdukanBahanDilokasiModel) getIntent().getSerializableExtra("model");
        Log.d("bajakmodel", new Gson().toJson(model));

        presenter = new FormPengamatanAdukanBahanDilokasiPresenter(this);
        mSubmit.setOnClickListener(this);
        mAddSample.setOnClickListener(this);
    }
    private void clearForm() {
        // Reset semua input
        mSPK.setText("");
        mLine.setText("");
        mTanggalPengamatan.setText("");
        mMandorBibit.setText("");
        mKet.setText("");
        mDiv.setText("");
        mKodeBsc.setText("");
        mShift.setText("");
        mReworking.setText("");
        mLokasi.setText("");
        mPlot.setText("");
        mAktivitas.setText("");
        mJenisBahan.setText("");
        mRencana.setText("");
        mReal.setText("");
        mPengisianKe.setText("");
        mVolumeAir.setText("");
        mCeklistKeaktifanAgitatorCameco.setText("");

        // Hapus data list
        dataPlot.clear();
        dataSample.clear();
        containerPlotData.removeAllViews();

        // Reset model
        model = new AdukanBahanDilokasiModel();

        // Hapus draft tersimpan
        TemporaryFormStorage.clearDraft(this, "draft_adukanbahan");

        Log.d("FormReset", "Form Adukan Bahan cleared");
    }

    private void saveTemporaryData() {
        model.setLOKASI(mLokasi.getText().toString());
        model.setDATA(dataPlot);

        String draftJson = new Gson().toJson(model);
        TemporaryFormStorage.saveDraft(this, "draft_adukanbahan", draftJson);
        Log.d("AutoSave", "Draft tersimpan: " + draftJson);
    }

    private void loadTemporaryData() {
        AdukanBahanDilokasiModel draftJson = TemporaryFormStorage.loadDraft(this, DRAFT_KEY, AdukanBahanDilokasiModel.class);
        if (draftJson != null) {
//            model = new Gson().fromJson(draftJson, AdukanBahanDilokasiModel.class);

            // isi ulang field utama
            mLokasi.setText(model.getLOKASI());
            if (model.getDATA() != null) {
                dataPlot = model.getDATA();
                for (PlotModel plot : dataPlot) {
                    for (SampleModel sample : plot.getSAMPLE()) {
                        // render ulang ke UI
                        addSampleView(sample);
                    }
                }
            }

            Log.d("AutoSave", "Draft dimuat ulang: " + draftJson);
        }
    }

    // untuk render ulang sample saat load draft
    private void addSampleView(SampleModel sample) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.data_adukanbahandilokasi_sample_field, null);

        ((TextView) rowView.findViewById(R.id.mNoSample)).setText(String.valueOf(sample.getNo_sample()));
        ((TextView) rowView.findViewById(R.id.mPlot)).setText(String.valueOf(sample.getPLOT()));
        ((TextView) rowView.findViewById(R.id.mLokasi)).setText(sample.getLokasi());
        ((TextView) rowView.findViewById(R.id.mAktivitas)).setText(sample.getAktivitas());
        ((TextView) rowView.findViewById(R.id.mJenisBahan)).setText(sample.getJenis_bahan());
        ((TextView) rowView.findViewById(R.id.mReal)).setText(String.valueOf(sample.getReal_value()));
        ((TextView) rowView.findViewById(R.id.mRencana)).setText(String.valueOf(sample.getRencana()));
        ((TextView) rowView.findViewById(R.id.mPengisianKe)).setText(String.valueOf(sample.getPengisian_ke()));
        ((TextView) rowView.findViewById(R.id.mVolumeAir)).setText(String.valueOf(sample.getVolume_air()));
        ((TextView) rowView.findViewById(R.id.mCeklistKeaktifanAgitatorCameco)).setText(sample.getCeklist_keaktifan_agitator_cameco());

        containerPlotData.addView(rowView, 0);
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
            Toast.makeText(this, "Mandor BSC tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mDiv.getText().toString().equals("")) {
            Toast.makeText(this, "Div tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mKodeBsc.getText().toString().equals("")) {
            Toast.makeText(this, "Kode BSC tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mReworking.getText().toString().equals("")) {
            Toast.makeText(this, "Reworking tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mShift.getText().toString().equals("")) {
            Toast.makeText(this, "Shift tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mPlot.getText().toString().equals("")) {
            Toast.makeText(this, "Urutan Adukan tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mLokasi.getText().toString().equals("")) {
            Toast.makeText(this, "Lokasi tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mAktivitas.getText().toString().equals("")) {
            Toast.makeText(this, "Aktivitas tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mJenisBahan.getText().toString().equals("")) {
            Toast.makeText(this, "Jenis Bahan tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mReal.getText().toString().equals("")) {
            Toast.makeText(this, "Real tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mRencana.getText().toString().equals("")) {
            Toast.makeText(this, "Rencana tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mPengisianKe.getText().toString().equals("")) {
            Toast.makeText(this, "Pengisian ke tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mVolumeAir.getText().toString().equals("")) {
            Toast.makeText(this, "Volume Air tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mCeklistKeaktifanAgitatorCameco.getText().toString().equals("")) {
            Toast.makeText(this, "Ceklist Keaktifan Agritator Cameco tidak boleh kosong", Toast.LENGTH_SHORT).show();
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
        rowView = inflater.inflate(R.layout.data_adukanbahandilokasi_sample_field, null);
//        final LinearLayout containerSampleData = view.findViewById(R.id.containerSampleData);

        final TextView noSample = rowView.findViewById(R.id.mNoSample);
        final TextView plot = rowView.findViewById(R.id.mPlot);
        final TextView lokasi = rowView.findViewById(R.id.mLokasi);
        final TextView aktivitas = rowView.findViewById(R.id.mAktivitas);
        final TextView jenisbahan = rowView.findViewById(R.id.mJenisBahan);
        final TextView real = rowView.findViewById(R.id.mReal);
        final TextView renc = rowView.findViewById(R.id.mRencana);
        final TextView pengisianke = rowView.findViewById(R.id.mPengisianKe);
        final TextView volumeair = rowView.findViewById(R.id.mVolumeAir);
        final TextView cameco = rowView.findViewById(R.id.mCeklistKeaktifanAgitatorCameco);


        lokasi.setText(mLokasi.getText().toString());
        aktivitas.setText(mAktivitas.getText().toString());
        jenisbahan.setText(mJenisBahan.getText().toString());
        real.setText(mReal.getText().toString());
        renc.setText(mRencana.getText().toString());
        pengisianke.setText(mPengisianKe.getText().toString());
        volumeair.setText(mVolumeAir.getText().toString());
//        tangki.setText(mCeklistKeaktifanAgitatorTangki.getText().toString());
        cameco.setText(mCeklistKeaktifanAgitatorCameco.getText().toString());

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

        String selectedDateString = mTanggalPengamatan.getText().toString();
        sampleModel.setTanggal_pengamatan(selectedDateString);
        sampleModel.setNo_spk(model.getNO_SPK());
        sampleModel.setNo_spk2(mSPK.getText().toString());
        sampleModel.setNo_line(mLine.getText().toString());
        sampleModel.setMandor_bibit(mMandorBibit.getText().toString());
        sampleModel.setDiv(mDiv.getText().toString());
        sampleModel.setKode_bsc(mKodeBsc.getText().toString());
        sampleModel.setShift(mShift.getText().toString());
        sampleModel.setReworking(mReworking.getText().toString());
        sampleModel.setPLOT(Integer.parseInt(mPlot.getText().toString()));
        sampleModel.setLokasi(mLokasi.getText().toString());
        sampleModel.setAktivitas(mAktivitas.getText().toString());
        sampleModel.setJenis_bahan(mJenisBahan.getText().toString());
        sampleModel.setReal_value(Float.parseFloat(mReal.getText().toString()));
        sampleModel.setRencana(Float.parseFloat(mRencana.getText().toString()));
        sampleModel.setPengisian_ke(Float.parseFloat(mPengisianKe.getText().toString()));
        sampleModel.setVolume_air(Float.parseFloat(mVolumeAir.getText().toString()));
        sampleModel.setCeklist_keaktifan_agitator_cameco(mCeklistKeaktifanAgitatorCameco.getText().toString());

        sampleModel.setKeterangan(
                mKet.getText().toString().trim().isEmpty() ? "-" : mKet.getText().toString().trim()
        );


//        dataSample.add(sampleModel);
        dataSample.add(sampleModel);
//        index +=1 ;
        containerPlotData.addView(rowView, 0);

        //Hapus Saat Apply
        Toast.makeText(this, "Data Sudah Ditambahkan", Toast.LENGTH_SHORT).show();
        mAktivitas.getText().clear();
        mJenisBahan.getText().clear();
        mReal.getText().clear();
        mRencana.getText().clear();
        mPengisianKe.getText().clear();
        mVolumeAir.getText().clear();

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
//            sampleModel.setKeterangan(mKeterangan.getText().toString());
//            plotModel.setSAMPLE(dataSample);
            model.setLOKASI(mLokasi.getText().toString());
            model.setDATA(dataPlot);
            sampleModel.setKeterangan(
                    mKet.getText().toString().trim().isEmpty() ? "-" : mKet.getText().toString().trim()
            );

            Log.d("dataBody", new Gson().toJson(model));
            presenter.createPengamatan(model);
        } else
            SweetDialogs.commonError(this, "Harap apply data terlebih dahulu", false);
    }

    @Override
    public void onCreateSuccess(String rm) {
        // Tambahkan format timestamp

        TemporaryFormStorage.clearDraft(this, DRAFT_KEY);
        clearForm();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        String currentTime = sdf.format(new Date()); // Mendapatkan waktu saat ini


        // Tambahkan waktu ke dalam pesan
        String message = "Adukan Bahan di Lokasi"
                + ", Lokasi: " + mLokasi.getText().toString()
                + "\nTanggal: " + generateTglSekarang()
                + "\nWaktu: " + currentTime
                + "\n"+getString(R.string.versi_apps);

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
    public void onNetworkError(String cause , String data) {

        Log.e("errornya", cause);
        SQLiteHelper dbHelper = new SQLiteHelper(this);
        dbHelper.saveChopperData(data, model.getNO_SPK());  // Assuming 'data' is a JSON string


        Log.d("Saved data", "datanyaSQL: " + data);
        Log.d("Saved data", "Data saved to SQLite: " + model.getNO_SPK());
        // Show a dialog indicating that the data has been saved offline
//        SweetDialogs.commonError(this, App.getApplication().getString(R.string.notif_offline_mode), false);
        SweetDialogs.commonWarningWithIntent(this,"Tidak ada Koneksi", App.getApplication().getString(R.string.notif_offline_mode), string -> startActivity(new Intent(this, c_1_home.class)));
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
                "1", "2", "3", "4", "5", "6"
        };

    private static final String[] mShift2 = new String[]{
            "Pagi", "Malam"
    };

    private static final String[] mReworking2 = new String[]{
            "Sebelum Reworking", "Sesudah Reworking"
    };

    private static final String[] mJenisBahan2 = new String[]{
            "Indostik", "Sidazinon", "Pastil", "Diuron", "Bromasil", "Metalaxyl", "Cypermethrin", "Propoxur", "Ethephon", "Quizalofop", "Ametryn", "Bifenthrin", "Urea Phosphat", "Sanisol", "Talstar", "Kaolin", "Gallant", "Urease", "Borax", "Na2CO3", "GA3", "Biosilac Padat", "Biosilac Cair", "BIO Pestisida", "LOB", "Sanifat", "R5B", "Mancozeb", "NBPT", "Glufosinate", "Fipronil", "Tora", "Glyphosate", "BPMC", "MAP", "Oxyfluorfen", "Glopost"
    };

//    private static final String [] mCeklistKeaktifanAgitatorTangki2 = new String[]{
//            "1" , "2"
//    };

    private static final String[] mCeklistKeaktifanAgitatorCameco2 = new String[]{
            "1", "2"
    };

    private static final String[] mAktivitas2 = new String[]{
            "Booster", "Cuci Bilas", "Foliar Spray", "Forcing", "Insectisida", "Pestisida", "Post Planting", "Pre Planting", "Repening", "Aplikasi Percoban"


    };

}