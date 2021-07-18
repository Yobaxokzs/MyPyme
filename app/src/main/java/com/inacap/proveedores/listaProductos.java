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

public class listaProductos extends AppCompatActivity {

    ListView listViewProductos;
    ArrayList<String> listaInformacionProd;
    ArrayList<ClaseProductos> listaProductos;

    AdminSQLiteOpenHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listaproductos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        conn = new AdminSQLiteOpenHelper(getApplicationContext(),"administracionProductos",null,1);

        listViewProductos = (ListView) findViewById(R.id.id_listViewProductos);

        consultarListaProductos();

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaInformacionProd);
        listViewProductos.setAdapter(adaptador);
        String nombreCliente = getIntent().getStringExtra("nombreCliente");
        String ciudadCliente = getIntent().getStringExtra("ciudadCliente");
        int numeroCliente = getIntent().getIntExtra("numeroCliente",0);
        listViewProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //logica.
                //se mostrará un Alertdialog con opciones a realizar con la selección.
                final CharSequence[] opciones={"Seleccionar","Cancelar"};
                final AlertDialog.Builder alertOpciones = new AlertDialog.Builder(com.inacap.proveedores.listaProductos.this);
                alertOpciones.setTitle("Seleccione una opcion");
                alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int x) {
                        if (opciones[x].equals("Seleccionar")){
                            //cuando se elija seleccionar, pasará algo...

                            Intent e = new Intent(view.getContext(), Pedidos.class);
                            e.putExtra("nombreProducto", listaProductos.get(position).getNombreProducto());
                            e.putExtra("stockProducto", listaProductos.get(position).getStockProducto());
                            e.putExtra("nombreCliente", nombreCliente);
                            e.putExtra("ciudadCliente", ciudadCliente);
                            e.putExtra("numeroCliente", numeroCliente);
                            startActivity(e);
                            }else {
                                //si no es ninguna de las opciones, se cierra el dialog.
                                dialog.dismiss();
                            }
                        }
                });
                alertOpciones.show();
            }
        });


    }

    public void volverAPedidos (View view) {
            Intent e = new Intent(this, Pedidos.class);
            startActivity(e);

    }
    private  void consultarListaProductos(){
        SQLiteDatabase db = conn.getReadableDatabase();

        ClaseProductos producto = null;
        listaProductos = new ArrayList<ClaseProductos>();
        Cursor cursor = db.rawQuery("SELECT * FROM Productos",null);

        while (cursor.moveToNext()){
            producto = new ClaseProductos();
            producto.setIdProducto(cursor.getInt(0));
            producto.setCategoriaProducto(cursor.getString(1));
            producto.setNombreProducto(cursor.getString(2));
            producto.setPrecioProductoProducto(cursor.getInt(3));
            producto.setStockProducto(cursor.getInt(4));

            listaProductos.add(producto);
        }
        obtenerListaProd();
    }

    private void obtenerListaProd() {
        listaInformacionProd = new ArrayList<>();
        for(int i=0; i<listaProductos.size();i++){
            listaInformacionProd.add(listaProductos.get(i).getIdProducto()+" - " + listaProductos.get(i).getNombreProducto());
        }
    }




}
