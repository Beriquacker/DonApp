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


public class Login extends AppCompatActivity {
    private EditText editTextCorreo;
    private EditText editTextContra;

    private static final String TAG = "MainActivity";

    DBCode dbCode = new DBCode(this);

    // PARA INICIAR SESION
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        editTextCorreo = findViewById(R.id.editTextTextEmailAddress);
        editTextContra = findViewById(R.id.editTextTextPassword);

        Button buttonEntrar = findViewById(R.id.iniciarsesion);
        buttonEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextCorreo.getText().toString();
                String Contra = editTextContra.getText().toString();

                if (dbCode.ExisteUsuario(email) != 0){
                    if(dbCode.ConfirmarCredenciales(email,Contra) == true)
                        openHome();
                    else
                        Toast.makeText(getApplicationContext(), "La contraseña o el email no coinciden", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getApplicationContext(), "El usuario no existe debe registrarse", Toast.LENGTH_SHORT).show();



            }
        });

        // PARA REGISTRARSE

        Button button = findViewById(R.id.registroboton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegistro();
            }
        });
    }

    private void openRegistro() {
        Intent intent = new Intent(this, Registro.class);
        startActivity(intent);
    }

    private void openHome() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

}
