package com.mycompany.pizzeria;

/**
 * Clase que representa al cocineroJefe.
 * Hereda los comportamientos de un cocineroAyudante, pero es el unico que puede 
 * terminar las preparaciones
 * @author andres
 */
public class CocineroJefe extends CocineroAyudante {
    
    /**
     * Constructor para el cocinero.
     * @param cocina La cocina a la que pertenece.
     * @param id ID del cocinero.
     */
    public CocineroJefe(Cocina cocina, String id) {
        super(cocina, id);
    }
    
    /**
     * Toma la primera orden que haya para hacer y termina con su preparacion.
     * Necesita accerder a la cola FIFO de ordenes presente en la cocina.
     * @return Un entero representando el tiempo que le tomo cocinar.
     */
    public int cocinar(){
        this.ocupado = true;
        Orden first = cocina.getFirstListaOrdenes();
        
        int count = 0;
        
        for (Comida c : first.getListaComidas()){
            
            count += c.getTiempoPreparacion();
        }
        
        this.cocina.removeFirstListaOrdenes();
        
        this.cocina.setTotalDelay(this.cocina.getTotalDelay() + count);
        
        this.ocupado = false;
        return count;
    }
    
}
