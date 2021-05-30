package com.inacap.proveedores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Inicio extends AppCompatActivity {

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



}