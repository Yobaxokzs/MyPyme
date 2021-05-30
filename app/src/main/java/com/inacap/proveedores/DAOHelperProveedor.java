package com.inacap.proveedores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DAOHelperProveedor extends SQLiteOpenHelper{
        private static String MI_BD = "BD_APP";
        private static String MI_TABLA = "PROVEEDOR";


        public DAOHelperProveedor(Context context) {
            super(context, MI_BD, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            /**
             * Definimos la tabla con sus campos
             */
            String crearTabla = "CREATE TABLE " + MI_TABLA + "(ID_PROVEEDOR INTEGER PRIMARY KEY, NOMBRE TEXT, APELLIDO TEXT, CORREO TEXT, NUMERO TEXT)";
            db.execSQL(crearTabla);
        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + MI_TABLA);
        }


        //Creamos el método agregarProveedor.
        public long agregarProveedor(Proveedor p) { //long se utiliza por el metodo
            /**
             * De retorno de los datos
             */
            /**
             * Para abrir la conexión
             */
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            /**
             * Objeto de tipo content values permite guardar datos a insertar
             */
            cv.put("ID_PROVEEDOR", p.getId_proveedor());
            cv.put("NOMBRE", p.getNombre());
            cv.put("APELLIDO", p.getApellido());
            cv.put("CORREO", p.getCorreo());
            cv.put("NUMERO", p.getNumero());
            /** agregamos los datos a la BD
             * */
            /** Método insert es propio de la clase SQLiteDatabase
              */

            long res = db.insert(MI_TABLA, null, cv);
            /** Retorna un long en caso de agregar datos
             */
            /** Si no hay datos retorna un valor -1
             */
            db.close();
            return res;
        }


    /** Creamos el método eliminarProveedor
     */
    public int eliminarProveedor(int id) {
            SQLiteDatabase db = this.getWritableDatabase();
            int res = db.delete(MI_TABLA, "ID_PROVEEDOR=?", new String[]{Integer.toString(id)});
        /**
         Cerramos la conexión
         */
            db.close();
            //Retornamos la respuesta
            return res;
        }

    /** Creamos el método modificarProveedor
     */
    public int modificarProveedor(Proveedor p) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("ID_PROVEEDOR", p.getId_proveedor());
            cv.put("NOMBRE", p.getNombre());
            cv.put("APELLIDO", p.getApellido());
            cv.put("CORREO", p.getCorreo());
            cv.put("NUMERO", p.getNumero());
        /**
         * agregamos los datos a la BD
         */
            int res = db.update(MI_TABLA, cv, "ID_PROVEEDOR=?", new String[]{Integer.toString(p.getId_proveedor())});
            //Retorna un long en caso de agregar datos
            //Si no hay datos retorna un valor -1
            db.close();
            return res;
        }

    /**Creamos un metodo obtenerProveedor para en un futuro poder listarlos.
     *
     */
    public ArrayList<Proveedor> obtenerProveedor() {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor datos = db.rawQuery("SELECT * FROM " + MI_TABLA + " ORDER BY ID_PROVEEDOR ASC", null);
            ArrayList<Proveedor> lista = new ArrayList<Proveedor>();
            lista.clear();
            while (datos.moveToNext()) {
                Proveedor p = new Proveedor();
                p.setId_proveedor(datos.getInt(0));
                p.setNombre(datos.getString(1));
                p.setApellido(datos.getString(2));
                p.setCorreo(datos.getString(3));
                p.setNumero(datos.getString(4));
                lista.add(p);
            }
            db.close();
            return lista;
        }

        /**Método para buscar un proveedor según la id que este posea.
         */
    public Proveedor buscarProveedor(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor datos = db.rawQuery("SELECT * FROM "+MI_TABLA+" WHERE ID_PROVEEDOR="+id,null);
        /**
         * Si encuentra algun dato
         */
        Proveedor p = null;
        if(datos.moveToNext()) {
            p = new Proveedor();
            p.setId_proveedor(Integer.parseInt(datos.getString(0)));
            p.setNombre(datos.getString(1));
            p.setApellido(datos.getString(2));
            p.setCorreo(datos.getString(3));
            p.setNumero(datos.getString(4));
        }
        /**
         * Cerramos la conexión a la BD
         */
        db.close();
        return p;
    }







}
