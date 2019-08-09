package com.example.celsiuscloud.Pantallas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.celsiuscloud.Adapters.listaActividades;
import com.example.celsiuscloud.Clases.Actividad;
import com.example.celsiuscloud.Clases.Perfil;
import com.example.celsiuscloud.Email.MailJob;
import com.example.celsiuscloud.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class justemail extends AppCompatActivity {


    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.justemail);


    }


    public void sendemail(View v){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        final String currentDate = sdf.format(new Date());
        EditText txt = (EditText) findViewById(R.id.email);
        final String email = txt.getText().toString();
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(email);


        if (email.equals("")){
            Toast.makeText(getApplicationContext(),"Ingrese su Correo electrónico",Toast.LENGTH_SHORT).show();
        }
        else if(!(mat.matches())){
            Toast.makeText(getApplicationContext(),"Ingrese un Correo electrónico válido",Toast.LENGTH_SHORT).show();
        }
        else{

            FirebaseAuth auth = FirebaseAuth.getInstance();
            final FirebaseUser user = auth.getCurrentUser();
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            final String id_perfil = sharedPreferences.getString("ID_Perfil","");

            DatabaseReference myRef1 = FirebaseDatabase.getInstance().getReference().child("Usuarios").child(user.getUid()).child("Familiares").child(id_perfil).child("Perfil");
            myRef1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    final Perfil userprofile = dataSnapshot.getValue(Perfil.class);
                    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("Usuarios").child(user.getUid()).child("Familiares").child(id_perfil).child("Actividades");
                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String actividades = "";
                            for (DataSnapshot cm: dataSnapshot.getChildren()){
                                Actividad tcm = cm.getValue(Actividad.class);
                                actividades += tcm.toString();
                            }

                            if (actividades.equals("")) {
                                actividades = "No hay actividades registradas";
                            }

                            new MailJob("Celsiuosinfo@gmail.com", "Ccelsiuos123").execute(
                                    new MailJob.Mail("Celsiuosinfo@gmail.com", email, userprofile.getNombre() + " / " +id_perfil+" / Reporte del "+currentDate, userprofile.toString()+actividades)
                            );
                            Toast.makeText(getApplicationContext(),"Correo electrónico enviado",Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(justemail.this, perfilscreen.class);
                            startActivity(i);

                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });




        }


    }

    public void backapp (View v){
        Intent i = new Intent(justemail.this, perfilscreen.class);
        startActivity(i);
    }
}
