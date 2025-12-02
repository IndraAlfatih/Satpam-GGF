package com.ggf.qcpp.c_home;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ggf.qcpp.App;
import com.ggf.qcpp.Prefs;
import com.ggf.qcpp.R;
import com.ggf.qcpp.b_account.b_2_login;
import com.ggf.qcpp.b_account.model.LoginResponse;
import com.ggf.qcpp.d_hasilpengamatan.d_2_hasilpengamatan_lahan.mandor.HasilPengamatanActivity;
import com.ggf.qcpp.g_website.g_website;
import com.ggf.qcpp.i_notify.i_notify_1;
import com.ggf.qcpp.j_realisasi.j_realisasi_;
import com.ggf.qcpp.rencana_kerja.RencanaKerja;
import com.ggf.qcpp.utils.GsonHelper;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;

public class home_leader extends AppCompatActivity {
    @BindView(R.id.mNotifikasi)
    LinearLayout mNotifikasi;

    @BindView(R.id.hasilpengamatan)
    LinearLayout hasilpengamatan;

    @BindView(R.id.frencanakerja)
    LinearLayout frencanakerja;
    @BindView(R.id.realisasikerja)
    LinearLayout realisasikerja;
    @BindView(R.id.website)
    LinearLayout website;
    @BindView(R.id.asisstant)
    LinearLayout asisstant;

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
    @BindView(R.id.dashboard)
    LinearLayout dashboard;

    @BindView(R.id.inventori)
    LinearLayout inventori;

    @BindView(R.id.permintaanbarang)
    LinearLayout permintaanbarang;

    @BindView(R.id.histori)
    LinearLayout histori;

    @BindView(R.id.mBtnLogout)
    Button mBtnLogout;
    @BindView(R.id.mUsername)
    TextView mUsername;

    @BindView(R.id.mRole)
    TextView mRole;

    LoginResponse mProfile ;

    String Login ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_leader);

        ButterKnife.bind(this);
        mBtnLogout.setOnClickListener(view->this.logout());
        mProfile = (LoginResponse) GsonHelper.parseGson(
                App.getPref().getString(Prefs.PREF_STORE_PROFILE, ""),
                new LoginResponse()
        );

        //StatusBar Transparant
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


        mUsername.setText(mProfile.getData().getUser().getName());

        mRole.setText(mProfile.getData().getUser().getRole());
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

//        frencanakerja.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent gorenc = new Intent(home_leader.this, RencanaKerja.class);
//                startActivity(gorenc);
//            }
//        });

        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://osiqcpp.ggfsystem.com/home"; // ganti dengan link tujuan
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://osiqcpp.ggfsystem.com/login"; // ganti dengan link tujuan
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        inventori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://sites.google.com/view/ggf-qcpp2/digital-library/report/inventory-utama"; // ganti dengan link tujuan
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        permintaanbarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://forms.office.com/pages/responsepage.aspx?id=GuWUbtsaKU-rGUHG7ob3Bi68Uk8OuhNCjlssUg4_5i9UMzA3VUMwSVJaNlg5MlpLSFA0V0JVVEM5TC4u"; // ganti dengan link tujuan
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
        https://sites.google.com/view/history-lokasi/history-lokasi-kasie

        histori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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


        realisasikerja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
  //              Intent goreal = new Intent(home_leader.this, j_realisasi_.class);
  //              startActivity(goreal);
            }
        });

        mNotifikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gonotif = new Intent(home_leader.this, i_notify_1.class);
                gonotif.putExtra("login", mUsername.getText().toString());
                gonotif.putExtra("fromClassname", "notifikasi");
                startActivity(gonotif);
            }
        });

        hasilpengamatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home_leader.this, i_notify_1.class);
                intent.putExtra("fromClassname", "home_leader");
                startActivity(intent);
            }
        });




    }
    void logout(){
        App.getPref().clear();
        startActivity(new Intent(this, b_2_login.class));
        finish();
        Toast.makeText(this, "Signout berhasil", Toast.LENGTH_LONG).show();
    }
}