package com.simarro.practica.jewishbank.Activity;



import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.simarro.practica.aplicacionbancoanna2018.pojo.Movimiento;
import com.simarro.practica.jewishbank.Fragment.DialogoPersonalizado;
import com.simarro.practica.jewishbank.Fragment.listado_movimientos;
import com.simarro.practica.jewishbank.R;

public class TransferenciasActivity extends AppCompatActivity  {
    listado_movimientos fragment=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferencias_lista);
         this.fragment = (listado_movimientos)
                getSupportFragmentManager().findFragmentById(R.id.fragment2);

       // listado_movimientos mov=((listado_movimientos)getSupportFragmentManager().findFragmentById(R.id.fragment3));
       // mov.setMovimientoListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_listado_movimientos,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(this.fragment==null){
            Toast.makeText(this,"Hay error no se puede continuar",Toast.LENGTH_SHORT).show();
            return true;
        }
        switch (item.getItemId()){
            case R.id.itemtodos:
                Toast.makeText(this,"Has elegido todos",Toast.LENGTH_SHORT).show();
                this.fragment.filtrarMovimientos(-1);
                return true;
            case R.id.itemtipo0:
                Toast.makeText(this,"Has elegido 0",Toast.LENGTH_SHORT).show();
                this.fragment.filtrarMovimientos(0);
                return true;
            case R.id.itemtipo1:
                Toast.makeText(this,"Has elegido 1",Toast.LENGTH_SHORT).show();
                this.fragment.filtrarMovimientos(1);
                return true;

            case R.id.itemtipo2:
                Toast.makeText(this,"Has elegido 2",Toast.LENGTH_SHORT).show();
                this.fragment.filtrarMovimientos(2);
                return true;

        }
        return false;
    }




}
