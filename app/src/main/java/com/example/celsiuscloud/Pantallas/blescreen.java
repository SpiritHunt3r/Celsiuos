package com.example.celsiuscloud.Pantallas;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.celsiuscloud.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class blescreen extends AppCompatActivity {


    public static final String TAG = "bt-pairing-test";
    BluetoothAdapter mBluetoothAdapter = null;
    private static final Set<String> TARGET_DEVICE_NAME = new HashSet<String>();

    static {
        //todo: add target device name after getting device name with startDiscovery
        TARGET_DEVICE_NAME.add("BT-S15");
    }

    private List<BluetoothDevice> mTargetDevices = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bleinstruccion);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            Log.d(TAG, "BT not supported..");
            return;
        }

        if(!mBluetoothAdapter.isEnabled()){
            Log.d(TAG, "BT enable.");
            mBluetoothAdapter.enable();
        }
        else{
            Log.d(TAG, "BT already enabled.");
        }

    }

    public void backapp (View v){
        Intent i = new Intent(blescreen.this, clientescreen.class);
        startActivity(i);
    }

    public void nextapp (View v){
        Intent i = new Intent(blescreen.this, blescreen2.class);
        startActivity(i);
    }
}
