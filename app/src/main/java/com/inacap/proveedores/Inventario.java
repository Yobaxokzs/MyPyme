package com.inacap.proveedores;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Inventario extends AppCompatActivity {

    private TextView tv1;  //no pescar esto.
    private ListView lv1;   // esta es la lista.
    Button bbtnBuscar;
    EditText et_idPedido, et_clientePedido, et_ciudadPedido, et_contactoPedido, et_productoPedido, et_cantidad, et_estado, et_fechaCreacion, et_fechaEntrega, et_nombreListadoBuscar;



/*
    private String listaMostrar [] = {obtenerNombre().toString()};    //en el listado se veran estos nombres, es como la "cabecera", slo se vera esto.
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventario);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lv1 = (ListView)findViewById(R.id.IdListView);
        bbtnBuscar = (Button) findViewById(R.id.btnBuscarPedidoListado);

        et_idPedido = (EditText)findViewById(R.id.txt_idPedido);
        et_clientePedido = (EditText)findViewById(R.id.txt_clientePedido);
        et_ciudadPedido = (EditText)findViewById(R.id.txt_ciudadPedido);
        et_contactoPedido = (EditText)findViewById(R.id.txt_contactoPedido);
        et_productoPedido = (EditText)findViewById(R.id.txt_productoPedido);
        et_cantidad = (EditText)findViewById(R.id.txt_cantidadProducto);
        et_estado = (EditText)findViewById(R.id.txt_estadoPedido);
        et_fechaCreacion = (EditText)findViewById(R.id.txt_fechaCreacion);
        et_fechaEntrega = (EditText)findViewById(R.id.txt_fechaEntrega);
        et_nombreListadoBuscar = (EditText)findViewById(R.id.txt_nombreListadoBuscar);

        final ArrayList<String> lista = obtenerNombre();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista);
        lv1.setAdapter(adapter);
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Aqui se pone la logica de la actividad.
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(Inventario.this, "administracioAsignacionPedidos", null, 1);
                SQLiteDatabase BaseDeDatos = admin.getWritableDatabase(); // se abrira la base de datos en modo lectura y escritura.

                    Cursor fila = BaseDeDatos.rawQuery
                            ("select idPedido, nombreCliente, ciudadPedido, nombreProducto, cantidadProducto, estadoPedido, fechaCreacion, fechaEntrega, contactoPedido" +
                                    "  from Pedidos", null);
                    if (fila.moveToFirst()) {
                        et_idPedido.setText(fila.getString(0));
                        et_clientePedido.setText(fila.getString(1));
                        et_ciudadPedido.setText(fila.getString(2));
                        et_productoPedido.setText(fila.getString(3));
                        et_cantidad.setText(fila.getString(4));
                        et_estado.setText(fila.getString(5));
                        et_fechaCreacion.setText(fila.getString(6));
                        et_fechaEntrega.setText(fila.getString(7));
                        et_contactoPedido.setText(fila.getString(8));
                        BaseDeDatos.close();
                    }
                };
            }

        );

                //Filtro para que buscar en base a lo ingresado en el txt de buscar.

            et_nombreListadoBuscar.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    adapter.getFilter().filter(s);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
    }


    public ArrayList<String> obtenerNombre(){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracionInventario", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase(); // se abrira la base de datos en modo lectura y escritura.
        String nombreBuscado = "";

        Cursor fila = BaseDeDatos.rawQuery("select * from Pedidos", null);
        ArrayList<String> listaSoloNombres = new ArrayList<String>();
        while (fila.moveToNext()){
                nombreBuscado.equals(fila.getString(1));
                listaSoloNombres.add(nombreBuscado);
        }
        BaseDeDatos.close();
        return listaSoloNombres;
    }
    
}