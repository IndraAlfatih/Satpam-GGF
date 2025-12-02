package com.ggf.qcpp.e_formpengamatan.finishing;

import static com.ggf.qcpp.utils.Utils.generateTglSekarang;
import static com.ggf.qcpp.utils.Utils.goToListPengamatan;

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
import com.ggf.qcpp.e_formpengamatan.finishing.model.FinishingModel;
import com.ggf.qcpp.e_formpengamatan.finishing.model.PlotModel;
import com.ggf.qcpp.e_formpengamatan.finishing.model.SampleModel;
import com.ggf.qcpp.network.SQLiteHelper;
import com.ggf.qcpp.ui.SweetDialogs;
import com.ggf.qcpp.utils.TemporaryFormStorage;
import com.google.gson.Gson;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FormPengamatanFinishing extends AppCompatActivity implements View.OnClickListener, IFormPengamatanFinishingView {

    @BindView(R.id.mSubmit)
    Button mSubmit;

    @BindView(R.id.mAddSample)
    ImageView mAddSample;

    @BindView(R.id.containerPlotData)
    LinearLayout containerPlotData;
    @BindView(R.id.mPlot)
    EditText mPlot;

    @BindView(R.id.mReworking)
    AutoCompleteTextView mReworking;
    @BindView(R.id.mSPK)
    EditText mSPK;
    @BindView(R.id.mJenisImplement)
    AutoCompleteTextView mJenisImplement;
    @BindView(R.id.mLine)
    EditText mLine;
    @BindView(R.id.mKeterangan)
    EditText mKeterangan;
    @BindView(R.id.mLokasi)
    EditText mLokasi;
    @BindView(R.id.mLuasPlot)
    EditText mLuasPlot;

    @BindView(R.id.autoComodityBajak)
    AutoCompleteTextView autoComodityBajak;

    @BindView(R.id.mLuasAktif)
    EditText mLuasAktif;

    @BindView(R.id.autoWilayah)
    AutoCompleteTextView autoWilayah;

    @BindView(R.id.mLolosAyakan)
    EditText mLolosAyakan;

    @BindView(R.id.mNoUnit)
    EditText mNoUnit;

    @BindView(R.id.mKet)
    EditText mKet;

    @BindView(R.id.mTidakLolosAyakan)
    EditText mTidakLolosAyakan;

    @BindView(R.id.mAplikasiKerataan)
    EditText mAplikasiKerataan;

    @BindView(R.id.automusim)
    AutoCompleteTextView automusim;

    @BindView(R.id.mStatusPengamatan)
    AutoCompleteTextView mStatusPengamatan;
    int index = 1 ;
    View rowView;
    View rowViewPlot;
    View viewnya = null;
    String plot = "0";
    FinishingModel model;

    List<PlotModel> dataPlot = new ArrayList<>();
    List<SampleModel> dataSample = new ArrayList<>();

    SweetAlertDialog sweetAlertDialog;
    SampleModel sampleModel = null;
    PlotModel plotModel = null;
    FormPengamatanFinishingPresenter presenter;

    private static final String DRAFT_KEY = "draft_finishing";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form_pengamatan_finishing);
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

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line , automusim2);
        automusim.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line , statuspengamatan2);
        mStatusPengamatan.setAdapter(adapter2);

        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, autoWilayah2);
        autoWilayah.setAdapter(adapter4);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mReworking2);
        mReworking.setAdapter(adapter3);

        ArrayAdapter<String> adapter5 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, autoComodityBajak2);
        autoComodityBajak.setAdapter(adapter5);

        ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mJenisImplement2);
        mJenisImplement.setAdapter(adapter6);

        EditText lokasiUppercase = findViewById(R.id.mLokasi);
        lokasiUppercase.setFilters(new InputFilter[] {new InputFilter.AllCaps()});

        // Menonaktifkan input teks, tetapi dropdown masih muncul
        automusim.setKeyListener(null);
        mStatusPengamatan.setKeyListener(null);
        mReworking.setKeyListener(null);
        autoComodityBajak.setKeyListener(null);
        autoWilayah.setKeyListener(null);
        mJenisImplement.setKeyListener(null);

        // Memastikan dropdown muncul meskipun tidak ada teks yang dimasukkan
        autoWilayah.setThreshold(1);
        automusim.setThreshold(1);
        mStatusPengamatan.setThreshold(1);
        mReworking.setThreshold(1);
        autoComodityBajak.setThreshold(1);
        mJenisImplement.setThreshold(1);

        // Atur threshold sesuai kebutuhan (misalnya 1 untuk memulai pencarian setelah 1 karakter)

        model = (FinishingModel) getIntent().getSerializableExtra("model");
        Log.d("bajakmodel", new Gson().toJson(model));

        presenter = new FormPengamatanFinishingPresenter(this);
        mSubmit.setOnClickListener(this);
        mAddSample.setOnClickListener(this);
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
        else if (automusim.getText().toString().equals("")) {
            Toast.makeText(this, "Musim tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mStatusPengamatan.getText().toString().equals("")) {
            Toast.makeText(this, "Status Pengamatan tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mNoUnit.getText().toString().equals("")) {
            Toast.makeText(this, "Nomor Unit Implement tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }else if (autoWilayah.getText().toString().equals("")) {
            Toast.makeText(this, "Wilayah tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mReworking.getText().toString().equals("")) {
            Toast.makeText(this, "Reworking tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (autoComodityBajak.getText().toString().equals("")) {
            Toast.makeText(this, "Comodity Bajak tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mJenisImplement.getText().toString().equals("")) {
            Toast.makeText(this, "Jenis Implement tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mPlot.getText().toString().equals("")) {
            Toast.makeText(this, "No Plot tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mLuasPlot.getText().toString().equals("")) {
            Toast.makeText(this, "Luas Plot tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mLolosAyakan.getText().toString().equals("")) {
            Toast.makeText(this, "Lolos Ayakan tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mTidakLolosAyakan.getText().toString().equals("")) {
            Toast.makeText(this, "Tidak Lolos Ayakan tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mAplikasiKerataan.getText().toString().equals("")) {
            Toast.makeText(this, "Kerataan Aplikasi tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mLuasAktif.getText().toString().equals("")) {
            Toast.makeText(this, "Luas Aktif tidak boleh kosong", Toast.LENGTH_SHORT).show();
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

    // di FormPengamatanFinishing.java
    private void clearForm() {
        // Kosongkan semua input header
        mSPK.setText("");
        mLine.setText("");
        mLokasi.setText("");
        autoWilayah.setText("");
        mKeterangan.setText("");

        automusim.setText("");
        mStatusPengamatan.setText("");
        mNoUnit.setText("");
        mReworking.setText("");
        autoComodityBajak.setText("");
        mJenisImplement.setText("");

        // Kosongkan data di memory
        dataPlot.clear();
        dataSample.clear();

        // Kosongkan container sample di UI
        containerPlotData.removeAllViews();

        // Reset model juga
        model = new FinishingModel();

        // Hapus draft tersimpan biar nggak balik lagi
        TemporaryFormStorage.clearDraft(this, DRAFT_KEY);

        Log.d("FormReset", "Form finishing cleared");
    }
    private void saveTemporaryData() {
        FinishingModel draft = new FinishingModel();
        draft.setNO_SPK(mSPK.getText().toString());
        draft.setNO_LINE(mLine.getText().toString());
        draft.setLOKASI(mLokasi.getText().toString());
        draft.setWILAYAH(autoWilayah.getText().toString());

        draft.setStd_musim(automusim.getText().toString());
        draft.setSTATUS_PENGAMATAN(mStatusPengamatan.getText().toString());
        draft.setNO_UNIT_IMPLEMENT(mNoUnit.getText().toString());
        draft.setReworking(mReworking.getText().toString());
        draft.setEx_comodity(autoComodityBajak.getText().toString());
        draft.setJenis_implement(mJenisImplement.getText().toString());

        draft.setDATA(dataPlot);

        TemporaryFormStorage.saveDraft(this, DRAFT_KEY, draft);
        Log.d("DraftSave", "Draft finishing saved: " + new Gson().toJson(draft));
    }

    private void loadTemporaryData() {
        FinishingModel draft = TemporaryFormStorage.loadDraft(this, DRAFT_KEY, FinishingModel.class);
        if (draft != null) {
            Log.d("DraftLoad", "Draft finishing loaded: " + new Gson().toJson(draft));

            mSPK.setText(draft.getNO_SPK());
            mLine.setText(draft.getNO_LINE());
            mLokasi.setText(draft.getLOKASI());
            autoWilayah.setText(draft.getWILAYAH());

            automusim.setText(draft.getStd_musim());
            mStatusPengamatan.setText(draft.getSTATUS_PENGAMATAN());
            mNoUnit.setText(draft.getNO_UNIT_IMPLEMENT());
            mReworking.setText(draft.getReworking());
            autoComodityBajak.setText(draft.getEx_comodity());
            mJenisImplement.setText(draft.getJenis_implement());


            containerPlotData.removeAllViews();
            dataPlot.clear();

            if (draft.getDATA() != null) {
                dataPlot = draft.getDATA();
                for (PlotModel p : dataPlot) {
                    for (SampleModel s : p.getSAMPLE()) {
                        restoreSampleView(s);
                    }
                }
            }

            // ✅ Tambahkan ini di akhir: rebind adapter biar dropdown aktif lagi
            automusim.post(() -> {
                ArrayAdapter<String> adapterAutomusim =
                        new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, automusim2);
                automusim.setAdapter(adapterAutomusim);
            });

            mStatusPengamatan.post(() -> {
                ArrayAdapter<String> adapterStatusPengamatan =
                        new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, statuspengamatan2);
                mStatusPengamatan.setAdapter(adapterStatusPengamatan);
            });

            mReworking.post(() -> {
                ArrayAdapter<String> adapterReworking =
                        new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, mReworking2);
                mReworking.setAdapter(adapterReworking);
            });

            autoComodityBajak.post(() -> {
                ArrayAdapter<String> adapterComodityBajak =
                        new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, autoComodityBajak2);
                autoComodityBajak.setAdapter(adapterComodityBajak);
            });

            autoWilayah.post(() -> {
                ArrayAdapter<String> adapterWilayah =
                        new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, autoWilayah2);
                autoWilayah.setAdapter(adapterWilayah);
            });

            mJenisImplement.post(() -> {
                ArrayAdapter<String> adapterJenisImplement =
                        new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, mJenisImplement2);
                mJenisImplement.setAdapter(adapterJenisImplement);
            });

        }
    }

    // fungsi restore view dari sample
    private void restoreSampleView(SampleModel sample) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.data_finishing_sample_field, null);

        TextView noSample = rowView.findViewById(R.id.mNoSample);
        TextView aplikasiKerataan = rowView.findViewById(R.id.mAplikasiKerataan);
        TextView lolosAyakan = rowView.findViewById(R.id.mLolosAyakan);
        TextView tidakLolosAyakan = rowView.findViewById(R.id.mTidakLolosAyakan);
        TextView no_plot = rowView.findViewById(R.id.mPlot);

        noSample.setText(String.valueOf(sample.getNo_sample()));
        aplikasiKerataan.setText(String.valueOf(sample.getAPLIKASI_KERATAAN()));
        lolosAyakan.setText(String.valueOf(sample.getLOLOS_AYAKAN()));
        tidakLolosAyakan.setText(String.valueOf(sample.getTIDAK_LOLOS_AYAKAN()));
        no_plot.setText(String.valueOf(sample.getPLOT()));

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



    void addSampleForm() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rowView = inflater.inflate(R.layout.data_finishing_sample_field, null);



//
        final TextView noSample = rowView.findViewById(R.id.mNoSample);
        final TextView aplikasiKerataan = rowView.findViewById(R.id.mAplikasiKerataan);
        final TextView lolosAyakan = rowView.findViewById(R.id.mLolosAyakan);
        final TextView tidakLolosAyakan = rowView.findViewById(R.id.mTidakLolosAyakan);
        final TextView no_plot = rowView.findViewById(R.id.mPlot);
//        noSample.setText(String.valueOf(index));
        aplikasiKerataan.setText(mAplikasiKerataan.getText().toString());
        lolosAyakan.setText(mLolosAyakan.getText().toString());
        tidakLolosAyakan.setText(mTidakLolosAyakan.getText().toString());
        no_plot.setText(mPlot.getText().toString());
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
        sampleModel.setNo_spk(model.getNO_SPK());
        sampleModel.setNo_sample(Integer.parseInt(noSample.getText().toString()));
        sampleModel.setPLOT(Integer.parseInt(no_plot.getText().toString()));
        sampleModel.setLokasi(mLokasi.getText().toString());
        sampleModel.setStd_musim(automusim.getText().toString());
        sampleModel.setNo_spk2(mSPK.getText().toString());
        sampleModel.setNo_line(mLine.getText().toString());
        sampleModel.setJenis_implement(mJenisImplement.getText().toString());
        sampleModel.setWil(autoWilayah.getText().toString());
        sampleModel.setReworking(mReworking.getText().toString());
        sampleModel.setStatus_pengamatan(mStatusPengamatan.getText().toString());
        sampleModel.setNo_unit_implement(mNoUnit.getText().toString());
        sampleModel.setEx_comodity(autoComodityBajak.getText().toString());
        sampleModel.setLuas_plot(Float.parseFloat(mLuasPlot.getText().toString()));
        sampleModel.setLuas_aktif(Float.parseFloat(mLuasAktif.getText().toString()));
        sampleModel.setAPLIKASI_KERATAAN(Float.parseFloat(aplikasiKerataan.getText().toString()));
        sampleModel.setLOLOS_AYAKAN(Float.parseFloat(lolosAyakan.getText().toString()));
        sampleModel.setTIDAK_LOLOS_AYAKAN(Float.parseFloat(tidakLolosAyakan.getText().toString()));
        sampleModel.setKeterangan(
                mKet.getText().toString().trim().isEmpty() ? "-" : mKet.getText().toString().trim()
        );

//        dataSample.add(sampleModel);
        dataSample.add(sampleModel);
        Log.d("datanyanih", new Gson().toJson(model));
        containerPlotData.addView(rowView, 0);

        //Hapus Saat Apply
        Toast.makeText(this, "Data Sudah Ditambahkan", Toast.LENGTH_SHORT).show();
        mLolosAyakan.getText().clear();
        mTidakLolosAyakan.getText().clear();
        mAplikasiKerataan.getText().clear();

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
        if(dataPlot.size()>0) {
//            sampleModel.setKeterangan(mKeterangan.getText().toString());
//            plotModel.setSAMPLE(dataSample);
            model.setLOKASI(mLokasi.getText().toString());
            model.setWILAYAH(autoWilayah.getText().toString());
            model.setDATA(dataPlot);
            Log.d("databody", new Gson().toJson(model));
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
        String message = "Finishing"
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

        TemporaryFormStorage.clearDraft(this, "draft_finishing");
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
    private static final String [] automusim2 = new String[]{
            "Musim Kering" , "Musim Basah"
    };

    private static final String [] statuspengamatan2 = new String[]{
            "Inprocess" , "Crosscheck"
    };
    private static final String[] autoComodityBajak2 = new String[]{
            "Nanas", "Singkong", "Pisang"
    };
    private static final String[] mReworking2 = new String[]{
            "Sebelum Reworking", "Sesudah Reworking"
    };
    private static final String[] mJenisImplement2 = new String[]{
            "Finishing Harrow", "Rotari Ridger"
    };
    private static final String[] autoWilayah2 = new String[]{
            "AW01", "AW02", "AW03", "AW04", "AW05", "AW06", "AW07", "AW08", "AW09", "AW10","AW11", "AW12", "AW13", "AW14", "AW15", "AW16", "AW17", "AW18", "AW19", "AW20", "AW21", "AW22", "AW23"
    };
}