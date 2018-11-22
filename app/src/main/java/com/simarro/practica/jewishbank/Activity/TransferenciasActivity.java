package com.simarro.practica.jewishbank.Activity;



import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.simarro.practica.aplicacionbancoanna2018.bd.MiBancoOperacional;
import com.simarro.practica.aplicacionbancoanna2018.pojo.Cuenta;
import com.simarro.practica.aplicacionbancoanna2018.pojo.Movimiento;
import com.simarro.practica.jewishbank.Fragment.DialogoPersonalizado;
import com.simarro.practica.jewishbank.Fragment.listado_movimientos;
import com.simarro.practica.jewishbank.Interfaces.MovimientoListener;
import com.simarro.practica.jewishbank.R;
import com.simarro.practica.jewishbank.Adapters.TransferAdapter;

import java.util.ArrayList;

public class TransferenciasActivity extends AppCompatActivity implements MovimientoListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferencias_lista);

       // listado_movimientos mov=((listado_movimientos)getSupportFragmentManager().findFragmentById(R.id.fragment3));
       // mov.setMovimientoListener(this);

    }

    @Override
    public void onMovimientoSeleccionada(Movimiento m) {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        DialogoPersonalizado dialogo = newInstance("jerk",1488);
        dialogo.show(fragmentManager, "tagAlerta");
    }

    static DialogoPersonalizado newInstance(String nombre, int num) {

        DialogoPersonalizado f = new DialogoPersonalizado();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putString("titulo", nombre);
        args.putInt("valor", num);
        f.setArguments(args);



        return f;
    }
}
