package com.inacap.proveedores;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class listaClientesINI extends AppCompatActivity {

    ListView listViewClientesINI;
    ArrayList<String> listaInformacionCliINI;
    ArrayList<ClaseClientes> listaClientesINI;
    EditText buscadorClientesINI;

    AdminSQLiteOpenHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listaclientesini);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        conn = new AdminSQLiteOpenHelper(getApplicationContext(),"administracionClientes",null,1);

        listViewClientesINI = (ListView) findViewById(R.id.id_listaClientesINI);
        buscadorClientesINI = (EditText) findViewById(R.id.et_idBuscadorClientesINI);

        consultarListaClientes();

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaInformacionCliINI);
        listViewClientesINI.setAdapter(adaptador);

        listViewClientesINI.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //logica.
                //se mostrará un Alertdialog con opciones a realizar con la selección.
                final CharSequence[] opciones={"Seleccionar","Cancelar"};
                final AlertDialog.Builder alertOpciones = new AlertDialog.Builder(com.inacap.proveedores.listaClientesINI.this);
                alertOpciones.setTitle("Seleccione una opcion");
                alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int x) {
                        if (opciones[x].equals("Seleccionar")){
                            //cuando se elija seleccionar, pasará algo...
                            Intent u = new Intent(view.getContext(), ActivityClientes.class);
                            String valor="valor";
                            u.putExtra("nombreCliente", listaClientesINI.get(position).getNombreCliente());
                            u.putExtra("ciudadCliente", listaClientesINI.get(position).getCiudadCliente());
                            u.putExtra("numeroCliente", listaClientesINI.get(position).getNumeroCliente());
                            u.putExtra("idCliente", listaClientesINI.get(position).getIdCliente());
                            u.putExtra("apellidoCliente", listaClientesINI.get(position).getApellidoCliente());
                            u.putExtra("correoCliente", listaClientesINI.get(position).getCorreoCliente());
                            u.putExtra("valor", valor);
                            startActivity(u);
                            }else {
                                //si no es ninguna de las opciones, se cierra el dialog.
                                dialog.dismiss();
                            }
                        }
                });
                alertOpciones.show();
            }
        });

        //--- filtro campo editText.
        buscadorClientesINI.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            adaptador.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private  void consultarListaClientes(){
        SQLiteDatabase db = conn.getReadableDatabase();

        ClaseClientes cliente = null;
        listaClientesINI = new ArrayList<ClaseClientes>();
        Cursor cursor = db.rawQuery("SELECT * FROM Cliente",null);

        while (cursor.moveToNext()){
            cliente = new ClaseClientes();
            cliente.setIdCliente(cursor.getInt(0));
            cliente.setNombreCliente(cursor.getString(1));
            cliente.setApellidoCliente(cursor.getString(2));
            cliente.setNumeroCliente(cursor.getInt(3));
            cliente.setCorreoCliente(cursor.getString(4));
            cliente.setCiudadCliente(cursor.getString(5));

            listaClientesINI.add(cliente);
        }
        obtenerListaCli();
    }

    private void obtenerListaCli() {
        listaInformacionCliINI = new ArrayList<>();
        for(int i=0; i<listaClientesINI.size();i++){
            listaInformacionCliINI.add(listaClientesINI.get(i).getIdCliente()+" - " + listaClientesINI.get(i).getNombreCliente()+" - " + listaClientesINI.get(i).getApellidoCliente());
        }
    }




}
