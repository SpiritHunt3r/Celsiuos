package com.example.celsiuscloud.Pantallas;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.celsiuscloud.Clases.Usuario;
import com.example.celsiuscloud.Email.MailJob;
import com.example.celsiuscloud.Fragments.FamiliaresFragment;
import com.example.celsiuscloud.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class clientescreen extends AppCompatActivity {

    private FirebaseAuth auth;
    private TextView activo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.familiares);
        activo = findViewById(R.id.activoTxT);
        getAvatar();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_contenedor, new FamiliaresFragment()).commit();


    }

    private void getAvatar(){
        auth = FirebaseAuth.getInstance();
        final FirebaseUser user = auth.getCurrentUser();
        activo.setText(user.getDisplayName());
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int avatarid = dataSnapshot.child("Usuarios").child(user.getUid()).child("Personal Info").child("avatar").getValue(int.class);
                ImageView avatar = findViewById(R.id.avatarshows);
                avatar.setImageResource(avatarid);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void exitapp (View v){
        auth.signOut();
        Intent i = new Intent(clientescreen.this, MainActivity.class);
        startActivity(i);
    }

    public void addPerfil (View v){
        Intent i = new Intent(clientescreen.this, createprofilescreen.class);
        startActivity(i);
    }

    public void onBLE(View v){

        Intent i = new Intent(clientescreen.this, blescreen.class);
        startActivity(i);
    }




}
