package com.example.celsiuscloud.Pantallas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.celsiuscloud.Clases.Actividad;
import com.example.celsiuscloud.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class addnota extends AppCompatActivity {

    private TextView fecha,hora;
    private Actividad a;
    private FirebaseUser firebaseUser;
    private FirebaseAuth auth;
    private DatabaseReference ref;
    private SharedPreferences sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnota);
        fecha = findViewById(R.id.fechatxt);
        hora = findViewById(R.id.horatxt);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = sdf.format(new Date());
        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
        String currentTime = stf.format(new Date());
        fecha.setText("Fecha: "+currentDate);

        hora.setText("Hora: "+currentTime);
        a = new Actividad();
        a.setTipo("Nota");
        a.setFecha(currentDate);
        a.setHora(currentTime);
        a.setFoto("NULL");
        auth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference();
    }

    public void addActivity (View v){
        TextView Titulo,Decp;
        Titulo = findViewById(R.id.tituloTxt);
        Decp = findViewById(R.id.desTxt);
        if (Titulo.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"Ingrese un Titulo",Toast.LENGTH_SHORT).show();
        }
        else if (Decp.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"Ingrese un Descripción",Toast.LENGTH_SHORT).show();
        }
        else{
            sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
            String id_perfil = sharedPref.getString("ID_Perfil","");
            a.setTitulo(Titulo.getText().toString());
            a.setDescripcion(Decp.getText().toString());
            firebaseUser = auth.getCurrentUser();
            ref.child("Usuarios").child(firebaseUser.getUid()).child("Familiares").child(id_perfil).child("Actividades").child(a.getFecha().replace('/',' ').concat(a.getHora())).setValue(a).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(getApplicationContext(),"Nota agregada correctamente",Toast.LENGTH_SHORT).show();
                    finish();
                    Intent i = new Intent(getApplicationContext(),perfilscreen.class);
                    startActivity(i);
                }
            });
        }
    }


    public void backapp (View v){
        Intent i = new Intent(addnota.this, perfilscreen.class);
        startActivity(i);
    }
}
