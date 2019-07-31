package com.example.celsiuscloud.Pantallas;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.celsiuscloud.Clases.Usuario;
import com.example.celsiuscloud.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import in.goodiebag.carouselpicker.CarouselPicker;

public class avatar_userpickerscreen extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference ref;
    private FirebaseUser firebaseUser;
    private Usuario u;
    private SharedPreferences sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avatar_userpicker);
        mAuth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference();
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String nombre = sharedPref.getString("Nombre","");
        String apellidos = sharedPref.getString("Apellidos","");
        String email = sharedPref.getString("Email","");
        String pass = sharedPref.getString("Pass","");
        u = new Usuario(nombre,apellidos);

    }


    /*mAuth.createUserWithEmailAndPassword(ActualEmail,ActualPass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                firebaseUser = mAuth.getCurrentUser();

                                ref.child("Usuarios").child(firebaseUser.getUid()).child("Personal Info").setValue(n);
                                Toast.makeText(getApplicationContext(),"Usuario creado correctamente",Toast.LENGTH_SHORT).show();
                                finish();
                                Intent i = new Intent(getApplicationContext(),loginscreen.class);
                                startActivity(i);
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"No es posible crear el usuario",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });*/
}
