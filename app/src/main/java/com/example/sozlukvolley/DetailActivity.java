package com.example.sozlukvolley;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    private TextView tvDetailIngilizce,tvDetailTurkce;
    private Kelimeler gelenKelimeler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvDetailIngilizce = findViewById(R.id.tvDetailIngilizce);
        tvDetailTurkce = findViewById(R.id.tvDetailTurkce);

        gelenKelimeler=(Kelimeler) getIntent().getSerializableExtra("nesne");
        Log.e("E","ingilizce "+gelenKelimeler.getIngilizce()+" turkce "+ gelenKelimeler.getTurkce());

        tvDetailIngilizce.setText(gelenKelimeler.getIngilizce());
        tvDetailTurkce.setText(gelenKelimeler.getTurkce());
    }
}