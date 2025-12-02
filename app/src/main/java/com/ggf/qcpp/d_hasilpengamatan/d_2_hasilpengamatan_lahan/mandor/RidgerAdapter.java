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

import java.util.List;


public class RidgerAdapter extends RecyclerView.Adapter<RidgerAdapter.ViewHolder> {
    public List<HasilPengamatanModel> models;
//    private final BajakAdapter.OnItemSelected listener;
    private String username;
    Activity context;

//    public interface OnItemSelected {
//        void onSelect(HasilPengamatanModel model);
//    }

    public RidgerAdapter(List<HasilPengamatanModel> data, Activity context) {
        this.models = data;
        this.context = context;
    }

    public void onPilihAll() {

    }


    @Override
    public RidgerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_hasil_pengamatan_ridger, parent, false);

        RidgerAdapter.ViewHolder viewHolder = new RidgerAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RidgerAdapter.ViewHolder holder, final int position) {
        final HasilPengamatanModel model = models.get(position);
//        holder.mCount.setText(String.valueOf(position+1));

        holder.mJarakPorosGulud.setText(String.valueOf(model.getJARAK_ANTAR_POROS_GULUD()));
        holder.mKedalamanKukuRidger.setText(String.valueOf(model.getKEDALAMAN_KUKU_RIDGER()));

        holder.mPlot.setText(model.getPlot());

        holder.mNoSample.setText(model.getNo_sample());

//        holder.mToko.setText(rut.getKios());

//        holder.itemView.setOnClickListener(view -> listener.onSelect(model));
    }


    @Override
    public int getItemCount() {
        return models.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mKedalamanKukuRidger, mNoSample, mPlot, mCreated , mJarakPorosGulud ;
        ImageView indicator;

        ViewHolder(View view) {
            super(view);
//            indicator = view.findViewById(R.id.indicator);
            mKedalamanKukuRidger = view.findViewById(R.id.mKedalamanKukuRidger);
            mJarakPorosGulud = view.findViewById(R.id.mJarakPorosGulud);
            mNoSample = view.findViewById(R.id.mNoSample);
            mPlot = view.findViewById(R.id.mPlot);


        }
    }


}
