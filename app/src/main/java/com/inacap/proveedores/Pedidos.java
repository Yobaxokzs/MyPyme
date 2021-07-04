package com.inacap.proveedores;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class Pedidos extends AppCompatActivity implements View.OnClickListener {

    Button bbtnAgregarPedido, bbtnBuscarPedido, bbtnModificarPedido, bbtnEliminarPedido, bbtnFechaCreacion, bbtnFechaEntrega, bbtnBuscarNombreCli;
    EditText et_idPedido, et_ClienteDelPedido, et_CiudadPedido, et_ContactoPedido, et_ProductoDelPedido, et_CantidadDelPedido, et_EstadoDelPedido,
            et_FechaCreacion, et_FechaEntrega, et_nombreClienteBuscar;
    private int dia,mes,ano;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pedidos);

        bbtnAgregarPedido = (Button) findViewById(R.id.btnAgregarPedido);
        bbtnBuscarPedido = (Button) findViewById(R.id.btnBuscarCliente);
        bbtnModificarPedido = (Button) findViewById(R.id.btnModificarPedido);
        bbtnEliminarPedido = (Button) findViewById(R.id.btnEliminarPedido);
        bbtnFechaCreacion = (Button) findViewById(R.id.btnFechaCreacion);
        bbtnFechaEntrega = (Button) findViewById(R.id.btnFechaEntrega);
        bbtnBuscarNombreCli = (Button) findViewById(R.id.btnBuscarNombreCliente);

        bbtnFechaCreacion.setOnClickListener(this);
        bbtnFechaEntrega.setOnClickListener(this);

        et_idPedido = (EditText) findViewById(R.id.txt_IdPedido);
        et_ClienteDelPedido = (EditText) findViewById(R.id.txt_ClienteDelPedido);
        et_CiudadPedido = (EditText) findViewById(R.id.txt_CiudadPedido);
        et_ContactoPedido = (EditText) findViewById(R.id.txt_ContactoPedido);
        et_ProductoDelPedido = (EditText) findViewById(R.id.txt_ProductoDelPedido);
        et_CantidadDelPedido = (EditText) findViewById(R.id.txt_CantidadDelPedido);
        et_EstadoDelPedido = (EditText) findViewById(R.id.txt_EstadoDelPedido);
        et_FechaCreacion = (EditText) findViewById(R.id.txt_FechaCreacion);
        et_FechaEntrega = (EditText) findViewById(R.id.txt_FechaEntrega);
        et_nombreClienteBuscar = (EditText) findViewById(R.id.txt_nombreClienteBuscar);

    }

    public void PEDIDOAgregarPedido (View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracionPedidos", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        String idPedid = et_idPedido.getText().toString();
        String nombreCliPedid = et_ClienteDelPedido.getText().toString();
        String ciudadPedid = et_CiudadPedido.getText().toString();
        String contactoPedid = et_ContactoPedido.getText().toString();
        String productoDelPedid = et_ProductoDelPedido.getText().toString();
        String cantidadDelPedid = et_CantidadDelPedido.getText().toString();
        String estadoDelPedid = et_EstadoDelPedido.getText().toString();
        String fechaCreaciDelPedid = et_FechaCreacion.getText().toString();
        String fechaEntregDelPedid = et_FechaEntrega.getText().toString();

        if (!idPedid.isEmpty()  && !nombreCliPedid.isEmpty() && !ciudadPedid.isEmpty() && !contactoPedid.isEmpty() && !productoDelPedid.isEmpty() && !cantidadDelPedid.isEmpty() &&
                !estadoDelPedid.isEmpty() && !fechaCreaciDelPedid.isEmpty() && !fechaEntregDelPedid.isEmpty()) {
            ContentValues registro = new ContentValues();
            registro.put("idPedido", idPedid);
            registro.put("nombreCliente", nombreCliPedid);
            registro.put("ciudadPedido", ciudadPedid);
            registro.put("nombreProducto", productoDelPedid);
            registro.put("cantidadProducto", cantidadDelPedid);
            registro.put("estadoPedido", estadoDelPedid);
            registro.put("fechaCreacion", fechaCreaciDelPedid);
            registro.put("fechaEntrega", fechaEntregDelPedid);
            registro.put("contactoPedido", contactoPedid);

            BaseDeDatos.insert("Pedidos", null, registro);

            BaseDeDatos.close();
            et_idPedido.setText("");
            et_ClienteDelPedido.setText("");
            et_CiudadPedido.setText("");
            et_ContactoPedido.setText("");
            et_ProductoDelPedido.setText("");
            et_CantidadDelPedido.setText("");
            et_EstadoDelPedido.setText("");
            et_FechaCreacion.setText("");
            et_FechaEntrega.setText("");

            Toast.makeText(this, "Registro existoso!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void PEDIDOBuscarPedido (View view){

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracionPedidos", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase(); // se abrira la base de datos en modo lectura y escritura.

        String idPedidoBuscar = et_idPedido.getText().toString();

        if (!idPedidoBuscar.isEmpty()) {
            Cursor fila = BaseDeDatos.rawQuery
                    ("select idPedido, nombreCliente, ciudadPedido, nombreProducto, cantidadProducto, estadoPedido, fechaCreacion, fechaEntrega, contactoPedido" +
                            "  from Pedidos where idPedido="+ idPedidoBuscar, null);

            if (fila.moveToFirst()) {                                   // aquí trabajaremos con Arrays, OJO que el primer valor empieza en 0: 0,1,2,3...
                et_idPedido.setText(fila.getString(0));
                et_ClienteDelPedido.setText(fila.getString(1));
                et_CiudadPedido.setText(fila.getString(2));
                et_ProductoDelPedido.setText(fila.getString(3));
                et_CantidadDelPedido.setText(fila.getString(4));
                et_EstadoDelPedido.setText(fila.getString(5));
                et_FechaCreacion.setText(fila.getString(6));
                et_FechaEntrega.setText(fila.getString(7));
                et_ContactoPedido.setText(fila.getString(8));
                BaseDeDatos.close();
            } else {
                Toast.makeText(this, "No existe el registro del pedido", Toast.LENGTH_SHORT).show();
                BaseDeDatos.close();
            }
        } else {
            Toast.makeText(this, "Debes ingresar el código/ID del pedido", Toast.LENGTH_SHORT).show();
        }

    }

    public void PEDIDOBuscarClientePedido (View view){

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracionClientes", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase(); // se abrira la base de datos en modo lectura y escritura.

        String NombreClienteBuscar = et_nombreClienteBuscar.getText().toString();

        if (!NombreClienteBuscar.isEmpty()) {
            Cursor fila = BaseDeDatos.rawQuery
                    ("select ciudadCliente, numeroCliente" + " from Clientes where nombreCliente="+ et_nombreClienteBuscar, null);

            if (fila.moveToFirst()) {                                   // aquí trabajaremos con Arrays, OJO que el primer valor empieza en 0: 0,1,2,3...
                et_CiudadPedido.setText(fila.getString(0));
                et_ContactoPedido.setText(fila.getString(1));
                BaseDeDatos.close();
            } else {
                Toast.makeText(this, "No existe el registro del cliente", Toast.LENGTH_SHORT).show();
                BaseDeDatos.close();
            }
        } else {
            Toast.makeText(this, "Debes ingresar el nombre del cliente", Toast.LENGTH_SHORT).show();
        }

    }

    public void PEDIDOEliminarPedido (View view){

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracionPedidos", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String idPedidoEliminar = et_idPedido.getText().toString();
        if (!idPedidoEliminar.isEmpty()) {

            int cantidad = BaseDeDatos.delete("Pedidos", "idPedido=" + idPedidoEliminar, null);
            BaseDeDatos.close();
            et_idPedido.setText("");
            et_ClienteDelPedido.setText("");
            et_CiudadPedido.setText("");
            et_ContactoPedido.setText("");
            et_ProductoDelPedido.setText("");
            et_CantidadDelPedido.setText("");
            et_EstadoDelPedido.setText("");
            et_FechaCreacion.setText("");
            et_FechaEntrega.setText("");

            if (cantidad == 1) {
                Toast.makeText(this, "Pedido eliminado exitosamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "El pedido no existe", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Debes escribir un código/ID para eliminar un producto", Toast.LENGTH_SHORT).show();
        }

    }

    public void PEDIDOModificarPedido (View view){

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracionPedidos", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String idPedid = et_idPedido.getText().toString();
        String nombreCliPedid = et_ClienteDelPedido.getText().toString();
        String ciudadPedid = et_CiudadPedido.getText().toString();
        String contactoPedid = et_ContactoPedido.getText().toString();
        String productoDelPedid = et_ProductoDelPedido.getText().toString();
        String cantidadDelPedid = et_CantidadDelPedido.getText().toString();
        String estadoDelPedid = et_EstadoDelPedido.getText().toString();
        String fechaCreaciDelPedid = et_FechaCreacion.getText().toString();
        String fechaEntregDelPedid = et_FechaEntrega.getText().toString();

        if(!idPedid.isEmpty()  && !nombreCliPedid.isEmpty() && !ciudadPedid.isEmpty() && !contactoPedid.isEmpty() && !productoDelPedid.isEmpty() && !cantidadDelPedid.isEmpty() &&
                !estadoDelPedid.isEmpty() && !fechaCreaciDelPedid.isEmpty() && !fechaEntregDelPedid.isEmpty()){

            ContentValues registro = new ContentValues();
            registro.put("idPedido", idPedid);
            registro.put("nombreCliente", nombreCliPedid);
            registro.put("ciudadPedido", ciudadPedid);
            registro.put("nombreProducto", productoDelPedid);
            registro.put("cantidadProducto", cantidadDelPedid);
            registro.put("estadoPedido", estadoDelPedid);
            registro.put("fechaCreacion", fechaCreaciDelPedid);
            registro.put("fechaEntrega", fechaEntregDelPedid);
            registro.put("contactoPedido", contactoPedid);


            int cantidad = BaseDeDatos.update("Pedidos", registro, "idPedido=" + idPedid, null);
            BaseDeDatos.close();

            if(cantidad == 1){
                Toast.makeText(this, "Pedido modificado correctamente!", Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(this, "El pedido no existe", Toast.LENGTH_SHORT).show();

            }

        }else{
            Toast.makeText(this, "Debes llenar todos los campos!", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onClick(View v) {
        if(v==bbtnFechaCreacion){
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            ano = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    et_FechaCreacion.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                }
            },dia,mes,ano);
            datePickerDialog.show();
        }
        if(v==bbtnFechaEntrega){
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            ano = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    et_FechaEntrega.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                }
            },dia,mes,ano);
            datePickerDialog.show();
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}

