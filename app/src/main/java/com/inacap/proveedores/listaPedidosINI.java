package com.inacap.proveedores;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class listaPedidosINI extends AppCompatActivity {

    ListView listViewPedidos;
    ArrayList<String> listaInformacionPedidosINI;
    ArrayList<ClasePedidos> listaPedidosINI;
    EditText buscadorPedidosINI;
    AdminSQLiteOpenHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listapedidoini);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        conn = new AdminSQLiteOpenHelper(getApplicationContext(),"administracionPedidos",null,1);

        listViewPedidos = (ListView) findViewById(R.id.id_listapedidosINI);
        buscadorPedidosINI = (EditText) findViewById(R.id.et_idBuscadorPedidosINI);

        consultarListaPedidos();

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaInformacionPedidosINI);
        listViewPedidos.setAdapter(adaptador);

        listViewPedidos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //logica.
                //se mostrará un Alertdialog con opciones a realizar con la selección.
                final CharSequence[] opciones={"Seleccionar","Cancelar"};
                final AlertDialog.Builder alertOpciones = new AlertDialog.Builder(com.inacap.proveedores.listaPedidosINI.this);
                alertOpciones.setTitle("Seleccione una opcion");
                alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int x) {
                        if (opciones[x].equals("Seleccionar")){
                            //cuando se elija seleccionar, pasará algo...
                            Intent u = new Intent(view.getContext(), Pedidos.class);
                            String hola="hola";
                            u.putExtra("nombreClienteINI", listaPedidosINI.get(position).getNombreCliente());
                            u.putExtra("ciudadPedidoINI", listaPedidosINI.get(position).getCiudadPedido());
                            u.putExtra("NombreProductoINI", listaPedidosINI.get(position).getNombreProducto());
                            u.putExtra("idPedidoINI", listaPedidosINI.get(position).getIdPedido());
                            u.putExtra("CantidadProductoINI", listaPedidosINI.get(position).getCantidadProducto());
                            u.putExtra("estadoINI", listaPedidosINI.get(position).getEstadoPedido());
                            u.putExtra("FechaCreacionINI", listaPedidosINI.get(position).getFechaCreacion());
                            u.putExtra("FechaEntregaINI", listaPedidosINI.get(position).getFechaEntrega());
                            u.putExtra("ContactoPedidoINI", listaPedidosINI.get(position).getContactoPedido());
                            u.putExtra("hola", hola);
                            startActivity(u);
                            }else {
                                //si no es ninguna de las opciones, se cierra el dialog.
                                dialog.dismiss();
                            }
                        }
                });
                alertOpciones.show();
            }
        });

        //--- filtro campo editText.
        buscadorPedidosINI.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adaptador.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private  void consultarListaPedidos(){
        SQLiteDatabase db = conn.getReadableDatabase();

        ClasePedidos pedidos = null;
        listaPedidosINI = new ArrayList<ClasePedidos>();
        Cursor cursor = db.rawQuery("SELECT * FROM Pedidos",null);

        while (cursor.moveToNext()){
            pedidos = new ClasePedidos();
            pedidos.setIdPedido(cursor.getInt(0));
            pedidos.setNombreCliente(cursor.getString(1));
            pedidos.setCiudadPedido(cursor.getString(2));
            pedidos.setNombreProducto(cursor.getString(3));
            pedidos.setCantidadProducto(cursor.getInt(4));
            pedidos.setEstadoPedido(cursor.getString(5));
            pedidos.setFechaCreacion(cursor.getString(6));
            pedidos.setFechaEntrega(cursor.getString(7));
            pedidos.setContactoPedido(cursor.getString(8));

            listaPedidosINI.add(pedidos);
        }
        obtenerListaP();
    }

    private void obtenerListaP() {
        listaInformacionPedidosINI = new ArrayList<>();
        for(int i=0; i<listaPedidosINI.size();i++){
            listaInformacionPedidosINI.add(listaPedidosINI.get(i).getIdPedido()+" - " + listaPedidosINI.get(i).getNombreCliente()+" - " + listaPedidosINI.get(i).getNombreProducto()+" - " + listaPedidosINI.get(i).getEstadoPedido());
        }
    }




}
