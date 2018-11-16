package com.simarro.practica.jewishbank.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.simarro.practica.aplicacionbancoanna2018.bd.MiBancoOperacional;
import com.simarro.practica.aplicacionbancoanna2018.pojo.Cliente;
import com.simarro.practica.jewishbank.R;

public class cambiarContrasenya extends AppCompatActivity implements View.OnClickListener {
    EditText ed;
    EditText ed2;
    EditText ed3;
    MiBancoOperacional mbo=null;
    Cliente aux;
    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cambiar_contrasenya);
        findViewById(R.id.imageButton2).setOnClickListener(this);
        findViewById(R.id.imageButton3).setOnClickListener(this);
        ll=findViewById(R.id.linearLayout);
        ed=findViewById(R.id.textvieja);
        ed2=findViewById(R.id.textnueva);
        ed3=findViewById(R.id.textnueva2);
        mbo = MiBancoOperacional.getInstance(this);
        Cliente cli=new Cliente();
        cli.setNif(getIntent().getStringExtra("nif"));
        aux = (Cliente) this.mbo.getmiBD().getClienteDAO().search(cli);
        System.out.println("información cargada con exito "+aux.toString());
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.imageButton2) {
            this.finish();
        }else{
            if(this.ed.getText().toString().length()>0 && this.ed2.getText().toString().length()>0 && this.ed3.getText().toString().length()>0){
                if(this.ed2.getText().toString().equalsIgnoreCase(this.ed3.getText().toString())) {
                    if(this.ed.getText().toString().equalsIgnoreCase(this.aux.getClaveSeguridad().toString())) {
                        aux.setClaveSeguridad(this.ed3.getText().toString());
                        mbo.changePassword(aux);
                        clearForm(this.ll);
                        Toast.makeText(this, "Cambiada con exito", Toast.LENGTH_SHORT).show();
                        System.out.println(aux.toString());
                        //  System.out.println("Cambiada con exito a "+aux.getClaveSeguridad());
                    }else{
                        Toast.makeText(this,"Has introducido una contraseña vieja incorrecta",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this,"No coinciden las contraseñas",Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this,"Se te olvida por completar algun campo",Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void clearForm(LinearLayout group) {
        for (int i = 0, count = group.getChildCount(); i < count; ++i) {
            View view = group.getChildAt(i);
            if (view instanceof EditText) {
                ((EditText)view).setText("");
            }

            if(view instanceof LinearLayout && (((LinearLayout)view).getChildCount() > 0))
                clearForm((LinearLayout)view);
        }
    }
}
