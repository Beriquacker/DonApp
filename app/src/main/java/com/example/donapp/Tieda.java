package com.example.donapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Tienda extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArticleAdapter articleAdapter;
    private List<Articulo> articles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Configurar RecyclerView
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Obtener los datos de la base de datos
        articles = getArticlesFromDatabase();

        // Configurar el adaptador y asignarlo al RecyclerView
        articleAdapter = new ArticleAdapter(articles);
        recyclerView.setAdapter(articleAdapter);
    }

    private List<Article> getArticlesFromDatabase() {
        List<Article> articles = new ArrayList<>();

        // Abrir la base de datos
        SQLiteDatabase db = openOrCreateDatabase("mydatabase", MODE_PRIVATE, null);

        // Ejecutar la consulta para obtener los artículos
        Cursor cursor = db.rawQuery("SELECT * FROM articles", null);

        // Recorrer el cursor y agregar cada artículo a la lista
        while (cursor.moveToNext()) {
            int image = cursor.getInt(cursor.getColumnIndex("image"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String articleClass = cursor.getString(cursor.getColumnIndex("class"));
            String description = cursor.getString(cursor.getColumnIndex("description"));
            Article article = new Article(image, name, articleClass, description);
            articles.add(article);
        }

        // Cerrar el cursor y la base de datos
        cursor.close();
        db.close();

        return articles;
    }

    private class ArticleAdapter extends RecyclerView.Adapter<ArticleViewHolder> {
        private List<Article> articles;

        public ArticleAdapter(List<Article> articles) {
            this.articles = articles;
        }

        @NonNull
        @Override
        public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_article, parent, false);
            return new ArticleViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
            Article article = articles.get(position);
            holder.bind(article);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Crear el intent para iniciar la actividad de detalle
                    Intent intent = new Intent(Tienda.this, DetailActivity.class);
                    intent.putExtra(DetailActivity.EXTRA_ARTICLE, article);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return articles.size();
        }
    }

    private static class ArticleViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView nameView;
        private TextView classView;
        private TextView descriptionView;

        public ArticleViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview);
            nameView = itemView.findViewById(R.id.name);
            classView = itemView.findViewById(R.id.class);
            descriptionView = itemView.findViewById(R.id.description);
        }

        public void bind(Article article) {
            imageView.setImageResource(article.getImage());
            nameView.setText(article.getName());
            classView.setText(article.getClass());
            descriptionView.setText(article.getDescription());
        }
    }
}
