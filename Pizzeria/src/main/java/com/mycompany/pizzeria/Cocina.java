package com.mycompany.pizzeria;

import java.util.HashMap;
import java.util.LinkedList;

//Esta clase maneja el stock de ingredientes y posee una cola FIFO para las ordenes
//El mesero controla el stock de ingredientes
//Los cocineros toman ordenes y las van realizando
public class Cocina {

    private LinkedList<Orden> listaOrdenes;
    private HashMap<String,Integer> stock;
    private int totalDelay;

    public Cocina(LinkedList<Orden> listaOrdenes, HashMap<String, Integer> stock) {
        this.listaOrdenes = listaOrdenes;
        this.stock = stock;
        this.totalDelay = 0;
    }

    public int getTotalDelay() {
        return totalDelay;
    }

    public void setTotalDelay(int totalDelay) {
        this.totalDelay = totalDelay;
    }

    public LinkedList<Orden> getListaOrdenes() {
        return listaOrdenes;
    }
    
    public Orden getFirstListaOrdenes(){
        return listaOrdenes.getFirst();
    }

    public void setListaOrdenes(LinkedList<Orden> listaOrdenes) {
        this.listaOrdenes = listaOrdenes;
    }
    
    public void removeFirstListaOrdenes(){
        this.listaOrdenes.removeFirst();
    }
    public void addLastListaOrdenes(Orden ord){
        this.listaOrdenes.addLast(ord);
    }

    public HashMap<String, Integer> getStock() {
        return stock;
    }

    public void setStock(HashMap<String, Integer> stock) {
        this.stock = stock;
    }
    
    
    
}
