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
import com.ggf.qcpp.e_formpengamatan.sesetbonggol.FormPengamatanSesetBonggol;
import com.ggf.qcpp.e_formpengamatan.sesetbonggol.model.SesetBonggolModel;
import com.ggf.qcpp.e_formpengamatan.tidakterseset.FormPengamatanBonggolTidakTerseset;
import com.ggf.qcpp.e_formpengamatan.tidakterseset.model.BonggolTidakTersesetModel;
import com.ggf.qcpp.utils.GsonHelper;

public class e_1_list_bonggol extends AppCompatActivity {
    LoginResponse mProfile ;
    LinearLayout buttonBack, btnseset, btntidakset;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_1_list_bonggol);

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(e_1_list_bonggol.this, e_1_formpengamatanlist.class);
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
//        //fungsi tombol sesetbonggol
//        btnseset = findViewById(R.id.sesetbonggol);
//        btnseset.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                SesetBonggolModel model = new SesetBonggolModel();
//                SesetBonggolModel.verified mandor = model. new verified();
//                SesetBonggolModel.verified.verified_by mand = mandor. new verified_by();
//
//                model.setNO_LINE("3");
//                model.setNO_UNIT_IMPLEMENT("1110");
//                model.setLOKASI("E552");
//                model.setKATEGORI("sesetbonggol");
//                model.setNO_SPK(generateSpk(model.getKATEGORI()));
//                model.setLUAS_NETTO("2");
//                model.setUSERNAME( mProfile.getData().getUser().getEmail());
//                model.setPG(mProfile.getData().getUser().getPg());
//                model.setWILAYAH("wilayah001");
//                model.setSTATUS_PENGAMATAN("zzz");
//                mandor.setVERIFIED_MANDOR(mand);
//                model.setVERIFIED(mandor);
//
//                Intent gobajak = new Intent(e_1_list_bonggol.this, FormPengamatanSesetBonggol.class);
//                gobajak.putExtra("model", model);
//                startActivity(gobajak);
//            }
//        });
//        //--------------------------------------------------------------------------------------

        //fungsi tombol tidakterseset
        btntidakset = findViewById(R.id.tidakterseset);
        btntidakset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BonggolTidakTersesetModel model = new BonggolTidakTersesetModel();
                BonggolTidakTersesetModel.verified mandor = model. new verified();
                BonggolTidakTersesetModel.verified.verified_by mand = mandor. new verified_by();

                model.setNO_LINE("3");
                model.setNO_UNIT_IMPLEMENT("1110");
                model.setLOKASI("E552");
                model.setKATEGORI("bonggol_tidak_terseset");
                model.setNO_SPK(generateSpk(model.getKATEGORI()));
                model.setLUAS_NETTO("2");
                model.setUSERNAME( mProfile.getData().getUser().getEmail());
                model.setPG(mProfile.getData().getUser().getPg());
                model.setWILAYAH("wilayah001");
                model.setSTATUS_PENGAMATAN("zzz");
                mandor.setVERIFIED_MANDOR(mand);
                model.setVERIFIED(mandor);
                Intent gobajak = new Intent(e_1_list_bonggol.this, FormPengamatanBonggolTidakTerseset.class);
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