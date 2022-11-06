package com.mycompany.pizzeria;

/**
 * Subclase de Empleado que simula un cajero.
 * Se encarga de cobrar y guardar el total ganado.
 * @author andres.
 */
public class Cajero extends Empleado {
    
    private int totalGanado;
    
    /**
     * Constructor usando el de la superclase.
     * @param id El nombre del cajero.
     */
    public Cajero(String id) {
        super(id, false);
        this.totalGanado = 0;
    }

    public int getTotalGanado() {
        return totalGanado;
    }
    /**
     * Aumenta el total ganado.
     * @param amt La cantidad en la que queremos aumentarlo.
     */
    public void incrGanancias(int amt){
        totalGanado += amt;
    }  
}
