package com.simarro.practica.jewishbank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class cambiarContrasenya extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_contrasenya);
        findViewById(R.id.imageButton2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.finish();
    }
}
