package com.inacap.proveedores;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ActivityClientes extends AppCompatActivity {

    private EditText et_idCliente, et_nombreCliente, et_apellidoCliente, et_numeroCliente, et_correoCliente, et_ciudadCliente;
    Button bbtnAgregarCliente, bbtnModificarCliente, bbtnBuscarCliente, bbtnEliminarCliente ;
    Spinner combociudad;
    ArrayList<String> listaClientesx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clientes);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        et_idCliente = (EditText) findViewById(R.id.txt_idCliente);
        et_nombreCliente = (EditText) findViewById(R.id.txt_nombreCliente);
        et_apellidoCliente = (EditText) findViewById(R.id.txt_apellidoCliente);
        et_numeroCliente = (EditText) findViewById(R.id.txt_numeroCliente);
        et_correoCliente = (EditText) findViewById(R.id.txt_correoCliente);
        combociudad = (Spinner) findViewById(R.id.spinner2);

        bbtnAgregarCliente = (Button) findViewById(R.id.btnAgregarCliente);
        bbtnModificarCliente = (Button) findViewById(R.id.btnModificarCliente);
        bbtnBuscarCliente = (Button) findViewById(R.id.btnBuscarCliente);
        bbtnEliminarCliente = (Button)findViewById(R.id.btnEliminarCliente);

        obtenerLista();
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaClientesx);
        combociudad.setAdapter(adaptador);

        int idCli = getIntent().getIntExtra("idCliente",0);
        String idcliText= String.valueOf(idCli);
        et_idCliente.setText(idcliText);

        String nombreCli = getIntent().getStringExtra("nombreCliente");
        et_nombreCliente.setText(nombreCli);

        String valor = getIntent().getStringExtra("valor");
        String ciudadCli = getIntent().getStringExtra("ciudadCliente");
        if(valor !=null) {
            for(int i= 0; i < combociudad.getAdapter().getCount(); i++)
            {
                if(combociudad.getAdapter().getItem(i).toString().contains(ciudadCli))
                {
                    combociudad.setSelection(i);
                }
            }
        }

        String apelliCli = getIntent().getStringExtra("apellidoCliente");
        et_apellidoCliente.setText(apelliCli);

        int numCli = getIntent().getIntExtra("numeroCliente",0);
        String numcliText= String.valueOf(numCli);
        et_numeroCliente.setText(numcliText);

        String correCli = getIntent().getStringExtra("correoCliente");
        et_correoCliente.setText(correCli);

        //String ciuCli = getIntent().getStringExtra("ciudadCliente");
        //combociudad.setText(ciuCli);

    }

    public void CLIENTEIrAlListadoCliINI (View view){
        Intent e = new Intent(this, listaClientesINI.class);
        startActivity(e);
    }

    public void CLIENTEAgregarCliente(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracionClientes", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        String idCli = et_idCliente.getText().toString();
        String nombreCli = et_nombreCliente.getText().toString();
        String ciudadCli = combociudad.getSelectedItem().toString();
        String apellidoCli = et_apellidoCliente.getText().toString();
        String numeroCli = et_numeroCliente.getText().toString();
        String correoCli = et_correoCliente.getText().toString();

        if (!idCli.isEmpty()  && !nombreCli.isEmpty() && !apellidoCli.isEmpty() && !numeroCli.isEmpty() && !correoCli.isEmpty()) {
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
        String ciudadCli = combociudad.getSelectedItem().toString();

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

    private void obtenerLista(){
        listaClientesx = new ArrayList<String>();
        listaClientesx.add("Arica y Parinacota");
        listaClientesx.add("Tarapaca");
        listaClientesx.add("Antofagasta");
        listaClientesx.add("Atacama");
        listaClientesx.add("Coquimbo");
        listaClientesx.add("Valparaiso");
        listaClientesx.add("Metropolitana");
        listaClientesx.add("O-Higgins");
        listaClientesx.add("del Maule");
        listaClientesx.add("Bío-Bío");
        listaClientesx.add("La Araucanía");
        listaClientesx.add("de Los Lagos");
        listaClientesx.add("Aysén");
        listaClientesx.add("Magallanes");
        listaClientesx.add("de Los Ríos");
        listaClientesx.add("de Ñuble");
    }

}