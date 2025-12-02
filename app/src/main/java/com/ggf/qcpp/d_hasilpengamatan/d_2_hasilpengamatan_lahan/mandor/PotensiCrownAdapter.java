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


public class PotensiCrownAdapter extends RecyclerView.Adapter<PotensiCrownAdapter.ViewHolder> {
    public List<HasilPengamatanModel> models;
//    private final BajakAdapter.OnItemSelected listener;
    private String username;
    Activity context;

//    public interface OnItemSelected {
//        void onSelect(HasilPengamatanModel model);
//    }

    public PotensiCrownAdapter(List<HasilPengamatanModel> data, Activity context) {
        this.models = data;
        this.context = context;
    }

    public void onPilihAll() {

    }


    @Override
    public PotensiCrownAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_hasil_pengamatan_potensi_crown, parent, false);

        PotensiCrownAdapter.ViewHolder viewHolder = new PotensiCrownAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final PotensiCrownAdapter.ViewHolder holder, final int position) {
        final HasilPengamatanModel model = models.get(position);
//        holder.mCount.setText(df.format(position+1));

//        holder.mNoSample.setText(df.format(model.getNo_sample()));
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        holder.mPlot.setText(model.getPlot());
//        holder.mLokasi.setText(model.getLokasi());
        holder.mDaunLemas.setText(df.format(model.getDaun_lemas()));
        holder.mDaunBerduri.setText(df.format(model.getDaun_berduri()));
        holder.mTitikTumbuhLebih.setText(df.format(model.getTumbuh_lebih_dari_1()));
        holder.mTumbuhTidakAda.setText(df.format(model.getTumbuh_tidak_ada()));
        holder.mUnderSize.setText(df.format(model.getUnder_size()));
        holder.mRusakMekanis.setText(df.format(model.getRusak_mekanis()));
        holder.mKet.setText(model.getKeterangan());
        holder.mBusuk.setText(df.format(model.getBusuk()));
        holder.mPenyakit.setText(df.format(model.getBergejala()));
        holder.mLayuPermanen.setText(df.format(model.getLayu()));
        holder.mCabangTiga.setText(df.format(model.getCabang_lebih_dari_3()));
        holder.mTotal.setText(df.format(model.getTotal_afkir()));
        holder.m1.setText(df.format(model.getBibit_10_sampai_11()));
        holder.m2.setText(df.format(model.getBibit_12_sampai_14()));
        holder.m3.setText(df.format(model.getBibit_15_sampai_17()));
        holder.m4.setText(df.format(model.getBibit_18_sampai_24()));
        holder.m5.setText(df.format(model.getBibit_25_sampai_33()));
        holder.m6.setText(df.format(model.getBibit_34_sampai_38()));
        holder.m7.setText(df.format(model.getBibit_lebih_dari_38()));
        holder.mTot.setText(df.format(model.getTotal_bibit()));
        holder.mNormal.setText(df.format(model.getNormal()));
        holder.mCabang.setText(df.format(model.getCabang()));
        holder.mLiar.setText(df.format(model.getLiar()));
        holder.mCrownCabangDua.setText(df.format(model.getCrown_cabang_2()));
        holder.mCrownCabangTiga.setText(df.format(model.getCrown_cabang_3()));
        holder.mTanamanMandul.setText(df.format(model.getTanaman_mandul()));




    }


    @Override
    public int getItemCount() {
        return models.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mNoSample, mPlot,mDaunLemas,mDaunBerduri,mTitikTumbuhLebih,mTumbuhTidakAda,mUnderSize,mRusakMekanis,mBusuk,mPenyakit,mLayuPermanen,mCabangTiga,mTotal,m1,m2,m3,m4,m5,m6,m7,mTot,mNormal,mCabang,mLiar,mCrownCabangDua,mCrownCabangTiga,mTanamanMandul, mKet;
        ImageView indicator;

        ViewHolder(View view) {
            super(view);
            mPlot = view.findViewById(R.id.mPlot);
            mDaunLemas = view.findViewById(R.id.mDaunLemas);
            mDaunBerduri = view.findViewById(R.id.mDaunBerduri);
            mTitikTumbuhLebih = view.findViewById(R.id.mTitikTumbuhLebih);
            mTumbuhTidakAda = view.findViewById(R.id.mTumbuhTidakAda);
            mUnderSize = view.findViewById(R.id.mUnderSize);
            mRusakMekanis = view.findViewById(R.id.mRusakMekanis);
            mBusuk = view.findViewById(R.id.mBusuk);
            mPenyakit = view.findViewById(R.id.mPenyakit);
            mLayuPermanen = view.findViewById(R.id.mLayuPermanen);
            mCabangTiga = view.findViewById(R.id.mCabangTiga);
            mTotal = view.findViewById(R.id.mTotal);
            mKet = view.findViewById(R.id.mKet);
            m1 = view.findViewById(R.id.m1);
            m2 = view.findViewById(R.id.m2);
            m3 = view.findViewById(R.id.m3);
            m4 = view.findViewById(R.id.m4);
            m5 = view.findViewById(R.id.m5);
            m6 = view.findViewById(R.id.m6);
            m7 = view.findViewById(R.id.m7);
            mTot = view.findViewById(R.id.mTot);
            mNormal = view.findViewById(R.id.mNormal);
            mCabang = view.findViewById(R.id.mCabang);
            mLiar = view.findViewById(R.id.mLiar);
            mCrownCabangDua = view.findViewById(R.id.mCrownCabangDua);
            mCrownCabangTiga = view.findViewById(R.id.mCrownCabangTiga);
            mTanamanMandul = view.findViewById(R.id.mTanamanMandul);



        }
    }


}
