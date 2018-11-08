package com.simarro.practica.jewishbank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PostLogin extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_login);
        String nombre= getIntent().getStringExtra("user");
        String pass= getIntent().getStringExtra("pass");
        TextView tv=findViewById(R.id.nombre);
        TextView tv2=findViewById(R.id.pass);

        tv.setText(nombre);
        tv2.setText(pass);
        Button btn=findViewById(R.id.cambiarClave);
        Button btn2=findViewById(R.id.transferen);
        btn.setOnClickListener(this);
        btn2.setOnClickListener(this);

    }

    public void cerrar(View view) {
        this.finish();
    }


    @Override
    public void onClick(View v) {


        if(v.getTag().toString().equalsIgnoreCase("cambiarClaveTag")) {
            Intent intento = new Intent(this.getBaseContext(), cambiarContrasenya.class);
            startActivity(intento);
        }

        if(v.getTag().toString().equalsIgnoreCase("transferenTag")) {
            Intent intento = new Intent(this.getBaseContext(), Transferencias.class);
            startActivity(intento);
        }
    }
}
