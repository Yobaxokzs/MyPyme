package com.inacap.proveedores;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {

    /**
     *  Declaración de variables.
     */
    private DAOHelperProveedor d;
    EditText etID, etNombre, etApellido, etCorreo, etNumero;
    Button btnAgregar, btnEliminar, btnModificar, btnBuscar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.proveedores);
        d = new DAOHelperProveedor(this);

        /**
         * Enlace de elementos al XML..
         */
        etID = (EditText) findViewById(R.id.etID);
        etNombre = (EditText) findViewById(R.id.etNombre);
        etApellido = (EditText) findViewById(R.id.etApellido);
        etCorreo = (EditText) findViewById(R.id.etCorreo);
        etNumero = (EditText) findViewById(R.id.etNumero);

        btnAgregar = (Button) findViewById(R.id.ButtonAgregar2);
        btnBuscar = (Button) findViewById(R.id.buttonbusqueda);
        btnEliminar = (Button) findViewById(R.id.buttonEliminar2);
        btnModificar = (Button) findViewById(R.id.buttonModificar2);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnAgregar.setEnabled(false);

        /**
         *  Llamado de métodos.
         */
        agregarProveedor();
        limpiarCampos();
        eliminarProveedor();
        modificarProveedor();
        buscarProveedor();

    }

    /**
     *  Creación de método limpiar campos.
     */
    public void limpiarCampos() {
        /**
         *  Limpia los campos y desactiva botones.
         */
        etID.setText("");
        etNombre.setText("");
        etApellido.setText("");
        etCorreo.setText("");
        etNumero.setText("");
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }
    /**
     *  Creación de método buscar Proveedor.
     */

    public void buscarProveedor() {

        /**
         *  Busca los proveedores.
         */
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Si el campo de texto está vacío
                if (etID.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "El ID está vacío", Toast.LENGTH_SHORT).show();
                } //si el ID tiene datos, los rescato
                else {
                    String id = (etID.getText().toString());
                    Proveedor p = d.buscarProveedor(id);
                    //Si no encuentra el ID permite guardar
                    if (p == null) {
                        Toast.makeText(MainActivity.this, "El ID no existe, puede guardar este dato", Toast.LENGTH_SHORT).show();
                        //Habilitamos y Deshabilitamos los botones necesarios
                        btnAgregar.setEnabled(true);
                        btnEliminar.setEnabled(false);
                        btnModificar.setEnabled(false);
                        //requestFocus sirve para dejar el puntero posicionado en el campo etID.
                        etID.requestFocus();
                    } //Si encuentra datos, los muestro en la app
                    else {
                        etNombre.setText(p.getNombre());
                        etApellido.setText(p.getApellido());
                        etCorreo.setText(p.getCorreo());
                        etNumero.setText(p.getNumero());
                        btnAgregar.setEnabled(false);
                        btnEliminar.setEnabled(true);
                        btnModificar.setEnabled(true);
                    }
                }
            }
        });
    }






    public void display(String titulo, String mensaje) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //Construimos la ventaja de dialogo de alerta
        builder.setCancelable(true);
        builder.setTitle(titulo);
        builder.setMessage(mensaje);
        builder.show();
    }

    /**
     *  Creación de método agregarProveedor.
     */
    public void agregarProveedor() {
        /**
         *  Agrega al proveedor a la base de datos con validaciones de campos.
         */
        btnAgregar.setOnClickListener(v -> {
            //Nos aseguramos que los campos tienen datos
            if (etNombre.getText().toString().isEmpty() || etApellido.getText().toString().isEmpty()){
                Toast.makeText(MainActivity.this, "Hay campos vacíos", Toast.LENGTH_SHORT).show();
            }else{
                Proveedor p = new Proveedor();
                p.setId_proveedor(Integer.parseInt(etID.getText().toString()));
                p.setNombre(etNombre.getText().toString());
                p.setApellido(etApellido.getText().toString());
                p.setCorreo(etCorreo.getText().toString());
                p.setNumero(etNumero.getText().toString());
                long res = d.agregarProveedor(p);
                if (res != -1) {
                    //Mostramos un OK (AlertDialog (método display())
                    StringBuffer buffer = new StringBuffer();
                    buffer.append("ID: " + p.getId_proveedor() + "\n");
                    buffer.append("Nombre: " + p.getNombre() + "\n");
                    buffer.append("Apellido: " + p.getApellido()+ "\n");
                    buffer.append("Correo: " + p.getCorreo()+ "\n");
                    buffer.append("Número: " + p.getNumero()+ "\n");
                    display("DATOS AGREGADOS CORRECTAMENTE", buffer.toString());
                    limpiarCampos();
                } else {
                    //Mostrar un error
                    Toast.makeText(MainActivity.this, "ID ya existe", Toast.LENGTH_SHORT).show();
                    etID.setText("");
                    etID.requestFocus();
                }
            }
        });
    }
    /**
     *  Creación de método eliminarProveedor.
     */
    public void eliminarProveedor() {
        /**
         *  Método que elimina al proveedor según la id que obtenga.
         */
        btnEliminar.setOnClickListener(v -> {
            //Preguntamos si el ID está vacío
            if (etID.getText().toString().isEmpty()) {
                Toast.makeText(MainActivity.this, "El ID está vacío", Toast.LENGTH_SHORT).show();
            }//Si hay contenido...
            else {
                int id = Integer.parseInt(etID.getText().toString());
                int res = d.eliminarProveedor(id);
                if (res == 1) {
                    Toast.makeText(MainActivity.this, "Registro eliminado correctamente", Toast.LENGTH_SHORT).show();
                    limpiarCampos();
                } else if (res != 1) {
                    Toast.makeText(MainActivity.this, "El ID no existe", Toast.LENGTH_SHORT).show();
                    etID.setText("");
                    etID.requestFocus();
                }
            }
        });
    }
    /**
     *  Creación de método modificarProveedor.
     */
        public void modificarProveedor(){
            /**
             *  Modifica al proveedor según la informaación almacenada en el ET.
             */

            btnModificar.setOnClickListener(v -> {
                //Preguntamos si el campo del id está vacío
                if (etID.getText().toString().isEmpty() ||  etNombre.getText().toString().isEmpty()
                        || etApellido.getText().toString().isEmpty() || etCorreo.getText().toString().isEmpty() || etNumero.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "El ID está vacío", Toast.LENGTH_SHORT).show();
                } //Si no está vacío verifico si los demás campos tambien tienen datos
                else {
                    if((etID.getText().toString().isEmpty() ||  etNombre.getText().toString().isEmpty()
                            || etApellido.getText().toString().isEmpty() || etCorreo.getText().toString().isEmpty() || etNumero.getText().toString().isEmpty())){
                        Toast.makeText(MainActivity.this, "Hay campos vacios", Toast.LENGTH_SHORT).show();
                    } else {
                        Proveedor p = new Proveedor();
                        p.setId_proveedor(Integer.parseInt(etID.getText().toString()));
                        p.setNombre(etNombre.getText().toString());
                        p.setApellido(etApellido.getText().toString());
                        p.setCorreo(etCorreo.getText().toString());
                        p.setNumero(etNumero.getText().toString());
                        int res = d.modificarProveedor(p);
                        if (res == 1) {
                            //Mostramos un OK (AlertDialog (método display())
                            StringBuffer buffer = new StringBuffer();
                            buffer.append("ID: " + p.getId_proveedor() + "\n");
                            buffer.append("Nombre: " + p.getNombre() + "\n");
                            buffer.append("Apellido: " + p.getApellido()+ "\n");
                            buffer.append("Correo: " + p.getCorreo()+ "\n");
                            buffer.append("Número: " + p.getNumero()+ "\n");
                            display("DATOS AGREGADOS CORRECTAMENTE", buffer.toString());
                            limpiarCampos();
                        } else if (res != 1) {
                            Toast.makeText(MainActivity.this, "El ID no existe", Toast.LENGTH_SHORT).show();
                            etID.setText("");
                            etID.requestFocus();
                        }
                    }
                }
            });
    }

}

