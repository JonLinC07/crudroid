package com.example.ojmlc.crudroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DBPlaneta {

    SQLiteDatabase DB;
    ArrayList<Planeta> lista = new ArrayList<Planeta>();
    Planeta planeta;
    Context ct;
    String nameBD = "BDPlanetas", tabla = "CREATE TABLE IF NOT EXISTS Planeta(id INTEGER PRIMARY KEY AUTOINCREMENT, Nombre TEXT, Tipo TEXT, Fecha DATE, " +
            "Diametro INTEGER)";

    public DBPlaneta (Context c) {
        this.ct = c;
        DB = c.openOrCreateDatabase(nameBD, Context.MODE_PRIVATE, null);
        DB.execSQL(tabla);
    }

    public boolean Insertar(Planeta planeta) {
        ContentValues cv = new ContentValues();
        cv.put("Nombre", planeta.getNombre());
        cv.put("Tipo", planeta.getTipo());
        cv.put("Fecha", planeta.getFecha());
        cv.put("Diametro", planeta.getDiametro());

        return (DB.insert("Planeta", null, cv)) > 0;
    }

    public boolean Eliminar(int id) {
        return (DB.delete("Planeta", "id = " + id, null)) > 0;
    }

    public boolean Editar(Planeta p) {
        ContentValues cv = new ContentValues();
        cv.put("Nombre", planeta.getNombre());
        cv.put("Tipo", planeta.getTipo());
        cv.put("Fecha", planeta.getFecha());
        cv.put("Diametro", planeta.getDiametro());

        return (DB.update("Planeta", cv, "id = " + p.getId(), null)) > 0;
    }

    public ArrayList<Planeta> verTodo() {
        lista.clear();
        Cursor cursor = DB.rawQuery("SELECT * FROM Planeta", null);

        if(cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                lista.add(new Planeta(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getInt(4)));
            } while(cursor.moveToNext());
        }
        return lista;
    }

    public Planeta getPlaneta(int id) {
        Cursor cursor = DB.rawQuery("SELECT * FROM Planeta", null);
        planeta = new Planeta(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getInt(4));
        return planeta;
    }
}
