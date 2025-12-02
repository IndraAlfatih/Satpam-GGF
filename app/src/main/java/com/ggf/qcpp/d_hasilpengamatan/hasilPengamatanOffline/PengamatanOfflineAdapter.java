package com.ggf.qcpp.d_hasilpengamatan.hasilPengamatanOffline;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ggf.qcpp.App;
import com.ggf.qcpp.Prefs;
import com.ggf.qcpp.R;
import com.ggf.qcpp.b_account.model.LoginResponse;
import com.ggf.qcpp.d_hasilpengamatan.hasilPengamatanOffline.model.OfflineModel;
import com.ggf.qcpp.e_formpengamatan.chopper.model.ChopperModel;
import com.ggf.qcpp.utils.GsonHelper;
import com.google.android.material.button.MaterialButton;

import java.util.List;


public class PengamatanOfflineAdapter extends RecyclerView.Adapter<PengamatanOfflineAdapter.ViewHolder> {
    public List<OfflineModel> carts;
    private final PengamatanOfflineAdapter.OnItemSelected listener;
    private final String username;
    Activity context;
    LoginResponse mProfile;

    public interface OnItemSelected {
        void onSelect(OfflineModel model);
    }

    public PengamatanOfflineAdapter(List<OfflineModel> data, Activity context, OnItemSelected listener , String username) {
        this.carts = data;
        this.context = context;
        this.listener = listener;
        this.username = username;
    }

    public void onPilihAll() {

    }


    @Override
    public PengamatanOfflineAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_offline, parent, false);

        PengamatanOfflineAdapter.ViewHolder viewHolder = new PengamatanOfflineAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final PengamatanOfflineAdapter.ViewHolder holder, final int position) {
        final OfflineModel model = carts.get(position);
        mProfile = (LoginResponse) GsonHelper.parseGson(
                App.getPref().getString(Prefs.PREF_STORE_PROFILE, ""),
                new LoginResponse()
        );
//        if(model.getVerify_kabag()!= 0){
//            if(model.getVerify_kabag() == 1){
//                holder.indicator.setImageResource(R.drawable.shape_indicator_active);
//                holder.mStatusVerif.setText("Kabag : Disetujui");
//            }else{
//                holder.indicator.setImageResource(R.drawable.shape_indicator_unactive);
//                holder.mStatusVerif.setText("Kabag : Ditolak");
//            }
//        } else if(model.getVerify_kasi()!= 0){
//            if(model.getVerify_kasi() == 1){
//                holder.indicator.setImageResource(R.drawable.shape_indicator_active);
//                holder.mStatusVerif.setText("Kasie : Disetujui");
//            }else{
//                holder.indicator.setImageResource(R.drawable.shape_indicator_unactive);
//                holder.mStatusVerif.setText("Kasie : Ditolak");
//            }
//        }else if(model.getVerify_mandor()!= 0){
//            if(model.getVerify_mandor() == 1){
//                holder.indicator.setImageResource(R.drawable.shape_indicator_active);
//                holder.mStatusVerif.setText("Mandor : Disetujui");
//            }else{
//                holder.indicator.setImageResource(R.drawable.shape_indicator_unactive);
//                holder.mStatusVerif.setText("Mandor : Ditolak");
//            }
//        }else {
//            holder.indicator.setImageResource(R.drawable.shape_indicator_orange);
//            holder.mStatusVerif.setText("Mandor : Sedang Proses");
//        }

        holder.mUsername.setText(model.getUsername());
        holder.mKategori.setText(model.getKategori());
        holder.mLokasi.setText("Lokasi : "+model.getLokasi());
        holder.mPG.setText("Plantation Group "+ mProfile.getData().getUser().getPg());

//        holder.mToko.setText(rut.getKios());

        holder.mResend.setOnClickListener(view -> listener.onSelect(model));
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
        TextView mUsername, mKategori, mLokasi, mPG, mCreated , mStatusVerif,mStatusVerifKasie,mStatusVerifKabag;
        ImageView indicator,indicatorKasie,indicatorKabag;
        MaterialButton mResend ;

        ViewHolder(View view) {
            super(view);
            mUsername = view.findViewById(R.id.mUsername);
            mKategori = view.findViewById(R.id.mKategori);
            mLokasi = view.findViewById(R.id.mLokasi);
            mPG = view.findViewById(R.id.mPG);
            mResend = view.findViewById(R.id.mResend);


        }
    }


}
