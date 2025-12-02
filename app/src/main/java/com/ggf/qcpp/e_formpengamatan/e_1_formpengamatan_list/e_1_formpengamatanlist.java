package com.ggf.qcpp.e_formpengamatan.e_1_formpengamatan_list;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import com.ggf.qcpp.R;
import com.ggf.qcpp.c_home.c_1_home;
import com.ggf.qcpp.d_hasilpengamatan.hasilPengamatanOffline.HasilPengamatanOfflineActivity;

public class e_1_formpengamatanlist extends AppCompatActivity {

    LinearLayout buttonBack, plahan, pbibittanam, pperawatanpanen, pbonggol, psingkong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_1_formpengamatanlist);

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(e_1_formpengamatanlist.this, c_1_home.class);
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

        //fungsi tombol lahan
        plahan = findViewById(R.id.PengolahanLahan);
        plahan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent golahan = new Intent(e_1_formpengamatanlist.this, e_1_list_lahan.class);
                startActivity(golahan);
            }
        });
        //--------------------------------------------------------------------------------------

        //fungsi tombol bibittanam
        pbibittanam = findViewById(R.id.PengolahanBibitTanam);
        pbibittanam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent gobibittanam = new Intent(e_1_formpengamatanlist.this, e_1_list_bibittanam.class);
//                startActivity(gobibittanam);
            }
        });
        //--------------------------------------------------------------------------------------

        //fungsi tombol perawatanpanen
        pperawatanpanen = findViewById(R.id.PengolahanPerawatanPanen);
        pperawatanpanen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent goperawatanpanen = new Intent(e_1_formpengamatanlist.this, e_1_list_perawatanpanen.class);
//                startActivity(goperawatanpanen);
            }
        });
        //--------------------------------------------------------------------------------------

        //fungsi tombol bonggol
        pbonggol = findViewById(R.id.PengolahanBonggol);
        pbonggol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gobonggol = new Intent(e_1_formpengamatanlist.this, e_1_list_bonggol.class);
                startActivity(gobonggol);
            }
        });
        //--------------------------------------------------------------------------------------

        //fungsi tombol bibittanam
//        psingkong = findViewById(R.id.PengolahanSingkong);
//        psingkong.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent gosingkong = new Intent(e_1_formpengamatanlist.this, e_1_list_singkong.class);
//                startActivity(gosingkong);
//            }
//        });
        //--------------------------------------------------------------------------------------

        //fungsi tombol perawatanpanen
        pperawatanpanen = findViewById(R.id.PengolahanPerawatanPanen);
        pperawatanpanen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent goperawatan = new Intent(e_1_formpengamatanlist.this, e_1_list_perawatanpanen.class);
//                startActivity(goperawatan);
            }
        });
        //--------------------------------------------------------------------------------------


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed(); // Sudah cukup
    }

}