package com.ggf.qcpp.e_formpengamatan.z_satpam;

import static com.ggf.qcpp.utils.Utils.generateTglSekarang;
import static com.ggf.qcpp.utils.Utils.goToListPengamatan;
import static com.ggf.qcpp.utils.Utils.now;
import static com.ggf.qcpp.utils.Utils.parseFloatDefault;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ggf.qcpp.App;
import com.ggf.qcpp.Prefs;
import com.ggf.qcpp.R;
import com.ggf.qcpp.b_account.model.LoginResponse;
import com.ggf.qcpp.c_home.c_1_home;
import com.ggf.qcpp.e_formpengamatan.e_1_formpengamatan_list.e_1_list_lahan;
import com.ggf.qcpp.e_formpengamatan.z_satpam.model.SatpamModel;
import com.ggf.qcpp.e_formpengamatan.z_satpam.model.PlotModel;
import com.ggf.qcpp.e_formpengamatan.z_satpam.model.SampleModel;
import com.ggf.qcpp.network.SQLiteHelper;
import com.ggf.qcpp.ui.SweetDialogs;
import com.ggf.qcpp.utils.GsonHelper;
import com.ggf.qcpp.utils.TemporaryFormStorage;
import com.google.gson.Gson;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import com.ggf.qcpp.utils.Utils;
import java.util.List;
import java.util.Locale;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LembarMutasi extends AppCompatActivity implements View.OnClickListener, IFormPengamatanLembarMutasiView {

    LoginResponse mProfile;

    @BindView(R.id.mAktivitasPost)
    AutoCompleteTextView mAktivitasPost;

    @BindView(R.id.mLokasi)
    EditText mLokasi;

    @BindView(R.id.mHari)
    EditText mHari;

    @BindView(R.id.mShift)
    AutoCompleteTextView mShift;

    @BindView(R.id.mNama1)
    EditText mNama1;
    @BindView(R.id.mIndeks1)
    EditText mIndeks1;
    @BindView(R.id.mJabatan1)
    EditText mJabatan1;
    @BindView(R.id.mKet1)
    EditText mKet1;

    @BindView(R.id.mNama2)
    EditText mNama2;
    @BindView(R.id.mIndeks2)
    EditText mIndeks2;
    @BindView(R.id.mJabatan2)
    EditText mJabatan2;
    @BindView(R.id.mKet2)
    EditText mKet2;

    @BindView(R.id.mNama3)
    EditText mNama3;
    @BindView(R.id.mIndeks3)
    EditText mIndeks3;
    @BindView(R.id.mJabatan3)
    EditText mJabatan3;
    @BindView(R.id.mKet3)
    EditText mKet3;

    @BindView(R.id.mNama4)
    EditText mNama4;
    @BindView(R.id.mIndeks4)
    EditText mIndeks4;
    @BindView(R.id.mJabatan4)
    EditText mJabatan4;
    @BindView(R.id.mKet4)
    EditText mKet4;

    @BindView(R.id.mNama5)
    EditText mNama5;
    @BindView(R.id.mIndeks5)
    EditText mIndeks5;
    @BindView(R.id.mJabatan5)
    EditText mJabatan5;
    @BindView(R.id.mKet5)
    EditText mKet5;

    @BindView(R.id.mNama6)
    EditText mNama6;
    @BindView(R.id.mIndeks6)
    EditText mIndeks6;
    @BindView(R.id.mJabatan6)
    EditText mJabatan6;
    @BindView(R.id.mKet6)
    EditText mKet6;

    @BindView(R.id.mPlot)
    EditText mPlot;

    @BindView(R.id.mPukul)
    EditText mPukul;


    @BindView(R.id.mAddSample)
    ImageView mAddSample;

    @BindView(R.id.mKeterangan)
    EditText mKeterangan;

    //----------------------------------------------------

    @BindView(R.id.containerPlotData)
    LinearLayout containerPlotData;

    @BindView(R.id.mSubmit)
    Button mSubmit;

    @BindView(R.id.mUsername)
    TextView mUsername;

    @BindView(R.id.mNow)
    TextView mNow;

    @BindView(R.id.layoutSample)
    LinearLayout layoutSample;

    View rowView;
    View rowViewPlot;
    View viewnya = null;
    String plot = "0";
    SatpamModel model;

    int index = 1;

    List<PlotModel> dataPlot = new ArrayList<>();
    List<SampleModel> dataSample = new ArrayList<>();

    SweetAlertDialog sweetAlertDialog;
    SampleModel sampleModel = null;
    PlotModel plotModel = null;
    FormPengamatanLembarMutasiPresenter presenter;

    private static final String[] mAktivitasPost2 = new String[]{"POS JAGA", "PATROLI"};
    private static final String[] mShift2 = {"SHIFT 1 (07:00 - 15:00)", "SHIFT 2 (15:00 - 23:00)", "SHIFT 3 (23:00 - 07:00)"};

    private ScrollView scrollView;
    private View contentView;
    private View focusedView;
    private static final String DRAFT_KEY = "draft_mutasi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_satpam_lembar_mutasi);

        // 1. Bind semua view terlebih dahulu
        ButterKnife.bind(this);

        // 2. Set data profile, username, dll.
        mProfile = (LoginResponse) GsonHelper.parseGson(App.getPref().getString(Prefs.PREF_STORE_PROFILE, ""), new LoginResponse());
        mUsername.setText(mProfile.getData().getUser().getName());
        mNow.setText(now());

        // 3. Atur WindowInsets (EdgeToEdge)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 4. StatusBar Transparant
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            getWindow().setDecorFitsSystemWindows(false);
        } else {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        // 5. Setelah semua view siap, baru atur adapter untuk AutoCompleteTextView
        ArrayAdapter<String> adapterAktivitas = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mAktivitasPost2);
        mAktivitasPost.setAdapter(adapterAktivitas);
        ArrayAdapter<String> adapterShift = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mShift2);
        mShift.setAdapter(adapterShift);

        // 6. Atur filter dan listener lainnya
        mLokasi.setFilters(new InputFilter[]{new InputFilter.AllCaps()}); // Menggunakan variabel dari ButterKnife

        // Menonaktifkan input keyboard agar user memilih dari dropdown
        mAktivitasPost.setKeyListener(null);
        mShift.setKeyListener(null);

        // Threshold 1 agar dropdown muncul setelah 1 karakter (atau saat diklik jika keylistener null)        mAktivitasPost.setThreshold(1);
        mAktivitasPost.setThreshold(1);
        mShift.setThreshold(1);

        // --- KODE BARU UNTUK DATE PICKER ---
        mHari.setOnClickListener(v -> showDatePickerDialog());
        // ------------------------------------

        // --- KODE BARU UNTUK TIME PICKER ---
        mPukul.setOnClickListener(v -> showTimePickerDialog());
        // ------------------------------------

        // 7. Ambil data dari Intent dengan aman
        model = (SatpamModel) getIntent().getSerializableExtra("model");
        Log.d("Model", new Gson().toJson(model));

        // 9. Inisialisasi Presenter dan listener tombol
        presenter = new FormPengamatanLembarMutasiPresenter(this);
        mSubmit.setOnClickListener(this);
        mAddSample.setOnClickListener(this);

    }

    // Metode baru untuk menampilkan TimePickerDialog
    private void showTimePickerDialog() {
        // Ambil waktu saat ini untuk dijadikan waktu default
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // Buat TimePickerDialog baru
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                LembarMutasi.this,
                (view, hourOfDay, minuteOfHour) -> {
                    // Method ini dipanggil saat user memilih waktu dan menekan "OK"

                    // Format waktu agar selalu 2 digit (misal: 07:05)
                    String formattedTime = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minuteOfHour);

                    // Set teks di EditText mPukul
                    mPukul.setText(formattedTime);
                },
                hour, minute, true); // 'true' untuk menggunakan format 24 jam

        // Tampilkan dialog
        timePickerDialog.show();
    }

    // Metode baru untuk menampilkan DatePickerDialog
    private void showDatePickerDialog() {
        // Ambil tanggal saat ini untuk dijadikan tanggal default
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Buat DatePickerDialog baru
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                LembarMutasi.this,
                (view, year1, monthOfYear, dayOfMonth) -> {
                    // Method ini dipanggil saat user memilih tanggal dan menekan "OK"

                    // Format tanggal agar sesuai (dd-MM-yyyy)
                    // Ingat, monthOfYear dimulai dari 0 (Januari = 0) jadi perlu ditambah 1
                    Calendar selectedDate = Calendar.getInstance();
                    selectedDate.set(year1, monthOfYear, dayOfMonth);
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                    String formattedDate = sdf.format(selectedDate.getTime());

                    // Set teks di EditText mHari
                    mHari.setText(formattedDate);
                },
                year, month, day);

        // Tampilkan dialog
        datePickerDialog.show();
    }

    private void clearForm() {
        // Simpan sementara
        // Kosongkan semua input header
        mAktivitasPost.setText("", false); // false agar tidak memicu dropdown
        mLokasi.setText("");
        mHari.setText("");
        mShift.setText("", false); // false agar tidak memicu dropdown

        mNama1.setText("");
        mIndeks1.setText("");
        mJabatan1.setText("");
        mKet1.setText("");

        mNama2.setText("");
        mIndeks2.setText("");
        mJabatan2.setText("");
        mKet2.setText("");

        mNama3.setText("");
        mIndeks3.setText("");
        mJabatan3.setText("");
        mKet3.setText("");

        mNama4.setText("");
        mIndeks4.setText("");
        mJabatan4.setText("");
        mKet4.setText("");

        mNama5.setText("");
        mIndeks5.setText("");
        mJabatan5.setText("");
        mKet5.setText("");

        mNama6.setText("");
        mIndeks6.setText("");
        mJabatan6.setText("");
        mKet6.setText("");

        mPukul.setText("");
        mKeterangan.setText(""); // Jangan lupa clear keterangan juga

        // Kosongkan data di memory
        if (dataPlot != null) dataPlot.clear();
        if (dataSample != null) dataSample.clear();

        // Kosongkan container sample di UI
        if (containerPlotData != null) containerPlotData.removeAllViews();


        // Reset model juga
        model = new SatpamModel();

        // Hapus draft tersimpan biar nggak balik lagi
        TemporaryFormStorage.clearDraft(this, DRAFT_KEY);

        Log.d("FormReset", "Form mutasi cleared");
    }

    private void saveTemporaryData() {
        // Simpan sementara
        SatpamModel draft = new SatpamModel();
        draft.setAKTIVITAS_POST(mAktivitasPost.getText().toString());
        draft.setLOKASI(mLokasi.getText().toString());
        draft.setTANGGAL_MUTASI(mHari.getText().toString());
        draft.setSHIFT(mShift.getText().toString());

        draft.setNAMA1(mNama1.getText().toString());
        draft.setINDEKS1(mIndeks1.getText().toString());
        draft.setJABATAN1(mJabatan1.getText().toString());
        draft.setKETERANGAN1(mKet1.getText().toString());

        draft.setNAMA2(mNama2.getText().toString());
        draft.setINDEKS2(mIndeks2.getText().toString());
        draft.setJABATAN2(mJabatan2.getText().toString());
        draft.setKETERANGAN2(mKet2.getText().toString());

        draft.setNAMA3(mNama3.getText().toString());
        draft.setINDEKS3(mIndeks3.getText().toString());
        draft.setJABATAN3(mJabatan3.getText().toString());
        draft.setKETERANGAN3(mKet3.getText().toString());

        draft.setNAMA4(mNama4.getText().toString());
        draft.setINDEKS4(mIndeks4.getText().toString());
        draft.setJABATAN4(mJabatan4.getText().toString());
        draft.setKETERANGAN4(mKet4.getText().toString());

        draft.setNAMA5(mNama5.getText().toString());
        draft.setINDEKS5(mIndeks5.getText().toString());
        draft.setJABATAN5(mJabatan5.getText().toString());
        draft.setKETERANGAN5(mKet5.getText().toString());

        draft.setNAMA6(mNama6.getText().toString());
        draft.setINDEKS6(mIndeks6.getText().toString());
        draft.setJABATAN6(mJabatan6.getText().toString());
        draft.setKETERANGAN6(mKet6.getText().toString());

        draft.setJAM_KEGIATAN(mPukul.getText().toString());
        draft.setNO_PLOT(mPlot.getText().toString());
        draft.setKETERANGAN(mKeterangan.getText().toString()); // Gunakan mKeterangan, bukan mKet

        draft.setDATA(dataPlot); // simpan semua plot + sample

        TemporaryFormStorage.saveDraft(this, DRAFT_KEY, draft);
        Log.d("DraftSaved", new Gson().toJson(draft));
    }

    //data header
    private void loadTemporaryData() {
        // Simpan sementara
        SatpamModel draft = TemporaryFormStorage.loadDraft(this, DRAFT_KEY, SatpamModel.class);
        if (draft != null) {
            Log.d("DraftLoaded", new Gson().toJson(draft));

            // Gunakan setText(text, false) untuk AutoCompleteTextView agar tidak memicu filter/dropdown saat load
            mAktivitasPost.setText(draft.getAKTIVITAS_POST(), false);
            mLokasi.setText(draft.getLOKASI());
            mHari.setText(draft.getTANGGAL_MUTASI());
            mShift.setText(draft.getSHIFT(), false);

            mNama1.setText(draft.getNAMA1());
            mIndeks1.setText(draft.getINDEKS1());
            mJabatan1.setText(draft.getJABATAN1());
            mKet1.setText(draft.getKETERANGAN1());

            mNama2.setText(draft.getNAMA2());
            mIndeks2.setText(draft.getINDEKS2());
            mJabatan2.setText(draft.getJABATAN2());
            mKet2.setText(draft.getKETERANGAN2());

            mNama3.setText(draft.getNAMA3());
            mIndeks3.setText(draft.getINDEKS3());
            mJabatan3.setText(draft.getJABATAN3());
            mKet3.setText(draft.getKETERANGAN3());

            mNama4.setText(draft.getNAMA4());
            mIndeks4.setText(draft.getINDEKS4());
            mJabatan4.setText(draft.getJABATAN4());
            mKet4.setText(draft.getKETERANGAN4());

            mNama5.setText(draft.getNAMA5());
            mIndeks5.setText(draft.getINDEKS5());
            mJabatan5.setText(draft.getJABATAN5());
            mKet5.setText(draft.getKETERANGAN5());

            mNama6.setText(draft.getNAMA6());
            mIndeks6.setText(draft.getINDEKS6());
            mJabatan6.setText(draft.getJABATAN6());
            mKet6.setText(draft.getKETERANGAN6());

            mPlot.setText(draft.getNO_PLOT());
            mPukul.setText(draft.getJAM_KEGIATAN());
            mKeterangan.setText(draft.getKETERANGAN()); // Gunakan mKeterangan, bukan mKet

            if (draft.getDATA() != null) {
                dataPlot = draft.getDATA();
                for (PlotModel p : dataPlot) {
                    if (p.getSAMPLE() != null) {
                        for (SampleModel s : p.getSAMPLE()) {
                            restoreSampleView(s);
                        }
                    }
                }
            }

            // ✅ Tambahkan ini di akhir: rebind adapter biar dropdown aktif lagi
            mAktivitasPost.post(() -> {
                ArrayAdapter<String> adapterAktivitas = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mAktivitasPost2);
                mAktivitasPost.setAdapter(adapterAktivitas);
            });

            mShift.post(() -> {
                ArrayAdapter<String> adapterShift = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mShift2);
            });
        }
    }

    // data detail
    private void restoreSampleView(SampleModel sampleModel) {
        // 1. Gunakan LayoutInflater untuk membuat View dari file XML yang baru dibuat
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.data_mutasi_sample_item, null);

        // 2. Ambil referensi ke TextView dari layout item tersebut
        final TextView kegiatanInfo = rowView.findViewById(R.id.item_kegiatan_info);
        final TextView waktuInfo = rowView.findViewById(R.id.item_waktu_info);

        // 3. Isi TextView dengan data dari objek sampleModel
        // Pastikan metode getter di SampleModel.java sudah ada (misal: getKETERANGAN(), getJAM_KEGIATAN(), dll.)
        kegiatanInfo.setText(String.valueOf(sampleModel.getKETERANGAN()));
        waktuInfo.setText(String.valueOf(sampleModel.getJAM_KEGIATAN()));

        containerPlotData.addView(rowView, 0);
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveTemporaryData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadTemporaryData();
    }

    void addPlotForm() {
        //Form Tidak boleh kosong

        if (mAktivitasPost.getText().toString().equals("")) {
            Toast.makeText(this, "Aktivitas Post tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mLokasi.getText().toString().equals("")) {
            Toast.makeText(this, "Lokasi tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mHari.getText().toString().equals("")) {
            Toast.makeText(this, "Tanggal Mutasi tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (mShift.getText().toString().equals("")) {
            Toast.makeText(this, "Shift tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if(mNama1.getText().toString().equals("")){
            Toast.makeText(this, "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if(mIndeks1.getText().toString().equals("")){
            Toast.makeText(this, "Nomor Indeks tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if(mJabatan1.getText().toString().equals("")){
            Toast.makeText(this, "Jabatan tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if(mKet1.getText().toString().equals("")){
            Toast.makeText(this, "Keterangan tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if(mPukul.getText().toString().equals("")){
            Toast.makeText(this, "Waktu tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if(mKeterangan.getText().toString().equals("")){
            Toast.makeText(this, "Kegiatan tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else {
            sampleModel = new SampleModel();
            boolean plotExist = false;

            // Cek apakah Plot sudah ada
            for (PlotModel existingPlot : dataPlot) {
                if (existingPlot.getPlot().equals(mPlot.getText().toString())) {
                    plotExist = true;
                    dataSample = existingPlot.getSAMPLE();
                    this.addSampleForm();
                    break;
                }
            }

            // Jika belum ada, tambahkan Plot baru
            if (!plotExist) {
                index = 1;
                dataSample = new ArrayList<>();
                plotModel = new PlotModel();
                this.addSampleForm();
                plotModel.setPlot(mPlot.getText().toString());
                plotModel.setSAMPLE(dataSample);

                dataPlot.add(plotModel);
            }

            plot = mPlot.getText().toString();
        }
    }

    void addSampleForm(){
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rowView = inflater.inflate(R.layout.data_mutasi_sample_item, null);

        final TextView kegiatanInfo = rowView.findViewById(R.id.item_kegiatan_info);
        final TextView waktuInfo = rowView.findViewById(R.id.item_waktu_info);

        kegiatanInfo.setText(mKeterangan.getText().toString());
        waktuInfo.setText(mPukul.getText().toString());

//        List<Integer> existingSamples = new ArrayList<>();
//        for (SampleModel sample : dataSample) {
//            if (sample.get)
//        }

        // Cari nomor urutan yang hilang

//        Collections.sort(existingSample);
//        int nextSampleNumber = 1; // Mulai dari 1
//        for (int i = 0; i < existingSample.size(); i++) {
//            if (existingSample.get(i) != nextSampleNumber) {
//                break;
//            }
//            nextSampleNumber++;
//        }

        sampleModel.setAKTIVITAS_POST(mAktivitasPost.getText().toString());
        sampleModel.setLOKASI(mLokasi.getText().toString());
        sampleModel.setTANGGAL_MUTASI(mHari.getText().toString());
        sampleModel.setSHIFT(mShift.getText().toString());

        sampleModel.setNAMA1(mNama1.getText().toString());
        sampleModel.setINDEKS1(mIndeks1.getText().toString());
        sampleModel.setJABATAN1(mJabatan1.getText().toString());
        sampleModel.setKETERANGAN1(mKet1.getText().toString());

        sampleModel.setNAMA2(mNama2.getText().toString());
        sampleModel.setINDEKS2(mIndeks2.getText().toString());
        sampleModel.setJABATAN2(mJabatan2.getText().toString());
        sampleModel.setKETERANGAN2(mKet2.getText().toString());

        sampleModel.setNAMA3(mNama3.getText().toString());
        sampleModel.setINDEKS3(mIndeks3.getText().toString());
        sampleModel.setJABATAN3(mJabatan3.getText().toString());
        sampleModel.setKETERANGAN3(mKet3.getText().toString());
        sampleModel.setNAMA4(mNama4.getText().toString());
        sampleModel.setINDEKS4(mIndeks4.getText().toString());
        sampleModel.setJABATAN4(mJabatan4.getText().toString());
        sampleModel.setKETERANGAN4(mKet4.getText().toString());

        sampleModel.setNAMA5(mNama5.getText().toString());
        sampleModel.setINDEKS5(mIndeks5.getText().toString());
        sampleModel.setJABATAN5(mJabatan5.getText().toString());
        sampleModel.setKETERANGAN5(mKet5.getText().toString());

        sampleModel.setNAMA6(mNama6.getText().toString());
        sampleModel.setINDEKS6(mIndeks6.getText().toString());
        sampleModel.setJABATAN6(mJabatan6.getText().toString());
        sampleModel.setKETERANGAN6(mKet6.getText().toString());

        sampleModel.setKETERANGAN(mKeterangan.getText().toString());
        sampleModel.setJAM_KEGIATAN(mPukul.getText().toString());

        dataSample.add(sampleModel);

        containerPlotData.addView(rowView, 0);

        // Hapus saat Apply
        Toast.makeText(this, "Data Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
        mKeterangan.getText().clear();
        mPukul.getText().clear();



    }

    public void onDeleteSample(View v) {
//        TextView txtPlot = ((View) v.getParent()).findViewById(R.id.mPlot);
//        int plotToDelete = Integer.parseInt(txtPlot.getText().toString());
//
//        Log.d("Hapus", "Menghapus data dengan No Plot: " + plotToDelete);
//
//        // Hapus data dari dataPlot
//        boolean dataRemoved = false;
//        for (int i = 0; i < dataPlot.size(); i++) {
//            if (dataPlot.get(i).getPlot().equals) {
//
    }

    @Override
    public void onSubmit() {
        // Implementasi dari IFormPengamatanLembarMutasiView
        if(dataPlot.size() > 0){
            model.setAKTIVITAS_POST(mAktivitasPost.getText().toString());
            model.setLOKASI(mLokasi.getText().toString());
            model.setTANGGAL_MUTASI(mHari.getText().toString());
            model.setSHIFT(mShift.getText().toString());

            PlotModel firstPlot = dataPlot.get(0);
            SampleModel firstSample = firstPlot.getSAMPLE().get(0);

            model.setJAM_KEGIATAN(firstSample.getJAM_KEGIATAN());
            model.setKETERANGAN(firstSample.getKETERANGAN());

            model.setNAMA1(firstSample.getNAMA1());
            model.setINDEKS1(firstSample.getINDEKS1());
            model.setJABATAN1(firstSample.getJABATAN1());
            model.setKETERANGAN1(firstSample.getKETERANGAN1());

            model.setNAMA2(firstSample.getNAMA2());
            model.setINDEKS2(firstSample.getINDEKS2());
            model.setJABATAN2(firstSample.getJABATAN2());
            model.setKETERANGAN2(firstSample.getKETERANGAN2());

            model.setNAMA3(firstSample.getNAMA3());
            model.setINDEKS3(firstSample.getINDEKS3());
            model.setJABATAN3(firstSample.getJABATAN3());
            model.setKETERANGAN3(firstSample.getKETERANGAN3());

            model.setNAMA4(firstSample.getNAMA4());
            model.setINDEKS4(firstSample.getINDEKS4());
            model.setJABATAN4(firstSample.getJABATAN4());
            model.setKETERANGAN4(firstSample.getKETERANGAN4());

            model.setNAMA5(firstSample.getNAMA5());
            model.setINDEKS5(firstSample.getINDEKS5());
            model.setJABATAN5(firstSample.getJABATAN5());
            model.setKETERANGAN5(firstSample.getKETERANGAN5());

            model.setNAMA6(firstSample.getNAMA6());
            model.setINDEKS6(firstSample.getINDEKS6());
            model.setJABATAN6(firstSample.getJABATAN6());
            model.setKETERANGAN6(firstSample.getKETERANGAN6());

            model.setDATA(null);
//            Log.d("Modelnyanih", new Gson().toJson(model));
            presenter.createPengamatan(model);
            Log.d("FINAL_REQUEST", new Gson().toJson(model));

        } else SweetDialogs.commonError(this, "Harap Apply Data Terlebih Dahulu!", false);
    }

    @Override
    public void onCreateSuccess(String rm) {
        // Implementasi dari IFormPengamatanLembarMutasiView

        SimpleDateFormat sdf = new SimpleDateFormat("HH-mm-ss", Locale.getDefault());
        String currentTime = sdf.format(new Date()); // Mendapatkan waktu saat ini

        // Tambahkan waktu ke dalam pesan
        String message = "Mutasi " + ", Lokasi: " + mLokasi.getText().toString() + "\nTanggal: " + generateTglSekarang() + "\nWaktu: " + currentTime + "\n: " + getString(R.string.versi_apps);

        SweetDialogs.commonSuccessWithIntent(this, message, string -> {
            goToListPengamatan(this);
        });

        TemporaryFormStorage.clearDraft(this, DRAFT_KEY);
        clearForm();

    }

    @Override
    public void onCreateFailed(String eror) {
        // Implementasi dari IFormPengamatanLembarMutasiView
        SweetDialogs.commonError(this, eror, true);
    }

    @Override
    public void showLoadingIndicator() {
//         Implementasi dari IFormPengamatanLembarMutasiView
//        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
//        sweetAlertDialog.setTitleText("Loading . . .");
//        sweetAlertDialog.show();

        if (isFinishing() || isDestroyed()) return;

        if (sweetAlertDialog == null) {
            sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
            sweetAlertDialog.setTitleText("Loading . . .");
            sweetAlertDialog.setCancelable(false);
        }

        if (!sweetAlertDialog.isShowing()) {
            sweetAlertDialog.show();
        }
    }

    @Override
    public void hideLoadingIndicator() {
//         Implementasi dari IFormPengamatanLembarMutasiView
//        sweetAlertDialog.dismiss();
        if (sweetAlertDialog != null && sweetAlertDialog.isShowing()) {
            sweetAlertDialog.dismiss();
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if (sweetAlertDialog != null) {
            sweetAlertDialog.dismiss();
            sweetAlertDialog = null;
        }
    }

    @Override
    public void onNetworkError(String cause, String data) {
        // Implementasi dari IFormPengamatanLembarMutasiView
        Log.e("errornya", cause);
        SQLiteHelper dbHelper = new SQLiteHelper(this);

        // GUNAKAN ID YANG TIDAK NULL, CONTOH: AKTIVITAS_POST
        String offlineId = model.getNO_PLOT();
        if (offlineId == null || offlineId.isEmpty()) {
            // Sebagai cadangan jika aktivitas post kosong
            offlineId = model.getLOKASI() + "_" + System.currentTimeMillis();
        }

        dbHelper.saveChopperData(data, offlineId);

//        dbHelper.saveChopperData(data, model.getNO_PLOT()); // Assuming 'data' is a JSON string

        Log.d("Saved data", "Data saved to SQLite: " + data);
        Log.d("Saved data", "Data saved to SQLite: " + model.getNO_PLOT());

        SweetDialogs.commonWarningWithIntent(this, "Anda Tidak ada Koneksi Internet", App.getApplication().getString(R.string.notif_offline_mode), string -> startActivity(new Intent(this, e_1_list_lahan.class)));

        TemporaryFormStorage.clearDraft(this, DRAFT_KEY);
        clearForm();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mSubmit:
                this.onSubmit();
                break;
            case R.id.mAddSample:
                this.addPlotForm();
                break;
        }
    }


//    private static final String[] mAktivitasPost2 = new String[]{ "Post", "Patroli"};

}
