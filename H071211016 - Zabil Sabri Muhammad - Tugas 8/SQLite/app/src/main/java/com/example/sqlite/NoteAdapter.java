package com.example.sqlite;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.CardViewHolder> {

    private ArrayList<Student> dataNote;

    private final ClickListener clickListener;

    public interface ClickListener {
        void onItemClicked(Student note, int i);
    }

    public NoteAdapter(ClickListener clickListener, ArrayList<Student> dataNote){
        this.clickListener = clickListener;
        this.dataNote = dataNote;
    }

    @NonNull
    @Override
    public NoteAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.CardViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Student note = dataNote.get(position);
        holder.tvTitle.setText(note.getName());
        holder.tvTask.setText(note.getNim());
        holder.tvCreatedAt.setText("Created At: " + note.getProdi());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                terClick(note, position, view);
            }
        });
    }

    public void terClick(Student note, int position, View view){
        NoteAdapter.this.clickListener.onItemClicked(note, position);
    }

    @Override
    public int getItemCount() {
        return dataNote.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvTask, tvCreatedAt;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvTask = itemView.findViewById(R.id.tv_task);
            tvCreatedAt = itemView.findViewById(R.id.tv_createdAt);
        }
    }
}
