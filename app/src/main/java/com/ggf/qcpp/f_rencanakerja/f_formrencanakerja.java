package com.ggf.qcpp.f_rencanakerja;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.ggf.qcpp.R;

public class f_formrencanakerja extends AppCompatActivity {

    String[] itemAktifitas = {"Chopper", "Pembajakan", "Agregat", "Ridger SR", "Kebersihan Bonggol", "Analisa pH Tanah", "Bibit di Lokasi Petik", "Kebersihan Transport", "Pool Dipping", "Bibit di Lokasi Tanam", "Kualitas Tanam", "Penambahan Baris", "Gudang Mixer", "Adukan Bahan di Lokasi", "Aplikasi Boom Spray", "Kebersihan Panen", "Seset Bonggol", "Bonggol Tidak Terseset", "Bibit Singkong Stek Panjang", "Bibit Singkong Stek Pendek", "Tanam Singkong"};
    String[] itemLokasi = {"301A", "301B", "301C"};

    AutoCompleteTextView autoCompleteTextView;

    ArrayAdapter<String> adapterItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_formrencanakerja);

        autoCompleteTextView = findViewById(R.id.autoCompleteAktifitas);
        adapterItem = new ArrayAdapter<String>(this, R.layout.list_item, itemAktifitas);

        autoCompleteTextView.setAdapter(adapterItem);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String item = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(f_formrencanakerja.this, "Aktifitas: " + item, Toast.LENGTH_SHORT).show();
            }
        });

        autoCompleteTextView = findViewById(R.id.autoCompleteLokasi);
        adapterItem = new ArrayAdapter<String>(this, R.layout.list_item, itemLokasi);

        autoCompleteTextView.setAdapter(adapterItem);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String item = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(f_formrencanakerja.this, "Lokasi: " + item, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void onBackPressed() {
        // Tambahkan kode lain yang Anda inginkan sebelum menutup aktivitas (jika perlu).
        finish(); // Menutup aktivitas saat tombol "Back" ditekan.
    }

}