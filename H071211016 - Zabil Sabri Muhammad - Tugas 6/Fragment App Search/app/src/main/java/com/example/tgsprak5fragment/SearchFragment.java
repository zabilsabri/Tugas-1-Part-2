package com.example.tgsprak5fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.appcompat.widget.SearchView;

import com.google.android.material.textfield.TextInputEditText;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SearchFragment extends Fragment {

    SearchView tie_search;
    RecyclerView rv_user;
    ProgressBar pg_blue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tie_search = view.findViewById(R.id.sv_search);
        rv_user = view.findViewById(R.id.rv_keren);
        pg_blue = view.findViewById(R.id.progressBar);

        SearchAdapter src_adapter = new SearchAdapter(HomeFragment.postingan);
        rv_user.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_user.setAdapter(src_adapter);
        src_adapter.notifyDataSetChanged();

        tie_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<User> cariUser = new ArrayList<>();
                Iterator<User> it = HomeFragment.postingan.iterator();
                while (it.hasNext()){
                    User user = it.next();
                    if (user.fullName.toLowerCase().contains(s.toLowerCase())){
                        cariUser.add(user);
                    } else if (s == ""){
                        SearchAdapter src_adapter = new SearchAdapter(HomeFragment.postingan);
                        rv_user.setLayoutManager(new LinearLayoutManager(getContext()));
                        rv_user.setAdapter(src_adapter);
                        src_adapter.notifyDataSetChanged();
                    }
                }
                if(!s.isEmpty()){
                    pg_blue.setVisibility(View.VISIBLE);
                    rv_user.setVisibility(View.GONE);
                    ExecutorService executor = Executors.newSingleThreadExecutor();
                    Handler handler = new Handler(Looper.myLooper());

                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(400L);
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        SearchAdapter src_adapter = new SearchAdapter(cariUser);
                                        rv_user.setLayoutManager(new LinearLayoutManager(getContext()));
                                        rv_user.setAdapter(src_adapter);
                                        src_adapter.notifyDataSetChanged();
                                        pg_blue.setVisibility(View.GONE);
                                        rv_user.setVisibility(View.VISIBLE);
                                    }
                                });
                            } catch (InterruptedException e){
                                throw new RuntimeException(e);
                            }
                        }
                    });
                } else {
                    SearchAdapter src_adapter = new SearchAdapter(HomeFragment.postingan);
                    rv_user.setLayoutManager(new LinearLayoutManager(getContext()));
                    rv_user.setAdapter(src_adapter);
                    src_adapter.notifyDataSetChanged();
                }
                return false;
            }
        });
    }
}