package com.ggf.qcpp.e_formpengamatan.e_1_formpengamatan_list;

import static com.ggf.qcpp.utils.Utils.generateSpk;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import com.ggf.qcpp.App;
import com.ggf.qcpp.Prefs;
import com.ggf.qcpp.R;
import com.ggf.qcpp.b_account.model.LoginResponse;
import com.ggf.qcpp.c_home.c_1_home;
import com.ggf.qcpp.d_hasilpengamatan.hasilPengamatanOffline.HasilPengamatanOfflineActivity;
import com.ggf.qcpp.e_formpengamatan.dropbibit.FormPengamatanDropBibit;
import com.ggf.qcpp.e_formpengamatan.dropbibit.model.DropBibitModel;
import com.ggf.qcpp.e_formpengamatan.jumlahbaris.FormPengamatanJumlahBaris;
import com.ggf.qcpp.e_formpengamatan.jumlahbaris.model.JumlahBarisModel;
import com.ggf.qcpp.e_formpengamatan.petikbibit.FormPengamatanPetikBibit;
import com.ggf.qcpp.e_formpengamatan.petikbibit.model.PetikBibitModel;
import com.ggf.qcpp.e_formpengamatan.pooldipping.FormPengamatanPoolDipping;
import com.ggf.qcpp.e_formpengamatan.pooldipping.model.PoolDippingModel;
import com.ggf.qcpp.e_formpengamatan.potensicrown.FormPengamatanPotensiCrown;
import com.ggf.qcpp.e_formpengamatan.potensicrown.model.PotensiCrownModel;
import com.ggf.qcpp.e_formpengamatan.tanam.FormPengamatanTanam;
import com.ggf.qcpp.e_formpengamatan.tanam.model.TanamModel;
import com.ggf.qcpp.e_formpengamatan.transport.FormPengamatanTransport;
import com.ggf.qcpp.e_formpengamatan.transport.model.TransportModel;
import com.ggf.qcpp.utils.GsonHelper;

import butterknife.ButterKnife;

public class e_1_list_bibittanam extends AppCompatActivity {
    LoginResponse mProfile;
    LinearLayout buttonBack, btnpetik, btnpotensicrown, btnpool, btnbibittanam, btntanam, btnpenambahanbaris;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_1_list_bibittanam);
        ButterKnife.bind(this);

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(e_1_list_bibittanam.this, e_1_formpengamatanlist.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish(); // biar activity ini langsung ditutup
            }
        });

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

        mProfile = (LoginResponse) GsonHelper.parseGson(
                App.getPref().getString(Prefs.PREF_STORE_PROFILE, ""),
                new LoginResponse()
        );
        //fungsi tombol petik
        btnpetik = findViewById(R.id.petik);
        btnpetik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PetikBibitModel model = new PetikBibitModel();
                PetikBibitModel.verified mandor = model.new verified();
                PetikBibitModel.verified.verified_by mand = mandor.new verified_by();

                model.setNO_LINE("3");
                model.setNO_UNIT_IMPLEMENT("1110");
                model.setLOKASI("E552");
                model.setKATEGORI("petik_bibit");
                model.setNO_SPK(generateSpk(model.getKATEGORI()));
                model.setLUAS_NETTO("2");
                model.setUSERNAME(mProfile.getData().getUser().getEmail());
                model.setPG(mProfile.getData().getUser().getPg());
                model.setWILAYAH("wilayah001");
                model.setSTATUS_PENGAMATAN("zzz");
                mandor.setVERIFIED_MANDOR(mand);
                model.setVERIFIED(mandor);
                Intent gobajak = new Intent(e_1_list_bibittanam.this, FormPengamatanPetikBibit.class);
                gobajak.putExtra("model", model);
                startActivity(gobajak);

            }
        });
        //--------------------------------------------------------------------------------------

//        //fungsi tombol transport
//        btntransport = findViewById(R.id.transport);
//        btntransport.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                TransportModel model = new TransportModel();
//                TransportModel.verified mandor = model.new verified();
//                TransportModel.verified.verified_by mand = mandor.new verified_by();
//
//                model.setNO_LINE("3");
//                model.setNO_UNIT_IMPLEMENT("1110");
//                model.setLOKASI("E552");
//                model.setKATEGORI("kebersihan_transport");
//                model.setNO_SPK(generateSpk(model.getKATEGORI()));
//                model.setLUAS_NETTO("2");
//                model.setUSERNAME(mProfile.getData().getUser().getEmail());
//                model.setPG(mProfile.getData().getUser().getPg());
//                model.setWILAYAH("wilayah001");
//                model.setSTATUS_PENGAMATAN("zzz");
////                mandor.setVERIFIED_MANDOR(mand);
////                model.setVERIFIED(mandor);
//                Intent gobajak = new Intent(e_1_list_bibittanam.this, FormPengamatanTransport.class);
//                gobajak.putExtra("model", model);
//                startActivity(gobajak);
//            }
//        });
//        //--------------------------------------------------------------------------------------

//        //fungsi tombol pool
        btnpool = findViewById(R.id.pooldipping);
        btnpool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PoolDippingModel model = new PoolDippingModel();
                PoolDippingModel.verified mandor = model.new verified();
                PoolDippingModel.verified.verified_by mand = mandor.new verified_by();

                model.setNO_LINE("3");
                model.setNO_UNIT_IMPLEMENT("1110");
                model.setLOKASI("E552");
                model.setKATEGORI("pool_dipping");
                model.setNO_SPK(generateSpk(model.getKATEGORI()));
                model.setLUAS_NETTO("2");
                model.setUSERNAME(mProfile.getData().getUser().getEmail());
                model.setPG(mProfile.getData().getUser().getPg());
                model.setWILAYAH("wilayah001");
                model.setSTATUS_PENGAMATAN("zzz");
                mandor.setVERIFIED_MANDOR(mand);
                model.setVERIFIED(mandor);
                Intent gobajak = new Intent(e_1_list_bibittanam.this, FormPengamatanPoolDipping.class);
                gobajak.putExtra("model", model);
                startActivity(gobajak);
            }
        });
        //--------------------------------------------------------------------------------------

        //fungsi tombol bibittanam
        btnbibittanam = findViewById(R.id.dropbibit);
        btnbibittanam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DropBibitModel model = new DropBibitModel();
                DropBibitModel.verified mandor = model.new verified();
                DropBibitModel.verified.verified_by mand = mandor.new verified_by();

                model.setNO_LINE("3");
                model.setNO_UNIT_IMPLEMENT("1110");
                model.setLOKASI("E552");
                model.setKATEGORI("drop_bibit");
//                model.setNO_SPK(generateSpk(model.getKATEGORI()));

                model.setLUAS_NETTO("2");
                model.setUSERNAME(mProfile.getData().getUser().getEmail());
                model.setPG(mProfile.getData().getUser().getPg());
                model.setWILAYAH("wilayah001");
                model.setSTATUS_PENGAMATAN("zzz");
                mandor.setVERIFIED_MANDOR(mand);
                model.setVERIFIED(mandor);
                Intent gobajak = new Intent(e_1_list_bibittanam.this, FormPengamatanDropBibit.class);
                gobajak.putExtra("model", model);
                startActivity(gobajak);
            }
        });
        //--------------------------------------------------------------------------------------

        //fungsi tombol kualitastanam
        btntanam = findViewById(R.id.kualitastanam);
        btntanam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TanamModel model = new TanamModel();
//                PoolDippingModel.verified mandor = model. new verified();
//                PoolDippingModel.verified.verified_by mand = mandor. new verified_by();

                model.setNO_LINE("3");
                model.setNO_UNIT_IMPLEMENT("1110");
                model.setLOKASI("E552");
                model.setKATEGORI("kualitas_tanam");
                model.setNO_SPK(generateSpk(model.getKATEGORI()));
                model.setLUAS_NETTO("2");
                model.setUSERNAME(mProfile.getData().getUser().getEmail());
                model.setPG(mProfile.getData().getUser().getPg());
                model.setSTATUS_PENGAMATAN("zzz");
                model.setWILAYAH("wil");
//                mandor.setVERIFIED_MANDOR(mand);
//                model.setVERIFIED(mandor);
                Intent gobajak = new Intent(e_1_list_bibittanam.this, FormPengamatanTanam.class);
                gobajak.putExtra("model", model);
                startActivity(gobajak);
            }
        });
        //--------------------------------------------------------------------------------------

        //fungsi tombol penambahanbaris
        btnpenambahanbaris = findViewById(R.id.jumlahbaris);
        btnpenambahanbaris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JumlahBarisModel model = new JumlahBarisModel();

                model.setNO_LINE("3");
                model.setNO_UNIT_IMPLEMENT("1110");
                model.setLOKASI("E552");
                model.setKATEGORI("jumlah_baris");
                model.setNO_SPK(generateSpk(model.getKATEGORI()));
                model.setLUAS_NETTO("2");
                model.setUSERNAME(mProfile.getData().getUser().getEmail());
                model.setPG(mProfile.getData().getUser().getPg());
                model.setWILAYAH("wilayah001");
                model.setSTATUS_PENGAMATAN("status");
                Intent gobajak = new Intent(e_1_list_bibittanam.this, FormPengamatanJumlahBaris.class);
                gobajak.putExtra("model", model);
                startActivity(gobajak);
            }
        });
        //--------------------------------------------------------------------------------------

        //fungsi tombol penambahanbaris
        btnpotensicrown = findViewById(R.id.potensicrown);
        btnpotensicrown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PotensiCrownModel model = new PotensiCrownModel();

                model.setNO_LINE("3");
                model.setNO_UNIT_IMPLEMENT("1110");
                model.setLOKASI("E552");
                model.setKATEGORI("potensi_bibit_crown");
                model.setNO_SPK(generateSpk(model.getKATEGORI()));
                model.setLUAS_NETTO("2");
                model.setUSERNAME(mProfile.getData().getUser().getEmail());
                model.setPG(mProfile.getData().getUser().getPg());
                model.setWILAYAH("wilayah001");
                model.setSTATUS_PENGAMATAN("status");
                Intent gobajak = new Intent(e_1_list_bibittanam.this, FormPengamatanPotensiCrown.class);
                gobajak.putExtra("model", model);
                startActivity(gobajak);
            }
        });
        //--------------------------------------------------------------------------------------
    }

    public void onBackPressed() {
        // Tambahkan kode lain yang Anda inginkan sebelum menutup aktivitas (jika perlu).
        super.onBackPressed();
        finish(); // Menutup aktivitas saat tombol "Back" ditekan.
    }

}

