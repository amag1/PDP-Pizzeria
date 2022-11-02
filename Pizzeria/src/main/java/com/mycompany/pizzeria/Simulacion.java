package com.mycompany.pizzeria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Clase principal: contiene al metodo main. Desde aqui debe inicializarse la simulacion
 * @author andres
 */
public class Simulacion {
    private boolean findeSemana;
    private static Pizzeria laTana;
            
    public static void main(String[] args) {
        
        LinkedList<Mesa> listaMesas = new LinkedList<Mesa>();
        for (int i=1; i<=8;i++){
            listaMesas.add(new Mesa(i, 2));
            }
        for (int i=9; i<=24;i++){
            listaMesas.add(new Mesa(i, 4));
            }
        
        LinkedList<Mesero> listaMeseros = new LinkedList<Mesero>();
        listaMeseros.add(new Mesero("Tonio"));
        listaMeseros.add(new Mesero("Stella"));
        listaMeseros.add(new Mesero("Maria"));
        
        ArrayList<Comida> menuComida = new ArrayList<Comida>();
        HashMap<String,Integer> ingPizza = new HashMap<String,Integer>();
        
        HashMap<String,Integer> ingBurger = new HashMap<String,Integer>();
        menuComida.add(new Comida("Pizza",ingPizza,20,30,5));
        menuComida.add(new Comida("Hamburguesa",ingBurger,15,15,3));
        
        ArrayList<Bebida> menuBebida = new ArrayList<Bebida>();
        menuBebida.add(new Bebida("Gaseosa",15,1));
        menuBebida.add(new Bebida("Cerveza",20,2));
        
        //this.laTana = new Pizzeria(listaMesas,listaMeseros,menuComida,menuBebida);
        
    }
    
}
