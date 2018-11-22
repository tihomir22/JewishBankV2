package com.simarro.practica.jewishbank.Fragment;

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
import android.widget.EditText;
import android.widget.TextView;

import com.simarro.practica.jewishbank.R;


public class DialogoPersonalizado extends DialogFragment {
    TextView textoId=null;

    public DialogoPersonalizado() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        String myTitulo = getArguments().getString("titulo");
        int myvalor = getArguments().getInt("valor");

        System.out.println(myTitulo + myvalor);
        builder.setView(inflater.inflate(R.layout.dialog_movimientos, null))
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

       //textoId=(TextView)getView().findViewById(R.id.textoIdDialogo);
        //textoId=getActivity().findViewById(R.id.textoIdDialogo);

        System.out.println(textoId.getText());


        return builder.create();

    }










}
