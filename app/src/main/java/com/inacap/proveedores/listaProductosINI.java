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

public class listaProductosINI extends AppCompatActivity {

    ListView listViewProductosINI;
    ArrayList<String> listaInformacionProdINI;
    ArrayList<ClaseProductos> listaProductosINI;
    EditText buscadorProdINI;

    AdminSQLiteOpenHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listaproductosini);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        conn = new AdminSQLiteOpenHelper(getApplicationContext(),"administracionProductos",null,1);

        listViewProductosINI = (ListView) findViewById(R.id.id_listaProductosINI);
        buscadorProdINI = (EditText) findViewById(R.id.etBuscadorListProdINI);

        consultarListaProductosINI();

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaInformacionProdINI);
        listViewProductosINI.setAdapter(adaptador);
        /*
        String nombreCliente = getIntent().getStringExtra("nombreCliente");
        String ciudadCliente = getIntent().getStringExtra("ciudadCliente");
        int numeroCliente = getIntent().getIntExtra("numeroCliente",0);
        */
        listViewProductosINI.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //logica.
                //se mostrará un Alertdialog con opciones a realizar con la selección.
                final CharSequence[] opciones={"Seleccionar","Cancelar"};
                final AlertDialog.Builder alertOpciones = new AlertDialog.Builder(com.inacap.proveedores.listaProductosINI.this);
                alertOpciones.setTitle("Seleccione una opcion");
                alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int x) {
                        if (opciones[x].equals("Seleccionar")){
                            //cuando se elija seleccionar, pasará algo...

                            Intent e = new Intent(view.getContext(), ActivityProductos.class);
                            String valor1 = "valor";
                            e.putExtra("nombreProducto", listaProductosINI.get(position).getNombreProducto());
                            e.putExtra("stockProducto", listaProductosINI.get(position).getStockProducto());
                            e.putExtra("precioProducto", listaProductosINI.get(position).getPrecioProducto());
                            e.putExtra("categoriaProducto", listaProductosINI.get(position).getCategoriaProducto());
                            e.putExtra("idProducto", listaProductosINI.get(position).getIdProducto());
                            e.putExtra("valor1", valor1);
                            /*
                            e.putExtra("nombreCliente", nombreCliente);
                            e.putExtra("ciudadCliente", ciudadCliente);
                            e.putExtra("numeroCliente", numeroCliente);
                            */
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


        //Se convierte el edittext en un filtro para la lista que se muestra en pantalla.
        buscadorProdINI.addTextChangedListener(new TextWatcher() {
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

        private  void consultarListaProductosINI(){
        SQLiteDatabase db = conn.getReadableDatabase();

        ClaseProductos producto = null;
        listaProductosINI = new ArrayList<ClaseProductos>();
        Cursor cursor = db.rawQuery("SELECT * FROM Productos",null);

        while (cursor.moveToNext()){
            producto = new ClaseProductos();
            producto.setIdProducto(cursor.getInt(0));
            producto.setCategoriaProducto(cursor.getString(1));
            producto.setNombreProducto(cursor.getString(2));
            producto.setPrecioProductoProducto(cursor.getInt(3));
            producto.setStockProducto(cursor.getInt(4));

            listaProductosINI.add(producto);
        }
        obtenerListaProdINI();
    }

    private void obtenerListaProdINI() {
        listaInformacionProdINI = new ArrayList<>();
        for(int i=0; i<listaProductosINI.size();i++){
            listaInformacionProdINI.add(listaProductosINI.get(i).getIdProducto()+" - " + listaProductosINI.get(i).getNombreProducto());
        }
    }


}



