package com.ggf.qcpp.d_hasilpengamatan.d_2_hasilpengamatan_lahan.mandor;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ggf.qcpp.R;
import com.ggf.qcpp.d_hasilpengamatan.d_2_hasilpengamatan_lahan.mandor.model.HasilPengamatanModel;

import java.text.DecimalFormat;
import java.util.List;


public class AdukanAdapter extends RecyclerView.Adapter<AdukanAdapter.ViewHolder> {
    public List<HasilPengamatanModel> models;
//    private final BajakAdapter.OnItemSelected listener;
    private String username;
    Activity context;

//    public interface OnItemSelected {
//        void onSelect(HasilPengamatanModel model);
//    }

    public AdukanAdapter(List<HasilPengamatanModel> data, Activity context) {
        this.models = data;
        this.context = context;
    }

    public void onPilihAll() {

    }


    @Override
    public AdukanAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_hasil_pengamatan_adukanbahandilokasi, parent, false);

        AdukanAdapter.ViewHolder viewHolder = new AdukanAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final AdukanAdapter.ViewHolder holder, final int position) {
        final HasilPengamatanModel model = models.get(position);
//        holder.mCount.setText(df.format(position+1));

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        holder.mPlot.setText(model.getPlot());
        holder.mAktivitas.setText(model.getAktivitas());
        holder.mJenisBahan.setText(model.getJenis_bahan());
        holder.mKet.setText(model.getKeterangan());
        holder.mReal.setText(df.format(model.getReal()));
        holder.mRencana.setText(df.format(model.getRencana()));
        holder.mPengisianKe.setText(df.format(model.getPengisian_ke()));
        holder.mVolumeAir.setText(df.format(model.getVolume_air()));
        boolean isChecked = "1".equals(model.getCeklist_keaktifan_agitator_cameco());
        holder.mCeklistKeaktifanAgitatorCameco.setChecked(isChecked);
//        holder.mCeklistKeaktifanAgitatorCameco.setText(isChecked ? "Aktif" : "Tidak Aktif");
        holder.mCeklistKeaktifanAgitatorCameco.setEnabled(false);



    }


    @Override
    public int getItemCount() {
        return models.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mNoSample, mPlot, mLokasi , mAktivitas , mJenisBahan,mReal , mRencana , mPengisianKe,mVolumeAir, mKet;
        CheckBox mCeklistKeaktifanAgitatorCameco;
        ImageView indicator;

        ViewHolder(View view) {
            super(view);

            mPlot = view.findViewById(R.id.mPlot);
            mAktivitas = view.findViewById(R.id.mAktivitas);
            mJenisBahan = view.findViewById(R.id.mJenisBahan);
            mReal = view.findViewById(R.id.mReal);
            mRencana = view.findViewById(R.id.mRencana);
            mPengisianKe = view.findViewById(R.id.mPengisianKe);
            mVolumeAir = view.findViewById(R.id.mVolumeAir);
            mKet = view.findViewById(R.id.mKet);
            mCeklistKeaktifanAgitatorCameco = view.findViewById(R.id.mCeklistKeaktifanAgitatorCameco);

        }
    }


}
