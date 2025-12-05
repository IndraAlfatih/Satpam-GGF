package com.ggf.qcpp.e_formpengamatan.e_1_formpengamatan_list;

import static com.ggf.qcpp.utils.Utils.generateSpk;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import com.ggf.qcpp.e_formpengamatan.bajak.FormPengamatanBajak;
import com.ggf.qcpp.e_formpengamatan.bajak.model.BajakModel;
import com.ggf.qcpp.e_formpengamatan.chopper.FormPengamatanChopper;
import com.ggf.qcpp.e_formpengamatan.chopper.model.ChopperModel;
import com.ggf.qcpp.e_formpengamatan.finishing.FormPengamatanFinishing;
import com.ggf.qcpp.e_formpengamatan.finishing.model.FinishingModel;
import com.ggf.qcpp.e_formpengamatan.kebersihanbonggol.FormPengamatanKebersihanBonggol;
import com.ggf.qcpp.e_formpengamatan.kebersihanbonggol.model.KebersihanBonggolModel;
import com.ggf.qcpp.e_formpengamatan.phtanah.FormPengamatanPhTanah;
import com.ggf.qcpp.e_formpengamatan.phtanah.model.PhtanahModel;
import com.ggf.qcpp.e_formpengamatan.ridger.FormPengamatanRidger;
import com.ggf.qcpp.e_formpengamatan.ridger.model.RidgerModel;
import com.ggf.qcpp.e_formpengamatan.subsoiler.FormPengamatanSubsoil;
import com.ggf.qcpp.e_formpengamatan.subsoiler.model.SubsoilerModel;
import com.ggf.qcpp.e_formpengamatan.z_satpam.LembarMutasi;
import com.ggf.qcpp.utils.GsonHelper;

public class e_1_list_lahan extends AppCompatActivity {
    LoginResponse mProfile ;
    LinearLayout buttonBack,finishing, btnchopper, btnbajak, btnagregat, btnridger, btnkebersihanbonggol, btnjalansaluran, btnphtanah, btnbajaksingkong,btnsubsoiler;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_1_list_lahan);

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(e_1_list_lahan.this, e_1_formpengamatanlist.class);
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
        //fungsi tombol mutasi
        btnchopper = findViewById(R.id.Chopper);
        btnchopper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChopperModel model = new ChopperModel();
                ChopperModel.verified mandor = model. new verified();
                ChopperModel.verified.verified_by mand = mandor. new verified_by();


                model.setNO_LINE("-");
                model.setNO_UNIT_IMPLEMENT("-");
                model.setLOKASI("-");
                model.setKATEGORI("chopper");
                model.setNO_SPK(generateSpk(model.getKATEGORI()));
                model.setLUAS_NETTO("-");

//                App.getPref().getPref("PREFF")
                model.setUSERNAME( mProfile.getData().getUser().getEmail());
                model.setPG(mProfile.getData().getUser().getPg());
                model.setWILAYAH("-");
                model.setSTATUS_PENGAMATAN("-");
//                mand.setNama("yanto");
//                mandor.setVERIFIED_MANDOR(mand);
//                model.setVERIFIED(mandor);
                Intent gochopper = new Intent(e_1_list_lahan.this, LembarMutasi.class);
                gochopper.putExtra("model", model);
                startActivity(gochopper);
            }
        });

        btnsubsoiler = findViewById(R.id.SubSoiler);
        btnsubsoiler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SubsoilerModel model = new SubsoilerModel();
                SubsoilerModel.verified mandor = model. new verified();
                SubsoilerModel.verified.verified_by mand = mandor. new verified_by();


                model.setNO_LINE("-");
                model.setNO_UNIT_IMPLEMENT("-");
                model.setLOKASI("-");
                model.setKATEGORI("subsoiler");
                model.setNO_SPK(generateSpk(model.getKATEGORI()));
                model.setLUAS_NETTO("-");

//                App.getPref().getPref("PREFF")
                model.setUSERNAME( mProfile.getData().getUser().getEmail());
                model.setPG(mProfile.getData().getUser().getPg());
                model.setWILAYAH("-");
                model.setSTATUS_PENGAMATAN("-");
//                mand.setNama("yanto");
//                mandor.setVERIFIED_MANDOR(mand);
//                model.setVERIFIED(mandor);
                Intent gochopper = new Intent(e_1_list_lahan.this, FormPengamatanSubsoil.class);
                gochopper.putExtra("model", model);
                startActivity(gochopper);
            }
        });
        //--------------------------------------------------------------------------------------

        //fungsi tombol pembajakan
        btnbajak = findViewById(R.id.Pembajakan);
        btnbajak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BajakModel model = new BajakModel();
                BajakModel.verified mandor = model. new verified();
                BajakModel.verified.verified_by mand = mandor. new verified_by();

                model.setNO_LINE("-");
                model.setNO_UNIT_IMPLEMENT("-");
                model.setLOKASI("-");
                model.setKATEGORI("bajak");
                model.setNO_SPK(generateSpk(model.getKATEGORI()));
                model.setLUAS_NETTO("-");
                model.setUSERNAME( mProfile.getData().getUser().getEmail());
                model.setPG(mProfile.getData().getUser().getPg());
                model.setWILAYAH("-");
                model.setSTATUS_PENGAMATAN("-");
//                mandor.setVERIFIED_MANDOR(mand);
//                model.setVERIFIED(mandor);
                Intent gobajak = new Intent(e_1_list_lahan.this, FormPengamatanBajak.class);
                gobajak.putExtra("model", model);
                startActivity(gobajak);
            }
        });
        //--------------------------------------------------------------------------------------

        //fungsi tombol pembajakan
        btnbajaksingkong = findViewById(R.id.PembajakanSingkong);
        btnbajaksingkong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BajakModel model = new BajakModel();
                BajakModel.verified mandor = model. new verified();
                BajakModel.verified.verified_by mand = mandor. new verified_by();

                model.setNO_LINE("-");
                model.setNO_UNIT_IMPLEMENT("-");
                model.setLOKASI("-");
                model.setKATEGORI("bajak");
                model.setNO_SPK(generateSpk(model.getKATEGORI()));
                model.setLUAS_NETTO("-");
                model.setUSERNAME( mProfile.getData().getUser().getEmail());
                model.setPG(mProfile.getData().getUser().getPg());
                model.setWILAYAH("-");
                model.setSTATUS_PENGAMATAN("-");
//                mandor.setVERIFIED_MANDOR(mand);
//                model.setVERIFIED(mandor);
                Intent gobajak = new Intent(e_1_list_lahan.this, FormPengamatanBajak.class);
                gobajak.putExtra("model", model);
                startActivity(gobajak);
            }
        });
        //--------------------------------------------------------------------------------------

        //fungsi tombol agregat
        finishing = findViewById(R.id.Finishing);
        finishing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FinishingModel model = new FinishingModel();
                FinishingModel.verified mandor = model. new verified();
                FinishingModel.verified.verified_by mand = mandor. new verified_by();

                model.setNO_LINE("-");
                model.setNO_UNIT_IMPLEMENT("-");
                model.setLOKASI("-");
                model.setKATEGORI("finishing");
                model.setNO_SPK(generateSpk(model.getKATEGORI()));
                model.setLUAS_NETTO("-");
                model.setUSERNAME( mProfile.getData().getUser().getEmail());
                model.setPG(mProfile.getData().getUser().getPg());
                model.setWILAYAH("-");
                model.setSTATUS_PENGAMATAN("-");
                mandor.setVERIFIED_MANDOR(mand);
                model.setVERIFIED(mandor);
                Intent gobajak = new Intent(e_1_list_lahan.this, FormPengamatanFinishing.class);
                gobajak.putExtra("model", model);
                startActivity(gobajak);
            }
        });
        //--------------------------------------------------------------------------------------

//        //fungsi tombol ridger
//        btnridger = findViewById(R.id.Ridger);
//        btnridger.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                RidgerModel model = new RidgerModel();
//                RidgerModel.verified mandor = model. new verified();
//                RidgerModel.verified.verified_by mand = mandor. new verified_by();
//
//                model.setNO_LINE("3");
//                model.setNO_UNIT_IMPLEMENT("1110");
//                model.setLOKASI("E552");
//                model.setKATEGORI("ridger");
//                model.setNO_SPK(generateSpk(model.getKATEGORI()));
//                model.setLUAS_NETTO("2");
//                model.setUSERNAME( mProfile.getData().getUser().getEmail());
//                model.setPG(mProfile.getData().getUser().getPg());
//                model.setWILAYAH("wilayah001");
//                model.setSTATUS_PENGAMATAN("zzz");
//                mandor.setVERIFIED_MANDOR(mand);
//                model.setVERIFIED(mandor);
//                Intent gobajak = new Intent(e_1_list_lahan.this, FormPengamatanRidger.class);
//                gobajak.putExtra("model", model);
//                startActivity(gobajak);
//            }
//        });
//        //--------------------------------------------------------------------------------------

//        //fungsi tombol kebersihan bonggol
//        btnkebersihanbonggol = findViewById(R.id.Kebersihanbonggol);
//        btnkebersihanbonggol.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                KebersihanBonggolModel model = new KebersihanBonggolModel();
//                KebersihanBonggolModel.verified mandor = model. new verified();
//                KebersihanBonggolModel.verified.verified_by mand = mandor. new verified_by();
//
//                model.setNO_LINE("3");
//                model.setNO_UNIT_IMPLEMENT("1110");
//                model.setLOKASI("E552");
//                model.setKATEGORI("kebersihan_bonggol");
//                model.setNO_SPK(generateSpk(model.getKATEGORI()));
//                model.setLUAS_NETTO("2");
//                model.setUSERNAME( mProfile.getData().getUser().getEmail());
//                model.setPG(mProfile.getData().getUser().getPg());
//                model.setWILAYAH("wilayah001");
//                model.setSTATUS_PENGAMATAN("zzz");
//                mandor.setVERIFIED_MANDOR(mand);
//                model.setVERIFIED(mandor);
//                Intent gobajak = new Intent(e_1_list_lahan.this, FormPengamatanKebersihanBonggol.class);
//                gobajak.putExtra("model", model);
//                startActivity(gobajak);
//            }
//        });
//        //--------------------------------------------------------------------------------------

//        //fungsi tombol kebersihan jalan saluran
//        btnjalansaluran = findViewById(R.id.JalanSaluran);
//        btnjalansaluran.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                JalanSaluranModel model = new JalanSaluranModel();
////                JalanSaluranModel.verified mandor = model. new verified();
////                JalanSaluranModel.verified.verified_by mand = mandor. new verified_by();
//
////                model.setNO_LINE("3");
////                model.setNO_UNIT_IMPLEMENT("1110");
////                model.setLOKASI("E552");
////                model.setKATEGORI("jalan_saluran");
////                model.setNO_SPK(generateSpk(model.getKATEGORI()));
////                model.setLUAS_NETTO("2");
////                model.setUSERNAME( mProfile.getData().getUser().getEmail());
////                model.setPG(mProfile.getData().getUser().getPg());
////                model.setWILAYAH("wilayah001");
////                model.setSTATUS_PENGAMATAN("zzz");
////                mandor.setVERIFIED_MANDOR(mand);
////                model.setVERIFIED(mandor);
////                Intent gobajak = new Intent(e_1_list_lahan.this, FormPengamatanJalanSaluran.class);
////                gobajak.putExtra("model", model);
////                startActivity(gobajak);
//            }
//        });
        //--------------------------------------------------------------------------------------

//        //fungsi tombol ph tanah
//        btnphtanah = findViewById(R.id.pHTanah);
//        btnphtanah.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                PhtanahModel model = new PhtanahModel();
//                PhtanahModel.verified mandor = model. new verified();
//                PhtanahModel.verified.verified_by mand = mandor. new verified_by();
//
//                model.setNO_LINE("3");
//                model.setNO_UNIT_IMPLEMENT("1110");
//                model.setLOKASI("E552");
//                model.setKATEGORI("ph_tanah");
//                model.setNO_SPK(generateSpk(model.getKATEGORI()));
//                model.setLUAS_NETTO("2");
//                model.setUSERNAME( mProfile.getData().getUser().getEmail());
//                model.setPG(mProfile.getData().getUser().getPg());
//                model.setWILAYAH("wilayah001");
//                model.setSTATUS_PENGAMATAN("zzz");
//                mandor.setVERIFIED_MANDOR(mand);
//                model.setVERIFIED(mandor);
//                Intent gobajak = new Intent(e_1_list_lahan.this, FormPengamatanPhTanah.class);
//                gobajak.putExtra("model", model);
//                startActivity(gobajak);
//            }
//        });
//        //--------------------------------------------------------------------------------------

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed(); // Sudah cukup
    }


}