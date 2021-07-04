package com.inacap.proveedores;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Registrar extends AppCompatActivity {

    //Llamamos al DAOHelper
    private DAOHelperUsuario d;

    private EditText  etNom, etApe, etUsu, etPas;
    private Button btnAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrar);
        //Inicializamos el DAOHelper dentro del onCreate
        d = new DAOHelperUsuario(this);

        //Enlazamos elementos del XML (GUI) a la clase MainActivity
        etNom = (EditText) findViewById(R.id.etPass);
        etApe = (EditText) findViewById(R.id.etApe);
        etUsu = (EditText) findViewById(R.id.etUsu);
        etPas = (EditText) findViewById(R.id.etPas);
        btnAgregar = (Button) findViewById(R.id.btnAcceder);


        //Dejamos el btnAgregar deshabilitado por defecto
        btnAgregar.setEnabled(true);


        //Llamamos al método buscarPersona()
        agregarUsuario();
        limpiarCampos();
    }


    //Creamos el método limpiar campos
    public void limpiarCampos() {
        //Vacía campos de texto y numeros y desactiva botones
        etNom.setText("");
        etApe.setText("");
        etUsu.setText("");
        etPas.setText("");
        btnAgregar.setEnabled(true);
    }


    //Creamos el método agregar persona
    public void agregarUsuario() {
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Nos aseguramos que los campos tienen datos
                if (etNom.getText().toString().isEmpty()
                        || etApe.getText().toString().isEmpty() || etUsu.getText().toString().isEmpty()
                        || etPas.getText().toString().isEmpty()) {
                    Toast.makeText(Registrar.this, "Hay campos vacíos", Toast.LENGTH_SHORT).show();
                } else {
                    Usuario u = new Usuario();
                    u.setNombre(etNom.getText().toString());
                    u.setApellido(etApe.getText().toString());
                    u.setUsuario(etUsu.getText().toString());
                    u.setContraseña(etPas.getText().toString());
                    long res = d.agregarUsuario(u);
                    if (res != -1) {
                        //Mostramos un OK (AlertDialog (método display())
                        StringBuffer buffer = new StringBuffer();
                        buffer.append("Nombre: " + u.getNombre() + "\n");
                        buffer.append("Apellido: " + u.getApellido() + "\n");
                        buffer.append("Usuario: " + u.getUsuario() + "\n");
                        buffer.append("Contraseña: " + u.getContraseña());
                        limpiarCampos();
                    } else {
                        //Mostrar un error
                        Toast.makeText(Registrar.this, "Nombre de Usuario ya existe", Toast.LENGTH_SHORT).show();
                        etUsu.setText("");
                        etUsu.requestFocus();
                    }
                }
            }
        });
    }
}