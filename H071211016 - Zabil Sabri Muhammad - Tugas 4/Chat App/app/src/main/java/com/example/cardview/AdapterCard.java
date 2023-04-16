package com.example.cardview;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import java.util.ArrayList;

public class AdapterCard extends RecyclerView.Adapter<AdapterCard.CardViewHolder> {
    private ArrayList<ModelPahlawan> dataPahlawan;

    public AdapterCard(ArrayList<ModelPahlawan> dataPahlawan) {
        this.dataPahlawan = dataPahlawan;
    }

    @NonNull
    @Override
    public AdapterCard.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCard.CardViewHolder holder, int position) {
        ModelPahlawan pahlawan = dataPahlawan.get(position);
        holder.tvNama.setText(pahlawan.getNama());
        holder.tvTentang.setText(pahlawan.getTentang());
        holder.tvWaktu.setText(pahlawan.getWaktu());
        Glide.with(holder.itemView.getContext())
                .load(pahlawan.getFoto())
                .apply(new RequestOptions().override(350,
                        550))
                .into(holder.ivFoto);
        holder.ivFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), PhotoProfileActivity.class);
                intent.putExtra("varFoto", pahlawan.getFoto());
                holder.itemView.getContext().startActivity(intent);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namaPahlawan = dataPahlawan.get(holder.getAdapterPosition()).getNama();
                String tentangPahlawan = dataPahlawan.get(holder.getAdapterPosition()).getTentang();
                String waktu = dataPahlawan.get(holder.getAdapterPosition()).getWaktu();
                String fotoPahlawan = dataPahlawan.get(holder.getAdapterPosition()).getFoto();
                String nomor = dataPahlawan.get(holder.getAdapterPosition()).getNomor();
                String status = dataPahlawan.get(holder.getAdapterPosition()).getStatus();
                String tgl_status = dataPahlawan.get(holder.getAdapterPosition()).getTgl_status();
                Intent kirim = new Intent(holder.itemView.getContext(), DetailActivity.class);
                kirim.putExtra("varNama", namaPahlawan);
                kirim.putExtra("varTentang", tentangPahlawan);
                kirim.putExtra("varWaktu", waktu);
                kirim.putExtra("varFoto", fotoPahlawan);
                kirim.putExtra("varNomor", nomor);
                kirim.putExtra("varStatus", status);
                kirim.putExtra("varTglStatus", tgl_status);

                holder.itemView.getContext().startActivity(kirim);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataPahlawan.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        ImageView ivFoto;
        TextView tvNama, tvTentang, tvWaktu;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            ivFoto = itemView.findViewById(R.id.iv_foto);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvTentang = itemView.findViewById(R.id.tv_tentang);
            tvWaktu = itemView.findViewById(R.id.tv_waktu);
        }
    }
}
