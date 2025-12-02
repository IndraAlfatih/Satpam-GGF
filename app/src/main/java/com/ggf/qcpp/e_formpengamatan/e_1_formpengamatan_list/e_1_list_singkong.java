package com.ggf.qcpp.e_formpengamatan.e_1_formpengamatan_list;

import static com.ggf.qcpp.utils.Utils.generateSpk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.ggf.qcpp.App;
import com.ggf.qcpp.Prefs;
import com.ggf.qcpp.R;
import com.ggf.qcpp.b_account.model.LoginResponse;
import com.ggf.qcpp.e_formpengamatan.sesetbonggol.FormPengamatanSesetBonggol;
import com.ggf.qcpp.e_formpengamatan.sesetbonggol.model.SesetBonggolModel;
import com.ggf.qcpp.e_formpengamatan.stekpanjang.FormPengamatanSingkongStekPanjang;
import com.ggf.qcpp.e_formpengamatan.stekpanjang.model.SingkongStekPanjangModel;
import com.ggf.qcpp.e_formpengamatan.stekpendek.FormPengamatanSingkongStekPendek;
import com.ggf.qcpp.e_formpengamatan.stekpendek.model.SingkongStekPendekModel;
import com.ggf.qcpp.e_formpengamatan.tanamsingkong.FormPengamatanTanamSingkong;
import com.ggf.qcpp.e_formpengamatan.tanamsingkong.model.TanamSingkongModel;
import com.ggf.qcpp.utils.GsonHelper;

public class e_1_list_singkong extends AppCompatActivity {

    LoginResponse mProfile ;

    LinearLayout buttonBack, stekpanjang, tanamsingkong, stekpendek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_1_list_singkong);

        mProfile = (LoginResponse) GsonHelper.parseGson(
                App.getPref().getString(Prefs.PREF_STORE_PROFILE, ""),
                new LoginResponse()
        );

        //fungsi tombol stekpanjang
        stekpanjang = findViewById(R.id.stekpanjang);
        stekpanjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SingkongStekPanjangModel model = new SingkongStekPanjangModel();
                SingkongStekPanjangModel.verified mandor = model. new verified();
                SingkongStekPanjangModel.verified.verified_by mand = mandor. new verified_by();

                model.setNO_LINE("3");
                model.setNO_UNIT_IMPLEMENT("1110");
                model.setLOKASI("E552");
                model.setKATEGORI("singkong_stek_panjang");
                model.setNO_SPK(generateSpk(model.getKATEGORI()));
                model.setLUAS_NETTO("2");
                model.setUSERNAME( mProfile.getData().getUser().getEmail());
                model.setPG(mProfile.getData().getUser().getPg());
                model.setWILAYAH("wilayah001");
                model.setSTATUS_PENGAMATAN("zzz");
                mandor.setVERIFIED_MANDOR(mand);
                model.setVERIFIED(mandor);

                Intent gopanjang = new Intent(e_1_list_singkong.this, FormPengamatanSingkongStekPanjang.class);
                gopanjang.putExtra("model", model);
                startActivity(gopanjang);
            }
        });
        //--------------------------------------------------------------------------------------

        //fungsi tombol stekpendek
        stekpendek = findViewById(R.id.stekpendek);
        stekpendek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SingkongStekPendekModel model = new SingkongStekPendekModel();
                SingkongStekPendekModel.verified mandor = model. new verified();
                SingkongStekPendekModel.verified.verified_by mand = mandor. new verified_by();

                model.setNO_LINE("3");
                model.setNO_UNIT_IMPLEMENT("1110");
                model.setLOKASI("E552");
                model.setKATEGORI("singkong_stek_pendek");
                model.setNO_SPK(generateSpk(model.getKATEGORI()));
                model.setLUAS_NETTO("2");
                model.setUSERNAME( mProfile.getData().getUser().getEmail());
                model.setPG(mProfile.getData().getUser().getPg());
                model.setWILAYAH("wilayah001");
                model.setSTATUS_PENGAMATAN("zzz");
                mandor.setVERIFIED_MANDOR(mand);
                model.setVERIFIED(mandor);

                Intent gopendek = new Intent(e_1_list_singkong.this, FormPengamatanSingkongStekPendek.class);
                gopendek.putExtra("model", model);
                startActivity(gopendek);
            }
        });
        //--------------------------------------------------------------------------------------

        //fungsi tombol tanamsingkong
        tanamsingkong = findViewById(R.id.tanamsingkong);
        tanamsingkong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TanamSingkongModel model = new TanamSingkongModel();
                TanamSingkongModel.verified mandor = model. new verified();
                TanamSingkongModel.verified.verified_by mand = mandor. new verified_by();

                model.setNO_LINE("3");
                model.setNO_UNIT_IMPLEMENT("1110");
                model.setLOKASI("E552");
                model.setKATEGORI("tanam_singkong");
                model.setNO_SPK(generateSpk(model.getKATEGORI()));
                model.setLUAS_NETTO("2");
                model.setUSERNAME( mProfile.getData().getUser().getEmail());
                model.setPG(mProfile.getData().getUser().getPg());
                model.setWILAYAH("wilayah001");
                model.setSTATUS_PENGAMATAN("zzz");
                mandor.setVERIFIED_MANDOR(mand);
                model.setVERIFIED(mandor);

                Intent gotanamsingkong = new Intent(e_1_list_singkong.this, FormPengamatanTanamSingkong.class);
                gotanamsingkong.putExtra("model", model);
                startActivity(gotanamsingkong);
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