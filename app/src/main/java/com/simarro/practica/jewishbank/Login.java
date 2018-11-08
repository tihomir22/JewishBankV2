package com.simarro.practica.jewishbank;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.simarro.practica.aplicacionbancoanna2018.bd.MiBancoOperacional;
import com.simarro.practica.aplicacionbancoanna2018.pojo.Cliente;

public class Login extends AppCompatActivity {
    MiBancoOperacional mbo=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
         mbo = MiBancoOperacional.getInstance(this);
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
                intento.putExtra("user",et.getText().toString());
                intento.putExtra("pass",et2.getText().toString());
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

    public void salir(View view) {
        finish();
    }
}
