package com.example.donapp;

<<<<<<<< HEAD:app/src/main/java/com/example/donapp/Articulo.java
public class Articulo {
========
import java.io.Serializable;

public class Articulo implements Serializable {
>>>>>>>> master:app/src/main/java/com/example/donapp/articulo.java
    private int image;
    private String name;
    private String state;
    private String description;

<<<<<<<< HEAD:app/src/main/java/com/example/donapp/Articulo.java
    public Articulo(String name, String description, String state) {
        // Asigna las variables de instancia usando los argumentos proporcionados
========
    public Articulo(int image, String name, String category, String description) {
        this.image = image;
>>>>>>>> master:app/src/main/java/com/example/donapp/articulo.java
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
