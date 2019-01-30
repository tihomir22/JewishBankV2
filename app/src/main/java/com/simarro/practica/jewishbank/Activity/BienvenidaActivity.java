package com.simarro.practica.jewishbank.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;

import com.simarro.practica.aplicacionbancoanna2018.bd.MiBD;
import com.simarro.practica.aplicacionbancoanna2018.bd.MiBancoOperacional;
import com.simarro.practica.jewishbank.R;


import java.util.Locale;

public class BienvenidaActivity extends AppCompatActivity {

    MiBancoOperacional mbo=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);
        mbo=MiBancoOperacional.getInstance(this);
        this.comprobarIdioma();
        System.out.println(getResources().getString(R.string.app_name));

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




    private void comprobarIdioma(){
       // SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(this);

        String idioma = prefs.getString("pais", "error");

        System.out.println("recibido " + idioma);
        if(!idioma.equals("error")){
            if(idioma.equals("ESP")){
                Locale localizacion = new Locale("es", "ES");
                Locale.setDefault(localizacion);
                Configuration config = new Configuration();
                config.locale = localizacion;
                getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
            }else{
                Locale localizacion = new Locale("en", "US");
                Locale.setDefault(localizacion);
                Configuration config = new Configuration();
                config.locale = localizacion;
                getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
            }
        }

    }


}
