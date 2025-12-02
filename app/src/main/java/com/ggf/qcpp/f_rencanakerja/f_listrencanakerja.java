package com.ggf.qcpp.f_rencanakerja;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ggf.qcpp.R;
import com.ggf.qcpp.e_formpengamatan.bajak.FormPengamatanBajak;
import com.ggf.qcpp.e_formpengamatan.bajak.model.BajakModel;
import com.ggf.qcpp.e_formpengamatan.chopper.FormPengamatanChopper;
import com.ggf.qcpp.e_formpengamatan.chopper.model.ChopperModel;
import com.ggf.qcpp.e_formpengamatan.dropbibit.FormPengamatanDropBibit;
import com.ggf.qcpp.e_formpengamatan.dropbibit.model.DropBibitModel;
import com.ggf.qcpp.e_formpengamatan.finishing.FormPengamatanFinishing;
import com.ggf.qcpp.e_formpengamatan.finishing.model.FinishingModel;
import com.ggf.qcpp.e_formpengamatan.jalansaluran.FormPengamatanJalanSaluran;
import com.ggf.qcpp.e_formpengamatan.jalansaluran.model.JalanSaluranModel;
import com.ggf.qcpp.e_formpengamatan.kebersihanbonggol.FormPengamatanKebersihanBonggol;
import com.ggf.qcpp.e_formpengamatan.kebersihanbonggol.model.KebersihanBonggolModel;
import com.ggf.qcpp.e_formpengamatan.petikbibit.FormPengamatanPetikBibit;
import com.ggf.qcpp.e_formpengamatan.petikbibit.model.PetikBibitModel;
import com.ggf.qcpp.e_formpengamatan.phtanah.FormPengamatanPhTanah;
import com.ggf.qcpp.e_formpengamatan.phtanah.model.PhtanahModel;
import com.ggf.qcpp.e_formpengamatan.pooldipping.FormPengamatanPoolDipping;
import com.ggf.qcpp.e_formpengamatan.pooldipping.model.PoolDippingModel;
import com.ggf.qcpp.e_formpengamatan.ridger.FormPengamatanRidger;
import com.ggf.qcpp.e_formpengamatan.ridger.model.RidgerModel;
import com.ggf.qcpp.e_formpengamatan.transport.FormPengamatanTransport;

import butterknife.BindView;
import butterknife.ButterKnife;

public class f_listrencanakerja extends AppCompatActivity {
    @BindView(R.id.mNoSpk)
    TextView mNoSpk;

    @BindView(R.id.mKabag)
    TextView mKabag;

    @BindView(R.id.mKasie)
    TextView mKasie;

    @BindView(R.id.mMandor)
    TextView mMandor;

    @BindView(R.id.mNoline)
    TextView mNoline;

    @BindView(R.id.mLokasi)
    TextView mLokasi;

    @BindView(R.id.mKategori)
    TextView mKategori;

    @BindView(R.id.mLuasNetto)
    TextView mLuasNetto;
    String[] itemShift = {"Siang", "Malam"};
    String[] itemWilayah = {"PG 1", "PG 2", "PG 3"};
    String[] itemMandor = {"Raditya Mahendra", "Iqbal Maulana", "Anggi Agung"};

    LinearLayout buttonBack, dropBibit,transport,plahan,kebersihanbonggol, JalanSaluran,chopper, agregat, bajak, ridger , finishing , petikBibit,poolDipping,pHTanah,bajakSingkong;
    Button buttonLanjut;

    AutoCompleteTextView autoCompleteTextView;

    ArrayAdapter<String> adapterItem;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_listrencanakerja);
        ButterKnife.bind(this);
        //fungsi tombol chopper
        chopper = findViewById(R.id.chopper);
        chopper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChopperModel model = new ChopperModel();
                ChopperModel.verified mandor = model. new verified();
                ChopperModel.verified.verified_by mand = mandor. new verified_by();
                model.setNO_SPK(mNoSpk.getText().toString());
                model.setNO_LINE(mNoline.getText().toString());
                model.setNO_UNIT_IMPLEMENT("1110");
                model.setLOKASI(mLokasi.getText().toString());
                model.setKATEGORI(mKategori.getText().toString());
                model.setLUAS_NETTO(mLuasNetto.getText().toString());
                mand.setNama("yanto");
                mandor.setVERIFIED_MANDOR(mand);
                model.setVERIFIED(mandor);

                Intent gochopper = new Intent(f_listrencanakerja.this, FormPengamatanChopper.class);
                gochopper.putExtra("model", model);
                startActivity(gochopper);
            }
        });
        //--------------------------------------------------------------------------------------

        //fungsi tombol bajak
        bajak = findViewById(R.id.bajak);
        bajak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BajakModel model = new BajakModel();
                BajakModel.verified mandor = model. new verified();
                BajakModel.verified.verified_by mand = mandor. new verified_by();
                model.setNO_SPK(mNoSpk.getText().toString());
                model.setNO_LINE(mNoline.getText().toString());
                model.setNO_UNIT_IMPLEMENT("1110");
                model.setLOKASI(mLokasi.getText().toString());
                model.setKATEGORI("Bajak");
                model.setLUAS_NETTO(mLuasNetto.getText().toString());
                mand.setNama("mandor");
                mandor.setVERIFIED_MANDOR(mand);
                model.setVERIFIED(mandor);
                Intent gobajak = new Intent(f_listrencanakerja.this, FormPengamatanBajak.class);
                gobajak.putExtra("model", model);
                startActivity(gobajak);
            }
        });

        bajakSingkong = findViewById(R.id.bajakSingkong);
        bajakSingkong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BajakModel model = new BajakModel();
                BajakModel.verified mandor = model. new verified();
                BajakModel.verified.verified_by mand = mandor. new verified_by();
                model.setNO_SPK(mNoSpk.getText().toString());
                model.setNO_LINE(mNoline.getText().toString());
                model.setNO_UNIT_IMPLEMENT("1110");
                model.setLOKASI(mLokasi.getText().toString());
                model.setKATEGORI("Bajak");
                model.setLUAS_NETTO(mLuasNetto.getText().toString());
                mand.setNama("mandor");
                mandor.setVERIFIED_MANDOR(mand);
                model.setVERIFIED(mandor);
                Intent gobajak = new Intent(f_listrencanakerja.this, FormPengamatanBajak.class);
                gobajak.putExtra("model", model);
                startActivity(gobajak);
            }
        });


         finishing = findViewById(R.id.finishing);
        finishing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FinishingModel model = new FinishingModel();
                FinishingModel.verified mandor = model. new verified();
                FinishingModel.verified.verified_by mand = mandor. new verified_by();
                model.setNO_SPK(mNoSpk.getText().toString());
                model.setNO_LINE(mNoline.getText().toString());
                model.setNO_UNIT_IMPLEMENT("1110");
                model.setLOKASI(mLokasi.getText().toString());
                model.setKATEGORI("Finishing");
                model.setLUAS_NETTO(mLuasNetto.getText().toString());
                mand.setNama("mandor");
                mandor.setVERIFIED_MANDOR(mand);
                model.setVERIFIED(mandor);
                Intent gofinishing = new Intent(f_listrencanakerja.this, FormPengamatanFinishing.class);
                gofinishing.putExtra("model", model);
                startActivity(gofinishing);
            }
        });

        petikBibit = findViewById(R.id.petikBibit);
        petikBibit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PetikBibitModel model = new PetikBibitModel();
                PetikBibitModel.verified mandor = model. new verified();
                PetikBibitModel.verified.verified_by mand = mandor. new verified_by();
                model.setNO_SPK(mNoSpk.getText().toString());
                model.setNO_LINE(mNoline.getText().toString());
                model.setNO_UNIT_IMPLEMENT("1110");
                model.setLOKASI(mLokasi.getText().toString());
                model.setKATEGORI("Petik Bibit");
                model.setLUAS_NETTO(mLuasNetto.getText().toString());
                mand.setNama("mandor");
                mandor.setVERIFIED_MANDOR(mand);
                model.setVERIFIED(mandor);
                Intent gofinishing = new Intent(f_listrencanakerja.this, FormPengamatanPetikBibit.class);
                gofinishing.putExtra("model", model);
                startActivity(gofinishing);
            }
        });

        poolDipping = findViewById(R.id.poolDipping);
        poolDipping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PoolDippingModel model = new PoolDippingModel();
                PoolDippingModel.verified mandor = model. new verified();
                PoolDippingModel.verified.verified_by mand = mandor. new verified_by();
                model.setNO_SPK(mNoSpk.getText().toString());
                model.setNO_LINE(mNoline.getText().toString());
                model.setNO_UNIT_IMPLEMENT("1110");
                model.setLOKASI(mLokasi.getText().toString());
                model.setKATEGORI("Petik Bibit");
                model.setLUAS_NETTO(mLuasNetto.getText().toString());
                mand.setNama("mandor");
                mandor.setVERIFIED_MANDOR(mand);
                model.setVERIFIED(mandor);
                Intent goDipping = new Intent(f_listrencanakerja.this, FormPengamatanPoolDipping.class);
                goDipping.putExtra("model", model);
                startActivity(goDipping);
            }
        });
        //--------------------------------------------------------------------------------------
//        Toast.makeText(this, "perintah kerja", Toast.LENGTH_SHORT).show();
        //fungsi tombol ridger
        ridger = findViewById(R.id.ridger);
        ridger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RidgerModel model = new RidgerModel();
                RidgerModel.verified mandor = model. new verified();
                RidgerModel.verified.verified_by mand = mandor. new verified_by();
                model.setNO_SPK(mNoSpk.getText().toString());
                model.setNO_LINE(mNoline.getText().toString());
                model.setNO_UNIT_IMPLEMENT("1110");
                model.setLOKASI(mLokasi.getText().toString());
                model.setKATEGORI("Ridger");
                model.setLUAS_NETTO(mLuasNetto.getText().toString());
                mand.setNama("mandor");
                mandor.setVERIFIED_MANDOR(mand);
                model.setVERIFIED(mandor);
                Intent goRidger = new Intent(f_listrencanakerja.this, FormPengamatanRidger.class);
                goRidger.putExtra("model", model);
                startActivity(goRidger);
            }
        });

        pHTanah = findViewById(R.id.pHTanah);
        pHTanah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhtanahModel model = new PhtanahModel();
                PhtanahModel.verified mandor = model. new verified();
                PhtanahModel.verified.verified_by mand = mandor. new verified_by();
                model.setNO_SPK(mNoSpk.getText().toString());
                model.setNO_LINE(mNoline.getText().toString());
                model.setNO_UNIT_IMPLEMENT("1110");
                model.setLOKASI(mLokasi.getText().toString());
                model.setKATEGORI("PH TANAH");
                model.setLUAS_NETTO(mLuasNetto.getText().toString());
                mand.setNama("mandor");
                mandor.setVERIFIED_MANDOR(mand);
                model.setVERIFIED(mandor);
                Intent goRidger = new Intent(f_listrencanakerja.this, FormPengamatanPhTanah.class);
                goRidger.putExtra("model", model);
                startActivity(goRidger);
            }
        });

        JalanSaluran = findViewById(R.id.JalanSaluran);
        JalanSaluran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JalanSaluranModel model = new JalanSaluranModel();
                JalanSaluranModel.verified mandor = model. new verified();
                JalanSaluranModel.verified.verified_by mand = mandor. new verified_by();
                model.setNO_SPK(mNoSpk.getText().toString());
                model.setNO_LINE(mNoline.getText().toString());
                model.setNO_UNIT_IMPLEMENT("1110");
                model.setLOKASI(mLokasi.getText().toString());
                model.setKATEGORI("Jalan Saluran");
                model.setLUAS_NETTO(mLuasNetto.getText().toString());
                mand.setNama("mandor");
                mandor.setVERIFIED_MANDOR(mand);
                model.setVERIFIED(mandor);
                Intent goRidger = new Intent(f_listrencanakerja.this, FormPengamatanJalanSaluran.class);
                goRidger.putExtra("model", model);
                startActivity(goRidger);
            }
        });

        kebersihanbonggol = findViewById(R.id.kebersihanbonggol);
        kebersihanbonggol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KebersihanBonggolModel model = new KebersihanBonggolModel();
                KebersihanBonggolModel.verified mandor = model. new verified();
                KebersihanBonggolModel.verified.verified_by mand = mandor. new verified_by();
                model.setNO_SPK(mNoSpk.getText().toString());
                model.setNO_LINE(mNoline.getText().toString());
                model.setNO_UNIT_IMPLEMENT("1110");
                model.setLOKASI(mLokasi.getText().toString());
                model.setKATEGORI("Kebersihan Bonggol");
                model.setLUAS_NETTO(mLuasNetto.getText().toString());
                mand.setNama("mandor");
                mandor.setVERIFIED_MANDOR(mand);
                model.setVERIFIED(mandor);
                Intent goRidger = new Intent(f_listrencanakerja.this, FormPengamatanKebersihanBonggol.class);
                goRidger.putExtra("model", model);
                startActivity(goRidger);
            }
        });

        dropBibit = findViewById(R.id.mDropBibit);
        dropBibit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DropBibitModel model = new DropBibitModel();
                DropBibitModel.verified mandor = model. new verified();
                DropBibitModel.verified.verified_by mand = mandor. new verified_by();
                model.setNO_SPK(mNoSpk.getText().toString());
                model.setNO_LINE(mNoline.getText().toString());
                model.setNO_UNIT_IMPLEMENT("1110");
                model.setLOKASI(mLokasi.getText().toString());
                model.setKATEGORI("Drop Bibit");
                model.setLUAS_NETTO(mLuasNetto.getText().toString());
                mand.setNama("mandor");
                mandor.setVERIFIED_MANDOR(mand);
                model.setVERIFIED(mandor);
                Intent goRidger = new Intent(f_listrencanakerja.this, FormPengamatanDropBibit.class);
                goRidger.putExtra("model", model);
                startActivity(goRidger);
            }
        });

        transport = findViewById(R.id.mTransport);
        transport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KebersihanBonggolModel model = new KebersihanBonggolModel();
                KebersihanBonggolModel.verified mandor = model. new verified();
                KebersihanBonggolModel.verified.verified_by mand = mandor. new verified_by();
                model.setNO_SPK(mNoSpk.getText().toString());
                model.setNO_LINE(mNoline.getText().toString());
                model.setNO_UNIT_IMPLEMENT("1110");
                model.setLOKASI(mLokasi.getText().toString());
                model.setKATEGORI("Transport");
                model.setLUAS_NETTO(mLuasNetto.getText().toString());
                mand.setNama("mandor");
                mandor.setVERIFIED_MANDOR(mand);
                model.setVERIFIED(mandor);
                Intent goRidger = new Intent(f_listrencanakerja.this, FormPengamatanTransport.class);
                goRidger.putExtra("model", model);
                startActivity(goRidger);
            }
        });
        //--------------------------------------------------------------------------------------

        //fungsi tombol agregat
        agregat = findViewById(R.id.agregat);
        agregat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goagregat = new Intent(f_listrencanakerja.this, FormPengamatanFinishing.class);
                startActivity(goagregat);
            }
        });
        //--------------------------------------------------------------------------------------


    }

}