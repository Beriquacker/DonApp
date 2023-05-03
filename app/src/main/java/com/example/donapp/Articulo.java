package com.example.donapp;

public class Articulo {
    private int image;
    private final String name;
    private final String state;
    private final String description;

    public Articulo(String name, String description, String state) {
        // Asigna las variables de instancia usando los argumentos proporcionados
        this.name = name;
        this.description = description;
        this.state = state;
    }


    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    public String getDescription() {
        return description;
    }
}
