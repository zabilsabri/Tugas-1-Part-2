package com.example.cardview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvPahlawan;
    private ArrayList<ModelPahlawan> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvPahlawan = findViewById(R.id.rv_pahlawan);
        rvPahlawan.setHasFixedSize(false);
        data.addAll(DataPahlawan.ambilDataPahlawan());
        tampilDataCard();
    }

    private void tampilDataCard() {
        rvPahlawan.setLayoutManager(new LinearLayoutManager(this));
        AdapterCard colokanCard = new AdapterCard(data);
        rvPahlawan.setAdapter(colokanCard);
        System.out.println(data);
    }

}