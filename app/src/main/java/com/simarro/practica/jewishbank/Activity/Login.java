package com.simarro.practica.jewishbank.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.simarro.practica.LocaleHelper.LocaleHelper;
import com.simarro.practica.aplicacionbancoanna2018.bd.MiBancoOperacional;
import com.simarro.practica.aplicacionbancoanna2018.pojo.Cliente;
import com.simarro.practica.jewishbank.R;

public class Login extends AppCompatActivity {
    MiBancoOperacional mbo=null;
    TextView text1=null;
    TextView text2=null;
    EditText editTextUser=null;
    EditText editTextPass=null;
    Button btnLogin=null;
    Button btnSalir=null;
    LinearLayout linear=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
         mbo = MiBancoOperacional.getInstance(this);
        linear=findViewById(R.id.linearLayout3);
        text1=linear.findViewById(R.id.dembow1);

        text2=linear.findViewById(R.id.dembow2);
        editTextUser=linear.findViewById(R.id.editText);
        editTextPass=linear.findViewById(R.id.editText2);
        btnLogin=linear.findViewById(R.id.btnLogin);
        btnSalir=linear.findViewById(R.id.btnSalir);
    }





    public void logearse(View view) {
        EditText et=findViewById(R.id.editText);
        EditText et2=findViewById(R.id.editText2);

        if (et.getText().toString().length()>0 && et2.getText().toString().length()>0){
            Cliente c=new Cliente();
            c.setNif(et.getText().toString());
            c.setClaveSeguridad(et2.getText().toString());
            if(mbo.login(c)!=null){
                Intent intento=new Intent(this.getBaseContext(),PostLogin.class);
                intento.putExtra("nif",et.getText().toString());
                intento.putExtra("pass",et2.getText().toString());
                //Cliente aux = (Cliente) mbo.getmiBD().getClienteDAO().search(c);
                startActivity(intento);
            }else{
                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                builder.setMessage("Credenciales incorrectas")
                        .setTitle("Error de credenciales");

                builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }

        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
            builder.setMessage("Debes introducir nombre y contraseña para acceder al programa de JewishBank")
                    .setTitle("Error de credenciales");

            builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }




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
        text1.setText(resources.getString(R.string.dnilogin));
        text2.setText(resources.getString(R.string.passlogin));
        editTextUser.setHint(resources.getString(R.string.dniInput));
        editTextPass.setHint(resources.getString(R.string.passInput));
        btnLogin.setText(resources.getString(R.string.btnlogin));
        btnSalir.setText(resources.getString(R.string.btnsalir));
        setTitle(resources.getString(R.string.app_name));
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.updateViews();
    }

    public void salir(View view) {
        finish();
    }
}
