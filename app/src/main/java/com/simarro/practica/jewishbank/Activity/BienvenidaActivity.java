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
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.simarro.practica.LocaleHelper.LocaleHelper;
import com.simarro.practica.aplicacionbancoanna2018.bd.MiBD;
import com.simarro.practica.aplicacionbancoanna2018.bd.MiBancoOperacional;
import com.simarro.practica.jewishbank.R;


import java.util.Locale;

public class BienvenidaActivity extends AppCompatActivity {

    MiBancoOperacional mbo=null;
    TextView text=null;
    Button btnLogin=null;
    Button btnRegistro=null;
    Button btnSalir=null;
    Button reiniciarBD=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);
        text=findViewById(R.id.textView);
        btnLogin=findViewById(R.id.Login);
        btnRegistro=findViewById(R.id.Register);
        btnSalir=findViewById(R.id.register);
        reiniciarBD=findViewById(R.id.button);

        mbo=MiBancoOperacional.getInstance(this);
        this.updateViews();
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





    private void updateViews() {

        SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(this);

        System.out.println("" + pref.getString("origendatos", ""));
        System.out.println("" + pref.getString("color", ""));
        System.out.println("" + pref.getBoolean("videobienvenida", false));
        System.out.println("" + pref.getString("pais", ""));
        System.out.println("" + pref.getBoolean("sonido", false));

        String codigo_lenguaje=pref.getString("pais", "");
        setTitle(getResources().getString(R.string.app_name));
        Context context=null;
        if(codigo_lenguaje.equalsIgnoreCase("ESP")){
             context = LocaleHelper.setLocale(this,"es");
        }else{
             context = LocaleHelper.setLocale(this,"en");
        }

        Resources resources = context.getResources();
        //Refresco de datos
        text.setText(resources.getString(R.string.app_name));
        btnLogin.setText(resources.getString(R.string.btnlogin));
        btnRegistro.setText(resources.getString(R.string.btnregistro));
        btnSalir.setText(resources.getString(R.string.btnsalir));
        reiniciarBD.setText(resources.getString(R.string.btnreiniciarbbdd));
        setTitle(resources.getString(R.string.app_name));
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.updateViews();
    }
}
