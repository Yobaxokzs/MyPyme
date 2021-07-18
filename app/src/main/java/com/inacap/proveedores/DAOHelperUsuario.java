package com.inacap.proveedores;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DAOHelperUsuario extends SQLiteOpenHelper  {


    private static String MI_BD = "BD_USUARIO";
    private static String MI_TABLA = "USUARIO";


    public DAOHelperUsuario(Context context) {
        super(context, MI_BD, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Definimos la tabla con sus campos
        String crearTabla = "CREATE TABLE " + MI_TABLA + "(ID_USU INTEGER PRIMARY KEY AUTOINCREMENT, NOM_USU TEXT, APE_USU TEXT, USERNAME TEXT, PASSWORD TEXT)";
        db.execSQL(crearTabla);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MI_TABLA);
    }


    //Creamos el método agregarPersona
    public long agregarUsuario(Usuario u) { //long se utiliza por el metodo
        //de retorno de los datos
        //Para abrir la conexión
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        //Objeto de tipo content values permite guardar
        //los datos a insertar
        cv.put("NOM_USU", u.getNombre());
        cv.put("APE_USU", u.getApellido());
        cv.put("USERNAME", u.getUsuario());
        cv.put("PASSWORD", u.getContrasena());
        //agregamos los datos a la BD
        //Método insert es propio de la clase SQLiteDatabase
        long res = db.insert(MI_TABLA, null, cv);
        //Retorna un long en caso de agregar datos
        //Si no hay datos retorna un valor -1
        db.close();
        return res;
    }



    //Creamos el método eliminarPersona
    public int eliminarUsuario(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(MI_TABLA, "ID_USU=?", new String[]{Integer.toString(id)});
        //Cerramos la conexión
        db.close();
        //Retornamos la respuesta
        return res;
    }

    //Creamos el método modificarPersona
    public int modificarUsuario(Usuario u) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("ID_USU", u.getId());
        cv.put("NOM_USU", u.getNombre());
        cv.put("APE_USU", u.getApellido());
        cv.put("USERNAME", u.getUsuario());
        cv.put("PASSWORD", u.getContrasena());
        //agregamos los datos a la BD
        int res = db.update(MI_TABLA, cv, "ID_USU=?", new String[]{Integer.toString(u.getId())});
        //Retorna un long en caso de agregar datos
        //Si no hay datos retorna un valor -1
        db.close();
        return res;
    }

    //Creamos un metodo obtenerPersonas
    public ArrayList<Usuario> obtenerUsuario() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor datos = db.rawQuery("SELECT * FROM " + MI_TABLA + " ORDER BY ID_USU ASC", null);
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        lista.clear();
        while (datos.moveToNext()) {
            Usuario u = new Usuario();
            u.setId(datos.getInt(0));
            u.setNombre(datos.getString(1));
            u.setApellido(datos.getString(2));
            u.setUsuario(datos.getString(3));
            u.setContrasena(datos.getString(4));
            lista.add(u);
        }
        db.close();
        return lista;
    }


    public int login(String u, String p) {
        int a = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor datos = db.rawQuery("SELECT * FROM " + MI_TABLA + " ORDER BY ID_USU ASC", null);
        if (datos != null && datos.moveToFirst()) {
            do {
                if (datos.getString(3).equals(u) && datos.getString(4).equals(p)) {
                    a++;
                }
            } while (datos.moveToNext());
        }
        return a;
    }

}