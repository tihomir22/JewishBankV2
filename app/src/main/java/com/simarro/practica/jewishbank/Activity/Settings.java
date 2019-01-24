package com.simarro.practica.jewishbank.Activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.simarro.practica.jewishbank.R;

public class Settings extends PreferenceActivity implements View.OnClickListener {

    Button buton=null;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(android.R.id.content, new PreferenciasFragment());
        ft.commit();
        this.buton=findViewById(R.id.button2);
        prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        this.buton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }

    public static class PreferenciasFragment extends PreferenceFragment {
        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.opciones);
        }
    }
}
