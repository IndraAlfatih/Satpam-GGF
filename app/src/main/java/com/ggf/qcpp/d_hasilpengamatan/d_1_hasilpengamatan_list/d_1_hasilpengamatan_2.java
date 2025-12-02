package com.ggf.qcpp.d_hasilpengamatan.d_1_hasilpengamatan_list;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ggf.qcpp.R;

public class d_1_hasilpengamatan_2 extends AppCompatActivity {

    LinearLayout buttonBack;
    RelativeLayout cardh1, cardh2;
    RelativeLayout relathasil1, relathasil2, relathasil3, relathasil4, relathasil5,relathasil6,relathasil7,relathasil8,relathasil9,relathasil10,relathasil11,relathasil12,relathasil13,relathasil14,relathasil15,relathasil16,relathasil17,relathasil18,relathasil19,relathasil20,relathasil21,relathasil22,relathasil23;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_1_hasilpengamatan2);





    }

    public void onBackPressed() {
        // Tambahkan kode lain yang Anda inginkan sebelum menutup aktivitas (jika perlu).
        super.onBackPressed();
        finish(); // Menutup aktivitas saat tombol "Back" ditekan.
    }

}