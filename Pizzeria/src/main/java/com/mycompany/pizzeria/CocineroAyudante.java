/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzeria;

/**
 *
 * @author andres
 */
public class CocineroAyudante {
    
    private Cocina cocina;
    
    public CocineroAyudante(Cocina cocina) {
        this.cocina = cocina;
    }

    public Cocina getCocina() {
        return cocina;
    }

    public void setCocina(Cocina cocina) {
        this.cocina = cocina;
    }
    
    //metodo que "hace una preparacion"
    //Dada una orden, reduce su tiempo de preparacion a la mitad
    //Devuelve lo que demoro en hacerlo
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
