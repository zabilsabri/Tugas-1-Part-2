package com.example.tgsprak5fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView btn_home, btn_add, btn_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_home = findViewById(R.id.homebutton);
        btn_add = findViewById(R.id.addbutton);
        btn_profile = findViewById(R.id.personbutton);

        FragmentManager fragmentManager = getSupportFragmentManager();
        HomeFragment homeFragment = new HomeFragment();
        Fragment fragment = fragmentManager.findFragmentByTag(HomeFragment.class.getSimpleName());

        if (!(fragment instanceof HomeFragment)){
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_main, homeFragment, HomeFragment.class.getSimpleName())
                    .commit();
        }

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                HomeFragment homeFragment = new HomeFragment();
                Fragment fragment = fragmentManager.findFragmentByTag(HomeFragment.class.getSimpleName());

                if (!(fragment instanceof HomeFragment)){
                    fragmentManager
                            .beginTransaction()
                            .replace(R.id.frame_main, homeFragment, HomeFragment.class.getSimpleName())
                            .commit();
                }
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                PostFragment postFragment = new PostFragment();
                Fragment fragment = fragmentManager.findFragmentByTag(PostFragment.class.getSimpleName());

                if (!(fragment instanceof PostFragment)){
                    fragmentManager
                            .beginTransaction()
                            .replace(R.id.frame_main, postFragment, PostFragment.class.getSimpleName())
                            .commit();
                }
            }
        });

        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                ProfileFragment profileFragment = new ProfileFragment();
                Fragment fragment = fragmentManager.findFragmentByTag(ProfileFragment.class.getSimpleName());

                if (!(fragment instanceof ProfileFragment)){
                    fragmentManager
                            .beginTransaction()
                            .replace(R.id.frame_main, profileFragment, ProfileFragment.class.getSimpleName())
                            .commit();
                }
            }
        });
    }
}