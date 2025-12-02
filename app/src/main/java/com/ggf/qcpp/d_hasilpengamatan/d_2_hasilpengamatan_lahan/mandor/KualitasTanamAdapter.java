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


public class KualitasTanamAdapter extends RecyclerView.Adapter<KualitasTanamAdapter.ViewHolder> {
    public List<HasilPengamatanModel> models;
    //    private final BajakAdapter.OnItemSelected listener;
    private String username;
    Activity context;

//    public interface OnItemSelected {
//        void onSelect(HasilPengamatanModel model);
//    }

    public KualitasTanamAdapter(List<HasilPengamatanModel> data, Activity context) {
        this.models = data;
        this.context = context;
    }

    public void onPilihAll() {

    }


    @Override
    public KualitasTanamAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_hasil_pengamatan_tanam, parent, false);

        KualitasTanamAdapter.ViewHolder viewHolder = new KualitasTanamAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final KualitasTanamAdapter.ViewHolder holder, final int position) {
        final HasilPengamatanModel model = models.get(position);
//        holder.mCount.setText(df.format(position+1));
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        float avgJTDB = 0 ;
        float avgJTAB = 0 ;
        avgJTDB = (float) model.getPanjang_jtdb() /  (model.getTot_tanamjtdb() - 1);
        avgJTAB = (float) model.getPanjang_jtab() /  (model.getTot_tanamjtab() - 1);
        holder.mMandor.setText(model.getMandor_bibit());
        holder.mTanamanJTDB.setText(df.format(model.getTot_tanamjtdb()));
        holder.mPlot.setText(model.getPlot());
        holder.mPanjangSampleJTDB.setText(df.format(model.getPanjang_jtdb()));
        holder.mRataJtdb.setText(df.format(avgJTDB));
        holder.mRataJtab.setText(df.format(avgJTAB));
        holder.mTanamanJTAB.setText(df.format(model.getTot_tanamjtab()));
        holder.m1.setText(df.format(model.getKedalaman_1()));
        holder.m2.setText(df.format(model.getKedalaman_2()));
        holder.m3.setText(df.format(model.getKedalaman_3()));
        holder.m4.setText(df.format(model.getKedalaman_4()));
        holder.mTegak.setText(df.format(model.getTot_tegakterinjak()));
        holder.mTidakTegak.setText(df.format(model.getTot_tidaktegakterinjak()));
        holder.mLuasPlot.setText(model.getLUAS_PLOT());
        holder.mKelasBibit.setText(model.getKelas_bibit());
        holder.mJenisBibit.setText(model.getJenis_bibit());
        holder.mStatusJTDB.setText(model.getStatus_jtdb());
        holder.mKet.setText(model.getKeterangan());
        holder.mPanjangSampleJTAB.setText(df.format(model.getPanjang_jtab()));
        holder.mStatusJTAB.setText(model.getStatus_jtab());
//        holder.mInjakan.setText(df.format(model.getTot_tegakterinjak()));



    }


    @Override
    public int getItemCount() {
        return models.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        //        TextView mNoSample, mPlot, mNormal , mAfkir , mReal,mOverPlus , mOver , m1,m2,m3,m4,m5,m6,m7;
        TextView mMandor ,mTanamanJTDB,mPanjangSampleJTDB,mRataJtdb,mTanamanJTAB,mRataJtab,m1,m2,m3,m4,mTegak,mTidakTegak,mLuasPlot,mKelasBibit,mJenisBibit, mPlot,
        mStatusJTDB,mPanjangSampleJTAB,mStatusJTAB, mKet;
        ImageView indicator;

        ViewHolder(View view) {
            super(view);
            mPlot = view.findViewById(R.id.mPlot);
            mMandor = view.findViewById(R.id.mMandor);
            mTanamanJTDB = view.findViewById(R.id.mTanamanJTDB);
            mPanjangSampleJTDB = view.findViewById(R.id.mPanjangJTDB);
            mRataJtdb = view.findViewById(R.id.mRataJtdb);
            mTanamanJTAB = view.findViewById(R.id.mTanamanJTAB);
            mRataJtab = view.findViewById(R.id.mRataJtab);
            m1 = view.findViewById(R.id.m1);
            m2 = view.findViewById(R.id.m2);
            mKet = view.findViewById(R.id.mKet);
            m3 = view.findViewById(R.id.m3);
            m4 = view.findViewById(R.id.m4);
            mTidakTegak = view.findViewById(R.id.mTidakTegak);
            mTegak = view.findViewById(R.id.mTegak);
            mLuasPlot = view.findViewById(R.id.mLuasPlot);
            mKelasBibit = view.findViewById(R.id.mKelasBibit);
            mJenisBibit = view.findViewById(R.id.mJenisBibit);
            mStatusJTDB = view.findViewById(R.id.mStatusJTDB);
            mPanjangSampleJTAB = view.findViewById(R.id.mPanjangSampleJTAB);
            mStatusJTAB = view.findViewById(R.id.mStatusJTAB);

        }
    }


}