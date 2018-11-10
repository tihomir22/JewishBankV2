package com.simarro.practica.jewishbank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.simarro.practica.aplicacionbancoanna2018.bd.MiBD;
import com.simarro.practica.aplicacionbancoanna2018.bd.MiBancoOperacional;

public class BienvenidaActivity extends AppCompatActivity {

    MiBancoOperacional mbo=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);
        mbo=MiBancoOperacional.getInstance(this);

    }

    public void llamarLogin(View view) {
        startActivity(new Intent(BienvenidaActivity.this, Login.class));
    }
    public void reiniciarbd(View view){
        mbo.getmiBD().reiniciar(MiBD.getDB());
        Toast.makeText(this,"reiniciada bbdd con exito",Toast.LENGTH_SHORT).show();
    }

    public void salir(View view) {
        finish();
    }
}
