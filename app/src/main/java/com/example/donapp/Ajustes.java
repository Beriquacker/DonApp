package com.example.donapp;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Ajustes extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajustes);


        Toolbar toolbar = findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar);
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void openLogin() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
    private void OpenHome()
    {
        Intent intent = new Intent(this, Home.class);
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