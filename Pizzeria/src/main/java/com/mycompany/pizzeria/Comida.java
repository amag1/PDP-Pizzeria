package com.mycompany.pizzeria;


import java.util.ArrayList;
import java.util.HashMap;


/**
 * Clase que simula una comida a traves de su nombre, ingredientes y precio.
 * Tambien tiene tiempos de preparacion y consumo
 * @author andres
 */
public class Comida {
    
    private String nombre;
    private HashMap<String,Integer> ingredientes;
    private int tiempoPreparacion;
    private int tiempoConsumo;
    private int precio;
    
    /**
     * Constructor vacio para comida.
     * Genera una comida con los atributos vacios.
     */
    public Comida() {
        nombre = "SinStock";
        ingredientes = new HashMap<String,Integer>();
        tiempoConsumo = 1;
        tiempoPreparacion = 1;
        precio = 0;
    }

    /**
     * Constructor para comida con todos sus atributos.
     * @param nombre Nombre de la comida.
     * @param ingredientes HashMap con los ingredientes (Ingrediente-Cantidad).
     * @param tiempoPreparacion int con el tiempo de preparacion.
     * @param tiempoConsumo int con el tiempo de consumo.
     * @param precio int con el precio.
     */
    public Comida(String nombre, HashMap<String,Integer> ingredientes, int tiempoPreparacion, int tiempoConsumo, int precio) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.tiempoPreparacion = tiempoPreparacion;
        this.tiempoConsumo = tiempoConsumo;
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
