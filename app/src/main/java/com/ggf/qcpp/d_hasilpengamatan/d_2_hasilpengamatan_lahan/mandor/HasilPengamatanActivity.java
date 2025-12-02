package com.ggf.qcpp.d_hasilpengamatan.d_2_hasilpengamatan_lahan.mandor;

import static com.ggf.qcpp.utils.Utils.totalnilaiChopper;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.service.chooser.ChooserAction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.ggf.qcpp.App;
import com.ggf.qcpp.Prefs;
import com.ggf.qcpp.R;
import com.ggf.qcpp.b_account.model.LoginResponse;
import com.ggf.qcpp.d_hasilpengamatan.d_2_hasilpengamatan_lahan.mandor.model.HasilPengamatanModel;
import com.ggf.qcpp.e_formpengamatan.chopper.model.ChopperModel;
import com.ggf.qcpp.i_notify.i_notify_1;
import com.ggf.qcpp.i_notify.model.PengamatanModel;
import com.ggf.qcpp.ui.SweetDialogs;
import com.ggf.qcpp.utils.GsonHelper;
import com.google.gson.Gson;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HasilPengamatanActivity extends AppCompatActivity implements IHasilPengamatanMandorView, ChopperAdapter.OnItemSelected {
    View dialogView;

    AlertDialog.Builder dialog;
    LayoutInflater inflater;

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.text1)
    TextView text1;

    @BindView(R.id.mTotalSample)
    TextView mTotalSample;

    @BindView(R.id.mAcc)
    Button mAcc;

    @BindView(R.id.mLihatHasil)
    Button mLihatHasil;

    @BindView(R.id.layoutApproval)
    LinearLayout layoutApproval;

    @BindView(R.id.mWa)
    LinearLayout mWa;


    HasilPengamatanMandorPresenter presenter;
    SweetAlertDialog sweetAlertDialog;
    PengamatanModel model;
    String no_spk, kategori, idPengamatan, login, lokasi;

    Float sumTanamanHancur, sumBonggolTercacah, sumAplikasiRapat;
    LoginResponse mProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_2_chopper_mandor);
        ButterKnife.bind(this);
        mProfile = (LoginResponse) GsonHelper.parseGson(
                App.getPref().getString(Prefs.PREF_STORE_PROFILE, ""),
                new LoginResponse()
        );
        model = (PengamatanModel) getIntent().getSerializableExtra("model");
        String fromClassname = getIntent().getStringExtra("fromClassname");
        Log.d("classname", fromClassname);
        if (fromClassname != null & fromClassname.equals("d_1_hasilpengamatan_1")) {
            layoutApproval.setVisibility(View.GONE);

        }
        if (mProfile.getData().getUser().getId_role().equals(App.getApplication().getString(R.string.role_mandor))) {
            if (model.getVerify_mandor() == 1) {
                mAcc.setVisibility(View.GONE);
            } else {
                mAcc.setVisibility(View.VISIBLE);

            }
        } else if (mProfile.getData().getUser().getId_role().equals(App.getApplication().getString(R.string.role_kasie))) {
            if (model.getVerify_mandor() == 0) {
                mAcc.setEnabled(false);
                mAcc.setBackgroundColor(Color.GRAY);
            } else {
                mAcc.setEnabled(true);
                if (model.getVerify_kasi() == 1) {
                    mAcc.setVisibility(View.GONE);
                } else {
                    mAcc.setVisibility(View.VISIBLE);

                }

            }
        } else if (mProfile.getData().getUser().getId_role().equals(App.getApplication().getString(R.string.role_kabag))) {
            if (model.getVerify_kasi() == 0) {
                mAcc.setEnabled(false);
                mAcc.setBackgroundColor(Color.GRAY);
            } else {
                mAcc.setEnabled(true);
                if (model.getVerify_kabag() == 1) {
                    mAcc.setVisibility(View.GONE);
                } else {
                    mAcc.setVisibility(View.VISIBLE);

                }
            }
        }
//        Log.d("classname" ,fromClassname);
        no_spk = model.getNO_SPK();
        kategori = model.getKATEGORI();
        idPengamatan = model.getGUID();
        lokasi = model.getLOKASI();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.clearFocus();
        presenter = new HasilPengamatanMandorPresenter(this);
        presenter.getPengamatan(no_spk, kategori, lokasi);
        text1.setText("Pengamatan " + kategori);
        mAcc.setOnClickListener(view -> this.onSubmit());
//        LinearLayout waButton = findViewById(R.id.wa_button);

        mWa.setOnClickListener(v -> {
            String phoneNumber = App.getApplication().getString(R.string.contact_wa);
            String uniqueId = String.valueOf(System.currentTimeMillis());
            String message = "Nomor Tiket#" + uniqueId + "\n-------------------------------------------------\nNama : \nPG : \nLokasi : \nTanggal Pengamatan : \nPengamatan : \n------------------------------------------------- \nPermintaan : ";
//            String message = "Halo, saya ingin bertanya...";
            String url = "https://wa.me/" + phoneNumber + "?text=" + Uri.encode(message);

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });


    }

    public void onLihatHasil() {

    }



    @Override
    public void onSubmit() {
        if (mProfile.getData().getUser().getId_role().equals(App.getApplication().getString(R.string.role_kasie))) {

            model.setVerify_kasi(1);
        } else if (mProfile.getData().getUser().getId_role().equals(App.getApplication().getString(R.string.role_kabag))) {

            model.setVerify_kabag(1);
        } else if (mProfile.getData().getUser().getId_role().equals(App.getApplication().getString(R.string.role_mandor))) {

            model.setVerify_mandor(1);
        }
//        model.setVerify_mandor(1);
        Log.d("dataModelnya", new Gson().toJson(model));
        presenter.onVerified(model);

    }

    @Override
    public void onVerified(String guid, ChopperModel model) {
//        model.setVERIFIED(model.getVERIFIED().getVERIFIED_MANDOR().setNama("ya"));
//        if(username.equals("mandor")) {
//            model.getVERIFIED().getVERIFIED_MANDOR().setNama(username);
//            model.getVERIFIED().getVERIFIED_MANDOR().setStatus(true);
//        }
//        if(username.equals("kasie")) {
//            model.getVERIFIED().getVERIFIED_KASI().setNama(username);
//            model.getVERIFIED().getVERIFIED_KASI().setStatus(true);
//        }
//        if(username.equals("kabag")) {
//            model.getVERIFIED().getVERIFIED_KABAG().setNama(username);
//            model.getVERIFIED().getVERIFIED_KABAG().setStatus(true);
//        }
        Log.d("onVerifiednya", new Gson().toJson(model));
//        Toast.makeText(this, "ver", Toast.LENGTH_SHORT).show();
//        presenter.onVerified(guid, model);
    }

    @Override
    public void onVerifiedSuccess(PengamatanModel model) {
        Log.d("kenapamodel", new Gson().toJson(model));
        SweetDialogs.commonSuccessWithIntent(this, "Berhasil Memuat Permintaan !", view -> this.refresh(model));
    }

    void refresh(PengamatanModel model) {
        Intent i = new Intent(this, i_notify_1.class);
        i.putExtra("fromClassname", "notifikasi");
        startActivity(i);
    }


    @Override
    public void onDataReady(List<HasilPengamatanModel> data) {
        mTotalSample.setText(String.valueOf(data.size()));
        Log.d("hasilamat", new Gson().toJson(data));

        if (!data.isEmpty()) {
            RecyclerView.Adapter adapter = null;

            if (kategori.equals("chopper")) {
                adapter = new ChopperAdapter(data, this, this);
                mLihatHasil.setOnClickListener(view -> formulaChopper(data));
                mLihatHasil.setVisibility(View.GONE);
            } else if (kategori.equals("bajak")) {
                adapter = new BajakAdapter(data, this);
                mLihatHasil.setOnClickListener(view -> formulaBajak(data));
                mLihatHasil.setVisibility(View.GONE);
            } else if (kategori.equals("subsoiler")) {
                adapter = new SubsoilerAdapter(data, this);
                mLihatHasil.setOnClickListener(view -> formulaSubsoiler(data));
                mLihatHasil.setVisibility(View.GONE);
            } else if (kategori.equals("finishing")) {
                adapter = new FinishingAdapter(data, this);
//                mLihatHasil.setOnClickListener(view -> formulaFinishing(data));
                mLihatHasil.setVisibility(View.GONE);
//            } else if (kategori.equals("ridger")) {
//                adapter = new RidgerAdapter(data, this);
//                mLihatHasil.setOnClickListener(view -> formulaRidger(data));
            } else if (kategori.equals("petik_bibit")) {
                adapter = new PetikBibitAdapter(data, this);
                mLihatHasil.setOnClickListener(view -> formulaPetikBibit(data));
                mLihatHasil.setVisibility(View.GONE);
            } else if (kategori.equals("drop_bibit")) {
                adapter = new DropBibitAdapter(data, this);

                mLihatHasil.setOnClickListener(view -> formulaDropBibit(data));
                mLihatHasil.setVisibility(View.GONE);
            } else if (kategori.equals("pool_dipping")) {
                adapter = new PoolDippingAdapter(data, this);
                mLihatHasil.setOnClickListener(view -> formulaPoolDipping(data));
                mLihatHasil.setVisibility(View.GONE);
//            } else if (kategori.equals("ph_tanah")) {
//                adapter = new phTanahAdapter(data, this);
//                mLihatHasil.setOnClickListener(view -> formulaPhTanah(data));
            } else if (kategori.equals("kualitas_tanam")) {
                adapter = new KualitasTanamAdapter(data, this);
                mLihatHasil.setOnClickListener(view -> formulaTanam(data));
                mLihatHasil.setVisibility(View.GONE);
//            } else if (kategori.equals("kebersihan_bonggol")) {
//                adapter = new KebersihanBonggolAdapter(data, this);
//                mLihatHasil.setOnClickListener(view -> formulaKebersihanBonggol(data));
//            } else if (kategori.equals("kebersihan_transport")) {
//                adapter = new KebersihanTransportAdapter(data, this);
//                mLihatHasil.setOnClickListener(view -> formulaTransport(data));
            } else if (kategori.equals("jumlah_baris")) {
                adapter = new JumlahBarisAdapter(data, this);
                mLihatHasil.setOnClickListener(view -> formulaJumlahBaris(data));
                mLihatHasil.setVisibility(View.GONE);
//            } else if (kategori.equals("sesetbonggol")) {
//                adapter = new SesetBonggolAdapter(data, this);
//                mLihatHasil.setOnClickListener(view -> formulaSesetBonggol(data));
            } else if (kategori.equals("bonggol_tidak_terseset")) {
                adapter = new BonggolTidakTersesetAdapter(data, this);
                mLihatHasil.setOnClickListener(view -> formulaTidakTerSesetBonggol(data));
                mLihatHasil.setVisibility(View.GONE);
//            } else if (kategori.equals("singkong_stek_panjang")) {
//                adapter = new SingkongStekPanjangAdapter(data, this);
//                mLihatHasil.setOnClickListener(view -> formulaStekPanjang(data));
//            } else if (kategori.equals("singkong_stek_pendek")) {
//                adapter = new BonggolTidakTersesetAdapter(data, this);
//                mLihatHasil.setOnClickListener(view -> formulaTidakTerSesetBonggol(data));
            } else if (kategori.equals("adukan")) {
                adapter = new AdukanAdapter(data, this);
                mLihatHasil.setOnClickListener(view -> formulaAdukan(data));
                mLihatHasil.setVisibility(View.GONE);
            } else if (kategori.equals("panen")) {
                adapter = new PanenAdapter(data, this);
                mLihatHasil.setOnClickListener(view -> formulaPanen(data));
                mLihatHasil.setVisibility(View.GONE);
            } else if (kategori.equals("mixer")) {
                adapter = new MixerAdapter(data, this);
                mLihatHasil.setOnClickListener(view -> formulaMixer(data));
                mLihatHasil.setVisibility(View.GONE);
            } else if (kategori.equals("potensi_bibit_crown")) {
                adapter = new PotensiCrownAdapter(data, this);
//                mLihatHasil.setOnClickListener(view -> formulaMixer(data));
                mLihatHasil.setVisibility(View.GONE);
            }



            mRecyclerView.setAdapter(adapter);
            mRecyclerView.setVisibility(View.VISIBLE);

        } else {
            // Buat pesan berdasarkan kategori
            String pesanKosong = "Tidak ada sample";
            switch (kategori) {
                case "chopper":
                    pesanKosong = "Tidak ada sample untuk kategori Chopper";
                    break;
                case "bajak":
                    pesanKosong = "Tidak ada sample untuk kategori Bajak";
                    break;
                case "finishing":
                    pesanKosong = "Tidak ada sample untuk kategori Finishing";
                    break;
                case "ridger":
                    pesanKosong = "Tidak ada sample untuk kategori Ridger";
                    break;
                case "petik_bibit":
                    pesanKosong = "Tidak ada sample untuk kategori Petik Bibit";
                    break;
                case "drop_bibit":
                    pesanKosong = "Tidak ada sample untuk kategori Drop Bibit";
                    break;
                case "pool_dipping":
                    pesanKosong = "Tidak ada sample untuk kategori Pool Dipping";
                    break;
                case "ph_tanah":
                    pesanKosong = "Tidak ada sample untuk kategori pH Tanah";
                    break;
                case "kualitas_tanam":
                    pesanKosong = "Tidak ada sample untuk kategori Kualitas Tanam";
                    break;
                case "kebersihan_bonggol":
                    pesanKosong = "Tidak ada sample untuk kategori Kebersihan Bonggol";
                    break;
                case "kebersihan_transport":
                    pesanKosong = "Tidak ada sample untuk kategori Kebersihan Transport";
                    break;
                case "jumlah_baris":
                    pesanKosong = "Tidak ada sample untuk kategori Jumlah Baris";
                    break;
                case "sesetbonggol":
                    pesanKosong = "Tidak ada sample untuk kategori Seset Bonggol";
                    break;
                case "bonggol_tidak_terseset":
                case "singkong_stek_pendek":
                    pesanKosong = "Tidak ada sample untuk kategori Bonggol Tidak Terseset";
                    break;
                case "singkong_stek_panjang":
                    pesanKosong = "Tidak ada sample untuk kategori Singkong Stek Panjang";
                    break;
                case "potensi_bibit_crown":
                    pesanKosong = "Tidak ada sample untuk kategori Potensi Crown";
                    break;
            }

            // Tampilkan pesan
            Toast.makeText(this, pesanKosong, Toast.LENGTH_SHORT).show();

            // Sembunyikan tampilan data
            mRecyclerView.setVisibility(View.GONE);
            mLihatHasil.setVisibility(View.GONE);
        }
    }

    public void formulaMixer(List<HasilPengamatanModel> data) {
        // Deklarasi daftar
        List<Float> normal_buah_tertinggal_besar = new ArrayList<>();

        for (int i = 0; i < data.size(); ++i) {


        }


//        Float sum_tersier_buah_tertinggal_kecil = (float) tersier_buah_tertinggal_kecil.stream()
//                .mapToDouble(Float::doubleValue)
//                .sum();

//        Double beratBuahTeringgal =


        // Dialog setup
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        dialog = new AlertDialog.Builder(HasilPengamatanActivity.this);
        dialogView = getLayoutInflater().inflate(R.layout.dialog_hasil_pengamatan_mixer, null);
        dialog.setView(dialogView).setCancelable(false).setIcon(R.mipmap.ic_launcher).setTitle("Hasil Pengamatan");

        final TextView mPanen = dialogView.findViewById(R.id.mJalurNormal);
        final TextView mCrown = dialogView.findViewById(R.id.mSalSekunder);


        // Tampilkan hasil
        mPanen.setText(df.format("pencapaian_panen"));
        mCrown.setText(df.format(1));

//        if (totalPencapaian >= 90)
//            mGrade.setText("Masuk Standart");
//        else {
//            mGrade.setText("Tidak Masuk Standart");
//            mGrade.setTextColor(Color.RED);
//        }

        dialog.setPositiveButton("OK", (dialog, which) -> {
            // Tambahkan logika aksi OK jika diperlukan
        });
        dialog.setNegativeButton("Kembali", (dialog, which) -> {
            // Tambahkan logika aksi Kembali jika diperlukan
        });

        dialog.show();
    }


    public void formulaAdukan(List<HasilPengamatanModel> data) {
        // Deklarasi daftar
//        List<Float> normal_buah_tertinggal_besar = new ArrayList<>();

        for (int i = 0; i < data.size(); ++i) {


        }


//        Float sum_tersier_buah_tertinggal_kecil = (float) tersier_buah_tertinggal_kecil.stream()
//                .mapToDouble(Float::doubleValue)
//                .sum();

//        Double beratBuahTeringgal =


        // Dialog setup
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        dialog = new AlertDialog.Builder(HasilPengamatanActivity.this);
        dialogView = getLayoutInflater().inflate(R.layout.dialog_hasil_pengamatan_adukan, null);
        dialog.setView(dialogView).setCancelable(false).setIcon(R.mipmap.ic_launcher).setTitle("Hasil Pengamatan");

        final TextView mPanen = dialogView.findViewById(R.id.mJalurNormal);
        final TextView mCrown = dialogView.findViewById(R.id.mSalSekunder);


        // Tampilkan hasil
        mPanen.setText(df.format("pencapaian_panen"));
        mCrown.setText(df.format(1));

//        if (totalPencapaian >= 90)
//            mGrade.setText("Masuk Standart");
//        else {
//            mGrade.setText("Tidak Masuk Standart");
//            mGrade.setTextColor(Color.RED);
//        }

        dialog.setPositiveButton("OK", (dialog, which) -> {
            // Tambahkan logika aksi OK jika diperlukan
        });
        dialog.setNegativeButton("Kembali", (dialog, which) -> {
            // Tambahkan logika aksi Kembali jika diperlukan
        });

        dialog.show();
    }

    public void formulaPanen(List<HasilPengamatanModel> data) {
        // Deklarasi daftar
        List<Float> normal_buah_tertinggal_besar = new ArrayList<>();
        List<Float> normal_buah_tertinggal_sedang = new ArrayList<>();
        List<Float> normal_buah_tertinggal_kecil = new ArrayList<>();
        List<Float> sekunder_buah_tertinggal_besar = new ArrayList<>();
        List<Float> sekunder_buah_tertinggal_sedang = new ArrayList<>();
        List<Float> sekunder_buah_tertinggal_kecil = new ArrayList<>();
        List<Float> tersier_buah_tertinggal_besar = new ArrayList<>();
        List<Float> tersier_buah_tertinggal_sedang = new ArrayList<>();
        List<Float> tersier_buah_tertinggal_kecil = new ArrayList<>();
        List<Float> panjang_pengamatan = new ArrayList<>();
        List<Float> jumlah_baris = new ArrayList<>();
        List<Float> jumlah_sal_sekunder = new ArrayList<>();
        List<Float> jumlah_sal_tersier = new ArrayList<>();
        List<Float> jumlah_titik_pengamatan = new ArrayList<>();
        List<Float> jumlah_titik_diamati = new ArrayList<>();

        for (int i = 0; i < data.size(); ++i) {
            normal_buah_tertinggal_besar.add(Float.parseFloat(String.valueOf(data.get(i).getNormal_buah_tertinggal_besar())));
            normal_buah_tertinggal_sedang.add(Float.parseFloat(String.valueOf(data.get(i).getNormal_buah_tertinggal_sedang())));
            normal_buah_tertinggal_kecil.add(Float.parseFloat(String.valueOf(data.get(i).getNormal_buah_tertinggal_kecil())));
            sekunder_buah_tertinggal_besar.add(Float.parseFloat(String.valueOf(data.get(i).getSekunder_buah_tertinggal_besar())));
            sekunder_buah_tertinggal_sedang.add(Float.parseFloat(String.valueOf(data.get(i).getSekunder_buah_tertinggal_sedang())));
            sekunder_buah_tertinggal_kecil.add(Float.parseFloat(String.valueOf(data.get(i).getSekunder_buah_tertinggal_kecil())));
            tersier_buah_tertinggal_besar.add(Float.parseFloat(String.valueOf(data.get(i).getTersier_buah_tertinggal_besar())));
            tersier_buah_tertinggal_sedang.add(Float.parseFloat(String.valueOf(data.get(i).getTersier_buah_tertinggal_sedang())));
            tersier_buah_tertinggal_kecil.add(Float.parseFloat(String.valueOf(data.get(i).getTersier_buah_tertinggal_kecil())));
            jumlah_baris.add(Float.parseFloat(String.valueOf(data.get(i).getJumlah_baris())));
            panjang_pengamatan.add(Float.parseFloat(String.valueOf(data.get(i).getPanjang_pengamatan())));
            jumlah_sal_sekunder.add(Float.parseFloat(String.valueOf(data.get(i).getJumlah_sal_sekunder())));
            jumlah_sal_tersier.add(Float.parseFloat(String.valueOf(data.get(i).getJumlah_sal_tersier())));
            jumlah_titik_pengamatan.add(Float.parseFloat(String.valueOf(data.get(i).getJumlah_titik_pengamatan())));
            jumlah_titik_diamati.add(Float.parseFloat(String.valueOf(data.get(i).getJumlah_titik_diamati())));

        }

        Float sum_jumlah_titik_diamati = (float) jumlah_titik_diamati.stream()
                .mapToDouble(Float::doubleValue)
                .sum();

        Float sum_jumlah_titik_pengamatan = (float) jumlah_titik_pengamatan.stream()
                .mapToDouble(Float::doubleValue)
                .sum();

        Float sum_jumlah_sal_tersier = (float) jumlah_sal_tersier.stream()
                .mapToDouble(Float::doubleValue)
                .sum();

        Float sum_jumlah_sal_sekunder = (float) jumlah_sal_sekunder.stream()
                .mapToDouble(Float::doubleValue)
                .sum();

        Float sum_jumlah_baris = (float) jumlah_baris.stream()
                .mapToDouble(Float::doubleValue)
                .sum();
        Float sum_panjang_pengamatan = (float) panjang_pengamatan.stream()
                .mapToDouble(Float::doubleValue)
                .sum();

        Float sum_normal_buah_tertinggal_besar = (float) normal_buah_tertinggal_besar.stream()
                .mapToDouble(Float::doubleValue)
                .sum();

        Float sum_normal_buah_tertinggal_sedang = (float) normal_buah_tertinggal_sedang.stream()
                .mapToDouble(Float::doubleValue)
                .sum();

        Float sum_normal_buah_tertinggal_kecil = (float) normal_buah_tertinggal_kecil.stream()
                .mapToDouble(Float::doubleValue)
                .sum();

        Float sum_sekunder_buah_tertinggal_besar = (float) sekunder_buah_tertinggal_besar.stream()
                .mapToDouble(Float::doubleValue)
                .sum();

        Float sum_sekunder_buah_tertinggal_sedang = (float) sekunder_buah_tertinggal_sedang.stream()
                .mapToDouble(Float::doubleValue)
                .sum();

        Float sum_sekunder_buah_tertinggal_kecil = (float) sekunder_buah_tertinggal_kecil.stream()
                .mapToDouble(Float::doubleValue)
                .sum();

        Float sum_tersier_buah_tertinggal_besar = (float) tersier_buah_tertinggal_besar.stream()
                .mapToDouble(Float::doubleValue)
                .sum();

        Float sum_tersier_buah_tertinggal_sedang = (float) tersier_buah_tertinggal_sedang.stream()
                .mapToDouble(Float::doubleValue)
                .sum();

        Float sum_tersier_buah_tertinggal_kecil = (float) tersier_buah_tertinggal_kecil.stream()
                .mapToDouble(Float::doubleValue)
                .sum();

//        Double beratBuahTeringgal =

        Double avg1 = ((((sum_normal_buah_tertinggal_besar + sum_sekunder_buah_tertinggal_besar + sum_tersier_buah_tertinggal_besar) * 1.36
                + (sum_normal_buah_tertinggal_sedang + sum_sekunder_buah_tertinggal_sedang + sum_tersier_buah_tertinggal_sedang) * 0.89
                + (sum_normal_buah_tertinggal_kecil + sum_sekunder_buah_tertinggal_kecil + sum_tersier_buah_tertinggal_kecil) * 0.46) * (277.78 * sum_jumlah_baris)) / (sum_panjang_pengamatan * 2.0)) * Float.parseFloat(data.get(0).getLUAS_PLOT());

        Double avg2 = ((((sum_normal_buah_tertinggal_besar + sum_sekunder_buah_tertinggal_besar + sum_tersier_buah_tertinggal_besar) * 1.36
                + (sum_normal_buah_tertinggal_sedang + sum_sekunder_buah_tertinggal_sedang + sum_tersier_buah_tertinggal_sedang) * 0.89
                + (sum_normal_buah_tertinggal_kecil + sum_sekunder_buah_tertinggal_kecil + sum_tersier_buah_tertinggal_kecil) * 0.46) * (Float.parseFloat(data.get(0).getLUAS_PLOT()) / data.get(0).getLebar_plot()) * sum_jumlah_sal_sekunder) / (sum_jumlah_titik_pengamatan * 10.0));

        Double avg3 = ((((sum_normal_buah_tertinggal_besar + sum_sekunder_buah_tertinggal_besar + sum_tersier_buah_tertinggal_besar) * 1.36
                + (sum_normal_buah_tertinggal_sedang + sum_sekunder_buah_tertinggal_sedang + sum_tersier_buah_tertinggal_sedang) * 0.89
                + (sum_normal_buah_tertinggal_kecil + sum_sekunder_buah_tertinggal_kecil + sum_tersier_buah_tertinggal_kecil) * 0.46) * (sum_jumlah_sal_tersier * data.get(0).getLebar_plot())) / (sum_jumlah_titik_diamati * 10.0));
        Double pencapaian_panen = avg1 + avg2 + avg3;
        // Dialog setup
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        dialog = new AlertDialog.Builder(HasilPengamatanActivity.this);
        dialogView = getLayoutInflater().inflate(R.layout.dialog_hasil_pengamatan_panen, null);
        dialog.setView(dialogView).setCancelable(false).setIcon(R.mipmap.ic_launcher).setTitle("Hasil Pengamatan");

        final TextView mPanen = dialogView.findViewById(R.id.mJalurNormal);
        final TextView mCrown = dialogView.findViewById(R.id.mSalSekunder);


        // Tampilkan hasil
        mPanen.setText(df.format(pencapaian_panen));
        mCrown.setText(df.format(1));

//        if (totalPencapaian >= 90)
//            mGrade.setText("Masuk Standart");
//        else {
//            mGrade.setText("Tidak Masuk Standart");
//            mGrade.setTextColor(Color.RED);
//        }

        dialog.setPositiveButton("OK", (dialog, which) -> {
            // Tambahkan logika aksi OK jika diperlukan
        });
        dialog.setNegativeButton("Kembali", (dialog, which) -> {
            // Tambahkan logika aksi Kembali jika diperlukan
        });

        dialog.show();
    }

    public void formulaTanam(List<HasilPengamatanModel> data) {
        // Deklarasi daftar
        List<HasilPengamatanModel> listStatusJTDB = new ArrayList<>();
        List<HasilPengamatanModel> listStatusJTAB = new ArrayList<>();
        List<Float> listPanjangJTAB = new ArrayList<>();
        List<Float> listPanjangJTDB = new ArrayList<>();
        List<Integer> listTotalTanamJTDB = new ArrayList<>();
        List<Integer> listTotalTanamJTAB = new ArrayList<>();
        List<Float> listKedalamanOnStandart = new ArrayList<>();
        List<Float> listAllKedalaman = new ArrayList<>();
        List<Float> listDataSize = new ArrayList<>();

        // Dialog setup
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        dialog = new AlertDialog.Builder(HasilPengamatanActivity.this);
        dialogView = getLayoutInflater().inflate(R.layout.dialog_hasil_pengamatan_tanam, null);
        dialog.setView(dialogView).setCancelable(false).setIcon(R.mipmap.ic_launcher).setTitle("Hasil Pengamatan");

        final TextView mPencapaianJTDB = dialogView.findViewById(R.id.mPencapaianJTDB);
        final TextView mAvgJTDB = dialogView.findViewById(R.id.mAvgJTDB);
        final TextView mPencapaianJTAB = dialogView.findViewById(R.id.mPencapaianJTAB);
        final TextView mAvgJTAB = dialogView.findViewById(R.id.mAvgJTAB);
        final TextView mPencapaianKD = dialogView.findViewById(R.id.mPencapaianKD);
        final TextView mAvgKD = dialogView.findViewById(R.id.mAvgKD);
        final TextView mGrade = dialogView.findViewById(R.id.mGrade);
        final TextView mTotalPencapaian = dialogView.findViewById(R.id.mTotalPencapaian);

        for (HasilPengamatanModel item : data) {
            // Tambahkan data ke daftar
            listPanjangJTAB.add(item.getPanjang_jtab());
            listPanjangJTDB.add(item.getPanjang_jtdb());
            listTotalTanamJTAB.add(item.getTot_tanamjtab());
            listTotalTanamJTDB.add(item.getTot_tanamjtdb());
            listAllKedalaman.add(item.getKedalaman_1());
            listAllKedalaman.add(item.getKedalaman_2());
            listAllKedalaman.add(item.getKedalaman_3());
            listAllKedalaman.add(item.getKedalaman_4());

            listDataSize.addAll(Arrays.asList(item.getKedalaman_1(), item.getKedalaman_2(), item.getKedalaman_3(), item.getKedalaman_4()));

            if (item.getStatus_jtdb().contains("On Stndar")) listStatusJTDB.add(item);
            if (item.getStatus_jtab().contains("On Stndar")) listStatusJTAB.add(item);

            // Cek kondisi musim dan kedalaman
            int minDepth = getMinimumDepth(item.getStd_musim(), item.getKelas_bibit());
            addValidDepths(item, minDepth, listKedalamanOnStandart);
        }

        listDataSize.removeIf(depth -> depth < 1);

        // Hitung statistik
        float sumPanjangJTAB = (float) listPanjangJTAB.stream().mapToDouble(Float::doubleValue).sum();
        float sumPanjangJTDB = (float) listPanjangJTDB.stream().mapToDouble(Float::doubleValue).sum();
        long sumTotalJTAB = listTotalTanamJTAB.stream().mapToLong(Integer::longValue).sum();
        long sumTotalJTDB = listTotalTanamJTDB.stream().mapToLong(Integer::longValue).sum();
        float sumTotalKedalaman = (float) listAllKedalaman.stream().mapToDouble(Float::doubleValue).sum();

        float avgJTDB = sumPanjangJTDB / (sumTotalJTDB - data.size());
        float avgJTAB = sumPanjangJTAB / (sumTotalJTAB - data.size());
        float pencapaianJTDB = (float) listStatusJTDB.size() / data.size() * 100;
        float pencapaianJTAB = (float) listStatusJTAB.size() / data.size() * 100;
        float kedalamanOnstandart = (float) listKedalamanOnStandart.size() / listDataSize.size() * 100;
        float avgKedalaman = sumTotalKedalaman / listDataSize.size();
        float totalPencapaian = (pencapaianJTDB * 0.4F) + (pencapaianJTAB * 0.2F) + (kedalamanOnstandart * 0.4F);

        // Tampilkan hasil
        mPencapaianJTDB.setText(df.format(pencapaianJTDB));
        mPencapaianJTAB.setText(df.format(pencapaianJTAB));
        mAvgJTDB.setText(df.format(avgJTDB));
        mAvgJTAB.setText(df.format(avgJTAB));
        mPencapaianKD.setText(df.format(kedalamanOnstandart));
        mAvgKD.setText(df.format(avgKedalaman));
        mTotalPencapaian.setText(df.format(totalPencapaian));
        if (totalPencapaian >= 90)
            mGrade.setText("Masuk Standart");
        else {
            mGrade.setText("Tidak Masuk Standart");
            mGrade.setTextColor(Color.RED);
        }

        dialog.setPositiveButton("OK", (dialog, which) -> {
            // Tambahkan logika aksi OK jika diperlukan
        });
        dialog.setNegativeButton("Kembali", (dialog, which) -> {
            // Tambahkan logika aksi Kembali jika diperlukan
        });

        dialog.show();
    }

    private int getMinimumDepth(String musim, String kelasBibit) {
        if ("Basah".equals(musim)) {
            switch (kelasBibit) {
                case "Kecil":
                    return 8;
                case "Sedang":
                    return 10;
                case "Besar":
                    return 12;
            }
        } else if ("Kering".equals(musim)) {
            switch (kelasBibit) {
                case "Kecil":
                    return 10;
                case "Sedang":
                    return 12;
                case "Besar":
                    return 14;
            }
        }
        return 0;
    }

    private void addValidDepths(HasilPengamatanModel item, int minDepth, List<Float> listKedalamanOnStandart) {
        if (item.getKedalaman_1() >= minDepth) listKedalamanOnStandart.add(item.getKedalaman_1());
        if (item.getKedalaman_2() >= minDepth) listKedalamanOnStandart.add(item.getKedalaman_2());
        if (item.getKedalaman_3() >= minDepth) listKedalamanOnStandart.add(item.getKedalaman_3());
        if (item.getKedalaman_4() >= minDepth) listKedalamanOnStandart.add(item.getKedalaman_4());
    }

    public void formulaPhTanah(List<HasilPengamatanModel> data) {
        // Group by plot


        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        dialog = new AlertDialog.Builder(HasilPengamatanActivity.this);
        dialogView = getLayoutInflater().inflate(R.layout.field_hasil_pengamatan_ph_tanah, null);
        dialog.setView(dialogView);
        dialog.setCancelable(false);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("Hasil Pengamatan");
//        final LinearLayout linearLayout = dialogView.findViewById(R.id.linearDataPlot);
        final TableLayout tableLayout = dialogView.findViewById(R.id.mTableLayout);

        final LinearLayout linearLayout = dialogView.findViewById(R.id.linearDataPlot);
// Map data by plot and sum the pH values
//

        Map<Integer, Map<String, Double>> plotToTotalPhMap = data.stream()
                .collect(Collectors.groupingBy(
                        d -> Integer.parseInt(d.getPlot()),
                        Collectors.reducing(
                                new HashMap<String, Double>(),
                                d -> {
                                    Map<String, Double> map = new HashMap<>();
                                    map.put("analisa_a1", (double) d.getANALISA_A1()); // Assuming getAnalisaA1() returns double
                                    map.put("analisa_a2", (double) d.getANALISA_A2()); // Assuming getAnalisaA2() returns double
                                    return map;
                                },
                                (m1, m2) -> {
                                    m1.merge("analisa_a1", m2.get("analisa_a1"), Double::sum);
                                    m1.merge("analisa_a2", m2.get("analisa_a2"), Double::sum);
                                    return m1;
                                }
                        )
                ));

// Reference to the LinearLayout where the table will be added
//        final LinearLayout linearLayout = dialogView.findViewById(R.id.linearDataPlot);

// Create a TableLayout to display the plot data
//        TableLayout tableLayout = new TableLayout(this);
        tableLayout.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));

// Add a header row to the TableLayout
        TableRow headerRow = new TableRow(this);
        headerRow.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));

// Define header cells: Plot, Total pH, and Status
        TextView headerPlot = new TextView(this);
        headerPlot.setText("Plot");
        headerPlot.setTypeface(null, Typeface.BOLD);
        headerPlot.setPadding(16, 8, 16, 8);
        headerRow.addView(headerPlot);

        TextView headerPh = new TextView(this);
        headerPh.setText("Total pH");
        headerPh.setTypeface(null, Typeface.BOLD);
        headerPh.setPadding(16, 8, 16, 8);
        headerRow.addView(headerPh);

        TextView headerStatus = new TextView(this);
        headerStatus.setText("Status");
        headerStatus.setTypeface(null, Typeface.BOLD);
        headerStatus.setPadding(16, 8, 16, 8);
        headerRow.addView(headerStatus);

// Add the header row to the TableLayout
        tableLayout.addView(headerRow);


        for (int i = 0; i < data.size(); ++i) {
            ArrayList<Float> listDataSize = new ArrayList<>();

            listDataSize.add(data.get(i).getANALISA_A1());
            listDataSize.add(data.get(i).getANALISA_A2());
            listDataSize.add(data.get(i).getANALISA_A3());
            listDataSize.add(data.get(i).getANALISA_A4());
            for (int x = 0; x < listDataSize.size(); ++x) {
                if (listDataSize.get(x) < 1) {
//                    listDataSize.remove(x);
                    listDataSize.removeIf(n -> n == 0.0);
                }
            }
            Log.d("datasize", new Gson().toJson(listDataSize));
            //            // Plot column
            float hasilpHPerlot = (data.get(i).getANALISA_A1() + data.get(i).getANALISA_A2() + data.get(i).getANALISA_A3() + data.get(i).getANALISA_A4()) / listDataSize.size();
            TableRow dataRow = new TableRow(this);
            TextView plotCell = new TextView(this);
            plotCell.setText(String.valueOf(data.get(i).getPlot()));
            plotCell.setPadding(16, 8, 16, 8);
            dataRow.addView(plotCell);

            // Total Analisa A1 column
            TextView totalAnalisaA1Cell = new TextView(this);
            totalAnalisaA1Cell.setGravity(View.TEXT_ALIGNMENT_CENTER);
            totalAnalisaA1Cell.setText(df.format(hasilpHPerlot));
            totalAnalisaA1Cell.setPadding(16, 8, 16, 8);

            dataRow.addView(totalAnalisaA1Cell);

            // Total Analisa A2 column
            TextView totalAnalisaA2Cell = new TextView(this);
            totalAnalisaA2Cell.setGravity(View.TEXT_ALIGNMENT_CENTER);
            String Status;
            if (hasilpHPerlot < 4.5F) {
                totalAnalisaA2Cell.setText("< STD");
            } else if (hasilpHPerlot > 5.5F) {
                totalAnalisaA2Cell.setText("> STD");
            } else
                totalAnalisaA2Cell.setText("STD");

            totalAnalisaA2Cell.setPadding(16, 8, 16, 8);
            dataRow.addView(totalAnalisaA2Cell);

            // Add data row to TableLayout
            tableLayout.addView(dataRow);

        }


        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Log.d("spiner", String.valueOf(spinnerPoktan.getSelectedItem()));
//                onCreatePoktan();
            }
        });

        dialog.setNegativeButton("Kembali", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
//                goToDashboard();
            }
        });

        dialog.show();
    }

    public void formulaKebersihanBonggol(List<HasilPengamatanModel> data) {
        ArrayList<Float> listBonggolSegarLebih = new ArrayList<>();
        ArrayList<Float> listBonggolSegarKurang = new ArrayList<>();
        ArrayList<Float> listEstimasi = new ArrayList<>();


        for (int i = 0; i < data.size(); ++i) {
            listBonggolSegarLebih.add(Float.parseFloat(String.valueOf(data.get(i).getBONGGOL_SEGAR_LEBIH_DARI())));
            listBonggolSegarKurang.add(Float.parseFloat(String.valueOf(data.get(i).getBONGGOL_SEGAR_KURANG_DARI())));
            listEstimasi.add(Float.parseFloat(String.valueOf(data.get(i).getESTIMASI())));

        }
        Float sumLebih = (float) listBonggolSegarLebih.stream()
                .mapToDouble(Float::doubleValue)
                .sum();
        Float sumKurang = (float) listBonggolSegarKurang.stream()
                .mapToDouble(Float::doubleValue)
                .sum();

        Float sumEstimasi = (float) listEstimasi.stream()
                .mapToDouble(Float::doubleValue)
                .sum();


        float kebersihanBonggol = (sumEstimasi - (sumLebih + sumKurang)) / sumEstimasi * 100;
        float bonggol = (sumLebih + sumKurang) / sumEstimasi * 100;

        Log.d("kebersihanBonggol", "" + kebersihanBonggol);
        Log.d("listBonggolSegarLebih", new Gson().toJson(listBonggolSegarLebih));
        Log.d("listBonggolSegarKurang", new Gson().toJson(listBonggolSegarKurang));
        Log.d("sumLebih", "" + sumLebih);
        Log.d("sumKurang", "" + sumKurang);
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        dialog = new AlertDialog.Builder(HasilPengamatanActivity.this);
        dialogView = getLayoutInflater().inflate(R.layout.dialog_hasil_pengamatan_bonggol, null);
        dialog.setView(dialogView);
        dialog.setCancelable(false);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("Hasil Pengamatan");

        final TextView mKebersihanBonggol = dialogView.findViewById(R.id.mKebersihanBonggol);
        final TextView mBonggol = dialogView.findViewById(R.id.mBonggol);
        final TextView mGrade = dialogView.findViewById(R.id.mGrade);


        mKebersihanBonggol.setText(df.format(kebersihanBonggol));
        mBonggol.setText(df.format(bonggol));

        if (kebersihanBonggol >= 95) {
            mGrade.setText("Masuk Standart");
            mGrade.setTextColor(App.getApplication().getColor(R.color.colorPrimary));
        } else {
            mGrade.setText("Tidak Masuk Standart");
            mGrade.setTextColor(Color.RED);
        }
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Log.d("spiner", String.valueOf(spinnerPoktan.getSelectedItem()));
//                onCreatePoktan();
            }
        });

        dialog.setNegativeButton("Kembali", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
//                goToDashboard();
            }
        });

        dialog.show();
    }

    public void formulaRidger(List<HasilPengamatanModel> data) {
        ArrayList<Float> listOnJAP = new ArrayList<>();
        ArrayList<Float> listJAP = new ArrayList<>();
        ArrayList<Float> listOnKedalaman = new ArrayList<>();
        ArrayList<Float> listKedalaman = new ArrayList<>();

        for (int i = 0; i < data.size(); ++i) {
            if (Float.parseFloat(data.get(i).getJARAK_ANTAR_POROS_GULUD()) >= 47 & Float.parseFloat(data.get(i).getJARAK_ANTAR_POROS_GULUD()) <= 53) {
                listOnJAP.add(Float.parseFloat(data.get(i).getJARAK_ANTAR_POROS_GULUD()));
            }
            if (Float.parseFloat(data.get(i).getKEDALAMAN_KUKU_RIDGER()) >= 15 & Float.parseFloat(data.get(i).getKEDALAMAN_KUKU_RIDGER()) <= 20) {
                listOnKedalaman.add(Float.parseFloat(data.get(i).getKEDALAMAN_KUKU_RIDGER()));
            }
            listJAP.add(Float.parseFloat(data.get(i).getJARAK_ANTAR_POROS_GULUD()));
            listKedalaman.add(Float.parseFloat(data.get(i).getKEDALAMAN_KUKU_RIDGER()));
        }
        Log.d("listOnJAP", new Gson().toJson(listOnJAP));
        Log.d("listKedalaman", new Gson().toJson(listKedalaman));


        Float sumJAP = (float) listJAP.stream()
                .mapToDouble(Float::doubleValue)
                .sum();

        Float sumKedalaman = (float) listKedalaman.stream()
                .mapToDouble(Float::doubleValue)
                .sum();

//        Log.d("sumOnJAP" , new Gson().toJson(sumOnJAP));
        Log.d("sumKedalaman", new Gson().toJson(sumKedalaman));

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        dialog = new AlertDialog.Builder(HasilPengamatanActivity.this);
        dialogView = getLayoutInflater().inflate(R.layout.dialog_hasil_pengamatan_ridger, null);
        dialog.setView(dialogView);
        dialog.setCancelable(false);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("Hasil Pengamatan");

        final TextView mPencapaianJAP = dialogView.findViewById(R.id.mPencapaianJAP);
        final TextView mAvgJAP = dialogView.findViewById(R.id.mAvgJAP);
        final TextView mPencapianKDK = dialogView.findViewById(R.id.mPencapianKDK);
        final TextView mAvgKDKK = dialogView.findViewById(R.id.mAvgKDKK);
        final TextView mTotalPencapaian = dialogView.findViewById(R.id.mTotalPencapaian);
        final TextView mGrade = dialogView.findViewById(R.id.mGrade);

        float pencapianJAP = (float) listOnJAP.size() / data.size() * 100;
        float avgJAP = sumJAP / data.size();

        float pencapianKDK = (float) listOnKedalaman.size() / data.size() * 100;
        float avgKDKK = sumKedalaman / data.size();

        float totalPencapaian = (pencapianJAP * 0.5F) + (pencapianKDK * 0.5F);

        mPencapaianJAP.setText(df.format(pencapianJAP));
        mAvgJAP.setText(df.format(avgJAP));
        mPencapianKDK.setText(df.format(pencapianKDK));
        mAvgKDKK.setText(df.format(avgKDKK));
        mTotalPencapaian.setText(df.format(totalPencapaian));
        if (totalPencapaian >= 95) {
            mGrade.setText("Masuk Standart");
            mGrade.setTextColor(App.getApplication().getColor(R.color.colorPrimary));
        } else {
            mGrade.setText("Tidak Masuk Standart");
            mGrade.setTextColor(Color.RED);
        }
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Log.d("spiner", String.valueOf(spinnerPoktan.getSelectedItem()));
//                onCreatePoktan();
            }
        });

        dialog.setNegativeButton("Kembali", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
//                goToDashboard();
            }
        });

        dialog.show();
    }

    public void formulaTransport(List<HasilPengamatanModel> data) {
        ArrayList<Float> listGulud = new ArrayList<>();
        ArrayList<Float> listTercecer = new ArrayList<>();
        ArrayList<Float> listTerlindas = new ArrayList<>();
        ArrayList<Integer> listTumpuk = new ArrayList<>();
        ArrayList<Float> listRataRataTumpuk = new ArrayList<>();

        for (int i = 0; i < data.size(); ++i) {
            listGulud.add(data.get(i).getGulud());
            listTercecer.add(data.get(i).getDijalan());
            listTerlindas.add(data.get(i).getTerlindas());
            listTumpuk.add(data.get(i).getJumlah_tumpuk());
            listRataRataTumpuk.add(data.get(i).getRerata_tumpuk());
        }
//        Log.d("nilaiPencapaiannya", new Gson().toJson(nilaiPencapaianAgregat));

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        dialog = new AlertDialog.Builder(HasilPengamatanActivity.this);
        dialogView = getLayoutInflater().inflate(R.layout.dialog_hasil_pengamatan_transport, null);
        dialog.setView(dialogView);
        dialog.setCancelable(false);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("Hasil Pengamatan");

        float sumGulud = (float) listGulud.stream()
                .mapToDouble(Float::doubleValue)
                .sum();

        float sumDijalan = (float) listTercecer.stream()
                .mapToDouble(Float::doubleValue)
                .sum();

        float sumTerlindas = (float) listTerlindas.stream()
                .mapToDouble(Float::doubleValue)
                .sum();

        long sumTumpuk = listTumpuk.stream()
                .mapToLong(Integer::longValue)
                .sum();

        float sumRerataTumpuk = (float) listRataRataTumpuk.stream()
                .mapToDouble(Float::doubleValue)
                .sum();

        final TextView mGulud = dialogView.findViewById(R.id.mGulud);
        final TextView mTercecer = dialogView.findViewById(R.id.mTercecer);
        final TextView mTerlindas = dialogView.findViewById(R.id.mTerlindas);
        final TextView mSatTumpuk = dialogView.findViewById(R.id.mSatTumpuk);
        final TextView mAvgBibitTumpuk = dialogView.findViewById(R.id.mAvgBibitTumpuk);
        final TextView mTotal = dialogView.findViewById(R.id.mTotal);
        final TextView mTotalBibitTertinggal = dialogView.findViewById(R.id.mTotalBibitTertinggal);

        final TextView mGrade = dialogView.findViewById(R.id.mGrade);
        float avgTumpuk = sumRerataTumpuk / data.size();
        float total = (float) sumTumpuk * avgTumpuk;
        float totalBibitTertinggal = total + sumGulud + sumDijalan + sumTerlindas;

        mGulud.setText(df.format(sumGulud));
        mTercecer.setText(df.format(sumDijalan));
        mTerlindas.setText(df.format(sumTerlindas));
        mSatTumpuk.setText(df.format(sumTumpuk));
        mAvgBibitTumpuk.setText(df.format(avgTumpuk));
        mTotal.setText(df.format(total));
        mTotalBibitTertinggal.setText(df.format(totalBibitTertinggal));
        if (total <= 500) {
            mGrade.setText("Masuk Standart");
            mGrade.setTextColor(App.getApplication().getColor(R.color.colorPrimary));
        } else {
            mGrade.setText("Tidak Masuk Standart");
            mGrade.setTextColor(Color.RED);
        }
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Log.d("spiner", String.valueOf(spinnerPoktan.getSelectedItem()));
//                onCreatePoktan();
            }
        });

        dialog.setNegativeButton("Kembali", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
//                goToDashboard();
            }
        });


        dialog.show();
    }

//    public void formulaFinishing(List<HasilPengamatanModel> data) {
//        ArrayList<Float> nilaiLolosAyakan = new ArrayList<>();
//        ArrayList<Float> nilaiPencapaianAgregat = new ArrayList<>();
//        ArrayList<Integer> kerataanAplikasi = new ArrayList<>();
//        float totalsample = 0;
////        float pencapaianAgregat = 0;
//        for (int i = 0; i < data.size(); ++i) {
//            kerataanAplikasi.add((data.get(i).getAPLIKASI_KERATAAN()));
//            totalsample = Float.parseFloat(String.valueOf(String.valueOf(data.get(i).getLolos_ayakan()))) + Float.parseFloat(String.valueOf(data.get(i).getTidak_lolos_ayakan()));
//            float pencapaianAgregat = Float.parseFloat(String.valueOf(data.get(i).getLolos_ayakan())) / totalsample * 100;
//            nilaiLolosAyakan.add(pencapaianAgregat);
//            Log.d("pencapaianAgregat", String.valueOf(pencapaianAgregat));
//            if (pencapaianAgregat >= 70) {
//                nilaiPencapaianAgregat.add(pencapaianAgregat);
//            }
//
////            Log.d("pencapaian" ,String.valueOf(Float.parseFloat(data.get(i).getLOLOS_AYAKAN())+Float.parseFloat(data.get(i).getTIDAK_LOLOS_AYAKAN())) );
//
//        }
//        Log.d("nilaiPencapaiannya", new Gson().toJson(nilaiPencapaianAgregat));
////
////        Log.d("totaldata masuk" ,String.valueOf(nilaiPencapaianAgregat.size()));
////        Log.d("total data semuanya" ,String.valueOf(data.size()));
//        long sumKerataan = kerataanAplikasi.stream()
//                .mapToLong(Integer::longValue)
//                .sum();
//
//        Float sumLolosAyakan = (float) nilaiLolosAyakan.stream()
//                .mapToDouble(Float::doubleValue)
//                .sum();
//
//
//        int hasilKerataanAplikasi = (int) sumKerataan / data.size() * 100;
//        float hasilnya = (float) nilaiPencapaianAgregat.size() / data.size() * 100;
//        Log.d("hasilnyawoi", String.valueOf(nilaiLolosAyakan));
//        DecimalFormat df = new DecimalFormat();
//        df.setMaximumFractionDigits(2);
//        dialog = new AlertDialog.Builder(HasilPengamatanActivity.this);
//        dialogView = getLayoutInflater().inflate(R.layout.dialog_hasil_pengamatan_finishing, null);
//        dialog.setView(dialogView);
//        dialog.setCancelable(false);
//        dialog.setIcon(R.mipmap.ic_launcher);
//        dialog.setTitle("Hasil Pengamatan");
//
//        final TextView mRataRataAgregat = dialogView.findViewById(R.id.mRataRataAgregat);
//        final TextView mAgregat = dialogView.findViewById(R.id.mAgregat);
//        final TextView mAplikasiKerataan = dialogView.findViewById(R.id.mAplikasiKerataan);
//        final TextView mTotalPencapaian = dialogView.findViewById(R.id.mTotalPencapaian);
//        final TextView mGrade = dialogView.findViewById(R.id.mGrade);
//
//        mAgregat.setText(df.format(hasilnya));
//        mAplikasiKerataan.setText(String.valueOf(hasilKerataanAplikasi));
//        float dataAgregat = sumLolosAyakan / data.size();
//        mRataRataAgregat.setText(df.format(dataAgregat));
//        float totalPencapaian = (float) (Float.parseFloat(mAgregat.getText().toString()) * 0.6 + Float.parseFloat(mAplikasiKerataan.getText().toString()) * 0.4);
//        mTotalPencapaian.setText(df.format(totalPencapaian));
//        if (totalPencapaian >= 80) {
//            mGrade.setText("Masuk Standart");
//        } else {
//            mGrade.setText("Tidak Masuk Standart");
//            mGrade.setTextColor(Color.RED);
//        }
//        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
////                Log.d("spiner", String.valueOf(spinnerPoktan.getSelectedItem()));
////                onCreatePoktan();
//            }
//        });
//
//        dialog.setNegativeButton("Kembali", new DialogInterface.OnClickListener() {
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
////                goToDashboard();
//            }
//        });
//
//
////        float pencapaian = totalnilaiChopper(mTanamanHancur.getText().toString() , mBonggol.getText().toString(),mAplikasiRapat.getText().toString());
////        mTotalPencapaian.setText(String.valueOf(pencapaian));
////        if(pencapaian >= 85){
////            mGrade.setText("Masuk Standart");
////        }else{
////            mGrade.setText("Tidak Masuk Standart");
////        }
//
//
//        dialog.show();
//    }

    private void formulaJumlahBaris(List<HasilPengamatanModel> data) {
        ArrayList<Integer> listKurangBaris = new ArrayList<>();
        ArrayList<Integer> listOnStandar = new ArrayList<>();
        ArrayList<Integer> listLebihBaris = new ArrayList<>();
        ArrayList<Integer> listDataPenambahBaris = new ArrayList<>();


        for (int i = 0; i < data.size(); ++i) {
            if (data.get(i).getPenambahan_baris() < 0) {
                listKurangBaris.add(data.get(i).getPenambahan_baris());
            }
            if (data.get(i).getPenambahan_baris() == 0) {
                listOnStandar.add(data.get(i).getPenambahan_baris());
            }
            if (data.get(i).getPenambahan_baris() > 0) {
                listLebihBaris.add(data.get(i).getPenambahan_baris());
            }

            listDataPenambahBaris.add(data.get(i).getPenambahan_baris());
        }

        Log.d("listkurangBaris", String.valueOf(listKurangBaris.size()));
        Log.d("listOnStandar", String.valueOf(listOnStandar.size()));
        Log.d("listLebihBaris", String.valueOf(listLebihBaris.size()));
        long sumKurangBaris = listKurangBaris.stream()
                .mapToLong(Integer::longValue)
                .sum();
        long sumLebihBaris = listLebihBaris.stream()
                .mapToLong(Integer::longValue)
                .sum();
        long sumAllData = listDataPenambahBaris.stream()
                .mapToLong(Integer::longValue)
                .sum();

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        dialog = new AlertDialog.Builder(HasilPengamatanActivity.this);
        dialogView = getLayoutInflater().inflate(R.layout.dialog_hasil_pengamatan_jumlah_baris, null);
        dialog.setView(dialogView);
        dialog.setCancelable(false);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("Hasil Pengamatan");

        final TextView mKesesuaianJumlahKurang = dialogView.findViewById(R.id.mKesesuaianJumlahKurang);
        final TextView mKesesuaianJumlahOnStd = dialogView.findViewById(R.id.mKesesuaianJumlahOnStd);
        final TextView mKesesuaianJumlahLebih = dialogView.findViewById(R.id.mKesesuaianJumlahLebih);
        final TextView mJumlahBarisKurang = dialogView.findViewById(R.id.mJumlahBarisKurang);
        final TextView mJumlahBarisLebih = dialogView.findViewById(R.id.mJumlahBarisLebih);
        final TextView mGrade = dialogView.findViewById(R.id.mGrade);
        mKesesuaianJumlahKurang.setText(df.format(listKurangBaris.size()));
        mKesesuaianJumlahOnStd.setText(df.format(listOnStandar.size()));
        mKesesuaianJumlahLebih.setText(df.format(listLebihBaris.size()));
        mJumlahBarisKurang.setText(df.format(sumKurangBaris));
        mJumlahBarisLebih.setText(df.format(sumLebihBaris));

//        mTotalPencapaian.setText(String.valueOf(totalnilaiChopper(String.valueOf(sumTanamanHancur),String.valueOf(sumBonggolTercacah),String.valueOf(sumAplikasiRapat))));

        if (sumAllData == 0) {
            mGrade.setText("Masuk Standart");
            mGrade.setTextColor(Color.GREEN);
        } else {
            mGrade.setText("Tidak Masuk Standart");
            mGrade.setTextColor(Color.RED);
        }
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        dialog.setNegativeButton("Kembali", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
//                goToDashboard();
            }
        });

        dialog.show();
    }

    //    private void formulaChopper(List<HasilPengamatanModel> data) {
//        ArrayList<Float> tanamanHancur = new ArrayList<>();
//        ArrayList<Float> bonggolTercacah = new ArrayList<>();
//        ArrayList<Float> aplikasiRapat = new ArrayList<>();
//
//
//        for (int i = 0; i < data.size(); ++i) {
//            tanamanHancur.add(Float.parseFloat(data.get(i).getTanaman_hancur()));
//            bonggolTercacah.add(Float.parseFloat(data.get(i).getBonggol_terpecah()));
//            aplikasiRapat.add(Float.parseFloat(data.get(i).getAplikasi_rapat()));
//        }
//
//        sumTanamanHancur = (float) tanamanHancur.stream()
//                .mapToDouble(Float::doubleValue)
//                .sum();
//        sumBonggolTercacah = (float) bonggolTercacah.stream()
//                .mapToDouble(Float::doubleValue)
//                .sum();
//        sumAplikasiRapat = (float) aplikasiRapat.stream()
//                .mapToDouble(Float::doubleValue)
//                .sum();
//        DecimalFormat df = new DecimalFormat();
//        df.setMaximumFractionDigits(2);
//        dialog = new AlertDialog.Builder(HasilPengamatanActivity.this);
//        dialogView = getLayoutInflater().inflate(R.layout.dialog_hasil_pengamatan, null);
//        dialog.setView(dialogView);
//        dialog.setCancelable(false);
//        dialog.setIcon(R.mipmap.ic_launcher);
//        dialog.setTitle("Hasil Pengamatan");
//
//        final TextView mTanamanHancur = dialogView.findViewById(R.id.mTanamanHancur);
//        final TextView mBonggol = dialogView.findViewById(R.id.mBonggol);
//        final TextView mAplikasiRapat = dialogView.findViewById(R.id.mAplikasiRapat);
//        final TextView mTotalPencapaian = dialogView.findViewById(R.id.mTotalPencapaian);
//        final TextView mGrade = dialogView.findViewById(R.id.mGrade);
//        mTanamanHancur.setText(df.format(sumTanamanHancur / data.size()));
//        mBonggol.setText(df.format(sumBonggolTercacah / data.size()));
//        mAplikasiRapat.setText(df.format(sumAplikasiRapat / data.size()));
//
////        mTotalPencapaian.setText(String.valueOf(totalnilaiChopper(String.valueOf(sumTanamanHancur),String.valueOf(sumBonggolTercacah),String.valueOf(sumAplikasiRapat))));
//        float pencapaian = totalnilaiChopper(mTanamanHancur.getText().toString(), mBonggol.getText().toString(), mAplikasiRapat.getText().toString());
//        mTotalPencapaian.setText(String.valueOf(pencapaian));
//        if (pencapaian >= 85) {
//            mGrade.setText("Masuk Standart");
//        } else {
//            mGrade.setText("Tidak Masuk Standart");
//        }
//        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
////                Log.d("spiner", String.valueOf(spinnerPoktan.getSelectedItem()));
////                onCreatePoktan();
//            }
//        });
//
//        dialog.setNegativeButton("Kembali", new DialogInterface.OnClickListener() {
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
////                goToDashboard();
//            }
//        });
//
//        dialog.show();
//    }
    private void formulaChopper(List<HasilPengamatanModel> data) {
        ArrayList<Float> tanamanHancur = new ArrayList<>();
        ArrayList<Float> bonggolTercacah = new ArrayList<>();
        ArrayList<Float> aplikasiRapat = new ArrayList<>();


        for (int i = 0; i < data.size(); ++i) {
            tanamanHancur.add(Float.parseFloat(data.get(i).getTanaman_hancur()));
            bonggolTercacah.add(Float.parseFloat(data.get(i).getBonggol_terpecah()));
            aplikasiRapat.add(Float.parseFloat(data.get(i).getAplikasi_rapat()));
        }

        sumTanamanHancur = (float) tanamanHancur.stream()
                .mapToDouble(Float::doubleValue)
                .sum();
        sumBonggolTercacah = (float) bonggolTercacah.stream()
                .mapToDouble(Float::doubleValue)
                .sum();
        sumAplikasiRapat = (float) aplikasiRapat.stream()
                .mapToDouble(Float::doubleValue)
                .sum();
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        dialog = new AlertDialog.Builder(HasilPengamatanActivity.this);
        dialogView = getLayoutInflater().inflate(R.layout.dialog_hasil_pengamatan, null);
        dialog.setView(dialogView);
        dialog.setCancelable(false);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("Hasil Pengamatan");

        final TextView mTanamanHancur = dialogView.findViewById(R.id.mTanamanHancur);
        final TextView mBonggol = dialogView.findViewById(R.id.mBonggol);
        final TextView mAplikasiRapat = dialogView.findViewById(R.id.mAplikasiRapat);
        final TextView mTotalPencapaian = dialogView.findViewById(R.id.mTotalPencapaian);
        final TextView mGrade = dialogView.findViewById(R.id.mGrade);

        float hasilTanamanHancur = sumTanamanHancur / data.size();
        float hasilBonggol = sumBonggolTercacah / data.size();
        float hasilTercacah = sumAplikasiRapat / data.size();
        mTanamanHancur.setText(df.format(hasilTanamanHancur));
        mBonggol.setText(df.format(hasilBonggol));
        mAplikasiRapat.setText(df.format(hasilTercacah));

//        mTotalPencapaian.setText(String.valueOf(totalnilaiChopper(String.valueOf(sumTanamanHancur),String.valueOf(sumBonggolTercacah),String.valueOf(sumAplikasiRapat))));
        float pencapaian = totalnilaiChopper(String.valueOf(hasilTanamanHancur), String.valueOf(hasilBonggol), String.valueOf(hasilTercacah));
        mTotalPencapaian.setText(df.format(pencapaian));
        if (pencapaian >= 85) {
            mGrade.setText("Masuk Standart");
        } else {
            mGrade.setText("Tidak Masuk Standart");
            mGrade.setTextColor(Color.RED);
        }
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Log.d("spiner", String.valueOf(spinnerPoktan.getSelectedItem()));
//                onCreatePoktan();
            }
        });

        dialog.setNegativeButton("Kembali", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
//                goToDashboard();
            }
        });

        dialog.show();
    }

    private void formulaBajak(List<HasilPengamatanModel> data) {

        ArrayList<Float> persenKedalamanBajak = new ArrayList<>();
        ArrayList<Float> listKedalamanBajak = new ArrayList<>();
        ArrayList<Float> persenAplikasiPinggiran = new ArrayList<>();
        ArrayList<Float> persenkerataanAplikasi = new ArrayList<>();
        ArrayList<Float> persenDeadFurrow = new ArrayList<>();
//
//
        for (int i = 0; i < data.size(); ++i) {
            float kedalaman = data.get(i).getKEDALAMAN();

            // ✅ Ubah DEAD_FURROW dari String ke float dulu
            String deadFurrowStr = data.get(i).getDEAD_FURROW();
            float deadFurrow = 0f;
            if (deadFurrowStr != null && !deadFurrowStr.isEmpty()) {
                try {
                    deadFurrow = Float.parseFloat(deadFurrowStr);
                } catch (NumberFormatException e) {
                    deadFurrow = 0f; // default jika input tidak valid
                }
            }

            String jenis = data.get(i).getJenis_bajak();

            if (jenis.contains("Bajak Dalam")) {
                if (kedalaman >= 40) {
                    persenKedalamanBajak.add(kedalaman);
                }
                if (deadFurrow == 0 || (deadFurrow > 0 && deadFurrow <= 20)) {
                    persenDeadFurrow.add(deadFurrow);
                }

            } else if (jenis.contains("Bajak Sedang")) {
                if (kedalaman >= 30) {
                    persenKedalamanBajak.add(kedalaman);
                }
                if (deadFurrow == 0 || (deadFurrow > 0 && deadFurrow <= 15)) {
                    persenDeadFurrow.add(deadFurrow);
                }

            } else if (jenis.contains("Bajak Dangkal")) {
                if (kedalaman >= 20) {
                    persenKedalamanBajak.add(kedalaman);
                }
                if (deadFurrow == 0 || (deadFurrow > 0 && deadFurrow <= 10)) {
                    persenDeadFurrow.add(deadFurrow);
                }
            }

            // ✅ yang lainnya tetap sama
            listKedalamanBajak.add(kedalaman);
            persenAplikasiPinggiran.add(Float.parseFloat(String.valueOf(data.get(i).getAPLIKASI_PINGGIRAN())));
            persenkerataanAplikasi.add(Float.parseFloat(String.valueOf(data.get(i).getAPLIKASI_KERATAAN())));
        }


        Log.d("deadfurrow", new Gson().toJson(persenDeadFurrow));
        float hasilPersenKedalaman = (float) persenKedalamanBajak.size() / data.size() * 100;
        Log.d("persenKedalaman", String.valueOf(hasilPersenKedalaman));
//
//        sumTanamanHancur = (float) tanamanHancur.stream()
//                .mapToDouble(Float::doubleValue)
//                .sum() ;
        float sumKedalamanBajak = (float) listKedalamanBajak.stream()
                .mapToDouble(Float::doubleValue)
                .sum();
        float sumAplikasiPinggiran = (float) persenAplikasiPinggiran.stream()
                .mapToDouble(Float::doubleValue)
                .sum();
        float sumKerataanAplikasi = (float) persenkerataanAplikasi.stream()
                .mapToDouble(Float::doubleValue)
                .sum();
        float sumDeadFurrow = (float) persenDeadFurrow.stream()
                .mapToDouble(Float::doubleValue)
                .sum();
        float hasilRataRataKedalaman = (int) sumKedalamanBajak / data.size();
        float hasilAplikasiPinggiran = sumAplikasiPinggiran / data.size() * 100;
        Log.d("persenAplikasiPinggiran", String.valueOf(hasilAplikasiPinggiran));

        float hasilKerataanAplikasi = sumKerataanAplikasi / data.size() * 100;
        Log.d("persenKerataanAplikasi", String.valueOf(hasilKerataanAplikasi));

        float hasilDeadFurrow = (float) persenDeadFurrow.size() / data.size() * 100;
        Log.d("DeadFurrow", String.valueOf(hasilDeadFurrow));

        float hasilPencapaianAkhir = (float) ((float) (hasilPersenKedalaman * 0.25) + (hasilAplikasiPinggiran * 0.25)
                + (hasilDeadFurrow * 0.25) + (hasilKerataanAplikasi * 0.25));
        Log.d("hasilakhirnyaaaaaa", String.valueOf(hasilPencapaianAkhir));
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        dialog = new AlertDialog.Builder(HasilPengamatanActivity.this);
        dialogView = getLayoutInflater().inflate(R.layout.dialog_hasil_pengamatan_bajak, null);
        dialog.setView(dialogView);
        dialog.setCancelable(false);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("Hasil Pengamatan");
//
        final TextView pencapaianKedalaman = dialogView.findViewById(R.id.mHasilPencapaianKedalamanBajak);
        final TextView aplikasiPinggiran = dialogView.findViewById(R.id.mHasilAplikasiPinggiran);
        final TextView deadFurrow = dialogView.findViewById(R.id.mHasilDeadFurrow);
        final TextView kerataanAplikasi = dialogView.findViewById(R.id.mHasilKerataanAplikasi);
        final TextView rata_rataKedalaman = dialogView.findViewById(R.id.mRataRataKedalaman);
        final TextView totalPencapaian = dialogView.findViewById(R.id.mTotalPencapaian);
        final TextView mGrade = dialogView.findViewById(R.id.mGrade);

        pencapaianKedalaman.setText(df.format(hasilPersenKedalaman));
        aplikasiPinggiran.setText(df.format(hasilAplikasiPinggiran));
        deadFurrow.setText(df.format(hasilDeadFurrow));
        kerataanAplikasi.setText(df.format(hasilKerataanAplikasi));
        rata_rataKedalaman.setText(df.format(hasilRataRataKedalaman));
        totalPencapaian.setText(df.format(hasilPencapaianAkhir));
        if (hasilPencapaianAkhir >= 90)
            mGrade.setText("Masuk Standart");
        else {
            mGrade.setText("Tidak Masuk Standart");
            mGrade.setTextColor(Color.RED);
        }
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Log.d("spiner", String.valueOf(spinnerPoktan.getSelectedItem()));
//                onCreatePoktan();
            }
        });

        dialog.setNegativeButton("Kembali", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
//                goToDashboard();
            }
        });

        dialog.show();
    }

    private void formulaSubsoiler(List<HasilPengamatanModel> data) {


        ArrayList<Float> listKedalamanBajak = new ArrayList<>();
        ArrayList<Float> listKerataanAplikasi = new ArrayList<>();
        ArrayList<Integer> valKedalam = new ArrayList<>();
//
//
        for (int i = 0; i < data.size(); ++i) {
            float kedalaman = Float.parseFloat(String.valueOf(data.get(i).getKEDALAMAN()));
            float kerataan = Float.parseFloat(String.valueOf(data.get(i).getAPLIKASI_KERATAAN()));
            valKedalam.add(data.get(i).getKEDALAMAN());
            // Kedalaman
            listKedalamanBajak.add(kedalaman >= 60 ? 100f : 0f);

            // Kerataan
            listKerataanAplikasi.add(kerataan <= 180 ? 100f : 0f);
        }



        float sumValKedalaman = (int) valKedalam.stream()
                .mapToDouble(Integer::doubleValue)
                .sum();


        float sumKedalamanBajak = (float) listKedalamanBajak.stream()
                .mapToDouble(Float::doubleValue)
                .sum();

        float sumKerataanAplikasi = (float) listKerataanAplikasi.stream()
                .mapToDouble(Float::doubleValue)
                .sum();

        float hasilRataRataKedalaman = (int) sumValKedalaman / data.size();

        float hasilPersenKedalaman = (float) sumKedalamanBajak / data.size() ;
        float hasilKerataanAplikasi = sumKerataanAplikasi / data.size() ;



        float hasilPencapaianAkhir = (float) ((float) (hasilPersenKedalaman * 0.5)
                + (hasilKerataanAplikasi * 0.5));
        Log.d("hasilakhirnyaaaaaa", String.valueOf(hasilPencapaianAkhir));
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        dialog = new AlertDialog.Builder(HasilPengamatanActivity.this);
        dialogView = getLayoutInflater().inflate(R.layout.dialog_hasil_pengamatan_subsoiler, null);
        dialog.setView(dialogView);
        dialog.setCancelable(false);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("Hasil Pengamatan");
//
        final TextView pencapaianKedalaman = dialogView.findViewById(R.id.mHasilPencapaianKedalamanBajak);
        final TextView kerataanAplikasi = dialogView.findViewById(R.id.mHasilKerataanAplikasi);
        final TextView rata_rataKedalaman = dialogView.findViewById(R.id.mRataRataKedalaman);
        final TextView totalPencapaian = dialogView.findViewById(R.id.mTotalPencapaian);
        final TextView mGrade = dialogView.findViewById(R.id.mGrade);

        pencapaianKedalaman.setText(df.format(hasilPersenKedalaman));

        kerataanAplikasi.setText(df.format(hasilKerataanAplikasi));
        rata_rataKedalaman.setText(df.format(hasilRataRataKedalaman));
        totalPencapaian.setText(df.format(hasilPencapaianAkhir));
        if (hasilPencapaianAkhir >= 90)
            mGrade.setText("Masuk Standart");
        else {
            mGrade.setText("Tidak Masuk Standart");
            mGrade.setTextColor(Color.RED);
        }
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Log.d("spiner", String.valueOf(spinnerPoktan.getSelectedItem()));
//                onCreatePoktan();
            }
        });

        dialog.setNegativeButton("Kembali", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
//                goToDashboard();
            }
        });

        dialog.show();
    }

    public void formulaDropBibit(List<HasilPengamatanModel> data) {
        Log.d("dataReady", new Gson().toJson(data));
        float sumbibit = 0;
        float sumBibitUnderClass = 0;
        float sumBibitUpperClass = 0;
        float sumNormalAfkir = 0;


        ArrayList<Float> onClass = new ArrayList<>();
        ArrayList<Float> underClass = new ArrayList<>();
        ArrayList<Float> upperClass = new ArrayList<>();
        ArrayList<Float> label = new ArrayList<>();
        ArrayList<Float> real = new ArrayList<>();
        ArrayList<Float> afkir = new ArrayList<>();
        ArrayList<Float> normal = new ArrayList<>();
        ArrayList<Float> jumlahBibitTumpuk = new ArrayList<>();


        for (int i = 0; i < data.size(); ++i) {


            afkir.add(data.get(i).getBibit_afkir());
            normal.add(data.get(i).getBibit_normal());
            sumNormalAfkir = data.get(i).getBibit_normal() + data.get(i).getBibit_afkir();


            if (data.get(i).getJenis_bibit().equals("Sucker") || data.get(i).getJenis_bibit().equals("Sucker Plus")) {

                if (sumNormalAfkir >= 25 & sumNormalAfkir <= 30) {
                    jumlahBibitTumpuk.add(sumNormalAfkir);
                }
                if (data.get(i).getKelas_bibit().equals("Besar")) {
                    sumbibit = (float) (data.get(i).getBibit_1() + data.get(i).getBibit_2()) / data.get(i).getBibit_normal();
                    sumBibitUnderClass = (float) (data.get(i).getBibit_3() + data.get(i).getBibit_4() +
                            data.get(i).getBibit_5() + data.get(i).getBibit_6() + data.get(i).getBibit_7()) / data.get(i).getBibit_normal();
                    sumBibitUpperClass = (float) (data.get(i).getBibit_over() + data.get(i).getBibit_over_plus()) / data.get(i).getBibit_normal();
                    underClass.add(sumBibitUnderClass);
                    upperClass.add(sumBibitUpperClass);
                    onClass.add(sumbibit);

                } else if (data.get(i).getKelas_bibit().equals("Sedang")) {
                    sumbibit = (float) (data.get(i).getBibit_3() + data.get(i).getBibit_4()) / data.get(i).getBibit_normal();
                    sumBibitUnderClass = (float) (data.get(i).getBibit_5() + data.get(i).getBibit_6() + data.get(i).getBibit_7()) / data.get(i).getBibit_normal();
                    sumBibitUpperClass = (float) (data.get(i).getBibit_over() + data.get(i).getBibit_over_plus() + data.get(i).getBibit_1() + data.get(i).getBibit_2()) / data.get(i).getBibit_normal();
                    underClass.add(sumBibitUnderClass);
                    upperClass.add(sumBibitUpperClass);
                    onClass.add(sumbibit);
                } else if (data.get(i).getKelas_bibit().equals("Kecil")) {
                    sumbibit = (float) (data.get(i).getBibit_5() + data.get(i).getBibit_6()) / data.get(i).getBibit_normal();
                    sumBibitUnderClass = (float) data.get(i).getBibit_7() / data.get(i).getBibit_normal();
                    sumBibitUpperClass = (float) (data.get(i).getBibit_over() + data.get(i).getBibit_over_plus() +
                            data.get(i).getBibit_1() + data.get(i).getBibit_2() + data.get(i).getBibit_3() + data.get(i).getBibit_4()) / data.get(i).getBibit_normal();
                    underClass.add(sumBibitUnderClass);
                    upperClass.add(sumBibitUpperClass);
                    onClass.add(sumbibit);
                } else if (data.get(i).getKelas_bibit().equals("Super Kecil")) {
                    sumbibit = (float) data.get(i).getBibit_7() / data.get(i).getBibit_normal();
                    sumBibitUpperClass = (float) (data.get(i).getBibit_over() + data.get(i).getBibit_over_plus() +
                            data.get(i).getBibit_1() + data.get(i).getBibit_2() + data.get(i).getBibit_3() + data.get(i).getBibit_4() + data.get(i).getBibit_5() + data.get(i).getBibit_6()) / data.get(i).getBibit_normal();
                    upperClass.add(sumBibitUpperClass);
                    onClass.add(sumbibit);
                } else if (data.get(i).getKelas_bibit().equals("Over")) {
                    sumbibit = (float) data.get(i).getBibit_over() / data.get(i).getBibit_normal();
                    sumBibitUnderClass = (float) (data.get(i).getBibit_1() + data.get(i).getBibit_2() + data.get(i).getBibit_3() + data.get(i).getBibit_4() + data.get(i).getBibit_5() + data.get(i).getBibit_6() + data.get(i).getBibit_7()) / data.get(i).getBibit_normal();
                    sumBibitUpperClass = (float) data.get(i).getBibit_over_plus() / data.get(i).getBibit_normal();
                    underClass.add(sumBibitUnderClass);
                    upperClass.add(sumBibitUpperClass);
                    onClass.add(sumbibit);
                } else {
                    sumbibit = (float) data.get(i).getBibit_over_plus() / data.get(i).getBibit_normal();
                    sumBibitUnderClass = (float) (data.get(i).getBibit_over() + data.get(i).getBibit_1() + data.get(i).getBibit_2() + data.get(i).getBibit_3() + data.get(i).getBibit_4() + data.get(i).getBibit_5() + data.get(i).getBibit_6() + data.get(i).getBibit_7()) / data.get(i).getBibit_normal();
                    underClass.add(sumBibitUnderClass);
                    onClass.add(sumbibit);
                }
            } else if (data.get(i).getJenis_bibit().equals("Crown")) {
                if (sumNormalAfkir >= 50 & sumNormalAfkir <= 60) {
                    jumlahBibitTumpuk.add(sumNormalAfkir);
                }
                if (data.get(i).getKelas_bibit().equals("Kecil")) {
                    sumbibit = (float) (data.get(i).getBibit_3() + data.get(i).getBibit_4() + data.get(i).getBibit_5() +
                            data.get(i).getBibit_6()) / data.get(i).getBibit_normal();
                    sumBibitUnderClass = (float) data.get(i).getBibit_7() / data.get(i).getBibit_normal();
                    underClass.add(sumBibitUnderClass);
                    onClass.add(sumbibit);
                } else {
                    sumbibit = (float) data.get(i).getBibit_7() / data.get(i).getBibit_normal();

                    sumBibitUpperClass = (float) (data.get(i).getBibit_3() + data.get(i).getBibit_4() + data.get(i).getBibit_5() +
                            data.get(i).getBibit_6()) / data.get(i).getBibit_normal();

                    upperClass.add(sumBibitUpperClass);
                    onClass.add(sumbibit);
                }
            } else {
                if (sumNormalAfkir >= 50 & sumNormalAfkir <= 60) {
                    jumlahBibitTumpuk.add(sumNormalAfkir);
                }
                if (data.get(i).getKelas_bibit().equals("Kecil")) {
                    sumbibit = (float) (data.get(i).getBibit_5() + data.get(i).getBibit_6()) / data.get(i).getBibit_normal();
                    onClass.add(sumbibit);
                    sumBibitUpperClass = (float) (data.get(i).getBibit_3() + data.get(i).getBibit_4()) / data.get(i).getBibit_normal();
                    upperClass.add(sumBibitUpperClass);

                } else {
                    sumbibit = (float) (data.get(i).getBibit_3() + data.get(i).getBibit_4()) / data.get(i).getBibit_normal();
                    onClass.add(sumbibit);
                    sumBibitUnderClass = (float) (data.get(i).getBibit_5() + data.get(i).getBibit_6()) / data.get(i).getBibit_normal();
                    underClass.add(sumBibitUnderClass);
                }
            }
        }


        float sumOnClass = (float) onClass.stream()
                .mapToDouble(Float::doubleValue)
                .sum();
        float sumUnderClass = (float) underClass.stream()
                .mapToDouble(Float::doubleValue)
                .sum();
        float sumUpperClass = (float) upperClass.stream()
                .mapToDouble(Float::doubleValue)
                .sum();
        float sumLabel = (float) label.stream()
                .mapToDouble(Float::doubleValue)
                .sum();

        float sumReal = (float) real.stream()
                .mapToDouble(Float::doubleValue)
                .sum();
        float sumAfkir = (float) afkir.stream()
                .mapToDouble(Float::doubleValue)
                .sum();
        float sumNormal = (float) normal.stream()
                .mapToDouble(Float::doubleValue)
                .sum();
        float sumSelisih = sumReal - sumLabel;
        float averageOnClass = sumOnClass / data.size() * 100;
        float averageUnderClass = sumUnderClass / data.size() * 100;
        float averageUpperClass = sumUpperClass / data.size() * 100;
        float persentaseAfkir = sumAfkir / (sumNormal + sumAfkir) * 100;
        float persentaseNormal = sumNormal / (sumNormal + sumAfkir) * 100;
        float persentaseJumlahBibitTumpuk = (float) jumlahBibitTumpuk.size() / data.size() * 100;


        Log.d("onClass", new Gson().toJson(onClass));

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        dialog = new AlertDialog.Builder(HasilPengamatanActivity.this);
        dialogView = getLayoutInflater().inflate(R.layout.dialog_hasil_pengamatan_drop_bibit, null);
        dialog.setView(dialogView);
        dialog.setCancelable(false);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("Hasil Pengamatan");
//
        final TextView txtOnclass = dialogView.findViewById(R.id.mOnClass);
        final TextView txtUnderClass = dialogView.findViewById(R.id.mUnderClass);
        final TextView txtUpperClass = dialogView.findViewById(R.id.mUpperClass);
        final TextView txtNormal = dialogView.findViewById(R.id.mNormal);
//        final TextView txtReal = dialogView.findViewById(R.id.mReal);
//        final TextView txtSelisih = dialogView.findViewById(R.id.mSelisih);
        final TextView txtAfkir = dialogView.findViewById(R.id.mAfkir);
//        final TextView txtJumlahBibit = dialogView.findViewById(R.id.mHasilJumlahBibit);
        final TextView mGrade = dialogView.findViewById(R.id.mGrade);

        txtOnclass.setText(df.format(averageOnClass));
        txtUpperClass.setText(df.format(averageUpperClass));
        txtUnderClass.setText(df.format(averageUnderClass));
        txtNormal.setText(df.format(persentaseNormal));

        txtAfkir.setText(df.format(persentaseAfkir));
//        txtJumlahBibit.setText(df.format(persentaseJumlahBibitTumpuk));
//        aplikasiPinggiran.setText(df.format(hasilAplikasiPinggiran));
//        deadFurrow.setText(df.format(hasilDeadFurrow));
//        kerataanAplikasi.setText(df.format(hasilKerataanAplikasi));
//        rata_rataKedalaman.setText(df.format(hasilRataRataKedalaman));
//        totalPencapaian.setText(df.format(hasilPencapaianAkhir));
        if (averageOnClass >= 95 & sumAfkir <= 0.75F)
            mGrade.setText("Masuk Standart");
        else {
            mGrade.setText("Tidak Masuk Standart");
            mGrade.setTextColor(Color.RED);
        }
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Log.d("spiner", String.valueOf(spinnerPoktan.getSelectedItem()));
//                onCreatePoktan();
            }
        });

        dialog.setNegativeButton("Kembali", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
//                goToDashboard();
            }
        });

        dialog.show();
    }

    public void formulaPetikBibit(List<HasilPengamatanModel> data) {
        Log.d("dataReady", new Gson().toJson(data));
        float sumbibit = 0;
        float sumBibitUnderClass = 0;
        float sumBibitUpperClass = 0;
        float sumNormalAfkir = 0;


        ArrayList<Float> onClass = new ArrayList<>();
        ArrayList<Float> underClass = new ArrayList<>();
        ArrayList<Float> upperClass = new ArrayList<>();
        ArrayList<Float> label = new ArrayList<>();
        ArrayList<Float> real = new ArrayList<>();
        ArrayList<Float> afkir = new ArrayList<>();
        ArrayList<Float> normal = new ArrayList<>();
        ArrayList<Float> jumlahBibitTumpuk = new ArrayList<>();


        for (int i = 0; i < data.size(); ++i) {

            label.add(Float.parseFloat(data.get(i).getLabel()));
            real.add(data.get(i).getReal());
            afkir.add(data.get(i).getBibit_afkir());
            normal.add(data.get(i).getBibit_normal());
            sumNormalAfkir = data.get(i).getBibit_normal() + data.get(i).getBibit_afkir();


            if (data.get(i).getJenis_bibit().equals("Sucker") || data.get(i).getJenis_bibit().equals("Sucker Plus")) {

                if (sumNormalAfkir >= 25 & sumNormalAfkir <= 30) {
                    jumlahBibitTumpuk.add(sumNormalAfkir);
                }
                if (data.get(i).getKelas_bibit().equals("Besar")) {
                    sumbibit = (float) (data.get(i).getBibit_1() + data.get(i).getBibit_2()) / data.get(i).getBibit_normal();
                    sumBibitUnderClass = (float) (data.get(i).getBibit_3() + data.get(i).getBibit_4() +
                            data.get(i).getBibit_5() + data.get(i).getBibit_6() + data.get(i).getBibit_7()) / data.get(i).getBibit_normal();
                    sumBibitUpperClass = (float) (data.get(i).getBibit_over() + data.get(i).getBibit_over_plus()) / data.get(i).getBibit_normal();
                    underClass.add(sumBibitUnderClass);
                    upperClass.add(sumBibitUpperClass);
                    onClass.add(sumbibit);

                } else if (data.get(i).getKelas_bibit().equals("Sedang")) {
                    sumbibit = (float) (data.get(i).getBibit_3() + data.get(i).getBibit_4()) / data.get(i).getBibit_normal();
                    sumBibitUnderClass = (float) (data.get(i).getBibit_5() + data.get(i).getBibit_6() + data.get(i).getBibit_7()) / data.get(i).getBibit_normal();
                    sumBibitUpperClass = (float) (data.get(i).getBibit_over() + data.get(i).getBibit_over_plus() + data.get(i).getBibit_1() + data.get(i).getBibit_2()) / data.get(i).getBibit_normal();
                    underClass.add(sumBibitUnderClass);
                    upperClass.add(sumBibitUpperClass);
                    onClass.add(sumbibit);
                } else if (data.get(i).getKelas_bibit().equals("Kecil")) {
                    sumbibit = (float) (data.get(i).getBibit_5() + data.get(i).getBibit_6()) / data.get(i).getBibit_normal();
                    sumBibitUnderClass = (float) data.get(i).getBibit_7() / data.get(i).getBibit_normal();
                    sumBibitUpperClass = (float) (data.get(i).getBibit_over() + data.get(i).getBibit_over_plus() +
                            data.get(i).getBibit_1() + data.get(i).getBibit_2() + data.get(i).getBibit_3() + data.get(i).getBibit_4()) / data.get(i).getBibit_normal();
                    underClass.add(sumBibitUnderClass);
                    upperClass.add(sumBibitUpperClass);
                    onClass.add(sumbibit);
                } else if (data.get(i).getKelas_bibit().equals("Super Kecil")) {
                    sumbibit = (float) data.get(i).getBibit_7() / data.get(i).getBibit_normal();
                    sumBibitUpperClass = (float) (data.get(i).getBibit_over() + data.get(i).getBibit_over_plus() +
                            data.get(i).getBibit_1() + data.get(i).getBibit_2() + data.get(i).getBibit_3() + data.get(i).getBibit_4() + data.get(i).getBibit_5() + data.get(i).getBibit_6()) / data.get(i).getBibit_normal();
                    upperClass.add(sumBibitUpperClass);
                    onClass.add(sumbibit);
                } else if (data.get(i).getKelas_bibit().equals("Over")) {
                    sumbibit = (float) data.get(i).getBibit_over() / data.get(i).getBibit_normal();
                    sumBibitUnderClass = (float) (data.get(i).getBibit_1() + data.get(i).getBibit_2() + data.get(i).getBibit_3() + data.get(i).getBibit_4() + data.get(i).getBibit_5() + data.get(i).getBibit_6() + data.get(i).getBibit_7()) / data.get(i).getBibit_normal();
                    sumBibitUpperClass = (float) data.get(i).getBibit_over_plus() / data.get(i).getBibit_normal();
                    underClass.add(sumBibitUnderClass);
                    upperClass.add(sumBibitUpperClass);
                    onClass.add(sumbibit);
                } else {
                    sumbibit = (float) data.get(i).getBibit_over_plus() / data.get(i).getBibit_normal();
                    sumBibitUnderClass = (float) (data.get(i).getBibit_over() + data.get(i).getBibit_1() + data.get(i).getBibit_2() + data.get(i).getBibit_3() + data.get(i).getBibit_4() + data.get(i).getBibit_5() + data.get(i).getBibit_6() + data.get(i).getBibit_7()) / data.get(i).getBibit_normal();
                    underClass.add(sumBibitUnderClass);
                    onClass.add(sumbibit);
                }
            } else if (data.get(i).getJenis_bibit().equals("Crown")) {
                if (sumNormalAfkir >= 50 & sumNormalAfkir <= 60) {
                    jumlahBibitTumpuk.add(sumNormalAfkir);
                }
                if (data.get(i).getKelas_bibit().equals("Kecil")) {
                    sumbibit = (float) (data.get(i).getBibit_3() + data.get(i).getBibit_4() + data.get(i).getBibit_5() +
                            data.get(i).getBibit_6()) / data.get(i).getBibit_normal();
                    sumBibitUnderClass = (float) data.get(i).getBibit_7() / data.get(i).getBibit_normal();
                    underClass.add(sumBibitUnderClass);
                    onClass.add(sumbibit);
                } else {
                    sumbibit = (float) data.get(i).getBibit_7() / data.get(i).getBibit_normal();

                    sumBibitUpperClass = (float) (data.get(i).getBibit_3() + data.get(i).getBibit_4() + data.get(i).getBibit_5() +
                            data.get(i).getBibit_6()) / data.get(i).getBibit_normal();

                    upperClass.add(sumBibitUpperClass);
                    onClass.add(sumbibit);
                }
            } else {
                if (sumNormalAfkir >= 50 & sumNormalAfkir <= 60) {
                    jumlahBibitTumpuk.add(sumNormalAfkir);
                }
                if (data.get(i).getKelas_bibit().equals("Kecil")) {
                    sumbibit = (float) (data.get(i).getBibit_5() + data.get(i).getBibit_6()) / data.get(i).getBibit_normal();
                    onClass.add(sumbibit);
                    sumBibitUpperClass = (float) (data.get(i).getBibit_3() + data.get(i).getBibit_4()) / data.get(i).getBibit_normal();
                    upperClass.add(sumBibitUpperClass);

                } else {
                    sumbibit = (float) (data.get(i).getBibit_3() + data.get(i).getBibit_4()) / data.get(i).getBibit_normal();
                    onClass.add(sumbibit);
                    sumBibitUnderClass = (float) (data.get(i).getBibit_5() + data.get(i).getBibit_6()) / data.get(i).getBibit_normal();
                    underClass.add(sumBibitUnderClass);
                }
            }
        }


        float sumOnClass = (float) onClass.stream()
                .mapToDouble(Float::doubleValue)
                .sum();
        float sumUnderClass = (float) underClass.stream()
                .mapToDouble(Float::doubleValue)
                .sum();
        float sumUpperClass = (float) upperClass.stream()
                .mapToDouble(Float::doubleValue)
                .sum();
        float sumLabel = (float) label.stream()
                .mapToDouble(Float::doubleValue)
                .sum();

        float sumReal = (float) real.stream()
                .mapToDouble(Float::doubleValue)
                .sum();
        float sumAfkir = (float) afkir.stream()
                .mapToDouble(Float::doubleValue)
                .sum();
        float sumNormal = (float) normal.stream()
                .mapToDouble(Float::doubleValue)
                .sum();
        float sumSelisih = sumReal - sumLabel;
        float averageOnClass = sumOnClass / data.size() * 100;
        float averageUnderClass = sumUnderClass / data.size() * 100;
        float averageUpperClass = sumUpperClass / data.size() * 100;
        float persentaseAfkir = sumAfkir / (sumNormal + sumAfkir) * 100;
        float persentaseJumlahBibitTumpuk = (float) jumlahBibitTumpuk.size() / data.size() * 100;


        Log.d("onClass", new Gson().toJson(onClass));

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        dialog = new AlertDialog.Builder(HasilPengamatanActivity.this);
        dialogView = getLayoutInflater().inflate(R.layout.dialog_hasil_pengamatan_petik_bibit, null);
        dialog.setView(dialogView);
        dialog.setCancelable(false);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("Hasil Pengamatan");
//
        final TextView txtOnclass = dialogView.findViewById(R.id.mOnClass);
        final TextView txtUnderClass = dialogView.findViewById(R.id.mUnderClass);
        final TextView txtUpperClass = dialogView.findViewById(R.id.mUpperClass);
        final TextView txtLabel = dialogView.findViewById(R.id.mLabel);
        final TextView txtReal = dialogView.findViewById(R.id.mReal);
        final TextView txtSelisih = dialogView.findViewById(R.id.mSelisih);
        final TextView txtAfkir = dialogView.findViewById(R.id.mAfkir);
        final TextView txtJumlahBibit = dialogView.findViewById(R.id.mHasilJumlahBibit);
        final TextView mGrade = dialogView.findViewById(R.id.mGrade);

        txtOnclass.setText(df.format(averageOnClass));
        txtUpperClass.setText(df.format(averageUpperClass));
        txtUnderClass.setText(df.format(averageUnderClass));
        txtLabel.setText(df.format(sumLabel));
        txtReal.setText(df.format(sumReal));
        txtSelisih.setText(df.format(sumSelisih));
        txtAfkir.setText(df.format(persentaseAfkir));
        txtJumlahBibit.setText(df.format(persentaseJumlahBibitTumpuk));
//        aplikasiPinggiran.setText(df.format(hasilAplikasiPinggiran));
//        deadFurrow.setText(df.format(hasilDeadFurrow));
//        kerataanAplikasi.setText(df.format(hasilKerataanAplikasi));
//        rata_rataKedalaman.setText(df.format(hasilRataRataKedalaman));
//        totalPencapaian.setText(df.format(hasilPencapaianAkhir));
        if (averageOnClass >= 95)
            mGrade.setText("Masuk Standart");
        else {
            mGrade.setText("Tidak Masuk Standart");
            mGrade.setTextColor(Color.RED);
        }
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Log.d("spiner", String.valueOf(spinnerPoktan.getSelectedItem()));
//                onCreatePoktan();
            }
        });

        dialog.setNegativeButton("Kembali", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
//                goToDashboard();
            }
        });

        dialog.show();
    }

    public void formulaPoolDipping(List<HasilPengamatanModel> data) {
        Log.d("dataReady", new Gson().toJson(data));
        float sumbibit = 0;
        float sumBibitUnderClass = 0;
        float sumBibitUpperClass = 0;
        float sumNormalAfkir = 0;

        ArrayList<Float> ListOnClass = new ArrayList<>();
        ArrayList<Integer> ListTerdipping = new ArrayList<>();
        ArrayList<Float> onClass = new ArrayList<>();
        ArrayList<Float> underClass = new ArrayList<>();
        ArrayList<Float> upperClass = new ArrayList<>();
        ArrayList<Float> afkir = new ArrayList<>();
        ArrayList<Float> normal = new ArrayList<>();
        ArrayList<Float> jumlahBibitTumpuk = new ArrayList<>();


        for (int i = 0; i < data.size(); ++i) {
            if (Integer.parseInt(data.get(i).getInformasi_bibit_terdipping()) == 1) {
                ListTerdipping.add(Integer.parseInt(data.get(i).getInformasi_bibit_terdipping()));
            }

//            label.add(Float.parseFloat(data.get(i).getLabel()));
//            real.add(data.get(i).getReal());
//            afkir.add(data.get(i).getBibit_afkir());
//            normal.add(data.get(i).getBibit_normal());
//            sumNormalAfkir = (float) data.get(i).getBibit_normal() + data.get(i).getBibit_afkir();


            if (data.get(i).getJenis_bibit().equals("Sucker") || data.get(i).getJenis_bibit().equals("Sucker Plus")) {

                if (data.get(i).getKelas_bibit().equals("Besar")) {
                    sumbibit = (float) (data.get(i).getBibit_1() + data.get(i).getBibit_2()) / data.get(i).getBibit_normal();
                    sumBibitUnderClass = (float) (data.get(i).getBibit_3() + data.get(i).getBibit_4() +
                            data.get(i).getBibit_5() + data.get(i).getBibit_6() + data.get(i).getBibit_7()) / data.get(i).getBibit_normal();
                    sumBibitUpperClass = (float) (data.get(i).getBibit_over() + data.get(i).getBibit_over_plus()) / data.get(i).getBibit_normal();
                    underClass.add(sumBibitUnderClass);
                    upperClass.add(sumBibitUpperClass);
                    onClass.add(sumbibit);

                } else if (data.get(i).getKelas_bibit().equals("Sedang")) {
                    sumbibit = (float) (data.get(i).getBibit_3() + data.get(i).getBibit_4()) / data.get(i).getBibit_normal();
                    sumBibitUnderClass = (float) (data.get(i).getBibit_5() + data.get(i).getBibit_6() + data.get(i).getBibit_7()) / data.get(i).getBibit_normal();
                    sumBibitUpperClass = (float) (data.get(i).getBibit_over() + data.get(i).getBibit_over_plus() + data.get(i).getBibit_1() + data.get(i).getBibit_2()) / data.get(i).getBibit_normal();
                    underClass.add(sumBibitUnderClass);
                    upperClass.add(sumBibitUpperClass);
                    onClass.add(sumbibit);
                } else if (data.get(i).getKelas_bibit().equals("Kecil")) {
                    sumbibit = (float) (data.get(i).getBibit_5() + data.get(i).getBibit_6()) / data.get(i).getBibit_normal();
                    sumBibitUnderClass = (float) data.get(i).getBibit_7() / data.get(i).getBibit_normal();
                    sumBibitUpperClass = (float) (data.get(i).getBibit_over() + data.get(i).getBibit_over_plus() +
                            data.get(i).getBibit_1() + data.get(i).getBibit_2() + data.get(i).getBibit_3() + data.get(i).getBibit_4()) / data.get(i).getBibit_normal();
                    underClass.add(sumBibitUnderClass);
                    upperClass.add(sumBibitUpperClass);
                    onClass.add(sumbibit);
                } else if (data.get(i).getKelas_bibit().equals("Super Kecil")) {
                    sumbibit = (float) data.get(i).getBibit_7() / data.get(i).getBibit_normal();
                    sumBibitUpperClass = (float) (data.get(i).getBibit_over() + data.get(i).getBibit_over_plus() +
                            data.get(i).getBibit_1() + data.get(i).getBibit_2() + data.get(i).getBibit_3() + data.get(i).getBibit_4() + data.get(i).getBibit_5() + data.get(i).getBibit_6()) / data.get(i).getBibit_normal();
                    upperClass.add(sumBibitUpperClass);
                    onClass.add(sumbibit);
                } else if (data.get(i).getKelas_bibit().equals("Over")) {
                    sumbibit = (float) data.get(i).getBibit_over() / data.get(i).getBibit_normal();
                    sumBibitUnderClass = (float) (data.get(i).getBibit_1() + data.get(i).getBibit_2() + data.get(i).getBibit_3() + data.get(i).getBibit_4() + data.get(i).getBibit_5() + data.get(i).getBibit_6() + data.get(i).getBibit_7()) / data.get(i).getBibit_normal();
                    sumBibitUpperClass = (float) data.get(i).getBibit_over_plus() / data.get(i).getBibit_normal();
                    underClass.add(sumBibitUnderClass);
                    upperClass.add(sumBibitUpperClass);
                    onClass.add(sumbibit);
                } else {
                    sumbibit = (float) data.get(i).getBibit_over_plus() / data.get(i).getBibit_normal();
                    sumBibitUnderClass = (float) (data.get(i).getBibit_over() + data.get(i).getBibit_1() + data.get(i).getBibit_2() + data.get(i).getBibit_3() + data.get(i).getBibit_4() + data.get(i).getBibit_5() + data.get(i).getBibit_6() + data.get(i).getBibit_7()) / data.get(i).getBibit_normal();
                    underClass.add(sumBibitUnderClass);
                    onClass.add(sumbibit);
                }
            } else if (data.get(i).getJenis_bibit().equals("Crown")) {
                if (sumNormalAfkir >= 50 & sumNormalAfkir <= 60) {
                    jumlahBibitTumpuk.add(sumNormalAfkir);
                }
                if (data.get(i).getKelas_bibit().equals("Kecil")) {
                    sumbibit = (float) (data.get(i).getBibit_3() + data.get(i).getBibit_4() + data.get(i).getBibit_5() +
                            data.get(i).getBibit_6()) / data.get(i).getBibit_normal();
                    sumBibitUnderClass = (float) data.get(i).getBibit_7() / data.get(i).getBibit_normal();
                    underClass.add(sumBibitUnderClass);
                    onClass.add(sumbibit);
                } else {
                    sumbibit = (float) data.get(i).getBibit_7() / data.get(i).getBibit_normal();

                    sumBibitUpperClass = (float) (data.get(i).getBibit_3() + data.get(i).getBibit_4() + data.get(i).getBibit_5() +
                            data.get(i).getBibit_6()) / data.get(i).getBibit_normal();

                    upperClass.add(sumBibitUpperClass);
                    onClass.add(sumbibit);
                }
            } else {
                if (sumNormalAfkir >= 50 & sumNormalAfkir <= 60) {
                    jumlahBibitTumpuk.add(sumNormalAfkir);
                }
                if (data.get(i).getKelas_bibit().equals("Kecil")) {
                    sumbibit = (float) (data.get(i).getBibit_5() + data.get(i).getBibit_6()) / data.get(i).getBibit_normal();
                    onClass.add(sumbibit);
                    sumBibitUpperClass = (float) (data.get(i).getBibit_3() + data.get(i).getBibit_4()) / data.get(i).getBibit_normal();
                    upperClass.add(sumBibitUpperClass);

                } else {
                    sumbibit = (float) (data.get(i).getBibit_3() + data.get(i).getBibit_4()) / data.get(i).getBibit_normal();
                    onClass.add(sumbibit);
                    sumBibitUnderClass = (float) (data.get(i).getBibit_5() + data.get(i).getBibit_6()) / data.get(i).getBibit_normal();
                    underClass.add(sumBibitUnderClass);
                }
            }
        }


        float sumOnClass = (float) onClass.stream()
                .mapToDouble(Float::doubleValue)
                .sum();
        float sumUnderClass = (float) underClass.stream()
                .mapToDouble(Float::doubleValue)
                .sum();
        float sumUpperClass = (float) upperClass.stream()
                .mapToDouble(Float::doubleValue)
                .sum();

        float sumAfkir = (float) afkir.stream()
                .mapToDouble(Float::doubleValue)
                .sum();
        float sumNormal = (float) normal.stream()
                .mapToDouble(Float::doubleValue)
                .sum();

        float averageOnClass = sumOnClass / data.size() * 100;
        float averageUnderClass = sumUnderClass / data.size() * 100;
        float averageUpperClass = sumUpperClass / data.size() * 100;
        float persentaseAfkir = sumAfkir / (sumNormal + sumAfkir) * 100;
        float persentaseJumlahBibitTumpuk = (float) jumlahBibitTumpuk.size() / data.size() * 100;

        float onClassStandart = 0.95F;


        for (int i = 0; i < onClass.size(); ++i) {
            if (onClass.get(i) >= onClassStandart) {
                ListOnClass.add(onClass.get(i));
            }
        }


        Log.d("ListOnClass", new Gson().toJson(onClass));

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        dialog = new AlertDialog.Builder(HasilPengamatanActivity.this);
        dialogView = getLayoutInflater().inflate(R.layout.dialog_hasil_pengamatan_pool_dipping, null);
        dialog.setView(dialogView);
        dialog.setCancelable(false);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("Hasil Pengamatan");
//
        final TextView mJumlahRitMasukStandart = dialogView.findViewById(R.id.mJumlahRitMasukStandart);
        final TextView mJumlahRitTidakMasukStandart = dialogView.findViewById(R.id.mJumlahRitTidakMasukStandart);
        final TextView mJumlahRitTerdipping = dialogView.findViewById(R.id.mJumlahRitTerdipping);
        final TextView mJumlahRitTidakTerdipping = dialogView.findViewById(R.id.mJumlahRitTidakTerdipping);


        mJumlahRitMasukStandart.setText(df.format(onClass.size()));
        mJumlahRitTerdipping.setText(df.format(ListTerdipping.size()));
        mJumlahRitTidakMasukStandart.setText(df.format(data.size() - onClass.size()));
        mJumlahRitTidakTerdipping.setText(df.format(data.size() - ListTerdipping.size()));


//        deadFurrow.setText(df.format(hasilDeadFurrow));
//        kerataanAplikasi.setText(df.format(hasilKerataanAplikasi));
//        rata_rataKedalaman.setText(df.format(hasilRataRataKedalaman));
//        totalPencapaian.setText(df.format(hasilPencapaianAkhir));
//        if (averageOnClass >= 95)
//            mGrade.setText("Masuk Standart");
//        else {
//            mGrade.setText("Tidak Masuk Standart");
//            mGrade.setTextColor(Color.RED);
//        }
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Log.d("spiner", String.valueOf(spinnerPoktan.getSelectedItem()));
//                onCreatePoktan();
            }
        });

        dialog.setNegativeButton("Kembali", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
//                goToDashboard();
            }
        });

        dialog.show();
    }

    public void formulaSesetBonggol(List<HasilPengamatanModel> data) {
        ArrayList<Float> ketinggianSampah = new ArrayList<>();
        ArrayList<Float> kebersihanKupasan = new ArrayList<>();
        ArrayList<Float> potonganBonggol = new ArrayList<>();
        ArrayList<Float> kondisiBonggol = new ArrayList<>();
        ArrayList<Float> kondisiMuatan = new ArrayList<>();
        float totalsample = 0;
//        float pencapaianAgregat = 0;
        for (int i = 0; i < data.size(); ++i) {
            float sampah = 0;
            float muatan = 0;
            float pencapianKupasan = data.get(i).getJumlah_sample_masuk_standar_kebersihan_kupasan() / data.get(i).getJumlah_sample() * 100;
            float pencapianPotongan = data.get(i).getJumlah_sample_masuk_standar_potongan_bonggol() / data.get(i).getJumlah_sample() * 100;
            float pencapianKondisi = data.get(i).getJumlah_sample_masuk_standar_kondisi_bonggol() / data.get(i).getJumlah_sample() * 100;
//            float pencapianMuatan = data.get(i).getJumlah_sample_masuk_standar_kondisi_bin() / data.get(i).getJumlah_sample() * 100 ;
            if (data.get(i).getKetinggian_sampah() <= 35)
                sampah = 100;
            else
                sampah = 0;

            if (data.get(i).getJumlah_sample_masuk_standar_kondisi_bin() == 1)
                muatan = 100;
            else
                muatan = 0;
            ketinggianSampah.add(sampah);
            kebersihanKupasan.add(pencapianKupasan);
            kondisiBonggol.add(pencapianKondisi);
            kondisiMuatan.add(muatan);
            potonganBonggol.add(pencapianPotongan);

        }

        Float sumKetinggianSampah = (float) ketinggianSampah.stream()
                .mapToDouble(Float::doubleValue)
                .sum();
        Float sumKebersihanKupasan = (float) kebersihanKupasan.stream()
                .mapToDouble(Float::doubleValue)
                .sum();
        Float sumPotonganBonggol = (float) potonganBonggol.stream()
                .mapToDouble(Float::doubleValue)
                .sum();
        Float sumKondisiBonggol = (float) kondisiBonggol.stream()
                .mapToDouble(Float::doubleValue)
                .sum();
        Float sumKondisiMuatan = (float) kondisiMuatan.stream()
                .mapToDouble(Float::doubleValue)
                .sum();


        float persentaseKetinggianSampah = sumKetinggianSampah / data.size();
        float persentaseKebersihanKupasan = sumKebersihanKupasan / data.size();
        float persentasiKondisiBonggol = sumKondisiBonggol / data.size();
        float persentaseKondisiMuatan = sumKondisiMuatan / data.size();
        float persentasePotonganBonggol = sumPotonganBonggol / data.size();

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        dialog = new AlertDialog.Builder(HasilPengamatanActivity.this);
        dialogView = getLayoutInflater().inflate(R.layout.dialog_hasil_pengamatan_setsetbonggol, null);
        dialog.setView(dialogView);
        dialog.setCancelable(false);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("Hasil Pengamatan");

        final TextView mKetinggianSampah = dialogView.findViewById(R.id.mKetinggianSampah);
        final TextView mKebersihanKupasan = dialogView.findViewById(R.id.mKebersihanKupasan);
        final TextView mKondisiBonggol = dialogView.findViewById(R.id.mKondisiBonggol);
        final TextView mKondisiMuatan = dialogView.findViewById(R.id.mKondisiMuatan);
        final TextView mPotonganBonggol = dialogView.findViewById(R.id.mPotonganBonggol);
//        final TextView mGrade = dialogView.findViewById(R.id.mGrade);
        mKetinggianSampah.setText(df.format(persentaseKetinggianSampah));
        mKebersihanKupasan.setText(df.format(persentaseKebersihanKupasan));
        mKondisiBonggol.setText(df.format(persentasiKondisiBonggol));
        mKondisiMuatan.setText(df.format(persentaseKondisiMuatan));
        mPotonganBonggol.setText(df.format(persentasePotonganBonggol));
//        mAgregat.setText(df.format(hasilnya));
//        mAplikasiKerataan.setText(String.valueOf(hasilKerataanAplikasi));
//        float dataAgregat = (float) sumLolosAyakan / data.size();
//        mRataRataAgregat.setText(df.format(dataAgregat));
//        float totalPencapaian = (float) (Float.parseFloat(mAgregat.getText().toString()) * 0.6 + Float.parseFloat(mAplikasiKerataan.getText().toString()) * 0.4);
//        mTotalPencapaian.setText(df.format(totalPencapaian));
//        if (totalPencapaian >= 80) {
//            mGrade.setText("Masuk Standart");
//        } else {
//            mGrade.setText("Tidak Masuk Standart");
//        }
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Log.d("spiner", String.valueOf(spinnerPoktan.getSelectedItem()));
//                onCreatePoktan();
            }
        });

        dialog.setNegativeButton("Kembali", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
//                goToDashboard();
            }
        });


//        float pencapaian = totalnilaiChopper(mTanamanHancur.getText().toString() , mBonggol.getText().toString(),mAplikasiRapat.getText().toString());
//        mTotalPencapaian.setText(String.valueOf(pencapaian));
//        if(pencapaian >= 85){
//            mGrade.setText("Masuk Standart");
//        }else{
//            mGrade.setText("Tidak Masuk Standart");
//        }


        dialog.show();
    }

    public void formulaTidakTerSesetBonggol(List<HasilPengamatanModel> data) {
        ArrayList<Float> dengan = new ArrayList<>();
        ArrayList<Float> tanpa = new ArrayList<>();
        ArrayList<Float> nonGrade = new ArrayList<>();
        ArrayList<Float> aKurang = new ArrayList<>();
        ArrayList<Float> a = new ArrayList<>();
        ArrayList<Float> b = new ArrayList<>();
        ArrayList<Float> c = new ArrayList<>();

        for (int i = 0; i < data.size(); ++i) {
            dengan.add((float) data.get(i).getJumlah_panjang_bonggol_kurang_dari_15() * 0.0773f);
            tanpa.add((float) data.get(i).getJumlah_panjang_bonggol_15_sampai_19() * 0.17482f);
            nonGrade.add((float) data.get(i).getJumlah_panjang_bonggol_20_sampai_22() * 0.25262f);
            aKurang.add((float) data.get(i).getJumlah_panjang_bonggol_23_sampai_25() * 0.31637f);
            a.add((float) data.get(i).getJumlah_panjang_bonggol_26_sampai_28() * 0.437f);
            b.add((float) data.get(i).getJumlah_panjang_bonggol_29_sampai_31() * 0.5215f);
            c.add((float) data.get(i).getJumlah_panjang_bonggol_lebih_dari_31() * 0.52263f);

        }


        Float sumDengan = (float) dengan.stream()
                .mapToDouble(Float::doubleValue)
                .sum();
        Float sumTanpa = (float) tanpa.stream()
                .mapToDouble(Float::doubleValue)
                .sum();
        Float sumNonGrade = (float) nonGrade.stream()
                .mapToDouble(Float::doubleValue)
                .sum();
        Float sumAkurang = (float) aKurang.stream()
                .mapToDouble(Float::doubleValue)
                .sum();
        Float sumA = (float) a.stream()
                .mapToDouble(Float::doubleValue)
                .sum();
        Float sumB = (float) b.stream()
                .mapToDouble(Float::doubleValue)
                .sum();
        Float sumC = (float) c.stream()
                .mapToDouble(Float::doubleValue)
                .sum();


        Float totalDengan = (float) ((sumDengan + sumNonGrade + sumAkurang + sumTanpa + sumA + sumB + sumC) / 0.0004) / 1000;
        Float totalTanpa = (float) ((sumNonGrade + sumAkurang + sumTanpa + sumA + sumB + sumC) / 0.0004) / 1000;
        Float totalNonGrade = (float) ((sumDengan) / 0.0004) / 1000;
        Float totalAkurang = (float) ((sumDengan + sumTanpa + sumNonGrade) / 0.0004) / 1000;
        Float totalA = (float) ((sumTanpa + sumNonGrade) / 0.0004) / 1000;
        Float totalB = (float) ((sumAkurang + sumA + sumB) / 0.0004) / 1000;
        Float totalC = (float) ((sumC) / 0.0004) / 1000;


        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        dialog = new AlertDialog.Builder(HasilPengamatanActivity.this);
        dialogView = getLayoutInflater().inflate(R.layout.dialog_hasil_pengamatan_bonggol_tidak_terseset, null);
        dialog.setView(dialogView);
        dialog.setCancelable(false);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("Hasil Pengamatan");

        final TextView mDengan = dialogView.findViewById(R.id.mDengan);
        final TextView mTanpa = dialogView.findViewById(R.id.mTanpa);
        final TextView mNonGrade = dialogView.findViewById(R.id.mNonGrade);
        final TextView mAkurang = dialogView.findViewById(R.id.mAkurang);
        final TextView mA = dialogView.findViewById(R.id.mA);
        final TextView mB = dialogView.findViewById(R.id.mB);
        final TextView mC = dialogView.findViewById(R.id.mC);
//        final TextView mGrade = dialogView.findViewById(R.id.mGrade);

        mDengan.setText(df.format(totalDengan));
        mTanpa.setText(df.format(totalTanpa));
        mNonGrade.setText(df.format(totalNonGrade));
        mAkurang.setText(df.format(totalAkurang));
        mA.setText(df.format(totalA));
        mB.setText(df.format(totalB));
        mC.setText(df.format(totalC));
//        mAplikasiKerataan.setText(String.valueOf(hasilKerataanAplikasi));
//        float dataAgregat = (float) sumLolosAyakan / data.size();
//        mRataRataAgregat.setText(df.format(dataAgregat));
//        float totalPencapaian = (float) (Float.parseFloat(mAgregat.getText().toString()) * 0.6 + Float.parseFloat(mAplikasiKerataan.getText().toString()) * 0.4);
//        mTotalPencapaian.setText(df.format(totalPencapaian));
//        if (totalPencapaian >= 80) {
//            mGrade.setText("Masuk Standart");
//        } else {
//            mGrade.setText("Tidak Masuk Standart");
//        }
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Log.d("spiner", String.valueOf(spinnerPoktan.getSelectedItem()));
//                onCreatePoktan();
            }
        });

        dialog.setNegativeButton("Kembali", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
//                goToDashboard();
            }
        });


        dialog.show();
    }

    public void formulaStekPanjang(List<HasilPengamatanModel> data) {
        ArrayList<Float> kelilingBatangBawah = new ArrayList<>();
        ArrayList<Float> kelilingBatangAtas = new ArrayList<>();
        ArrayList<Integer> ikatSPK = new ArrayList<>();
        ArrayList<Integer> realSPK = new ArrayList<>();
        ArrayList<Float> bibitNormal = new ArrayList<>();
        ArrayList<Float> afkir = new ArrayList<>();
        ArrayList<Float> sesuaiStd = new ArrayList<>();
        ArrayList<Float> afkirStd = new ArrayList<>();
        ArrayList<Float> kelilingBawah = new ArrayList<>();
        ArrayList<Float> kelilingAtas = new ArrayList<>();
        ArrayList<Float> kelilingStd = new ArrayList<>();


        for (int i = 0; i < data.size(); ++i) {
            realSPK.add(data.get(i).getRealBibitIkat());
            ikatSPK.add(data.get(i).getJumlahBibitIkatDiSpk());
            bibitNormal.add(data.get(i).getBibit_normal());
            afkir.add(data.get(i).getBibit_afkir());

        }
        kelilingBatangBawah.addAll(
                data.stream()
                        .flatMap(d -> Stream.of(d.getKelilingBibitBawah1(), d.getKelilingBibitBawah2(), d.getKelilingBibitBawah3(), d.getKelilingBibitBawah4(), d.getKelilingBibitBawah5(), d.getKelilingBibitBawah6()
                                , d.getKelilingBibitBawah7(), d.getKelilingBibitBawah8(), d.getKelilingBibitBawah9(), d.getKelilingBibitBawah10()))
                        .filter(keliling -> keliling >= 9)
                        .collect(Collectors.toList())
        );
        kelilingBatangAtas.addAll(
                data.stream()
                        .flatMap(d -> Stream.of(d.getKelilingBibitAtas1(), d.getKelilingBibitAtas2(), d.getKelilingBibitAtas3(), d.getKelilingBibitAtas4(), d.getKelilingBibitAtas5(), d.getKelilingBibitAtas6()
                                , d.getKelilingBibitAtas7(), d.getKelilingBibitAtas8(), d.getKelilingBibitAtas9(), d.getKelilingBibitAtas10()))
                        .filter(keliling -> keliling >= 6)
                        .collect(Collectors.toList())
        );

//
//        Float sumRealBibit = (float) dengan.stream()
//                .mapToDouble(Float::doubleValue)
//                .sum();
        long sumRealBibit = realSPK.stream()
                .mapToLong(Integer::longValue)
                .sum();
        long sumIkatSpk = ikatSPK.stream()
                .mapToLong(Integer::longValue)
                .sum();
        Float sumBibitNormal = (float) bibitNormal.stream()
                .mapToDouble(Float::doubleValue)
                .sum();
        Float sumAfkir = (float) afkir.stream()
                .mapToDouble(Float::doubleValue)
                .sum();


        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        dialog = new AlertDialog.Builder(HasilPengamatanActivity.this);
        dialogView = getLayoutInflater().inflate(R.layout.dialog_hasil_pengamatan_singkongstekpanjang, null);
        dialog.setView(dialogView);
        dialog.setCancelable(false);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("Hasil Pengamatan");

        final TextView mBawahSTD = dialogView.findViewById(R.id.mBawahSTD);
        final TextView mAtasSTD = dialogView.findViewById(R.id.mAtasSTD);
        final TextView mIkatSPK = dialogView.findViewById(R.id.mIkatSPK);
        final TextView mMasukSTD = dialogView.findViewById(R.id.mMasukSTD);
        final TextView mAfkir = dialogView.findViewById(R.id.mAfkir);
        final TextView mSesuaiSTD = dialogView.findViewById(R.id.mSesuaiSTD);
        final TextView mAfkirSTD = dialogView.findViewById(R.id.mAfkirSTD);
        final TextView mKelilingBawah = dialogView.findViewById(R.id.mKelilingBawah);
        final TextView mKelilingAtas = dialogView.findViewById(R.id.mKelilingAtas);
        final TextView mKelilingSTD = dialogView.findViewById(R.id.mKelilingSTD);
        float persentaseReal = (float) (sumRealBibit / sumIkatSpk) * 100;
        float persentaseNormal = (sumBibitNormal / sumRealBibit) * 100;
        float persentaseAfkir = (sumAfkir / sumRealBibit) * 100;
        float totalBatangbawah = (float) (kelilingBawah.size() / sumRealBibit) * 100;
        float totalBatangAtas = (float) (kelilingAtas.size() / sumRealBibit) * 100;
        Log.d("persentaseReal", String.valueOf(persentaseReal));
        mBawahSTD.setText(df.format(kelilingBatangBawah.size()));
        mAtasSTD.setText(df.format(kelilingBatangAtas.size()));
        mIkatSPK.setText(df.format(persentaseReal));
        mMasukSTD.setText(df.format(persentaseNormal));
        mAfkir.setText(df.format(persentaseAfkir));
        mKelilingBawah.setText(df.format(totalBatangbawah));
        mKelilingAtas.setText(df.format(totalBatangAtas));
        if (persentaseNormal <= 95) {
            mSesuaiSTD.setText("< STD");
        } else
            mSesuaiSTD.setText("STD");

        if (persentaseAfkir <= 5) {
            mAfkirSTD.setText("STD");
        } else
            mAfkirSTD.setText("< STD");


        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Log.d("spiner", String.valueOf(spinnerPoktan.getSelectedItem()));
//                onCreatePoktan();
            }
        });

        dialog.setNegativeButton("Kembali", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
//                goToDashboard();
            }
        });


        dialog.show();
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
    public void onNetworkError(String cause) {
        Log.d("causenya", cause);
//        Toast.makeText(this, cause, Toast.LENGTH_SHORT).show();
        SweetDialogs.endpointError(this);
    }

    @Override
    public void onSelect(HasilPengamatanModel model) {
//        Intent gobajak = new Intent(e_1_list_lahan.this, FormPengamatanPhTanah.class);
//        gobajak.putExtra("model", model);
//        startActivity(gobajak);
    }
}