package com.simarro.practica.jewishbank.Fragment;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;

//import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.simarro.practica.aplicacionbancoanna2018.pojo.Movimiento;
import com.simarro.practica.jewishbank.R;


public class DialogoPersonalizado extends DialogFragment implements View.OnClickListener {
    TextView textoId=null;
    TextView descripcion=null;
    TextView descripcion2=null;
    public DialogoPersonalizado() {
    }

    @SuppressLint("ResourceType")
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();



        View view  = getActivity().getLayoutInflater().inflate(R.layout.dialog_movimientos, null);
        Movimiento myTitulo = (Movimiento) getArguments().getSerializable("obj");



        /*builder.setView(view)

                .setPositiveButton(R.id.dembow, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });*/
        builder .setView(view);

       //textoId=(TextView)getView().findViewById(R.id.textoIdDialogo);
        //textoId=getActivity().findViewById(R.id.textoIdDialogo);
            textoId=view.findViewById(R.id.textoIdDialogo);
            descripcion=view.findViewById(R.id.textoDatosDialogo);
            descripcion2=view.findViewById(R.id.textoDatosDialogo2);
            Button btnAceptar=view.findViewById(R.id.dembow);
            Button btnCancelar=view.findViewById(R.id.dembow2);

            textoId.setText("id: "+myTitulo.getId()+"");
            descripcion.setText("Descripcion: "+myTitulo.getDescripcion()+"\n Importe: "+myTitulo.getImporte()+"\n Tipo:"+myTitulo.getTipo());
            descripcion2.setText("Destino: "+myTitulo.getCuentaDestino().getNumeroCuenta()+"\n"+"Origen: "+myTitulo.getCuentaOrigen().getNumeroCuenta());
            btnCancelar.setOnClickListener(this);
            btnAceptar.setOnClickListener(this);
            //System.out.println(textoId.getText());


        return builder.create();

    }


    @Override
    public void onClick(View v) {
        getDialog().cancel();
    }
}
