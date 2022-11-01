package com.mycompany.pizzeria;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.ArrayList;

/**
 * Clase que representa la cocina de la pizzeria.
 * Contiene colas FIFO para ordenes comlpetas y sin completar.
 * El stock de ingredientes se representa como un hashmap.
 * @author andres
 */
public class Cocina {
    
    private LinkedList<Orden> listaEntregas;
    private LinkedList<Orden> listaOrdenes;
    private ArrayList<CocineroAyudante> cocinerosA;
    private ArrayList<CocineroJefe> cocinerosJ;
    private HashMap<String,Integer> stock;
    private int totalDelay;
    
    /**
     * Crea una cocina dado un HashMap para el stock inicial.
     * Las demas variables estan inicialmente vacias.
     * @param stock 
     */
    public Cocina(HashMap<String, Integer> stock) {
        this.listaEntregas = new LinkedList();
        this.listaOrdenes = new LinkedList();
        this.stock = stock;
        this.totalDelay = 0;
    }

    public LinkedList<Orden> getListaEntregas() {
        return listaEntregas;
    }

    public void setListaEntregas(LinkedList<Orden> listaEntregas) {
        this.listaEntregas = listaEntregas;
    }

    public ArrayList<CocineroAyudante> getCocinerosA() {
        return cocinerosA;
    }

    public void setCocinerosA(ArrayList<CocineroAyudante> CocinerosA) {
        this.cocinerosA = CocinerosA;
    }

    public ArrayList<CocineroJefe> getCocinerosJ() {
        return cocinerosJ;
    }

    public void setCocinerosJ(ArrayList<CocineroJefe> CocinerosJ) {
        this.cocinerosJ = CocinerosJ;
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
    
    /**
     * Obtiene el primer elemento de la lista de ordenes.
     * @return La proxima orden que debe realizarse
     */
    public Orden getFirstListaOrdenes(){
        return listaOrdenes.getFirst();
    }

    public void setListaOrdenes(LinkedList<Orden> listaOrdenes) {
        this.listaOrdenes = listaOrdenes;
    }
    
    /**
     * Elimina el primer elemento de la lista de ordenes.
     * @see getFirstListaOrdenes()
     */
    public void removeFirstListaOrdenes(){
        this.listaOrdenes.removeFirst();
    }
    
    /**
     * Aniade una nueva orden al final de la cola.
     * @param ord La orden que queremos agregar
     */
    public void addLastListaOrdenes(Orden ord){
        this.listaOrdenes.addLast(ord);
    }

    public HashMap<String, Integer> getStock() {
        return stock;
    }

    public void setStock(HashMap<String, Integer> stock) {
        this.stock = stock;
    }
    
    public void hacerPreparaciones(){
        
        for (CocineroAyudante cocinero : cocinerosA){
            
            for (Orden ord : listaOrdenes){
                
                if (!ord.isPreparada()){
                    
                    cocinero.hacerPreparacion(ord);
                    
                }
            }
        }
    }
    
    public void addLastListaEntregas(Orden ord){
        listaEntregas.add(ord);
    }
    
    public void cocinarTurno(){
        
        for (CocineroJefe cocinero : cocinerosJ){
            
            Comida plato = getFirstListaOrdenes().getListaComidas().get(0);
            
            if (plato.getTiempoPreparacion() == 1){
                
                addLastListaEntregas(getFirstListaOrdenes());
                removeFirstListaOrdenes();
            }
            
            else{
                
                plato.setTiempoPreparacion(plato.getTiempoPreparacion() - 1);
                
            }
        }
    }
    
}
