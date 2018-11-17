package com.simarro.practica.jewishbank.Fragment;



import android.content.Intent;
import android.os.Bundle;
//import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.simarro.practica.aplicacionbancoanna2018.bd.MiBancoOperacional;
import com.simarro.practica.aplicacionbancoanna2018.pojo.Cliente;
import com.simarro.practica.aplicacionbancoanna2018.pojo.Cuenta;
import com.simarro.practica.jewishbank.Activity.PosicionGlobal;
import com.simarro.practica.jewishbank.Activity.TransferenciasActivity;
import com.simarro.practica.jewishbank.Adapters.AccountAdapter;
import com.simarro.practica.jewishbank.Interfaces.CuentaListener;
import com.simarro.practica.jewishbank.R;


import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class listado_cuentas extends Fragment {


    private AccountAdapter adaptadorCuentas = null;
    private ListView lstListado;
    private Cliente cli = null;
    private MiBancoOperacional mbo = null;
    private CuentaListener listener;

    public listado_cuentas() {
        // Required empty public constructor
    }

    public void setCuentaListener(CuentaListener listener) {
        this.listener = listener;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_listado_cuentas, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.lstListado = getView().findViewById(R.id.listadocuentasfragment);
        mbo = MiBancoOperacional.getInstance(this.getActivity());
        this.inicializarMovil();
    }

    public void cargarDatosCliente() {
        Cliente aux = new Cliente();
        aux.setNif(getActivity().getIntent().getStringExtra("nif"));
        cli = (Cliente) mbo.getmiBD().getClienteDAO().search(aux);
        cli.setListaCuentas(mbo.getCuentas(cli));
        for (int i = 0; i < this.cli.getListaCuentas().size(); i++) {
            this.cli.getListaCuentas().get(i).setListaMovimientos(this.mbo.getMovimientos(this.cli.getListaCuentas().get(i)));
        }
    }

    public void inicializarMovil() {
        this.cargarDatosCliente();
        this.adaptadorCuentas = new AccountAdapter<Cuenta>(this.getActivity(), R.layout.elemento_lista, this.cli.getListaCuentas());
        this.lstListado.setAdapter(adaptadorCuentas);

        this.lstListado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(listener!=null){
                    listener.onCuentaSeleccionada((Cuenta) lstListado.getAdapter().getItem(i));
                }
                //Cuenta item = (Cuenta) adaptadorCuentas.getItem(i);
                //System.out.println(item.toString());
                /*
                Intent intento=new Intent(view.getContext(),TransferenciasActivity.class);
                intento.putExtra("id",item.getId()+"");
                startActivity(intento);
                */
    }
    });

    }
    public void yeaboiii(){
        System.out.println("Yea boi");
    }
}
