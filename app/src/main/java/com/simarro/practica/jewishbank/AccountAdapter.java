package com.simarro.practica.jewishbank;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.simarro.practica.aplicacionbancoanna2018.pojo.Cuenta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AccountAdapter<T> extends ArrayAdapter<T> {

    public AccountAdapter(Context context, int elemento_lista, ArrayList<T> objects) {
        super(context, elemento_lista,objects);
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {

        LayoutInflater inflater= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View listItemView=convertView;

        if(convertView==null){
            listItemView=inflater.inflate(R.layout.elemento_lista,parent,false);
        }

        TextView titulo=(TextView)listItemView.findViewById(R.id.textprincipal1);
        TextView subtitulo=(TextView)listItemView.findViewById(R.id.textsecundario1);
        ImageView imagen=(ImageView)listItemView.findViewById(R.id.imagenLista);

        Cuenta cuenta1= (Cuenta) getItem(position);

        titulo.setText("Cuenta num "+cuenta1.getNumeroCuenta()+ "\t sucursal "+cuenta1.getSucursal());
        subtitulo.setText("Saldo actual " + ((double) cuenta1.getSaldoActual())+ " \t con "+ cuenta1.getListaMovimientos().size()+" Movimientos");

        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        imagen.setBackgroundColor(color);
        return listItemView;
    }




}

