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


public class BonggolTidakTersesetAdapter extends RecyclerView.Adapter<BonggolTidakTersesetAdapter.ViewHolder> {
    public List<HasilPengamatanModel> models;
    //    private final BajakAdapter.OnItemSelected listener;
    private String username;
    Activity context;

//    public interface OnItemSelected {
//        void onSelect(HasilPengamatanModel model);
//    }

    public BonggolTidakTersesetAdapter(List<HasilPengamatanModel> data, Activity context) {
        this.models = data;
        this.context = context;
    }

    public void onPilihAll() {

    }


    @Override
    public BonggolTidakTersesetAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_hasil_pengamatan_bonggoltidakterseset, parent, false);

        BonggolTidakTersesetAdapter.ViewHolder viewHolder = new BonggolTidakTersesetAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final BonggolTidakTersesetAdapter.ViewHolder holder, final int position) {
        final HasilPengamatanModel model = models.get(position);
//        holder.mCount.setText(df.format(position+1));


        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        holder.mPlot.setText(model.getPlot());
        holder.mLuasPlot.setText(model.getLUAS_PLOT());
        holder.mNoSample.setText(model.getNo_sample());
        holder.mKet.setText(model.getKeterangan());
        holder.m1.setText(String.valueOf(model.getJumlah_panjang_bonggol_kurang_dari_15()));
        holder.m2.setText(String.valueOf(model.getJumlah_panjang_bonggol_15_sampai_19()));
        holder.m3.setText(String.valueOf(model.getJumlah_panjang_bonggol_20_sampai_22()));
        holder.m4.setText(String.valueOf(model.getJumlah_panjang_bonggol_23_sampai_25()));
        holder.m5.setText(String.valueOf(model.getJumlah_panjang_bonggol_26_sampai_28()));
        holder.m6.setText(String.valueOf(model.getJumlah_panjang_bonggol_29_sampai_31()));
        holder.m7.setText(String.valueOf(model.getJumlah_panjang_bonggol_lebih_dari_31()));
        holder.mJumSucker.setText(String.valueOf(model.getKeterangan_jumlah_sucker()));



    }

    @Override
    public int getItemCount() {
        return models.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mNoSample, mPlot, mLuasPlot, m1 , m2 , m3, m4 ,m5 ,m6,m7,mJumSucker, mKet;
        ImageView indicator;

        ViewHolder(View view) {
            super(view);
//            indicator = view.findViewById(R.id.indicator);
//            mBonggolSegarKurangDari = view.findViewById(R.id.mBonggolSegarKurangDari);
//            mBonggolSegarLebihDari = view.findViewById(R.id.mBonggolSegarLebihDari);
            mNoSample = view.findViewById(R.id.mNoSample);
            mPlot = view.findViewById(R.id.mPlot);
            mLuasPlot = view.findViewById(R.id.mLuasPlot);
            m1 = view.findViewById(R.id.m1);
            m2 = view.findViewById(R.id.m2);
            m3 = view.findViewById(R.id.m3);
            m4 = view.findViewById(R.id.m4);
            m5 = view.findViewById(R.id.m5);
            mKet = view.findViewById(R.id.mKet);
            m6 = view.findViewById(R.id.m6);
            m7 = view.findViewById(R.id.m7);
            mJumSucker = view.findViewById(R.id.mJumSucker);

//            mEstimasi = view.findViewById(R.id.mEstimasi);

        }
    }


}
