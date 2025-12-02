package com.ggf.qcpp.e_formpengamatan.kebersihanbonggol;

import static com.ggf.qcpp.utils.Utils.generateTglSekarang;
import static com.ggf.qcpp.utils.Utils.goToListPengamatan;
import static com.ggf.qcpp.utils.Utils.parseFloatDefault;

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

import com.ggf.qcpp.App;
import com.ggf.qcpp.R;
import com.ggf.qcpp.e_formpengamatan.kebersihanbonggol.model.PlotModel;
import com.ggf.qcpp.e_formpengamatan.kebersihanbonggol.model.SampleModel;
import com.ggf.qcpp.e_formpengamatan.kebersihanbonggol.model.KebersihanBonggolModel;
import com.ggf.qcpp.network.SQLiteHelper;
import com.ggf.qcpp.ui.SweetDialogs;
import com.google.gson.Gson;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FormPengamatanKebersihanBonggol extends AppCompatActivity implements View.OnClickListener , IFormPengamatanKebersihanBonggolView {

    @BindView(R.id.mAddSample)
    ImageView mAddSample;

    @BindView(R.id.mSubmit)
    Button mSubmit;
    @BindView(R.id.containerPlotData)
    LinearLayout containerPlotData;
    @BindView(R.id.mPlot)
    EditText mPlot;

    @BindView(R.id.autoWilayah)
    AutoCompleteTextView autoWilayah;

    @BindView(R.id.mLokasi)
    EditText mLokasi;

    @BindView(R.id.mBonggolSegarLebihDari)
    EditText mBonggolSegarLebihDari;
    @BindView(R.id.mBonggolSegarKurangDari)
    EditText mBonggolSegarKurangDari;

    @BindView(R.id.mEstimasi)
    EditText mEstimasi;

    @BindView(R.id.mLuasPlot)
    EditText mLuasPlot;

    @BindView(R.id.mKeterangan)
    EditText mKeterangan;

    @BindView(R.id.automusim)
    AutoCompleteTextView automusim;



    int index = 1 ;
    View rowView;
    View rowViewPlot;
    View viewnya = null;
    String plot = "0";
    KebersihanBonggolModel model;

    List<PlotModel> dataPlot = new ArrayList<>();
    List<SampleModel> dataSample = new ArrayList<>();

    SweetAlertDialog sweetAlertDialog;
    SampleModel sampleModel = null;
    PlotModel plotModel = null;
    FormPengamatanKebersihanBonggolPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_form_pengamatan_kebersihan_bonggol);
        ButterKnife.bind(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line , automusim2);
        automusim.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, autoWilayah2);
        autoWilayah.setAdapter(adapter2);

        // Menonaktifkan input teks, tetapi dropdown masih muncul
        automusim.setKeyListener(null);

        // Memastikan dropdown muncul meskipun tidak ada teks yang dimasukkan
        automusim.setThreshold(1);
        // Atur threshold sesuai kebutuhan (misalnya 1 untuk memulai pencarian setelah 1 karakter)

        model = (KebersihanBonggolModel) getIntent().getSerializableExtra("model");
        Log.d("bajakmodel", new Gson().toJson(model));

        presenter = new FormPengamatanKebersihanBonggolPresenter(this);
        mSubmit.setOnClickListener(this);
        mAddSample.setOnClickListener(this);
    }

    void addPlotForm() {

        //Tidak boleh kosong
        if (mLokasi.getText().toString().equals("")) {
            Toast.makeText(this, "Lokasi tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (automusim.getText().toString().equals("")) {
            Toast.makeText(this, "Musim tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (autoWilayah.getText().toString().equals("")) {
            Toast.makeText(this, "Wilayah tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mPlot.getText().toString().equals("")) {
            Toast.makeText(this, "No Plot tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mLuasPlot.getText().toString().equals("")) {
            Toast.makeText(this, "Luas Plot tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mEstimasi.getText().toString().equals("")) {
            Toast.makeText(this, "Estimasi tidak boleh kosong", Toast.LENGTH_SHORT).show();
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
        rowView = inflater.inflate(R.layout.data_kebersihanbonggol_sample_field, null);


//
        final TextView mNoSample = rowView.findViewById(R.id.mNoSample);
        final TextView bonggolSegarKurangDari = rowView.findViewById(R.id.mBonggolSegarKurangDari);
        final TextView bonggolSegarLebihDari = rowView.findViewById(R.id.mBonggolSegarLebihDari);
        final TextView estimasi = rowView.findViewById(R.id.mEstimasi);
        final TextView no_plot = rowView.findViewById(R.id.mPlot);
        final TextView luasplot = rowView.findViewById(R.id.mLuasPlot);

        bonggolSegarKurangDari.setText(mBonggolSegarKurangDari.getText().toString());
        bonggolSegarLebihDari.setText(mBonggolSegarLebihDari.getText().toString());
        estimasi.setText(mEstimasi.getText().toString());
        no_plot.setText(mPlot.getText().toString());
        luasplot.setText(mLuasPlot.getText().toString());



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

        mNoSample.setText(String.valueOf(nextSampleNumber));

        sampleModel.setNo_spk(model.getNO_SPK());
        sampleModel.setLokasi(mLokasi.getText().toString());
        sampleModel.setStd_musim(automusim.getText().toString());
        sampleModel.setPlot(Integer.parseInt(mPlot.getText().toString()));
        sampleModel.setLuas_plot(Float.parseFloat(mLuasPlot.getText().toString()));
        sampleModel.setWil(autoWilayah.getText().toString());
        sampleModel.setBONGGOL_SEGAR_KURANG_DARI(parseFloatDefault(mBonggolSegarKurangDari.getText().toString(), 0.0f));
        sampleModel.setBONGGOL_SEGAR_LEBIH_DARI(parseFloatDefault(mBonggolSegarLebihDari.getText().toString(), 0.0f));
        sampleModel.setNo_sample(Integer.parseInt(mNoSample.getText().toString()));
        sampleModel.setESTIMASI(Float.parseFloat(mEstimasi.getText().toString()));
        sampleModel.setKeterangan(mKeterangan.getText().toString());
//        dataSample.add(sampleModel);
        dataSample.add(sampleModel);
        Log.d("datanyanih", new Gson().toJson(model));
        containerPlotData.addView(rowView, 0);

        //Hapus Saat Apply
        Toast.makeText(this, "Data Sudah Ditambahkan", Toast.LENGTH_SHORT).show();
        mBonggolSegarKurangDari.getText().clear();
        mBonggolSegarLebihDari.getText().clear();
        mEstimasi.getText().clear();
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
        plotModel.setSAMPLE(dataSample);
        model.setLOKASI(mLokasi.getText().toString());
        model.setWILAYAH(autoWilayah.getText().toString());
        model.setDATA(dataPlot);

        Log.d("dataBody" , new Gson().toJson(model));
        presenter.createPengamatan(model);
    }

    @Override
    public void onCreateSuccess(String rm) {
          String message = "Kebersihan Bonggol Berhasil di Kirim" + "\n\"" + generateTglSekarang() + "\"\nLokasi: " + mLokasi.getText().toString();
            SweetDialogs.commonSuccessWithIntent(this,message, string -> {
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
    public void onNetworkError(String cause,String data) {

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

    private static final String [] automusim2 = new String[]{
            "Musim Kering" , "Musim Basah"
    };

    private static final String[] autoWilayah2 = new String[]{
            "AW01", "AW02", "AW03", "AW04", "AW05", "AW06", "AW07", "AW08", "AW09", "AW10","AW11", "AW12", "AW13", "AW14", "AW15", "AW16", "AW17", "AW18", "AW19", "AW20", "AW21", "AW22", "AW23"
    };
}