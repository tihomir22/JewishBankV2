package com.simarro.practica.jewishbank.Activity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.simarro.practica.aplicacionbancoanna2018.bd.CajerosSQLHelper;
import com.simarro.practica.aplicacionbancoanna2018.bd.Constantes;
import com.simarro.practica.aplicacionbancoanna2018.dao.CajeroDAO;
import com.simarro.practica.jewishbank.Adapters.CajeroCursorAdapter;
import com.simarro.practica.jewishbank.R;

import java.util.Arrays;
import java.util.List;

public class GestionCajerosActivity extends AppCompatActivity {
    ListView lista;
    CajeroDAO cajeroDAO;
    Cursor cursor;
    TextView textosindatos;
    Button añadirCajero;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.admin_cajero,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            showInputDialogAdd();
            return true;
        }else if(id==R.id.info){
            mostrarAlerta();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_cajeros);

        CajerosSQLHelper dbHelper = new CajerosSQLHelper(getBaseContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Toast.makeText(getBaseContext(), "Base de datos preparada", Toast.LENGTH_LONG).show();
        lista = (ListView) findViewById(R.id.lista);
        cajeroDAO = new CajeroDAO(this);
        cajeroDAO.abrir();

        cursor=cajeroDAO.getCursor();
        startManagingCursor(cursor);
        CajeroCursorAdapter cajeroadapter=new CajeroCursorAdapter(this,cursor);
        lista.setAdapter(cajeroadapter);

        //aguantando un item se borra
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                eliminar(id);
                return false;
            }
        });
        //haciendo click sencillo se abre dialogo para modificarlo
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor= (Cursor) parent.getItemAtPosition(position);
                showInputDialog(cursor,position);
            }
        });


        if(cursor.getCount()>0){
            textosindatos=findViewById(R.id.txtSinDatos);
            textosindatos.setVisibility(View.INVISIBLE);
            textosindatos.invalidate();
        }

    }

    /*public void añadir(){
        ContentValues reg = new ContentValues();
        reg.put(Constantes.FIELD_CAJEROS_ID, 69);
        reg.put(Constantes.FIELD_DIRECCION,"Calle dembow");
        reg.put(Constantes.FIELD_LAT,"Demasiada");
        reg.put(Constantes.FIELD_LNG,"Largo como...");
        reg.put(Constantes.FIELD_ZOOM,"No necesitas zoom tranquilo");
        cajeroDAO.add(reg);
        Toast.makeText(this,"Añadido con exito",Toast.LENGTH_SHORT).show();
        this.actualizarListView();
    }*/
    public void eliminar(long id){
        cajeroDAO.delete(id);
        Toast.makeText(this,"Eliminado con exito",Toast.LENGTH_SHORT).show();
        this.actualizarListView();

    }

    public void actualizarListView(){
        cursor=cajeroDAO.getCursor();
        startManagingCursor(cursor);
        CajeroCursorAdapter cajeroadapter=new CajeroCursorAdapter(this,cursor);
        lista.setAdapter(cajeroadapter);
        ((BaseAdapter) lista.getAdapter()).notifyDataSetChanged();

    }

    protected void showInputDialogAdd() {
        final EditText direccioncajero,latitudcajero,longitudcajero,zoomcajero,idcajero;
        LayoutInflater layoutInflater = LayoutInflater.from(GestionCajerosActivity.this);
        View promptView = layoutInflater.inflate(R.layout.input_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(GestionCajerosActivity.this);
        alertDialogBuilder.setView(promptView);
        idcajero = (EditText) promptView.findViewById(R.id.idcajero);


        direccioncajero = (EditText) promptView.findViewById(R.id.iddireccion);
        latitudcajero = (EditText) promptView.findViewById(R.id.idlat);
        longitudcajero = (EditText) promptView.findViewById(R.id.idlng);
        zoomcajero = (EditText) promptView.findViewById(R.id.idzoom);

        // get prompts.xml view




        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ContentValues reg = new ContentValues();
                        reg.put(Constantes.FIELD_CAJEROS_ID,Long.parseLong(idcajero.getText().toString()));
                        reg.put(Constantes.FIELD_DIRECCION,direccioncajero.getText().toString());
                        reg.put(Constantes.FIELD_LAT,latitudcajero.getText().toString());
                        reg.put(Constantes.FIELD_LNG,longitudcajero.getText().toString());
                        reg.put(Constantes.FIELD_ZOOM,zoomcajero.getText().toString());
                        cajeroDAO.add(reg);
                        actualizarListView();
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    protected void showInputDialog(Cursor cursor,int posicion) {
        String idcajerostr="",direccion="",latitud="",longitud="",zoom="";
        final EditText direccioncajero,latitudcajero,longitudcajero,zoomcajero,idcajero;
        cursor.moveToPosition(posicion);

            idcajerostr=cursor.getString(cursor.getColumnIndex(Constantes.FIELD_CAJEROS_ID));
             direccion=cursor.getString(cursor.getColumnIndex(Constantes.FIELD_DIRECCION));
             latitud=cursor.getString(cursor.getColumnIndex(Constantes.FIELD_LAT));
             longitud=cursor.getString(cursor.getColumnIndex(Constantes.FIELD_LNG));
             zoom=cursor.getString(cursor.getColumnIndex(Constantes.FIELD_ZOOM));
            System.out.println(zoom);

            // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(GestionCajerosActivity.this);
        View promptView = layoutInflater.inflate(R.layout.input_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(GestionCajerosActivity.this);
        alertDialogBuilder.setView(promptView);

         idcajero = (EditText) promptView.findViewById(R.id.idcajero);
         idcajero.setEnabled(false);
         direccioncajero = (EditText) promptView.findViewById(R.id.iddireccion);
         latitudcajero = (EditText) promptView.findViewById(R.id.idlat);
         longitudcajero = (EditText) promptView.findViewById(R.id.idlng);
         zoomcajero = (EditText) promptView.findViewById(R.id.idzoom);

        idcajero.setText(idcajerostr);
        direccioncajero.setText(direccion);
        latitudcajero.setText(latitud);
        longitudcajero.setText(longitud);
        zoomcajero.setText(zoom);

        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //resultText.setText("Hello, " + editText.getText());
                        ContentValues reg = new ContentValues();
                        reg.put(Constantes.FIELD_CAJEROS_ID,Long.parseLong(idcajero.getText().toString()));
                        reg.put(Constantes.FIELD_DIRECCION,direccioncajero.getText().toString());
                        reg.put(Constantes.FIELD_LAT,latitudcajero.getText().toString());
                        reg.put(Constantes.FIELD_LNG,longitudcajero.getText().toString());
                        reg.put(Constantes.FIELD_ZOOM,zoomcajero.getText().toString());
                        cajeroDAO.update(reg);
                        actualizarListView();
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    public void mostrarAlerta(){
        AlertDialog alertDialog = new AlertDialog.Builder(GestionCajerosActivity.this).create();
        alertDialog.setTitle("Información");
        alertDialog.setMessage("Haga click simple para modificar un cajero , o aguanta sobre un elemento para eliminarlo");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
