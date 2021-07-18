package com.inacap.proveedores;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class    Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * Intent que envia a Pantalla de Productos al hacer click.
     */

    public void Producto(View view) {
        Intent Producto = new Intent(this, ActivityProductos.class);
        startActivity(Producto);
    }

    /**
     * Intent que envia a Pantalla de Proveedor al hacer click.
     */

    public void Proveedor(View view) {
        Intent Proveedor = new Intent(this, MainActivity.class);
        startActivity(Proveedor);
    }

    public void Clientes(View view) {
        Intent Clientes = new Intent(this, ActivityClientes.class);
        startActivity(Clientes);
    }
    public void Pedidos(View view) {
        Intent Pedidos = new Intent(this, Pedidos.class);
        startActivity(Pedidos);
    }

    public void Inventario(View view) {
        Intent Inventario = new Intent(this, pruebainventario.class);
        startActivity(Inventario);
    }





}