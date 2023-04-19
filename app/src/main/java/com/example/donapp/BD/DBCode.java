package com.example.donapp.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import androidx.annotation.Nullable;

public class DBCode extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String  DATABASE_NOMBRE = "DonAPP.db";
    public static final String  TABLE_USUARIOS = "t_usuarios";
    public static final String  TABLE_TIENDA = "t_tienda";
    public static final String  TABLE_DONACION = "t_donacion";
    public static final String  TABLE_ONG = "t_ong";

    public DBCode(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_USUARIOS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "contra TEXT NOT NULL," +
                "CodPostal TEXT NOT NULL," +
                "email TEXT NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_TIENDA + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "articulo TEXT NOT NULL," +
                "descripcion TEXT NOT NULL," +
                "estado TEXT NOT NULL," +
                "usuario TEXT NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_DONACION + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NumArt TEXT NOT NULL," +
                "idUsuario TEXT NOT NULL," +
                "NumDonacion TEXT NOT NULL," +
                " TEXT NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_ONG + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "CIF TEXT NOT NULL," +
                "contraseña TEXT NOT NULL," +
                "nombre_e TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_USUARIOS);
        onCreate(sqLiteDatabase);
    }

    // AÑADIR USUARIO
    public void addUser(String name, String email, String password, String CodPostal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("CodPostal", CodPostal);
        values.put("contra", password);
        values.put("email", email);

        db.insert(TABLE_USUARIOS, null, values);
        db.close();
    }

    public int ExisteUsuario(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_USUARIOS, // Nombre de la tabla
                new String[]{"id"}, // Columnas que queremos obtener
                "email = ?", // Condición (WHERE)
                new String[]{email}, // Valores para reemplazar '?' en la condición
                null, // GROUP BY
                null, // HAVING
                null // ORDER BY
        );

        int count = cursor.getCount(); // Obtener el número de filas devueltas
        cursor.close();
        return count;
    }

    public boolean ConfirmarCredenciales(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_USUARIOS, // Nombre de la tabla
                new String[]{"id"}, // Columnas que queremos obtener
                "email = ? AND contra = ?", // Condición (WHERE)
                new String[]{email, password}, // Valores para reemplazar '?' en la condición
                null, // GROUP BY
                null, // HAVING
                null // ORDER BY
        );

        int count = cursor.getCount(); // Obtener el número de filas devueltas
        cursor.close();
        return count > 0;
    }


    /*
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Nota: Asegúrate de incrementar DATABASE_VERSION cuando realices cambios en el esquema de la base de datos

        // Actualizar las tablas existentes en lugar de eliminarlas y recrearlas
        if (oldVersion < 2) {
            // Supongamos que necesitas agregar una columna "phone" en la versión 2 de la base de datos
            db.execSQL("ALTER TABLE " + TABLE_USUARIOS + " ADD COLUMN phone TEXT");
        }

        if (oldVersion < 3) {
            // Supongamos que necesitas agregar otra columna "address" en la versión 3 de la base de datos
            db.execSQL("ALTER TABLE " + TABLE_USUARIOS + " ADD COLUMN address TEXT");
        }

        // Continuar con otros cambios en la base de datos según sea necesario
    }
        */

}
