package com.example.praktikumbangunruang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private EditText p_balok, l_balok, t_balok, j_kerucut, t_kerucut, j_bola;
    private LinearLayout layout_balok, layout_kerucut, layout_bola;
    private TextView h_balok, h_kerucut, h_bola;
    private Button b_balok, b_kerucut, b_bola;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        p_balok = findViewById(R.id.panjang_balok);
        l_balok = findViewById(R.id.lebar_balok);
        t_balok = findViewById(R.id.tinggi_balok);
        h_balok = findViewById(R.id.hasil_balok);
        b_balok = findViewById(R.id.hitung_balok);

        j_kerucut = findViewById(R.id.jari_kerucut);
        t_kerucut = findViewById(R.id.tinggi_kerucut);
        h_kerucut = findViewById(R.id.hasil_kerucut);
        b_kerucut = findViewById(R.id.hitung_kerucut);

        j_bola = findViewById(R.id.jari_bola);
        h_bola = findViewById(R.id.hasil_bola);
        b_bola = findViewById(R.id.hitung_bola);

        spinner = findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.option, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
        String selectedItem = (String) parent.getItemAtPosition(i);

        if (selectedItem.equals("Balok")) {
            findViewById(R.id.balok_layout).setVisibility(View.VISIBLE);
            findViewById(R.id.kerucut_layout).setVisibility(View.GONE);
            findViewById(R.id.bola_layout).setVisibility(View.GONE);

            b_balok.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){

                    String test = p_balok.getText().toString();
                    String test2 = l_balok.getText().toString();
                    String test3 = t_balok.getText().toString();

                    if(test.equals("") || test2.equals("") || test3.equals("")){
                        if(test.equals("")){
                            p_balok.setError("Kosong");
                        }
                        if(test2.equals("")){
                            l_balok.setError("Kosong");
                        }
                        if(test3.equals("")){
                            t_balok.setError("Kosong");
                        }
                    } else {
                        double d_l_balok = Double.parseDouble(String.valueOf(l_balok.getText()));
                        double d_t_balok = Double.parseDouble(String.valueOf(t_balok.getText()));
                        double d_p_balok = Double.parseDouble(String.valueOf(p_balok.getText()));
                        double volume = d_p_balok * d_l_balok * d_t_balok;
                        String formatVolume = String.format("%.2f", volume);
                        h_balok.setText(formatVolume);
                    }
                }
            });


        } else if (selectedItem.equals("Kerucut")) {
            findViewById(R.id.balok_layout).setVisibility(View.GONE);
            findViewById(R.id.kerucut_layout).setVisibility(View.VISIBLE);
            findViewById(R.id.bola_layout).setVisibility(View.GONE);

            b_kerucut.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){

                    String test = j_kerucut.getText().toString();
                    String test2 = t_kerucut.getText().toString();

                    if(test.equals("") || test2.equals("")){
                        if(test.equals("")){
                            j_kerucut.setError("Kosong");
                        }
                        if(test2.equals("")){
                            t_kerucut.setError("Kosong");
                        }
                    } else {
                        double d_j_kerucut = Double.parseDouble(String.valueOf(j_kerucut.getText()));
                        double d_t_kerucut = Double.parseDouble(String.valueOf(t_kerucut.getText()));

                        double volume = 1.0/3.0 * 3.14 * (d_j_kerucut * d_t_kerucut) * d_t_kerucut;
                        String formatVolume = String.format("%.2f", volume);
                        h_kerucut.setText(formatVolume);
                    }
                }
            });

        } else if (selectedItem.equals("Bola")) {

            findViewById(R.id.balok_layout).setVisibility(View.GONE);
            findViewById(R.id.kerucut_layout).setVisibility(View.GONE);
            findViewById(R.id.bola_layout).setVisibility(View.VISIBLE);

            b_bola.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    String test = j_bola.getText().toString();

                    if(test.equals("")){
                        j_bola.setError("Kosong");
                    } else {
                        double d_r_bola = Double.parseDouble(String.valueOf(j_bola.getText()));

                        double volume = 4.0/3.0 * (d_r_bola * d_r_bola * d_r_bola);
                        String formatVolume = String.format("%.2f", volume);
                        h_bola.setText(formatVolume);
                    }
                }
            });
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}