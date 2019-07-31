package com.example.celsiuscloud.Pantallas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.celsiuscloud.Clases.Usuario;
import com.example.celsiuscloud.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class registerscreen extends AppCompatActivity {


    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerscreen);


    }

    public void openLogin(View v){
        Intent i = new Intent(this,loginscreen.class);
        startActivity(i);
    }

    public void registrarUsuario(View v){
        EditText Nombre = findViewById(R.id.nameTxt);
        EditText Apellidos = findViewById(R.id.lastnameTxt);
        EditText Email = findViewById(R.id.emailTxt);
        EditText Password = findViewById(R.id.passwordTxt);
        EditText Password2 = findViewById(R.id.password2Txt);
        Switch Terminos = findViewById(R.id.termsSwitch);
        Switch Data = findViewById(R.id.dataSwitch);

        String email = Email.getText().toString();
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(email);

        if (Nombre.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"Ingrese su Nombre",Toast.LENGTH_SHORT).show();
        }
        else if (Apellidos.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"Ingrese sus Apellidos",Toast.LENGTH_SHORT).show();
        }
        else if (Email.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"Ingrese su Correo electrónico",Toast.LENGTH_SHORT).show();
        }
        else if(!(mat.matches())){
            Toast.makeText(getApplicationContext(),"Ingrese un Correo electrónico válido",Toast.LENGTH_SHORT).show();
        }
        else if (Password.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"Ingrese una Contraseña",Toast.LENGTH_SHORT).show();
        }
        else if (Password2.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"Verifique su Contraseña",Toast.LENGTH_SHORT).show();
        }
        else if (!(Password.getText().toString().equals(Password2.getText().toString()))){
            Toast.makeText(getApplicationContext(),"Las contraseñas no coinciden",Toast.LENGTH_SHORT).show();
        }
        else if (!(Terminos.isChecked())){
            Toast.makeText(getApplicationContext(),"Debe aceptar los Términos y Condiciones",Toast.LENGTH_SHORT).show();
        }
        else if (!(Data.isChecked())){
            Toast.makeText(getApplicationContext(),"Debe aceptar el Uso de Información",Toast.LENGTH_SHORT).show();
        }
        else{
            final Usuario n = new Usuario(Nombre.getText().toString(),Apellidos.getText().toString());
            String ActualEmail = email;
            String ActualPass = Password.getText().toString();

            sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("Nombre",Nombre.getText().toString());
            editor.putString("Apellidos",Apellidos.getText().toString());
            editor.putString("Email",ActualEmail);
            editor.putString("Pass",ActualPass);
            editor.commit();
            Intent i = new Intent(this,avatar_userpickerscreen.class);
            startActivity(i);



        }
    }
}
