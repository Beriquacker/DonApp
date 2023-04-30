package com.example.donapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private EditText searchBar;
    private RecyclerView recyclerView;
    private Tienda.ArticleAdapter articleAdapter;
    private List<Articulo> articles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Configurar RecyclerView
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Obtener los datos de la base de datos
        Tienda tiendaActivity = new Tienda();
        articles = tiendaActivity.getArticlesFromDatabase();

        // Configurar el adaptador y asignarlo al RecyclerView
        articleAdapter = tiendaActivity.new ArticleAdapter(articles);
        recyclerView.setAdapter(articleAdapter);

        // Configurar la barra de búsqueda
        searchBar = findViewById(R.id.search_bar);
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterArticles(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void filterArticles(String searchText) {
        List<Articulo> filteredArticles = new ArrayList<>();

        for (Articulo article : articles) {
            if (article.getName().toLowerCase().contains(searchText.toLowerCase())) {
                filteredArticles.add(article);
            }
        }

        articleAdapter.articles = filteredArticles;
        articleAdapter.notifyDataSetChanged();
    }
}