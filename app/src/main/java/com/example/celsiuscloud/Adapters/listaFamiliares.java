package com.example.celsiuscloud.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.celsiuscloud.Clases.Perfil;
import com.example.celsiuscloud.R;

import java.util.ArrayList;

public class listaFamiliares extends  BaseAdapter{
    private ArrayList<Perfil> arrayList;
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Perfil> listPelis = null;


    public listaFamiliares(ArrayList<Perfil> arrayList, Context context) {
        this.arrayList = arrayList;
        this.listPelis = new ArrayList<>();
        this.listPelis.addAll(arrayList);
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View vistaItem = layoutInflater.inflate(R.layout.itemfamiliar, parent, false);
        ImageView tv_imagen = (ImageView) vistaItem.findViewById(R.id.tv_imagen);
        TextView tv_titulo = (TextView) vistaItem.findViewById(R.id.tv_nombre);
        TextView tv_director = (TextView) vistaItem.findViewById(R.id.tv_apellidos);
        TextView tv_calificacion = (TextView) vistaItem.findViewById(R.id.tv_sexo);

        tv_imagen.setImageResource(arrayList.get(position).getAvatar());

        tv_titulo.setText(arrayList.get(position).getNombre());
        tv_director.setText(arrayList.get(position).getApellidos());
        tv_calificacion.setText(arrayList.get(position).getSexo());
        return vistaItem;
    }



}