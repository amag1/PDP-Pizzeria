/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzeria;

/**
 *
 * @author andres
 */
public class CocineroJefe extends CocineroAyudante {

    public CocineroJefe(Cocina cocina) {
        super(cocina);
    }
    
    //Dada una orden, devuelve el tiempo de preparacion total
    public int cocinar(Orden ord){
        int count = 0;
        
        for (Comida c : ord.getListaComidas()){
            
            count += c.getTiempoPreparacion();
        }
        
        return count;
    }
    
}
