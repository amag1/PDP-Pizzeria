package com.mycompany.pizzeria;

public class CocineroAyudante extends Empleado {
    
    protected Cocina cocina;

    public CocineroAyudante(Cocina cocina, String id, boolean ocupado) {
        super(id, ocupado);
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
