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
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MixerAdapter extends RecyclerView.Adapter<MixerAdapter.ViewHolder> {
    public List<HasilPengamatanModel> models;
    private final Activity context;

    public MixerAdapter(List<HasilPengamatanModel> data, Activity context) {
        this.models = data;
        this.context = context;
    }

    @Override
    public MixerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_hasil_pengamatan_gudangmixer, parent, false);
        return new MixerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MixerAdapter.ViewHolder holder, final int position) {
        final HasilPengamatanModel model = models.get(position);
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        holder.mPlot.setText(model.getPlot());
        holder.mNoSample.setText(model.getNo_sample());
        holder.mMulaiAdukanMixerKecil.setText(model.getMulaiAdukanMixerKecil());
        holder.mSelesaiAdukanMixerKecil.setText(model.getSelesaiAdukanMixerKecil());
        holder.mMulaiAdukanMixerBesar.setText(model.getMulaiAdukanMixerBesar());
        holder.mSelesaiAdukanMixerBesar.setText(model.getSelesaiAdukanMixerBesar());
        holder.mKodeUnitTangkiSuplay.setText(model.getKodeUnitTangkiSuplay());
        holder.mKeteranganPengisian.setText(model.getKeteranganPengisian());
        holder.mLokasicor.setText(model.getLokasi_adukan());
        holder.mJenisBahan.setText(model.getJenis_bahan());
        holder.mKet.setText(model.getKeterangan());
        holder.mReal.setText(df.format(model.getReal()));
        holder.mRencana.setText(df.format(model.getRencana()));

        // Bahan-bahan tambahan
        holder.mJenisBahan2.setText(model.getJenis_bahan_2());
        holder.mReal2.setText(df.format(model.getReal_2()));
        holder.mRencana2.setText(df.format(model.getRencana_2()));

        holder.mJenisBahan3.setText(model.getJenis_bahan_3());
        holder.mReal3.setText(df.format(model.getReal_3()));
        holder.mRencana3.setText(df.format(model.getRencana_3()));

        holder.mJenisBahan4.setText(model.getJenis_bahan_4());
        holder.mReal4.setText(df.format(model.getReal_4()));
        holder.mRencana4.setText(df.format(model.getRencana_4()));

        holder.mJenisBahan5.setText(model.getJenis_bahan_5());
        holder.mReal5.setText(df.format(model.getReal_5()));
        holder.mRencana5.setText(df.format(model.getRencana_5()));

        holder.mJenisBahan6.setText(model.getJenis_bahan_6());
        holder.mReal6.setText(df.format(model.getReal_6()));
        holder.mRencana6.setText(df.format(model.getRencana_6()));

        holder.mJenisBahan7.setText(model.getJenis_bahan_7());
        holder.mReal7.setText(df.format(model.getReal_7()));
        holder.mRencana7.setText(df.format(model.getRencana_7()));

        holder.mJenisBahan8.setText(model.getJenis_bahan_8());
        holder.mReal8.setText(df.format(model.getReal_8()));
        holder.mRencana8.setText(df.format(model.getRencana_8()));

        holder.mJenisBahan9.setText(model.getJenis_bahan_9());
        holder.mReal9.setText(df.format(model.getReal_9()));
        holder.mRencana9.setText(df.format(model.getRencana_9()));

        holder.mJenisBahan10.setText(model.getJenis_bahan_10());
        holder.mReal10.setText(df.format(model.getReal_10()));
        holder.mRencana10.setText(df.format(model.getRencana_10()));

        // ✅ Hitung durasi mixer besar & kecil tanpa hasil minus
        long selisihMenitBesar = hitungSelisihMenit(model.getMulaiAdukanMixerBesar(), model.getSelesaiAdukanMixerBesar());
        long selisihMenitKecil = hitungSelisihMenit(model.getMulaiAdukanMixerKecil(), model.getSelesaiAdukanMixerKecil());

        holder.mTotalMixerKecil.setText(selisihMenitKecil + " Menit");
        holder.mTotalMixerBesar.setText(selisihMenitBesar + " Menit");
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    /**
     * ✅ Fungsi hitung selisih menit (tanpa minus, termasuk lewat tengah malam)
     */
    private long hitungSelisihMenit(String mulaiStr, String selesaiStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime mulai = LocalTime.parse(mulaiStr, formatter);
            LocalTime selesai = LocalTime.parse(selesaiStr, formatter);

            int menitMulai = mulai.getHour() * 60 + mulai.getMinute();
            int menitSelesai = selesai.getHour() * 60 + selesai.getMinute();

            // kalau selesai < mulai → berarti lewat tengah malam
            if (menitSelesai < menitMulai) {
                menitSelesai += 24 * 60;
            }

            return menitSelesai - menitMulai;

        } catch (Exception e) {
            return 0;
        }
    }

    // ViewHolder class
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mNoSample, mPlot,
                mJenisBahan, mReal, mRencana,
                mMulaiAdukanMixerKecil, mSelesaiAdukanMixerKecil, mTotalMixerKecil,
                mMulaiAdukanMixerBesar, mSelesaiAdukanMixerBesar, mTotalMixerBesar,
                mKodeUnitTangkiSuplay, mKeteranganPengisian, mLokasicor, mKet,
                mJenisBahan2, mRencana2, mReal2,
                mJenisBahan3, mRencana3, mReal3,
                mJenisBahan4, mRencana4, mReal4,
                mJenisBahan5, mRencana5, mReal5,
                mJenisBahan6, mRencana6, mReal6,
                mJenisBahan7, mRencana7, mReal7,
                mJenisBahan8, mRencana8, mReal8,
                mJenisBahan9, mRencana9, mReal9,
                mJenisBahan10, mRencana10, mReal10;

        ImageView indicator;

        ViewHolder(View view) {
            super(view);

            mNoSample = view.findViewById(R.id.mNoSample);
            mPlot = view.findViewById(R.id.mPlot);
            mJenisBahan = view.findViewById(R.id.mJenisBahan);
            mReal = view.findViewById(R.id.mReal);
            mRencana = view.findViewById(R.id.mRencana);
            mMulaiAdukanMixerKecil = view.findViewById(R.id.mMulaiAdukanMixerKecil);
            mSelesaiAdukanMixerKecil = view.findViewById(R.id.mSelesaiAdukanMixerKecil);
            mTotalMixerKecil = view.findViewById(R.id.mTotalMixerKecil);
            mMulaiAdukanMixerBesar = view.findViewById(R.id.mMulaiAdukanMixerBesar);
            mSelesaiAdukanMixerBesar = view.findViewById(R.id.mSelesaiAdukanMixerBesar);
            mTotalMixerBesar = view.findViewById(R.id.mTotalMixerBesar);
            mKodeUnitTangkiSuplay = view.findViewById(R.id.mKodeUnitTangkiSuplay);
            mKeteranganPengisian = view.findViewById(R.id.mKeteranganPengisian);
            mKet = view.findViewById(R.id.mKet);
            mLokasicor = view.findViewById(R.id.mLokasiCor);

            mJenisBahan2 = view.findViewById(R.id.mJenisBahan2);
            mRencana2 = view.findViewById(R.id.mRencana2);
            mReal2 = view.findViewById(R.id.mReal2);

            mJenisBahan3 = view.findViewById(R.id.mJenisBahan3);
            mRencana3 = view.findViewById(R.id.mRencana3);
            mReal3 = view.findViewById(R.id.mReal3);

            mJenisBahan4 = view.findViewById(R.id.mJenisBahan4);
            mRencana4 = view.findViewById(R.id.mRencana4);
            mReal4 = view.findViewById(R.id.mReal4);

            mJenisBahan5 = view.findViewById(R.id.mJenisBahan5);
            mRencana5 = view.findViewById(R.id.mRencana5);
            mReal5 = view.findViewById(R.id.mReal5);

            mJenisBahan6 = view.findViewById(R.id.mJenisBahan6);
            mRencana6 = view.findViewById(R.id.mRencana6);
            mReal6 = view.findViewById(R.id.mReal6);

            mJenisBahan7 = view.findViewById(R.id.mJenisBahan7);
            mRencana7 = view.findViewById(R.id.mRencana7);
            mReal7 = view.findViewById(R.id.mReal7);

            mJenisBahan8 = view.findViewById(R.id.mJenisBahan8);
            mRencana8 = view.findViewById(R.id.mRencana8);
            mReal8 = view.findViewById(R.id.mReal8);

            mJenisBahan9 = view.findViewById(R.id.mJenisBahan9);
            mRencana9 = view.findViewById(R.id.mRencana9);
            mReal9 = view.findViewById(R.id.mReal9);

            mJenisBahan10 = view.findViewById(R.id.mJenisBahan10);
            mRencana10 = view.findViewById(R.id.mRencana10);
            mReal10 = view.findViewById(R.id.mReal10);
        }
    }
}
