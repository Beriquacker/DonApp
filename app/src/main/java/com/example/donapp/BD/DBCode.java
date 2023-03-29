package com.example.donapp.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBCode extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String  DATABASE_NOMBRE = "DonAPP.db";
    public static final String  TABLE_USUARIOS = "t_usuarios";
    public static final String  TABLE_TIENDA = "t_tienda";


    public DBCode(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_USUARIOS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username TEXT NOT NULL," +
                "name TEXT NOT NULL," +
                "ubication TEXT NOT NULL," +
                "password TEXT NOT NULL," +
                "email TEXT)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_TIENDA + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "articulo TEXT NOT NULL," +
                "descripcion TEXT NOT NULL," +
                "estado TEXT NOT NULL," +
                "usuario TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_USUARIOS);
        onCreate(sqLiteDatabase);

    }
}
