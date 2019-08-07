package com.example.celsiuscloud.Fragments;

import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.celsiuscloud.Adapters.listaFamiliares;
import com.example.celsiuscloud.Clases.Perfil;
import com.example.celsiuscloud.Pantallas.clientescreen;
import com.example.celsiuscloud.Pantallas.perfilscreen;
import com.example.celsiuscloud.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class FamiliaresFragment extends Fragment {

    private View rootView;
    private ListView familiares;
    private ArrayList<Perfil> arrayListCom;
    private listaFamiliares adapter;
    private SharedPreferences sharedPreferences;
    private FirebaseAuth auth;

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
                Perfil temp = (Perfil) arrayListCom.get(i);
                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(rootView.getContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("ID_Perfil",temp.getId()).commit();
                Intent n = new Intent(getContext(), perfilscreen.class);
                startActivity(n);

            }
        });

        familiares.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final String perfil = arrayListCom.get(position).getId();
                new AlertDialog.Builder(getActivity())
                        .setIcon(android.R.drawable.ic_menu_delete)
                        .setTitle("Eliminando Perfil")
                        .setMessage("Desea eliminar el perfil de "+ arrayListCom.get(position).getNombre() +"?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                auth = FirebaseAuth.getInstance();
                                final FirebaseUser user = auth.getCurrentUser();
                                DatabaseReference myref = FirebaseDatabase.getInstance().getReference().child("Usuarios").child(user.getUid()).child("Familiares").child(perfil);
                                myref.removeValue();
                                Toast.makeText(getContext(),"Se ha eliminado el perfil de "+arrayListCom.get(position).getNombre(),Toast.LENGTH_SHORT).show();
                                Intent j = new Intent(getContext(), clientescreen.class);
                                startActivity(j);
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                return true;
            }
        });

        return rootView;
    }




    private void cargarLista(Context context){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        final FirebaseUser user = auth.getCurrentUser();
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("Usuarios").child(user.getUid()).child("Familiares");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot cm: dataSnapshot.getChildren()){
                    Perfil tcm = cm.child("Perfil").getValue(Perfil.class);
                    arrayListCom.add(tcm);
                }
                if (arrayListCom.size() != 0) {
                    //Aqui
                    adapter = new listaFamiliares(arrayListCom, getContext());
                    familiares.setAdapter(adapter);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
