package com.ggf.qcpp.b_account;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ggf.qcpp.R;

public class b_3_profil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_3_profil);
    }

    public void onBackPressed() {
        // Tambahkan kode lain yang Anda inginkan sebelum menutup aktivitas (jika perlu).
        finish(); // Menutup aktivitas saat tombol "Back" ditekan.
    }

}