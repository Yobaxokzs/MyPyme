package com.inacap.proveedores;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class pruebainventario extends AppCompatActivity {

    ListView listViewPedidos;
    ArrayList<String> listaInformacion;
    ArrayList<ClasePedidos> listaPedidos;

    AdminSQLiteOpenHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pruebainventario);

        conn = new AdminSQLiteOpenHelper(getApplicationContext(),"administracionPedidos",null,1);

        listViewPedidos = (ListView) findViewById(R.id.id_listviewprueba);

        consultarListaInventario();

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaInformacion);
        listViewPedidos.setAdapter(adaptador);

        listViewPedidos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //logica.
                String informacion="ID: "+listaPedidos.get(position).getIdPedido()+'\n';
                informacion+="Nombre Cliente:"+listaPedidos.get(position).getNombreCliente()+'\n';
                informacion+="Ciudad Pedido:"+listaPedidos.get(position).getCiudadPedido()+'\n';
                informacion+="Nombre Producto:"+listaPedidos.get(position).getNombreProducto()+'\n';
                informacion+="Cantidad Producto:"+listaPedidos.get(position).getCantidadProducto()+'\n';
                informacion+="Estado Pedido:"+listaPedidos.get(position).getEstadoPedido()+'\n';
                informacion+="Fecha Creacion:"+listaPedidos.get(position).getFechaCreacion()+'\n';
                informacion+="Fecha Envio:"+listaPedidos.get(position).getFechaEntrega()+'\n';

                Toast.makeText(getApplicationContext(), informacion,Toast.LENGTH_LONG).show();
            }
        });

    }


      private  void consultarListaInventario(){
            SQLiteDatabase db = conn.getReadableDatabase();

            ClasePedidos pedido = null;
            listaPedidos = new ArrayList<ClasePedidos>();
            Cursor cursor = db.rawQuery("SELECT * FROM Pedidos",null);

        while (cursor.moveToNext()){
            pedido = new ClasePedidos();
            pedido.setIdPedido(cursor.getInt(0));
            pedido.setNombreCliente(cursor.getString(1));
            pedido.setCiudadPedido(cursor.getString(2));
            pedido.setNombreProducto(cursor.getString(3));
            pedido.setCantidadProducto(cursor.getInt(4));
            pedido.setEstadoPedido(cursor.getString(5));
            pedido.setFechaCreacion(cursor.getString(6));
            pedido.setFechaEntrega(cursor.getString(7));

            listaPedidos.add(pedido);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaInformacion = new ArrayList<>();
        for(int i=0; i<listaPedidos.size();i++){
            listaInformacion.add(listaPedidos.get(i).getIdPedido()+" - " + listaPedidos.get(i).getNombreCliente());
        }
    }


}
