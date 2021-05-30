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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.inacap.proveedores.R;

public class ActivityProductos extends AppCompatActivity {

    private EditText et_idProd, et_categoria, et_nombreProd, et_precioProd, et_stockProd;
    Button bbtnAgregar, bbtnBuscar, bbtnModificar, bbtnEliminar;
    ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productos);

        et_idProd = (EditText) findViewById(R.id.txt_idProd);
        et_categoria = (EditText) findViewById(R.id.txt_categoria);
        et_nombreProd = (EditText) findViewById(R.id.txt_nombreProd);
        et_precioProd = (EditText) findViewById(R.id.txt_precioProd);
        et_stockProd = (EditText) findViewById(R.id.txt_stockProd);

        bbtnAgregar = (Button) findViewById(R.id.btnAgregar);
        bbtnBuscar = (Button) findViewById(R.id.btnBuscar);
        bbtnModificar = (Button) findViewById(R.id.btnModificar);
        bbtnEliminar = (Button)findViewById(R.id.btnEliminar);
    }

    // Metodos botones.

    public void Agregar(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracionProductos", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        String idProducto = et_idProd.getText().toString();
        String categoria = et_categoria.getText().toString();
        String nombreProducto = et_nombreProd.getText().toString();
        String precioProducto = et_precioProd.getText().toString();
        String stockProducto = et_stockProd.getText().toString();

        if (!idProducto.isEmpty()  && !categoria.isEmpty() && !nombreProducto.isEmpty() && !precioProducto.isEmpty() && !stockProducto.isEmpty()) {
            ContentValues registro = new ContentValues();
            registro.put("idProducto", idProducto);
            registro.put("categoriaProducto", categoria);
            registro.put("nombreProd", nombreProducto);
            registro.put("precioProd", precioProducto);
            registro.put("stockProd", stockProducto);

            BaseDeDatos.insert("Productos", null, registro);

            BaseDeDatos.close();
            et_idProd.setText("");
            et_categoria.setText("");
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

        String idProdBuscar = et_idProd.getText().toString();

        if (!idProdBuscar.isEmpty()) {
            Cursor fila = BaseDeDatos.rawQuery
                    ("select categoriaProducto, nombreProd, precioProd, stockProd  from Productos where idProducto", null);

            if (fila.moveToFirst()) {
                et_categoria.setText(fila.getString(0));
                et_nombreProd.setText(fila.getString(1));
                et_precioProd.setText(fila.getString(2));
                et_stockProd.setText(fila.getString(3));
                // imagen.setImageURI(fila.getString(5));
                BaseDeDatos.close();
            } else {
                Toast.makeText(this, "No existe el registro", Toast.LENGTH_SHORT).show();
                BaseDeDatos.close();
            }
        } else {
            Toast.makeText(this, "Debes ingresar el ID del producto", Toast.LENGTH_SHORT).show();
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
            et_categoria.setText("");
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
    public void Modificar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracionProductos", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String idProducto = et_idProd.getText().toString();
        String categoria = et_categoria.getText().toString();
        String nombreProducto = et_nombreProd.getText().toString();
        String precioProducto = et_precioProd.getText().toString();
        String stockProducto = et_stockProd.getText().toString();
        //String imagenProducto = et_imagenProd.getText().toString();
        // String imagenProducto = imagen.getDrawable().toString();   // Se puso getDrawable ya que tengo entendido que será donde se guardan las imagenes?

        if(!idProducto.isEmpty() &&  !categoria.isEmpty() && !nombreProducto.isEmpty() && !precioProducto.isEmpty() && !stockProducto.isEmpty()
               ){

            ContentValues registro = new ContentValues();
            registro.put("idProducto", idProducto);
            registro.put("categoriaProducto", categoria);
            registro.put("nombreProd", nombreProducto);
            registro.put("precioProd", precioProducto);
            registro.put("stockProd", stockProducto);


            int cantidad = BaseDeDatos.update("Productos", registro, "idProducto=" + idProducto, null);
            BaseDeDatos.close();

            if(cantidad == 1){
                Toast.makeText(this, "Producto modificado correctamente!", Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(this, "Producto no existe", Toast.LENGTH_SHORT).show();

            }

        }else{
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
        startActivityForResult(intent.createChooser(intent, "Seleccione la Aplicación"),10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            Uri path=data.getData();
            imagen.setImageURI(path);
        }
    }
}