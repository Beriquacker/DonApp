package com.example.donapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.donapp.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.donapp.BD.DBCode;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

public class Tienda extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArticleAdapter articleAdapter;
    private List<Articulo> articles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda);

        // Configurar RecyclerView
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Obtener los datos de la base de datos
        articles = getArticlesFromDatabase();

        // Configurar el adaptador y asignarlo al RecyclerView
        articleAdapter = new ArticleAdapter(articles);
        recyclerView.setAdapter(articleAdapter);

        // configurar el menu
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar5);
        setSupportActionBar(toolbar);

        // Establecer un OnMenuItemClickListener en el Toolbar
        toolbar.setOnMenuItemClickListener(item -> onOptionsItemSelected(item));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Crear un nuevo objeto MenuInflater usando el contexto de la aplicación
        MenuInflater inflater = new MenuInflater(getApplicationContext());
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

    public List<Articulo> getArticlesFromDatabase() {
        List<Articulo> articles = new ArrayList<>();

        // Abrir la base de datos
        DBCode dbHelper = new DBCode(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Ejecutar la consulta para obtener los artículos
        Cursor cursor = db.rawQuery("SELECT * FROM " + DBCode.TABLE_TIENDA, null);

        // Recorrer el cursor y agregar cada artículo a la lista
        // Recorrer el cursor y agregar cada artículo a la lista

        while (cursor.moveToNext()) {
            int nameIndex = cursor.getColumnIndex("articulo");
            int descriptionIndex = cursor.getColumnIndex("descripcion");
            int stateIndex = cursor.getColumnIndex("estado");

            if (nameIndex != -1 && descriptionIndex != -1 && stateIndex != -1) {
                String name = cursor.getString(nameIndex);
                String description = cursor.getString(descriptionIndex);
                String state = cursor.getString(stateIndex);
                Articulo article = new Articulo(name, description, state);
                articles.add(article);
            }
        }


        // Cerrar el cursor y la base de datos
        cursor.close();
        db.close();

        return articles;
    }

    public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
        public List<Articulo> articles;

        public ArticleAdapter(List<Articulo> articles) {
            this.articles = articles;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Articulo article = articles.get(position);
            // Aquí puedes establecer la imagen del artículo
            holder.nameTextView.setText(article.getName());
            holder.stateTextView.setText(article.getState());
            holder.descriptionTextView.setText(article.getDescription());
        }

        @Override
        public int getItemCount() {
            return articles.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ImageView imageView;
            public TextView nameTextView;
            public TextView stateTextView;
            public TextView descriptionTextView;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.imageview);
                nameTextView = itemView.findViewById(R.id.name);
                stateTextView = itemView.findViewById(R.id.state);
                descriptionTextView = itemView.findViewById(R.id.description);
            }
        }



    }
}