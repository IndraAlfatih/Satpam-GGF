package com.ggf.qcpp.e_formpengamatan.jalansaluran;

import static com.ggf.qcpp.utils.Utils.goToListPengamatan;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.ggf.qcpp.e_formpengamatan.jalansaluran.model.PlotModel;
import com.ggf.qcpp.e_formpengamatan.jalansaluran.model.SampleModel;
import com.ggf.qcpp.e_formpengamatan.jalansaluran.model.JalanSaluranModel;
import com.ggf.qcpp.network.SQLiteHelper;
import com.ggf.qcpp.ui.SweetDialogs;
import com.google.gson.Gson;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FormPengamatanJalanSaluran extends AppCompatActivity implements  View.OnClickListener , IFormPengamatanJalanSaluranView {
    @BindView(R.id.mSubmit)
    Button mSubmit;

    @BindView(R.id.mAddSample)
    ImageView mAddSample;

    @BindView(R.id.containerPlotData)
    LinearLayout containerPlotData;
    @BindView(R.id.mPlot)
    EditText mPlot;

    @BindView(R.id.mLokasi)
    EditText mLokasi;

    @BindView(R.id.mBlock)
    EditText mBlock;

    @BindView(R.id.mLuasPlot)
    EditText mLuasPlot;
    @BindView(R.id.mPlot360)
    EditText mPlot360;

    @BindView(R.id.mSeksi)
    EditText mSeksi;

    @BindView(R.id.mPerimeter)
    EditText mPerimeter;
    @BindView(R.id.mSekunder)
    EditText mSekunder;
    @BindView(R.id.mTersier)
    EditText mTersier;
    @BindView(R.id.mJalanSaluranRipper)
    EditText mJalanSaluranRipper;

    @BindView(R.id.mTersierExamini)
    EditText mTersierExamini;

    @BindView(R.id.mSaluranTraktor)
    EditText mSaluranTraktor;

    @BindView(R.id.mJumlahSaluranTersier)
    EditText mJumlahSaluranTersier;

    @BindView(R.id.mTersierDindingAtas)
    EditText mTersierDindingAtas;

    @BindView(R.id.mTersierErosi)
    EditText mTersierErosi;

    @BindView(R.id.mSekunderDindingAtas)
    EditText mSekunderDindingAtas;

    @BindView(R.id.mSekunderErosi)
    EditText mSekunderErosi;

    @BindView(R.id.mKeterangan)
    EditText mKeterangan;

    View rowView;
    View rowViewPlot;
    View viewnya = null ;
    String plot = "0" ;
    JalanSaluranModel model;

    List<PlotModel> dataPlot = new ArrayList<>() ;
    List<SampleModel> dataSample = new ArrayList<>() ;

    SweetAlertDialog sweetAlertDialog;
    SampleModel sampleModel = null;
    PlotModel plotModel = null ;
    FormPengamatanJalanSaluranPresenter presenter;
    int index = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form_pengamatan_jalan_saluran);
        ButterKnife.bind(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        model = (JalanSaluranModel) getIntent().getSerializableExtra("model");
        Log.d("bajakmodel" , new Gson().toJson(model));

        presenter = new FormPengamatanJalanSaluranPresenter(this);
        mSubmit.setOnClickListener(this);
        mAddSample.setOnClickListener(this);
    }

    void addPlotForm() {

        //Tidak boleh kosong
        if (mLokasi.getText().toString().equals("")) {
            Toast.makeText(this, "Data tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mPlot.getText().toString().equals("")) {
            Toast.makeText(this, "Data tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mLuasPlot.getText().toString().equals("")) {
            Toast.makeText(this, "Data tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mBlock.getText().toString().equals("")) {
            Toast.makeText(this, "Data tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mPlot360.getText().toString().equals("")) {
            Toast.makeText(this, "Data tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mSeksi.getText().toString().equals("")) {
            Toast.makeText(this, "Data tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mPerimeter.getText().toString().equals("")) {
            Toast.makeText(this, "Data tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mSekunder.getText().toString().equals("")) {
            Toast.makeText(this, "Data tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mTersier.getText().toString().equals("")) {
            Toast.makeText(this, "Data tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mJalanSaluranRipper.getText().toString().equals("")) {
            Toast.makeText(this, "Data tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mTersierExamini.getText().toString().equals("")) {
            Toast.makeText(this, "Data tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mSaluranTraktor.getText().toString().equals("")) {
            Toast.makeText(this, "Data tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mJumlahSaluranTersier.getText().toString().equals("")) {
            Toast.makeText(this, "Data tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mTersierDindingAtas.getText().toString().equals("")) {
            Toast.makeText(this, "Data tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mTersierErosi.getText().toString().equals("")) {
            Toast.makeText(this, "Data tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mSekunderDindingAtas.getText().toString().equals("")) {
            Toast.makeText(this, "Data tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if (mSekunderErosi.getText().toString().equals("")) {
            Toast.makeText(this, "Data tidak boleh kosong", Toast.LENGTH_SHORT).show();
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
        rowView = inflater.inflate(R.layout.data_jalansaluran_sample_field, null);
//        final LinearLayout containerSampleData = view.findViewById(R.id.containerSampleData);

//
        final TextView block = rowView.findViewById(R.id.mBlock);
        final TextView plot360 = rowView.findViewById(R.id.mPlot360);
        final TextView seksi = rowView.findViewById(R.id.mSeksi);
        final TextView perimeter = rowView.findViewById(R.id.mPerimeter);
        final TextView sekunder = rowView.findViewById(R.id.mSekunder);
        final TextView tersier = rowView.findViewById(R.id.mTersier);
        final TextView jalanSaluranRipper = rowView.findViewById(R.id.mJalanSaluranRipper);
        block.setText(mBlock.getText().toString());
        plot360.setText(mPlot360.getText().toString());
        seksi.setText(mSeksi.getText().toString());
        perimeter.setText(mPerimeter.getText().toString());
        sekunder.setText(mSekunder.getText().toString());
        tersier.setText(mTersier.getText().toString());
        jalanSaluranRipper.setText(mJalanSaluranRipper.getText().toString());

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

//        nosample.setText(String.valueOf(nextSampleNumber));
        sampleModel.setNo_spk(model.getNO_SPK());
        sampleModel.setLokasi(mLokasi.getText().toString());
        sampleModel.setPLOT(Integer.parseInt(mPlot.getText().toString()));
        sampleModel.setLuas_plot(Float.parseFloat(mLuasPlot.getText().toString()));
        sampleModel.setJalan_block(Float.parseFloat(mBlock.getText().toString()));
        sampleModel.setJalan_plot(Float.parseFloat(mPlot360.getText().toString()));
        sampleModel.setJalan_seksi(Float.parseFloat(mSeksi.getText().toString()));
        sampleModel.setJalan_perimeter(Float.parseFloat(mPerimeter.getText().toString()));
        sampleModel.setSaluran_sekunder(Float.parseFloat(mSekunder.getText().toString()));
        sampleModel.setSaluran_tersier(Float.parseFloat(mTersier.getText().toString()));
        sampleModel.setJalan_saluran_tidak_ada_ripper(Float.parseFloat(mJalanSaluranRipper.getText().toString()));
        sampleModel.setSaluran_tersier_traktor(Float.parseFloat(mSaluranTraktor.getText().toString()));
        sampleModel.setJumlah_saluran_tersier(Integer.parseInt(mJumlahSaluranTersier.getText().toString()));
        sampleModel.setLebar_tersier_dinding_atas(Float.parseFloat(mTersierDindingAtas.getText().toString()));
        sampleModel.setLebar_tersier_erosi(Float.parseFloat(mTersierErosi.getText().toString()));
        sampleModel.setNo_sample(nextSampleNumber);
        sampleModel.setLebar_sekunder_dinding_atas(Float.parseFloat(mSekunderDindingAtas.getText().toString()));
        sampleModel.setLebar_sekunder_erosi(Float.parseFloat(mSekunderErosi.getText().toString()));
        sampleModel.setKeterangan((mKeterangan.getText().toString().contains(""))
                ? "-" : mKeterangan.getText().toString());
//        dataSample.add(sampleModel);
        dataSample.add(sampleModel);
        Log.d("datanyanih" , new Gson().toJson(model));
        containerPlotData.addView(rowView, 0);

        //Hapus Saat Apply
        Toast.makeText(this, "Data Sudah Ditambahkan", Toast.LENGTH_SHORT).show();
        mBlock.getText().clear();
        mPlot360.getText().clear();
        mSeksi.getText().clear();
        mPerimeter.getText().clear();
        mSekunder.getText().clear();
        mTersier.getText().clear();
        mJalanSaluranRipper.getText().clear();
        mTersierExamini.getText().clear();
        mSaluranTraktor.getText().clear();
        mJumlahSaluranTersier.getText().clear();
        mTersierDindingAtas.getText().clear();
        mTersierErosi.getText().clear();
        mSekunderDindingAtas.getText().clear();
        mSekunderErosi.getText().clear();
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
        plotModel.setSAMPLE(dataSample);
        model.setLOKASI(mLokasi.getText().toString());

        model.setDATA(dataPlot);
        Log.d("dataBody" , new Gson().toJson(model));
        presenter.createPengamatan(model);
    }
    @Override
    public void onCreateSuccess(String rm) {
//        Toast.makeText(this, "woi", Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, rm, Toast.LENGTH_SHORT).show();
        SweetDialogs.commonSuccessWithIntent(this, "Jalan Saluran Berhasil Di Kirim"  ,string -> {
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
        Log.e("errornya", cause);
        Log.e("errornya", cause);
        SQLiteHelper dbHelper = new SQLiteHelper(this);
        dbHelper.saveChopperData(data,model.getNO_SPK());  // Assuming 'data' is a JSON string

        Log.d("Saved data", "Data saved to SQLite: " + data);

        // Show a dialog indicating that the data has been saved offline
        SweetDialogs.commonError(this, App.getApplication().getString(R.string.notif_offline_mode), false);
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
}