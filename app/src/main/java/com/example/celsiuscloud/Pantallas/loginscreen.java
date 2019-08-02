package com.example.celsiuscloud.Pantallas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.celsiuscloud.Clases.Usuario;
import com.example.celsiuscloud.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class loginscreen extends AppCompatActivity {
    private EditText e1, e2;
    private SharedPreferences sharedPreferences;
    private FirebaseAuth auth;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginscreen);
        e1 = findViewById(R.id.email);
        e2 = findViewById(R.id.password);
        auth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference();
    }

    public void login(View v){

        String email = e1.getText().toString();
        String pass = e2.getText().toString();
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(email);

        if (email.equals("")){
            Toast.makeText(getApplicationContext(),"Ingrese su Correo electrónico",Toast.LENGTH_SHORT).show();
        }
        else if(!(mat.matches())){
            Toast.makeText(getApplicationContext(),"Ingrese un Correo electrónico válido",Toast.LENGTH_SHORT).show();
        }
        else if (pass.equals("")){
            Toast.makeText(getApplicationContext(),"Ingrese su Contraseña",Toast.LENGTH_SHORT).show();
        }
        else{

            auth.signInWithEmailAndPassword(email,pass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){

                                finish();

                                ref = ref.child("Usuarios").child(auth.getCurrentUser().getUid()).child("Personal Info");
                                ref.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        Usuario u = dataSnapshot.getValue(Usuario.class);
                                        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putInt("User_Avatar",u.getAvatar()).commit();
                                        finish();
                                        Intent i = new Intent(getApplicationContext(),clientescreen.class);
                                        startActivity(i);

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });


                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Credenciales incorrectas\nIntente nuevamente",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    }


}
