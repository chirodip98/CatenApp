package com.example.project101;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ProgressBar pbar1;
    private static int SPLASH_SCREEN_TIME_OUT=3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        pbar1=findViewById(R.id.pBar1);
        pbar1.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pbar1.setVisibility(View.GONE);
                Intent intent =new Intent(MainActivity.this,Main3Activity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"Great to have you onboard!", Toast.LENGTH_SHORT).show();
                finish();

            }
        } ,SPLASH_SCREEN_TIME_OUT);
    }

}
