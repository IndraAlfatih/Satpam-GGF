package com.ggf.qcpp.c_home;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.ggf.qcpp.R;

public class home_pengamat2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_c_1_home_pengamat2);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inisialisasi LinearLayout
        LinearLayout linearLayout1 = findViewById(R.id.realisasikerja);
        LinearLayout linearLayout2 = findViewById(R.id.registertk);
        LinearLayout linearLayout3 = findViewById(R.id.website);
        LinearLayout linearLayout4 = findViewById(R.id.dashboard);

        // Menambahkan OnClickListener
        linearLayout1.setOnClickListener(view -> showAccessAlert("Akses Realisasi Kerja tidak tersedia!"));
        linearLayout2.setOnClickListener(view -> showAccessAlert("Akses Monitoring Lokasi tidak tersedia!"));
        linearLayout3.setOnClickListener(view -> showAccessAlert("Akses Website tidak tersedia!"));
        linearLayout4.setOnClickListener(view -> showAccessAlert("Akses Dashboard tidak tersedia!"));
    }

    private void showAccessAlert(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Peringatan")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }
}
