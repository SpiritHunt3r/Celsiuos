package com.example.celsiuscloud.Pantallas;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.celsiuscloud.Clases.Perfil;
import com.example.celsiuscloud.Clases.Usuario;
import com.example.celsiuscloud.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class avatar_profilepickerscreen extends AppCompatActivity {

    private FirebaseUser firebaseUser;
    private FirebaseAuth auth;
    private DatabaseReference ref;
    private Perfil u;
    private SharedPreferences sharedPref;
    private AlertDialog.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avatar_userpicker);
        auth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference();
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String nombre = sharedPref.getString("Nombre","");
        String apellidos = sharedPref.getString("Apellidos","");
        String fecha = sharedPref.getString("FechaNacimiento","");
        String genero = sharedPref.getString("Genero","");
        int estatura = sharedPref.getInt("Estatura",0);
        int peso = sharedPref.getInt("Peso",0);



        u = new Perfil (nombre,apellidos,fecha,genero,estatura,peso);
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Avatar Seleccionado");
        builder.setMessage("Es correcta esta decisión?");
    }

    private void cretareuser(int avt){
        u.setAvatar(avt);
        firebaseUser = auth.getCurrentUser();
        ref.child("Usuarios").child(firebaseUser.getUid()).child("Familiares").child(u.getId()).child("Perfil").setValue(u).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(getApplicationContext(),"Perfil creado correctamente",Toast.LENGTH_SHORT).show();
                finish();
                Intent i = new Intent(getApplicationContext(),clientescreen.class);
                startActivity(i);
            }
        });



    }



    public void avatarpick1(View v){
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                cretareuser(R.drawable.avatar1);
                dialog.dismiss();

            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    public void avatarpick2(View v){
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                cretareuser(R.drawable.avatar2);
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    public void avatarpick3(View v){
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                cretareuser(R.drawable.avatar3);
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    public void avatarpick4(View v){
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                cretareuser(R.drawable.avatar4);
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    public void avatarpick5(View v){
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                cretareuser(R.drawable.avatar5);
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }


    public void avatarpick6(View v){
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                cretareuser(R.drawable.avatar6);
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }


}
