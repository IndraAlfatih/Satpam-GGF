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


public class PoolDippingAdapter extends RecyclerView.Adapter<PoolDippingAdapter.ViewHolder> {
    public List<HasilPengamatanModel> models;
//    private final BajakAdapter.OnItemSelected listener;
    private String username;
    Activity context;

//    public interface OnItemSelected {
//        void onSelect(HasilPengamatanModel model);
//    }

    public PoolDippingAdapter(List<HasilPengamatanModel> data, Activity context) {
        this.models = data;
        this.context = context;
    }

    public void onPilihAll() {

    }


    @Override
    public PoolDippingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_hasil_pengamatan_pooldipping, parent, false);

        PoolDippingAdapter.ViewHolder viewHolder = new PoolDippingAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final PoolDippingAdapter.ViewHolder holder, final int position) {
        final HasilPengamatanModel model = models.get(position);
//        holder.mCount.setText(df.format(position+1));

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
//        holder.mPlot.setText(df.format(model.getPlot()));
        holder.mPlot.setText(model.getPlot());
        holder.mNoSample.setText(model.getNo_sample());
        holder.mNormal.setText(df.format(model.getBibit_normal()));
        holder.mAfkir.setText(df.format(model.getBibit_afkir()));
        holder.mJumlahSample.setText(df.format(model.getJumlah_sample()));
        holder.mHasil.setText(df.format(model.getHasil()));
        holder.mJenisBibit.setText(model.getJenis_bibit());
        holder.mKelasBibit.setText(model.getKelas_bibit());
        holder.mNomorBibit.setText(model.getNomor_bibit());
        holder.mJenisUnit.setText(model.getJenis_unit());
        holder.mKet.setText(model.getKeterangan());
        holder.mAsalDo.setText(model.getAsal_do());
        holder.mKeteranganDO.setText(model.getTujuan_do());
        holder.mInforTerdipping.setText(model.getInformasi_bibit_terdipping());
        holder.mKendaraan.setText(model.getNo_kendaraan());
        holder.mOverPlus.setText(df.format(model.getBibit_over_plus()));
        holder.mOver.setText(df.format(model.getBibit_over()));
        holder.m1.setText(df.format(model.getBibit_1()));
        holder.m2.setText(df.format(model.getBibit_2()));
        holder.m3.setText(df.format(model.getBibit_3()));
        holder.m4.setText(df.format(model.getBibit_4()));
        holder.m5.setText(df.format(model.getBibit_5()));
        holder.m6.setText(df.format(model.getBibit_6()));
        holder.m7.setText(df.format(model.getBibit_7()));


    }


    @Override
    public int getItemCount() {
        return models.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mNoSample, mPlot, mNormal , mAfkir , mReal,mOverPlus , mOver , m1,m2,m3,m4,m5,m6,m7;
        TextView mInforTerdipping, mJumlahSample, mHasil, mKendaraan, mJenisUnit , mAsalDo , mTujuanDo,mKelasBibit ,mKeteranganDO , mJenisBibit, mNomorBibit, mKet;
        ImageView indicator;

        ViewHolder(View view) {
            super(view);

            mInforTerdipping = view.findViewById(R.id.mInforTerdipping);
            mPlot = view.findViewById(R.id.mPlot);
            mNormal = view.findViewById(R.id.mNormal);
            mNoSample = view.findViewById(R.id.mNoSample);
            mJumlahSample = view.findViewById(R.id.mJumlahSample);
            mHasil = view.findViewById(R.id.mHasil);
            mAfkir = view.findViewById(R.id.mAfkir);
            mNomorBibit = view.findViewById(R.id.autoNoBibit);
            mAsalDo = view.findViewById(R.id.mAsalDO);
            mKendaraan = view.findViewById(R.id.mKendaraan);
            mKelasBibit = view.findViewById(R.id.mKelasBibit);
            mKet = view.findViewById(R.id.mKet);
            mJenisUnit = view.findViewById(R.id.mJenisUnit);
            mJenisBibit = view.findViewById(R.id.mJenisBibit);
            mKeteranganDO = view.findViewById(R.id.mKeteranganDO);
            mOverPlus = view.findViewById(R.id.mOverPlus);
            mOver = view.findViewById(R.id.mOver);
            m1 = view.findViewById(R.id.m1);
            m2 = view.findViewById(R.id.m2);
            m3 = view.findViewById(R.id.m3);
            m4 = view.findViewById(R.id.m4);
            m5 = view.findViewById(R.id.m5);
            m6 = view.findViewById(R.id.m6);
            m7 = view.findViewById(R.id.m7);

        }
    }


}
