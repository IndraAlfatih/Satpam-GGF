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


public class SingkongStekPanjangAdapter extends RecyclerView.Adapter<SingkongStekPanjangAdapter.ViewHolder> {
    public List<HasilPengamatanModel> models;
    //    private final BajakAdapter.OnItemSelected listener;
    private String username;
    Activity context;

//    public interface OnItemSelected {
//        void onSelect(HasilPengamatanModel model);
//    }

    public SingkongStekPanjangAdapter(List<HasilPengamatanModel> data, Activity context) {
        this.models = data;
        this.context = context;
    }

    public void onPilihAll() {

    }


    @Override
    public SingkongStekPanjangAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_hasil_pengamatan_singkongbibitstekpanjang, parent, false);

        SingkongStekPanjangAdapter.ViewHolder viewHolder = new SingkongStekPanjangAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final SingkongStekPanjangAdapter.ViewHolder holder, final int position) {
        final HasilPengamatanModel model = models.get(position);
//        holder.mCount.setText(df.format(position+1));


        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        holder.mPlot.setText(model.getPlot());
        holder.mLuasPlot.setText(model.getLUAS_PLOT());
        holder.mNoSample.setText(model.getNo_sample());




    }

    @Override
    public int getItemCount() {
        return models.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mNoSample, mPlot, mLuasPlot, mKodeUnit , mJumlahBibitIkatDiSpk , mRealBibitIkat, mBibitNormal ,mBibitAfkir ,mJumlahKelilingBatangMasukStandar,mKelilingBibitBawah1,mKelilingBibitAtas1
        ,mKelilingBibitBawah2,mKelilingBibitAtas2,mKelilingBibitBawah3,mKelilingBibitAtas3,mKelilingBibitBawah4,mKelilingBibitAtas4,mKelilingBibitBawah5,mKelilingBibitAtas5,mKelilingBibitBawah6,mKelilingBibitAtas6,mKelilingBibitBawah7,mKelilingBibitAtas7,mKelilingBibitBawah8,mKelilingBibitAtas8,mKelilingBibitBawah9,mKelilingBibitAtas9,mKelilingBibitBawah10,mKelilingBibitAtas10;
        ImageView indicator;

        ViewHolder(View view) {
            super(view);
//            indicator = view.findViewById(R.id.indicator);
//            mBonggolSegarKurangDari = view.findViewById(R.id.mBonggolSegarKurangDari);
//            mBonggolSegarLebihDari = view.findViewById(R.id.mBonggolSegarLebihDari);
            mNoSample = view.findViewById(R.id.mNoSample);
            mPlot = view.findViewById(R.id.mPlot);
            mLuasPlot = view.findViewById(R.id.mLuasPlot);


//            mEstimasi = view.findViewById(R.id.mEstimasi);

        }
    }


}
