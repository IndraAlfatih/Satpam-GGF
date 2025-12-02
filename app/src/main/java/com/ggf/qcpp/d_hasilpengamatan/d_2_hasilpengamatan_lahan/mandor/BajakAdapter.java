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


public class BajakAdapter extends RecyclerView.Adapter<BajakAdapter.ViewHolder> {
    public List<HasilPengamatanModel> models;
//    private final BajakAdapter.OnItemSelected listener;
    private String username;
    Activity context;

//    public interface OnItemSelected {
//        void onSelect(HasilPengamatanModel model);
//    }

    public BajakAdapter(List<HasilPengamatanModel> data, Activity context) {
        this.models = data;
        this.context = context;
    }

    public void onPilihAll() {

    }


    @Override
    public BajakAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_hasil_pengamatan_bajak, parent, false);

        BajakAdapter.ViewHolder viewHolder = new BajakAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final BajakAdapter.ViewHolder holder, final int position) {
        final HasilPengamatanModel model = models.get(position);
//        holder.mCount.setText(df.format(position+1));
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        holder.mKet.setText(model.getKeterangan());
//        holder.mKedalaman.setText(df.format(model.getKEDALAMAN()));
//        holder.mAplikasiKerataan.setText(df.format(model.getAPLIKASI_KERATAAN()));
        holder.mKedalaman.setText(String.valueOf(model.getKEDALAMAN()));
        holder.mAplikasiKerataan.setText(String.valueOf(model.getAPLIKASI_KERATAAN()));
        holder.mAplikasiPinggiran.setText(String.valueOf(model.getAPLIKASI_PINGGIRAN()));
        holder.mDeadFurrow.setText(String.valueOf(model.getDEAD_FURROW()));
        holder.LolosAyakan.setText(String.valueOf(model.getLolos_ayakan()));
        holder.TidakLolosAyakan.setText(String.valueOf(model.getTidak_lolos_ayakan()));


//        holder.LolosAyakan.setText(
//                model.getLolos_ayakan() == null ? "-" : String.valueOf(model.getLolos_ayakan())
//        );
//
//        holder.TidakLolosAyakan.setText(
//                model.getTidak_lolos_ayakan() == null ? "-" : String.valueOf(model.getTidak_lolos_ayakan())
//        );


//        holder.LolosAyakan.setText(model.getLolos_ayakan());
//        holder.TidakLolosAyakan.setText(model.getTidak_lolos_ayakan());
//        holder.LolosAyakan.setText(df.format(Float.parseFloat(String.valueOf(model.getLolos_ayakan()))));
//        holder.TidakLolosAyakan.setText(df.format(Float.parseFloat(String.valueOf(model.getTidak_lolos_ayakan()))));


//        holder.mLolosAyakan.setText(df.format(Float.parseFloat(model.getLOLOS_AYAKAN())));
//        holder.mTidakLolosAyakan.setText(df.format(Float.parseFloat(model.getTIDAK_LOLOS_AYAKAN())));
//        holder.mAplikasiKerataan.setText(model.getAPLIKASI_KERATAAN());
//        holder.mAplikasiPinggiran.setText(model.getAPLIKASI_PINGGIRAN());
//        holder.mDeadFurrow.setText(model.getDEAD_FURROW());
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

//        holder.itemView.setOnClickListener(view -> listener.onSelect(model));
    }


    @Override
    public int getItemCount() {
        return models.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mKedalaman, mNoSample, mPlot, mCreated , mDeadFurrow , mAplikasiPinggiran,mAplikasiKerataan,mKet, LolosAyakan, TidakLolosAyakan;
        ImageView indicator;

        ViewHolder(View view) {
            super(view);
//            indicator = view.findViewById(R.id.indicator);
            mKedalaman = view.findViewById(R.id.mKedalaman);
            mDeadFurrow = view.findViewById(R.id.mDeadFurrow);
            mNoSample = view.findViewById(R.id.mNoSample);
            mPlot = view.findViewById(R.id.mPlot);
            mAplikasiKerataan = view.findViewById(R.id.mAplikasiKerataan);
            mKet = view.findViewById(R.id.mKet);
            mAplikasiPinggiran = view.findViewById(R.id.mAplikasiPinggiran);

            LolosAyakan = view.findViewById(R.id.mLolosAyakan);
            TidakLolosAyakan = view.findViewById(R.id.mTidakLolosAyakan);


        }
    }


}
