package com.example.celsiuscloud.Pantallas;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.celsiuscloud.R;

public class clientescreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.familiares);

    }

    public void buttonFloat (View v){
        Log.d("Button","HOLI");
    }

}
