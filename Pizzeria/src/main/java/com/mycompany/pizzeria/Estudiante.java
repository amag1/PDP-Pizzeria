/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzeria;

/**
 * Subclase de Cliente para instanciar Estudiantes
 * @author longh
 */
public class Estudiante extends Cliente {
    public Estudiante (){
        super();
        this.id = "Estudiante";
        this.comidaFavorita = "Pizza";
        this.bebidaFavorita = "Gaseosa";
    }
}
