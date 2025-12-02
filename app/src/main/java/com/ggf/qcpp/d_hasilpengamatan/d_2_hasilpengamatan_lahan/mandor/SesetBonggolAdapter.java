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


public class SesetBonggolAdapter extends RecyclerView.Adapter<SesetBonggolAdapter.ViewHolder> {
    public List<HasilPengamatanModel> models;
    //    private final BajakAdapter.OnItemSelected listener;
    private String username;
    Activity context;

//    public interface OnItemSelected {
//        void onSelect(HasilPengamatanModel model);
//    }

    public SesetBonggolAdapter(List<HasilPengamatanModel> data, Activity context) {
        this.models = data;
        this.context = context;
    }

    public void onPilihAll() {

    }


    @Override
    public SesetBonggolAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_hasil_pengamatan_bonggolterseset, parent, false);

        SesetBonggolAdapter.ViewHolder viewHolder = new SesetBonggolAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final SesetBonggolAdapter.ViewHolder holder, final int position) {
        final HasilPengamatanModel model = models.get(position);
//        holder.mCount.setText(df.format(position+1));

        float pencapianKupasan = model.getJumlah_sample_masuk_standar_kebersihan_kupasan() / model.getJumlah_sample() * 100 ;
        float pencapianPotongan = model.getJumlah_sample_masuk_standar_potongan_bonggol() / model.getJumlah_sample() * 100 ;
        float pencapianKondisi = model.getJumlah_sample_masuk_standar_kondisi_bonggol() / model.getJumlah_sample() * 100 ;
        float pencapianMuatan = model.getJumlah_sample_masuk_standar_kondisi_bin() / model.getJumlah_sample() * 100 ;
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        holder.mPlot.setText(model.getPlot());
        holder.mLuasPlot.setText(model.getLUAS_PLOT());
        holder.mNoSample.setText(model.getNo_sample());
        holder.mSampleBonggol.setText(String.valueOf(model.getJumlah_sample()));
        holder.mKetinggianSampah.setText(df.format(model.getKetinggian_sampah()));
        holder.mKupasanRata.setText(df.format(model.getRerata_panjang_bonggol()));
        if (model.getKetinggian_sampah() <= 35)
            holder.mPencapaian.setText("100 %");
        else
            holder.mPencapaian.setText("0 %");
//        holder.mPencapaian.setText(model.getPenca());
        holder.mKupasanSTD.setText(df.format(model.getJumlah_sample_masuk_standar_kebersihan_kupasan()));
        holder.mPencapaianKupasan.setText(df.format(pencapianKupasan));

//        holder.mKupasanRata.setText(model.getKupasanRata());
//        holder.mPencapaianKupasan.setText(model.getPencapaianKupasan());
        holder.mPotonganSTD.setText(df.format(model.getJumlah_sample_masuk_standar_potongan_bonggol()));
        holder.mPencapaianPotongan.setText(df.format(pencapianPotongan));
        holder.mKondisiSTD.setText(df.format(model.getJumlah_sample_masuk_standar_kondisi_bonggol()));
        holder.mPencapaianKondisi.setText(df.format(pencapianKondisi));
        holder.mMuatan.setText(df.format(model.getJumlah_sample_masuk_standar_kondisi_bin()));
        holder.mPencapianMuatan.setText(df.format(pencapianMuatan));


    }

    @Override
    public int getItemCount() {
        return models.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mPencapianMuatan,mSampleBonggol, mNoSample, mPlot, mLuasPlot, mKetinggianSampah, mPencapaian, mKupasanSTD, mKupasanRata, mPencapaianKupasan, mPotonganSTD, mPencapaianPotongan, mKondisiSTD, mPencapaianKondisi, mMuatan;
        ImageView indicator;

        ViewHolder(View view) {
            super(view);
//            indicator = view.findViewById(R.id.indicator);
//            mBonggolSegarKurangDari = view.findViewById(R.id.mBonggolSegarKurangDari);
//            mBonggolSegarLebihDari = view.findViewById(R.id.mBonggolSegarLebihDari);
            mNoSample = view.findViewById(R.id.mNoSample);
            mPlot = view.findViewById(R.id.mPlot);
            mLuasPlot = view.findViewById(R.id.mLuasPlot);
            mKetinggianSampah = view.findViewById(R.id.mKetinggianSampah);
            mPencapaian = view.findViewById(R.id.mPencapaianSampah);
            mKupasanSTD = view.findViewById(R.id.mKupasanSTD);
            mKupasanRata = view.findViewById(R.id.mKupasanRata);
            mPencapaianKupasan = view.findViewById(R.id.mPencapaianKupasan);
            mPotonganSTD = view.findViewById(R.id.mPotonganSTD);
            mPencapaianPotongan = view.findViewById(R.id.mPencapaianPotongan);
            mKondisiSTD = view.findViewById(R.id.mKondisiSTD);
            mPencapaianKondisi = view.findViewById(R.id.mPencapaianKondisi);
            mMuatan = view.findViewById(R.id.mMuatan);
            mSampleBonggol = view.findViewById(R.id.mSampleBonggol);
            mPencapianMuatan = view.findViewById(R.id.mPencapianMuatan);
//            mEstimasi = view.findViewById(R.id.mEstimasi);

        }
    }


}
