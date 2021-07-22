package com.inacap.proveedores;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class listaProductos extends AppCompatActivity {

    ListView listViewProductos;
    ArrayList<String> listaInformacionProd;
    ArrayList<ClaseProductos> listaProductos;
    ArrayList<String> carrito;
    String valorcito;
    Button botoncito;

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

                final CharSequence[] opciones={"Agregar al Pedido","Cancelar"};
                final AlertDialog.Builder alertOpciones = new AlertDialog.Builder(com.inacap.proveedores.listaProductos.this);
                alertOpciones.setTitle("Seleccione una opcion");
                alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int x) {
                        if (opciones[x].equals("Agregar al Pedido")){
                            Intent e = new Intent(view.getContext(), Seleccion.class);
                            e.putExtra("nombreProductoxz", listaProductos.get(position).getNombreProducto());
                            e.putExtra("stockProductoxz", listaProductos.get(position).getStockProducto());
                            e.putExtra("precioProductoxz", listaProductos.get(position).getPrecioProducto());
                            e.putExtra("nombreCliente", nombreCliente);
                            e.putExtra("ciudadCliente", ciudadCliente);
                            e.putExtra("numeroCliente", numeroCliente);
                            startActivity(e);
                        }else {
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


    private void obtenerListaPedido() {
        carrito = new ArrayList<>();
        for(int i=0; i<listaProductos.size();i++){
            carrito.add(listaProductos.get(i).getIdProducto()+" - " + listaProductos.get(i).getNombreProducto());
        }
    }

    public void AgregarPp(String nombreprueba, String cantidadpp10, String resultadoproducto) {
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracionPp", null, 1);
            SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
            ContentValues registro = new ContentValues();
            int cantidadPp = getIntent().getIntExtra("stockProducto", 0);
            String cantidadPpxd = String.valueOf(cantidadPp);
            String nombrePp = getIntent().getStringExtra("nombreProducto");
            double precioPp;
            registro.put("nombrePp ", nombreprueba);
            registro.put("precioPp ", resultadoproducto);
            registro.put("cantidadPp ", cantidadpp10);
            BaseDeDatos.insert("pedidoProducto", null, registro);
            BaseDeDatos.close();
            Toast.makeText(this, "Producto agregado!", Toast.LENGTH_SHORT).show();
    }

    public void deleteAll()
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracionPp", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        BaseDeDatos.delete("pedidoProducto",null, null);
    }

    public void AvanzarCliente(View view)
    {
        Intent u = new Intent(this, listaClientes.class);
        startActivity(u);
    }
}

