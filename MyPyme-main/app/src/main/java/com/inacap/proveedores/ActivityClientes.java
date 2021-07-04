package com.inacap.proveedores;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityClientes extends AppCompatActivity {

    private EditText et_idCliente, et_nombreCliente, et_apellidoCliente, et_numeroCliente, et_correoCliente, et_nombreClienteBuscar, et_ciudadCliente;
    Button bbtnAgregarCliente, bbtnModificarCliente, bbtnBuscarCliente, bbtnEliminarCliente, bbtnBuscarNomCli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clientes);


        et_idCliente = (EditText) findViewById(R.id.txt_idCliente);
        et_nombreCliente = (EditText) findViewById(R.id.txt_nombreCliente);
        et_apellidoCliente = (EditText) findViewById(R.id.txt_apellidoCliente);
        et_numeroCliente = (EditText) findViewById(R.id.txt_numeroCliente);
        et_correoCliente = (EditText) findViewById(R.id.txt_correoCliente);
        et_nombreClienteBuscar = (EditText) findViewById(R.id.txt_nombreClienteBuscar);
        et_ciudadCliente = (EditText) findViewById(R.id.txt_ciudadCliente);

        bbtnAgregarCliente = (Button) findViewById(R.id.btnAgregarCliente);
        bbtnModificarCliente = (Button) findViewById(R.id.btnModificarCliente);
        bbtnBuscarCliente = (Button) findViewById(R.id.btnBuscarCliente);
        bbtnEliminarCliente = (Button)findViewById(R.id.btnEliminarCliente);
        bbtnBuscarNomCli = (Button)findViewById(R.id.btnBuscarNomCli);

    }

    public void CLIENTEAgregarCliente(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracionClientes", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        String idCli = et_idCliente.getText().toString();
        String nombreCli = et_nombreCliente.getText().toString();
        String apellidoCli = et_apellidoCliente.getText().toString();
        String numeroCli = et_numeroCliente.getText().toString();
        String correoCli = et_correoCliente.getText().toString();
        String ciudadCli = et_ciudadCliente.getText().toString();

        if (!idCli.isEmpty()  && !nombreCli.isEmpty() && !apellidoCli.isEmpty() && !numeroCli.isEmpty() && !correoCli.isEmpty() && !ciudadCli.isEmpty()) {
            ContentValues registro = new ContentValues();
            registro.put("idCliente", idCli);
            registro.put("nombreCliente", nombreCli);
            registro.put("apellidoCliente", apellidoCli);
            registro.put("numeroCliente", numeroCli);
            registro.put("correoCliente", correoCli);
            registro.put("ciudadCliente", ciudadCli);

            BaseDeDatos.insert("Cliente", null, registro);

            BaseDeDatos.close();
            et_idCliente.setText("");
            et_nombreCliente.setText("");
            et_apellidoCliente.setText("");
            et_numeroCliente.setText("");
            et_correoCliente.setText("");
            et_nombreClienteBuscar.setText("");
            et_ciudadCliente.setText("");

            Toast.makeText(this, "Registro existoso!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void CLIENTEBuscarCliente(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracionClientes", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase(); // se abrira la base de datos en modo lectura y escritura.

        String IdClienteBuscar = et_idCliente.getText().toString();

        if (!IdClienteBuscar.isEmpty()) {
            Cursor fila = BaseDeDatos.rawQuery
                    ("select idCliente, nombreCliente, apellidoCliente,numeroCliente, correoCliente, ciudadCliente  from Cliente where idCliente="+ IdClienteBuscar, null);

            if (fila.moveToFirst()) {
                et_idCliente.setText(fila.getString(0));
                et_nombreCliente.setText(fila.getString(1));
                et_apellidoCliente.setText(fila.getString(2));
                et_numeroCliente.setText(fila.getString(3));
                et_correoCliente.setText(fila.getString(4));
                et_ciudadCliente.setText(fila.getString(5));
                BaseDeDatos.close();
            } else {
                Toast.makeText(this, "No existe el registro de cliente", Toast.LENGTH_SHORT).show();
                BaseDeDatos.close();
            }
        } else {
            Toast.makeText(this, "Debes ingresar el nombre del cliente", Toast.LENGTH_SHORT).show();
        }
    }

    public void CLIENTEEliminarCliente(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracionClientes", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String idClienteEliminar = et_idCliente.getText().toString();
        if (!idClienteEliminar.isEmpty()) {

            int cantidad = BaseDeDatos.delete("Cliente", "idCliente=" + idClienteEliminar, null);
            BaseDeDatos.close();
            et_idCliente.setText("");
            et_nombreCliente.setText("");
            et_apellidoCliente.setText("");
            et_numeroCliente.setText("");
            et_correoCliente.setText("");
            et_nombreClienteBuscar.setText("");

            if (cantidad == 1) {
                Toast.makeText(this, "Cliente eliminado exitosamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "El cliente no existe", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Debes escribir un ID para eliminar un cliente", Toast.LENGTH_SHORT).show();
        }
    }

    public void CLIENTEModificarCliente(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracionClientes", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String idCli = et_idCliente.getText().toString();
        String nombreCli = et_nombreCliente.getText().toString();
        String apellidoCli = et_apellidoCliente.getText().toString();
        String numeroCli = et_numeroCliente.getText().toString();
        String correoCli = et_correoCliente.getText().toString();
        String ciudadCli = et_ciudadCliente.getText().toString();

        if(!idCli.isEmpty() &&  !nombreCli.isEmpty() && !apellidoCli.isEmpty() && !numeroCli.isEmpty() && !correoCli.isEmpty() && !ciudadCli.isEmpty()){

            ContentValues registro = new ContentValues();
            registro.put("idCliente", idCli);
            registro.put("nombreCliente", nombreCli);
            registro.put("apellidoCliente", apellidoCli);
            registro.put("numeroCliente", numeroCli);
            registro.put("correoCliente", correoCli);
            registro.put("ciudadCliente", ciudadCli);


            int cantidad = BaseDeDatos.update("Cliente", registro, "idCliente=" + idCli, null);
            BaseDeDatos.close();

            if(cantidad == 1){
                Toast.makeText(this, "Cliente modificado correctamente!", Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(this, "el cliente no existe", Toast.LENGTH_SHORT).show();

            }

        }else{
            Toast.makeText(this, "Debes llenar todos los campos!", Toast.LENGTH_SHORT).show();
        }

    }

}