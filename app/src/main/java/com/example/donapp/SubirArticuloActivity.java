package com.example.donapp;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
