package com.ggf.qcpp.i_notify;

import android.app.Activity;
import android.opengl.Visibility;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ggf.qcpp.App;
import com.ggf.qcpp.Prefs;
import com.ggf.qcpp.R;
import com.ggf.qcpp.b_account.model.LoginResponse;
import com.ggf.qcpp.e_formpengamatan.chopper.model.ChopperModel;
import com.ggf.qcpp.i_notify.model.PengamatanModel;
import com.ggf.qcpp.utils.GsonHelper;
import com.ggf.qcpp.utils.Utils;

import java.util.List;


public class NotifyAdapter extends RecyclerView.Adapter<NotifyAdapter.ViewHolder> {
    public List<PengamatanModel> carts;
    private final NotifyAdapter.OnItemSelected listener;
    private final String username;
    Activity context;
    LoginResponse mProfile;

    boolean hapus;

    public interface OnItemSelected {
        void onSelect(PengamatanModel model);

        void onHapus(PengamatanModel model);
    }

    public NotifyAdapter(List<PengamatanModel> data, Activity context, OnItemSelected listener, String username, boolean hapus) {
        this.carts = data;
        this.context = context;
        this.listener = listener;
        this.username = username;
        this.hapus = hapus;
    }

    public void onPilihAll() {

    }


    @Override
    public NotifyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_notifikasi, parent, false);

        NotifyAdapter.ViewHolder viewHolder = new NotifyAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final NotifyAdapter.ViewHolder holder, final int position) {
        final PengamatanModel model = carts.get(position);
        mProfile = (LoginResponse) GsonHelper.parseGson(
                App.getPref().getString(Prefs.PREF_STORE_PROFILE, ""),
                new LoginResponse()
        );
        if (model.getVerify_kabag() != 0) {
            if (model.getVerify_kabag() == 1) {
                holder.indicator.setImageResource(R.drawable.shape_indicator_active);
                holder.mStatusVerif.setText("Mandor : Approve");
                holder.indicator2.setImageResource(R.drawable.shape_indicator_active);
                holder.mStatusVerif2.setText("Kasie : Approve");
                holder.indicator3.setImageResource(R.drawable.shape_indicator_active);
                holder.mStatusVerif3.setText("Kabag : Approve");
            } else {
                holder.indicator.setImageResource(R.drawable.shape_indicator_unactive);
                holder.mStatusVerif.setText("Kabag : Ditolak");
                holder.indicator2.setImageResource(R.drawable.shape_indicator_unactive);
                holder.mStatusVerif2.setText("Kasie : Ditolak");
                holder.indicator3.setImageResource(R.drawable.shape_indicator_unactive);
                holder.mStatusVerif3.setText("Mandor : Ditolak");
            }
        } else if (model.getVerify_kasi() != 0) {
            if (model.getVerify_kasi() == 1) {
                holder.indicator.setImageResource(R.drawable.shape_indicator_active);
                holder.mStatusVerif.setText("Mandor : Approve");
                holder.indicator2.setImageResource(R.drawable.shape_indicator_active);
                holder.mStatusVerif2.setText("Kasie : Approve");
                holder.indicator3.setImageResource(R.drawable.shape_indicator_orange);
                holder.mStatusVerif3.setText("Kabag : Menunggu Approve");
            } else {
                holder.indicator.setImageResource(R.drawable.shape_indicator_unactive);
                holder.mStatusVerif.setText("Kabag : Ditolak");
                holder.indicator2.setImageResource(R.drawable.shape_indicator_unactive);
                holder.mStatusVerif2.setText("Kasie : Ditolak");
                holder.indicator3.setImageResource(R.drawable.shape_indicator_unactive);
                holder.mStatusVerif3.setText("Mandor : Ditolak");
            }
        } else if (model.getVerify_mandor() != 0) {
            if (model.getVerify_mandor() == 1) {
                holder.indicator.setImageResource(R.drawable.shape_indicator_active);
                holder.mStatusVerif.setText("Mandor : Approve");
                holder.indicator2.setImageResource(R.drawable.shape_indicator_orange);
                holder.mStatusVerif2.setText("Kasie : Menunggu Approve");
                holder.indicator3.setImageResource(R.drawable.shape_indicator_orange);
                holder.mStatusVerif3.setText("Kabag : Menunggu Approve");
            } else {
                holder.indicator.setImageResource(R.drawable.shape_indicator_unactive);
                holder.mStatusVerif.setText("Kabag : Ditolak");
                holder.indicator2.setImageResource(R.drawable.shape_indicator_unactive);
                holder.mStatusVerif2.setText("Kasie : Ditolak");
                holder.indicator3.setImageResource(R.drawable.shape_indicator_unactive);
                holder.mStatusVerif3.setText("Mandor : Ditolak");
            }
        } else {
            holder.indicator.setImageResource(R.drawable.shape_indicator_orange);
            holder.mStatusVerif.setText("Mandor : Menunggu Approve");
            holder.indicator2.setImageResource(R.drawable.shape_indicator_orange);
            holder.mStatusVerif2.setText("Kasie : Menunggu Approve");
            holder.indicator3.setImageResource(R.drawable.shape_indicator_orange);
            holder.mStatusVerif3.setText("Kabag : Menunggu Approve");
        }

        holder.mUsername.setText(model.getName());
        holder.mKategori.setText(model.getKATEGORI());
        holder.mLokasi.setText("Lokasi : " + model.getLOKASI());
        holder.mCreated.setText(model.getCREATED_AT());
        holder.mPG.setText("Plantation Group " + mProfile.getData().getUser().getPg());

//        holder.mToko.setText(rut.getKios());

        holder.itemView.setOnClickListener(view -> listener.onSelect(model));
        if (hapus)
            holder.mHapus.setVisibility(View.GONE);
        else
            holder.mHapus.setVisibility(View.VISIBLE);
        holder.mHapus.setOnClickListener(view -> listener.onHapus(model));
//        if (cart.getItem().getFoto().size()>0)
//            Glide.with(context)
//                    .load(App.getApplication().getResources().getString(R.string.img_end_point) + cart.getItem().getFoto().get(0).getNamaFile())
//                    .apply(new RequestOptions().placeholder(R.drawable.loading_ios))
//                    .into(holder.mIconImage);
//        else Glide.with(context)
//                .load(R.drawable.shopping_bag)
//                .apply(new RequestOptions().placeholder(R.drawable.loading_ios))
//                .into(holder.mIconImage);
//        holder.mNama.setOnClickListener(view -> listener.onCartSelect("ini CartSelect"));
//        holder.mCheckBox.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) context);

    }


    @Override
    public int getItemCount() {
        return carts.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mUsername, mKategori, mLokasi, mPG, mCreated, mStatusVerif, mStatusVerif2, mStatusVerif3, mStatusVerifKasie, mStatusVerifKabag;
        ImageView indicator, indicator2, indicator3, indicatorKasie, indicatorKabag;

        Button mHapus;

        ViewHolder(View view) {
            super(view);
            mUsername = view.findViewById(R.id.mUsername);
            mKategori = view.findViewById(R.id.mKategori);
            mLokasi = view.findViewById(R.id.mLokasi);
            mPG = view.findViewById(R.id.mPG);
            mCreated = view.findViewById(R.id.mCreated);
            mStatusVerif = view.findViewById(R.id.mStatusVerif);
            mStatusVerif2 = view.findViewById(R.id.mStatusVerif2);
            mStatusVerif3 = view.findViewById(R.id.mStatusVerif3);
            mStatusVerifKasie = view.findViewById(R.id.mStatusVerifKasie);
            mStatusVerifKabag = view.findViewById(R.id.mStatusVerifKabag);
            indicator = view.findViewById(R.id.indicator);
            indicator2 = view.findViewById(R.id.indicator2);
            indicator3 = view.findViewById(R.id.indicator3);
            indicatorKasie = view.findViewById(R.id.indicatorKasie);
            indicatorKabag = view.findViewById(R.id.indicatorKabag);
            mHapus = view.findViewById(R.id.mHapus);

        }
    }


}
