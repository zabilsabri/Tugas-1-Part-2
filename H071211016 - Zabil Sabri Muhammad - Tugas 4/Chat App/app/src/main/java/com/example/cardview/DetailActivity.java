package com.example.cardview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.text.Layout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {
    private ImageView ivFoto;
    private TextView tvNama, tvChat, tvWaktu;
    private LinearLayout profileBar;
    private String nama;
    private String foto;
    private String status;
    private String tgl_status;
    private String nomor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ivFoto = findViewById(R.id.iv_foto);
        tvNama = findViewById(R.id.tv_nama);
        tvChat = findViewById(R.id.chat);
        tvWaktu = findViewById(R.id.waktu_chat);
        profileBar = findViewById(R.id.profileBar);

        Intent terima = getIntent();
        nama = terima.getStringExtra("varNama");
        foto = terima.getStringExtra("varFoto");
        status = terima.getStringExtra("varStatus");
        nomor = terima.getStringExtra("varNomor");
        tgl_status = terima.getStringExtra("varTglStatus");
        String chat = terima.getStringExtra("varTentang");
        String waktu = terima.getStringExtra("varWaktu");

        tvNama.setText(nama);
        tvChat.setText(chat);
        tvWaktu.setText(waktu);
        Glide.with(DetailActivity.this)
                .load(foto)
                .into(ivFoto);
    }

    public void openProfile(View view){
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra("varNama", nama);
        intent.putExtra("varFoto", foto);
        intent.putExtra("varNomor", nomor);
        intent.putExtra("varStatus", status);
        intent.putExtra("varTglStatus", tgl_status);
        startActivity(intent);
    }

}