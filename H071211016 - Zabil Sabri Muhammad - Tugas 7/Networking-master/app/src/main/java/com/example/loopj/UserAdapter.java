package com.example.loopj;

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
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.CardViewHolder>{
    private ArrayList<UserResponse> dataUser;
    public UserAdapter(ArrayList<UserResponse> dataUser) {
        this.dataUser = dataUser;
    }


    @NonNull
    @Override
    public UserAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.CardViewHolder holder, int position) {
        UserResponse userResponse = dataUser.get(position);
        System.out.println(userResponse.getEmail());
        holder.tvNama.setText(userResponse.getFirstName());
        holder.tvEmail.setText(userResponse.getEmail());
        Glide.with(holder.ivFoto.getContext()).load(userResponse.getAvatarUrl()).apply(new RequestOptions()
                .override(350, 550)).into(holder.ivFoto);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id_number = dataUser.get(holder.getAdapterPosition()).getId();
                String id_wow = Integer.toString(id_number);
                Intent intent = new Intent(holder.itemView.getContext(), ProfileActivity.class);
                intent.putExtra("id", id_wow);
                System.out.println(id_wow);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataUser.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        ImageView ivFoto;
        TextView tvNama, tvEmail;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            ivFoto = itemView.findViewById(R.id.iv_foto);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvEmail = itemView.findViewById(R.id.tv_email);
        }
    }
}
