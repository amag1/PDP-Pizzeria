package com.mycompany.pizzeria;


import java.util.ArrayList;
import java.util.HashMap;

public class Comida {
    
    private String nombre;
    private HashMap<String,Integer> ingredientes;
    private int tiempoPreparacion;
    private int tiempoConsumo;
    private int precio;

    public Comida() {
    }

    
    public Comida(String nombre, HashMap<String,Integer> ingredientes, int tiempoPreparacion, int tiempoConsumo, int precio) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.tiempoPreparacion = tiempoPreparacion;
        this.tiempoConsumo = tiempoConsumo;
        this.precio = precio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIngredientes(HashMap<String,Integer> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public void setTiempoPreparacion(int tiempoPreparacion) {
        this.tiempoPreparacion = tiempoPreparacion;
    }

    public void setTiempoConsumo(int tiempoConsumo) {
        this.tiempoConsumo = tiempoConsumo;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public HashMap<String,Integer> getIngredientes() {
        return ingredientes;
    }

    public int getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public int getTiempoConsumo() {
        return tiempoConsumo;
    }

    public int getPrecio() {
        return precio;
    }
    
    
}
