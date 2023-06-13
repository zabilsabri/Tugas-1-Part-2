package com.example.tgsprak5fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.CardViewHolder> {
    private ArrayList<Post> users;

    public PostAdapter(ArrayList<Post> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public PostAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        Post user = users.get(position);
        System.out.println(user);
        holder.tv_caption.setText(user.getCapt());
        Glide.with(holder.itemView.getContext()).load(user.getImage()).into(holder.iv_postphoto);
    }

    @Override
    public int getItemCount() {
        return this.users.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder{

        TextView tv_username, tv_fullname, tv_caption;
        ImageView iv_userphoto, iv_postphoto;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_caption = itemView.findViewById(R.id.caption);
            tv_username = itemView.findViewById(R.id.tv_username);
            tv_fullname = itemView.findViewById(R.id.tv_fullname);
            iv_userphoto = itemView.findViewById(R.id.iv_profile);
            iv_postphoto = itemView.findViewById(R.id.imageView);
        }
    }
}
