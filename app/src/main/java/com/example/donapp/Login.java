package com.example.donapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

                if (dbCode.ExisteUsuario(email) != 0) {
                    if (dbCode.ConfirmarCredenciales(email, Contra) == true) {
                        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                        myEdit.putString("email", email);
                        openHome();
                    } else
                        Toast.makeText(getApplicationContext(), "La contraseña o el email no coinciden", Toast.LENGTH_SHORT).show();
                } else
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
        Intent intent = new Intent(this, Tienda.class);
        Bundle b = new Bundle();
        b.putString("email", editTextCorreo.getText().toString());
        intent.putExtras(b);
        startActivity(intent);
    }

}
