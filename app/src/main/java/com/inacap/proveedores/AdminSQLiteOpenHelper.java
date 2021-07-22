package com.inacap.proveedores;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper  {
    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    /**
     *  Se crea el OPENHELPER de Productos, que permitirá darle a la app acceso a la tabla referenciada en el modelo de datos.
     */


    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {
        BaseDeDatos.execSQL("create table Productos(idProducto int primary key , categoriaProducto text, nombreProd text, precioProd real, stockProd real)");
        BaseDeDatos.execSQL("create table Cliente(idCliente int primary key , nombreCliente text, apellidoCliente text, numeroCliente real, correoCliente text, ciudadCliente text)");
        BaseDeDatos.execSQL("create table Pedidos(idPedido int primary key , nombreCliente text, ciudadPedido text, nombreProducto text, cantidadProducto real, estadoPedido text, fechaCreacion text, fechaEntrega text, contactoPedido text, precioTotal real)");
        BaseDeDatos.execSQL("Create table pedidoProducto(idPp int primary key AUTOINCREMENT, nombrePp text, precioPp real, cantidadPp real)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}