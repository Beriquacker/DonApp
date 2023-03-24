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

        editTextNombre = findViewById(R.id.editTextTextPersonName);
        editTextEmail = findViewById(R.id.editTextTextEmailAddress2);
        editTextContraseña = findViewById(R.id.editTextTextPassword2);
        editTextRepContraseña = findViewById(R.id.editTextTextPassword3);
        editTextCodPostal = findViewById(R.id.editTextNumber);

        Button buttonMostrarNombre = findViewById(R.id.button2);
        buttonMostrarNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = editTextNombre.getText().toString();
                String Contra = editTextContraseña.getText().toString();
                String ContraRep = editTextRepContraseña.getText().toString();
                String Email = editTextEmail.getText().toString();
                String CodPostal = editTextCodPostal.getText().toString();

                Log.d(TAG,nombre);
                Log.d(TAG,Contra);
                Log.d(TAG,ContraRep);
                Log.d(TAG,Email);
                Log.d(TAG,CodPostal);
            }
        });
    }


}
