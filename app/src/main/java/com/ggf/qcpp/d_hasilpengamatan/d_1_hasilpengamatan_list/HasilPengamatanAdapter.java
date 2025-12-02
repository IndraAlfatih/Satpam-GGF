package com.ggf.qcpp.d_hasilpengamatan.d_1_hasilpengamatan_list;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ggf.qcpp.App;
import com.ggf.qcpp.R;
import com.ggf.qcpp.e_formpengamatan.chopper.model.ChopperModel;
import com.ggf.qcpp.utils.Utils;

import java.util.List;


public class HasilPengamatanAdapter extends RecyclerView.Adapter<HasilPengamatanAdapter.ViewHolder> {
    public List<ChopperModel> carts;
    private final OnItemSelected listener;
    private final String username;
    Activity context;

    public interface OnItemSelected {
        void onSelect(ChopperModel model);
    }

    public HasilPengamatanAdapter(List<ChopperModel> data, Activity context, OnItemSelected listener , String username) {
        this.carts = data;
        this.context = context;
        this.listener = listener;
        this.username = username;
    }

    public void onPilihAll() {

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_hasil_pengamatan_chopper, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final ChopperModel model = carts.get(position);


//        holder.mHarga.setText(Utils.convertRupiah(String.valueOf(cart.getItem().getHargaBarang())));
        if(username.equals("mandor")) {
            if (model.getVERIFIED().getVERIFIED_MANDOR().isStatus()) {
//                mNamaVerif.setText(model.getVERIFIED().getVERIFIED_MANDOR().getNama());
                holder.mStatusVerif.setText("Sudah Disetujui");
                holder.mStatusVerif.setTextColor(App.getApplication().getColor(R.color.green1));
            } else {
//                mNamaVerif.setText(data.getVERIFIED().getVERIFIED_MANDOR().getNama());
                holder.mStatusVerif.setText("Belum Disetujui");
                holder.mStatusVerif.setTextColor(App.getApplication().getColor(R.color.orange_red));
            }
        }

        if(username.equals("kasie")) {
            if (model.getVERIFIED().getVERIFIED_KASI().isStatus()) {
                holder.mStatusVerif.setText("Sudah Disetujui");
                holder.mStatusVerif.setTextColor(App.getApplication().getColor(R.color.green1));
            } else {
//                mNamaVerif.setText(data.getVERIFIED().getVERIFIED_MANDOR().getNama());
                holder.mStatusVerif.setText("Belum Disetujui");
                holder.mStatusVerif.setTextColor(App.getApplication().getColor(R.color.orange_red));
            }
        }

        if(username.equals("kabag")) {
            if (model.getVERIFIED().getVERIFIED_KABAG().isStatus()) {
                holder.mStatusVerif.setText("Sudah Disetujui");
                holder.mStatusVerif.setTextColor(App.getApplication().getColor(R.color.green1));
            } else {
//                mNamaVerif.setText(data.getVERIFIED().getVERIFIED_MANDOR().getNama());
                holder.mStatusVerif.setText("Belum Disetujui");
                holder.mStatusVerif.setTextColor(App.getApplication().getColor(R.color.orange_red));
            }
        }


        holder.mUsername.setText("Yanto Sulaiman");
        holder.mKategori.setText(model.getKATEGORI());
        holder.mLokasi.setText(model.getLOKASI());
        holder.mCreated.setText(Utils.convertMongoDateWithoutTIme(model.getCREATED_AT()));
        holder.mPG.setText("Plantation Group "+ model.getPG());

//        holder.mToko.setText(rut.getKios());

        holder.itemView.setOnClickListener(view -> listener.onSelect(model));
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
        TextView mUsername, mKategori, mLokasi, mPG, mCreated , mStatusVerif;
        ImageView mFoto;

        ViewHolder(View view) {
            super(view);
            mUsername = view.findViewById(R.id.mUsername);
            mKategori = view.findViewById(R.id.mKategori);
            mLokasi = view.findViewById(R.id.mLokasi);
            mPG = view.findViewById(R.id.mPG);
            mCreated = view.findViewById(R.id.mCreated);
            mStatusVerif = view.findViewById(R.id.mStatusVerif);

        }
    }


}
