package com.example.tugasfaishal;

import android.content.Intent;
import android.os.Handler;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class splashscreen extends AppCompatActivity {
    private int waktu_loading=4000;
    //4000=4 detik

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splashscreen);
        new Handler() .postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent Intent=new Intent(splashscreen.this, StartingScreenActivity.class);

                startActivity(Intent);
                finish();
            }
        },waktu_loading);
    }
}

