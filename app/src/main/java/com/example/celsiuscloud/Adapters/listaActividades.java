package com.example.celsiuscloud.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.celsiuscloud.Clases.Actividad;
import com.example.celsiuscloud.Clases.Perfil;
import com.example.celsiuscloud.R;

import java.util.ArrayList;

public class listaActividades extends  BaseAdapter{
    private ArrayList<Actividad> arrayList;
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Actividad> listPelis = null;


    public listaActividades(ArrayList<Actividad> arrayList, Context context) {
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

        View vistaItem = layoutInflater.inflate(R.layout.itemactividad, parent, false);
        ImageView tv_imagen = (ImageView) vistaItem.findViewById(R.id.tv_imagen);
        TextView tv_titulo = (TextView) vistaItem.findViewById(R.id.tv_nombre);
        TextView tv_director = (TextView) vistaItem.findViewById(R.id.tv_apellidos);
        TextView tv_calificacion = (TextView) vistaItem.findViewById(R.id.tv_sexo);

        String tipo = arrayList.get(position).getTipo();
        if (tipo.equals("Nota")){
            tv_imagen.setImageResource(R.drawable.notas);
            tv_titulo.setText(arrayList.get(position).getTitulo());
        }
        else if (tipo.equals("Sintoma")){
            tv_imagen.setImageResource(R.drawable.sintomas);
            tv_titulo.setText(arrayList.get(position).getTitulo());
        }
        else if (tipo.equals("Foto")){
            tv_imagen.setImageResource(R.drawable.foto);
            tv_titulo.setText(arrayList.get(position).getTitulo());
        }
        else if (tipo.equals("Temperatura")){
            tv_imagen.setImageResource(R.drawable.temperatura);
            tv_titulo.setText(arrayList.get(position).getTitulo()+" ℃ - " + arrayList.get(position).getSintoma() );
        }

        tv_director.setText(arrayList.get(position).getFecha());
        tv_calificacion.setText(arrayList.get(position).getHora());
        return vistaItem;
    }



}