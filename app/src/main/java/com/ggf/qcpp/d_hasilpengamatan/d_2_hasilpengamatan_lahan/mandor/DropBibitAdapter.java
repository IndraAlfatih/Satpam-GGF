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


public class DropBibitAdapter extends RecyclerView.Adapter<DropBibitAdapter.ViewHolder> {
    public List<HasilPengamatanModel> models;
//    private final BajakAdapter.OnItemSelected listener;
    private String username;
    Activity context;

//    public interface OnItemSelected {
//        void onSelect(HasilPengamatanModel model);
//    }

    public DropBibitAdapter(List<HasilPengamatanModel> data, Activity context) {
        this.models = data;
        this.context = context;
    }

    public void onPilihAll() {

    }


    @Override
    public DropBibitAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_hasil_pengamatan_dropbibit, parent, false);

        DropBibitAdapter.ViewHolder viewHolder = new DropBibitAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final DropBibitAdapter.ViewHolder holder, final int position) {
        final HasilPengamatanModel model = models.get(position);
//        holder.mCount.setText(df.format(position+1));
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        holder.mNoSample.setText(model.getNo_sample());
        holder.mPlot.setText(model.getPlot());
        holder.mNormal.setText(df.format(model.getBibit_normal()));
        holder.mAfkir.setText(df.format(model.getBibit_afkir()));
        holder.mKet.setText(model.getKeterangan());
        holder.mJumlahBibitPertumpuk.setText(df.format(model.getJumlah_bibit_tertumpuk()));
        holder.mOverPlus.setText(df.format(model.getBibit_over_plus()));
        holder.mOver.setText(df.format(model.getBibit_over()));
        holder.mNomorBibit.setText(model.getNomor_bibit());
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
        TextView mNoSample, mPlot, mNormal , mAfkir , mJumlahBibitPertumpuk,mOverPlus , mOver , m1,m2,m3,m4,m5,m6,m7, mNomorBibit, mKet;
        ImageView indicator;

        ViewHolder(View view) {
            super(view);

            mNoSample = view.findViewById(R.id.mNoSample);
            mPlot = view.findViewById(R.id.mPlot);
            mNormal = view.findViewById(R.id.mNormal);
            mAfkir = view.findViewById(R.id.mAfkir);
            mNomorBibit = view.findViewById(R.id.autoNoBibit);
            mJumlahBibitPertumpuk = view.findViewById(R.id.mJumlahBibitPertumpuk);
            mKet = view.findViewById(R.id.mKet);
            mOverPlus = view.findViewById(R.id.mOverPlus);
            mOver = view.findViewById(R.id.mOver);
            m1 = view.findViewById(R.id.mPetik1);
            m2 = view.findViewById(R.id.mPetik2);
            m3 = view.findViewById(R.id.mPetik3);
            m4 = view.findViewById(R.id.mPetik4);
            m5 = view.findViewById(R.id.mPetik5);
            m6 = view.findViewById(R.id.mPetik6);
            m7 = view.findViewById(R.id.mPetik7);

        }
    }


}
