package com.example.donapp;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.SharedPreferences;
import com.example.donapp.BD.DBCode;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Ajustes extends AppCompatActivity {

    private EditText etEmailEliminar;
    private Button btnEliminarCuenta;
    DBCode dbCode = new DBCode(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajustes);


        Toolbar toolbar = findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar);

        etEmailEliminar = findViewById(R.id.et_email_eliminar);
        btnEliminarCuenta = findViewById(R.id.btn_eliminar_cuenta);

        btnEliminarCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmailEliminar.getText().toString();

                if (dbCode.ExisteUsuario(email) != 0) {
                    dbCode.deleteAccount(email);
                    Toast.makeText(Ajustes.this, "Cuenta eliminada con éxito", Toast.LENGTH_SHORT).show();
                    etEmailEliminar.setText(""); // Limpia el campo de correo electrónico después de eliminar la cuenta
                } else {
                    Toast.makeText(Ajustes.this, "El correo ingresado no está registrado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.idhome:
                OpenHome();
                Toast.makeText(this, "Home seleccionado", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.idperfil:
                OpenPerfil();
                Toast.makeText(this, "Perfil seleccionado", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.idajustes:
                OpenAjustes();
                Toast.makeText(this, "Ajustes seleccionado", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.idcerrarSesion:
                openLogin();
                Toast.makeText(this, "Se ha cerrado la sesión", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.idbuscar:
                openSubirProducto();
                Toast.makeText(this, "Se ha cerrado la sesión", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    private void openSubirProducto() {
        Intent intent = new Intent(this, SubirArticuloActivity.class);
        startActivity(intent);
    }
    private void openLogin() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
    private void OpenHome()
    {
        Intent intent = new Intent(this, Tienda.class);
        startActivity(intent);
    }
    private void OpenPerfil()
    {
        Intent intent = new Intent(this, Perfil.class);
        startActivity(intent);
    }
    private void OpenAjustes()
    {
        Intent intent = new Intent(this, Ajustes.class);
        startActivity(intent);
    }
}