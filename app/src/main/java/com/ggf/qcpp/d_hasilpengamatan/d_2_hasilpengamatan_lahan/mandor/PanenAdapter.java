package com.ggf.qcpp.d_hasilpengamatan.d_2_hasilpengamatan_lahan.mandor;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ggf.qcpp.R;
import com.ggf.qcpp.d_hasilpengamatan.d_2_hasilpengamatan_lahan.mandor.model.HasilPengamatanModel;

import java.text.DecimalFormat;
import java.util.List;


public class PanenAdapter extends RecyclerView.Adapter<PanenAdapter.ViewHolder> {
    public List<HasilPengamatanModel> models;
    //    private final BajakAdapter.OnItemSelected listener;
    private String username;
    Activity context;

//    public interface OnItemSelected {
//        void onSelect(HasilPengamatanModel model);
//    }

    public PanenAdapter(List<HasilPengamatanModel> data, Activity context) {
        this.models = data;
        this.context = context;
    }

    public void onPilihAll() {

    }


    @Override
    public PanenAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_hasil_pengamatan_panen, parent, false);

        PanenAdapter.ViewHolder viewHolder = new PanenAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final PanenAdapter.ViewHolder holder, final int position) {
        final HasilPengamatanModel model = models.get(position);

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        holder.mPlot.setText(model.getPlot());
        holder.mLuasPlot.setText(model.getLUAS_PLOT());
        holder.mReguPanen.setText(model.getRegu_panen());
        holder.mLokasi.setText(model.getLokasi());
//        holder.mTidakCrown.setText(model.getTidak_crown());
        holder.mPanjangPengamatan.setText(df.format(model.getPanjang_pengamatan()));
        holder.mWil.setText(model.getWil());
        holder.mShift.setText(model.getShift());
        holder.mJalur.setText(model.getJalur());
        holder.mStatusLokasi.setText(model.getStatuslokasi());
        holder.mKet.setText(model.getKeterangan());
        holder.mStatusPengamatan.setText(model.getStatus_pengamatan());

        holder.mNormalBuahTertinggalBesar.setText(df.format(model.getNormal_buah_tertinggal_besar()));
        holder.mNormalBuahTertinggalSedang.setText(df.format(model.getNormal_buah_tertinggal_sedang()));
        holder.mNormalBuahTertinggalKecil.setText(df.format(model.getNormal_buah_tertinggal_kecil()));

        holder.mSekunderBuahTertinggalBesar.setText(df.format(model.getSekunder_buah_tertinggal_besar()));
        holder.mSekunderBuahTertinggalSedang.setText(df.format(model.getSekunder_buah_tertinggal_sedang()));
        holder.mSekunderBuahTertinggalKecil.setText(df.format(model.getSekunder_buah_tertinggal_kecil()));

        holder.mJumlahSalSekunder.setText(df.format(model.getJumlah_sal_sekunder()));
        holder.mJumlahTitikPengamatan.setText(df.format(model.getJumlah_titik_pengamatan()));
        holder.mTersierBuahTertinggalBesar.setText(df.format(model.getTersier_buah_tertinggal_besar()));
        holder.mTersierBuahTertinggalSedang.setText(df.format(model.getTersier_buah_tertinggal_sedang()));
        holder.mTersierBuahTertinggalKecil.setText(df.format(model.getTersier_buah_tertinggal_kecil()));
        holder.mJumlahSalTersier.setText(df.format(model.getJumlah_sal_tersier()));
        holder.mJumlahTitikDiamati.setText(df.format(model.getJumlah_titik_diamati()));
        holder.mJumlahBaris.setText(df.format(model.getJumlah_baris()));
        holder.mTotalCrown.setText(df.format(model.getTotal_crown()));
        holder.mCrownNormal.setText(model.getCrownnormal());
        holder.mCrownKipas.setText(model.getCrownkipas());
        holder.mCrownBusukNormal.setText(model.getCrownbusuknormal());
        holder.mCrownBusukTidakNormal.setText(model.getCrownbusuktidaknormal());

    }



    @Override
    public int getItemCount() {
        return models.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mPlot, mLuasPlot, mReguPanen, mLokasi, mPanjangPengamatan, mWil, mShift, mJalur,
                mStatusLokasi, mStatusPengamatan,mTidakCrown,
                mNormalBuahTertinggalBesar, mNormalBuahTertinggalSedang, mNormalBuahTertinggalKecil,
                mSekunderBuahTertinggalBesar, mSekunderBuahTertinggalSedang, mSekunderBuahTertinggalKecil,
                mJumlahSalSekunder, mJumlahTitikPengamatan,mTersierBuahTertinggalBesar,mTersierBuahTertinggalSedang,mTersierBuahTertinggalKecil,
                mJumlahSalTersier,mJumlahTitikDiamati,mJumlahBaris,mTotalCrown,mCrownNormal,mCrownKipas,mCrownBusukNormal,mCrownBusukTidakNormal, mKet;



        ViewHolder(View view) {
            super(view);
            mPlot = view.findViewById(R.id.mPlot);
            mLuasPlot = view.findViewById(R.id.mLuasPlot);
            mTidakCrown = view.findViewById(R.id.mTidakCrown);
            mReguPanen = view.findViewById(R.id.mReguPanen);
            mLokasi = view.findViewById(R.id.mLokasi);
            mPanjangPengamatan = view.findViewById(R.id.mPjgPengamatan);
            mWil = view.findViewById(R.id.mWil);
            mShift = view.findViewById(R.id.mShift);
            mJalur = view.findViewById(R.id.mJalur);
            mKet = view.findViewById(R.id.mKet);
            mStatusLokasi = view.findViewById(R.id.mStatusLokasi);
            mStatusPengamatan = view.findViewById(R.id.mStatusPengamatan);
            mNormalBuahTertinggalBesar = view.findViewById(R.id.mNormalBuahTertinggalBesar);
            mNormalBuahTertinggalSedang = view.findViewById(R.id.mNormalBuahTertinggalSedang);
            mNormalBuahTertinggalKecil = view.findViewById(R.id.mNormalBuahTertinggalKecil);
            mSekunderBuahTertinggalBesar = view.findViewById(R.id.mSekunderBuahTertinggalBesar);
            mSekunderBuahTertinggalSedang = view.findViewById(R.id.mSekunderBuahTertinggalSedang);
            mSekunderBuahTertinggalKecil = view.findViewById(R.id.mSekunderBuahTertinggalKecil);
            mJumlahSalSekunder = view.findViewById(R.id.mJumlahSalSekunder);
            mJumlahTitikPengamatan = view.findViewById(R.id.mJumlahTitikPengamatan);
            mTersierBuahTertinggalBesar = view.findViewById(R.id.mTersierBuahTertinggalBesar);
            mTersierBuahTertinggalSedang = view.findViewById(R.id.mTersierBuahTertinggalSedang);
            mTersierBuahTertinggalKecil = view.findViewById(R.id.mTersierBuahTertinggalKecil);
            mJumlahSalTersier = view.findViewById(R.id.mJumlahSalTersier);
            mJumlahTitikDiamati = view.findViewById(R.id.mJumlahTitikDiamati);
            mJumlahBaris = view.findViewById(R.id.mJumlahBaris);
            mTotalCrown = view.findViewById(R.id.mTotalCrown);
            mCrownNormal = view.findViewById(R.id.mCrownNormal);
            mCrownKipas = view.findViewById(R.id.mCrownKipas);
            mCrownBusukNormal = view.findViewById(R.id.mCrownBusukNormal);
            mCrownBusukTidakNormal = view.findViewById(R.id.mCrownBusukTidakNormal);

        }
    }



}
