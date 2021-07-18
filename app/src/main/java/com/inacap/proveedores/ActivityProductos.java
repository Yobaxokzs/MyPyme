package com.inacap.proveedores;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.inacap.proveedores.R;

import java.util.ArrayList;

public class ActivityProductos extends AppCompatActivity {

    private EditText et_idProd, et_categoria, et_nombreProd, et_precioProd, et_stockProd;
    Spinner comboproducto;
    ArrayList<String> listaProductos;
    Button bbtnAgregar, bbtnBuscar, bbtnModificar, bbtnEliminar;
    ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        et_idProd = (EditText) findViewById(R.id.txt_idProd);
        comboproducto = (Spinner) findViewById(R.id.spinner);
        et_nombreProd = (EditText) findViewById(R.id.txt_nombreProd);
        et_precioProd = (EditText) findViewById(R.id.txt_precioProd);
        et_stockProd = (EditText) findViewById(R.id.txt_stockProd);

        bbtnAgregar = (Button) findViewById(R.id.btnAgregar);
        bbtnBuscar = (Button) findViewById(R.id.btnBuscar);
        bbtnModificar = (Button) findViewById(R.id.btnModificar);
        bbtnEliminar = (Button) findViewById(R.id.btnEliminar);
        obtenerLista();
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaProductos);
        comboproducto.setAdapter(adaptador);

        int idProd = getIntent().getIntExtra("idProducto",0);
        String idprodText= String.valueOf(idProd);
        et_idProd.setText(idprodText);

        //String categoriaProd = getIntent().getStringExtra("categoriaProducto");
        //comboproducto.setText(categoriaProd);
        String valor1 = getIntent().getStringExtra("valor1");
        String categoriaProd = getIntent().getStringExtra("categoriaProducto");
        if(valor1 !=null) {
            for(int i= 0; i < comboproducto.getAdapter().getCount(); i++)
            {
                if(comboproducto.getAdapter().getItem(i).toString().contains(categoriaProd))
                {
                    comboproducto.setSelection(i);
                }
            }
        }

        String nombreProd = getIntent().getStringExtra("nombreProducto");
        et_nombreProd.setText(nombreProd);

        int precioProd = getIntent().getIntExtra("precioProducto",0);
        String precioprodText= String.valueOf(precioProd);
        et_precioProd.setText(precioprodText);

        int stockProd = getIntent().getIntExtra("stockProducto",0);
        String stockprodText= String.valueOf(stockProd);
        et_stockProd.setText(stockprodText);

    }

    // Metodos botones.

    public void IrAlListadoProd (View view){
        Intent e = new Intent(this, listaProductosINI.class);
        startActivity(e);
    }

    public void Agregar(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracionProductos", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        String idProducto = et_idProd.getText().toString();
        String categoria = comboproducto.getSelectedItem().toString();
        String nombreProducto = et_nombreProd.getText().toString();
        String precioProducto = et_precioProd.getText().toString();
        String stockProducto = et_stockProd.getText().toString();

        if (!idProducto.isEmpty() && !categoria.isEmpty() && !nombreProducto.isEmpty() && !precioProducto.isEmpty() && !stockProducto.isEmpty()) {
            ContentValues registro = new ContentValues();
            registro.put("idProducto", idProducto);
            registro.put("categoriaProducto", categoria);
            registro.put("nombreProd", nombreProducto);
            registro.put("precioProd", precioProducto);
            registro.put("stockProd", stockProducto);

            BaseDeDatos.insert("Productos", null, registro);

            BaseDeDatos.close();
            et_idProd.setText("");
            et_nombreProd.setText("");
            et_precioProd.setText("");
            et_stockProd.setText("");

            Toast.makeText(this, "Registro existoso!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    // Metodo de buscar por ID
    public void Buscar(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracionProductos", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        //String idProdBuscar = et_idProd.getText().toString();  //<- aqui se cambia para que se busque por nombre..
        String NomProdBuscar = et_nombreProd.getText().toString();

        if (!NomProdBuscar.isEmpty()) {
            Cursor fila = BaseDeDatos.rawQuery
                    ("select idProducto, categoriaProducto, precioProd, stockProd  from Productos where nombreProducto=" + NomProdBuscar, null);

            if (fila.moveToFirst()) {
                et_idProd.setText(fila.getString(0));
                //et_nombreProd.setText(fila.getString(2));
                et_precioProd.setText(fila.getString(3));
                et_stockProd.setText(fila.getString(4));
                // imagen.setImageURI(fila.getString(5));
                BaseDeDatos.close();
            } else {
                Toast.makeText(this, "No existe el registro", Toast.LENGTH_SHORT).show();
                BaseDeDatos.close();
            }
        } else {
            Toast.makeText(this, "Debes ingresar el Nombre del producto para", Toast.LENGTH_SHORT).show();
        }
    }

    //Metodo para eliminar registro
    public void Eliminar(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracionProductos", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String idProductoEliminar = et_idProd.getText().toString();
        if (!idProductoEliminar.isEmpty()) {

            int cantidad = BaseDeDatos.delete("Productos", "idProducto=" + idProductoEliminar, null);
            BaseDeDatos.close();
            et_idProd.setText("");
            et_nombreProd.setText("");
            et_precioProd.setText("");
            et_stockProd.setText("");

            if (cantidad == 1) {
                Toast.makeText(this, "Producto eliminado exitosamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "El producto no existe", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Debes escribir un ID para eliminar un producto", Toast.LENGTH_SHORT).show();
        }
    }

    // Metodo para modificar.
    public void Modificar(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracionProductos", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String idProducto = et_idProd.getText().toString();
        String categoria = comboproducto.getSelectedItem().toString();
        String nombreProducto = et_nombreProd.getText().toString();
        String precioProducto = et_precioProd.getText().toString();
        String stockProducto = et_stockProd.getText().toString();
        //String imagenProducto = et_imagenProd.getText().toString();
        // String imagenProducto = imagen.getDrawable().toString();   // Se puso getDrawable ya que tengo entendido que será donde se guardan las imagenes?

        if (!idProducto.isEmpty() && !categoria.isEmpty() && !nombreProducto.isEmpty() && !precioProducto.isEmpty() && !stockProducto.isEmpty()
        ) {

            ContentValues registro = new ContentValues();
            registro.put("idProducto", idProducto);
            registro.put("categoriaProducto", categoria);
            registro.put("nombreProd", nombreProducto);
            registro.put("precioProd", precioProducto);
            registro.put("stockProd", stockProducto);


            int cantidad = BaseDeDatos.update("Productos", registro, "idProducto=" + idProducto, null);
            BaseDeDatos.close();

            if (cantidad == 1) {
                Toast.makeText(this, "Producto modificado correctamente!", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "Producto no existe", Toast.LENGTH_SHORT).show();

            }

        } else {
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }

    }

    // Metodo para seleccionar imagen en la galeria del telefono.

    public void onclick(View view) {
        cargarImagen();
    }

    private void cargarImagen() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent, "Seleccione la Aplicación"), 10);
    }


    private void obtenerLista() {
        listaProductos = new ArrayList<String>();
        listaProductos.add("Bebidas");
        listaProductos.add("Galletas");
        listaProductos.add("Frituras");
        listaProductos.add("Lacteos");
        listaProductos.add("Vegano");
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri path = data.getData();
            imagen.setImageURI(path);
        }
    }
}
