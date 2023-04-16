package com.example.tugasprak3quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {
    TextView username, score, bestscore;
    Button backbutton;
    User user;
    Photo fotoNew;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        int Score = getIntent().getIntExtra("score", 0);
        user = getIntent().getParcelableExtra("user");

        fotoNew = getIntent().getParcelableExtra("fotolagi");

        if (Score>user.getBest_score()){
            user.setBest_score(Score);
        }
        username = findViewById(R.id.name);
        score = findViewById(R.id.tv_score);
        bestscore = findViewById(R.id.tv_bestscore);
        backbutton = findViewById(R.id.btn_back);

        username.setText("Wahh keren bangett " + user.getUsername());
        bestscore.setText(String.valueOf(user.getBest_score()));
        score.setText(String.valueOf(Score));

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScoreActivity.this, HomeActivity.class);
                intent.putExtra("user", user);
                intent.putExtra("useryou", user);
                intent.putExtra("foto", fotoNew);
                startActivity(intent);
            }
        });
    }
}
