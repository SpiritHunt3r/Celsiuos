package com.example.celsiuscloud.Pantallas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
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

public class addtemperatura extends AppCompatActivity {

    private TextView fecha,hora;
    private Actividad a;
    private FirebaseUser firebaseUser;
    private FirebaseAuth auth;
    private DatabaseReference ref;
    private Spinner spinner;
    private SharedPreferences sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addtemperture);
        fecha = findViewById(R.id.fechatxt);
        hora = findViewById(R.id.horatxt);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = sdf.format(new Date());
        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
        String currentTime = stf.format(new Date());
        fecha.setText("Fecha: "+currentDate);

        hora.setText("Hora: "+currentTime);
        a = new Actividad();
        a.setTipo("Temperatura");
        a.setFecha(currentDate);
        a.setHora(currentTime);

        auth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference();

        spinner = (Spinner) findViewById(R.id.tipos);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.tiposTemp, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void addActivity (View v){
        TextView Titulo,Decp;
        Titulo = findViewById(R.id.tituloTxt);
        Decp = findViewById(R.id.desTxt);
        if (Titulo.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"Ingrese una Temperatura",Toast.LENGTH_SHORT).show();
        }
        else{
            sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
            String id_perfil = sharedPref.getString("ID_Perfil","");
            a.setTitulo(Titulo.getText().toString());
            a.setDescripcion(Decp.getText().toString());
            a.setSintoma(spinner.getSelectedItem().toString());
            firebaseUser = auth.getCurrentUser();
            ref.child("Usuarios").child(firebaseUser.getUid()).child("Familiares").child(id_perfil).child("Actividades").child(a.getHora().concat(a.getFecha().replace('/',' '))).setValue(a).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(getApplicationContext(),"Temperatura agregada correctamente",Toast.LENGTH_SHORT).show();
                    finish();
                    Intent i = new Intent(getApplicationContext(),perfilscreen.class);
                    startActivity(i);
                }
            });
        }
    }


    public void backapp (View v){
        Intent i = new Intent(addtemperatura.this, perfilscreen.class);
        startActivity(i);
    }
}
