package com.example.tgsprak5fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    TextView tv_namaPenuh, tv_namaSetengah;
    ImageView iv_gambarPropil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tv_namaPenuh = findViewById(R.id.tv_name);
        tv_namaSetengah = findViewById(R.id.tv_username);
        iv_gambarPropil = findViewById(R.id.iv_profile);
        Intent terima = getIntent();
        String namaPenuh = terima.getStringExtra("namaPull");
        String namaSetengah = terima.getStringExtra("namaUser");
        int gambarPropil = terima.getIntExtra("potoPropil", 0);

        Uri imageUri = Uri.parse("android.resource://"+getPackageName()+"/"+gambarPropil);
        tv_namaPenuh.setText(namaPenuh);
        tv_namaSetengah.setText(namaSetengah);
        iv_gambarPropil.setImageURI(imageUri);
    }
}