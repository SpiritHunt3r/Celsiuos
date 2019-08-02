package com.example.celsiuscloud.Pantallas;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.celsiuscloud.R;

public class MainActivity extends AppCompatActivity {

    private static int TIME_OUT = 1800; //Time to launch the another activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, startscreen.class);
                startActivity(i);
                finish();
            }
        }, TIME_OUT);
    }
}
