/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzeria;

/**
 *
 * @author andres
 */
abstract class Empleado {
    
    protected String id;
    protected boolean ocupado;

    public Empleado(String id, boolean ocupado) {
        this.id = id;
        this.ocupado = ocupado;
    }
    
    
}
