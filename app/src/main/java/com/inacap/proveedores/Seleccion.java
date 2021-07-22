package com.inacap.proveedores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Seleccion extends AppCompatActivity {


    EditText et_cantidad;
    TextView tv11, tv22, tv33;
    Button agregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion);

        tv11 = (TextView) findViewById(R.id.id_tvn);
        tv22 = (TextView) findViewById(R.id.id_tvs);
        tv33 = (TextView) findViewById(R.id.id_tvp);
        agregar = (Button) findViewById(R.id.buttonpedidos);
        et_cantidad = (EditText) findViewById(R.id.etCantidadPedido);
        String nombreCliente = getIntent().getStringExtra("nombreCliente");
        String ciudadCliente = getIntent().getStringExtra("ciudadCliente");
        int numeroCliente = getIntent().getIntExtra("numeroCliente",0);
        String productillo = getIntent().getStringExtra("nombreProductoxz");
        tv11.setText(productillo);
        int stock22 = getIntent().getIntExtra("stockProductoxz", 0);
        String stockillo = String.valueOf(stock22);
        tv22.setText(stockillo);
        double precio22 = getIntent().getIntExtra("precioProductoxz", 0);
        String precillo = String.valueOf(precio22);
        tv33.setText(precillo);
        int cantidadseleccionada = Integer.parseInt(et_cantidad.getText().toString());
    }

    public void AgregarPp(String productillo,double precio22, double cantidadseleccionada, int stock22) {
        if(cantidadseleccionada<= stock22) {
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracionPp", null, 1);
            SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
            ContentValues registro = new ContentValues();
            double preciofinal = cantidadseleccionada * precio22;
            registro.put("nombrePp ", productillo);
            registro.put("precioPp ", preciofinal);
            registro.put("cantidadPp ", cantidadseleccionada);
            BaseDeDatos.insert("pedidoProducto", null, registro);
            BaseDeDatos.close();
            Toast.makeText(this, "Producto agregado!", Toast.LENGTH_SHORT).show();
            Intent e = new Intent(this, listaProductos.class);
            startActivity(e);
        }
    }

    public void Accion (View view){
        String productillo = getIntent().getStringExtra("nombreProductoxz");
        double precio22 = getIntent().getIntExtra("precioProductoxz", 0);
        double cantidadseleccionada = Integer.parseInt(et_cantidad.getText().toString());
        int stock22 = getIntent().getIntExtra("stockProductoxz", 0);
        if (cantidadseleccionada<= stock22) {
            AgregarPp(productillo, precio22, cantidadseleccionada, stock22);
            Intent e = new Intent(this, listaProductos.class);
            String nombreCliente = getIntent().getStringExtra("nombreCliente");
            String ciudadCliente = getIntent().getStringExtra("ciudadCliente");
            int numeroCliente = getIntent().getIntExtra("numeroCliente", 0);
            e.putExtra("nombreCliente", nombreCliente);
            e.putExtra("ciudadCliente", ciudadCliente);
            e.putExtra("numeroCliente", numeroCliente);
            startActivity(e);
        }else{
            Toast.makeText(this, "Cantidad excede el límite en stock", Toast.LENGTH_SHORT).show();
        }
    }





}