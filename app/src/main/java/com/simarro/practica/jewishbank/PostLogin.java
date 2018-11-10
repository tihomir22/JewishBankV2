package com.simarro.practica.jewishbank;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.simarro.practica.aplicacionbancoanna2018.bd.MiBancoOperacional;
import com.simarro.practica.aplicacionbancoanna2018.pojo.Cliente;

public class PostLogin extends AppCompatActivity implements View.OnClickListener {
    MiBancoOperacional mbo=null;
    Cliente aux;
    TextView tv;
    TextView tv2;

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
                startActivity(intento);
            }
        }

    }
    public void actualizar(){
        this.aux= (Cliente) this.mbo.getmiBD().getClienteDAO().search(aux);
        tv.setText(aux.getNif());
        tv2.setText(aux.getClaveSeguridad());
    }
}
