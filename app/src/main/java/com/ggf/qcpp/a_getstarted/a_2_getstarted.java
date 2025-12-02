package com.ggf.qcpp.a_getstarted;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.ggf.qcpp.App;
import com.ggf.qcpp.Prefs;
import com.ggf.qcpp.R;
import com.ggf.qcpp.b_account.AuthPresenter;
import com.ggf.qcpp.b_account.b_2_login;
import com.ggf.qcpp.c_home.c_1_home;
import com.ggf.qcpp.c_home.home_leader;

public class a_2_getstarted extends AppCompatActivity {

    Button btn_login;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_2_getstarted);

        if (App.getPref().getBoolean(Prefs.PREF_IS_LOGEDIN, false)) {
            if (App.getPref().getString(Prefs.PREF_ROLE, "").equals("6")) {
                this.goToDashboardPengamat();
            }
            else if (App.getPref().getString(Prefs.PREF_ROLE, "").equals("5")) {
                this.goToDashboardMandor();
            }
        }

        btn_login = findViewById(R.id.button_getstartedtologin);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gologin = new Intent(a_2_getstarted.this, b_2_login.class);
                startActivity(gologin);
            }
        });

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
    }

    public void onBackPressed() {
        // Tambahkan kode lain yang Anda inginkan sebelum menutup aktivitas (jika perlu).
        super.onBackPressed();
        finish(); // Menutup aktivitas saat tombol "Back" ditekan.
    }

    void goToDashboardPengamat(){
        startActivity(new Intent(this , c_1_home.class));
        finish();
    }

    void goToDashboardMandor(){
        startActivity(new Intent(this , home_leader.class));
        finish();
    }

}