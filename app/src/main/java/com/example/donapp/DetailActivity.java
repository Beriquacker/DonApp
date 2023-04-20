package com.example.donapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_ARTICLE = "extra_article";

    private ImageView imageView;
    private TextView nameView;
    private TextView categoryView;
    private TextView descriptionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageView = findViewById(R.id.imageview);
        nameView = findViewById(R.id.name);
        categoryView = findViewById(R.id.category);
        descriptionView = findViewById(R.id.description);

        Intent intent = getIntent();
        Articulo article = (Articulo) intent.getSerializableExtra(EXTRA_ARTICLE);

        if (article != null) {
            imageView.setImageResource(article.getImage());
            nameView.setText(article.getName());
            categoryView.setText(article.getCategory());
            descriptionView.setText(article.getDescription());
        }
    }
}