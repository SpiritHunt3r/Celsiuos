package com.example.celsiuscloud.Pantallas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.celsiuscloud.Clases.Perfil;
import com.example.celsiuscloud.Fragments.ActividadesFragment;
import com.example.celsiuscloud.Fragments.FamiliaresFragment;
import com.example.celsiuscloud.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class perfilscreen extends AppCompatActivity {

    private FirebaseAuth auth;
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.familiares);
        getInfo();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_contenedor, new ActividadesFragment()).commit();


    }

    private void getInfo(){
        auth = FirebaseAuth.getInstance();
        final FirebaseUser user = auth.getCurrentUser();
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();

        final String perfil = sharedPref.getString("Nombre","");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Perfil actual = dataSnapshot.child("Usuarios").child(user.getUid()).child("Familiares").child(perfil).getValue(Perfil.class);
                ImageView avatar = findViewById(R.id.avatarshows);
                avatar.setImageResource(actual.getAvatar());
                TextView nombre = findViewById(R.id.activoTxT);
                nombre.setText(actual.getNombre());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void exitapp (View v){
        auth.signOut();
        Intent i = new Intent(perfilscreen.this, MainActivity.class);
        startActivity(i);
    }

    public void addPerfil (View v){
        //Crear acciones
    }

}
