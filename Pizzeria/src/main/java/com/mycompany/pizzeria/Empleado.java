/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzeria;

/**
 * Clase abstracta para simular empleados
 * Cada tipo de empleado deberia ordenar de ella
 * @author andres
 */
abstract class Empleado {
    
    protected String id;
    protected boolean ocupado;

    public Empleado(String id, boolean ocupado) {
        this.id = id;
        this.ocupado = ocupado;
    }

    public String getId() {
        return id;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }
    
    
    
    
}
