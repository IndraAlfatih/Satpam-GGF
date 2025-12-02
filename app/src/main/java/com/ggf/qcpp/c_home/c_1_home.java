package com.ggf.qcpp.c_home;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ggf.qcpp.App;
import com.ggf.qcpp.Prefs;
import com.ggf.qcpp.R;
import com.ggf.qcpp.b_account.b_2_login;
import com.ggf.qcpp.b_account.model.LoginResponse;
import com.ggf.qcpp.d_hasilpengamatan.hasilPengamatanOffline.HasilPengamatanOfflineActivity;
import com.ggf.qcpp.e_formpengamatan.e_1_formpengamatan_list.e_1_formpengamatanlist;
import com.ggf.qcpp.b_account.b_3_profil;
import com.ggf.qcpp.d_hasilpengamatan.d_1_hasilpengamatan_list.d_1_hasilpengamatan_1;
import com.ggf.qcpp.g_website.g_website;
import com.ggf.qcpp.h_dashboard.h_dashboard;
import com.ggf.qcpp.i_notify.i_notify_1;
import com.ggf.qcpp.j_realisasi.j_realisasi_;
import com.ggf.qcpp.k_historylocation.HistoryLocation_Form;
import com.ggf.qcpp.rencana_kerja.RencanaKerja;
import com.ggf.qcpp.utils.GsonHelper;
import com.ggf.qcpp.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class c_1_home extends AppCompatActivity {
    LoginResponse mProfile ;
    @BindView(R.id.mBtnLogout)
    Button mBtnLogout;

    @BindView(R.id.mNama)
    TextView mNama;
    @BindView(R.id.asisstant)
    LinearLayout asisstant;

    @BindView(R.id.mRole)
    TextView mRole;

    @BindView(R.id.internalev)
    LinearLayout ev;

    @BindView(R.id.ssk)
    LinearLayout ssk;

    @BindView(R.id.cost)
    LinearLayout cost;

    @BindView(R.id.fms)
    LinearLayout fms;

    @BindView(R.id.formssk)
    LinearLayout formssk;


    LinearLayout inven,permintaan,histori, linierprofile1, registertk1, hasilpengamatanOffline,formpengamatan, formrencanakerja, webs, dashb, hasilpengamatan, validasi;
    RelativeLayout relathasil1, relathasil2, relathasil3, relathasil4;
    boolean doubleBackToExitPressedOnce = false;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_1_home);
        ButterKnife.bind(this);
        mProfile = (LoginResponse) GsonHelper.parseGson(
                App.getPref().getString(Prefs.PREF_STORE_PROFILE, ""),
                new LoginResponse()


        );

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


        mNama.setText(mProfile.getData().getUser().getName());
        mRole.setText(mProfile.getData().getUser().getRole());
        mBtnLogout.setOnClickListener(view->this.logout());
        Log.d("dataTime" , Utils.generateSpk("bibit"));

        //fungsi tombol hasil pengamatan
        hasilpengamatan = findViewById(R.id.hasilpengamatan);
        hasilpengamatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gohasil = new Intent(c_1_home.this, d_1_hasilpengamatan_1.class);
                startActivity(gohasil);
            }
        });
        //--------------------------------------------------------------------------------------

        asisstant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = "628977796546"; // ganti dengan nomor tujuan
                String uniqueId = String.valueOf(System.currentTimeMillis());
                String message = "Nomor Tiket#" + uniqueId + "\n-------------------------------------------------\nNama : \nPG : \nLokasi : \nTanggal Pengamatan : \nPengamatan : \n------------------------------------------------- \nPermintaan : ";
                String url = "https://wa.me/" + phoneNumber + "?text=" + Uri.encode(message);

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });


        //fungsi tombol profil
//        linierprofile1 = findViewById(R.id.linierprofile);
//        linierprofile1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent goprofil = new Intent(c_1_home.this, b_3_profil.class);
//                startActivity(goprofil);
//            }
//        });
        //--------------------------------------------------------------------------------------


        //fungsi tombol formpengamatan
        formpengamatan = findViewById(R.id.fpengamatan);
        formpengamatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gopengamatan = new Intent(c_1_home.this, e_1_formpengamatanlist.class);
                startActivity(gopengamatan);
            }
        });

        hasilpengamatanOffline = findViewById(R.id.hasilpengamatanOffline);
        hasilpengamatanOffline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gopengamatan = new Intent(c_1_home.this, HasilPengamatanOfflineActivity.class);
                startActivity(gopengamatan);
            }
        });
        //--------------------------------------------------------------------------------------

//        fungsi tombol website
        webs = findViewById(R.id.website);
        webs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://osiqcpp.ggfsystem.com/login"; // ganti dengan link tujuan
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
//                Intent goweb = new Intent(c_1_home.this, g_website.class);
//                startActivity(goweb);
            }
        });
        //--------------------------------------------------------------------------------------

       //fungsi tombol dashboard
        dashb = findViewById(R.id.dashboard);
        dashb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://osiqcpp.ggfsystem.com/"; // ganti dengan link tujuan
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
//                Intent godash = new Intent(c_1_home.this, h_dashboard.class);
//                startActivity(godash);
            }
        });
        //--------------------------------------------------------------------------------------

        // fungsi tombol inventori menuju link
        inven = findViewById(R.id.inventori);
        inven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://sites.google.com/view/ggf-qcpp2/digital-library/report/inventory-utama"; // ganti dengan link tujuan
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        // fungsi tombol inventori menuju link
        permintaan = findViewById(R.id.permintaanbarang);
        permintaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://forms.office.com/pages/responsepage.aspx?id=GuWUbtsaKU-rGUHG7ob3Bi68Uk8OuhNCjlssUg4_5i9UMzA3VUMwSVJaNlg5MlpLSFA0V0JVVEM5TC4u"; // ganti dengan link tujuan
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

//         fungsi tombol inventori menuju link
                histori = findViewById(R.id.histori);
                histori.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String url = "https://sites.google.com/view/history-lokasi/history-lokasi-kasie"; // ganti dengan link tujuan
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        startActivity(intent);
                    }
                });

        ev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://apc01.safelinks.protection.outlook.com/?url=http%3A%2F%2Fggf-tableau.ggpc.co.id%2F%23%2Fviews%2FInternalEvaluationQCPP13-Agustus-2025%2FDashboardMonthPeriodeAgustus&data=05%7C02%7CIndra.Yulianto%40gg-foods.com%7C571dd9d2da7f44b1ed0f08ddded2a2f5%7C6e94e51a1adb4f29ab1941c6ee86f706%7C0%7C0%7C638911718441486449%7CUnknown%7CTWFpbGZsb3d8eyJFbXB0eU1hcGkiOnRydWUsIlYiOiIwLjAuMDAwMCIsIlAiOiJXaW4zMiIsIkFOIjoiTWFpbCIsIldUIjoyfQ%3D%3D%7C0%7C%7C%7C&sdata=0sTvOrXJ5HGQY%2Fx03Gvywwi6NQIW43PYHBUDg6wHe%2B4%3D&reserved=0"; // ganti dengan link tujuan
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        cost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://apc01.safelinks.protection.outlook.com/?url=http%3A%2F%2Fggf-tableau.ggpc.co.id%2F%23%2Fviews%2FQCProcesspineCostMonitoring%2FDashboard1%3F%3Aiid%3D1&data=05%7C02%7CIndra.Yulianto%40gg-foods.com%7C571dd9d2da7f44b1ed0f08ddded2a2f5%7C6e94e51a1adb4f29ab1941c6ee86f706%7C0%7C0%7C638911718441473198%7CUnknown%7CTWFpbGZsb3d8eyJFbXB0eU1hcGkiOnRydWUsIlYiOiIwLjAuMDAwMCIsIlAiOiJXaW4zMiIsIkFOIjoiTWFpbCIsIldUIjoyfQ%3D%3D%7C0%7C%7C%7C&sdata=aO%2B60IZ0XryBu19T0XVavnBV%2BsUN1Q2UbBkvk%2BO0iX4%3D&reserved=0"; // ganti dengan link tujuan
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        ssk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://sites.google.com/view/ggf-qcpp2/digital-library/report/sistem-mutu/ssk-standarisasi"; // ganti dengan link tujuan
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        formssk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://forms.office.com/pages/responsepage.aspx?id=GuWUbtsaKU-rGUHG7ob3Bi68Uk8OuhNCjlssUg4_5i9UODlCSUpYOTBYQ0VLNDJPSTBYSDRPUFRENy4u&route=shorturl"; // ganti dengan link tujuan
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        fms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://fms.ggfsystem.com/"; // ganti dengan link tujuan
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });


//        //fungsi tombol notify
//        histori = findViewById(R.id.histori);
//        histori.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent gonotif = new Intent(c_1_home.this, HistoryLocation_Form.class);
//                startActivity(gonotif);
//            }
//        });
//        //--------------------------------------------------------------------------------------

        //fungsi tombol realisasi
//        dashb = findViewById(R.id.realisasikerja);
//        dashb.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Intent goreal = new Intent(c_1_home.this, j_realisasi_.class);
////                startActivity(goreal);
//            }
//        });
        //--------------------------------------------------------------------------------------

        //fungsi tombol rencana
//        dashb = findViewById(R.id.frencanakerja);
//        dashb.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Intent gorenc = new Intent(c_1_home.this, f_1_suratperintahkerja.class);
// //               Intent gorenc = new Intent(c_1_home.this, RencanaKerja.class);
// //               startActivity(gorenc);
//            }
//        });
        //--------------------------------------------------------------------------------------


    }

    public void onBackPressed() {
        // Tambahkan kode lain yang Anda inginkan sebelum menutup aktivitas (jika perlu).
       // Menutup aktivitas saat tombol "Back" ditekan.

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            finishAffinity();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    void logout(){
        App.getPref().clear();
        startActivity(new Intent(this, b_2_login.class));
        finish();
        Toast.makeText(this, "Signout berhasil", Toast.LENGTH_LONG).show();
    }

}