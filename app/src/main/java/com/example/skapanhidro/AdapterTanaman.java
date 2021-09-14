package com.example.skapanhidro;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class AdapterTanaman extends RecyclerView.Adapter<AdapterTanaman.MyViewHolder> {

    private Context mContext;
    private List<ClassTanaman> tanamanList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtIdtanaman, txtNamaTanaman, txtJumlah;
        public CardView cv_main;
        public RelativeLayout relaList;
        public ImageView imgTanaman;
        public Button btnDetail;

        public MyViewHolder(View view) {
            super(view);
            cv_main = view.findViewById(R.id.card_tanaman);
            relaList = view.findViewById(R.id.relaListTanaman);
            txtIdtanaman = view.findViewById(R.id.txtId);
            txtNamaTanaman = view.findViewById(R.id.txtTanaman);
            txtJumlah = view.findViewById(R.id.txtJumlah);
            imgTanaman = view.findViewById(R.id.fotoTanaman);
            btnDetail = view.findViewById(R.id.btnDetail);
        }
    }

    public AdapterTanaman(Context mContext, List<ClassTanaman> tanamanlList) {
        this.mContext = mContext;
        this.tanamanList = tanamanlList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemlist_tanaman, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

        if (tanamanList.isEmpty()) {
            Toast.makeText(mContext.getApplicationContext(), "Belum ada data", Toast.LENGTH_LONG).show();
        } else {
            final ClassTanaman tanamanClassku = tanamanList.get(position);

            holder.txtIdtanaman.setText(tanamanClassku.getIdTanaman());
            holder.txtNamaTanaman.setText(tanamanClassku.getNamaTanaman());
            holder.txtJumlah.setText(tanamanClassku.getJumlahTanaman()+" Tanaman");
            Glide.with(mContext)
                    .load(tanamanClassku.getFotoTanaman()) // image url
                    .placeholder(R.drawable.ic_noimage) // any placeholder to load at start
                    .error(R.drawable.ic_noimage)  // any image in case of error
                    .override(100, 100)
                    .into(holder.imgTanaman); // resizing

            holder.btnDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext.getApplicationContext(), HstActivity.class);
                    intent.putExtra("id_tanaman",tanamanClassku.getIdTanaman());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return tanamanList.size();
    }
}