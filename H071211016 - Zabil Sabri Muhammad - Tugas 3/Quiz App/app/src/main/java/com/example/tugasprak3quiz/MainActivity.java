package com.example.tugasprak3quiz;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    CircleImageView profile;

    EditText EnterName;

    Button buttonApply;

    Photo foto;
    User user;

    private ActivityResultLauncher<Intent>PhotoLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
        new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK && result.getData() !=null) {
                    Uri ChooseProfile = result.getData().getData();
                    profile.setImageURI(ChooseProfile);
                    foto.setFotoUri(ChooseProfile);
                }
            }
        }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EnterName = findViewById(R.id.et_name);
        buttonApply = findViewById(R.id.btn_apply);
        profile = findViewById(R.id.civ_pict);
        foto = new Photo();

        user = new User();
        user.setBest_score(0);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent image = new Intent(Intent.ACTION_GET_CONTENT);
                image.setType("image/*");
                PhotoLauncher.launch(Intent.createChooser(image, "eitss pilih foto gais"));
            }
        });


        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user.setUsername(String.valueOf(EnterName.getText().toString()));

                if (user.getUsername().isEmpty()){
                    EnterName.setError("Tidak boleh kosong!");
                    Toast.makeText(getApplicationContext(), "isi nama weyy!", Toast.LENGTH_SHORT).show();
                }else {
                    System.out.println(foto);
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("user", user);
                    intent.putExtra("foto", foto);
                    startActivity(intent);
                }
            }
        });


    }
}
