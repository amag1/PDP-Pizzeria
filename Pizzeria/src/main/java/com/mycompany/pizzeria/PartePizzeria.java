package com.mycompany.pizzeria;

/**
 * Interfaz que debe implementar una parte de la pizzeria.
 * @author andres
 */
public interface PartePizzeria {
    
    /**
     * Una parte de la pizzeria debe poder mostrar su estado actual.
     */
    public void mostrarEstado();
    
    /**
     * Finalmente, tambien debe poder informar su estado final.
     */
    public void mostrarTotales();
}
