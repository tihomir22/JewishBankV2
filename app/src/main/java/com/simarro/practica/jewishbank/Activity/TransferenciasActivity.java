package com.simarro.practica.jewishbank.Activity;



import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.simarro.practica.aplicacionbancoanna2018.pojo.Movimiento;
import com.simarro.practica.jewishbank.Fragment.DialogoPersonalizado;
import com.simarro.practica.jewishbank.R;

public class TransferenciasActivity extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferencias_lista);

       // listado_movimientos mov=((listado_movimientos)getSupportFragmentManager().findFragmentById(R.id.fragment3));
       // mov.setMovimientoListener(this);

    }




}
