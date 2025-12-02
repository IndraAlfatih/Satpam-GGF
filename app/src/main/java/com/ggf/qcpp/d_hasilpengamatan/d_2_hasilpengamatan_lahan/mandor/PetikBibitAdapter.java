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


public class PetikBibitAdapter extends RecyclerView.Adapter<PetikBibitAdapter.ViewHolder> {
    public List<HasilPengamatanModel> models;
//    private final BajakAdapter.OnItemSelected listener;
    private String username;
    Activity context;

//    public interface OnItemSelected {
//        void onSelect(HasilPengamatanModel model);
//    }

    public PetikBibitAdapter(List<HasilPengamatanModel> data, Activity context) {
        this.models = data;
        this.context = context;
    }

    public void onPilihAll() {

    }


    @Override
    public PetikBibitAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_hasil_pengamatan_petikbibit, parent, false);

        PetikBibitAdapter.ViewHolder viewHolder = new PetikBibitAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final PetikBibitAdapter.ViewHolder holder, final int position) {
        final HasilPengamatanModel model = models.get(position);
//        holder.mCount.setText(df.format(position+1));
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        holder.mNoSample.setText(model.getNo_sample());
        holder.mPlot.setText(model.getPlot());
        holder.mNormal.setText(df.format(model.getBibit_normal()));
        holder.mAfkir.setText(df.format(model.getBibit_afkir()));
        holder.mReal.setText(df.format(model.getReal()));
        holder.mLabel.setText(model.getLabel());
//        holder.mLabel.setText(df.format(model.getLabel()));
        holder.mKet.setText(model.getKeterangan());
        holder.m1.setText(df.format(model.getBibit_1()));
        holder.m2.setText(df.format(model.getBibit_2()));
        holder.m3.setText(df.format(model.getBibit_3()));
        holder.m4.setText(df.format(model.getBibit_4()));
        holder.m5.setText(df.format(model.getBibit_5()));
        holder.m6.setText(df.format(model.getBibit_6()));
//        holder.mNomorBibit.setText(model.getNomor_bibit());
        holder.m7.setText(df.format(model.getBibit_7()));
        holder.mOver.setText(df.format(model.getBibit_over()));
        holder.mOverPlus.setText(df.format(model.getBibit_over_plus()));


    }


    @Override
    public int getItemCount() {
        return models.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mNoSample,mNomorBibit, mPlot, mNormal , mAfkir, mLabel, mReal,mOverPlus , mOver , m1,m2,m3,m4,m5,m6,m7, mKet;
        ImageView indicator;

        ViewHolder(View view) {
            super(view);

            mNoSample = view.findViewById(R.id.mNoSample);
            mPlot = view.findViewById(R.id.mPlot);
            mNormal = view.findViewById(R.id.mNormal);
            mAfkir = view.findViewById(R.id.mAfkir);
            mReal = view.findViewById(R.id.mReal);
            mKet = view.findViewById(R.id.mKet);
            mLabel = view.findViewById(R.id.mLabel);
//            mNomorBibit = view.findViewById(R.id.autoNoBibit);
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
