package com.mycompany.pizzeria;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Clase que simula una bebida a partir de su nombre
 * @author andres
 */
public class Bebida {
    
    private String nombre;
    private int precio;

    /**
     * Constructor para una bebida vacia
     */
    public Bebida() {
        String nombre = "SinStock";
        int precio = 0;
    }
    
    /**
     * Constructor para bebida a partir de sus atribu.os
     * @param nombre El nombre de la bebida (String).
     * @param precio El precio de la bebida (int).
     */
    public Bebida(String nombre, int precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecio() {
        return precio;
    }   
}
