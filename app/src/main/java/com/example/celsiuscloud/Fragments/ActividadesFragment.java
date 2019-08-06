package com.example.celsiuscloud.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.celsiuscloud.Adapters.listaActividades;
import com.example.celsiuscloud.Adapters.listaFamiliares;
import com.example.celsiuscloud.Clases.Actividad;
import com.example.celsiuscloud.Clases.Perfil;
import com.example.celsiuscloud.Pantallas.perfilscreen;
import com.example.celsiuscloud.Pantallas.showfoto;
import com.example.celsiuscloud.Pantallas.shownota;
import com.example.celsiuscloud.Pantallas.showsintoma;
import com.example.celsiuscloud.Pantallas.showtemperatura;
import com.example.celsiuscloud.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ActividadesFragment extends Fragment {

    private View rootView;
    private ListView familiares;
    private ArrayList<Actividad> arrayListCom;
    private listaActividades adapter;
    private SharedPreferences sharedPreferences;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_familiares, container, false);
        familiares = rootView.findViewById(R.id.LV_familiares);
        cargarLista(rootView.getContext());
        arrayListCom = new ArrayList<>();

        familiares.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView,View view, int i, long l){
                Actividad temp = (Actividad) arrayListCom.get(i);
                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(rootView.getContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("ID_Actividad",temp.getHora().concat(temp.getFecha().replace('/',' '))).commit();
                if(temp.getTipo().equals("Nota")){
                    Intent n = new Intent(getContext(), shownota.class);
                    startActivity(n);
                }
                else if (temp.getTipo().equals("Temperatura")){
                    Intent n = new Intent(getContext(), showtemperatura.class);
                    startActivity(n);
                }
                else if (temp.getTipo().equals("Sintoma")){
                    Intent n = new Intent(getContext(), showsintoma.class);
                    startActivity(n);
                }
                else if (temp.getTipo().equals("Foto")){
                    Intent n = new Intent(getContext(), showfoto.class);
                    startActivity(n);
                }


            }
        });

        return rootView;
    }




    private void cargarLista(Context context){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        final FirebaseUser user = auth.getCurrentUser();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(rootView.getContext());
        String id_perfil = sharedPreferences.getString("ID_Perfil","");
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("Usuarios").child(user.getUid()).child("Familiares").child(id_perfil).child("Actividades");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot cm: dataSnapshot.getChildren()){
                    Actividad tcm = cm.getValue(Actividad.class);
                    arrayListCom.add(tcm);
                }
                if (arrayListCom.size() != 0) {
                    //Aqui
                    adapter = new listaActividades(arrayListCom, getContext());
                    familiares.setAdapter(adapter);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
