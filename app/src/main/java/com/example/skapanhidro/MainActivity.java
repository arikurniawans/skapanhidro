package com.example.skapanhidro;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent home=new Intent(MainActivity.this, MenuApps.class);
                    startActivity(home);
                    finish();

                }
            },SPLASH_TIME_OUT);
        }
    }
