package com.simarro.practica.jewishbank.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.simarro.practica.aplicacionbancoanna2018.bd.MiBancoOperacional;
import com.simarro.practica.aplicacionbancoanna2018.pojo.Cliente;
import com.simarro.practica.aplicacionbancoanna2018.pojo.Cuenta;
import com.simarro.practica.jewishbank.Activity.PosicionGlobal;
import com.simarro.practica.jewishbank.Adapters.AccountAdapter;
import com.simarro.practica.jewishbank.R;


public class listado_transferencias_cuenta extends Fragment {



    public listado_transferencias_cuenta() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_listado_cuentas, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


}
