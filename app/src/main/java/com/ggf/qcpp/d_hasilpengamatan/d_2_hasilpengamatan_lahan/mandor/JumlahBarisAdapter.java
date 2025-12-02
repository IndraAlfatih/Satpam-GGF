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


public class JumlahBarisAdapter extends RecyclerView.Adapter<JumlahBarisAdapter.ViewHolder> {
    public List<HasilPengamatanModel> models;
//    private final BajakAdapter.OnItemSelected listener;
    private String username;
    Activity context;

//    public interface OnItemSelected {
//        void onSelect(HasilPengamatanModel model);
//    }

    public JumlahBarisAdapter(List<HasilPengamatanModel> data, Activity context) {
        this.models = data;
        this.context = context;
    }

    public void onPilihAll() {

    }


    @Override
    public JumlahBarisAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_hasil_pengamatan_jumlah_baris, parent, false);

        JumlahBarisAdapter.ViewHolder viewHolder = new JumlahBarisAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final JumlahBarisAdapter.ViewHolder holder, final int position) {
        final HasilPengamatanModel model = models.get(position);
//        holder.mCount.setText(df.format(position+1));

//        holder.mNoSample.setText(df.format(model.getNo_sample()));
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        holder.mPlot.setText(model.getPlot());
//        holder.mLokasi.setText(model.getLokasi());
        holder.mCe.setText(df.format(model.getCe()));
        holder.mManual.setText(df.format(model.getManual()));
        holder.mLebarJalan.setText(df.format(model.getLebar_jalan()));
        holder.mExamini.setText(df.format(model.getExamini()));
        holder.mKet.setText(model.getKeterangan());
        holder.mTraktor.setText(df.format(model.getTraktor()));
        holder.mDijer.setText(df.format(model.getDitcher()));
        holder.mJumlahBaris.setText(df.format(model.getJumlah_baris()));
        holder.mJumlahBarisStd.setText(df.format(model.getJumlah_baris_std()));
        holder.mPenambahanBaris.setText(df.format(model.getPenambahan_baris()));
        holder.mJumlahPb.setText(df.format(model.getJumlah_pb()));
        holder.mTersier.setText(df.format(model.getTERSIER()));
        holder.mKancingan.setText(df.format(model.getKancingan()));
        holder.mPenambahanBarisTersier.setText(df.format(model.getPenambahan_baris_sal_tersier()));




    }


    @Override
    public int getItemCount() {
        return models.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mNoSample, mPlot,mLokasi,mCe,mManual,mLebarJalan,mExamini,mTraktor,mDijer,mJumlahBaris
                ,mJumlahBarisStd,mPenambahanBaris,mJumlahPb,mTersier,mKancingan,mPenambahanBarisTersier, mKet;
        ImageView indicator;

        ViewHolder(View view) {
            super(view);

   
            mPlot = view.findViewById(R.id.mPlot);
            mLokasi = view.findViewById(R.id.mLokasi);
            mCe = view.findViewById(R.id.mCe);
            mManual = view.findViewById(R.id.mManual);
            mLebarJalan = view.findViewById(R.id.mLebarJalan);
            mExamini = view.findViewById(R.id.mExamini);
            mTraktor = view.findViewById(R.id.mTraktor);
            mKet = view.findViewById(R.id.mKet);
            mDijer = view.findViewById(R.id.mDijer);
            mJumlahBaris = view.findViewById(R.id.mJumlahBaris);
            mJumlahBarisStd = view.findViewById(R.id.mJumlahBarisStd);
            mPenambahanBaris = view.findViewById(R.id.mPenambahanBaris);
            mJumlahPb = view.findViewById(R.id.mJumlahPb);
            mTersier = view.findViewById(R.id.mTersier);
            mKancingan = view.findViewById(R.id.mKancingan);
            mPenambahanBarisTersier = view.findViewById(R.id.mPenambahanBarisTersier);


        }
    }


}
