package com.example.donapp;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Login extends AppCompatActivity {
    private EditText editTextCorreo;
    private EditText editTextContra;

    private static final String TAG = "MainActivity";

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
                Log.d(TAG,email);
                Log.d(TAG,Contra);

                if (email.equals("admin") && Contra.equals("root")) {
                    openHome();
                }
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
