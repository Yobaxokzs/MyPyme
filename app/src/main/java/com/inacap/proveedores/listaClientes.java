package com.inacap.proveedores;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class listaClientes extends AppCompatActivity {

    ListView listViewClientes;
    ArrayList<String> listaInformacionCli;
    ArrayList<ClaseClientes> listaClientes;

    AdminSQLiteOpenHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listaclientes);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        conn = new AdminSQLiteOpenHelper(getApplicationContext(),"administracionClientes",null,1);

        listViewClientes = (ListView) findViewById(R.id.id_listaClientes);

        consultarListaClientes();

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaInformacionCli);
        listViewClientes.setAdapter(adaptador);

        listViewClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //logica.
                //se mostrará un Alertdialog con opciones a realizar con la selección.
                final CharSequence[] opciones={"Seleccionar","Cancelar"};
                final AlertDialog.Builder alertOpciones = new AlertDialog.Builder(com.inacap.proveedores.listaClientes.this);
                alertOpciones.setTitle("Seleccione una opcion");
                alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int x) {
                        if (opciones[x].equals("Seleccionar")) {
                            //cuando se elija seleccionar, pasará algo...
                            Intent e = new Intent(view.getContext(), Pedidos.class);
                            e.putExtra("nombreCliente", listaClientes.get(position).getNombreCliente());
                            e.putExtra("ciudadCliente", listaClientes.get(position).getCiudadCliente());
                            e.putExtra("numeroCliente", listaClientes.get(position).getNumeroCliente());
                            startActivity(e);
                        } else {
                            //si no es ninguna de las opciones, se cierra el dialog.
                            dialog.dismiss();
                        }
                    }
                });
                alertOpciones.show();
            }
        });

    }

    private  void consultarListaClientes(){
        SQLiteDatabase db = conn.getReadableDatabase();

        ClaseClientes cliente = null;
        listaClientes = new ArrayList<ClaseClientes>();
        Cursor cursor = db.rawQuery("SELECT * FROM Cliente",null);

        while (cursor.moveToNext()){
            cliente = new ClaseClientes();
            cliente.setIdCliente(cursor.getInt(0));
            cliente.setNombreCliente(cursor.getString(1));
            cliente.setApellidoCliente(cursor.getString(2));
            cliente.setNumeroCliente(cursor.getInt(3));
            cliente.setCorreoCliente(cursor.getString(4));
            cliente.setCiudadCliente(cursor.getString(5));

            listaClientes.add(cliente);
        }
        obtenerListaCli();
    }

    private void obtenerListaCli() {
        listaInformacionCli = new ArrayList<>();
        for(int i=0; i<listaClientes.size();i++){
            listaInformacionCli.add(listaClientes.get(i).getIdCliente()+" - " + listaClientes.get(i).getNombreCliente()+" - " + listaClientes.get(i).getApellidoCliente());
        }
    }




}
