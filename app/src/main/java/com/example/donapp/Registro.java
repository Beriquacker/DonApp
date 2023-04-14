package com.example.donapp;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageButton;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.util.Log;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.donapp.BD.DBCode;




public class Registro extends AppCompatActivity {
    private EditText editTextNombre;
    private EditText editTextEmail;
    private EditText editTextContraseña;
    private EditText editTextRepContraseña;
    private EditText editTextCodPostal;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);
        // Instancia de la BBDD
        DBCode dbCode = new DBCode(this);

        editTextNombre = findViewById(R.id.editTextTextPersonName);
        editTextEmail = findViewById(R.id.editTextTextEmailAddress2);
        editTextContraseña = findViewById(R.id.editTextTextPassword2);
        editTextRepContraseña = findViewById(R.id.editTextTextPassword3);
        editTextCodPostal = findViewById(R.id.editTextNumber);

        Button BotonEnviar = findViewById(R.id.button2);
        BotonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = editTextNombre.getText().toString();
                String Contra = editTextContraseña.getText().toString();
                String ContraRep = editTextRepContraseña.getText().toString();
                String Email = editTextEmail.getText().toString();
                String CodPostal = editTextCodPostal.getText().toString();

                if(dbCode.ExisteUsuario(Email)== 0 )
                {
                    if (Contra.equals(ContraRep)){
                        dbCode.addUser(nombre, Contra, Email, CodPostal);
                        Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_SHORT).show();
                        toInicio();
                    }
                    else
                        Toast.makeText(getApplicationContext(), "Las constraseñas no coinciden", Toast.LENGTH_SHORT).show();

                }
                else
                    Toast.makeText(getApplicationContext(), "El usuario ya existe", Toast.LENGTH_SHORT).show();



            }
        });

        Button BotonVolver = findViewById(R.id.buttonvolver);

        BotonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toInicio();
            }
        });
    }
    private void toInicio(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }





}



