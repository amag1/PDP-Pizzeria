package com.mycompany.pizzeria;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * Clase que representa la cocina de la pizzeria.
 * Contiene colas FIFO para ordenes comlpetas y sin completar.
 * El stock de ingredientes se representa como un hashmap.
 * @author andres
 */
public class Cocina implements PartePizzeria{
    
    private LinkedList<Orden> listaEntregas;
    private LinkedList<Orden> listaOrdenes;
    private ArrayList<CocineroAyudante> cocinerosA;
    private ArrayList<CocineroJefe> cocinerosJ;
    private HashMap<String,Integer> stock;
    private int totalPreparadas;
    private HashMap<String,Integer> totalesComidas;
    
    /**
     * Crea una cocina dado un HashMap para el stock inicial.
     * Las demas variables estan inicialmente vacias.
     * @param stock 
     */
    public Cocina(HashMap<String, Integer> stock,ArrayList<CocineroAyudante> listaCocineros) {
        this.listaEntregas = new LinkedList();
        this.listaOrdenes = new LinkedList();
        this.stock = stock;
        this.totalPreparadas = 0;
        this.totalesComidas = new HashMap();
        this.cocinerosA = listaCocineros;
        this.cocinerosJ = new ArrayList<CocineroJefe>();
        for (CocineroAyudante c : listaCocineros){
            if(CocineroJefe.class.isInstance(c)){
                this.cocinerosJ.add((CocineroJefe) c);
            }
        }
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
    
    
    public int getTotalPreparadas() {
        return totalPreparadas;
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
            
            Iterator<Orden> iter = listaOrdenes.iterator();
            boolean flag = true;
            
            while (iter.hasNext() && flag){
                Orden ord = iter.next();
                if (!ord.isPreparada()){
                    cocinero.hacerPreparacion(ord);
                }
            }
        }
    }
    
    public void addLastListaEntregas(Orden ord){
        listaEntregas.add(ord);
    }
    
    public Orden getFirstListaEntregas(){
        return this.listaEntregas.getFirst();
    }
    
    public void removeFirstListaEntregas(){
        this.listaEntregas.remove(0);
    }
    
    /**
     * Metodo para cocinar en cada turno.
     * Recorre la lista de cocineros y a cada uno lo manda a cocinar una orden.
     * Se encarga de quitar las ordenes de la cola.
     */
    public void cocinarTurnoActual(){
        
        if (listaOrdenes.size() > 0){
            
            for (CocineroJefe cocinero : cocinerosJ){
            
            if (cocinero.cocinar(getFirstListaOrdenes()) == 0){
                
                Orden ultimaOrden = getFirstListaOrdenes();
                addLastListaEntregas(ultimaOrden);
                
                actualizarInventario(ultimaOrden);
                
                for (Comida com : ultimaOrden.getListaComidas()){
                    String nombre = com.getNombre();
                    
                    if (!totalesComidas.containsKey(com))
                        totalesComidas.put(nombre,1);
                    
                    else
                        
                        totalesComidas.put(nombre, totalesComidas.get(nombre) +1);
                }
                
                System.out.println("remove");
                removeFirstListaOrdenes();
                totalPreparadas++;
            }
            
        }
        }
    
    }
    
    public void actualizarInventario(Orden ord){
        for (Comida c : ord.getListaComidas()){
            for (HashMap.Entry<String,Integer> entry : c.getIngredientes().entrySet()){
                String ingrediente = entry.getKey();
                int cantidad = entry.getValue();
                
                c.getIngredientes().put(ingrediente, c.getIngredientes().get(ingrediente) - cantidad);
            }
        }
    }

    @Override
    public void mostrarEstado() {
        System.out.println("Estado actual de los ingredientes:");
        for (String ingr : stock.keySet()){
            String amt = stock.get(ingr).toString();
            System.out.println(ingr + ": " + amt);
        }
    }

    @Override
    public void mostrarTotales() {
        System.out.println("aca falta algo");
    }
}
