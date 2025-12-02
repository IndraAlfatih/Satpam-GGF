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


public class KebersihanTransportAdapter extends RecyclerView.Adapter<KebersihanTransportAdapter.ViewHolder> {
    public List<HasilPengamatanModel> models;
//    private final BajakAdapter.OnItemSelected listener;
    private String username;
    Activity context;

//    public interface OnItemSelected {
//        void onSelect(HasilPengamatanModel model);
//    }

    public KebersihanTransportAdapter(List<HasilPengamatanModel> data, Activity context) {
        this.models = data;
        this.context = context;
    }

    public void onPilihAll() {

    }


    @Override
    public KebersihanTransportAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_hasil_pengamatan_transport, parent, false);

        KebersihanTransportAdapter.ViewHolder viewHolder = new KebersihanTransportAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final KebersihanTransportAdapter.ViewHolder holder, final int position) {
        final HasilPengamatanModel model = models.get(position);
//        holder.mCount.setText(df.format(position+1));

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        holder.mLuasPlot.setText(df.format(Float.parseFloat(model.getLUAS_PLOT())));
        holder.mPlot.setText(model.getPlot());
        holder.mTumpuk.setText(df.format(model.getJumlah_tumpuk()));
        holder.mRataRataTumpuk.setText(df.format(model.getRerata_tumpuk()));
        holder.mGulud.setText(df.format(model.getGulud()));
        holder.mDijalan.setText(df.format(model.getDijalan()));
        holder.mTerlindas.setText(df.format(model.getTerlindas()));



    }


    @Override
    public int getItemCount() {
        return models.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mLuasPlot, mPlot ,mTumpuk,mRataRataTumpuk,mTotalBibitTertinggal,mGulud,mDijalan,mTerlindas,mTotalBibitTersier;
        ImageView indicator;

        ViewHolder(View view) {
            super(view);

            mLuasPlot = view.findViewById(R.id.mLuasPlot);
            mPlot = view.findViewById(R.id.mPlot);
            mTumpuk = view.findViewById(R.id.mTumpuk);
            mRataRataTumpuk = view.findViewById(R.id.mRataRataTumpuk);
            mTotalBibitTertinggal = view.findViewById(R.id.mTotalBibitTertinggal);
            mGulud = view.findViewById(R.id.mGulud);
            mDijalan = view.findViewById(R.id.mDijalan);
            mTerlindas = view.findViewById(R.id.mTerlindas);
            mTotalBibitTersier = view.findViewById(R.id.mTotalBibitTersier);


        }
    }


}
