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
import com.simarro.practica.jewishbank.util.LocaleHelper;

public class Settings extends PreferenceActivity  {

    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(android.R.id.content, new PreferenciasFragment());
        ft.commit();

        prefs=getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);


        //LocaleHelper.setLocale(Settings.this, "en");

        //It is required to recreate the activity to reflect the change in UI.
       // recreate();

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
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(Settings.this);
        System.out.println(""+pref.getString("origendatos",""));
        System.out.println(""+pref.getString("color",""));
        System.out.println(""+pref.getBoolean("videobienvenida",false));
        System.out.println(""+pref.getString("pais",""));
        System.out.println(""+pref.getBoolean("sonido",false));
        super.onBackPressed();
    }
}
