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

public class home_leader2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_c_1_home_leader2);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Menginisialisasi LinearLayout
        LinearLayout layout1 = findViewById(R.id.frencanakerja);
        LinearLayout layout2 = findViewById(R.id.fpengamatan);

        // Menangani klik LinearLayout 1
        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAccessAlert("Akses Form Rencana Kerja tidak tersedia!");
            }
        });

        // Menangani klik LinearLayout 2
        layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAccessAlert("Akses Form Pengamatan tidak tersedia!");
            }
        });
    }

    private void showAccessAlert(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Peringatan")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }
}
