package com.simarro.practica.jewishbank.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.simarro.practica.aplicacionbancoanna2018.pojo.Cuenta;
import com.simarro.practica.jewishbank.Fragment.listado_cuentas;
import com.simarro.practica.jewishbank.Fragment.listado_movimientos;
import com.simarro.practica.jewishbank.Interfaces.CuentaListener;
import com.simarro.practica.jewishbank.Interfaces.MovimientoListener;
import com.simarro.practica.jewishbank.R;

public class PosicionGlobal extends AppCompatActivity implements CuentaListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posicion_global);


        listado_cuentas cuentas= (listado_cuentas) getSupportFragmentManager().findFragmentById(R.id.fragment);
        cuentas.setCuentaListener(this);

        System.out.println("AQUI"+ cuentas);
    }


    @Override
    public void onCuentaSeleccionada(Cuenta c) {

        boolean hayMovimiento=(getSupportFragmentManager().findFragmentById(R.id.fragment3)!=null);
        if(hayMovimiento){

            ((listado_movimientos)getSupportFragmentManager().findFragmentById(R.id.fragment3)).cargarAPartirDeCuenta(c);


        }else{
            Intent intento=new Intent(this,TransferenciasActivity.class);
            intento.putExtra("id",c.getId()+"");
            startActivity(intento);
        }
    }
}
