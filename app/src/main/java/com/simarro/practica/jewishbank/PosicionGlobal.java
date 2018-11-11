package com.simarro.practica.jewishbank;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.simarro.practica.aplicacionbancoanna2018.bd.MiBancoOperacional;
import com.simarro.practica.aplicacionbancoanna2018.pojo.Cliente;
import com.simarro.practica.aplicacionbancoanna2018.pojo.Cuenta;

public class PosicionGlobal extends AppCompatActivity {
    AccountAdapter<Cuenta>adaptadorCuentasV2=null;
    MiBancoOperacional mbo=null;
    Cliente cli=null;
    ListView list=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posicion_global);
        mbo=MiBancoOperacional.getInstance(this);
        this.cargarDatosCliente();
        System.out.println("Hay "+this.cli.getListaCuentas().get(0).getListaMovimientos().size()+" movimientos");

        this.adaptadorCuentasV2=new AccountAdapter<Cuenta>(this,R.layout.elemento_lista,this.cli.getListaCuentas());
        list=findViewById(R.id.listview1);
        list.setAdapter(this.adaptadorCuentasV2);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cuenta item = adaptadorCuentasV2.getItem(i);
                //System.out.println(item.toString());
                Intent intento=new Intent(view.getContext(),TransferenciasActivity.class);
                intento.putExtra("id",item.getId()+"");
                startActivity(intento);
            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }



    public void cargarDatosCliente(){
        Cliente aux=new Cliente();
        aux.setNif(getIntent().getStringExtra("nif"));
        cli= (Cliente) mbo.getmiBD().getClienteDAO().search(aux);
        cli.setListaCuentas(mbo.getCuentas(cli));
        for (int i=0;i<this.cli.getListaCuentas().size();i++){
            this.cli.getListaCuentas().get(i).setListaMovimientos(this.mbo.getMovimientos(this.cli.getListaCuentas().get(i)));
        }
    }
}
