package com.mycompany.pizzeria;

import java.util.HashMap;

public class Gerente extends Empleado {

    public Gerente(String id, boolean ocupado) {
        super(id, ocupado);
    }
    
    
    public void cerrar(){
        System.out.println("Salon cerrado");
    }
    
    /*public HashMap<String,Integer> renovarInventario (HashMap<String,Integer> stock){
        for (stock){}
        return stock;
    }**/
    
    public void lidiarConKaren(Cliente k){
        System.out.println("Lidiar con karen");
    }
}
