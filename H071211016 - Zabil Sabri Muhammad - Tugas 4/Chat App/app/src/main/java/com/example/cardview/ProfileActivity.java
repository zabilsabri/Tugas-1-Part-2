package com.example.cardview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ProfileActivity extends AppCompatActivity {

    private ImageView ivFoto;
    private TextView tvNama, tvNomor, tvStatus, tvTglStatus;
    private String foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ivFoto = findViewById(R.id.iv_foto);
        tvNama = findViewById(R.id.tv_nama);
        tvNomor = findViewById(R.id.tv_nomor);
        tvStatus = findViewById(R.id.tv_status);
        tvTglStatus = findViewById(R.id.tv_tglStatus);

        Intent terima = getIntent();
        String nama = terima.getStringExtra("varNama");
        foto = terima.getStringExtra("varFoto");
        String status = terima.getStringExtra("varStatus");
        String tgl_status = terima.getStringExtra("varTglStatus");
        String nomor = terima.getStringExtra("varNomor");

        tvNama.setText(nama);
        tvNomor.setText(nomor);
        tvStatus.setText(status);
        tvTglStatus.setText(tgl_status);
        Glide.with(ProfileActivity.this).load(foto).into(ivFoto);

    }

    public void openPicture(View view){
        Intent intent = new Intent(this, PhotoProfileActivity.class);
        intent.putExtra("varFoto", foto);
        startActivity(intent);
    }
}