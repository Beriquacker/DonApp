package com.example.donapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.donapp.BD.DBCode;
import com.example.donapp.Clases.Usuario;

import java.io.IOException;

public class Perfil extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageButton imageButton;
    private Button actualizarButton;
    private String email;
    DBCode dbCode = new DBCode(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil);


        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);

        Bundle b = getIntent().getExtras();
        email = b.getString("email", "");


        Usuario usuario = dbCode.LeerUsuario(email);

        EditText editTextName = findViewById(R.id.name);
        editTextName.setText(usuario.getName());

        EditText editTextEmail = findViewById(R.id.email);
        editTextEmail.setText(usuario.getEmail());

        EditText editTextCodPostal = findViewById(R.id.codpostal);
        editTextCodPostal.setText(usuario.getCodPostal());

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);


        imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(view -> openImageChooser());

        actualizarButton = findViewById(R.id.actualizar);

        actualizarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String newNombre = ((EditText)findViewById(R.id.name)).getText().toString();
                String newEmail = ((EditText)findViewById(R.id.email)).getText().toString();
                String newCodPostal = ((EditText)findViewById(R.id.codpostal)).getText().toString();
                dbCode.updateUser(email, newNombre, newEmail, newCodPostal);
                email = newEmail;
                OpenHome();
            }
        });
    }

    // FOTO PERFIL
    private void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Selecciona una imagen"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imageButton.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // MENU
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

    private void OpenHome() {
        Intent intent = new Intent(this, Tienda.class);
        Bundle b = new Bundle();
        b.putString("email", email);
        intent.putExtras(b);
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

}
