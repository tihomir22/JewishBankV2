package com.simarro.practica.jewishbank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.simarro.practica.aplicacionbancoanna2018.bd.MiBancoOperacional;
import com.simarro.practica.aplicacionbancoanna2018.pojo.Cuenta;
import com.simarro.practica.aplicacionbancoanna2018.pojo.Movimiento;

import java.util.ArrayList;

public class TransferenciasActivity extends AppCompatActivity {
    MiBancoOperacional mbo=null;
    TransferAdapter adaptador=null;
    ListView lista=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferencias_lista);
        String id= getIntent().getStringExtra("id");
        System.out.println("jerk it bra "+id);
        mbo=MiBancoOperacional.getInstance(this);
        Cuenta c=new Cuenta();
        c.setId(Integer.parseInt(id.trim()));
        ArrayList<Movimiento> listamov=mbo.getMovimientos(c);
        System.out.println("tiene "+listamov.size());
        if(listamov.size()>0){
            lista=findViewById(R.id.listatransferencias1);
            adaptador=new TransferAdapter(this,R.layout.elemento_lista,listamov);
            lista.setAdapter(adaptador);
            lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(view.getContext(),adaptador.getItem(i).toString(),Toast.LENGTH_SHORT).show();
                }
                });
        }

    }
}
