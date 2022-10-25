package com.mycompany.pizzeria;

public class CocineroJefe extends CocineroAyudante {

    public CocineroJefe(Cocina cocina) {
        super(cocina);
    }
    
    //Dada una orden, devuelve el tiempo de preparacion total
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
