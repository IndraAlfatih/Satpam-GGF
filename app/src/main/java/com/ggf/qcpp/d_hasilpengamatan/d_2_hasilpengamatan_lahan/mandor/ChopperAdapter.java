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


public class ChopperAdapter extends RecyclerView.Adapter<ChopperAdapter.ViewHolder> {
    public List<HasilPengamatanModel> models;
    private final ChopperAdapter.OnItemSelected listener;
    private String username;
    Activity context;

    public interface OnItemSelected {
        void onSelect(HasilPengamatanModel model);
    }

    public ChopperAdapter(List<HasilPengamatanModel> data, Activity context, OnItemSelected listener) {
        this.models = data;
        this.context = context;
        this.listener = listener;
    }

    public void onPilihAll() {

    }


    @Override
    public ChopperAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_hasil_pengamatan_chopper, parent, false);

        ChopperAdapter.ViewHolder viewHolder = new ChopperAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ChopperAdapter.ViewHolder holder, final int position) {
        final HasilPengamatanModel model = models.get(position);
//        holder.mCount.setText(String.valueOf(position+1));
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        holder.mBonggol.setText(df.format(Float.parseFloat(model.getBonggol_terpecah())));
        holder.mAplikasiRapat.setText(df.format(Float.parseFloat(model.getAplikasi_rapat())));
        holder.mKet.setText(model.getKeterangan());
        holder.mTanamanHancur.setText(df.format(Float.parseFloat(model.getTanaman_hancur())));
        holder.mPlot.setText("Plot "+model.getPlot());

//        if(model.getVerify_mandor()!= 0){
//            if(model.getVerify_mandor() == 1){
//                holder.indicator.setImageResource(R.drawable.shape_indicator_active);
//                holder.mStatusVerif.setText("Disetujui");
//            }else{
//                holder.indicator.setImageResource(R.drawable.shape_indicator_unactive);
//                holder.mStatusVerif.setText("Ditolak");
//            }
//        }else {
//            holder.indicator.setImageResource(R.drawable.shape_indicator_orange);
//            holder.mStatusVerif.setText("Pending");
//        }
//        holder.mCreated.setText(model.getCreated_at());
        holder.mNoSample.setText(model.getNo_sample());

//        holder.mToko.setText(rut.getKios());

        holder.itemView.setOnClickListener(view -> listener.onSelect(model));
    }


    @Override
    public int getItemCount() {
        return models.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mBonggol, mNoSample, mPlot, mCreated , mAplikasiRapat , mTanamanHancur, mKet;
        ImageView indicator;

        ViewHolder(View view) {
            super(view);
//            indicator = view.findViewById(R.id.indicator);
            mTanamanHancur = view.findViewById(R.id.mTanamanHancur);
            mBonggol = view.findViewById(R.id.mBonggol);
            mNoSample = view.findViewById(R.id.mNoSample);
            mPlot = view.findViewById(R.id.mPlot);
            mKet = view.findViewById(R.id.mKet);
//            mCreated = view.findViewById(R.id.mCreated);
            mAplikasiRapat = view.findViewById(R.id.mAplikasiRapat);

        }
    }


}
