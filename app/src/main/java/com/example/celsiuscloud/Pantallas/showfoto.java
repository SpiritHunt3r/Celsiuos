package com.example.celsiuscloud.Pantallas;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.celsiuscloud.Clases.Actividad;
import com.example.celsiuscloud.Dowloaders.ImageDownloadTask;
import com.example.celsiuscloud.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ExecutionException;


public class showfoto extends AppCompatActivity {

    private TextView fecha,hora;
    private EditText titulo;
    private ImageView img;
    private FirebaseAuth auth;
    private SharedPreferences sharedPref;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showfoto);
        fecha = findViewById(R.id.fechatxt);
        hora = findViewById(R.id.horatxt);
        titulo = findViewById(R.id.tituloTxt);
        img = findViewById(R.id.fotoselected);

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
                titulo.setText(actual.getTitulo());
                ImageDownloadTask downloadTask = new ImageDownloadTask();
                try {
                    Bitmap result = downloadTask.execute(actual.getFoto()).get();
                    img.setImageBitmap(result);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }




    public void backapp (View v){
        Intent i = new Intent(showfoto.this, perfilscreen.class);
        startActivity(i);
    }
}
