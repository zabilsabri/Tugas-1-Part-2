package com.example.cardview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class PhotoProfileActivity extends AppCompatActivity {

    private ImageView ivFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_profile);

        ivFoto = findViewById(R.id.iv_foto);

        Intent terima = getIntent();
        String foto = terima.getStringExtra("varFoto");

        Glide.with(PhotoProfileActivity.this).load(foto).into(ivFoto);
    }

    public void keluar(View view){
        finish();
    }
}