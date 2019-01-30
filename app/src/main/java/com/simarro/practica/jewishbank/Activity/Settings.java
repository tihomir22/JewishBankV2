package com.simarro.practica.jewishbank.Activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.simarro.practica.jewishbank.R;

public class Settings extends PreferenceActivity  {

    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(android.R.id.content, new PreferenciasFragment());
        ft.commit();
         pref = PreferenceManager.getDefaultSharedPreferences(this);



    }


    public static class PreferenciasFragment extends PreferenceFragment {
        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.opciones);
            //Change Application level locale


        }
    }

    @Override
    public void onBackPressed() {
        System.out.println(""+pref.getString("origendatos",""));
        System.out.println(""+pref.getString("color",""));
        System.out.println(""+pref.getBoolean("videobienvenida",false));
        System.out.println(""+pref.getString("pais",""));
        System.out.println(""+pref.getBoolean("sonido",false));
        SharedPreferences.Editor editor=pref.edit();

        editor.putString("origendatos",pref.getString("origendatos",""));
        editor.putString("color",pref.getString("color",""));
        editor.putBoolean("videobienvenida",pref.getBoolean("videobienvenida",false));
        editor.putString("pais",pref.getString("pais",""));
        editor.putBoolean("sonido",pref.getBoolean("sonido",false));

        editor.commit();



        super.onBackPressed();
    }
}
