package com.example.donapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

public class ActividadMenu extends AppCompatActivity implements SearchView.OnQueryTextListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu); // Reemplaza 'R.menu.tu_menu' con el nombre de tu archivo de menú XML

        MenuItem searchItem = menu.findItem(R.id.idbuscar);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(this);

        return super.onCreateOptionsMenu(menu);
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

    @Override
    public boolean onQueryTextSubmit(String query) {
        // Aquí puedes realizar la búsqueda cuando el usuario presiona "Enviar" en el teclado
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        // Aquí puedes realizar la búsqueda en tiempo real mientras el usuario escribe en la barra de búsqueda
        return false;
    }
}

