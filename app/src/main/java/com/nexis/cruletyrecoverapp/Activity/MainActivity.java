package com.nexis.cruletyrecoverapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.nexis.cruletyrecoverapp.R;

public class MainActivity extends AppCompatActivity {

    private Object KayitOl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        new CountDownTimer(4000, 1000) {

            public void onTick(long millisUntilFinished) {}

            public void onFinish() {
                Intent intent = new Intent(MainActivity.this, GirisYapActivity.class);
                startActivity(intent);
            }
        }.start();

    }
}