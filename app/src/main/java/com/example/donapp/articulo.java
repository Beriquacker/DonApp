package com.example.donapp;

public class articulo {
    private int image;
    private String name;
    private String category;
    private String description;

    public articulo(int image, String name, String category, String description) {
        this.image = image;
        this.name = name;
        this.category = category;
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }
}