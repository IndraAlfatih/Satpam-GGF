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
import com.ggf.qcpp.e_formpengamatan.adukanbahan.FormPengamatanAdukanBahanDilokasi;
import com.ggf.qcpp.e_formpengamatan.adukanbahan.model.AdukanBahanDilokasiModel;
import com.ggf.qcpp.e_formpengamatan.boom.FormPengamatanBoomMixer;
import com.ggf.qcpp.e_formpengamatan.boom.model.BoomMixerModel;
import com.ggf.qcpp.e_formpengamatan.gudangmixer.FormPengamatanGudangMixer;
import com.ggf.qcpp.e_formpengamatan.gudangmixer.model.GudangMixerModel;
import com.ggf.qcpp.e_formpengamatan.kebersihanpanen.FormPengamatanKebersihanPanen;
import com.ggf.qcpp.e_formpengamatan.kebersihanpanen.model.PanenModel;
import com.ggf.qcpp.utils.GsonHelper;

public class e_1_list_perawatanpanen extends AppCompatActivity {

    LoginResponse mProfile ;
    LinearLayout  btngudangmixer, btnadukanlok, btnboom, btnpanen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_1_list_perawatanpanen);

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(e_1_list_perawatanpanen.this, e_1_formpengamatanlist.class);
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

        //fungsi tombol gudangmixer
        btngudangmixer = findViewById(R.id.gudangmixer);
        btngudangmixer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GudangMixerModel model = new GudangMixerModel();
                GudangMixerModel.verified mandor = model. new verified();
                GudangMixerModel.verified.verified_by mand = mandor. new verified_by();

                model.setNO_LINE("3");
                model.setNO_UNIT_IMPLEMENT("1110");
                model.setLOKASI("E552");
                model.setKATEGORI("mixer");
                model.setNO_SPK(generateSpk(model.getKATEGORI()));
                model.setLUAS_NETTO("2");
                model.setUSERNAME( mProfile.getData().getUser().getEmail());
                model.setPG(mProfile.getData().getUser().getPg());
                model.setWILAYAH("wilayah001");
                model.setSTATUS_PENGAMATAN("zzz");
                mandor.setVERIFIED_MANDOR(mand);
                model.setVERIFIED(mandor);

                Intent gogmixer = new Intent(e_1_list_perawatanpanen.this, FormPengamatanGudangMixer.class);
                gogmixer.putExtra("model", model);
                startActivity(gogmixer);
            }
        });
        //--------------------------------------------------------------------------------------

        //fungsi tombol adukanbahanlokasi
        btnadukanlok = findViewById(R.id.adukanbahan);
        btnadukanlok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AdukanBahanDilokasiModel model = new AdukanBahanDilokasiModel();
                AdukanBahanDilokasiModel.verified mandor = model. new verified();
                AdukanBahanDilokasiModel.verified.verified_by mand = mandor. new verified_by();

                model.setNO_LINE("3");
                model.setNO_UNIT_IMPLEMENT("1110");
                model.setLOKASI("E552");
                model.setKATEGORI("adukan");
                model.setNO_SPK(generateSpk(model.getKATEGORI()));
                model.setLUAS_NETTO("2");
                model.setUSERNAME( mProfile.getData().getUser().getEmail());
                model.setPG(mProfile.getData().getUser().getPg());
                model.setWILAYAH("wilayah001");
                model.setSTATUS_PENGAMATAN("zzz");
                mandor.setVERIFIED_MANDOR(mand);
                model.setVERIFIED(mandor);

                Intent goadukanlok = new Intent(e_1_list_perawatanpanen.this, FormPengamatanAdukanBahanDilokasi.class);
                goadukanlok.putExtra("model", model);
                startActivity(goadukanlok);
            }
        });
        //--------------------------------------------------------------------------------------

//        //fungsi tombol boomspray
//        btnboom = findViewById(R.id.boomspray);
//        btnboom.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                BoomMixerModel model = new BoomMixerModel();
//                BoomMixerModel.verified mandor = model. new verified();
//                BoomMixerModel.verified.verified_by mand = mandor. new verified_by();
//
//                model.setNO_LINE("3");
//                model.setNO_UNIT_IMPLEMENT("1110");
//                model.setLOKASI("E552");
//                model.setKATEGORI("boomspray");
//                model.setNO_SPK(generateSpk(model.getKATEGORI()));
//                model.setLUAS_NETTO("2");
//                model.setUSERNAME( mProfile.getData().getUser().getEmail());
//                model.setPG(mProfile.getData().getUser().getPg());
//                model.setWILAYAH("wilayah001");
//                model.setSTATUS_PENGAMATAN("zzz");
//                mandor.setVERIFIED_MANDOR(mand);
//                model.setVERIFIED(mandor);
//
//                Intent goboom = new Intent(e_1_list_perawatanpanen.this, FormPengamatanBoomMixer.class);
//                goboom.putExtra("model", model);
//                startActivity(goboom);
//            }
//        });
//        //--------------------------------------------------------------------------------------

        //fungsi tombol kebersihanpanen
        btnpanen = findViewById(R.id.kebersihanpanen);
        btnpanen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PanenModel model = new PanenModel();
                PanenModel.verified mandor = model. new verified();
                PanenModel.verified.verified_by mand = mandor. new verified_by();

                model.setNO_LINE("3");
                model.setNO_UNIT_IMPLEMENT("1110");
                model.setLOKASI("E552");
                model.setKATEGORI("panen");
                model.setNO_SPK(generateSpk(model.getKATEGORI()));
                model.setLUAS_NETTO("2");
                model.setUSERNAME( mProfile.getData().getUser().getEmail());
                model.setPG(mProfile.getData().getUser().getPg());
                model.setWILAYAH("wilayah001");
                model.setSTATUS_PENGAMATAN("zzz");
                mandor.setVERIFIED_MANDOR(mand);
                model.setVERIFIED(mandor);

                Intent gopanen = new Intent(e_1_list_perawatanpanen.this, FormPengamatanKebersihanPanen.class);
                gopanen.putExtra("model", model);
                startActivity(gopanen);
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