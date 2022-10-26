package com.mycompany.pizzeria;

public class Cajero extends Empleado {
    
    private int totalGanado;

    public Cajero(int totalGanado, String id, boolean ocupado) {
        super(id, ocupado);
        this.totalGanado = totalGanado;
    }

    public int getTotalGanado() {
        return totalGanado;
    }

    public void setTotalGanado(int totalGanado) {
        this.totalGanado = totalGanado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }
    
    
}
