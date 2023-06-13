package com.example.tgsprak5fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView rv_home;
    PostAdapter postAdapter;
    Parcelable user;
    static ArrayList<Post> postingan = new ArrayList<>();
    Post users;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv_home = view.findViewById(R.id.rv_postingan);
        rv_home.setHasFixedSize(true);
        rv_home.setLayoutManager(new LinearLayoutManager(getContext()));

        Bundle bundle = getArguments();
        if (bundle !=null){
            user = bundle.getParcelable("PhotoPost");
            postingan.add((Post) user);
        }

        postAdapter = new PostAdapter(postingan);
        rv_home.setAdapter(postAdapter);
        System.out.println(postingan);
    }

}