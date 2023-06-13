package com.example.tgsprak5fragment;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.sql.DataSource;

public class PostFragment extends Fragment {
    private ImageView iv_pict;
    private EditText et_caption;
    private Button btn_post;
    PostAdapter postAdapter;
    Post user;
    private ArrayList<Post> PhotoPost;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false);
    }
    private ActivityResultLauncher<Intent> PhotoLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == -1 && result.getData() != null){
                        Uri uri = result.getData().getData();
                        iv_pict.setImageURI(uri);

                        btn_post.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (uri == null) {
                                    Toast.makeText(getActivity(), "Choose photo first!!", Toast.LENGTH_SHORT).show();
                                }else {
                                    String capt = et_caption.getText().toString();
                                    user = new Post(uri, capt);
                                    Bundle bundle = new Bundle();
                                    bundle.putParcelable("PhotoPost", user);
                                    HomeFragment homeFragment = new HomeFragment();
                                    homeFragment.setArguments(bundle);

                                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                                    transaction.replace(R.id.frame_main, homeFragment);
                                    transaction.commit();
                                }
                            }
                        });
                    }
                }
            }
    );

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        iv_pict = view.findViewById(R.id.iv_pict);
        et_caption = view.findViewById(R.id.et_capt);
        btn_post = view.findViewById(R.id.btn_uploud);

        PhotoPost = new ArrayList<>();
        postAdapter = new PostAdapter(PhotoPost);

        iv_pict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType( "image/*");
                PhotoLauncher.launch(Intent.createChooser(intent, "Choose Photo"));
            }
        });
    }
}