package com.example.donapp;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.donapp.BD.DBCode;

public class SubirArticuloActivity extends AppCompatActivity {

    private EditText articuloNombre;
    private EditText articuloDescripcion;
    private EditText articuloEstado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subir_articulo);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar6);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(item -> onOptionsItemSelected(item));

        articuloNombre = findViewById(R.id.articulo_nombre);
        articuloDescripcion = findViewById(R.id.articulo_descripcion);
        articuloEstado = findViewById(R.id.articulo_estado);

        Button botonSubirArticulo = findViewById(R.id.boton_subir_articulo);
        botonSubirArticulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subirArticulo();
            }
        });
    }
    // MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Crear un nuevo objeto MenuInflater usando el contexto de la aplicación
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

    private void OpenHome() {
        Intent intent = new Intent(this, Tienda.class);
        startActivity(intent);
    }

    private void OpenPerfil() {
        Intent intent = new Intent(this, Perfil.class);
        startActivity(intent);
    }

    private void OpenAjustes() {
        Intent intent = new Intent(this, Ajustes.class);
        startActivity(intent);
    }

    private void subirArticulo() {
        String nombre = articuloNombre.getText().toString().trim();
        String descripcion = articuloDescripcion.getText().toString().trim();
        String estado = articuloEstado.getText().toString().trim();
        String usuario = "nombre_de_usuario"; // Reemplaza esto con el nombre de usuario actual

        if (TextUtils.isEmpty(nombre) || TextUtils.isEmpty(descripcion) || TextUtils.isEmpty(estado)) {
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
        } else {
            DBCode dbCode = new DBCode(this);
            dbCode.addArticle(nombre, descripcion, estado, usuario);
            Toast.makeText(this, "Artículo subido con éxito", Toast.LENGTH_SHORT).show();

            // Limpiar los campos de texto
            articuloNombre.setText("");
            articuloDescripcion.setText("");
            articuloEstado.setText("");
        }
    }
}
