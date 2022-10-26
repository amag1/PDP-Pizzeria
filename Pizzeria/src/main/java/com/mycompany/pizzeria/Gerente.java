package com.mycompany.pizzeria;

public class Gerente extends Empleado {

    public Gerente(String id, boolean ocupado) {
        super(id, ocupado);
    }
    
    
    public void cerrar(){
        System.out.println("Salon cerrado");
    }
    
    public void lidiarConKaren(Cliente k){
        System.out.println("Lidiar con karen");
    }
}
