package com.inacap.proveedores;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;

public class Pedidos extends AppCompatActivity implements View.OnClickListener {

    Button bbtnAgregarPedido, bbtnBuscarPedido, bbtnModificarPedido, bbtnEliminarPedido, bbtnFechaCreacion, bbtnFechaEntrega, bbtnBuscarNombreCli;
    EditText et_idPedido, et_ClienteDelPedido, et_CiudadPedido, et_ContactoPedido, et_ProductoDelPedido, et_CantidadDelPedido, et_EstadoDelPedido,
            et_FechaCreacion, et_FechaEntrega, et_nombreClienteBuscar, editTextTextPersonName;
    ListView productoslistado;
    ArrayList<String> listProductos;
    private int dia,mes,ano;
    Spinner comboestado;
    ArrayList<String> Estado, ListadoPrueba;
    TextView tv1, tv2, tv3, tv4, tv5, tv6;
    String dato, prueba;
    ListView listViewPp;
    ArrayList<String> listaInformacionPp;
    ArrayList<ClasePp> listaPp;
    AdminSQLiteOpenHelper conn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pedidos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tv1 = (TextView) findViewById(R.id.id_tv1);
        tv2 = (TextView) findViewById(R.id.id_tv2);
        tv3 = (TextView) findViewById(R.id.id_tv3);
        tv4 = (TextView) findViewById(R.id.id_tv4);
        tv5 = (TextView) findViewById(R.id.id_tv5);
        tv6 = (TextView) findViewById(R.id.id_tv6);

        bbtnAgregarPedido = (Button) findViewById(R.id.btnAgregarPedido);
        bbtnBuscarPedido = (Button) findViewById(R.id.btnBuscarCliente);
        bbtnModificarPedido = (Button) findViewById(R.id.btnModificarPedido);
        bbtnEliminarPedido = (Button) findViewById(R.id.btnEliminarPedido);
        bbtnFechaCreacion = (Button) findViewById(R.id.btnFechaCreacion);
        bbtnFechaEntrega = (Button) findViewById(R.id.btnFechaEntrega);
        bbtnBuscarNombreCli = (Button) findViewById(R.id.btnBuscarNombreCliente);
        comboestado = (Spinner) findViewById(R.id.spinner3);

        bbtnFechaCreacion.setOnClickListener(this);
        bbtnFechaEntrega.setOnClickListener(this);
        et_idPedido = (EditText) findViewById(R.id.txt_IdPedido);
        et_FechaCreacion = (EditText) findViewById(R.id.txt_FechaCreacion);
        et_FechaEntrega = (EditText) findViewById(R.id.txt_FechaEntrega);
        productoslistado = (ListView) findViewById(R.id.listado);
        conn = new AdminSQLiteOpenHelper(getApplicationContext(),"administracionPp",null,1);
        listViewPp = (ListView) findViewById(R.id.listado);
        consultarListaPp();
        obtenerLista();
        cargarListado();
        consultaCantidad();
        precioProductos();
        ArrayAdapter adaptadorPp = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaInformacionPp);
        listViewPp.setAdapter(adaptadorPp);
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Estado);
        comboestado.setAdapter(adaptador);
        String datos="";
        datos = obtenerStrings();
        String cantidadx = "";
        cantidadx = consultaCantidad();
        String preciox ="";
        preciox= precioProductos();
        // Pa mostrar la wea de productos seleccionados Toast.makeText(this, obtenerStrings(), Toast.LENGTH_SHORT).show();
        String nombreCliente = getIntent().getStringExtra("nombreCliente");
        tv3.setText(nombreCliente);
        String ciudadCliente = getIntent().getStringExtra("ciudadCliente");
        tv4.setText(ciudadCliente);
        int numeroCliente = getIntent().getIntExtra("numeroCliente", 0);
        String numeroCli = String.valueOf(numeroCliente);
        tv5.setText(numeroCli);

        String nombreProducto = getIntent().getStringExtra("nombreProducto");
        tv1.setText(datos);
        int stockProducto = getIntent().getIntExtra("stockProducto", 0);
        String stockText = String.valueOf(stockProducto);
        tv2.setText(cantidadx);
        tv6.setText(preciox);

        /* Seteo /*/
        String valor = getIntent().getStringExtra("hola");
        if (valor != null) {
            int idpedido = getIntent().getIntExtra("idPedidoINI", 0);
            String idPedidoText = String.valueOf(idpedido);
            et_idPedido.setText(idPedidoText);
            String nombreClienteINI = getIntent().getStringExtra("nombreClienteINI");
            tv3.setText(nombreClienteINI);
            String ciudadPedidoINI = getIntent().getStringExtra("ciudadPedidoINI");
            tv4.setText(ciudadPedidoINI);
            String contactoPedidoINI = getIntent().getStringExtra("ContactoPedidoINI");
            tv5.setText(contactoPedidoINI);
            String nombreProductoINI = getIntent().getStringExtra("NombreProductoINI");
            tv1.setText(nombreProductoINI);
            int CantidadProductoINI = getIntent().getIntExtra("CantidadProductoINI", 0);
            String cantTextINI = String.valueOf(CantidadProductoINI);
            tv2.setText(cantTextINI);
            String fechaCreacionINI = getIntent().getStringExtra("FechaCreacionINI");
            et_FechaCreacion.setText(fechaCreacionINI);
            String fechaEntregaINI = getIntent().getStringExtra("FechaEntregaINI");
            et_FechaEntrega.setText(fechaEntregaINI);
            String estadoPedido1 = getIntent().getStringExtra("estadoINI");
                for(int i= 0; i < comboestado.getAdapter().getCount(); i++)
                {
                    if(comboestado.getAdapter().getItem(i).toString().contains(estadoPedido1))
                    {
                        comboestado.setSelection(i);
                    }
                }
        }
    }

    public void PEDIDOAgregarPedido (View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracionPedidos", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        String idPedid = et_idPedido.getText().toString();

        String productoDelPedid = tv1.getText().toString();
        String cantidadDelPedid = tv2.getText().toString();

        String nombreCliPedid = tv3.getText().toString();
        String ciudadPedid = tv4.getText().toString();
        String contactoPedid = tv5.getText().toString();
        String precioPedido = tv6.getText().toString();

        String estadoDelPedid = comboestado.getSelectedItem().toString();
        String fechaCreaciDelPedid = et_FechaCreacion.getText().toString();
        String fechaEntregDelPedid = et_FechaEntrega.getText().toString();

        if (!idPedid.isEmpty()  && !nombreCliPedid.isEmpty() && !ciudadPedid.isEmpty() && !contactoPedid.isEmpty() && !productoDelPedid.isEmpty() && !cantidadDelPedid.isEmpty() &&
                !estadoDelPedid.isEmpty() && !fechaCreaciDelPedid.isEmpty() && !fechaEntregDelPedid.isEmpty() && !precioPedido.isEmpty()) {
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
            registro.put("precioTotal", precioPedido);
            BaseDeDatos.insert("Pedidos", null, registro);

            et_idPedido.setText("");
            tv1.setText("");
            tv2.setText("");
            tv3.setText("");
            tv4.setText("");
            tv5.setText("");
            tv6.setText("");
            et_FechaCreacion.setText("");
            et_FechaEntrega.setText("");
            deleteAll();
            Toast.makeText(this, "Registro existoso!", Toast.LENGTH_SHORT).show();
            Intent e = new Intent(view.getContext(), Pedidos.class);
            startActivity(e);
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
        Intent e = new Intent(this, listaProductos.class);
        startActivity(e);
    }

    public void PEDIDOBuscarProductoPedido (View view){
        Intent i = new Intent(this, listaProductos.class);
        startActivity(i);
    }

    public void PEDIDOEliminarPedido (View view){

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracionPedidos", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String idPedidoEliminar = et_idPedido.getText().toString();
        if (!idPedidoEliminar.isEmpty()) {

            int cantidad = BaseDeDatos.delete("Pedidos", "idPedido=" + idPedidoEliminar, null);
            BaseDeDatos.close();
            et_idPedido.setText("");
            tv1.setText("");
            tv2.setText("");
            tv3.setText("");
            tv4.setText("");
            tv5.setText("");
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
        String productoDelPedid = tv1.getText().toString();
        String cantidadDelPedid = tv2.getText().toString();

        String nombreCliPedid = tv3.getText().toString();
        String ciudadPedid = tv4.getText().toString();
        String contactoPedid = tv5.getText().toString();

        String estadoDelPedid = comboestado.getSelectedItem().toString();
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
    public void CLIENTEIrAlListadoPINI (View view){
        Intent e = new Intent(this, listaPedidosINI.class);
        startActivity(e);
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


    private void obtenerLista() {
        Estado = new ArrayList<String>();
        Estado.add("Pendiente");
        Estado.add("Entregado");
    }

    private void cargarListado(){
        ListadoPrueba = new ArrayList<String>();
        ListadoPrueba.add("Kukys");
        ListadoPrueba.add("Holas");

    }

    private  void consultarListaPp(){
        SQLiteDatabase db = conn.getReadableDatabase();
        ClasePp Pp = null;
        listaPp = new ArrayList<ClasePp>();
        Cursor cursor = db.rawQuery("SELECT * FROM pedidoProducto",null);
        while (cursor.moveToNext()){
            Pp = new ClasePp();
            Pp.setIdPp(cursor.getInt(0));
            Pp.setNombrePp(cursor.getString(1));
            Pp.setPrecioPp(cursor.getInt(2));
            Pp.setCantidadPp(cursor.getInt(3));

            listaPp.add(Pp);
        }
        obtenerListaPp();
    }
    private void obtenerListaPp() {
        listaInformacionPp = new ArrayList<>();
        for(int i=0; i<listaPp.size();i++){
            listaInformacionPp.add(listaPp.get(i).getNombrePp()+" | " + listaPp.get(i).getPrecioPp()+" | " + listaPp.get(i).getCantidadPp());
        }
    }


    private String obtenerStrings() {
        String productoscanasta="";
        listaInformacionPp = new ArrayList<>();
        for(int i=0; i<listaPp.size();i++){
            productoscanasta += (listaPp.get(i).getNombrePp()+" | " );
        }
        return productoscanasta;
    }



    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        dato = tv3.getText().toString();
        outState.putString("nombreCliente", dato);
    }

    public void deleteAll()
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracionPp", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        BaseDeDatos.delete("pedidoProducto",null, null);
    }

    private  String consultaCantidad(){
        SQLiteDatabase db = conn.getReadableDatabase();
        String cantidadpp ="";
        Cursor cursor = db.rawQuery("SELECT SUM(cantidadPp) FROM pedidoProducto",null);
        while (cursor.moveToNext()){
            cantidadpp = (cursor.getString(0));
        }
        return cantidadpp;
    }

    private  String precioProductos(){
        SQLiteDatabase db = conn.getReadableDatabase();
        String preciopp ="";
        Cursor cursor = db.rawQuery("SELECT SUM(precioPp) FROM pedidoProducto",null);
        while (cursor.moveToNext()){
            preciopp = (cursor.getString(0));
        }
        return preciopp;
    }


}

