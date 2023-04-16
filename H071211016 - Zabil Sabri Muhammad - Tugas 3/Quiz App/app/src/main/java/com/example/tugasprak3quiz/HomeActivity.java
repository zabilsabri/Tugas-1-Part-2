package com.example.tugasprak3quiz;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class HomeActivity extends AppCompatActivity {
   ImageView profile;
   TextView name, bestscore;
   Button buttonPlay;
   User user;
   User useryou;
   Photo fotoback, foto3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        profile = findViewById(R.id.civ_pict);
        name = findViewById(R.id.tv_name);
        bestscore = findViewById(R.id.tv_bestscore);
        buttonPlay = findViewById(R.id.btn_playAgain);

        Intent intent = getIntent();
        user = intent.getParcelableExtra("user");
        fotoback = intent.getParcelableExtra("foto");
        Picasso.get().load(fotoback.getFotoUri()).into(profile);

        if(getIntent().getParcelableExtra("useryou") == null){
            fotoback = intent.getParcelableExtra("foto");
            Picasso.get().load(fotoback.getFotoUri()).into(profile);
            name.setText(user.getUsername());
            bestscore.setText("Best Score: 0");
        } else {
            useryou = getIntent().getParcelableExtra("useryou");
            name.setText(useryou.getUsername());
            Picasso.get().load(fotoback.getFotoUri()).into(profile);
            bestscore.setText("Best Score: " + String.valueOf(useryou.getBest_score()));
            buttonPlay.setText("PLAY AGAIN?");
        }

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent play = new Intent(HomeActivity.this, QuestionActivity.class);
                play.putExtra("user", user);
                play.putExtra("fotoback", fotoback);
                startActivity(play);
            }

        });
    }
}
