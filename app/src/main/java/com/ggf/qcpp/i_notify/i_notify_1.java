package com.ggf.qcpp.i_notify;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ggf.qcpp.App;
import com.ggf.qcpp.Prefs;
import com.ggf.qcpp.R;
import com.ggf.qcpp.b_account.model.LoginResponse;
import com.ggf.qcpp.c_home.home_leader;
import com.ggf.qcpp.d_hasilpengamatan.d_2_hasilpengamatan_lahan.mandor.HasilPengamatanActivity;
import com.ggf.qcpp.e_formpengamatan.chopper.model.ChopperModel;
import com.ggf.qcpp.i_notify.model.PengamatanModel;
import com.ggf.qcpp.ui.SweetDialogs;
import com.ggf.qcpp.utils.CommonResponse;
import com.ggf.qcpp.utils.GsonHelper;
import com.google.gson.Gson;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class i_notify_1 extends AppCompatActivity implements INotifyView, NotifyAdapter.OnItemSelected {
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.mVerif)
    AutoCompleteTextView mVerif;

    @BindView(R.id.btnSort)
    ImageView btnSort;

    @BindView(R.id.tvStartDate)
    TextView mStartDate;

    @BindView(R.id.tvEndDate)
    TextView mEndDate;

    LinearLayout buttonBack;
    RelativeLayout cardh1, cardh2;
    INotifyPresenter presenter;
    SweetAlertDialog sweetAlertDialog;
    public NotifyAdapter adapter;
    LoginResponse mProfile;
    private List<PengamatanModel> allData = new ArrayList<>();

    int approval = 0;
    boolean hapus = false ;
    private boolean isAscending = false;
    private static final String[] mListVerif = new String[]{
            "Semua","Mandor", "Kasie", "Kabag"
    };

    private String selectedStartDate = null;
    private String selectedEndDate = null;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i_notif_1);
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
        ButterKnife.bind(this);

        TextView tvSort = findViewById(R.id.tvSort);

//        btnSort.setOnClickListener(v -> {
//            isAscending = !isAscending;
//            sortData();
//            if (isAscending) {
//                btnSort.setImageResource(R.drawable.outline_arrows_more_up_24);
//                tvSort.setText("Terlama");
//            } else {
//                btnSort.setImageResource(R.drawable.outline_arrows_more_down_24);
//                tvSort.setText("Terbaru");
//            }
//        });

        LinearLayout layoutSort = findViewById(R.id.layoutSort);

        layoutSort.setOnClickListener(v -> {
            isAscending = !isAscending;
            sortData();
            if (isAscending) {
                btnSort.setImageResource(R.drawable.outline_arrows_more_up_24);
                tvSort.setText("Terlama");
            } else {
                btnSort.setImageResource(R.drawable.outline_arrows_more_down_24);
                tvSort.setText("Terbaru");
            }
        });

        mStartDate.setOnClickListener(v -> showDatePickerDialog(mStartDate));
        mEndDate.setOnClickListener(v -> showDatePickerDialog(mEndDate));



        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(i_notify_1.this, home_leader.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish(); // biar activity ini langsung ditutup
            }
        });

        String fromClassname = getIntent().getStringExtra("fromClassname");
        if (fromClassname != null & fromClassname.equals("home_leader")) {
            approval = 1;
            hapus = true;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, mListVerif);
        mVerif.setAdapter(adapter);

        // 🔹 Ambil filter terakhir dari SharedPreferences
        SharedPreferences prefs = getSharedPreferences("QCPP_PREFS", MODE_PRIVATE);
        String lastFilter = prefs.getString("last_filter", "Semua");
        mVerif.setText(lastFilter, false);
//        filterRecyclerView(lastFilter);
        sortData();
        // 🔹 Simpan filter saat user pilih
        mVerif.setOnItemClickListener((parent, view, position, id) -> {
            String selectedValue = adapter.getItem(position);

            getSharedPreferences("QCPP_PREFS", MODE_PRIVATE)
                    .edit()
                    .putString("last_filter", selectedValue)
                    .apply();

            filterRecyclerView(selectedValue);
        });

        presenter = new INotifyPresenter(this);
        presenter.getPengamatanByLeader(mProfile.getData().getUser().getEmail(), mProfile.getData().getUser().getId_role());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.clearFocus();
    }

    private void showDatePickerDialog(TextView targetTextView) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePicker = new DatePickerDialog(this,
                (view, year, month, dayOfMonth) -> {
                    String selectedDate = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth); // format YYYY-MM-DD
                    targetTextView.setText(selectedDate);

                    if (targetTextView.getId() == R.id.tvStartDate) {
                        selectedStartDate = selectedDate;
                    } else if (targetTextView.getId() == R.id.tvEndDate) {
                        selectedEndDate = selectedDate;
                    }

                    applyDateFilter(); // panggil filter setelah pilih tanggal
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePicker.show();
    }

//    private void applyDateFilter() {
//        List<PengamatanModel> filteredData = new ArrayList<>();
//
//        for (PengamatanModel item : allData) {
//            String itemDate = item.getCREATED_AT(); // Pastikan formatnya sama (YYYY-MM-DD)
//
//            boolean withinRange = true;
//
//            if (selectedStartDate != null && itemDate.compareTo(selectedStartDate) < 0) {
//                withinRange = false;
//            }
//            if (selectedEndDate != null && itemDate.compareTo(selectedEndDate) > 0) {
//                withinRange = false;
//            }
//
//            if (withinRange) {
//                filteredData.add(item);
//            }
//        }
//
//        // Terapkan sort sesuai mode sekarang
//        Collections.sort(filteredData, (o1, o2) -> {
//            if (isAscending) {
//                return o1.getCREATED_AT().compareTo(o2.getCREATED_AT());
//            } else {
//                return o2.getCREATED_AT().compareTo(o1.getCREATED_AT());
//            }
//        });
//
//
//        adapter = new NotifyAdapter(filteredData, this, this, mProfile.getData().getUser().getEmail(), hapus);
//        mRecyclerView.setAdapter(adapter);
//    }

    private void applyDateFilter() {
        List<PengamatanModel> filteredData = new ArrayList<>();

        // bikin endDate jadi full timestamp
        String endDateTime = null;
        if (selectedEndDate != null) {
            endDateTime = selectedEndDate + " 23:59:59";
        }

        for (PengamatanModel item : allData) {
            String itemDate = item.getCREATED_AT(); // tetap full datetime

            boolean withinRange = true;

            if (selectedStartDate != null && itemDate.compareTo(selectedStartDate) < 0) {
                withinRange = false;
            }
            if (endDateTime != null && itemDate.compareTo(endDateTime) > 0) {
                withinRange = false;
            }

            if (withinRange) {
                filteredData.add(item);
            }
        }

        // sort pakai datetime langsung
        Collections.sort(filteredData, (o1, o2) -> {
            if (isAscending) {
                return o1.getCREATED_AT().compareTo(o2.getCREATED_AT());
            } else {
                return o2.getCREATED_AT().compareTo(o1.getCREATED_AT());
            }
        });

        adapter = new NotifyAdapter(filteredData, this, this, mProfile.getData().getUser().getEmail(), hapus);
        mRecyclerView.setAdapter(adapter);
    }



//    private void sortData() {
//        List<PengamatanModel> filteredData = new ArrayList<>();
//
//        // Gunakan filter terakhir yang tersimpan
//        SharedPreferences prefs = getSharedPreferences("QCPP_PREFS", MODE_PRIVATE);
//        String lastFilter = prefs.getString("last_filter", "Semua");
//
//        for (PengamatanModel item : allData) {
//            if (lastFilter.equals("Semua")) {
//                filteredData.add(item);
//            } else if (lastFilter.equals("Mandor") && item.getVerify_mandor() == approval && item.getVerify_kasi() == 0 && item.getVerify_kabag() == 0) {
//                filteredData.add(item);
//            } else if (lastFilter.equals("Kasie") && item.getVerify_kasi() == approval && item.getVerify_mandor() == 1 && item.getVerify_kabag() == 0) {
//                filteredData.add(item);
//            } else if (lastFilter.equals("Kabag") && item.getVerify_kabag() == approval && item.getVerify_kasi() == 1 && item.getVerify_mandor() == 1) {
//                filteredData.add(item);
//            }
//        }
//
//        // Sort berdasarkan CREATED_AT
//        Collections.sort(filteredData, (o1, o2) -> {
//            if (isAscending) {
//                return o1.getCREATED_AT().compareTo(o2.getCREATED_AT());
//            } else {
//                return o2.getCREATED_AT().compareTo(o1.getCREATED_AT());
//            }
//        });
//        if (selectedStartDate != null || selectedEndDate != null) {
//            List<PengamatanModel> tempData = new ArrayList<>();
//            for (PengamatanModel item : filteredData) {
//                String itemDate = item.getCREATED_AT();
//                boolean withinRange = true;
//                if (selectedStartDate != null && itemDate.compareTo(selectedStartDate) < 0) withinRange = false;
//                if (selectedEndDate != null && itemDate.compareTo(selectedEndDate) > 0) withinRange = false;
//                if (withinRange) tempData.add(item);
//            }
//            filteredData = tempData;
//        }
//        adapter = new NotifyAdapter(filteredData, this, this, mProfile.getData().getUser().getEmail(), hapus);
//        mRecyclerView.setAdapter(adapter);
//    }

//    private void sortData() {
//        List<PengamatanModel> filteredData = new ArrayList<>();
//
//        // Gunakan filter terakhir yang tersimpan
//        SharedPreferences prefs = getSharedPreferences("QCPP_PREFS", MODE_PRIVATE);
//        String lastFilter = prefs.getString("last_filter", "Semua");
//
//        for (PengamatanModel item : allData) {
//            if (lastFilter.equals("Semua")) {
//                filteredData.add(item);
//            } else if (lastFilter.equals("Mandor") && item.getVerify_mandor() == approval && item.getVerify_kasi() == 0 && item.getVerify_kabag() == 0) {
//                filteredData.add(item);
//            } else if (lastFilter.equals("Kasie") && item.getVerify_kasi() == approval && item.getVerify_mandor() == 1 && item.getVerify_kabag() == 0) {
//                filteredData.add(item);
//            } else if (lastFilter.equals("Kabag") && item.getVerify_kabag() == approval && item.getVerify_kasi() == 1 && item.getVerify_mandor() == 1) {
//                filteredData.add(item);
//            }
//        }
//
//        // Sort berdasarkan CREATED_AT (datetime string)
//        Collections.sort(filteredData, (o1, o2) -> {
//            if (isAscending) {
//                return o1.getCREATED_AT().compareTo(o2.getCREATED_AT());
//            } else {
//                return o2.getCREATED_AT().compareTo(o1.getCREATED_AT());
//            }
//        });
//
//        // 🔹 Tambahkan 00:00:00 & 23:59:59 agar dibandingkan dengan datetime juga
//        String startDateTime = null;
//        String endDateTime = null;
//
//        if (selectedStartDate != null) {
//            startDateTime = selectedStartDate + " 00:00:00";
//        }
//        if (selectedEndDate != null) {
//            endDateTime = selectedEndDate + " 23:59:59";
//        }
//
//        if (startDateTime != null || endDateTime != null) {
//            List<PengamatanModel> tempData = new ArrayList<>();
//            for (PengamatanModel item : filteredData) {
//                String itemDate = item.getCREATED_AT(); // full datetime
//                boolean withinRange = true;
//
//                if (startDateTime != null && itemDate.compareTo(startDateTime) < 0) withinRange = false;
//                if (endDateTime != null && itemDate.compareTo(endDateTime) > 0) withinRange = false;
//
//                if (withinRange) tempData.add(item);
//            }
//            filteredData = tempData;
//        }
//
//        adapter = new NotifyAdapter(filteredData, this, this, mProfile.getData().getUser().getEmail(), hapus);
//        mRecyclerView.setAdapter(adapter);
//    }

    private void sortData() {
        List<PengamatanModel> filteredData = new ArrayList<>();

        // 🔹 Ambil filter terakhir
        SharedPreferences prefs = getSharedPreferences("QCPP_PREFS", MODE_PRIVATE);
        String lastFilter = prefs.getString("last_filter", "Semua");

        for (PengamatanModel item : allData) {
            if (lastFilter.equals("Semua")) {
                filteredData.add(item);
            } else if (lastFilter.equals("Mandor") && item.getVerify_mandor() == approval && item.getVerify_kasi() == 0 && item.getVerify_kabag() == 0) {
                filteredData.add(item);
            } else if (lastFilter.equals("Kasie") && item.getVerify_kasi() == approval && item.getVerify_mandor() == 1 && item.getVerify_kabag() == 0) {
                filteredData.add(item);
            } else if (lastFilter.equals("Kabag") && item.getVerify_kabag() == approval && item.getVerify_kasi() == 1 && item.getVerify_mandor() == 1) {
                filteredData.add(item);
            }
        }

        // 🔹 Sorting sesuai toggle
        Collections.sort(filteredData, (o1, o2) -> {
            if (isAscending) {
                return o1.getCREATED_AT().compareTo(o2.getCREATED_AT());
            } else {
                return o2.getCREATED_AT().compareTo(o1.getCREATED_AT());
            }
        });

        // 🔹 Filter tanggal (kalau ada)
        String startDateTime = selectedStartDate != null ? selectedStartDate + " 00:00:00" : null;
        String endDateTime   = selectedEndDate   != null ? selectedEndDate + " 23:59:59" : null;

        if (startDateTime != null || endDateTime != null) {
            List<PengamatanModel> tempData = new ArrayList<>();
            for (PengamatanModel item : filteredData) {
                String itemDate = item.getCREATED_AT();
                boolean withinRange = true;

                if (startDateTime != null && itemDate.compareTo(startDateTime) < 0) withinRange = false;
                if (endDateTime   != null && itemDate.compareTo(endDateTime) > 0) withinRange = false;

                if (withinRange) tempData.add(item);
            }
            filteredData = tempData;
        }

        adapter = new NotifyAdapter(filteredData, this, this, mProfile.getData().getUser().getEmail(), hapus);
        mRecyclerView.setAdapter(adapter);
    }



    @Override
    public void onSubmit() {}

    @Override
    public void onVerified(String guid, ChopperModel model) {}

    @Override
    public void onVerifiedSuccess() {}

//    @Override
//    public void onDataReady(List<PengamatanModel> data) {
//        allData.clear();
//        allData.addAll(data);
//
//        // tetap gunakan filter terakhir yang tersimpan
//        SharedPreferences prefs = getSharedPreferences("QCPP_PREFS", MODE_PRIVATE);
//        String lastFilter = prefs.getString("last_filter", "Semua");
//        filterRecyclerView(lastFilter);
//    }

    @Override
    public void onDataReady(List<PengamatanModel> data) {
        allData.clear();
        allData.addAll(data);

        // tetap gunakan filter terakhir yang tersimpan
        SharedPreferences prefs = getSharedPreferences("QCPP_PREFS", MODE_PRIVATE);
        String lastFilter = prefs.getString("last_filter", "Semua");

        // langsung pakai sortData() biar sinkron
        sortData();
    }


//    private void filterRecyclerView(String filter) {
//        List<PengamatanModel> filteredData = new ArrayList<>();
//
//        for (PengamatanModel item : allData) {
//            if (filter.equals("Semua")) {
//                filteredData.add(item);
//            } else if (filter.equals("Mandor") && item.getVerify_mandor() == approval && item.getVerify_kasi() == 0 && item.getVerify_kabag() == 0) {
//                filteredData.add(item);
//            } else if (filter.equals("Kasie") && item.getVerify_kasi() == approval && item.getVerify_mandor() == 1 && item.getVerify_kabag() == 0) {
//                filteredData.add(item);
//            } else if (filter.equals("Kabag") && item.getVerify_kabag() == approval && item.getVerify_kasi() == 1 && item.getVerify_mandor() == 1) {
//                filteredData.add(item);
//            }
//        }
//
//        Collections.sort(filteredData, (o1, o2) -> o2.getCREATED_AT().compareTo(o1.getCREATED_AT()));
//        if (selectedStartDate != null || selectedEndDate != null) {
//            List<PengamatanModel> tempData = new ArrayList<>();
//            for (PengamatanModel item : filteredData) {
//                String itemDate = item.getCREATED_AT();
//                boolean withinRange = true;
//                if (selectedStartDate != null && itemDate.compareTo(selectedStartDate) < 0) withinRange = false;
//                if (selectedEndDate != null && itemDate.compareTo(selectedEndDate) > 0) withinRange = false;
//                if (withinRange) tempData.add(item);
//            }
//            filteredData = tempData;
//        }
//        if (selectedStartDate != null || selectedEndDate != null) {
//            List<PengamatanModel> tempData = new ArrayList<>();
//            for (PengamatanModel item : filteredData) {
//                String itemDate = item.getCREATED_AT();
//                boolean withinRange = true;
//                if (selectedStartDate != null && itemDate.compareTo(selectedStartDate) < 0) withinRange = false;
//                if (selectedEndDate != null && itemDate.compareTo(selectedEndDate) > 0) withinRange = false;
//                if (withinRange) tempData.add(item);
//            }
//            filteredData = tempData;
//        }
//        adapter = new NotifyAdapter(filteredData, this, this, mProfile.getData().getUser().getEmail(),hapus);
//        mRecyclerView.setAdapter(adapter);
//    }

    private void filterRecyclerView(String filter) {
        // Simpan filter ke SharedPreferences
        getSharedPreferences("QCPP_PREFS", MODE_PRIVATE)
                .edit()
                .putString("last_filter", filter)
                .apply();

        // Setelah ganti filter, langsung refresh dengan sortData()
        sortData();
    }

    @Override
    public void showLoadingIndicator() {
        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText("Loading ...");
        sweetAlertDialog.show();
    }

    @Override
    public void hideLoadingIndicator() {
        sweetAlertDialog.dismiss();
    }

    @Override
    public void onNetworkError(String cause) {}

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onSelect(PengamatanModel model) {
        Log.d("datanyamodel", new Gson().toJson(model));
        Intent intent = new Intent(this, HasilPengamatanActivity.class);
        intent.putExtra("model", model);
        intent.putExtra("fromClassname", getClass().getSimpleName());
        startActivity(intent);
    }

    @Override
    public void onDeleteSuccess(CommonResponse response) {
        SweetDialogs.commonSuccessWithIntent(this,response.getRm(),string -> presenter.getPengamatanByLeader(mProfile.getData().getUser().getEmail(), mProfile.getData().getUser().getId_role()));

    }

    public void Invalidate(){

    }
    @Override
    public void onHapus(PengamatanModel model) {
        SweetDialogs.confirmDialog(this, "Hapus Data",
                "Apakah anda yakin ingin hapus?", "Berhasil Menghapus Data.", string -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        presenter.onDeletePengamatan(model.getNO_SPK());
                    }
                });
    }


}


