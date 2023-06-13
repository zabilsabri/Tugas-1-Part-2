package com.example.loopj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    private ProgressBar pg_putar;
    private TextView tv_errorWadaw;
    private Button btn_refreshPage;
    private TextView tv_email, tv_nama;
    private ImageView iv_orangnya;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        callAPI();

    }

    public void Refresh(){
        pg_putar = findViewById(R.id.pg_putarCuy);
        tv_errorWadaw = findViewById(R.id.tv_error);
        btn_refreshPage = findViewById(R.id.bt_refresh);

        tv_errorWadaw.setVisibility(View.VISIBLE);
        btn_refreshPage.setVisibility(View.VISIBLE);
        pg_putar.setVisibility(View.GONE);

        btn_refreshPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_errorWadaw.setVisibility(View.GONE);
                btn_refreshPage.setVisibility(View.GONE);
                pg_putar.setVisibility(View.VISIBLE);
                callAPI();
            }
        });

    }

    public void callAPI(){
        pg_putar = findViewById(R.id.pg_putarCuy);
        tv_email = findViewById(R.id.tv_emailLepi);
        tv_nama = findViewById(R.id.tv_namaLepi);
        iv_orangnya = findViewById(R.id.civ_lepi);
        tv_errorWadaw = findViewById(R.id.tv_error);
        btn_refreshPage = findViewById(R.id.bt_refresh);

        Intent terima = getIntent();
        String id =terima.getStringExtra("id");

        tv_errorWadaw.setVisibility(View.GONE);
        btn_refreshPage.setVisibility(View.GONE);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executorService.execute(() -> {
            Call<ProfileDataResponse> client = ApiConfig.getApiService().getUserData(id);
            client.enqueue(new Callback<ProfileDataResponse>() {
                @Override
                public void onResponse(Call<ProfileDataResponse> call, Response<ProfileDataResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            UserResponse userResponse = response.body().getDataProfile();

                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    String nama = userResponse.getFirstName() + " " + userResponse.getLastName();
                                    tv_nama.setText(nama);
                                    tv_email.setText(userResponse.getEmail());
                                    Glide.with(iv_orangnya.getContext()).load(userResponse.getAvatarUrl()).apply(new RequestOptions()
                                            .override(350, 550)).into(iv_orangnya);
                                    pg_putar.setVisibility(View.GONE);
                                    tv_nama.setVisibility(View.VISIBLE);
                                    tv_email.setVisibility(View.VISIBLE);
                                    iv_orangnya.setVisibility(View.VISIBLE);
                                }
                            });
                        }
                    } else {
                        if (response.body() != null) {
                            Log.e("MainActivity", "onFailure1: " + response.message());
                        }
                    }
                }
                @Override
                public void onFailure(Call<ProfileDataResponse> call, Throwable t) {
                    Refresh();
                }
            });
        });
    }
}