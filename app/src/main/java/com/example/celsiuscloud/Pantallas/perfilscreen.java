package com.example.celsiuscloud.Pantallas;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.celsiuscloud.Clases.Perfil;
import com.example.celsiuscloud.Email.MailJob;
import com.example.celsiuscloud.Fragments.ActividadesFragment;
import com.example.celsiuscloud.Fragments.FamiliaresFragment;
import com.example.celsiuscloud.R;
import com.google.android.material.internal.NavigationMenu;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.github.yavski.fabspeeddial.FabSpeedDial;
import io.github.yavski.fabspeeddial.SimpleMenuListenerAdapter;

import static androidx.core.os.LocaleListCompat.create;
import static java.security.AccessController.getContext;

public class perfilscreen extends AppCompatActivity {

    private FirebaseAuth auth;
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividades);

        getInfo();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_contenedor, new ActividadesFragment()).commit();

        FabSpeedDial fabSpeedDial = (FabSpeedDial) findViewById(R.id.fab_speed_dial);
        fabSpeedDial.setMenuListener(new SimpleMenuListenerAdapter() {
            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
                // TODO: Do something with yout menu items, or return false if you don't want to show them
                return true;
            }

            @Override
            public boolean onMenuItemSelected(MenuItem menuItem){
                String option = menuItem.getTitle().toString();
                if (option.equals("Nota")){
                    Intent i = new Intent(perfilscreen.this, addnota.class);
                    startActivity(i);
                }
                else if (option.equals("Sintoma")){
                    Intent i = new Intent(perfilscreen.this, addsintoma.class);
                    startActivity(i);
                }
                else if (option.equals("Foto")){
                    Intent i = new Intent(perfilscreen.this, addfoto.class);
                    startActivity(i);
                }
                else if (option.equals("Temperatura")){
                    Intent i = new Intent(perfilscreen.this, addtemperatura.class);
                    startActivity(i);
                }


                return true;
            }
        });
    }

    private void getInfo(){
        auth = FirebaseAuth.getInstance();
        final FirebaseUser user = auth.getCurrentUser();
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        final String perfil = sharedPref.getString("ID_Perfil","");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Perfil actual = dataSnapshot.child("Usuarios").child(user.getUid()).child("Familiares").child(perfil).child("Perfil").getValue(Perfil.class);
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

    public void backapp (View v){

        Intent i = new Intent(perfilscreen.this, clientescreen.class);
        startActivity(i);
    }


    public void sendInfo (View v){

        Intent i = new Intent(perfilscreen.this, justemail.class);
        startActivity(i);

    }




}
