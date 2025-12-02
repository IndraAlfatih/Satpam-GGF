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


public class phTanahAdapter extends RecyclerView.Adapter<phTanahAdapter.ViewHolder> {
    public List<HasilPengamatanModel> models;
//    private final BajakAdapter.OnItemSelected listener;
    private String username;
    Activity context;

//    public interface OnItemSelected {
//        void onSelect(HasilPengamatanModel model);
//    }

    public phTanahAdapter(List<HasilPengamatanModel> data, Activity context) {
        this.models = data;
        this.context = context;
    }

    public void onPilihAll() {

    }


    @Override
    public phTanahAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_hasil_pengamatan_phtanah, parent, false);

        phTanahAdapter.ViewHolder viewHolder = new phTanahAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final phTanahAdapter.ViewHolder holder, final int position) {
        final HasilPengamatanModel model = models.get(position);
//        holder.mCount.setText(df.format(position+1));
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        holder.mAnalisaA1.setText(df.format(model.getANALISA_A1()));
        holder.mAnalisaA2.setText(df.format(model.getANALISA_A2()));
        holder.mAnalisaA3.setText(df.format(model.getANALISA_A3()));
        holder.mAnalisaA4.setText(df.format(model.getANALISA_A4()));
//        holder.mAplikasiKerataan.setText(model.getAPLIKASI_KERATAAN());
//        holder.mAplikasiPinggiran.setText(model.getAPLIKASI_PINGGIRAN());
//        holder.mDeadFurrow.setText(model.getDEAD_FURROW());
        holder.mPlot.setText(model.getPlot());
        holder.mNoSample.setText(model.getNo_sample());

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

//        holder.itemView.setOnClickListener(view -> listener.onSelect(model));
    }


    @Override
    public int getItemCount() {
        return models.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView  mNoSample, mPlot, mCreated , mAnalisaA1 , mAnalisaA2,mAnalisaA3,mAnalisaA4;
        ImageView indicator;

        ViewHolder(View view) {
            super(view);
//            indicator = view.findViewById(R.id.indicator);
            mAnalisaA1 = view.findViewById(R.id.mAnalisaA1);
            mAnalisaA2 = view.findViewById(R.id.mAnalisaA2);
            mNoSample = view.findViewById(R.id.mNoSample);
            mPlot = view.findViewById(R.id.mPlot);
            mAnalisaA3 = view.findViewById(R.id.mAnalisaA3);
            mAnalisaA4 = view.findViewById(R.id.mAnalisaA4);

        }
    }


}
