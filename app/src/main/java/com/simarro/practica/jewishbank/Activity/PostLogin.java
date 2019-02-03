package com.simarro.practica.jewishbank.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.simarro.practica.LocaleHelper.LocaleHelper;
import com.simarro.practica.aplicacionbancoanna2018.bd.MiBancoOperacional;
import com.simarro.practica.aplicacionbancoanna2018.pojo.Cliente;
import com.simarro.practica.jewishbank.R;

public class PostLogin extends AppCompatActivity implements View.OnClickListener {
    MiBancoOperacional mbo=null;
    Cliente aux;
    TextView tv;
    TextView tv2;
    Menu optionsMenu;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_post_login,menu);
        optionsMenu=menu;
        SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(this);
        String codigo_lenguaje=pref.getString("pais", "");
        Context context=null;
        if(codigo_lenguaje.equalsIgnoreCase("ESP")){
            context = LocaleHelper.setLocale(this,"es");
        }else{
            context = LocaleHelper.setLocale(this,"en");
        }

        Resources resources = context.getResources();
        optionsMenu.getItem(0).setTitle(resources.getString(R.string.changePassOption));
        optionsMenu.getItem(1).setTitle(resources.getString(R.string.openTransOption));
        optionsMenu.getItem(2).setTitle(resources.getString(R.string.openGlobalPos));
        optionsMenu.getItem(3).setTitle(resources.getString(R.string.openSettings));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intento;
        switch (item.getItemId()){
            case R.id.contrasenyaitem:
                 intento =new Intent(this,cambiarContrasenya.class);
                intento.putExtra("nif", aux.getNif());
                startActivity(intento);
                return true;
            case R.id.posicionglobalitem:
                 intento =new Intent(this,PosicionGlobal.class);
                intento.putExtra("nif", aux.getNif());
                startActivity(intento);
                return true;
            case R.id.transferenciasitem:
                intento =new Intent(this,TransferenciasActivity.class);
                intento.putExtra("nif", aux.getNif());
                startActivity(intento);
                return true;

            case R.id.action_settings:
                    intento =new Intent(this,Settings.class);
                    startActivity(intento);
                    return true;

        }
        return false;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            System.out.println("tienes el foco ");
            actualizar();
        }else{
            System.out.println("perdiste el foco ");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_login);
        mbo = MiBancoOperacional.getInstance(this);

        this.updateViews();
        String pass= getIntent().getStringExtra("pass");
        String nif=getIntent().getStringExtra("nif");
        Cliente cli=new Cliente();
        cli.setNif(nif);
        cli.setClaveSeguridad(pass);



         aux = (Cliente) this.mbo.getmiBD().getClienteDAO().search(cli);

         tv=findViewById(R.id.nombre);
         tv2=findViewById(R.id.pass);

        tv.setText(nif);
        tv2.setText(pass);
        Button btn=findViewById(R.id.cambiarClave);
        Button btn2=findViewById(R.id.transferen);
        findViewById(R.id.posglobal).setOnClickListener(this);
        btn.setOnClickListener(this);
        btn2.setOnClickListener(this);

    }

    public void cerrar(View view) {
        this.finish();
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.posglobal){
            Intent intento=new Intent(this.getBaseContext(),PosicionGlobal.class);
            intento.putExtra("nif",aux.getNif());
            startActivity(intento);
        }else {

            if (v.getTag().toString().equalsIgnoreCase("cambiarClaveTag")) {
                Intent intento = new Intent(this.getBaseContext(), cambiarContrasenya.class);
                intento.putExtra("nif", aux.getNif());
                startActivity(intento);
            }

            if (v.getTag().toString().equalsIgnoreCase("transferenTag")) {
                Intent intento = new Intent(this.getBaseContext(), Transferencias.class);
                intento.putExtra("id",aux.getId()+"");
                startActivity(intento);
            }
        }

    }
    public void actualizar(){
        this.aux= (Cliente) this.mbo.getmiBD().getClienteDAO().search(aux);
        tv.setText(aux.getNif());
        tv2.setText(aux.getClaveSeguridad());
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
        setTitle(resources.getString(R.string.app_name));

    }

    @Override
    protected void onResume() {
        super.onResume();
        this.updateViews();
    }
}
