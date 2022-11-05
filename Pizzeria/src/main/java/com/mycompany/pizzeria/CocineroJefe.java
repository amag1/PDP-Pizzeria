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
    public CocineroJefe(String id) {
        super(id);
    }
    
    /**
     * Toma la primera orden que haya para hacer y disminuye en 1 el tiempo restante.
     * Desde la cocina se le pasa la orden a preparar.
     * @return 0, si la orden fue terminada; 1, si la comida fue terminada pero la orden no; 2, en otro caso.
     */
    public int cocinar(Orden ord){
        
        int tiempoRestante = ord.getTiempoPreparacion();
        System.out.println("tiempo restante: " + tiempoRestante);
        if (tiempoRestante == 1){
            
            ord.setTiempoPreparacion(0);
            return 0;
        }
        else{
            ord.setTiempoPreparacion(tiempoRestante - 1);
            return 1;
        }
            
            
           
}   

}
