package com.example.loopj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rv_user;
    private TextView tv_errorWadaw;
    private Button btn_refreshPage;
    private ProgressBar pg_putar;
    private ArrayList<UserResponse> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callAPI();

    }

    public void Refresh(){
        pg_putar = findViewById(R.id.pg_lepi);
        tv_errorWadaw = findViewById(R.id.tv_error);
        btn_refreshPage = findViewById(R.id.bt_refresh);

        pg_putar.setVisibility(View.GONE);
        tv_errorWadaw.setVisibility(View.VISIBLE);
        btn_refreshPage.setVisibility(View.VISIBLE);
        btn_refreshPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pg_putar.setVisibility(View.VISIBLE);
                tv_errorWadaw.setVisibility(View.GONE);
                btn_refreshPage.setVisibility(View.GONE);
                callAPI();
            }
        });
    }

    public void callAPI(){
        pg_putar = findViewById(R.id.pg_lepi);
        tv_errorWadaw = findViewById(R.id.tv_error);
        btn_refreshPage = findViewById(R.id.bt_refresh);
        rv_user = findViewById(R.id.rv_lepi);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        tv_errorWadaw.setVisibility(View.GONE);
        btn_refreshPage.setVisibility(View.GONE);
        pg_putar.setVisibility(View.VISIBLE);

        executorService.execute(() -> {
            Call<DataResponse> client = ApiConfig.getApiService().getUser("12");
            client.enqueue(new Callback<DataResponse>() {
                @Override
                public void onResponse(Call<DataResponse> call, Response<DataResponse>
                        response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            ArrayList<UserResponse> userResponse = response.body().getData();
                            UserAdapter adkhCard = new UserAdapter(userResponse);

                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    rv_user.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                                    rv_user.setAdapter(adkhCard);
                                    pg_putar.setVisibility(View.GONE);
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
                public void onFailure(Call<DataResponse> call, Throwable t) {
                    Refresh();
                }
            });
        });
    }
}