package com.example.celsiuscloud.Pantallas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.celsiuscloud.Clases.Actividad;
import com.example.celsiuscloud.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class showtemperatura extends AppCompatActivity {

    private TextView fecha,hora;
    private EditText titulo,descp;
    private FirebaseAuth auth;
    private SharedPreferences sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showtemperatura);
        fecha = findViewById(R.id.fechatxt);
        hora = findViewById(R.id.horatxt);
        titulo = findViewById(R.id.tituloTxt);
        descp = findViewById(R.id.desTxt);

        auth = FirebaseAuth.getInstance();
        final FirebaseUser user = auth.getCurrentUser();
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        final String perfil = sharedPref.getString("ID_Perfil","");
        final String act = sharedPref.getString("ID_Actividad","");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Actividad actual = dataSnapshot.child("Usuarios").child(user.getUid()).child("Familiares").child(perfil).child("Actividades").child(act).getValue(Actividad.class);
                fecha.setText("Fecha: "+actual.getFecha());
                hora.setText("Hora: "+actual.getHora());
                titulo.setText(actual.getTitulo() +" ℃");
                descp.setText(actual.getDescripcion());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }



    public void backapp (View v){
        Intent i = new Intent(showtemperatura.this, perfilscreen.class);
        startActivity(i);
    }
}
