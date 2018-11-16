package com.simarro.practica.jewishbank.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.simarro.practica.aplicacionbancoanna2018.pojo.Cuenta;
import com.simarro.practica.aplicacionbancoanna2018.pojo.Movimiento;
import com.simarro.practica.jewishbank.R;

import java.util.ArrayList;
import java.util.Random;

public class TransferAdapter<T> extends ArrayAdapter<T> {

    public TransferAdapter(Context context, int elemento_lista, ArrayList<T> objects) {
        super(context, elemento_lista, objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View listItemView = convertView;

        if (convertView == null) {
            listItemView = inflater.inflate(R.layout.elemento_lista, parent, false);
        }

        TextView titulo = (TextView) listItemView.findViewById(R.id.textprincipal1);
        TextView subtitulo = (TextView) listItemView.findViewById(R.id.textsecundario1);
        ImageView imagen = (ImageView) listItemView.findViewById(R.id.imagenLista);

        Movimiento movimiento = (Movimiento) getItem(position);
        imagen.setImageResource(R.drawable.profit);
        titulo.setText("Tipo " + movimiento.getTipo() + " fecha " + movimiento.getFechaOperacion()+" Importe " + ((double) movimiento.getImporte()));
        subtitulo.setText(  " descripcion " + movimiento.getDescripcion());

        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        imagen.setBackgroundColor(color);
        titulo.setTextColor(Color.parseColor("#FFFFFF") );
        subtitulo.setTextColor(Color.parseColor("#FFFFFF") );

        if(movimiento.getImporte()<0){
            listItemView.setBackgroundColor(Color.parseColor("#FF4136"));
        }else{
            listItemView.setBackgroundColor(Color.parseColor("#2ECC40"));
        }
        return listItemView;
    }
}
