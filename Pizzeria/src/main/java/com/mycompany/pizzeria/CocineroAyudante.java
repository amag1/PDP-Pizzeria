package com.mycompany.pizzeria;

/**
 * Clase que representa a un cocinero Ayudante.
 * Hereda las caracteristicas de un empleado.
 * Se encarga de hacer preparaciones
 * @author andres
 */
public class CocineroAyudante extends Empleado {
        
    /**
     * Constructor para CocineroAyudante: necesita la cocina a la que pertenece y el nombre.
     * @param cocina Cocina a la que pertenece el empleado.
     * @param id Nombre del cocinero.
     */
    public CocineroAyudante(Cocina cocina, String id) {
        super(id, false);
    }
    
    
   
    /**
     * Metodo que "hace una preparacion".
     * Toma una orden y reduce su tiempo de preparacion a la mitad.
     * @param ord La orden que deseamos preparar.
     * @return El tiempo que le tomo al cocinero prepararla.
     */
    public int hacerPreparacion(Orden ord){
        
        int count = 0;
        
        for (Comida item : ord.getListaComidas()){
            
            int tiempo = item.getTiempoPreparacion()/2;
            
            item.setTiempoPreparacion(tiempo);
            
            count += tiempo;
        }
        
        return count;
    }
    
}
