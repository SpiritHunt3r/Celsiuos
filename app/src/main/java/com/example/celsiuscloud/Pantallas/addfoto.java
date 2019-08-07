package com.example.celsiuscloud.Pantallas;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.PermissionRequest;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.celsiuscloud.Clases.Actividad;
import com.example.celsiuscloud.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;


public class addfoto extends AppCompatActivity {

    private TextView fecha,hora;
    private Actividad a;
    private FirebaseUser firebaseUser;
    private FirebaseAuth auth;
    private DatabaseReference rootRefence;
    private SharedPreferences sharedPref;
    private ImageView imageView;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private Uri filePath;

    private final int PICK_IMAGE_REQUEST = 71;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addfoto);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        auth = FirebaseAuth.getInstance();
        rootRefence = FirebaseDatabase.getInstance().getReference();
        fecha = findViewById(R.id.fechatxt);
        hora = findViewById(R.id.horatxt);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = sdf.format(new Date());
        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
        String currentTime = stf.format(new Date());
        fecha.setText("Fecha: "+currentDate);
        filePath = null;
        hora.setText("Hora: "+currentTime);
        imageView = findViewById(R.id.fotoselected);
        a = new Actividad();
        a.setTipo("Foto");
        a.setFecha(currentDate);
        a.setHora(currentTime);

    }

    public void addActivity (View v){
        TextView Titulo;
        Titulo = findViewById(R.id.tituloTxt);
        if (Titulo.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"Ingrese un Titulo",Toast.LENGTH_SHORT).show();
        }
        else if (filePath == null){
            Toast.makeText(getApplicationContext(),"Ingrese una Foto",Toast.LENGTH_SHORT).show();
        }
        else{
            sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
            final String id_perfil = sharedPref.getString("ID_Perfil","");
            a.setTitulo(Titulo.getText().toString());
            firebaseUser = auth.getCurrentUser();
            final String ID = UUID.randomUUID().toString();
            final String dir = "portadas/"+ ID;
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading Picture...");
            progressDialog.show();
            StorageReference ref = storageReference.child(dir);
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            a.setFoto("https://firebasestorage.googleapis.com/v0/b/baby-f2523.appspot.com/o/portadas%2F"+ID+"?alt=media");
                            rootRefence.child("Usuarios").child(firebaseUser.getUid()).child("Familiares").child(id_perfil).child("Actividades").child(a.getFecha().replace('/',' ').concat(a.getHora())).setValue(a)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(getApplicationContext(), "Foto Agregada Correctamente", Toast.LENGTH_SHORT).show();
                                            finish();
                                            Intent i = new Intent(getApplicationContext(),perfilscreen.class);
                                            startActivity(i);
                                        }
                                    });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }

    public void pickimage(View v){
        chooseImage();
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }


    public void backapp (View v){
        Intent i = new Intent(addfoto.this, perfilscreen.class);
        startActivity(i);
    }
}
