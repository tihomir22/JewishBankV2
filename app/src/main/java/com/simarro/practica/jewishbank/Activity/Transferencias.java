package com.simarro.practica.jewishbank.Activity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.simarro.practica.jewishbank.R;

import java.io.Console;

public class Transferencias extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    String [] listaCuentas = new String [] {"AL86751639367318444714198669","AL89515635252277023782748302","AL39153296222641598198140883","AL47907501989147671525950076"};
    String [] listaDivisas= new String[]{"€","$","£","\u20BF"};
    ArrayAdapter<String>adaptadorString=null;
    ArrayAdapter<String>adaptadorSpinner=null;
    ArrayAdapter<String>adaptadorDivisas=null;
    GridView gv=null;
    RadioButton radio1=null;
    RadioButton radio2=null;
    RadioGroup grupoRadio=null;
    CheckBox checky=null;
    Button benviar=null;
    Button bcancelar=null;
    View itemLista=null;
    String seleccion_radio=null;
    EditText importe=null;

    Spinner sp=null;
    Spinner spinerdivisas=null;
    Spinner spinnerDentro=null;
    TextView textoCuenta=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferencias);
        gv=findViewById(R.id.cuentasGrid);
        adaptadorString=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listaCuentas);
        adaptadorSpinner=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,this.listaCuentas);
        this.adaptadorDivisas=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,this.listaDivisas);
        gv.setAdapter(adaptadorString);
        gv.setOnItemClickListener(this);

        //Referencias...
        radio1=findViewById(R.id.radioCpropia);
        radio2=findViewById(R.id.radioCajena);
        sp=findViewById(R.id.spinnerDentro);
        benviar=findViewById(R.id.benviar);
        bcancelar=findViewById(R.id.bcancelar);
        spinerdivisas=findViewById(R.id.spinerDivisas);
        textoCuenta=findViewById(R.id.textCuenta);
        importe =findViewById(R.id.editText4);
        grupoRadio=findViewById(R.id.radioGroup);
        checky=findViewById(R.id.checkBox);



        radio1.setOnClickListener(this);
        radio2.setOnClickListener(this);
        benviar.setOnClickListener(this);
        bcancelar.setOnClickListener(this);
        this.textoCuenta.setOnClickListener(this);
        adaptadorSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adaptadorSpinner);
        this.spinerdivisas.setAdapter(this.adaptadorDivisas);
        //this.spinnerDentro.setAdapter(adaptadorSpinner);

    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this,"Seleccionaste "+parent.getItemAtPosition(position).toString(),Toast.LENGTH_LONG).show();
        if(this.itemLista!=null){
            this.itemLista.setBackgroundColor(Color.WHITE);
        }
        this.itemLista=view;
        view.setBackgroundColor(R.color.colorDarculaLightGray);
    }

    @Override
    public void onClick(View v) {

        if(v.getTag().toString().equalsIgnoreCase("radio1")){
            Toast.makeText(this,"le diste al 1",Toast.LENGTH_LONG).show();
            this.seleccion_radio="RADIO 1 - Seleccionaste cuenta propia y esta es : ";
            ViewGroup.LayoutParams params = this.sp.getLayoutParams();
            params.width= ActionBar.LayoutParams.MATCH_PARENT;
            params.height= ActionBar.LayoutParams.MATCH_PARENT;
            this.sp.setLayoutParams(params);
            this.sp.setVisibility(View.VISIBLE);


            ViewGroup.LayoutParams params2 = this.textoCuenta.getLayoutParams();
            params2.width= 1;
            params2.height= 1;
            this.textoCuenta.setLayoutParams(params2);
            this.textoCuenta.setVisibility(View.INVISIBLE);


        }


        if(v.getTag().toString().equalsIgnoreCase("radio2")){
            Toast.makeText(this,"le diste al 2",Toast.LENGTH_LONG).show();
            this.seleccion_radio="RADIO 2 - Seleccionaste cuenta ajena y esta es : ";
            textoCuenta=findViewById(R.id.textCuenta);
            this.textoCuenta.setOnClickListener(this);
            ViewGroup.LayoutParams params = this.textoCuenta.getLayoutParams();
            params.width= ActionBar.LayoutParams.MATCH_PARENT;
            params.height= ActionBar.LayoutParams.MATCH_PARENT;
            this.textoCuenta.setLayoutParams(params);
            this.textoCuenta.setVisibility(View.VISIBLE);

            ViewGroup.LayoutParams params2 = this.sp.getLayoutParams();
            params2.width= 1;
            params2.height= 1;
            this.sp.setLayoutParams(params2);
            this.sp.setVisibility(View.INVISIBLE);
        }

        if(v.getTag().toString().equalsIgnoreCase("enviarBtn")){

            if(grupoRadio.getCheckedRadioButtonId()!=-1) {
                Toast.makeText(this, "Toda la información " + ((TextView) itemLista).getText().toString() + "\n" + this.seleccion_radio + "\n" + returnSelectedItem() + "\n" + "Importe de " + this.importe.getText().toString() + " " + this.spinerdivisas.getSelectedItem().toString()+"\n"+returnCheckboxString(), Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this,"Debes elegir una opcion antes ",Toast.LENGTH_LONG).show();
            }
            }

        if(v.getTag().toString().equalsIgnoreCase("cancelarBtn")){
            this.itemLista.setBackgroundColor(Color.WHITE);
            this.importe.setText("");
            if(checky.isSelected()) {
                this.checky.toggle();
            }
            this.spinerdivisas.clearFocus();
            this.grupoRadio.clearCheck();
        }



    }

    public String returnSelectedItem(){
        if(this.radio2.isSelected()){
            return this.textoCuenta.getText().toString();
        }else{
            return this.sp.getSelectedItem().toString();
        }

    }
    public String returnCheckboxString(){
        if(this.checky.isSelected()){
            return "Se desea enviar justificante";
        }else{
            return "No se desea enviar justificante";
        }

    }


}
