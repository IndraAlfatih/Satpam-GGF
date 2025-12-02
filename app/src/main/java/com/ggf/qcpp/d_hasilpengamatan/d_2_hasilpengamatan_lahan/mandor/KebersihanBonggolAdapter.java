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


public class KebersihanBonggolAdapter extends RecyclerView.Adapter<KebersihanBonggolAdapter.ViewHolder> {
    public List<HasilPengamatanModel> models;
//    private final BajakAdapter.OnItemSelected listener;
    private String username;
    Activity context;

//    public interface OnItemSelected {
//        void onSelect(HasilPengamatanModel model);
//    }

    public KebersihanBonggolAdapter(List<HasilPengamatanModel> data, Activity context) {
        this.models = data;
        this.context = context;
    }

    public void onPilihAll() {

    }


    @Override
    public KebersihanBonggolAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_hasil_pengamatan_kebersihanbonggol, parent, false);

        KebersihanBonggolAdapter.ViewHolder viewHolder = new KebersihanBonggolAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final KebersihanBonggolAdapter.ViewHolder holder, final int position) {
        final HasilPengamatanModel model = models.get(position);
//        holder.mCount.setText(df.format(position+1));

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        holder.mPlot.setText(model.getPlot());
        holder.mLuasPlot.setText(model.getLUAS_PLOT());
        holder.mNoSample.setText(model.getNo_sample());
        holder.mBonggolSegarLebihDari.setText(df.format(model.getBONGGOL_SEGAR_LEBIH_DARI()));
        holder.mBonggolSegarKurangDari.setText(df.format(model.getBONGGOL_SEGAR_KURANG_DARI()));
        holder.mEstimasi.setText(df.format(model.getESTIMASI()));
//        holder.mAplikasiKerataan.setText(model.getAPLIKASI_KERATAAN());
//        holder.mAplikasiPinggiran.setText(model.getAPLIKASI_PINGGIRAN());
//        holder.mDeadFurrow.setText(model.getDEAD_FURROW());

//
    }

    @Override
    public int getItemCount() {
        return models.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView  mNoSample, mPlot, mLuasPlot, mCreated , mBonggolSegarLebihDari , mBonggolSegarKurangDari, mEstimasi;
        ImageView indicator;

        ViewHolder(View view) {
            super(view);
//            indicator = view.findViewById(R.id.indicator);
            mBonggolSegarKurangDari = view.findViewById(R.id.mBonggolSegarKurangDari);
            mBonggolSegarLebihDari = view.findViewById(R.id.mBonggolSegarLebihDari);
            mNoSample = view.findViewById(R.id.mNoSample);
            mPlot = view.findViewById(R.id.mPlot);
            mLuasPlot = view.findViewById(R.id.mLuasPlot);
            mEstimasi = view.findViewById(R.id.mEstimasi);

        }
    }


}
